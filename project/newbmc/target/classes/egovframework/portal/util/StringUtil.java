package egovframework.portal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 문자열 유틸 열거형
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2016. 10. 16.	권태성				appendStr 메소드 추가
 * 2016. 12. 19.	권태성				clobToString 메소드 추가
 * 2017. 01. 05.	J.Ryeon Lee			웹 취약 코드 수정
 * 2017. 04. 12.	권태성				StrUtil 내 메소드들 통합
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2015. 07. 29
 */
public enum StringUtil {

	INSTANCE,;

	private static final Logger LOGGER = LogManager.getLogger();

	// TODO MOVE TO TEST PACKAGE
//	public static void main(String[] args) {
//		/* ===== test for isBlank(arg) ===== */
//		System.out.println(isBlank(null)); // result: TRUE
//		System.out.println(isBlank("")); // result: TRUE
//		System.out.println(isBlank(" ")); // result: TRUE
//		System.out.println(isBlank("	")); // result: TRUE
//		System.out.println(isBlank("    ")); // result: TRUE
//		System.out.println(isBlank(" a ")); // result: FALSE
//		System.out.println(isBlank("aaa")); // result: FALSE
//
//		/* ===== test for turnToNullWhenItsBlankValue(arg) */
//		System.out.println(changeBlankToNull(null)); // result: null
//		System.out.println(changeBlankToNull("")); // result: null
//		System.out.println(changeBlankToNull(" ")); // result: null
//		System.out.println(changeBlankToNull("	")); // result: nulll
//		System.out.println(changeBlankToNull("    ")); // result: nulll
//		System.out.println(changeBlankToNull(" a ")); // result: [ a ]
//		System.out.println(changeBlankToNull("aaa")); // result: [aaa]
//	}

	/**
	 * 확장자 추출
	 *
	 * @param fileName
	 * @return
	 */
	public static String extractFileExt(String fileName) {
		return StringUtil.isNotBlank(fileName) ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
	}

	/**
	 * 허용하는 확장자 검증
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean isLegalExtension(String fileName) {
		return isNotBlank(fileName) && !fileName.endsWith(".jsp") && !fileName.endsWith(".sh") && !fileName.endsWith(".asp");
	}

	/**
	 * 배열로 받은 문자배열에 key가 포함 여부 검증
	 *
	 * @param key
	 * @param arr
	 * @return
	 */
	public static boolean contains(String key, final String[] arr) {
		if (isNotBlank(key) && arr != null) {
			return Arrays.asList(arr).contains(key);
		}
		return Boolean.FALSE;
	}

	/**
	 * 숫자 검증
	 *
	 * @param arg
	 * @return
	 */
	public static boolean isNumber(String arg) {
		return isNotBlank(arg) && arg.matches("^[0-9]+$");
	}

	/**
	 * 숫자 아닌지 검증
	 *
	 * @param arg
	 * @return
	 */
	public static boolean isNotNumber(String arg) {
		return !isNumber(arg);
	}

