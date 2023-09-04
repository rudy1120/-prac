<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<h2>사전정보공표 목록</h2>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="pubIdx"/>
	<form:hidden path="page"/>

	<%-- ============================ 검색 영역 ============================ --%>
	<div class="search_box">
		<div class="fL">
			<form:select path="searchCatIdx">
				<option value="">-- 카테고리선택 --</option>
				<c:forEach var="c" items="${catList}">
					<c:choose>
						<c:when test="${c.catIdx eq searchVO.searchCatIdx}">
							<form:option value="${c.catIdx}" selected="true">${c.catName}</form:option>
						</c:when>
						<c:otherwise>
							<form:option value="${c.catIdx}">${c.catName}</form:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<label for="searchName">공표목록</label>
			<form:input type="text" path="searchName"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>

	<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w10">카테고리</th>
				<th scope="col" class="w15">공표목록</th>
				<th scope="col">공표항목</th>
				<th scope="col" class="w7">시기</th>
				<th scope="col" class="w7">주기</th>
				<th scope="col" class="w7">공개부서</th>
				<th scope="col" class="w10">수정일</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${element.catName}</td>
					<td>${element.name}</td>
					<td>${element.article}</td>
					<td>${element.time}</td>
					<td>${element.cycle}</td>
					<td>${element.depart}</td>
					<td>${element.uptDate}</td>
					<td>
						<a href="#" data-action="/sys/public/dataModify.do" data-keyset="{'pubIdx': '${element.pubIdx}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/public/dataDeleteProc.do?mId=${menuVO.mId}" data-keyset="{'pubIdx': '${element.pubIdx}'}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
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
		<a href="#" data-action="/sys/public/dataWrite.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
</form:form>

<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
