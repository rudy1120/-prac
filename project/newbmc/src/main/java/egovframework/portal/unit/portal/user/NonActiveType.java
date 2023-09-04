package egovframework.portal.unit.portal.user;

public enum NonActiveType {

	NO_LOGIN_HISTORY("0", "2년간 로그인 이력 없음"),
	REJECT_PRIVACY_POLICY("1", "개인정보 이용약관 갱신 거부"), // 실제로는 사용되고 있지 않음
	DELETE("2", "회원 탈퇴"),
	ADMIN_DELETE("3", "관리자 삭제"),
	;

	private String code;
	private String name;

	private NonActiveType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
