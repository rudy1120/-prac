package egovframework.edosi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

import egovframework.rte.fdl.string.EgovDateUtil;

public class EdosiDateUtil extends EgovDateUtil {
	/**
	 * 요일을 문자열로 돌려준다
	 * 
	 * @param date : 문자형 날짜
	 * @param delim : 구분자
	 * @return
	 */
	public static String getDayOfWeekAsString(String date, String delim) {
		String _year = "";
		String _month = "";
		String _day = "";
		if (date != null && date.length() >= 8) {
			if (date.length() == 8 && (delim == null || delim.length() == 0)) {
				_year = date.substring(0, 4);
				_month = date.substring(4, 6);
				_day = date.substring(6, 8);
			} else {
				StringTokenizer dates = new StringTokenizer(date, delim);
				if (dates.countTokens() == 3) {
					_year = dates.nextToken();
					_month = dates.nextToken();
					_day = dates.nextToken();
				}
			}
			if (_year.length() == 4 && _month.length() == 2 && _day.length() == 2) {
				return EgovDateUtil.getDayOfWeekAsString(_year, _month, _day);
			}
		}

		return "";
	}

	/**
	 * 요일을 숫자로 돌려준다
	 * 
	 * @param date : 문자형 날짜
	 * @param delim : 구분자
	 * @return
	 * @throws ParseException
	 */
	public static int getDayOfWeek(String date, String delim) {
		String _year = "";
		String _month = "";
		String _day = "";
		if (date != null && date.length() >= 8) {
			if (date.length() == 8 && (delim == null || delim.length() == 0)) {
				_year = date.substring(0, 4);
				_month = date.substring(4, 6);
				_day = date.substring(6, 8);
			} else {
				StringTokenizer dates = new StringTokenizer(date, delim);
				if (dates.countTokens() == 3) {
					_year = dates.nextToken();
					_month = dates.nextToken();
					_day = dates.nextToken();
				}
			}
			if (_year.length() == 4 && _month.length() == 2 && _day.length() == 2) {
				Calendar cal = Calendar.getInstance();
				cal.set(Integer.parseInt(_year), Integer.parseInt(_month), Integer.parseInt(_day));
				return cal.get(Calendar.DAY_OF_WEEK);
			}
		}

		return 0;
	}

	/**
	 * 8자라 날짜문자열을 구분자로 년월일 구분하여 돌려준다.
	 * 
	 * @param date : 문자형 날짜 (yyyyMMdd)
	 * @param delim : 구분자
	 * @return
	 */
	public static String getDayOfDelimAsString(String date, String delim) {
		String _year = "";
		String _month = "";
		String _day = "";
		if (date != null && date.length() == 8 && delim != null) {
			_year = date.substring(0, 4);
			_month = date.substring(4, 6);
			_day = date.substring(6, 8);
			return _year + delim + _month + delim + _day;
		} else {
			return date;
		}
	}

