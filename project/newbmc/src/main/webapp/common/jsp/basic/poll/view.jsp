<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 설명
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.10.10		권태성			최초 생성 및 코드 수정
	 * 2019.05.29		김선영			마크업 수정
	 *
	 * @author 권태성
	 * @since 2017.10.10
	 */
%>

<style>
.clFix dt, .clFix dd {
	margin-bottom : 5px;
}
</style>
<div id="contents"><div class="content">
<form:form commandName="searchVO" name="list" id="list" method="post">
	<form:hidden path="page" />
	<form:hidden path="idx" />
</form:form>

<div class="poll_view">
	<h4 id="pollTitle">${poll.title}</h4>
	<div class="info">
		<figure>
			<c:if test="${not empty poll.mainFile}">
				<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${poll.mainFile}"/>
					<c:param name="mode" value="ratio"/>
					<c:param name="width" value="360"/>
					<c:param name="height" value="245"/>
				</c:import>
			</c:if>
			<c:if test="${empty poll.mainFile}">
				<img src="/common/img/board/poll_noimg.jpg" alt="BMC 부산도시공사 고객 설문조사.RESEARCH" />
			</c:if>
		</figure>
		<div class="txtBox1 txtBox2 txtBox3"><ul>
			<li class="ico1"><dl>
				<dt style="float:none">설문조사기간</dt>
				<dd>${poll.startDate} ~ ${poll.endDate}</dd>
			</dl></li>
			<li class="ico1"><dl>
				<dt style="float:none">참여대상</dt>
				<dd>${poll.joinTarget}
				<c:if test="${poll.joinAgeYn eq 'Y'}">
					<%-- &nbsp;중 --%>&nbsp;${poll.joinStAge}년&nbsp;~&nbsp;${poll.joinEdAge}년생 ${poll.joinGender eq 'M' ? '(남성) ' : (poll.joinGender eq 'W' ? '(여성) ' : '')}대상
				</c:if></dd>
			</dl></li>
			<li class="ico1"><dl>
				<dt style="float:none">상태</dt>
				<dd>${poll.stateYn eq 'N' ? '설문 중단' : poll.stateFlag }</dd>
			</dl></li>
			<li class="ico1"><dl>
				<dt style="float:none">참여자 현황</dt>
				<dd>${poll.userCnt}명</dd>
			</dl></li>
			<li class="ico1"><dl>
				<dt style="float:none">결과 공개여부</dt>
				<dd>${poll.publicYn eq 'Y' ? '공개' : '비공개'}</dd>
			</dl></li>
		</ul></div>
	</div>
	<div class="tit">
		<figure>
			<c:if test="${not empty poll.headerFile}">
				<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${poll.headerFile}"/>
					<c:param name="mode" value="origin"/>
				</c:import>
			</c:if>
		</figure>
		 ${yh:nl2br(poll.header)}
	</div>
	<%-- 첨부파일 다운로드 필요하여 idx 234인 경우에만 임시방편 설정 --%>
		<c:if test="${poll.idx eq '234'}">
				<div class="gap"></div>
				<div style="text-align:right;">
				<a style="display: inline-block;background: #02768a;color: #fff;padding: 1.5rem 2.4rem;"href="/FileDown_direct.do?file=brochure.pdf">브로슈어 다운로드</a>
				&nbsp;&nbsp;
				<a style="display: inline-block;background: #02768a;color: #fff;padding: 1.5rem 2.4rem;"href="/FileDown_direct.do?file=leaflet.pdf">리플렛 다운로드</a>
				</div>
		</c:if>
		<c:if test="${poll.idx eq '236'}">
				<div class="gap"></div>
				<div style="text-align:right;">
				<a style="display: inline-block;background: #02768a;color: #fff;padding: 1.5rem 2.4rem;"href="/FileDown_direct.do?file=BMC_2023_brochure.pdf">브로슈어 다운로드</a>
				&nbsp;&nbsp;
				<a style="display: inline-block;background: #02768a;color: #fff;padding: 1.5rem 2.4rem;"href="/FileDown_direct.do?file=BMC_2023_leaflet.pdf">리플렛 다운로드</a>
				</div>
		</c:if>
	<%-- 끝 --%>	
	<h5 class="mT30">개인정보 수집 및 이용 동의</h5>
	<div class="agree_wrap">
		<div>
			<dl class="clFix">
				<c:choose>
					<c:when test="${poll.idx eq '240'}">
						<dt>개인정보 수집 항목</dt>
						<dd>IP주소, 성명, 휴대전화번호</dd>
						<dt>개인정보 수집 목적</dt>
						<dd>설문조사 관리, 당첨자 선정 및 경품 발송</dd>
						<dt>개인정보 보유 기간</dt>
						<dd>설문조사 완료 후 30일까지</dd><%-- 개인 정보 보유 기간은 해당 기관에 맞춰서 수정하여 사용 --%>
					</c:when>
					<c:otherwise>
						<dt>개인정보 수집 항목</dt>
						<dd>${poll.dupType eq '1' or poll.dupType eq '2' or poll.dupType eq '3' ? 'IP 주소' : '이름, 생년월일, 성별, 연락처, IP 주소'}</dd>
						<dt>개인정보 수집 목적</dt>
						<dd>설문조사 관리</dd>
						<dt>개인정보 보유 기간</dt>
						<dd>설문 완료일로 부터 1년</dd><%-- 개인 정보 보유 기간은 해당 기관에 맞춰서 수정하여 사용 --%>
					</c:otherwise>
				</c:choose>
			</dl>
			<div class="exp">
				위의 개인정보 수집 &middot; 이용에 대한 동의를 거부할 권리가 있습니다.<br>그러나 동의를 거부할 경우, 원활한 서비스 제공에 일부 제한을 받을 수 있습니다.
			</div>
			<div class="choose">
				<span>개인정보의 수집 및 이용목적에 동의합니다.</span>
				<fieldset>
					<legend>동의 미동의 버튼</legend>
					<input type="radio" name="agree" id="agree1" value="Y" /><label for="agree1" class="mR10">동의</label>
					<input type="radio" name="agree" id="agree2" value="N" checked="checked" /><label for="agree2">미동의</label>
				</fieldset>
			</div>
		</div>
	</div>
