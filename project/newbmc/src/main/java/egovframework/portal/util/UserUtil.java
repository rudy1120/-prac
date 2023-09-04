package egovframework.portal.util;

import java.util.Calendar;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import egovframework.portal.security.user.vo.User;
import egovframework.portal.unit.portal.user.vo.UserVO;

/**
 * Security User Object Acces Util
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 11. 16.	J.Ryeon Lee			최초 생성 및 코딩
 * 2017. 02. 06.	J.Ryeon Lee			의정부 기존 회원 로직 적용
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 11. 16.
 */
public enum UserUtil {

	INSTANCE,;

	/** @return 인증된 회원 정보 객체(미인증시 null) */
	public static UserVO getInstance() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof User) {
			return (UserVO) auth.getPrincipal();
		}

		return null;
	}

	public static String getDi() {
		UserVO user = getInstance();
		return user != null ? user.getPrivatecode() : null;
	}

	public static String getName() {
		UserVO user = getInstance();
		return user != null ? user.getUserName() : null;
	}

	public static String getId() {
		UserVO user = getInstance();
		return user != null ? user.getUserId() : null;
	}

	public static String getBirthday() {
		UserVO user = getInstance();
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
