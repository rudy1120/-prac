<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%	String remoteAddr = (String)request.getRemoteAddr(); %>
<%	String cmsUrl = EgovProperties.getProperty("ySmartCMS.url"); %>
<c:set var="cmsUrl" value="<%=cmsUrl %>" />
<c:choose>
	<c:when test="${not empty stateResults}">
<script src="/common/amcharts/amcharts.js" type="text/javascript"></script>
<script src="/common/amcharts/serial.js" type="text/javascript"></script>
<script type="text/javascript">
			<c:set var="lineColor" value="" />
			<c:set var="month" value="" />
			<c:set var="duration" value="" />

			var chartData = [
		<c:forEach items="${stateResults}" var="result" varStatus="status">
			<c:choose>
			<c:when test="${status.index/4<1}"><c:set var="lineColor" value="#b7e021" /></c:when>
			<c:when test="${status.index/4<2}"><c:set var="lineColor" value="#fbd51a" /></c:when>
			<c:when test="${status.index/4<3}"><c:set var="lineColor" value="#f4783a" /></c:when>
			<c:when test="${status.index/4<4}"><c:set var="lineColor" value="#2498d2" /></c:when>
			</c:choose>

			<c:set var="month" value="${year}년 ${result.statsLabel}월" />
			<c:if test="${status.index > 0}">,</c:if>
			<c:set var="duration" value="${result.statsCount}" />
			{
				<c:if test="${status.index==0 || status.index%3==1}">
				"lineColor": "${lineColor}",
				</c:if>
				"month": "${month}",
				"duration": ${duration}
			 }
		</c:forEach>
			];
			var chart;

			AmCharts.ready(function () {
				// SERIAL CHART
				chart = new AmCharts.AmSerialChart();
				chart.dataProvider = chartData;

				chart.categoryField = "month";
				chart.dataDateFormat = "YYYY MM";

				var balloon = chart.balloon;
				balloon.cornerRadius = 6;
				balloon.adjustBorderColor = false;
				balloon.horizontalPadding = 10;
				balloon.verticalPadding = 10;

				// AXES
				// category axis
				var categoryAxis = chart.categoryAxis;
				categoryAxis.parseDates = false; // as our data is date-based, we set parseDates to true
				categoryAxis.minPeriod = "MM"; // our data is daily, so we set minPeriod to DD
				categoryAxis.autoGridCount = false;
				categoryAxis.gridCount = 50;
				categoryAxis.gridAlpha = 0;
				categoryAxis.gridColor = "#000000";
				categoryAxis.axisColor = "#555555";

				// GRAPHS
				// duration graph
				var durationGraph = new AmCharts.AmGraph();
				durationGraph.title = "duration";
				durationGraph.valueField = "duration";
				durationGraph.type = "line";
				durationGraph.lineColorField = "lineColor";
				durationGraph.fillColorsField = "lineColor";
				durationGraph.fillAlphas = 0.3;
				durationGraph.balloonText = "[[value]]";
				durationGraph.lineThickness = 1;
				durationGraph.legendValueText = "[[value]]";
				durationGraph.bullet = "square";
				durationGraph.bulletBorderThickness = 1;
				durationGraph.bulletBorderAlpha = 1;
				chart.addGraph(durationGraph);

				// CURSOR
				var chartCursor = new AmCharts.ChartCursor();
				chartCursor.zoomable = false;
				chartCursor.categoryBalloonDateFormat = "YYYY년 MM월";
				chartCursor.cursorAlpha = 0;
				chartCursor.cursorColor="#000000";
				chart.addChartCursor(chartCursor);


				var chartScrollbar = new AmCharts.ChartScrollbar();
				chart.addChartScrollbar(chartScrollbar);

				// WRITE
				chart.write("chartdiv");
			});

		// window.onload
		window.onload = function () {
			$("#chartdiv a").remove();
			$.ajax({ // CMS SITE 목록 연동
				url: "${cmsUrl}/linkage/siteList?callback=?&key=${cmsLoginKey}",
				dataType: "jsonp",
				success: function (data) {
					var options = "";
					var len = data.siteList.length;
					for (var i = 0; i < len; i++) {
						options += "<option value='" + data.siteList[i].linkageUrl + "'>" + data.siteList[i].siteName + "</option>";
					}

					$("#cms_site").append(options);
				}
			});
			$("#anchor_cms_site").click(function() {
				window.open(
					"${cmsUrl}" + $("#cms_site").val(),
					"_blank"
				);
			});
		}

		function logout() {
			$.ajax({
				url : "${pageContext.request.contextPath}/sys/logout.do", //"http://61.85.36.149:8180/cms/AdminLogin/logoutCms",
				//jsonp : "callback",
				//dataType: 'jsonp',
				withCredentials: true,
				success : function(result) {
					location.href="${pageContext.request.contextPath}/sys/login.do";
					/*
					if(result.flag == "success") {
						parent.document.location.replace("${pageContext.request.contextPath}/sys/logout.do");
					} else {
						parent.document.location.replace("${pageContext.request.contextPath}/sys/logout.do");
					}
					*/
				}
			});
		}

		function change_site_anchor(obj) {
			$("#site_anchor").attr("href", $("#site_anchor_list").val());
		}

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





