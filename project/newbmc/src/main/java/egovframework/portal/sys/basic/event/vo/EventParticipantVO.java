package egovframework.portal.sys.basic.event.vo;

import egovframework.portal.common.vo.CommonVO;

public class EventParticipantVO extends CommonVO {

	/* 고객참여이벤트 CONTENT */
	
	/* 이벤트 참가자 기본키 */
	private int num;
	
	/* 이벤트 참조키 */
	private int idx;

	/* 이벤트 참가자 이름 */
	private String usernm;
	
	/* 이벤트 참가자 전화번호 */
	private String tel;
	
	/* 이벤트 참가자 이메일 */
	private String email;
	
	/* 이벤트 참가자 담청여부 */
	private int iswin;
	
	/* 이벤트 참가 등록일 */
	private String regdate;
	
	/* 이벤트 추첨인원 */
	private int lim;
	
	private String birth;
	
	private String bAddr1 ;
	private String bAddr2 ;
	private String content;
	
	
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getbAddr1() {
		return bAddr1;
	}

	public void setbAddr1(String bAddr1) {
		this.bAddr1 = bAddr1;
	}

	public String getbAddr2() {
		return bAddr2;
	}

	public void setbAddr2(String bAddr2) {
		this.bAddr2 = bAddr2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLim() {
		return lim;
	}

	public void setLim(int lim) {
		this.lim = lim;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public int getIswin() {
		return iswin;
	}

	public void setIswin(int iswin) {
		this.iswin = iswin;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "EventParticipantVO [num=" + num + ", idx=" + idx + ", usernm=" + usernm + ", tel=" + tel + ", email="
				+ email + ", iswin=" + iswin + ", regdate=" + regdate + ", lim=" + lim + "]";
	}
}
