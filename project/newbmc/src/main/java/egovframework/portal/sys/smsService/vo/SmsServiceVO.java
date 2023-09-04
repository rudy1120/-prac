package egovframework.portal.sys.smsService.vo;

import java.io.Serializable;

import egovframework.portal.common.vo.CommonVO;

public class SmsServiceVO  extends CommonVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/*idx*/
	private int idx;
	
	/*그룹코드*/
	private String grpcode;
	
	/*코드명*/
	private String codename;
	
	/*지역코드 id*/
	private String areacode;
	
	/*용도코드 id*/
	private String usescode;
	
	/*지역+용도*/
	private String areauses;
	
	/*핸드폰 번호*/
	private String phone;
	
	/*신청자 이름*/
	private String name;
	
	/*사업지구명*/
	private String areaname;
	
	/*공급용도명*/
	private String usesname;

	/* 관리자 GBN */
	private String searchGbn = "";
	
	/*등록일자*/
	private String regdate;
	
	/* 주택유형 */
	private String housingtype;
	
	/*제목*/
	private String subject;

	/*내용*/
	private String msg;
	
	/*발송시간*/
	private String sentdate;
	
	/*월년*/
	private String year;
	private String month;
	
	/*서비스 이용기간*/
	private String termcode;
	
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

	public String getSentdate() {
		return sentdate;
	}

	public void setSentdate(String sentdate) {
		this.sentdate = sentdate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getGrpcode() {
		return grpcode;
	}

	public void setGrpcode(String grpcode) {
		this.grpcode = grpcode;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getUsescode() {
		return usescode;
	}

	public void setUsescode(String usescode) {
		this.usescode = usescode;
	}

	public String getAreauses() {
		return areauses;
	}

	public void setAreauses(String areauses) {
		this.areauses = areauses;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getUsesname() {
		return usesname;
	}

	public void setUsesname(String usesname) {
		this.usesname = usesname;
	}

	public String getSearchGbn() {
		return searchGbn;
	}

	public void setSearchGbn(String searchGbn) {
		this.searchGbn = searchGbn;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getHousingtype() {
		return housingtype;
	}

	public void setHousingtype(String housingtype) {
		this.housingtype = housingtype;
	}

	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}
	
	
	

}
