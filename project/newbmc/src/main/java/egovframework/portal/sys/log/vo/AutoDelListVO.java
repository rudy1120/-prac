package egovframework.portal.sys.log.vo;

import egovframework.portal.common.vo.CommonVO;

public class AutoDelListVO extends CommonVO {

	private int idx;
	private String regdate;
	private String sale;
	private String housing;
	
	private String total;
	private String year;
	private String month;
	
	/* 관리자 GBN */
	private String searchGbn = "";
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getHousing() {
		return housing;
	}
	public void setHousing(String housing) {
		this.housing = housing;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}	
	public String getSearchGbn() {
		return searchGbn;
	}
	public void setSearchGbn(String searchGbn) {
		this.searchGbn = searchGbn;
	}
	
	@Override
	public String toString() {
		return "TermVO [idx=" + idx + ", sale=" + sale + ", housing=" + housing + ", regdate=" + regdate 
				+ ", total=" + total + ", year=" + year + ", month" + ", searchGbn=" + searchGbn + "]";
	}

}
