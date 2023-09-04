<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 통계
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.01.22		엄동건			최초 생성 및 코딩
	 * 2015.01.22		김현호			디자인 코딩
	 * 2016.05.17		J.Ryeon Lee		첨부파일 통계 추가
	 * 2017.10.17		J.Ryeon Lee		첨부파일/게시물 통계 숨김 처리(게시판 관리 아래 별도 메뉴 생성)
	 *
	 *
	 *
	 * @author 엄동건
	 * @since 2015.01.22
	 */
%>
<script type="text/javascript">
//<![CDATA[
jQuery(function(jQuery) {

	jQuery.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전',
		nextText: '다음',
		currentText: '오늘',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '년',
		onSelect: function( selectedDate ) {
			var option = this.id == "dateStart" ? "minDate" : "maxDate",
			instance = $( this ).data( "datepicker" ),
			date = $.datepicker.parseDate(
			instance.settings.dateFormat || $.datepicker._defaults.dateFormat,
			selectedDate, instance.settings );
			jQuery( "#dateStart, #dateEnd" ).not( this ).datepicker( "option", option, date );
		}
		};
	jQuery.datepicker.setDefaults(jQuery.datepicker.regional['ko']);

	jQuery( "#dateStart" ).datepicker();
	jQuery( "#dateEnd" ).datepicker();
	$("#ui-datepicker-div").hide();
});



function InfoSubmit(){
	var frm = $('#frm');

	if($(".radio:checked").val() == "3") {

		if($('#dateStart').val() == "") {
			alert("시작일을 입력하세요.");
			$("#dateStart").focus();
			return false;
		}
		if($("#dateEnd").val() == "") {
			alert("종료일을 입력하세요.");
			$("#dateEnd").focus();
			return false;
		}
	}

	frm.action = "${pageContext.request.contextPath}/sys/stateMng/list.do?mId=${menuVO.mId}";
	frm.submit();
}

//]]>
</script>

<%-- 파라미터 셋팅 --%>
<c:set var="year" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${year}" pattern="yyyy" var="year"/>
<c:set var="intervalYear" value="${year-2015}" />

<c:set var="descStats" value="사이트" />
<c:if test="${inputVo.kind == 1 || inputVo.kind == 7 }"><c:set var="descStats" value="메뉴" /></c:if>

<div class="stats_total_box">
	<ul class="stats_total_list">
		<li>
			<div style="padding-top:30px;">
				<p>총 접속자수</p>
				<fmt:formatNumber type="number" groupingUsed="true" value="${totalCnt.FULL}"/>
			</div>
		</li>
		<li>
			<div style="padding-top:30px;">
				<p>${year}년 접속자 수</p>
				<fmt:formatNumber type="number" groupingUsed="true" value="${totalCnt.YEAR}"/>
			</div>
		</li>
		<li>
			<div style="padding-top:30px;">
				<p>이번달 접속자 수</p>
				<fmt:formatNumber type="number" groupingUsed="true" value="${totalCnt.MONTH}"/>
			</div>
		</li>
		<li>
			<div style="padding-top:30px;">
				<p>오늘 접속자 수</p>
				<fmt:formatNumber type="number" groupingUsed="true" value="${totalCnt.DAY}"/>
			</div>
		</li>
	</ul>
	<div class="cleB"></div>
</div>

<%-- <h2>${menuVO.menuName}</h2> --%>

	<form name="frm" id="frm" action="/sys/stateMng/list.do?mId=${menuVO.mId}" method="post">
		<input type="hidden" id="kind" name="kind" value="${inputVo.kind}">

		<!-- tab start -->
		<div class="tab">
			<ul>
				<li><a href="#" data-kind="0" id="btn0" >사이트</a></li>
				<li><a href="#" data-kind="6" id="btn6" >많이찾는 사이트</a></li>
				<li><a href="#" data-kind="1" id="btn1">메뉴</a></li>
				<li><a href="#" data-kind="7" id="btn6" >많이찾는 메뉴</a></li>
<!-- 				<li><a href="#" data-kind="2" id="btn2">외부유입</a></li> -->
				<li><a href="#" data-kind="3" id="btn3">브라우저</a></li>
