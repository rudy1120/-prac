<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객참여이벤트 사용자 목록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.09.03		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.09.03
 */
%>
<style>
	.listPlan .item .cont .info dl {
		background-position: 0% 40%;
	}
	.longdd {
		width:400px;
		line-height:1.7;
	}
</style>

<form:form commandName="eventContentVO" name="list" id="list" onclick="$('#page').val(1);" methodParam="post">
	<form:hidden path="page"/>
	<div id="contents"><div class="content">
	<div class="gap20"></div>

	<div class="bod_head clFix">
		<p class="page_num">
			Total <em>${totalCnt}</em>건
			
		</p>
		<fieldset class="bod_search">
			<label for="searchType" class="blind">검색구분 선택 : </label>
			<form:select path="searchType" title="검색유형 선택">
				<form:option value="subject">제목</form:option>
			</form:select>
			<label for="searchTxt" class="blind">검색어 입력: </label>
			<form:input path="searchTxt" title="검색어 입력"/>
			<input type="submit" value="검색" name="searchBt" title="검색" />
		</fieldset>
	</div>
	<ul class="listPlan">
		<c:forEach var="element" items="${result}" varStatus="status">
			<li class="item">
				<div class="thumb">
					<button class="mapView" data-action="/bmc/event/view.do?idx=${element.idx}" onclick="req.post(this); return false;"> 
						
							<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${element.attachFile}"/>
								<c:param name="mode" value="ratio"/>
								<c:param name="width" value="250"/>
								<c:param name="height" value="220"/>
								<c:param name="all" value="${false}"/>
								<c:param name="showComment" value="${true}"/>
							</c:import>
						
					</button>
				</div>
				<div class="cont">
					<div class="info evinfo">
						<h4 class="tit"><a href="#" data-action="/bmc/event/view.do?idx=${element.idx}" onclick="req.post(this); return false;" class="tit_underline">${element.subject}</a></h4>
						<dl class="addr">
							<dt>기간</dt>
							<dd>${element.exedate}</dd>
						</dl>
						<dl class="size">
							<dt>발표일</dt>
							<dd>${element.pubdate}</dd>
						</dl>
						<dl class="date">
							<dt>내용</dt>
							<dd class="longdd">${element.headcomment}</dd>
						</dl>
						<dl class="state">
							<dt>진행현황</dt>
							<dd class="status-ing">${element.procLvl eq 1 ? '진행중' : '결과발표'}</dd>
						</dl>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>
	
	<c:if test="${empty result}">
		<li class="no_data"><span>등록된 게시물이 없습니다.</span></li>
	</c:if>
</div></div>
</form:form>
	
<c:if test="${not empty result}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
	</div>
</c:if>


<script type="text/javascript" src="/common/js/commonProcess.js"></script>
