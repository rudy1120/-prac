<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<%
    request.setCharacterEncoding("UTF-8");
%>

<script type="text/javascript" charaset="UTF-8">

	$(document).ready(function(){
		
		dp.bind($("[data-date=y]"));
		
		$("#searchTxt").hide();
		$("#selDate").val($("#searchTxt").val());
	
	});
	

	//엑셀다운
	function excelDown(){
	
// 		var searchType = $("#searchType").val();
// 		var searchTxt = $("#searchTxt").val();
		
// 		location.href= "/sys/moveRes/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+encodeURI(searchTxt,"UTF-8");
		/* location.href= "/sys/webzine/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+searchTxt;   */
		//$("#list").attr('action',"/sys/smsApply/ExcelDownloader.do?searchGbn="+searchGbn+"&searchType="+searchType+"&searchTxt="+searchTxt).submit();
		
	}
	
</script>

<h2>자동삭제 내역</h2>

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<form:select path="searchGbn" id="searchGbn">
				<form:option value="">연도</form:option>
				<%-- <c:forEach var="area" items="${resultList}">
					<form:option value="${area.idx}">${area.codename}</form:option>
				</c:forEach> --%>
				<c:forEach var="year" begin="2023" end="2025" step="1" >
					<form:option value="${year}">${year}</form:option>
				</c:forEach>
			</form:select>
			<form:select path="searchType" id="searchType">
				<form:option value="">월</form:option>
				<c:forEach var="month" begin="1" end="12" step="1" >
					<form:option value="${month}">${month}</form:option>
				</c:forEach>
			</form:select>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>
 
<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w20">번호</th>
				<th scope="col" class="w20">분양 서비스</th>
				<th scope="col" class="w20">임대 서비스</th>
				<th scope="col" class="w20">합계</th>
				<th scope="col" class="w20">삭제일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<%-- ============================ 번호 & idx============================ --%>
					<td>${listOrder - status.index}</td>
					<%-- ============================ 성함 ============================ --%>
					<td>
						${element.sale}
					</td>
					<%-- ============================ 연락처 ============================ --%>
					<td>
						${element.housing}
					</td>
					<%-- ============================ 동,호수 ============================ --%>
					<td>
						${element.total}
					</td>
					<%-- ============================ 이사일자 ============================ --%>
					<td>
						${element.regdate}
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
