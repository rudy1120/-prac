package egovframework.portal.unit.bmc.receipt.vo;

import javax.xml.bind.annotation.XmlTransient;

import egovframework.portal.common.vo.CommonVO;
import net.arnx.jsonic.JSONHint;

public class ReceiptContVO extends CommonVO {
	
	/*** 채용응시원서 접수 프로그램 ***/
	
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
	private String stime;
	
	/* 종료일 */
	private String edate;
	private String etime;
	
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
	
	/* 파일 개수 */
	private int file_cnt;
	
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

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	
	@XmlTransient
	@JSONHint(ignore = true)
	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
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

	public int getFile_cnt() {
		return file_cnt;
	}

	public void setFile_cnt(int file_cnt) {
		this.file_cnt = file_cnt;
	}

	@Override
	public String toString() {
		return "PartContVO [idx=" + idx + ", title=" + title + ", cont=" + cont + ", thumbnail=" + thumbnail
				+ ", sdate=" + sdate + ", edate=" + edate + ", cnt=" + cnt + ", gbn=" + gbn + ", isDel=" + isDel
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", begin=" + begin + ", end=" + end
				+ ", searchGbn=" + searchGbn + ", totcnt=" + totcnt + "]";
	}
}
