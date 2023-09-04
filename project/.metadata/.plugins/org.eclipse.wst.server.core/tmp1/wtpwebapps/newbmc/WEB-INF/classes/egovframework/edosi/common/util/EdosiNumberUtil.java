package egovframework.edosi.common.util;

import egovframework.com.utl.fcc.service.EgovNumberUtil;

public class EdosiNumberUtil extends EgovNumberUtil {

	public static String onlyNumber(String str) {
		String restr = "";
		char c = ' ';
		if (str == null || str.equals("")) {
			return restr;
		}
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c >= 48 && c <= 57) {
				restr += c;
			}
		}
		return restr;
	}
}
