<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리 상세
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.10		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.05.10
	 */
%>

<script type="text/javascript">
//<![CDATA[

//]]>
</script>

<form name="form" id="form" method="post" action="/${siteCodeFull}/${tableDef.urlName}/list.do?mId=${menuVO.mId}">
	<input type="hidden" name="idx" value="${entity['IDX']}" />
	<input type="hidden" name="page" value="${searchVO['page']}" />
	<input type="hidden" name="searchType" value="${searchVO['searchType']}" />
	<input type="hidden" name="searchTxt" value="${searchVO['searchTxt']}" />

	<div class="table_responsive">
		<p class="p_notice">모바일 사용시 표를 좌우로 이동하여 내용을 확인 할 수 있습니다.</p>
		<table class="table_st Thead">
			<caption>
				${activeMenu.menuName} 목록을 번호<c:forEach var="element" items="${tableDef.columnDefList}">, ${element.description}</c:forEach> 항목별 상세히 안내하는 표입니다.
			</caption>
			<tbody>
				<c:if test="${not empty tableDef.category1List}">
				<tr>
					<th scope="row" class="col20">${fn:split(tableDef.category,',')[0] }</th>
					<td>
						<c:set var="chk" value="0" />
						<c:forEach var="category" items="${tableDef.category1List}">
							<c:if test="${category.categoryValue eq entity['COL_CATEGORY']}">
							${category.categoryKey}
							<c:set var="chk" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${chk eq '0' }">
							${entity['COL_CATEGORY'] }
						</c:if>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty tableDef.category2List}">
					<tr>
						<th scope="row"><span class="red">*</span><label for="CATEGORY2">${fn:split(tableDef.category,',')[1] }</label></th>
						<td>
							<c:set var="chk" value="0" />
							<c:forEach var="category" items="${tableDef.category2List}">
								<c:if test="${category.categoryValue eq entity['COL_CATEGORY2']}">
								${category.categoryKey}
								<c:set var="chk" value="1" />
								</c:if>
							</c:forEach>
							<c:if test="${chk eq '0' }">
								${entity['COL_CATEGORY2'] }
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:forEach var="column" items="${tableDef.columnDefList}">
					<c:if test="${column.isUse == 'Y'}">
						<c:set var="key" value="COL_${column.name}" />
						<tr>
							<th scope="row" class="col20">${column.description}</th>
							<td>${entity[key]}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${tableDef.useFile == 'Y' && not empty entity['ATTACH_ID']}">
					<tr>
						<th scope="row" class="col20">첨부 파일</th>
						<td>
							<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${entity['ATTACH_ID']}" />
							</c:import>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	<%-- 지도 표시 / 접근성 문제로 지원하지 않음
	<c:if test="${tableDef.useMap == 'Y' && not empty entity['COL_LAT'] && not empty entity['COL_LNG']}">
		<h4 class="mB10">위치 정보</h4>
		<p>지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업지도작업</p>
	</c:if>
	 --%>

	<div class="btnboxR">
		<a href="#" onclick="document.getElementById('form').submit(); return false;" class="btn_blue_normal" title="${activeMenu.menuName} 목록으로 돌아가기">목록</a>
	</div>

</form>
