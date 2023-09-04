<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * @Class Name : sitemap.jsp
	 * @Description : 공통사이트맵
	 * @Modification Information
	 *
	 * 수정일			수정자				수정내용
	 * -------------	----------------	---------------------------
	 * 2015-07-14		김장섭				최초생성
	 * 2015-11-27		J.Ryeon Lee			사이트별 사이트맵 기능 추가
	 * 2019-50-31		김선영				마크업 수정
	 */
%>
<link media="all" type="text/css" rel="stylesheet" href="/common/css/application.css">
<link media="all" type="text/css" rel="stylesheet" href="/common/css/common.css">
<c:set var="menuDepthLink" value="/${empty sitemapCode ? siteCodeFull : sitemapCode}/contents.do"/>
<c:set var="target_menus" value="${empty sitemapMenuMap ? menus : sitemapMenuMap}"/>

<%-- <form id="searchForm" name="searchForm" action="/portal/guide/siteMap.do?mId=${menuVO.mId}" method="post">
	<div class="bod_head sitemap clFix">
		<fieldset class="bod_search">
			<legend class="blind">사이트 목록 검색</legend>
			<label for="sitemapCode" class="blind">사이트 선택 영역</label>
			<select name="sitemapCode" id="sitemapCode">
				<option value="portal" <c:if test="${sitemapCode == 'portal'}">selected="selected"</c:if>>본청</option>
				<option value="park" <c:if test="${sitemapCode == 'park'}">selected="selected"</c:if>>공원관리소</option>
				<option value="health" <c:if test="${sitemapCode == 'health'}">selected="selected"</c:if>>구미보건소</option>
				<option value="ssphc" <c:if test="${sitemapCode == 'ssphc'}">selected="selected"</c:if>>선산보건소</option>
				<option value="silver" <c:if test="${sitemapCode == 'silver'}">selected="selected"</c:if>>노인종합복지관</option>
				<option value="farm" <c:if test="${sitemapCode == 'farm'}">selected="selected"</c:if>>농업기술센터</option>
				<option value="arts" <c:if test="${sitemapCode == 'arts'}">selected="selected"</c:if>>문화예술회관</option>
				<option value="car" <c:if test="${sitemapCode == 'car'}">selected="selected"</c:if>>차량등록사업소</option>
				<option value="edu" <c:if test="${sitemapCode == 'edu'}">selected="selected"</c:if>>평생교육원</option>
			</select>
			<input type="submit" value="검색" name="searchBt" title="검색">
		</fieldset>
	</div>
</form> --%>

<div id="body">
	<c:forEach var="depth1" items="${target_menus}" varStatus="depth1Seq">
		<div class="sitemap_wrap clFix" id="sitemap${depth1.mId}">
			<div class="topmenu">
				<h4 id="sitemap_${depth1.mId}"><span>${depth1.menuName}</span></h4>
			</div>

			<div class="submenu">
				<ul>
					<c:forEach var="depth2" items="${depth1.subMenu}">
						<li>
						<c:choose>
							<c:when test="${depth2.target == '_blank'}">
								<a href="${fn:escapeXml(depth2.linkUrl)}" target="_blank" title="새창열림"><span>${depth2.menuName}</span></a>
							</c:when>
							<c:otherwise>
								<a href="${menuDepthLink}?mId=${depth2.mId}"><span>${depth2.menuName}</span></a>
							</c:otherwise>
						</c:choose>

						<c:if test="${not empty depth2.subMenu}">
							<div>
								<ul class="clFix">
									<c:forEach var="depth3" items="${depth2.subMenu}">
										<li>
											<c:choose>
												<c:when test="${depth3.target == '_blank'}">
													<a href="${fn:escapeXml(depth3.linkUrl)}" target="_blank" title="새창열림"><span>${depth3.menuName}</span></a>
												</c:when>
												<c:otherwise>
													<a href="${menuDepthLink}?mId=${depth3.mId}"><span>${depth3.menuName}</span></a>
												</c:otherwise>
											</c:choose>
										</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						</li>
					</c:forEach>
					<c:if test="${empty depth1.subMenu}">
						<li><a href="${fn:escapeXml(depth1.linkUrl)}" target="_blank" title="새창열림"><span class="bt_R_blank">바로 가기</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</c:forEach>
</div>
