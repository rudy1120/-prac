package egovframework.portal.sys.sysAuth.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import egovframework.portal.common.Constant;
import egovframework.portal.sys.MenuMng.service.MenuSysMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.sysAuth.service.SysMemberAuthMngService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.DeptMngVO;
import egovframework.portal.sys.sysAuth.vo.SysMenuAuthVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.TUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 관리자 - 권한조회 서비스 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2013.12.02		이종길				최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author 이종길
 * @since 2013.12.02
 */
@Controller
public class SysMemberAuthMngController {

	@Autowired
	protected MenuSysMngService menuSysMngService;
	@Autowired
	protected MenuSysService menuService;
	@Autowired
	protected SysMemberAuthMngService sysMemberAuthMngService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/sys/sysMemberMng/auth/list.do")
	public String deptAuthList(@ModelAttribute("searchVO") DeptMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		String searchType = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchType", "")); // 검색타입
		String searchTxt = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchTxt", "")); // 검색어
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
		int perPage = Constant.LIST_CUTPAGE;
		int cpage = 1;
		if (page > 1) {
			cpage = (page - 1) * perPage + 1;
		}

		perPage = page * searchVO.getPageSize();
		searchVO.setFirstIndex(cpage);
		searchVO.setLastIndex(perPage);
		String search_query = getSearchQuery(searchType, searchTxt);

		// 입력값 설정
		searchVO.setSearchQuery(search_query);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(page);
		paginationInfo.setRecordCountPerPage(Constant.LIST_CUTRECORD);
		paginationInfo.setPageSize(Constant.LIST_CUTPAGE);

		int totalCnt = sysMemberAuthMngService.getAuthDeptListCnt(searchVO);
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("resultList", sysMemberAuthMngService.getAuthDeptList(searchVO));

		paginationInfo.setTotalRecordCount(totalCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("page", page);

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/sysMemberMng/auth/list";
	}

	private String getSearchQuery(String searchType, String searchTxt) {
		String search_query = ""; // 쿼리문

		// 검색쿼리 설정
		if (!"".equals(searchType) && !"".equals(searchTxt)) {
			search_query = " AND " + searchType + " LIKE '%" + searchTxt + "%'";
		}
		return search_query;
	}

	@RequestMapping("/sys/sysMemberMng/auth/menu/listPop.do")
	public String sysMenuListPop(@ModelAttribute("searchVO") SysMenuAuthVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//직원 권한조회, 20160509 손영식
		if (!searchVO.getUsrId().equals("")) {
			model.addAttribute("authList", sysMemberAuthMngService.getSysUsrAuth(searchVO));
//			viewName = "/sys/sysMemberMng/auth/menu/listPop";
		} else {
			model.addAttribute("authList", sysMemberAuthMngService.getSysMenusAuthList(searchVO));
		}

		//직원 목록 조회, 20160510 손영식
//		model.addAttribute("usrList", sysMemberAuthMngService.getSysUsrList(searchVO));
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, null, request));

