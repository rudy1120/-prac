package egovframework.portal.common;

import egovframework.portal.util.StringUtil;

public enum CategoryGubunType {

	/** 부서별 카테고리 */
	DEPARTMENT("B"),
	/** 직접 입력 */
	CUSTOM("W"),
	/** 전체 */
	ALL("A"),;

	private String code;

	private CategoryGubunType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static boolean useDeptCategory(String value) {
		return StringUtil.isNotBlank(value) && (value.equals(CategoryGubunType.ALL.getCode()) || value.equals(CategoryGubunType.DEPARTMENT.getCode()));
	}

	public static boolean useCustomCategory(String value) {
		return StringUtil.isNotBlank(value) && (value.equals(CategoryGubunType.ALL.getCode()) || value.equals(CategoryGubunType.CUSTOM.getCode()));
	}
}
