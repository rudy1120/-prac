package egovframework.portal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.Payload;

import egovframework.portal.validation.impl.AfterTodayValidator;

/**
 * 날짜 기간 검증 아노테이션
 *
 * @author J.Ryeon Lee
 * @since 2016.08.22
 */
@Documented
@Constraint(validatedBy = { AfterTodayValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterToday {

	String message() default "오늘 이후의 날짜를 선택해주세요.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/** 0: 금일 미포함 -1: 금일 포함 @see {@link Date#compareTo(Date)} */
	int compare() default 0;

	String pattern() default "yyyy-MM-dd";

}