<%-- 상단 시작 --%>
<header>
	<div>
		<h1>Integration Management</h1>
		<nav style="margin-left:-200px;">
			<ul>
				<c:choose>
					<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_hr'}"> <!-- 특정 관리자일 경우에만 사용 -->
						<li><a href="/sys/sysContents.do?mId=0121010000" id="goSiteMng"><span class="icon02"></span><span>홈페이지관리</span></a></li>
<!-- 						<li><a href="/sys/sysContents.do?mId=0121010000" id="goSiteMng"><span class="icon02"></span><span>홈페이지관리</span></a></li> -->
					</c:when>
					<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_human'}"> <!-- 특정 관리자일 경우에만 사용 -->
						<li><a href="/sys/sysContents.do?mId=0122010000" id="goSiteMng"><span class="icon02"></span><span>홈페이지관리</span></a></li>
<!-- 						<li><a href="/sys/report/list.do?mId=0122010000" id="goSiteMng"><span class="icon02"></span><span>홈페이지관리</span></a></li> -->
					</c:when>
					<c:otherwise>
						<c:if test="${sessionScope.ADMIN_LOGIN_INFO.cntAuthHead3 > 0 }">
							<li><a href="${cmsUrl}/linkage?key=${sessionScope.cmsLoginKey}" target="_blank"><span class="icon01"></span><span>CMS</span></a></li>
						</c:if>
						<li><a href="${pageContext.request.contextPath}/sys" id="goSiteMng"><span class="icon02"></span><span>홈페이지관리</span></a></li>
		<%-- 				<li><a href="${empty hmsLoginKey ? 'http://hms.yhdatabase.com/' : 'http://hms.yhdatabase.com/linkage.do?key='}${hmsLoginKey}" target="_blank"> --%>
		<!-- 						<span class="icon03"></span><span>HMS<br />(유지보수요청)</span> -->
		<!-- 					</a> -->
		<!-- 				</li> -->
						<c:if test="${sessionScope.ADMIN_LOGIN_INFO.cntAuthHead2 > 0 }">
							<li><a href="${pageContext.request.contextPath}${sessionScope.ADMIN_LOGIN_INFO.urlAuthHead2 }"><span class="icon04"></span><span>권한관리</span></a></li>
						</c:if>
					</c:otherwise>
				</c:choose>
			</ul>
			<div class="cleB"></div>
		</nav>

		<div class="gnb">
			<ul>
				<li>${ADMIN_LOGIN_INFO.name}(${ADMIN_LOGIN_INFO.adminId })님</li>
				<li><a href="javascript:logout()" class="btn_logout">Log out</a></li>
			</ul>
		</div>
	</div>
</header>

<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/intro.do" method="post" style="height: 793px;">
<section>
	<article class="stats_area">
		<div>
			<h2>방문자통계</h2>
			<span class="date">${today_info} <a href="#" class="btn_refresh" id="refresh">새로고침</a></span>

			<div class="select_site">
				<label for="statsSite">사이트선택항목</label>
				<select name="siteCode" id="siteCode">
					<option value="">전체사이트</option>
					<c:if test="${not empty listSite }">
					<c:forEach items="${listSite}" varStatus="status" var="site">
						<option value="${site.siteCode}" <c:if test="${site.siteCode eq stateVO.siteCode}">selected="selected"</c:if>>${site.siteName}</option>
					</c:forEach>
					</c:if>
				</select>
			</div>
			<a href="/sys/stateMng/list.do?mId=0108010000&kind=0&siteCode=${empty stateVO.siteCode ? 'portal' : stateVO.siteCode}" class="btn_more">더보기</a>
			<div class="cleB"></div>
		</div>
		<div class="charts" id="chartdiv">
		</div>
	</article>
	<div class="middle_wrap">
		<article class="board_area" style="width:60%; height:80%;">
			<h2>최근 게시물</h2>
			<ul class="list_ul">
				<c:forEach var="element" items="${recentBoardList}" varStatus="status">
					<li>
						<a href="/sys/superMng/bbs/bbsMng/view.do?mId=0103010000&amp;ptIdx=${element.ptIdx}&amp;bIdx=${element.bIdx}">
							<span class="name">${element.bWrite}</span><span class="subject">${element.bTitle}</span>
						</a>
					</li>
				</c:forEach>
