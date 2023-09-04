package egovframework.portal.sys.sysAuth.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdatabase.hms.linkage.HMS;
import com.yhdatabase.ysmartcmslinkage.main.SmartCMS;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.common.Constant;
import egovframework.portal.main.BoardCode;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.stateMng.service.StateSysService;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.log.service.LoggingService;
import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.service.AdminMngService;
import egovframework.portal.sys.sysAuth.service.IntroService;
import egovframework.portal.sys.sysAuth.service.SysMemberAuthMngService;
import egovframework.portal.sys.sysAuth.service.SysMemberService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.PwChangeVO;
import egovframework.portal.sys.sysAuth.vo.SessionChangeVO;
import egovframework.portal.sys.sysAuth.vo.SysMenuAuthVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.CommonUtil;
import egovframework.portal.util.LoginManager;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------	------------	---------------------------
 * 2014.11.17						최초 생성
 * 2017.03.03		J.Ryeon Lee		버전업 코드 병합 처리
 * 2017.07.17		J.Ryeon Lee		리팩키징
 * </pre>
 *
 * @author ?
 * @sicne 2014.11.17
 */

@Controller
public class SysMemberController {

//	private static final String SSO_LOGIN_KEY = "Seol_Login"; // SSO 로그인에서 넘겨주는 key 파라미터
//	private static final Logger logger = Logger.getLogger(SysMemberController.class);

	@Autowired
	protected MenuSysService menuService;
	@Autowired
	protected SysMemberService sysMemberService;
	@Autowired
	protected SysMemberAuthMngService sysMemberAuthMngService;
	@Autowired
	protected StateSysService stateService;
	@Autowired
	protected MenuMngService menuMngService;
	@Autowired
	protected IntroService introService;
	@Autowired
	protected AdminMngService adminMngService;
	@Autowired
	protected LoggingService loggingService;
	@Autowired
	protected BbsService bbsService;

	private final Logger LOGGER = LogManager.getLogger();
	private final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	/** 관리자 계정 목록 */
	@RequestMapping("/sys/totalAdminMng/sysMemberMng/list.do")
	public String list(@ModelAttribute("searchVO") AdminLoginVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		int totalCnt = sysMemberService.getSysMemberListCnt(searchVO);

		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("resultList", sysMemberService.getSysMemberList(searchVO));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		return "/sys/totalAdminMng/sysMemberMng/list";
	}

	/** 관리자 계정 등록 페이지 */
	@RequestMapping("/sys/totalAdminMng/sysMemberMng/writePage.do")
	public String writePage(@ModelAttribute("searchVO") AdminLoginVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		model.addAttribute("deptAllList", sysMemberService.getDeptAllList());
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/sysMemberMng/write";
	}

	/** 관리자 계정 등록 처리 */
	@ResponseBody
	@RequestMapping("/sys/totalAdminMng/sysMemberMng/writeProc.do")
	public byte[] writeProc(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("searchVO") @Valid AdminLoginVO insertVO, BindingResult result) throws Exception {
		response.setContentType("text/plain; charset=utf-8");
		JSONObject rtn = new JSONObject() //
			.put("flag", "false") //
			.put("message", "관리자 정보 등록 중 오류가 발생했습니다.");

		if (!result.hasErrors()) {
			try {
				sysMemberService.insertSysMember(insertVO);
				rtn.put("flag", "success");
				rtn.put("message", "관리자 정보를 정상적으로 등록했습니다.");
			} catch (Exception e) {
				LOGGER.error(">> failed sys.member.insertProc", e);
			}
		}

		return rtn.toString().getBytes("UTF-8");
	}

	/** 관리자 계정 수정 페이지 */
	@RequestMapping("/sys/totalAdminMng/sysMemberMng/modifyPage.do")
	public String modifyPage(@ModelAttribute("searchVO") AdminLoginVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		BeanUtil.copy(sysMemberService.getSysMember(searchVO), searchVO);
		model.addAttribute("deptAllList", sysMemberService.getDeptAllList());
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/sysMemberMng/write";
	}

