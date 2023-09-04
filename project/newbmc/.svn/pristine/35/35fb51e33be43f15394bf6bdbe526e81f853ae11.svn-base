package egovframework.portal.security.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.yhdatabase.hms.linkage.HMS;

import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.service.SysMemberAuthMngService;
import egovframework.portal.sys.sysAuth.service.SysMemberService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.SysMenuAuthVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.portal.util.LoginManager;
import egovframework.portal.util.StringUtil;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	protected SysMemberAuthMngService sysMemberAuthMngService;
	@Autowired
	protected SysMemberService sysMemberService;

	private final Logger LOGGER = LogManager.getLogger();

	/**
	 * 에러 페이지로 이동 기능
	 *
	 * @param response
	 * @param message
	 * @throws IOException
	 */
	private void moveExceptiinPage(HttpServletResponse response, String message, int adminAccessLevelCode)
		throws IOException {
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

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject jSONObject = new JSONObject();

		AdminLoginVO adminLoginVO = (AdminLoginVO) authentication.getPrincipal();

		try {
			// 관리자 세션 저장
//			adminLoginVO.setAdminId(adminLoginVO.getId());
			adminLoginVO.setAdminName(adminLoginVO.getName());
			adminLoginVO.setDeptName(adminLoginVO.getName());

			if (adminLoginVO.getAdminAccessLevelCode() != AuthType.SUPER.getCode()) {
				List<SysMenuAuthVO> sysMenuAuths = sysMemberAuthMngService.getSysMenusLoginAuthList(adminLoginVO);

				if (sysMenuAuths.size() <= 0) {
					this.moveExceptiinPage(response, "죄송합니다. 접근가능한 메뉴가 없습니다.", AuthType.GENERAL.getCode());
					return;
				}

				List<SysSiteAuthVO> sysSiteAuths = sysMemberAuthMngService.getSysSitesLoginAuthList(adminLoginVO);
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

			HttpSession sessionInfo = request.getSession(false);
			if (sessionInfo != null) {
				sessionInfo.invalidate();
			}

			sessionInfo = request.getSession(true);

			LoginManager loginManager = LoginManager.getInstance();
			loginManager.setAdminSession(sessionInfo, adminLoginVO);

			/* ================================= y-SmartCMS 로그인 연계 처리 ================================= */

			// 20160902 J.Ryeon Lee 관리자단은 스프링 시큐리티를 이용하지 않으므로 일단 주석 처리. cms lib version이 맞지 않아서 현재는 에러 남.

//			String adminLevel = String.valueOf(adminLoginVO.getAdminAccessLevelCode());
//			String cmsLoginKey = "";
//			if (!adminLevel.equals("10")) {
//				adminLevel = "1";
//				List<SysSiteAuthVO> sysSiteAuths = sysMemberAuthMngService.getSysSitesLoginAuthList(adminLoginVO);
//				String siteCodes = "";
//				for (int i = 0; i < sysSiteAuths.size(); i++) {
//					if (i == 0) {
//						siteCodes += sysSiteAuths.get(i).getSiteCode();
//					} else {
//						siteCodes += "," + sysSiteAuths.get(i).getSiteCode();
//					}
//				}
//				cmsLoginKey = SmartCMS.setLogin(adminLoginVO.getAdminId(), adminLoginVO.getAdminName(), adminLevel, siteCodes, EgovProperties.getProperty("ySmartCMS.url"));
//			} else {
//				cmsLoginKey = SmartCMS.setLogin(adminLoginVO.getAdminId(), adminLoginVO.getAdminName(), adminLevel, EgovProperties.getProperty("ySmartCMS.url"));
//			}
//			sessionInfo.setAttribute("cmsLoginKey", cmsLoginKey);

			/* ================================= HMS 로그인 연계처리 ================================= */

			String hmsLoginKey = HMS.setHmsInfo(adminLoginVO.getAdminId(), adminLoginVO.getAdminName(), adminLoginVO.getDeptName(), 721);
			if (StringUtil.isBlank(hmsLoginKey) || hmsLoginKey.equals("error")) {
				// TODO HMS 로그인 실패
			} else {
				sessionInfo.setAttribute("hmsLoginKey", hmsLoginKey);
			}

			/* ======================================================================================= */

			jSONObject.put("flag", "success");
			jSONObject.put("message", adminLoginVO.getName() + "님 환영합니다.");

			out.println(jSONObject.toString());
		} catch (Exception ex) {
			try {
				jSONObject.put("flag", "fail");
				jSONObject.put("message", "로그인 중 오류가 발생하였습니다.");
			} catch (JSONException e) {
				LOGGER.error(">> failed to parse json", e);
			}

			out.println(jSONObject.toString());
		}
	}

}