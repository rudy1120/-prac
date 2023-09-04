<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 휴대전화 ============================== --%>
<c:if test="${isMinwon}">
	<c:set var="bPhoneRequired" value="${fn:contains(bbsConfigVO.ptSaveCols, 'bPhone')}"/>
	<dl>
		<dt><c:if test="${bPhoneRequired}"><span class="red" title="필수 입력">*</span></c:if><label for="bPhone">휴대전화</label></dt>
		<dd>
			<form:input path="bPhone" cssClass="wp30" maxlength="50" data-validator="on" data-inputType="phone" data-required="${bPhoneRequired ? 'y' : 'n'}" data-fieldName="휴대전화"/>
			<span class="orange">예) 010-0000-0000</span>
		</dd>
	</dl>
</c:if>
