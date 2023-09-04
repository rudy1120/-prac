<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 개인정보 취급 메뉴 데이터 삭제 이력 열람
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.06.20		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.06.20
	 */
%>
<c:set var="listUrl" value="/sys/privacy/data/history/list.do?mId=${menuVO.mId}" />
<c:set var="downUrl" value="/sys/privacy/data/history/down.do?mId=${menuVO.mId}" />

<p class="info mT10"><span class="red">1년 이내의 이력만 출력</span>합니다.</p>
<h2>개인정보 취급 메뉴 데이터 삭제 이력 열람</h2>
<form:form commandName="searchVO" name="list" id="list" action="${listUrl}" methodParam="post">
	<form:hidden path="page"/>
	<form:hidden path="prmIdx"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchType" class="hidden">검색키 선택 항목</label>
			<form:select path="searchType">
				<form:option value="p_nm">프로그램명</form:option>
			</form:select>
			<label for="searchTxt" class="hidden">검색값 입력</label>
			<form:input path="searchTxt"/>
			<form:input path="searchSday" class="input100" placeholder="시작일" data-date="y" data-ismindatefor="searchEday"/> ~
			<form:input path="searchEday" class="input100" placeholder="종료일" data-date="y" data-ismindatefor="searchSday"/>
			<input type="submit" onclick="$('#page').val(1); document.list.action = '${listUrl}';" class="btn_white" value="검색"/>
			<c:if test="${not empty result}">
				<input type="submit" onclick="document.list.action = '${downUrl}'; return confirm('현재 검색 결과를 다운로드하시겠습니까?');" class="btn_white" value="다운로드" />
			</c:if>
			<a href="/sys/privacy/prm/list.do?mId=${menuVO.mId}" class="btn_white">설정</a>
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption></caption>
		<thead>
			<tr>
				<th scope="col" class="w5">번호</th>
				<th scope="col">프로그램명</th>
				<th scope="col" class="w8">테이블명</th>
				<th scope="col" class="w6">보유 기간</th>
				<th scope="col" class="w6">삭제 건수</th>
				<th scope="col" class="w13">삭제일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td class="taL">${element.prmName}</td>
					<td>${element.prmTableName}</td>
					<td>${element.prmPeriod}</td>
					<td>${element.procCnt}</td>
					<td><fmt:formatDate value="${element.deleteDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 이력이 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>
</form:form>

<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script type="text/javascript">
	yh.siteCode = "sys";
	$(document).ready(function() {
		dp.bind($("[data-date=y]"));
	});
</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
