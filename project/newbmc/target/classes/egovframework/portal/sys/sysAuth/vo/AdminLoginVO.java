package egovframework.portal.sys.sysAuth.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.sysAuth.AdminType;
import egovframework.portal.util.SecurityUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.validation.Password;

/**
 * 공통 - 관리자 로그인 정보를 위한 VO 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자			수정내용
 * -------------	------------	---------------------------
 * 2014.11.17		엄동건			최초 생성
 * 2016.03.18		J.Ryeon Lee		외부 관리자 로그인 정보도 담을 수 있도록 추가 처리
 * 2017.07.17		J.Ryeon Lee		리팩키징
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2014.11.17
 * @version 1.0
 */
public class AdminLoginVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = -1;

	/**  */
	private String hostIp = "";
	/** 관리자 타입 */
	private AdminType adminType = AdminType.PUBLIC_OFFICIAL;
	/** 관리자 타입 코드 (외부 관리자용) */
	private String AdminTypeCode = "";
	/** 관리 대상 mid (외부 관리자용) */
	private String mids = "";
	/** 관리 대상 mid (외부 관리자용, view용) */
	private List<String> midList = new ArrayList<>();
	/** 관리 대상 게시판 IDX 목록 (외부 관리자용) */
	private String ptIdxs = "";
	/**  */
	private String isDel = "";

	private int cntAuthHead2 = 0; // 대메뉴 권한관리 접근권한 카운팅
	private int cntAuthHead3 = 0; // 대메뉴 cms관리 접근권한 카운팅
	private String urlAuthHead2 = ""; // 관리메뉴 접근시 url

	//SSO로 넘어올경우 개인권한 조회 위한 아이디 매핑 처리 20160513 손영식
	private String usrId = "";

	// CMS 로그인키
	private String cmsLoginKey = "";

	/***/
	private String adminId;
	/***/
	private String[] adminGroupCode;
	/***/
	private String adminLevelCode;
	/***/
	private String adminLevelDisp;
	/***/
	private String adminName;
	/***/
	private String adminRealName;
	/***/
	private String email;
	/***/
	private String tel;
	/***/
	private Date overDate;
	/***/
	private boolean bUse;

	// 홈페이지 로그인
	/** 관리자 아이디 */
	private String id = "";
	/** 관리자 패스워드 */
	@Password
	private String pwd = "";
	/** 관리자 이름 */
	private String name = "";
	/** 부서 1depth 번호 */
	private String ptIdx = "";
	/** 부서 2depth 번호 */
	private String tmIdx = "";
	/** 부서 2depth 번호 */
	private String chIdx = "";
	/** 관리자 권한(모든권한) */
	private String adminAuth = "";
	/** 게시판 접근권한(전체 접근권한) */
	private String bbsAuth = "";
	/** 부서 관리 권한 */
	private String orgAuth = "";
	/** 통계 관리 권한 */
	private String statAuth = "";
	/** 부서 이름 */
	private String ptnm = "";
	/** 카테고리 정보 */
	private String cate = "";
	/** 작업내용 */
	private String cont1 = "";
	/** 작업 세부내용 */
	private String cont2 = "";

	/** 틈새업무에서 사용하는 소속 부서코드 */
	private String deptId = "";

	/** 틈새업무에서 사용하는 소속 부서명 */
	private String deptName = "";

	/** 관리자 메뉴 접근권한 리스트 */
	private List<SysMenuAuthVO> adminMenuAuths = null;

	/** 관리자 사이트 접근권한 리스트 */
	private List<SysSiteAuthVO> adminSiteAuths = null;

	/** 관리자 엑세스 레벨 코드 */
	private int adminAccessLevelCode = 0;

	/** 관리자 설명 */
	private String explan = "";

	/** 접근 IP */
	private String ip = "";
	/**  */
	private String searchAdminTypeCode = "";

	/* 20170420 J.Ryeon Lee 보안 가이드 추가 처리 */

	/** 비밀번호 암호화 */
	private String encPasswd = "";
	/** 비밀번호 변경용 */
	@Password
	private String newPwd = "";
	/** 비밀번호 변경 확인용 */
	private String retypePwd = "";
	/** 암호화된 변경 비밀번호 */
	private String encNewPw = "";
	/** 비밀번호 변경일 */
	private Date lastPwChangeDt = null;
	/** 로그인 실패 횟수 */
	private int loginFailedCnt = -1;
	/** 분기 1회 비밀번호 변경 대상 상태 플래그 (Y: 대상, N: 허용) */
	private String changePwTarget = "N";
	/** 최종 로그인 일시 */
	private Date lastestLoginDt = null;

	public AdminLoginVO() {
		// default
	}

	public AdminLoginVO(String userId) {
		setAdminId(userId);
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public AdminType getAdminType() {
		return adminType;
	}

	public void setAdminType(AdminType adminType) {
		this.adminType = adminType;
	}

	public String getAdminTypeCode() {
		return AdminTypeCode;
	}

	public void setAdminTypeCode(String adminTypeCode) {
		AdminTypeCode = adminTypeCode;
	}

	public String getMids() {
		return mids;
	}

	public void setMids(String mids) {
		this.mids = mids;
	}

	public List<String> getMidList() {
		return midList;
	}

	public void setMidList(List<String> midList) {
		this.midList = midList;
	}

	public String getPtIdxs() {
		return ptIdxs;
	}

	public void setPtIdxs(String ptIdxs) {
		this.ptIdxs = ptIdxs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String[] getAdminGroupCode() {

		int _length = 0;
		if (adminGroupCode != null) {
			_length = adminGroupCode.length;
		}
		String[] _from = adminGroupCode;
		String[] _to = new String[_length];
		for (int i = 0; i < _length; i++) {
			_to[i] = _from[i];
		}

		return _to;
	}

	public void setAdminGroupCode(String[] adminGroupCode) {
		int _length = 0;
		if (adminGroupCode != null) {
			_length = adminGroupCode.length;
		}
		String[] _from = adminGroupCode;
		String[] _to = new String[_length];
		for (int i = 0; i < _from.length; i++) {
			_to[i] = _from[i];
		}
		this.adminGroupCode = _to;
	}

	public String getAdminLevelCode() {
		return adminLevelCode;
	}

	public void setAdminLevelCode(String adminLevelCode) {
		this.adminLevelCode = adminLevelCode;
	}

	public String getAdminLevelDisp() {
		return adminLevelDisp;
	}

	public void setAdminLevelDisp(String adminLevelDisp) {
		this.adminLevelDisp = adminLevelDisp;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminRealName() {
		return adminRealName;
	}

	public void setAdminRealName(String adminRealName) {
		this.adminRealName = adminRealName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getOverDate() {
		return overDate;
	}

	public void setOverDate(Date overDate) {
		this.overDate = overDate;
	}

	public boolean isbUse() {
		return bUse;
	}

	public void setbUse(boolean bUse) {
		this.bUse = bUse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
		setEncPasswd(StringUtil.isNotBlank(pwd) ? SecurityUtil.encrypt(pwd) : "");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getTmIdx() {
		return tmIdx;
	}

	public void setTmIdx(String tmIdx) {
		this.tmIdx = tmIdx;
	}

	public String getChIdx() {
		return chIdx;
	}

	public void setChIdx(String chIdx) {
		this.chIdx = chIdx;
	}

	public String getAdminAuth() {
		return adminAuth;
	}

	public void setAdminAuth(String adminAuth) {
		this.adminAuth = adminAuth;
	}

	public String getBbsAuth() {
		return bbsAuth;
	}

	public void setBbsAuth(String bbsAuth) {
		this.bbsAuth = bbsAuth;
	}

	public String getOrgAuth() {
		return orgAuth;
	}

	public void setOrgAuth(String orgAuth) {
		this.orgAuth = orgAuth;
	}

	public String getStatAuth() {
		return statAuth;
	}

	public void setStatAuth(String statAuth) {
		this.statAuth = statAuth;
	}

	public String getPtnm() {
		return ptnm;
	}

	public void setPtnm(String ptnm) {
		this.ptnm = ptnm;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getCont1() {
		return cont1;
	}

	public void setCont1(String cont1) {
		this.cont1 = cont1;
	}

	public String getCont2() {
		return cont2;
	}

	public void setCont2(String cont2) {
		this.cont2 = cont2;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<SysMenuAuthVO> getAdminMenuAuths() {
		return adminMenuAuths;
	}

	public void setAdminMenuAuths(List<SysMenuAuthVO> adminMenuAuths) {
		List<SysMenuAuthVO> _from = adminMenuAuths;
		List<SysMenuAuthVO> _to = new ArrayList<SysMenuAuthVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}

		this.adminMenuAuths = _to;
	}

	public List<SysSiteAuthVO> getAdminSiteAuths() {
		return adminSiteAuths;
	}

	public void setAdminSiteAuths(List<SysSiteAuthVO> adminSiteAuths) {
		List<SysSiteAuthVO> _from = adminSiteAuths;
		List<SysSiteAuthVO> _to = new ArrayList<SysSiteAuthVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.adminSiteAuths = _to;
	}

	public int getAdminAccessLevelCode() {
		return adminAccessLevelCode;
	}

	public void setAdminAccessLevelCode(int adminAccessLevelCode) {
		this.adminAccessLevelCode = adminAccessLevelCode;
	}

	public String getExplan() {
		return explan;
	}

	public void setExplan(String explan) {
		this.explan = explan;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setCmsLoginKey(String cmsLoginKey) {
		this.cmsLoginKey = cmsLoginKey;
	}

	public String getCmsLoginKey() {
		return cmsLoginKey;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public void setCntAuthHead2(int cntAuthHead2) {
		this.cntAuthHead2 = cntAuthHead2;
	}

	public int getCntAuthHead2() {
		return cntAuthHead2;
	}

	public void setCntAuthHead3(int cntAuthHead3) {
		this.cntAuthHead3 = cntAuthHead3;
	}

	public int getCntAuthHead3() {
		return cntAuthHead3;
	}

	public void setUrlAuthHead2(String urlAuthHead2) {
		this.urlAuthHead2 = urlAuthHead2;
	}

	public String getUrlAuthHead2() {
		return urlAuthHead2;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getSearchAdminTypeCode() {
		return searchAdminTypeCode;
	}

	public void setSearchAdminTypeCode(String searchAdminTypeCode) {
		this.searchAdminTypeCode = searchAdminTypeCode;
	}

	public String getEncPasswd() {
		return encPasswd;
	}

	public void setEncPasswd(String encPasswd) {
		this.encPasswd = encPasswd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
		if (StringUtil.isNotBlank(newPwd)) {
			setEncNewPw(SecurityUtil.encrypt(newPwd));
		}
	}

	public String getRetypePwd() {
		return retypePwd;
	}

	public void setRetypePwd(String retypePwd) {
		this.retypePwd = retypePwd;
	}

	public String getEncNewPw() {
		return encNewPw;
	}

	public void setEncNewPw(String encNewPw) {
		this.encNewPw = encNewPw;
	}

	public Date getLastPwChangeDt() {
		return lastPwChangeDt;
	}

	public void setLastPwChangeDt(Date lastPwChangeDt) {
		this.lastPwChangeDt = lastPwChangeDt;
	}

	public int getLoginFailedCnt() {
		return loginFailedCnt;
	}

	public void setLoginFailedCnt(int loginFailedCnt) {
		this.loginFailedCnt = loginFailedCnt;
	}

	public String getChangePwTarget() {
		return changePwTarget;
	}

	public void setChangePwTarget(String changePwTarget) {
		this.changePwTarget = changePwTarget;
	}

	public Date getLastestLoginDt() {
		return lastestLoginDt;
	}

	public void setLastestLoginDt(Date lastestLoginDt) {
		this.lastestLoginDt = lastestLoginDt;
	}

	public Boolean isSameAs(AdminLoginVO entity) {
		return entity != null && getAdminId().equals(entity.getAdminId()) && getEncPasswd().equals(entity.getPwd());
	}

}
