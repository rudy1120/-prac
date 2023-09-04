<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리 목록(사용자)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.09		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.05.09
	 */
%>
<script type="text/javascript" src="/common/js/map.js"></script>
<script type="text/javascript">
//<![CDATA[

	function goPage(curPage) {
		var frm = document.getElementById('listForm');
		frm.page.value = curPage;
		frm.action = "/${siteCode}/${tableDef.urlName}/list.do?mId=${menuVO.mId}";
		frm.submit();
	}

	function fn_go_view(idx) {
		var frm = document.getElementById('listForm');
		frm.action = "/${siteCode}/${tableDef.urlName}/view.do?mId=${menuVO.mId}&idx=" + idx;
		frm.submit();
	}

//]]>
</script>

<c:choose>
	<c:when test="${tableDef.urlName eq 'dispositionList' }">
		<c:set var="category1All" value="부서" />
		<c:set var="category2All" value="전체" />
	</c:when>
	<c:when test="${tableDef.urlName eq 'landtrade1' }">
		<c:set var="category1All" value="연" />
		<c:set var="category2All" value="월" />
	</c:when>
	<c:otherwise>
		<c:set var="category1All" value="전체" />
		<c:set var="category2All" value="전체" />
	</c:otherwise>
</c:choose>

<h4 class="hidden">${activeMenu.menuName}</h4>

<%-- 안내 문구 --%>
<c:forEach var="column" items="${tableDef.columnDefList}">
	<c:if test="${fn:toLowerCase(tableDef.linkColName) == fn:toLowerCase(column.name)}">
		<c:set var="linkColDesc" value="${column.description}" />
	</c:if>
</c:forEach>

<%-- Daynimc Menu Guide --%>
<c:if test="${not empty tableDef.headerContent}">${tableDef.headerContent}</c:if>

