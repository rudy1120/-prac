package egovframework.portal.validation.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import egovframework.portal.util.StringUtil;
import egovframework.portal.validation.DateFormat;

/**
 * 날짜 형식 검증 클래스
 *
 * @author J.Ryeon Lee
 * @since 2016.07.13
 */
@Component
public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

	private String pattern;
	private SimpleDateFormat formatter = null;

	@Override
	public void initialize(DateFormat arg0) {
		pattern = arg0.pattern();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtil.isBlank(value)) {
			return true;
		}

		return isDateFormat(value);
	}

	private boolean isDateFormat(String value) {
		try {
			formatter = new SimpleDateFormat(pattern);
			String _value = formatter.format(formatter.parse(value));
			return _value.equals(value);
		} catch (ParseException e) {
			return false;
		}
	}
}
