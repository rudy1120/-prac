package egovframework.portal.unit.common;

import egovframework.portal.util.StringUtil;

public enum UserType {

	NONE(""),
	FACEBOOK("F"),
	TWITTER("T"),
	MEMBER("M"),
	PHONE("P"),
	GPIN("G"),;

	private String code;

	private UserType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static UserType toType(String code) {
		if (StringUtil.isNotBlank(code)) {
			for (UserType type : UserType.values()) {
				if (type.getCode().equals(code)) {
					return type;
				}
			}
		}

		return NONE;
	}

}
