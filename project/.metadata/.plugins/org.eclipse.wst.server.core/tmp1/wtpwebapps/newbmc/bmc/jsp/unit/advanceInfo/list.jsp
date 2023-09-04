<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 사전 정보 공표
	 * --------------------------------------------------------------
	 * 베이스 프로젝트의 사전 정보 공표 목록 화면은 의정부 시청 코딩 베이스로 작업되어있습니다.
	 * 디자인 작업시 참고해주세요. 대부분의 요소는 스크립트에서 처리하니 스타일 외에는 개발팀에 문의해주세요.
	 * --------------------------------------------------------------
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.08.10		권태성			최초 생성 및 코드 수정

	 * @author 권태성
	 * @since 2017.08.10
	 */
%>
<h4>사전정보공표 검색하기</h4>
<form:form commandName="searchVO" id="list" name="list" action="/${siteCode}/advanceInfo/list.do?mId=${menuVO.mId}" method="post">
	<form:hidden path="page" />
	<form:hidden path="method" />
	<form:hidden path="deptCode" />
	<form:hidden path="idx" />

	<div class="search_opendata">
		<label class="blind" for="searchType">검색항목선택</label>
		<form:select path="searchType">
			<form:option value="title">공개내용</form:option>
			<form:option value="dept">공표부서</form:option>
		</form:select>
		<label class="label_style blind" for="searchTxt">검색어 입력 : </label>
		<form:input path="searchTxt" />
		<input type="submit" value="검색" class="btn_search" title="검색">
	</div>

	<div class="info_tab mT20">
		<ul id="search_tab_type" class="clFix">
			<li><a href="#" class="on" onclick="advance.setTab('brm'); return false;">정보 분류별</a></li>
			<li><a href="#" class="off" onclick="advance.setTab('dept'); return false;">공표부서</a></li>
		</ul>
	</div>

	<div id="brmTab" class="menu_open">
		<ul class="clFix">
			<li>
				<a href="#" onclick="advance.goSearch(); return false;" class="on"><span class="ico"></span>전체</a>
			</li>
		</ul>
	</div>

	<div id="deptTab" class="menu_open02" style="display:none;">
		<ul class="clFix">
		</ul>
	</div>
	<p class="exp">※ 해당 항목을 클릭하시면 아래에 검색결과가 나타납니다.</p>
	
	<h4>검색결과</h4>
	<div class="tbl_wrap">
		<p class="tbl_guide">모바일환경에서는 좌우로 이동하여 내용(표)을 보실 수 있습니다.</p>
		<table class="bod_maintain">
			<caption>사전정보공개 게시판 번호, 분류, 공표대상, 공표부서, 주기, 시기, 제공되는 방법, 파일보기 및 웹바로가기 제공, 조회 순으로 제공한 표</caption>
			<thead>
				<tr>
					<th scope="col" class="w7">번호</th>
					<th scope="col" class="w13">분류</th>
					<th scope="col">공표대상</th>
					<th scope="col" class="w15">공표부서</th>
					<th scope="col" class="w10">주기</th>
					<th scope="col" class="w10">시기</th>
					<th scope="col" class="w6">방법</th>
					<%-- <th scope="col" class="w10">바로가기</th> --%>
					<th scope="col" class="w6">조회</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty result}">
					<c:forEach var="def" items="${result }" varStatus="status">
						<tr>
							<td>${listOrder - status.index }</td>
							<td>${def.brm1Name }</td>
							<td><a href="#" data-action="${pageContext.request.contextPath}/${siteCode}/advanceInfo/view.do" data-keyset="{'idx' : ${def.idx}}" onclick="req.post(this); return false;">${def.title }</a></td>
							<td>${def.dept }</td>
							<td>${def.period }</td>
							<td>${def.moment }</td>
							<td>${def.type eq '0' ? '<button class="file" title="파일"></button>' : '<button class="link" title="링크"></button>' }</td>
							<%-- <td><a href="#" onclick="viewCount('W','','621','1357'); return false;" title="새창으로열림" class="btn_sblue">바로가기</a><br></td> --%>
							<td>${def.viewCnt }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty result}">
					<tr>
						<td colspan="8">조회된 데이터가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	<c:if test="${not empty result}">
		<div class="bod_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
		</div>
	</c:if>
</form:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/require.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/${siteCode}/js/unit/advanceInfo.js"></script>
<script type="text/javascript">
advance.mode = "list";
advance.tab = "${not empty searchVO.deptCode ? 'dept' : 'brm'}";
advance.searchType = "${searchVO.searchType}";
advance.searchMethod = "${not empty searchVO.method ? searchVO.method : ''}";
advance.searchDept = "${not empty searchVO.deptCode ? searchVO.deptCode : '53802040000'}";
</script>
