package egovframework.portal.sys.basic.poll;

/**
 * 질문 구분
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
public enum PollQuestionType {

	/** 객관식 중복 */
	MULTI("1"),
	/** 객관식 단답 */
	SINGLE("2"),
	/** 주관식 단답 */
	SHORT_SUBJECTIVE("3"),
	/** 주관식 장문 */
	SUBJECTIVE("4"),
	/** 순위 선정형 */
	RANKING("5"),
	/** 매트릭스 형 */
	MATRIX("6"),
	;

	private String code = "";

	private PollQuestionType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
