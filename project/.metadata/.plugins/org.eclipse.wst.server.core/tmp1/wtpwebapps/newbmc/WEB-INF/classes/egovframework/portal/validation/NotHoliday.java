package egovframework.portal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import egovframework.portal.validation.impl.NotHolidayValidator;

/**
 * 공휴일 검증 아노테이션
 *
 * @author J.Ryeon Lee
 * @since 2016.08.22
 */
@Documented
@Constraint(validatedBy = { NotHolidayValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotHoliday {

	String message() default "공휴일입니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String pattern() default "yyyy-MM-dd";

}
