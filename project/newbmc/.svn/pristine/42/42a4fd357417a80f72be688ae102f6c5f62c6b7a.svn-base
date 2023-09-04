<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/EgovMultiFile.js"></script>

<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
	<%-- 첨부파일 ID --%>
	<c:param name="param_atchFileId" value="${param_attachId}" />
	<%-- 썸네일 출력유무 --%>
	<c:param name="param_showThumbnail" value="${param_showThumbnail}" />
	<%-- 썸네일 width --%>
	<c:param name="param_thumbWidth" value="${param_thumbWidth}" />
	<%-- 썸네일 height --%>
	<c:param name="param_thumbHeight" value="${param_thumbHeight}" />
	<%-- 썸네일 출력 모드 --%>
	<c:param name="mode" value="${param_mode}" />
	<c:param name="fileExtSn" value="${param_fileExtSn}" />
	<%-- 이미지 alt 코멘트 출력여부 --%>
	<c:param name="param_showComment" value="${param_showComment}" />
	<%-- 문서 파일만 첨부하는 경우에는 접근성 코멘트 출력하지 않도록 구분하기 위한 옵션 --%>
	<c:param name="param_onlyDoc" value="${param_onlyDoc}" />
	<%-- 사이냅 파일 변환여부 --%>
	<c:param name="convertYn" value="${convertYn }"/>
	<%-- 파일 다운로드 링크 적용 --%>
	<c:param name="downloadYn" value="${empty downloadYn ? 'N' : 'Y' }"/>
	<%-- 파일 순서 변경 --%>
	<c:param name="orderEdit" value="${empty orderEdit ? 'N' : 'Y' }"/>
	<%-- 파일 개수 --%>
	<c:param name="param_maxFileNum" value="${param_maxFileNum}"/>
</c:import>
<c:if test="${empty param_onlyDoc or param_onlyDoc ne 'Y'}">
<p class="tip mB10 mT_im0">※ 접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요(확장자 jpg, gif, png, bmp 파일)</p>
</c:if>

<div class="area_file">
	
	<c:if test="${!isMovie}">
		<p class="red mB10">※ 파일은 최대 ${param_maxFileNum}개까지 첨부하실 수 있습니다. 첨부 가능한 하나의 파일 사이즈는 최대 100Mbyte 입니다.</p>
	</c:if>
	
	<c:if test="${not empty param_onlyDoc and param_onlyDoc eq 'Y'}">
	<p class="red mB10">※ 첨부 가능한 파일 형식은 .doc .docx .xls .xlsx .pdf .hwp입니다.</p>
	</c:if>
	<input name="file_1" id="egovComFileUploader" type="file" class="input400" title="파일첨부" />
	<ul id="egovComFileList" class="pT10 pic">
	</ul>
	<script type="text/javascript">
		//<![CDATA[
		$(document).ready(function() {
			multiSelector();
			
		});

		<%-- 파일 순서 변경 기능 추가로 인해 함수로 사용할 필요가 있어 함수로 변경 / 2017.05.10 권태성 --%>
		function multiSelector() {
			var multi_selector = new MultiSelector(document.getElementById("egovComFileList"), "${param_maxFileNum}");
			multi_selector.addElement(document.getElementById("egovComFileUploader"));

			var file_cnt = jQuery("[id^=FILE_]").length;
			if (0 < file_cnt && "${param_maxFileNum}" <= file_cnt) {
				jQuery("#egovComFileUploader").prop("disabled", true);
			}
		}
		//]]>
	</script>
</div>
