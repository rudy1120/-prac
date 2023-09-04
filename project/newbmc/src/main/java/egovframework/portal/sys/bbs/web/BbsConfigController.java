package egovframework.portal.sys.bbs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.portal.common.Constant;
import egovframework.portal.common.service.CommonCodeService;
import egovframework.portal.common.vo.CommonCodeVO;
import egovframework.portal.dept.service.DeptService;
import egovframework.portal.staff.service.StaffService;
import egovframework.portal.sys.MenuMng.SiteGubun;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.bbs.ColType;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;
import net.arnx.jsonic.JSON;

/**
 * 관리자 - 게시판관리 Controller
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.09.25		김혜민				최초 생성
 * 2016.03.17		J.Ryeon Lee			외부관리자에서도 게시판을 관리할 수 있도록 사이트 코드 도입
 * 2017.06.14		J.Ryeon Lee			코드 리팩토링
 * </pre>
 *
 * @author 개발팀 김혜민
 * @since 2014.09.25
 * @version 1.0
 */
@Controller("BbsConfigController")
public class BbsConfigController {

	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected CommonCodeService commonCodeService;
	@Autowired
	protected SiteMngService siteMngService;
	@Autowired
	protected DeptService deptService;
	@Autowired
	protected StaffService staffService;

	private final Logger LOGGER = LogManager.getLogger();

