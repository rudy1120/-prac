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
	

	//엑셀다운
	function excelDown(){
	
		var searchType = $("#searchType").val();
		var searchTxt = $("#searchTxt").val();
		var gbn2 = $("#gbn2").val();
		
		location.href= "/sys/moveRes/ExcelDownloader.do?gbn2="+gbn2+"&searchType="+searchType+"&searchTxt="+encodeURI(searchTxt,"UTF-8");
		/* location.href= "/sys/webzine/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+searchTxt;   */
		//$("#list").attr('action',"/sys/smsApply/ExcelDownloader.do?searchGbn="+searchGbn+"&searchType="+searchType+"&searchTxt="+searchTxt).submit();
		
	}
	
</script>

<h2>${title} 예약자 목록</h2>
<input type="hidden" id="gbn2" value="${gbn2}" />

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="reserveVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<form:select path="searchType" id="searchType">
				<form:option value="name">성함</form:option>
				<form:option value="addr1">동</form:option>
				<form:option value="m_date">이사일자</form:option>
				<form:option value="yRes">예약자</form:option>
				<form:option value="nRes">미예약자</form:option>
				<form:option value="oList">1차 계약자</form:option>
				<form:option value="nList">2차 계약자</form:option>
				<form:option value="tList">3차 계약자</form:option>
			</form:select>
			<form:input type="text" path="searchTxt" id="searchTxt"/>
			<input type="text" id="selDate" class="input100" placeholder="날짜" data-date="y" style="display:none;"readonly/>
			<input type="button" id="btn" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>
 
<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w2">번호</th>
				<th scope="col" class="w4">성함</th>
				<th scope="col" class="w4">연락처</th>
				<th scope="col" class="w4">동 &middot; 호수</th>
				<th scope="col" class="w5">이사일자</th>
				<th scope="col" class="w5">이사시간</th>
				<th scope="col" class="w5">등록일시</th>
				<th scope="col" class="w5">수정일시</th>
				<th scope="col" class="w5">수정가능여부</th>
 				<th scope="col" class="w4">계약 차수</th>
				<th scope="col" class="w8">관리</th>
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
						${element.name}
					</td>
					<%-- ============================ 연락처 ============================ --%>
					<td>
						${element.tel}
					</td>
					<%-- ============================ 동,호수 ============================ --%>
					<td>
						${element.addr1}동   ${element.addr2}호
					</td>
					<%-- ============================ 이사일자 ============================ --%>
					<td>
						${element.move_date}
					</td>
					<%-- ============================ 이사시간 ============================ --%>
					<td>
						${element.m_time}
					</td>
					<%-- ============================ 등록일시 ============================ --%>
					<td>
						${element.regdate}
					</td>
					<%-- ============================ 수정일시 ============================ --%>
					<td>
						${element.uptdate}
					</td>
					<%-- ============================ 수정가능여부 ============================ --%>
					<td>
						<c:if test="${element.updt_yn eq 'Y'}">
							가능
						</c:if>
						<c:if test="${element.updt_yn eq 'N'}">
							불가능
						</c:if>
					</td>
					<%-- ============================ 기계약자 여부 ============================ --%>
					<td>
						<c:if test="${element.gbn1 eq 'Y'}">
							1차 계약자
						</c:if>
						<c:if test="${element.gbn1 eq 'N'}">
							2차 계약자
						</c:if>
						<c:if test="${element.gbn1 eq 'T'}">
							3차 계약자
						</c:if>
					</td>
					<td>
						<a href="#" data-action="/sys/moveRes/reserve.do?gbn2=${gbn2}" data-keyset="{'cust_id': ${element.cust_id}}" onclick="req.post(this); return false;" class="btn_swhite">예약</a>
						<a href="#" data-action="/sys/moveRes/modify.do?gbn2=${gbn2}" data-keyset="{'cust_id': ${element.cust_id}}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/moveRes/delResProc.do?mId=${menuVO.mId}&gbn2=${gbn2}" data-keyset="{'cust_id': ${element.cust_id}}" onclick="req.ajax(this); return false;" class="btn_swhite">초기화</a>
						<a href="#" data-action="/sys/moveRes/delCustProc.do?mId=${menuVO.mId}" data-keyset="{'cust_id': ${element.cust_id}}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
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

	<div class="btn_boxR">
		<input type="button"  class="btn_dblue" value="엑셀 다운로드" onclick="excelDown();"/>
 		<%-- <a href="#" data-action="/sys/moveRes/write.do" data-keyset="{'searchSiteIdx': ${searchVO.searchSiteIdx}}" onclick="req.post(this); return false;" class="btn_cyan">등록</a> --%>
		<a href="#" data-action="/sys/moveRes/write.do?gbn2=${gbn2}" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
</form:form>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
