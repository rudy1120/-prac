package egovframework.portal.security;

import egovframework.portal.security.user.vo.User;
import egovframework.portal.util.StringUtil;

/**
 * 회원 ROLE 타입
 *
 * @author J.Ryeon Lee
 * @since 2016.08.11
 */
public enum RoleType {

	/** 회원 */
	USER("ROLE_USER"),
	/** 미인증 회원 */
	USER_NOT_CERTIFICATED("ROLE_USER_NOT_CERTIFICATED"),
	/** 휴면 회원 */
	USER_DORMANT("ROLE_USER_DORMANT"),
	/** 본인인증 이용자 */
	USER_TMP("ROLE_USER_TMP"),
	/** 본인인증 이용자 */
	USER_TMP_IPIN("ROLE_USER_TMP_IPIN"),
	/** 관리자 (현재 사용 안 함) */
	ADMIN("ROLE_USER_TMP"),
	;

	private String code;

	private RoleType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static RoleType getType(User user) {
		if (StringUtil.isBlank(user.getPrivatecode())) {
			return USER_NOT_CERTIFICATED;
		} else if ("Y".equals(user.getIsDormant())) {
			return USER_DORMANT;
		}

		return USER;
	}
}
