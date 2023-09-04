package egovframework.portal.unit.bmc.receipt.vo;

import egovframework.portal.common.vo.CommonVO;

public class ReceiptVO extends CommonVO {

	/* 채용응시원서 접수자정보 교사*/
	private String sttidx;			// 접수자 기본키
	private int idx;				// 게시글 참조키
	private String school;			// 소속학교
	private String tname;			// 담당교사 이름
	private String tel;				// 담당교사 전화번호
	private String dupinfo;			// 접수자 인증키값
	private String resolution;		// 추천심사위원회 의결서
	private String recStatus;		// 추천현황표
	private String attachId;		// 첨부파일(입사지원서)
	private String createDate;		// 등록 날짜
	private String updateDate;		// 수정 날짜
	
	/* 채용응시원서 접수자정보 추천학생 */
	private String stsidx;			// 추천학생 기본키
	private String sname;			// 추천학생 이름	
	private String sname1;
	private String sname2;
	private String attach1;			// 기능인재추천서
	private String attach2;			// 개인정보수집 및 위탁동의서
	private String attach3;			// 성적증명서
	private String attach4;			// 졸업(예정)증명서
	
	private String now_y;
	private int cntN;
	private String attach5;	
	private String attach6;	
	private String attach7;	
	private String attach8;	
	private String attach9;	
	private String attach10;	
	private String attach11;	
	private String attach12;	
	
	private String tCnt;
	private String gbn;
	private String title;
	private String edate;
	private String stime;
	private String etime;
	//private String cntS;
	
	public String getSttidx() {
		return sttidx;
	}

	public void setSttidx(String sttidx) {
		this.sttidx = sttidx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDupinfo() {
		return dupinfo;
	}

	public void setDupinfo(String dupinfo) {
		this.dupinfo = dupinfo;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStsidx() {
		return stsidx;
	}

	public void setStsidx(String stsidx) {
		this.stsidx = stsidx;
	}

	public String getAttach1() {
		return attach1;
	}

	public void setAttach1(String attach1) {
		this.attach1 = attach1;
	}

	public String getAttach2() {
		return attach2;
	}

	public void setAttach2(String attach2) {
		this.attach2 = attach2;
	}

	public String getAttach3() {
		return attach3;
	}

	public void setAttach3(String attach3) {
		this.attach3 = attach3;
	}

	public String getAttach4() {
		return attach4;
	}

	public void setAttach4(String attach4) {
		this.attach4 = attach4;
	}
	

	public String getAttach5() {
		return attach5;
	}

	public void setAttach5(String attach5) {
		this.attach5 = attach5;
	}

	public String getAttach6() {
		return attach6;
	}

	public void setAttach6(String attach6) {
		this.attach6 = attach6;
	}

	public String getAttach7() {
		return attach7;
	}

	public void setAttach7(String attach7) {
		this.attach7 = attach7;
	}

	public String getAttach8() {
		return attach8;
	}

	public void setAttach8(String attach8) {
		this.attach8 = attach8;
	}

	public String getAttach9() {
		return attach9;
	}

	public void setAttach9(String attach9) {
		this.attach9 = attach9;
	}

	public String getAttach10() {
		return attach10;
	}

	public void setAttach10(String attach10) {
		this.attach10 = attach10;
	}

	public String getAttach11() {
		return attach11;
	}

	public void setAttach11(String attach11) {
		this.attach11 = attach11;
	}

	public String getAttach12() {
		return attach12;
	}

	public void setAttach12(String attach12) {
		this.attach12 = attach12;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSname1() {
		return sname1;
	}

	public void setSname1(String sname1) {
		this.sname1 = sname1;
	}

	public String getSname2() {
		return sname2;
	}

	public void setSname2(String sname2) {
		this.sname2 = sname2;
	}
	
	

	public String getNow_y() {
		return now_y;
	}

	public void setNow_y(String now_y) {
		this.now_y = now_y;
	}

	public int getCntN() {
		return cntN;
	}

	public void setCntN(int cntN) {
		this.cntN = cntN;
	}

	public String gettCnt() {
		return tCnt;
	}

	public void settCnt(String tCnt) {
		this.tCnt = tCnt;
	}

	public String getGbn() {
		return gbn;
	}

	public void setGbn(String gbn) {
		this.gbn = gbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	@Override
	public String toString() {
		return "ReceiptVO [sttidx=" + sttidx + ", idx=" + idx + ", school=" + school + ", tname=" + tname + ", tel="
				+ tel + ", resolution=" + resolution + ", recStatus=" + recStatus + ", attachId=" + attachId + 
				", updateDate=" + updateDate + ", createDate=" + createDate + 
				", stsidx=" + stsidx + ", sname=" + sname + ", attach1=" + attach1 + ", attach2=" + attach2 + 
				", attach3=" + attach3 + ", attach4=" + attach4 + ", tCnt=" + tCnt + ", gbn=" + gbn +
				", title=" + title + ", edate=" + edate + "]";
	}
}
