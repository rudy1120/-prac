<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>창원시 홈페이지 창 닫기</title>
		<!-- common -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.9.1.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js" ></script>
	</head>
	<body>
		<script type="text/javascript">
		//<![CDATA[
		$(document).ready(function() {
			self.close();
		});
		//]]>
		</script>
	</body>
</html>