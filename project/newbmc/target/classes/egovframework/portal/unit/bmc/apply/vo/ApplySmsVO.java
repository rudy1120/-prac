package egovframework.portal.unit.bmc.apply.vo;

import egovframework.portal.common.vo.CommonVO;

public class ApplySmsVO extends CommonVO {

	/*idx*/
	private int idx;
	
	/*코드명*/
	private String codename;
	
	/*지역코드 id*/
	private String areacode;
	
	/*용도코드 id*/
	private String usescode;
	
	/*서비스이용기간 id*/
	private String termcode;
	
	/*공급용도체크값*/
	private String usesCk;
	
	/*서비스이용기간 체크값*/
	private String termCk;
	
	/*지역+용도*/
	//private String areauses;
	
	/*핸드폰 번호*/
	private String phone;
	
	private String phone2;
	
	/*신청자 이름*/
	private String name;
	
	private String name2;
	
	/*주택유형*/
	private String housingtype;
	
	/*저장타입*/
	private String savetype;

	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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
	
	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}

	public String getUsesCk() {
		return usesCk;
	}

	public void setUsesCk(String usesCk) {
		this.usesCk = usesCk;
	}

	public String getTermCk() {
		return termCk;
	}

	public void setTermCk(String termCk) {
		this.termCk = termCk;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getHousingtype() {
		return housingtype;
	}

	public void setHousingtype(String housingtype) {
		this.housingtype = housingtype;
	}

	public String getSavetype() {
		return savetype;
	}

	public void setSavetype(String savetype) {
		this.savetype = savetype;
	}

	
}
