package egovframework.portal.sys.sysAuth.web;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.sysAuth.AdminType;
import egovframework.portal.sys.sysAuth.service.AdminMngService;
import egovframework.portal.sys.sysAuth.service.ExternalAdminMngService;
import egovframework.portal.sys.sysAuth.service.SysMemberAuthMngService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 외부 관리자 관리 CONTROLLEr
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.09.22		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 9. 22.
 */
@Controller
public class ExternalAdminMngController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected ExternalAdminMngService externalAdminMngService;
	@Autowired
	protected SysMemberAuthMngService sysMemberAuthMngService;
	@Autowired
	protected MenuSysService menuSysService;
	@Autowired
	protected AdminMngService adminMngService;

	private final Logger LOGGER = LogManager.getLogger();

	/** 외부 관리자 목록 */
	@RequestMapping("/sys/externalAdminMng/list.do")
	public String list(@ModelAttribute("searchVO") AdminLoginVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = externalAdminMngService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", externalAdminMngService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("adminTypeList", AdminType.values());

		return "/sys/externalAdminMng/list";
	}

	/** 외부 관리자 등록 */
	@RequestMapping("/sys/externalAdminMng/write.do")
	public String write(@ModelAttribute("searchVO") AdminLoginVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("adminTypeList", AdminType.values());
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/externalAdminMng/modify";
	}

	/** 외부 관리자 등록 proc */
	@ResponseBody
	@RequestMapping(value = "/sys/externalAdminMng/writeProc.do", method = RequestMethod.POST)
	public String writeProc(@ModelAttribute("searchVO") @Valid AdminLoginVO searchVO, BindingResult result) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		if (result.hasErrors()) {
			rtn.put("inputError", Boolean.TRUE);
		} else {
			try {
				if (externalAdminMngService.isUnique(searchVO)) {
					externalAdminMngService.insert(searchVO);
					rtn.put("success", Boolean.TRUE);
				} else {
					rtn.put("errCode", "NOT_UNIQUE");
				}
			} catch (DataAccessException e) {
				LOGGER.error("", e);
			}
		}

		return rtn.toString();
	}

	/** 외부 관리자 수정 */
	@RequestMapping("/sys/externalAdminMng/modify.do")
	public String modify(@ModelAttribute("searchVO") AdminLoginVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {
		AdminLoginVO admin = externalAdminMngService.getEntity(searchVO.getId(), searchVO.getAdminTypeCode());
		if (admin != null) {
			BeanUtils.copyProperties(admin, searchVO, Constant.IGNORE_PROPERTIES);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("adminTypeList", AdminType.values());
			model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

			return "/sys/externalAdminMng/modify";
		}

		WriterUtil.flushJSAlert(response, WriterUtil.createJsAlertContent("해당 관리자 정보가 존재하지 않습니다.", "location.href='/sys/externalAdminMng/list.do?mId=" + mId + "'"));
		return null;
	}

	/** 외부 관리자 수정 proc */
	@ResponseBody
	@RequestMapping(value = "/sys/externalAdminMng/modifyProc.do", method = RequestMethod.POST)
	public String modifyProc(@ModelAttribute("searchVO") @Valid AdminLoginVO searchVO, BindingResult result) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		if (result.hasErrors()) {
			rtn.put("inputError", Boolean.TRUE);
		} else {
			AdminLoginVO admin = externalAdminMngService.getEntity(searchVO.getId(), searchVO.getAdminTypeCode());
			if (admin != null) {
				externalAdminMngService.update(searchVO);
				rtn.put("success", Boolean.TRUE);
			}
		}

		return rtn.toString();
	}

	/** 외부 관리자 삭제 proc */
	@ResponseBody
	@RequestMapping(value = "/sys/externalAdminMng/deleteProc.do", method = RequestMethod.POST)
	public String deleteProc(@ModelAttribute("searchVO") AdminLoginVO searchVO, HttpServletRequest request) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		AdminLoginVO admin = externalAdminMngService.getEntity(searchVO.getId(), searchVO.getAdminTypeCode());
		if (admin != null) {
			externalAdminMngService.delete(admin);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString();
	}

	/** 외부 관리자 로그인 제한 해제 proc */
	@ResponseBody
	@RequestMapping(value = "/sys/externalAdminMng/resetLoginFailedCntProc.do", method = RequestMethod.POST)
	public String resetLoginFailedCntProc(@ModelAttribute("searchVO") AdminLoginVO searchVO) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		try {
			AdminLoginVO admin = externalAdminMngService.getEntity(searchVO.getId(), searchVO.getAdminTypeCode());
			if (admin != null) {
				adminMngService.resetFailedCnt(admin);
				rtn.put("success", Boolean.TRUE);
			}
		} catch (Exception e) {
			LOGGER.error(">> failed.sys.externalAdminMng.modifyProc", e);
		}

		return rtn.toString();
	}

	@RequestMapping("/sys/externalAdminMng/excel/format/download.do")
	public void excelFormatDownload(@RequestParam String mId, HttpServletResponse response) throws Exception {
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("sys_admin_external_insert_format", "외부 관리자 일괄 업로드 양식", new ArrayList<Map<String, String>>(), Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("id,name,pwd,admin_type_code,mids,pt_idxs"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("ID,관리자명,비밀번호,관리자타입코드,메뉴ID목록,게시판IDX목록"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("15,20,30,10,50,50");
		excel.setExcel(response, excelData);
	}


	/** 엑셀 업로드 */
	@ResponseBody
	@RequestMapping(value = "/sys/externalAdminMng/excel/uploadProc.do")
	public String excelUploadProc(@RequestParam String mId, MultipartHttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject().put("success", Boolean.FALSE);

		String mode = request.getParameter("mode");
		try {
			result.put("success", Boolean.TRUE) //
				.put("cnt", externalAdminMngService.upload(request.getFile("excelFile"), StringUtil.isNotBlank(mode) && "copy".equals(mode)));
		} catch (Exception e) {
			result.put("cnt", -1);
		}

		return result.toString();
	}

}
