<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 통합 로그 보관 주기 변경 이력
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.06.08		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.06.08
	 */
%>

<c:set var="listUrl" value="/sys/logging/config/history/list.do?mId=${menuVO.mId}" />
<c:set var="downUrl" value="/sys/logging/config/history/down.do?mId=${menuVO.mId}" />

<h2>통합 로그 보관 주기 변경 이력 열람</h2>
<form:form commandName="searchVO" name="list" id="list" action="/sys/logging/config/history/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>
	<form:hidden path="configIdx"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchType" class="hidden">검색키 선택 항목</label>
			<form:select path="searchType">
				<form:option value="log_nm">항목</form:option>
				<form:option value="adm_id">변경자ID</form:option>
				<form:option value="adm_nm">변경자명</form:option>
				<form:option value="dept_nm">부서명</form:option>
				<form:option value="adm_ip">접근IP</form:option>
			</form:select>
			<label for="searchTxt" class="hidden">검색값 입력</label>
			<form:input path="searchTxt"/>
			<form:input path="searchSday" class="input100" placeholder="시작일" data-date="y" data-ismindatefor="searchEday"/> ~
			<form:input path="searchEday" class="input100" placeholder="종료일" data-date="y" data-ismaxdatefor="searchSday"/>
			<input type="submit" onclick="$('#page').val(1); document.getElementById('list').action = '${listUrl}';" class="btn_white" value="검색" />
			<c:if test="${not empty result}">
				<input type="submit" onclick="document.getElementById('list').action = '${downUrl}'; return confirm('데이터가 많아 처리에 시간이 걸립니다.\n조건을 상세하게 설정하시거나 꼭 필요한 경우에만 다운로드해주세요.\n다운로드하시겠습니까?');" class="btn_white" value="다운로드" />
			</c:if>
			<a href="/sys/logging/config/list.do?mId=${menuVO.mId}" class="btn_white">설정</a>
		</div>
		<div class="fR">전체 <span class="red"><strong>${resultCnt}</strong></span>건</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption></caption>
		<thead>
			<tr>
				<th scope="col" class="wp7">번호</th>
				<th scope="col">항목</th>
				<th scope="col" class="wp8">기존<br/>보관 기간</th>
				<th scope="col" class="wp8">변경<br/>보관 기간</th>
				<th scope="col" class="wp8">변경자ID</th>
				<th scope="col" class="wp8">변경자명</th>
				<th scope="col" class="wp8">부서명</th>
				<th scope="col" class="wp6">접근IP</th>
				<th scope="col" class="wp13">변경일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td class="taL">${element.logName}</td>
					<td>${element.oldLogPeriod}년</td>
					<td>${element.newLogPeriod}년</td>
					<td>${element.adminId}</td>
					<td>${element.adminName}</td>
					<td>${element.deptName}</td>
					<td>${element.hostIp}</td>
					<td><fmt:formatDate value="${element.updateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">변경 이력이 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>

<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		dp.bind($("[data-date=y]"));
	});
</script>
