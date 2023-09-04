package egovframework.portal.sys.privacy.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.util.StringUtil;
import net.arnx.jsonic.JSON;

/**
 * 개인정보 취급 메뉴 데이터 삭제 이력 VO
 *
 * <pre>
 * 개정이력(Modification Prmrmation)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 20.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 20.
 */
public class PrivacyDataHistoryVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** {@link PrivacyPrmVO#getIdx()} */
	private String prmIdx = "";
	/** 프로그램명 {@link PrivacyPrmVO#getPrmName()} */
	private String prmName = "";
	/** 테이블명 {@link PrivacyPrmVO#getPrmTableName()} */
	private String prmTableName = "";
	/** 보유 기간 {@link PrivacyPrmVO#getPrmPeriod()} */
	private String prmPeriod = "";
	/** 설정 정보 JSON {@link PrivacyPrmVO} */
	private String prmInfo = "";
	/** 데이터 삭제 건수 */
	private int procCnt = -1;
	/** 접근 IP */
	private String hostIp = "";
	/** 삭제 처리일 */
	private Date deleteDate = null;

	/* ##### PROCESS ##### */

	/** {@link #prmInfo} 객체 */
	private PrivacyPrmVO prm = null;

	/* ##### SEARCH KEYWORD ##### */

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

	public String getPrmPeriod() {
		return prmPeriod;
	}

	public void setPrmPeriod(String prmPeriod) {
		this.prmPeriod = prmPeriod;
	}

	public String getPrmInfo() {
		return prmInfo;
	}

	public void setPrmInfo(String prmInfo) {
		this.prmInfo = prmInfo;
		if (StringUtil.isNotBlank(prmInfo)) {
			setPrm(JSON.decode(prmInfo, PrivacyPrmVO.class));
		}
	}

	public int getProcCnt() {
		return procCnt;
	}

	public void setProcCnt(int procCnt) {
		this.procCnt = procCnt;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public PrivacyPrmVO getPrm() {
		return prm;
	}

	public void setPrm(PrivacyPrmVO prm) {
		this.prm = prm;
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
