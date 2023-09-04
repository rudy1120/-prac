package egovframework.portal.sys.log.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

public class LoggingHistoryVO extends CommonVO {

	/** PK */
	private String idx = "";
	/**  */
	private String configIdx = "";
	/** 항목명 */
	private String logName = "";
	/** 삭제건수 */
	private int delCnt = -1;
	/** 처리 관리자 ID */
	private String adminId = "";
	/** 처리 관리자명 */
	private String adminName = "";
	/** 처리 부서명 */
	private String deptName = "";
	/** 접근 HOST IP */
	private String hostIp = "";
	/** 삭제일자 */
	private Date deleteDate = null;

	/* SEARCH */

	/** 검색 시작일 */
	private String searchSday = "";
	/** 검색 종료일 */
	private String searchEday = "";
	/**  */
	private String searchDelType = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getConfigIdx() {
		return configIdx;
	}

	public void setConfigIdx(String configIdx) {
		this.configIdx = configIdx;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public int getDelCnt() {
		return delCnt;
	}

	public void setDelCnt(int delCnt) {
		this.delCnt = delCnt;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getSearchSday() {
		return searchSday;
	}

	public void setSearchSday(String searchSday) {
		this.searchSday = searchSday;
	}

	public String getSearchEday() {
		return searchEday;
	}

	public void setSearchEday(String searchEday) {
		this.searchEday = searchEday;
	}

	public String getSearchDelType() {
		return searchDelType;
	}

	public void setSearchDelType(String searchDelType) {
		this.searchDelType = searchDelType;
	}

}
