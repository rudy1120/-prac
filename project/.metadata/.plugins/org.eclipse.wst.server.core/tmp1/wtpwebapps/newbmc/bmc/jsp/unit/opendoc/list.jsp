<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<div id="contents">
	<div class="content">
	
		<div class="inr vision">
			<div class="area_box box2">
			<p>「공공기관의 정보공개에 관한 법률」(이하 정보공개법) 제8조(정보목록의 작성 • 비치 등) 및 제9조에 의거하여 우리 공사가 소유한 정보목록을 아래와 같이 공개합니다. </p>
			</div>
		</div>
	
		<form:form commandName="opendocVO" name="list" id="list" onclick="$('#page').val(1);" methodParam="post">
			<form:hidden path="page"/>
	
		<div class="bod_head clFix">
			<fieldset class="bod_search">
				<label for="searchTitle">문서제목 : </label>
				<form:input id="searchTitle" path="searchTitle" title="제목 입력" style="width:200px"/>
				<input type="submit" value="검색" name="searchBt" title="검색" />
				<div class="gap20"></div>
			</fieldset>
		</div>
		
		<div class="gap"></div>
		
		<table class="bod_list">
		<caption>번호, 문서제목, 담당부서, 생산일자, 공개여부로 구성된 결재 문서 표입니다.</caption>
			<thead>
				<tr>
					<th scope="col" class="w10">번호</th>
					<th scope="col">문서제목</th>
					<th scope="col" class="w25">담당부서</th>
					<th scope="col" class="w15">생산일자</th>
					<th scope="col" class="w10">공개여부</th>
				</tr>
			</thead>
			<c:forEach var="list" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td><a href="#" data-action="/bmc/opendoc/view.do?serialNo=${list.serialNo}" data-keyset="{'deptNm' : '${list.deptNm}', 'prodYear' : '${list.prodYear}'}" onclick="req.post(this); return false;">${list.docTitle}</a></td>
					<td>${list.docMgr}</td>
					<td>${list.regDate}</td>
					<td>${list.openYn}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty result}">
				<tr>
					<td colspan="5">조회할 데이터가 없습니다.</td>
				</tr>
			</c:if>
		</table>
		
		</form:form>
		
		<div class="gap"></div>
		<p class="page_num">
			현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
		</p>
		<div class="bod_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
		</div>
	</div>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>