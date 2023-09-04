<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:set var="successUrl" value="${successUrl}" />
<c:set var="cancelUrl" value="${cancelUrl}" />
<script type="text/javascript">
//<![CDATA[
	function joiner(value) { return value.indexOf("?") > -1 ? "&" : "?"; }

	function open_namecheck(postUrl) {
		var successUrl = "${successUrl}" ? "${successUrl}" : postUrl + joiner(postUrl) + "mId=${menuVO.mId}";
		window.open("/NICE/checkplus_input.jsp?successUrl=" + successUrl + "&mId=${menuVO.mId}&cancelUrl=${cancelUrl}", "popupChk", "width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");
	}
//]]>
</script>
