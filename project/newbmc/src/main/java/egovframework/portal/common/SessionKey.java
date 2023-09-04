package egovframework.portal.common;

public enum SessionKey {

	/* USER */

	/** 회원가입용 SESSION KEY */
	USER_CONFIRM("userConfirmInfo"),
	/**  */
	USER_TYPE("userType"),
	/**  */
	FB_USER_ID("fbUserId"),
	/**  */
	FB_USER_NAME("fbUserName"),
	/**  */
	TW_USER_ID("twUserId"),
	/**  */
	TW_USER_NAME("twUserName"),
	/**  */
	TW_OAUTH_URL("twOauthUrl"),
	/**  */
	TW_REQ_TOKEN("twRequestToken"),
	/**  */
	TW_BACK_URL("twBackUrl"),
	/**  */
	TW_PROFILE_IMG_URL("twProfileImgUrl"),

	/* ADMIN */

	/**  */
	ADMIN_LOGIN_INFO("ADMIN_LOGIN_INFO"),   ;

	private String key;

	private SessionKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
