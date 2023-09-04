<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 납부금조회 메인 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.21		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.10.21
 */
%>

 <script>
//  if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// } 
$(function(){
	var urlValue = window.location.href;
	
	if(urlValue.indexOf(0308010100) != -1){
		$("#subtitle").html("납부금 조회신청 정보-임대관련");
	}else{
		$("#subtitle").html("납부금 조회신청 정보-분양관련");
	}
});
</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<form:form commandName="lookCustomPayVO" id="list" name="list" action="/bmc/look/result.do?mId=${menuVO.mId}" onsubmit="return validator() && confirm('조회하시겠습니까?');" methodParam="post" htmlEscape="false">

<input type="hidden" name="name" value="${user.userName}"/>
<input type="hidden" name="juminId" value="${fn:substring(user.birthday, 2, 8)}"/>
<input type="hidden" name="tel" value="${user.tel1}-${user.tel2}-${user.tel3}"/>

<div id="contents"><div class="content">
<div class="gap20"></div>
<div class="subSec cpProfile clearfix">
	<div class="ceoInfo aniBox active">
		<div class="txtBox">
			<strong>
				빠르고 편리한 부산도시공사의 인터넷 납부금 조회
			</strong>
		</div>	
	</div>
</div>
<div class="gap"></div>
<p>본 서비스를 통해 고객님의 계약번호로 기준일까지의 체납정보를 즉시 확인하실 수 있습니다.</p>


<c:choose>
	<c:when test="${not empty user}"> 
		<div class="gap20"></div>
		<p class="depth2-title" id="subtitle"></p>
		<!-- 본인인증 정보존재 -->
		<div class="bod_wrap">
			<fieldset>
				<div class="bod_write">
					<dl>
						<dt><label for="name">이름</label></dt>
						<dd>
						 <span class="spantxt">${user.userName}</span> 
						<%-- <form:input path="name" maxlength="50" data-validator="on" data-required="y" data-fieldName="이름"/>  --%>
						</dd>
					</dl>
					<dl>
						<dt><label for="juminId">생년월일</label></dt>
						<dd>
						 <span class="spantxt">${fn:substring(user.birthday, 2, 8)}</span>
							<%-- <form:input path="juminId" maxlength="50" data-validator="on" data-inputType="birth" data-required="y" data-fieldName="생년월일" />&nbsp;&nbsp; --%>
							<!-- <span class="tip">앞자리 6자리를 입력해주세요.</span> -->
						</dd>
					</dl>
					<dl>
						<dt><label for="tel">휴대폰번호</label></dt>
						<dd>
						<span class="spantxt">${user.tel1}-${user.tel2}-${user.tel3}</span>
							<%--  <form:input path="tel" maxlength="50" data-validator="on" data-inputType="tel" data-required="y" data-fieldName="휴대폰번호" />&nbsp;&nbsp;
							<span class="tip">010-1234-1234 형식으로 입력해주세요.</span>  --%>
						</dd>
					</dl>
				</div>
			</fieldset>
		</div>
		<div class="btnall_wrap">
			<div class="btn_wrap">
				<div class="btn_in">
					<input id="submitBtn" type="submit" class="btn_blue" value="조회"/>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="gap20"></div>
		<!-- 본인인증 정보없음 -->
	<div class="orgTable_wrap jobTable_wrap">
		<p class="depth2-title">고객정보</p>
		<c:if test="${menuVO.mId eq '0308010100'}">
			<table class="orgTable jobTable">
		<caption>고객정보</caption>
		<tbody>
			<tr>
				<th style="width: 10%">이름</th>
				<th>주택명</th>
				<th style="width: 15%">최종계약일</th>
				<th style="width: 15%">임대보증금</th>
				<th style="width: 15%">월임대료</th>
			</tr>
	
		</tbody>
	</table>
</div>
<div class="gap20"></div>
	<p class="depth2-title">${lookVO.name} ○○○고객님의 임대료 가상계좌번호는 ○○○○○○○○ 입니다. </p>
	<p class="depth2-sub">※ 임대보증금 납부 가상계좌와 상이하니 참고 부탁드립니다.
	<div class="gap20"></div> 
	<p class="depth2-title">납부금정보</p>
	<div class="orgTable_wrap jobTable_wrap">
		<table class="orgTable jobTable">
			<caption>납부금정보</caption>
			<tbody>
				<tr>
					<th>과목</th>
					<th>납입기한</th>
					<th>당월임대료</th>
					<th>체납금액</th>
					<th>연체이자</th>
					<th class="dwelling">*주거급여예정액</th>
					<th>배상금</th>
					<th>**총납부하실금액</th>
				</tr>
			
				
			
			</tbody>
		</table> 
		
	</c:if>
		
	<c:if test="${menuVO.mId eq '0308010200'}">
			<table class="orgTable jobTable">
				<caption>고객정보</caption>
				<tbody>
					<tr>
						<th style="width: 10%">성명</th>
						<th>주소</th>
						<th style="width: 30%">분양물건</th>
						<th style="width: 15%">계약일</th>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div class="gap20"></div>
		
			<p class="depth2-title">○○○고객님의 가상계좌번호는 ○○○○○○○○ 입니다. </p>
			<div class="gap20"></div>
			<p class="depth2-title">납부금정보</p>
			<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
					<caption>납부금정보</caption>
					<tbody>
						<tr>
							<th>납입기한</th>
							<th>차수</th>
							<th>미납원급</th>
							<th>연체이자</th>
							<th>소계</th>
						</tr>
						
					
					</tbody>
				</table> 	
	</c:if>
	
	</div>
		<div class="gap20"></div>
		<div class="btn_wrap btn_wrap2">
			<a href="/bmc/user/inRealName.do?successUrl=/bmc/look/retrieve.do&mId=${menuVO.mId}&sAuthType=M" onclick="req.post(this); return false;">납부금 조회 화면 이동</a>
		</div>
	</c:otherwise>
</c:choose> 

</div></div>
</form:form>


<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
