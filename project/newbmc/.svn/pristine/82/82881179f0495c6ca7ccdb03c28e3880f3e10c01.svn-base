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
	 * 2017.04.10		권태성			링크 타입별 목록 세팅 추가
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.05.04
	 */
%>
<script type="text/javascript" src="/common/js/map.js"></script>
<script type="text/javascript">
//<![CDATA[

	function fn_delete(idx) {
		if (confirm("삭제하시겠습니까?")) {
			$.ajax({
				type : "post",
				url : "/sys/dataMng/${tableDef.urlName}/deleteProc.do",
				data : { "IDX" : idx, "tableName" : "${tableDef.urlName}" },
				success : function (data) {
					var result = $.parseJSON(data);
					if (result.success) {
						alert("삭제되었습니다.");
						location.href = "/sys/dataMng/${tableDef.urlName}/list.do?mId=${menuVO.mId}";
						return false;
					}

					alert("처리 중 에러가 발생했습니다.");
				}
			}).fail(function() { alert("서버와 통신 중 에러가 발생했습니다."); });

			return false;
		}
	}

	function fn_batch_delete() {
		if ($("[name=idxs]:checked").length == 0) {
			alert("삭제하실 데이터를 선택해주세요.");
			return false;
		}

		if (confirm("선택하신 목록을 삭제하시겠습니까?")) {
			$.ajax({
				type : "post", dataType: "json", data : { idxs : $("[name=idxs]:checked").map(function() { return this.value; }).get().toString() },
				url : "/sys/dataMng/${tableDef.urlName}/batchDeleteProc.do",
				success : function (result) {
					if (result.success) {
						alert("삭제되었습니다.");
						location.href = "/sys/dataMng/${tableDef.urlName}/list.do?mId=${menuVO.mId}";
						return false;
					}

					alert("처리 중 에러가 발생했습니다.");
				}
			}).fail(function() { alert("서버와 통신 중 에러가 발생했습니다."); });
		}

		return false;
	}

	function fn_modify(idx) {
		var frm = document.getElementById("form");
		frm.action = "/sys/dataMng/${tableDef.urlName}/modify.do?mId=${menuVO.mId}&idx=" + idx;
		frm.submit();
	}

	/** PAGING */
	function goPage(page) {
		var frm = document.getElementById("form");
		frm.page.value = page;
		frm.submit();
	}

	function fn_chkbox_toggle() {
		if ($("[name=all]:checked").length == 0) {
			$('[name=idxs]').prop('checked', false);
		} else {
			$('[name=idxs]').prop('checked', true);
		}
	}

//]]>
</script>

<h2>${tableDef.tableDesc} 관리</h2>
<jsp:include page="./component/headerModify.jsp"/>
<form:form commandName="searchVO" name="form" id="form" methodParam="post" action="/sys/dataMng/${tableDef.urlName}/list.do?mId=${menuVO.mId}">

	<input id="page" type="hidden" name="page" value="${paginationInfo.currentPageNo}"/>

	<div class="search_box">
		<div class="fL">
			<%-- 카테고리 검색 --%>
			<c:forEach var="categoryDef" items="${tableDef.categoryDefList }" varStatus="sts">
				<c:if test="${categoryDef.showYn eq 'Y' and categoryDef.showSearchYn eq 'Y' }">
					<select name="CATEGORY${sts.count eq 1 ? '' : '2' }">
						<c:if test="${sts.count eq 1 }"><c:set var="categoryList" value="${tableDef.category1List}"/></c:if>
						<c:if test="${sts.count eq 2 }"><c:set var="categoryList" value="${tableDef.category2List}"/></c:if>
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
			<label for="keyName" class="hidden">검색키선택항목</label>
			<select name="searchType">
				<c:forEach var="def" items="${tableDef.columnDefList}">
					<c:if test="${def.display == 'Y' and def.isSearch == 'Y'}">
						<option value="${def.name}" <c:if test="${searchVO['searchType'] == def.name}">selected</c:if>>${def.description}</option>
					</c:if>
				</c:forEach>
			</select>

			<%-- 검색 키워드 --%>
			<label for="keyValue" class="hidden">검색값입력</label>
			<input type="text" name="searchTxt" value="${searchVO['searchTxt']}"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
			<input type="button" onclick="return fn_batch_delete();" class="btn_white" value="일괄삭제"/>
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<c:set var="totalCol" value="1"/>
				<th scope="col" class="w2"><input type="checkbox" id="all" name="all" onclick="fn_chkbox_toggle();"/></th>
				<th scope="col" class="w5"><label for="all">번호</label></th>
				<c:forEach var="categoryDef" items="${tableDef.categoryDefList }" varStatus="sts">
					<c:if test="${categoryDef.showYn eq 'Y' and categoryDef.showListYn eq 'Y' }">
						<c:if test="${sts.count eq 1 }"><c:set var="categoryList" value="${tableDef.category1List}"/></c:if>
						<c:if test="${sts.count eq 2 }"><c:set var="categoryList" value="${tableDef.category2List}"/></c:if>
						<c:if test="${not empty categoryList}">
							<c:set var="totalCol" value="${totalCol + 1}"/>
							<th scope="col" <c:if test="${not empty categoryDef.width}">class="w${categoryDef.width}"</c:if>>${categoryDef.name }</th>
						</c:if>
					</c:if>
				</c:forEach>
				<c:forEach var="element" items="${tableDef.columnDefList}">
					<c:if test="${element.isUse == 'Y' && element.display == 'Y' && element.type != 'clob'}">
						<c:set var="totalCol" value="${totalCol + 1}"/>
						<th scope="col" <c:if test="${not empty element.width}">class="w${element.width}"</c:if>>${element.description}</th>
					</c:if>
				</c:forEach>
				<th scope="col" class="w10">등록일</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td><input type="checkbox" id="idxs${element.IDX}" name="idxs" value="${element.IDX}"/></td>
					<td>${listOrder - status.index}</td>
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
							<c:set var="key" value="COL_${column.name}"/>
							<td <c:if test="${column.align == 'L'}">class="taL"</c:if>>
								<c:choose>
									<c:when test="${column.linkType eq '0'}">
										<c:choose>
											<c:when test="${column.type eq 'file'}">
												<c:import url="/cmm/fms/fileInfo.do" charEncoding="utf-8">
													<c:param name="attchId" value="${element[key]}"/>
													<c:param name="mode" value="fileView"/>
													<c:param name="fileSn" value="0"/>
												</c:import>
											</c:when>
											<c:otherwise>
												${element[key]}
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${column.linkType eq '1'}">
										${element[key]}
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
					<td>${fn:substring(element['CREATE_DATE'],0,10)}</td>
					<td>
						<a href="#" onclick="fn_modify('${element['IDX']}'); return false;" class="btn_swhite">보기</a>
						<a href="#" onclick="fn_delete('${element['IDX']}'); return false;" class="btn_swhite">삭제</a>
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

	<div class="btn_boxR">
		<a href="/sys/dataMng/${tableDef.urlName}/dynamicDataDownloader.do" class="btn_dblue" target="_blank">다운로드</a>
		<input type="button" onclick="window.open('/sys/dataMng/${tableDef.urlName}/excelUp.do?mId=${menuVO.mId}','excelUp', 'width=544, height=437, scrollbars=yes'); return false;" class="btn_cyan" value="엑셀업로드"/>
		<a href="/sys/dataMng/${tableDef.urlName}/write.do?mId=${menuVO.mId}" class="btn_cyan">등록</a>
	</div>

</form:form>
