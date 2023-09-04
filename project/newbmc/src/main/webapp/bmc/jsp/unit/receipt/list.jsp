<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 사용자 리스트 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
%>

<style>
	
	/* .pull-right{float:right!important; font-size:30px;}
	.btntolist{margin:30px 0; position:absolute; top:0; right:0;} */
	.label{background: #026273; color:#fff; padding: 1.2rem 1.5rem; position: absolute; bottom: 0; left: 0;} 

</style>
<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>

<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>
<jsp:useBean id="currTime" class="java.util.Date" />
<fmt:formatDate var="currTime" value="${currTime}" pattern="yyyy-MM-dd HH:mm:ss" />

<div id="contents"><div class="content">

<div class="gap20"></div>

<form:form commandName="receiptContVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);">
	<form:hidden path="page" />
	
		<div class="bod_head clFix">
			<p class="page_num">
				현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
			</p>
			<fieldset class="bod_search">
				<label for="searchType" class="blind">검색구분 선택 : </label>
				<form:select path="searchType" title="검색유형 선택">
					<form:option value="title">제목</form:option>
					<form:option value="cont">내용</form:option>
				</form:select>
				<label for="searchTxt" class="blind">검색어 입력: </label>
				<form:input path="searchTxt" title="검색어 입력"/>
				<input type="submit" value="검색" name="searchBt" title="검색" />
			</fieldset>
		</div>
	
		<div class="bod_photo">
		<ul class="clFix">
			<c:forEach var="element" items="${result}">
			<li> 
				<c:set var="deadline" value="${currTime < element.edate ? 1 : 2}" />
				<c:set var="deadline" value="${currTime < element.sdate ? 3 : deadline}" />
				<a href="#" data-action="/bmc/receipt/view.do?idx=${element.idx}" onclick="req.post(this); return false;">
					<div class="thumb">
						<figure>
							<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${element.thumbnail}"/>
								<c:param name="mode" value="ratio"/>
								<c:param name="width" value="250"/>
								<c:param name="height" value="220"/>
								<c:param name="all" value="${false}"/>
								<c:param name="showComment" value="${true}"/>
							</c:import>
						</figure>
						<span class="label label-end">
							<span>
							<c:choose>
								<c:when test="${deadline eq 2}">마감</c:when>
								<c:when test="${deadline eq 3}">접수예정</c:when>
								<c:otherwise>접수중</c:otherwise>
							</c:choose>
							</span>
						</span>
					</div>
					<div class="cont">
						<span class="tit">${element.title}</span>
						<span class="date">부산도시공사</span>
					</div>
				</a>
			</li>
		</c:forEach>
		<c:if test="${empty result}">
			<li class="no_data"><span>등록된 게시물이 없습니다.</span></li>
		</c:if>
		</ul>
	</div>	
	
</form:form>	
	
<div class="bod_page">
	<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
</div>

</div></div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>

