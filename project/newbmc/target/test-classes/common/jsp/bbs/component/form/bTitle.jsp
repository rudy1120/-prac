<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 제목 ============================== --%>
<dl>
	<dt><span class="red" title="필수입력">*</span><label for="bTitle">제목</label></dt>
	<dd>
		<form:input path="bTitle" cssClass="w90" maxlength="${bbsConfigVO.ptLimitTitLen}" data-validator="on" data-required="y" data-fieldName="제목"/>
	</dd>
</dl>
