<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 첨부 파일 ============================== --%>
<c:if test="${!isFAQ}">
	<tr>
		<th scope="row"><label for="egovComFileUploader">첨부 파일</label></th>
		<td>
			<c:set var="param_maxFileNum" value="${bbsConfigVO.ptFileCnt}" scope="request"/>
			<c:set var="param_attachId" value="${bbsMngVO.attachId}" scope="request"/>
			<jsp:include page="/sys/jsp/component/fileAttachForm.jsp"/>
			<form:hidden path="attachId"/>
			<c:if test="${isPhoto}">
				<input type="hidden" data-validator="on" />
			</c:if>
			<c:if test="${isMovie}">
				<input type="hidden" data-validator="on" data-inputType="file" data-fileType="movie"/>
			</c:if>
		</td>
	</tr>
</c:if>