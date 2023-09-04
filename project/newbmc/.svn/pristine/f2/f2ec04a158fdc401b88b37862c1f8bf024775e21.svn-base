<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
String now = format.format(new Date());
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <link rel="stylesheet" type="text/css" href="/bmc/font/font.css"> -->
<link rel="stylesheet" type="text/css" href="../font/font.css">
<link rel="stylesheet" type="text/css" href="../css/board2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>개인정보 제3자 제공 동의서</title>
<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/bmc/js/netfunnel.js" charset="UTF-8"></script>

<c:set var="successUrl" value="/bmc/happyhouse/chkProc.do" />
<c:set var="sAuthType" value="M" />

<script>
// window.onload = function(){
// 	NetFunnel_Complete();
// }
</script>

<style>

table { width: 95%; border-collapse: collapse; border-top: 1px solid #ccc; margin: auto; }
thead { z-index: 1000; position: relative; }
th,td { 
    text-align: center; border-right: 1px solid #ccc; border-bottom: 1px solid #ccc; 
    background-clip: padding-box; scroll-snap-align: start; 
    padding: 0.6rem; min-width: 6rem; margin: 1px 0 0 0;
}
th:last-of-type,td:last-of-type { border-right: 0; }
thead th { position: sticky; top: 0; background-clip: padding-box; border-left: 0; }
tbody { z-index: 10; position: relative; }
tbody th { position: sticky; left: 0; }
thead th, tbody th { background-color: #f8f8f8; }

.red{ color: #e11f2e; }
.gap_20 { height: 20px; }
.gap_70 { height: 70px; }

.txt-3 { text-align: center; display: block; font-size: 23px; }

</style>

</head>

<body>

	<h3 style="font-size:3em;margin-bottom:20px;" class="tit">개인정보 제3자 제공 동의서</h3>
	<div class="all_wrap">
		<div class="gap_70"></div>
		<div>
			<div class="gap"></div>
			<img src="/bmc/images/content/happy-img.png" alt="" style="width: 827px; display: block; margin: 0 auto;">
			<div class="gap20-il"></div>
			<div class="gap20-il"></div>
			<span class="txt-3">개인정보 제3자 제공 동의서 제출이 완료 되었습니다.</span>
			<div class="gap20-il"></div>
			<span class="txt-3">감사합니다.</span>
		</div>
	</div>

</body></html>