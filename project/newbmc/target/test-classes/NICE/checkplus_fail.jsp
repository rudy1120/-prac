<%@page import="egovframework.portal.util.WriterUtil"%>
<%@ page language="java" contentType="text/html;charset=euc-kr"%>

<%
	NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

	String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
// 	String sReserved1 = requestReplace(request.getParameter("param_r1"), "");
	String sReserved1 = (String) session.getAttribute("successUrl"); // backUrl �۽�
	String sReserved2 = (String) session.getAttribute("mId");
	/* String sReserved3 = request.getParameter("param_r3"); */
// 	String sReserved2 = requestReplace(request.getParameter("param_r2"), "");
// 	String sReserved3 = requestReplace(request.getParameter("param_r3"), "");

	String sSiteCode = "G5010"; // NICE�κ��� �ο����� ����Ʈ �ڵ�
	String sSitePassword = "4WA4X7E22TK2"; // NICE�κ��� �ο����� ����Ʈ �н�����

	String sCipherTime = ""; // ��ȣȭ�� �ð�
	String sRequestNumber = ""; // ��û ��ȣ
	String sErrorCode = ""; // ���� ����ڵ�
	String sAuthType = ""; // ���� ����
	String sMessage = "";
	String sPlainData = "";

	int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	if (iReturn == 0) {
		sPlainData = niceCheck.getPlainData();
		sCipherTime = niceCheck.getCipherDateTime();

		// ����Ÿ�� �����մϴ�.
		java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);

		sRequestNumber = (String) mapresult.get("REQ_SEQ");
		sErrorCode = (String) mapresult.get("ERR_CODE");
		sAuthType = (String) mapresult.get("AUTH_TYPE");
		sMessage = "�Է��Ͻ� ���� �� �߸��� ���� �ְų� �ùٸ��� ���� ��Ż縦 �����ϼ̽��ϴ�.";
	} else if (iReturn == -1) {
		sMessage = "��ȣȭ �ý��� �����Դϴ�.";
	} else if (iReturn == -4) {
		sMessage = "��ȣȭ ó�������Դϴ�.";
	} else if (iReturn == -5) {
		sMessage = "��ȣȭ �ؽ� �����Դϴ�.";
	} else if (iReturn == -6) {
		sMessage = "��ȣȭ ������ �����Դϴ�.";
	} else if (iReturn == -9) {
		sMessage = "�Է� ������ �����Դϴ�.";
	} else if (iReturn == -12) {
		sMessage = "����Ʈ �н����� �����Դϴ�.";
	} else {
		sMessage = "�� �� ���� �����Դϴ�. iReturn : " + iReturn;
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
<title>NICE�ſ������� - CheckPlus �Ƚɺ������� �׽�Ʈ</title>
</head>
<body>
	<center>
		<p>
		<p>
		<p>
		<p>
			���������� �����Ͽ����ϴ�.<br>
		<table border=1>
			<tr>
				<td>��ȣȭ�� �ð�</td>
				<td><%=sCipherTime%> (YYMMDDHHMMSS)</td>
			</tr>
			<tr>
				<td>��û ��ȣ</td>
				<td><%=sRequestNumber%></td>
			</tr>
			<tr>
				<td>�������� ���� �ڵ�</td>
				<td><%=sErrorCode%></td>
			</tr>
			<tr>
				<td>��������</td>
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