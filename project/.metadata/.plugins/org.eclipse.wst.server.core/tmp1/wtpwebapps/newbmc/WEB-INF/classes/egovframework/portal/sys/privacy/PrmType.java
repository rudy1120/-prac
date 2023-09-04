package egovframework.portal.sys.privacy;

public enum PrmType {

	BOARD(0, "게시판"),
	UNIT(1, "프로그램"),
	;

	private int code;
	private String name;

	private PrmType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
