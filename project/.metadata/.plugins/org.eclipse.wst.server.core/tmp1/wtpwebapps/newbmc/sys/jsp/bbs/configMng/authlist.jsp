<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파  일  명 : authlist.jsp
* 설      명 : 공통게시판 권한 리스트
* 작  성  자 : 김장섭
* 작  성  일 : 2018.11.13
*********************************************************************
--%>

<script type="text/javascript">
//<![CDATA[

	function fn_select_list(frm) {
		frm.page.value = 1;
		frm.submit();
		return false;
	}

	function goPage(curPage) {
		var frm = document.getElementById("listForm");
		frm.page.value = curPage;
		frm.action = "/sys/${siteCode}/bbs/configMng/auth/list.do?mId=${menuVO.mId}";
		frm.submit();
	}

	$(document).ready(function() {
		$("#site_radio_box > input").click(function() {
			fn_select_list(document.getElementById("listForm"));
		});
	});
//]]>
</script>
<c:set var="downUrl" value="${_context}/sys/${siteCode}/bbs/configMng/auth/downProc.do"/>

<h2>게시판 권한조회</h2>
<form:form commandName="searchVO" name="listForm" id="listForm" action="/sys/${siteCode}/bbs/configMng/auth/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page" />
	<form:hidden path="ptIdx" />

	<c:if test="${ADMIN_LOGIN_INFO.adminAccessLevelCode == 10}">
		<div id="site_radio_box" class="board_head" style="margin-bottom:0px;">
			<form:radiobutton path="menu_category" value="" label="전체" />
			<c:forEach var="siteGubun" items="${siteGubunList}">
				<form:radiobutton path="menu_category" value="${siteGubun.code}" label="${siteGubun.name}" />
			</c:forEach>
			<%-- <input type="radio" name="menu_category" value="" <c:if test="${searchVO.menu_category eq ''}">checked="checked"</c:if> id="menu_category_all"><label for="menu_category_all">전체</label>
			<input type="radio" name="menu_category" value="0" <c:if test="${searchVO.menu_category eq '0'}">checked="checked"</c:if> id="menu_category_main"><label for="menu_category_main">대표(공통/열린시장실)</label>
			<input type="radio" name="menu_category" value="1" <c:if test="${searchVO.menu_category eq '1'}">checked="checked"</c:if> id="menu_category_dept"><label for="menu_category_dept">부서및빌트인</label>
			<input type="radio" name="menu_category" value="2" <c:if test="${searchVO.menu_category eq '2'}">checked="checked"</c:if> id="menu_category_csc"><label for="menu_category_csc">읍면동</label>
			<input type="radio" name="menu_category" value="3" <c:if test="${searchVO.menu_category eq '3'}">checked="checked"</c:if> id="menu_category_etc"><label for="menu_category_etc">기타</label> --%>
		</div>
	</c:if>
	<div class="search_box">
		<div class="fL">
			<c:if test="${not empty siteList}">
				<form:select path="searchType">
					<option value="">사이트선택</option>
					<c:if test="${searchVO.menu_category == '' or searchVO.menu_category == '0'}">
						<option value="common" <c:if test="${searchVO.searchType == 'common'}"> selected ="selected" </c:if>>공통</option>
					</c:if>
					<c:forEach var="code" items="${siteList}">
						<form:option value="${code.siteCode}">${code.siteName}</form:option>
					</c:forEach>
				</form:select>
			</c:if>
			<form:select path="searchPtType">
				<form:option value="">게시판 타입</form:option>
				<c:forEach var="code" items="${boardTypeList}" >
					<form:option value="${code.codeId}">${code.codeNm}</form:option>
				</c:forEach>
			</form:select>
			<form:input type="text" path="searchTxt" />
			<input type="submit" onclick="$('#page').val(1);" class="btn_white" value="검색" />
			<a href="#" class="btn_blue" onclick="document.listForm.action = '${downUrl}?mId=${menuVO.mId}'; document.listForm.submit(); return false;" target="_blank" title="새창열림">현재 조건 엑셀 다운로드</a>
		</div>
		<div class="cleB"></div>
	</div>

	<c:set var="isAdmin" value="${ADMIN_LOGIN_INFO.adminAccessLevelCode == 10 || ADMIN_LOGIN_INFO.adminId == 'jhaoo'}" />
	<table class="tableSt_list row_over">
		<caption>공통게시판 리스트</caption>
		<colgroup>
			<col span="1" style="width:5%;" />
			<c:if test="${not empty siteList}"><col span="1" style="width:13%;" /></c:if>
			<col span="1" style="width:10%;" />
			<col span="1" />
			<col span="1" style="width:35%;" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<c:if test="${not empty siteList}"><th scope="col">사이트</th></c:if>
				<th scope="col">구분</th>
				<th scope="col">게시판명</th>
				<th scope="col">권한자 & 권한부서</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${not empty result}">
			<c:forEach var="result" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<c:if test="${not empty siteList}">
						<td>
							<c:forEach var="code" items="${siteList}">
								<c:if test="${code.siteCode == result.ptSiteCode}">${code.siteName}</c:if>
							</c:forEach>
							<c:if test="${result.ptSiteCode == 'common'}">공통</c:if>
						</td>
					</c:if>
					<td>
						<c:forEach var="code" items="${boardList}">
							<c:if test="${code.codeId == result.ptType}">${code.codeNm}</c:if>
						</c:forEach>
					</td>
					<td class="taL">${result.ptTitle}</td>
					<td class="taL">
						<c:if test="${!empty result.ptMngNms}">권한자 : ${result.ptMngNms}</c:if>
						<c:if test="${!empty result.ptMngDeptNms}"><br/>권한부서 : ${result.ptMngDeptNms}</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 게시판이 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage" />
		</div>
	</c:if>

</form:form>
