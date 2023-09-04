<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 첨부 파일 ============================== --%>
<dl>
	<dt><c:if test="${isPhoto}"><span class="red" title="필수 입력">*</span></c:if><label for="egovComFileUploader">첨부 파일</label></dt>
	<dd>
		<p class="orange">※ 파일은 최대 ${bbsConfigVO.ptFileCnt}개까지 첨부하실 수 있습니다. 첨부 가능한 하나의 파일 사이즈는 최대 ${bbsConfigVO.ptLimitFileSize}mb입니다.</p>
		<c:set var="param_maxFileNum" value="${bbsConfigVO.ptFileCnt}" scope="request"/>
		<c:set var="param_attachId" value="${bbsVO.attachId}" scope="request"/>
		<jsp:include page="/common/jsp/component/fileAttachForm.jsp"/>
		<form:hidden path="attachId"/>

		<c:if test="${isPhoto}">
			<input type="hidden" data-validator="on" data-inputType="file" data-fileType="img"/>
		</c:if>
		<c:if test="${isMovie}">
			<input type="hidden" data-validator="on" data-inputType="file" data-fileType="movie"/>
		</c:if>
	</dd>
</dl>
