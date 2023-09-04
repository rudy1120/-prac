<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/bmc/jsp/common/taglib.jsp"%>

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request"/>
</c:if>
<c:set var="isFirstDepth" value="${p_depth == 1}" />
<c:set var="isSecondDepth" value="${p_depth == 2}" />
<c:set var="isThirdDepth" value="${p_depth == 3}" />
<%-- <c:set var="isFourthDepth" value="${p_depth == 4}" /> --%>
<c:set var="subyn" value="N" />

<c:set var="headTitle" value="${fn:split(activeMenu.subHeadTitle, '>')}" />
<c:if test="${isFirstDepth}">
	<c:set var="headTitle" value="${headTitle[0]}"/>
</c:if>
<c:if test="${isSecondDepth}">
	<c:set var="headTitle" value="${headTitle[1]}"/>
</c:if>
<c:if test="${isThirdDepth}">
	<c:set var="headTitle" value="${headTitle[2]}"/>
</c:if>
<%-- <c:if test="${isFourthDepth}">
	<c:set var="headTitle" value="${headTitle[3]}"/>
</c:if> --%>

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">
	<li>
		<a class="path-selected" href="#a" title="펼치기">
			${headTitle}
			<c:if test="${menuVO.mId > '0402010000' and menuVO.mId < '0403000000'}">
				<c:if test="${p_depth == 1}">정보공개</c:if>
				<c:if test="${p_depth == 2}">사전정보공표</c:if>
			</c:if>
			
				<c:if test="${menuVO.mId > '0201000000' and menuVO.mId < '0202000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">행복주택사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0202000000' and menuVO.mId < '0203000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">주거복지사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0203000000' and menuVO.mId < '0204000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">도시재생사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0204000000' and menuVO.mId < '0205000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">산업단지조성사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0205000000' and menuVO.mId < '0206000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">도시개발사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0206000000' and menuVO.mId < '0207000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">혁신도시개발사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0207000000' and menuVO.mId < '0208000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">택지개발사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0208000000' and menuVO.mId < '0209000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">주택건립사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0209000000' and menuVO.mId < '0211000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">공공건축사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0211000000' and menuVO.mId < '0212000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">기타단지조성사업</c:if>
				</c:if>
				<c:if test="${menuVO.mId > '0212000000' and menuVO.mId < '0300000000'}">
					<c:if test="${p_depth == 1}">사업안내</c:if>
					<c:if test="${p_depth == 2}">공유수면매립사업</c:if>
				</c:if>
			
			<!-- 사이트맵 추가0803000000 및 개인정보처리방침-->
			<c:if test="${menuVO.mId == '0803000000'}">
				<c:if test="${p_depth == 1}">전체메뉴</c:if>
			</c:if>
			
			<c:if test="${menuVO.mId >= '0804010100' and menuVO.mId <= '0804010500'}">
				<c:if test="${p_depth == 1}">개인정보처리방침</c:if>
			</c:if>
		
		
		</a>
		<c:choose>
			<c:when test = "${menuVO.mId == '0803000000' or (menuVO.mId >= '0804010100' and menuVO.mId <= '0804010400')}">

			</c:when>
			<c:otherwise>
				<ul class="path-depth">
					<c:forEach var="element" items="${p_elements}" varStatus="status">
						<c:set var="subs_curMid" value="${fn:substring(p_curMid, 0, element.menuLevel * 2)}" />
						<c:set var="subs_mid" value="${fn:substring(element.mId, 0, element.menuLevel * 2)}" />
						<c:set var="isActiveMenu" value="${subs_mid == subs_curMid}" />
						
					<li <c:if test="${isActiveMenu}">class="active"</c:if>>
						<c:choose>
							<c:when test="${element.menuType == 'L'}">
								<a <c:if test="${isActiveMenu}">class="active"</c:if> href="${element.linkUrl}" target="${element.target}">
									${element.menuName}
									<c:if test="${element.target == '_blank'}"><i class="blank">새창</i></c:if>
								</a>
							</c:when>
							<c:otherwise>
								<a <c:if test="${isActiveMenu}">class="active"</c:if> href="${pageContext.request.contextPath}/${p_siteCode}/contents.do?mId=${element.mId}">${element.menuName}</a>
							</c:otherwise>
						</c:choose>
						<c:if test="${isActiveMenu && not empty element.subMenu}">
							<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
							<c:set var="p_elements" value="${element.subMenu}" scope="request" />
							<c:if test="${element.mId != '0406000000' && element.mId != '0310000000'}">
								<c:set var="subyn" value="Y" />
							</c:if>
						</c:if>
					</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</li>
</c:if>

<c:if test="${subyn eq 'Y' }">
	<jsp:include page="subpathBody.jsp" />
</c:if>
