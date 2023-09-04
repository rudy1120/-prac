package egovframework.portal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import egovframework.portal.validation.impl.PasswordValidator;

/**
 * 비밀번호 패턴 검증 아노테이션
 *
 * @author J.Ryeon Lee
 * @since 2016.11.01
 */
@Documented
@Constraint(validatedBy = { PasswordValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

	String message() default "비밀번호는 특수 문자, 숫자, 영문자의 조합으로 구성된 9자리 이상의 문자열이어야 합니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
