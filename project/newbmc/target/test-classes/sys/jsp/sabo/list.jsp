<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 웹진 구독신청 
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2022.12.23		이정화		    최초 생성 및 코드 수정
 *
 *
 * @author 이정화
 * @since 2022.12.23
 */
%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<script type="text/javascript" charaset="UTF-8">

	//엑셀다운
	function excelDown(){
	
		var searchType = $("#searchType").val();
		var searchTxt = $("#searchTxt").val();
		
		location.href= "/sys/sabo/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+encodeURI(searchTxt,"UTF-8");
		/* location.href= "/sys/webzine/ExcelDownloader.do?searchType="+searchType+"&searchTxt="+searchTxt;   */
		//$("#list").attr('action',"/sys/smsApply/ExcelDownloader.do?searchGbn="+searchGbn+"&searchType="+searchType+"&searchTxt="+searchTxt).submit();
		

	}
	
	
</script>
<h2>사보 구독 신청 목록</h2>

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<form:select path="searchType" id="searchType">
				<form:option value="name">성함</form:option>
			</form:select>
			<form:input type="text" path="searchTxt" id="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>
 
<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w2">번호</th>
				<th scope="col" class="w5">성함</th>
				<th scope="col" class="w10">주소</th>
				<th scope="col" class="w5">등록일시</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<%-- ============================ 번호 ============================ --%>
					<td>${listOrder - status.index}</td>
					<%-- ============================ 성함 ============================ --%>
					<td>
						${element.name}
					</td>
					<%-- ============================ 연락처 ============================ --%>
					<td>
						${element.address}
					</td>
					<%-- ============================ 등록일시 ============================ --%>
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
