package egovframework.portal.unit.bmc.participation.vo;

import egovframework.portal.common.vo.CommonVO;

public class ParticipantVO extends CommonVO {
	
	/* 고객경영참여 프로그램 */
	
	/* 참가자 등록 기본키 */
	private int ptidx;
	
	/* 게시글 아이디 */
	private int idx;
	
	/* 참가자 이름 */
	private String usernm;
	
	/* 참가자 이메일 */
	private String email;
	
	/* 참가자 전화번호 */
	private String tel;
	
	/* 참가자 주소 */
	private String addr1;
	
	/* 참가자 상세주소 */
	private String addr2;
	
	/* 참가자 인증키 */
	private String dupinfo;
	
	/* 참가자 신청서 첨부파일 */
	private String attachId;
	
	/* 참가자 등록일 */
	private String createDate;
	
	/* 참가자 등록 수정일 */
	private String updateDate;
	
	/* 참가등록 삭제 유무 */
	private String isDel;
	
	/* 참가자가 참여한 공모전 제목 */
	private String title;
	
	/* 참가자가 참여한 공모전 구분 */
	private String gbn;
	
	/* 참가자 등록 삭제 가능 여부를 나타내기 위한 마감일 */
	private String edate;
	
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGbn() {
		return gbn;
	}

	public void setGbn(String gbn) {
		this.gbn = gbn;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public int getPtidx() {
		return ptidx;
	}

	public void setPtidx(int ptidx) {
		this.ptidx = ptidx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "ParticipantVO [ptidx=" + ptidx + ", idx=" + idx + ", usernm=" + usernm + ", email=" + email + ", tel="
				+ tel + ", addr1=" + addr1 + ", addr2=" + addr2 + ", isDel=" + isDel + ", title=" + title + ", gbn="
				+ gbn + ", edate=" + edate + ", dupinfo=" + dupinfo + ", attachId=" + attachId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
}
