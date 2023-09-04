package egovframework.portal.common.vo;

import javax.xml.bind.annotation.XmlTransient;

import net.arnx.jsonic.JSONHint;

public class CommonVO {

	/** 사용자 아이디 */
	private String regId = "";
	/** 사용자 이름 */
	private String regNm = "";
	/** 사용자 psn */
	private String psn = "";
	/** 첨부파일 ID */
	private String attachId;
	/** 첨부파일 개수 */
	private int fileCnt;

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}

	@XmlTransient
	public String getAttachId() {
		return attachId;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public int getFileCnt() {
		return fileCnt;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	/** 공통 쿼리단 */
	private String searchQuery = "";
	private String order_query = "";
	private String searchTxt = "";
	private String searchType = "";
	private int page = 1;
	private int pageSize = 10;
	private int firstIndex = 1;
	private int lastIndex = 1;
	private int rnum = 0;
	private String formSearch = "";

	@JSONHint(ignore = true)
	@XmlTransient
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public String getOrder_query() {
		return order_query;
	}

	public void setOrder_query(String order_query) {
		this.order_query = order_query;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) throws Exception {
		if ("Y".equals(this.formSearch)) {
			this.searchTxt = new String(searchTxt.getBytes("ISO-8859-1"), "UTF-8");
		} else {
			this.searchTxt = searchTxt;
		}
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@JSONHint(ignore = true)
	@XmlTransient
	public String getFormSearch() {
		return formSearch;
	}

	public void setFormSearch(String formSearch) {
		this.formSearch = formSearch;
	}

}