package egovframework.portal.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * 날짜 유틸 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 01. 26.	권태성				최초 생성 및 코딩
 * 2017. 01. 05.	J.Ryeon Lee			시큐어 코딩/싱글톤 처리
 * </pre>
 *
 * @author 권태성
 * @since 2017. 1. 5.
 */
public enum DateUtil {

	INSTANCE,;

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * arg에 day 만큼 날짜를 더 합니다. arg의 파라메터 포멧 형태는 yyyy-MM-dd
	 *
	 * @param arg
	 * @param day
	 * @return
	 */
	public static String addDate(String arg, Integer day) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt;
		try {
			dt = sdf.parse(arg);
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.DATE, day);
			dt = c.getTime();
			arg = sdf.format(dt);
			return arg;
		} catch (ParseException e) {
			LOGGER.error(">> failed to run addDate/ParseException", e);
			return "";
		}

	}

	/**
	 * arg에 mon 만큼 월을 더 합니다. arg의 파라메터 포멧 형태는 yyyy-MM-dd
	 *
	 * @param arg
	 * @param mon
	 * @return
	 */
	public static String addMonth(String arg, Integer mon) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt;
		try {
			dt = sdf.parse(arg);
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.MONTH, mon);
			dt = c.getTime();
			arg = sdf.format(dt);
			return arg;
		} catch (ParseException e) {
			LOGGER.error(">> failed to run addMonth/ParseException", e);
			return "";
		}

	}

	/**
	 * arg에 hour 만큼 시간을 더 합니다. arg의 파라메터 포멧 형태는 yyyy-MM-dd HH:mm:ss
	 *
	 * @param arg 대상
	 * @param hour 더하고자 하는 시간
	 * @return 결과
	 */
	public static String addHour(String arg, Integer hour) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt;
		try {
			dt = sdf.parse(arg);
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.HOUR, hour);
			dt = c.getTime();
			arg = sdf.format(dt);
			return arg;
		} catch (ParseException e) {
			LOGGER.error(">> failed to run addHour/ParseException", e);
			return "";
		}

	}

	/**
	 * 두 날짜 차이를 return 해줍니다.
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static Long diffOfDate(Date beginDate, Date endDate) throws Exception {

		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays;
	}

	/**
	 * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
	 *
	 * @param date
	 * @param dateType
	 * @return
	 * @throws Exception
	 *              
	 */
	public static String dateDay(String date, String dateType) throws Exception {
		String day = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
		Date nDate = dateFormat.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		switch (dayNum) {
			case 1:
				day = "일";
				break;
			case 2:
				day = "월";
				break;
			case 3:
				day = "화";
				break;
			case 4:
				day = "수";
				break;
			case 5:
				day = "목";
				break;
			case 6:
				day = "금";
				break;
			case 7:
				day = "토";
				break;
			default:
				break;
		}

		return day;
	}

	/**
	 * 특정 날짜에 대하여 요일 코드 리턴
	 *
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Integer dateDayCode(Date date) throws Exception {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		return dayNum;
	}

	/**
	 * 현재 년월일을 return합니다.
	 *
	 * @return
	 */
	public static String getToDay(String division) {
		String month = "";
		String day = "";
		if (getMonth() < 10) {
			month = "0" + getMonth();
		} else {
			month = String.valueOf(getMonth());
		}
		if (getDay() < 10) {
			day = "0" + getDay();
		} else {
			day = String.valueOf(getDay());
		}
		return getYear() + division + month + division + day;
	}

	/**
	 * 현재 년도를 return 합니다.
	 *
	 * @return
	 */
	public static int getYear() {

		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);

	}

	/**
	 * 현재 월을 return 합니다.
	 *
	 * @return
	 */
	public static int getMonth() {

		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;

	}

	/**
	 * 현재 날짜를 return 합니다.
	 *
	 * @return
	 */
	public static int getDay() {

		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * date 값을 받아서 yyyy년 M월 d일로 return 합니다.
	 *
	 * @param date
	 * @return
	 */
	public static String getKorYear(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		String result = cal.get(Calendar.YEAR) + "년 " + (cal.get(Calendar.MONTH) + 1) + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일";

		return result;

	}

	/**
	 * String 형의 날짜 값을 받아서 Formatting 해줍니다.
	 *
	 * @param date
	 * @return
	 */
	public static String setDateFormat(String date, String format) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		try {

			Date nDate = dateFormat.parse(date);
			return dateFormat.format(nDate);

		} catch (ParseException | NullPointerException e) {
			LOGGER.error(">> failed to run setDateFormat/ParseException | NullPointerException", e);
			return "error";
		}
	}

}