<%@page import="egovframework.portal.util.TUtil"%>
<%@page import="egovframework.portal.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 본인인증/G-PIN 함수 컴포넌트
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.11		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.05.11
	 */
%>
<% String sUrl = URLEncoder.encode(TUtil.nullToBlank(request.getParameter("successUrl")), "UTF-8"); %>
<c:set var="useModule" value="${yh:getProperty('Globals.auth.module.use') == 'Y'}"/>
<c:set var="successUrl" value="<%=sUrl %>" />

<script type="text/javascript">
//<![CDATA[
	function joiner(value) { return value.indexOf("?") > -1 ? "&" : "?"; }
//]]>
</script>
<c:if test="${useModule}">
	<script type="text/javascript">
	//<![CDATA[

		function open_namecheck(postUrl) {
			var successUrl = "${successUrl}" ? "${successUrl}" : postUrl + joiner(postUrl) + "mId=${menuVO.mId}";
			window.open("/NICE/checkplus_input.jsp?successUrl=" + successUrl + "&mId=${menuVO.mId}&cancelUrl=${cancelUrl}", "popupChk", "width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");
		}

		function open_gpin(postUrl) {
			alert("현재 서비스 준비 중입니다. 빠른 시일내에 서비스할 수 있도록 하겠습니다.");
// 			var successUrl = "${successUrl}" ? "${successUrl}" : postUrl + joiner(postUrl) + "mId=${menuVO.mId}";
// 			window.open("/G-PIN/gpinAuthRequest.jsp?returnUrl=" + successUrl + "&mId=${menuVO.mId}&cancelUrl=${cancelUrl}", "gPinLoginWin", "width=410, height=450, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200");
		}

	//]]>
	</script>
</c:if>
<c:if test="${!useModule}">
	<script type="text/javascript">
	//<![CDATA[

		function open_namecheck(postUrl) {
			var successUrl = "${successUrl}" ? "${successUrl}" : postUrl + joiner(postUrl) + "mId=${menuVO.mId}";
			window.open("/makeSession.jsp?nameCheck=Y&successUrl=" + successUrl, "SIRENWindow", "width=550, height=300, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200");
		}

		function open_gpin(postUrl) {
			var successUrl = "${successUrl}" ? "${successUrl}" : postUrl + joiner(postUrl) + "mId=${menuVO.mId}";
			window.open("/makeSession.jsp?nameCheck=Y&authBy=ipin&successUrl=" + successUrl, "SIRENWindow", "width=550, height=300, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200");
		}

	//]]>
	</script>
</c:if>
