<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/sys/jsp/bbs/bbsMng/component/variables.jsp" %>
<%
	/**
	 * 게시글 등록/수정
	 *
	 * 작업 가이드:
	 * 1. 변수는 최상단에 scope="request"로 작업 OR /sys/jsp/bbs/bbsMng/component/variables.jsp에 추가(공통 변수의 경우)
	 * 2. 입력 항목이 늘어나는 경우는 /sys/jsp/bbs/bbsMng/component/form/ 밑에 jsp를 추가해 작업
	 * 3. 해당 파일은 /common/jsp/bbs/write.jsp에 노출되는 것과 동일해야 함
	 * 4. label 및 웹접근성 준수 추천
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.04.26		J.Ryeon Lee		최초 생성 및 코딩 (타입별로 분리되어 있던 jsp 통합)
	 *
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.04.26
	 */
%>
<h2>${bbsConfigVO.ptTitle}</h2>
<form:form commandName="searchVO" name="list" id="list" action="/sys/${siteCode}/bbs/bbsMng/${isNew ? 'list' : 'view'}.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchDelete"/>
	<form:hidden path="searchCategory"/>
</form:form>
<%-- <form:form commandName="bbsMngVO" name="detailForm" id="detailForm" action="/sys/${siteCode}/bbs/bbsMng/${isNew ? 'writeProc' : 'modifyProc'}.do?mId=${menuVO.mId}" onsubmit="return validator() && bbs.sys_additional_validator() && bbs.sys_additional_warning();" methodParam="post" enctype="multipart/form-data"> --%>
<form:form commandName="bbsMngVO" name="detailForm" id="detailForm" action="/sys/${siteCode}/bbs/bbsMng/${isNew ? 'writeProc' : 'modifyProc'}.do?mId=${menuVO.mId}" onsubmit="return validator() && bbs.sys_additional_validator();" methodParam="post" enctype="multipart/form-data">
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>
	<form:hidden path="writeId"/>
	<form:hidden path="bPrivatecode"/>
	<input type="hidden" name="TOKEN_KEY" value="${TOKEN_KEY}"/>

	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<caption>게시글 등록</caption>
		<colgroup>
			<col span="1" style="width:20%;"/>
			<col span="1"/>
		</colgroup>
		<tbody>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bTitle.jsp" %> <%-- 게시글 제목 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bWrite.jsp" %> <%-- 작성자 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bDept.jsp" %> <%-- 부서 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bCategory.jsp" %> <%-- 분류 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bPublicYn.jsp" %> <%-- 비밀글 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bAddr.jsp" %> <%-- 주소 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bPhone.jsp" %> <%-- 휴대전화 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bTermYn.jsp" %> <%-- 게시기간 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bNtermYn.jsp" %> <%-- 공지기간 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bCalendar.jsp" %> <%-- 달력형게시판 필드 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/optionalFields.jsp" %> <%-- 추가 옵션 필드 --%>
			<c:if test="${isMovie}">
				<%@include file="/sys/jsp/bbs/bbsMng/component/form/bYoutube.jsp" %> <%-- 유튜브 영상 ID --%>
			</c:if>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bContent.jsp" %> <%-- 내용 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bFile.jsp" %> <%-- 첨부 파일 --%>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bPassword.jsp" %> <%-- 비밀번호 --%>
			<c:if test="${bbsConfigVO.ptIdx eq '768' || bbsConfigVO.ptIdx eq '769'}">
				<%@include file="/sys/jsp/bbs/bbsMng/component/form/bSms.jsp" %> <%--  문자서비스 --%>
			</c:if>
		</tbody>
	</table>
	<%@include file="/sys/jsp/bbs/bbsMng/component/form/bNuriType.jsp" %> <%-- 공공누리 유형 --%>
	
	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" id="subtn" value="저장"/>
		<a href="#" onclick="document.getElementById('list').submit(); return false;" class="btn_dblue">취소</a>
	</div>

</form:form>

<!--  웹필터 수정 -->  
<%@ include file="/webfilter/inc/initCheckWebfilter.jsp"%> 
<!--  웹필터 수정 -->
<script>
$(function(){
	
	$('#subtn').click(function(e) {
		
		//-- 스마트 에디터 내용 삽입 --//
		oEditors.getById["bContent"].exec("UPDATE_CONTENTS_FIELD", []);
		
		<c:if test="${isRootArticle && useTerm}">
			if ((($('#bTermSdate').val().length == 0 && $('#bTermStime').val().length == 0) || (
					$('#bTermSdate').val().length > 0 && $('#bTermStime').val().length > 0)) && (
							($('#bTermEdate').val().length == 0 && $('#bTermEtime').val().length == 0) || (
									$('#bTermEdate').val().length > 0 && $('#bTermEtime').val().length > 0)) 
			) {
					
			} else {
				alert("게시기간의 날짜와 시간을 확인해주세요.");
				return false;
				e.preventDefault();
			}
		</c:if>
	});
});
</script>



<script type="text/javascript">
	yh.siteCodeFull = "${siteCodeFull}";
	yh.ptIdx = "${bbsConfigVO.ptIdx}";
	yh.bIdx = "${searchVO.bIdx}";
	yh.isNew = eval("${isNew}");
	yh.formId = "detailForm";
	yh.formName = "detailForm";
	yh.ptCheckWord = "${bbsConfigVO.ptCheckWord}";
	yh.isPhoto = eval("${isPhoto}");
	yh.isMovie = eval("${isMovie}");
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/bbs/etc.js"></script>
<script type="text/javascript" src="/sys/js/nuri.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/jquery/jquery-confirm.css" />
<script type="text/javascript" src="/common/js/jquery/jquery-confirm.js"></script>

