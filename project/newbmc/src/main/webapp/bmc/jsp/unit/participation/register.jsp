<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%/**
 * 고객경영참여 참가자 등록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String now = format.format(new Date());
%>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<style>
	.red {color:red;}
</style>

<div id="contents"><div class="content">
<form:form commandName="participantVO" id="list" name="list" action="/bmc/participation/registerProc.do?mId=${menuVO.mId}" onsubmit="return validator() && confirm('등록하시겠습니까?') && alert('등록되었습니다.');" methodParam="post" enctype="multipart/form-data">
	<form:hidden path="idx"/>
	<!-- DUPINFO -->
	<input type="hidden" name="dupinfo" value="${user.privatecode}"/>
	<input type="hidden" name="usernm" value="${user.userName}"/>

	<div class="bod_wrap">
		<fieldset>
			<legend class="hidden">${result.gbn}</legend>
			<p class="taR pB5">(<span class="red">*</span>)표시는 필수 입력 사항입니다.</p>
			<div class="gap"></div>
			
			<div class="bod_write">
				<dl>
					<dt><label for="title">제목</label></dt>
					<dd>
						<span class="spantxt">${result.title}</span>
					</dd>
				</dl>
				
				<dl>
					<dt><label for="usernm"><span class="red">*</span>이름</label></dt>
					<dd>
						<span class="spantxt">${user.userName}</span>

					</dd>
				</dl>
				
				<dl>
					<dt><label for="email"><span class="red">*</span>이메일</label></dt>
					<dd>
						<form:input path="email" cssClass="w90" maxlength="100" data-validator="on" data-inputType="email" data-required="y" data-fieldName="이메일"/><br/>
						<span class="spantxt spantxt2">이메일 형식으로 입력해주세요.</span>
					</dd>
				</dl>
				
				<dl>
					<dt><label for="tel"><span class="red">*</span>휴대폰</label></dt>
					<dd>
						<form:input path="tel" maxlength="50" data-validator="on" data-inputType="phone" data-required="y" data-fieldName="휴대전화" />&nbsp;&nbsp;
						<span class="tip spantxt2">01012345678 형식으로 입력해주세요.</span>
					</dd>
				</dl>
				
				<dl>
					<dt><label for="addr1">주소</label></dt>
					<dd>
						<form:input path="addr1" cssClass="w70" maxlength="200" data-validator="on" data-fieldName="주소" readonly="true" onclick="addr.popup('#addr1', '#addr2'); return false;"/>
						<button onclick="addr.popup('#addr1', '#addr2'); return false;" title="도로명 주소 검색 사이트를 새창으로 표시" class="btnmove btntolist">주소검색</button>
					</dd>
				</dl>
				<dl>
					<dt><label for="addr2">상세주소</label></dt>
					<dd>
						<form:input path="addr2" cssClass="w70" maxlength="300" data-validator="on" data-fieldName="상세 주소"/>
					</dd>
				</dl>
				<script type="text/javascript" src="/common/js/address.js"></script> 	

				<dl>
					<dt><span class="red">*</span>신청서 첨부</dt>
					<dd>
						<input type="file" id="attachId" name="attach_file" data-validator="on" value="" data-fileType="doc"/>
					</dd>
				</dl>
			</div>
		</fieldset>
	</div>
	
	<div class="btnall_wrap">
		<div class="btn_wrap">
			<div class="btn_in">
				<input id="submitBtn" type="submit" class="btn_blue" value="저장"/>
				<input type="button" class="btn-blue btntolist" onclick="location.href='/bmc/participation/list.do?mId=${menuVO.mId}'" value="목록"/>
			</div>
		</div>
	</div>
</form:form>

</div></div>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>

<script>
	
	$(function() {
		$('#submitBtn').click(function() {
			var fileCheck = document.getElementById("attachId");	
				if(!fileCheck){
					alert("신청서를 첨부해주세요.");
					return false;
				}
			});
		
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




