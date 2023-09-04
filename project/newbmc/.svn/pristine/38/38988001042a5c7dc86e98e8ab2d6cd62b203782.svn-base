package egovframework.portal.sys.bbs;

/**
 * bbs_log#pt_state
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 14.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 14.
 */
public enum ProcessType {

	INSERTED("modIn", "등록"),
	UPDATED("modUp", "수정"),
	DELETED("modDe", "삭제"),
	ANSWERED("modReIn", "답글 등록"),
	RESTORED("modReDe", "복원"),
	MOVED("modMovIn", "게시글 이동"),
	;

	private String code;
	private String name;

	private ProcessType(String code, String name) {
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
