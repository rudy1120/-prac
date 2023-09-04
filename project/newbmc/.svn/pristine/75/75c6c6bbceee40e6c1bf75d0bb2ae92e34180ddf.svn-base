<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 관리자 수정 및 등록 화면
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

<c:set var="isNew"   value="${searchVO.idx eq 0}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>

<form:form commandName="searchVO" id="cancel" name="cancel"  methodParam="post" action="/sys/participation/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="searchVO" id="modify" name="modify" action="/sys/participation/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<h2>고객경영참여 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="gbn">구분</label></th>
				<td>
					<form:select id="gbn" path="gbn" cssClass="input200" data-validator="on" data-required="y" data-fieldName="구분">
						<form:option value="A">사업 및 경영혁신 아이디어</form:option>
						<form:option value="B">주민참여예산제</form:option>
						<form:option value="C">규제입증책임제</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="title">제목</label></th>
				<td>
					<form:input path="title" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="제목"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>시작/종료일</th>
				<td>
					<form:input path="sdate" cssClass="input100" data-date="y" data-isMinDateFor="sdate" data-validator="on" data-required="y" data-inputType="date" data-fieldName="시작일" autocomplete="off" readonly="true"/> ~
					<form:input path="edate" cssClass="input100" data-date="y" data-isMaxDateFor="edate" data-validator="on" data-required="y" data-inputType="date" data-fieldName="종료일" autocomplete="off" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="cont">내용</label></th>
				<td>
					<form:textarea path="cont" rows="20" class="input99" maxlength="100000" data-validator="on" data-required="y" data-fieldName="내용"></form:textarea>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row">공모기간</th>
				<td>
					<form:input path="etc1" maxlength="300" cssClass="input500" data-fieldName="정보1"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row">참가자격</th>
				<td>
					<form:input path="etc2" maxlength="300" cssClass="input500" data-fieldName="정보2"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row">제안내용</th>
				<td>
					<form:input path="etc3" maxlength="300" cssClass="input500" data-fieldName="정보3"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row">제출방법</th>
				<td>
					<form:input path="etc4" maxlength="300" cssClass="input500" data-fieldName="정보4"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row">포상금</th>
				<td>
					<form:input path="etc5" maxlength="300" cssClass="input500" data-fieldName="정보5"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="thumbnail">썸네일</label></th>
				<td>
					<c:set var="param_maxFileNum" value="${1}" scope="request"/>
					<c:set var="param_attachId" value="${searchVO.thumbnail}" scope="request"/>
					<jsp:include page="/sys/jsp/component/fileAttachForm.jsp"/>
					<form:hidden path="thumbnail"/>
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

<script>
$(function() {
	$('#gbn').on('change', function() {
		if ($(this).val() == 'B') {
			$('.i1:eq(0)').text('대상사업');
			$('.i1:eq(1)').text('규모');
			$('.i1:eq(2)').text('접수기간');
			$('.i1:eq(4)').text('-');
		} else {
			$('.i1:eq(0)').text('공모기간');
			$('.i1:eq(1)').text('참가자격');
			$('.i1:eq(2)').text('제안내용');
			$('.i1:eq(4)').text('포상금');
		}
		
	});
});
</script>

<script src="${pageContext.request.contextPath}/sys/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
	tinymce.init({
		  selector: 'textarea',
		  setup: function (editor) {
	          editor.on('change', function () {
	              tinymce.triggerSave();
	          });
		  },
		  height: 500,
		  plugins: [
		    "advlist autolink lists link image charmap print preview anchor",
		    "searchreplace visualblocks code fullscreen",
		    "insertdatetime media table paste imagetools wordcount"
		  ],
		  toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
		  /* without images_upload_url set, Upload tab won't show up*/
		  images_upload_url: '/sys/editor/image/upload.do',
		  relative_urls : false,
		  /* we override default upload handler to simulate successful upload*/
		  images_upload_handler: function (blobInfo, success, failure) {
			var xhr, formData;
	
	        xhr = new XMLHttpRequest();
	        xhr.withCredentials = false;
	        xhr.open('POST', '/sys/editor/image/upload.do');
	
	        xhr.onload = function() {
	            var json;
	
	            if (xhr.status != 200) {
	                failure('HTTP Error: ' + xhr.status);
	                return;
	            }
	
	            json = JSON.parse(xhr.responseText);
	
	            if (!json || typeof json.location != 'string') {
	                failure('Invalid JSON: ' + xhr.responseText);
	                return;
	            }
	
	            success(json.location);
	        };
	
	        formData = new FormData();
	        formData.append('file', blobInfo.blob(), blobInfo.filename());
	
	        xhr.send(formData);
		  }
		});
</script>
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
<script type="text/javascript" src="/sys/js/basic/promotion.js"></script>
