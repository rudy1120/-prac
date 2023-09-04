package egovframework.portal.sys.sysAuth;

import egovframework.portal.util.StringUtil;

/**
 * 관리자 타입
 *
 * @author J.Ryeon Lee
 * @since 2015.12.17
 */
public enum AdminType {

	/** 공무원 */
	PUBLIC_OFFICIAL("0", null, "공무원"),
	/** 외부 관리자 (SAMPLE, 추후 추가하는 경우 아래 객체를 복사해 사용해주세요.) */
	SAMPLE_EXTERNAL_ADMIN("1", "sampleMng", "샘플"),
	;

	private String code;
	private String mngSiteCode;
	private String name;

	private AdminType(String code, String mngSiteCode, String name) {
		this.code = code;
		this.mngSiteCode = mngSiteCode;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getMngSiteCode() {
		return mngSiteCode;
	}

	public String getName() {
		return name;
	}

	public static AdminType toType(String mngSiteCode) {
		if (StringUtil.isNotBlank(mngSiteCode)) {
			for (AdminType type : AdminType.values()) {
				if (mngSiteCode.equals(type.getMngSiteCode())) {
					return type;
				}
			}
		}

		return null;
	}

}
