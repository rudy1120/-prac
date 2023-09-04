package egovframework.portal.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * response 성형 util
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2016. 8. 23.		권태성				redirect 관련 수정
 * 2016. 9. 25.		권태성				createJsPostContent, flushJsPostRedirect 메소드 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 5. 2.
 */
public enum WriterUtil {

	INSTANCE,;

	public static final Logger LOGGER = Logger.getLogger(WriterUtil.class.getName());

	public static void flush(HttpServletResponse response, String contentType, String contents) {
		Writer writer = null;
		try {
			response.setContentType(contentType);
			writer = response.getWriter();
			writer.write(contents);
		} catch (IOException e) {
			LOGGER.error("IOException - can not write response content");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				LOGGER.error("IOException - can not close writer object");
			}
		}
	}

	public static void flushJSAlert(HttpServletResponse response, String contents) {
		flush(response, "text/html; charset=UTF-8", contents);
	}

	public static String createJsAlertContent(String msg, String returnPath) {
		return createJsAlertContent("알림창", msg, returnPath);
	}

	public static String createJsAlertContent(String title, String msg, String returnPath) {
		StringBuilder content = new StringBuilder();
		content.append("<!DOCTYPE html>");
		content.append("<html lang=\"ko\">");
		content.append("	<head>");
		content.append("		<meta charset=\"UTF-8\">");
		content.append("		<title> " + title + " </title>");
		content.append("		<script type=\"text/javascript\">");
		if (StringUtil.isNotBlank(msg)) {
			content.append("		alert('" + msg + "');");
		}
		content.append(returnPath);
		content.append("		</script>");
		content.append("	</head>");
		content.append("	<body></body>");
		content.append("</html>");
		return content.toString();
	}

	public static String createJsPostContent(String title, String msg, String returnPath, Map<String, String> paramMap) {
		StringBuilder content = new StringBuilder();
		content.append("<!DOCTYPE html>");
		content.append("<html lang=\"ko\">");
		content.append("	<head>");
		content.append("		<meta charset=\"UTF-8\">");
		content.append("		<title>" + title + "</title>");
		content.append("		<script type=\"text/javascript\">");
		content.append("			function goSubmit(){");
		if (StringUtil.isNotBlank(msg)) {
			content.append("				alert('" + msg + "');");
		}
		content.append("				var frm = document.getElementById(\"frm\");");
		content.append("				frm.submit();");
		content.append("			}");
		content.append("		</script>");
		content.append("	</head>");
		content.append("	<body onload=\"goSubmit();\">");
		content.append("		<form id=\"frm\" action=\"" + returnPath + "\" method=\"post\">");

		if (paramMap != null) { // parameter 추가
			Set<String> keySet = paramMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				content.append("			<input type=\"hidden\" name=\"" + key + "\" value=\"" + paramMap.get(key) + "\" />");
			}
		}

		content.append("		</form>");
		content.append("	</body>");
		content.append("</html>");
		return content.toString();
	}

	public static void flushJSAlertNotExistBoard(HttpServletResponse response) {
		flushJSAlertNotExistBoard(response, "location.href='/main.do'");
	}

	public static void flushJSAlertNotExistBoard(HttpServletResponse response, String returnPath) {
		flushJSAlert(response, createJsAlertContent("게시판이 삭제되었거나 존재하지 않습니다.", returnPath));
	}

	public static void flushJSSysInvalidAccess(HttpServletResponse response) {
		flushJSInvalidAccess(response, "location.href='/sys/intro.do'");
	}

	public static void flushJSInvalidAccess(HttpServletResponse response) {
		flushJSInvalidAccess(response, "location.href='/'");
	}

	public static void flushJSInvalidAccess(HttpServletResponse response, String returnPath) {
		flushJSAlert(response, createJsAlertContent("잘못된 접근입니다.", returnPath));
	}

	public static void flushJsAlertAndHistoryBack(HttpServletResponse response, String msg) {
		flushJSAlert(response, createJsAlertContent(msg, "history.back(-1);"));
	}

	public static void flushJsAlertAndHistoryBack(HttpServletResponse response, String msg, String depth) {
		flushJSAlert(response, createJsAlertContent(msg, "history.go(-" + depth + ");"));
	}

	public static void flushJsAlertAndHistoryBack(HttpServletResponse response) {
		flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
	}

	public static void flushJsAlertAndRedirect(HttpServletResponse response, String msg, String returnPath) {
		flushJSAlert(response, createJsAlertContent(msg, returnPath));
	}

	public static void flushJsAlertAndRedirect(HttpServletResponse response, String title, String msg, String returnPath) {
		flushJSAlert(response, createJsAlertContent(title, msg, returnPath));
	}

	/**
	 * paramMap에서 받은 객체들 form에 담아서 submit 요청을하는 메소드
	 *
	 * @Method Name : flushJsPostRedirect
	 * @param response
	 * @param title
	 * @param msg
	 * @param returnPath
	 * @param paramMap
	 */
	public static void flushJsPostRedirect(HttpServletResponse response, String title, String msg, String returnPath, Map<String, String> paramMap) {
		flushJSAlert(response, createJsPostContent(title, msg, returnPath, paramMap));
	}

	public static void flushAlertAndRedirectToAuthenticationPage(HttpServletRequest request, HttpServletResponse response, String title, String mId, String msg, String siteCode) throws UnsupportedEncodingException {
		String successUrl = request.getRequestURI() + TUtil.addParams(request.getParameterMap());
		flushAlertAndRedirectToAuthenticationPage(request, response, title, mId, msg, successUrl, siteCode);
	}

	public static void flushAlertAndRedirectToAuthenticationPage(HttpServletRequest request, HttpServletResponse response, String title, String mId, String msg, String successUrl, String siteCode) throws UnsupportedEncodingException {
		String returnPath = "/" + siteCode + "/bbs/inRealName.do" + "?mId=" + mId + "&successUrl=" + URLEncoder.encode(successUrl, "UTF-8");
		flushJsAlertAndRedirect(response, title, msg, "location.href = '" + returnPath + "';");
	}

}
