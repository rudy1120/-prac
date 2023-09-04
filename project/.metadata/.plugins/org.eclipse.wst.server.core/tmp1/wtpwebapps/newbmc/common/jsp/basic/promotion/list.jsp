<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 홍보 자료 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.07.20		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.07.20
	 */
%>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="idx"/>
	<form:hidden path="page"/>

	<%-- ============================ 검색 영역 ============================ --%>
	<div class="search_box">
		<div class="fL">
			<form:select path="searchSiteIdx">
				<form:option value="">전체</form:option>
				<c:forEach var="s" items="${siteList}">
					<form:option value="${s.idx}">${s.siteName}</form:option>
				</c:forEach>
			</form:select>
			<form:select path="searchType">
				<form:option value="nm">제목</form:option>
			</form:select>
			<form:input type="text" path="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>

	<%-- ============================ 결과 영역 ============================ --%>
	<div class="tbl_wrap">
		<p class="tbl_guide">모바일환경에서는 좌우로 이동하여 내용(표)을 보실 수 있습니다.</p>
		<table class="tbl">
			<thead>
				<tr>
					<th scope="col" class="w7">번호</th>
					<th scope="col" class="w10">사이트</th>
					<th scope="col">제목</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="element" items="${result}" varStatus="status">
					<tr>
						<%-- ============================ 번호 ============================ --%>
						<td>${listOrder - status.index}</td>
						<%-- ============================ 사이트 ============================ --%>
						<td>
							<c:forEach var="s" items="${element.promotionSites}" varStatus="status">
								${s.siteName}<c:if test="${status.count != fn:length(element.promotionSites)}"><br/></c:if>
							</c:forEach>
						</td>
						<%-- ============================ 제목 ============================ --%>
						<td class="taL">
							<c:choose>
								<c:when test="${not empty element.prmtUrl}"><a href="${fn:escapeXml(element.prmtUrl)}" target="_blank">${element.prmtName}</a></c:when>
								<c:otherwise>${element.prmtName}</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty result}"><td colspan="3">등록된 자료가 없습니다.</td></c:if>
			</tbody>
		</table>
	</div>

	<c:if test="${not empty result}">
		<div class="bod_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>

<script type="text/javascript"> yh.siteCode = "${siteCodeFull}"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
