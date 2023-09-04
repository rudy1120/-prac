package egovframework.portal.sys.sysAuth.web;

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.sysAuth.service.SiteAccessMngService;
import egovframework.portal.sys.sysAuth.vo.SiteAccessVO;
import egovframework.portal.util.TUtil;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;

/**
 * 관리자 - 관리자페이지 접근허용 Controller 클래스
 *
 * @author 개발팀 엄동건
 * @since 2015-01-15
 * @version 1.0
 * @see
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2015-01-15 엄동건
 *
 * </pre>
 */
/**
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.15		엄동건				최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author 엄동건
 * @since 2015.01.15
 */
@Controller("SiteAccessMngController")
public class SiteAccessMngController {
	@Autowired
	protected MenuSysService menuService;

	@Autowired
	protected SiteAccessMngService siteAccessMngService;

	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/sys/totalAdminMng/siteAccessMng/list.do")
	public String accessAllowList(@ModelAttribute("searchVO") SiteAccessVO searchVO
		, @RequestParam("mId") String mId
		, HttpServletRequest request
		, ModelMap model) throws Exception {
		String searchType = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchType", "")); // 검색타입
		String searchTxt = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchTxt", "")); // 검색어

		String search_query = getSearchQuery(searchType, searchTxt);

		searchVO.setSearchQuery(search_query);
		int totalCnt = siteAccessMngService.getAccessAllowListCnt(searchVO);
		List<SiteAccessVO> resultList = siteAccessMngService.getAccessAllowList(searchVO);

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("resultList", resultList);

		return "/sys/totalAdminMng/siteAccessMng/list";
	}

	private String getSearchQuery(String searchType, String searchTxt) {
		String search_query = ""; // 쿼리문

		// 검색쿼리 설정
		if (!"".equals(searchType) && !"".equals(searchTxt)) {
			search_query = " and (" + searchType + " LIKE '%" + searchTxt + "%' or IP_BAND_A LIKE '%" + searchTxt + "%' or IP_BAND_B LIKE '%" + searchTxt + "%')";
		}
		return search_query;
	}

	@RequestMapping("/sys/totalAdminMng/siteAccessMng/insertPage.do")
	public String accessAllowInsertPage(@ModelAttribute("searchVO") SiteAccessVO searchVO
		, @RequestParam("mId") String mId
		, HttpServletRequest request
		, ModelMap model) throws Exception {

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/siteAccessMng/insert";
	}

	@RequestMapping("/sys/totalAdminMng/siteAccessMng/insertProc.do")
	public void accessAllowInsertProc(@ModelAttribute("insertVO") SiteAccessVO insertVO
		, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			int dupChk = siteAccessMngService.getAccessAllowDupChk(insertVO);

			if (dupChk > 0) {
				jsonObj.put("flag", "fail");
				jsonObj.put("message", "등록된 접근허용 아이피가 존재합니다.\r\n 다른 정보를 입력해주십시요.");
				out.println(jsonObj.toString());
			} else {
				siteAccessMngService.insertAccessAllow(insertVO);
				jsonObj.put("flag", "success");
				jsonObj.put("message", "접근허용 아이피 등록이 정상적으로 처리되었습니다.");
				out.println(jsonObj.toString());
			}
		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "접근허용 아이피 등록 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			logger.error("접근허용 아이피 등록 오류");
		}
	}

	@RequestMapping("/sys/totalAdminMng/siteAccessMng/modifyPage.do")
	public String accessAllowUpdatePage(@ModelAttribute("searchVO") SiteAccessVO searchVO
		, @RequestParam("mId") String mId
		, HttpServletRequest request
		, ModelMap model) throws Exception {

		model.addAttribute("result", siteAccessMngService.getAccessAllow(searchVO));
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/siteAccessMng/modify";
	}

	@RequestMapping("/sys/totalAdminMng/siteAccessMng/modifyProc.do")
	public void accessAllowUpdateProc(@ModelAttribute("updateVO") SiteAccessVO updateVO
		, HttpServletRequest request
		, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			siteAccessMngService.updateAccessAllow(updateVO);
			jsonObj.put("flag", "success");
			jsonObj.put("message", "접근허용 아이피 수정이 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());

		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "접근허용 아이피 수정 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			logger.error("접근허용 아이피 수정 오류");
		}
	}

	@RequestMapping("/sys/totalAdminMng/siteAccessMng/deleteProc.do")
	public void accessAllowDeleteProc(@ModelAttribute("searchVO") SiteAccessVO deleteVO
		, HttpServletRequest request
		, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			siteAccessMngService.deleteAccessAllow(deleteVO);
			jsonObj.put("flag", "success");
			jsonObj.put("message", "접근허용 아이피 삭제가 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());

		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "접근허용 아이피 삭제 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			logger.error("접근허용 아이피 삭제 오류");
		}
	}
}