</div>
<div class="btn_wrap taR mT20 btn_wrap2">
	<%-- 쿠키 & IP체크의 경우 설문 참여화면으로 이동 --%>
	<a data-action="${_context}/${siteCodeFull}/poll/apply.do?mId=${curMid}" data-formname="list" href="#">설문참여하기</a>
	<%-- 본인인증 체크의 경우 인증받고 설문 참여화면으로 이동 --%>
	<a data-action="${_context}/${siteCodeFull}/poll/list.do?mId=${curMid}" data-formname="list" onclick="req.post(this); return false;" href="#">목록</a>
	<c:if test="${not empty yh:getUser()}">
		<a href="/logout?successUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="cancel" title="본인 인증 해제">인증 취소</a>
	</c:if>
</div>
</div></div>
<script type="text/javascript" src="${_context}/common/js/commonProcess.js"></script>
<script type="text/javascript">
	$(".btn_wrap > a:first").click(function() {
		if ($("input[name='agree']:checked").val() == "N") {
			alert("개인정보 이용에 동의하셔야 참여할 수 있습니다.");
			$("#agree1").focus();
		} else {
			req.post(this);
		}
		return false;
	});
</script>
<script>



$(function(){
	//20.04.09 웹접근성 반영 - 각페이지 title 정보 수정 
	var titleVal = "개인정보 수집 및 이용동의 | " + $("#pollTitle").html().trim() + " | " + $("title").html();
	$("title").html(titleVal);
	
});
</script>
