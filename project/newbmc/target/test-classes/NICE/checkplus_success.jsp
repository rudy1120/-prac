<%@page import="egovframework.portal.util.UserUtil"%>
<%@ page language="java" contentType="text/html;charset=euc-kr" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="egovframework.portal.util.TUtil"%>
<%@ page import="egovframework.portal.unit.common.UserType"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.authentication.UsernamePasswordAuthenticationToken"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="egovframework.portal.security.vo.Role"%>
<%@ page import="egovframework.portal.security.RoleType"%>
<%@ page import="egovframework.portal.security.user.vo.User"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="egovframework.portal.util.WriterUtil"%>
<%
	NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

	String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
	String sReserved1 = (String) session.getAttribute("successUrl"); // backUrl �۽�
	String sReserved2 = (String) session.getAttribute("mId");
	/* String sReserved3 = request.getParameter("param_r3"); */

	if(sReserved1.contains("?")) {
		sReserved1 += "&mId="+sReserved2;
	} else {
		sReserved1 += "?mId="+sReserved2;
	}

	String sSiteCode = "G5010"; // NICE�κ��� �ο����� ����Ʈ �ڵ�
	String sSitePassword = "4WA4X7E22TK2"; // NICE�κ��� �ο����� ����Ʈ �н�����

	String sCipherTime = ""; 	// ��ȣȭ�� �ð�
	String sRequestNumber = ""; // ��û ��ȣ
	String sResponseNumber = "";// ���� ������ȣ
	String sAuthType = ""; 		// ���� ����
	String sName = ""; 			// ����
	String sDupInfo = ""; 		// �ߺ����� Ȯ�ΰ� (DI_64 byte)
	String sConnInfo = ""; 		// �������� Ȯ�ΰ� (CI_88 byte)
	String sBirthDate = ""; 	// ����
	String sGender = ""; 		// ����
	String sNationalInfo = ""; 	// ��/�ܱ������� (���߰��̵� ����)
	String sMobileNo = ""; 		// �޴�����ȣ
	String sMobileCo = ""; 		// ��Ż�
	String sMessage = "";
	String sPlainData = "";

	int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);
	StringBuilder content = new StringBuilder();

	if (iReturn == 0) { // success
		sPlainData = niceCheck.getPlainData();
		sCipherTime = niceCheck.getCipherDateTime();

		// ����Ÿ�� �����մϴ�.
		java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);

		sRequestNumber = (String)mapresult.get("REQ_SEQ");
		sResponseNumber = (String)mapresult.get("RES_SEQ");
		sAuthType = (String)mapresult.get("AUTH_TYPE");
		sName = (String)mapresult.get("NAME");
		sBirthDate = (String)mapresult.get("BIRTHDATE");
		sGender = (String)mapresult.get("GENDER");
		sNationalInfo = (String)mapresult.get("NATIONALINFO");
		sDupInfo = (String)mapresult.get("DI");
		sConnInfo = (String)mapresult.get("CI");
		sMobileNo = (String)mapresult.get("MOBILE_NO");
		if(sMobileNo == null){
			sMobileNo ="";
		}else{
			sMobileNo = sMobileNo;
		}
        sMobileCo = (String)mapresult.get("MOBILE_CO");
