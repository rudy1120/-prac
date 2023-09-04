package egovframework.portal.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import egovframework.portal.common.Constant;
import egovframework.portal.util.StringUtil;
import egovframework.portal.validation.Password;

/**
 * 비밀번호 검증 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 11. 1.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 11. 1.
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public void initialize(Password anno) {
		// no parameter
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtil.isNotBlank(value)) {
			int hit = 0;

			if (value.matches(Constant.NUMBER_REG)) {
				hit++;
			}
			if (value.matches(Constant.ALPH_REG)) {
				hit++;
			}
			if (value.matches(Constant.SPECIAL_REG)) {
				hit++;
			}

			return value.length() >= Constant.PW_MIN_LENGTH && hit > 2;
		}

		return true;
	}

}
