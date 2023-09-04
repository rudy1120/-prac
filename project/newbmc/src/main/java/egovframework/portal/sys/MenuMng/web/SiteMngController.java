package egovframework.portal.sys.MenuMng.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.dept.service.DeptService;
import egovframework.portal.dept.vo.DeptVO;
import egovframework.portal.sys.MenuMng.SiteGubun;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.MenuMng.vo.SiteGroupJsonVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.TUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.arnx.jsonic.JSON;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014-10-07		엄동건				최초 생성 및 코딩
 * 2016-06-08		권태성				CMS 연계관련 기능 추가
 * 2017-01-05		J.Ryeon Lee			시큐어 코딩 처리
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 1. 5.
 */
@Controller("SiteMngController")
public class SiteMngController {

	@Autowired
	protected SiteMngService siteMngService;
	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected DeptService deptService;

	private static final Logger LOGGER = LogManager.getLogger();

	@RequestMapping("/sys/totalAdminMng/siteMng/list.do")
	public String siteList(@ModelAttribute("searchVO") MenusMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String mId = request.getParameter("mId");

		String searchType = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchType", "")); // 검색타입
		String searchTxt = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchTxt", "")); // 검색어
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
		int perPage = Constant.ADMIN_LIST_CUTRECORD;
		int cpage = 1;
		if (page > 1) {
			cpage = (page - 1) * perPage + 1;
		}

		perPage = page * perPage;
		searchVO.setFirstIndex(cpage);
		searchVO.setLastIndex(perPage);
		String search_query = getSearchQuery(searchType, searchTxt);

		int listCutrecord = Constant.ADMIN_LIST_CUTRECORD;
		int listCutpage = Constant.LIST_CUTPAGE;

		// 입력값 설정
		searchVO.setSearchQuery(search_query);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(page);
		if (listCutrecord > 0) {
			paginationInfo.setRecordCountPerPage(listCutrecord);
		} else {
			paginationInfo.setRecordCountPerPage(20);
		}

		if (listCutpage > 0) {
			paginationInfo.setPageSize(listCutpage);
		} else {
			paginationInfo.setPageSize(10);
		}

		int totalCnt = siteMngService.getSiteListCnt(searchVO);
		model.addAttribute("siteListCnt", totalCnt);
		model.addAttribute("siteList", siteMngService.getSiteList(searchVO));

		if (totalCnt > 0) {
			paginationInfo.setTotalRecordCount(totalCnt);
		} else {
			paginationInfo.setTotalRecordCount(0);
		}

		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("page", page);
		model.addAttribute("siteGubunList", SiteGubun.values());

		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/siteMng/list";
	}

	private String getSearchQuery(String searchType, String searchTxt) {
		String search_query = ""; // 쿼리문

		// 검색쿼리 설정
		if (!"".equals(searchType) && !"".equals(searchTxt)) {
			search_query = " where " + searchType + " LIKE '%" + searchTxt + "%'";
		}
		return search_query;
	}

