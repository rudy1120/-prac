<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영이벤트 관리자 수정 및 등록 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
%>
<link rel="stylesheet" href="/common/js/jquery/timepicker/jquery.ui.timepicker.css"/>
<script src="/common/js/jquery/timepicker/jquery.ui.timepicker.js"></script>

<c:set var="isNew"   value="${searchVO.idx eq 0}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>

<form:form commandName="searchVO" id="cancel" name="cancel"  methodParam="post" action="/sys/event/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="searchVO" id="modify" name="modify" action="/sys/event/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<h2>고객경영이벤트 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="subject">제목</label></th>
				<td>
					<form:input path="subject" maxlength="300" cssClass="input99" data-validator="on" data-required="y" data-fieldName="제목"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="headcomment">머리말</label></th>
				<td>
					<form:input path="headcomment" maxlength="300" cssClass="input99" data-validator="on" data-required="y" data-fieldName="머리말"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>시작/종료일</th>
				<td>
					<form:input path="sdate" cssClass="input200" data-date="y" data-isMinDateFor="sdate" data-validator="on" data-required="y" data-fieldName="시작일" autocomplete="off" readonly="true"/> ~
					<form:input path="edate" cssClass="input200" data-date="y" data-isMaxDateFor="edate" data-validator="on" data-required="y" data-fieldName="종료일" autocomplete="off" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>발표일</th>
				<td>
					<form:input path="pubdate" cssClass="input200" data-date="y" data-validator="on" data-required="y" data-inputType="date2" data-fieldName="발표일" autocomplete="off" readonly="true"/>
					<form:input path="pubtime" cssClass="input200" data-fieldName="시간" autocomplete="off"/>
					<form:input path="pubetc"  cssClass="input200" data-fieldName="발표일" placeholder="기타사항"/> 
				</td>
			</tr>
			<tr>
				<th scope="rop"><span class="red">*</span>사용 여부</th>
				<td>
					<form:select path="is_use">
					<form:option value="Y">활성화</form:option>
					<form:option value="N">비활성화</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="rop">참여자 의견작성 여부</th>
				<td>
					<form:select path="gubun">
					<form:option value="1">가능</form:option>
					<form:option value="2">불가능</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="cont">내용</label></th>
				<td>
					<form:textarea path="content" rows="20" class="input99" maxlength="4000" data-validator="on" data-required="y" data-fieldName="내용"></form:textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>추첨인원</th>
				<td>
					<form:input path="lim" cssClass="input100" data-validator="on" data-required="y" data-inputType="num" data-fieldName="추첨인원"/> 명
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="attachFile">이미지</label></th>
				<td>
					<c:set var="param_maxFileNum" value="${1}" scope="request"/>
					<c:set var="param_attachId" value="${searchVO.attachFile}" scope="request"/>
					<jsp:include page="/sys/jsp/component/fileAttachForm.jsp"/>
					<form:hidden path="attachFile"/>
					<input type="hidden" data-validator="on" data-inputType="file" data-fileType="img"/>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script src="${pageContext.request.contextPath}/sys/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
	yh.siteCode = "sys";
	yh.isNew = eval("${isNew}");
	yh.yearRange = "2017:+0";
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<link   rel="stylesheet"       type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
<script type="text/javascript" src="/common/js/bbs/etc.js"></script>
<script type="text/javascript" src="/sys/js/nuri.js"></script>
<link   rel="stylesheet"       type="text/css" href="/common/js/jquery/jquery-confirm.css" />
<script type="text/javascript" src="/common/js/jquery/jquery-confirm.js"></script>
<script>
	jQuery(document).ready(function(){
		dp.bind(jQuery("[data-date=y]"), "yy년 mm월 dd일(D)");
		//jQuery("#siteIdxs").fastselect(); // 사이트 멀티 셀렉트 디자인 적용
	
		/* if (yh.isNew) { // 최초 등록시 기본값 세팅
			jQuery("[name=useYn][value=Y]").trigger("click"); // 홍보 자료 사용
			jQuery("[name=blankYn][value=Y]").trigger("click"); // 새창 열림
			jQuery("[name=prmtTemplate]:first").trigger("click"); // 템플릿 사용 안 함
		} */
	
		$("#pubtime").timepicker({});
	});
</script>
