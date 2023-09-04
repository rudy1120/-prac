package egovframework.portal.sys.log.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.bbs.vo.BbsLogMngVO;

/**
 * 게시글 변경 이력 기록용 VO
 * 관리 table: bbs_log
 *
 * @author ?
 * @since ?
 * @see BbsLogMngVO
 */
public class BbsLog extends CommonVO {

	private int ptIdx = 0;
	private String ptPtIdx = "";
	private String ptBidx = "";
	private String ptWriter = "";
	private String ptTitle = "";
	private String ptState = "";
	private String ptHostIp = "";
	private Date ptDate;
	private String searchYear = "";
	private String ptLocation = "";
	private String ptWriterId = "";

	public int getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(int ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getPtPtIdx() {
		return ptPtIdx;
	}

	public void setPtPtIdx(String ptPtIdx) {
		this.ptPtIdx = ptPtIdx;
	}

	public String getPtBidx() {
		return ptBidx;
	}

	public void setPtBidx(String ptBidx) {
		this.ptBidx = ptBidx;
	}

	public String getPtWriter() {
		return ptWriter;
	}

	public void setPtWriter(String ptWriter) {
		this.ptWriter = ptWriter;
	}

	public String getPtTitle() {
		return ptTitle;
	}

	public void setPtTitle(String ptTitle) {
		this.ptTitle = ptTitle;
	}

	public String getPtState() {
		return ptState;
	}

	public void setPtState(String ptState) {
		this.ptState = ptState;
	}

	public String getPtHostIp() {
		return ptHostIp;
	}

	public void setPtHostIp(String ptHostIp) {
		this.ptHostIp = ptHostIp;
	}

	public Date getPtDate() {
		return ptDate;
	}

	public void setPtDate(Date ptDate) {
		this.ptDate = ptDate;
	}

	public String getSearchYear() {
		return searchYear;
	}

	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}

	public String getPtLocation() {
		return ptLocation;
	}

	public void setPtLocation(String ptLocation) {
		this.ptLocation = ptLocation;
	}

	public String getPtWriterId() {
		return ptWriterId;
	}

	public void setPtWriterId(String ptWriterId) {
		this.ptWriterId = ptWriterId;
	}

}
