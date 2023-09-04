<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 대가지급 목록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.02		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.10.02
 */
%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<script type="text/javascript">

	// 엑셀다운
	function excelDown(){

		var firstDay = $("#firstDay").val();
		var lastDay  = $("#lastDay").val();
		var searchTxt = $("#searchTxt").val();
		
		location.href= "/bmc/contract/ExcelDownloader2.do?firstDay=" + firstDay + "&lastDay=" + lastDay + "&searchTxt=" + searchTxt;
	}
</script>

<div id="contents"><div class="content">

<form:form commandName="paymentVO" name="list" id="list" onclick="$('#page').val(1);" methodParam="post">
	<form:hidden path="page"/>
	<div class="gap20"></div>

<div class="bod_head clFix">

	<fieldset class="bod_search">
		<label>날짜</label>
		<label for="firstDay"><form:input path="firstDay" id="firstDay" cssClass="input100" data-isMinDateFor="firstDay" data-date="y" data-validator="on" autocomplete="off" title="대가지급 시작날짜 입력(YYYY-MM-DD)"/></label> ~
		<label for="lastDay"><form:input path="lastDay"  id="lastDay" cssClass="input100" data-isMaxDateFor="lastDay" data-date="y" data-validator="on" autocomplete="off" title="대가지급 끝날짜 입력(YYYY-MM-DD)"/></label>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label for="cntrNm" class="blind">검색구분 선택 : </label>
		<form:select path="cntrNm" title="검색유형 선택">
			<form:option value="cntr_nm">계약명</form:option>
		</form:select>
		<label for="searchTxt" class="blind">검색어 입력: </label>
		<form:input id="searchTxt" path="searchTxt" title="검색어 입력"/>
		<input type="submit" value="검색" name="searchBt" title="검색" />
		<div class="gap20"></div>
		<div id="excel">
			<input type="button"  class="btn_dblue" value="엑셀 다운로드" onclick="excelDown();" style="background-color:#026273;"/>
		</div>
	</fieldset>
</div>

<div class="gap"></div>
<table class="bod_list">
	<caption>대가지급 테이블입니다.</caption>
	<thead>
		<tr>
			<th scope="col" class="w10">번호</th>
			<th scope="col" class="w10">지급일</th>
			<th scope="col" class="w10">지급종류</th>
			<th scope="col">계약명</th>
			<th scope="col" class="w20">업체명</th>
			<th scope="col" class="w10">지급액(원)</th>
		</tr>
	</thead>
	
	<c:forEach var="element" items="${result}" varStatus="status">
		<tr>
			<td>${listOrder - status.index}</td>
			<td>
				${element.payYrmd}
			</td>
			<td>
				${element.payGb}
			</td>
			<td class="list_tit">
				<c:set var="title" value="${element.cntrNm}"/>
				<%-- <c:if test="${fn:length(title) > 40}"><c:set var="title" value="${fn:substring(element.cntrNm, 0, 40)}..."/></c:if> --%> 
				&nbsp;&nbsp;&nbsp;&nbsp;${title}
			</td>
			<td>
				<c:set var="comp" value="${element.compNm}"/>
				<%-- <c:if test="${fn:length(comp) > 7}"><c:set var="comp" value="${fn:substring(element.compNm, 0, 7)}..."/></c:if> --%> 
				${comp}
			</td>
			<td><fmt:formatNumber value="${element.payAmt}" pattern="#,###"/></td>
		</tr>
	</c:forEach>
	
	<c:if test="${empty result}">
		<tr>
			<td colspan="${colLength}">등록된 게시물이 없습니다.</td>
		</tr>
	</c:if>
</table>

</form:form>
<div class="gap"></div>
<p class="page_num">
	현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
</p>
<div class="bod_page">
	<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
</div>

</div></div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<link rel="stylesheet" type="text/css" href="/common/css/jquery-ui.1.12.1.min.css"/>
<script type="text/javascript" src="/common/js/jquery/jquery-1.11.3.min.js" charset="UTF-8"></script> 
<script type="text/javascript" src="/common/js/jquery/jquery-ui-1.10.0.custom.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script>
	
	jQuery(document).ready(function(){
		dp.bind(jQuery("[data-date=y]"), "yy-mm-dd");
	});
	
</script>
