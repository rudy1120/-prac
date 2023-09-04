package egovframework.portal.sys.privacy.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.privacy.PrmType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.StringUtil;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONHint;

/**
 * 개인정보취급메뉴 관리 VO
 * 관리 테이블: pt_privacy_prm_config
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 19.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 19.
 */
public class PrivacyPrmVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** 프로그램 타입 {@link PrmType} ※ 사용자 입력은 받지 않음. {@link #getPtIdx()} 입력 여부에 따라 자동으로 타입 결정. {@link #setPtIdx(String)} 참조 */
	private int prmType = -1;
	/** 사이트 코드 {@link MenusMngVO} */
	private String prmSiteCode = "";
	/** 사이트명 */
	private String prmSiteName = "";
	/** 사용자 메뉴 ID */
	private String prmMid = "";
	/** 관리자 메뉴 ID */
	private String prmSysMid = "";
	/** 프로그램명 */
	private String prmName = "";
	/** 테이블명 */
	private String prmTableName = "";
	/** 기준일 컬럼명 */
	private String prmTriggerCol = "";
	/** 추가 파라미터 */
	private String prmParamsMap = "";
	/** 개인정보취급항목명 */
	private String prmColNames = "";
	/** 담당 개발자명 */
	private String prmDeveloper = "";
	/** 보유 기간 */
	private String prmPeriod = "";
	/** 게시판 IDX {@link BbsConfigVO#getPtIdx()} */
	private String ptIdx = "";

	/* #### PROCESS #### */

	/** {@link #paramsMap} json 데이터 객체화 */
	@JSONHint(ignore = true)
	private Map<String, Object> paramsMap = null;

	/* #### SEARCH KEYWORD #### */

	@JSONHint(ignore = true)
	private String searchPrmType = "";
	@JSONHint(ignore = true)
	private String searchSiteCode = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public int getPrmType() {
		return prmType;
	}

	public void setPrmType(int prmType) {
		this.prmType = prmType;
	}

	public String getPrmSiteCode() {
		return prmSiteCode;
	}

	public void setPrmSiteCode(String prmSiteCode) {
		this.prmSiteCode = prmSiteCode;
	}

	public String getPrmSiteName() {
		return prmSiteName;
	}

	public void setPrmSiteName(String prmSiteName) {
		this.prmSiteName = prmSiteName;
	}

	public String getPrmMid() {
		return prmMid;
	}

	public void setPrmMid(String prmMid) {
		this.prmMid = prmMid;
	}

	public String getPrmSysMid() {
		return prmSysMid;
	}

	public void setPrmSysMid(String prmSysMid) {
		this.prmSysMid = prmSysMid;
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

	public String getPrmTriggerCol() {
		return prmTriggerCol;
	}

	public void setPrmTriggerCol(String prmTriggerCol) {
		this.prmTriggerCol = prmTriggerCol;
	}

	public String getPrmParamsMap() {
		return prmParamsMap;
	}

	public void setPrmParamsMap(String prmParamsMap) {
		this.prmParamsMap = prmParamsMap;
	}

	public String getPrmColNames() {
		return prmColNames;
	}

	public void setPrmColNames(String prmColNames) {
		this.prmColNames = prmColNames;
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

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
		setPrmType(StringUtil.isNumber(ptIdx) ? PrmType.BOARD.getCode() : PrmType.UNIT.getCode());
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	@SuppressWarnings("serial")
	public void setParamsMap() {
		if (StringUtil.isNotBlank(getPrmParamsMap())) {
			this.paramsMap = JSON.decode(getPrmParamsMap(), (new HashMap<String, Object>() {
			}).getClass().getGenericSuperclass());
		}
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getSearchPrmType() {
		return searchPrmType;
	}

	public void setSearchPrmType(String searchPrmType) {
		this.searchPrmType = searchPrmType;
	}

	public String getSearchSiteCode() {
		return searchSiteCode;
	}

	public void setSearchSiteCode(String searchSiteCode) {
		this.searchSiteCode = searchSiteCode;
	}

	public Map<String, Object> getSqlInjectionClearedParamsMap() {
		Map<String, Object> clear = new HashMap<>();
		if (getParamsMap() != null) {
			for (Entry<String, Object> entry : getParamsMap().entrySet()) {
				if (entry.getKey().matches(Constant.COLUMN_REG)) {
					clear.put(entry.getKey(), entry.getValue());
				}
			}
		}

		return clear;
	}

	public void copyFrom(BbsConfigVO bbsConfigVO, AdminLoginVO admin) {
		setPrmType(PrmType.BOARD.getCode());
		setPrmSiteCode(bbsConfigVO.getPtSiteCode());
		setPrmSiteName(bbsConfigVO.getSiteName());
		setPrmSysMid(EgovProperties.getProperty(Constant.SYS_MENU_ID_BOARD));
		setPrmTableName("bbs_board");
		setPrmName("[게시판] " + bbsConfigVO.getPtTitle());
		setPrmDeveloper("게시판 생성자: " + admin.getAdminId());
		setPrmColNames(bbsConfigVO.getPtSaveColNames());
		setPrmPeriod(bbsConfigVO.getPtSavePeriod());
		setPtIdx(bbsConfigVO.getPtIdx());
	}

}
