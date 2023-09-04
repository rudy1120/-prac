<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 사전 정보 공표 상세 페이지
	 * --------------------------------------------------------------
	 * 베이스 프로젝트의 사전 정보 공표 목록 화면은 의정부 시청 코딩 베이스로 작업되어있습니다.
	 * 디자인 작업시 참고해주세요. 대부분의 요소는 스크립트에서 처리하니 스타일 외에는 개발팀에 문의해주세요.
	 * --------------------------------------------------------------
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.08.30		권태성			최초 생성 및 코드 수정

	 * @author 권태성
	 * @since 2017.08.30
	 */
%>

<form:form commandName="searchVO" name="view" id="view" method="post" action="/portal/advanceInfoBigo/list.do?mId=0402010000">
	<form:hidden path="page" />
	<form:hidden path="method" />
	<form:hidden path="deptCode" />
	<form:hidden path="idx" />

	<div class="table_responsive">
		<p class="p_notice">모바일 사용시 표를 좌우로 이동하여 내용을 확인 할 수 있습니다.</p>
		<table class="table_st Thead">
			<caption>
				사전정보공표 공표 목록, BRM 단계, 관련 기능, 항목, 공표 부서, 공표 주기, 담당자, 연락처, 공표방법 항목별 상세히 안내하는 표입니다.
			</caption>
			<tbody>
				<tr>
					<th scope="row" class="col20">공표 목록</th>
					<td>${result.title }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">BRM 단계</th>
					<td>
						<c:if test="${not empty result.brm2Name}"><c:set var="brm2Name">&#62; ${result.brm2Name}</c:set></c:if>
						<c:if test="${not empty result.brm3Name}"><c:set var="brm3Name">&#62; ${result.brm3Name}</c:set></c:if>
						${result.brm1Name } ${brm2Name } ${brm3Name }
					</td>
				</tr>
				<tr>
					<th scope="row" class="col20">관련 기능</th>
					<td>${result.methodName }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">항목</th>
					<td>${result.item }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">공표 부서</th>
					<td>${result.dept }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">공표 주기</th>
					<td>${result.period }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">공표 시기</th>
					<td>${result.moment }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">담당자</th>
					<td>${result.respon }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">연락처</th>
					<td>${result.tel }</td>
				</tr>
				<tr>
					<th scope="row" class="col20">공표방법</th>
					<td>${result.type eq '0' ? '파일' : '링크' }</td>
				</tr>
			</tbody>
		</table>
	</div>

	<h4 class="mT_im0">관련 정보보기</h4>
	<table class="bod_list mT10 advance_info">
		<caption>번호, 공개정보, ${result.type eq '0' ? '파일' : 'URL' }, 등록부서, 등록일, 조회수 순으로 제공한 표</caption>
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w20">공개정보</th>
				<th scope="col" class="w20">${result.type eq '0' ? '파일' : 'URL' }</th>
				<th scope="col" class="w15">등록부서</th>
				<th scope="col" class="w10">등록일</th>
				<th scope="col" class="w8">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty dataList }">
				<c:forEach var="data" items="${dataList }" varStatus="sts">
					<tr>
						<td>${sts.count }</td>
						<td>${result.type eq '0' ? '파일' : data.name }</td>
						<td>
							<c:if test="${result.type eq '0' }">
								<c:import url="${pageContext.request.contextPath}/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${data.attachId }"/>
									<c:param name="convertYn" value="N"/>
								</c:import>
							</c:if>
							<c:if test="${result.type eq '1' }">
								<a href="/${siteCode}/advanceInfo/url.do?url=${data.idx }" class="btn_blue" title="새 창으로 ${data.name} 바로가기" target="_blank">바로가기</a>
							</c:if>
						</td>
						<td>${data.dept }</td>
						<td>
							<fmt:formatDate value="${data.createDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<c:if test="${result.type eq '0' }">
								<c:import url="${pageContext.request.contextPath}/cmm/fms/fileInfo.do" charEncoding="utf-8">
									<c:param name="attchId" value="${data.attachId }" />
									<c:param name="mode" value="fileDownCnt" />
									<c:param name="fileSn" value="0" />
								</c:import>
							</c:if>
							<c:if test="${result.type eq '1' }">
								${data.hit}
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty dataList }">
				<tr>
					<td colspan="6">등록된 데이터가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>

	<div class="btnboxR">
		<a href="#" data-action="${pageContext.request.contextPath}/${siteCode}/advanceInfo/list.do" data-formname="view" data-keyset="{'page' : ${searchVO.page}}" onclick="req.post(this); return false;" class="btn_white" title="목록 페이지로 이동">목록</a>
	</div>
</form:form>


<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/require.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/${siteCode}/js/unit/advanceInfo.js"></script>
<script type="text/javascript">
advance.mode = "list";
advance.tab = "${not empty searchVO.deptCode ? 'dept' : 'brm'}";
advance.searchType = "${searchVO.searchType}";
advance.searchMethod = "${not empty searchVO.method ? searchVO.method : '1'}";
advance.searchDept = "${not empty searchVO.deptCode ? searchVO.deptCode : '53802040000'}";
</script>
