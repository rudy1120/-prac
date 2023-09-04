package egovframework.portal.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import egovframework.portal.main.SiteCode;

/**
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * ?				?					최초 생성 및 코딩
 * 2017. 01. 05.	J.Ryeon Lee			시큐어 코딩 처리, 싱글톤 처리
 * 2017. 05. 18.	권태성				isEmpty 메소드 추가
 * </pre>
 *
 * @author ?
 * @since ?
 */
public enum TUtil {

	INSTANCE,;

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * LEFT 함수 기능.
	 *
	 * @param str
	 * @param n
	 * @return
	 */
	public static String left(String str, int n) {
		if (str == null || "".equals(str)) {
			return "";
		} else if (n > str.length()) {
			return str;
		} else {
			return str.substring(0, n);
		}

	}

	/**
	 * RIGHT 함수 기능.
	 *
	 * @param str
	 * @param n
	 * @return
	 */
	public static String right(String str, int n) {
		if (str == null || "".equals(str)) {
			return "";
		} else if (n > str.length()) {
			return str;
		} else {
			int len = str.length();
			return str.substring(len - n);
		}
	}

	/**
	 * null값을base값으로 초기화한다.
	 *
	 * @param str
	 * @param base
	 * @return
	 */
	public static int nullToBaseNumber(String str, int base) {
		if (str == null || "".equals(str)) {
			return base;
		}

		return Integer.parseInt(str);
	}

	/**
	 * null값을 0으로 초기화한다.
	 *
	 * @param str
	 * @return int
	 */
	public static int null2Zero(String str) {
		if (str == null || "".equals(str)) {
			return 0;
		}

		return Integer.parseInt(str);
	}

	/**
	 * null값을 공백으로 초기화한다.
	 *
	 * @param str
	 * @return String
	 */
	public static String nullToBlank1(String str) {
		String ret = null;

		if (str == null || (str.trim()).length() == 0) { //param.length() == 0)
			ret = "";
		} else {
			ret = str;
		}
		return ret;
	}

	/**
	 * null값을 공백으로 초기화한다.
	 *
	 * @param str
	 * @return String
	 */
	public static String nullToBlank(String str) {
		return urlFilter4(str, "");
	}

	/**
	 * null값을 0으로 초기화한다.
	 *
	 * @param str
	 * @return String
	 */
	public static String nullToZero(String str) {
		String ret = null;

		if (str == null || str.equals(" ") || str.equals("null") || (str.trim()).equals("")) {
			ret = "0";
		} else {
			ret = str;
		}
		return ret;
	}

	/**
	 * null값을 base값으로 초기화한다.
	 *
	 * @param str
	 * @param base
	 * @return String
	 */
	public static String nullTobaseStr1(String str, String base) {
		String ret = null;
		if (str == null || (str.trim()).length() == 0) {
			ret = base;
		} else {
			ret = str;
		}
		return ret;
	}

	/**
	 * null값을 base값으로 초기화한다.
	 *
	 * @param str
	 * @param base
	 * @return String
	 */
	public static String nullTobaseStr(String str, String base) {
		return urlFilter4(str, base);
	}

	/**
	 * NULL값을 공백으로 대체한다.
	 *
	 * @param obj
	 * @return
	 */
	public static String nvl(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else {
			return "";
		}
	}

