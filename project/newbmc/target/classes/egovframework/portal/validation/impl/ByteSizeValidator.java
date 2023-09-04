package egovframework.portal.validation.impl;

import java.io.UnsupportedEncodingException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import egovframework.portal.util.StringUtil;
import egovframework.portal.validation.ByteSize;

/**
 * 입력값 byte 길이 검증 클래스
 *
 * @author J.Ryeon Lee
 * @since 2016.05.27
 */
@Component
public class ByteSizeValidator implements ConstraintValidator<ByteSize, String> {

	private String encoding;
	private int max;

	@Override
	public void initialize(ByteSize byteSize) {
		encoding = byteSize.encoding();
		max = byteSize.max();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtil.isBlank(value)) {
			return true;
		}

		return isByteSizeValid(value);
	}

	private boolean isByteSizeValid(String value) {
		try {
			byte[] bytes = value.getBytes(encoding);
			if (bytes.length > max) {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			return false;
		}

		return true;
	}
}
