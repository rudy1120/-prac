package egovframework.portal.sys.basic.poll;

/**
 * 설문 진행 상태 구분
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
public enum PollStateType {

	/** 진행중 */
	ONGOING("1"),
	/** 중지 */
	STOP("2"),
	/** 종료 */
	END("3"),
	;

	private String code = "";

	private PollStateType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}