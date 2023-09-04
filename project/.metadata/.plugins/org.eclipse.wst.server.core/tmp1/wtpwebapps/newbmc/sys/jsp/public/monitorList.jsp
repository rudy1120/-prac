<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<h2>사전정보 모니터링 목록</h2>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="pubIdx"/>
	<form:hidden path="page"/>

	<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w5">성함</th>
				<th scope="col" class="w7">이메일</th>
				<th scope="col" class="w3">전화번호</th>
				<th scope="col" class="w10">제목</th>
				<th scope="col" >내용</th>
				<th scope="col" class="w7">일자</th>
				<th scope="col" class="w4">관리</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					
					<td>${element.usernm}</td>
					<td>${element.email}</td>
					<td>${element.tel}</td>
					<td>${element.title}</td>
					<td>${element.content}</td>
					<td>${element.create_date}</td>
					<td>
						<a href="#" data-action="/sys/public/monitorDeleteProc.do?mId=${menuVO.mId}" data-keyset="{'ptidx': '${element.ptidx}'}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
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

<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
