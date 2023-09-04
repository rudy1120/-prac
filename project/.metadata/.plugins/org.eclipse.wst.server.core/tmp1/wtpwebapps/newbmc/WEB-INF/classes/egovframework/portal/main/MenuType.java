package egovframework.portal.main;

public enum MenuType {

	CONTENT("C"),
	BOARD("B"),
	PROGRAM("P"),
	FILE("F"),
	;

	private String code;

	private MenuType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
