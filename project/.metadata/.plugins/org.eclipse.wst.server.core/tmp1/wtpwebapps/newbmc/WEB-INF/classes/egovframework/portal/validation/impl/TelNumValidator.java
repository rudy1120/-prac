package egovframework.portal.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import egovframework.portal.validation.TelNum;

/**
 * 
 * @ Author : 권태성
 * @ Date : 2016. 8. 11.
 * @ Comment : 
 * @ File Name : TelNumValidator.java
 * @see
 *
 * << 개정이력(Modification Information) >>
 *   수정일     		 수정자           수정내용
 *  -------    		--------    ---------------------------
 *   2016. 8. 11.                  		최초 생성
 */
public class TelNumValidator implements ConstraintValidator<TelNum, String>{

	private Pattern pattern = Pattern.compile("^[0-9]\\d{2}-(\\d{3}|\\d{4})-\\d{4}$");
	
	@Override
	public void initialize(TelNum constraintAnnotation) {
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