<%-- 				<c:if test="${empty recentBoardList}">
					<li>등록된 게시글이 없습니다.</li>
				</c:if> --%>
			</ul>
		</article>
		<%-- <article class="system_area">
			<h2>시스템 공지사항</h2>
			<ul class="list_ul">
				<c:forEach var="element" items="${sysNotiList}">
					<li>
						<a href="/sys/superMng/bbs/bbsMng/view.do?mId=0103030000&amp;ptIdx=${element.ptIdx}&amp;bIdx=${element.bIdx}">
							<span class="title">${element.bTitle}</span>
						</li>
				</c:forEach>
				<c:if test="${empty sysNotiList}">
					<li class="no_data">등록된 공지가 없습니다</li>
				</c:if>
			</ul>
			<a href="/sys/superMng/bbs/bbsMng/list.do?mId=0103030000&amp;ptIdx=11" class="btn_more">더보기</a>
		</article> --%>
		<div class="sitelink_area">
			<article class="favorite_area" style="height:75%; width:135%;">
				<h2>자주 이용하는 메뉴</h2>
				<div id="favorite_link">
					<c:choose>
						<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_hr'}">
							<ul id="favorite_menu_container" style="margin-left: 90px;">
								<li><a href="/sys/sysContents.do?mId=0121010000">채용응시원서</a></li>
								<li><a href="/sys/sysContents.do?mId=0112010000">마이페이지</a></li>
							</ul>
						</c:when>
						<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_human'}">
							<ul id="favorite_menu_container" style="margin-left: 90px;">
								<li><a href="/sys/sysContents.do?mId=0122010000">인권침해센터</a></li>
								<li><a href="/sys/sysContents.do?mId=0112010000">사이트 통계</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul id="favorite_menu_container">
								<li><a href="/sys/superMng/bbs/configMng/list.do?mId=0103010000">게시판 관리</a></li>
								<li><a href="/sys/stateMng/list.do?mId=0108000000">사이트 통계</a></li>
								<li><a href="/sys/totalAdminMng/menuMng/menuList.do?mId=0101040000">메뉴 관리</a></li>
							</ul>
						</c:otherwise>
						</c:choose>
					<div class="cleB"></div>
				</div>
			</article>
			<article class="siteGo_area" style="width:135%;">
				<h2>사이트바로가기</h2>
				<div>
					<label for="">사이트선택항목</label>
					<select name="" id="site_anchor_list" onchange="change_site_anchor(); return false;">
						<option value="http://www.bmc.busan.kr/">부산도시공사</option>
						<option value="http://osiria.bmc.busan.kr">오시리아</option>
						<option value="http://www.bmc.busan.kr/history/main.do">역사관</option>
						<option value="http://badaga.bmc.busan.kr/">바다가</option>
					</select>
					<a id="site_anchor" href="http://www.bmc.busan.kr" target="_blank" class="btn_gray">이동하기</a>
				</div>
			</article>
		</div>
	</div>

	<c:if test="${sessionScope.ADMIN_LOGIN_INFO.adminAccessLevelCode == 10}">
		<div class="admin_wrap">
	<%-- 		<article class="solution_area">
				<h2>솔루션 관리</h2>
				<div>
					<ul>
						<c:forEach var="element" items="${solutionList}">
							<li>
								<a href="/sys/dataMng/solution/modify.do?mId=0102060000&amp;idx=${element['IDX']}" class="name">${element['COL_NAME']}</a>
								<span class="date"><fmt:formatDate value="${element['CREATE_DATE']}" pattern="yyyy-MM-dd" /></span>
								<a href="${element['COL_URL']}" class="btn_white" target="_blank">바로가기</a></li>
						</c:forEach>
						<c:if test="${empty solutionList}">
							<li class="no_data">등록된 솔루션이 없습니다.</li>
						</c:if>
					</ul>
				</div>
				<a href="/sys/sysContents.do?mId=0102060000" class="btn_more">더보기</a>
			</article> --%>
			
			<div class="cleB"></div>
		</div>
	</c:if>
</section>
</form>
<script type="text/javascript">

	// 사이트 변경시
	$("#siteCode").change(function(){
		var frm = document.getElementById("frm");
		frm.submit();
	});

	$("#refresh").click(function(){
		var frm = document.getElementById("frm");
		frm.submit();
	});

	function popup_menus() {
		var url = "/sys/intro/add/favorite/menus.do";
		var option = "width=500, height=500, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
		window.open(url, "addFavoriteMenus", option);
	};

	$(document).ready(function() {
		/* var key = null;
		var obj = null;
		var li = null;
		if (localStorage.length > 0) {
			for (var i = 0; i < localStorage.length; i++) {
				console.log(localStorage.key(i));
				obj = JSON.parse(localStorage[localStorage.key(i)]);
				li = "<li>";
				li += "<a href='" + obj.url + "?mId=" + obj.mId + "'>" + obj.title + "</a>";
				li += "</li>";
				$("#favorite_menu_container").prepend(li);

				if ($("#favorite_menu_container").children().length > 6) {
					$("#favorite_menu_container").children().last().remove();
				}
			}
		} else {
			li = "<li class='no_data'>등록된 즐겨찾기가 없습니다.</li>";
			$("#favorite_menu_container").append(li);
		} */

	});

</script>
