<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /**
  * @File Name : fileInfo.jsp
  * @Description : 특정 파일에 대한 출력
  * @Modification Information
  *
  * 수정일			수정자				수정내용
  * ------------	---------------		---------------------------
  * 2016.11.22		권태성				최초 생성
  */
%>


<c:if test="${mode eq 'fileView' }">
	<script type="text/javascript" src="/common/js/common/EgovFileUtils.js"></script>
	<c:if test="${showFileName eq 'Y' }"><c:set var="fileName" value=" ${file.orignlFileNm }" /></c:if>
	<c:set var="onclick" value="fn_egov_downFile('${yh:ariaEnc(atchFileId)}', '${yh:ariaEnc(fileSn)}'); return false;" />
	<c:if test="${not empty file }">
		<c:choose>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'pdf'}"><c:set var="aTitle" value="PDF" /><c:set var="fileImg" value="pdf.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'bmp'}"><c:set var="aTitle" value="BMP" /><c:set var="fileImg" value="bmp.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'doc'}"><c:set var="aTitle" value="DOC" /><c:set var="fileImg" value="doc.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'htm'}"><c:set var="aTitle" value="HTM" /><c:set var="fileImg" value="htm.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'hwp'}"><c:set var="aTitle" value="HWP" /><c:set var="fileImg" value="hwp.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'jpg'}"><c:set var="aTitle" value="JPG" /><c:set var="fileImg" value="jpg.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'png'}"><c:set var="aTitle" value="PNG" /><c:set var="fileImg" value="png.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'ppt'}"><c:set var="aTitle" value="PPT" /><c:set var="fileImg" value="ppt.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'pptx'}"><c:set var="aTitle" value="PPTX" /><c:set var="fileImg" value="pptx.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'txt'}"><c:set var="aTitle" value="TXT" /><c:set var="fileImg" value="txt.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'xls'}"><c:set var="aTitle" value="XLS" /><c:set var="fileImg" value="xls.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'xlsx'}"><c:set var="aTitle" value="XLSX" /><c:set var="fileImg" value="xls.gif" /></c:when>
			<c:when test="${fn:toLowerCase(file.fileExtsn) eq 'zip'}"><c:set var="aTitle" value="ZIP" /><c:set var="fileImg" value="zip.gif" /></c:when>
			<c:otherwise>
				<c:set var="aTitle" value="ETC" /><c:set var="fileImg" value="etc.gif" />
			</c:otherwise>
		</c:choose>
		<a href="#" onclick="${onclick}" title="새 창에서 ${aTitle } 파일 다운로드"><img src="${pageContext.request.contextPath}/common/img/board/${fileImg }" alt="${aTitle }파일"/>${fileName }</a>
	</c:if>
</c:if>
<c:if test="${mode eq 'fileDownCnt' }">${downloadCnt }</c:if>
<c:if test="${mode eq 'fileExt' }">${file.fileExtsn }</c:if>
<c:if test="${mode eq 'fileName' }">${file.orignlFileNm }</c:if>
<c:if test="${mode eq 'fileCn' }">${file.fileCn }</c:if>