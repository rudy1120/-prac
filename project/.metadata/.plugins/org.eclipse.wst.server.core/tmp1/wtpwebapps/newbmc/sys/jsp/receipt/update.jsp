<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<c:set var="isNew"   value="${searchVO.idx eq 0}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>

<form:form commandName="searchVO" id="cancel" name="cancel"  methodParam="post" action="/sys/receipt/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="searchVO" id="modify" name="modify" action="/sys/receipt/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<h2>채용응시원서 접수 등록/수정</h2>
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
						<form:option value="A">기능인재 접수</form:option>
						<form:option value="B">기간제직원 접수</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="title">제목</label></th>
				<td>
					<form:input path="title" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="제목"/>
				</td>
			</tr>
			<%@include file="/sys/jsp/receipt/termYn.jsp" %> <%-- 접수기간 --%>
			<tr>
				<th scope="row"><span class="red">*</span><label for="cont">내용</label></th>
<!-- 				<th rowspan="2" scope="row"><span class="red">*</span><label for="cont">내용</label></th> -->
				<td>
					<form:textarea path="cont" rows="20" class="input99" maxlength="100000" data-validator="on" data-required="y" data-fieldName="내용"></form:textarea>
					<jsp:include page="/plugin/SmartEditor/smartEditor.jsp"></jsp:include>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td> -->
<!-- 					<input type="button" id="preview" class="btn_cyan" value="미리보기"/><br/> -->
<!-- 					1. 일반게시판의 경우 사용자에게 나타나는 화면을 미리 확인할 수 있습니다.<br/> -->
<!-- 					2. 포토게시판의 경우 첨부파일로 올린 이미지가 상단에 중앙정렬로 나타납니다. 첨부파일 이미지는 미리보기가 지원이 안됩니다.<br/>		 -->
<!-- 					3. FAQ게시판의 경우 배경색과 크기등의 다른 요소가 많을 수 있습니다. -->
<!-- 				</td> -->
<!-- 			</tr> -->
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
