<!--
***************************************************************
* 파 일 명 : ServiceResponse.jsp
* 설       명 : G-PIN 인증 결과 페이지
* 작   성  자 : 김장섭

* 작   성  일 : 2016.02.03
* ======================================
* 변경자/변경일 : 
* 변경사유/내역 : 
* ======================================
* 변경자/변경일 :
* 변경사유/내역 :
* ======================================
***************************************************************
-->
<%@ page language = "java" contentType = "text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="gov.mogaha.gpin.sp.proxy.*" %>
<%@ page import="gov.mogaha.gpin.sp.util.SAMLConstants" %>
<%@ page import ="java.net.URLDecoder" %>
<%@page import="egovframework.portal.util.TUtil"%>

<%
    response.addHeader("Cache-Control", "private");
	GPinProxy proxy = GPinProxy.getInstance(this.getServletConfig().getServletContext());

	String[] attrNames = new String[] {"dupInfo", "virtualNo", "realName", "sex", "age", "birthDate", "nationalInfo", "authInfo", "GPIN_AQ_SERVICE_SITE_USER_CONFIRM"}; 

	String samlResponse = request.getParameter("SAMLResponse");
	String[] rets = proxy.parseSAMLResponse(samlResponse, attrNames);

	int attLenth = rets.length;

	String redirUrl = session.getAttribute("redirUrl")+"";
    redirUrl = TUtil.rplcDecRedirectUrl(redirUrl);
    redirUrl = URLDecoder.decode(redirUrl, "UTF-8");
    String mId = session.getAttribute("mId")+"";
	String resDate = session.getAttribute("resDate")+"";
    String svrName = (String)request.getServerName().toString();


	if(!rets[0].equals(""))
		session.setAttribute("dupInfo", rets[0]);
	if(!rets[1].equals(""))
		session.setAttribute("virtualNo", rets[1]);
	if(!rets[2].equals(""))
		session.setAttribute("realName", rets[2]);
	if(!rets[3].equals(""))
		session.setAttribute("sex", rets[3]);
	if(!rets[4].equals(""))
		session.setAttribute("age", rets[4]);
	if(!rets[5].equals(""))
		session.setAttribute("birthDate", rets[5]);
	if(!rets[6].equals(""))
		session.setAttribute("nationalInfo", rets[6]);
	if(!rets[7].equals(""))
		session.setAttribute("authInfo", rets[7]);
	if(!rets[8].equals(""))
		session.setAttribute("GPIN_AQ_SERVICE_SITE_USER_CONFIRM", rets[8]);
	
	String gpinAuthRetPage = (String)session.getAttribute("gpinAuthRetPage");	
	String dupInfo = (String)session.getAttribute("dupInfo");
	String userName = (String)session.getAttribute("realName");
	String birYMD = (String)session.getAttribute("birthDate");	
	//String cellNo = (String)session.getAttribute("realName");
	dupInfo = dupInfo.trim(); 

	// 웹취약점 처리(2014.10.01)
    userName.replaceAll("<","&lt;").replaceAll(">","&gt;");
    dupInfo.replaceAll("<","&lt;").replaceAll(">","&gt;");
    //reqNum.replaceAll("<","&lt;").replaceAll(">","&gt;");
    redirUrl.replaceAll("<","&lt;").replaceAll(">","&gt;");

	
	redirUrl = redirUrl;
	if(!mId.equals("")) redirUrl +="&mId="+mId;
	if(!resDate.equals("")) redirUrl +="&resDate="+resDate;


	HttpSession s = request.getSession(true);
	s.putValue("CheckRealName",userName);
	s.putValue("CheckRealCode", dupInfo);
	s.putValue("CheckRealBirth", birYMD);	
	s.putValue("authType", "gpin");
	//s.putValue("reqNum", reqNum);
	//s.putValue("cellNo", cellNo);
	
%>
<html>
<head>
    <script type="text/javascript" src="/js/common.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript">
       function end() {			
			opener.location.href = '<%=redirUrl%>'
			self.close();
       }       
    </script>
</head>
<body onload="end();">
</body>
</html>
