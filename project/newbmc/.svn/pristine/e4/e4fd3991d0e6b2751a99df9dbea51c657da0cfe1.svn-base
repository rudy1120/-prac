<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /**
  * @Class Name : EgovFileList.jsp
  * @Description : 파일 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2014.06.20  김장섭
  *
  */
%>
<!-- DWR -->
<script type="text/javascript" src="<c:url value="/dwr/interface/EgovFileMngDwr.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>

<script type="text/javascript" src="<c:url value='/common/js/common/EgovFileUtils.js' />"></script>

<c:if test="${!empty fileList}">
	<ul class="list_file">
		<c:forEach var="fileVO" items="${fileList}" varStatus="status">
		<c:choose>
	 		<c:when test="${updateFlag=='Y'}">
				<li id="<c:out value="${fileVO.atchFileId}"/>-<c:out value="${fileVO.fileSn}"/>"><c:out value="${fileVO.orignlFileNm}"/>&nbsp;[${fileVO.fileMgUnit}]
	  				<input type="button" onclick="fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');return false;" class="btn_dele" value="" title="첨부파일삭제"/>
	  			</li>
	 		</c:when>
	 		<c:otherwise>
	 			<li>
		 			<a href="#" onclick="fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')">
			  			<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[${fileVO.fileMgUnit}]
		 			</a>
	 			</li>
	 		</c:otherwise>
		</c:choose>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${fn:length(fileList) == 0}"></c:if>
