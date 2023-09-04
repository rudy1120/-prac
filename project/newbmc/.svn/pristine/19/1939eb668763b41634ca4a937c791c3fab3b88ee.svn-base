<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : wa.jsp
* 설	명 : 메뉴 URL 접근성 체크 페이지
			 각 사이트별로 전체 메뉴에 대하여 접근성을 체크합니다.
			 (NACT에서 차단되는 경우 접근성 확인이 불가하므로 실서버에서 사용은 금합니다.)
* 작 성 자 : 권태성
* 작 성	일 : 2017-09-12
*********************************************************************
--%>


<html>
	<head>
		<title>W.A Checker</title>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<link rel="stylesheet" type="text/css" class="ui" href="/common/js/semanticui/semantic.min.css">
		<script src="/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script src="/common/js/semanticui/semantic.min.js"></script>
		<script type="text/javascript">
		var checker = {
			"progressCnt" : 0,
			"target" : [<c:forEach var="data" items="${dataList }" varStatus="sts">{"siteName" : "${data.SITE_NAME }", "siteCode" : "${data.SITE_CODE }", "menuName" : "${data.TITLE}", "menuPath" : "${data.MENU_NAVI}", "pageId" : "${data.MID}"}${sts.count eq fn:length(dataList) ? '' : ','}</c:forEach>],
			"percent" : function(per) {
				$("#progress").progress({ percent: per });
				$("#progress").children(".label").text(per + "%");
				if (per == 100) {
					$("#progress").parent().parent().fadeOut(600);
				}
			},
			"data" : function(siteName, siteCode, menuName, menuPath, pageId) {
				$.ajax({
					type : "POST",
					url : "/waData.do",
					data : {"siteCode" : siteCode, "pageId" : pageId},
					dataType : "json",
					success : function(data) {
						if (data.flag == "fail" || !data.url) {
							var html = "<tr class=\"negative\"><td colspan=\"5\" style=\"text-align: center;\">검사 대상이 아니거나 검사할 수 없는 페이지입니다.</td></tr>";
							$("table > tbody").append(html);
						} else {
							var html = "";
							if (data.warn == '0' && data.error == '0') {
								html = "<tr class=\"positive\"><td>"+siteName+"</td><td>"+menuName+"</td><td>"+menuPath+"</td><td><a href=\""+data.url+"\" target=\"_blank\">"+data.url+"</a></td><td><i class=\"icon checkmark\"></i>OK</td></tr>";
							} else {
								html = "<tr class=\"" + (data.error != '0' ? "error" : "warning") + "\"><td>"+siteName+"</td><td>"+menuName+"</td><td>"+menuPath+"</td><td><a href=\""+data.url+"\" target=\"_blank\">"+data.url+"</a></td><td><i class=\"attention icon\"></i>"+data.warn+"&nbsp;<i class=\"icon close\"></i>"+data.error+"</td></tr>";
							}
							$("table > tbody").append(html);
						}
						checker.progressCnt++;
					},
					error : function() {
						console.log("server connection error params : "+siteCode+", "+pageId);
					}
				});
			}
		};
		$(document).ready(function() {
			var domain = "${domain}";
			if (domain.indexOf(".go.kr") != -1) {
				alert("방화벽 등 문제로 운영 서버에서는 이 기능을 사용할 수 없습니다. 개발 환경에서 실행해주세요.");
				return false;
			} else {
				checker.percent(0);
				$(checker.target).each(function(idx, ele) {
					checker.data(ele.siteName, ele.siteCode, ele.menuName, ele.menuPath, ele.pageId);
				});
				setInterval(function() { checker.percent(Math.floor((checker.progressCnt)/checker.target.length * 100)); }, 8000);
			}
		});
		</script>
	</head>
	<body>
		<h1 style="margin-left:10px;">W.A Checker</h1>
		<div class="ui info message" style="margin-left:10px; margin-right:10px;">
			<div class="header">
				사용 안내
			</div>
			<ul class="list">
				<li>현재는 경고, 오류 여부만 확인할 수 있습니다. 상세한 내용이 필요하면 <a href="http://nact.naver.com" target="_blank"><b>NACT</b></a>에서 해당 페이지를 검사해주세요.</li>
				<li><b>NACT</b> 서버 부하 방지를 위해 8초당 한 개의 페이지에 대한 검사 요청을 합니다.</li>
			</ul>
		</div>
		<table class="ui celled table">
			<caption></caption>
			<thead>
				<tr>
					<th style="width:7%">SITE NAME</th>
					<th style="width:10%">MENU NAME</th>
					<th style="width:20%">MENU PATH</th>
					<th style="width:20%">LINK URL</th>
					<th style="width:10%">RESULT</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5">
						<div class="ui teal progress" data-percent="0" id="progress">
							<div class="bar"></div>
							<div class="label">0%</div>
						</div>
					</td>
				</tr>
				<%-- <c:forEach var="ret" items="${retList }">
					<tr ${ret.urlStatus ne 200 ? 'class="negative"' : 'class="positive"' }>
						<td>${ret.site }</td>
						<td>${ret.title }</td>
						<td>${ret.menuNavi }</td>
						<td>${ret.linkName }</td>
						<td><a href="${ret.linkUrl }">${ret.linkUrl }</a></td>
						<td>
							${ret.urlStatus ne 200 ? '<i class="icon close"></i>' : '<i class="icon checkmark"></i>' }
							${ret.urlStatus }
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty retList }">
					<tr>
						<td colspan="6">조회된 데이터가 없습니다.</td>
					</tr>
				</c:if> --%>
			</tbody>
		</table>
	</body>
</html>