<!-- 				<li><a href="#" data-kind="5" id="btn5">게시물</a></li> -->
<!-- 				<li><a href="#" data-kind="4" id="btn4">첨부파일</a></li> -->
			</ul>
			<div class="cleB"></div>
		</div>

		<div class="search_box">
			<div class="fL mR20">
				<%-- 사이트/메뉴 세부 목록에 대한 출력 --%>
				<label for="siteCode" style="display:none;">사이트분류</label>

				<select id="siteCode" name="siteCode">
					<c:if test="${inputVo.kind == 0 || inputVo.kind == 6 || inputVo.kind == 7}"><option value="">전체</option></c:if>
					<c:if test="${not empty listSite}">
						<c:forEach items="${listSite}" varStatus="status" var="site">
							<c:if test="${site.siteCode ne 'history'}">
							<option value="${site.siteCode}" <c:if test="${site.siteCode eq inputVo.siteCode}">selected="selected"</c:if>>${site.siteName}</option>
							</c:if>
						</c:forEach>
					</c:if>
				</select>

				<c:if test="${inputVo.kind == '1'}">
					<label for="menuId" style="display:none;">메뉴분류</label>
					<select id="menuId" name="menuId" style="min-width: 100px; ${empty listMenu ? 'display: none;' : ''}">
						<c:if test="${not empty listMenu }">
						<c:forEach items="${listMenu}" varStatus="status" var="menu">
							<option value="${menu.mId}" <c:if test="${menu.mId eq inputVo.menuId}">selected="selected"</c:if>>
								<c:if test="${not empty menu.parentName}">[${menu.parentName}]</c:if>
								${menu.menuName}
							</option>
						</c:forEach>
						</c:if>
					</select>
				</c:if>
			</div>

			<div class="search_radio">
				<c:choose>
					<c:when test="${inputVo.kind == 4 || inputVo.kind == 5}"><%-- 20160517 J.Ryeon Lee 게시물 & 첨부파일 통계 검색박스 --%>
						<select name="year" class="checkList" onchange="$('#dateStart').removeAttr('value'); $('#dateEnd').removeAttr('value');">
							<option value="">전체</option>
							<c:forEach begin="2019" end="${thisYear}" var="yearValue" step="1">
								<option value="${yearValue}" <c:if test="${yearValue == inputVo.year}">selected="selected"</c:if>>${yearValue}</option>
							</c:forEach>
						</select>
						<select name="month" class="checkList" onchange="$('#dateStart').removeAttr('value'); $('#dateEnd').removeAttr('value');">
							<option value="">전체</option>.
							<c:forEach begin="1" end="12" var="i" step="1">
								<fmt:formatNumber var="tmpM" value="${i}" pattern="00"/>
								<option value="${tmpM}" <c:if test="${tmpM == inputVo.month}">selected="selected"</c:if>>${tmpM}월</option>
							</c:forEach>
						</select>
					</c:when>
					<c:otherwise>
						<input type="radio" name="sType" class="radio" id="sType1" value="1" <c:if test="${1 == inputVo.sType}">checked="checked"</c:if>/><label for="sType1">년</label>
						<input type="radio" name="sType" class="radio" id="sType2" value="2" <c:if test="${2 == inputVo.sType}">checked="checked"</c:if>/><label for="sType2">월</label>
						<input type="radio" name="sType" class="radio" id="sType3" value="3" <c:if test="${3 == inputVo.sType}">checked="checked"</c:if>/><label for="sType3">기간</label>

						<select name="year" id="year" class="checkList">
							<c:forEach begin="2019" end="${thisYear}" var="yearValue" step="1">
								<option value="${yearValue}" <c:if test="${yearValue == inputVo.year}">selected="selected"</c:if>>${yearValue}</option>
							</c:forEach>
						</select>

						<select name="month" class="checkList" id="month">
							<c:forEach begin="1" end="12" var="i" step="1">
								<fmt:formatNumber var="tmpM" value="${i}" pattern="00"/>
								<option value="${tmpM}" <c:if test="${tmpM == inputVo.month}">selected="selected"</c:if>>${i}</option>
							</c:forEach>
						</select>
					</c:otherwise>
				</c:choose>
				<span id="dateStEn" class="checkList">
					<input type="text" id="dateStart" name="dateStart" class="checkList input100" value="${inputVo.dateStart}" /> ~
					<input type="text" id="dateEnd" name="dateEnd" class="checkList input100" value="${inputVo.dateEnd}" />
				</span>
				<input type="submit" onclick="$('#downYn').val(''); InfoSubmit(); return false;" class="btn_white" value="검색"/>
				<input type="submit" onclick="$('#downYn').val('Y'); InfoSubmit(); return false;" class="btn_white" value="검색 조건 다운로드"/>
