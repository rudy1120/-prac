package egovframework.portal.security.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;

/**
 * SPRING SECURITY 회원 인증시 비밀번호 암호화 처리 SERVICE
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.07.15		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.07.15
 */
public class PasswordEncoderImpl implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return UserUtil.getEncodedPw(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return StringUtil.isNotBlank(rawPassword.toString()) && //
			StringUtil.isNotBlank(encodedPassword) && //
			encode(rawPassword).equals(encodedPassword);
	}

}
