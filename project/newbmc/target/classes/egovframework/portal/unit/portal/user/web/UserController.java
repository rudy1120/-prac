package egovframework.portal.unit.portal.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.common.SessionKey;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.unit.portal.user.CertResult;
import egovframework.portal.unit.portal.user.service.UserService;
import egovframework.portal.unit.portal.user.vo.ConfirmVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;
import egovframework.portal.util.WriterUtil;

/**
 * 회원 관리 CONTROLLER
 *
 * <pre>
 * 수정일			작업자				작업내용
 * -------------	----------------	------------------------------------------------------
 * 2015.12.10		J.Ryeon Lee			최초 생성 및 코딩
 * 2016.07.05		J.Ryeon Lee			spring-security 적용
 * 2016.12.23		J.Ryeon Lee			기능 이식 후 의정부시에 맞춰 기능 커스텀 (14세 미만 가입 로직 지원)
 * 2017.08.03		J.Ryeon Lee			기능 개선 및 MyBatis 처리
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2015.12.10
 */
@Controller
public class UserController {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected UserService userService;
	@Resource(name = "userValidator")
	protected Validator validator;

	private final Logger LOGGER = LogManager.getLogger();

	/** 로그인 폼 */
	@RequestMapping("/portal/user/loginForm.do")
	public String loginForm(Authentication authentication, @ModelAttribute UserVO userVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		userService.logout();
		return "/portal/unit/user/loginForm/";
	}

	/** 정책 안내 페이지 */
	@RequestMapping(value = "/portal/user/policy.do", method = RequestMethod.GET)
	public String policy(@ModelAttribute ConfirmVO confirmVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		userService.logout();
		request.getSession().setAttribute(SessionKey.USER_CONFIRM.getKey(), null); // init session
		return "/portal/unit/user/policy/";
	}

	/** 정책 안내 페이지 */
	@RequestMapping(value = "/portal/user/policy.do", method = RequestMethod.POST)
	public String policyProc(@RequestParam String mId, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute @Valid ConfirmVO confirmVO, BindingResult result) throws Exception {
		userService.logout(); // clear user info

		if (result.hasErrors()) {
			model.addAttribute("error", Boolean.TRUE);
			return "/portal/unit/user/policy/";
		}

		session.setAttribute(SessionKey.USER_CONFIRM.getKey(), confirmVO);
		model.clear();
		return "redirect:/portal/user/join.do?mId=" + mId;
	}

	/** 정책 동의 처리 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/portal/user/join.do")
	public String join(@ModelAttribute UserVO userVO, @RequestParam String mId, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Object _confirmVO = session.getAttribute(SessionKey.USER_CONFIRM.getKey());
		ConfirmVO confirmVO = _confirmVO != null ? (ConfirmVO) _confirmVO : null;
		UserVO user = UserUtil.getInstance();
		if (confirmVO == null) {
			userService.logout();
			WriterUtil.flushJSInvalidAccess(response);
			return null;
		} else if (userService.isDuplicate(confirmVO)) { // 중복 DI
			userService.logout();
			WriterUtil.flushJSAlert(response, WriterUtil.createJsAlertContent("가입 이력이 있습니다.", "location.href = '/portal/contents.do?mId=" + EgovProperties.getProperty(Constant.USER_LOGIN_MID) + "'"));
			return null;
		} else if ("1".equals(confirmVO.getAgeType()) && user.getAge() >= 14) {
			WriterUtil.flushJsAlertAndRedirect(response, "14세 이상은 14세 미만으로 가입할 수 없습니다.", "document.location.href='/portal/contents.do?mId=" + mId + "';");
			return null;
		} else if ("0".equals(confirmVO.getAgeType()) && user.getAge() < 14) {
			WriterUtil.flushJsAlertAndRedirect(response, "14세 미만은 14세 이상으로 가입할 수 없습니다.", "document.location.href='/portal/contents.do?mId=" + mId + "';");
			return null;
		}
		userVO.setConfirm(confirmVO);
		model.addAttribute("userVO", userVO);

		return "/portal/unit/user/join/";
	}

	/** 회원 ID 중복 체크 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@ResponseBody
	@RequestMapping(value = "/portal/user/check/id/unique.do", method = RequestMethod.POST)
	public String isUnique(@ModelAttribute UserVO userVO) throws Exception {
		JSONObject rtn = new JSONObject();

		UserVO record = userService.getUserByUserId(userVO.getUserId());
		rtn.put("success", record == null);

		return rtn.toString();
	}

	/** 회원 가입 처리 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@ResponseBody
	@RequestMapping(value = "/portal/user/joinProc.do", method = RequestMethod.POST)
	public String joinProc(HttpSession session, @ModelAttribute @Valid UserVO userVO, BindingResult result, HttpServletRequest request) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		Object _confirm = session.getAttribute(SessionKey.USER_CONFIRM.getKey());
		ConfirmVO confirmVO = _confirm != null ? (ConfirmVO) _confirm : null;
		UserVO user = UserUtil.getInstance();

		if (_confirm == null) {
			userService.logout();
			rtn.put("errCode", 0);
			return rtn.toString();
		}

		if ("1".equals(confirmVO.getAgeType()) && user.getAge() >= 14) {
			userService.logout();
			rtn.put("errCode", 0);
			return rtn.toString();
		} else if ("0".equals(confirmVO.getAgeType()) && user.getAge() < 14) {
			userService.logout();
			rtn.put("errCode", 0);
			return rtn.toString();
		} else if ("1".equals(confirmVO.getAgeType()) && user.getAge() < 14) {
			userVO.setParentPrivatecode((String) session.getAttribute("parentDI"));
		}

		if ("1".equals(confirmVO.getAgeType())) {
			userVO.setParentPrivatecode((String) session.getAttribute("parentDI"));
			userVO.setParentName((String) session.getAttribute("parentName"));
		}

		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldErrors().get(0).getField());
			rtn.put("errCode", 1);
			return rtn.toString();
		}

		validator.validate(userVO, result); // 추가적 오류 검증
		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldErrors().get(0).getField());
			rtn.put("errCode", 2);
			return rtn.toString();
		}

		userVO.setConfirm(confirmVO);
		userService.insert(userVO, userVO.getUserId());
		userService.insertUserLog(userVO.getUserId(), request.getRemoteAddr(), "회원가입");

		session.removeAttribute(SessionKey.USER_CONFIRM.getKey());
		userService.logout();
		rtn.put("success", Boolean.TRUE);

		return rtn.toString();
	}

	/** 회원 정보 수정 폼 */
	@Secured({ "ROLE_USER" })
	@RequestMapping("/portal/user/modify.do")
	public String modify(@RequestParam String mId, @ModelAttribute UserVO userVO, Authentication authentication, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		BeanUtils.copyProperties(authentication.getPrincipal(), userVO);
		model.addAttribute("userVO", userVO);
		return "/portal/unit/user/modify/";
	}