	/**
	 * null값을 replaceSTR값으로 초기화한다.
	 *
	 * @param c
	 * @param replaceSTR
	 * @return
	 */
	public static String urlFilter4(String c, String replaceSTR) {
		String ret = c;
		if (c == null || c.equals(" ") || c.equals("null") || (c.trim()).equals("")) {
			return replaceSTR;
		}

		if (c.length() < 4 && isInteger(c)) {
			return c;
		}

		Pattern BOOL_PATTERN = Pattern.compile("java|'|\"|script|$|&lt|&quot|union|<|>|\\./|\\.\\./", Pattern.CASE_INSENSITIVE);
		Matcher m = BOOL_PATTERN.matcher(c);

		StringBuffer sb = new StringBuffer();
		try {
			if (c == null || c.equals(" ") || c.equals("null") || (c.trim()).equals("")) {
				ret = replaceSTR;
			} else {
				while (m.find()) {
					m.appendReplacement(sb, "");
				}
				m.appendTail(sb);
				ret = sb.toString();
			}
		} catch (Exception e) {
			LOGGER.info("TUtil > URLFilter4");
		} finally {
			if (BOOL_PATTERN != null) {
				try {
					BOOL_PATTERN = null;
				} catch (Exception ex) {
					LOGGER.error("TUtil > finally_1", ex);
				}
			}
			if (m != null) {
				try {
					m = null;
				} catch (Exception ex) {
					LOGGER.error("TUtil > finally_2", ex);
				}
			}
			if (sb != null) {
				try {
					sb = null;
				} catch (Exception ex) {
					LOGGER.error("TUtil > finally_3", ex);
				}
			}
		}
		return ret;
	}

	/**
	 * 숫자를 받아서 10보다 작으면, 앞자리에 0을 붙이 문자열을 반환한다.
	 *
	 * @param num
	 * @return returnVal
	 */
	public static String zeroPlus(int num) {
		String returnVal = "";
		if (num < 10) {
			returnVal = String.valueOf("0" + num);
		} else {
			returnVal = String.valueOf(num);
		}
		return returnVal;
	}

	/**
	 * 숫자값인지 체크한다.
	 *
	 * @param s
	 * @return boolean
	 */
	private static final boolean isInteger(final String s) {
		if (s != null) {
			for (int x = 0; x < s.length(); x++) {
				final char c = s.charAt(x);
				if (x == 0 && (c == '-')) {
					continue; // negative
				}
				if ((c >= '0') && (c <= '9')) {
					continue; // 0 - 9
				}
				return false; // invalid
			}
			return true; // valid
		} else {
			return false;
		}
	}

