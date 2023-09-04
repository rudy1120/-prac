<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 공급용도 관리자 수정 및 등록 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.18		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.18
 */
%>
<script type="text/javascript">
$(document).ready(function () {
	
	
	if($("#codename").val()!=""){
		$("#codename").attr("readonly",true); 
	}
	
});
</script>
<c:set var="isNew"   value="${searchVO.idx eq 0}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>

<form:form commandName="searchVO" id="cancel" name="cancel"  methodParam="post" action="/sys/buy/uses/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="searchVO" id="modify" name="modify" action="/sys/buy/uses/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<h2>공급용도 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="title">공급용도명</label></th>
				<td>
					<form:input path="codename" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="공급용도명"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="title">사용여부</label></th>
				<td>
					<form:radiobutton id="useYN1" path="useYN" value="Y" checked="checked"/><label for="useYN1">사용</label>
					<form:radiobutton id="useYN2" path="useYN" value="N" /><label for="useYN2">미사용</label>
				</td>
			</tr>
			
		</tbody>
	</table>
	
	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script src="${pageContext.request.contextPath}/sys/js/tinymce/tinymce.min.js"></script>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<link   rel="stylesheet"       type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
<script type="text/javascript" src="/common/js/bbs/etc.js"></script>
<script type="text/javascript" src="/sys/js/nuri.js"></script>
<link   rel="stylesheet"       type="text/css" href="/common/js/jquery/jquery-confirm.css" />
<script type="text/javascript" src="/common/js/jquery/jquery-confirm.js"></script>
<script type="text/javascript" src="/sys/js/basic/promotion.js"></script>
