<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 비밀글 ============================== --%>
<c:if test="${isRootArticle && usePublic}">
	<dl>
		<dt><span class="red" title="필수 입력">*</span>비밀글</dt>
		<dd>
			<c:if test="${empty searchVO.bPublic}">
				<form:radiobutton path="bPublic" id="bPublicY" value="Y" checked="checked" label="공개"/>
			</c:if>
			<c:if test="${not empty searchVO.bPublic}">
				<form:radiobutton path="bPublic" id="bPublicY" value="Y" label="공개"/>
			</c:if>
			<form:radiobutton path="bPublic" id="bPublicN" value="N" label="비공개"/>
		</dd>
	</dl>
</c:if>
