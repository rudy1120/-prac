/*
 ***************************************************************
 * 파 일 명 : CommonUtil.java
 * 설       명 : ParameterMap 관련 공용 유틸 클래스
 * 작   성  자 : 김혜민

 * 작   성  일 : 2014.01.16
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 ***************************************************************
 */
package egovframework.portal.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CommonUtil {
	/**
	 * HttpServletRequest 객체에서 parameter 값을 분리
	 *
	 * @param request request 객체
	 * @return parameterMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getParameterMap(HttpServletRequest request) {
		Enumeration ener = request.getParameterNames();
		String paramNM = null;
		String value = null;
		Map parameterMap = new HashMap();
		while (ener.hasMoreElements()) {
			paramNM = (String) ener.nextElement();
			value = ServletRequestUtils.getStringParameter(request, paramNM, "");
			value = value.replaceAll("<", "&lt;");
			value = value.replaceAll(">", "&gt;");
			value = value.replace("../", "");
			value = value.replace("./", "");
			value = value.replace(";", "");
			parameterMap.put(paramNM, value);
		}

		return parameterMap;
	}

	/**
	 * MultipartHttpServletRequest 객체에서 parameter 값을 분리
	 *
	 * @param mpRequest multipart request 객체
	 * @return parameterMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getParameterMap(MultipartHttpServletRequest mpRequest) {
		Map parameterMap = new HashMap();
		if (mpRequest == null) {
			return parameterMap;
		}

		Enumeration ener = mpRequest.getParameterNames();
		String paramNM = null;
		String value = null;

		while (ener.hasMoreElements()) {
			paramNM = (String) ener.nextElement();
			value = ServletRequestUtils.getStringParameter(mpRequest, paramNM, "");
			value = value.replaceAll("<", "&lt;");
			value = value.replaceAll(">", "&gt;");
			value = value.replace("../", "");
			value = value.replace("./", "");
			value = value.replace(";", "");
			parameterMap.put(paramNM, value);
		}

		return parameterMap;
	}

	/**
	 * String 문자열에서 parameter 값을 분리
	 *
	 * @param request 대상 문자열
	 * @return parameterMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getParameterMap(String request) {
		String paramNM = "";
		String value = "";
		Map parameterMap = new HashMap();
		if (!"".equals(request)) {
			String[] paramStr = request.split("&");
			for (int i = 0; i < paramStr.length; i++) {
				String[] splitStr = paramStr[i].split("=");
				paramNM = splitStr[0];
				value = splitStr[1];
				value = value.replaceAll("<", "&lt;");
				value = value.replaceAll(">", "&gt;");
				value = value.replace("../", "");
				value = value.replace("./", "");
				value = value.replace(";", "");
				parameterMap.put(paramNM, value);
			}
		}

		return parameterMap;
	}

	/**
	 * 금액 세자리마다 콤마 찍기
	 *
	 * @param data 대상 문자열
	 * @return 결과
	 */
	public static String chgMoney(String data) {

		return NumberFormat.getInstance().format(Long.parseLong(data));
	}

	/**
	 * 주어진 포맷에 맞게 오늘 날짜를 구한다.
	 *
	 * @param df 대상 문자열
	 * @return 결과
	 */
	public static String getToday(String df) {

		String str = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(df);
			Calendar tdate = Calendar.getInstance();
			str = sdf.format(tdate.getTime());
		} catch (Exception e) {
			str = "Error";
		}
		return str;
	}

	/**
	 * 현재 실행환경이 운영서버인지 로컬서버인지 확인
	 *
	 * @return 운영서버 true, 로컬서버 false
	 */
	public static boolean checkServerMode() {

		boolean liveMode = true;
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		for (String arg : arguments) {
			if ("-Denv=dev".equals(arg)) {
				liveMode = false;
				break;
			}
		}

		return liveMode;

	}
}
