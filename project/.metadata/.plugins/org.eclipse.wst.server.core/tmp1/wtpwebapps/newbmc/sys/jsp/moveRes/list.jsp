<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<script type="text/javascript" charaset="UTF-8">

	$(document).ready(function(){
		
		$("#searchType").change(function(){
			var sel = $("#searchType option:selected").val();
			
			if(sel == 'name' || sel == 'addr1'){
				$("#searchTxt").val("");
				$("#searchTxt").show();
				$("#selDate").hide();
			} else if(sel == 'yRes' || sel == 'nRes' || sel == 'oList' || sel == 'nList' || sel == 'tList'){
				$("#searchTxt").val("");
				$("#searchTxt").hide();
				$("#selDate").hide();
			} else{
				$("#searchTxt").val("");
				$("#searchTxt").hide();
				$("#selDate").show();
			}
		});
		
		var sel = $("#searchType option:selected").val();
		
		if(sel == 'yRes' || sel == 'nRes' || sel == 'oList' || sel == 'nList' || sel == 'tList'){
			$("#searchTxt").hide();
		}
		if(sel == 'm_date'){
			$("#searchTxt").hide();
			$("#selDate").show();
			$("#selDate").val($("#searchTxt").val());
		}
		
		dp2.bind($("[data-date=y]"));
	
	});
	
	function fn_select_house(gbn2) {
		var frm = document.getElementById('list');
		frm.gbn2.value = gbn2;
		frm.page.value = "1";
		frm.action ="/sys/moveRes/detailList.do?mId=${menuVO.mId}";
		frm.submit();
	}
	

	//엑셀다운
	function excelDown(){
	
		var searchType = $("#searchType").val();
		var searchTxt = $("#searchTxt").val();
		
		location.href= "/sys/moveRes/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+encodeURI(searchTxt,"UTF-8");
		/* location.href= "/sys/webzine/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+searchTxt;   */
		//$("#list").attr('action',"/sys/smsApply/ExcelDownloader.do?searchGbn="+searchGbn+"&searchType="+searchType+"&searchTxt="+searchTxt).submit();
		
	}
	
</script>

<h2>이사날짜 예약 관리</h2>

<form:form commandName="reserveVO" id="list" name="list" methodParam="post" action="/sys/moveRes/resList.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="gbn2"/>

	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w2">번호</th>
				<th scope="col" class="w4">행복주택명</th>
				<th scope="col" class="w4">예약기간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<td>${listOrder - status.index}</td>
					<td><a href="#" onclick="fn_select_house('${element.gbn2}'); return false;">${element.name}</a></td>
					<td>${element.period}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>

</form:form>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
