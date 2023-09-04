package egovframework.portal.sys.basic.promotion.vo;

import egovframework.portal.util.StringUtil;

public class PromotionSiteVO {

	/** 프로모션 PK {@link PromotionVO#getIdx()} */
	private String prmtIdx = "";
	/** 사이트 PK */
	private String siteIdx = "";

	/* ##### PROCESS ##### */

	/** 사이트명 */
	private String siteName = "";

	public PromotionSiteVO() {
		// default
	}

	public PromotionSiteVO(String prmtIdx, String siteIdx) {
		setPrmtIdx(prmtIdx);
		setSiteIdx(siteIdx);
	}

	public String getPrmtIdx() {
		return prmtIdx;
	}

	public void setPrmtIdx(String prmtIdx) {
		this.prmtIdx = prmtIdx;
	}

	public String getSiteIdx() {
		return siteIdx;
	}

	public void setSiteIdx(String siteIdx) {
		this.siteIdx = siteIdx;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = StringUtil.isNotBlank(siteName) ? siteName : "전체";
	}

}
