package egovframework.portal.sys.basic.event.vo;

import egovframework.portal.common.vo.CommonVO;

public class EventContentVO extends CommonVO {
		
	/* 고객참여이벤트 CONTENT */
	
	/* 이벤트 기본키 */
	private int idx;
	
	/* 이벤트 제목 */
	private String subject;
	
	/* 이벤트 머리말 */
	private String headcomment;
	
	/* 이벤트 내용 */
	private String content;
	
	/* 이벤트 진행 여부 */
	private int procLvl;
	
	/* 이벤트 이미지 */
	private String attachFile;
	
	/* 이벤트 등록일 */
	private String regdate;
	
	/* 이벤트 기간 */
	private String exedate;
	private String sdate;
	private String edate;
	
	/* 이벤트 발표일 */
	private String pubdate;
	private String pubtime;
	private String pubetc;
	
	/* 이벤트 추첨인원 */
	private int lim;
	
	/* 이벤트 신청인원 수 */
	private int parCnt;
	
	/* 당첨여부 */
	private int iswin;

	private String gubun;
	
	private String usernm;
	private String tel;
	
	private String is_use;
	
	
	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
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

	public int getIswin() {
		return iswin;
	}

	public void setIswin(int iswin) {
		this.iswin = iswin;
	}

	public int getParCnt() {
		return parCnt;
	}

	public void setParCnt(int parCnt) {
		this.parCnt = parCnt;
	}

	public String getPubtime() {
		return pubtime;
	}

	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}

	public String getPubetc() {
		return pubetc;
	}

	public void setPubetc(String pubetc) {
		this.pubetc = pubetc;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHeadcomment() {
		return headcomment;
	}

	public void setHeadcomment(String headcomment) {
		this.headcomment = headcomment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getProcLvl() {
		return procLvl;
	}

	public void setProcLvl(int procLvl) {
		this.procLvl = procLvl;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getExedate() {
		return exedate;
	}

	public void setExedate(String exedate) {
		this.exedate = exedate;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public int getLim() {
		return lim;
	}

	public void setLim(int lim) {
		this.lim = lim;
	}

	public String getIs_use() {
		return is_use;
	}

	public void setIs_use(String is_use) {
		this.is_use = is_use;
	}

	@Override
	public String toString() {
		return "EventContentVO [idx=" + idx + ", subject=" + subject + ", headcomment=" + headcomment + ", content="
				+ content + ", procLvl=" + procLvl + ", attachFile=" + attachFile + ", regdate=" + regdate
				+ ", exedate=" + exedate + ", sdate=" + sdate + ", edate=" + edate + ", pubdate=" + pubdate
				+ ", pubtime=" + pubtime + ", pubetc=" + pubetc + ", lim=" + lim + ", parCnt=" + parCnt + ", iswin="
				+ iswin + "]";
	}
}
