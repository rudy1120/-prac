<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ page isErrorPage="true" %>
<jsp:useBean id="date" class="java.util.Date" />

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		
		<title>페이지를 찾을 수 없습니다.</title>
		
		<link rel="stylesheet" type="text/css" href="/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="/common/css/error.css" />	
	    <script type="text/javascript" src="/common/js/jquery/jquery-1.11.3.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/prefixfree.min.js"></script>
	    <script type="text/javascript">
		    //<![CDATA[
		        setTimeout(function(){
		        	window.history.back(-1);
		        },3000);//"location.href='/'"
		    //]]>
    	</script>
	</head>
	<body>
		<div id="wrap_page">
			<div class="top">
				<p class="title">죄송합니다. 요청하신 페이지를 찾을 수 없습니다.</p>
				<p class="body">서비스 이용에 불편드려 죄송합니다.<br/>잠시후 <font>이전화면으로</font> 이동합니다.</p>
			</div>
		    <div class="tip">
		        <div>
		            <ul class="list_green">
		                <li>요청하신 페이지를 찾을 수 없습니다.</li>
		                <li>찾으시려는 페이지는 주소를 잘못 입력하였거나</li>
		                <li>페이지 주소의 변경 또는 삭제 또는 정상적인 URL접근이 아닌경우 등의 이유로 페이지를 찾을 수 없습니다.</li>
		                <li>입력하신 페이지의 주소와 경로가 정확한지 한번 더 확인 후 이용하시기 바랍니다.</li>
		                
		                <!-- 디버깅용 - 필요시 주석해제 
		                <li>Timestamp: <fmt:formatDate value="${date}" type="both" dateStyle="long" timeStyle="long" /></li>
			            <li>Action: <c:out value="${requestScope['javax.servlet.forward.request_uri']}" /></li>
			            <li>Exception: <c:out value="${requestScope['javax.servlet.error.exception']}" /></li>
			            <li>Message: <c:out value="${requestScope['javax.servlet.error.message']}" /></li>
			            <li>Status code: <c:out value="${requestScope['javax.servlet.error.status_code']}" /></li>
			            <li>User agent: <c:out value="${header['user-agent']}" /></li>
			            -->
		            </ul>
		        </div>
		    </div>
		</div>
	</body>
</html>