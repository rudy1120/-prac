<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 만족도 관리(평균점)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.12.04		김현호			디자인 퍼블리싱
	 * 2016.07.14		J.Ryeon Lee		고도화에 맞춰 지원하지 않는 항목 삭제
	 * 2017.07.13		J.Ryeon Lee		리팩키징, 코드 리팩토링
	 *
	 *
	 * @author 엄동건
	 * @since 2014.10.21
	 */
%>
<c:set var="listUrl" value="/sys/satisfaction/listSatis.do?mId=${menuVO.mId}" />
<c:set var="downUrl" value="/sys/satisfaction/listSatis/down.do?mId=${menuVO.mId}" />

<h2>${menuVO.menuName}</h2>
<form:form commandName="searchVO" id="list" name="list" action="${listUrl}" method="post" onsubmit="return validator();">
	<form:hidden path="page"/>

	<%-- ============================== 검색 영역 ============================== --%>
	<div class="search_box">
		<label for="siteCode" class="hidden">사이트분류</label>
		<form:select path="siteCode">
			<form:option value="">전체</form:option>
			<c:if test="${not empty listSite}">
				<c:forEach var="site" items="${listSite}">
					<form:option value="${site.siteCode}">${site.siteName}</form:option>
				</c:forEach>
			</c:if>
		</form:select>
		<label for="satisMid" class="hidden">메뉴분류</label>
		<form:select path="satisMid">
			<form:option value="">선택</form:option>
			<c:if test="${not empty listMenu}">
				<c:forEach var="menu" items="${listMenu}">
					<form:option value="${menu.satisMid}">
						<c:if test="${not empty menu.parentName}">[${menu.parentName}]</c:if>${menu.menuName}
					</form:option>
				</c:forEach>
			</c:if>
		</form:select>
		<label for="orderByCode" class="hidden">정렬순서</label>
		<form:select path="orderByCode">
			<form:option value="orderA">평점역순</form:option>
			<form:option value="orderB">평점순</form:option>
			<form:option value="orderC">참여수역순</form:option>
			<form:option value="orderD">참여수순</form:option>
		</form:select>
		<form:input path="sDay" class="input100" placeholder="시작일" data-validator="on" data-inputType="date" data-date="y" data-ismindatefor="eDay"/> ~
		<form:input path="eDay" class="input100" placeholder="종료일" data-validator="on" data-inputType="date" data-date="y" data-ismaxdatefor="sDay"/>
		<input type="hidden" id="downYn" name="downYn" value=""/>
		<input type="submit" onclick="document.list.action = '${listUrl}';" class="btn_white" value="검색"/>
		<input type="submit" onclick="document.list.action = '${downUrl}';" class="btn_white" value="다운로드"/>
		<span class="fR mT15">총 <span class="red">${totalCount}</span>개의 게시물이 있습니다.</span>
	</div>
	<div class="cleB"></div>

	<%-- ============================== 검색 결과 영역 ============================== --%>
	<table class="tableSt_list row_over">
		<caption>${menuVO.menuName} 리스트</caption>
		<thead>
			<tr>
				<th scope="row" class="w30">사이트</th>
				<th scope="row">메뉴</th>
				<th scope="row" class="w15">평균 점수</th>
				<th scope="row" class="w15">참여자수</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty resultList}">
				<c:forEach var="element" items="${resultList}">
					<tr>
						<td>${element.siteName}</td>
						<td>
							<c:choose>
								<c:when test="${empty element.menuName}">
									<span class="red">삭제된 메뉴입니다. [메뉴ID: ${element.satisMid}]</span>
								</c:when>
								<c:otherwise>${element.menuName}</c:otherwise>
							</c:choose>
						</td>
						<td><fmt:formatNumber value="${element.avgPoint}" pattern=".00"/>점</td>
						<td>${element.appCnt}명</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<c:if test="${empty resultList}">
		<div class="no_data">등록된 게시물이 없습니다.</div>
	</c:if>

	<c:if test="${not empty resultList}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>
</form:form>


<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script type="text/javascript">
	yh.siteCode = "sys";
	$(document).ready(function() { // 검색 시작일, 종료일 datePicker 적용
		dp.bind(jQuery("[data-date=y]"));
	});

	$(document).ready(function() {
		$("#siteCode").change(function() { // 사이트 변경시 메뉴 반영
			$.ajax({
				type: "post", dataType:"json", async: true, url: '/sys/satisfaction/comboMenuForSite.do', data: { siteCode: $(this).val() },
				success: function(data) {
					if (data.flag == "success") {
						var list=data.list;
						var opts = "";
						$.each(list,function(i) {
							var optVal = "";
							if (list[i].parentName) { optVal="["+list[i].parentName+"]"; }
							optVal += list[i].menuName;
							opts += '<option value="'+list[i].satisMid+'">' + optVal + '</option>';
						});
						$("#satisMid").empty().html(opts).prepend('<option value="">선택</option>');
					} else {
						alert(data.message);
					}
				}
			});
		});
	});
</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
