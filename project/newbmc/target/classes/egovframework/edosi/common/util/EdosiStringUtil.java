package egovframework.edosi.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import egovframework.com.utl.fcc.service.EgovStringUtil;

public class EdosiStringUtil extends EgovStringUtil {

	public static String getChargeFormat(int charge) {
		if (charge >= 0) {
			DecimalFormat chargeFormat = new DecimalFormat("###,###");
			return chargeFormat.format(charge);
		} else {
			return "";
		}
	}

	public static String getBirthdayFormat(String date, String delim) {
		String _year = "";
		String _month = "";
		String _day = "";
		if (date != null && date.length() == 8) {
			_year = date.substring(0, 4);
			_month = date.substring(4, 6);
			_day = date.substring(6, 8);
			return _year + delim + _month + delim + _day;
		}
		return date;
	}

	public static String getCardViewFormat(String cardNo) {
		String _cardNo;
		String _tmp = "";
		if (cardNo != null && cardNo.length() >= 12) {
			_cardNo = cardNo.substring(0, 4) + "-xxxx-xxxx-";
			for (int i = 12; i < _cardNo.length(); i++) {
				_tmp = _tmp + "x";
			}
			return _cardNo + _tmp;
		} else {
			return "";
		}
	}

	/**
	 * 한글
	 *
	 * @param value
	 * @return
	 */
	public static String toKorean(String value) {
		if (value != null) {
			try {
				value = new String(value.getBytes("ISO-8859-1"), "EUC_KR");
				return value;
			} catch (UnsupportedEncodingException e) {
				System.out.println("UnsupportedEncodingException Occured"); // 보안점검 후속조치
			}
		}
		return value;
	}

	/**
	 * 한글
	 *
	 * @param value
	 * @return
	 */
	public static String toUnicode(String value) {
		if (value != null) {
			try {
				value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				return value;
			} catch (UnsupportedEncodingException e) {
				System.out.println("UnsupportedEncodingException Occured"); // 보안점검 후속조치
			}
		}
		return value;
	}

	/**
	 * 한글(프로그램)에서 입력되는 특수문자를 웹에서 대처가능한 문자로 치환한다.
	 *
	 * @param str
	 * @return
	 */
	public static String replaceHangulToSpecialChar(String str) {
		if (str == null) return "";
		str = str.replaceAll("❍", "&Omicron;");
		str = str.replaceAll("․", "&middot;");
		str = str.replaceAll("｢", "&lceil;");
		str = str.replaceAll("｣", "&rfloor;");
		return str;
	}

	/**
	 * 웹에서 대처가능한 특수 문자를 다시 한글용 특수문자로 변경한다.
	 *
	 * @param str
	 * @return
	 */
	public static String replaceSpecialCharToHangul(String str) {
		if (str == null) return "";
		str = str.replaceAll("&Omicron;", "❍");
		str = str.replaceAll("&middot;", "․");
		str = str.replaceAll("&lceil;", "｢");
		str = str.replaceAll("&rfloor;", "｣");
		return str;
	}

}