<form name="listForm" id="listForm" action="/${siteCodeFull}/${tableDef.urlName}/list.do?mId=${menuVO.mId}" method="post">

	<input type="hidden" name="page" value="${paginationInfo.currentPageNo}" />

	<%-- ============================================ 검색 영역 ============================================ --%>

	<div class="search_box">
		<p class="page_num">
			현재 페이지 <span class="bold red">${page}</span> / 전체 페이지 ${paginationInfo.lastPageNo}
		</p>
		<fieldset class="bod_search">
			<legend class="hidden">${tableDef.tableDesc} 현황 검색</legend>

			<%-- 카테고리 검색 --%>
			<c:if test="${not empty tableDef.category1List}">
				<select name="CATEGORY" title="카테고리 선택">
					<option value="">${category1All }</option>
					<c:forEach var="category" items="${tableDef.category1List}">
						<c:if test="${category.useYn eq 'Y' }">
							<option value="${category.categoryValue}" <c:if test="${category.categoryValue == searchVO['CATEGORY']}">selected</c:if>>${category.categoryKey}</option>
						</c:if>
					</c:forEach>
				</select>
			</c:if>
			<%-- 2차 카테고리 검색 --%>
			<c:if test="${not empty tableDef.category2List}">
				<select name="CATEGORY2" title="세부 카테고리 선택">
					<option value="">${category2All }</option>
					<c:forEach var="category" items="${tableDef.category2List}">
						<c:if test="${category.useYn eq 'Y' }">
							<option value="${category.categoryValue}" <c:if test="${category.categoryValue == searchVO['CATEGORY2']}">selected</c:if>>${category.categoryKey}</option>
						</c:if>
					</c:forEach>
				</select>
			</c:if>

			<input type="submit" value="검색" name="searchBt" title="검색" class="" onclick="return goPage(1);">

			<%-- Daynimc Data Download Link --%>
			<c:if test="${tableDef.fileDownYn == 'Y'}">
				<a href="/${siteCodeFull}/${tableDef.urlName}/data/download.do?category=${searchVO['CATEGORY']}&amp;category2=${searchVO['CATEGORY2']}" class="btn_blue_down" target="_blank" title="${activeMenu.menuName} 전체 목록 엑셀 다운로드">엑셀</a>
			</c:if>
		</fieldset>
	</div>

	<%-- ============================================ 검색 결과 영역 ============================================ --%>

	<div class="table_responsive">
		<p class="p_notice">모바일 사용시 표를 좌우로 이동하여 내용을 확인 할 수 있습니다.</p>
		<table class="table_st">
			<caption class="hidden">
				${activeMenu.menuName} 목록을 번호<c:forEach var="element" items="${tableDef.columnDefList}">, ${element.description}</c:forEach> 항목별 상세히 안내하는 표입니다.
				<c:choose>
					<c:when test="${tableDef.linkType == 1}"><strong>${linkColDesc}을(를) 클릭시 상세 보기 화면으로 이동</strong>합니다.</c:when>
					<c:when test="${tableDef.linkType == 2}"><strong>${linkColDesc}을(를) 클릭시 지도를 새창으로 표시</strong>합니다.</c:when>
					<c:when test="${tableDef.linkType == 3}"><strong>${linkColDesc}을(를) 클릭시 지도를 새창으로 표시</strong>합니다.</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</caption>
			<thead>
				<tr>
					<c:set var="totalCol" value="0" />
					<c:if test="${tableDef.numYn eq 'Y'}">
						<c:set var="totalCol" value="1" />
						<th scope="col" class="col6">번호</th>
					</c:if>
					<c:if test="${not empty tableDef.category1List}">
						<c:set var="totalCol" value="${totalCol + 1}" />
						<th scope="col" <c:if test="${not empty tableDef.categoryWidth}">class="col${tableDef.categoryWidth}"</c:if>>${fn:split(tableDef.category,',')[0] }</th>
					</c:if>
					<c:if test="${not empty tableDef.category2List}">
						<c:set var="totalCol" value="${totalCol + 1}" />
						<th scope="col" <c:if test="${not empty tableDef.categoryWidth}">class="wp${tableDef.categoryWidth}"</c:if>>${fn:split(tableDef.category,',')[1] }</th>
					</c:if>
					<c:forEach var="element" items="${tableDef.columnDefList}">
						<c:if test="${element.isUse == 'Y' && element.display == 'Y' && element.type != 'clob'}">
							<c:set var="totalCol" value="${totalCol + 1}" />
							<th scope="col" <c:if test="${not empty element.width}">class="col${element.width}"</c:if>>${element.description}</th>
						</c:if>
					</c:forEach>
					<th scope="col" class="col10">첨부파일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="element" items="${result}" varStatus="status">
					<tr>
						<c:if test="${tableDef.numYn eq 'Y'}">
						<td class="taC">${listOrder - status.index}</td>
						</c:if>
						<c:if test="${not empty tableDef.category1List}">
							<td class="taC">
								<c:set var="chk" value="0" />
								<c:forEach var="category" items="${tableDef.category1List}">
									<c:if test="${category.categoryValue eq element['COL_CATEGORY']}">
									${category.categoryKey}
									<c:set var="chk" value="1" />
									</c:if>
								</c:forEach>
								<c:if test="${chk eq '0' }">
									${element['COL_CATEGORY'] }
								</c:if>
							</td>
						</c:if>
						<c:if test="${not empty tableDef.category2List}">
							<td class="taC">
								<c:set var="chk" value="0" />
								<c:forEach var="category" items="${tableDef.category2List}">
									<c:if test="${category.categoryValue eq element['COL_CATEGORY2']}">
									${category.categoryKey}
									<c:set var="chk" value="1" />
									</c:if>
								</c:forEach>
								<c:if test="${chk eq '0' }">
									${element['COL_CATEGORY2'] }
								</c:if>
							</td>
						</c:if>
						<c:forEach var="column" items="${tableDef.columnDefList}" varStatus="status2">
							<c:if test="${column.isUse == 'Y' && column.display == 'Y' && column.type != 'clob'}">
								<c:set var="key" value="COL_${column.name}" />
								<td <c:if test="${column.align == 'L'}">class="taL"</c:if><c:if test="${column.align != 'L'}">class="taC"</c:if>>
									<c:if test="${tableDef.linkType == 0}">
										<c:if test="${not empty element[key]}">
											${element[key]}
										</c:if>
										<c:if test="${empty element[key]}">
											-
										</c:if>
									</c:if>
									<c:if test="${tableDef.linkType != 0}">
										<c:if test="${fn:toLowerCase(tableDef.linkColName) != fn:toLowerCase(column.name)}">${element[key]}</c:if>
										<c:if test="${fn:toLowerCase(tableDef.linkColName) == fn:toLowerCase(column.name)}">
											<c:choose>
												<c:when test="${tableDef.linkType == 1}">
													<a href="${element['COL_HP_URL']}" target="_blank" title="${element[key]} 홈페이지로 이동">${element[key]}</a>
												</c:when>
												<c:when test="${tableDef.linkType == 2}">
													<a href="#" onclick="fn_go_view(${element['IDX']}); return false;" title="${element[key]} 상세 정보 확인하기">
														${element[key]}
													</a>
												</c:when>
												<c:when test="${tableDef.linkType == 3 && not empty element['COL_LAT'] && not empty element['COL_LNG']}">
													<a href="#" onclick="popup_map('${element[key]}', '${element['COL_LAT']}', '${element['COL_LNG']}'); return false;" title="${element[key]} 위치 지도로 보기">
														${element[key]}
													</a>
												</c:when>
												<c:when test="${tableDef.linkType == 4}">
													<a href="${element[key]}" target="_blank" title="${element[key]}(으)로 새창 이동">${element[key]}</a>
												</c:when>
												<c:when test="${tableDef.linkType == 3}">${element[key]}</c:when>
												<c:otherwise>?</c:otherwise>
											</c:choose>
										</c:if>
									</c:if>
								</td>
							</c:if>
						</c:forEach>
						<td class="taC">
							<c:import url="/cmm/fms/fileInfo.do" charEncoding="utf-8">
								<c:param name="attchId" value="${element['ATTACH_ID']}" />
								<c:param name="mode" value="fileView" />
								<c:param name="fileSn" value="0" />
							</c:import>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty result}">
					<tr class="taC">
						<td colspan="${totalCol + 1}" class="taC">검색된 결과가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage" />
		</div>
	</c:if>
</form>
