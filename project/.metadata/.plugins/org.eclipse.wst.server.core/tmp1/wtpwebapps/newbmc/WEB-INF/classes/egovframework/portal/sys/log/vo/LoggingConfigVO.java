package egovframework.portal.sys.log.vo;

import egovframework.portal.sys.log.DelType;
import egovframework.portal.sys.log.LogType;

public class LoggingConfigVO {

	/** PK */
	private String idx = "";
	/** 항목명 */
	private String logName = "";
	/** 보관 기간 */
	private String logPeriod = "";
	/** 로그 타입 @see {@link LogType} */
	private String logType = "";
	/** 로그 삭제 타입 @see {@link DelType} */
	private String logDelType = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogPeriod() {
		return logPeriod;
	}

	public void setLogPeriod(String logPeriod) {
		this.logPeriod = logPeriod;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogDelType() {
		return logDelType;
	}

	public void setLogDelType(String logDelType) {
		this.logDelType = logDelType;
	}

}
