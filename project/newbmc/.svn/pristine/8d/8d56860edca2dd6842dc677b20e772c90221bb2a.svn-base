<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시글 등록/삭제/수정/조회 통계
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.08.28		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.08.28
	 */
%>
<c:set var="listUrl" value="${_context}/sys/bbs/stat/operation/list.do"/>
<c:set var="downUrl" value="${_context}/sys/bbs/stat/operation/downProc.do"/>

<h2>게시글 등록/삭제/수정/조회 통계</h2>
<form:form commandName="searchVO" name="list" id="list" action="${listUrl}?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator('#list');" htmlEscape="false">
	<form:hidden path="page"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchSiteCode" class="hidden">사이트코드선택</label>
			<form:select path="searchSiteCode" class="fL mR7">
				<form:option value="">사이트 전체</form:option>
				<c:forEach var="s" items="${siteList}">
					<form:option value="${s.siteCode}">${s.siteName}</form:option>
				</c:forEach>
			</form:select>
			<label for="searchBbsType" class="hidden">게시판타입선택</label>
			<form:select path="searchBbsType" class="fL mR7">[${searchBbsType}]
				<form:option value="">타입 전체</form:option>
				<c:forEach var="t" items="${bbsTypeList}">
					<form:option value="${t.codeId}">${t.codeNm}</form:option>
				</c:forEach>
			</form:select>
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
					<c:forEach var="add" begin="0" end="${current - 2019}" step="1">
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
			<label for="searchTxt" class="hidden">게시판명검색</label>
			<form:input path="searchTxt" placeholder="게시판명 검색"/>
			<input type="submit" onclick="document.list.action = '${listUrl}?mId=${menuVO.mId}'; $('#page').val(1);" class="btn_white" value="검색"/>
			<a href="#" class="btn_blue" onclick="document.list.action = '${downUrl}?mId=${menuVO.mId}'; document.list.submit(); return false;" target="_blank" title="새창열림">현재 조건 엑셀 다운로드</a>
		</div>
		<div class="fR mT15">
			전체 <span class="blue bold">${totalCnt}</span>건의 통계가 있습니다.
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>게시글 등록/삭제/수정/조회 통계 통계 목록을 사이트명, 게시판 타입, 게시판명, 등록 건수, 삭제 건수, 수정 건수, 조회 건수 순서대로 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col">[사이트명] 게시판명</th>
				<th scope="col" class="w10">게시판 타입</th>
				<th scope="col" class="w8">등록 건수</th>
				<th scope="col" class="w8">삭제 건수<br/>(전체)</th>
				<th scope="col" class="w8">삭제 건수<br/>(관리자)</th>
				<th scope="col" class="w8">삭제 건수<br/>(사용자)</th>
				<th scope="col" class="w8">삭제 건수<br/>(자동)</th>
				<th scope="col" class="w8">수정 건수</th>
				<th scope="col" class="w8">조회 건수</th>
			</tr>
		</thead>
		<tbody>
			<tr class="total_td">
				<td colspan="2"><span class="blue bold">${searchSummary}</span> 전체</td>
				<td><fmt:formatNumber value="${snippet.sumCreateCnt}" pattern="###,###,###"/></td>
				<td><fmt:formatNumber value="${snippet.sumDeleteCnt + snippet.sumAutoDeleteCnt}" pattern="###,###,###"/></td>
				<td><fmt:formatNumber value="${snippet.sumAdminDeleteCnt}" pattern="###,###,###"/></td>
				<td><fmt:formatNumber value="${snippet.sumUserDeleteCnt}" pattern="###,###,###"/></td>
				<td><fmt:formatNumber value="${snippet.sumAutoDeleteCnt}" pattern="###,###,###"/></td>
				<td><fmt:formatNumber value="${snippet.sumUpdateCnt}" pattern="###,###,###"/></td>
				<td><fmt:formatNumber value="${snippet.sumHitCnt}" pattern="###,###,###"/></td>
			</tr>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td class="taL">
						<span class="blue bold">[${element.siteName}]</span>
						<a href="/sys/superMng/bbs/bbsMng/list.do?ptIdx=${element.ptIdx}&amp;mId=0103010000">${element.configName}</a>
					</td>
					<td>${element.bbsTypeName}</td>
					<td><fmt:formatNumber value="${element.createCnt}" pattern="###,###,###"/></td>
					<td><fmt:formatNumber value="${element.deleteCnt}" pattern="###,###,###"/></td>
					<td><fmt:formatNumber value="${element.adminDeleteCnt}" pattern="###,###,###"/></td>
					<td><fmt:formatNumber value="${element.userDeleteCnt}" pattern="###,###,###"/></td>
					<td><fmt:formatNumber value="${element.autoDeleteCnt}" pattern="###,###,###"/></td>
					<td><fmt:formatNumber value="${element.updateCnt}" pattern="###,###,###"/></td>
					<td><fmt:formatNumber value="${element.hitCnt}" pattern="###,###,###"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>

</form:form>
<script type="text/javascript" src="${_context}/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="${_context}/common/js/commonProcess.js"></script>
<script type="text/javascript" src="${_context}/common/js/datePicker.js"></script>
<script type="text/javascript" src="${_context}/sys/js/bbs/period.js"></script>
