<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="egovframework.portal.util.TUtil"%>
<%
	String virtualNo = TUtil.nullTobaseStr(request.getParameter("virtualNo"), "");
	String realName = TUtil.nullTobaseStr(request.getParameter("realName"), "");
	String dupInfo = TUtil.nullTobaseStr(request.getParameter("dupInfo"), "");
	String birthday = TUtil.nullTobaseStr(request.getParameter("birthday"), "");
	
	String sex = TUtil.nullTobaseStr(request.getParameter("Sex"), "");
		
	//윈도우서버 사용시 적용, 리눅스 서버 적용시 주석처리
	realName = new String(realName.getBytes("8859_1"),"utf-8");
	
	HttpSession s = request.getSession(true);
	
	s.putValue("CheckRealName",realName);
	s.putValue("CheckRealCode", dupInfo);
	s.putValue("CheckRealBirth", birthday);
	s.putValue("sex", sex);

	if(s.getValue("backUrl") != null) {
		%>
		<script language="javascript">
			location.href = '<%=s.getValue("backUrl") %>'; 
		</script>
	<%    
		} else {    
	%>
		<script language="javascript">
			location.href = "/main.do"; 
		</script>
	<%
		}
	%>




