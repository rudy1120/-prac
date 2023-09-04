<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/config/tld/fmt-rt.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="edosiFmt" uri="edosiFormatTagLib"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="yh" uri="/WEB-INF/config/tld/yh-util.tld"%>

<%--
	String domain = request.getServerName();
	String reqURL = "";
	if(request.getAttribute("javax.servlet.forward.request_uri") != null) {
		reqURL = request.getAttribute("javax.servlet.forward.request_uri").toString();
	}
	String contextPath = "";
	String query = request.getQueryString();
	
	System.out.println("domain=>"+domain);
	System.out.println("reqURL=>"+reqURL);
	System.out.println("query=>"+query);
	
	if(reqURL.startsWith("/bmc/")) {
		contextPath = "bmc";
	} else if(reqURL.startsWith("/osiria/")) {
		contextPath = "osiria";
	}
	System.out.println("contextPath=>"+contextPath);
--%>
<%-- <c:set var="domain" value="<%= domain%>"/> --%>
<%-- <c:set var="reqURL" value="<%= reqURL%>"/> --%>
<%-- <c:set var="contextPath" value="<%= contextPath%>"/> --%>
<%-- <c:choose> --%>
<%-- 	<c:when test="${contextPath == 'bmc' && domain == 'osiria.bmc.busan.kr'}"><c:redirect url="http://www.bmc.busan.kr/${reqURL}" /></c:when> --%>
<%-- 	<c:when test="${contextPath == 'osiria' && domain == 'www.bmc.busan.kr'}"><c:redirect url="http://osiria.bmc.busan.kr/${reqURL}" /></c:when> --%>
<%-- </c:choose> --%>