package egovframework.portal.sys.MenuMng.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yhdb.solution.secukeypad.pcweb.commonUtil.TUtil;

import egovframework.portal.common.CclType;
import egovframework.portal.common.NuriType;
import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.util.StringUtil;

/**
 * 메뉴 관리 VO
 * 관리 테이블: menus
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2014. 10. 7.		엄동건				최초 생성 및 코딩
 * 2017. 4. 18.		권태성				MyBatis cache 적용을 위해 serializable 설정
 * 2017. 6. 19.		권태성				다중 메뉴 담당자 기능 추가를 위한 수정
 * 2017. 6. 26.		권태성				constructor 추가
 * 2017. 6. 27.		권태성				시스템 이력에 영향을 주는 기능 수정에 따른 targetMid 추가
 * 2017. 9. 15.		J.Ryeon Lee			공공누리, CCL 타입코드 추가, 기존 미사용 코드 제거(CCL 및 라이브리)
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2014. 10. 7.
 */
public class MenuVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 476277913494064650L;

	/** 하위메뉴 우선노출 */
	private int isFirst = 0;
	/** IDX */
	private int idx;
	/** 메뉴 아이디 */
	private String mId;
	/** 메뉴명 */
	private String menuName;
	/** 링크 URL */
	private String linkUrl;
	/** 링크 타겟(_SELF, _BLANK) */
	private String target;
	/** 메뉴 타입(B(BOARD), P(PROGRAM), C(CONTENT), L(LINK), F(FILE)) */
	private String menuType;
	/** 사용 여부(Y,N) */
	private String isUse;
	/** 단위 프로그램 URL */
	private String programUrl;
	/** 파일 유형 컨텐츠 위치 */
	private String contentFilePath;
	/** CMS 사이트 코드 */
	private String cmsSiteCode;
	/** CMS 페이지 아이디 */
	private String cmsPageId;
	/** CMS 페이지 타입 코드 */
	private String cmsPageTypeCode;
	/** CMS 폴더 IDX */
	private String cmsFolderIdx;
	/** CMS 상위 폴더 IDX */
	private String cmsParentIdx;
	/** CMS 폴더 코드 */
	private String cmsFolderCode;
	/** 상위 메뉴 IDX */
	private int parentIdx;
	/** 사이트 코드 */
	private String siteCode;
	/** 메뉴 정렬 순서 */
	private int menuOrder;
	/** 메뉴 뎁스 레벨 */
	private int menuLevel;
	/**  */
	private String isIncContent;
	/** 공통 게시판 마스터 IDX */
	private int bbsMstIdx = 0;
	/** 공통 게시판 IDX */
	private int bbsIdx = 0;
	/** 설문 조사형 메뉴 여부 */
	private String isPoll;
	/** 등록일 */
	private Date regDt;
	/** 수정일 */
	private Date modDt;
	/** 메뉴 정렬 코드 */
	private String menuOrderCode;
	/** CCL타입 {@link CclType} */
	private String cclType = "";
	/** 공공누리 타입 {@link NuriType} */
	private String nuriType = "";

	private List<MenuVO> depth2MenuList;
	private List<MenuVO> depth3MenuList;
	private List<MenuVO> depth4MenuList;
	private List<MenuVO> depth5MenuList;
	private String cmsContentData = "";
	private String bbsName = "";
	private Date cmsContentDate = null;

	/** 부서구분코드1 */
	private String ptIdx = "";
	/** 부서구분코드2 */
	private String tmIdx = "";
	/** 부서구분코드3 */
	private String chIdx = "";
	/** 담당자 연락처 */
	private String chargeTel = "";
	/** 담당자 연락처- 앞자리 055-392- */
	private String chargeTel1 = "";
	/** 담당자 연락처 - 나머지 뒷자리 */
	private String chargeTel2 = "";
	/**  */
	private String chargeFnm = "";

	private String addNextValue = "";
	private int accessLevelCode = 0;
	/** 메뉴 개요 */
	private String menuSummary;

	private String isTermset = ""; // 기간설정 유무
	private String termStartDtStr = ""; // 메뉴노출시작일자 문자열
	private String termEndDtStr = ""; // 메뉴노출종료일자 문자열
	private Date termStartDt = null; // 메뉴노출시작일자
	private Date termEndDt = null; // 메뉴노출종료일자

	private String chargeId = ""; // 메뉴담당자 아이디
	private String chargeDepCode = ""; // 메뉴담당자 부서코드
	private String chargeDepNm = ""; // 메뉴담당자 부서명

	private String isSnsComment = ""; // 소셜댓글 사용 유무

	private String oldChargeId = ""; // 이전 메뉴담당자 아이디

	private String incContent;

	/** 하위 메뉴 리스트 */
	private List<MenuVO> subMenu = null;
	/** 첫 하위 메뉴 */
	private MenuVO lastMenu = null;
	/**  */
	private MenuVO parentMenu = null;

	private String spotTitle = null;
	private String subHeadTitle = null;
	private String subHeadTitle2 = null;

	private String targetMid = ""; //메뉴에 대한 수정시 사용하는 파라미터용



	//인계기능 추가 필드
	private int reqIdx = 0;
	private String reqState = "";
	private String reqChargeId = "";
	private String reqChargeFnm = "";
	private String reqChargeDepCode = "";
	private String reqChargeDepNm = "";
	private String reqChargeTel = "";
	private String moveChargeId = "";
	private String moveChargeFnm = "";
	private String moveChargeDepCode = "";
	private String moveChargeDepNm = "";
	private String moveChargeTel = "";
	private Date ackDt;

	private String notMappingVal = "";

	public MenuVO() {
		// default
	}

	public MenuVO(String mId) {
		setmId(mId);
	}

	public MenuVO(String mId, String siteCode) {
		super();
		this.mId = mId;
		this.siteCode = siteCode;
	}

	public MenuVO(String siteCode, String mId, String chargeFnm, String chargeId, String chargeDepCode, String chargeDepNm) {
		super();
		this.siteCode = siteCode;
		this.mId = mId;
		this.chargeFnm = chargeFnm;
		this.chargeId = chargeId;
		this.chargeDepCode = chargeDepCode;
		this.chargeDepNm = chargeDepNm;
	}

	public int getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}

	public String getChargeTel1() {
		return chargeTel1;
	}

	public void setChargeTel1(String chargeTel1) {
		this.chargeTel1 = chargeTel1;
	}

	public String getChargeTel2() {
		return chargeTel2;
	}

	public void setChargeTel2(String chargeTel2) {
		this.chargeTel2 = chargeTel2;
	}

	public void setMenuSummary(String menuSummary) {
		this.menuSummary = menuSummary;
	}

	public String getMenuSummary() {
		return menuSummary;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLinkUrl() {
		return linkUrl;

	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getProgramUrl() {
		return programUrl;
	}

	public void setProgramUrl(String programUrl) {
		this.programUrl = programUrl;
	}

	public String getContentFilePath() {
		return contentFilePath;
	}

	public void setContentFilePath(String contentFilePath) {
		this.contentFilePath = contentFilePath;
	}

	public String getCmsSiteCode() {
		return cmsSiteCode;
	}

	public void setCmsSiteCode(String cmsSiteCode) {
		this.cmsSiteCode = cmsSiteCode;
	}

	public String getCmsPageId() {
		return cmsPageId;
	}

	public void setCmsPageId(String cmsPageId) {
		this.cmsPageId = cmsPageId;
	}

	public String getCmsPageTypeCode() {
		return cmsPageTypeCode;
	}

	public void setCmsPageTypeCode(String cmsPageTypeCode) {
		this.cmsPageTypeCode = cmsPageTypeCode;
	}

	public String getCmsFolderIdx() {
		return cmsFolderIdx;
	}

	public void setCmsFolderIdx(String cmsFolderIdx) {
		this.cmsFolderIdx = cmsFolderIdx;
	}

	public String getCmsParentIdx() {
		return cmsParentIdx;
	}

	public void setCmsParentIdx(String cmsParentIdx) {
		this.cmsParentIdx = cmsParentIdx;
	}

	public String getCmsFolderCode() {
		return cmsFolderCode;
	}

	public void setCmsFolderCode(String cmsFolderCode) {
		this.cmsFolderCode = cmsFolderCode;
	}

	public int getParentIdx() {
		return parentIdx;
	}

	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getIsIncContent() {
		return isIncContent;
	}

	public void setIsIncContent(String isIncContent) {
		this.isIncContent = isIncContent;
	}

	public int getBbsIdx() {
		return bbsIdx;
	}

	public void setBbsIdx(int bbsIdx) {
		this.bbsIdx = bbsIdx;
	}

	public int getBbsMstIdx() {
		return bbsMstIdx;
	}

	public void setBbsMstIdx(int bbsMstIdx) {
		this.bbsMstIdx = bbsMstIdx;
	}

	public String getIsPoll() {
		return isPoll;
	}

	public void setIsPoll(String isPoll) {
		this.isPoll = isPoll;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}

	public String getMenuOrderCode() {
		return menuOrderCode;
	}

	public void setMenuOrderCode(String menuOrderCode) {
		this.menuOrderCode = menuOrderCode;
	}

	public List<MenuVO> getDepth2MenuList() {
		return depth2MenuList;
	}

	public void setDepth2MenuList(List<MenuVO> depth2MenuList) {
		List<MenuVO> _from = depth2MenuList;
		List<MenuVO> _to = new ArrayList<MenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth2MenuList = _to;
	}

	public List<MenuVO> getDepth3MenuList() {
		return depth3MenuList;
	}

	public void setDepth3MenuList(List<MenuVO> depth3MenuList) {
		List<MenuVO> _from = depth3MenuList;
		List<MenuVO> _to = new ArrayList<MenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth3MenuList = _to;
	}

	public List<MenuVO> getDepth4MenuList() {
		return depth4MenuList;
	}

	public void setDepth4MenuList(List<MenuVO> depth4MenuList) {
		List<MenuVO> _from = depth4MenuList;
		List<MenuVO> _to = new ArrayList<MenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth4MenuList = _to;
	}

	public List<MenuVO> getDepth5MenuList() {
		return depth5MenuList;
	}

	public void setDepth5MenuList(List<MenuVO> depth5MenuList) {
		List<MenuVO> _from = depth5MenuList;
		List<MenuVO> _to = new ArrayList<MenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth5MenuList = _to;
	}

	public String getCmsContentData() {
		return cmsContentData;
	}

	public void setCmsContentData(String cmsContentData) {
		this.cmsContentData = cmsContentData;
	}

	public String getBbsName() {
		return bbsName;
	}

	public void setBbsName(String bbsName) {
		this.bbsName = bbsName;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getTmIdx() {
		return tmIdx;
	}

	public void setTmIdx(String tmIdx) {
		this.tmIdx = tmIdx;
	}

	public String getChIdx() {
		return chIdx;
	}

	public void setChIdx(String chIdx) {
		this.chIdx = chIdx;
	}

	public String getChargeTel() {
		return chargeTel;
	}

	public void setChargeTel(String chargeTel) {
		this.chargeTel = chargeTel;
	}

	public String getChargeFnm() {
		return chargeFnm;
	}

	public void setChargeFnm(String chargeFnm) {
		this.chargeFnm = chargeFnm;
	}

	@Override
	public String toString() {
		return "MenuVO{" + "idx=" + idx + ", mId='" + mId + '\'' + ", menuName='" + menuName + '\'' + ", linkUrl='" + linkUrl + '\'' + ", target='" + target + '\'' + ", menuType='" + menuType + '\'' + ", isUse='" + isUse + '\'' + ", programUrl='" + programUrl + '\'' + ", contentFilePath='" + contentFilePath + '\'' + ", cmsSiteCode='" + cmsSiteCode + '\'' + ", cmsPageId='" + cmsPageId + '\'' + ", cmsPageTypeCode='" + cmsPageTypeCode + '\'' + ", cmsFolderIdx='" + cmsFolderIdx + '\'' + ", cmsParentIdx='" + cmsParentIdx + '\'' + ", cmsFolderCode='" + cmsFolderCode + '\'' + ", parentIdx=" + parentIdx + ", siteCode='" + siteCode + '\'' + ", menuOrder=" + menuOrder + ", menuLevel=" + menuLevel + ", isIncContent='" + isIncContent + '\'' + ", bbsMstIdx=" + bbsMstIdx + ", bbsIdx=" + bbsIdx + ", isPoll='" + isPoll + '\'' + ", regDt=" + regDt + ", modDt=" + modDt + ", menuOrderCode='" + menuOrderCode + '\'' + ", depth2MenuList=" + depth2MenuList + ", depth3MenuList=" + depth3MenuList + ", depth4MenuList=" + depth4MenuList + ", depth5MenuList=" + depth5MenuList + ", cmsContentData='" + cmsContentData + '\'' + ", ptIdx='" + ptIdx + '\'' + ", tmIdx='" + tmIdx + '\'' + ", chIdx='" + chIdx + '\'' + ", chargeTel='" + chargeTel + '\'' + ", chargeFnm='" + chargeFnm + '\'' + '}';
	}

	public String getAddNextValue() {
		return addNextValue;
	}

	public void setAddNextValue(String addNextValue) {
		this.addNextValue = addNextValue;
	}

	public int getAccessLevelCode() {
		return accessLevelCode;
	}

	public void setAccessLevelCode(int accessLevelCode) {
		this.accessLevelCode = accessLevelCode;
	}

	public String getIsTermset() {
		return isTermset;
	}

	public void setIsTermset(String isTermset) {
		this.isTermset = isTermset;
	}

	public Date getTermStartDt() {
		return termStartDt;
	}

	public void setTermStartDt(Date termStartDt) {
		this.termStartDt = termStartDt;
	}

	public Date getTermEndDt() {
		return termEndDt;
	}

	public void setTermEndDt(Date termEndDt) {
		this.termEndDt = termEndDt;
	}

	public String getTermStartDtStr() {
		return termStartDtStr;
	}

	public void setTermStartDtStr(String termStartDtStr) {
		this.termStartDtStr = termStartDtStr;
		if (termStartDtStr != null && !"".equals(termStartDtStr)) {
			this.termStartDt = TUtil.changeStringToDate(termStartDtStr, "yyyy-MM-dd");
		}
	}

	public String getTermEndDtStr() {
		return termEndDtStr;
	}

	public void setTermEndDtStr(String termEndDtStr) {
		this.termEndDtStr = termEndDtStr;

		if (termEndDtStr != null && !"".equals(termEndDtStr)) {
			this.termEndDt = TUtil.changeStringToDate(termEndDtStr, "yyyy-MM-dd");
		}
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getChargeDepCode() {
		return chargeDepCode;
	}

	public void setChargeDepCode(String chargeDepCode) {
		this.chargeDepCode = chargeDepCode;
	}

	public String getChargeDepNm() {
		return chargeDepNm;
	}

	public void setChargeDepNm(String chargeDepNm) {
		this.chargeDepNm = chargeDepNm;
	}

	public String getIsSnsComment() {
		return isSnsComment;
	}

	public void setIsSnsComment(String isSnsComment) {
		this.isSnsComment = isSnsComment;
	}

	public String getOldChargeId() {
		return oldChargeId;
	}

	public void setOldChargeId(String oldChargeId) {
		this.oldChargeId = oldChargeId;
	}

	public String getIncContent() {
		return incContent;
	}

	public void setIncContent(String incContent) {
		this.incContent = incContent;
	}

	public List<MenuVO> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<MenuVO> subMenu) {
		this.subMenu = subMenu;
	}

	public MenuVO getLastMenu() {
		return lastMenu;
	}

	public void setLastMenu(MenuVO lastMenu) {
		this.lastMenu = lastMenu;
	}

	public MenuVO getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(MenuVO parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getSpotTitle() {
		return spotTitle;
	}

	public void setSpotTitle(String spotTitle) {
		this.spotTitle = spotTitle;
	}

	public String getSubHeadTitle() {
		return subHeadTitle;
	}

	public void setSubHeadTitle(String subHeadTitle) {
		this.subHeadTitle = subHeadTitle;
	}

	public String getSubHeadTitle2() {
		return subHeadTitle2;
	}

	public void setSubHeadTitle2(String subHeadTitle2) {
		this.subHeadTitle2 = subHeadTitle2;
	}

	public Date getCmsContentDate() {
		return cmsContentDate;
	}

	public void setCmsContentDate(Date cmsContentDate) {
		this.cmsContentDate = cmsContentDate;
	}

	public String getTargetMid() {
		return targetMid;
	}

	public void setTargetMid(String targetMid) {
		this.targetMid = targetMid;
		this.mId = targetMid;
	}

	public String getCclType() {
		return StringUtil.isNotBlank(cclType) ? cclType : CclType.NO_USE.getCode();
	}

	public void setCclType(String cclType) {
		this.cclType = cclType;
	}

	public String getNuriType() {
		return StringUtil.isNotBlank(nuriType) ? nuriType : NuriType.NO_USE.getCode();
	}

	public void setNuriType(String nuriType) {
		this.nuriType = nuriType;
	}



	public int getReqIdx() {
		return reqIdx;
	}

	public void setReqIdx(int reqIdx) {
		this.reqIdx = reqIdx;
	}

	public String getReqState() {
		return reqState;
	}

	public void setReqState(String reqState) {
		this.reqState = reqState;
	}

	public String getReqChargeId() {
		return reqChargeId;
	}

	public void setReqChargeId(String reqChargeId) {
		this.reqChargeId = reqChargeId;
	}

	public String getReqChargeFnm() {
		return reqChargeFnm;
	}

	public void setReqChargeFnm(String reqChargeFnm) {
		this.reqChargeFnm = reqChargeFnm;
	}

	public String getReqChargeDepCode() {
		return reqChargeDepCode;
	}

	public void setReqChargeDepCode(String reqChargeDepCode) {
		this.reqChargeDepCode = reqChargeDepCode;
	}

	public String getReqChargeDepNm() {
		return reqChargeDepNm;
	}

	public void setReqChargeDepNm(String reqChargeDepNm) {
		this.reqChargeDepNm = reqChargeDepNm;
	}

	public String getReqChargeTel() {
		return reqChargeTel;
	}

	public void setReqChargeTel(String reqChargeTel) {
		this.reqChargeTel = reqChargeTel;
	}

	public String getMoveChargeId() {
		return moveChargeId;
	}

	public void setMoveChargeId(String moveChargeId) {
		this.moveChargeId = moveChargeId;
	}

	public String getMoveChargeFnm() {
		return moveChargeFnm;
	}

	public void setMoveChargeFnm(String moveChargeFnm) {
		this.moveChargeFnm = moveChargeFnm;
	}

	public String getMoveChargeDepCode() {
		return moveChargeDepCode;
	}

	public void setMoveChargeDepCode(String moveChargeDepCode) {
		this.moveChargeDepCode = moveChargeDepCode;
	}

	public String getMoveChargeDepNm() {
		return moveChargeDepNm;
	}

	public void setMoveChargeDepNm(String moveChargeDepNm) {
		this.moveChargeDepNm = moveChargeDepNm;
	}

	public String getMoveChargeTel() {
		return moveChargeTel;
	}

	public void setMoveChargeTel(String moveChargeTel) {
		this.moveChargeTel = moveChargeTel;
	}

	public Date getAckDt() {
		return ackDt;
	}

	public void setAckDt(Date ackDt) {
		this.ackDt = ackDt;
	}

	public String getNotMappingVal() {
		return notMappingVal;
	}

	public void setNotMappingVal(String notMappingVal) {
		this.notMappingVal = notMappingVal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
