package egovframework.edosi.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import egovframework.edosi.common.util.EdosiDateUtil;

public class FormatDateTag extends TagSupport {
	protected String value;
	protected String delimiter;
	private String var;
	private int scope;
	private int viewDay;

	public FormatDateTag() {
		init();
	}

	private void init() {
		this.value = null;
		this.delimiter = "";
		this.scope = 1;
		this.viewDay = 0;
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

	public void setViewDay(int viewDay) {
		this.viewDay = viewDay;
	}

	/**
	 * @param delimiter the delimiter to set
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public void release() {
		init();
	}

	public int doEndTag() throws JspException {
		if (this.value == null) {
			return 6;
		}
		String formatted = EdosiDateUtil.getDayOfDelimAsString(this.value, delimiter);
		if (viewDay != 1) formatted += "(" + EdosiDateUtil.getDayOfWeekAsString(this.value, "") + ")";

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
