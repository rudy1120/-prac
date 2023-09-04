package egovframework.portal.security.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.setContentType("text/plain; charset=utf-8");
		JSONObject rtn = new JSONObject();

		try {
			rtn.put("flag", "fail");

			if (exception.getMessage().contains("Bad credentials")) {
				rtn.put("message", "계정과 암호를 확인해주세요.");
			} else {
				rtn.put("message", "로그인 중 오류가 발생하였습니다.");
			}
		} catch (Exception e) {
			LOGGER.error(">> failed to create json", e);
		}

		response.getWriter().println(rtn.toString());
	}

}