package egovframework.portal.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import egovframework.portal.validation.impl.NumberOnlyValidator;

/**
 * 
 * @ Author : 권태성
 * @ Date : 2016. 8. 11.
 * @ Comment : 숫자만 입력가능하도록 처리 (기본 메시지 : 숫자만 입력할 수 있습니다.)
 * @ File Name : Number.java
 * @see
 *
 * << 개정이력(Modification Information) >>
 *   수정일     		 수정자           수정내용
 *  -------    		--------    ---------------------------
 *   2016. 8. 11.                  		최초 생성
 */
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberOnlyValidator.class)
@NotNull
public @interface NumberOnly {
	
	String message() default "숫자만 입력할 수 있습니다.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}