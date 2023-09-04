package egovframework.portal.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.portal.sys.sysAuth.service.ExternalAdminLoginService;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;

/**
 * 외부 관리자 접근 권한 체크 INTERCEPTOR
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 10. 24.	J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 10. 24.
 */
@Service
public class AdminAuthCheckerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	protected ExternalAdminLoginService externalAdminLoginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
		String mId = request.getParameter("mId");
		Boolean allow = Boolean.TRUE;
		String requestURL = request.getRequestURI();

		if (admin != null) {

			if (admin.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode()) {
				if (StringUtil.isNotBlank(mId) && !StringUtil.contains(mId, admin.getMids().split(","))) { // 허가되지 않은 mId에 접근
					allow = Boolean.FALSE;
				}

				// 컨텐츠가 아닌 관리자 프로그램 url 접근시 인가된 패턴인지 검증
				if (allow && !requestURL.contains("/sysContents.do") && !requestURL.contains(admin.getAdminType().getMngSiteCode())) {
					allow = Boolean.FALSE;
				}
			}

			if (!allow) {
				externalAdminLoginService.logout(request);
				response.sendRedirect("/sys/" + admin.getAdminType().getMngSiteCode() + "/login.do");
			}
		}

		return allow;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// ignore
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// ignore
	}
}