	public static String[] getTokenArray(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		String rtnVal[] = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++) {
			rtnVal[i] = st.nextToken();
		}
		return rtnVal;
	}

	/* width 만큼 짤라서 줄내림 */
	public static String justifyLeft(int width, String st) {
		StringBuffer buf = new StringBuffer(st);
		int lastspace = -1;
		int linestart = 0;
		int i = 0;

		while (i < buf.length()) {
			if (buf.charAt(i) == ' ') {
				lastspace = i;
			}
			if (buf.charAt(i) == '\n') {
				lastspace = -1;
				linestart = i + 1;
			}

			if (i > linestart + width - 1) {
				if (lastspace != -1) {
					buf.setCharAt(lastspace, '\n');
					linestart = lastspace + 1;
					lastspace = -1;
				} else {
					buf.insert(i, '\n');
					linestart = i + 1;
				}
			}
			i++;
		}
		return buf.toString();
	}

	/**
	 * 문자열로 된 날짜 정보를 주어진 패턴 정보로 변환한다.
	 *
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static String getSimpleDateFormat(String strDate, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		java.util.Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (Exception ex) {
			LOGGER.error("TUTIL - getSimpleDateFormat", ex);
		}
		return formatter.format(date);
	}

	/**
	 * 날짜 데이터를 주어진 패턴 정보로 변환한다.
	 *
	 * @param dDate
	 * @param pattern
	 * @return
	 */
	public static String getSimpleDateFormat(Date dDate, String pattern) {
		if (dDate == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
		return formatter.format(dDate);
	}

	/**
	 * 문자열로 된 날자 정보를 기본 형식(yyyy-MM-dd)로 변환한다.
	 *
	 * @param strDate
	 * @return
	 */
	public static String getSimpleDateFormat(String strDate) {
		return getSimpleDateFormat(strDate, "yyyy-MM-dd");
	}

	/**
	 * 오늘 날짜 정보를 Date형식으로 얻어온다.
	 *
	 * @return
	 */
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();

		return cal.getTime();
	}

	public static int getTodayWeek(String date) {

		Calendar cal = null;
		if (date == null) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date _date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				cal = new GregorianCalendar();
				cal.setTime(_date);
			} catch (ParseException e) {
				LOGGER.error("TUtil > getTodayWeek > ParseException", e);
			}
		}

		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 오늘 날짜 정보를 주어진 패턴 형식으로 변환한다.
	 *
	 * @param pattern
	 * @return
	 */
	public static String getToday(String pattern) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 + 1분의 날짜를 가져온다.
	 */
	public static String getOneSencond(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE) + 1, cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 + n일의 날짜를 가져온다.
	 */
	public static String getPlusDay(String date, int range, String pattern) {
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}

		Calendar cal = null;
		if (date == null) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date _date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				cal = new GregorianCalendar();
				cal.setTime(_date);
			} catch (ParseException e) {
				LOGGER.error("TUtil > getPlusDay > ParseException", e);
			}
		}
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) + range, 9, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 - n일의 날짜를 가져온다.
	 */
	public static String getMinusDay(String date, int range, String pattern) {
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}

		Calendar cal = null;
		if (date == null) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date _date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				cal = new GregorianCalendar();
				cal.setTime(_date);
			} catch (ParseException e) {
				LOGGER.error("TUtil > getPlusDay > ParseException", e);
			}
		}
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) - range, 9, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 + 1일의 날짜를 가져온다.
	 */
	public static String getOnePlusDay(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) + 1, 9, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	public static String getOnePlusDay(Date date, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) + 1, 9, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 - 1일의 날짜를 가져온다.
	 */
	public static String getOneMinusDay(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) - 1, 9, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	public static String getOneMinusDay(Date date, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) - 1, 9, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 + 2일의 날짜를 가져온다.
	 */
	public static String getTwoPlusDay(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) + 2);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	public static String getTwoPlusDay(Date date, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) + 2);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜-+ 2일의 날짜를 가져온다.
	 */
	public static String getTwoMinusDay(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) - 2);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	public static String getTwoMinusDay(Date date, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) - 2);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 term월의 날짜를 가져온다.
	 */
	@SuppressWarnings("static-access")
	public static String getTermMonthDay(int term, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, term);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 주어진 날짜 term일의 날짜를 가져온다.
	 */
	public static String getTermDay(int term, Date date, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)), cal.get(Calendar.DAY_OF_MONTH) + term, 9, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(cal.getTime());
	}

	/**
	 * 오늘 날짜 정보를 주어진 패턴 형식으로 변환한다.
	 *
	 * @param dateStyle
	 * @param timeStyle
	 * @return
	 */
	public static String getToday(int dateStyle, int timeStyle) {
		Calendar cal = Calendar.getInstance();
		DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle);

		return df.format(cal.getTime());
	}

	/**
	 * 주어진 년, 월 정보를 기반으로 해당월의 마지막 날짜를 구한다.
	 *
	 * @param selYear
	 * @param selMonth
	 * @return
	 */
	public static int getLastDay(String selYear, String selMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(selYear));
		cal.set(Calendar.MONTH, (Integer.parseInt(selMonth) - 1));

		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 스트링문자열을 date형식으로 변환.
	 *
	 * @param dateInput
	 * @return
	 */
	public static Date changeStringToDate(String dateInput, String pattern) {
		if (dateInput == null || "".equals(dateInput)) {
			return null;
		}

		Date resultDate = null;
		String dateFormat = pattern;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			resultDate = df.parse(dateInput);
		} catch (ParseException e) {
			LOGGER.error("TUtil > changeStringToDate > ParseException", e);

		}

		return resultDate;
	}

	/**
	 * 두날짜 스트링 사이의 일수를 구한다.
	 *
	 * @param todayString
	 * @param compareString
	 * @return
	 */
	public static int getDistDay(String todayString, String compareString) {
		Date todayDate = null;
		Date compareDate = null;
		todayDate = changeStringToDate(todayString, "yyyy-MM-dd");
		compareDate = changeStringToDate(compareString, "yyyy-MM-dd");

		long duration = compareDate.getTime() - todayDate.getTime();
		return (int) (duration / (1000 * 60 * 60 * 24));
	}

	/**
	 * 두날짜 스트링 사이의 시간을 구한다.
	 *
	 * @param todayString
	 * @param compareString
	 * @return
	 */
	public static int getDistHour(String todayString, String compareString) {
		Date todayDate = null;
		Date compareDate = null;
		todayDate = changeStringToDate(todayString, "yyyy-MM-dd HH:mm");
		compareDate = changeStringToDate(compareString, "yyyy-MM-dd HH:mm");

		long duration = compareDate.getTime() - todayDate.getTime();
		return (int) (duration / (1000 * 60));
	}

	/**
	 * 정수형 연도와 달정보를 받아 달의 시작일을 getTime() long 형으로 리턴.
	 *
	 * @param year
	 * @param month
	 * @param flag
	 * @return
	 */
	public static long getMonthTimeStamp(int year, int month, String flag) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date date = null;
		try {
			String yearStr = String.valueOf(year);
			String monthStr = String.valueOf(month);
			if (month < 10) {
				monthStr = "0" + monthStr;
			}

			String strDate = "";

			if ("".equals(flag) || "start".equals(flag)) {
				strDate = yearStr + "-" + monthStr + "-01";
			} else if ("end".equals(flag)) {
				int lastDay = TUtil.getLastDay(yearStr, monthStr);
				String dayStr = String.valueOf(lastDay);
				if (lastDay < 10) {
					dayStr = "0" + dayStr;
				}

				strDate = yearStr + "-" + monthStr + "-" + dayStr;
			}

			date = formatter.parse(strDate);

		} catch (Exception ex) {
			LOGGER.error("TUTIL - getMonthTimeStamp", ex);
		}
		return date.getTime();
	}

	/**
	 * 정수형 연도와 달, 일자 정보를 받아 달의 시작일을 getTime() long 형으로 리턴.
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static long getTimeStamp(int year, int month, int day) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date date = null;
		try {
			String yearStr = String.valueOf(year);
			String monthStr = String.valueOf(month);
			if (month < 10) {
				monthStr = "0" + monthStr;
			}

			String dayStr = String.valueOf(day);
			if (day < 10) {
				dayStr = "0" + dayStr;
			}

			String strDate = yearStr + "-" + monthStr + "-" + dayStr;

			date = formatter.parse(strDate);

		} catch (Exception ex) {
			LOGGER.error("TUTIL - getTimeStamp", ex);
		}
		return date.getTime();
	}

	/**
	 * 주어진 값이 숫자와 알파벳으로 구성되어있는지 확인
	 *
	 * @param str
	 * @return
	 */
	public static boolean isAlphaNumeric(String str) {
		return Pattern.matches("[a-zA-Z0-9]+", str);
	}

	/**
	 * 파라미터의 보안처리
	 *
	 * @param value
	 * @return
	 */
	public static String securityParameter(String value) {
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		value = value.replace("../", "");
		value = value.replace("./", "");
		value = value.replace(";", "");

		return value;
	}

	/**
	 * 파라미터의 보안처리
	 *
	 * @param value
	 * @return
	 */
	public static String securityContents(String value) {
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		value = value.replaceAll("&", "&amp;");
		value = value.replaceAll("\"", "&quot;");
		value = value.replaceAll("\\\\", "&apos;");

		return value;
	}

	/**
	 * 넘어온 인자값이 숫자인지 아닌지 체크
	 *
	 * @param str
	 * @return
	 */
	public static boolean numberChk(String str) {
		char c = ' ';

		if ("".equals(str)) {
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c < 48 || c > 59) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 두 위도, 경도 사이의 거리 계산
	 *
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @param unit
	 * @return
	 */
	public static double gpsdist(double lat1, double lng1, double lat2, double lng2, String unit) {
		double theta = lng1 - lng2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		} else if (unit == "m") {
			dist = dist * 1.609344;
			dist = dist * 1000;
		}

		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	public static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	/**
	 * 현재 페이지의 URL 리턴
	 *
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String curUrl(HttpServletRequest request) throws UnsupportedEncodingException {
		Enumeration<?> param = request.getParameterNames();

		StringBuffer strParam = new StringBuffer();
		StringBuffer strURL = new StringBuffer();

		if (param.hasMoreElements()) {
			strParam.append("?");
		}

		while (param.hasMoreElements()) {
			String name = (String) param.nextElement();
			if (name != null) {
				String value = new String(request.getParameter(name).getBytes("ISO-8859-1"), "UTF-8");

				strParam.append(name + "=" + value);

				if (param.hasMoreElements()) {
					strParam.append("&");
				}
			}

		}
		if (request.getRequestURI() != null) {
			strURL.append(request.getRequestURI());
		}
		strURL.append(strParam);

		return strURL.toString();
	}

	/**
	 * 리다이렉트 URL의 파라미터 구분자 변경
	 *
	 * @param url
	 * @return
	 */
	public static String rplcEncRedirectUrl(String url) {
		if (url == null) {
			return null;
		}
		if (url.indexOf("?") > 0) {
			url = url.replace("?", "@");
		}
		if (url.indexOf("&") > 0) {
			url = url.replace("&", "^");
		}
		if (url.indexOf("=") > 0) {
			url = url.replace("=", ";");
		}

		return url;
	}

	/**
	 * 리다이렉트 URL의 파라미터 구분자 변경
	 *
	 * @param url
	 * @return
	 */
	public static String rplcDecRedirectUrl(String url) {
		if (url == null) {
			return null;
		}
		if (url.indexOf(";") > 0) {
			url = url.replace(";", "=");
		}
		if (url.indexOf("^") > 0) {
			url = url.replace("^", "&");
		}
		if (url.indexOf("@") > 0) {
			url = url.replace("@", "?");
		}

		return url;
	}

	/**
	 * 게시판의 리다이렉트 URL의 파라미터 문자열을 만든다
	 *
	 * @param url
	 * @return
	 */
	public static String makeBbsParamStr(String urlName, String url, String ptIdx, String mId) {
		String returnUrl = "";

		returnUrl = urlName + "=" + url + "&ptIdx=" + ptIdx + "&mId=" + mId;

		return returnUrl;
	}

	public static JSONArray listToJson(List<?> objectList, String[] set) {
		if (set.length == 0) {
			return null;
		}

		JSONArray jsonArray = new JSONArray();

		for (Object object : objectList) {
			JSONObject jsonObject = new JSONObject();

			try {
				for (int i = 0; i < set.length; i++) {
					jsonObject.put(set[i], PropertyUtils.getProperty(object, set[i]) == null ? "" : PropertyUtils.getProperty(object, set[i]));
				}
			} catch (Exception e) {
				LOGGER.error("리스트 json 변환 오류", e);
				return null;
			}
			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	/**
	 * 확장자 리스트 반환
	 *
	 * @param extStr
	 * @return
	 */
	public static List<String> getExtList(String extStr) {
		String[] extStand = { "hwp", "xls", "zip", "pdf", "jpg", "htm", "doc", "png", "bmp", "ppt", "txt", "gif", "jpeg" };
		String[] extUrl =
			{ "<img src='/common/images/board/hwp.gif' alt='한글파일' width='16' height='16'/>", "<img src='/common/images/board/xls.gif' alt='엑셀파일' width='16' height='16' />", "<img src='/common/images/board/zip.gif' alt='압축파일' width='16' height='16'/>", "<img src='/common/images/board/pdf.gif' alt='pdf파일' width='16' height='16' />", "<img src='/common/images/board/jpg.gif' alt='jpg파일' width='16' height='16'/>", "<img src='/common/images/board/htm.gif' alt='html파일' width='16' height='16'/>", "<img src='/common/images/board/doc.gif' alt='워드파일' width='16' height='16'/>", "<img src='/common/images/board/png.gif' alt='png파일' width='16' height='16'/>", "<img src='/common/images/board/bmp.gif' alt='bmp파일' width='16' height='16'/>", "<img src='/common/images/board/ppt.gif' alt='ppt파일' width='16' height='16'/>", "<img src='/common/images/board/txt.gif' alt='텍스트 파일' width='16' height='16'/>", "<img src='/common/images/board/gif.gif' alt='gif파일' width='16' height='16'/>", "<img src='/common/images/board/jpeg.gif' alt='jpeg파일' width='16' height='16'/>"
			};

		List<String> extList = new ArrayList<String>();

		if (extStr == null || "".equals(extStr)) {
			return null;
		}

		extStr = extStr.toLowerCase();

		for (int i = 0; i < extStand.length; i++) {
			if (extStr.indexOf(extStand[i]) > -1) {
				extList.add(extUrl[i]);
			}
		}

		return extList;
	}

	/**
	 * 확장자 리스트 반환
	 *
	 * @param extStr
	 * @param imgPath
	 * @return
	 */
	public static List<String> getExtList(String extStr, String imgPath) {
		String[] extStand = { "hwp", "xls", "zip", "pdf", "jpg", "htm", "doc", "png", "bmp", "txt" };
		String[] extUrl = { "<img src='" + imgPath + "hwp.gif' alt='한글파일' width='16' height='16'/>", "<img src='" + imgPath + "xls.gif' alt='엑셀파일' width='16' height='16'/>", "<img src='" + imgPath + "zip.gif' alt='압축파일' width='16' height='16'/>", "<img src='" + imgPath + "pdf.gif' alt='pdf파일' width='16' height='16'/>", "<img src='" + imgPath + "jpg.gif' alt='jpg파일' width='16' height='16'/>", "<img src='" + imgPath + "htm.gif' alt='html파일' width='16' height='16'/>", "<img src='" + imgPath + "doc.gif' alt='워드파일' width='16' height='16'/>", "<img src='" + imgPath + "png.gif' alt='png파일' width='16' height='16'/>", "<img src='" + imgPath + "bmp.gif' alt='bmp파일' width='16' height='16'/>", "<img src='" + imgPath + "txt.gif' alt='텍스트 파일' width='16' height='16'/>" };

		List<String> extList = new ArrayList<String>();

		if (extStr == null || "".equals(extStr)) {
			return null;
		}

		extStr = extStr.toLowerCase();

		for (int i = 0; i < extStand.length; i++) {
			if (extStr.indexOf(extStand[i]) > -1) {
				extList.add(extUrl[i]);
			}
		}

		return extList;
	}

	/**
	 * HTML태그에서 한개의 이미지src를 반환
	 *
	 * @param str
	 * @return
	 */
	public static String getImgSrcFromHTML(String str) {
//		String imgSrcPath = "";
		Pattern imgPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

		List<String> result = new ArrayList<String>();
		Matcher matcher = imgPattern.matcher(str);
		while (matcher.find()) {
			result.add(matcher.group(1));
		}

		if (result.size() == 0) {
			imgPattern = Pattern.compile("<IMG[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
			matcher = imgPattern.matcher(str);
			while (matcher.find()) {
				result.add(matcher.group(1));
			}
		}

		if (result.size() == 0) {
			imgPattern = Pattern.compile("<Img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
			matcher = imgPattern.matcher(str);
			while (matcher.find()) {
				result.add(matcher.group(1));
			}
		}

		if (result.size() == 0) {
			return "";
		} else {
			String imgPath = result.get(0);

			// jpg 파일 체크
			if (imgPath.indexOf(".JPG") != -1) {
				return imgPath.split(".JPG")[0] + ".JPG";
			}
			if (imgPath.indexOf(".jpg") != -1) {
				return imgPath.split(".jpg")[0] + ".jpg";
			}
			// gif 파일 체크
			if (imgPath.indexOf(".GIF") != -1) {
				return imgPath.split(".GIF")[0] + ".GIF";
			}
			if (imgPath.indexOf(".gif") != -1) {
				return imgPath.split(".gif")[0] + ".gif";
			}
			// png 파일 체크
			if (imgPath.indexOf(".PNG") != -1) {
				return imgPath.split(".PNG")[0] + ".PNG";
			}
			if (imgPath.indexOf(".png") != -1) {
				return imgPath.split(".png")[0] + ".png";
			}
			// bmp 파일 체크
			if (imgPath.indexOf(".BMP") != -1) {
				return imgPath.split(".BMP")[0] + ".BMP";
			}
			if (imgPath.indexOf(".bmp") != -1) {
				return imgPath.split(".bmp")[0] + ".bmp";
			}

			return result.get(0);
		}
	}

	/**
	 * 사이트코드 전체를 받아 동한글명을 리턴
	 *
	 * @param siteCodeFull
	 * @return
	 */
	public static String getDongNameBySiteCodeFull(String siteCodeFull) {
		if (StringUtil.isNotBlank(siteCodeFull) && siteCodeFull.startsWith("csc")) {
			return SiteCode.toType(siteCodeFull.split("/")[0]).getName();
		}

		return "";
	}

	/**
	 * xss 코드 처리
	 *
	 * @param str
	 * @return
	 */
	public static String cleanXSS(String str) {
		if (!"".equals(str)) {
			String str_low = "";
			// 스크립트 문자열 필터링 (선별함 - 필요한 경우 보안가이드에 첨부된 구문 추가)
			str_low = str.toLowerCase();
			if (str_low.contains("javascript") || str_low.contains("script") || str_low.contains("iframe") || str_low.contains("document") || str_low.contains("vbscript") || str_low.contains("applet") || str_low.contains("embed") || str_low.contains("object") || str_low.contains("frame") || str_low.contains("grameset") || str_low.contains("layer") || str_low.contains("bgsound") || str_low.contains("alert") || str_low.contains("onblur") || str_low.contains("onchange") || str_low.contains("onclick") || str_low.contains("ondblclick") || str_low.contains("enerror") || str_low.contains("onfocus") || str_low.contains("onload") || str_low.contains("onmouse") || str_low.contains("onscroll") || str_low.contains("onsubmit") || str_low.contains("onunload")) {
				str = str_low;
				str = str.replaceAll("javascript", "");
				str = str.replaceAll("script", "");
				str = str.replaceAll("iframe", "");
				str = str.replaceAll("document", "");
				str = str.replaceAll("vbscript", "");
				str = str.replaceAll("applet", "");
				str = str.replaceAll("embed", "");
				str = str.replaceAll("object", "");
				str = str.replaceAll("frame", "");
				str = str.replaceAll("grameset", "");
				str = str.replaceAll("layer", "");
				str = str.replaceAll("bgsound", "");
				str = str.replaceAll("alert", "");
				str = str.replaceAll("onblur", "");
				str = str.replaceAll("onchange", "");
				str = str.replaceAll("onclick", "");
				str = str.replaceAll("ondblclick", "");
				str = str.replaceAll("enerror", "");
				str = str.replaceAll("onfocus", "");
				str = str.replaceAll("onload", "");
				str = str.replaceAll("onmouse", "");
				str = str.replaceAll("onscroll", "");
				str = str.replaceAll("onsubmit", "");
				str = str.replaceAll("onunload", "");
			}

			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("%00", null);
			str = str.replaceAll("\"", "&#34;");
			str = str.replaceAll("\'", "&#39;");
			str = str.replaceAll("%", "&#37;");
			// str = str.replaceAll("../", "");
			str = str.replaceAll("..\\\\", "");
			// str = str.replaceAll("./", "");
			// str = str.replaceAll("%2F", "");

			/*
			 * if(str == null || "".equals(str))
			 * return "";
			 * String str_low = str.toLowerCase();
			 * String[] scriptStr = str_low.split("script");
			 * if(scriptStr != null && scriptStr.length > 0) {
			 * String tmp = "";
			 * for(int i=0; i < scriptStr.length; i++) {
			 * if(scriptStr[i].indexOf("script") > -1) {
			 * if(i%2==0)
			 * scriptStr[i] = scriptStr[i].substring(0,scriptStr[i].indexOf("script") - 1);
			 * else
			 * scriptStr[i] = scriptStr[i].substring(scriptStr[i].indexOf("script")+6,scriptStr[i].length());
			 * }
			 * tmp += scriptStr[i];
			 * }
			 * str = tmp;
			 * }
			 */
			return str;
		} else {
			return "";
		}

	}

	/**
	 * xss 코드 처리 - 변환내용 글 수정시
	 *
	 * @param str
	 * @return
	 */
	public static String covertXSS(String str) {
		if (StringUtil.isNotBlank(str)) {

			str = str.replaceAll("[&]amp;", "&");
			str = str.replaceAll("[&]lt;", "<");
			str = str.replaceAll("[&]gt;", ">");

			str = str.replaceAll("[&]#34;", "\"");
			str = str.replaceAll("[&]#35;", "#");
			str = str.replaceAll("[&]#38;", "&");
			str = str.replaceAll("[&]#39;", "\'");
			str = str.replaceAll("[&]#37;", "%");

			return str;
		} else {
			return "";
		}
	}

	public static boolean isXSS(String text) {

		if (text != null && !text.equals("")) {
			if (text.contains("javascript") || text.contains("script") || text.contains("iframe") || text.contains("document") || text.contains("vbscript") || text.contains("applet") || text.contains("embed") || text.contains("object") || text.contains("frame") || text.contains("grameset") || text.contains("layer") || text.contains("bgsound") || text.contains("alert") || text.contains("onblur") || text.contains("onchange") || text.contains("onclick") || text.contains("ondblclick") || text.contains("enerror") || text.contains("onfocus") || text.contains("onload") || text.contains("onmouse") || text.contains("onscroll") || text.contains("onsubmit") || text.contains("onunload")

				|| text.contains("&lt") || text.contains("&gt")

			) {
				return true;
			}

		}

		return false;
	}

	/**
	 * 입력받은 s가 double형으로 사용가능한 문자인지 아닌지 확인해줌 (2015/10/권태성 추가)
	 *
	 * @param s
	 * @return
	 */
	public static boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 이메일 형식 확인 (2015/10/권태성 추가)
	 *
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			err = true;
		}
		return err;
	}

	/**
	 * 파라미터 문자열을 생성
	 *
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String addParams(Map map) throws UnsupportedEncodingException {
		Map<String, String[]> params = new HashMap<String, String[]>();
		params.putAll(map);

		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (Entry<String, String[]> param : params.entrySet()) {
			if (i++ == 0) {
				sb.append("?");
			} else {
				sb.append("&");
			}

			sb.append(param.getKey() + "=" + URLEncoder.encode(param.getValue()[0], "UTF-8"));
		}

		return sb.toString();
	}

	/**
	 * 정의된 자릿수만큼 난수를 문자열로 리턴합니다.
	 *
	 * @param loopcount
	 * @return
	 */
	public static String makeRandomNum(int loopcount) {
		String str = "";
		int d = 0;

		for (int i = 1; i <= loopcount; i++) {
			SecureRandom r = new SecureRandom();
			d = r.nextInt(9);
			str = str + Integer.toString(d);
		}

		return str;
	}

	/**
	 * 객체의 empty 여부를 체크
	 * @param s 대상 객체
	 * @return
	 */
	public static boolean isEmpty(Object s) {
		if (s == null) {
			return true;
		}
		if ((s instanceof String) && (((String) s).trim().length() == 0)) {
			return true;
		}
		if (s instanceof Map) {
			return ((Map<?, ?>) s).isEmpty();
		}
		if (s instanceof List) {
			return ((List<?>) s).isEmpty();
		}
		if (s instanceof Object[]) {
			return (((Object[]) s).length == 0);
		}
		return false;
	}

	/**
	 * BindingResult의 오류 메시지 1개를 리턴합니다.
	 *
	 * @param result
	 * @return
	 */
	public static String getBindingErrorMessage(BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			if (errors != null && errors.size() > 0) {
				return errors.get(0).getDefaultMessage();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * BindingResult의 오류 필드 이름 1개를 리턴합니다.
	 *
	 * @param result
	 * @return
	 */
	public static String getBindingErrorField(BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			if (errors != null && errors.size() > 0) {
				return errors.get(0).getField();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

}