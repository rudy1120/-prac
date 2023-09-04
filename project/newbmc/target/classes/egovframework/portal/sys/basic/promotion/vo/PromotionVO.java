package egovframework.portal.sys.basic.promotion.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.basic.promotion.PromotionType;
import egovframework.portal.util.StringUtil;

/**
 * 홍보자료 관리 VO
 * 관리 대상 테이블: promotion
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 18.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 18.
 */
public class PromotionVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** @see PromotionType */
	private String prmtType = "";
	/** 프로모션명 */
	private String prmtName = "";
	/** 사용여부 (Y: 사용, N: 미사용) */
	private String useYn = "";
	/** URL */
	private String prmtUrl = "";
	/** 새창여부 (Y: 새창, N: 현재창) */
	private String blankYn = "";
	/** 내용 */
	private String prmtContent = "";
	/** 시작일 */
	private String prmtSday = "";
	/** 종료일 */
	private String prmtEday = "";
	/** 템플릿 코드 */
	private String prmtTemplate = "";
	/** 정렬순서 */
	private String prmtOrder = "";
	/** 정렬순서기존 */
	private String prmtOldOrder = "";
	/** 첨부파일 ID */
	private String attachId = "";
	/** 삭제 여부(Y: 삭제, N: 미삭제) */
	private String isDel = "";
	/** 등록일 */
	private Date createDate = null;
	/** 수정일 */
	private Date updateDate = null;
	/** 삭제일 */
	private Date deleteDate = null;

	/* ==================== PROCESS ==================== */

	/** 사이트 정보 */
	private List<PromotionSiteVO> promotionSites = new ArrayList<>();
	/** 사이트 IDX 목록 */
	private List<String> siteIdxs = new ArrayList<>();
	/**  */
	private PromotionType type = null;

	/* ==================== SEARCH KEYWORD ==================== */

	/** 사이트 PK 검색 키워드 */
	private String searchSiteIdx = "";
	/** 사용 여부 검색 키워드 */
	private String searchUseYn = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getPrmtType() {
		return prmtType;
	}

	public void setPrmtType(String prmtType) {
		this.prmtType = prmtType;
	}

	public String getPrmtName() {
		return prmtName;
	}

	public void setPrmtName(String prmtName) {
		this.prmtName = prmtName;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public List<PromotionSiteVO> getPromotionSites() {
		return promotionSites;
	}

	public void setPromotionSites(List<PromotionSiteVO> promotionSites) {
		this.promotionSites = promotionSites;
	}

	public String getPrmtUrl() {
		return prmtUrl;
	}

	public void setPrmtUrl(String prmtUrl) {
		this.prmtUrl = prmtUrl;
	}

	public String getBlankYn() {
		return blankYn;
	}

	public void setBlankYn(String blankYn) {
		this.blankYn = blankYn;
	}

	public String getPrmtContent() {
		return prmtContent;
	}

	public void setPrmtContent(String prmtContent) {
		this.prmtContent = prmtContent;
	}

	public String getPrmtSday() {
		return prmtSday;
	}

	public void setPrmtSday(String prmtSday) {
		this.prmtSday = prmtSday;
	}

	public String getPrmtEday() {
		return prmtEday;
	}

	public void setPrmtEday(String prmtEday) {
		this.prmtEday = prmtEday;
	}

	public String getPrmtTemplate() {
		return prmtTemplate;
	}

	public void setPrmtTemplate(String prmtTemplate) {
		this.prmtTemplate = prmtTemplate;
	}


	public String getPrmtOrder() {
		return prmtOrder;
	}

	public void setPrmtOrder(String prmtOrder) {
		this.prmtOrder = prmtOrder;
	}

	public String getPrmtOldOrder() {
		return prmtOldOrder;
	}

	public void setPrmtOldOrder(String prmtOldOrder) {
		this.prmtOldOrder = prmtOldOrder;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public List<String> getSiteIdxs() {
		return siteIdxs;
	}

	public void setSiteIdxs(List<String> siteIdxs) {
		this.siteIdxs = siteIdxs;
	}

	public PromotionType getType() {
		return type;
	}

	public void setType(PromotionType type) {
		this.type = type;
		if (type != null) {
			setPrmtType(type.getCode());
		}
	}

	public String getSearchSiteIdx() {
		return searchSiteIdx;
	}

	public void setSearchSiteIdx(String searchSiteIdx) {
		this.searchSiteIdx = searchSiteIdx;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public void setDefaultValues() {
		if (StringUtil.isBlank(getUseYn()) || "N".equals(getUseYn())) {
			setUseYn("N");
		} else {
			setUseYn("Y");
		}

		if (StringUtil.isBlank(getBlankYn()) || "N".equals(getBlankYn())) {
			setBlankYn("N");
		} else {
			setBlankYn("Y");
		}

		if (StringUtil.isBlank(getPrmtTemplate())) {
			setPrmtTemplate("0");
		}

		if (getSiteIdxs() == null || getSiteIdxs().size() < 1) {
			setSiteIdxs(new ArrayList<String>());
			getSiteIdxs().add("0"); // 전체 사이트 검색용 dummy값 설정
		} else {
			setSearchSiteIdx(this.siteIdxs.get(0));
		}
	}

}
