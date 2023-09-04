<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 만족도 평가 코멘트 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.02.22		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.02.22
	 */
%>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script type="text/javascript">
//<![CDATA[

	$(document).ready(function() {
		dp.bind($("[data-date=y]"));
	});

	function goPage(curPage) {
		var frm = document.getElementById('listForm');
		frm.page.value = curPage;
		frm.submit();
	}

//]]>
</script>
<c:set var="listUrl" value="/sys/satisfaction/total/list.do?mId=${menuVO.mId}" />
<c:set var="downUrl" value="/sys/satisfaction/total/down.do?mId=${menuVO.mId}" />

<h2>${menuVO.menuName}</h2>
<form:form commandName="searchVO" id="listForm" name="listForm" action="/sys/satisfaction/total/list.do?mId=${menuVO.mId}" methodParam="POST">
	<form:hidden path="page" />

	<div class="search_box">
		<div class="fL">
			<form:select path="siteCode">
				<form:option value="">전체</form:option>
				<c:forEach var="element" items="${siteList}">
					<form:option value="${element.siteCode}">${element.siteName}</form:option>
				</c:forEach>
			</form:select>
			<form:input path="sDay" class="input100" placeholder="시작일" data-validator="on" data-inputType="date" data-date="y" data-ismindatefor="eDay"/> ~
			<form:input path="eDay" class="input100" placeholder="종료일" data-validator="on" data-inputType="date" data-date="y" data-ismaxdatefor="sDay"/>
			<input type="hidden" id="downYn" name="downYn" value="" />
			<form:select path="searchType">
				<form:option value="menu_name">메뉴명</form:option>
				<form:option value="mid">메뉴ID</form:option>
				<form:option value="research_content">평가내용</form:option>
			</form:select>
			<form:input path="searchTxt" />
			<input type="submit" onclick="$('#page').val(1); document.getElementById('listForm').action = '${listUrl}';" class="btn_white" value="검색" />
			<input type="submit" onclick="document.getElementById('listForm').action = '${downUrl}';" class="btn_white" value="다운로드" />
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>만족도 평가 내용 목록</caption>
		<thead>
			<tr>
				<th scope="row" class="wp7">번호</th>
				<th scope="row" class="wp10">사이트</th>
				<th scope="row" class="wp15">메뉴</th>
				<th scope="row">평가 내용</th>
				<th scope="row" class="wp7">점수</th>
				<th scope="row" class="wp10">평가일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<c:choose>
						<c:when test="${
							element.siteCode == 'gongdo' ||
							element.siteCode == 'bogae' ||
							element.siteCode == 'geumgwang' ||
							element.siteCode == 'seoun' ||
							element.siteCode == 'miyang' ||
							element.siteCode == 'daedeok' ||
							element.siteCode == 'yangseong' ||
							element.siteCode == 'wongok' ||
							element.siteCode == 'iljuk' ||
							element.siteCode == 'juksan' ||
							element.siteCode == 'samjuk' ||
							element.siteCode == 'gosam' ||
							element.siteCode == 'anseong1' ||
							element.siteCode == 'anseong2' ||
							element.siteCode == 'anseong3'
						}">
							<c:set var="site" value="csc/${element.siteCode}" />
						</c:when>
						<c:when test="${
							element.siteCode == 'teen' ||
							element.siteCode == 'asimc' ||
							element.siteCode == 'youthlove'
						}">
							<c:set var="site" value="depart/${element.siteCode}" />
						</c:when>
						<c:when test="${
							element.siteCode == 'museum' ||
							element.siteCode == '41' ||
							element.siteCode == 'astro' ||
							element.siteCode == 'namsadang' ||
							element.siteCode == 'baudeogi' ||
							element.siteCode == 'camp' ||
							element.siteCode == 'folkloriada'
						}">
							<c:set var="site" value="tourPortal/${element.siteCode}" />
						</c:when>
						<c:otherwise>
							<c:set var="site" value="${element.siteCode}" />
						</c:otherwise>
					</c:choose>

					<td>${listOrder - status.index}</td>
					<td>${element.siteCode}</td>
					<td><a href="/${site}/contents.do?mId=${element.satisMid}" target="_blank">${element.menuName}</a></td>
					<td class="taL">${element.researchContent}</td>
					<td>${element.researchPoint}</td>
					<td><fmt:formatDate value="${element.regDt}" pattern="yy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 게시물이 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

</form:form>
