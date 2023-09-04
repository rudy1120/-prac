package egovframework.portal.sys.dynamic;

/**
 * @author J.Ryeon Lee
 * @since 2016.05.24
 */
public enum LinkType {

	/** 사용안함 */
	NONE("0"),
	/** 홈페이지 */
	HOMEPAGE("1"),
	/** 상세 페이지 */
	VIEW("2"),
	/** 지도 */
	MAP("3"),;

	private String code;

	private LinkType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
