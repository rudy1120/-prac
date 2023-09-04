<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 분양 목록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.09.30		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.09.30
 */
%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<script type="text/javascript">

	
</script>
<h2>분양 목록</h2>

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<!-- 분양상태 -->
			<form:select path="searchGbn" id="searchGbn">
				<form:option value="">전체</form:option>
				<form:option value="1">분양중</form:option>
				<form:option value="4">분양완료</form:option>
				<form:option value="9">분양계획중</form:option>
			</form:select>
			<label for="gbn" >사업명 : </label>
			<form:input type="text" path="searchTxt" id="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>
 <span>&nbsp;&nbsp;&nbsp;&nbsp; * 등록 시 메뉴 관리에서 페이지를 생성해야합니다. (아이디 필요)</span>
 <br/><br/>
<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w7">아이디</th>
				<th scope="col" >사업명</th>
				<th scope="col" class="w10">분양상태</th>
				<th scope="col" class="w10">등록일시</th>
				<th scope="col" class="w15">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<%-- ============================ 번호 ============================ --%>
					<td>${listOrder - status.index}</td>
					<td>${element.idx}</td>
					
					<%-- ============================ 사업명 ============================ --%>
 					<td>
 						<a href="#" data-action="/sys/buy/view.do?idx=${element.idx}" onclick="req.post(this); return false;">${element.name}
					</td>
					<%-- ============================ 분양상태 ============================ --%>
					<td>
						<c:choose>
							<c:when test="${element.state eq 4}">
								분양완료
							</c:when>
							<c:when test="${element.state eq 1}">
								분양중
							</c:when>
							<c:when test="${element.state eq 9}">
								분양계획중
							</c:when>
						</c:choose>
					</td>
					<%-- ============================ 등록일시 ============================ --%>
					<td>
						${element.regDate}
					</td>
					
					<td>
						<a href="#" data-action="/sys/buy/update.do?mId=${menuVO.mId}&idx=${element.idx}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/buy/deleteProc.do?mId=${menuVO.mId}&idx=${element.idx}" onclick="if(confirm('삭제하시겠습니까?'))req.post(this); return false;" class="btn_sblack">삭제</a>
						<a href="#"  class="btn_swhite"  data-action="/sys/buy/saleWrite.do?idx=${element.idx}" onclick="req.post(this); return false;">매물 관리</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>
<div class="btn_boxR">
	<a href="#" data-action="/sys/buy/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
</div>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
