package egovframework.portal.sys.departMng.emp.vo;

import egovframework.portal.common.vo.CommonVO;

/**
 * 직원관리 VO
 * @author boram
 */
public class EmpMngVO extends CommonVO {

	/** 사용자ID */
	private String usrId = "";
	/** 로그인ID */
	private String logonId = "";
	/** 이름 */
	private String usrNm = "";
	/** 사용자근무상태코드 */
	private String usrWorkStateCode = "";
	/** 사용자근무상태 */
	private String usrWorkStateCodeNm = "";
	/** 채용구분(0:비정규, 1:정규) */
	private String engmSe = "";
	/** 원소속조직ID */
	private String orgCodeNm = "";
	/** 현소속조직ID */
	private String realUseDepCode = "";
	/** 현소속조직명 */
	private String realUseDepNm = "";
	/** 현소속조직명 */
	private String depCodeNm = "";
	/** 원소속조직명 */
	private String orginBlgDepCode = "";
	/** 기관명 */
	private String orginBlgDepNm = "";
	/** 직급명 */
	private String clssNm = "";
	/** 직위명 */
	private String positNm = "";
	/** 이메일 */
	private String emailAddr = "";
	/** 전화번호 */
	private String telno = "";
	/** 담당업무 */
	private String adiInfo7 = "";
	/** 사용여부(0:미사용, 1:사용) */
	private String useYn = "";
	/** 직책*/
	private String rdutyName = "";
	/** 사용자순서 */
	private String usrRank = "";
	/** 팀명 */
	private String blgTeamNm = "";
	/** 수정일 */
	private String dtUpdate = "";
	/** 전체경로 */
	private String fullPath = "";
	
	/** 직원명 검색 키워드 */
	private String searchUsrNm = "";
	/** 부서명 검색 키워드 */
	private String searchRealUseDepCode = "";
	/**사원번호*/
	private String empNum;
	
	// 정렬순서
	private int newOrder;
	
	public int getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(int newOrder) {
		this.newOrder = newOrder;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getLogonId() {
		return logonId;
	}
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public String getUsrWorkStateCode() {
		return usrWorkStateCode;
	}
	public void setUsrWorkStateCode(String usrWorkStateCode) {
		this.usrWorkStateCode = usrWorkStateCode;
	}
	public String getUsrWorkStateCodeNm() {
		return usrWorkStateCodeNm;
	}
	public void setUsrWorkStateCodeNm(String usrWorkStateCodeNm) {
		this.usrWorkStateCodeNm = usrWorkStateCodeNm;
	}
	public String getEngmSe() {
		return engmSe;
	}
	public void setEngmSe(String engmSe) {
		this.engmSe = engmSe;
	}
	public String getOrgCodeNm() {
		return orgCodeNm;
	}
	public void setOrgCodeNm(String orgCodeNm) {
		this.orgCodeNm = orgCodeNm;
	}
	public String getRealUseDepCode() {
		return realUseDepCode;
	}
	public void setRealUseDepCode(String realUseDepCode) {
		this.realUseDepCode = realUseDepCode;
	}
	public String getRealUseDepNm() {
		return realUseDepNm;
	}
	public void setRealUseDepNm(String realUseDepNm) {
		this.realUseDepNm = realUseDepNm;
	}
	public String getOrginBlgDepCode() {
		return orginBlgDepCode;
	}
	public void setOrginBlgDepCode(String orginBlgDepCode) {
		this.orginBlgDepCode = orginBlgDepCode;
	}
	public String getOrginBlgDepNm() {
		return orginBlgDepNm;
	}
	public void setOrginBlgDepNm(String orginBlgDepNm) {
		this.orginBlgDepNm = orginBlgDepNm;
	}
	public String getClssNm() {
		return clssNm;
	}
	public void setClssNm(String clssNm) {
		this.clssNm = clssNm;
	}
	public String getPositNm() {
		return positNm;
	}
	public void setPositNm(String positNm) {
		this.positNm = positNm;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getAdiInfo7() {
		return adiInfo7;
	}
	public void setAdiInfo7(String adiInfo7) {
		this.adiInfo7 = adiInfo7;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRdutyName() {
		return rdutyName;
	}
	public void setRdutyName(String rdutyName) {
		this.rdutyName = rdutyName;
	}
	public String getUsrRank() {
		return usrRank;
	}
	public void setUsrRank(String usrRank) {
		this.usrRank = usrRank;
	}
	public String getBlgTeamNm() {
		return blgTeamNm;
	}
	public void setBlgTeamNm(String blgTeamNm) {
		this.blgTeamNm = blgTeamNm;
	}
	public String getDtUpdate() {
		return dtUpdate;
	}
	public void setDtUpdate(String dtUpdate) {
		this.dtUpdate = dtUpdate;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getSearchUsrNm() {
		return searchUsrNm;
	}
	public void setSearchUsrNm(String searchUsrNm) {
		this.searchUsrNm = searchUsrNm;
	}
	public String getSearchRealUseDepCode() {
		return searchRealUseDepCode;
	}
	public void setSearchRealUseDepCode(String searchRealUseDepCode) {
		this.searchRealUseDepCode = searchRealUseDepCode;
	}
	public String getDepCodeNm() {
		return depCodeNm;
	}
	public void setDepCodeNm(String depCodeNm) {
		this.depCodeNm = depCodeNm;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	@Override
	public String toString() {
		return "EmpMngVO [usrId=" + usrId + ", logonId=" + logonId + ", usrNm=" + usrNm + ", usrWorkStateCode="
				+ usrWorkStateCode + ", usrWorkStateCodeNm=" + usrWorkStateCodeNm + ", engmSe=" + engmSe
				+ ", orgCodeNm=" + orgCodeNm + ", realUseDepCode=" + realUseDepCode + ", realUseDepNm=" + realUseDepNm
				+ ", depCodeNm=" + depCodeNm + ", orginBlgDepCode=" + orginBlgDepCode + ", orginBlgDepNm="
				+ orginBlgDepNm + ", clssNm=" + clssNm + ", positNm=" + positNm + ", emailAddr=" + emailAddr
				+ ", telno=" + telno + ", adiInfo7=" + adiInfo7 + ", useYn=" + useYn + ", rdutyName=" + rdutyName
				+ ", usrRank=" + usrRank + ", blgTeamNm=" + blgTeamNm + ", dtUpdate=" + dtUpdate + ", fullPath="
				+ fullPath + ", searchUsrNm=" + searchUsrNm + ", searchRealUseDepCode=" + searchRealUseDepCode
				+ ", empNum=" + empNum + ", newOrder=" + newOrder + "]";
	}


}