/* System.out.println("sRequestNumber=>"+sRequestNumber);
System.out.println("sResponseNumber=>"+sResponseNumber);
System.out.println("sAuthType=>"+sAuthType);
System.out.println("sName=>"+sName);
System.out.println("sBirthDate=>"+sBirthDate);
System.out.println("sGender=>"+sGender);
System.out.println("sNationalInfo=>"+sNationalInfo);
System.out.println("sDupInfo=>"+sDupInfo);
System.out.println("sConnInfo=>"+sConnInfo);
System.out.println("sMobileNo=>"+sMobileNo);
System.out.println("sMobileCo=>"+sMobileCo); */
		// checkplus_success ���������� ����� null �� ���, ���� ���Ǵ� ��������ڿ��� �Ͻñ� �ٶ��ϴ�.
		
		String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
		if (!sRequestNumber.equals(session_sRequestNumber)) {
			sMessage = "���ǰ��� �ٸ��ϴ�. �ùٸ� ��η� �����Ͻñ� �ٶ��ϴ�.";
			sResponseNumber = "";
			sAuthType = "";
		} else {

			try {
				User user = new User();
				user.setUserName(sName); // name
				user.setPrivatecode(sDupInfo); // di
				user.setBirthday(sBirthDate); // birthday
				if(sMobileNo == ""){
					user.setTel1("");					
					user.setTel2("");					
					user.setTel3("");					
				}else{
				if (sMobileNo.length() < 11) {
					user.setTel1(sMobileNo.substring(0, 3));
					user.setTel2(sMobileNo.substring(3, 6));
					user.setTel3(sMobileNo.substring(6, 10));
				} else {
					user.setTel1(sMobileNo.substring(0, 3));
					user.setTel2(sMobileNo.substring(3, 7));
					user.setTel3(sMobileNo.substring(7, 11));
				}
				}
				user.setGender(sGender);
				user.setAge(UserUtil.calculateManAge(user.getBirthday()));
				user.setUserType(UserType.MEMBER.getCode());

				List<Role> roles = new ArrayList<Role>();
				roles.add(new Role(RoleType.USER_TMP.getCode()));
				user.setAuthorities(roles);

				Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication); // ���� ����

			} catch (Exception e) {
				e.printStackTrace();
				SecurityContextHolder.getContext().setAuthentication(null); // ���� ���� �ʱ�ȭ
			}
			content.append("<script>");
			sReserved1 = sReserved1.replaceAll("%2FF", "/");
			sReserved1 = sReserved1.replaceAll("/NICE/", "");
			sReserved1 = sReserved1.replaceAll("%2F", "/");
			sReserved1 = sReserved1.replaceAll("%3F", "?");
			sReserved1 = sReserved1.replaceAll("%3D", "=");
			sReserved1 = sReserved1.replaceAll("%2F6amp%3B", "&");
			sReserved1 = sReserved1.replaceAll("&amp;", "&");
			content.append("opener.location.href = '" + sReserved1 + "';");
			content.append("window.close();");
			content.append("</script>");
			WriterUtil.flushJSAlert(response, content.toString());
		}
	} else { // fail
		if (iReturn == -1) {
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
		content.append("<script>");
		content.append("alert('" + sMessage + "');");
		content.append("window.close();");
		content.append("</script>");
		WriterUtil.flushJSAlert(response, content.toString());
	}
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
	<p><p><p><p>
	���������� �Ϸ� �Ǿ����ϴ�.<br>
	<table border=1>
		<tr>
			<td>��ȣȭ�� �ð�</td>
			<td><%= sCipherTime %> (YYMMDDHHMMSS)</td>
		</tr>
		<tr>
			<td>��û ��ȣ</td>
			<td><%= sRequestNumber %></td>
		</tr>
		<tr>
			<td>NICE���� ��ȣ</td>
			<td><%= sResponseNumber %></td>
		</tr>
		<tr>
			<td>��������</td>
			<td><%= sAuthType %></td>
		</tr>
				<tr>
			<td>����</td>
			<td><%= sName %></td>
		</tr>
				<tr>
			<td>�ߺ����� Ȯ�ΰ�(DI)</td>
			<td><%= sDupInfo %></td>
		</tr>
				<tr>
			<td>�������� Ȯ�ΰ�(CI)</td>
			<td><%= sConnInfo %></td>
		</tr>
				<tr>
			<td>�������</td>
			<td><%= sBirthDate %></td>
		</tr>
				<tr>
			<td>����</td>
			<td><%= sGender %></td>
		</tr>
				<tr>
			<td>��/�ܱ�������</td>
			<td><%= sNationalInfo %></td>
		</tr>
		<tr>
			<td>RESERVED1</td>
			<td><%= sReserved1 %></td>
		</tr>
		<tr>
			<td>RESERVED2</td>
			<td><%= sReserved2 %></td>
		</tr>
		<tr>
			<td>RESERVED3</td>
			<td><%= sReserved3 %></td>
		</tr>
	</table><br><br>
	<%= sMessage %><br>
	</center>
</body>
</html> --%>