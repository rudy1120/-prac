package egovframework.portal.bbs;

/**
 * 게시판 타입
 *
 * @author J.Ryeon Lee
 * @since 2016.05.26
 */
public enum BbsType {

	/** 일반 게시판 */
	BASIC("BRD001", "basic"),
	/** 포토 게시판 */
	PHOTO("BRD002", "photo"),
	/** 동영상 게시판 */
	MOVIE("BRD003", "movie"),
	/** 블로그 게시판 */
	BLOG("BRD004", "blog"),
	/** 민원형 게시판 */
	MINWON("BRD005", "minwon"),
	/** FAQ 게시판 */
	FAQ("BRD006", "faq"), 
	/** 다운로드 게시판 */
	DOWNLOAD("BRD009","download");
	
	private String code;
	private String prefix;

	private BbsType(String code, String prefix) {
		this.code = code;
		this.prefix = prefix;
	}

	public String getCode() {
		return code;
	}

	public String getPrefix() {
		return prefix;
	}

	public static BbsType toType(String code) {
		for (BbsType type : BbsType.values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}

		return BASIC;
	}

}
