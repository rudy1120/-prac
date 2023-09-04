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

<script>
function goSubmit(){
	if(!validator()){
		return;
	}
	confirm('등록하시겠습니까?');
	document.getElementById("list").submit();
}

</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<style>
	.red {color:red;}
</style>

<div id="contents"><div class="content">
<form:form commandName="receiptVO" id="list" name="list" action="/bmc/receipt/updateProc.do?mId=${menuVO.mId}" methodParam="post" enctype="multipart/form-data">
	<form:hidden path="idx"/>
	<!-- DUPINFO -->
	<input type="hidden" name="dupinfo" value="${user.privatecode}"/>
	<input type="hidden" name="usernm" value="${user.userName}"/>
	<input type="hidden" name="tel" value="${user.tel1}-${user.tel2}-${user.tel3}"/>
	<input type="hidden" name="sttidx" value="${result.sttidx}"/>

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
						<input type="hidden" name="gbn" value="${result.gbn}"/>
					</dd>
				</dl>
				
				<dl>
					<dt><label for="usernm">지원자</label></dt>
					<dd>
						<span class="spantxt">${user.userName}</span>

					</dd>
				</dl>
				
				<dl>
					<dt><label for="tel">지원자 핸드폰번호</label></dt>
					<dd>
						<span class="spantxt">${user.tel1}-${user.tel2}-${user.tel3}</span>
					</dd> 
				</dl>
				
				<dl>
					<dt><span>기존 입사지원서</span></dt>
					<dd>
						<c:set var="fileExts" value="${result.attachId}" scope="request"/>
						<c:if test="${!empty fileExts}">
							<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="showDownload" value="N"/>
								<c:param name="updateFlag" value="N"/>
								<c:param name="downloadYn" value="Y"/>
								<c:param name="param_atchFileId" value="${fileExts}"/>
							</c:import>
						</c:if>
						<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
					</dd>
				</dl>
				<dl>
					<dt><span class="red">*</span>입사지원서</dt>
					<dd>
						<input type="hidden" name="attachId" value="${receiptVO.attachId}" />
						<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${receiptVO.attachId}" />
							<c:param name="downloadYn" value="Y"/>
						</c:import>
						<input type="file" id="attachId_file" name="attachId_file" data-validator="on" data-required="y" data-fieldName="입사지원서" class="input300 mB10" value="" />
					</dd>
				</dl>  
		</fieldset>
	</div>
	
	<div class="btnall_wrap">
		<div class="btn_wrap">
			<div class="btn_in">
				<input id="submitBtn" type="button" onclick="goSubmit();" class="btn_blue" value="저장"/>
				<input type="button" class="btn-blue btntolist" onclick="location.href='/bmc/receipt/list.do?mId=${menuVO.mId}'" value="목록"/>
			</div>
		</div>
	</div>
</form:form>

</div></div>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>

<script>
	
	$(function() {

		//[2019.11.20.] 키보드 보안모듈
// 		$("#userAgent").text(navigator.userAgent);
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
// 		npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
		
	});
	
</script>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>


