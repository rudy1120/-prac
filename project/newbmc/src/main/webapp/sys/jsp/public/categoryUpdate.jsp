<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<c:set var="isNew" value="${empty publicVO.catIdx}"/>
<c:set var="postUrl" value="${isNew ? 'categoryWriteProc' : 'categoryModifyProc'}"/>
<form:form commandName="publicVO" id="cancel" name="cancel"  methodParam="post" action="/sys/public/categoryList.do?mId=${menuVO.mId}">
</form:form>

<form:form commandName="publicVO" id="modify" name="modify" action="/sys/public/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false">
	<form:hidden path="catIdx" />

	<h2>카테고리 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="catName">카테고리명</label></th>
				<td>
					<form:input path="catName" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="카테고리명"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="orderNum">정렬순서</label></th>
				<td>
					<form:input path="orderNum" maxlength="10" cssClass="input100" data-inputType='num' data-fieldName="정렬순서"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="useYn">사용여부</label></th>
				<td>
					<form:select path="useYn" cssClass="input150" data-validator="on" data-required="y" data-fieldName="사용여부">
						<form:option value="Y">사용</form:option>
						<form:option value="N">미사용</form:option>
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script type="text/javascript">
	yh.siteCode = "sys";
	yh.isNew = eval("${isNew}");
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
