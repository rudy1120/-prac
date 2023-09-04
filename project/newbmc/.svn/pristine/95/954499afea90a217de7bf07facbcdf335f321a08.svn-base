package egovframework.portal.sys.bbs;

public enum BoardState {

	INSERTED(0, "등록"),
	MODIFIED_BY_OWNER(1, "작성자 수정"),
	MODIFIED_BY_OTHER(2, "관리자 수정"),
	DELETED_BY_OWNER(3, "작성자 삭제"),
	DELETED_BY_OTHER(4, "관리자 삭제"),
	RESTORED(5, "복원"),
	MOVED(6, "이동"),
	;

	private int code;
	private String desc;

	private BoardState(int code, String desc) {
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
