<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /**
  * @Class Name : EgovFileMovie.jsp
  * @Description : 파일 동영상 상세화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2014.11.03  김혜민
  *
  */
%>
<!-- DWR -->
<script type="text/javascript" src="<c:url value="/dwr/interface/EgovFileMngDwr.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>

<script type="text/javascript" src="<c:url value='/js/common/EgovFileUtils.js' />"></script>

	<c:if test="${ not empty fileList }">

	<ul class="list_file">
	<c:choose>
 		<c:when test="${updateFlag=='Y'}">
			<li id="<c:out value="${fileList.atchFileId}"/>-<c:out value="${fileList.fileSn}"/>"><c:out value="${fileList.orignlFileNm}"/>&nbsp;[${fileList.fileMgUnit}]
				<c:choose>
					<c:when test="${fileList.fileExtsn == 'bmp' or fileList.fileExtsn =='jpeg' or fileList.fileExtsn =='jpg' or fileList.fileExtsn == 'gif' or fileList.fileExtsn == 'png' or fileList.fileExtsn == 'BMP' or fileList.fileExtsn =='JPEG' or fileList.fileExtsn =='JPG' or fileList.fileExtsn == 'GIF' or fileList.fileExtsn == 'PNG' }">
						<input type="button" onclick="fn_egov_deleteFile_Movie('<c:out value="${fileList.atchFileId}"/>','<c:out value="${fileList.fileSn}"/>','file_2');return false;" class="btn_dele" value="" title="첨부파일삭제"/>
					</c:when>
					<c:otherwise>
						<input type="button" onclick="fn_egov_deleteFile_Movie('<c:out value="${fileList.atchFileId}"/>','<c:out value="${fileList.fileSn}"/>','file_1');return false;" class="btn_dele" value="" title="첨부파일삭제"/>
					</c:otherwise>
				</c:choose>
  			</li>
 		</c:when>
 		<c:otherwise>
 			<li>
	 			<a href="#" onclick="fn_egov_downFile('<c:out value="${fileList.atchFileId}"/>','<c:out value="${fileList.fileSn}"/>')">
		  			<c:out value="${fileList.orignlFileNm}"/><span class="black">[${fileList.fileMgUnit}]</span>
	 			</a>
 			</li>
 		</c:otherwise>
	</c:choose>
	</ul>

	</c:if>