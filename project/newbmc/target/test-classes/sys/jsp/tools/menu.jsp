<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : menu.jsp
* 설	명 : 메뉴 URL 체크 페이지
			 각 사이트별로 전체 메뉴에 대하여 접속 상태를 체크합니다.
* 작 성 자 : 권태성
* 작 성	일 : 2017-07-17
*********************************************************************
--%>

<html>
	<head>
		<title>Menu URL Checker</title>
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
					url : "/menuCheckData.do",
					data : {"siteCode" : siteCode, "pageId" : pageId},
					dataType : "json",
					success : function(data) {
						if (data.flag == "fail") {
							var html = "<tr class=\"negative\"><td colspan=\"5\">Server Error</td></tr>";
							$("table > tbody").append(html);
						} else {
							if (data.urlState == "200") {
								var html = "<tr class=\"positive\"><td>"+siteName+"</td><td>"+menuName+"</td><td>"+menuPath+"</td><td><a href=\""+data.url+"\" target=\"_blank\">"+data.url+"</a></td><td><i class=\"icon checkmark\"></i>"+data.urlState+"</td></tr>";
								$("table > tbody").append(html);
							} else {
								var html = "<tr class=\"negative\"><td>"+siteName+"</td><td>"+menuName+"</td><td>"+menuPath+"</td><td><a href=\""+data.url+"\" target=\"_blank\">"+data.url+"</a></td><td><i class=\"icon close\"></i>"+data.urlState+"</td></tr>";
								$("table > tbody").append(html);
							}
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
			checker.percent(0);
			$(checker.target).each(function(idx, ele) {
				checker.data(ele.siteName, ele.siteCode, ele.menuName, ele.menuPath, ele.pageId);
			});
			setInterval(function() { checker.percent(Math.floor((checker.progressCnt)/checker.target.length * 100)); }, 1000);
		});
		</script>
	</head>
	<body>
		<h1 style="margin-left:10px;">Menu URL Checker</h1>
		<div class="ui info message" style="margin-left:10px; margin-right:10px;">
			<div class="header">
				사용 안내
			</div>
			<ul class="list">
				<li>전체 메뉴에 대한 정상 접속여부를 확인하는 페이지입니다.</li>
				<li><strong>운영 단계에서는 서비스에 부하를 줄 수 있으므로 개발 단계에서만 사용하기 바랍니다.</strong></li>
				<li>검사에 실패하고 오류가 발생한 내역은 브라우저에서 F12키를 눌러 콘솔에서 확인할 수 있습니다.</li>
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
