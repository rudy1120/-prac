package egovframework.portal.sys.bbs.vo;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;

/**
 * 게시글 등록/수정/삭제/조회 통계 관리 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 8. 28.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 8. 28.
 */
public class BbsOperationStatVO extends CommonVO {

	/** 사이트코드 */
	private String siteCode = "";
	/** 사이트명 */
	private String siteName = "";
	/** 게시판 타입명 */
	private String bbsTypeName = "";
	/** 게시판 PK */
	private String ptIdx = "";
	/** 게시판명 */
	private String configName = "";
	/** 등록 건수 */
	private String createCnt = "";
	/** 삭제 건수 */
	private String deleteCnt = "";
	/** 삭제 건수(관리자) */
	private String adminDeleteCnt = "";
	/** 삭제 건수(사용자) */
	private String userDeleteCnt = "";
	/** 삭제 건수(자동) */
	private String autoDeleteCnt = "";
	/** 수정 건수 */
	private String updateCnt = "";
	/** 조회수 합계 */
	private String hitCnt = "";

	/* ##### SEARCH KEYWORD ##### */

	/** 사이트 코드 */
	private String searchSiteCode = "";
	/** 게시판 타입 */
	private String searchBbsType = "";
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

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getBbsTypeName() {
		return bbsTypeName;
	}

	public void setBbsTypeName(String bbsTypeName) {
		this.bbsTypeName = bbsTypeName;
	}

	public String getConfigName() {
		return configName;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getCreateCnt() {
		return createCnt;
	}

	public void setCreateCnt(String createCnt) {
		this.createCnt = createCnt;
	}

	public String getDeleteCnt() {
		return deleteCnt;
	}

	public void setDeleteCnt(String deleteCnt) {
		this.deleteCnt = deleteCnt;
	}

	public String getAdminDeleteCnt() {
		return adminDeleteCnt;
	}

	public void setAdminDeleteCnt(String adminDeleteCnt) {
		this.adminDeleteCnt = adminDeleteCnt;
	}

	public String getUserDeleteCnt() {
		return userDeleteCnt;
	}

	public void setUserDeleteCnt(String userDeleteCnt) {
		this.userDeleteCnt = userDeleteCnt;
	}

	public String getAutoDeleteCnt() {
		return autoDeleteCnt;
	}

	public void setAutoDeleteCnt(String autoDeleteCnt) {
		this.autoDeleteCnt = autoDeleteCnt;
	}

	public String getUpdateCnt() {
		return updateCnt;
	}

	public void setUpdateCnt(String updateCnt) {
		this.updateCnt = updateCnt;
	}

	public String getHitCnt() {
		return hitCnt;
	}

	public void setHitCnt(String hitCnt) {
		this.hitCnt = hitCnt;
	}

	public String getSearchSiteCode() {
		return searchSiteCode;
	}

	public void setSearchSiteCode(String searchSiteCode) {
		this.searchSiteCode = searchSiteCode;
	}

	public String getSearchBbsType() {
		return searchBbsType;
	}

	public void setSearchBbsType(String searchBbsType) {
		this.searchBbsType = searchBbsType;
	}

	public String getSearchPeriodType() {
		return StringUtil.isNotBlank(searchPeriodType) ? searchPeriodType : "year";
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
