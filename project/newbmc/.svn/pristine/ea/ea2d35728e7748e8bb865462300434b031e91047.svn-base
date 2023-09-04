package egovframework.portal.security.user;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.main.SiteCode;
import egovframework.portal.security.RoleType;
import egovframework.portal.security.vo.Role;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.WriterUtil;

/**
 * SPRING SECURITY ROLE 권한별 메뉴 접근 불허 처리 SERVICE
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.07.15		J.Ryeon Lee			최초 생성 및 코딩
 * 2016.08.23		권태성				각 ROLE에 따른 handler 처리
 * 2017.02.08		J.Ryeon Lee			미인증 회원 거부 처리
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.07.15
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	private Boolean redirect = true;

	public Boolean getRedirect() {
		return redirect;
	}

	public void setRedirect(Boolean redirect) {
		this.redirect = redirect;
	}

	private String errorPage;

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getErrorPage() {
		return errorPage;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role role = (Role) auth.getAuthorities().iterator().next();
		String roleName = role.getName();
		String siteCodeFull = SiteCode.full(request.getRequestURI());
		if (RoleType.USER_TMP.getCode().equals(roleName)) {
			String redirect = "/" + siteCodeFull + "/user/loginForm.do?mId=" + request.getParameter("mId") + "&successUrl=" + URLEncoder.encode(request.getRequestURI() + TUtil.addParams(request.getParameterMap()), "UTF-8") + "';";
			WriterUtil.flushJsAlertAndRedirect(response, "가입된 회원만 이용 가능합니다.", redirectAction(request) + redirect);
		} else if (RoleType.USER.getCode().equals(roleName)) {
			if (StringUtil.isNotBlank(request.getParameter("nameCheck"))) {
				response.sendRedirect("/SIREN24/pcc_vname_Input.jsp?returnUrl=" + URLEncoder.encode(request.getRequestURI() + TUtil.addParams(request.getParameterMap()), "UTF-8") + "&mId=" + request.getParameter("mId"));
			} else {
				String redirect = "/" + siteCodeFull + "/bbs/inRealName.do?mId=" + request.getParameter("mId") + "&nameCheck=" + request.getParameter("nameCheck") + "&successUrl=" + URLEncoder.encode(request.getRequestURI() + TUtil.addParams(request.getParameterMap()), "UTF-8") + "';";
				WriterUtil.flushJsAlertAndRedirect(response, "인증 받은 회원만 이용 가능합니다.", redirectAction(request) + redirect);
			}
		} else if (RoleType.USER_DORMANT.getCode().equals(roleName)) {
			WriterUtil.flushJsAlertAndRedirect(response, "휴면 회원입니다. 인증 후 이용 가능합니다.", "location.href = '/portal/user/activation.do?mId=" + EgovProperties.getProperty(Constant.USER_MODIFY_MID) + "'");
		} else if (RoleType.USER_NOT_CERTIFICATED.getCode().equals(roleName)) {
			WriterUtil.flushJsAlertAndRedirect(response, "미인증 회원입니다. 인증 후 이용 가능합니다.", "location.href = '/portal/user/certification.do?mId=" + EgovProperties.getProperty(Constant.USER_MODIFY_MID) + "'");
		} else {
			WriterUtil.flushJsAlertAndHistoryBack(response, "이용하실 수 없는 메뉴입니다.");
		}

	}

	/**
	 * request에서 받은 파라미터에 따라 redirect시 스크립트 세팅
	 *
	 * @Method Name : redirectAction
	 * @param request
	 * @return
	 */
	private String redirectAction(HttpServletRequest request) {

		if (StringUtil.isNotBlank(request.getParameter("popup"))) {
			//popup 값을 받으면 alert 메시지를 띄우고 window.close
			return "window.close();//";
		} else {
			//popup이 아닌 경우에는 alert 메시지를 띄우고 location.href
			return "document.location.href='";
		}

	}

}