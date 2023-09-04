package egovframework.portal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import egovframework.portal.validation.impl.ByteSizeValidator;

/**
 * 입력값 byte 길이 검증 아노테이션
 *
 * @author J.Ryeon Lee
 * @since 2016.05.27
 */
@Documented
@Constraint(validatedBy = { ByteSizeValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ByteSize {

	String message() default "입력값의 길이가 너무 깁니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String encoding() default "UTF-8";

	int max();

}
