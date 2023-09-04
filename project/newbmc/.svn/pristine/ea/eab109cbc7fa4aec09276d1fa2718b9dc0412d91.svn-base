<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 하도급 계약현황 목록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2021.06.29		손문배		    최초 생성 및 코드 수정
 *
 *
 * @author 손문배
 * @since 2021.06.29
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
				<td>검색구분</td>
				<td>
					<label for="cntrNm" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">검색구분 선택 : </label>
					<form:select path="cntrNm" title="검색유형 선택">
						<form:option value="cntr_nm">공사명</form:option>
						<form:option value="comp_nm">수급인</form:option>
					</form:select>
					
					<label for="searchTxt" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">검색어 입력 : </label>
					<form:input id="searchTxt" path="searchTxt" title="검색어 입력" />
					
				</td>
				<td>
					<label for="workKind" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">검색구분 선택 : </label>
					<form:select path="workKind" title="검색유형 선택">
						<form:option value="work_kind">하도급공종</form:option>
						<form:option value="subcomp_nm">하수급인</form:option>
					</form:select>
					<label for="searchTxt1" style="position: absolute; width: 1px; height: 1px; clip: rect(0 0 0 0); overflow: hidden;">검색어 입력 : </label>
					<form:input id="searchTxt1" path="searchTxt1" title="검색어 입력" />
					<input type="submit" value="검색" name="searchBt" title="검색" />
			
					
				</td>
			</tr>
		</tbody>
	</table>
	

</div>

<div class="gap"></div>

<table class="bod_list">

		<caption>하도급 계약현황 목록</caption>

		
		

	<thead>
		<tr>
			<th scope="col"  class="w5">번호</th>
			<th scope="col"  class="w15">공사명</th>
			<th scope="col"  class="w10">수급인(상호)</th>
			<th scope="col"  class="w12" >도급금액</th>
			<th scope="col"  class="w5">낙찰률</th>
			<th scope="col"  class="w10">하도급공종</th>
			<th scope="col"  class="w11">하수급인(상호)</th>
			<th scope="col"  class="w12">하도급부분<br> 도급액</th>
			<th scope="col" class="w12">하도급금액</th>
			<th scope="col" class="w5">하도급률</th>
			
		</tr>

	</thead>
	
	<c:forEach var="element" items="${result}" varStatus="status">
		<tr>
			<td>${listOrder - status.index}</td>
			<td  class="list_tit">
				<c:set var="title" value="${element.cntrNm}"/>
				<c:if test="${fn:length(title) > 35}"><c:set var="title" value="${fn:substring(element.cntrNm, 0, 35)}..."/></c:if> 
			<%-- 	<a href="#" data-action="/bmc/contract/view2.do?cntrNo=${element.cntrNo}&subcontractNo=${element.subcontractNo}" onclick="req.post(this); return false;">${title}</a> --%>
				${element.cntrNm}
			</td>
			<td>
				${element.compNm} 
			</td>
			<td><fmt:formatNumber value="${element.finalcntrAmt}" pattern="#,###"/></td>
			<td>${element.succbidRate} </td>
			<td>
				${element.workKind}
			</td>
			<td>${element.subcompNm}</td>
			<td><fmt:formatNumber value="${element.sconstAmt}" pattern="#,###"/></td>
			<td><fmt:formatNumber value="${element.hcntrAmt}" pattern="#,###"/></td>
			<td>${element.contractRate}</td>
		</tr>
	</c:forEach>
	
	<c:if test="${empty result}">
		<tr>
			<td colspan="10">등록된 게시물이 없습니다.</td>
		</tr>
	</c:if>

	</table>
	
	
</form:form>
<div class="gap"></div>
<p class="page_num">
	현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
</p>
<!-- <div id="excel">
			<input type="button"  class="btn_dblue" value="엑셀 다운로드" onclick="excelDown();"/>
		</div> -->
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
