<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<h2>카테고리 목록</h2>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" htmlEscape="false">
	<form:hidden path="catIdx"/>

	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col">카테고리명</th>
				<th scope="col" class="w10">정렬순서</th>
				<th scope="col" class="w10">사용여부</th>
				<th scope="col" class="w15">수정일자</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${element.catName}</td>
					<td>${element.orderNum}</td>
					<td>${element.useYn == 'Y' ? '사용' : '미사용'}</td>
					<td>${element.uptDate}</td>
					<td>
						<a href="#" data-action="/sys/public/categoryModify.do" data-keyset="{'catIdx': '${element.catIdx}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/public/categoryDeleteProc.do?mId=${menuVO.mId}" data-keyset="{'catIdx': '${element.catIdx}'}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>

	<div class="btn_boxR">
		<a href="#" data-action="/sys/public/categoryWrite.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
</form:form>

<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
