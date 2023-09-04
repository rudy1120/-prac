package egovframework.portal.sys.bbs.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;

/**
 * 게시글 로그 목록 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 9. 7.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 9. 7.
 */
public class BbsLogMngVO extends CommonVO {

	/** 게시판 PK */
	private String ptIdx = "";
	/** 사이트명 */
	private String siteName = "";
	/** 게시글 PK */
	private String bIdx = "";
	/** 게시판 제목 */
	private String ptTitle = "";
	/** 게시판 타입명 */
	private String ptTypeNm = "";
	/** 게시글 제목 */
	private String bTitle = "";
	/** 게시글 작성자 */
	private String bWrite = "";
	/** 처리 */
	private String bProc = "";
	/** 처리자 접근 IP */
	private String operatorIp = "";
	/** 처리자 ID */
	private String operatorId = "";
	/** 처리 일자 */
	private Date operateDate = null;

	/* ##### SEARCH KEYWORD ##### */

	/** 사이트 코드 */
	private String searchSiteCode = "";
	/** 처리 */
	private String searchProc = "";
	/** 게시판 타입 */
	private String searchBbsType = "";
	/** 게시판명 */
	private String searchPtTitle = "";
	/** 검색 기간 타입(y: 연, m: 월, r: 기간 범위) */
	private String searchPeriodType = "";
	/** 검색 연 */
	private String searchYear = "";
	/** 검색 월 */
	private String searchMonth = "";
	/** 시작일 */
	private String searchSday = "";
	/** 종료일 */
	private String searchEday = "";

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getbIdx() {
		return bIdx;
	}

	public void setbIdx(String bIdx) {
		this.bIdx = bIdx;
	}

	public String getPtTitle() {
		return ptTitle;
	}

	public void setPtTitle(String ptTitle) {
		this.ptTitle = ptTitle;
	}

	public String getPtTypeNm() {
		return ptTypeNm;
	}

	public void setPtTypeNm(String ptTypeNm) {
		this.ptTypeNm = ptTypeNm;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbWrite() {
		return bWrite;
	}

	public void setbWrite(String bWrite) {
		this.bWrite = bWrite;
	}

	public String getbProc() {
		return bProc;
	}

	public void setbProc(String bProc) {
		this.bProc = bProc;
	}

	public String getOperatorIp() {
		return operatorIp;
	}

	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getSearchSiteCode() {
		return searchSiteCode;
	}

	public void setSearchSiteCode(String searchSiteCode) {
		this.searchSiteCode = searchSiteCode;
	}

	public String getSearchProc() {
		return searchProc;
	}

	public void setSearchProc(String searchProc) {
		this.searchProc = searchProc;
	}

	public String getSearchBbsType() {
		return searchBbsType;
	}

	public void setSearchBbsType(String searchBbsType) {
		this.searchBbsType = searchBbsType;
	}

	public String getSearchPtTitle() {
		return searchPtTitle;
	}

	public void setSearchPtTitle(String searchPtTitle) {
		this.searchPtTitle = searchPtTitle;
	}

	public String getSearchPeriodType() {
		return StringUtil.isNotBlank(searchPeriodType) ? searchPeriodType : "y";
	}

	public void setSearchPeriodType(String searchPeriodType) {
		this.searchPeriodType = searchPeriodType;
	}

	public String getSearchYear() {
		return StringUtil.isNotBlank(searchYear) ? searchYear : TUtil.getToday("yyyy");
	}

	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}

	public String getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
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
