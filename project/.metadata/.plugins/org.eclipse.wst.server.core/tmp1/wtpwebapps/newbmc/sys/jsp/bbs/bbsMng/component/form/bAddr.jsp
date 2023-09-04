<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 주소 ============================== --%>
<c:if test="${isMinwon}">
	<tr>
		<th scope="row"><label for="bAddr1">주소</label></th>
		<td>
			<form:input path="bAddr1" cssClass="input500" maxlength="200" data-validator="on" data-fieldName="주소" />
			<a href="#" onclick="addr.popup('#bAddr1', '#bAddr2'); return false;" class="btn_white" title="새창으로 열림">주소 검색</a>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="bAddr2">상세 주소</label></th>
		<td>
			<form:input path="bAddr2" cssClass="input500" maxlength="300" data-validator="on" data-fieldName="상세 주소" />
		</td>
	</tr>
	<script type="text/javascript" src="/common/js/address.js"></script>
</c:if>