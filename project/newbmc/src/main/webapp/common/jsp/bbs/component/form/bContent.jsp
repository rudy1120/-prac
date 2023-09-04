<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 내용 ============================== --%>
<dl>
	<dt><span class="red" title="필수입력">*</span><label for="bContent">내용</label></dt>
	<dd>
		<form:textarea path="bContent" class="w90" cols="30" rows="20" data-validator="on" data-required="y" data-fieldName="내용"/>
	</dd>
</dl>
