package egovframework.portal.sys.MenuMng.vo;

import egovframework.portal.common.vo.CommonVO;

import java.io.Serializable;
import java.util.Date;

/**
 * 메뉴별 담당자 연혁 관리 VO 클래스
 * @ Author : 엄동건
 * @ Date : 2014-10-07
 * @ File Name : MenusMngVO.java
 *
 * @see
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014-01-20                 최초 생성
 *
 * </pre>
 */
public class MenusChargeHistoryMngVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String siteCode = "";
	private String mid = "";
	private String chargeId = "";
	private String chargeFnm = "";
	private String chargeDepCode = "";
	private String chargeDepNm = "";
	private Date regDate = null;

	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getChargeFnm() {
		return chargeFnm;
	}
	public void setChargeFnm(String chargeFnm) {
		this.chargeFnm = chargeFnm;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
