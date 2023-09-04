<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:set var="headTitle" value="${fn:split(activeMenu.subHeadTitle, '>')}" />
<%-- <c:set var="headTitle1" value="${headTitle[1]}" /> --%>

<c:choose>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'About BMC')}">
		<c:set var="visual" value="aboutBmc" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'Achievements')}">
		<c:set var="visual" value="bmcAc" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'BMC Projects') && fn:contains(headTitle[1], 'My Home')}">	
		<c:set var="visual" value="bmcProjects" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'BMC Projects') && fn:contains(headTitle[1], 'Our Workplace')}">
		<c:set var="visual" value="bmcWp" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'BMC Projects') && fn:contains(headTitle[1], 'Everyone')}">
		<c:set var="visual" value="bmcCity" /></c:when>		
	<c:when test="${fn:contains(activeMenu.spotTitle, 'ESG Management of BMC')}">
		<c:set var="visual" value="bmcESG" /></c:when>	
</c:choose>

<div class ="sVisual ${visual}">
	<div class="svInner">
		<h3>${headTitle[1]}</h3>
		<ul>
			<li><img src="/eng/img/sub/icon/home_icon2.png" alt="home icon"></li>			
			<li>${headTitle[0]}</li>
			<li>${headTitle[1]}</li>
		</ul>
	</div>
</div>