package egovframework.portal.unit.bmc.participation.vo;

import egovframework.portal.common.vo.CommonVO;

public class PartContVO extends CommonVO {
	
	/* 고객경영참여 프로그램 */
	
	/* 게시글 아이디 */
	private int idx;
	
	/* 게시글 제목 */
	private String title;
	
	/* 게시글 내용 */
	private String cont;
	
	/* 게시글 썸네일 */
	private String thumbnail;
	
	/* 시작일 */
	private String sdate;
	
	/* 종료일 */
	private String edate;
	
	/* 게시글 정보1 */
	private String etc1;
	
	/* 게시글 정보2 */
	private String etc2;
	
	/* 게시글 정보3 */
	private String etc3;
	
	/* 게시글 정보4 */
	private String etc4;
	
	/* 게시글 정보5 */
	private String etc5;
	
	/* 게시글 조회수 */
	private int cnt;
	
	/* 게시글 구분값 */
	private String gbn;
	
	/* 게시글 삭제 여부 */
	private String isDel;
	
	/* 게시글 생성 날짜 */
	private String createDate;
	
	/* 게시글 수정 날짜 */
	private String updateDate;
	
	/* 첫 리스트 화면 페이징 */
	private int begin;
	private int end;
	
	/* 관리자 GBN */
	private String searchGbn = "";
	
	/* 참가자 수 */
	private int totcnt;
	
	public int getTotcnt() {
		return totcnt;
	}

	public void setTotcnt(int totcnt) {
		this.totcnt = totcnt;
	}

	public String getSearchGbn() {
		return searchGbn;
	}

	public void setSearchGbn(String searchGbn) {
		this.searchGbn = searchGbn;
	}
	
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public String getEtc1() {
		return etc1;
	}

	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}

	public String getEtc2() {
		return etc2;
	}

	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}

	public String getEtc3() {
		return etc3;
	}

	public void setEtc3(String etc3) {
		this.etc3 = etc3;
	}

	public String getEtc4() {
		return etc4;
	}

	public void setEtc4(String etc4) {
		this.etc4 = etc4;
	}

	public String getEtc5() {
		return etc5;
	}

	public void setEtc5(String etc5) {
		this.etc5 = etc5;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
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
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "PartContVO [idx=" + idx + ", title=" + title + ", cont=" + cont + ", thumbnail=" + thumbnail
				+ ", sdate=" + sdate + ", edate=" + edate + ", etc1=" + etc1 + ", etc2=" + etc2 + ", etc3=" + etc3
				+ ", etc4=" + etc4 + ", etc5=" + etc5 + ", cnt=" + cnt + ", gbn=" + gbn + ", isDel=" + isDel
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", begin=" + begin + ", end=" + end
				+ ", searchGbn=" + searchGbn + ", totcnt=" + totcnt + "]";
	}
}
