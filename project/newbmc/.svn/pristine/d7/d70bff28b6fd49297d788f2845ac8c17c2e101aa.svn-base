<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.ByteArrayOutputStream"%>
<%@ page import="java.io.OutputStreamWriter"%>
<%@ page import="java.nio.charset.Charset"%>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
String now = format.format(new Date());

request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>nProtect Online Security v1.0.0</title>
<style>
body,td, th{font-size:10pt}
input, textarea{font-size:9pt;}
</style>

<script type="text/javascript" src="/pluginfree/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>

<script type="text/javascript">
jQuery(document).ready(function(){	
	jQuery("#userAgent").text(navigator.userAgent);
	
	/*
	npPfsStartup(document.form1, false, true, true, "enc", "on");
	1. form 양식 : 기본값 DOM Document 상의 첫번째 form
	2. 개인방화벽 사용여부 : 기본값 false
	3. 키보드보안 사용여부 : 기본값 false
	4. 단말정보수집 사용여부 : 기본값 false
	5. 키보드보안 E2E 필드 설정 속성명 : 기본값 "enc"
	6. 키보드보안 E2E 필드 설정 속성값: 기본값 "on"
	부가적인 설정은(예, 설치확인 등) /pluginfree/js/nppfs-1.0.0.js를 수정하여 설정하십시오.
*/
/*
	npPfsStartup(
		document.form2
		, true
		, true
		, true
		, "npkencrypt"
		, "on"
	);
*/

	npPfsStartup(null, false, true, false, true, "npkencrypt", "on");
});


function decryptKeyCryptData() {
	npPfsCtrl.waitSubmit(function(){
		document.form1.submit();
	});
}

function doDecrypt(){
	npPfsCtrl.waitSubmit(function(){
		document.form2.submit();
	});
}

</script>

</head>
<!-- <body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"> -->
<body>

<table>
	<tr>
 		<th style="text-align:left;font-size:14pt;">접속정보</th>
 	</tr>
	<tr>
 		<td>
			<span id="userAgent"></span>
		</td>
 		<td>
			<span id="nos-install"></span>
		</td>
	</tr>
</table> 

<form name="dummy">
</form>

<div style="margin-bottom:20px; padding:10px; border:1px solid #000;">
<table>
 	<tr>
 		<th style="text-align:left;font-size:14pt;">개인방화벽 테스트</th>
 	</tr>
	<tr>
		<td>
			<input type="button" name="startNos" id="startNos" value="방화벽 시작" onclick="npNCtrl.start();">
			<input type="button" name="stopNos" id="stopNos" value="방화벽 종료" onclick="npNCtrl.stop();">
			
		</td>
	</tr>
</table> 
</div>

<div style="margin-bottom:20px; padding:10px; border:1px solid #000;">

<form name="form1" action="<%= response.encodeURL("decrypt.jsp") %>" method="post" target="resultTarget">
<div id="nppfs-loading-modal" style="display:none;"></div>
<div class="nppfs-elements" style="display:none;"></div>

	<input type="hidden" name="mode" value="KEYCRYPT" />
	<table width="100%">
		<colgroup>
			<col width="10%"></col>
			<col width="90%"></col>
		</colgroup>
		<tr>
			<th colspan="2" style="text-align:left;font-size:14pt;">키보드보안 테스트</th>
		</tr>
		<tr>
			<td> 미보호 </td>
			<td> <input type="text"     name="NONE_TEXT_2" id="n2" value="" npkencrypt="off" /></td>
		</tr>
		<tr>
			<td> Id(Inca):</td>
			<td>
			<input type="text"	name="e2e_text_with_checkbox" style="ime-mode:disabled;" npkencrypt="key" data-keypad-type="alpha" value="" maxlength="10" data-keypad-useyn-type="checkbox" data-keypad-useyn-input="e2e_text_useyn" /> : 10글자 
			<input type="checkbox" id="e2e_text_useyn" name="e2e_text_useyn" value="Y" /><label for="e2e_text_useyn">마우스입력기 사용</label>
			</td>
		</tr>				
		<tr>
			<td> PW(Inca):</td>
			<td>
			<input type="password" name="e2e_pass_with_checkbox" style="ime-mode:disabled;" npkencrypt="key" data-keypad-type="alpha" value="" maxlength="16" data-keypad-useyn-type="checkbox" data-keypad-useyn-input="e2e_pass_useyn" /> : 16글자
			<input type="checkbox" id="e2e_pass_useyn" name="e2e_pass_useyn" value="Y" /><label for="e2e_pass_useyn">마우스입력기 사용</label>
			</td>
		</tr>
		<tr>
			<td>Card(Inca):</td>
			<td>
				<input type="password" name="cardNo1" id="cardNo1" style="ime-mode:disabled;" npkencrypt="key" data-keypad-type="num" data-keypad-theme="mobile" value="" maxlength="4" size="4" style="width:20px;" />
				<input type="password" name="cardNo2" id="cardNo2" style="ime-mode:disabled;" npkencrypt="key" data-keypad-type="num" value="" maxlength="4" size="4" style="width:20px;" />
				<input type="password" name="cardNo3" id="cardNo3" style="ime-mode:disabled;" npkencrypt="key" data-keypad-type="num" data-keypad-theme="mobile" value="" maxlength="4" size="4" style="width:20px;" />
				<input type="password" name="cardNo4" id="cardNo4" style="ime-mode:disabled;" npkencrypt="key" data-keypad-type="num" value="" maxlength="4" size="4" style="width:20px;" />
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<input type="button" name="getClientKey" id="getClientKey" value="복호화" onclick="decryptKeyCryptData();">
			</td>
		</tr>
	</table>
</form>
</div>

<%--
<div style="margin-bottom:20px; padding:10px; border:1px solid #000;">
<form name="form2" action="decrypt.jsp" method="post" target="resultTarget">
<div class="nppfs-elements" style="display:none;"></div>
	<input type="hidden" name="mode" value="" />
	<table>
	 	<tr>
	 		<th style="text-align:left;font-size:14pt;"> 단말정보수집 테스트</th>
	 	</tr>
		<tr>
			<td><input type="button" name="doDec" onclick="doDecrypt();" value="복호화" /></td>
		</tr>
	</table>
</form>
</div>
 --%>

<div style="margin-bottom:20px; padding:10px; border:1px solid #000;">
	<table width="100%">
	 	<tr>
	 		<th style="text-align:left;font-size:14pt;"> 복호화 테스트</th>
	 	</tr>
		<tr>
			<td>
	<iframe id="resultTarget" name="resultTarget" src="about:blank" style="border:0px solid #000;width:100%;height:300px;"></iframe>
			</td>
		</tr>
	</table>
</div>

</body>
</html>
