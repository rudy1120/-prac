<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 회원 현황 목록
	 *
	 * 수정일		수정자			수정내용
	 * ------------	------------	-----------------------------
	 * 2015.10.16	J.Ryeon Lee		최초 생성 및 코드 수정
	 * 2016.08.17	J.Ryeon Lee		의정부시 회원 관리 기능 추가
	 * 2017.08.03	J.Ryeon Lee		기능 개선 및 불필요 항목 제거
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.10.16
	 */
%>
<h2>활동 회원 현황</h2>
<form:form commandName="searchVO" id="list" name="list" action="/sys/portal/user/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchType" class="hidden">검색키 선택 항목</label>
			<form:select path="searchType">
				<form:option value="uid">회원 ID</form:option>
				<form:option value="unm">이름</form:option>
			</form:select>
			<label for="searchTxt" class="hidden">검색값 입력</label>
			<form:input path="searchTxt"/>
			<input type="button" onclick="goPage(1); return false;" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>회원 목록을 번호, 회원 ID, 이름, 전화번호, 상태, 가입일, 신청일, 관리 항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w8">번호</th>
				<th scope="col" class="w13">회원 ID</th>
				<th scope="col">이름</th>
				<th scope="col" class="w5">성별</th>
				<th scope="col" class="w15">가입일</th>
				<th scope="col" class="w15">최종 로그인 일자</th>
				<th scope="col" class="w8">로그인 횟수</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${element.userId}</td>
					<td class="taL">${element.userName}</td>
					<td>${element.gender == 'M' ? '남' : '여'}</td>
					<td><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${element.lastestLoginDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${element.loginCount}</td>
					<td>
						<a href="#" data-action="/sys/portal/user/modify.do" data-keyset="{'userId': '${element.userId}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 게시물이 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>
</form:form>

<script type="text/javascript"> yh.siteCode = "sys";</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
