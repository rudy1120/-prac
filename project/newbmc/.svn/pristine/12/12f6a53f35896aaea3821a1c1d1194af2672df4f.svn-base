<%@page import="egovframework.portal.util.TUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * @Class Name : sitemapVisualization.jsp
	 * @Description : 공통시각화사이트맵
	 * @Modification Information
	 *
	 * 수정일			수정자				수정내용
	 * -------------	----------------	---------------------------
	 * 2016-02-29		김장섭				최초생성
	 * 2016-03-03		강은영				css 및 퍼블리싱
	 *
	 */
%>
<%
String maxMonthStr = TUtil.getToday("MM");
int maxMonth = Integer.parseInt(maxMonthStr);
pageContext.setAttribute("maxMonth", maxMonth);
%>
<div class="visual_sitemap">
	<div class="container">
		<header>
			<h1>자주찾는 메뉴</h1>
			<form name="form" id="form" action="" method="post">
				<select name="year" title="select" title="년도">
					<option value="2016">2016년</option>
				</select>
				<select name="month" title="select" title="월">
				<c:forEach begin="1" end="${maxMonth}" var="i" step="1">
					<c:set var="mh" value=""/>
					<c:if test="${i<10}"><c:set var="mh" value="0"/></c:if>
					<c:set var="mh" value="${mh}${i}"/>
					<option value="${mh}" <c:if test="${searchVO.month==mh}">selected="selected"</c:if> >${mh}월</option>
				</c:forEach>
				</select>
				<a href="#" onclick="javascript:document.form.submit();" id="submitBtn">검색</a>
			</form>
				<a href="/portal/guide/siteMap.do?mId=0708000000" target="_blank" class="allMenu">전체메뉴보기</a>
		</header>
		<c:set var="menuLink" value="/${empty sitemapCode ? siteCodeFull : sitemapCode}/contents.do"/>
		<section>
			<ul>
				<!-- 최초 first li는 on상태로 유지되었다가, 다른 li click시 icon01 class 로 변경되도록 부탁.   -->
				<li<c:if test="${sitemapCode=='portal'}"> class="on"</c:if>><a href="/portal/guide/siteMapVisualization.do?mId=0708000000" class="${sitemapCode=='portal' ? 'icon01_on' : 'icon01'}">대표포털</a></li>
				<li<c:if test="${sitemapCode=='depart'}"> class="on"</c:if>><a href="/portal/guide/siteMapVisualization.do?sitemapCode=depart&mId=0708000000" class="${sitemapCode=='depart' ? 'icon02_on' : 'icon02'}">분야별포털</a></li>
				<li<c:if test="${sitemapCode=='tour'}"> class="on"</c:if>><a href="/portal/guide/siteMapVisualization.do?sitemapCode=tour&mId=0708000000" class="${sitemapCode=='tour' ? 'icon03_on' : 'icon03'}">문화관광</a></li>
			</ul>
			<ol>
			<c:set var="count" value="0"/>
			<c:set var="strLenMax" value="0"/>
			<c:forEach var="el" items="${result}" varStatus="status">
				<c:choose>
					<c:when test="${status.count == 1}"><c:set var="strLenMax" value="4"/></c:when>
					<c:when test="${status.count < 11}"><c:set var="strLenMax" value="10"/></c:when>
					<c:when test="${status.count > 10 && status.count < 18}"><c:set var="strLenMax" value="8"/></c:when>
					<c:when test="${status.count > 17 && status.count < 21}"><c:set var="strLenMax" value="4"/></c:when>
				</c:choose>
				<c:if test="${status.count <= 20}">
				<li>
					<!-- 필요없음 <span>순위 : ${status.count}</span> -->
					<span>
					<a href="${menuLink}?mId=${el.mId}" target="_blank">
					<c:choose>
						<c:when test="${fn:length(el.menuName) > strLenMax}">${fn:substring(el.menuName, 0, strLenMax-1)}..</c:when>
						<c:otherwise>${el.menuName}</c:otherwise>
					</c:choose>
					</a>
					</span>
					<!-- 글자갯수 1~10위 10글자 / 11~17위 8글자 18~20위 4글자 -->
				</li>
				<c:set var="count" value="${count+1}"/>
				</c:if>
			</c:forEach>

			<c:if test="${count < 20}">
			<c:forEach begin="${count+1}" end="20" var="i" step="1">
				<li>
				<!--<span>순위 : ${i}</span>	-->
				<span>메뉴없음</span>
				</li>
			</c:forEach>
			</c:if>

			</ol>
		</section>
	</div>
</div>