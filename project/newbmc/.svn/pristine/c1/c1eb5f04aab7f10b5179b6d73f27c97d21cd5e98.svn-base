<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<div class="bod_guide mT20">
	<p class="bold">사이트별 전체 단위 기능 일람</p>
	<ul class="list_ul_h">
		<li>상태가 준비중인 메뉴는 현재 프로그램 개발이 시작되기 전입니다.</li>
		<li>URL 작업 완료된 기능을 검토하실 때는 <span class="red">담당자가 기능을 개발 완료했는지 구글 문서를 통해 확인 후 진행</span>해주세요.</li>
	</ul>
</div>

<table class="tbl">
	<caption></caption>
	<thead>
		<tr>
			<th scope="col">메뉴ID</th>
			<th scope="col">상태</th>
			<th scope="col">메뉴명</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="e" items="${result}">
			<tr>
				<td class="taC w10">${e.MID}</td>
				<td class="taC w10">
					${empty e.PROGRAM_URL ? '준비중' : 'URL 작업 완료'}
				</td>
				<td>
					<c:set var="siteCodeFull" value="${e.SITE_CODE}"/>
					<c:if test="${not empty e.SITE_GROUP}"><c:set var="siteCodeFull" value="${e.SITE_GROUP}/${e.SITE_CODE}"/></c:if>
					<c:if test="${empty e.PROGRAM_URL}">
						<a href="#" onclick="alert('준비중입니다.'); return false;"><span class="blue bold">[${e.SITE_NAME}]</span> ${e.MENU_NAME}</a>
					</c:if>
					<c:if test="${not empty e.PROGRAM_URL}">
						<a href="/${siteCodeFull}/contents.do?mId=${e.MID}" target="_blank" title="새창열림"><span class="blue bold">[${e.SITE_NAME}]</span> ${e.MENU_NAME}</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty result}">
			<tr>
				<td colspan="3">등록된 메뉴가 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>
