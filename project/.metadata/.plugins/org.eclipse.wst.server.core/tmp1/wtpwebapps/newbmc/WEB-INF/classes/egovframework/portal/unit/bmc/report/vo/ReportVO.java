package egovframework.portal.unit.bmc.report.vo;

import egovframework.portal.common.vo.CommonVO;

public class ReportVO extends CommonVO {

	private String idx;
	private String uname;
	private String dupinfo;
	private String tel;
	private String email;
	private String title;
	private String cont;
	private String attachId;
	private String creatDate;
	
	private String orignl_file_nm;
	
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDupinfo() {
		return dupinfo;
	}
	public void setDupinfo(String dupinfo) {
		this.dupinfo = dupinfo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	
	
	public String getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	
	public String getOrignl_file_nm() {
		return orignl_file_nm;
	}
	public void setOrignl_file_nm(String orignl_file_nm) {
		this.orignl_file_nm = orignl_file_nm;
	}
	
	
	@Override
	public String toString() {
		return "ReportVO [idx=" + idx + ", uname=" + uname + ", tel=" + tel + ", email=" + email + ", title=" + title + ", cont="
				+ cont + ", attachId=" + attachId + ", creatDate=" + creatDate + ", orignl_file_nm=" + orignl_file_nm + "]";
	}
	
}
