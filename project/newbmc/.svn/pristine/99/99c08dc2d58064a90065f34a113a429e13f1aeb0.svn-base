<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 코멘트 출력
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.10.24		권태성			최초 생성 및 코드 수정
	 *
	 *
	 * @author 권태성
	 * @since 2017.10.24
	 */
%>

<h2>설문조사 답변보기</h2>

<table class="tableSt_list row_over">
	<caption>항목별 상세히 안내하는 표입니다.</caption>
	<thead>
		<tr>
			<th scope="col" class="w10">번호</th>
			<th scope="col" class="">답변</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty responseList}">
			<c:forEach var="response" items="${responseList }" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${response.value}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>

<c:if test="${empty responseList}">
	<div class="no_data">등록된 데이터가 없습니다.</div>
</c:if>