	/**
	 * 문자열이 null 이거나 "" 일 때 true, 아닐 때 false
	 *
	 * @param arg
	 * @return
	 */
	public static boolean isEmpty(String arg) {
		if (arg == null) {
			return Boolean.TRUE;
		} else if (arg.trim().equals("")) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * {@link #isEmpty(String)}의 반대
	 *
	 * @param arg
	 * @return
	 */
	public static boolean isNotEmpty(String arg) {
		return !isEmpty(arg);
	}

	/**
	 * 파라미터가 "null", null, 탭 문자, 공백 문자, 공백 문자열인 경우 TRUE
	 *
	 * @param arg
	 * @return
	 */
	public static boolean isBlank(String arg) {
		if (arg == null) {
			return Boolean.TRUE;
		} else if (arg.trim().equals("")) {
			return Boolean.TRUE;
		} else if (arg.trim().equals("null")) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * {@link StringUtil#isBlank(String)}의 반대
	 *
	 * @param arg
	 * @return
	 */
	public static boolean isNotBlank(String arg) {
		return !isBlank(arg);
	}

	/**
	 * 문자배열 값 중에 {@link #isBlank(String)} 값이 포함되면 true
	 *
	 * @param args
	 * @return
	 */
	public static boolean hasBlankValue(String... args) {
		if (args != null) {
			for (String arg : args) {
				if (isBlank(arg)) {
					return Boolean.TRUE;
				}
			}
		}

		return Boolean.FALSE;
	}

	/**
	 * 문자 배열이 모두 값을 가지고 있음
	 *
	 * @param args
	 * @return
	 */
	public static boolean noBlankValue(String... args) {
		return !hasBlankValue(args);
	}

	/**
	 * 문자 배열이 모두 값이 없음
	 *
	 * @param args
	 * @return
	 */
	public static boolean areAllBlankValue(String... args) {
		if (args != null) {
			for (String arg : args) {
				if (isNotBlank(arg)) {
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.TRUE;
	}

	/**
	 * 공백 문자열을 null로 반환
	 *
	 * @param arg
	 * @return
	 */
	public static String changeBlankToNull(String arg) {
		return isBlank(arg) ? null : arg;
	}

	/**
	 * null을 포함하는 공백 문자열을 ""로 반환
	 *
	 * @param arg
	 * @return
	 */
	public static String changeBlankToBlank(String arg) {
		return isBlank(arg) ? "" : arg;
	}

	/**
	 * 문자 배열을 token으로 연결해서 반환
	 *
	 * @param token
	 * @param args
	 * @return
	 */
	public static String concat(String token, String... args) {
		StringBuilder rtn = new StringBuilder();
		if (args != null) {
			for (String arg : args) {
				if (StringUtil.isNotBlank(rtn.toString())) {
					rtn.append(token);
				}
				rtn.append(arg);
			}
		}

		return rtn.toString();
	}

	/**
	 * {@link String#trim()}의 레퍼메소드
	 *
	 * @param arg
	 * @return
	 */
	public static String trim(String arg) {
		return isNotBlank(arg) ? arg.trim() : "";
	}

	/**
	 * 문자열의 값이 Y인지 확인
	 *
	 * @param arg
	 * @return
	 */
	public static boolean equalY(String arg) {
		return StringUtil.isNotBlank(arg) && arg.equals("Y");
	}

	/**
	 * 문자열의 값이 N인지 확인
	 *
	 * @param arg
	 * @return
	 */
	public static boolean equalN(String arg) {
		return StringUtil.isNotBlank(arg) && arg.equals("N");
	}

	/**
	 * 스네이크케이스로 변환
	 *
	 * @param target
	 * @return
	 */
	public static String toSnakeCase(String target) {
		String rtn = "";
		if (isNotBlank(target)) {
			char[] cs = target.toCharArray();
			for (char c : cs) {
				if (String.valueOf(c).matches("[A-Z]+")) { // 대문자인 경우
					rtn += "_" + String.valueOf(c).toLowerCase();
				} else {
					rtn += c;
				}
			}
		}

		return rtn;
	}

	/**
	 * 각 문자 뒤에 arg 문자를 arg2에 입력받은 만큼 추가합니다. 단, 마지막 문자 뒤에는 추가하지 않음.
	 *
	 * @param source
	 * @param arg
	 * @param arg2
	 * @return
	 */
	public static String appendStr(String source, String arg, Integer arg2) {
		String tmp = "";
		for (int i = 0; i < source.length(); i++) {
			tmp += source.substring(i, i + 1);
			if (i + 1 != source.length()) {
				for (int j = 0; j < arg2; j++) {
					tmp += arg;
				}
			}
		}
		return tmp;
	}

	/**
	 * Clob 를 String 으로 변경
	 *
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String clobToString(Clob clob) throws Exception {
		StringBuffer strOut = new StringBuffer();
		if (clob != null) {
			String str = "";
			BufferedReader br = null;
			try {
				br = new BufferedReader(clob.getCharacterStream());
				while ((str = br.readLine()) != null) {
					strOut.append(str);
				}
			} catch (Exception e) {
				LOGGER.error(">> failed to convert clob to string");
			} finally {
				if (br != null) {
					br.close();
				}
			}
		}
		return strOut.toString();
	}

	/**
	 * 지정된 문자로 length개로 구성된 문자열 만듦
	 *
	 * @param c
	 * @param length
	 * @return
	 */
	public static String stringOf(char c, int length) {
		String s = "";
		while (--length >= 0) {
			s = s + c;
		}
		return s;
	}

	/**
	 * 문자열 내의 \n을 HTML의 <br/>
	 * 태그로 변환
	 *
	 * @param s
	 * @return
	 */
	/**
	 * 문자열 내의 \n을 HTML의 &lt;br/&gt; 태그로 변환
	 * @param s
	 * @return
	 */
	public static String nl2br(String s) {
		return nl2br(s, 1);
	}

	/**
	 * 문자열 내의 \n을 HTML의 <br/>
	 * 태그로 변환.
	 *
	 * @param s
	 * @param n
	 * @return
	 */
	public static String nl2br(String s, int n) {
		if (StringUtil.isNotBlank(s)) {
			String br = "<br />";
			while (n > 1) {
				br += br;
				n--;
			}
			return s.replaceAll("\\r\\n", br);
		}
		return s;
	}

	/**
	 * 문자열내의 ', ", \를 \', \", \\로 바꿈.
	 * 데이터베이스 쿼리 등에 사용.
	 * PHP의 AddSlashes() 참조.
	 *
	 * @param s
	 * @return
	 */
	public static String addSlashes(String s) {
		StringBuffer buffer = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, "\'\"\\", true);
		String token;
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			switch (token.charAt(0)) {
				case '\'':
					buffer.append("\\\'");
					break;
				case '\"':
					buffer.append("\\\"");
					break;
				case '\\':
					buffer.append("\\\\");
					break;
				default:
					buffer.append(token);
			}
		}
		return buffer.toString();
	}

	/**
	 * 문자열내의 HTML태그를 모두 제거.
	 *
	 * @param s
	 * @return
	 */
	public static String stripTags(String s) {
		StringBuffer buffer = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, "<>", true);
		String token;
		boolean inTag = false;
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			switch (token.charAt(0)) {
				case '<':
					inTag = true;
					break;
				case '>':
					inTag = false;
					break;
				default:
					if (!inTag) {
						buffer.append(token);
					}
			}
		}
		return buffer.toString();
	}

	/**
	 * 태그를 삭제
	 *
	 * @param str
	 * @return
	 */
	public static String removeTag(String str) {
		try {
			String tmp = "";
			if (str != null && !"".equals(str)) {
				int lt = str.indexOf("<");

				if (lt != -1) {
					int gt = str.indexOf(">", lt);
					if ((gt != -1)) {
						str = str.substring(0, lt) + str.substring(gt + 1);
						// 재귀 호출
						str = removeTag(str);
					}
				}
				tmp = str;
			}

			return tmp;
		} catch (Exception e) {
			LOGGER.error("[Util] removeTag 처리 중 오류");
			return "";
		}
	}

	/**
	 * html 태그가 포함되어있는지 확인
	 *
	 * @param str
	 * @return
	 */
	public static boolean isHtml(String str) {
		if (str != null && !"".equals(str)) {
			if (str.toLowerCase().indexOf("<p") > -1 || str.toLowerCase().indexOf("<table") > -1 || str.toLowerCase().indexOf("<tr") > -1 || str.toLowerCase().indexOf("<td") > -1 || str.toLowerCase().indexOf("<br") > -1)
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	/**
	 * HTML에서 특별한 의미를 갖는 문자들을 대체 문자열로 바꿈.
	 * 현재는 다음의 문자들에 대해서 처리한다:
	 *
	 * <pre>
	 * '&'(ampersand)를 '&amp;'
	 * '"'(double quote)를 '&quot;'
	 * '<'(less than)를 '&lt;'
	 * '>'(greater than)를 '&gt;'
	 * </pre>
	 *
	 * @param s
	 * @return
	 */
	public static String htmlSpecialChars(String s) {
		StringBuffer buffer = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, "&\"<>", true);
		String token;
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			switch (token.charAt(0)) {
				case '&':
					buffer.append("&amp;");
					break;
				case '\"':
					buffer.append("&quot;");
					break;
				case '<':
					buffer.append("&lt;");
					break;
				case '>':
					buffer.append("&gt;");
					break;
				default:
					buffer.append(token);
			}
		}
		return buffer.toString();
	}

	/**
	 * Removes invalid XML
	 *
	 * @param value $value
	 * @return string
	 */
	public static String stripInvalidXml(String value) {
		StringBuffer out = new StringBuffer();

		if (value == null || value.equals("")) {
			return "";
		}

		char current = '\u0000';

		for (int i = 0; i < value.length(); i++) {
			current = value.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF)) || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF))) {
				out.append(current);
			}
		}
		return out.toString();
	}

	/**
	 * 원본 문자열 내에서 s1을 s2로 바꿈.
	 * java.lang.String의 replace과 비슷하나 문자열을 대상으로 함.
	 *
	 * @param s 원본 문자열
	 * @param s1 찾을 문자열
	 * @param s2 바꿀 문자열
	 * @return 원본 문자열 내에서 s1이 s2로 바뀐 문자열
	 * @see java.lang.String#replace
	 */
	public static String replace(String s, String s1, String s2) {
		if (s == null || s1 == null || s2 == null) {
			return s;
		}

		StringBuffer result = new StringBuffer();
		int index1 = 0;
		int index2 = s.indexOf(s1);
		while (index2 >= 0) {
			if (index2 < 0) {
				break;
			}
			result.append(s.substring(index1, index2));
			result.append(s2);
			index1 = index2 + s1.length();
			index2 = s.indexOf(s1, index1);
		}
		result.append(s.substring(index1));
		return result.toString();
	}

	/**
	 * 문자열내의 \', \", \\를 ', ", \로 바꿈.
	 * addSlashes()함수가 한 동작의 역동작을 수행.
	 *
	 * @see #addSlashes
	 */
	public static String stripSlashes(String s) {
		StringBuffer buffer = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, "\'\"\\", true);
		String token;
		boolean escapeFlag = false;
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			switch (token.charAt(0)) {
				case '\'':
					buffer.append("\'");
					escapeFlag = false;
					break;
				case '\"':
					buffer.append("\"");
					escapeFlag = false;
					break;
				case '\\':
					if (escapeFlag) {
						buffer.append("\\");
						escapeFlag = false;
					} else {
						escapeFlag = true;
					}
					break;
				default:
					buffer.append(token);
			}
		}
		return buffer.toString();
	}

	/**
	 * 문자열을 지정된 구분자로 쪼개서 문자열 배열로 만듦
	 *
	 * @param str 원본 문자열
	 * @param delim 구분자 문자열
	 * @return 문자열 배열
	 */
	public static String[] toArray(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		String[] result = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			result[i++] = st.nextToken();
		}
		return result;
	}

	/**
	 * 문자열의 앞뒤에 지정된 길이가 되도록 특정 문자를 붙여준다.
	 *
	 * @param s 원본 문자열
	 * @param c 채울 문자
	 * @param len 돌려줄 문자열의 길이
	 * @param flag 채울 위치 0:앞에 그외는 뒤에
	 * @return 특정문자가 채워진 문자열
	 */
	public static String fillChar(String s, char c, int len, int flag) {
		if (s == null) {
			return s;
		}

		int slen = s.length();
		int tlen = (len > slen) ? len - slen : slen - len;
		String t = "";

		for (int i = 0; i < tlen; i++) {
			t += c;
		}

		if (flag == 0) {
			return t + s;
		} else {
			return s + t;
		}
	}

	/**
	 * 문자열형의 숫자를 받아 3자리마다 , 를 찍어서 반환
	 *
	 * @param num
	 * @return
	 */
	public static String formatNumber(String num) {
		if (num == null) {
			return "";
		}
		String temp = "";
		String num2 = "";
		if (num.indexOf("-") >= 0) {
			num2 = num.substring(1);
		} else {
			num2 = num;
		}
		while (num2.length() > 3) {
			temp = "," + num2.substring(num2.length() - 3, num2.length()) + temp;
			num2 = num2.substring(0, num2.length() - 3);
		}
		if (num.indexOf("-") >= 0) {
			return "-" + num2 + temp;
		} else {
			return (num2 + temp);
		}

	}

	/**
	 * 3자리마다 , 를 찍어 반환한다.
	 *
	 * @param number
	 * @return
	 */
	public static String formatNumber(long number) {
		return formatNumber(String.valueOf(number));
	}

	/**
	 * 3자리마다 , 를 찍어 반환한다.
	 *
	 * @param number
	 * @return
	 */
	public static String formatNumber(int number) {
		return formatNumber(String.valueOf(number));
	}

	/**
	 * 해당 구분자로 날짜를 리턴한다.
	 *
	 * @param strDate : YYYYMMDD
	 * @param delim : / or - ...etc
	 * @return
	 */
	public static String formatDate(String strDate, String delim) {
		if (strDate == null) {
			return "";
		}
		if (strDate.length() != 8) {
			return strDate;
		}

		return strDate.substring(0, 4) + delim + strDate.substring(4, 6) + delim + strDate.substring(6, 8);
	}

	/**
	 * 화페 단위로 변환
	 *
	 * @param money
	 * @return
	 */
	public static String convertMoney(long money) {

		String[] hanNumber = new String[] { "영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };
		String[] fourFour = new String[] { "일", "만", "억", "조" };
		String[] fourDigit = new String[] { "일", "십", "백", "천" };

		// if(money > Long.MAX_VALUE) money =Long.MAX_VALUE;

		if (money < Long.MAX_VALUE) {
			String moneyStr = String.valueOf(money);

			int bPos = 0; // 만, 억, 조
			int sPos = 0; // 십, 백, 천
			int digit = 0;

			boolean is_start = false;
			boolean appendFF = false;
			int len = moneyStr.length();
			String szHan = "";

			for (int i = len - 1; i >= 0; i--) {
				String szDigit = moneyStr.substring(i, i + 1);
				digit = Integer.parseInt(szDigit);

				if (digit != 0) {
					if (bPos != 0 && sPos == 0) {
						szHan += fourFour[bPos]; // 만, 억
						appendFF = false;
					}
					if (bPos != 0 && appendFF == true) {
						szHan += fourFour[bPos]; // 만, 억
						appendFF = false;
					}

					if (sPos != 0) {
						szHan += fourDigit[sPos]; // 십, 백, 천
					}
					szHan += hanNumber[digit]; // 일, 이, 삼
					is_start = true;

				} else if (sPos == 0 && bPos != 0) {
					appendFF = true;
				}
				sPos++;

				if (sPos % 4 == 0) {
					sPos = 0;
					bPos++;
					if (bPos >= 4) {
						LOGGER.info("범위초과");
					}
				}
			}
			if (is_start == false) {
				szHan += "";
			}
			String rslt = "";
			for (int i = szHan.length() - 1; i >= 0; i--) {
				if (i < 0) {
					break;
				}
				rslt += szHan.substring(i, i + 1);
			}

			if (!rslt.equals("")) {
				return rslt; // + "원";
			} else {
				return "";
			}
		} else {
			return "범위초과";
		}

	}

	/**
	 * 배열을 문자열로 변환
	 *
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String convetStr(List list) {

		if (list == null) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < list.size(); i++) {
			buffer.append("[" + list.get(i) + "]");
		}

		return buffer.toString();
	}


	/**
	 * PT_NAME 형식을 ptName 형식으로 바꿔줍니다.
	 *
	 * @param source
	 * @return
	 */
	public static String toCamelCase(String source) {
		boolean check = true;
		source = source.toLowerCase();
		while (check) {
			if (source.indexOf("_") != -1) {
				String target = source.substring(source.indexOf("_") + 1, source.indexOf("_") + 2);
				source = source.replace("_" + target, "_" + target.toUpperCase());
				source = source.replaceFirst("_", "");
				check = true;
			} else {
				check = false;
			}
		}
		return source;
	}

	/**
	 * 문자열 배열을 문자열로 변환
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list) {
		return list.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
	}

	/**
	 * LEFT 함수 기능
	 * @param str
	 * @return
	 */
	public static String brFilter(String str) {
		if (str == null || "".equals(str)) {
			return "";
		} else {
			return str.replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>").replaceAll("<br/><br/>", "<br/>");
		}

	}


	/**
	 * lpad 함수
	 *
	 * @param str 대상문자열, len 길이, addStr 대체문자
	 * @return 문자열
	 */
	public static String lpad(String str, int len, String addStr) {
		String result = str;
		int templen = len - result.length();

		for (int i = 0; i < templen; i++) {
			result = addStr + result;
		}

		return result;
	}
}