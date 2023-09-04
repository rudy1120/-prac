<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 변경 이력 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.02.16		김혜민			최초 생성 및 코드 작성
	 * 2017.10.12		J.Ryeon Lee		디자인 정비
	 *
	 * @author 김혜민
	 * @since 2015.02.16
	 */
%>
<c:set var="listUrl" value="${_context}/sys/${siteCode}/bbs/bbsConfigLog/list.do"/>
<c:set var="downUrl" value="${_context}/sys/${siteCode}/bbs/bbsConfigLog/downProc.do"/>

<h2>게시판 변경 이력</h2>
<form:form commandName="searchVO" name="list" id="list" action="${listUrl}?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>

	<div class="search_box">
		<div class="fL">
			<form:select path="searchType">
				<form:option value="idx">게시판코드</form:option>
					<form:option value="title">제목</form:option>
				<form:option value="writer">이름</form:option>
			</form:select>
			<form:input type="text" path="searchTxt"/>
			<input type="submit" onclick="document.list.action = '${listUrl}?mId=${menuVO.mId}'; $('#page').val(1);" class="btn_white" value="검색"/>
			<a href="#" class="btn_blue" onclick="document.list.action = '${downUrl}?mId=${menuVO.mId}'; document.list.submit(); return false;" target="_blank" title="새창열림">현재 조건 엑셀 다운로드</a>
		</div>
		<div class="fR mT15">
			전체 <span class="blue bold">${totalCnt}</span>건의 이력이 있습니다.
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>게시판 생성/수정/삭제 로그 목록</caption>
		<thead>
			<tr>
				<th scope="col" class="w5">코드</th>
				<th scope="col">처리 당시 게시판명</th>
				<th scope="col" class="w10">부서명</th>
				<th scope="col" class="w8">이름</th>
				<th scope="col" class="w8">처리ID</th>
				<th scope="col" class="w7">처리 상태</th>
				<th scope="col" class="w10">접근 아이피</th>
				<th scope="col" class="w13">처리일자</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${not empty result}">
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${element.ptIdx}</td>
					<td class="taL">${element.ptTitle}</td>
					<td>${element.ptDeptName}</td>
					<td>${element.ptWriter}</td>
					<td>${element.ptWriteId}</td>
					<td>${element.ptProc}</td>
					<td>${element.ptHostip}</td>
					<td><fmt:formatDate value="${element.ptDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty result}">
			<tr>
				<td colspan="8" class="no_data">등록된 게시물이 없습니다.</td>
			</tr>
		</c:if>
		</tbody>
	</table>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>
</form:form>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
