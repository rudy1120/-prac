<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/common/taglib.jsp" %>
<%@include file="/common/jsp/bbs/component/variables.jsp" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	/**
	 * 게시글 등록/수정
	 *
	 * 작업 가이드:
	 * 1. 변수는 최상단에 scope="request"로 작업 OR /common/jsp/bbs/component/variables.jsp에 추가(공통 변수의 경우)
	 * 2. 입력 항목이 늘어나는 경우는 /bbs/jsp/bbs/component/form/ 밑에 jsp를 추가해 작업
	 * 3. 해당 파일은 /sys/jsp/bbs/bbsMng/write.jsp에 노출되는 것과 동일해야 함
	 * 4. label 및 웹접근성 준수必
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.04.26		J.Ryeon Lee		최초 생성 및 코딩 (타입별로 분리되어 있던 jsp 통합)
	 *
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.04.26
	 */
%>
<%
	String frmName = "detailForm";
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("formName", frmName);
%>
<c:set var="formName" value="<%=frmName %>"/>
<form:form commandName="bbsVO" name="list" id="list" action="/${siteCodeFull}/bbs/list.do?ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}" method="POST">
	<form:hidden path="page"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>
</form:form>
<form:form commandName="searchVO" id="${formName}" name="${formName}" action="/${siteCodeFull}/bbs/${postUrl}.do?mId=${menuVO.mId}" onsubmit="return validator('#${formName}') && bbs.additional_validator() && confirm('저장하시겠습니까?');" methodParam="post" enctype="multipart/form-data" htmlEscape="false">
	<input name="TOKEN_KEY" type="hidden" value="${TOKEN_KEY}"/> <%-- 중복방지 Token Parameter --%>
	<input type="hidden" name="writeId" value="${bbsVO.bWrite}"/>
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>

	<c:set var="p_article" value="${searchVO}" scope="request"/>
	<jsp:include page="/common/jsp/bbs/component/privacyPolicy.jsp"/> <%-- 개인정보 이용방침 동의 안내 --%>

	<div class="bod_wrap">
		<fieldset>
			<legend class="hidden">${activeMenu.menuName} 게시판 글쓰기</legend>
			<p class="taR pB5">(<span class="red">*</span>)표시는 필수 입력 사항입니다.</p>

			<%-- =============================== INPUT 영역 =============================== --%>
			<div class="bod_write">
				<%@include file="/common/jsp/bbs/component/form/bTitle.jsp"%> <%-- 게시글 제목 --%>
				<%@include file="/common/jsp/bbs/component/form/bWrite.jsp"%> <%-- 작성자 --%>
				<%@include file="/common/jsp/bbs/component/form/bDept.jsp"%> <%-- 부서 --%>
				<%@include file="/common/jsp/bbs/component/form/bCategory.jsp"%> <%-- 분류 --%>
				<%@include file="/common/jsp/bbs/component/form/bPublicYn.jsp"%> <%-- 비밀글 --%>
				<%@include file="/common/jsp/bbs/component/form/bAddr.jsp"%> <%-- 주소 --%>
				<%@include file="/common/jsp/bbs/component/form/bPhone.jsp"%> <%-- 휴대전화 --%>
				<%@include file="/common/jsp/bbs/component/form/optionalFields.jsp"%> <%-- 추가 옵션 필드 --%>
				<%@include file="/common/jsp/bbs/component/form/bContent.jsp"%> <%-- 내용 --%>
				<%@include file="/common/jsp/bbs/component/form/bFile.jsp"%> <%-- 첨부 파일 --%>
				<%@include file="/common/jsp/bbs/component/form/bPassword.jsp"%> <%-- 비밀번호 --%>
			</div>

			<%-- =============================== BUTTON 영역 =============================== --%>
			<div class="bod_foot">
				<div class="btnboxC">
					<input id="submitBtn" type="submit" class="btn_blue" value="저장"/>
					<c:if test="${not empty searchVO.bIdx}">
						<a href="#" onclick="goTo.view('${formName}', '${searchVO.bIdx}'); return false;" class="btn" title="이전 페이지로 이동">취소</a>
					</c:if>
					<c:if test="${empty searchVO.bIdx}">
						<a href="#" onclick="document.getElementById('list').submit(); return false;" class="btn" title="이전 페이지로 이동">취소</a>
					</c:if>
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
	yh.privacyCols = "${bbsConfigVO.ptSaveCols}" ? "${bbsConfigVO.ptSaveCols}".split(",") : null;
	yh.optPrivacyCols = "${bbsConfigVO.ptSaveOptCols}" ? "${bbsConfigVO.ptSaveOptCols}".split(",") : null;
	yh.limitFileSize = '${bbsConfigVO.ptLimitFileSize}';
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/bbs/etc.js"></script>
<script type="text/javascript" src="/common/js/bbs/pageMove.js"></script>