<%-- 				<a href="/sys/stateMng/download.do?kind=${inputVo.kind}&mId=${menuVO.mId}" class="btn_white" target="_blank">다운로드</a> --%>
			</div>
			<div class="cleB"></div>
		</div>
		<input type="hidden" id="downYn" name="downYn" value="" />
	</form>

	<%-- 사이트/메뉴 검색시 노출 --%>
	<c:if test="${inputVo.kind < 2}">
		<table class="tableSt_write" id="descStats">
			<caption>방문자 요약</caption>
			<colgroup>
				<col span="1" style="width:20%;"/>
				<col span="2" style="width:40%;"/>
			</colgroup>
			<tbody>
				<tr>
					<th rowspan="2">${descStats} 방문자 요약</th>
					<td>총 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.FULL}"/></span></td>
					<td>${year}년 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.YEAR}"/></span></td>
				</tr>
				<tr>
					<td>이번달 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.MONTH}"/></span></td>
					<td>오늘 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.DAY}"/></span></td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<%-- 20160517 J.Ryeon Lee ADD 첨부파일 통계의 경우 노출 --%>
	<c:if test="${inputVo.kind == 4}">
		<table class="tableSt_write" id="descStats">
			<caption>첨부파일 이용자 요약</caption>
			<colgroup>
				<col span="1" style="width:20%;"/>
				<col span="2" style="width:40%;"/>
			</colgroup>
			<tbody>
				<tr>
					<th rowspan="2">첨부파일 이용자 요약</th>
					<td>총 이용자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.FULL}"/></span></td>
					<td>${year}년 이용자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.YEAR}"/></span></td>
				</tr>
				<tr>
					<td>이번달 이용자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.MONTH}"/></span></td>
					<td>오늘 이용자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.DAY}"/></span></td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<%-- 20160518 J.Ryeon Lee ADD 게시물 통계의 경우 노출 --%>
	<c:if test="${inputVo.kind == 5}">
		<table class="tableSt_write" id="descStats">
			<caption>게시물 열람자 요약</caption>
			<colgroup>
				<col span="1" style="width:20%;"/>
				<col span="2" style="width:40%;"/>
			</colgroup>
			<tbody>
				<tr>
					<th rowspan="2">게시물 열람자 요약</th>
					<td>총 열람자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.FULL}"/></span></td>
					<td>${year}년 열람자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.YEAR}"/></span></td>
				</tr>
				<tr>
					<td>이번달 열람자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.MONTH}"/></span></td>
					<td>오늘 열람자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${userStat.DAY}"/></span></td>
				</tr>
			</tbody>
		</table>
	</c:if>

	<%-- 20180703  상천규   ADD 많이찾는 사이트 통계의 경우 노출 --%>
	<c:if test="${inputVo.kind == 6}">
		<table class="tableSt_write" id="descStats">
			<caption>전체 사이트 방문자 요약</caption>
			<colgroup>
				<col span="1" style="width:25%;"/>
				<col span="1" />
				<col span="1" style="width:30%;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">전체 사이트 방문자 요약</th>
					<th colspan="2" scope="col">많이 찾는 사이트</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>총 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.FULL}"/></span></td>
					<td rowspan="6">
						<div id="chartdiv" style="width: 100%;height: 400px;"></div>
					</td>
					<c:choose>
						<c:when test="${not empty results && inputVo.kind == 6}">
					<script src="/common/amcharts/amcharts.js" type="text/javascript"></script>
					<script src="/common/amcharts/pie.js" type="text/javascript"></script>
					<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
					<script type="text/javascript">
								<c:set var="pieColor" value="" />
								<c:set var="siteName" value="" />
								<c:set var="litres" value="" />
								<c:set var="etcPie" value="0" />

								var chartData = [
							<c:forEach items="${results}" var="result" varStatus="status">
								<c:if test="${status.index < 5 }">
									<c:set var="siteName" value="${result.siteName}" />
									<c:if test="${status.index > 0}">,</c:if>
									<c:set var="litres" value="${result.statsCount}" />
									{
										"siteName": "${siteName}",
										"litres": ${litres}
									 }
								</c:if>

								<c:if test="${status.index > 4 }">
									<c:set var="etcPie" value="${etcPie + result.statsCount}" />
								</c:if>

							</c:forEach>
								,{
									"siteName": "기타",
									"litres": ${etcPie}
								 }
								]

								var chart = AmCharts.makeChart("chartdiv", {
									  "type": "pie",
									  "theme": "light",
									  "dataProvider": chartData,
									  "valueField": "litres",
									  "titleField": "siteName",
									  "precision": 2,
									  "showZeroSlices": true
									});


								$(document).ready(function() {
									jQuery(".amcharts-chart-div > a").remove();
								});
