package egovframework.portal.main;

import java.util.ArrayList;
import java.util.List;

import egovframework.portal.util.StringUtil;

/**
 * 사이트 코드 프로퍼티 관리 열거 클래스
 *
 * @author J.Ryeon Lee
 * @since 2015.11.23
 */
public enum SiteCode {

	NONE("", "", false, false, false, false, false),

	/* ======================= 개별 ======================= */

	/** 공통 */
	COMMON("common", "공통", false, false, false, false, false),
	/** 대표 */
	PORTAL("portal", "대표", false, false, true, false, false),
	/** BMC */
	BMC("bmc", "부산도시공사", false, false, true, false, false),
	
	OSIRIA("osiria", "오시리아", false, false, true, false, false),
	/*
	HISTORY("history", "역사관", false, false, true, false, false),*/
	BADAGA("badaga", "바다가", false, false, true, false, false),
	
	PORTAL_EN("eng", "대표(영문)", false, false, true, false, false),

	/* ======================= 빌트인(원뎁스) ======================= */

	// ONE("one", "", true, false, false, false, false),

	/* ======================= 빌트인(투뎁스) ======================= */

	CSC("csc", "읍면동", false, true, false, false, false),
	SOJU("soju", "소주동", false, true, false, false, false),

	;

	/** 사이트 코드 */
	private String code;
	/** 사이트명 */
	private String name;
	/** 원뎁스 빌트인 */
	private boolean oneDepthBuiltIn;
	/** 투뎁스 빌트인 */
	private boolean twoDepthBuiltIn;
	/** 사이트맵 지원 여부 */
	private boolean sitemap;
	/** 반응형 사이트 */
	private boolean isResponsive;
	/** 외부 공통 게시판 지원 사이트 여부 (본청은 불가) */
	private boolean supportExternalCommonBbs;

	private SiteCode(String code, String name, boolean oneDepthBuiltIn, boolean twoDepthBuiltIn, boolean hasSitemap, boolean isResponsive, boolean supportExternalCommonBbs) {
		this.code = code;
		this.name = name;
		this.oneDepthBuiltIn = oneDepthBuiltIn;
		this.twoDepthBuiltIn = twoDepthBuiltIn;
		this.sitemap = hasSitemap;
		this.isResponsive = isResponsive;
		this.supportExternalCommonBbs = supportExternalCommonBbs;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public boolean isBuiltIn() {
		return oneDepthBuiltIn || twoDepthBuiltIn;
	}

	public boolean isOneDepthBuiltIn() {
		return oneDepthBuiltIn;
	}

	public boolean isTwoDepthBuiltIn() {
		return twoDepthBuiltIn;
	}

	public boolean hasSitemap() {
		return sitemap;
	}

	public boolean isResponsive() {
		return isResponsive;
	}

	public boolean isSupportExternalCommonBbs() {
		return supportExternalCommonBbs;
	}

	public List<SiteCode> getAllBuiltInSite() {
		List<SiteCode> rtn = getAllOneDepthBuiltInSite();
		rtn.addAll(getAllTwoDepthBuiltInSite());
		return rtn;
	}

	public List<SiteCode> getAllOneDepthBuiltInSite() {
		List<SiteCode> rtn = new ArrayList<SiteCode>();
		for (SiteCode sitecode : SiteCode.values()) {
			if (sitecode.isOneDepthBuiltIn()) {
				rtn.add(sitecode);
			}
		}

		return rtn;
	}

	public List<SiteCode> getAllTwoDepthBuiltInSite() {
		List<SiteCode> rtn = new ArrayList<SiteCode>();
		for (SiteCode sitecode : SiteCode.values()) {
			if (sitecode.isTwoDepthBuiltIn()) {
				rtn.add(sitecode);
			}
		}

		return rtn;
	}

	public static SiteCode toType(String code) {
		if (StringUtil.isNotBlank(code)) {
			for (SiteCode sitecode : SiteCode.values()) {
				if (sitecode.getCode().equals(code)) {
					return sitecode;
				}
			}
		}

		return NONE; // dummy
	}

	public static String branch(String uri) { // TODO 2뎁스 루트가 늘어나는 경우
		if (StringUtil.isNotBlank(uri)) {
			if (uri.matches("^/dept/.+$")) { // 부서
				return uri.split("/")[2];
			} else if (uri.matches("^/csc/.+$")) { // 읍면동
				return uri.split("/")[2];
			} else if (uri.matches("^/sys/.+$")) { // 관리자
				return uri.split("/")[2];
			}

			return uri.split("/")[1];
		}

		return "";
	}

	public static String full(String _uri) {
		String uri[] = _uri.split("/");
		return uri[1].equals("csc") || uri.equals("dept") ? uri[1] + "/" + uri[2] : uri[1];
	}
}
