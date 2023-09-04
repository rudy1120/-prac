<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 비밀글 ============================== --%>
<c:if test="${isRootArticle && usePublic}">
	<tr>
		<th scope="row"><span class="red">*</span><label for="bPublic">공개 여부</label></th>
		<td>
			<c:if test="${empty searchVO.bPublic}">
				<form:radiobutton path="bPublic" id="bPublicY" value="Y" checked="checked" label="공개"/>
			</c:if>
			<c:if test="${not empty searchVO.bPublic}">
				<form:radiobutton path="bPublic" id="bPublicY" value="Y" label="공개"/>
			</c:if>
			<form:radiobutton path="bPublic" id="bPublicN" value="N" label="비공개"/>
		</td>
	</tr>
</c:if>
