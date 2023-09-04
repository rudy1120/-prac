package egovframework.portal.sys.sysAuth.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.sysAuth.AdminType;
import egovframework.portal.sys.sysAuth.service.AdminMngService;
import egovframework.portal.sys.sysAuth.service.ExternalAdminLoginService;
import egovframework.portal.sys.sysAuth.service.ExternalAdminMngService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.LoginManager;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;

/**
 * 외부 관리자 로그인 CONTROLLEr
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
public class ExternalAdminLoginController {

	@Resource
	protected ExternalAdminMngService service;
	@Resource
	protected ExternalAdminLoginService loginService;
	@Resource
	protected AdminMngService adminMngService;

	/** 외부 관리자 로그인 화면 */
	@RequestMapping("/sys/{mngSiteCode}/login.do")
	public String login(@PathVariable String mngSiteCode, @ModelAttribute("loginVO") AdminLoginVO loginVO, HttpServletRequest request, ModelMap model) {
		LoginManager.logout(request, SessionUtil.getAdminInstance(request));
		AdminType adminType = AdminType.toType(mngSiteCode);
		if (adminType != null) {
			model.addAttribute("mngSiteCode", adminType.getMngSiteCode());
			model.addAttribute("mngSiteName", adminType.getName());
			return "/sys/externalAdminMng/login";
		}

		return "redirect:/";
	}

	/** 외부 관리자 로그인 처리 */
	@ResponseBody
	@RequestMapping("/sys/{mngSiteCode}/loginProc.do")
	public byte[] loginProc(@PathVariable String mngSiteCode, @ModelAttribute("loginVO") AdminLoginVO loginVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		response.setContentType("text/html; charset=UTF-8");

		JSONObject result = new JSONObject().put("success", Boolean.FALSE).put("msg", "비정상적인 요청입니다.");
		loginService.logout(request);

		AdminType adminType = AdminType.toType(mngSiteCode);
		if (adminType != null) {
			try {
				if (StringUtil.isNotBlank(loginVO.getAdminId()) && StringUtil.isNotBlank(loginVO.getPwd())) {
					AdminLoginVO admin = service.getEntity(loginVO.getAdminId(), adminType.getCode());
					if (admin == null) {
						result.put("msg", "ID 또는 비밀번호를 확인해주세요.");
					} else if (Constant.FAILED_LOGIN_LIMIT <= admin.getLoginFailedCnt()) { // 최대 로그인 실패 허용 횟수 초과
						result.put("msg", Constant.FAILED_LOGIN_LIMIT + "회 이상 비밀번호 오입력으로 인해 로그인 제한 상태입니다. 관리자에게 문의해주세요.");
						adminMngService.increaseFailedCnt(admin);
					} else if (!admin.getPwd().equals(loginVO.getEncPasswd())) { // 비밀번호 불일치
						adminMngService.increaseFailedCnt(admin);
						result.put(
							"msg",
							Constant.FAILED_LOGIN_LIMIT - 1 == admin.getLoginFailedCnt() //
								? Constant.FAILED_LOGIN_LIMIT + "회 이상 비밀번호 오류로 로그인이 제한됩니다." : "비밀번호가 일치하지 않습니다." //
						);
					} else { // 로그인
						admin.setAdminType(adminType);
						admin.setHostIp(SessionUtil.getRemoteAddr(request));
						loginService.login(request, admin);
						adminMngService.resetFailedCnt(admin);
						adminMngService.updateLastestLoginDt(admin);
						result.remove("msg");
						result.put("success", Boolean.TRUE);
						result.put("changePwTarget", "Y".equals(admin.getChangePwTarget()));
						result.put("firstMid", admin.getMidList().get(2)); // TODO 3뎁스 메뉴가 권한 분기되는 경우
					}
				}
			} catch (Exception e) {
				result.put("msg", "로그인 처리 중 오류가 발생했습니다.");
			}

		}

		return result.toString().getBytes("UTF-8");
	}

	/** 외부 관리자 로그아웃 처리 */
	@ResponseBody
	@RequestMapping("/sys/{mngSiteCode}/logout.do")
	public byte[] logout(@PathVariable String mngSiteCode, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		loginService.logout(request);
		return new JSONObject()
			.put("success", Boolean.TRUE)
			.toString()
			.getBytes("UTF-8");
	}

}
