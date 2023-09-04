package egovframework.portal.bbs.aop;

import static egovframework.portal.util.WriterUtil.createJsAlertContent;
import static egovframework.portal.util.WriterUtil.flushJSAlert;
import static egovframework.portal.util.WriterUtil.flushJSAlertNotExistBoard;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;
import egovframework.portal.util.WriterUtil;

/**
 * 게시판 유효성 검증 클래스
 *
 * @author J.Ryeon Lee
 * @since 2016.08.18
 */
@Component("userBbsAccessValidator")
public class UserBbsAccessValidator {

	@Autowired
	protected MenuMngService menuService;
	@Autowired
	protected BbsConfigService bbsConfigService;

	private final String ONLY_FOR_AUTHENTICATED_USER = "본인 인증 후 이용하실 수 있습니다.";

	public Object isAccessable(ProceedingJoinPoint method) throws Throwable {
		HttpServletRequest request = null;
		HttpServletResponse response = null;

		Object[] signatureArgs = method.getArgs();
		for (Object signatureArg : signatureArgs) {
			if (signatureArg instanceof HttpServletRequest) {
				request = (HttpServletRequest) signatureArg;
			} else if (signatureArg instanceof HttpServletResponse) {
				response = (HttpServletResponse) signatureArg;
			}
		}

		Boolean notFlushed = Boolean.FALSE;
		String uri = request.getRequestURI();
		String mId = request.getParameter("mId");
		String siteCode = SiteCode.branch(uri);
		UserVO user = UserUtil.getInstance();
		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(request.getParameter("ptIdx")));

		/* ============================================ 게시판 전체 프로세스 ============================================ */

		if (config == null) { // case 존재하지 않는 게시판
			flushJSAlertNotExistBoard(response);
		} else if ("Y".equals(config.getIsDel())) {
			flushJSAlertNotExistBoard(response);
		} else if (!"Y".equals(config.getPtIsolateYn()) && isNotValidBbs(config, mId, siteCode)) { // case 메뉴와 게시판 연결이 invalid
			flushJSAlertNotExistBoard((HttpServletResponse) response);
		} else if ("Y".equals(config.getPtReportYn()) && user == null) { // case 신고형 게시판 && 미인증 회원
			flushJSAlert(response, createJsAlertContent(ONLY_FOR_AUTHENTICATED_USER, "location.href='" + toAuthForm(mId, siteCode, config.getPtIdx()) + "'"));
		} else if (StringUtil.isNotBlank(config.getPtCheckIp())) { // case check access ip
			String checkIp = SessionUtil.getRemoteAddr(request);

			String[] array_data = config.getPtCheckIp().toString().split(";");
			Boolean flushed = Boolean.FALSE;
			for (int i = 0; i < array_data.length; i++) {
				if (checkIp.toString().equals(array_data[i])) {
					WriterUtil.flushJsAlertAndHistoryBack(response, "접근할 수 없는 IP 입니다.");
					flushed = Boolean.TRUE;
					break;
				}
			}
			notFlushed = !flushed;
		} else {
			notFlushed = Boolean.TRUE;
		}

		/* ============================================ 글쓰기/수정/삭제 ============================================ */

		if (notFlushed && (uri.contains("write") || uri.contains("modify") || uri.contains("delete"))) { // 이 시점에서 user는 항상 존재함. security 처리 이후.
			notFlushed = Boolean.TRUE; // 글쓰기/수정/삭제 처리에 공통적으로 무언가를 제어하는 경우 기술
		}

		if (notFlushed) {
			return method.proceed();
		}

		return null;
	}

	private String toAuthForm(String mId, String siteCode, String ptIdx) throws UnsupportedEncodingException {
		return "/" + siteCode + "/bbs/inRealName.do?mId=" + mId + "&successUrl=" + successUrl(siteCode, mId, ptIdx);
	}

	private boolean isValidBbs(BbsConfigVO bbs, String mId, String siteCode) {
		if (bbs != null && StringUtil.noBlankValue(mId, siteCode)) {
			MenuVO menu = menuService.getMenuInfo(mId, siteCode);
			if (menu != null && "B".equals(menu.getMenuType()) && bbs.getPtIdx().equals(menu.getBbsMstIdx() + "") && !"Y".equals(bbs.getIsDel())) {
				return Boolean.TRUE;
			}
		}

		return Boolean.FALSE;
	}

	private boolean isNotValidBbs(BbsConfigVO bbs, String mId, String siteCode) {
		return !isValidBbs(bbs, mId, siteCode);
	}

	private String successUrl(String siteCode, String mId, String ptIdx) throws UnsupportedEncodingException {
		return URLEncoder.encode("/" + siteCode + "/bbs/list.do?mId=" + mId + "&ptIdx=" + ptIdx, "UTF-8");
	}

}