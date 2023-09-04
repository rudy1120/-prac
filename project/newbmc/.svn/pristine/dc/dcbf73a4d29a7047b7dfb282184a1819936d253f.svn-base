<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<script>
//조회수 집계 및 새창 오픈
var fn_countStats= function(pubIdx, link) {
	$.ajax({
		type: "post", 
		dataType: "json", 
		data : {pubIdx : pubIdx}, 
		url: "/bmc/public/statsInsert.do"
	}).fail(function () {
		alert("서버와 통신 중 에러가 발생했습니다.");
	});
	window.open(link, '_blank');
};


$(function(){
	
	//20.04.09 웹접근성 반영 - 각페이지 title 정보 수정 
	var titleVal = "사전정보공표 - ";

	titleVal += $('.activeClass1 a span').html() + "| 부산도시공사";
	
	$("title").html(titleVal);
	
});
</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if> 
<div id="contents">
	<div class="content">
		

		<div class="inr vision">
			<div style="float:right;">
			<p class="lawbtn_wrap">
			
			<!-- <a href="#" data-action="/bmc/user/inRealName.do?successUrl=/bmc/public/monitor.do&mId=0402000000" onclick= "req.post(this); return false;" class="buttonT3 icon down" ><span style="color:#ffffff;">사전정보 모니터링</span></a></p> -->
