package egovframework.portal.unit.bmc.user.web;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.common.SessionKey;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.unit.bmc.user.service.BmcUserService;
import egovframework.portal.unit.bmc.user.vo.BmcUserVO;
import egovframework.portal.unit.bmc.util.BmcUserUtil;
import egovframework.portal.unit.portal.user.CertResult;
import egovframework.portal.unit.portal.user.vo.ConfirmVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;

@Controller
public class BmcUserController {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected BmcUserService bmcUserService;
	@Resource(name = "userValidator")
	protected Validator validator;

	@SuppressWarnings("unused")
	private final Logger LOGGER = LogManager.getLogger();
	
	/** 본인인증 페이지 */
	@RequestMapping("/bmc/user/inRealName.do")
	public String inRealName(@RequestParam String mId, HttpSession session,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경
		SecurityContextHolder.getContext().setAuthentication(null); // 세션 초기화
		
		//20.05.12 고객경영참여 본인인증 파라미터오류로 세션값에 저장
		session.removeAttribute("idx");
		session.setAttribute("idx", request.getParameter("idx"));
		
		model.addAttribute("successUrl", request.getParameter("successUrl"));
		model.addAttribute("cancelUrl", (String)request.getHeader("Referer"));
		model.addAttribute("mId", mId);
		model.addAttribute("page", ServletRequestUtils.getIntParameter(request, "page", 1));
		model.addAttribute("sAuthType", StringUtil.changeBlankToBlank(request.getParameter("sAuthType")));

		return "/bmc/unit/user/inRealName/";
	}

	/** 로그인 폼 */
	@RequestMapping("/bmc/user/loginForm.do")
	public String loginForm(Authentication authentication, @ModelAttribute BmcUserVO bmcUserVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		bmcUserService.logout();
		return "/bmc/unit/user/loginForm/";
	}

	/** 정책 안내 페이지 */
	@RequestMapping(value = "/bmc/user/policy.do", method = RequestMethod.GET)
	public String policy(@ModelAttribute ConfirmVO confirmVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		bmcUserService.logout();
		request.getSession().setAttribute(SessionKey.USER_CONFIRM.getKey(), null); // init session
		return "/bmc/unit/user/policy/";
	}

	/** 정책 안내 페이지 */
	@RequestMapping(value = "/bmc/user/policy.do", method = RequestMethod.POST)
	public String policyProc(@RequestParam String mId, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute @Valid ConfirmVO confirmVO, BindingResult result) throws Exception {
		bmcUserService.logout(); // clear user info

		if (result.hasErrors()) {
			model.addAttribute("error", Boolean.TRUE);
			return "/bmc/unit/user/policy/";
		}

		session.setAttribute(SessionKey.USER_CONFIRM.getKey(), confirmVO);
		model.clear();
		return "redirect:/bmc/user/join.do?mId=" + mId;
	}

