package egovframework.portal.unit.bmc.opendoc.vo;

import egovframework.portal.common.vo.CommonVO;

public class OpendocVO extends CommonVO {
	
	/** 부서명 **/
	private String deptNm;
	
	/** 문서번호 **/
	private String docNo;
	
	/** 문서제목 **/
	private String docTitle;
	
	/** 생산년도 **/
	private String prodYear;
	
	/** 일련번호 **/
	private String serialNo;
	
	/** 등록일자 **/
	private String regDate;
	
	/** 담당자 **/
	private String docMgr;
	
	/** 공개여부 **/
	private String openYn;
	
	/** 보존기간 **/
	private String docPeriod;
	
	/** 검색조건 제목 **/
	private String searchTitle;
	
	/** 검색조건 생산년도 **/
	private String searchYear;

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getProdYear() {
		return prodYear;
	}

	public void setProdYear(String prodYear) {
		this.prodYear = prodYear;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getDocMgr() {
		return docMgr;
	}

	public void setDocMgr(String docMgr) {
		this.docMgr = docMgr;
	}

	public String getOpenYn() {
		return openYn;
	}

	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	public String getDocPeriod() {
		return docPeriod;
	}

	public void setDocPeriod(String docPeriod) {
		this.docPeriod = docPeriod;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getSearchYear() {
		return searchYear;
	}

	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	
}
