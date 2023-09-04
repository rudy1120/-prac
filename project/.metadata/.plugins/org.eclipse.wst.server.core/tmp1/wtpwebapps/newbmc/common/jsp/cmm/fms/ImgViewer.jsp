<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%

%>
<!-- DWR -->
<script type="text/javascript" src="<c:url value="/dwr/interface/EgovFileMngDwr.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script> 

<script type="text/javascript" src="<c:url value='/common/js/common/EgovFileUtils.js' />"></script>
<c:if test="${empty fileCnId }"><c:set var="fileCnYn" value="${false }" /></c:if>
<c:if test="${!empty fileList}">
	<c:if test="${orderEdit eq 'Y' }">
		<p class="mB10">
			<a href="#" id="btn_${fileList[0].atchFileId}" onclick="window.open('${pageContext.request.contextPath}/cmm/cms/fileOrder.do?atchFileId=${fileList[0].atchFileId}&showThumbnail=${showThumbnail }&thumbWidth=${thumbWidth }&thumbHeight=${thumbHeight }&fileCnt=${maxFileNum }&TOKEN_KEY=<%=request.getAttribute("TOKEN_KEY")%>', '', 'width=600, height=450, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200'); return false;" class="btn_blue">파일 순서 설정</a>
		</p>
	</c:if>
	<div id="updateFileList" class="updateFileList">
		<div>
			<c:forEach var="fileVO" items="${fileList}" varStatus="status">
				<c:set var="isConvert" value="N"/>
				<c:set var="isImg" value="${false}"/>
				<c:set var="lowFileExtsn" value="${fn:toLowerCase(fileVO.fileExtsn)}" />
				<c:if test="${lowFileExtsn == 'png' or lowFileExtsn == 'jpg' or lowFileExtsn == 'jpeg' or lowFileExtsn == 'bmp' or lowFileExtsn == 'gif'}"><c:set var="isImg" value="${true}"/></c:if>
				<c:if test="${isImg and lowFileExtsn == 'pdf' or lowFileExtsn == 'doc' or lowFileExtsn == 'hwp' or lowFileExtsn == 'ppt' or lowFileExtsn == 'pptx' or lowFileExtsn == 'xls' or lowFileExtsn == 'xlsx' or lowFileExtsn == 'docx'}"><c:set var="isConvert" value="Y"/></c:if>
				<ul class="imageList" style="float:left; width:260px;">
					<li id="<c:if test="${empty fileCnId }"><c:out value="${fileVO.atchFileId}"/>-<c:out value="${fileVO.fileSn}"/></c:if>">
						<div class="mT10 mB5" style="float:left;">
							<c:choose>
								<c:when test="${mode == 'movieThumbnail'}">
									<img src="/common/jsp/common/movieThumbnailViewer.jsp?basePath=${fileVO.fileStreCours}&streFileNm=${fileVO.streFileNm}" width="200"/>
								</c:when>
								<c:when test="${mode =='imgThumbnail' }">
									<img src="/common/imgView.do?attachId=${yh:ariaEnc(fileVO.atchFileId)}&fileSn=${yh:ariaEnc(fileVO.fileSn)}&width=${not empty thumbWidth ? thumbWidth : '250'}&height=${not empty thumbHeight ? thumbHeight : '100'}&mode=ratio"/>
								</c:when>
							</c:choose>
							<!-- 파일다운 -->
							<span><a href="#" onclick="fn_egov_downFile('${yh:ariaEnc(fileVO.atchFileId)}','${yh:ariaEnc(fileVO.fileSn)}'); return false;">${fileVO.orignlFileNm}</a></span>&nbsp;[${fileVO.fileMgUnit}]
						</div>
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>
</c:if>

<c:if test="${fn:length(fileList) == 0}"><span class="noFile"></span></c:if>
<c:if test="${convertYn == 'Y'}">
	<form name="synapPreviewFrm" id="synapPreviewFrm" action="/sn3hcvConvert.jsp" method="post" target="_blank">
	<input type="hidden" name="atchFileId" id="atchFileId" value="">
	<input type="hidden" name="fileSn" id="fileSn" value="">
	<input type="hidden" name="streFileNm" id="streFileNm" value="">
	<input type="hidden" name="fileStreCours" id="fileStreCours" value="">
	<input type="hidden" name="orignlFileNm" id="orignlFileNm" value="">
	</form>
</c:if>