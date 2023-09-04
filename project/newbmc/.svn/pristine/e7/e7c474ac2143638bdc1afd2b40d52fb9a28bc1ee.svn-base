<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객참여이벤트 관리자 참여자 리스트 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.09.04		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.09.04
 */
%>

<h2>고객경영이벤트 참여자 목록</h2>

<form:form commandName="searchVO" name="list" id="list" methodParam="post" onsubmit="$('#page').val(1);">
	<form:hidden path="page"/>
	<form:hidden path="iswin"/>

	<table class="tableSt_list row_over mB60">
		<caption>참여자 목록 조회화면입니다.</caption>
		<thead>
			<tr>
				<th scope="col">제목</th>
				<th scope="col">기간</th>
				<th scope="col">발표일</th>
				<th scope="col">참여인원</th>
				<th scope="col">추첨인원</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${result.subject}</td>
				<td>${result.exedate}</td>
				<td>${result.pubdate}</td>
				<td>${result.parCnt}</td>
				<td>${result.lim}</td>
			</tr>
		</tbody>
	</table>
	<c:if test="${result.gubun eq 2}">
	<div class="search_box">
		<div class="fL">
			<a href="#" data-action="/sys/event/view.do?idx=${result.idx}" data-keyset="{'iswin' : '1', 'page' : 1}" onclick="req.post(this); return false;" class="btn_sblack">당첨자보기</a>
			<a href="#" data-action="/sys/event/view.do?idx=${result.idx}" data-keyset="{'iswin' : '0', 'page' : 1}" onclick="req.post(this); return false;" class="btn_sblack">전체보기</a>
			<c:if test="${result.procLvl == 1}">
				<a href="#" data-action="/sys/event/loc.do?mId=${menuVO.mId}" data-stay="y" data-keyset="{'idx': ${result.idx}, 'lim': ${result.lim}}" onclick="req.ajax(this); history.go(0);" class="btn_sblack">
					추첨하기
				</a>
			</c:if>	
		</div>
	</c:if>
	<c:if test="${result.gubun eq 1}">
	<div style="height:40px;">
		<a href="#" data-action="/sys/event/loc2.do?mId=${menuVO.mId}" data-stay="y" data-keyset="{'idx': ${result.idx}, 'lim': ${result.lim}}" onclick="req.ajax(this); history.go(0);" class="btn_sblack">
				마감하기
		</a>
	</div>
	</c:if>
		<div class="cleB"></div>

	
	<table class="tableSt_list row_over">
		<caption>참여자 명단</caption>
		
		<thead>
			<c:if test="${result.gubun eq 1}">
			<tr>
				<th scope="col" class="w2">번호</th> 
				<th scope="col" class="w2">참여자명</th>
				<th scope="col" class="w2">전화번호</th>
				<th scope="col" class="w2">생년월일</th>
				<th scope="col" class="w3">참여일자</th>
				<th scope="col" class="w11">주소/내용</th>
				<th scope="col" class="w3">첨부파일</th>
			</tr>
			</c:if>
			<c:if test="${result.gubun eq 2}">
			<tr>
				<th scope="col" class="w7">번호</th> 
				<th scope="col" class="w11">참여자명</th>
				<th scope="col" class="w13">전화번호</th>
				<th scope="col" class="w7">당첨여부</th>
				<th scope="col" class="w11">참여일자</th>
			</tr>
			</c:if>
		</thead>
		<tbody>
			<c:if test="${result.gubun eq 1}">
			<c:forEach var="e" items="${list}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${e.usernm}</td>
					<td>${e.tel}</td>
					<td>${e.birth}</td>
					<td>${e.regdate}</td>
					<c:choose>
						<c:when test="${not empty e.content}">
							<td>${e.content}</td>						
						</c:when>
						<c:otherwise>
							<td>${e.bAddr1} ${e.bAddr2}</td>
						</c:otherwise>
					</c:choose>
					<td class="list_file">
					<c:set var="fileExts" value="${e.attachId}" scope="request"/>
						<c:if test="${!empty fileExts}">
							<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="showDownload" value="Y"/>
								<c:param name="updateFlag" value="N"/>
								<c:param name="downloadYn" value="Y"/>
								<c:param name="param_atchFileId" value="${fileExts}"/>
							</c:import>
						</c:if>
						<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
					</td>
				</tr>
			</c:forEach>
			</c:if>
				<c:if test="${result.gubun eq 2}">
				<c:forEach var="e" items="${list}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${e.usernm}</td>
					<td>${e.tel}</td>
					<td>${e.iswin == 1 ? 'Y' : 'N'}</td>
					<td>${e.regdate}</td>
				</tr>
			</c:forEach>
				</c:if>
		</tbody>
	</table>

	<c:if test="${empty list}">
		<div class="no_data">참여자가 없습니다.</div>
	</c:if>
	
</form:form>

<c:if test="${not empty result}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>

<div class="btn_boxR">
	<a href="/sys/event/list.do?mId=${menuVO.mId}" class="btn_dblue">목록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
