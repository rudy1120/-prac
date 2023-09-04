package egovframework.portal.sys.log.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

/**
 * 관리자 통합 로그 삭제 설정 변경 이력 VO
 * 관리 TABLE: logging_config_history
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 7.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 7.
 */
public class LoggingConfigHistoryVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** {@link LoggingConfigVO#getIdx()} */
	private String configIdx = "";
	/** {@link LoggingConfigVO#getName()} */
	private String logName = "";
	/** 변경전 {@link LoggingConfigVO#getLogPeriod()} */
	private String oldLogPeriod = "";
	/** 변경후 {@link LoggingConfigVO#getLogPeriod()} */
	private String newLogPeriod = "";
	/** 처리 관리자 ID */
	private String adminId = "";
	/** 처리 관리자명 */
	private String adminName = "";
	/** 처리 관리자 부서 ID */
	private String deptId = "";
	/** 처리 관리자 부서명 */
	private String deptName = "";
	/** 처리 접속 IP */
	private String hostIp = "";
	/** 수정일 */
	private Date updateDate = null;

	/* SEARCH */

	/** 검색 시작일 */
	private String searchSday = "";
	/** 검색 종료일 */
	private String searchEday = "";

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

	public String getOldLogPeriod() {
		return oldLogPeriod;
	}

	public void setOldLogPeriod(String oldLogPeriod) {
		this.oldLogPeriod = oldLogPeriod;
	}

	public String getNewLogPeriod() {
		return newLogPeriod;
	}

	public void setNewLogPeriod(String newLogPeriod) {
		this.newLogPeriod = newLogPeriod;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

}
