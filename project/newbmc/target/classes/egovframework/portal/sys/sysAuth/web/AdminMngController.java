package egovframework.portal.sys.sysAuth.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.service.AdminMngService;
import egovframework.portal.sys.sysAuth.service.ExternalAdminMngService;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.sysAuth.service.SysMemberService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;

/**
 * 관리자 정보 수정 페이지
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017.04.20		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee		리팩키징
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 4. 20.
 */
@Controller
public class AdminMngController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected AdminMngService adminMngService;
	@Autowired
	protected SysMemberService sysMemberService;
	@Resource
	protected ExternalAdminMngService externalAdminMngService;

	public static final Logger LOGGER = Logger.getLogger(AdminMngController.class.getName());

	@RequestMapping("/sys/{siteCode}/adminMng/modify.do")
	public String modify(@ModelAttribute("searchVO") AdminLoginVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
		if (currentAdmin != null) {
			BeanUtils.copyProperties(currentAdmin, searchVO, Constant.IGNORE_PROPERTIES);
			model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("siteCode", siteCode);

			return "/sys/adminMng/modify";
		}

		WriterUtil.flushJSSysInvalidAccess(response);
		return null;
	}

	@RequestMapping("/sys/{siteCode}/adminMng/modifyProc.do")
	public void modifyProc(@PathVariable String siteCode, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("searchVO") AdminLoginVO searchVO, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
				searchVO.setId(currentAdmin.getAdminId());
				searchVO.setAdminId(currentAdmin.getAdminId());
				searchVO.setAdminAccessLevelCode(currentAdmin.getAdminAccessLevelCode());
				searchVO.setAdminTypeCode(currentAdmin.getAdminTypeCode());

				AdminLoginVO entity = currentAdmin.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode()
					? externalAdminMngService.getEntity(currentAdmin.getId(), currentAdmin.getAdminTypeCode()) //
					: sysMemberService.selectSysMemberDataDetail(searchVO);
				if (entity != null && searchVO.isSameAs(entity)) {
					searchVO.setAdminAccessLevelCode(entity.getAdminAccessLevelCode());
					adminMngService.update(searchVO);

					AdminLoginVO logined = SessionUtil.getAdminInstance(request); // 세션 update
					logined.setName(searchVO.getName());
					logined.setAdminName(searchVO.getName());
					logined.setExplan(searchVO.getExplan());
					if (StringUtil.isNotBlank(searchVO.getNewPwd())) {
						logined.setChangePwTarget("N");
					}

					flushJsAlertAndRedirect(response, "수정되었습니다.", mId, siteCode);
				} else {
					flushJsAlertAndRedirect(response, "비밀번호가 일치하지 않습니다.", mId, siteCode);
				}
			} catch (Exception e) {
				LOGGER.error(">> failed sys.adminMng.modifyProc", e);
			}
		}

		flushJsAlertAndRedirect(response, "처리 중 오류가 발생했습니다.", mId, siteCode);
	}

	/** 비밀번호 재입력 체크 */
	@ResponseBody
	@RequestMapping(value = "/sys/{siteCode}/adminMng/chkPasswordProc.do", method = RequestMethod.POST)
	public String chkPasswordProc(@ModelAttribute AdminLoginVO searchVO, @PathVariable String siteCode, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE).put("matched", Boolean.FALSE);

		try {
			AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
			searchVO.setAdminId(currentAdmin.getAdminId());
			searchVO.setAdminAccessLevelCode(currentAdmin.getAdminAccessLevelCode());
			searchVO.setAdminTypeCode(currentAdmin.getAdminTypeCode());

			AdminLoginVO entity = currentAdmin.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode()
				? externalAdminMngService.getEntity(currentAdmin.getId(), currentAdmin.getAdminTypeCode()) //
				: sysMemberService.selectSysMemberDataDetail(searchVO);
			rtn.put("success", Boolean.TRUE);
			rtn.put("matched", entity != null && searchVO.isSameAs(entity));
		} catch (Exception e) {
			LOGGER.error(">> failed to sys.adminMng.chkPasswordProc", e);
		}

		return rtn.toString();
	}

	private void flushJsAlertAndRedirect(HttpServletResponse response, String msg, String mId, String siteCode) {
		WriterUtil.flushJsAlertAndRedirect(response, msg, "location.href = '/sys/" + siteCode + "/adminMng/modify.do?mId=" + mId + "';");
	}
}
