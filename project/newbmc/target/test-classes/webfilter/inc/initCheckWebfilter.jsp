<%@page import="java.net.*,java.io.*"%>
<%
	Socket s = null;
	String wfcontextRoot = "";
	String wfServerAddress="121.144.171.69"; 
	try{
		s = new Socket();
		s.connect(new InetSocketAddress(wfServerAddress,80),3000); 
		out.print("<script type='text/javascript' src='"+wfcontextRoot+"/webfilter/js/webfilter.js' defer='defer'></script>");
		out.print("<iframe id='webfilterTargetFrame'title='webfilter' name='webfilterTargetFrame' width='0' height='0' frameborder='0' scrolling='no' noresize></iframe>");
	}catch(Exception e){
		out.print("<script type='text/javascript' src='"+wfcontextRoot+"/webfilter/js/webfilterBypass.js' defer='defer'></script>");
		out.print("<iframe id='webfilterSmsFrame' title='webfilter'  name='webfilterSmsFrame' width='0' src='"+wfcontextRoot+"/webfilter/html/webfilterBypass.html' height='0' frameborder='0' scrolling='no' noresize></iframe>");
	}finally{
		if( s != null ){
			try{
				s.close();
			}catch(Exception ignore){}
		}
	}
%>