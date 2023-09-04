<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 공급용도 목록 
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.18		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.18
 */
%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<script type="text/javascript">

function delUses(codename){
		
	if(confirm("삭제 하시겠습니까?")) {
		$("#codename").val(codename);
		$("#delForm").attr("action","/sys/buy/uses/deleteProc.do").submit();
	}else{
		return false;
	}

}
	
</script>
<h2>공급용도 목록</h2>

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>

	<div class="search_box">
		<div class="fL">
			<label for="gbn" >용도명 : </label>
			<form:input type="text" path="searchTxt" id="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>
 
<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" >공급용도명</th>
				<th scope="col" class="w10">사용여부</th>
				<th scope="col" class="w10">등록일시</th>
				<th scope="col" class="w15">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<%-- ============================ 번호 ============================ --%>
					<td>${listOrder - status.index}</td>
					
					<%-- ============================ 공급용도명 ============================ --%>
 					<td>
 						${element.codename}
					</td>
					<%-- ============================ 사용여부 ============================ --%>
					<td>
						${element.useYN}
					</td>
					<%-- ============================ 등록일시 ============================ --%>
					<td>
						${element.regDate}
					</td>
					
					<td>
						<a href="#" data-action="/sys/buy/uses/modify.do?mId=${menuVO.mId}&idx=${element.idx}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<%-- <a href="#" onclick="delUses('${element.codename}');" class="btn_sblack">삭제</a> --%>
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
<form id="delForm" name="delForm" methodParam="post"  htmlEscape="false">
	<input type="hidden" id="codename" name="codename"/>
</form>
<div class="btn_boxR">
	<a href="#" data-action="/sys/buy/uses/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
</div>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
