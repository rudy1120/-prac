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
			} else if(sel == 'applyY' || sel == 'applyN'){
				$("#searchTxt").val("");
				$("#searchTxt").hide();
			} else{
				$("#searchTxt").val("");
				$("#searchTxt").hide();
			}
		});
		
		var sel = $("#searchType option:selected").val();
		
		if(sel == 'applyY' || sel == 'applyN'){
			$("#searchTxt").hide();
		}
		
	
	});
	

	//엑셀다운
	function excelDown(){
	
		var searchType = $("#searchType").val();
		var searchTxt = $("#searchTxt").val();
		
		location.href= "/sys/moveRes/agree1/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+encodeURI(searchTxt,"UTF-8");
		/* location.href= "/sys/webzine/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+searchTxt;   */
		//$("#list").attr('action',"/sys/smsApply/ExcelDownloader.do?searchGbn="+searchGbn+"&searchType="+searchType+"&searchTxt="+searchTxt).submit();
		
	}
	
</script>

<h2>시청앞 행복주택 동의서 목록</h2>

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="agreementVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<form:select path="searchType" id="searchType">
				<form:option value="name">성함</form:option>
				<form:option value="addr1">동</form:option>
				<form:option value="applyY">제출자</form:option>
				<form:option value="applyN">미제출자</form:option>
			</form:select>
			<form:input type="text" path="searchTxt" id="searchTxt"/>
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
				<th scope="col" class="w5">필수1</th>
				<th scope="col" class="w5">필수2</th>
				<th scope="col" class="w5">필수3</th>
				<th scope="col" class="w5">이해여부</th>
				<th scope="col" class="w5">제출일시</th>
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
						${element.username}
					</td>
					<%-- ============================ 연락처 ============================ --%>
					<td>
						${element.usertel}
					</td>
					<%-- ============================ 동,호수 ============================ --%>
					<td>
						${element.addr1}동   ${element.addr2}호
					</td>
					<%-- ============================ 필수1 ============================ --%>
					<td>
						<c:if test="${element.chk1 eq 'Y'}">
							동의함
						</c:if>
						<c:if test="${element.chk1 eq 'N'}">
							동의안함
						</c:if>
					</td>
					<%-- ============================ 필수2 ============================ --%>
					<td>
						<c:if test="${element.chk2 eq 'Y'}">
							동의함
						</c:if>
						<c:if test="${element.chk1 eq 'N'}">
							동의안함
						</c:if>
					</td>
					<%-- ============================ 필수3 ============================ --%>
					<td>
						<c:if test="${element.chk3 eq 'Y'}">
							동의함
						</c:if>
						<c:if test="${element.chk1 eq 'N'}">
							동의안함
						</c:if>
					</td>
					<%-- ============================ 이해여부 ============================ --%>
					<td>
						<c:if test="${element.understand eq 'Y'}">
							이해함
						</c:if>
						<c:if test="${element.understand eq 'N'}">
							이해안함
						</c:if>
					</td>
					<%-- ============================ 제출일시 ============================ --%>
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

	<div class="btn_boxR">
		<input type="button"  class="btn_dblue" value="엑셀 다운로드" onclick="excelDown();"/>
	</div>
</form:form>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
