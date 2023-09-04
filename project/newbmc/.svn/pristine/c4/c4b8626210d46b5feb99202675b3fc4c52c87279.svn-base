package egovframework.portal.unit.portal.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.unit.portal.user.service.UserService;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;
import egovframework.portal.util.WriterUtil;

/**
 * 휴면 회원 관리 CONTROLLER
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 2. 8.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 2. 8.
 */
@Controller
public class DormantUserController {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected UserService userService;

	private final Logger LOGGER = LogManager.getLogger();

	/** 회원 활성화 폼 */
	@Secured({ "ROLE_USER_DORMANT" })
	@RequestMapping("/portal/user/activation.do")
	public String activation(@ModelAttribute UserVO userVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		BeanUtils.copyProperties(UserUtil.getInstance(), userVO);

		commonService.commonDataCreater(request, response, model);
		return "/portal/unit/user/activation/";
	}

	/** 회원 활성화 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/portal/user/activationProc.do")
	public String activationProc(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		UserVO authenticated = UserUtil.getInstance();

		String newDi = authenticated.getPrivatecode();
		String msg = "잘못된 접근입니다.";
		String returnPath = "location.href='/main.do'";

		if (StringUtil.isNotBlank(userId) && StringUtil.isNotBlank(newDi)) { // id와 di값으로 로그인과 인증 여부를 체크
			returnPath = "location.href='/portal/contents.do?mId=" + EgovProperties.getProperty(Constant.USER_MODIFY_MID) + "'";

			UserVO target = userService.getUserByUserId(userId);
			if (target == null) {
				msg = "회원 정보가 존재하지 않습니다.";
			} else if (StringUtil.isBlank(target.getPrivatecode())) {
				msg = "미인증 회원입니다. 의정부시 정보통신과로 문의해주세요.";
			} else if (!target.getPrivatecode().equals(newDi)) {
				msg = "가입 당시 정보와 일치하지 않습니다.";
			} else {
				try {
					userService.activate(target, newDi);
					userService.insertUserLog(target.getUserId(), request.getRemoteAddr(), "휴면회원활성화");
					userService.logout();
					msg = "계정을 활성화했습니다. 재로그인해주세요.";
					returnPath = "location.href='/portal/contents.do?mId=" + EgovProperties.getProperty(Constant.USER_LOGIN_MID) + "'";
				} catch (Exception e) {
					LOGGER.error(">> failed to run [certificationProc]", e);
					msg = "처리 중 오류가 발생했습니다. 의정부시 정보통신과로 문의해주세요.";
				}
			}

		}

		userService.logout(); // 본인 인증을 통해 생성된 session clear
		WriterUtil.flushJsAlertAndRedirect(response, msg, returnPath);
		return null;
	}

	/** 1년간 미로그인 회원 휴면 처리 */
	@ResponseBody
	@RequestMapping("/crontab/user/sortDormantUser.do")
	public String sortDormantUser(HttpServletRequest request) throws Exception {
		return new JSONObject()
			.put("success", Boolean.TRUE)
			.put("cnt", userService.sortDormantUser(request.getRemoteAddr()))
			.toString();
	}

	/** 2년간 미로그인 회원 삭제 처리 */
	@ResponseBody
	@RequestMapping("/crontab/user/deleteDormantUser.do")
	public String deleteDormantUser(HttpServletRequest request) throws Exception {
		return new JSONObject()
			.put("success", Boolean.TRUE)
			.put("cnt", userService.deleteDormantUser(request.getRemoteAddr()))
			.toString();
	}

}
