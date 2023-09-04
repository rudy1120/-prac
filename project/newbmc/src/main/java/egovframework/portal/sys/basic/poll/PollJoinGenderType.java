package egovframework.portal.sys.basic.poll;

/**
 * 참여 대상 성별 구분
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 7. 20.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 20.
 */
public enum PollJoinGenderType {

	/** 전체 */
	ALL("A"),
	/** 남 */
	MAN("M"),
	/** 여 */
	WOMAN("W"),
	;

	private String code = "";

	private PollJoinGenderType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
