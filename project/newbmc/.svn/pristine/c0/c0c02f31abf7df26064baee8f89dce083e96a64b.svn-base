<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<table class="bod_list">
	<caption class="hidden">
		${activeMenu.menuName} 목록의 다운로드 리스트를 제공하는 게시판입니다.
	</caption>
	<thead>
		<tr>
			<c:set var="colLength" value="5"/>
			<th scope="col" class="list_num">번호</th>
			<c:if test="${useCategory && useCustom}">
				<th scope="col" class="w11">분류</th>
				<c:set var="colLength" value="${colLength + 1}"/>
			</c:if>
			<th scope="col" class="list_tit">제목</th>
			<th scope="col" class="list_write">작성자</th>
			<th scope="col" class="list_date">작성일</th>
		
			<th scope="col" class="list_file">파일</th>
		</tr>
	</thead>
	<tbody>
		 <%-- 공지 --%>
		<c:set var="elements" value="${result}" scope="request"/>
		<c:forEach var = "element" items = "${elements}" varStatus="status">
			<tr>
				<td>${listOrder - status.index}</td>
				<c:if test="${useCategory && useCustom}">
					<td>${element.bCategory}</td>
				</c:if>
				<td style="text-align:left;">${element.bTitle}</td>
				<td>
				<c:choose>
					<c:when test="${element.bPublic == 'N' && not empty element.bPrivatecode}">
						${fn:substring(element.bWrite, 0, 1)}**
					</c:when>
					<c:otherwise>${not empty element.rltdDeptNm ? element.rltdDeptNm : element.bWrite}</c:otherwise>
				</c:choose>
			</td>
			<%-- ====================== 등록일 ====================== --%>
			<td><c:if test="${empty element.bTermSdate}">
				<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
			</c:if>
			<c:if test="${not empty element.bTermSdate}">
				<c:if test="${fn:length(element.bTermSdate) >= 10}">
					${fn:substring(element.bTermSdate, 0 ,10)}
				</c:if>
			</c:if></td>
			
				
				<td>
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
				<!-- 이미지 말고 버튼으로 하고 누르면 다운로드 받을 수 있게 하깅 -->
			</td>
			</tr>
		</c:forEach>
		<c:if test="${empty noti && empty result}">
			<tr>
				<td colspan="${colLength}">등록된 게시물이 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>

<c:if test="${not empty noti || not empty result}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
	</div>
</c:if>
