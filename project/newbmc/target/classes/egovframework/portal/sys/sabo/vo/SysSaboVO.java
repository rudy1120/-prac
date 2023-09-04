package egovframework.portal.sys.sabo.vo;

import java.io.Serializable;

import egovframework.portal.common.vo.CommonVO;

public class SysSaboVO extends CommonVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**idx**/
	private int idx;
	
	/**구독자성명**/
	private String name;
	
	/**구독 주소**/
	private String address;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
}