// 								AmCharts.ready(function () {
// 									// PIE CHART
// 									var chart = new AmCharts.AmPieChart();
// 									chart.type = "pie";
// 									chart.titleField = "siteName";
// 									chart.valueField = "litres";
// 									chart.dataProvider = chartData;

// 									var balloon = chart.balloon;
// 									balloon.fixedPosition = true;

// 									// WRITE
// 									chart.write("chartdiv");
// 								});
					</script>
						</c:when>
						<c:otherwise>
					<script type="text/javascript">
						window.onload = function () {
							$("#chartdiv").text("등록된 방문기록이 없습니다.");
						}
					</script>
						</c:otherwise>
					</c:choose>
					<td>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:if test="${status.index eq 0 }">
								<c:set var="percent" value="${(result.statsCount /inputVo.statsCountMax)*100}"/>
								<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
								<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
								${result.siteName} (<fmt:formatNumber value="${result.statsCount}" pattern="#,###" />건 / ${percent} %)
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>${year}년 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCnt.YEAR}"/></span></td>
					<td>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:if test="${status.index eq 1 }">
								<c:set var="percent" value="${(result.statsCount /inputVo.statsCountMax)*100}"/>
								<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
								<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
								${result.siteName} (<fmt:formatNumber value="${result.statsCount}" pattern="#,###" />건 / ${percent} %)
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>이번달 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCnt.MONTH}"/></span></td>
					<td>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:if test="${status.index eq 2 }">
								<c:set var="percent" value="${(result.statsCount /inputVo.statsCountMax)*100}"/>
								<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
								<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
								${result.siteName} (<fmt:formatNumber value="${result.statsCount}" pattern="#,###" />건 / ${percent} %)
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>오늘 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCnt.DAY}"/></span></td>
					<td>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:if test="${status.index eq 3 }">
								<c:set var="percent" value="${(result.statsCount /inputVo.statsCountMax)*100}"/>
								<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
								<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
								${result.siteName} (<fmt:formatNumber value="${result.statsCount}" pattern="#,###" />건 / ${percent} %)
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:if test="${status.index eq 4 }">
								<c:set var="percent" value="${(result.statsCount /inputVo.statsCountMax)*100}"/>
								<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
								<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
								${result.siteName} (<fmt:formatNumber value="${result.statsCount}" pattern="#,###" />건 / ${percent} %)
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<c:set var="etcTotal" value="0"/>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:if test="${status.index > 4 }">
								<c:set var="etcTotal" value="${etcTotal + result.statsCount }"/>
								<c:set var="percent" value="${(etcTotal /inputVo.statsCountMax)*100}"/>
							</c:if>
						</c:forEach>
