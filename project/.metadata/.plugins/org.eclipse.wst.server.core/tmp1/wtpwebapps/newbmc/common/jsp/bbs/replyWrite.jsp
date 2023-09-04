<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%@ taglib uri="/tlds/SecuKeyPad.tld" prefix="secuKeypad"%>
<%@ taglib uri="/tlds/SecuKeypadMobile.tld" prefix="secuKeypadMobile"%>
<%--
*********************************************************************
* 파  일  명 : replyWrite.jsp
* 설      명 : 공통 게시판 쓰기 (답글 쓰기)
* 작  성  자 : 김혜민
* 작  성  일 : 2014.11.12
* -------------------------------------------------------------------
* 수정일			작업자				작업내용
* --------------	----------------	-----------------------------
* 2015.02.25		김현호				웹접근성작업
* 2015.08.07		J.Ryeon Lee			검증 코드 공통화, 외부 파일화.
* 2016.07.22		J.Ryeon Lee			dnworks 디자인 적용
* 2016.08.26		권태성				모바일 가상키패드 적용
* 2016.08.26		권태성				가상키패드 초기화부분 분리
*********************************************************************
--%>
<%
	String frmName = "detailForm";
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("frmName", frmName);
%>
<c:set var="formName" value="<%=frmName %>"/>
<c:set var="isNew" value="${empty searchVO.bContent}" scope="request"/>
<form:form commandName="bbsVO" name="viewForm" id="viewForm" action="/${siteCodeFull}/bbs/view.do?ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}" method="POST">
	<form:hidden path="bIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>
</form:form>
<form:form commandName="bbsVO" id="${formName}" name="${formName}" action="/${siteCodeFull}/bbs/${empty searchVO.bContent ? 'replyProc' : 'replyModifyProc'}.do?mId=${menuVO.mId}" onsubmit="return validator('#${formName}') && bbs.additional_validator() && confirm('저장하시겠습니까?');" htmlEscape="false" methodParam="post" enctype="multipart/form-data">
	<input name="TOKEN_KEY" type="hidden" value="<%=request.getAttribute("TOKEN_KEY")%>"/> <%-- 중복방지 Token Parameter --%>
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>
	<form:hidden path="bReplyAdmin"/>
	<form:hidden path="bPublic" value="Y"/>
	<input type="hidden" name="writeId" value="${bbsVO.bWrite}"/>

	<%-- 답변 등록시 필요 --%>
	<form:hidden path="bSame"/>
	<form:hidden path="bLevel"/>

	<jsp:include page="/common/jsp/bbs/component/privacyPolicy.jsp"/> <%-- 개인정보 이용방침 동의 안내 --%>

	<div class="bod_wrap">
		<fieldset>
			<legend class="hidden">${activeMenu.menuName} 게시판 글쓰기</legend>
			<p class="taR">(<span class="orange">*</span>)표시는 필수입력 사항입니다.</p>
			<div class="bod_write">
				<%@include file="/common/jsp/bbs/component/form/bTitle.jsp"%> <%-- 게시글 제목 --%>
				<%@include file="/common/jsp/bbs/component/form/bWrite.jsp"%> <%-- 작성자 --%>
				<%@include file="/common/jsp/bbs/component/form/bContent.jsp"%> <%-- 내용 --%>
				<%@include file="/common/jsp/bbs/component/form/bFile.jsp"%> <%-- 첨부 파일 --%>
				<%@include file="/common/jsp/bbs/component/form/bPassword.jsp"%> <%-- 비밀번호 --%>
			</div>

			<%-- ============================== 저장/취소 ============================== --%>
			<div class="bod_foot">
				<div class="btnboxC">
					<input id="submitBtn" type="submit" class="btn_blue" value="저장"/>
					<a href="#" onclick="document.viewForm.submit(); return false;" class="btn_white" title="이전 페이지로 이동">취소</a>
				</div>
			</div>
		</fieldset>
	</div>
</form:form>

<script type="text/javascript">
	yh.siteCodeFull = "${siteCodeFull}";
	yh.ptIdx = "${bbsConfigVO.ptIdx}";
	yh.bIdx = "${searchVO.bIdx}";
	yh.isNew = eval("${isNew}");
	yh.formId = "${formName}";
	yh.formName = "${formName}";
	yh.ptCheckWord = "${bbsConfigVO.ptCheckWord}";
	yh.isPhoto = eval("${isPhoto}");
	yh.isMovie = eval("${isMovie}");
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/bbs/etc.js"></script>
