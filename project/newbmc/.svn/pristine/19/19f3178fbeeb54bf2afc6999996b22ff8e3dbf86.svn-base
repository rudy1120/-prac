<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<%
	/**
	 * MENU LIST BREAD CRUMB
	 * Tree 형태의 리스트 오브젝트를 ul, li 태그로 변환합니다.
	 * 화면에 출력하는 Tree의 Depth는 본 파일을 include하는 jsp에서 변수명 p_maxDepth에,
	 * Tree Object는 변수명 p_elements에 담아 넘겨주세요.
	 * p_maxDepth의 경우 설정하지 않으면 Tree의 마지막 Depth까지 출력합니다.
	 *
	 * 참고: /common/jsp/layout/header.jsp
	 *
	 * 수정일				수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.08.25		J.Ryeon Lee		최초 생성 및 코드 작성
	 * 2019.05.13		김선영			마크업 수정
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.08.25
	 * @version 1.0
	 * @see egovframework.portal.content.vo.MenuVO
	 */
%>
<c:set var="exclusion_mid" value="0700000000" />

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request"/>
</c:if>
<c:if test="${empty p_curMid}">
	<c:set var="p_curMid" value="9999999999" scope="request"/>
</c:if>
<c:set var="isFirstDepth" value="${p_depth == 1}" />
<c:set var="isSecondDepth" value="${p_depth == 2}" />
<c:set var="isThirdDepth" value="${p_depth == 3}" />

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">
	<c:set var="ul_class" value="" />
	<c:if test="${isFirstDepth}">
		<c:set var="ul_class" value="lnb_nav" />
	</c:if>
	<c:if test="${isSecondDepth}">
		<c:set var="ul_class" value="${ul_class}depth02 clFix" />
	</c:if>
	<c:if test="${isThirdDepth}">
		<c:set var="ul_class" value="${ul_class}depth03 clFix" />
	</c:if>

	<ul <c:if test="${isFirstDepth}">id="lnb"</c:if>
		<c:if test="${not empty ul_class}">class="${ul_class}"</c:if>
		<c:if test="${isSecondDepth}"></c:if>
		<c:if test="${isThirdDepth}"></c:if>
	>
		<c:forEach var="element" items="${p_elements}" varStatus="status">
			<c:set var="subs_curMid" value="${fn:substring(p_curMid, 0, element.menuLevel * 2)}" />
			<c:set var="subs_mId" value="${fn:substring(element.mId, 0, element.menuLevel * 2)}" />
			<c:set var="isActiveMenu" value="${subs_curMid == subs_mId}" />
			<c:set var="anchor_class" value="" />
			<c:if test="${isActiveMenu}"><c:set var="anchor_class" value="on" /></c:if>
			<c:if test="${element.menuLevel == '2' && not empty element.subMenu}"><%-- <c:set var="anchor_class" value="${anchor_class} arrow" /> --%></c:if>

			<c:if test="${p_siteCode == 'health' || p_siteCode == 'teen' || p_siteCode == 'depart' || element.mId != exclusion_mid}">
				<li id="lnb_${subs_mId}" class="clFix">
					<!-- Current Level Menu BreadCrumb Flush -->
					<c:choose>
						<c:when test="${element.menuType == 'L'}">
							<a href="${element.linkUrl}" target="${element.target}" title="새창열림" <c:if test="${not empty anchor_class}">class="${anchor_class}"</c:if>><span>${element.menuName}</span></a>
						</c:when>
						<c:otherwise>
							<a href="/${p_siteCode}/contents.do?mId=${element.mId}" <c:if test="${not empty anchor_class}">class="${anchor_class}"</c:if>><span>${element.menuName}</span></a>
						</c:otherwise>
					</c:choose>

					<!-- Lower Level Menu BreadCrumb Flush  -->
					<c:if test="${not empty element.subMenu}">
						<c:choose>
							<c:when test="${isFirstDepth}">
								<div class="lnb_sub clFix">
									<c:set var="p_headTitle" value="${element.menuName}" scope="request" />
									<div class="tit">${p_headTitle}</div>
									<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
									<c:set var="p_elements" value="${element.subMenu}" scope="request" />
									<jsp:include page="headerBreadCrumb.jsp" />
								</div>
							</c:when>
							<c:otherwise>
								<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
								<c:set var="p_elements" value="${element.subMenu}" scope="request" />
								<jsp:include page="headerBreadCrumb.jsp" />
							</c:otherwise>
						</c:choose>
					</c:if>
					
					<!-- 분야별메뉴 (수정 필요함)
					<div class="lnb_sub clFix">
						<ul class="clFix">
							<li>
								<a href="#"><span class="ico"></span><span class="tit">취업/생활</span></a>
							</li>
							<li>
								<a href="#"><span class="ico"></span><span class="tit">교통정보</span></a>
							</li>
							<li>
								<a href="#"><span class="ico"></span><span class="tit">농업정보</span></a>
							</li>
							<li>	
								<a href="#"><span class="ico"></span><span class="tit">기업경제</span></a>
							</li>
							<li>
								<a href="#"><span class="ico"></span><span class="tit">체육/공원</span></a>
							</li>
							<li>
								<a href="#"><span class="ico"></span><span class="tit">청소/환경/위생</span></a>
							</li>
							<li>
								<a href="#"><span class="ico"></span><span class="tit">주택/재개발</span></a>
							</li>
							<li>
								<a href="#"><span class="ico"></span><span class="tit">재난/민방위 복지</span></a>
							</li>                                
						</ul>
					</div> -->
				</li>
			</c:if>
		</c:forEach>
	</ul>
