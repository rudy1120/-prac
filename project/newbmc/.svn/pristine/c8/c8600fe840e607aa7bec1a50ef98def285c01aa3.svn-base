<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시글 처리 이력 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.09.07		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.09.07
	 */
%>
<c:set var="listUrl" value="${pageContext.request.contextPath}/sys/bbs/log/list.do"/>
<c:set var="downUrl" value="${pageContext.request.contextPath}/sys/bbs/log/downProc.do"/>

<h2>게시글 처리 이력 목록</h2>
<form:form commandName="searchVO" name="list" id="list" action="${listUrl}?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator('#list');" htmlEscape="false">
	<form:hidden path="page"/>
	<span class="info">본인인증/공공IPIN 인증을 통해 이용 중인 시민이 등록/수정/삭제한 이력은 처리ID 정보가 없습니다.</span>
	<div class="search_box">
		<div class="fL">
			<label for="searchSiteCode" class="hidden">사이트코드선택</label>
			<form:select path="searchSiteCode" class="fL mR7">
				<form:option value="">사이트 전체</form:option>
				<c:forEach var="s" items="${siteList}">
					<form:option value="${s.siteCode}">${s.siteName}</form:option>
				</c:forEach>
			</form:select>
			<label for="searchProc" class="hidden">게시글상태선택</label>
			<form:select path="searchProc" class="fL mR7">
				<form:option value="">상태 전체</form:option>
				<form:option value="modIn">등록</form:option>
				<form:option value="modUp">수정</form:option>
				<form:option value="modDe">삭제</form:option>
				<form:option value="modReIn">답글/답변 등록</form:option>
				<form:option value="modReDe">복원</form:option>
				<form:option value="modMovIn">이동</form:option>
			</form:select>
			<%--
			<label for="searchBbsType" class="hidden">게시판타입선택</label>
			<form:select path="searchBbsType" class="fL mR7">
				<form:option value="">타입 전체</form:option>
				<c:forEach var="t" items="${bbsTypeList}">
					<form:option value="${t.code}">${t.codeNm}</form:option>
				</c:forEach>
			</form:select>
			--%>
			<label for="searchPeriodType" class="hidden">기간검색타입선택</label>
			<form:select path="searchPeriodType" class="fL mR7">
				<form:option value="year">연</form:option>
				<form:option value="month">월</form:option>
				<form:option value="range">기간</form:option>
			</form:select>
			<div id="div_y" class="mR7">
				<label for="searchYear" class="hidden">기간검색연도선택</label>
				<form:select path="searchYear">
					<fmt:parseNumber var="current" value="${yh:getYear()}"/>
					<c:forEach var="add" begin="0" end="${current - 1999}" step="1">
						<form:option value="${current - add}">${current - add}년</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div id="div_m" class="mR7" style="display: none;">
				<label for="searchMonth" class="hidden">기간검색월선택</label>
				<form:select path="searchMonth">
					<c:forEach var="month" begin="1" end="12" step="1">
						<fmt:formatNumber var="month" value="${month}" pattern="00"/>
						<form:option value="${month}">${month}월</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div id="div_r" class="mR7" style="display: none;">
				<label for="searchSday" class="hidden">시작일검색</label>
				<form:input path="searchSday" class="input100" data-validator="on" placeholder="예) 2017-08-15" data-inputType="y" data-date="y" data-ismindatefor="searchEday"/> ~
				<label for="searchEday" class="hidden">종료일검색</label>
				<form:input path="searchEday" class="input100 mR7" data-validator="on" placeholder="예) 2017-08-15" data-inputType="y" data-date="y" data-ismaxdatefor="searchSday"/>
			</div>
			<label for="searchPtTitle" class="hidden">게시판명검색</label>
			<form:input path="searchPtTitle" placeholder="게시판명 검색"/>
			<label for="searchTxt" class="hidden">게시글제목검색</label>
			<form:input path="searchTxt" placeholder="게시글 제목 검색"/>
			<input type="submit" onclick="document.list.action = '${listUrl}?mId=${menuVO.mId}'; $('#page').val(1);" class="btn_white" value="검색"/>
			<a href="#" class="btn_blue" onclick="document.list.action = '${downUrl}?mId=${menuVO.mId}'; document.list.submit(); return false;" target="_blank" title="새창열림">현재 조건 엑셀 다운로드</a>
		</div>
		<div class="fR mT15">
			전체 <span class="blue bold">${totalCnt}</span>건의 처리 이력이 있습니다.
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>게시글 처리 이력을 사이트명, 게시판명, 제목, 작성자, 처리, 처리 IP, 처리 ID, 처리일자 순서대로 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w30">[사이트명]게시판명</th>
				<th scope="col">게시글 제목</th>
				<th scope="col" class="w8">작성자</th>
				<th scope="col" class="w5">처리</th>
				<th scope="col" class="w8">처리IP</th>
				<th scope="col" class="w6">처리ID</th>
				<th scope="col" class="w10">처리일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td class="taL">
						<c:if test="${not empty element.ptIdx}">
							&nbsp;&nbsp;
							<c:if test="${not empty element.siteName}"><span class="blue bold">[${element.siteName}]</span></c:if>
							<a href="/sys/superMng/bbs/bbsMng/list.do?ptIdx=${element.ptIdx}&amp;mId=0103010000">
								${fn:replace(element.ptTitle, ' ', '')}
							</a>
						</c:if>
					</td>
					<c:set var="tit" value="${element.bTitle}"/>
					<td class="taL" title="${tit}">
						<c:if test="${fn:length(tit) > 40}"><c:set var="tit" value="${fn:substring(tit, 0, 40)}..."/></c:if>
						${tit}
					</td>
					<td>${element.bWrite}</td>
					<td>
						<c:choose>
							<c:when test="${element.bProc == 'modIn'}">등록</c:when>
							<c:when test="${element.bProc == 'modUp'}">수정</c:when>
							<c:when test="${element.bProc == 'modDe'}">삭제</c:when>
							<c:when test="${element.bProc == 'modReIn'}">답글/답변 등록</c:when>
							<c:when test="${element.bProc == 'modReDe'}">복원</c:when>
							<c:when test="${element.bProc == 'modMovIn'}">게시글 이동</c:when>
							<c:otherwise>unknown</c:otherwise>
						</c:choose>
					</td>
					<td>${element.operatorIp}</td>
					<td>${element.operatorId}</td>
					<td><fmt:formatDate value="${element.operateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>

</form:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/commonProcess.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/datePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/bbs/period.js"></script>
