<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<jsp:useBean id="toDay" class="java.util.Date" />
<%
	/**
	 * menu mobile navigation
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2018.01.03		정은정			최초 생성 및 퍼블리싱
	 *
	 *
	 * @author 정은정
	 * @since 2018.01.03
	 */
%>

<c:set var="menuDepthLink" value="/${siteCodeFull}/contents.do"/>

<%-- MOBILE NAVIGATION --%>
	<div id="m_menu" class="m">
		<div>
			<nav class="side_menu" id="slide_menu">
				<h1 class="snb_logo">로고</h1>

				<a href="#" class="btn_menu_close"><span class="hide">메뉴 닫기</span></a>

				<ul class="depth1">
					<c:forEach var="depth1" items="${menus}" varStatus="status">
						<c:set var="isHiddenMenu" value="${((empty siteCodeFull || siteCodeFull == 'portal') && fn:startsWith(depth1.mId, '07')) || (siteCodeFull == 'depart' && fn:startsWith(depth1.mId, '09')) || (siteCodeFull == 'health' && fn:startsWith(depth1.mId, '06')) || (siteCodeFull == 'farm' && fn:startsWith(depth1.mId, '06')) }" />
						<%-- =============== FIRST DEPTH =============== --%>
						<c:if test="${!isHiddenMenu}">
							<li <c:if test="${fn:substring(curMid, 0, 2) == fn:substring(depth1.mId, 0, 2) || siteCodeFull eq 'teen' || siteCodeFull eq 'asimc' || siteCodeFull eq 'youthlove'}">class="on"</c:if>>
								<c:if test="${empty depth1.subMenu}">
									<c:choose>
										<c:when test="${depth1.menuType == 'L'}">
											<a href="${depth1.linkUrl}" target="${depth1.target}">${depth1.menuName}</a>
										</c:when>
										<c:otherwise>
											<a href="/${siteCodeFull}/contents.do?mId=${depth1.mId}">${depth1.menuName}</a>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${not empty depth1.subMenu}">
									<a>${depth1.menuName}</a>
									<ul class="depth2">
										<c:forEach var="depth2" items="${depth1.subMenu}" varStatus="status">
											<%-- =============== SECOND DEPTH =============== --%>
											<li <c:if test="${fn:substring(curMid, 0, 4) == fn:substring(depth2.mId, 0, 4)}">class="on"</c:if>>
												<a <c:if test="${fn:length(depth2.subMenu) == 0}">
													<c:if test="${depth2.menuType == 'L'}">href="${depth2.linkUrl}" target="${depth2.target}"</c:if>
													<c:if test="${depth2.menuType != 'L' }">href="/${siteCodeFull}/contents.do?mId=${depth2.mId}"</c:if>
													</c:if>>
													${depth2.menuName}
												</a>
												<c:if test="${not empty depth2.subMenu}">
													<ul class="depth3">
														<c:forEach var="depth3" items="${depth2.subMenu}" varStatus="status">
															<%-- =============== THIRD DEPTH =============== --%>
															<li <c:if test="${fn:substring(curMid, 0, 6) == fn:substring(depth3.mId, 0, 6)}">class="on"</c:if>>
																<c:choose>
																	<c:when test="${depth3.menuType == 'L'}">
																		<a href="${depth3.linkUrl}" target="${depth3.target}">${depth3.menuName}</a>
																	</c:when>
																	<c:otherwise>
																		<a href="/${siteCodeFull}/contents.do?mId=${depth3.mId}">${depth3.menuName}</a>
																	</c:otherwise>
																</c:choose>
															</li>
														</c:forEach>
													</ul>
												</c:if>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</nav>
		</div>
	</div>
	<div id="mask_mn"></div>
	<div id="mask_kw"></div>