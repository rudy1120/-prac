<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객참여이벤트 상세보기
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
	td {padding: 5px 30px;}
</style>

<script>
/*  if (document.location.protocol == 'http:') {
    document.location.href = document.location.href.replace('http:', 'https:');
}  */
</script>

<div id="contents"><div class="content">
<div class="gap40"></div>
<div class="bod_wrap">
	<div class="bod_view">
		<h4>${result.subject}
			<span style="color:green;">${result.procLvl eq 1 ? '진행중' : '결과발표'}</span>
		</h4>
		<div class="view_info">
			<ul>
				<li class="view_date"><span>기간</span> : ${result.exedate}</li>
				<li class="view_date"><span>발표일</span> : ${result.pubdate}</li>
			</ul>
		</div>
		<div class="view_cont">
			<div class="mT10">
				<c:set var="content" value="${fn:replace(result.content, '  ', '&nbsp;&nbsp;')}"/>
				${yh:nl2br(content)}
			</div>
			<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
				<c:param name="param_atchFileId" value="${result.attachFile}"/>
				<c:param name="mode" value="origin"/>
				<c:param name="insertBr" value="${true}"/>
			</c:import>
		</div>
	</div><hr />
	
	<c:choose>
		<c:when test="${result.procLvl eq 1}">
			<form:form commandName="eventParticipantVO" id="list" name="list" methodParam="post">
				<div class="btn_wrap btn_wrap2" style="text-align:center">
					<a href="#" data-action="/bmc/event/register.do?idx=${result.idx}" onclick="req.post(this); return false;">
						참가하기
					</a>&nbsp;&nbsp;
					<a href="#" data-action="/bmc/event/chkapply.do?idx=${result.idx}" onclick="req.post(this); return false;">
						응모내역
					</a>  
					<a href="#" data-action="/bmc/event/list.do?mId=${menuVO.mId}" onclick="req.post(this); return false;">
						목록
					</a>
				</div>
			</form:form>
		</c:when>
		
		<c:when test="${result.procLvl eq 2 && result.gubun eq 2}">
			<div class="gap"></div>
			<strong>[결과 발표]</strong>
			<div class="gap"></div>
			이벤트에 참여해주신 분들께 진심으로 감사드립니다.(신청인원 : ${result.parCnt}명)<div class="gap"></div>
			<table>
				<caption>이벤트에 당첨되신 분의 고객명, 전화번호입니다.</caption>
				<tr>
					<th scope="col">고객명</th>
					<th scope="col">전화번호</th>
				</tr>
				<c:forEach var="e" items="${list}" varStatus="status">
					<tr>
						<td>${e.usernm}</td>
						<td>${e.tel}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="btnall_wrap">
				<div class="btn_wrap">
					<input type="button" class="btn-blue" onclick="location.href='/bmc/event/list.do?mId=${menuVO.mId}'" value="목록"/>
				</div>
			</div>
		</c:when>
		<c:when test="${result.procLvl eq 2 && result.gubun eq 1}">
			<div class="gap"></div>
			<strong>[결과 발표는 개별 통지 예정입니다]</strong>
			<div class="gap"></div>
			이벤트에 참여해주신 분들께 진심으로 감사드립니다.(신청인원 : ${result.parCnt}명)<div class="gap"></div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div>
	
</div></div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
