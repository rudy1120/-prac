package egovframework.portal.sys.basic.stateMng.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

/**
 * 통계관리 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.22		엄동건				최초 생성 및 코딩
 * 2016.09.05		J.Ryeon Lee			유일키 조건 변경
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2015.01.22
 */
public class StateSysVO extends CommonVO {

	// log site + menu

	/* owt_state_today */
	private Date logToday = null;
	private String logSitecode = "";
	private String logMid = "";
	private int logCount = 0;
	private int logTotal = 0;

	/* owt_state_log */
	private int idx = 0;
	private String siteCode = "";
	private String menuId = "";
	private String ip = "";
	private String userAgent = "";
	private String userAgentFull = "";
	private String referer = ""; // 접근경로 도메인
	private String refererFull = ""; // 접근경로 전체
	private String sessionId = "";
	private Date logDate = null;

	private String year; // 년도검색
	private String month; // 월검색
	private String dateStart; // 기간검색 시작일
	private String dateEnd; // 기간검색 종료일

	private int sType; // 기간검색 조건
	private String sTarget; // 사이트나 메뉴 조회대상

	private int kind; // 조회종류
	private String searchTable; // 조회테이블
	private String statsLabel; // 통계출력 개별 레이블
	private int statsCount; // 통계출력 개별 카운트
	private int statsCountMax; // 조회 카운트 합계

	private String mId; // 메뉴아이디
	private String menuName; // 메뉴명

	private String today; // yyyy-MM-dd 검색 조건
	private int cntTotal; // 로그 체크 카운트 - 프로시저 대용 step에서 사용

	private long keyTime;

	// 많이찾는 메뉴, 사이트
	private String siteName = "";
	private String menuPath = "";

	public void setCntTotal(int cntTotal) {
		this.cntTotal = cntTotal;
	}

	public int getCntTotal() {
		return cntTotal;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getToday() {
		return today;
	}

	public Date getLogToday() {
		return logToday;
	}

	public void setLogToday(Date logToday) {
		this.logToday = logToday;
	}

	public String getLogSitecode() {
		return logSitecode;
	}

	public void setLogSitecode(String logSitecode) {
		this.logSitecode = logSitecode;
	}

	public String getLogMid() {
		return logMid;
	}

	public void setLogMid(String logMid) {
		this.logMid = logMid;
	}

	public int getLogCount() {
		return logCount;
	}

	public void setLogCount(int logCount) {
		this.logCount = logCount;
	}

	public int getLogTotal() {
		return logTotal;
	}

	public void setLogTotal(int logTotal) {
		this.logTotal = logTotal;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgentFull() {
		return userAgentFull;
	}

	public void setUserAgentFull(String userAgentFull) {
		this.userAgentFull = userAgentFull;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getRefererFull() {
		return refererFull;
	}

	public void setRefererFull(String refererFull) {
		this.refererFull = refererFull;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getsType() {
		return sType;
	}

	public void setsType(int sType) {
		this.sType = sType;
	}

	public String getsTarget() {
		return sTarget;
	}

	public void setsTarget(String sTarget) {
		this.sTarget = sTarget;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getSearchTable() {
		return searchTable;
	}

	public void setSearchTable(String searchTable) {
		this.searchTable = searchTable;
	}

	public String getStatsLabel() {
		return statsLabel;
	}

	public void setStatsLabel(String statsLabel) {
		this.statsLabel = statsLabel;
	}

	public int getStatsCount() {
		return statsCount;
	}

	public void setStatsCount(int statsCount) {
		this.statsCount = statsCount;
	}

	public int getStatsCountMax() {
		return statsCountMax;
	}

	public void setStatsCountMax(int statsCountMax) {
		this.statsCountMax = statsCountMax;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public long getKeyTime() {
		return keyTime;
	}

	public void setKeyTime(long keyTime) {
		this.keyTime = keyTime;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}



}
