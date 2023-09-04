<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 관리자 리스트 조회
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
%>

<h2>고객경영참여 목록</h2>

<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<form:select path="searchGbn">
				<form:option value="">전체</form:option>
				<form:option value="A">사업 및 경영혁신 아이디어</form:option>
				<form:option value="B">주민참여예산제</form:option>
				<form:option value="C">규제입증책임제</form:option>				
			</form:select>
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
				<th scope="col" class="w10">사이트</th>
				<th scope="col" class="w20">썸네일</th>
				<th scope="col">제목</th>
				<th scope="col" class="w15">기간</th>
				<th scope="col" class="w5">구분</th>
				<th scope="col" class="w15">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<td>${listOrder - status.index}</td>
					<td>
						부산도시공사
					</td>
					<td>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${element.thumbnail}"/>
							<c:param name="mode" value="ratio"/>
							<c:param name="width" value="240"/>
							<c:param name="height" value="169"/>
						</c:import>
					</td>
					<td class="taL">
						<a href="/sys/participation/memberList.do?mId=${menuVO.mId}&idx=${element.idx}">${element.title}</a>
					</td>
					<td>
						${element.sdate} ~ ${element.edate}
					</td>
					<td>
						<c:choose>
                            <c:when test="${element.gbn == 'A'}">사업 및 경영혁신 아이디어</c:when>
                            <c:when test="${element.gbn == 'B'}">주민참여예산제</c:when>
                            <c:when test="${element.gbn == 'C'}">규제입증책임제</c:when>
                            
                            <c:otherwise>기타</c:otherwise>
                        </c:choose>
					</td>
					<td>
						<a href="#" data-action="/sys/participation/modify.do" data-keyset="{'idx': ${element.idx}}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/participation/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'idx': ${element.idx}}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
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
	<a href="#" data-action="/sys/participation/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
