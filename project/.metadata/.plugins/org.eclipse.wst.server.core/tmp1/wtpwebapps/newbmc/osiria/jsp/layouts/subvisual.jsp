<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:choose>
	<c:when test="${fn:contains(activeMenu.spotTitle, '오시리아소개')}">
		<c:set var="visual" value="sub_visual01" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, '숙박시설')}">
		<c:set var="visual" value="sub_visual02" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, '상업시설')}">
		<c:set var="visual" value="sub_visual03" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, '운동오락시설')}">
		<c:set var="visual" value="sub_visual04" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, '휴양문화시설')}">
		<c:set var="visual" value="sub_visual05" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, '오시리아 소식')}">
		<c:set var="visual" value="sub_visual06" /></c:when>
	
</c:choose>


<section id="subBanner">
	<div class="${visual}">
       	<div class="sub_title">
       		<h2 class="subTitle_name">${activeMenu.spotTitle}</h2>
      			<p>Osiria Tourism Complex</p>
      		</div>
	</div>
</section>