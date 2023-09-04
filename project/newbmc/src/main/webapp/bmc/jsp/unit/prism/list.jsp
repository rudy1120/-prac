<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 정책연구용역 보고서 리스트 사용자 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.28		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.28
 */
%>
<script>
$(document).ready(function () {
	
	
	var year = new Date().getFullYear();
	
	for( var i = 0 ; i < 50; i++ ){
		
		var yearNum = year-i;
		var options = $("<option value='"+yearNum +"'>"+yearNum+"</option>");
		$("#searchType").append(options);
		
	}
	
	$("#searchType").val($("#issYearVal").val()).prop("selected",true);
	$("#issYear").val($("#issYearVal").val()).prop("selected",true);
});
</script>
<!-- <style>
	.pull-right{float:right!important; font-size:30px;}
	.btntolist{margin:30px 0; position:absolute; top:0; right:0;}
	.label{background: #00879e; color:#fff; padding: 1.2rem 1.5rem; position: absolute; bottom: 0; left: 0;}
</style> -->

<jsp:useBean id="currTime" class="java.util.Date" />
<fmt:formatDate var="currTime" value="${currTime}" pattern="yyyy-MM-dd" />

<div id="contents"><div class="content">

<div class="gap20"></div>

<form:form commandName="prismVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);">
	<form:hidden path="page" />
	<div class="bod_head clFix">
		<p class="page_num">
			현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
		</p>
		<fieldset class="bod_search">
			<input type="hidden" id="issYearVal" value="${prismVO.searchType}" />
			<label for="searchType" class="blind">발행년도 선택: </label>
			<form:select path="searchType" title="검색유형 발행년도 선택">
				<form:option value="">전체</form:option>
			</form:select>
			<label for="searchTxt" class="blind">과제명 입력: </label>
			<form:input path="searchTxt" title="검색어 입력" placeholder="과제명"/>
			<input type="submit" value="검색" name="searchBt" title="검색" />
		</fieldset>
	</div>
		
	<table class="bod_list">
	<caption>정책연구용역 보고서 테이블입니다.</caption>
		<thead>
			<tr>
				<c:set var="colLength" value="4"/>
				<th scope="col" class="list_num">번호</th>
				<th scope="col" class="list_tit">과제명</th>
				<th scope="col" style="width:15%;">연구수행기관</th>
				<th scope="col" class="list_date">발행년도</th>
			</tr>
		</thead>
		
		<c:forEach var="element" items="${elements}" varStatus="status">
			<tr>
				<td class="list_num">${listOrder - status.index}</td>
				<td class="list_tit">
					<a href="#" data-action="/bmc/prism/view.do?idx=${element.idx}" onclick="req.post(this); return false;">${element.projectNm}</a>
				</td>
				<td>${element.executeNm}</td>
				<td>${element.issYear}</td>
			</tr>
		</c:forEach>
		
		<c:if test="${empty elements}">
			<tr>
				<td colspan="${colLength}">등록된 게시물이 없습니다.</td>
			</tr>
		</c:if>
	
	</table>
</form:form>	
	
<c:if test="${fn:length(result) eq 1}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
	</div>
</c:if>
<div class="bod_page">
	<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
</div>
</div></div>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>

