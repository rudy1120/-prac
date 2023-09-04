package egovframework.portal.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import egovframework.rte.fdl.idgnr.impl.Base64;

/**
 * 토큰 util
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * ?				?					최초 생성 및 코딩
 * 2017.04.04		J.Ryeon Lee			singleton 처리
 * </pre>
 *
 * @author ?
 * @since ?
 */
public enum TokenUtil {

	INSTANCE,;

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String TOKEN_KEY = "TOKEN_KEY";

	/**
	 * 로직처리를 위해 세션과 request에 Token 생성
	 *
	 * @param request
	 */
	public static void saveToken(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		long systemTime = System.currentTimeMillis();
		String time = String.valueOf(systemTime);
		String id = session.getId();

		String token = Base64.encode((SecurityUtil.encrypt(id) + SecurityUtil.encrypt(time)).getBytes());
		request.setAttribute(TOKEN_KEY, token);
		session.setAttribute(TOKEN_KEY, token);
	}

	/**
	 * 로직처리 이후 중복방지를 위해 세션의 Token 초기화
	 *
	 * @param request
	 */
	public static void resetToken(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		try {
			session.removeAttribute(TOKEN_KEY);
		} catch (Exception e) {
			LOGGER.error("Exception Occued", e);
		}
	}

	/**
	 * 세션과 request의 Token이 동일한지 비교
	 *
	 * @param request
	 * @return
	 */
	public static boolean isTokenValid(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String requestToken = request.getParameter(TOKEN_KEY);
		String sessionToken = (String) session.getAttribute(TOKEN_KEY);

		if (requestToken != null && !request.equals("") && sessionToken != null && !sessionToken.equals("")) {
			return requestToken.equals(sessionToken);
		} else {
			return false;
		}
	}

}
