package egovframework.portal.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import egovframework.portal.validation.NumberOnly;

/**
 * 
 * @ Author : 권태성
 * @ Date : 2016. 8. 11.
 * @ Comment : 
 * @ File Name : NumberValidator.java
 * @see
 *
 * << 개정이력(Modification Information) >>
 *   수정일     		 수정자           수정내용
 *  -------    		--------    ---------------------------
 *   2016. 8. 11.                  		최초 생성
 */
public class NumberOnlyValidator implements ConstraintValidator<NumberOnly, String>{

	private Pattern pattern = Pattern.compile("^[0-9]*$");
	
	@Override
	public void initialize(NumberOnly constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.length() == 0) {
			return true;
		}
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}