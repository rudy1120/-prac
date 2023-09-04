<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * YHD-Calendar 캘린더
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.08.12		Sonys			최초 생성 및 코드 작성
	 * 2016.08.18		J.Ryeon Lee		이슈 사항 수정 및 공통 컴포넌트화
	 * 2017.01.11		김은진			안성 디자인으로 수정
	 * 2017.07.06		J.Ryeon Lee		기능 고도화, 컬럼 정비에 따라 버전 업데이트
	 *
	 * @author Sonys
	 * @since 2016.08.12
	 */
%>
<link rel="stylesheet" href="/common/fullcalendar-2.6.0/fullcalendar.css"/>
<link rel="stylesheet" href="/common/fullcalendar-2.6.0/fullcalendar.print.css" media="print"/>
<link rel="stylesheet" href="/common/yhdcalendar-1.1/yhdcalendar.css"/>





<%-- ##### 안내글 ##### --%>
<c:if test="${not empty tableInfo.colHeaderGuide}">${tableInfo.colHeaderGuide}</c:if>

<%-- ##### CALENDAR ##### --%>
<div id="yhdCalendar" class="roll_over"></div>
<div id="loading" class="w100 taC" style="display: none;">
	<img src="/common/yhdcalendar-1.1/img/loading_b.gif" alt="로딩 중">로딩 중...
</div>
<div id="loading_failed" class="w100 taC red" style="display: none;">※ 데이터를 불러오는 중 오류가 발생했습니다.</div>

<%-- ##### 일별 일정 목록 ##### --%>
<c:set var="totalColSpan" value="${1}"/>
<c:set var="optKeysCnt" value="${0}"/>
<c:set var="useWidth" value="${not empty tableInfo.colTdWidths}"/>
<c:if test="${useWidth}"><c:set var="widths" value="${fn:split(tableInfo.colTdWidths, ',')}"/></c:if>

<div id="dailySchedule">
	<h4 id="search_tit">일별 현황</h4>
	<p class="p_notice">모바일 사용시 표를 좌우로 이동하여 내용을 확인 할 수 있습니다.</p>
	<table class="bod_list">
		<caption>
			${disMenuName}
			<span id="day_of_list"></span>
			${tableInfo.colCaptionTitle}을(를)
			<c:if test="${tableInfo.colNoYn == 'Y'}">번호,</c:if>
			${tableInfo.colTitleDesc}
			<c:if test="${not empty tableInfo.colOptDescs}">, ${tableInfo.colOptDescs}</c:if>
			<c:if test="${tableInfo.colDateYn == 'Y'}">, 일자</c:if>
			항목별 상세히 나타내는 표입니다.
		</caption>
		<thead>
			<tr>
				<%-- ### 번호 ###--%>
				<c:if test="${tableInfo.colNoYn == 'Y'}">
					<th scope="col" class="list_num w${useWidth && widths[0] != '-' ? widths[0] : '8'}">번호</th>
					<c:set var="totalColSpan" value="${totalColSpan + 1}"/>
				</c:if>
				<%-- ### 제목 ###--%>
				<th scope="col" <c:if test="${useWidth && widths[1] != '-'}">class="w${widths[1]}"</c:if>>${tableInfo.colTitleDesc}</th>
				<%-- ### 추가 항목(dmt_yhd_calendar.col_opt_keys 설정값) ###--%>
				<c:if test="${not empty tableInfo.colOptKeys}">
					<c:set var="optDescs" value="${fn:split(tableInfo.colOptDescs, ',')}" />
					<c:forEach var="optDesc" items="${optDescs}" varStatus="status">
						<th scope="col" <c:if test="${useWidth && widths[2 + status.index] != '-'}">class="w${widths[2 + status.index]}"</c:if>>${optDesc}</th>
						<c:set var="totalColSpan" value="${totalColSpan + 1}"/>
					</c:forEach>
					<c:set var="optKeysCnt" value="${fn:length(optDescs) - 1}"/>
				</c:if>
				<%-- ### 시작/종료일 ### --%>
				<c:if test="${tableInfo.colDateYn == 'Y'}">
					<th scope="row" <c:if test="${useWidth && widths[3 + optKeysCnt] != '-'}">class="w${widths[3 + optKeysCnt]}"</c:if>>일자</th>
					<c:set var="totalColSpan" value="${totalColSpan + 1}"/>
				</c:if>
				<%-- ### 승인 상태 ### --%>
				<c:if test="${not empty tableInfo.colStateKey}">
					<th scope="row" <c:if test="${useWidth && widths[4 + optKeysCnt] != '-'}">class="w${widths[4 + optKeysCnt]}"</c:if>>상태</th>
					<c:set var="totalColSpan" value="${totalColSpan + 1}"/>
				</c:if>
			</tr>
		</thead>
		<tbody id="yhc-dataList" class="reservationList">
			<tr>
				<td colspan="${totalColSpan}" style="text-align:center">
					위 달력에서 조회를 원하는 날짜를 선택하세요
				</td>
			</tr>
		</tbody>
	</table>
</div>

<c:if test="${not empty tableInfo.colStateKey && not empty yh:getUser()}">
	<div class="bod_foot mB20">
		<div class="btnboxR">
			<a href="/logout?successUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="btn_list" title="본인 인증 해제">인증 취소</a>
		</div>
	</div>
</c:if>

<script type="text/javascript" src="/common/fullcalendar-2.6.0/lib/moment.min.js"></script>
<script type="text/javascript">
	var params = {
		mId: "${menuVO.mId}",
		prefixUrl: "/${siteCodeFull}/${tableInfo.colKeyUrl}",
		today: "${goTo}" ? "${goTo}" : moment().format("YYYY-MM-DD"),
		year: moment().format("YYYY"),
		month: moment().format("MM"),
		day: moment().format("DD"),
		menuName: "${activeMenu.menuName}",
		tableInfo: $.parseJSON("${tableInfoJson}"),
		totalColSpan: "${totalColSpan}"
	};
</script>
<script type="text/javascript" src="/common/yhdcalendar-1.1/yhdcalendar.lib.js"></script>
<script type="text/javascript" src="/common/yhdcalendar-1.1/yhdcalendar.js"></script>
<script type="text/javascript" src="/common/yhdcalendar-1.1/dataList.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#yhdCalendar").yhdCalendar({ // 캘린터 초기화
			url: params.prefixUrl + "/monthly/data/fetch.do",
			today: params.today,
			scheduleClick: function(obj) { daily.list(obj.date); },
		});

		daily.list(params.today); // 오늘 일자 일정 조회/표시
	});
</script>