<%-- 						<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
						<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
						기타 (<fmt:formatNumber value="${etcTotal}" pattern="#,###" />건 / ${percent} %) --%>
						<fmt:formatNumber value="${etcTotal}" pattern="0.00" var="percent"/>
						<div class="graph"><span style="width:${etcTotal*0.9}%;"></span></div>
						기타 (<fmt:formatNumber value="${etcTotal}" pattern="#,###" />건 / ${etcTotal} %)
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>





	<c:if test="${inputVo.kind == 7}">
		<table class="tableSt_write" id="descStats">
			<caption>방문자 요약</caption>
			<colgroup>
				<col span="1" style="width:20%;"/>
				<col span="2" style="width:40%;"/>
			</colgroup>
			<tbody>
				<tr>
					<th rowspan="2">${descStats} 방문자 요약</th>
					<td>총 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.FULL}"/></span></td>
					<td>${year}년 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.YEAR}"/></span></td>
				</tr>
				<tr>
					<td>이번달 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.MONTH}"/></span></td>
					<td>오늘 접속자 수 : <span class="red"><fmt:formatNumber type="number" groupingUsed="true" value="${totalCntKind.DAY}"/></span></td>
				</tr>
			</tbody>
		</table>
	</c:if>

	<table class="tableSt_list row_over mT20">
		<caption>상세요약 리스트</caption>
		<colgroup>
			<c:if test="${inputVo.kind < 6}">
				<col span="1" style="width:20%;"/>
				<col span="1" />
			</c:if>
			<c:if test="${inputVo.kind == 6}">
				<col span="1" style="width:10%;"/>
				<col span="1" style="width:20%;" />
				<col span="1" style="width:20%;" />
				<col span="1" />
			</c:if>
			<c:if test="${inputVo.kind == 7}">
				<col span="1" style="width:7%;"/>
				<col span="1" style="width:15%;" />
				<col span="1" style="width:15%;" />
				<col span="1" style="width:20%;" />
				<col span="1" style="width:10%;" />
				<col span="1" />
			</c:if>
		</colgroup>
		<thead>
			<tr>
			<c:if test="${inputVo.kind < 6}">
				<th colspan="2">상세 요약</th>
			</c:if>
			<c:if test="${inputVo.kind == 6}">
				<th>순위</th>
				<th>사이트명</th>
				<th>방문자수</th>
				<th>비율</th>
			</c:if>
			<c:if test="${inputVo.kind == 7}">
				<th>순위</th>
				<th>사이트구분</th>
				<th>1차메뉴명</th>
				<th>메뉴경로</th>
				<th>방문자수</th>
				<th>비율</th>
			</c:if>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty results}">
				<c:if test="${inputVo.kind < 6}">
				<tr>
					<td><strong>합계</strong></td>
					<td class="taL"><span id="result"></span>건</td>
				</tr>
				</c:if>
				<c:choose>
					<c:when test="${inputVo.kind > 1}"><c:set var="str" value=""/></c:when>
					<c:when test="${inputVo.sType==1}"><c:set var="str" value="월"/></c:when>
					<c:when test="${inputVo.sType==2}"><c:set var="str" value="일"/></c:when>
					<c:when test="${inputVo.sType==3}"><c:set var="str" value=""/></c:when>
					<c:otherwise><c:set var="str" value="월"/></c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${inputVo.kind == 4 || inputVo.kind == 5}"><%-- 20160517 J.Ryeon Lee ADD 게시물 & 첨부파일 통계 --%>
						<fmt:formatNumber value="${userStat.FULL}" pattern="0.00" var="fTotal"/>
						<c:forEach var="element" items="${results}">
							<tr>
								<td>
									<c:choose>
										<c:when test="${not empty inputVo.dateStart}">${inputVo.dateStart} ~ ${inputVo.dateEnd}</c:when>
										<c:when test="${not empty inputVo.year && not empty inputVo.month}">${element.DDAY}일</c:when>
										<c:when test="${not empty inputVo.year}">${element.DMONTH}월</c:when>
										<c:otherwise>${element.DYEAR}년</c:otherwise>
									</c:choose>
								</td>
								<td class="taL poll_list">
									<fmt:formatNumber value="${element.CNT}" pattern="0.00" var="fCnt"/>
									<fmt:formatNumber value="${(fCnt / fTotal) * 100}" pattern="0.00" var="fileDownPercent"/>
									<div class="graph"><span style="width:${fileDownPercent}%;"></span></div>
									<b>${fileDownPercent}%</b>&nbsp;(<span class="stateSum" data-value="${element.CNT }"></span>${element.CNT}건)
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:when test="${inputVo.kind == 3}"><%-- 브라우저 통계 ie 및 기타 브라우저 - 본부장님 의견 --%>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:choose>
								<c:when test="${result.statsLabel eq 'IE6'}">
									<c:set var="statsLabelIE6" value="${result.statsLabel}"/>
									<c:set var="percentIE6" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentIE6}" pattern="0.00" var="percentIE6"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountIE6" />
									<c:set var="CountIE6" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'IE7'}">
									<c:set var="statsLabelIE7" value="${result.statsLabel}"/>
									<c:set var="percentIE7" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentIE7}" pattern="0.00" var="percentIE7"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountIE7" />
									<c:set var="CountIE7" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'IE8'}">
									<c:set var="statsLabelIE8" value="${result.statsLabel}"/>
									<c:set var="percentIE8" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentIE8}" pattern="0.00" var="percentIE8"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountIE8" />
									<c:set var="CountIE8" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'IE9'}">
									<c:set var="statsLabelIE9" value="${result.statsLabel}"/>
									<c:set var="percentIE9" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentIE9}" pattern="0.00" var="percentIE9"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountIE9" />
									<c:set var="CountIE9" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'IE10'}">
									<c:set var="statsLabelIE10" value="${result.statsLabel}"/>
									<c:set var="percentIE10" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentIE10}" pattern="0.00" var="percentIE10"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountIE10" />
									<c:set var="CountIE10" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'IE11'}">
									<c:set var="statsLabelIE11" value="${result.statsLabel}"/>
									<c:set var="percentIE11" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentIE11}" pattern="0.00" var="percentIE11"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountIE11" />
									<c:set var="CountIE11" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'Chrome'}">
									<c:set var="statsLabelChrome" value="${result.statsLabel}"/>
									<c:set var="percentChrome" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentChrome}" pattern="0.00" var="percentChrome"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountChrome" />
									<c:set var="CountChrome" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'FireFox'}">
									<c:set var="statsLabelFireFox" value="${result.statsLabel}"/>
									<c:set var="percentFireFox" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentChrome}" pattern="0.00" var="percentFireFox"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountFireFox" />
									<c:set var="CountFireFox" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'Safari'}">
									<c:set var="statsLabelSafari" value="${result.statsLabel}"/>
									<c:set var="percentSafari" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentSafari}" pattern="0.00" var="percentSafari"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountSafari" />
									<c:set var="CountSafari" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'Opera'}">
									<c:set var="statsLabelOpera" value="${result.statsLabel}"/>
									<c:set var="percentOpera" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentOpera}" pattern="0.00" var="percentOpera"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountOpera" />
									<c:set var="CountOpera" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq 'mobile'}">
									<c:set var="statsLabelMobile" value="${result.statsLabel}"/>
									<c:set var="percentMobile" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentMobile}" pattern="0.00" var="percentMobile"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountMobile" />
									<c:set var="CountMobile" value="${result.statsCount}"/>
								</c:when>
								<c:when test="${result.statsLabel eq '기타'}">
									<c:set var="statsLabelETC" value="${result.statsLabel}"/>
									<c:set var="percentETC" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percentETC}" pattern="0.00" var="percentETC"/>
									<fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" var="statsCountETC" />
									<c:set var="CountETC" value="${result.statsCount}"/>
								</c:when>
							</c:choose>

						</c:forEach>
						<c:if test="${not empty statsLabelIE6}">
						<tr>
							<td>${statsLabelIE6}</td>
							<td class="taL poll_list">
								<c:set var="percent2" value="${fn:substring(percentIE6*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentIE6} %</b> (<span class="stateSum" data-value="${CountIE6 }"></span>${statsCountIE6}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelIE7}">
						<tr>
							<td>${statsLabelIE7}</td>
							<td class="taL poll_list">
								<c:set var="percent2" value="${fn:substring(percentIE7*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentIE7} %</b> (<span class="stateSum" data-value="${CountIE7 }"></span>${statsCountIE7}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelIE8}">
						<tr>
							<td>${statsLabelIE8}</td>
							<td class="taL poll_list">
								<c:set var="percent2" value="${fn:substring(percentIE8*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentIE8} %</b> (<span class="stateSum" data-value="${CountIE8 }"></span>${statsCountIE8}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelIE9}">
						<tr>
							<td>${statsLabelIE9}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentIE9*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentIE9} %</b> (<span class="stateSum" data-value="${CountIE9 }"></span>${statsCountIE9}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelIE10}">
						<tr>
							<td>${statsLabelIE10}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentIE10*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentIE10} %</b> (<span class="stateSum" data-value="${CountIE10 }"></span>${statsCountIE10}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelIE11}">
						<tr>
							<td>${statsLabelIE11}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentIE11*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentIE11} %</b> (<span class="stateSum" data-value="${CountIE11 }"></span>${statsCountIE11}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelChrome}">
						<tr>
							<td>${statsLabelChrome}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentChrome*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentChrome} %</b> (<span class="stateSum" data-value="${CountChrome }"></span>${statsCountChrome}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelFireFox}">
						<tr>
							<td>${statsLabelFireFox}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentFireFox*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentFireFox} %</b> (<span class="stateSum" data-value="${CountFireFox }"></span>${statsCountFireFox}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelSafari}">
						<tr>
							<td>${statsLabelSafari}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentSafari*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentSafari} %</b> (<span class="stateSum" data-value="${CountSafari }"></span>${statsCountSafari}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelOpera}">
						<tr>
							<td>${statsLabelOpera}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentOpera*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2}%;"></span></div>
								<b>${percentOpera} %</b> (<span class="stateSum" data-value="${CountOpera }"></span>${statsCountOpera}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelMobile}">
						<tr>
							<td>${statsLabelMobile}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentMobile*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2*0.9}%;"></span></div>
								<b>${percentMobile} %</b> (<span class="stateSum" data-value="${CountMobile }"></span>${statsCountMobile}건)
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty statsLabelETC}">
						<tr>
							<td>${statsLabelETC}</td>
							<td class="taL poll_list">
							<c:set var="percent2" value="${fn:substring(percentETC*0.9,0,4)}"/>
								<div class="graph"><span style="width:${percent2*0.9}%;"></span></div>
								<b>${percentETC} %</b> (<span class="stateSum" data-value="${CountETC }"></span>${statsCountETC}건)
							</td>
						</tr>
						</c:if>
					</c:when>

					<c:when test="${inputVo.kind == 6}"><%-- 많이 찾는 사이트 --%>
						<c:set var="resultTotal" value="0"/>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:set var="resultTotal" value="${resultTotal + result.statsCount }"/>
						</c:forEach>
						<tr>
							<td>전체</td>
							<td>전체</td>
							<td><fmt:formatNumber type="number" groupingUsed="true" value="${resultTotal}"/></td>
							<td class="taL poll_list">
								<div class="graph"><span style="width:100%;"></span></div>
								<b>100 %</b>
							</td>
						</tr>
						<c:forEach items="${results}" var="result" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${result.siteName}</td>
								<td><fmt:formatNumber value="${result.statsCount}" pattern="#,###" /></td>
								<td class="taL poll_list">
									<c:set var="percent" value="${(result.statsCount /inputVo.statsCountMax)*100}"/>
									<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
									<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
									<b>${percent} %</b>
								</td>
							</tr>
						</c:forEach>
					</c:when>

					<c:when test="${inputVo.kind == 7}"><%-- 많이 찾는 메뉴 --%>
						<c:set var="resultTotal" value="0"/>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:set var="resultTotal" value="${resultTotal + result.statsCount }"/>
						</c:forEach>
					<%-- 	<tr>
							<td>전체</td>
							<td>전체</td>
							<td>전체</td>
							<td>전체</td>
							<td><fmt:formatNumber type="number" groupingUsed="true" value="${resultTotal}"/></td>
							<td class="taL poll_list">
								<div class="graph"><span style="width:100%;"></span></div>
								<b>100 %</b>
							</td>
						</tr> --%>
						<c:forEach items="${results}" var="result" varStatus="status">
							<c:set var="firstDepthName" value="${fn:split(result.menuPath, '&gt;') }"/>
							<tr>
								<td>${status.count}</td>
								<td>${result.siteName}</td>
								<td>${firstDepthName[0]}</td>
								<td>${result.menuPath}</td>
								<td><fmt:formatNumber value="${result.statsCount}" pattern="#,###" /></td>
								<td class="taL poll_list">
									<c:set var="percent" value="${(result.statsCount /inputVo.statsCountMax)*100}"/>
									<c:set var="percent2" value="${fn:substring(percent*0.9,0,4)}"/>
									<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
									<div class="graph"><span style="width:${percent2}%;"></span></div>
									<b>${percent2} %</b>
								</td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<c:forEach items="${results}" var="result" varStatus="status">
							<tr>
								<td>${result.statsLabel} ${str}</td>
								<td class="taL poll_list">
									<c:set var="percent" value="${result.statsCount /inputVo.statsCountMax*100}"/>
									<fmt:formatNumber value="${percent}" pattern="0.00" var="percent"/>
									<div class="graph"><span style="width:${percent*0.9}%;"></span></div>
									<b>${percent} %</b> (<span class="stateSum" data-value="${result.statsCount }"></span><fmt:formatNumber type="number" groupingUsed="true" value="${result.statsCount}" />건)
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>

				</c:choose>
			</c:if>

			<!-- 내용이없을경우 -->
			<c:if test="${empty results}">
				<tr>
					<td colspan="2">등록된 기록이 없습니다.</td>
				</tr>
			</c:if>

		</tbody>
	</table>


