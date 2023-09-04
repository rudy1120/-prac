<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/bmc/jsp/common/taglib.jsp"%>

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request"/>
</c:if>
<c:set var="isFirstDepth"  value="${p_depth == 1}" />
<c:set var="isSecondDepth" value="${p_depth == 2}" />
<c:set var="isThirdDepth"  value="${p_depth == 3}" />

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">
	<c:set var="ul_class" value="" />
	<c:if test="${isFirstDepth}">
		<c:set var="ul_class" value="topNavMenu" />
	</c:if>
	<c:if test="${isSecondDepth}">
		<c:set var="ul_class" value="topNavSub" />
	</c:if>
	<c:if test="${isThirdDepth}">
		<c:set var="ul_class" value="NavSub" />
	</c:if>

	<ul <c:if test="${not empty ul_class and p_mId != '0200000000'}">class="${ul_class}"</c:if>
		<c:if test="${not empty ul_class and p_mId == '0200000000'}">class="${ul_class} topNavSub_3"</c:if>
		<c:if test="${isThirdDepth}"></c:if>
	>
		<c:if test="${isSecondDepth}">
			<li class="topNavTit">
				<span>${p_headTitle}</span>
				
				<p class="Tit_txt">
				<c:choose>
					<c:when test="${p_mId == '0700000000'}">부산도시공사가 만드는 부산의 <br/>중심에는 늘 시민이 있습니다.</p><p class="bgimg1"></p></c:when>
					<c:when test="${p_mId == '0100000000'}">부산도시공사의 분양·임대·보상 정보를 빠르고 쉽게 찾아보실 수 있습니다.</p><p class="bgimg2"></p></c:when>
					<c:when test="${p_mId == '0200000000'}">부산 시민의 안녕과 행복을 위한 미래의 청사진을 그립니다.</p><p class="bgimg3"></p></c:when>
					<c:when test="${p_mId == '0300000000'}">항상 고객의 입장에서 생각하고 고객의 말씀에 귀를 기울이겠습니다.</p><p class="bgimg4"></p></c:when>
					<c:when test="${p_mId == '0400000000'}">공급자 중심의 정보공개방식에서<br/>탈피하여 시민의 입장에서 필요한<br/>정보를 확대 공개하겠습니다.</p><p class="bgimg5"></p></c:when>
					<c:when test="${p_mId == '0500000000'}">시민행복도시를 위해 사람 중심의<br/>사회적 책임 가치경영을<br/>실현하겠습니다.</p><p class="bgimg6"></p></c:when>
					<c:when test="${p_mId == '0600000000'}">창의적인 도시공간 조성으로 시민의<br/>주거복지와 지역사회 발전에 기여하는<br/>부산의 대표 공공디벨로퍼입니다.</p><p class="bgimg7"></p></c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				
			</li>
		</c:if>
		
		<c:forEach var="element" items="${p_elements}" varStatus="status">
		
		<c:choose>
		<c:when test="${element.mId eq '0406000000'}">
			<li class="libottomS">
		</c:when>
		<c:when test="${element.mId eq '0312000000'}">
			<li class="libottomS120">
		</c:when>
		<c:when test="${element.mId eq '0210000000'}">
			<li class="glm">
		</c:when>
		<c:when test="${element.mId eq '0406010000' or element.mId eq '0406020000'}">
			<li style="display: none;">
		</c:when>
		<c:when test="${element.mId eq '0506000000'}">
			<li class="libottomS50">
		</c:when>
		<c:otherwise>
			<li>
		</c:otherwise>
		</c:choose>
			<c:choose>
				<c:when test="${element.menuType == 'L' && element.target == '_blank'}">
					<a href="${element.linkUrl}" target="${element.target}" title="새창열림"><span>${element.menuName}</span><i class="blank">새창</i></a>
				</c:when>
				<c:when test="${element.menuType == 'L' && element.target == '_self'}">
					<a href="${element.linkUrl}" target="${element.target}" ><span>${element.menuName}</span></a>
				</c:when>
				<c:otherwise>
					<a href="/${p_siteCode}/contents.do?mId=${element.mId}"><span>${element.menuName}</span></a>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty element.subMenu}">
				<button class="menuOpenBtn depth${p_depth}">하위메뉴 열기</button>
				<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
				<c:set var="p_elements" value="${element.subMenu}" scope="request" />
				<c:set var="p_headTitle" value="${element.menuName}" scope="request" />
				<c:set var="p_mId" value="${element.mId}" scope="request" />
				<jsp:include page="headerBody.jsp" />
			</c:if>
		</li>
		</c:forEach>
		<c:if test="${p_mId == '0200000000'}">
			<li class="mobile_none">
				<a title="녹색랜드마크 바로가기 새 창 열기" href="/bmc/apply/green.do" target="_blank" class="glm_img" style="position: absolute;right: 0px;bottom: 0px;display: inline-block; width: 211px;height: 140px;">
					<img style="position:absolute;right:0px;bottom:0px" src="/bmc/images/content/landmark_logo1.png" alt="BMC가 만든 도심 속 녹색랜드마크! 바로가기" />
				</a>
			</li>
		</c:if>
		<c:if test="${p_mId == '0401000000'}">
			<li class="mobile_none">
				<a title="클린아이 바로가기 새 창 열기" href="https://www.cleaneye.go.kr/" target="_blank" class="glm_img" style="position: absolute;right: 0px;bottom: -40px;display: inline-block;">
					<img style="position:absolute;right:0px;bottom:0px" src="/bmc/images/content/cleaneye-logo.png" alt="클린아이 부산도시공사 경영 전반에 대한 주요정보를 국민에게 제공하고 있습니다" />
				</a>
			</li>
		</c:if>		
	</ul>
</c:if>


