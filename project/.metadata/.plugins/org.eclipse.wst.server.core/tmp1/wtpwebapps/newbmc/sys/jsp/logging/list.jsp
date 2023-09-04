<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * log 열람
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.04.12		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.04.12
	 */
%>

<c:set var="listUrl" value="/sys/logging/list.do?mId=${menuVO.mId}" />
<c:set var="downUrl" value="/sys/logging/down.do?mId=${menuVO.mId}" />

<h2>${menuVO.menuName} 로그 열람</h2>
<form:form commandName="searchVO" name="list" id="list" action="/sys/logging/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>
	<form:hidden path="searchProcType"/>
	<c:set var="loginOnly" value="${searchVO.searchProcType == '0'}"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchType" class="hidden">검색키 선택 항목</label>
			<form:select path="searchType">
				<form:option value="menu_id">접근 메뉴ID</form:option>
				<form:option value="url">접근URL</form:option>
				<form:option value="ip">접근 IP</form:option>
				<form:option value="id">관리자ID</form:option>
			</form:select>
			<label for="searchTxt" class="hidden">검색값 입력</label>
			<form:input path="searchTxt"/>
			<form:input path="searchSday" class="input100" placeholder="시작일" data-date="y" data-ismindatefor="searchEday"/> ~
			<form:input path="searchEday" class="input100" placeholder="종료일" data-date="y" data-ismindatefor="searchSday"/>
			<input type="submit" onclick="$('#page').val(1); document.getElementById('list').action = '${listUrl}';" class="btn_white" value="검색" />
			<input type="submit" onclick="document.getElementById('list').action = '${downUrl}'; return confirm('데이터가 많아 처리에 시간이 걸립니다.\n조건을 상세하게 설정하시거나 꼭 필요한 경우에만 다운로드해주세요.\n다운로드하시겠습니까?');" class="btn_white" value="다운로드" />
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption></caption>
		<thead>
			<tr>
				<th scope="col" class="wp7">번호</th>
				<th scope="col">접근 URL</th>
				<c:if test="${!loginOnly}"><th scope="col" class="wp10">메뉴ID</th></c:if>
				<th scope="col" class="wp15">
					<c:if test="${loginOnly}">처리내용</c:if>
					<c:if test="${!loginOnly}">메뉴명<br/>(처리내용)</c:if>
				</th>
				<th scope="col" class="wp10">접근 IP</th>
				<th scope="col" class="wp8">관리자ID</th>
				<th scope="col" class="wp6">관리자명</th>
				<th scope="col" class="wp8">부서명</th>
				<th scope="col" class="wp10">접근일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td class="taL">${element.full_url}</td>
					<c:if test="${searchVO.searchProcType != '0'}"><td>${element.mid}</td></c:if>
					<td>
						<c:set var="lowercaseUrl" value="${fn:toLowerCase(element.full_url)}"/>
						<c:set var="procName" value="기타"/>
						<c:choose>
							<c:when test="${fn:contains(lowercaseUrl, 'write.do')}"><c:set var="procName" value="등록 화면"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'modify.do')}"><c:set var="procName" value="수정 화면"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'modifyview.do')}"><c:set var="procName" value="수정 화면"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'update.do')}"><c:set var="procName" value="수정 화면"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'writeproc.do')}"><c:set var="procName" value="등록"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'insertproc.do')}"><c:set var="procName" value="등록"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'modifyproc.do')}"><c:set var="procName" value="수정"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'updateproc.do')}"><c:set var="procName" value="수정"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'deleteproc.do')}"><c:set var="procName" value="삭제"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'list.do')}"><c:set var="procName" value="목록"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'view.do')}"><c:set var="procName" value="상세 열람"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, '/adminlogin/login.do')}"><c:set var="procName" value="로그인"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'login.do')}"><c:set var="procName" value="로그인 화면"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'superloginproc.do')}"><c:set var="procName" value="로그인"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'loginproc.do')}"><c:set var="procName" value="로그인"/></c:when>
							<c:when test="${fn:contains(lowercaseUrl, 'logout.do')}"><c:set var="procName" value="로그아웃"/></c:when>
						</c:choose>
						<c:if test="${loginOnly}">${procName}</c:if>
						<c:if test="${!loginOnly}">${element.menu_name}(${procName})</c:if>
					</td>
					<td>${element.host_ip}</td>
					<td>${element.admin_id}</td>
					<td>${element.admin_name}</td>
					<td>${element.dept_name}</td>
					<td><fmt:formatDate value="${element.access_date}" pattern="yyyy-MM-dd HH:mm"/></td>
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
<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		dp.bind($("[data-date=y]"));
	});
</script>
