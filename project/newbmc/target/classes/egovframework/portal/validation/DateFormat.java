package egovframework.portal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.NotBlank;

import egovframework.portal.validation.impl.DateFormatValidator;

/**
 * 날짜 형식 검증 아노테이션
 *
 * @author J.Ryeon Lee
 * @since 2016.07.13
 */
@Documented
@Constraint(validatedBy = { DateFormatValidator.class })
@NotBlank
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {

	String message() default "날짜 형식에 어긋납니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String pattern() default "yyyy-MM-dd";

}