		return "/sys/sysMemberMng/auth/menu/listPop";
	}

	@RequestMapping("/sys/sysMemberMng/auth/sysmenu/insertMenuAuthProc.do")
	public void insertMenuAuthProc(@ModelAttribute("insertVO") SysMenuAuthVO insertVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {

			//직원개인 권한 등록, 20160509 손영식
			if (!insertVO.getUsrId().equals("")) {
				AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);
				insertVO.setRegId(adminLoginVO.getId());
				sysMemberAuthMngService.writeSysUsrAuth(insertVO);
				jsonObj.put("flag", "success");
				jsonObj.put("message", "해당직원의 메뉴 권한정보를 정상적으로 등록했습니다.");
			} else {
				sysMemberAuthMngService.writeSysMenusAuth(insertVO);
				jsonObj.put("flag", "success");
				jsonObj.put("message", "해당부서의 메뉴 권한정보를 정상적으로 등록했습니다.");
			}

			out.println(jsonObj.toString());
		} catch (Exception e) {
			logger.error("메뉴 권한정보 등록 오류");

			jsonObj.put("flag", "false");
			jsonObj.put("message", "메뉴 권한정보 등록 중 오류가 발생했습니다.");

			out.println(jsonObj.toString());
		}
	}

	@RequestMapping("/sys/sysMemberMng/auth/sysmenu/deleteMenuAuthProc.do")
	public void deleteSysMenuAuthProc(@ModelAttribute("deleteVO") SysMenuAuthVO deleteVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {

			//직원개인 권한 등록, 20160509 손영식
			if (!deleteVO.getUsrId().equals("")) {
				sysMemberAuthMngService.deleteSysUsrAuth(deleteVO);
				jsonObj.put("flag", "success");
				jsonObj.put("message", "해당직원의 메뉴 권한정보를 정상적으로 삭제했습니다.");
			} else {
				sysMemberAuthMngService.deleteSysMenusAuth(deleteVO);
				jsonObj.put("flag", "success");
				jsonObj.put("message", "해당부서의 메뉴 권한정보를 정상적으로 삭제했습니다.");
			}

			out.println(jsonObj.toString());
		} catch (Exception e) {
			logger.error("메뉴 권한삭제");

			jsonObj.put("flag", "false");
			jsonObj.put("message", "메뉴 권한정보 삭제 중 오류가 발생했습니다.");

			out.println(jsonObj.toString());
		}
	}

	@RequestMapping("/sys/sysMemberMng/auth/site/listPop.do")
	public String sysSiteListPop(@ModelAttribute("searchVO") SysSiteAuthVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MenusMngVO siteSearch = new MenusMngVO();

		model.addAttribute("deptInAuthList", sysMemberAuthMngService.getSysSitesAuthInDeptList(searchVO));
		model.addAttribute("authList", sysMemberAuthMngService.getSysSitesAuthList(siteSearch));
		model.addAttribute("deptId", TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "deptId", "")));

		return "/sys/sysMemberMng/auth/site/listPop";
	}

	@RequestMapping("/sys/sysMemberMng/auth/sysmenu/insertSiteAuthProc.do")
	public void insertMenuAuthProc(@ModelAttribute("insertVO") SysSiteAuthVO insertVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			sysMemberAuthMngService.writeSysSitesAuth(insertVO);
			jsonObj.put("flag", "success");
			jsonObj.put("message", "해당부서의 메뉴 권한정보를 정상적으로 등록했습니다.");

			out.println(jsonObj.toString());
		} catch (Exception e) {
			logger.error("해당부서 메뉴권한 정보 등록 오류");

			jsonObj.put("flag", "false");
			jsonObj.put("message", "해당부서의 메뉴 권한정보 등록 중 오류가 발생했습니다.");

			out.println(jsonObj.toString());
		}
	}

	@RequestMapping("/sys/sysMemberMng/auth/sysmenu/deleteSiteAuthProc.do")
	public void deleteSysSiteAuthProc(@ModelAttribute("deleteVO") SysSiteAuthVO deleteVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			sysMemberAuthMngService.deleteSysSitesAuth(deleteVO);
			jsonObj.put("flag", "success");
			jsonObj.put("message", "해당부서의 사이트 권한정보를 정상적으로 삭제했습니다.");

			out.println(jsonObj.toString());
		} catch (Exception e) {
			logger.error("해당부서의 사이트 권한정보 삭제오류");

			jsonObj.put("flag", "false");
			jsonObj.put("message", "해당부서의 사이트 권한정보 삭제 중 오류가 발생했습니다.");

			out.println(jsonObj.toString());
		}
	}

	//권한조회-메뉴별 20160516 손영식
	@RequestMapping("/sys/sysMemberMng/auth/search/menu.do")
	public String searchMenuAuthList(ModelMap model, HttpServletRequest request, SysMenuAuthVO searchVO,
		@RequestParam(value = "mId", defaultValue = "0") String mId) throws Exception {

		model.addAttribute("deptList", sysMemberAuthMngService.getSysDeptList(searchVO));
		model.addAttribute("memberList", sysMemberAuthMngService.getSysMemberList(searchVO));

		model.addAllAttributes(menuSysMngService.getMenuListMap(""));
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/sysMemberMng/auth/search/menu";
	}

	//권한조회-부서별 20160517 손영식
	@RequestMapping("/sys/sysMemberMng/auth/search/dept.do")
	public String searchDeptAuthList(ModelMap model, HttpServletRequest request, DeptMngVO searchVO,
		SysMenuAuthVO sysSearchVO, @RequestParam(value = "mId", defaultValue = "0") String mId) throws Exception {

		model.addAttribute("authList", sysMemberAuthMngService.getSysMenusAuthList(sysSearchVO));
		model.addAttribute("resultList", sysMemberAuthMngService.getAuthDeptListAll(searchVO));
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/sysMemberMng/auth/search/dept";
	}

	//권한조회-담당자별 20160517 손영식
	@RequestMapping("/sys/sysMemberMng/auth/search/member.do")
	public String searchMemberAuthList(ModelMap model, HttpServletRequest request, SysMenuAuthVO searchVO, @RequestParam(value = "mId", defaultValue = "0") String mId) throws Exception {
		model.addAttribute("usrList", sysMemberAuthMngService.getSysUsrList(searchVO));
		//직원 목록 조회, 20160510 손영식
		model.addAttribute("authList", sysMemberAuthMngService.getSysUsrAuth(searchVO));
		model.addAttribute("authList2", sysMemberAuthMngService.getSysMenusAuthList(searchVO));
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		model.addAttribute("searchVO",searchVO);

		return "/sys/sysMemberMng/auth/search/member";
	}
}
