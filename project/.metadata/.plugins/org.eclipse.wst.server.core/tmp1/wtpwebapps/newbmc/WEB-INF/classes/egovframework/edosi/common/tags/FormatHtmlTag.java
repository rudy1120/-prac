package egovframework.edosi.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import egovframework.com.utl.fcc.service.EgovStringUtil;

public class FormatHtmlTag extends TagSupport {
	protected String value;
	private String var;
	private int scope;

	public FormatHtmlTag() {
		init();
	}

	private void init() {
		this.value = null;
		this.scope = 1;
	}

	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public void release() {
		init();
	}

	public int doEndTag() throws JspException {
		if (this.value == null) {
			return 6;
		}
		String formatted = EgovStringUtil.checkHtmlView(this.value);
		if (this.var != null) {
			this.pageContext.setAttribute(this.var, formatted, this.scope);
		} else {
			try {
				this.pageContext.getOut().print(formatted);
			} catch (IOException ioe) {
				throw new JspTagException(ioe.toString(), ioe);
			}
		}
		return 6;
	}

}
