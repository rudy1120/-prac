<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
    String domain = request.getServerName();
%>
<c:set var="domain" value="<%= domain%>"/>
<c:choose>
 	<%-- <c:when test="${domain == 'bmc.busan.kr'}"><c:redirect url="http://www.bmc.busan.kr/bmc/main.do" /></c:when>
	<c:when test="${domain == 'www.bmc.busan.kr'}"><c:redirect url="http://www.bmc.busan.kr/bmc/main.do" /></c:when> --%>  
	<%-- 일광행복주택 메인화면 변경을 위한 메인 컨트롤러 변경   --%>
	<c:when test="${domain == 'bmc.busan.kr'}"><c:redirect url="http://www.bmc.busan.kr/bmc/hompage/open.do" /></c:when>
	<c:when test="${domain == 'www.bmc.busan.kr'}"><c:redirect url="http://www.bmc.busan.kr/bmc/hompage/open.do" /></c:when>
	<c:when test="${domain == 'osiria.bmc.busan.kr'}"><c:redirect url="http://osiria.bmc.busan.kr/osiria/main.do" /></c:when>
	<c:when test="${domain == 'history.bmc.busan.kr'}"><c:redirect url="http://history.bmc.busan.kr/history/main.do" /></c:when>
    <c:otherwise>
    	<%-- <c:redirect url="/bmc/hompage/open.do" /> --%>
    	<c:redirect url="/bmc/main.do" />
	</c:otherwise>
</c:choose>