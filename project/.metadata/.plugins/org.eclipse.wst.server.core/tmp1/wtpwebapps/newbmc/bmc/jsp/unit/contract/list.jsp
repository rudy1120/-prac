<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 계약현황 목록
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

		var cntrWay    = '${cntrWay}';
		var firstDay  = $("#firstDay").val();
		var lastDay   = $("#lastDay").val();
		var searchTxt = $("#searchTxt").val();
		var dept      = $("#dept").val();
		var cntrGb   = $("#cntrGb").val();
		
		
		location.href= "/bmc/contract/ExcelDownloader.do?cntrWay=" + cntrWay + "&firstDay=" 
				+ firstDay + "&lastDay=" + lastDay + "&searchTxt=" + searchTxt
				+ "&dept=" + dept + "&cntrGb=" + cntrGb;
	}
	
</script>

<!-- <style>

.btn_dblue {
	display: inline-block;
	border-radius: 3px;
	background: #3e4959;
	color: #ffffff !important;
	letter-spacing: -1px;
	border: 0px;
	width: 100px;
}

#excel {
	text-align:right;
}

.buylistT{background:#f7f7f7;}
.buylistT td{text-align:left;}
.buylisttbody{border:1px solid #ddd;}
.buy_bod_list thead th{border-right:1px solid #ddd;}
.buy_bod_list th.buyListLocation{border-right:none;}
table.buylistT td{padding:20px 40px;}
.orgTable tr, th, td{padding:20px 5px;}
table.orgTable td:nth-child(2), table.jobTable td:nth-child(4){text-align:center;}
input#startSpace,input#endSpace,input#startPrice,input#endPrice{width:120px;}
.spanetc{margin:5px;}

@media all and (max-width:1199px){
	table.buylistT td{padding:20px 10px;}
}
@media all and (max-width:960px){
table.buylistT td{display:block; padding:10px 20px;}
input#startSpace,input#endSpace,input#startPrice,input#endPrice,select#searchPurpose,select#searchArea{width:100%;}
input.btn_white{width:100%; margin:10px 0;}
.spanetc{display:block; text-align:center;}
</style> -->

<div id="contents"><div class="content">

<form:form commandName="contractVO" name="list" id="list" onclick="$('#page').val(1);" methodParam="post">
	<form:hidden path="page"/>
	<form:hidden path="cntrWay"/>
<div class="bod_head clFix">
	
	<table class="buylistT" style="width: -webkit-fill-available; margin-top: 20px;">
		<tbody class="buylisttbody">
			<tr>
			    <!-- <td rowspan="2" class="cb first">검색<br/>조건</td> -->
				<td>계약구분</td>
				<td>
					<label for="cntrGb" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">계약구분</label>
					<form:select path="cntrGb" title="계약구분 선택">
						<form:option value="1">공사</form:option>
						<form:option value="2">용역</form:option>
						<form:option value="3">물품</form:option>
					</form:select>
					<input type="submit" value="보기" name="searchBt" title="보기" />
				</td>
				<td>담당부서</td>
				<td>
					<label for="dept" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">담당부서</label>
					<form:select id="dept" path="dept" title="담당부서 선택">
						<form:option value="">선택</form:option>
						<c:forEach var="d" items="${deptList}">
							<form:option value="${d.dept}">${d.dept}</form:option>
						</c:forEach>
					</form:select> 
				</td>
			</tr>
			<tr>
				<td>날짜</td>
				<td>
					<label for="firstDay" class="blind">계약검색 시작날짜</label>
					<form:input path="firstDay" id="firstDay" cssClass="input100" data-isMinDateFor="firstDay" data-date="y" data-validator="on" autocomplete="off" title="계약검색 시작날짜 입력(YYYY-MM-DD)"/> ~
					<label for="lastDay" class="blind">계약검색 끝날짜</label>
					<form:input path="lastDay"  id="lastDay" cssClass="input100" data-isMaxDateFor="lastDay" data-date="y" data-validator="on" autocomplete="off" title="계약검색 끝날짜 입력(YYYY-MM-DD)"/>
				</td>
				<td>검색구분</td>
				<td>
					<label for="cntrNm" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">검색구분 선택 : </label>
					<form:select path="cntrNm" title="검색유형 선택">
						<form:option value="cntr_nm">계약명</form:option>
						<form:option value="comp_nm">업체명</form:option>
					</form:select>
					
					<label for="searchTxt" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">검색어 입력 : </label>
					<form:input id="searchTxt" path="searchTxt" title="검색어 입력" />
					<input type="submit" value="검색" name="searchBt" title="검색" />
					
				</td>
			</tr>
		</tbody>
	</table>
	

</div>

<div class="gap"></div>

<table class="bod_list">
<c:if test="${cntrWay eq 1}">
		<caption>일반계약현황 목록</caption>
		</c:if>
		<c:if test="${cntrWay ne 1}">
		<caption>수의계약현황 목록</caption>
</c:if>
	<thead>
		<tr>
			<th scope="col" class="w10">번호</th>
			<th scope="col" class="w15">담당부서</th>
			<th scope="col">계약명</th>
			<th scope="col" class="w15">계약일자</th>
			<th scope="col" class="w10">계약금액(원)</th>
			<th scope="col" class="w15">업체명</th>
			<th scope="col" class="w10">계약방법</th>
		</tr>
	</thead>
	
	<c:forEach var="element" items="${result}" varStatus="status">
		<tr>
			<td>${listOrder - status.index}</td>
			<td>
				${element.dept}
			</td>
			<td class="list_tit">
				<c:set var="title" value="${element.cntrNm}"/>
				<c:if test="${fn:length(title) > 35}"><c:set var="title" value="${fn:substring(element.cntrNm, 0, 35)}..."/></c:if> 
				<a href="#" data-action="/bmc/contract/view.do?cntrNo=${element.cntrNo}" onclick="req.post(this); return false;">${title}</a>
			</td>
			<td>${element.cntrYrmd}</td>
			<td><fmt:formatNumber value="${element.finalcntrAmt}" pattern="#,###"/></td>
			<td>
				<c:set var="comp" value="${element.compNm}"/>
				<c:if test="${fn:length(comp) > 7}"><c:set var="comp" value="${fn:substring(element.compNm, 0, 7)}..."/></c:if> 
				${comp}
			</td>
			<td>${element.cntrWay}</td>
		</tr>
	</c:forEach>
	
	<c:if test="${empty result}">
		<tr>
			<td colspan="7">등록된 게시물이 없습니다.</td>
		</tr>
	</c:if>

	</table>
	
	
</form:form>
<div class="gap"></div>
<p class="page_num">
	현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
</p>
<div id="excel">
			<input type="button"  class="btn_dblue" value="엑셀 다운로드" onclick="excelDown();" style="background-color:#026273;"/>
		</div>
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