	/** 회원 정보 수정 처리 */
	@Secured({ "ROLE_USER" })
	@ResponseBody
	@RequestMapping(value = "/portal/user/modifyProc.do", method = RequestMethod.POST)
	public byte[] modifyProc(@ModelAttribute @Valid UserVO userVO, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		validator.validate(userVO, result);
		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldError().getField());
			rtn.put("errMsg", "#" + result.getFieldError().getDefaultMessage());
		} else {
			UserVO user = UserUtil.getInstance();
			UserVO origin = userService.getUserByUserId(user.getUserId());
			CertResult certResult = userService.cert(user.getUserId(), userVO.getPassword());
			switch (certResult) {
				case SUCCESS:
					userService.update(userVO);
					userService.insertUserLog(userVO.getUserId(), request.getRemoteAddr(),  changedValueList(origin, userVO) + " 수정");

					BeanUtils.copyProperties(userService.getUserByPrivatecode(user.getPrivatecode()), user); // 변경사항을 현재 로그인 정보에 반영
					rtn.put("success", Boolean.TRUE);
					break;
				case INVALID_PASSWORD:
					rtn.put("errCode", certResult.getErrCode());
					rtn.put("selector", "#password");
					break;
				default:
					userService.logout();
					rtn.put("errCode", certResult.getErrCode());
					rtn.put("redirect", "/main.do");
					break;
			}
		}

		return rtn.toString().getBytes("UTF-8");
	}

	private String changedValueList(UserVO origin, UserVO updateVO) {
		List<String> colList = new ArrayList<>();

		if (StringUtil.isNotBlank(updateVO.getNewPassword())) {
			colList.add("비밀번호");
		}

		String originTel = StringUtil.concat("-", origin.getTel1(), origin.getTel2(), origin.getTel3());
		String updateTel = StringUtil.concat("-", updateVO.getTel1(), updateVO.getTel2(), updateVO.getTel3());
		if (!originTel.equals(updateTel)) {
			colList.add("전화번호");
		}

		return colList.toString();
	}

	/** 탈퇴 처리 */
	@Secured({ "ROLE_USER" })
	@ResponseBody
	@RequestMapping("/portal/user/deleteAccountProc.do")
	public byte[] deleteAccountProc(@RequestParam String password, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject().put("success", Boolean.FALSE);

		UserVO user = UserUtil.getInstance();
		CertResult certResult = userService.cert(user.getUserId(), password);
		switch (certResult) {
			case SUCCESS:
				userService.delete(user);
				userService.insertUserLog(user.getUserId(), request.getRemoteAddr(), "회원탈퇴");

				userService.logout();
				result.put("success", Boolean.TRUE);
				break;
			case INVALID_PASSWORD:
				result.put("errCode", certResult.getErrCode());
				break;
			default:
				userService.logout();
				result.put("errCode", certResult.getErrCode());
				result.put("redirect", "/main.do");
				break;
		}

		return result.toString().getBytes("UTF-8");
	}

	/** 회원정보 검색 폼 */
	@RequestMapping("/portal/user/findAccount.do")
	public String findAccount(@RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		userService.logout();
		return "/portal/unit/user/findAccount/";
	}

	/** 회원정보 검색 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/portal/user/findAccountResult.do")
	public String findAccountResult(@RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		model.addAttribute("userName", UserUtil.getName()); // 세션을 지우기 전 인증된 사용자의 이름을 저장
		model.addAttribute("userVO", userService.getUserByPrivatecode(UserUtil.getDi()));
		userService.logout();

		return "/portal/unit/user/findAccountResult/";
	}

	/** 임시 비밀번호 발급 처리 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/portal/user/resetPasswordProc.do")
	public String resetPasswordProc(@RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		model.addAttribute("userName", UserUtil.getName()); // 세션을 지우기 전 인증된 사용자의 이름을 저장
		UserVO user = userService.getUserByPrivatecode(UserUtil.getDi());
		if (user != null) {
			user.setPassword( //
				RandomStringUtils.random(5, Constant.PW_RANGE_CHARACTERS) + //
					RandomStringUtils.random(5, Constant.PW_RANGE_NUMBERS) + //
					RandomStringUtils.random(1, Constant.PW_RANGE_SPECIAL_CHARACTERS) //
			);
			userService.updatePassword(user);
			userService.insertUserLog(user.getUserId(), request.getRemoteAddr(), "임시비밀번호발급");

			model.addAttribute("tmpPw", user.getPassword());
			model.addAttribute("userVO", user);
		}

		commonService.commonDataCreater(request, response, model);
		userService.logout();
		return "/portal/unit/user/findAccountResult/";
	}

	/** 본인 인증 폼 */
	@Secured({ "ROLE_USER_NOT_CERTIFICATED" })
	@RequestMapping("/portal/user/certification.do")
	public String certification(@ModelAttribute UserVO userVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		UserVO logined = UserUtil.getInstance();
		userVO.setUserId(logined.getUserId());
		userVO.setUserName(logined.getUserName());

		if (isNotCertificated(logined)) {
			return "/portal/unit/user/certification/";
		}

		userService.logout();
		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

	/** 비밀번호 재입력 체크 */
	@Secured({ "ROLE_USER", "ROLE_USER_NOT_CERTIFICATED", "ROLE_USER_DORMANT" })
	@ResponseBody
	@RequestMapping(value = "/portal/user/chkPasswordProc.do", method = RequestMethod.POST)
	public String chkPasswordProc(@ModelAttribute UserVO userVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject result = new JSONObject().put("success", Boolean.FALSE);

		UserVO target = userService.getUserByUserId(userVO.getUserId());
		if (target == null) {
			result.put("errCode", 0);
		} else if (!target.getPassword().equals(userVO.getEncryptedPw())) {
			result.put("errCode", 1);
		} else {
			result.put("success", Boolean.TRUE);
		}

		return result.toString();
	}

	/** 본인 인증 처리 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/portal/user/certificationProc.do")
	public String certificationProc(@RequestParam String userId, @RequestParam String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		UserVO authenticated = UserUtil.getInstance();

		String newDi = authenticated.getPrivatecode();
		String msg = "잘못된 접근입니다.";
		String returnPath = "location.href='/main.do'";

		if (StringUtil.isNotBlank(userId) && StringUtil.isNotBlank(newDi)) { // id와 di값으로 로그인과 인증 여부를 체크
			returnPath = "location.href='/portal/contents.do?mId=" + EgovProperties.getProperty(Constant.USER_MODIFY_MID) + "'";

			UserVO target = userService.getUserByUserId(userId);
			if (target == null) {
				msg = "회원 정보가 존재하지 않습니다.";
			} else if (StringUtil.isNotBlank(target.getPrivatecode())) {
				msg = "이미 인증된 회원입니다.";
			} else if (!target.getPassword().equals(UserUtil.getEncodedPw(password))) {
				msg = "비밀번호가 일치하지 않습니다.";
			} else if (!target.getUserName().equals(UserUtil.getName())) {
				msg = "가입 당시 성명과 일치하지 않습니다.";
			} else {
				try {
					userService.updateUserPrivatecode(target, newDi);
					userService.insertUserLog(target.getUserId(), request.getRemoteAddr(), "재인증");

					msg = "본인 인증이 완료되었습니다. 재로그인해주세요.";
					returnPath = "location.href='/portal/contents.do?mId=" + EgovProperties.getProperty(Constant.USER_LOGIN_MID) + "'";
				} catch (Exception e) {
					LOGGER.error(">> failed to run [certificationProc]", e);
					msg = "처리 중 오류가 발생했습니다. 페이지 하단 담당자에게 문의해주세요.";
				}
			}
		}

		userService.logout(); // 본인 인증을 통해 생성된 session clear
		WriterUtil.flushJsAlertAndRedirect(response, msg, returnPath);
		return null;
	}

	private boolean isNotCertificated(UserVO user) {
		return user != null && StringUtil.isBlank(user.getPrivatecode());
	}

}
