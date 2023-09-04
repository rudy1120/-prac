package egovframework.portal.sys.departMng.depart.vo;

import java.util.List;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.departMng.emp.vo.EmpMngVO;

/**
 * 부서관리 VO
 * @author boram
 */
public class DepartMngVO extends CommonVO {

	/** 부서코드 */
	private String depCode = "";
	/** 부서명 */
	private String depCodeNm = "";
	/** 상위부서코드 */
	private String uprDeptCode = "";
	/** 최상위부서코드 */
	private String sprmDeptCode = "";
	/** 부서정렬순서 */
	private String deptRank = "";
	/** 사용여부 */
	private String useYn = "";
	/** 수정일 */
	private String dtUpdate = "";
	/** 부서타입 */
	private String deptType = "";
	/** 전체경로 */
	private String fullPath = "";
	/** 부서전체명 */
	private String deptFullNm = "";
	/** 전화번호 */
	private String repTelNo = "";
	/** 팩스번호 */
	private String repFaxNo = "";
	/** 부서업무설명 */
	private String depComment = "";
	
	/** 등록 키 */
	private String idx = "";
	
	/** 부서 검색 키워드 */
	private String searchDepCode = "";
	/** 부서명 검색 키워드 */
	private String searchDepCodeNm = "";
	
	// 부서 관리 사용자 페이지 연동
	private String newSname;
	private String newLocation;
	private String newParent;
	private int newDepth;
	private int newOrder;
	private List<EmpMngVO> empList;
	
	
	public List<EmpMngVO> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmpMngVO> empList) {
		this.empList = empList;
	}

	public String getNewSname() {
		return newSname;
	}

	public void setNewSname(String newSname) {
		this.newSname = newSname;
	}

	public String getNewLocation() {
		return newLocation;
	}

	public void setNewLocation(String newLocation) {
		this.newLocation = newLocation;
	}

	public String getNewParent() {
		return newParent;
	}

	public void setNewParent(String newParent) {
		this.newParent = newParent;
	}

	public int getNewDepth() {
		return newDepth;
	}

	public void setNewDepth(int newDepth) {
		this.newDepth = newDepth;
	}

	public int getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(int newOrder) {
		this.newOrder = newOrder;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepCodeNm() {
		return depCodeNm;
	}

	public void setDepCodeNm(String depCodeNm) {
		this.depCodeNm = depCodeNm;
	}

	public String getUprDeptCode() {
		return uprDeptCode;
	}

	public void setUprDeptCode(String uprDeptCode) {
		this.uprDeptCode = uprDeptCode;
	}

	public String getSprmDeptCode() {
		return sprmDeptCode;
	}

	public void setSprmDeptCode(String sprmDeptCode) {
		this.sprmDeptCode = sprmDeptCode;
	}

	public String getDeptRank() {
		return deptRank;
	}

	public void setDeptRank(String deptRank) {
		this.deptRank = deptRank;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getDeptFullNm() {
		return deptFullNm;
	}

	public void setDeptFullNm(String deptFullNm) {
		this.deptFullNm = deptFullNm;
	}

	public String getRepTelNo() {
		return repTelNo;
	}

	public void setRepTelNo(String repTelNo) {
		this.repTelNo = repTelNo;
	}

	public String getRepFaxNo() {
		return repFaxNo;
	}

	public void setRepFaxNo(String repFaxNo) {
		this.repFaxNo = repFaxNo;
	}

	public String getDepComment() {
		return depComment;
	}

	public void setDepComment(String depComment) {
		this.depComment = depComment;
	}

	public String getSearchDepCode() {
		return searchDepCode;
	}

	public void setSearchDepCode(String searchDepCode) {
		this.searchDepCode = searchDepCode;
	}

	public String getSearchDepCodeNm() {
		return searchDepCodeNm;
	}

	public void setSearchDepCodeNm(String searchDepCodeNm) {
		this.searchDepCodeNm = searchDepCodeNm;
	}

	public String getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(String dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	@Override
	public String toString() {
		return "DepartMngVO [depCode=" + depCode + ", depCodeNm=" + depCodeNm + ", uprDeptCode=" + uprDeptCode
				+ ", sprmDeptCode=" + sprmDeptCode + ", deptRank=" + deptRank + ", useYn=" + useYn + ", dtUpdate="
				+ dtUpdate + ", deptType=" + deptType + ", fullPath=" + fullPath + ", deptFullNm=" + deptFullNm
				+ ", repTelNo=" + repTelNo + ", repFaxNo=" + repFaxNo + ", depComment=" + depComment + ", idx=" + idx
				+ ", searchDepCode=" + searchDepCode + ", searchDepCodeNm=" + searchDepCodeNm + ", newSname=" + newSname
				+ ", newLocation=" + newLocation + ", newParent=" + newParent + ", newDepth=" + newDepth + ", newOrder="
				+ newOrder + ", empList=" + empList + "]";
	}
}
