<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 직원 업무 정보 목록(관련해 문의는 개발팀 이자련 및 김장섭)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.09.27		J.Ryeon Lee		최초 생성 및 코딩(리팩토링)
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.09.27
	 */
%>
<form:form commandName="searchVO" id="list" name="list" action="${_context}/${siteCodeFull}/staff/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>

	<%-- ===================================== 검색 영역 ===================================== --%>
	<div class="bod_head">
		<p class="page_num">현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}</p>
		<fieldset class="bod_search">
			<legend class="blind">게시판 검색</legend>
			<c:if test="${siteCode == 'portal' && menuVO.mId == '0506030000'}">
				<label for="deptCode" class="blind">부서 선택</label>
				<form:select path="deptCode">
					<form:option value="">부서 전체</form:option>
					<c:forEach var="element" items="${deptList}" varStatus="status">
						<form:option value="${element.deptId}">${element.deptName}</form:option>
					</c:forEach>
				</form:select>
			</c:if>

			<label for="searchType" class="blind">검색유형선택</label>
			<form:select path="searchType">
				<form:option value="all">전체</form:option>
				<form:option value="usr_nm">이름</form:option>
				<form:option value="real_use_dep_nm">부서</form:option>
				<form:option value="adi_info7">업무내용</form:option>
				<form:option value="telno">전화번호</form:option>
			</form:select>
			<label for="searchTxt" class="blind">검색어</label>
			<form:input path="searchTxt" title="검색어"/>
			<input type="submit" value="검색" title="검색"/>
		</fieldset>
	</div>

	<%-- ===================================== 목록 영역 ===================================== --%>
	<div class="bod_wrap">
		<div class="scroll">
			<table class="bod_list staff">
				<caption>직원 목록을 부서, 직위, 이름, 전화번호, 업무 내용 순서대로 안내하는 표입니다.</caption>
				<thead>
					<tr>
						<th scope="col" class="list_dept">부서</th>
						<th scope="col" class="list_class">직위</th>
						<th scope="col" class="list_name">이름</th>
						<th scope="col" class="list_tel">전화번호</th>
						<th scope="col" class="list_work">업무 내용</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="element" items="${result}" varStatus="status">
						<tr class="clFix">
							<td class="list_dept">${element.realUseDepNm}<c:if test="${not empty element.blgTeamNm}"><br>${element.blgTeamNm}</c:if></td>
							<td class="list_class">${element.clssNm}</td>
							<td class="list_name">${element.usrNm}</td>
							<td class="list_tel"><a href="tel:${element.telno}">${element.telno}</a></td>
							<td class="list_work">${yh:nl2br(fn:escapeXml(element.adiInfo7))}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty result}">
						<tr>
							<td colspan="5">직원 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<c:if test="${not empty result}">
		<div class="bod_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
		</div>
	</c:if>
</form:form>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
