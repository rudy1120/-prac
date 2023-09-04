<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객참여이벤트 관리자 리스트 조회 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.09.02		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.09.02
 */
%>

<h2>고객경영이벤트 목록</h2>

<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<form:select path="searchType">
				<form:option value="nm">제목</form:option>
			</form:select>
			<form:input type="text" path="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w20">이미지</th>
				<th scope="col">제목</th>
				<th scope="col" class="w15">기간</th>
				<th scope="col" class="w10">발표일</th>
				<th scope="col" class="w5">추첨인원</th>
				<th scope="col" class="w5">진행여부</th>
				<th scope="col" class="w5">사용여부</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<td>${listOrder - status.index}</td>
					<td>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${element.attachFile}"/>
							<c:param name="mode"   value="ratio"/>
							<c:param name="width"  value="240"/>
							<c:param name="height" value="169"/>
						</c:import>
					</td>
					<td class="taL">
						<a href="#" data-action="/sys/event/view.do?idx=${element.idx}" onclick="req.post(this); return false;">${element.subject}</a>
					</td>
					<td>
						${element.exedate}
					</td>
					<td>
						${element.pubdate}
					</td>
					<td>
						${element.lim}
					</td>
					<td>
						${element.procLvl eq 1 ? 'Y' : 'N'}
					</td>
					<td>
						${element.is_use}
					</td>
					<td>
						<a href="#" data-action="/sys/event/modify.do" data-keyset="{'idx': ${element.idx}}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/event/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'idx': ${element.idx}}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>
</form:form>

<c:if test="${not empty result}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>

<div class="btn_boxR">
	<a href="#" data-action="/sys/event/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
