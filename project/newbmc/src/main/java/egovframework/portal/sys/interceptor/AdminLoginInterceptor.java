package egovframework.portal.sys.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.sysAuth.service.ExternalAdminLoginService;
import egovframework.portal.sys.sysAuth.AdminType;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.service.SiteAccessMngService;
import egovframework.portal.sys.sysAuth.service.SysMemberService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.SiteAccessVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.TUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 관리자 인증 여부 검증 INTERCEPTOR
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * ?				?					최초 생성 및 코딩
 * 2016.09.23		J.Ryeon Lee			외부 관리자 인증 여부 검증 로직 추가
 * </pre>
 *
 * @author ?
 * @since ?
 */
@Service
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	@Autowired
	protected SiteAccessMngService siteAccessMngService;
	@Autowired
	protected SysMemberService sysMemberService;
	@Autowired
	protected ExternalAdminLoginService externalAdminLoginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//20.04.24 웹방화벽 설치로 X-Forwarded-For로 변경
		String remoteIp =  SessionUtil.getRemoteAddr(request);
		String siteAccessUseYn = propertyService.getString("access.sysAccessAllowUseYn");
		String requestURI = request.getRequestURI();
		Boolean isAllow = false;
		AdminLoginVO admin = SessionUtil.getAdminInstance(request);

		try {

			/* ====================================== 외부 관리자 URL CHECK (20160923 J.Ryeon Lee) ====================================== */

			if (isAllowedProcessForExternalAdmin(requestURI)) { // 외부 관리자 로그인 처리는 언제든 접근 가능
				return true;
			} else if (admin != null && requestURI.contains("/sysContents.do")) {
				return true;
			}

			boolean accessExternalAdminMng = false;
			for (AdminType adminType : AdminType.values()) {
				if (adminType.getMngSiteCode() != null && requestURI.contains(adminType.getMngSiteCode())) { // 외부 관리자 URL 접근시
					accessExternalAdminMng = true;
					if (!SessionUtil.isAdminLogin(request)) { // 인증되지 않은 사용자
						externalAdminLoginService.logout(request);
						response.sendRedirect("/sys/" + adminType.getMngSiteCode() + "/login.do");
						return false;
					}
				}
			}

			if (accessExternalAdminMng) { // 인증된 외부 관리자 계정의 접근
				if (admin.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode()) { // 외부 관리자의 경우에만 체크
					for (AdminType adminType : AdminType.values()) {
						if (adminType.getMngSiteCode() != null && adminType.getMngSiteCode().equals(admin.getAdminType().getMngSiteCode())) {
							isAllow = true; // 현재 외부 관리자가 접근 가능한 메뉴
						}
					}

					if (!isAllow) { // 인가되지 않은 메뉴 접근시
						externalAdminLoginService.logout(request);
						response.sendRedirect("/sys/" + SiteCode.branch(requestURI) + "/login.do");
						return false;
					}
				}
			}

			/* ====================================== ALL 관리자 CHECK ====================================== */

			SiteAccessVO searchVO = new SiteAccessVO();
			List<SiteAccessVO> accList = siteAccessMngService.getAccessAllowChkList(searchVO);
			if (!"0:0:0:0:0:0:0:1".equals(remoteIp)) {
				for (SiteAccessVO vo : accList) {
					if ("A".equals(vo.getType())) {
						String[] remoteIpA = remoteIp.trim().split("\\.");
						String[] ipBandAA = vo.getIpBandA().trim().split("\\.");
						String[] ipBandAB = vo.getIpBandB().trim().split("\\.");

						Boolean ipChk = false;
						if (Integer.parseInt(remoteIpA[0]) == Integer.parseInt(ipBandAA[0]) && Integer.parseInt(remoteIpA[0]) == Integer.parseInt(ipBandAB[0])) {
							for (int i = 0; i < remoteIpA.length; i++) {
								if (Integer.parseInt(remoteIpA[i]) >= Integer.parseInt(ipBandAA[i]) && Integer.parseInt(remoteIpA[i]) <= Integer.parseInt(ipBandAB[i])) {
									ipChk = true;
								} else {
									ipChk = false;
									break;
								}
							}
						}

						if (ipChk) {
							isAllow = true;
							isAllow = getAllowDateChk(isAllow, vo);
							break;
						}
					} else {
						if (vo.getIp().trim().equals(remoteIp)) {
							isAllow = true;
							isAllow = getAllowDateChk(isAllow, vo);
							break;
						}
					}
				} //for accList
			} else {
				isAllow = true;
			}
			if ("N".equals(siteAccessUseYn) || isAllow) {

				if (request.getRequestURI().contains("voteSatis.do")) {
					return true;
				}

				if (request.getRequestURI().contains("login.do")) {
					return true;
				}

				if (request.getRequestURI().contains("loginProc.do")) {
					return true;
				}

				if (request.getRequestURI().contains("superLoginProc.do")) {
					return true;
				}

				if (SessionUtil.isAdminLogin(request)) {
					return true;
				} else {
					externalAdminLoginService.logout(request);
					response.sendRedirect("/sys/login.do");
					return false;
				}
			} else {
				// 20141114 ums
				response.sendRedirect("/");
				return false;
			}
		} catch (Exception e) {
			// 20141114 ums
			response.sendRedirect("/");
			return false;
		}

	}

	private boolean isAllowedProcessForExternalAdmin(String requestURI) {
		for (AdminType adminType : AdminType.values()) {
			if (requestURI.contains(adminType.getMngSiteCode() + "/login.do") //
				|| requestURI.contains(adminType.getMngSiteCode() + "/loginProc.do") //
			) {
				return true;
			}
		}

		return false;
	}

	private Boolean getAllowDateChk(Boolean allow, SiteAccessVO vo) {
		if ("Y".equals(vo.getAllowDateUseYn())) {
			int compare1 = TUtil.getToday().compareTo(vo.getAllowDateSt());
			int compare2 = TUtil.getToday().compareTo(vo.getAllowDateEnd());

			if (compare1 > 0 && compare2 < 0) {
				allow = true;
			} else {
				allow = false;
			}
		}
		return allow;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// default
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// default
	}
}