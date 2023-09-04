<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 관리자 관리 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.01.06		엄동건			최초 생성 및 코드 수정
	 * 2017.04.24		J.Ryeon Lee		코드 리팩토링, 보안 지침 적용
	 *
	 *
	 *
	 * @author 엄동건
	 * @since 2015.01.06
	 */
%>

<h2>관리자 계정 관리</h2>
<form:form commandName="searchVO" id="list" name="list" action="${_context}/sys/totalAdminMng/sysMemberMng/list.do?mId=${menuVO.mId}" method="post">
	<form:hidden path="page"/>
	<form:hidden path="id" value=""/>

	<div class="search_box">
		<div class="fL">
			<label for="searchType" class="hidden">검색</label>
			<form:select path="searchType">
				<form:option value="name">이름</form:option>
				<form:option value="id">아이디</form:option>
			</form:select>
			<form:input path="searchTxt"/>
			<input type="submit" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="fR">
			총 <span class="blue"><strong>${resultCnt}</strong></span>개의 관리자 정보가 있습니다.
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>관리자 목록</caption>
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w30">아이디</th>
				<th scope="col">이름</th>
				<th scope="col" class="w10">레벨</th>
				<th scope="col" class="w10">상태</th>
				<th scope="col" class="w13">로그인<br />일자</th>
				<th scope="col" class="w15">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="listOrder" value="${fn:length(resultList)}" />
			<c:forEach var="element" items="${resultList}"  varStatus="memberListStatus">
				<tr>
					<td>${listOrder - memberListStatus.index}</td>
					<td class="taL">${element.id}</td>
					<td>${element.name}</td>
					<td>
						<c:choose>
							<c:when test="${element.adminAccessLevelCode == 10}">최고관리자</c:when>
							<c:when test="${element.adminAccessLevelCode == 5}">서브관리자</c:when>
						</c:choose>
					</td>
					<td>${5 <= element.loginFailedCnt ? '<span class="red">로그인 제한</span>' : '로그인 가능'}</td>
					<td>
					<fmt:timeZone value="GMT">
					<fmt:formatDate value="${element.lastestLoginDt}" pattern="yyyy-MM-dd HH:mm"/>
					</fmt:timeZone>
					</td>
					<td>
						<a href="#" data-action="${_context}/sys/totalAdminMng/sysMemberMng/modifyPage.do?mId=${menuVO.mId}"
							data-keyset="{'id': '${element.id}'}" onclick="req.post(this); return false;" class="btn_swhite">
							수정
						</a>
						<a href="#" data-action="${_context}/sys/totalAdminMng/sysMemberMng/deleteProc.do?mId=${menuVO.mId}"
							data-confirmmsg="[주의]해당 관리자 삭제시 정보는 복구 할 수 없습니다."
							data-failedmsg="관리자 정보 삭제 중 오류가 발생했습니다."
							data-successmsg="관리자 정보를 정상적으로 삭제했습니다."
							data-keyset="{'id': '${element.id}'}" onclick="req.ajax(this); return false;" class="btn_swhite">
							삭제
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty resultList }">
		<div class="no_data">
			등록된 게시물이 없습니다.
		</div>
	</c:if>

	<c:if test="${not empty resultList}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

	<div class="btn_boxR">
		<a href="#" data-action="${_context}/sys/totalAdminMng/sysMemberMng/writePage.do?mId=${menuVO.mId}" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
</form:form>
<script type="text/javascript"> 
	var yh = {}; 
	yh.siteCode = "sys";
	yh.mId = "${menuVO.mId}";
</script>
<script type="text/javascript" src="${_context}/common/js/commonProcess.js"></script>
