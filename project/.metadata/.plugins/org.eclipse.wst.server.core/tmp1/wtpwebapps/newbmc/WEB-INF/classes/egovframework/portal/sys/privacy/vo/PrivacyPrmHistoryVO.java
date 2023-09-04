package egovframework.portal.sys.privacy.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.privacy.ProcType;

/**
 * 개인정보 취급 메뉴 정보 변경 이력 관리 VO
 * 관리 table: privacy_prm_history
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 20.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 20.
 */
public class PrivacyPrmHistoryVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** prm PK */
	private String prmIdx = "";
	/** 프로그램명 */
	private String prmName = "";
	/** 테이블병 */
	private String prmTableName = "";
	/** 추가 파라미터 */
	private String prmParamsMap = "";
	/** 담당 개발자 */
	private String prmDeveloper = "";
	/** 보유 기간 */
	private String prmPeriod = "";
	/** 변경 관리자 ID */
	private String adminId = "";
	/** 변경 관리자명 */
	private String adminName = "";
	/** 변경 부서 코드 */
	private String deptId = "";
	/** 변경 부서명 */
	private String deptName = "";
	/** HOST IP */
	private String hostIp = "";
	/** 처리 타입 {@link ProcType} */
	private String procType = "";
	/** 변경일 */
	private Date updateDate = null;
	/** 게시판 IDX */
	private String ptIdx = "";

	/* ##### SEARCH KEYWORD ##### */

	/** 처리 타입 */
	private String searchProcType = "";
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

	public String getPrmIdx() {
		return prmIdx;
	}

	public void setPrmIdx(String prmIdx) {
		this.prmIdx = prmIdx;
	}

	public String getPrmName() {
		return prmName;
	}

	public void setPrmName(String prmName) {
		this.prmName = prmName;
	}

	public String getPrmTableName() {
		return prmTableName;
	}

	public void setPrmTableName(String prmTableName) {
		this.prmTableName = prmTableName;
	}

	public String getPrmParamsMap() {
		return prmParamsMap;
	}

	public void setPrmParamsMap(String prmParamsMap) {
		this.prmParamsMap = prmParamsMap;
	}

	public String getPrmDeveloper() {
		return prmDeveloper;
	}

	public void setPrmDeveloper(String prmDeveloper) {
		this.prmDeveloper = prmDeveloper;
	}

	public String getPrmPeriod() {
		return prmPeriod;
	}

	public void setPrmPeriod(String prmPeriod) {
		this.prmPeriod = prmPeriod;
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

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSearchProcType() {
		return searchProcType;
	}

	public void setSearchProcType(String searchProcType) {
		this.searchProcType = searchProcType;
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
