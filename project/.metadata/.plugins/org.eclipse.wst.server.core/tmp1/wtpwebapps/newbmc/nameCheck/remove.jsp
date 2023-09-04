<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	HttpSession s = request.getSession(true);
	s.invalidate();

	String backResetUrl = null;
	backResetUrl = request.getParameter("backResetUrl");

	String mId = null;
	mId = request.getParameter("mId");

	String siteCode = null;
	siteCode = request.getParameter("siteCode");

	backResetUrl = URLDecoder.decode(backResetUrl, "euc-kr");
	//if(siteCode!=null && !"".equals(siteCode))
	//response.sendRedirect(backResetUrl+"&siteCode="+siteCode +"&mId="+mId);
	//else
	if (backResetUrl.indexOf("main.do") > -1) {
		response.sendRedirect(backResetUrl);
	} else {
		response.sendRedirect(backResetUrl + "&mId=" + mId);
	}
%>
