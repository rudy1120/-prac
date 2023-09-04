<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리 목록(사용자)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.09		J.Ryeon Lee		최초 생성 및 코드 작성
	 * 2017.04.05		권태성			링크 타입 옵션 변경에 따른 반영
	 * 2017.04.10		권태성			링크 타입별 caption 출력 추가
	 * 2019.06.05 		김선영			마크업 일부 수정
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

<h4 class="blind">${activeMenu.menuName}</h4>

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
	<c:if test="${tableDef.searchYn == 'Y'}">
		<div class="bod_head">
			<p class="page_num">
				현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
			</p>
			<fieldset class="bod_search clFix">
				<legend class="hidden">${tableDef.tableDesc} 현황 검색</legend>

				<%-- 카테고리 검색 --%>
				<c:forEach var="categoryDef" items="${tableDef.categoryDefList }" varStatus="sts">
					<c:if test="${categoryDef.showYn eq 'Y' and categoryDef.showSearchYn eq 'Y' }">
						<select name="CATEGORY${sts.count eq 1 ? '' : '2' }" title="카테고리 선택">
							<c:if test="${sts.count eq 1 }"><c:set var="categoryList" value="${tableDef.category1List}" /></c:if>
							<c:if test="${sts.count eq 2 }"><c:set var="categoryList" value="${tableDef.category2List}" /></c:if>
							<option value="">전체</option>
							<c:forEach var="category" items="${categoryList}">
								<c:if test="${category.useYn eq 'Y' }">
									<option value="${category.categoryValue}" ${sts.count eq 1 and category.categoryValue eq searchVO['CATEGORY'] ? 'selected' : sts.count eq 2 and category.categoryValue eq searchVO['CATEGORY2'] ? 'selected' : ''}>${category.categoryKey}</option>
								</c:if>
							</c:forEach>
						</select>
					</c:if>
				</c:forEach>

				<%-- 검색 조건 --%>
				<label for="searchType" class="blind">검색영역선택항목</label>
				<select id="searchType" name="searchType">
					<c:forEach var="column" items="${tableDef.columnDefList}">
						<c:if test="${column.display eq 'Y' and column.isSearch eq 'Y' }">
							<c:if test="${column.description ne '전화번호' }">
								<option value="${column.name}" <c:if test="${column.name == searchVO['searchType']}">selected="selected"</c:if>>${column.description}</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>

				<%-- 검색 키워드 --%>
				<label for="searchTxt" class="blind">검색어 입력 : </label>
				<input type="text" id="searchTxt" name="searchTxt" title="검색어 입력" value="${searchVO.searchTxt}" />

				<input type="submit" value="검색" name="searchBt" title="검색" class="" onclick="return goPage(1);">

				<%-- Daynimc Data Download Link --%>
				<c:if test="${tableDef.fileDownYn == 'Y'}">
					<a href="/${siteCodeFull}/${tableDef.urlName}/data/download.do?category=${searchVO['CATEGORY']}&amp;category2=${searchVO['CATEGORY2']}" class="btn_blue_down" target="_blank" title="${activeMenu.menuName} 전체 목록 엑셀 다운로드">엑셀</a>
				</c:if>
			</fieldset>
		</div>
	</c:if>

	<%-- ============================================ 검색 결과 영역 ============================================ --%>

	<div class="bod_wrap">
		<div class="tbl_wrap">
			<p class="tbl_guide">모바일 사용시 표를 좌우로 이동하여 내용을 확인 할 수 있습니다.</p>
		
			<table class="bod_maintain">
				<caption class="blind">
					${activeMenu.menuName} 목록을 번호<c:forEach var="element" items="${tableDef.columnDefList}">, ${element.description}</c:forEach> 항목별 상세히 안내하는 표입니다.
					<c:forEach var="column" items="${tableDef.columnDefList}" varStatus="status2">
						<c:if test="${column.isUse == 'Y' && column.display == 'Y' && column.type != 'clob'}">
							<c:set var="key" value="COL_${column.name}" />
							<c:choose>
								<c:when test="${column.linkType eq '1'}">
									<strong>${column.description}을(를) 클릭시 상세 보기 화면으로 이동</strong>합니다.</strong>
								</c:when>
								<c:when test="${column.linkType eq '2'}">
									<strong>${column.description}을(를) 클릭시 현재창에서 링크 주소로 이동</strong>합니다.</strong>
								</c:when>
								<c:when test="${column.linkType eq '3'}">
									<strong>${column.description}을(를) 클릭시 새 창에서 링크 주소로 이동</strong>합니다.</strong>
								</c:when>
								<c:when test="${column.linkType eq '4'}">
									<strong>${column.description}을(를) 클릭시 새 창에서 위치 보기 링크 주소로 이동</strong>합니다.</strong>
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
				</caption>
				<thead>
					<tr>
						<c:set var="totalCol" value="0" />
						<c:if test="${tableDef.numYn eq 'Y'}">
							<c:set var="totalCol" value="1" />
							<th scope="col" class="w6">번호</th>
						</c:if>
						<c:forEach var="categoryDef" items="${tableDef.categoryDefList }" varStatus="sts">
							<c:if test="${categoryDef.showYn eq 'Y' and categoryDef.showListYn eq 'Y' }">
								<c:if test="${sts.count eq 1 }"><c:set var="categoryList" value="${tableDef.category1List}" /></c:if>
								<c:if test="${sts.count eq 2 }"><c:set var="categoryList" value="${tableDef.category2List}" /></c:if>
								<c:if test="${not empty categoryList}">
									<c:set var="totalCol" value="${totalCol + 1}" />
									<th scope="col" <c:if test="${not empty categoryDef.width}">class="w${categoryDef.width}"</c:if>>${categoryDef.name }</th>
								</c:if>
							</c:if>
						</c:forEach>
						<c:forEach var="element" items="${tableDef.columnDefList}">
							<c:if test="${element.isUse == 'Y' && element.display == 'Y' && element.type != 'clob'}">
								<c:set var="totalCol" value="${totalCol + 1}" />
								<th scope="col" <c:if test="${not empty element.width}">class="w${element.width}"</c:if>>${element.description}</th>
							</c:if>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="element" items="${result}" varStatus="status">
						<tr>
							<c:if test="${tableDef.numYn eq 'Y'}">
							<td class="taC">${listOrder - status.index}</td>
							</c:if>
							<c:forEach var="categoryDef" items="${tableDef.categoryDefList }" varStatus="sts">
								<c:if test="${categoryDef.showYn eq 'Y' and categoryDef.showListYn eq 'Y' }">
									<c:if test="${sts.count eq 1 }"><c:set var="categoryList" value="${tableDef.category1List}"/></c:if>
									<c:if test="${sts.count eq 2 }"><c:set var="categoryList" value="${tableDef.category2List}"/></c:if>
									<c:if test="${not empty categoryList}">
										<td>
											<c:set var="chk" value="0"/>
											<c:forEach var="category" items="${categoryList}">
												<c:if test="${(sts.count eq 1 and category.categoryValue eq element['CATEGORY']) or (sts.count eq 2 and category.categoryValue eq element['CATEGORY2'])}">
													${category.categoryKey}
												<c:set var="chk" value="1"/>
												</c:if>
											</c:forEach>
											<c:if test="${chk eq '0' }">
												${sts.count eq 1 ? element['CATEGORY'] : element['CATEGORY2'] }
											</c:if>
										</td>
									</c:if>
								</c:if>
							</c:forEach>
							<c:forEach var="column" items="${tableDef.columnDefList}" varStatus="status2">
								<c:if test="${column.isUse == 'Y' && column.display == 'Y' && column.type != 'clob'}">
									<c:set var="key" value="COL_${column.name}" />
									<td <c:if test="${column.align == 'L'}">class="taL"</c:if><c:if test="${column.align != 'L'}">class="taC"</c:if>>
										<c:choose>
											<c:when test="${column.linkType eq '0'}">
												<c:if test="${not empty element[key]}">${column.newLine eq 'Y' ? element[key] : element[key]}</c:if>
												<c:if test="${empty element[key]}">-</c:if>
											</c:when>
											<c:when test="${column.linkType eq '1'}">
												<a href="#" onclick="fn_go_view(${element['IDX']}); return false;" title="${element[key]} 상세 정보 확인하기">${element[key]}</a>
											</c:when>
											<c:when test="${column.linkType eq '2'}">
												<a href="${element[key]}" target="_self" title="${element[key]}으로 현재창에서 이동">${element[key]}</a>
											</c:when>
											<c:when test="${column.linkType eq '3'}">
												<a href="${element[key]}" target="_blank" title="${element[key]}으로 새창에서 이동">${element[key]}</a>
											</c:when>
											<c:when test="${column.linkType eq '4'}">
												<a href="http://map.daum.net/?q=${element[key]}" target="_blank" title="새창에서 위치보기">위치보기</a>
											</c:when>
											<c:otherwise>-</c:otherwise>
										</c:choose>
									</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
					<c:if test="${empty result}">
						<tr class="taC">
							<td colspan="${totalCol}" class="taC">검색된 결과가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<c:if test="${not empty result}">
		<div class="bod_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage" />
		</div>
	</c:if>
</form>
