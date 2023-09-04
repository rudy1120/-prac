<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 참가자 리스트 조회
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

<h2>${partContVO.title} 참여자 목록</h2>
<form:form commandName="searchVO" name="list" id="list" methodParam="post" onclick="$('#page').val(1);">
	<form:hidden path="page"/>

	<table class="tableSt_list row_over mB60">
		<caption>참여자 목록 조회화면입니다.</caption>
		<thead>
			<tr>
				<th scope="col">구분</th>
				<th scope="col">제목</th>
				<th scope="col">기간</th>
				<th scope="col">참여인원</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
				<c:choose>
					<c:when test="${partContVO.gbn == 'A'}">
						사업 및 경영혁신 아이디어
					</c:when>
					<c:when test="${partContVO.gbn == 'B'}">
						주민참여예산제
					</c:when>
				</c:choose>
				</td>
				<td>${partContVO.title}</td>
				<td>${partContVO.sdate} ~ ${partContVO.edate}</td>
				<td>${totalCnt}</td>
			</tr>
		</tbody>
	</table>
	
	<div class="search_box">
		<div class="fL">
			<label for="searchType" class="hidden">검색조건선택</label>
			<form:select path="searchType">
				<form:option value="nm">참여자명</form:option>
			</form:select>
			<form:input path="searchTxt"/>
			<input type="submit"  class="btn_white" value="검색"/>
		</div>
		<div class="fR">
			총 <span class="bold blue">${totalCnt}</span>건의 데이터가 있습니다.
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>참여자 명단</caption>
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w11">참여자명</th>
				<th scope="col">이메일</th>
				<th scope="col" class="w13">전화번호</th>
				<th scope="col">주소</th>
				<th scope="col" class="w11">상세주소</th>
				<th scope="col" class="w11">참여일자</th>
				<th scope="col" class="w11">신청서 다운로드</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${e.usernm}</td>
					<td>${e.email}</td>
					<td>${e.tel}</td>
					<td>${e.addr1}</td> 
					<td>${e.addr2}</td>
					<td>${e.createDate}</td>
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
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">참여자가 없습니다.</div>
	</c:if>
</form:form>

<c:if test="${not empty result}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>

<div class="btn_boxR">
	<a href="/sys/participation/list.do?mId=${menuVO.mId}" class="btn_dblue">목록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script> 
