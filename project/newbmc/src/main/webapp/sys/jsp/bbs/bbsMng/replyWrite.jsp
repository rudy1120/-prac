<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/sys/jsp/bbs/bbsMng/component/variables.jsp" %>
<%
	/**
	 * 답변/답글 등록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.11.14		김혜민			최초 생성 및 코드 작성
	 * 2015.07.29		J.Ryeon Lee		답변 비공개 등록 불가 처리, 답글 공지 불가 처리
	 * 2017.05.15		J.Ryeon Lee		등록/수정 통합, 코드 정리
	 *
	 *
	 * @author 김혜민
	 * @since 2014.10.15
	 */
%>

<h2>${isMinwon ? '답변' : '답글'} 작성/수정</h2>
<form:form commandName="searchVO" id="viewForm" name="viewForm" action="/sys/${siteCode}/bbs/bbsMng/view.do?mId=${menuVO.mId}" methodParam="post">
	<input type="hidden" name="bIdx" value="${bbsMngVO.bSame}"/>
	<form:hidden path="ptIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchDelete"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>
</form:form>
<form:form commandName="bbsMngVO" name="writeForm" id="writeForm" action="/sys/${siteCode}/bbs/bbsMng/${isNew ? 'replyProc' : 'replyModifyProc'}.do?mId=${menuVO.mId}" onsubmit="return validator() && additional_validator();" htmlEscape="false" methodParam="post" enctype="multipart/form-data">
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchDelete"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>
	<form:hidden path="writeId"/>

	<%-- 답변 등록시 필요 --%>
	<form:hidden path="bSame"/>
	<form:hidden path="bLevel"/>

	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<caption></caption>
		<colgroup>
			<col span="1" style="width:20%;"/>
			<col span="1"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span><label for="bTitle">제목</label></th>
				<td>
					<form:input path="bTitle" cssClass="input500" maxlength="500" data-validator="on" data-required="y" data-fieldName="제목"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="bWrite">작성자</label></th>
				<td>
					<form:hidden path="bDeptNm"/>
					${bbsMngVO.bWrite}
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="bContent">내용</label></th>
				<td>
					<form:textarea path="bContent" rows="20" cssClass="input99" maxlength="4000" data-validator="on" data-required="y" data-fieldName="내용"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="egovComFileUploader">첨부 파일</label></th>
				<td>
					<c:set var="param_maxFileNum" value="${bbsConfigVO.ptFileCnt}" scope="request"/>
					<c:set var="param_attachId" value="${bbsMngVO.attachId}" scope="request"/>
					<jsp:include page="/sys/jsp/component/fileAttachForm.jsp"/>
					<form:hidden path="attachId"/>
				</td>
			</tr>
			<%@include file="/sys/jsp/bbs/bbsMng/component/form/bPassword.jsp" %> <%-- 비밀번호 --%>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" onclick="document.getElementById('viewForm').submit(); return false;" class="btn_dblue">취소</a>
	</div>

</form:form>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">

	function additional_validator() {
		if ("${bbsConfigVO.ptCheckWord}") { // 규제 단어 포함
			var hit = forbiddenWordChecker("${bbsConfigVO.ptCheckWord}", jQuery("#bTitle").val());
			if (hit) {
				alert("[" + hit + "](은)는 규제된 단어입니다.");
				jQuery("#bTitle").focus();
				return false;
			}

			hit = forbiddenWordChecker("${bbsConfigVO.ptCheckWord}", jQuery("#bContent").val());
			if (hit) {
				alert("[" + hit + "](은)는 규제된 단어입니다.");
				jQuery("#bContent").focus();
				return false;
			}
		}

		return true;
	}

</script>