<script type="text/javascript">
<c:if test="${not empty results}">
$(document).ready(function() {
	var sum = $(".stateSum").toArray().reduce(function(s, e) {
		return s + Number($(e).data("value"));
	}, 0);
	$("#result").text(sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
});
</c:if>
$(function(){
	//로그 분류 탭 처리
	$("a[id^='btn']").filter("[data-kind="+$("#kind").val()+"]").addClass("on");
	if($("#kind").val()==1) $("#menuId").show();

	switch($("#kind").val()){
		case '0':
			$("#siteCode").show();
		break;
		case '1':
			$("#siteCode, #menuId").show();
		break;
		case '7' :
			$("#siteCode").show();
		break;
		default:
			$("#siteCode, #menuId").hide();
		break;
	}

	//로그 분류 탭 처리 - event
	$("a[id^='btn']").click(function(){
		$(this).addClass("on").siblings().each(function(){
			$(this).removeClass("on");
		});
		_kind=$(this).data("kind");
		$("#kind").val(_kind);

		switch(_kind){
			case 0:
				$("#siteCode").show();
				$("#menuId").hide();
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}"
				break;
			case 1:
				$("#siteCode,#menuId").show();
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}&kind=1"
				break;
			case 2:
				$("#siteCode, #menuId").hide();
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}&kind=2"
				break;
			case 3:
				$("#siteCode, #menuId").hide();
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}&kind=3"
				break;
			case 4:
				$("#year").removeAttr("value");
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}&kind=4";
				break;
			case 5:
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}&kind=5";
				break;
			case 6:
				$("#siteCode, #menuId").hide();
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}&kind=6";
				break;
			case 7:
				$("#siteCode").show();
				$("#menuId").hide();
				location.href = "/sys/stateMng/list.do?mId=${menuVO.mId}&kind=7";
				break;
		}

		return false;
	});


	//기간단위 노출처리
	switch($(".radio:checked").val()){
		case "1":
			$("#year").show().siblings(".checkList").hide();
			break;
		case "2":
			$("#dateStEn").hide().siblings(".checkList").show();
			break;
		case "3":
			$("#dateStEn").show().siblings(".checkList").hide();
			break;
	}

	//기간단위 노출처리 - event
	$(".radio").change(function(){
		//기간단위 변경시
		switch($(".radio:checked").val()){
			case "1":
				$("#year").show().siblings(".checkList").hide();
				break;
			case "2":
				$("#dateStEn").hide().siblings(".checkList").show();
				break;
			case "3":
				$("#dateStEn").show().siblings(".checkList").hide();
				break;
		}
		$("#dateStEn .checkList").val("");
	});

	//사이트 변경시
	$("#siteCode").change(function(){
		$(".radio:eq(0)").trigger("click");
		$("#downYn").val("N");
		InfoSubmit();
// 		$.ajax({
// 			type: 'post'
// 			, async: true
// 			, url: '/sys/stateMng/comboMenuForSite.do'
// 			, dataType:"json"
// 			, data: {
// 				paramCode:$(this).val()
// 			}
// 			, success: function(data) {
// 				if(data.flag == "success") {
// 					var list=data.list;
// 					var opts="";
// 					$.each(list,function(i){
// 						var optVal="";
// 						if(list[i].parentName!="") optVal="["+list[i].parentName+"]";
// 						optVal+=list[i].menuName;
// 						opts+='<option value="'+list[i].mId+'">'+optVal+'</option>';
// 					});
// 					$("#menuId").empty().html(opts);
// 					InfoSubmit();

// 				} else alert(data.message);
// 			}
// 		});
	});
	$("#menuId").change(function(){
		$(".radio:eq(0)").trigger("click");
		InfoSubmit();
	});

});
//li tabon a tab_on
</script>