package egovframework.portal.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import egovframework.portal.common.SessionKey;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * Session과 관련된 Util 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2010. 08. 11.	박동환				최초 생성 및 코딩
 * 2017. 04. 12.	J.Ryeon Lee			동시 로그인 제어 처리 // 미사용 메소드 제거
 * </pre>
 *
 * @author 박동환
 * @since 2010. 08. 11.
 */
public class SessionUtil {

	public static String getSessionAttrAsString(HttpServletRequest request, String key) {
		return (String) request.getSession(Boolean.TRUE).getAttribute(key);
	}

	/**
	 * LoginManager에서 관리자 로그인 정보를 가져와서 Return한다.
	 *
	 * @param request
	 * @return
	 */
	public static Object getAdminSessionObj(HttpServletRequest request) {
		Object _session = (HttpSession) LoginManager.getInstance().getLoginInfo(request.getSession().getId());
		HttpSession session = _session != null ? (HttpSession) _session : request.getSession(true);

		return session.getAttribute(SessionKey.ADMIN_LOGIN_INFO.getKey());
	}

	public static AdminLoginVO getAdminInstance(HttpServletRequest request) {
		Object admin = getAdminSessionObj(request);
		return admin != null ? (AdminLoginVO) admin : null;
	}

	public static String getAdminName(HttpServletRequest request) {
		return getAdminInstance(request).getAdminName();
	}

	public static String getAdminId(HttpServletRequest request) {
		return getAdminInstance(request).getAdminId();
	}

	/**
	 * LoginManager에서 현재 관리자가 로그인이 되어있는지에 대한 정보를 Return한다.
	 *
	 * @param request
	 * @return
	 */
	public static boolean isAdminLogin(HttpServletRequest request) {
		boolean bReturn = false;
		if (request != null && request.getSession().getAttribute(SessionKey.ADMIN_LOGIN_INFO.getKey()) != null) {
			bReturn = LoginManager.getInstance().isLogin(request.getSession().getId());
		}
		return bReturn;
	}

	/**
	 * 사용자의 IP를 return 해줌
	 *
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("x-forwarded-for");
		if (remoteAddr == null || remoteAddr.length() == 0 || remoteAddr.toLowerCase().equals("unknown")) {
			remoteAddr = request.getHeader("REMOTE_ADDR");
		}

		if (remoteAddr == null || remoteAddr.length() == 0 || remoteAddr.toLowerCase().equals("unknown")) {
			remoteAddr = request.getRemoteAddr();
		}

		return remoteAddr;
	}

}
