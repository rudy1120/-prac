package egovframework.portal.sys.log.vo;

import java.text.ParseException;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.log.LogType;
import egovframework.portal.util.StringUtil;

/**
 * 관리 테이블: pt_admin_proc_log
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 4. 12.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 4. 12.
 */
public class LoggingVO extends CommonVO {

	/* ======== DB 영속화 대상 ======== */

	/** PK */
	private String idx = "";
	/** 메뉴 ID */
	private String mid = "";
	/** 접근 HOST IP */
	private String host_ip = "";
	/** 접근 URL */
	private String full_url = "";
	/** 관리자 ID */
	private String admin_id = "";
	/** 관리자명 */
	private String admin_name = "";
	/** 부서 코드 */
	private String dept_id = "";
	/** 부서명 */
	private String dept_name = "";
	/** 접근일 */
	private Date access_date = null;
	/** 파라미터 */
	private String params = null;

	/* ======== JOIN RESULT ======== */

	/** 메뉴명 */
	private String menu_name = "";
	/** URL 간략 분석 */
	private String proc_name = "";
	/**  */
	private LoggingConfigVO config = null;

	/* ======== 검색 키워드 ======== */

	/** 0: 로그인, 1: 로그인이 아닌 기타 처리, 2:, 3: {@link LogType#getCode()} */
	private String searchProcType = "";
	/** 검색 최소일 */
	private String searchSday = "";
	/** 검색 최대일 */
	private String searchEday = "";
	/** {@link LogType#getSql()} */
	private String logTypeSearcher = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getFull_url() {
		return full_url;
	}

	public void setFull_url(String full_url) {
		this.full_url = full_url;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Date getAccess_date() {
		return access_date;
	}

	public void setAccess_date(String cAccess_date) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.access_date = transFormat.parse(cAccess_date);
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getProc_name() {
		return proc_name;
	}

	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}

	public LoggingConfigVO getConfig() {
		return config;
	}

	public void setConfig(LoggingConfigVO config) {
		this.config = config;
	}

	public String getSearchProcType() {
		return searchProcType;
	}

	public void setSearchProcType(String searchProcType) {
		this.searchProcType = searchProcType;
		if (StringUtil.isNotBlank(searchProcType)) {
			setLogTypeSearcher(LogType.toType(searchProcType).getSql());
		}
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

	public String getLogTypeSearcher() {
		return logTypeSearcher;
	}

	public void setLogTypeSearcher(String logTypeSearcher) {
		this.logTypeSearcher = logTypeSearcher;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