	/** 게시판 목록 (JSON) */
	@ResponseBody
	@RequestMapping("/sys/bbs/list/json.do")
	public byte[] list(@RequestParam(required = false, defaultValue = "") String title,
		@RequestParam int page, @RequestParam(required = false, defaultValue = "10") int perPage,
		@RequestParam(required = false, defaultValue = "7") int pageCut, HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");
		Map<String, Object> rtn = new HashMap<>();

		try {
			BbsConfigVO searchVO = new BbsConfigVO();
			searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, perPage));
			searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, perPage));
			searchVO.setSearchTxt(title);
			int totalCnt = bbsConfigService.getBbsConfigCnt(searchVO);

			rtn.put("success", Boolean.TRUE);
			rtn.put("totalCnt", totalCnt);
			rtn.put("list", bbsConfigService.getBbsConfigList(searchVO));
			rtn.put("pagingInfo", PaginationInfoUtil.calPaginationInfo(page, perPage, pageCut, totalCnt));
		} catch (DataAccessException e) {
			LOGGER.error("", e);
			rtn.put("success", Boolean.FALSE);
		}

		return JSON.encode(rtn).getBytes("UTF-8");
	}

	/** 게시판 목록 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/list.do")
	public String list(@ModelAttribute("searchVO") BbsConfigVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);

		StringBuilder que1 = new StringBuilder();
		StringBuilder que2 = new StringBuilder();

		List<SysSiteAuthVO> sysSiteAuthList = adminLoginVO.getAdminSiteAuths();
		AuthType auth = AuthType.toAuthType(adminLoginVO.getAdminAccessLevelCode());

		switch (auth) {
			case NONE: // invalid
				WriterUtil.flushJSInvalidAccess(response);
				return null;

			case SUPER:
				break;

			case EXTERNAL:
				que2.append(" AND c.pt_idx IN (" + adminLoginVO.getPtIdxs() + ")");
				break;

			default: // GENERAL OR SSO
				if (sysSiteAuthList != null && sysSiteAuthList.size() > 0) {
					que1.append(" AND ( idx = " + sysSiteAuthList.get(0).getSiteIdx());
					que2.append(" AND ( pt_sitecode = 'common' OR pt_sitecode = '" + sysSiteAuthList.get(0).getSiteCode() + "'");
					if (sysSiteAuthList.size() > 1) {
						for (int i = 1; i < sysSiteAuthList.size(); i++) {
							que1.append(" OR idx = " + sysSiteAuthList.get(i).getSiteIdx());
							que2.append(" OR pt_sitecode = '" + sysSiteAuthList.get(i).getSiteCode() + "'");
						}
					}

					que1.append(" ) ");
					que2.append(" ) ");
				} else { // 허용된 사이트가 없는 경우. 접근 불가능
					WriterUtil.flushJSSysInvalidAccess(response);
					return null;
				}

				break;
		}

		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		String search_query = que2.toString(); // 2017.02.28 J.Ryeon Lee xml 파일로 쿼리 이동
		if (auth != AuthType.SUPER) { // 2016.05.16 J.Ryeon Lee ADD 부서 권한
			//search_query += " AND (" + "(pt_mng_dept_ids IS NULL AND pt_mng_ids IS NULL) " + " OR pt_mng_dept_ids LIKE '%" + adminLoginVO.getDeptId() + "%'" + " OR UPPER(pt_mng_ids) LIKE '%" + adminLoginVO.getAdminId().toUpperCase() + "%'" + ")";
			//2020.06.10 게시물 부서권한 수정
			search_query += " AND (" + "pt_mng_dept_ids LIKE '%" + adminLoginVO.getDeptId() + "%'" + " OR UPPER(pt_mng_ids) LIKE '%" + adminLoginVO.getAdminId().toUpperCase() + "%'" + ")";
			searchVO.setSearchQuery(search_query);
		}
		searchVO.setSearchQuery(search_query);

		MenusMngVO mn = new MenusMngVO();
		mn.setSiteCode(searchVO.getMenu_category());

		mn.setSearchQuery(que1.toString());
		List<MenusMngVO> siteList = adminLoginVO.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode() ? null : siteMngService.getSiteCodeListWhere(mn);

		
		List<BbsConfigVO> configList = bbsConfigService.getBbsConfigList(searchVO);
		int totalCnt = bbsConfigService.getBbsConfigCnt(searchVO);

		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("siteList", siteList);
		model.addAttribute("boardList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));
		model.addAttribute("result", configList);
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("siteCode", siteCode);
		model.addAttribute("boardTypeList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));
		model.addAttribute("siteGubunList", SiteGubun.values());

		return "/sys/bbs/configMng/list";
	}

	/** 게시판 등록 페이지 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/write.do")
	public String write(@ModelAttribute("searchVO") BbsConfigVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MenusMngVO> siteList = siteMngService.getSiteCodeList(request);

		model.addAttribute("bbsConfigVO", searchVO);
		model.addAttribute("siteList", siteList);
		model.addAttribute("boardList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));
		model.addAttribute("siteCode", siteCode);
		model.addAttribute("deptInfoList", deptService.getAllOpenDataDepthList());
		model.addAttribute("colTypeList", ColType.values());

		//게시판 담당자 선택 리스트
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("searchCondition", "0");
		param.put("searchKeyword", "");
		model.addAttribute("staffInfoList", staffService.allStaffList(param));

		return "/sys/bbs/configMng/write";
	}

	/** 게시판 등록 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/writeProc.do")
	public String writeProc(@PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("searchVO") @Valid BbsConfigVO searchVO, BindingResult bindingResult) throws Exception {
		if (!bindingResult.hasErrors()) {
			String newPtIdx = bbsConfigService.insertBbsConfig(request, searchVO);
			if (StringUtil.isNotEmpty(newPtIdx)) {
				return "redirect:/sys/" + siteCode + "/bbs/configMng/list.do?mId=" + mId;
			}
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
		return null;
	}

	/** 게시판 수정 페이지 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/modify.do")
	public String modify(@ModelAttribute("searchVO") BbsConfigVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		BeanUtils.copyProperties(bbsConfigVO, searchVO, Constant.IGNORE_PROPERTIES);

		model.addAttribute("bbsConfigVO", searchVO);
		model.addAttribute("siteList", siteMngService.getSiteCodeList(request));
		model.addAttribute("boardList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));
		model.addAttribute("siteCode", siteCode);
		model.addAttribute("deptInfoList", deptService.getAllOpenDataDepthList());
		model.addAttribute("colTypeList", ColType.values());
		model.addAttribute("cancelMid", ServletRequestUtils.getStringParameter(request, "cancelMid")); // 현재 메뉴가 아닌 다른 메뉴로 이동하는 경우 이용

		//게시판 담당자 선택 리스트
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("searchCondition", "0");
		param.put("searchKeyword", "");
		model.addAttribute("staffInfoList", staffService.allStaffList(param));

		return "/sys/bbs/configMng/write";
	}

	/** 게시판 수정 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/modifyProc.do")
	public String modifyProc(@PathVariable String siteCode, @RequestParam String mId, @RequestParam(required = false) String cancelMid, ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("searchVO") @Valid BbsConfigVO searchVO, BindingResult bindingResult) throws Exception {
		if (!bindingResult.hasErrors()) {
			String updatePtIdx = bbsConfigService.updateBbsConfig(request, searchVO);
			if (StringUtil.isNotEmpty(updatePtIdx)) {
				return "redirect:/sys/sysContents.do?mId=" + (StringUtil.isNotBlank(cancelMid) ? cancelMid : mId); // cancelMid가 있는 경우 해당 메뉴로 이동. 개인정보 설정을 통해 수정으로 들어온 경우 이전 페이지로 이동.
			}
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
		return null;
	}

	/** 게시판 삭제 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") BbsConfigVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {
		String deletePtIdx = bbsConfigService.deleteBbsConfig(request, searchVO);
		if (StringUtil.isNotEmpty(deletePtIdx)) {
			return "redirect:/sys/" + siteCode + "/bbs/configMng/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
		return null;
	}

	/** 게시판 사용 유무 검증 */
	@ResponseBody
	@RequestMapping("/sys/{siteCode}/bbs/bbsCongig/isUsedBbs.do")
	public String isUsedBbs(@PathVariable String siteCode, HttpServletRequest request, HttpServletResponse response, @RequestParam String ptIdx) throws JSONException {
		JSONObject rtn = new JSONObject();
		rtn.put("result", Boolean.FALSE);

		if (StringUtil.isNotBlank(ptIdx)) {
			rtn.put("isUsed", bbsConfigService.isUsedBbs(ptIdx));
			rtn.put("result", Boolean.TRUE);
		}

		return rtn.toString();
	}

	/** 게시판 목록 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/auth/list.do")
	public String authlist(@ModelAttribute("searchVO") BbsConfigVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);

		StringBuilder que1 = new StringBuilder();
//		StringBuilder que2 = new StringBuilder();

		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		MenusMngVO mn = new MenusMngVO();
		mn.setSiteCode(searchVO.getMenu_category());

		mn.setSearchQuery(que1.toString());
		List<MenusMngVO> siteList = adminLoginVO.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode() ? null : siteMngService.getSiteCodeListWhere(mn);

		List<BbsConfigVO> configList = bbsConfigService.getBbsConfigAuthList(searchVO);
		int totalCnt = bbsConfigService.getBbsConfigCnt(searchVO);

		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("siteList", siteList);
		model.addAttribute("boardList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));
		model.addAttribute("result", configList);
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("siteCode", siteCode);
		model.addAttribute("boardTypeList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));
		model.addAttribute("siteGubunList", SiteGubun.values());

		return "/sys/bbs/configMng/auth/list";
	}

	/** 게시글 처리 이력 엑셀 */
	@RequestMapping("/sys/{siteCode}/bbs/configMng/auth/downProc.do")
	public void operationExcel(@ModelAttribute("searchVO") BbsConfigVO searchVO, @RequestParam String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = bbsConfigService.getTotalBbsConfigAuthListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("bbs_auth_" + TUtil.getToday("yyyyMMddHHmmss"), "[게시판별 권한 목록]", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("SITE_NAME,PT_TYPE_NAME,PT_TITLE,PT_MNG_NMS,PT_MNG_DEPT_NMS"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("사이트,구분,게시판명,권한자,권한부서"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("15,15,50,70,70");
		excel.setExcel(response, excelData);
	}

}