	/**
	 * 오늘날짜를 문자열로 돌려준다.
	 * 
	 * @param delim : 년-월-일 구분자
	 * @param amount : 오늘을 기준으로 일자를 더해준다.
	 * @return String
	 */
	public static String getToDayString(String delim, int amount) {
		Calendar _calendar = Calendar.getInstance();
		_calendar.add(Calendar.DATE, amount);
		String _year = Integer.toString(_calendar.get(Calendar.YEAR));
		String _month = (_calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + Integer.toString(_calendar.get(Calendar.MONTH) + 1) : Integer.toString(_calendar.get(Calendar.MONTH) + 1);
		String _day = _calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + Integer.toString(_calendar.get(Calendar.DAY_OF_MONTH)) : Integer.toString(_calendar.get(Calendar.DAY_OF_MONTH));
		return _year + delim + _month + delim + _day;
	}

	/**
	 * 오늘날짜를 문자열로 돌려준다.
	 * 
	 * @param delim : 년-월-일 구분자
	 * @param amount : 오늘을 기준으로 일자를 더해준다.
	 * @return String
	 */
	public static String getStrDayString(String strDate, String delim, int amount) {
		Calendar _calendar = Calendar.getInstance();
		_calendar = CalendarFromStringSimple(strDate);
		_calendar.add(Calendar.DATE, amount);
		String _year = Integer.toString(_calendar.get(Calendar.YEAR));
		String _month = (_calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + Integer.toString(_calendar.get(Calendar.MONTH) + 1) : Integer.toString(_calendar.get(Calendar.MONTH) + 1);
		String _day = _calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + Integer.toString(_calendar.get(Calendar.DAY_OF_MONTH)) : Integer.toString(_calendar.get(Calendar.DAY_OF_MONTH));
		return _year + delim + _month + delim + _day;
	}

	/**
	 * yyyyMMdd 형태의 문자열을 캘린더 객체로 변환합니다.
	 * 만약 변환에 실패할 경우 오늘 날짜를 반환합니다.
	 * 
	 * @param date 날짜를 나타내는 문자열
	 * @return 변환된 캘린더 객체
	 */
	public static Calendar CalendarFromStringSimple(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			cal.setTime(formatter.parse(date));
		} catch (ParseException e) {
			System.out.println("ParseException Occured"); // 보안점검 후속조치 
		}
		return cal;
	}

	/**
	 * 월을 문자배열로 돌려준다.
	 * 
	 * @return String[]
	 */
	public static String[] getMonthList() {
		String[] months = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		return months;
	}

	/**
	 * 금년부터 10년의 년목록을 문자배열로 돌려준다.
	 * 
	 * @return String[]
	 */
	public static String[] getYearList() {
		int year = EgovDateUtil.getCurrentYearAsInt();
		String[] years = new String[10];
		for (int i = 0; i < 10; i++) {
			years[i] = Integer.toString(year + i);
		}
		return years;
	}

	/**
	 * 년월일을 구분하여 문자배열로 돌려준다.
	 * 
	 * @param date
	 * @param delim
	 * @return String[0] : 년, String[1] : 월, String[2] : 일
	 */
	public static String[] getDates(String date, String delim) {
		String _year = "";
		String _month = "";
		String _day = "";
		String[] array = new String[3];

		if (date != null && date.length() >= 8) {
			if (date.length() == 8 && (delim == null || delim.length() == 0)) {
				array[0] = date.substring(0, 4);
				array[1] = date.substring(4, 6);
				array[2] = date.substring(6, 8);
			} else {
				StringTokenizer dates = new StringTokenizer(date, delim);
				if (dates.countTokens() == 3) {
					array[0] = dates.nextToken();
					array[1] = dates.nextToken();
					array[2] = dates.nextToken();
				}
			}
		}
		return array;
	}

	/**
	 * 년월일을 구분하여 문자배열로 돌려준다.
	 * 
	 * @param date
	 * @param delim
	 * @return String[0] : 년, String[1] : 월, String[2] : 일
	 */
	public static String[] getDailyList(String startDate, String endDate, String delim) {
		String[] startDates = EdosiDateUtil.getDates(startDate, delim);
		String[] endDates = EdosiDateUtil.getDates(endDate, delim);
		// System.out.println(startDate + ":" + endDate);
		String[] dailys = null;

		try {
			dailys = new String[EdosiDateUtil.getDayCount(startDate.replaceAll(delim, ""), endDate.replaceAll(delim, "")) + 1];
			// System.out.println("dailysCOunt : " + dailys.length);
			int dailyCursor = 0;

			int termMonth = Integer.parseInt(endDates[0] + endDates[1]) - Integer.parseInt(startDates[0] + startDates[1]);
			int monthLength = 0;

			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2]));
			for (int i = 0; i <= termMonth; i++) {
				if (dailyCursor == dailys.length) break;
				monthLength = EdosiDateUtil.getDayCountForMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
				// System.out.println("년 : " + cal.get(Calendar.YEAR) + " 월 : " + cal.get(Calendar.MONTH) + " 일수 : " + Integer.toString(monthLength));
				for (int j = 0; j < monthLength; j++) {
					if (dailyCursor == dailys.length) break;
					if (startDate.equals(endDate)) {
						if (j >= Integer.parseInt(startDates[2]) - 1
							&& j < Integer.parseInt(endDates[2]) - 1) {
							dailys[dailyCursor++] = Integer.toString(cal.get(Calendar.YEAR))
								+ (cal.get(Calendar.MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.MONTH)) : Integer.toString(cal.get(Calendar.MONTH)))
								+ (j + 1 < 10 ? "0" + Integer.toString(j + 1) : Integer.toString(j + 1));
						}
					} else if (cal.get(Calendar.YEAR) == Integer.parseInt(startDates[0])
						&& cal.get(Calendar.MONTH) == Integer.parseInt(startDates[1])) {
						if (j >= Integer.parseInt(startDates[2]) - 1) {
							dailys[dailyCursor++] = Integer.toString(cal.get(Calendar.YEAR))
								+ (cal.get(Calendar.MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.MONTH)) : Integer.toString(cal.get(Calendar.MONTH)))
								+ (j + 1 < 10 ? "0" + Integer.toString(j + 1) : Integer.toString(j + 1));
						}
					} else if (cal.get(Calendar.YEAR) == Integer.parseInt(endDates[0])
						&& cal.get(Calendar.MONTH) == Integer.parseInt(endDates[1])) {
						if (j <= Integer.parseInt(endDates[2]) - 1) {
							dailys[dailyCursor++] = Integer.toString(cal.get(Calendar.YEAR))
								+ (cal.get(Calendar.MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.MONTH)) : Integer.toString(cal.get(Calendar.MONTH)))
								+ (j + 1 < 10 ? "0" + Integer.toString(j + 1) : Integer.toString(j + 1));
						} else {
							continue;
						}
					} else {
						dailys[dailyCursor++] = Integer.toString(cal.get(Calendar.YEAR))
							+ (cal.get(Calendar.MONTH) < 10 ? "0" + Integer.toString(cal.get(Calendar.MONTH)) : Integer.toString(cal.get(Calendar.MONTH)))
							+ (j + 1 < 10 ? "0" + Integer.toString(j + 1) : Integer.toString(j + 1));
					}
				}
				cal.add(Calendar.MONTH, 1);
			}
		} catch (ParseException e) {
			System.out.println("ParseException Occured"); // 보안점검 후속조치 
		}
		return dailys;
	}
}
