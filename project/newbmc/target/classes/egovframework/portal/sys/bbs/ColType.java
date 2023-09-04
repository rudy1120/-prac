package egovframework.portal.sys.bbs;

import egovframework.portal.util.StringUtil;

public enum ColType {

	NONE("", ""),
	IP("ip", "IP"),
	LOG("log", "이용 내역 기록"),
	NAME("bWrite", "이름"),
	ADDR1("bAddr1", "주소"),
	ADDR2("bAddr2", "상세 주소"),
	PHONE("bPhone", "휴대전화"),
	;

	private String code;
	private String name;

	private ColType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static ColType toType(String code) {
		if (StringUtil.isNotBlank(code)) {
			for (ColType col : ColType.values()) {
				if (col.getCode().equals(code)) {
					return col;
				}
			}
		}

		return NONE;
	}

}
