package egovframework.portal.sys.dynamic;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 4. 4.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 4. 4.
 */
public enum CategoryType {

	CATEGORY1(""),
	CATEGORY2("2"),
	;

	String code;

	private CategoryType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
