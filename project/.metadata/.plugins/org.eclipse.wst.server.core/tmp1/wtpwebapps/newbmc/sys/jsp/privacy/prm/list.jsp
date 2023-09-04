<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 개인정보 취급 메뉴 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.06.15		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.06.15
	 */
%>
<h2>개인정보 취급 메뉴 목록 열람</h2>
<form:form commandName="searchVO" name="list" id="list" action="/sys/privacy/prm/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>
	<form:hidden path="idx"/>
	<form:hidden path="ptIdx"/>

	<div class="search_box">
		<div class="fL">
			<label for="searchSiteCode" class="hidden">사이트 선택</label>
			<form:select path="searchSiteCode">
				<option value="">전체</option>
				<c:forEach var="s" items="${siteList}">
					<form:option value="${s.siteCode}">${s.siteName}</form:option>
				</c:forEach>
			</form:select>
			<label for="searchPrmType" class="hidden">구분값 선택</label>
			<form:select path="searchPrmType">
				<option value="">전체</option>
				<c:forEach var="p" items="${prmTypeList}">
					<form:option value="${p.code}">${p.name}</form:option>
				</c:forEach>
			</form:select>
			<label for="searchType" class="hidden">검색키 선택 항목</label>
			<form:select path="searchType">
				<form:option value="p_nm">프로그램명</form:option>
				<form:option value="col_nms">개인정보항목</form:option>
			</form:select>
			<label for="searchTxt" class="hidden">검색값 입력</label>
			<form:input path="searchTxt"/>
			<input type="submit" onclick="$('#page').val(1);" class="btn_white" value="검색"/>
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption></caption>
		<thead>
			<tr>
				<th scope="col" class="w5">번호</th>
				<th scope="col" class="w8">구분</th>
				<th scope="col" class="w10">사이트</th>
				<th scope="col">프로그램명</th>
				<th scope="col" class="w25">항목명</th>
				<th scope="col" class="w8">보유 기간</th>
				<th scope="col" class="w13">이력</th>
				<th scope="col" class="w11">메뉴 가기</th>
				<th scope="col" class="w8">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>
						<c:forEach var="p" items="${prmTypeList}">
							<c:if test="${p.code == element.prmType}">${p.name}</c:if>
						</c:forEach>
					</td>
					<td>${element.prmSiteName}</td>
					<td class="taL">${element.prmName}</td>
					<td class="taL">${element.prmColNames}</td>
					<td>
						<c:if test="${element.prmPeriod < 12}">${element.prmPeriod}개월</c:if>
						<c:if test="${element.prmPeriod > 11}"><fmt:formatNumber value="${element.prmPeriod / 12}" pattern="#"/>년</c:if>
					</td>
					<td>
						<a href="/sys/privacy/prm/history/list.do?mId=${menuVO.mId}&amp;prmIdx=${element.idx}" class="btn_swhite">변경 이력</a>
						<a href="/sys/privacy/data/history/list.do?mId=${menuVO.mId}&amp;prmIdx=${element.idx}" class="btn_swhite">데이터 삭제 이력</a>
					</td>
					<td>
						<c:set var="siteCodeFull" value="${element.prmSiteCode}"/>
						<c:forEach var="s" items="${siteList}">
							<c:if test="${s.siteCode == element.prmSiteCode && not empty s.siteGroup}">
								<c:set var="siteCodeFull" value="${s.siteGroup}/${element.prmSiteCode}"/>
							</c:if>
						</c:forEach>
						<c:if test="${siteCodeFull == 'common'}"><c:set var="siteCodeFull" value="portal"/></c:if>

						<%-- ##### 사용자 메뉴 링크 ##### --%>
						<c:if test="${empty element.prmMid}">
							<a href="#" onclick="alert('연결된 메뉴가 없습니다.\n[메뉴 관리 > 사용자 메뉴 관리 > 메뉴 관리]에서\n메뉴와 게시판을 연결할 수 있습니다.');" class="btn_swhite">사용자</a>
						</c:if>
						<c:if test="${not empty element.prmMid}">
							<a href="/${siteCodeFull}/contents.do?mId=${element.prmMid}" class="btn_swhite" target="_blank">사용자</a>
						</c:if>
						<%-- ##### 관리자 메뉴 링크 ##### --%>
						<c:if test="${empty element.ptIdx}">
							<a href="/sys/sysContents.do?mId=${element.prmSysMid}" class="btn_swhite" target="_blank">관리자</a>
						</c:if>
						<c:if test="${not empty element.ptIdx}">
							<a href="/sys/superMng/bbs/bbsMng/list.do?ptIdx=${element.ptIdx}&amp;mId=${element.prmSysMid}" class="btn_swhite" target="_blank">관리자</a>
						</c:if>
					</td>
					<td>
						<c:if test="${not empty element.ptIdx}"> <%-- case 게시판: 게시판 수정 페이지로 이동 --%>
							<a href="#"
								data-action="/sys/superMng/bbs/configMng/modify.do?mId=0103010000&cancelMid=${menuVO.mId}"
								data-keyset="{'ptIdx': ${element.ptIdx}, 'cancelParmas1': '${searchVO.searchPrmType}'}"
								onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						</c:if>
						<c:if test="${empty element.ptIdx}"> <%-- case 단위 프로그램: 수정 페이지로 이동 --%>
							<a href="#" data-action="/sys/privacy/prm/modify.do" data-keyset="{'idx': ${element.idx}}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
						</c:if>
						<a href="#" class="btn_sblack"
							data-action="/sys/privacy/prm/deleteProc.do"
							data-keyset="{'idx': ${element.idx}}"
							data-failedmsg="${empty element.ptIdx ? '' : '게시판을 삭제 후 해당 설정을 삭제할 수 있습니다.'}"
							onclick="req.ajax(this); return false;">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 메뉴가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

	<div class="btn_boxR">
		<a href="/sys/privacy/prm/history/list.do?mId=${menuVO.mId}" class="btn_dblue">전체 변경 이력</a>
		<a href="#" data-action="/sys/privacy/prm/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
</form:form>

<script type="text/javascript">
	yh.siteCode = "sys";
</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