<c:choose>
<c:when test="${not empty user}">
<!-- 본인인증 정보존재 -->
<a title="사전정보 모니터링(건의함)" href="#" data-action="/bmc/public/monitor.do?mId=0402000000" onclick= "req.post(this); return false;" class="buttonT3 icon down" ><span style="color:#ffffff;">사전정보 모니터링(건의함)</span></a></p>
</c:when>
<c:otherwise>
<!-- 본인인증 정보없음 -->
<a title="사전정보 모니터링(건의함)" href="#" data-action="/bmc/user/inRealName.do?successUrl=/bmc/public/monitor.do&mId=0402000000" onclick= "req.post(this); return false;" class="buttonT3 icon down" ><span style="color:#ffffff;">사전정보 모니터링(건의함)</span></a></p>
</c:otherwise>
</c:choose>
			
			
			</div>
			<!-- <div class="area_box box2 area_box_color">
			<p><strong class="strong_st">
			부산도시공사는 국민의 알 권리 보장을 위하여 공사가 보유·관리하고 있는 주요 정보를 다음과 같이 공표하고 있습니다. <br>
            또는 국민의 알 권리 보장을 위하여 부산도시공사가 보유·관리하고 있는 정보를 사전에 적극적으로 공개하는 제도입니다. 
			
			</strong></p>
			</div> -->
			<div class="area_box box2">
				<p>
					<strong class="strong_st">공표목록별 조회순위</strong>
				</p>
					<div class="rank">
						<ul>
						<c:forEach items="${statsList}" var="sts">
							<li data-index="${sts.ranking}" ><a title="${sts.name}" href="${sts.link}"><c:out value="${sts.name}" escapeXml="false"/>(<span class="rank_data">${sts.cnt}</span>)</a></li>
						</c:forEach>
						</ul>
					</div>
				<p>
					<strong class="strong_st"></strong>
				</p>
			</div>
		</div>
		
		<div class="tabmenu" >
			<div class="guide_tab">
				<ul style="height:260px;">
					<li <c:if test="${publicVO.searchCatIdx eq 0}">class="activeClass1" </c:if> onclick="location.href='/bmc/public/list.do?mId=${menuVO.mId}&searchCatIdx=0'" style="height:130px;"><a href="javascript:void(0)" class="#tab0${cat.catIdx}" style="cursor: pointer;"><img src="/bmc/images/guide/guide_tap0_on.png" style="padding-top:15px;" alt=""><br><span>전체</span></a></li> 
					<c:forEach items="${catList}" var="cat">
						<li <c:if test="${publicVO.searchCatIdx eq cat.catIdx}">class="activeClass1"</c:if> onclick="location.href='/bmc/public/list.do?mId=${menuVO.mId}&searchCatIdx=${cat.catIdx}'" style="height:130px;"><a href="javascript:void(0)" class="#tab0${cat.catIdx}" style="cursor: pointer;"><img src="/bmc/images/guide/guide_tap${cat.catIdx}_on.png" style="padding-top:5px;" alt=""><br><span>${cat.catName}</span></a></li>	
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="gap"></div>
		<form:form commandName="publicVO" id="list" name="list" action="/bmc/public/list.do?mId=${menuVO.mId}&searchCatIdx=${searchCatIdx}&depart=${depart}" method="post">
			<form:hidden path="page"/>
			<div class="bod_head clFix" style="text-align:center">
			<fieldset class="bod_search" style="float:none;">	
				<legend class="blind">사전정보공표 검색</legend>
				<label for="searchType" class="blind">검색구분 선택 : </label>
				<%-- <label for="depart" class="blind">부서 선택 : </label>
				<form:select path="depart" title="부서 선택">
					<form:option value="">부서선택</form:option>
					<c:forEach items="${departList}" var="depart">
						<form:option value="${depart.depart}">${depart.depart}</form:option>
					</c:forEach>
				</form:select> --%>
				
				<form:select path="searchType" title="검색유형 선택">
					<form:option value="name">공표목록(업무)</form:option>
					<form:option value="article">공표항목(내용)</form:option>
					<form:option value="depart">담당부서</form:option>
					<%-- <form:option value="time">시기</form:option>
					<form:option value="cycle">주기</form:option> --%>
				</form:select>
				<label for="searchTxt" class="blind">검색어 입력: </label>
				<form:input path="searchTxt" title="검색어 입력"/>
				<input type="submit" value="검색" name="searchBt" title="검색" onclick="$('#page').val(1);"/>
			</fieldset>
			
			</div>
		</form:form>
		<div>
				<span><c:out value="총  ${listOrder}건 " /></span>
		</div>
		<div class="tab-contents" id="tab01">
			<div class="gap"></div>
			<p class="scroll_txt">좌우로 스크롤 하세요</p><br>
			<div class="orgTable_wrap">
				<table class="orgTable">
						<c:if test= "${publicVO.searchCatIdx eq 0}">
						<caption><span class="blind">사전정보공표 공표목록</span></caption></c:if>
						<c:if test= "${publicVO.searchCatIdx eq 1}">
						<caption><span class="blind">사전정보공표 감사·윤리정보 공표목록</span></caption></c:if>
						<c:if test= "${publicVO.searchCatIdx eq 2}">
						<caption><span class="blind">사전정보공표 기획·재정정보 공표목록</span></caption></c:if>
						<c:if test= "${publicVO.searchCatIdx eq 3}">
						<caption><span class="blind">사전정보공표 법무·노무정보 공표목록</span></caption></c:if>
						<c:if test= "${publicVO.searchCatIdx eq 4}">
						<caption><span class="blind">사전정보공표 인사·총무·회계정보 공표목록</span></caption></c:if>
						<c:if test= "${publicVO.searchCatIdx eq 5}">
						<caption><span class="blind">사전정보공표 정보화정보 공표목록</span></caption></c:if>
						<c:if test="${publicVO.searchCatIdx eq 6}">
						<caption><span class="blind">사전정보공표 홍보·대외협력정보 공표목록</span></caption></c:if>
						<c:if test= "${publicVO.searchCatIdx eq 7}">
						<caption><span class="blind">사전정보공표 기타정보 공표목록</span></caption></c:if>
						<c:if test="${publicVO.searchCatIdx eq 8}">
						<caption><span class="blind">사전정보공표 지정보수업체 정보 공표목록</span></caption></c:if>
					<colgroup>
						<col style="width:18%;">
						<col>
						<col style="width: 10%;">
						<col style="width: 10%;">
						<col style="width: 10%;">
						<col style="width: 11%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">공표목록</th>
							<th scope="col">공표항목</th>
							<th scope="col">공표주기</th>
							<th scope="col">공표시기</th>
							<th scope="col">담당부서</th>
							<th scope="col">공표매체</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${result}" var="d">
						<tr>
							<td><c:out value="${d.name}" escapeXml="false"/></td>
							<td><c:out value="${d.article}" escapeXml="false"/></td>
							<td>${d.cycle}</td>
							<td>${d.time}</td>
							<td style="background-color: #EFF7F9;">${d.depart}</td>
							<td><button title="${d.name} 새창 열림" class="btnmove1" onclick="javascript:fn_countStats('${d.pubIdx}', '${d.link}');">정보보기</button></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="gap"></div>
		<div class="bod_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
		</div>
	</div>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
