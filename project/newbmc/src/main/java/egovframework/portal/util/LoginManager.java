package egovframework.portal.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import egovframework.portal.common.SessionKey;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 사용자 접속현황 관리를 위한 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2010. 08. 11.	박동환				최초 생성 및 코딩
 * 2017. 04. 13.	J.Ryeon Lee			중복 로그인 방지 처리 // 미사용 메소드 주석 처리
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 4. 14.
 */
public class LoginManager implements HttpSessionBindingListener, Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private static LoginManager loginManager = null;
	private static Hashtable<String, Object> loginUsers = new Hashtable<String, Object>();

	private LoginManager() {
		super();
	}

	public static synchronized LoginManager getInstance() {
		if (loginManager == null) {
			loginManager = new LoginManager();
		}
		return loginManager;
	}

	/* ========================================== 2017.04.13 J.Ryeon Lee ADD ========================================== */

	/**
	 * 기존의 세션을 무효화하고 현재 로그인하려는 사용자의 정보로 새로이 세션을 생성/저장
	 *
	 * @param request
	 * @param loginVO
	 */
	public static void login(HttpServletRequest request, AdminLoginVO loginVO) {
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null) {
			oldSession.invalidate();
		}
		request.getSession(true);

		LoginManager loginManager = LoginManager.getInstance();
		loginManager.setAdminSession(request.getSession(), loginVO);
		loginManager.removeDupLogin(request.getSession().getId(), loginVO.getId());
	}

	public static void logout(HttpServletRequest request, AdminLoginVO loginVO) {
		if (loginVO != null) {
			LoginManager.getInstance().logout(request.getSession(false));
		}
	}

	/**
	 * logout
	 *
	 * @param session
	 */
	private void logout(HttpSession session) {
		if (session != null) {
			loginUsers.remove(session.getId());
			session.invalidate();
		}
	}

	/**
	 * @param sessionId
	 * @param loginId
	 * @author J.Ryeon Lee
	 */
	@SuppressWarnings("unchecked")
	public synchronized void removeDupLogin(String sessionId, String loginId) {
		if (CommonUtil.checkServerMode()) {
			Object _loginVO = null;
			AdminLoginVO loginVO = null;
			List<String> errorKeys = new ArrayList<String>();

			Hashtable<String, Object> cloneUsers = (Hashtable<String, Object>) loginUsers.clone();

			for (Entry<String, Object> entry : cloneUsers.entrySet()) {
				HttpSession session = null;
				try {
					session = (HttpSession) entry.getValue();
					if (session != null) {
						_loginVO = session.getAttribute(SessionKey.ADMIN_LOGIN_INFO.getKey());
						if (_loginVO != null) {
							loginVO = (AdminLoginVO) _loginVO;
							if (loginId.equals(loginVO.getId()) && !sessionId.equals(session.getId())) {
								loginUsers.remove(session.getId());
								session.invalidate();
							}
						}
					}
				} catch (Exception e) {
					errorKeys.add(entry.getKey());
					continue;
				}
			}

			if (errorKeys.size() > 0) {
				for (String key : errorKeys) {
					loginUsers.remove(key);
				}
			}
		}
	}

	/**
	 * 관리자 세션 생성
	 *
	 * @param session
	 * @param loginInfo
	 */
	public void setAdminSession(HttpSession session, Object loginInfo) {
		session.setAttribute(SessionKey.ADMIN_LOGIN_INFO.getKey(), loginInfo);
		session.setAttribute("login", getInstance());
		loginUsers.put(session.getId(), session);
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// ignore
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		loginUsers.remove(event.getSession().getId());
	}

	/* ====================================================================================================================== */

	/**
	 * 해당 세션에 이미 로그인 되어있는지 검사
	 *
	 * @param sessionId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean isLogin(String sessionId) {
		boolean isLogin = false;
		Enumeration e = loginUsers.keys();
		String key = null;
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			if (sessionId.equals(key)) {
				isLogin = true;
			}
		}
		return isLogin;
	}

	/**
	 * 세션 ID로 로그인 정보 구분
	 *
	 * @param sessionId
	 * @return
	 */
	public Object getLoginInfo(String sessionId) {
		return loginUsers.get(sessionId);
	}

}