	@RequestMapping("/sys/totalAdminMng/siteMng/view.do")
	public String siteDetailPage(@ModelAttribute("searchVO") MenusMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {

		model.addAttribute("result", siteMngService.getSite(searchVO));
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/siteMng/view";
	}

	@RequestMapping("/sys/totalAdminMng/siteMng/insertPage.do")
	public String siteInsertPage(@ModelAttribute("searchVO") MenusMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {

		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
		
		List<DeptVO> deptList = new ArrayList<DeptVO>();
		
		deptList = deptService.getDeptCategoryList();
		
		model.addAttribute("siteGubunList", SiteGubun.values());
		model.addAttribute("deptList", deptList);

		return "/sys/totalAdminMng/siteMng/insert";
	}

	@RequestMapping("/sys/totalAdminMng/siteMng/insertProc.do")
	public void siteInsertProc(@ModelAttribute("insertVO") MenusMngVO insertVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			siteMngService.insertSite(insertVO);

			jsonObj.put("flag", "success");
			jsonObj.put("message", "사이트 등록이 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());
		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "사이트 등록 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			LOGGER.error("failed to run siteInsertProc", e);
		}
	}

	@RequestMapping("/sys/totalAdminMng/siteMng/modifyPage.do")
	public String siteModifyPage(@ModelAttribute("searchVO") MenusMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {

		model.addAttribute("result", siteMngService.getSite(searchVO));
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
		
		List<DeptVO> deptList = new ArrayList<DeptVO>();
		
		deptList = deptService.getDeptCategoryList();
		
		model.addAttribute("siteGubunList", SiteGubun.values());
		model.addAttribute("deptList", deptList);

		return "/sys/totalAdminMng/siteMng/modify";
	}

	@RequestMapping("/sys/totalAdminMng/siteMng/modifyProc.do")
	public void siteModifyProc(@ModelAttribute("updateVO") MenusMngVO updateVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			siteMngService.updateSite(updateVO);

			jsonObj.put("flag", "success");
			jsonObj.put("message", "사이트 수정이 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());

		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "사이트 수정 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			LOGGER.error(">> failed to run siteModifyProc", e);
		}
	}

	@RequestMapping("/sys/totalAdminMng/siteMng/deleteProc.do")
	public void siteDeleteProc(@ModelAttribute("deleteVO") MenusMngVO deleteVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			siteMngService.deleteSite(deleteVO);

			jsonObj.put("flag", "success");
			jsonObj.put("message", "사이트 삭제가 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());
		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "사이트 삭제 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			LOGGER.error(">> failed to run siteDeleteProc", e);
		}
	}

	/**
	 * 사이트 관리 목록을 CMS로 전달 할 사이트 목록 선택 화면
	 *
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sys/totalAdminMng/siteMng/sendToCMS.do")
	public String sendSiteToCMS(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws IOException {

		MenusMngVO searchVO = new MenusMngVO();
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(100);
		model.addAttribute("siteList", siteMngService.getSiteList(searchVO));

		String cmsUrl = EgovProperties.getProperty("ySmartCMS.url");

		//사이트 그룹 사용여부 확인
		String key = (String) session.getAttribute("cmsLoginKey");
		String callUrl = cmsUrl + "/linkage/getSettings?key=" + key;
		String userAgent = "Mozilla/5.0";

		BufferedReader in = null;
		try {

			URL obj = new URL(callUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			// optional default is GET
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", userAgent);

//			int responseCode = con.getResponseCode();

			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer rep = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				rep.append(inputLine);
			}
			String result = rep.toString();
			SiteGroupJsonVO groupInfo = JSON.decode(result, SiteGroupJsonVO.class);
			if ("Y".equals(groupInfo.getSiteGroupUseYN())) {
				if (groupInfo.getGroups() != null) {
					model.addAttribute("groupList", groupInfo.getGroups());
				} else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = null;
					try {
						out = response.getWriter();
						out.println("<script>alert('등록된 사이트 그룹이 없습니다. y-SmartCMS에서 먼저 사이트 그룹을 등록하세요.');window.close();</script>");
						return null;
					} catch (IOException e1) {
						LOGGER.error(">> failed to run sendSiteToCMS/IOException", e1);
					} finally {
						out.close();
					}
				}
			}

		} catch (SocketTimeoutException e) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("<script>alert('y-SmartCMS에 접속되지 않았습니다. 관리자에게 문의하세요.(ST)');window.close();</script>");
				return null;
			} catch (IOException e1) {
				LOGGER.error(">> failed to run sendSiteToCMS/IOException", e1);
			} finally {
				out.close();
			}
		} catch (FileNotFoundException e) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("<script>alert('y-SmartCMS에 접속되지 않았습니다. 관리자에게 문의하세요.(FNFE)');window.close();</script>");
				return null;
			} catch (IOException e1) {
				LOGGER.error(">> failed to run sendSiteToCMS/IOException", e1);
			} finally {
				out.close();
			}
		} catch (MalformedURLException e) {
			LOGGER.error(">> failed to run sendSiteToCMS / MalformedURLException", e);
		} catch (IOException e) {
			LOGGER.error(">> failed to run sendSiteToCMS / IOException", e);
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return "/sys/totalAdminMng/siteMng/sendSiteList";

	}

}
