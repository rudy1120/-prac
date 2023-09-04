<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/EgovMultiFile.js"></script> 

<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
	<c:param name="param_atchFileId"    value="${param_attachId}" />
	<c:param name="param_showThumbnail" value="${param_showThumbnail}" />
	<c:param name="param_thumbWidth"    value="${param_thumbWidth}" />
	<c:param name="param_thumbHeight"   value="${param_thumbHeight}" />
	<c:param name="mode"                value="${param_mode}" />
	<c:param name="fileExtSn"           value="${param_fileExtSn}" />
	<c:param name="param_showComment"   value="${param_showComment}" /> 
</c:import>

<div class="mT10">
	<input type="file" name="file_1" id="egovComFileUploader" title="파일첨부" class="w90" value="aa"/>
	<input type="file" name="hidden_dummy_file" style="display: none;" />
</div>

<ul id="egovComFileList" class="pT10"> 
	<li id="noFile"></li>
</ul>
<script type="text/javascript">
//<![CDATA[
	
	$(document).ready(function() {
		multiSelector();
	});

	function multiSelector() {
		var multi_selector = new MultiSelector(document.getElementById("egovComFileList"), "${param_maxFileNum}" ,"${param_showComment}");
		multi_selector.addElement(document.getElementById("egovComFileUploader"));

		var file_cnt = jQuery("[id^=FILE_]").length;
		if (0 < file_cnt && "${param_maxFileNum}" <= file_cnt) {
			jQuery("#egovComFileUploader").prop("disabled", true);
		}
		
	}

//]]>
</script>