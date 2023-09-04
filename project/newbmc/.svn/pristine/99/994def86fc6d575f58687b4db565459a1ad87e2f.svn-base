package egovframework.portal.unit.bmc.util;

import java.util.Calendar;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import egovframework.portal.security.user.vo.User;
import egovframework.portal.unit.bmc.user.vo.BmcUserVO;

import egovframework.portal.util.StringUtil;
import egovframework.portal.util.SecurityUtil;

public enum BmcUserUtil {

	INSTANCE,;

	/** @return 인증된 회원 정보 객체(미인증시 null) */
	public static BmcUserVO getInstance() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof User) {
			return (BmcUserVO) auth.getPrincipal();
		}

		return null;
	}

	public static String getDi() {
		BmcUserVO user = getInstance();
		return user != null ? user.getPrivatecode() : null;
	}

	public static String getName() {
		BmcUserVO user = getInstance();
		return user != null ? user.getUserName() : null;
	}

	public static String getId() {
		BmcUserVO user = getInstance();
		return user != null ? user.getUserId() : null;
	}

	public static String getBirthday() {
		BmcUserVO user = getInstance();
		return user != null ? user.getBirthday() : null;
	}

	public static String getEncodedPw(String rawPw) {
		return StringUtil.isNotEmpty(rawPw) ? SecurityUtil.encrypt(rawPw) : null;
	}

	/** 만나이 계산 @author 김장섭 */
	public static int calculateManAge(String birthYMD) {
		birthYMD = birthYMD.replaceAll("-", ""); //'-' 제거
		if (birthYMD.length() < 8) { //8자리 보다 작으면
			return 0;
		}

		int birthYear = Integer.parseInt(birthYMD.substring(0, 4));
		int birthMonth = Integer.parseInt(birthYMD.substring(4, 6));
		int birthDay = Integer.parseInt(birthYMD.substring(6, 8));

		Calendar current = Calendar.getInstance();
		int currentYear = current.get(Calendar.YEAR);
		int currentMonth = current.get(Calendar.MONTH) + 1;
		int currentDay = current.get(Calendar.DAY_OF_MONTH);

		int age = currentYear - birthYear;

		if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay) { // 생일 안 지난 경우 -1
			age--;
		}

		return age;
	}
}