</c:if>

<%-- 
<c:set var="exclusion_mid" value="0700000000" />

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request"/>
</c:if>
<c:if test="${empty p_curMid}">
	<c:set var="p_curMid" value="9999999999" scope="request"/>
</c:if>
<c:set var="isFirstDepth" value="${p_depth == 1}" />
<c:set var="isThirdDepth" value="${p_depth == 3}" />

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">
	<c:set var="ul_class" value="" />
	<c:if test="${isFirstDepth}">
		<c:set var="ul_class" value="lnb_nav" />
	</c:if>
	<c:if test="${isThirdDepth}">
		<c:set var="ul_class" value="${ul_class} depth03" />
	</c:if>

	<ul <c:if test="${isFirstDepth}">id="lnb_wrap"</c:if>
		<c:if test="${not empty ul_class}">class="${ul_class}"</c:if>
		<c:if test="${isThirdDepth}">style="display: none;"</c:if>
	>
		<c:forEach var="element" items="${p_elements}" varStatus="status">
			<c:set var="subs_curMid" value="${fn:substring(p_curMid, 0, element.menuLevel * 2)}" />
			<c:set var="subs_mId" value="${fn:substring(element.mId, 0, element.menuLevel * 2)}" />
			<c:set var="isActiveMenu" value="${subs_curMid == subs_mId}" />
			<c:set var="anchor_class" value="" />
			<c:if test="${isActiveMenu}"><c:set var="anchor_class" value="on" /></c:if>
			<c:if test="${element.menuLevel == '2' && not empty element.subMenu}"><c:set var="anchor_class" value="${anchor_class} arrow" /></c:if>

			<c:if test="${p_siteCode == 'health' || p_siteCode == 'teen' || p_siteCode == 'depart' || element.mId != exclusion_mid}">
				<li>
					<!-- Current Level Menu BreadCrumb Flush -->
					<c:choose>
						<c:when test="${element.menuType == 'L'}">
							<a href="${element.linkUrl}" target="${element.target}" <c:if test="${not empty anchor_class}">class="${anchor_class}"</c:if>>${element.menuName}</a>
						</c:when>
						<c:otherwise>
							<a href="/${p_siteCode}/contents.do?mId=${element.mId}" <c:if test="${not empty anchor_class}">class="${anchor_class}"</c:if>>${element.menuName}</a>
						</c:otherwise>
					</c:choose>

					<!-- Lower Level Menu BreadCrumb Flush  -->
					<c:if test="${not empty element.subMenu}">
						<c:choose>
							<c:when test="${isFirstDepth}">
								<div id="lnb_${subs_mId}_sub" class="depth0${element.menuLevel + 1}" style="display: none;">
									<div class="lnb_depth0${element.menuLevel + 1}">
										<!-- snb하위영역 타이틀및 배경 -->
										<c:set var="p_headTitle" value="${element.menuName}" scope="request" />
										<h2 class="header_snb_title">${p_headTitle}</h2>
										<span class="header_snb_bg"></span>
										<!-- snb하위영역 타이틀및 배경 끝 -->
										<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
										<c:set var="p_elements" value="${element.subMenu}" scope="request" />
										<jsp:include page="headerBreadCrumb.jsp" />
										<div class="cleB"></div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
								<c:set var="p_elements" value="${element.subMenu}" scope="request" />
								<jsp:include page="headerBreadCrumb.jsp" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</c:if> --%>