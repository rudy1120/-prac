package egovframework.portal.main;

/**
 * 게시판 정보 관리
 *
 * @author J.Ryeon Lee
 * @since 2015.12.14
 */
public enum BoardCode {

	/** 개발용 게시판 */
	DEVELOPER_BOARD("2"),
	/** 시스템 공지사항 */
	SYSTEM_NOTICE("3"),
	/** 선거인명부 */
	ELECTOR_REPORT("4"),
	/** 공지사항 (공통 게시판) */
	PORTAL_NOTICE("10"),
	;

	private String ptIdx = null;

	BoardCode(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

}
