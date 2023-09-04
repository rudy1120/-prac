<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * Common Header Frame
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.08.11		김현호			최초 생성 및 퍼블리싱
	 * 2015.08.25		J.Ryeon Lee		헤더 출력 부분 컴포넌트화
	 * 2015.09.02		김현호			양산 타입으로 변경
	 * 2015.09.04		김현호			글로벌네비게이션 include작업
	 *
	 *
	 * @author 김현호
	 * @since 2015.08.11
	 */
%>

	<%-- gnb공통영역 include --%>
	<jsp:include page="/common/jsp/layouts/component/gnb.jsp" />

	<!-- HEADER BREAD CRUMBS -->
	<div id="lnbWrap" class="type02">
		<div class="layer">
			<nav>
				<h2 class="blind">메뉴구성</h2>	
				<c:set var="p_elements" value="${menus}" scope="request" />
				<c:set var="p_curMid" value="${curMid}" scope="request" />
				<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
				<c:set var="p_maxDepth" value="${3}" scope="request" />
				<c:set var="p_depth" value="${1}" scope="request" />
				<jsp:include page="/common/jsp/layouts/component/headerBreadCrumb.jsp" />
			</nav>
		</div>
	</div>
	
</header>