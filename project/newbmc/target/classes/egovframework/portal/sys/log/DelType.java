package egovframework.portal.sys.log;

public enum DelType {

	AUTO(0, "자동"),
	MANUAL(1, "수동"),;

	private int code;
	private String desc;

	private DelType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
