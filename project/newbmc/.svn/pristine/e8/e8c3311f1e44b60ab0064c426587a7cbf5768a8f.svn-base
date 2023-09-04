<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 웹진 구독신청 
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2022.12.23		이정화		    최초 생성 및 코드 수정
 *
 *
 * @author 이정화
 * @since 2022.12.23
 */
%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<script type="text/javascript" charaset="UTF-8">
	
</script>
<h2>인권침해신고센터 신고 목록</h2>

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="page"/>
	
	<div class="search_box">
		<div class="fL">
			<form:select path="searchType" id="searchType">
				<form:option value="name">성함</form:option>
				<form:option value="tel">연락처</form:option>
				<form:option value="email">이메일</form:option>
				<form:option value="title">제목</form:option>
			</form:select>
			<form:input type="text" path="searchTxt" id="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>
 
<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w2">번호</th>
				<th scope="col" class="w10">제목</th>
				<th scope="col" class="w5">성함</th>
				<th scope="col" class="w5">연락처</th>
				<th scope="col" class="w5">이메일</th>
				<th scope="col" class="w5">첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<%-- ============================ 번호 ============================ --%>
					<td>${listOrder - status.index}</td>
					<%-- ============================ 성함 ============================ --%>
					<td>
						<a href="/sys/report/view.do?mId=${menuVO.mId}&idx=${element.idx}">${element.title}</a>
					</td>
					<%-- ============================ 연락처 ============================ --%>
					<td>
						${element.uname}
					</td>
					<%-- ============================ 등록일시 ============================ --%>
					<td>
						${element.tel}
					</td>
					<td>
						${element.email}
					</td>
					<td class="list_file">
						<c:set var="fileExts" value="${element.attachId}" scope="request"/>
						<c:if test="${!empty fileExts}">
							<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="showDownload" value="Y"/>
								<c:param name="updateFlag" value="N"/>
								<c:param name="downloadYn" value="Y"/>
								<c:param name="param_atchFileId" value="${fileExts}"/>
							</c:import>
						</c:if>
						<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
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

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
