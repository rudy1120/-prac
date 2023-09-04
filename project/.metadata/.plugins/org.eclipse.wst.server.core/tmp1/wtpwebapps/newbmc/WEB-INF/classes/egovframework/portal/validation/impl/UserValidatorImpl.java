package egovframework.portal.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import egovframework.portal.common.Constant;
import egovframework.portal.unit.portal.user.service.UserService;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;

/**
 * 요원 VO 검증 로직
 *
 * @author J.Ryeon Lee
 * @since 2016.07.13
 */
@Service("userValidator")
public class UserValidatorImpl implements Validator {

	private final String INVALID_PASSWORD_PATTERN = "비밀번호는 특수 문자, 숫자, 영문자의 조합으로 구성된 9자리 이상의 문자열이어야 합니다.";

	@Autowired
	protected UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object _target, Errors errors) {
		UserVO target = (UserVO) _target;
		if (!errors.hasErrors()) {

			UserVO session = UserUtil.getInstance();
			if (StringUtil.isEmpty(session.getUserId())) { // 신규 가입
				if (isNotAppropriatePw(target.getPassword())) { // 비밀번호 특문 1자 이상, 숫자 4자 이상, 영문자 4자 이상 조합
					errors.rejectValue("password", String.format(INVALID_PASSWORD_PATTERN, Constant.PW_MIN_LENGTH));
				}

				UserVO record = userService.getUserByUserId(target.getUserId());
				if (record != null) { // check unique id
					errors.rejectValue("userId", "중복된 ID가 존재합니다.");
				}

				record = record == null ? userService.getUserByPrivatecode(session.getPrivatecode()) : null;
				if (record != null) { // check unique privatecode
					errors.rejectValue("privatecode", "이미 가입되어 있습니다.");
				}
			} else { // 수정
				if (StringUtil.isNotEmpty(target.getRetypePw()) && isNotAppropriatePw(target.getRetypePw())) { // 새 비밀번호 설정시 패턴 검증
					errors.rejectValue("retypePw", String.format(INVALID_PASSWORD_PATTERN, Constant.PW_MIN_LENGTH));
				}
			}
		}
	}

	public boolean isNotAppropriatePw(String pw) {
		return !isAppropriatePw(pw);
	}

	/** @see PasswordValidator */
	public boolean isAppropriatePw(String pw) {
		int hit = 0;

		if (pw.matches(Constant.NUMBER_REG)) {
			hit++;
		}
		if (pw.matches(Constant.ALPH_REG)) {
			hit++;
		}
		if (pw.matches(Constant.SPECIAL_REG)) {
			hit++;
		}

		return pw.length() >= Constant.PW_MIN_LENGTH && hit == 3;
	}

}
