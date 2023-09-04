<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /**
  * @Class Name : selectContents.jsp
  * @Description : 각 셀렉트형 컨텐츠 화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2015-08-31   김장섭          최초 생성
  *
  *  @author 개발팀 김장섭
  *  @since 2015-08-31
  *  @version 1.0
  */
%>
<!-- 해당메뉴별 selectTarget.jsp 를 불러온다. -->
<c:set var="incFolderDepth1" value="${fn:substring(curMid, 0, 2)}" />
<c:set var="incFolderDepth2" value="${fn:substring(curMid, 2, 4)}" />
<c:set var="incFolderDepth3" value="${fn:substring(curMid, 4, 6)}" />
<c:set var="incFolderDepth4" value="${fn:substring(curMid, 6, 8)}" />
<c:set var="incFolder" value="${incFolderDepth1}" />
<c:if test="${not empty incFolderDepth2 && incFolderDepth2 != '00'}"><c:set var="incFolder" value="${incFolder}/${incFolderDepth2}" /></c:if>
<c:if test="${not empty incFolderDepth3 && incFolderDepth3 != '00'}"><c:set var="incFolder" value="${incFolder}_${incFolderDepth3}" /></c:if>
<c:if test="${not empty incFolderDepth4 && incFolderDepth4 != '00'}"><c:set var="incFolder" value="${incFolder}_${incFolderDepth4}" /></c:if>
<c:set var="incFolder" value="${incFolder}" />

<jsp:include page="/${siteCode}/contents/${incFolder}/selectTarget.jsp" />