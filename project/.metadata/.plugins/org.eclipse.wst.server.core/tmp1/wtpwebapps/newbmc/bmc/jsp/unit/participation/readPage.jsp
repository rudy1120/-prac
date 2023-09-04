<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 조회 리스트 화면
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
<style>
	a.btn_dblue {
		padding: 8px 13px;
		vertical-align: middle;
		line-height: 19px;
		display: inline-block;
		border-radius: 3px;
		background: #3e4959;
		color: #ffffff !important;
		letter-spacing: -1px;
		border: 0px;
	}
</style>

<jsp:useBean id="currTime" class="java.util.Date" />
<fmt:formatDate var="currTime" value="${currTime}" pattern="yyyy-MM-dd" />

<div id="contents"><div class="content">
	
	<div class="gap20"></div>
	<p class="depth2-title">${participantVO.usernm}님 조회 결과</p>

<div class="gap"></div>

<form:form commandName="participantVO" name="list" id="list" methodParam="post" onsubmit="$('#page').val(1);">
	<form:hidden path="page"/>
	<form:hidden path="usernm"/> 
	<form:hidden path="tel"/> 
	
	<div class="bod_head clFix">
		<p class="page_num">
			총 <span class="bold blue">${totalCnt}</span>건의 데이터가 있습니다.
		</p>
		<fieldset class="bod_search">
			<form:select path="searchType">
				<form:option value="nm">제목</form:option>
			</form:select>
			<form:input path="searchTxt" />
			<input type="submit" value="검색" name="searchBt" title="검색" />
		</fieldset>
	</div>
	<div class="gap"></div>
	<div class="orgTable_wrap jobTable_wrap">
		<table class="orgTable jobTable">
		<caption>참여자명단</caption>
		<thead>
			<tr>
				<th width="5%">번호</th>
				<th width="15%">구분</th>
				<th width="20%">제목</th>
				<th width="10%">전화번호</th>
				<th width="10%">참가일</th>
				<th width="10%">신청서</th>
				<th width="10%">삭제하기</th>
			</tr>
		</thead>
		<c:forEach var="e" items="${result}" varStatus="status">
			<tr>
				<td>${listOrder - status.index}</td>
				<td>
					<c:choose>
						<c:when test="${e.gbn == 'A'}">
							사업 및 경영혁신 아이디어
						</c:when>
						<c:when test="${e.gbn == 'B'}">
							주민참여예산제
						</c:when>
					</c:choose>
				</td>
				<td>${e.title}</td>
				<td>${e.tel}</td>
				<td>${fn:substring(e.createDate, 0, 10)}</td>
				<td>
					<c:set var="fileExts" value="${e.attachId}" scope="request"/>
					<c:if test="${!empty fileExts}">
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="showDownload" value="Y"/>
							<c:param name="updateFlag" value="N"/>
							<c:param name="downloadYn" value="Y"/>
							<c:param name="param_atchFileId" value="${fileExts}"/>
						</c:import>
					</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="${currTime < e.edate}">		
							<a href="#" data-action="/bmc/participation/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'ptidx': ${e.ptidx}}" onclick="req.ajax(this); return false;" class="btn_sblack">
								삭제하기
							</a>
						</c:when>
						<c:otherwise>
							마감
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	<c:if test="${empty result}">
		<div class="gap"></div>	
		<div class="no_data">조회 자료가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="bod_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>


		<div class="btn_wrap">
			<input type="button" class="btn-blue" onclick="location.href='/bmc/participation/list.do?mId=${menuVO.mId}'" value="목록"/>
		</div>
	

</div></div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>