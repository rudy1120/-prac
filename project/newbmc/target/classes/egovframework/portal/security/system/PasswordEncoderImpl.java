package egovframework.portal.security.system;

import org.springframework.security.crypto.password.PasswordEncoder;

import egovframework.portal.util.SecurityUtil;
import egovframework.portal.util.StringUtil;

public class PasswordEncoderImpl implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return SecurityUtil.encrypt(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return StringUtil.isNotBlank(rawPassword.toString()) && StringUtil.isNotBlank(encodedPassword) &&
			encode(rawPassword).equals(encodedPassword);
	}

}