	/** 정책 동의 처리 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/bmc/user/join.do")
	public String join(@ModelAttribute BmcUserVO bmcUserVO, @RequestParam String mId, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Object _confirmVO = session.getAttribute(SessionKey.USER_CONFIRM.getKey());
		ConfirmVO confirmVO = _confirmVO != null ? (ConfirmVO) _confirmVO : null;
		BmcUserVO user = BmcUserUtil.getInstance();
		if (confirmVO == null) {
			bmcUserService.logout();
			WriterUtil.flushJSInvalidAccess(response);
			return null;
		} else if (bmcUserService.isDuplicate(confirmVO)) { // 중복 DI
			bmcUserService.logout();
			WriterUtil.flushJSAlert(response, WriterUtil.createJsAlertContent("가입 이력이 있습니다.", "location.href = '/bmc/contents.do?mId=" + EgovProperties.getProperty(Constant.USER_LOGIN_MID) + "'"));
			return null;
		} else if ("1".equals(confirmVO.getAgeType()) && user.getAge() >= 14) {
			WriterUtil.flushJsAlertAndRedirect(response, "14세 이상은 14세 미만으로 가입할 수 없습니다.", "document.location.href='/bmc/contents.do?mId=" + mId + "';");
			return null;
		} else if ("0".equals(confirmVO.getAgeType()) && user.getAge() < 14) {
			WriterUtil.flushJsAlertAndRedirect(response, "14세 미만은 14세 이상으로 가입할 수 없습니다.", "document.location.href='/bmc/contents.do?mId=" + mId + "';");
			return null;
		}
		bmcUserVO.setConfirm(confirmVO);
		model.addAttribute("bmcUserVO", bmcUserVO);

		return "/bmc/unit/user/join/";
	}

	/** 회원 ID 중복 체크 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@ResponseBody
	@RequestMapping(value = "/bmc/user/check/id/unique.do", method = RequestMethod.POST)
	public String isUnique(@ModelAttribute BmcUserVO bmcUserVO) throws Exception {
		JSONObject rtn = new JSONObject();

		BmcUserVO record = bmcUserService.getUserByUserId(bmcUserVO.getUserId());
		rtn.put("success", record == null);

		return rtn.toString();
	}

	/** 회원 가입 처리 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@ResponseBody
	@RequestMapping(value = "/bmc/user/joinProc.do", method = RequestMethod.POST)
	public String joinProc(HttpSession session, @ModelAttribute @Valid BmcUserVO bmcUserVO, BindingResult result, HttpServletRequest request) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		Object _confirm = session.getAttribute(SessionKey.USER_CONFIRM.getKey());
		ConfirmVO confirmVO = _confirm != null ? (ConfirmVO) _confirm : null;
		BmcUserVO user = BmcUserUtil.getInstance();

		if (_confirm == null) {
			bmcUserService.logout();
			rtn.put("errCode", 0);
			return rtn.toString();
		}

		if ("1".equals(confirmVO.getAgeType()) && user.getAge() >= 14) {
			bmcUserService.logout();
			rtn.put("errCode", 0);
			return rtn.toString();
		} else if ("0".equals(confirmVO.getAgeType()) && user.getAge() < 14) {
			bmcUserService.logout();
			rtn.put("errCode", 0);
			return rtn.toString();
		} else if ("1".equals(confirmVO.getAgeType()) && user.getAge() < 14) {
			bmcUserVO.setParentPrivatecode((String) session.getAttribute("parentDI"));
		}

		if ("1".equals(confirmVO.getAgeType())) {
			bmcUserVO.setParentPrivatecode((String) session.getAttribute("parentDI"));
			bmcUserVO.setParentName((String) session.getAttribute("parentName"));
		}

		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldErrors().get(0).getField());
			rtn.put("errCode", 1);
			return rtn.toString();
		}

		validator.validate(bmcUserVO, result); // 추가적 오류 검증
		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldErrors().get(0).getField());
			rtn.put("errCode", 2);
			return rtn.toString();
		}

		bmcUserVO.setConfirm(confirmVO);
		bmcUserService.insert(bmcUserVO, bmcUserVO.getUserId());
		bmcUserService.insertUserLog(bmcUserVO.getUserId(), request.getRemoteAddr(), "회원가입");

		session.removeAttribute(SessionKey.USER_CONFIRM.getKey());
		bmcUserService.logout();
		rtn.put("success", Boolean.TRUE);

		return rtn.toString();
	}

	/** 회원 정보 수정 폼 */
	@Secured({ "ROLE_USER" })
	@RequestMapping("/bmc/user/modify.do")
	public String modify(@RequestParam String mId, @ModelAttribute BmcUserVO bmcUserVO, Authentication authentication, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		BeanUtils.copyProperties(authentication.getPrincipal(), bmcUserVO);
		model.addAttribute("userVO", bmcUserVO);
		return "/bmc/unit/user/modify/";
	}

