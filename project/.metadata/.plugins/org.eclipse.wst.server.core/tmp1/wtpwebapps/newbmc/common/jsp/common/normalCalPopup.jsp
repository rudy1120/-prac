<%
 /**
  * @Class Name  : normalCalPopup.jsp
  * @Description : normalCalPopup 화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2013-04-09   박동환          최초 생성
  *
  *  @author 개발팀 박동환
  *  @since 2013-04-09
  *  @version 1.0 
  *  @see
  */
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>일반달력</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/calendar.css'/>" />
<script type="text/javaScript" language="javascript">
<!--
-->
</script>
</head>
<form name="pForm">
<input type="hidden" name="init" value="">
</form>
<!-- IE
<iframe name="ifcal" src="<c:url value='/common/selectNormalCalendar.do'/>" style="width:252px; height:175px;" frameborder="0"></iframe>
-->
<!-- FIREFOX -->
<iframe name="ifcal" src="<c:url value='/common/selectNormalCalendar.do'/>" style="width:100%; height:100%;" frameborder="0"></iframe>
