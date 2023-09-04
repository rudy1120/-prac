<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 개인정보취급메뉴 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.30		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.05.30
	 */
%>
<script type="text/javascript">
//<![CDATA[

	function goPage(curPage) {
		var frm = document.getElementById("listForm");
		frm.page.value = curPage;
		frm.submit();
	}

//]]>
</script>

<c:set var="listUrl" value="/sys/totalAdminMng/menuSysMng/privacy/list.do?mId=${menuVO.mId}" />
<c:set var="downUrl" value="/sys/totalAdminMng/menuSysMng/privacy/down.do?mId=${menuVO.mId}" />

<h2>${menuVO.menuName} 로그 열람</h2>
<form:form commandName="searchVO" name="listForm" id="listForm" action="/sys/totalAdminMng/menuSysMng/privacy/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchType" class="hidden">검색키 선택 항목</label>
			<form:select path="searchType">
				<form:option value="id">메뉴ID</form:option>
				<form:option value="name">메뉴명</form:option>
			</form:select>
			<label for="searchTxt" class="hidden">검색값 입력</label>
			<form:input path="searchTxt"/>
			<input type="submit" onclick="$('#page').val(1); document.getElementById('listForm').action = '${listUrl}';" class="btn_white" value="검색" />
			<input type="submit" onclick="document.getElementById('listForm').action = '${downUrl}'; return confirm('데이터가 많아 처리에 시간이 걸립니다.\n조건을 상세하게 설정하시거나 꼭 필요한 경우에만 다운로드해주세요.\n다운로드하시겠습니까?');" class="btn_white" value="다운로드" />
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption></caption>
		<thead>
			<tr>
				<th scope="col" class="wp7">번호</th>
				<th scope="col" class="wp10">메뉴ID</th>
				<th scope="col">메뉴명</th>
				<th scope="col" class="wp10">사용여부</th>
				<th scope="col" class="wp8">등록일</th>
				<th scope="col" class="wp6">수정일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${element.mId}</td>
					<td class="taL"><a href="/sys/sysContents.do?mId=${element.mId}" target="_blank">${element.menuName}</a></td>
					<td>${element.isUse == 'Y' ? '사용' : '미사용'}</td>
					<td><fmt:formatDate value="${element.regDt}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${element.modDt}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">개인정보를 취급하는 관리자 메뉴가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>
