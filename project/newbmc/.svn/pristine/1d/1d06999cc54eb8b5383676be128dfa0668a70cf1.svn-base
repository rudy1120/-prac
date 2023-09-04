<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객참여이벤트 등록 정보입력 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2021.12.14       손문배          소스작성 
 *
 * @author 손문배
 * @since 2021.12.14
 */
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String now = format.format(new Date());
%>

<div id="contents"><div class="content">
<form:form commandName="eventParticipantVO" id="list" name="list" action="/bmc/event/chkapply2.do?mId=${menuVO.mId}" onsubmit="return confirm('조회하시겠습니까?');" methodParam="post" enctype="multipart/form-data" htmlEscape="false">
	<form:hidden path="idx"/>	

<div class="bod_wrap">
	<fieldset>
	<div class="gap20"></div>
		<div class="bod_write">
			<dl>
				<dt><label for="subject">행사명</label></dt>
				<dd>
					${result.subject}
				</dd>
			</dl>
			<dl>
				<dt><label for="usernm">이름</label></dt>
				<dd>
					<form:input path="usernm" maxlength="50" data-validator="on" data-required="y" data-fieldName="이름"/>
				</dd>
			</dl>
			<dl>
				<dt><label for="tel">휴대폰</label></dt>
				<dd>
					<form:input path="tel" maxlength="50" data-validator="on" data-inputType="phone" data-required="y" data-fieldName="휴대전화" />&nbsp;&nbsp;
					<span class="tip">01012345678 형식으로 입력해주세요.</span>
				</dd>
			</dl>
				
		</div>
	</fieldset>
</div>

<div class="btnall_wrap">
	<div class="btn_wrap">
		<div class="btn_in">
			<input id="submitBtn" type="submit" class="btn_blue" value="조회하기"/>
			<input type="button" class="btn_blue" onclick="javascript:history.back()" title="이전 페이지로 이동" value="취소" />
		</div>
	</div>
</div>
</form:form>

</div></div>
 <script type="text/javascript" src="/common/js/address.js"></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>
<script>
$(document).ready(function () {
	//[2019.11.20.] 키보드 보안모듈
	$("#userAgent").text(navigator.userAgent);
	/*
	npPfsStartup(document.form1, false, true, true, "enc", "on");
	1. form 양식 : 기본값 DOM Document 상의 첫번째 form
	2. 개인방화벽 사용여부 : 기본값 false
	3. 키보드보안 사용여부 : 기본값 false
	4. 단말정보수집 사용여부 : 기본값 false
	5. 키보드보안 E2E 필드 설정 속성명 : 기본값 "enc"
	6. 키보드보안 E2E 필드 설정 속성값: 기본값 "on"
	부가적인 설정은(예, 설치확인 등) /pluginfree/js/nppfs-1.0.0.js를 수정하여 설정하십시오.
	*/
	npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
	
	
});
</script>