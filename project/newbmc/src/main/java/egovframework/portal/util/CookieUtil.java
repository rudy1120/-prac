package egovframework.portal.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 쿠키 관리 유틸
 * 기존 유틸의 기능이 부족하고 제대로 작동하지 않아 클래스 교체
 * (출처 : http://zero-gravity.tistory.com/299)
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 10. 18.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 10. 18.
 */
public class CookieUtil {

	private static final String encoding = "UTF-8";
	private static final String path = "/";
	private static final Boolean encode = Boolean.FALSE;

	/**
	 * @description 특정 key의 쿠키값을 List로 반환한다.
	 * @params key: 쿠키 이름
	 */
	public static List<String> getValueList(String key, HttpServletRequest request) throws UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies();
		String[] cookieValues = null;
		String value = "";
		List<String> list = null;

		// 특정 key의 쿠키값을 ","로 구분하여 String 배열에 담아준다.
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(key)) {
					value = cookies[i].getValue();
					cookieValues = (encode ? (URLDecoder.decode(value, encoding)).split(",") : value.split(","));
					break;
				}
			}
		}

		// String 배열에 담겼던 값들을 List로 다시 담는다.
		if (cookieValues != null) {
			list = new ArrayList<String>(Arrays.asList(cookieValues));

			if (list.size() > 3) { // 값이 3개를 초과하면, 최근 것 3개만 담는다.
				int start = list.size() - 3;
				List<String> copyList = new ArrayList<String>();
				for (int i = start; i < list.size(); i++) {
					copyList.add(list.get(i));
				}
				list = copyList;
			}
		}
		return list;
	}

	/**
	 * @description 쿠키를 생성 혹은 업데이트한다.
	 * @params key: 쿠키 이름, value: 쿠키 이름과 짝을 이루는 값, day: 쿠키의 수명
	 */
	public static void setCookie(String key, String value, int day, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List<String> list = getValueList(key, request);
		String sumValue = "";
		int equalsValueCnt = 0;

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				sumValue += list.get(i) + ",";
				if (list.get(i).equals(value)) {
					equalsValueCnt++;
				}
			}
			if (equalsValueCnt != 0) { // 같은 값을 넣으려고 할 때의 처리
				if (sumValue.substring(sumValue.length() - 1).equals(",")) {
					sumValue = sumValue.substring(0, sumValue.length() - 1);
				}
			} else {
				sumValue += value;
			}
		} else {
			sumValue = value;
		}

		if (!sumValue.equals("")) {
			Cookie cookie = new Cookie(key, (encode ? URLEncoder.encode(sumValue, encoding) : sumValue));
			cookie.setMaxAge(60 * 60 * 24 * day);
			cookie.setPath(path);
			response.addCookie(cookie);
		}
	}

	/**
	 * @description 쿠키값들 중 특정 값을 삭제한다.
	 * @params key: 쿠키 이름, value: 쿠키 이름과 짝을 이루는 값
	 */
	public static void deleteCookie(String key, String value, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List<String> list = getValueList(key, request);
		list.remove(value);

		String sumValue = "";
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				sumValue += list.get(i) + ",";
			}
			if (sumValue.substring(sumValue.length() - 1).equals(",")) {
				sumValue = (sumValue.substring(0, sumValue.length() - 1)).replaceAll(" ", "");
			}
		}
		Cookie cookie = null;
		int time = 60 * 60 * 24 * 20;

		if (sumValue.equals("")) {
			time = 0;
		}

		cookie = new Cookie(key, (encode ? URLEncoder.encode(sumValue, encoding) : sumValue));
		cookie.setMaxAge(time);
		cookie.setPath(path);
		response.addCookie(cookie);
	}

	/**
	 * @description 일반적인 쿠키 생성
	 * @params key: 쿠키 이름, value: 쿠키 이름과 짝을 이루는 값, day: 쿠키의 수명
	 */
	public static Cookie createNewCookie(String key, String value, int day, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(key, (encode ? URLEncoder.encode(value, encoding) : value));
		cookie.setPath(path);
		cookie.setMaxAge(60 * 60 * 24 * day);
		response.addCookie(cookie);
		return cookie;
	}

	/**
	 * @description 쿠키들을 맵으로 반환한다.
	 * @params
	 */
	public static HashMap<String, Cookie> getValueMap(HttpServletRequest request) {
		HashMap<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
		return cookieMap;
	}

	/**
	 * @description ","로 구분된 값이 아닌 단일 값으로 저장된 쿠키의 값을 반환한다.
	 * @params key: 쿠키 이름
	 */
	public static String getValue(String key, HttpServletRequest request) throws UnsupportedEncodingException {
		Cookie cookie = (Cookie) getValueMap(request).get(key);
		if (cookie == null) {
			return null;
		}
		return (encode ? URLDecoder.decode(cookie.getValue(), encoding) : cookie.getValue());
	}

	/**
	 * @description 쿠키가 있는지 확인.
	 * @params key: 쿠키 이름
	 */
	public static boolean isExist(String key, HttpServletRequest request) {
		return getValueMap(request).get(key) != null;
	}

}