<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:set var="flush_cnt" value="0"/>
<c:if test="${not empty fileExts}">
	<c:forEach var="fileExt" items="${fileExts}" varStatus="statusExt">
		<c:if test="${flush_cnt < 2}">
			<c:set var="fileExt" value="${fn:toLowerCase(fileExt)}"/>
			<c:set var="flush_cnt" value="${flush_cnt + 1}"/>
			<c:choose>
				<c:when test="${fileExt == 'jpg' || fileExt == 'jpeg'}">
					<c:set var="fileExt" value="jpg"/>
					<c:set var="fileAlt" value="이미지 파일"/>
				</c:when>
				<c:when test="${fileExt == 'gif' || fileExt == 'png' || fileExt == 'bmp'}">
					<c:set var="fileAlt" value="이미지 파일"/>
				</c:when>
				<c:when test="${fileExt == 'hwp'}"><c:set var="fileAlt" value="한글 파일"/></c:when>
				<c:when test="${fileExt == 'ppt' || fileExt == 'pptx'}">
					<c:set var="fileExt" value="ppt"/>
					<c:set var="fileAlt" value="파워포인트 파일"/>
				</c:when>
				<c:when test="${fileExt == 'xls' || fileExt == 'xlsx'}">
						<c:set var="fileExt" value="xls"/>
					<c:set var="fileAlt" value="엑셀 파일"/>
				</c:when>
				<c:when test="${fileExt == 'pdf'}"><c:set var="fileAlt" value="PDF 파일"/></c:when>
				<c:when test="${fileExt == 'zip'}"><c:set var="fileAlt" value="압축 파일"/></c:when>
				<c:when test="${fileExt == 'txt'}"><c:set var="fileAlt" value="텍스트 파일"/></c:when>
				<c:when test="${fileExt == 'htm' || fileExt == 'html'}">
					<c:set var="fileExt" value="htm"/>
					<c:set var="fileAlt" value="html 파일"/>
				</c:when>
				<c:otherwise>
					<c:set var="fileExt" value="etc"/>
					<c:set var="fileAlt" value="기타 첨부 파일"/>
				</c:otherwise>
			</c:choose>
			<img src="/common/img/board/${fileExt}.gif" alt="${fileAlt}"/>
		</c:if>
	</c:forEach>
</c:if>
