package egovframework.portal.sys.log;

import egovframework.portal.util.StringUtil;

public enum LogType {

	LOGIN(0, "접속 로그", " (log.full_url LIKE '%/login.do%' OR log.full_url LIKE '%/logout.do%' OR log.full_url LIKE '%superLoginProc.do%' OR log.full_url LIKE '%loginProc.do%') "),
	PRIVACY_MENU_ACCESS(1, "개인정보 취급 메뉴 접근 로그", " (log.mid IS NOT NULL AND sm.privacy_data_yn = 'Y') "),
	/** 메뉴 접근 로그 중 개인정보 취급 메뉴 접근 로그를 제외 */
	ACCESS(2, "메뉴 접근 로그", " NOT (" + LogType.LOGIN.getSql() + " AND " + LogType.PRIVACY_MENU_ACCESS.getSql() + ")"),
	;

	private int code;
	private String desc;
	private String sql;

	private LogType(int code, String desc, String sql) {
		this.code = code;
		this.desc = desc;
		this.sql = sql;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String getSql() {
		return sql;
	}

	public static LogType toType(String code) {
		if (StringUtil.isNumber(code)) {
			for (LogType type : LogType.values()) {
				if (type.getCode() == Integer.parseInt(code)) {
					return type;
				}
			}
		}

		return null;
	}

}
