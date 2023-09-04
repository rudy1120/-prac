<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : href.jsp
* 설	명 : 컨텐츠 본문 URL 체크 페이지
* 작 성 자 : 권태성
* 작 성	일 : 2017-07-17
*********************************************************************
--%>

<html>
	<head>
		<title>Contents URL Checker</title>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<link rel="stylesheet" type="text/css" class="ui" href="/common/js/semanticui/semantic.min.css">
		<script src="/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script src="/common/js/semanticui/semantic.min.js"></script>
		<script type="text/javascript">
		var checker = {
			"progressCnt" : 0,
			"target" : [<c:forEach var="data" items="${dataList }" varStatus="sts">{"siteName" : "${data.SITE_NAME }", "siteCode" : "${data.SITE_CODE }", "menuName" : "${data.TITLE}", "menuPath" : "${data.MENU_NAVI}", "pageId" : "${data.PAGE_ID}"}${sts.count eq fn:length(dataList) ? '' : ','}</c:forEach>],
			"percent" : function(per) {
				$("#progress").progress({ percent: per });
				$("#progress").children(".label").text(per + "%");
				if (per == 100) {
					$("#progress").parent().parent().fadeOut(600);
				}
			},
			"data" : function(siteName, siteCode, menuName, menuPath, pageId, idx) {
				$.ajax({
					type : "POST",
					url : "/hrefCheckData.do",
					data : {"siteCode" : siteCode, "pageId" : pageId},
					dataType : "json",
					success : function(data) {
						if (data.flag == "fail") {
							var html = "<tr class=\"negative\"><td colspan=\"6\">Server Error</td></tr>";
							$("table > tbody").append(html);
						} else {
							$(data).each(function(index, ele) {
								if (ele.urlState == "200") {
									var html = "<tr class=\"positive\"><td>"+siteName+"</td><td>"+menuName+"</td><td>"+menuPath+"</td><td>"+ele.linkName+"</td><td><a href=\""+ele.linkUrl+"\" target=\"_blank\">"+ele.linkUrl+"</a></td><td><i class=\"icon checkmark\"></i>"+ele.urlState+"</td></tr>";
									$("table > tbody").append(html);
								} else {
									var html = "<tr class=\"negative\"><td>"+siteName+"</td><td>"+menuName+"</td><td>"+menuPath+"</td><td>"+ele.linkName+"</td><td><a href=\""+ele.linkUrl+"\" target=\"_blank\">"+ele.linkUrl+"</a></td><td><i class=\"icon close\"></i>"+ele.urlState+"</td></tr>";
									$("table > tbody").append(html);
								}
							});
						}
						checker.progressCnt++;
					},
					error : function() {
						alert("server connection error params : "+siteCode+", "+pageId);
					}
				});
			}
		};
		$(document).ready(function() {
			checker.percent(0);
			$(checker.target).each(function(idx, ele) {
				checker.data(ele.siteName, ele.siteCode, ele.menuName, ele.menuPath, ele.pageId, idx);
			});
			setInterval(function() { checker.percent(Math.floor((checker.progressCnt)/checker.target.length * 100)); }, 1000);
		});
		</script>
	</head>
	<body>
		<h1 style="margin-left:5px;">Contents URL Checker</h1>
		<table class="ui celled table">
			<caption></caption>
			<thead>
				<tr>
					<th style="width:7%">SITE NAME</th>
					<th style="width:10%">MENU NAME</th>
					<th style="width:20%">MENU PATH</th>
					<th style="width:10%">LINK NAME</th>
					<th style="width:20%">LINK URL</th>
					<th style="width:10%">RESULT</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="6">
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
