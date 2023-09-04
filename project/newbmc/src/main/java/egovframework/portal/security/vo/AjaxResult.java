package egovframework.portal.security.vo;

public class AjaxResult {

	public Boolean success = Boolean.FALSE;
	public String msg = null;
	public String procCode = "";

	public AjaxResult() {
		// default
	}

	public AjaxResult(String msg) {
		this.msg = msg;
	}
}
