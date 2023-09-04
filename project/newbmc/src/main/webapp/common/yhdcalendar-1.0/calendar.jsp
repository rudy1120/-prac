<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * YH 캘린더
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.08.12		Sonys			최초 생성 및 코드 작성
	 * 2016.08.18		J.Ryeon Lee		이슈 사항 수정 및 공통 컴포넌트화
	 * 2017.01.11		김은진			안성디자인으로 수정
	 *
	 * @author Sonys
	 * @since 2016.08.12
	 */
%>
<c:set var="totalColSize" value="${3}"/>

<link rel="stylesheet" href="/common/fullcalendar-2.6.0/fullcalendar.css"/>
<link rel="stylesheet" href="/common/fullcalendar-2.6.0/fullcalendar.print.css" media="print"/>
<link rel="stylesheet" href="/common/yhdcalendar-1.0/yhdcalendar.css"/>
<script type="text/javascript" src="/common/fullcalendar-2.6.0/lib/moment.min.js"></script>
<script type="text/javascript">
	var params = {
		mId: "${menuVO.mId}",
		prefixUrl: "/${siteCodeFull}/${tableInfo.colKeyUrl}",
		fetchMonthlyDataUrl: "/${siteCodeFull}/${tableInfo.colKeyUrl}/monthly/data/fetch.do",
		fetchDailyDataUrl: "/${siteCodeFull}/${tableInfo.colKeyUrl}/daily/data/fetch.do",
		today: "${goTo}" ? "${goTo}" : moment().format("YYYY-MM-DD"),
		year: moment().format("YYYY"),
		month: moment().format("MM"),
		day: moment().format("DD"),
		menuName: "${activeMenu.menuName}",
		caption: "${tableInfo.colTitle} 건수를 일, 월, 화, 수, 목, 금, 토요일 별로 안내하는 표입니다.",
		tableGuide: "${tableInfo.colTableGuide}",
		useWriteLink: "${tableInfo.colWriteLinkYn == 'N' ? 'N' : 'Y'}",
		category: "${searchVO.category}",
		category2: "${searchVO.category2}"
	};
</script>
<script type="text/javascript" src="/common/yhdcalendar-1.0/yhdcalendar.lib.js"></script>
<script type="text/javascript" src="/common/yhdcalendar-1.0/yhdcalendar.js"></script>
<script type="text/javascript" src="/common/yhdcalendar-1.0/dataList.js"></script>

<c:if test="${not empty tableInfo.colHeaderGuide}">${tableInfo.colHeaderGuide}</c:if>

<div id="yhdCalendar" class="roll_over"></div>

<div id="loading" class="col100 taC" style="display: none;">
	<img src="/common/yhdcalendar-1.0/img/loading_b.gif" alt="로딩 중">로딩 중...
</div>
<div id="loading_failed" class="col100 taC red" style="display: none;">
	※ 데이터를 불러오는 중 오류가 발생했습니다.
</div>
<h4 id="seach_title" class="mB10">일별 현황</h4>
<div class="table_responsive">
	<p class="p_notice">모바일 사용시 표를 좌우로 이동하여 내용을 확인 할 수 있습니다.</p>
	<table class="bod_list">
		<caption>
			${disMenuName}
			<span id="day_of_list"></span>
			${tableInfo.colTableGuide}을(를) 번호, ${tableInfo.colCol1Desc}, ${tableInfo.colCol2Desc}<c:if test="${tableInfo.colCol3UseYn == 'Y'}">, ${tableInfo.colCol3Desc}</c:if>
			항목별 상세히 나타내는 표입니다.
		</caption>
		<thead class="bg-primary">
			<tr>
				<%-- <th scope="col" class="col10">번호</th> --%>
				<th scope="col" <c:if test="${not empty tableInfo.colCol1Width}">class="col${tableInfo.colCol1Width}"</c:if>>${tableInfo.colCol1Desc}</th>
				<c:if test="${not empty tableInfo.colOptCols}">
					<c:set var="colOptCols" value="${fn:split(tableInfo.colOptCols, ',')}" />
					<c:set var="colOptColNames" value="${fn:split(tableInfo.colOptColNames, ',')}" />
					<c:set var="colOptColWidths" value="${fn:split(tableInfo.colOptColWidths, ',')}" />
					<c:forEach var="colOptCol" items="${colOptCols}" varStatus="status">
						<th scope="col" <c:if test="${not empty fn:trim(colOptColWidths[status.index])}">class="col${fn:trim(colOptColWidths[status.index])}"</c:if>>${colOptColNames[status.index]}</th>
						<c:set var="totalColSize" value="${totalColSize + 1}"/>
					</c:forEach>
				</c:if>
				<th scope="col" <c:if test="${not empty tableInfo.colCol2Width}">class="col${tableInfo.colCol2Width}"</c:if>>${tableInfo.colCol2Desc}</th>
				<c:if test="${tableInfo.colCol3UseYn == 'Y'}">
					<th scope="col" class="col15">${tableInfo.colCol3Desc}</th>
					<c:set var="totalColSize" value="${totalColSize + 1}"/>
				</c:if>
			</tr>
		</thead>
		<tbody id="yhc-dataList" class="reservationList">
			<tr>
				<td colspan="${totalColSize}" style="text-align:center">
					위 달력에서 조회를 원하는 날짜를 선택하세요
				</td>
			</tr>
		</tbody>
	</table>
	</div>

<%-- <security:authorize access="hasAnyRole('ROLE_USER_TMP', 'ROLE_USER_TMP_IPIN')">
	<div class="bod_foot mB20">
		<div class="btnboxR">
			<a href="/logout?successUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="btn_list" title="본인 인증 해제">인증 취소</a>
		</div>
	</div>
</security:authorize> --%>