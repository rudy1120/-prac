package egovframework.portal.sys.webzine.vo;

import java.io.Serializable;

import egovframework.portal.common.vo.CommonVO;

public class SysWebzineVO extends CommonVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**idx**/
	private int idx;
	
	/**구독자성명**/
	private String name;
	
	/**구독 이메일**/
	private String email;
	
	/**등록일자**/
	private String regdate;  
	
	private String searchTxt;
	
	
	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
}
