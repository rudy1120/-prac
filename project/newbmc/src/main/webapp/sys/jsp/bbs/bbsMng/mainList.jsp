<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/sys/jsp/bbs/bbsMng/component/variables.jsp" %>
<%
	/**
	 * 메인 게시글 관리
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2019.12.27       박상혁
	 *
	 *
	 * @author 박상혁
	 * @since 2019.12.27
	 */
%>
<br/>
<span>&nbsp;&nbsp;&nbsp;&nbsp;메인화면에 나타나는 게시글을 관리하는 페이지입니다.</span>
<span>8개 미만 등록시 최신글 순으로 나타납니다.</span>
<br/><br/>
<form:form commandName="searchVO" action="/sys/main/bbs/list.do?mId=${menuVO.mId}" id="list" name="list" methodParam="post" htmlEscape="false">
	<table class="tableSt_list row_over">
		<caption>메인게시글 관리</caption>
		<thead>
			<tr>
				<th>순서</th>
				<th>게시판</th>
				<th>제목</th>
				<th>게시기간(까지)</th>
				<th>정렬번호</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>
						<c:if test="${element.ptIdx == 768}">분양공고</c:if>
						<c:if test="${element.ptIdx == 769}">임대공고</c:if>
						<c:if test="${element.ptIdx == 788}">공지사항</c:if>
						<c:if test="${element.ptIdx == 801}">언론보도</c:if>
					</td>
					<td>${element.bTitle}</td>
					<td>${element.bTermEdate}</td>
					
					<td>
						<c:if test="${searchVO.bIdx == element.bIdx}">
							<form:input path="mainOrder" type="text" class="input50" value="${element.mainOrder}"/>
						</c:if>
						<c:if test="${searchVO.bIdx != element.bIdx}">${element.mainOrder}</c:if>
					</td>
					<td>
						<c:if test="${searchVO.bIdx == element.bIdx}">
							<form:input type="hidden" path="bIdx" />
							<input id="submitBtn" type="submit" class="btn_blue" value="저장"/>
						</c:if>
						<c:if test="${searchVO.bIdx != element.bIdx}">
							<a href="#" data-action="/sys/main/bbs/modifyOrder.do" data-keyset="{'bIdx': ${element.bIdx}}" onclick="req.post(this); return false;" class="btn_swhite">순서변경</a>
						</c:if>
						<a href="#" data-action="/sys/main/bbs/uncheck.do?mId=0103050000" data-keyset="{'bIdx': ${element.bIdx}}" onclick="req.ajax(this); return false;" class="btn_sblack">해제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<c:if test="${empty result}">
	<div class="no_data">등록된 게시물이 없습니다.</div>
</c:if>

</form:form>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>