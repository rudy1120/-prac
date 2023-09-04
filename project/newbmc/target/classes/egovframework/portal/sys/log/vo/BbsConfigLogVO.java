package egovframework.portal.sys.log.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

/**
 * 게시판 생성/수정/삭제 로그 관리 VO
 * 참조 테이블: PT_LOG_BBS_CONFIG
 *
 * @author J.Ryeon Lee
 * @since 2015.12.31
 */
public class BbsConfigLogVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** 게시판 IDX */
	private String ptIdx = "";
	/** 수정자명 */
	private String ptWriter = "";
	/** 수정자 ID */
	private String ptWriteId = "";
	/** 게시판명 */
	private String ptTitle = "";
	/** 처리내역 */
	private String ptProc = "";
	/** 처리 IP */
	private String ptHostip = "";
	/** 처리일 */
	private Date ptDate = null;
	/**  */
	private String ptDeptName = "";

	/* SEARCH KEYWORD */

	/**  */
	private String sYear = "";
	/**  */
	private String eYear = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getPtWriter() {
		return ptWriter;
	}

	public void setPtWriter(String ptWriter) {
		this.ptWriter = ptWriter;
	}

	public String getPtWriteId() {
		return ptWriteId;
	}

	public void setPtWriteId(String ptWriteId) {
		this.ptWriteId = ptWriteId;
	}

	public String getPtTitle() {
		return ptTitle;
	}

	public void setPtTitle(String ptTitle) {
		this.ptTitle = ptTitle;
	}

	public String getPtProc() {
		return ptProc;
	}

	public void setPtProc(String ptProc) {
		this.ptProc = ptProc;
	}

	public String getPtHostip() {
		return ptHostip;
	}

	public void setPtHostip(String ptHostip) {
		this.ptHostip = ptHostip;
	}

	public Date getPtDate() {
		return ptDate;
	}

	public void setPtDate(Date ptDate) {
		this.ptDate = ptDate;
	}

	public String getPtDeptName() {
		return ptDeptName;
	}

	public void setPtDeptName(String ptDeptName) {
		this.ptDeptName = ptDeptName;
	}

	public String getsYear() {
		return sYear;
	}

	public void setsYear(String sYear) {
		this.sYear = sYear;
	}

	public String geteYear() {
		return eYear;
	}

	public void seteYear(String eYear) {
		this.eYear = eYear;
	}

}
