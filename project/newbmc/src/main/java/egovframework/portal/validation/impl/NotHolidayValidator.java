package egovframework.portal.validation.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import egovframework.portal.util.StringUtil;
import egovframework.portal.validation.NotHoliday;

/**
 * 날짜 기간 검증 클래스
 *
 * @author J.Ryeon Lee
 * @since 2016.08.22
 */
@Component
public class NotHolidayValidator implements ConstraintValidator<NotHoliday, String> {

	private String pattern;
	private SimpleDateFormat formatter = null;

	@Override
	public void initialize(NotHoliday arg0) {
		pattern = arg0.pattern();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtil.isBlank(value)) {
			return true;
		}

		return isNotHoliday(value);
	}

	private boolean isNotHoliday(String value) {
		try {
			formatter = new SimpleDateFormat(pattern);

			Calendar target = Calendar.getInstance();
			target.setTime(formatter.parse(value));

			int day = target.get(Calendar.DAY_OF_WEEK);

			return day != Calendar.SATURDAY && day != Calendar.SUNDAY;
		} catch (ParseException e) {
			return false;
		}
	}
}
