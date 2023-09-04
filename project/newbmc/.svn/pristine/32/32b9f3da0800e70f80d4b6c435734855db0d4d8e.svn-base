<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 관리자 정보 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.09.22		J.Ryeon Lee		최초 생성 및 코드 수정
	 * 2017.11.07		J.Ryeon Lee		오류 수정, 베이스 구조에 맞춰 리팩토링
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.09.22
	 */
%>
<h2>외부 관리자 현황</h2>
<form:form commandName="searchVO" name="list" id="list" action="/sys/externalAdminMng/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>
	<form:hidden path="id"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchAdminTypeCode" class="hidden">관리자 타입 선택</label>
			<form:select path="searchAdminTypeCode">
				<form:option value="">전체</form:option>
				<c:forEach var="element" items="${adminTypeList}">
					<c:if test="${element.code != 0}">
						<form:option value="${element.code}">${element.name}</form:option>
					</c:if>
				</c:forEach>
			</form:select>
			<label for="searchType" class="hidden">검색키 선택 항목</label>
			<form:select path="searchType">
				<form:option value="admin_id">관리자 ID</form:option>
				<form:option value="admin_name">관리자명</form:option>
			</form:select>
			<label for="searchTxt" class="hidden">검색값 입력</label>
			<form:input path="searchTxt"/>
			<input type="submit" onclick="$('#page').val(1);" class="btn_white" value="검색"/>
		</div>
		<div class="fR">총 <span class="blue">${resultCnt}</span>개의 관리자 정보가 있습니다.</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>외부 관리자 목록을 번호, 관리자 ID, 관리자명, 관리 사이트 코드 항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w15">관리자 ID</th>
				<th scope="col" class="w15">관리자명</th>
				<th scope="col">외부관리자명</th>
				<th scope="col" class="w10">상태</th>
				<th scope="col" class="w13">최종 로그인 일자</th>
				<th scope="col" class="w15">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${e.adminId}</td>
					<td>${e.name}</td>
					<td>
						<c:forEach var="adminType" items="${adminTypeList}">
							<c:if test="${adminType.code == e.adminTypeCode}">${adminType.name} 외부 관리자</c:if>
						</c:forEach>
					</td>
					<td>${5 <= e.loginFailedCnt ? '<span class="red">로그인 제한</span>' : '로그인 가능'}</td>
					<td><fmt:formatDate value="${e.lastestLoginDt}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a href="#" data-action="/sys/externalAdminMng/modify.do" data-keyset="{'id': '${e.id}', 'adminTypeCode': '${e.adminTypeCode}'}" onclick="req.post(this); return false;" class="btn_swhite">보기</a>
						<a href="#" data-action="/sys/externalAdminMng/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'id': '${e.id}', 'adminTypeCode': '${e.adminTypeCode}'}"
							data-confirmmsg="선택하신 외부 관리자 정보를 삭제하시겠습니까?" data-successmsg="관리자 정보를 삭제했습니다."
							onclick="req.ajax(this); return false;" class="btn_swhite">
							삭제
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 관리자 목록이 없습니다.</div>
	</c:if>

	<div class="btn_boxR">
		<a href="#" data-action="/sys/externalAdminMng/excel/upload.do?mId=${menuVO.mId}" onclick="req.post(this); return false;" data-target="blank" class="btn_dblue">엑셀 업로드</a>
		<a href="#" data-action="/sys/externalAdminMng/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>
</form:form>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
