package egovframework.portal.security.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import egovframework.portal.util.StringUtil;

/**
 * SPRING SECURITY 회원 로그아웃 처리 후 화면 처리 SERVICE
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.07.15		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.07.15
 */
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String successUrl = request.getParameter("successUrl");
		if (StringUtil.isBlank(successUrl)) {
			response.sendRedirect("/main.do"); // TODO
		} else {
			response.sendRedirect(successUrl);
		}
	}

}