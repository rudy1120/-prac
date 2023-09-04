<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<h2>직원 목록</h2>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="usrId"/>
	<form:hidden path="page"/>

	<%-- ============================ 검색 영역 ============================ --%>
	<div class="search_box">
		<div class="fL">
			<form:select path="searchRealUseDepCode">
				<option value="">-- 부서선택 --</option>
				<c:forEach var="d" items="${departList}">
					<c:if test="${d.newParent == '0'}">
						<form:option value="${d.depCode}" style="font-weight:600">${d.depCodeNm}</form:option>
					</c:if>
					<c:if test="${d.newParent != '0'}">
						<form:option value="${d.depCode}">${d.depCodeNm}</form:option>
					</c:if>
				</c:forEach>
			</form:select>
			<form:select path="searchType">
				<form:option value="nm">직원명</form:option>
			</form:select>
			<form:input type="text" path="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>

	<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
<!-- 				<th scope="col" class="w7">ID</th> -->
				<th scope="col" class="w7">이름</th>
				<th scope="col" class="w10">부서명</th>
				<th scope="col" class="w10">직책</th>
				<th scope="col">주요업무</th>
				<th scope="col" class="w7">사용여부</th>
				<th scope="col" class="w10">수정일</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
<%-- 					<td>${element.usrId}</td> --%>
					<td>${element.usrNm}</td>
					<td>${element.depCodeNm}</td>
					<td>${element.blgTeamNm}</td>
					<td>${element.adiInfo7}</td>
					<td>${element.useYn == 'Y' ? '사용' : '미사용'}</td>
					<td>${element.dtUpdate}</td>
					<td>
						<a href="#" data-action="/sys/empMng/modify.do" data-keyset="{'usrId': '${element.usrId}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/empMng/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'usrId': '${element.usrId}', 'useYn' : '${element.useYn}'}" onclick="req.ajax(this); return false;" class="btn_sblack">${element.useYn == 'Y' ? '삭제' : '복구'}</a>
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
		<a href="#" data-action="/sys/empMng/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
</form:form>

<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
