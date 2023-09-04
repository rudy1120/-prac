package egovframework.portal.sys.basic.poll;

/**
 * 설문조사 엑셀 데이터 포맷
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 10. 31.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 10. 31.
 */
public enum ExcelFormat {

	/** 헤더 */
	HEADER("headerFmt"),
	/** 제목 */
	TITLE("titleFmt"),
	/** 질문 가운데 정렬 */
	QUESTION_CENTRE("questionCentreFmt"),
	/** 질문 왼쪽 정렬 */
	QUESTION_LEFT("questionLeftFmt"),
	/** 데이터 가운데 정렬 */
	DATA_CENTRE("dataCentreFmt"),
	/** 데이터 왼쪽 정렬 */
	DATA_LEFT("dataLeftFmt");

	String code = "";

	private ExcelFormat(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