	/** 회원 정보 수정 처리 */
	@Secured({ "ROLE_USER" })
	@ResponseBody
	@RequestMapping(value = "/bmc/user/modifyProc.do", method = RequestMethod.POST)
	public byte[] modifyProc(@ModelAttribute @Valid BmcUserVO bmcUserVO, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		validator.validate(bmcUserVO, result);
		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldError().getField());
			rtn.put("errMsg", "#" + result.getFieldError().getDefaultMessage());
		} else {
			BmcUserVO user = BmcUserUtil.getInstance();
			BmcUserVO origin = bmcUserService.getUserByUserId(user.getUserId());
			CertResult certResult = bmcUserService.cert(user.getUserId(), bmcUserVO.getPassword());
			switch (certResult) {
				case SUCCESS:
					bmcUserService.update(bmcUserVO);
					bmcUserService.insertUserLog(bmcUserVO.getUserId(), request.getRemoteAddr(),  changedValueList(origin, bmcUserVO) + " 수정");

					BeanUtils.copyProperties(bmcUserService.getUserByPrivatecode(user.getPrivatecode()), user); // 변경사항을 현재 로그인 정보에 반영
					rtn.put("success", Boolean.TRUE);
					break;
				case INVALID_PASSWORD:
					rtn.put("errCode", certResult.getErrCode());
					rtn.put("selector", "#password");
					break;
				default:
					bmcUserService.logout();
					rtn.put("errCode", certResult.getErrCode());
					rtn.put("redirect", "/main.do");
					break;
			}
		}

		return rtn.toString().getBytes("UTF-8");
	}

	private String changedValueList(BmcUserVO origin, BmcUserVO updateVO) {
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
	@RequestMapping("/bmc/user/deleteAccountProc.do")
	public byte[] deleteAccountProc(@RequestParam String password, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject().put("success", Boolean.FALSE);

		BmcUserVO user = BmcUserUtil.getInstance();
		CertResult certResult = bmcUserService.cert(user.getUserId(), password);
		switch (certResult) {
			case SUCCESS:
				bmcUserService.delete(user);
				bmcUserService.insertUserLog(user.getUserId(), request.getRemoteAddr(), "회원탈퇴");

				bmcUserService.logout();
				result.put("success", Boolean.TRUE);
				break;
			case INVALID_PASSWORD:
				result.put("errCode", certResult.getErrCode());
				break;
			default:
				bmcUserService.logout();
				result.put("errCode", certResult.getErrCode());
				result.put("redirect", "/main.do");
				break;
		}

		return result.toString().getBytes("UTF-8");
	}

	/** 회원정보 검색 폼 */
	@RequestMapping("/bmc/user/findAccount.do")
	public String findAccount(@RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		bmcUserService.logout();
		return "/bmc/unit/user/findAccount/";
	}

	/** 회원정보 검색 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/bmc/user/findAccountResult.do")
	public String findAccountResult(@RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		model.addAttribute("userName", BmcUserUtil.getName()); // 세션을 지우기 전 인증된 사용자의 이름을 저장
		model.addAttribute("bmcUserVO", bmcUserService.getUserByPrivatecode(BmcUserUtil.getDi()));
		bmcUserService.logout();

		return "/bmc/unit/user/findAccountResult/";
	}

	/** 임시 비밀번호 발급 처리 */
	@Secured({ "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/bmc/user/resetPasswordProc.do")
	public String resetPasswordProc(@RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		model.addAttribute("userName", BmcUserUtil.getName()); // 세션을 지우기 전 인증된 사용자의 이름을 저장
		BmcUserVO user = bmcUserService.getUserByPrivatecode(BmcUserUtil.getDi());
		if (user != null) {
			user.setPassword( //
				RandomStringUtils.random(5, Constant.PW_RANGE_CHARACTERS) + //
					RandomStringUtils.random(5, Constant.PW_RANGE_NUMBERS) + //
					RandomStringUtils.random(1, Constant.PW_RANGE_SPECIAL_CHARACTERS) //
			);
			bmcUserService.updatePassword(user);
			bmcUserService.insertUserLog(user.getUserId(), request.getRemoteAddr(), "임시비밀번호발급");

			model.addAttribute("tmpPw", user.getPassword());
			model.addAttribute("bmcUserVO", user);
		}

		commonService.commonDataCreater(request, response, model);
		bmcUserService.logout();
		return "/bmc/unit/user/findAccountResult/";
	}

	/** 비밀번호 재입력 체크 */
	@Secured({ "ROLE_USER", "ROLE_USER_NOT_CERTIFICATED", "ROLE_USER_DORMANT" })
	@ResponseBody
	@RequestMapping(value = "/bmc/user/chkPasswordProc.do", method = RequestMethod.POST)
	public String chkPasswordProc(@ModelAttribute BmcUserVO bmcUserVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject result = new JSONObject().put("success", Boolean.FALSE);

		BmcUserVO target = bmcUserService.getUserByUserId(bmcUserVO.getUserId());
		if (target == null) {
			result.put("errCode", 0);
		} else if (!target.getPassword().equals(bmcUserVO.getEncryptedPw())) {
			result.put("errCode", 1);
		} else {
			result.put("success", Boolean.TRUE);
		}

		return result.toString();
	}

}
