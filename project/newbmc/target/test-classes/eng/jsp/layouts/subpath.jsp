<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:choose>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'About BMC')}">
		<c:set var="navknd" value="nav01" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'Achievements')}">
		<c:set var="navknd" value="nav01" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'BMC Projects')}">
		<c:set var="navknd" value="nav03" /></c:when>
	<c:when test="${fn:contains(activeMenu.spotTitle, 'ESG Management of BMC')}">
		<c:set var="navknd" value="nav03" /></c:when>	
</c:choose>

<c:set var="headTitle" value="${fn:split(activeMenu.subHeadTitle, '>')}" />

<div class="sNav ${navknd}">
	<ul>
		<li><a href="/eng/main.do"><img src="/eng/img/sub/icon/home_icon.png" alt="go to home icon"></a></li>
		<li><a href="javascript:void(0);">${headTitle[1]}</a></li>		
		<!-- 2depth active -->
		<c:choose>			
			<c:when test="${fn:contains(activeMenu.spotTitle, 'About BMC')}">				
		        <li><a href="/eng/contents.do?mId=0101000000">About BMC</a></li>
		        <li><a href="/eng/contents.do?mId=0102000000">CEO Greetings</a></li>
		        <li><a href="/eng/contents.do?mId=0103000000">Management Plan</a></li>
		        <li><a href="/eng/contents.do?mId=0104000000">Organization Chart</a></li>							
			</c:when>			
			<c:when test="${fn:contains(activeMenu.spotTitle, 'Achievements')}">				
		        <li><a href="/eng/contents.do?mId=0201000000">History</a></li>
		        <li><a href="/eng/contents.do?mId=0202000000">Awards</a></li>
		        <li><a href="/eng/contents.do?mId=0203000000">Achievements</a></li>
		        <li><a href="/eng/contents.do?mId=0204000000">Promotion</a></li>		        							
			</c:when>
			<c:when test="${fn:contains(activeMenu.spotTitle, 'BMC Projects')}">				
		        <li><a href="/eng/contents.do?mId=0301000000">My Home</a></li>
		        <li><a href="/eng/contents.do?mId=0302000000">Our Workplace</a></li>
		        <li><a href="/eng/contents.do?mId=0303000000">Everyone's City</a></li>		        							
			</c:when>
			<c:when test="${fn:contains(activeMenu.spotTitle, 'ESG Management of BMC')}">				
		        <li><a href="/eng/contents.do?mId=0401000000">Environmental Management</a></li>
		        <li><a href="/eng/contents.do?mId=0402000000">Win-Win Management</a></li>
		        <li><a href="/eng/contents.do?mId=0403000000">Open Management</a></li>		        							
			</c:when>									
		</c:choose>		
	</ul>
</div>
			    
			    


