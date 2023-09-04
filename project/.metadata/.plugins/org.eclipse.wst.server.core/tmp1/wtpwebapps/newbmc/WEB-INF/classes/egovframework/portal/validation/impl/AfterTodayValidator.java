package egovframework.portal.validation.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import egovframework.portal.util.StringUtil;
import egovframework.portal.validation.AfterToday;

/**
 * 날짜 기간 검증 클래스
 *
 * @author J.Ryeon Lee
 * @since 2016.08.22
 */
@Component
public class AfterTodayValidator implements ConstraintValidator<AfterToday, String> {

	private int compare = -1;
	private String pattern = "yyyy-MM-dd";
	private SimpleDateFormat formatter = null;

	@Override
	public void initialize(AfterToday arg0) {
		if (arg0 != null) {
			compare = arg0.compare();
			pattern = arg0.pattern();
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtil.isBlank(value)) {
			return true;
		}

		return isAfterToday(value);
	}

	public boolean isAfterToday(String value) {
		try {
			formatter = new SimpleDateFormat(pattern);

			Date target = formatter.parse(value);
			int result = target.compareTo(new Date());

			return result > compare;
		} catch (ParseException e) {
			return false;
		}
	}
}
