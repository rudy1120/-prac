<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 사전 정보 공표
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.31		권태성			최초 생성 및 코드 수정
	 *
	 *
	 * @author 권태성
	 * @since 2017.05.31
	 */
%>

<h2>${menuVO.menuName} 관리</h2>

<form:form commandName="searchVO" name="list" id="list" methodParam="post" action="${pageContext.request.contextPath}/sys/advanceInfoMng/list.do?mId=${menuVO.mId}">
	<input id="page" type="hidden" name="page" value="${paginationInfo.currentPageNo}" />

	<div class="search_box">
		<div class="fL">
			<select name="searchType" id="searchType">
				<option value="">-- 선택하세요 --</option>
				<option value="category" ${searchVO.searchType eq 'category' ? 'selected="selected"' : '' }>정보분류별</option>
				<option value="dept" ${searchVO.searchType eq 'dept' ? 'selected="selected"' : '' }>공표부서</option>
			</select>
			<label for="searchCat1" class="hidden">서브카테고리</label>
			<select id="searchCat1" name="method" style="display:none;">
				<option value="">-- 서브카테고리 선택 --</option>
				<c:forEach var="bigo" items="${bigoList }" varStatus="sts">
					<option value="${bigo.code }" ${searchVO.method eq bigo.code ? 'selected="selected"' : ''}>${bigo.codeName }</option>
				</c:forEach>
			</select>
			<label for="searchCat2" class="hidden">서브카테고리</label>
			<select id="searchCat2" name="deptCode" style="display: none;">
				<option value="" selected="selected">-- 서브카테고리 선택 --</option>
				<c:forEach var="dept" items="${deptList}" varStatus="sts">
					<option value="${dept.deptId }" ${fn:contains(searchVO.deptCode, dept.deptId) ? 'selected="selected"' : '' }>${fn:replace(dept.deptName, ' ', '&nbsp;')}</option>
				</c:forEach>
			</select>
			<input type="text" name="searchTxt" id="searchTxt" value="${searchVO.searchTxt }">
			<input type="submit" onclick="boardSubmit(); return false;" class="btn_white" value="검색">
			<input type="button" onclick="document.location.href='${pageContext.request.contextPath}/sys/advanceInfoMng/excel.do'; return false;" class="btn_white" value="엑셀 다운로드">
		</div>
		<div class="fR">
			총 <b>${totalCnt }</b>개의 데이터가 있습니다.
		</div>
		<div class="cleB"></div>
	</div>
	<%--
	<input type="button" onclick="window.open('/sys/dataMng/${tableDef.urlName}/excelUp.do?mId=${menuVO.mId}','excelUp', 'width=544, height=437, scrollbars=yes'); return false;" class="btn_cyan" value="엑셀업로드" />
	--%>
	<table class="tableSt_list row_over">
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w10">번호</th>
				<th scope="col" class="w10">분류<br/>(BRM1)</th>
				<th scope="col" class="">공표 목록</th>
				<th scope="col" class="w10">공표 부서</th>
				<th scope="col" class="w10">공표 주기</th>
				<th scope="col" class="w10">공표 시기</th>
				<th scope="col" class="w10">공표 방법</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty result}">
				<c:forEach var="def" items="${result }" varStatus="status">
					<tr>
						<td>${listOrder - status.index}</td>
						<td>${def.brm1Name }</td>
						<td><a href="${pageContext.request.contextPath}/sys/advanceInfoMng/view.do?mId=${menuVO.mId}&amp;idx=${def.idx}">${def.title }</a></td>
						<td>${def.dept }</td>
						<td>${def.period }</td>
						<td>${def.moment }</td>
						<td>${def.type eq '0' ? '파일' : '링크' }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage" />
		</div>
	</c:if>

	<div class="btn_boxR">
		<a href="${pageContext.request.contextPath}/sys/advanceInfoMng/write.do?mId=${menuVO.mId}" class="btn_cyan">등록</a>
	</div>

</form:form>
<script type="text/javascript">
yh.formId = "list";
yh.isNew = "${isNew}";
var advance = {edit : false, searchType : "${searchVO.searchType}", searchMethod : "${searchVO.method}", searchDept : "${searchVO.deptCode}"};
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/commonProcess.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/advanceInfoMng/advanceInfoMng.js<%--=System.currentTimeMillis() --%>"></script>