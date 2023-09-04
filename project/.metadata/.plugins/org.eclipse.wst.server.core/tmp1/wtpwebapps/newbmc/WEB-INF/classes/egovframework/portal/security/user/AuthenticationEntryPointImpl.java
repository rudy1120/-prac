package egovframework.portal.security.user;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.rte.fdl.string.EgovStringUtil;

/**
 * SPRING SECURITY 인증 ENTRY POINT
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.08.12		J.Ryeon Lee			최초 생성 및 코딩
 * 2016.08.22		권태성				정보통신공사 사용전 검사필증 관련 파라미터 처리 로직 추가
 * 2016.10.16		J.Ryeon Lee			cancelUrl 추가
 * 2016.12.26		J.Ryeon Lee			회원 관리자만 글을 쓸 수 있는 게시판의 경우 로그인 화면으로 이동하는 처리 추가
 * 2017.03.03		J.Ryeon Lee			기능 병합
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.08.12
 * @version 1.0
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Autowired
	protected BbsConfigService bbsConfigService;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String requestURI = request.getRequestURI();
		String successUrl = URLEncoder.encode(requestURI + TUtil.addParams(request.getParameterMap()), "UTF-8");
		String cancelUrl = URLEncoder.encode(EgovStringUtil.null2string(request.getParameter("cancelUrl"), ""), "UTF-8");
		String siteCodeFull = SiteCode.full(requestURI);

		Boolean notFlushed = Boolean.TRUE;
		if (requestURI.contains("/bbs/") && request.getParameterMap().containsKey("ptIdx")) { // case 게시판 접근
			// TODO 추후 게시판별 특정 처리를 하는 경우 활성화
//			BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(request.getParameter("ptIdx")));
//			if ("Y".equals(config.getPtAdminOnlyYn())) { // 회원 관리자만 이용 가능 메뉴 접근
//				response.sendRedirect("/" + siteCodeFull + "/user/loginForm.do?mId=" + request.getParameter("mId") + "&successUrl=" + successUrl + "&cancelUrl=" + cancelUrl);
//				notFlushed = Boolean.FALSE;
//			}
		} else if (requestURI.contains("/portal/user/") // case 회원 서비스 중
			&& !requestURI.contains("/join") // 가입 페이지 제외
			&& !requestURI.contains("/findAccount") // 계정 검색 페이지 제외
			&& !requestURI.contains("/resetPassword") // 임시 비밀번호 발급 처리 제외
		) { // case 회원 서비스 처리
			response.sendRedirect("/portal/user/loginForm.do?mId=" + request.getParameter("mId") + "&successUrl=" + successUrl + "&cancelUrl=" + cancelUrl);
			notFlushed = Boolean.FALSE;
		} else if (StringUtil.isNotEmpty(request.getParameter("nameCheck"))) { // 휴대폰 본인 인증만 지원
			response.sendRedirect("/SIREN24/pcc_vname_Input.jsp?returnUrl=" + successUrl + "&mId=" + request.getParameter("mId") + "&cancelUrl=" + cancelUrl);
			notFlushed = Boolean.FALSE;
		} else if (StringUtil.isNotEmpty(request.getParameter("ipin"))) { // 공공 I-PIN만 지원
			response.sendRedirect("/G-PIN/gpinAuthRequest.jsp?returnUrl=" + successUrl + "&mId=" + request.getParameter("mId") + "&cancelUrl=" + cancelUrl);
			notFlushed = Boolean.FALSE;
		}

		if (notFlushed) {
			response.sendRedirect("/" + siteCodeFull + "/bbs/inRealName.do" + "?mId=" + request.getParameter("mId") + "&successUrl=" + successUrl + "&cancelUrl=" + cancelUrl);
		}
	}

}