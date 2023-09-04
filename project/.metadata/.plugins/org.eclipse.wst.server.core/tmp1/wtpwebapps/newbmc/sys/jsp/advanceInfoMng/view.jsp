<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
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

<h2>공표 내용</h2>

<form name="list" id="list" method="post" action="${pageContext.request.contextPath}/sys/advanceInfoMng/list.do?mId=${menuVO.mId}">
	<input type="hidden" name="page" value="${searchVO.page}" />
	<input type="hidden" name="idx" value="${result.idx}" />
	<input type="hidden" name="target" value="${result.idx}" />
	<input type="hidden" name="searchType" value="${searchVO.searchType}" />
	<input type="hidden" name="searchTxt" value="${searchVO.searchTxt}" />

	<table class="tableSt_write">
		<caption>항목별 입력을 제공하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width: 15%" />
			<col span="1" />
			<col span="1" style="width: 15%" />
			<col span="1" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="title"><span class="red">*</span>공표 목록</label></th>
				<td colspan="3">${result.title }</td>
			</tr>
			<tr>
				<th scope="row"><label for="brm1"><span class="red">*</span>BRM 단계</label></th>
				<td colspan="3">
					<c:if test="${not empty result.brm2Name}">
						<c:set var="brm2Name">&#62; ${result.brm2Name}</c:set>
					</c:if>
					<c:if test="${not empty result.brm3Name}">
						<c:set var="brm3Name">&#62; ${result.brm3Name}</c:set>
					</c:if>
					 ${result.brm1Name } ${brm2Name } ${brm3Name }
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="method"><span class="red">*</span>관련 기능</label></th>
				<td colspan="3">${result.methodName }</td>
			</tr>
			<tr>
				<th scope="row"><label for="item"><span class="red">*</span>항목</label></th>
				<td colspan="3">${result.item }</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span> 공표 부서</th>
				<td colspan="3">${result.dept }</td>
			</tr>
			<tr>
				<th scope="row"><label for="period">공표 주기</label></th>
				<td>${result.period }</td>
				<th scope="row"><label for="moment">공표 시기</label></th>
				<td>${result.moment }</td>
			</tr>
			<tr>
				<th scope="row"><label for="respon">담당자</label></th>
				<td>${result.respon }</td>
				<th scope="row"><label for="tel">연락처</label></th>
				<td>${result.tel }</td>
			</tr>
			<tr>
				<th scope="row">링크</th>

				<td colspan="3">
					<a href="#" onclick="postPopUp(); return false;" class="btn_swhite"> 사용자 페이지</a>
				</td>
			</tr>
		</tbody>
	</table>

	<h2>공표 자료</h2>
	<div class="tab mT10" id="typeTab">
		<input type="hidden" id="type" name="type" value="0" />
		<ul>
			<li><a href="#" ${result.type eq '0' ? 'class="on"' : ''} data-value="0">파일</a></li>
			<li><a href="#" ${result.type eq '1' ? 'class="on"' : ''} data-value="1">링크</a></li>
		</ul>
		<div class="cleB"></div>
	</div>

	<div class="mL10" id="infoTxt"></div>
	<table class="tableSt_list row_over mT10" id="dataFileTable">
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w10">번호</th>
				<th scope="col" class="w10">공개정보</th>
				<th scope="col" class="w40">${result.type eq '0' ? '파일' : 'URL' }</th>
				<th scope="col" class="w10">등록부서</th>
				<th scope="col" class="w10">등록일</th>
				<th scope="col" class="w10">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty dataList }">
				<c:forEach var="data" items="${dataList }" varStatus="sts">
					<tr>
						<td>${sts.count }</td>
						<td>${result.type eq '0' ? '파일등록' : data.name }</td>
						<td><c:if test="${result.type eq '0' }">
								<c:import url="${pageContext.request.contextPath}/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${data.attachId }" />
									<c:param name="convertYn" value="N" />
								</c:import>
							</c:if>
							<c:if test="${result.type eq '1' }">
								${data.url }
							</c:if>
						</td>
						<td>${data.dept }</td>
						<td>
							<fmt:formatDate value="${data.createDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<c:import url="${pageContext.request.contextPath}/cmm/fms/fileInfo.do" charEncoding="utf-8">
								<c:param name="attchId" value="${data.attachId }" />
								<c:param name="mode" value="fileDownCnt" />
								<c:param name="fileSn" value="0" />
							</c:import>
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

	<div class="btn_boxR">
		<a data-action="${pageContext.request.contextPath}/sys/advanceInfoMng/update.do" data-keyset="{'idx' : ${result.idx}}" onclick="req.post(this);" href="#" id="backBtn" class="btn_cyan">수정</a>
		<a data-action="${pageContext.request.contextPath}/sys/advanceInfoMng/deleteProc.do" data-keyset="{'target' : ${result.idx}}" onclick="if (confirm('삭제하시겠습니까?')) { req.post(this); } return false;" href="#" id="delBtn" class="btn_cyan">삭제</a>
		<a data-action="${pageContext.request.contextPath}/sys/advanceInfoMng/list.do" onclick="req.post(this); return false;" href="#" id="backBtn" class="btn_dblue">목록</a>
	</div>

</form>

<script type="text/javascript">
	yh.formId = "list";
	yh.siteCode = "sys";

	function postPopUp() {
		frm = document.getElementById("list");
		window.open("", "list");
		frm.action = "/portal/advanceInfo/view.do?mId=0900000000";
		frm.target = "list";
		frm.method = "post";
		frm.submit();
		frm.target ="_self";
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/commonProcess.js"></script>