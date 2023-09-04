<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/osiria/jsp/common/taglib.jsp"%>

<c:set var="_context" value="${pageContext.request.contextPath}" scope="request"/>
<script type="text/javascript">
//<![CDATA[
var yh = {
	"mId" : "${menuVO.mId}",
	"menuName" : "${menuVO.menuName}",
	"programUrl" : "${menuVO.programUrl}",
	"siteCode" : "${siteCode}",
	"siteCodeFull" : "${siteCodeFull}",
	"error" : "${error}",
	"contextPath" : "${_context}"
};
//]]>
</script>