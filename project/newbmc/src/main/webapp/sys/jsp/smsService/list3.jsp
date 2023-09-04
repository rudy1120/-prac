<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 문자발송이력
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2021.02.08		손문배		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2021.02.08
 */
%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<script type="text/javascript">

	/* //엑셀다운
	function excelDown(){

		
		
		var searchGbn = $("#searchGbn").val();
		var searchType = $("#searchType").val();
		var searchTxt = $("#searchTxt").val();
		

		
		location.href= "/sys/smsApply/ExcelDownloader2.do?searchGbn="+searchGbn+"&searchType="+searchType+"&searchTxt="+  encodeURI(searchTxt,"UTF-8");; */
		//$("#list").attr('action',"/sys/smsApply/ExcelDownloader.do?searchGbn="+searchGbn+"&searchType="+searchType+"&searchTxt="+searchTxt).submit();
		
		/*$.ajax({
			type:'post',
			url:'/sys/smsApply/ExcelDownloader.do',
			data : $("#list").serialize(),
			success : function(data) {
				
			},
			error:function(request,status,error){
				//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		}); 
	}*/
	
</script>
<h2>문자발송이력</h2>

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
				<c:forEach var="year" begin="2019" end="2030" step="1" >
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
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w10">제목</th>
				<th scope="col" class="w15">연락처</th>
				<th scope="col">내용</th>
				<th scope="col" class="w7">발송일시</th>
				<!-- <th scope="col" class="w15">관리</th> -->
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
						${element.subject}
					</td>
					<%-- ============================ 연락처 ============================ --%>
					<td>
						${element.phone}
					</td>
					<%-- ============================ 주택유형 ============================ --%>
					<td>
						${element.msg}
					</td>
					<%-- ============================ 등록일시 ============================ --%>
					<td>
						${element.sentdate}
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

<!-- 	<div class="btn_boxR">
		<input type="button"  class="btn_dblue" value="엑셀 다운로드" onclick="excelDown();"/>
	</div> -->
</form:form>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