	/** 관리자 계정 수정 처리 */
	@ResponseBody
	@RequestMapping("/sys/totalAdminMng/sysMemberMng/modifyProc.do")
	public byte[] modifyProc(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("searchVO") @Valid AdminLoginVO modifyVO, BindingResult result) throws Exception {
		response.setContentType("text/plain; charset=utf-8");
		JSONObject rtn = new JSONObject() //
			.put("flag", "false") //
			.put("message", "관리자 정보 수정 중 오류가 발생했습니다.");

		if (!result.hasErrors()) {
			try {
				sysMemberService.modifySysMember(modifyVO);

				rtn.put("flag", "success");
				rtn.put("message", "관리자 정보를 정상적으로 수정했습니다.");
			} catch (Exception e) {
				LOGGER.error(">> failed sys.member.modifyProc", e);
			}
		}

		return rtn.toString().getBytes("UTF-8");
	}

	/** 관리자 계정 삭제 처리 */
	@ResponseBody
	@RequestMapping("/sys/totalAdminMng/sysMemberMng/deleteProc.do")
	public byte[] deleteSysMemberProc(@ModelAttribute("searchVO") AdminLoginVO deleteVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		response.setContentType("text/plain; charset=utf-8");
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		try {
			sysMemberService.deleteSysMember(deleteVO);
			rtn.put("success", Boolean.TRUE);
		} catch (DataAccessException e) {
			LOGGER.error(">> failed sys.member.deleteProc", e);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	/** 관리자 로그인 페이지 */
	@RequestMapping("/sys/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);
		if (adminLoginVO != null && adminLoginVO.getAdminAccessLevelCode() != AuthType.EXTERNAL.getCode()) { // 외부 관리자 intro 이용 방지
			return "redirect:/sys/stateMng/list.do?mId=0108000000";
		}

		return "/admin/login";
	}

	/** 관리자 로그인 처리 */
	@RequestMapping("/sys/superLoginProc.do")
	public void superLoginProc(@RequestParam("adminId") String adminId, @RequestParam("pwd") String pwd, HttpServletRequest request, HttpServletResponse response, ModelMap model, AdminLoginVO inputVO) throws IOException, JSONException {
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jSONObject = new JSONObject();

		try {
			AdminLoginVO adminLoginVO = sysMemberService.selectSysMemberDataDetail(inputVO);
			if (adminLoginVO == null) {
				jSONObject.put("flag", "fail");
				jSONObject.put("message", "등록되지 않은 ID입니다.");
				out.println(jSONObject.toString());
				return;
			}

			if (Constant.FAILED_LOGIN_LIMIT <= adminLoginVO.getLoginFailedCnt()) { // 20170407 J.Ryeon Lee ADD 로그인 제한 횟수 검증
				adminMngService.increaseFailedCnt(adminLoginVO);
				jSONObject.put("flag", "fail");
				jSONObject.put("message", "비밀번호 " + Constant.FAILED_LOGIN_LIMIT + "회 이상 오입력으로 로그인이 제한된 상태입니다. 관리자에게 문의해주세요.");
				out.println(jSONObject.toString());
				return;
			}

			if (!inputVO.getEncPasswd().equals(adminLoginVO.getPwd())) {
				String message = "잘못된 비밀번호입니다. 5회 이상 오입력시 로그인이 제한됩니다.";
				try { // 20170407 J.Ryeon Lee ADD 로그인 실패 cnt 증가
					adminMngService.increaseFailedCnt(adminLoginVO);
					if (Constant.FAILED_LOGIN_LIMIT - 1 == adminLoginVO.getLoginFailedCnt()) {
						message = "비밀번호를 " + Constant.FAILED_LOGIN_LIMIT + "회 이상 잘못 입력하셨습니다. 로그인이 제한됩니다.";
					}
				} catch (Exception e) {
					LOGGER.error(">> failed sys.increase.failed.cnt", e);
				}

				jSONObject.put("flag", "fail");
				jSONObject.put("message", message);
				out.println(jSONObject.toString());
				return;
			}

			adminMngService.resetFailedCnt(adminLoginVO); // 20170407 J.Ryeon Lee ADD 로그인 실패 cnt 초기화
			adminMngService.updateLastestLoginDt(adminLoginVO);

			adminLoginVO.setHostIp(SessionUtil.getRemoteAddr(request));
			adminLoginVO.setAdminId(adminLoginVO.getId());
			adminLoginVO.setAdminName(adminLoginVO.getName());
			adminLoginVO.setDeptName(StringUtil.isBlank(adminLoginVO.getDeptName()) ? adminLoginVO.getName() : adminLoginVO.getDeptName());

			// 관리자 세션 저장
			adminLoginVO.setAdminId(adminLoginVO.getId());
			adminLoginVO.setAdminName(adminLoginVO.getName());
			if (StringUtil.isBlank(adminLoginVO.getDeptName())) {
				adminLoginVO.setDeptName(adminLoginVO.getName());
			}

			if (adminLoginVO.getAdminAccessLevelCode() != AuthType.SUPER.getCode()) {
				List<SysMenuAuthVO> sysMenuAuths = sysMemberAuthMngService.getSysMenusLoginAuthList(adminLoginVO);

				if (sysMenuAuths.size() <= 0) {
					this.moveExceptiinPage(response, "죄송합니다. 접근 가능한 메뉴가 없습니다.", AuthType.GENERAL.getCode());
					return;
				}

//				List<SysSiteAuthVO> sysSiteAuths = sysMemberAuthMngService.getSysSitesLoginAuthList(adminLoginVO); // 20161018 J.Ryeon Lee SSO용으로 검색 조건이 바뀌었으므로 메소드 분기
				List<SysSiteAuthVO> sysSiteAuths = sysMemberAuthMngService.getSysSitesLoginAuthListForSubAdmin(adminLoginVO);
				adminLoginVO.setAdminMenuAuths(sysMenuAuths);
				adminLoginVO.setAdminSiteAuths(sysSiteAuths);

				SysMenuAuthVO searchVO = new SysMenuAuthVO();
				searchVO.setDeptId(adminLoginVO.getDeptId());
				searchVO.setmId("03");
				int cntAuth3 = sysMemberAuthMngService.cntCheckAuthMenuHead(searchVO);
				searchVO.setmId("02");
				int cntAuth2 = sysMemberAuthMngService.cntCheckAuthMenuHead(searchVO);
				SysMenuAuthVO goMenu = sysMemberAuthMngService.getAuthMenuHeadUrl(searchVO);
				adminLoginVO.setCntAuthHead2(cntAuth2);
				adminLoginVO.setCntAuthHead3(cntAuth3);
				if (goMenu != null) {
					adminLoginVO.setUrlAuthHead2(goMenu.getProgramUrl() + "?mId=" + goMenu.getmId());
				}
			} else {
				adminLoginVO.setCntAuthHead2(1);
				adminLoginVO.setCntAuthHead3(1);
				adminLoginVO.setUrlAuthHead2("/sys/totalAdminMng/sysMemberMng/list.do?mId=0200000000");

			}

			LoginManager.login(request, adminLoginVO);

			if (EgovProperties.getProperty(Constant.USE_CMS_YN).equals("Y")) {

				/* ================================= y-SmartCMS 로그인 연계 처리 ================================= */

				String adminLevel = String.valueOf(adminLoginVO.getAdminAccessLevelCode());
				String cmsLoginKey = "";
				if (!adminLevel.equals("10")) {
					adminLevel = "1";
					List<SysSiteAuthVO> sysSiteAuths = sysMemberAuthMngService.getSysSitesLoginAuthList(adminLoginVO);
					String siteCodes = "";
					for (int i = 0; i < sysSiteAuths.size(); i++) {
						if (i == 0) {
							siteCodes += sysSiteAuths.get(i).getSiteCode();
						} else {
							siteCodes += "," + sysSiteAuths.get(i).getSiteCode();
						}
					}
					cmsLoginKey = SmartCMS.setLogin(adminLoginVO.getAdminId(), adminLoginVO.getAdminName(), adminLevel, siteCodes);
				} else {
					cmsLoginKey = SmartCMS.setLogin(adminLoginVO.getAdminId(), adminLoginVO.getAdminName(), adminLevel);
				}

				request.getSession().setAttribute("cmsLoginKey", cmsLoginKey);

				/* ================================= HMS 로그인 연계처리 ================================= */

				String hmsLoginKey = HMS.setHmsInfo( //
					adminLoginVO.getAdminId(), adminLoginVO.getAdminId(), adminLoginVO.getDeptName(), //
					Integer.parseInt(EgovProperties.getProperty(Constant.HMS_CITY_CODE)) //
				);
//				String hmsLoginKey = "error";
				if (StringUtil.isBlank(hmsLoginKey) || hmsLoginKey.equals("error")) {
					// TODO HMS 로그인 실패
				} else {
					request.getSession().setAttribute("hmsLoginKey", hmsLoginKey);
				}

				/* ======================================================================================= */
			}
			
			PwChangeVO period = new PwChangeVO();
			
			if (sysMemberService.getPwPeriodInfo(period).size() > 0) {
				period = sysMemberService.getPwPeriodInfo(period).get(0);
				Calendar cal = Calendar.getInstance();
				cal.setTime(adminLoginVO.getLastPwChangeDt());
				cal.add(Calendar.MONTH, period.getPeriod());
				if (FORMATTER.format(cal.getTime()).compareTo(FORMATTER.format(new Date())) <= 0) {
					String message = "비밀번호를 변경하신지 " + period.getPeriod() + "개월이 지났습니다. 마이페이지에서 비밀번호를 변경하여주세요.";
					jSONObject.put("flag", "mypage");
					jSONObject.put("message", message);
					out.println(jSONObject.toString());
					return;
				};
			}
			
			
			jSONObject.put("flag", "success");
			jSONObject.put("message", adminLoginVO.getName() + "님 환영합니다.");	
			jSONObject.put("link", "http://" + request.getServerName() + ":" + request.getServerPort() + "/sys/");

			out.println(jSONObject.toString());
		} catch (Exception ex) {
			LOGGER.error(">> failed to superlogin", ex);

			jSONObject.put("flag", "fail");
			jSONObject.put("message", "로그인 중 오류가 발생하였습니다.");
			out.println(jSONObject.toString());
		}

	}

	/** SSO 로그인 */
	@RequestMapping("/AdminLogin/login.do")
	public String ssoLoginProc(@ModelAttribute("searchVO") AdminLoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {
		response.setContentType("text/plain; charset=utf-8");

		//SSO정책변경으로 세션으로 추출 kjspro
		//String key = ServletRequestUtils.getStringParameter(request, "userid", "");
		String key = "";

		key = TUtil.nullTobaseStr((String)session.getAttribute("userid"),"");
		//System.out.println(">> AdminLogin/login.do 1 : " + key);
		if (StringUtil.isBlank(key)) {
			key = ServletRequestUtils.getStringParameter(request, "userid", "");
			//System.out.println(">> AdminLogin/login.do 2 : " + key);
		}

		//loginVO.setId(key.toUpperCase());
		loginVO.setId(key);
		int chkId = sysMemberService.chkSysMemberSsoId(loginVO);

		try {

			// 권한처리 전에 우선 전체오픈
			if (chkId == 0) { // || !SSO_LOGIN_KEY.equals(key)
				this.moveExceptiinPage(response, "죄송합니다. 로그인 권한이 없습니다.", AuthType.GENERAL.getCode());
				return null;
			} else {
				AdminLoginVO adminLoginVO = sysMemberService.getSsoUser(loginVO);

				// SSO로 넘어올경우 개인권한 조회 위한 아이디 매핑 처리 20160513 손영식
				adminLoginVO.setUsrId(loginVO.getId());

				List<SysMenuAuthVO> sysMenuAuths = sysMemberAuthMngService.getSysMenusLoginAuthList(adminLoginVO);

				if (sysMenuAuths.size() <= 0) {
					this.moveExceptiinPage(response, "죄송합니다. 접근가능한 메뉴가 없습니다.", AuthType.GENERAL.getCode());
					return null;
				}

				SysMenuAuthVO searchVO = new SysMenuAuthVO();
				searchVO.setDeptId(adminLoginVO.getDeptId());
				searchVO.setmId("03");
				int cntAuth3 = sysMemberAuthMngService.cntCheckAuthMenuHead(searchVO);
				searchVO.setmId("02");
				int cntAuth2 = sysMemberAuthMngService.cntCheckAuthMenuHead(searchVO);
				SysMenuAuthVO goMenu = sysMemberAuthMngService.getAuthMenuHeadUrl(searchVO);
				adminLoginVO.setCntAuthHead2(cntAuth2);
				adminLoginVO.setCntAuthHead3(cntAuth3);
				if (goMenu != null) {
					adminLoginVO.setUrlAuthHead2(goMenu.getProgramUrl() + "?mId=" + goMenu.getmId());
				}

				List<SysSiteAuthVO> sysSiteAuths = sysMemberAuthMngService.getSysSitesLoginAuthList(adminLoginVO);
				adminLoginVO.setAdminMenuAuths(sysMenuAuths);
				adminLoginVO.setAdminSiteAuths(sysSiteAuths);
//				adminLoginVO.setAdminAccessLevelCode(AuthType.GENERAL.getCode()); // 일반 부서 관리자 레벨코드는 5
				adminLoginVO.setAdminAccessLevelCode(AuthType.SSO.getCode()); // 20151222 J.Ryeon Lee
				adminLoginVO.setHostIp(SessionUtil.getRemoteAddr(request));
				adminLoginVO.setAdminName(adminLoginVO.getDeptName());

				LoginManager.login(request, adminLoginVO);

				if (CommonUtil.checkServerMode()) {
					/* ================================= LOGGING ================================= */

					// TODO total access log, interceptor pattern이 실서버에서 문제를 발생해 controller로 이식 // 추후 디버깅 필요
					AdminLoginVO admin = (AdminLoginVO) SessionUtil.getAdminSessionObj(request);
					LoggingVO params = new LoggingVO();
					params.setMid(null);
					params.setFull_url(request.getRequestURL().toString());
					params.setHost_ip(SessionUtil.getRemoteAddr(request));
					params.setAdmin_id(admin.getAdminId());
					params.setAdmin_name(admin != null ? (StringUtil.isBlank(admin.getName()) ? admin.getAdminName() : admin.getName()) : null);
					params.setDept_name(admin != null ? admin.getDeptName() : null);
					params.setDept_id(admin != null ? admin.getDeptId() : null);

					loggingService.logging(params);

					/* ================================= CMS 로그인 연계처리 ================================= */
					HttpSession sessionInfo = request.getSession();
					String adminLevel = String.valueOf(adminLoginVO.getAdminAccessLevelCode());
					if (!adminLevel.equals("10")) {
						String siteCodes = "";
						String cmsLoginKey = SmartCMS.setLogin(adminLoginVO.getAdminId(), adminLoginVO.getAdminName(), adminLevel, siteCodes);
						sessionInfo.setAttribute("cmsLoginKey", cmsLoginKey);
					} else {
						String cmsLoginKey = SmartCMS.setLogin(adminLoginVO.getAdminId(), adminLoginVO.getAdminName(), adminLevel);
						sessionInfo.setAttribute("cmsLoginKey", cmsLoginKey);
					}

					/* ================================= HMS 로그인 연계처리 ================================= */

					String hmsLoginKey = HMS.setHmsInfo( //
						adminLoginVO.getAdminId(), adminLoginVO.getAdminId(), adminLoginVO.getDeptName(), //
						Integer.parseInt(EgovProperties.getProperty(Constant.HMS_CITY_CODE)) //
					);
//					String hmsLoginKey = "error";
					if (StringUtil.isBlank(hmsLoginKey) || hmsLoginKey.equals("error")) {
						// TODO HMS 로그인 실패
					} else {
						sessionInfo.setAttribute("hmsLoginKey", hmsLoginKey);
					}
				}

				return "redirect:/sys/superMng/bbs/configMng/list.do?mId=0103010000";
			}

		} catch (Exception ex) {
//			logger.error(ex.toString(), ex);
			this.moveExceptiinPage(response, "SSO 로그인 중 오류가 발생했습니다.", AuthType.GENERAL.getCode());
		}

		return null;
	}

	/** 관리자 로그아웃 */
	@RequestMapping("/sys/logout.do")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);
		LoginManager.logout(request, adminLoginVO);
		moveMainPage(response, null, adminLoginVO.getAdminAccessLevelCode());
	}

	/** 에러 페이지로 이동 기능 */
	private void moveExceptiinPage(HttpServletResponse response, String message, int adminAccessLevelCode) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		StringBuilder script = new StringBuilder();
		script.append("<script>")
			.append("alert('" + message + "');");
		if (adminAccessLevelCode == 10) {
			script.append("location.href='/sys/login.do';");
		} else {
			script.append("history.back(-1);");
		}
		script.append("</script>");

		out.println(script.toString());
	}

