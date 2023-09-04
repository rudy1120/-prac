<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 세션 변경 주기 페이지 
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2020.01.16		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2020.01.16
 */
%>
<h2>세션 변경 주기 설정</h2>

<form:form commandName="searchVO" id="list" name="list" action="/sys/totalAdminMng/period/sessionUpdate.do?mId=${menuVO.mId}" onsubmit="return validator();" method="post">
	<form:hidden path="id" value="${ADMIN_LOGIN_INFO.adminId}"/>
	
	<div class="search_box">
		<div class="fL">
			세션 주기 
			<form:input path="period" cssClass="input100" maxlength="3" data-validator="on" data-required="n" data-inputType="num" data-fieldName="시간(분)" />
			분
			<input type="submit" class="btn_sblack" value="변경"/>
		</div>
	</div>

</form:form> 
	<br/><br/>
	<table class="tableSt_list row_over">
		<caption>세션 유지기간 변경 이력</caption>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">변경 주기</th>
				<th scope="col">담당자 아이디</th>
				<th scope="col">등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="listOrder" value="${fn:length(list)}" />
			<c:forEach var="list" items="${list}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${list.period} 분</td>
					<td>${list.id}</td>
					<td>${list.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${empty list }">
		<div class="no_data">
			변경이력이 없습니다.
		</div>
	</c:if>	

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>