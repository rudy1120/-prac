package egovframework.portal.common.vo;

public class CommonCodeVO {

	private String code = "";
	private String codeId = "";
	private String codeNm = "";
	private String isDel = "";

	public CommonCodeVO() {
		// default
	}

	public CommonCodeVO(String code) {
		setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}
