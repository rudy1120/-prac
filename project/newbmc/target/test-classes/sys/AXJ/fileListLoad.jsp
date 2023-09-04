<%@page import="java.util.Iterator"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.PrintWriter"%>
<% 
 /*
  * @Class Name : fileListLoad.jsp
  * @Description : AXUploader5와 연계되는 파일목록
  * @  수정일       수정자            수정내용
  * @ -------    --------    ---------------------------
  * @ 2014-10-31   권태성           최초 생성
  * @ --------------------------------------------------
  */
%>
<%
/*
	AXUploader의 파일 목록에 추가할 파일 목록을 세팅해주어야 합니다.
	return 해줘야 할 JSON 형식은 아래를 참조해주세요.
	ex) {id:'MF_AX_0000', name:'fileName', saveName:'saveName', type:'jpg', fileSize:'fileSize', uploadedPath:'uploadedPath', thumbUrl:'thumbUrl'}
	여기서 사용하는 ID는 파일 리스트 상에 AXUploader가 각 파일을 구분하기위한 ID 입니다.
	
	상황에 따라 따로 구현을 해야하므로 이 페이지에는 코딩을 해두지 않았습니다.
*/
%>