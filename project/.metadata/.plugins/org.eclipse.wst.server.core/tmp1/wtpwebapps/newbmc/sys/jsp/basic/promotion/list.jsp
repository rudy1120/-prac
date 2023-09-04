<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 홍보 자료 관리 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.07.18		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.07.18
	 */
%>
<c:set var="isNotBanner" value="${searchVO.type.code != prmtTypeList[0].code}"/>
<c:set var="isPopupzone" value="${searchVO.type.code == prmtTypeList[2].code}"/>
<h2>${searchVO.type.desc} 목록</h2>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="idx"/>
	<form:hidden path="page"/>
	<form:hidden path="prmtOrder"/>
	<form:hidden path="prmtOldOrder"/>

	<%-- ============================ 검색 영역 ============================ --%>
	<div class="search_box">
		<div class="fL">
			<form:select path="searchSiteIdx">
				<c:forEach var="s" items="${siteList}">
					<form:option value="${s.idx}">${s.siteName}</form:option>
				</c:forEach>
			</form:select>
			<form:select path="searchUseYn">
				<form:option value="">전체</form:option>
				<form:option value="Y">사용</form:option>
				<form:option value="N">미사용</form:option>
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
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w10">사이트</th>
				<c:if test="${isNotBanner}"><th scope="col" class="w20">이미지</th></c:if>
				<th scope="col">제목</th>
				<th scope="col" class="w15">게시 기간</th>
				<th scope="col" class="w5">정렬<br/>순서</th>
				<th scope="col" class="w5">사용<br/>여부</th>
				<th scope="col" class="w15">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="idCnt" value="${status.count}"/>
				<tr>
					<%-- ============================ 번호 ============================ --%>
					<td>${listOrder - status.index}</td>
					<%-- ============================ 사이트 ============================ --%>
					<td>
						<c:forEach var="s" items="${element.promotionSites}" varStatus="status2">
							${s.siteName}<c:if test="${status.count != fn:length(element.promotionSites)}"><br/></c:if>
						</c:forEach>
					</td>
					<%-- ============================ 이미지 ============================ --%>
					<c:if test="${isNotBanner}">
						<td>
							<c:choose>
								<c:when test="${isPopupzone && element.prmtTemplate != '0' && element.fileCnt == 0}">
									<img src="/common/img/basic/promotion/${fn:toLowerCase(searchVO.type.path)}_template${element.prmtTemplate}.jpg" alt="${searchVO.type.desc} 템플릿${element.prmtTemplate}"/>
								</c:when>
								<c:otherwise>
									<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${element.attachId}"/>
										<c:param name="mode" value="ratio"/>
										<c:param name="width" value="240"/>
										<c:param name="height" value="169"/>
									</c:import>
								</c:otherwise>
							</c:choose>
						</td>
					</c:if>
					<%-- ============================ 제목 ============================ --%>
					<td class="taL">
						<c:choose>
							<c:when test="${not empty element.prmtUrl}"><a href="${element.prmtUrl}" target="_blank">${element.prmtName}</a></c:when>
							<c:otherwise>${element.prmtName}</c:otherwise>
						</c:choose>
					</td>
					<%-- ============================ 게시 기간 ============================ --%>
					<td>
						<c:choose>
							<c:when test="${not empty element.prmtSday && not empty element.prmtEday}">${element.prmtSday}<br/>~<br/>${element.prmtEday}</c:when>
							<c:when test="${not empty element.prmtSday}">${element.prmtSday}~</c:when>
							<c:when test="${not empty element.prmtEday}">~${element.prmtEday}</c:when>
							<c:otherwise>상시 게시</c:otherwise>
						</c:choose>
					</td>
					<%-- ============================ 정렬순서 ============================ --%>
					<td>
						<select id="prmtCurOrder${idCnt}" name="prmtCurOrder" class="input150"  data-action="/sys/${searchVO.type.path}/changeOrderProc.do" data-keyset="{'idx': ${element.idx}, 'prmtOldOrder': ${element.prmtOrder},'searchSiteIdx': ${searchVO.searchSiteIdx}}" onfocus="$('#prmtOldOrder').val($('#prmtCurOrder${idCnt}').val());" onchange="if (confirm('순서를 변경하시겠습니까?')) { $('#prmtOrder').val($('#prmtCurOrder${idCnt}').val()); req.post(this); } else {$('#prmtCurOrder${idCnt}').val($('#prmtOldOrder').val()); $('#prmtOldOrder').val('');} return false;">
							<c:forEach var="oelement" items="${prmtOrderList}" varStatus="ostatus">
								<option value="${oelement.prmtOrder}" ${element.idx eq oelement.idx ? 'selected="selected"' : ''}>${oelement.prmtOrder}</option>
							</c:forEach>
						</select>
					</td>
					<%-- ============================ 사용 여부 ============================ --%>
					<td>${element.useYn == 'Y' ? '사용' : '미사용'}</td>
					<%-- ============================ 관리 ============================ --%>
					<td>
						<a href="#" data-action="/sys/${searchVO.type.path}/modify.do" data-keyset="{'idx': ${element.idx},'searchSiteIdx': ${searchVO.searchSiteIdx}}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						<a href="#" data-action="/sys/${searchVO.type.path}/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'idx': ${element.idx},'searchSiteIdx': ${searchVO.searchSiteIdx}}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
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
		<c:if test="${ADMIN_LOGIN_INFO.adminAccessLevelCode == 10 && !isNotBanner}">
			<a href="#" data-action="/sys/banner/excel/upload.do?mId=${menuVO.mId}" onclick="req.post(this); return false;" data-target="blank" class="btn_dblue">엑셀 업로드</a>
		</c:if>
		<a href="#" data-action="/sys/${searchVO.type.path}/write.do" data-keyset="{'searchSiteIdx': ${searchVO.searchSiteIdx}}" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
</form:form>

<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
