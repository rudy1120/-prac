<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파  일  명 : list.jsp
* 설      명 : 공통게시판 속성 리스트
* 작  성  자 : 김혜민
* 작  성  일 : 2014.09.24
*********************************************************************
--%>

<script type="text/javascript">
//<![CDATA[

	function fn_modify_view(idx){
		var frm = document.getElementById('listForm');
		frm.ptIdx.value = idx;
		frm.action = "/sys/${siteCode}/bbs/configMng/modify.do?mId=${menuVO.mId}";
		frm.submit();
	}

	function fn_select_list(frm) {
		frm.page.value = 1;
		frm.submit();
		return false;
	}

	function fn_delete(idx){
		$.ajax({
			type : 'get',
			url : '/sys/${siteCode}/bbs/bbsCongig/isUsedBbs.do',
			data : { ptIdx : idx },
			success : function (data) {
				var msg = "삭제하면 복구할 수 없습니다. 삭제하시겠습니까?";
				var result = $.parseJSON(data);
				if (result.result) {
					if (result.isUsed) {
						msg = "사용중인 게시판입니다. " + msg;
					}

					if (confirm(msg)) {
						var frm = document.getElementById('listForm');
						frm.ptIdx.value = idx;
						frm.action = "/sys/${siteCode}/bbs/configMng/deleteProc.do?mId=${menuVO.mId}";
						frm.submit();
					}
					return false;
				}
				alert("통신 중 에러가 발생했습니다.");
			}
		});

		return false;
	}

	function fn_select_board(idx) {
		var frm = document.getElementById('listForm');
		frm.ptIdx.value = idx;
		frm.page.value = "1";
		frm.searchTxt.value = "";
		frm.action ="/sys/${siteCode}/bbs/bbsMng/list.do?mId=${menuVO.mId}";
		frm.submit();
	}

	function goPage(curPage) {
		var frm = document.getElementById("listForm");
		frm.page.value = curPage;
		frm.action = "/sys/${siteCode}/bbs/configMng/list.do?mId=${menuVO.mId}";
		frm.submit();
	}

	$(document).ready(function() {
		$("#site_radio_box > input").click(function() {
			fn_select_list(document.getElementById("listForm"));
		});
	});
//]]>
</script>

<h2>게시판관리 // ${siteCode}</h2>
<form:form commandName="searchVO" name="listForm" id="listForm" action="/sys/${siteCode}/bbs/configMng/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page" />
	<form:hidden path="ptIdx" />

	<%-- <c:if test="${ADMIN_LOGIN_INFO.adminAccessLevelCode == 10}">
		<div id="site_radio_box" class="board_head" style="margin-bottom:0px;">
			<form:radiobutton path="menu_category" value="" label="전체" />
			<c:forEach var="siteGubun" items="${siteGubunList}">
				<form:radiobutton path="menu_category" value="${siteGubun.code}" label="${siteGubun.name}" />
			</c:forEach>
			<input type="radio" name="menu_category" value="" <c:if test="${searchVO.menu_category eq ''}">checked="checked"</c:if> id="menu_category_all"><label for="menu_category_all">전체</label>
			<input type="radio" name="menu_category" value="0" <c:if test="${searchVO.menu_category eq '0'}">checked="checked"</c:if> id="menu_category_main"><label for="menu_category_main">대표(공통/열린시장실)</label>
			<input type="radio" name="menu_category" value="1" <c:if test="${searchVO.menu_category eq '1'}">checked="checked"</c:if> id="menu_category_dept"><label for="menu_category_dept">부서및빌트인</label>
			<input type="radio" name="menu_category" value="2" <c:if test="${searchVO.menu_category eq '2'}">checked="checked"</c:if> id="menu_category_csc"><label for="menu_category_csc">읍면동</label>
			<input type="radio" name="menu_category" value="3" <c:if test="${searchVO.menu_category eq '3'}">checked="checked"</c:if> id="menu_category_etc"><label for="menu_category_etc">기타</label>
		</div>
	</c:if> --%>
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
			<col span="1" style="width:15%;" />
			<c:if test="${isAdmin}">
				<col span="1" style="width:17%;" />
			</c:if>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<c:if test="${not empty siteList}"><th scope="col">사이트</th></c:if>
				<th scope="col">구분</th>
				<th scope="col">게시판명</th>
				<th scope="col">쓰기권한</th>
				<c:if test="${isAdmin}">
					<th scope="col">관리</th>
				</c:if>
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
					<td class="taL"><a href="#" onclick="fn_select_board('${result.ptIdx}'); return false;">${result.ptTitle}</a></td>
					<td>
						<c:choose>
							<c:when test="${result.ptLevelInput eq 'YY'}">일반사용자 / 관리자</c:when>
							<c:when test="${fn:substring(result.ptLevelInput,1,2) eq 'Y'}">일반사용자</c:when>
							<c:when test="${fn:substring(result.ptLevelInput,0,1) eq 'Y'}">관리자</c:when>
						</c:choose>
					</td>
					<c:if test="${isAdmin}">
						<td>
							<a href="#" onclick="fn_modify_view('${result.ptIdx }'); return false;" class="btn_swhite">수정</a>
							<a href="#" onclick="fn_delete('${result.ptIdx}'); return false;" class="btn_swhite">삭제</a>
						</td>
					</c:if>
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

	<c:if test="${isAdmin}">
		<div class="btn_boxR">
			<a href="${pageContext.request.contextPath}/sys/${siteCode}/bbs/configMng/write.do?mId=${menuVO.mId}" class="btn_cyan">등록</a>
		</div>
	</c:if>

</form:form>
