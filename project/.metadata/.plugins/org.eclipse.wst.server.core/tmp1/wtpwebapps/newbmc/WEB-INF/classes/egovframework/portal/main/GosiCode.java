package egovframework.portal.main;

public enum GosiCode {

	/** 고시 코드 */
	GOSI("01"),
	/** 입찰 코드 */
	BID("02"),
	/** 입법 코드 */
	LEGISLATION("03"), ;

	private String code;

	GosiCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
