<%@page import="egovframework.portal.util.WriterUtil"%>
<%@ page language="java" contentType="text/html;charset=euc-kr"%>

<%
	NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

	String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
// 	String sReserved1 = requestReplace(request.getParameter("param_r1"), "");
	String sReserved1 = (String) session.getAttribute("successUrl"); // backUrl 송신
	String sReserved2 = (String) session.getAttribute("mId");
	/* String sReserved3 = request.getParameter("param_r3"); */
// 	String sReserved2 = requestReplace(request.getParameter("param_r2"), "");
// 	String sReserved3 = requestReplace(request.getParameter("param_r3"), "");

	String sSiteCode = "G5010"; // NICE로부터 부여받은 사이트 코드
	String sSitePassword = "4WA4X7E22TK2"; // NICE로부터 부여받은 사이트 패스워드

	String sCipherTime = ""; // 복호화한 시간
	String sRequestNumber = ""; // 요청 번호
	String sErrorCode = ""; // 인증 결과코드
	String sAuthType = ""; // 인증 수단
	String sMessage = "";
	String sPlainData = "";

	int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	if (iReturn == 0) {
		sPlainData = niceCheck.getPlainData();
		sCipherTime = niceCheck.getCipherDateTime();

		// 데이타를 추출합니다.
		java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);

		sRequestNumber = (String) mapresult.get("REQ_SEQ");
		sErrorCode = (String) mapresult.get("ERR_CODE");
		sAuthType = (String) mapresult.get("AUTH_TYPE");
		sMessage = "입력하신 정보 중 잘못된 값이 있거나 올바르지 않은 통신사를 선택하셨습니다.";
	} else if (iReturn == -1) {
		sMessage = "복호화 시스템 에러입니다.";
	} else if (iReturn == -4) {
		sMessage = "복호화 처리오류입니다.";
	} else if (iReturn == -5) {
		sMessage = "복호화 해쉬 오류입니다.";
	} else if (iReturn == -6) {
		sMessage = "복호화 데이터 오류입니다.";
	} else if (iReturn == -9) {
		sMessage = "입력 데이터 오류입니다.";
	} else if (iReturn == -12) {
		sMessage = "사이트 패스워드 오류입니다.";
	} else {
		sMessage = "알 수 없는 에러입니다. iReturn : " + iReturn;
	}

	StringBuilder content = new StringBuilder();
	content.append("<script>");
	content.append("alert('" + sMessage + "');");
	content.append("window.close();");
	content.append("</script>");
	WriterUtil.flushJSAlert(response, content.toString());
%>
<%!
	public static String requestReplace(String paramValue, String gubun) {
		String result = "";

		if (paramValue != null) {

			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");

			if (gubun != "encodeData") {
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}

			result = paramValue;

		}
		return result;
	}
%>

<%-- <html>
<head>
<title>NICE신용평가정보 - CheckPlus 안심본인인증 테스트</title>
</head>
<body>
	<center>
		<p>
		<p>
		<p>
		<p>
			본인인증이 실패하였습니다.<br>
		<table border=1>
			<tr>
				<td>복호화한 시간</td>
				<td><%=sCipherTime%> (YYMMDDHHMMSS)</td>
			</tr>
			<tr>
				<td>요청 번호</td>
				<td><%=sRequestNumber%></td>
			</tr>
			<tr>
				<td>본인인증 실패 코드</td>
				<td><%=sErrorCode%></td>
			</tr>
			<tr>
				<td>인증수단</td>
				<td><%=sAuthType%></td>
			</tr>
			<tr>
				<td>RESERVED1</td>
				<td><%=sReserved1%></td>
			</tr>
			<tr>
				<td>RESERVED2</td>
				<td><%=sReserved2%></td>
			</tr>
			<tr>
				<td>RESERVED3</td>
				<td><%=sReserved3%></td>
			</tr>
		</table>
		<br>
		<br>
		<%=sMessage%><br>
	</center>
</body>
</html> --%>