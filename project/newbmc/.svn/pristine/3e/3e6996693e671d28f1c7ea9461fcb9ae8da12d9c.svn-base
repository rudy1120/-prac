<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 관리자 정책연구용역보고서 관리 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2019.10.22		박선민			최초 생성 및 코드 수정
	 *
	 *
	 * @author 박선민
	 * @since 2019.10.22
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
<h2>정책연구용역보고서 목록</h2>

<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<input type="hidden" id="issYearVal" value="${searchVO.searchType}" />
			<label for="searchType" >발행년도 : </label>
			<form:select path="searchType" >
				<form:option value="">전체</form:option>
<%-- 				<c:forEach var="uses" items="${resultList}">
					<form:option value="${uses.idx}">${uses.codename}</form:option>
				</c:forEach> --%>
			</form:select>
			<label for="searchTxt">과제명 : </label>
			<form:input type="text" path="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>
 	
 	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col">과제명</th>
				<th scope="col" class="w10">연구시행기관</th>
				<th scope="col" class="w10">발행년도</th>
				<th scope="col" class="w10">작성자</th>
				<th scope="col" class="w10">작성일</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<td>${listOrder - status.index}</td>
					<td>
						<a href="#" data-action="/sys/prismMngview.do?idx=${element.idx}" onclick="req.post(this); return false;">${element.projectNm}</a>
					</td>
					<td class="list_tit">
						${element.executeNm}
					</td>
					<td>
						${element.issYear}
					</td>
					<td>
						${element.bWrite}
					</td>
					<td>
						${fn:substring(element.createDate,0,10) }
					</td>
					<td>
						<a href="#" data-action="/sys/prismMng/modify.do?mId=${menuVO.mId}&idx=${element.idx}" onclick="req.post(this); return false;" class="btn_cyan">수정</a>
						<a href="#" data-action="/sys/prismMng/deleteProc.do?mId=${menuVO.mId}&idx=${element.idx}" onclick="if(confirm('삭제하시겠습니까?'))req.post(this); return false;" class="btn_sblack">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>
</form:form>

<c:if test="${not empty result}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>

<div class="btn_boxR">
	<a href="#" data-action="/sys/prismMng/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>