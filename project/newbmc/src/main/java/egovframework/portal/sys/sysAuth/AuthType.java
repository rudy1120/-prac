package egovframework.portal.sys.sysAuth;

/**
 * 관리자 권한 플래그
 *
 * @author J.Ryeon Lee
 * @since 2015.12.22
 */
public enum AuthType {

	/** 최고 관리자 */
	SUPER(10),
	/** SSO 경유 로그인. 부서 관리자. */
	SSO(9),
	/** 서브 관리자 */
	GENERAL(5),
	/** 외부 관리자 */
	EXTERNAL(6),
	/** invalid access */
	NONE(-1), ;

	private int code;

	private AuthType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AuthType toAuthType(int code) {
		for (AuthType auth : AuthType.values()) {
			if (auth.getCode() == code) {
				return auth;
			}
		}

		return NONE;
	}
}