	/** Exception 발생시 메인페이지 이동 */
	private void moveMainPage(HttpServletResponse response, String message, int adminAccessLevelCode) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		StringBuilder script = new StringBuilder();
		script.append("<script>");
		if (StringUtil.isNotBlank(message)) {
			script.append("alert('" + message + "');");
		}
		if (adminAccessLevelCode == 10) {
			script.append("location.href='/sys/login.do';");
		} else {
			script.append("location.href='/';");
		}
		script.append("</script>");

		out.println(script.toString());
	}

	@RequestMapping("/sys/intro/add/favorite/menus.do")
	public String sysMenuListPop(@ModelAttribute("searchVO") SysMenuAuthVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, null, request));
		return "/sys/intro/menus";
	}

	/** 관리자 인트로 */
	@RequestMapping("/sys/intro.do")
	public String moveSysIntro(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		// 방문자통계
		int kind = ServletRequestUtils.getIntParameter(request, "kind", 0); // 통계 탭분류
		String siteCode = ServletRequestUtils.getStringParameter(request, "siteCode", ""); // 사이트분류
		String year = ServletRequestUtils.getStringParameter(request, "year", TUtil.getToday("yyyy"));
		String month = ServletRequestUtils.getStringParameter(request, "month", TUtil.getToday(""));
		int sType = ServletRequestUtils.getIntParameter(request, "sType", 1); // 기간분류

		StateSysVO stateVO = new StateSysVO();

		stateVO.setSiteCode(siteCode);
		stateVO.setYear(year);
		stateVO.setMonth(month);
		stateVO.setsType(sType);

		// 데이터 셋팅
		List<MenusMngVO> listSite = menuMngService.getMenusMngListAll(request); // 사이트코드 리스트 추출- PT_STATE_TODAY_SITE
		stateVO.setSearchTable("state_today_site");
		List<StateSysVO> results = null; // 통계 출력컨텐츠

		String searchQuery = "";
		if (!"".equals(siteCode)) {
			searchQuery = " log_sitecode='" + siteCode + "'";
			stateVO.setSearchQuery(searchQuery);
		} else {
			searchQuery = " log_sitecode IS NOT NULL ";
			stateVO.setSearchQuery(searchQuery);
		}

		switch (kind) {
			case 0:
			case 1:
				switch (sType) {
					case 1:
						results = stateService.selectStatsYearSM(stateVO);
						break;
					case 2:
						results = stateService.selectStatsMonthSM(stateVO);
						break;
					case 3:
						results = stateService.selectStatsPeriodSM(stateVO);
						break;
					default:
						break;
				}
				break;
			case 2:
				results = stateService.selectStatsReferer(stateVO);
				break;
			case 3:
				results = stateService.selectStatsAgent(stateVO);
				break;
			default:
				break;
		}

		int statsCountMax = 0;
		for (StateSysVO i : results) {
			statsCountMax += i.getStatsCount();
		}
		stateVO.setStatsCountMax(statsCountMax);
		model.addAttribute("stateResults", results);
		model.addAttribute("stateVO", stateVO);
		model.addAttribute("year", year);
		model.addAttribute("listSite", listSite);

		model.addAttribute("isIntro", true);

		/* ========================================= 20160128 J.Ryeon Lee ADD ========================================= */

		// 게시글 관리
		model.addAttribute("recentBoardList", introService.getRecentBoardList(8));
		// 사이트 바로가기
		model.addAttribute("siteList", menuMngService.getMenusMngListAll(request));
		// 공지사항
		BbsMngVO bbsVO = new BbsMngVO();
		bbsVO.setFirstIndex(1);
		bbsVO.setLastIndex(8);
		bbsVO.setPtIdx("0");
		model.addAttribute("portalNotiList", bbsService.getMainBbsList(bbsVO));
		//System.out.println("portalNotiList:" + introService.getBbsList(BoardCode.PORTAL_NOTICE, 6));
		// 시스템 공지사항
		model.addAttribute("sysNotiList", introService.getBbsList(BoardCode.SYSTEM_NOTICE, 6));
		// 오늘 날짜
		model.addAttribute("today_info", FORMATTER.format(new Date()));
		// CMS site list
		model.addAttribute("ySmartCMS.url", EgovProperties.getProperty("ySmartCMS.url"));
		List<SessionChangeVO> sessionList = sysMemberService.getSiPeriodInfo(new SessionChangeVO());
		
		if (sessionList.size() > 0) {
			model.addAttribute("sessionTime", sessionList.get(0).getPeriod());
		}
		
		return "/admin/intro";
	}
	
	/** 비밀번호 변경 주기 */
	@RequestMapping("/sys/totalAdminMng/period/list.do")
	public String pwchange(@ModelAttribute("searchVO") PwChangeVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		List<PwChangeVO> list = sysMemberService.getPwPeriodInfo(searchVO);
		model.addAttribute("list", list);
		
		return "/sys/totalAdminMng/period/list";
	}
	
	@RequestMapping("/sys/totalAdminMng/period/update.do")
	public String pwchangeUpdate(@ModelAttribute("searchVO") PwChangeVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		sysMemberService.updatePwPeriod(searchVO);
		return "redirect:/sys/totalAdminMng/period/list.do?mId=" + mId;
	}
	
	/** 세션유지시간 변경 주기 */
	@RequestMapping("/sys/totalAdminMng/period/sessionList.do")
	public String sessionChange(@ModelAttribute("searchVO") SessionChangeVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		List<SessionChangeVO> list = sysMemberService.getSiPeriodInfo(searchVO);
		model.addAttribute("list", list);
		
		return "/sys/totalAdminMng/period/sessionList";
	}
	
	@RequestMapping("/sys/totalAdminMng/period/sessionUpdate.do")
	public String pwchangeUpdate(@ModelAttribute("searchVO") SessionChangeVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		sysMemberService.updateSiPeriod(searchVO);
		return "redirect:/sys/totalAdminMng/period/sessionList.do?mId=" + mId;
	}
}
