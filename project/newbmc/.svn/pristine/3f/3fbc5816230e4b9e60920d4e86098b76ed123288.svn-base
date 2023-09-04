<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황 관리 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.04		J.Ryeon Lee		최초 생성 및 코드 수정
	 * 2016.08.04		J.Ryeon Lee		카테고리 검색 지원 처리
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.05.04
	 */
%>
<h2>${menuVO.menuName}</h2>
<form:form commandName="searchVO" name="list" id="list" methodParam="post" action="/sys/dataMng/${tableDef.urlName}/list.do?mId=${menuVO.mId}">
	<input id="page" type="hidden" name="page" value="${paginationInfo.currentPageNo}"/>

	<div class="search_box">
		<div class="fL">
			<%-- 카테고리 검색 --%>
			<c:if test="${not empty tableDef.category1List}">
				<select name="CATEGORY">
					<option value="">전체</option>
					<c:forEach var="category" items="${tableDef.category1List}">
						<c:if test="${category.useYn eq 'Y' }">
							<option value="${category.categoryValue}" <c:if test="${category.categoryValue == searchVO['CATEGORY']}">selected</c:if>>${category.categoryKey}</option>
						</c:if>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${not empty tableDef.category2List}">
				<select name="CATEGORY2">
					<option value="">전체</option>
					<c:forEach var="category" items="${tableDef.category2List}">
						<c:if test="${category.useYn eq 'Y' }">
							<option value="${category.categoryValue}" <c:if test="${category.categoryValue == searchVO['CATEGORY2']}">selected</c:if>>${category.categoryKey}</option>
						</c:if>
					</c:forEach>
				</select>
			</c:if>
			<%-- 검색 조건 --%>
			<label for="keyName" class="hidden">검색키선택항목</label>
			<select name="searchType">
				<c:forEach var="def" items="${tableDef.columnDefList}">
					<c:if test="${def.display == 'Y'}">
						<option value="${def.name}" <c:if test="${searchVO['searchType'] == def.name}">selected</c:if>>${def.description}</option>
					</c:if>
				</c:forEach>
			</select>

			<%-- 검색 키워드 --%>
			<label for="keyValue" class="hidden">검색값입력</label>
			<input type="text" name="searchTxt" value="${searchVO['searchTxt']}"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<c:set var="totalCol" value="1"/>
				<th scope="col" class="w8">번호</th>
				<c:if test="${not empty tableDef.category1List}">
					<c:set var="totalCol" value="${totalCol + 1}"/>
					<th scope="col" <c:if test="${not empty tableDef.categoryWidth}">class="w${tableDef.categoryWidth}"</c:if>>${fn:split(tableDef.category,',')[0] }</th>
				</c:if>
				<c:if test="${not empty tableDef.category2List}">
					<c:set var="totalCol" value="${totalCol + 1}"/>
					<th scope="col" <c:if test="${not empty tableDef.categoryWidth}">class="w${tableDef.categoryWidth}"</c:if>>${fn:split(tableDef.category,',')[1] }</th>
				</c:if>
				<c:forEach var="element" items="${tableDef.columnDefList}">
					<c:if test="${element.isUse == 'Y' && element.display == 'Y' && element.type != 'clob'}">
						<c:set var="totalCol" value="${totalCol + 1}"/>
						<th scope="col" <c:if test="${not empty element.width}">class="w${element.width}"</c:if>>${element.description}</th>
					</c:if>
				</c:forEach>
				<th scope="col" class="w10">등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<c:if test="${not empty tableDef.category1List}">
						<td>
							<c:set var="chk" value="0"/>
							<c:forEach var="category" items="${tableDef.category1List}">
								<c:if test="${category.categoryValue eq element['COL_CATEGORY']}">
								${category.categoryKey}
								<c:set var="chk" value="1"/>
								</c:if>
							</c:forEach>
							<c:if test="${chk eq '0'}">
								${element['COL_CATEGORY'] }
							</c:if>
						</td>
					</c:if>
					<c:if test="${not empty tableDef.category2List}">
						<td>
							<c:set var="chk" value="0"/>
							<c:forEach var="category" items="${tableDef.category2List}">
								<c:if test="${category.categoryValue eq element['COL_CATEGORY2']}">
								${category.categoryKey}
								<c:set var="chk" value="1"/>
								</c:if>
							</c:forEach>
							<c:if test="${chk eq '0' }">
								${element['COL_CATEGORY'] }
							</c:if>
						</td>
					</c:if>
					<c:forEach var="column" items="${tableDef.columnDefList}" varStatus="status2">
						<c:if test="${column.isUse == 'Y' && column.display == 'Y' && column.type != 'clob'}">
							<c:set var="key" value="COL_${column.name}"/>
							<td <c:if test="${not empty column.align}">class="ta${column.align}"</c:if>>
								<c:if test="${tableDef.linkType == 0}">${element[key]}</c:if>
								<c:if test="${tableDef.linkType != 0}">
									<c:if test="${fn:toLowerCase(tableDef.linkColName) != fn:toLowerCase(column.name)}">${element[key]}</c:if>
									<c:if test="${fn:toLowerCase(tableDef.linkColName) == fn:toLowerCase(column.name)}">
										<c:choose>
											<c:when test="${tableDef.linkType == 1}">
												<a href="${element['COL_HP_URL']}" target="_blank">${element[key]}</a>
											</c:when>
											<c:when test="${tableDef.linkType == 2}">
												${element[key]}
												<%--
												<a href="#" onclick="fn_go_view(${element['IDX']}); return false;">
													${element[key]}
												</a>
												--%>
											</c:when>
											<c:when test="${tableDef.linkType == 3 && not empty element['COL_LAT'] && not empty element['COL_LNG']}">
												<a href="#" onclick="popup_map('${element[key]}', '${element['COL_LAT']}', '${element['COL_LNG']}'); return false;" title="${element[key]} 위치 지도로 보기">
													${element[key]}
												</a>
											</c:when>
											<c:when test="${tableDef.linkType == 3}">${element[key]}</c:when>
											<c:when test="${tableDef.linkType == 4}">
												<a href="${element[key]}" target="_blank">${element[key]}</a>
											</c:when>
											<c:otherwise>?</c:otherwise>
										</c:choose>
									</c:if>
								</c:if>
							</td>
						</c:if>
					</c:forEach>
					<td><fmt:formatDate value="${element['CREATE_DATE']}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 정보가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>

<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
