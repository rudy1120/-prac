package egovframework.portal.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

import egovframework.portal.validation.impl.TelNumValidator;

/**
 * 
 * @ Author : 권태성
 * @ Date : 2016. 8. 11.
 * @ Comment : 전화번호 검증 000-0000-0000 형식을 검증 합니다.
 * @ File Name : TelNum.java
 * @see
 *
 * << 개정이력(Modification Information) >>
 *   수정일     		 수정자           수정내용
 *  -------    		--------    ---------------------------
 *   2016. 8. 11.                  		최초 생성
 */
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelNumValidator.class)
@Size(min = 12, max = 13)
public @interface TelNum {

	String message() default "전화번호가 올바르지 않습니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}