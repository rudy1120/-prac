<%@page import="java.util.Iterator"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.PrintWriter"%>
<% 
 /*
  * @Class Name : file_delete.jsp
  * @Description : AXUploader5와 연계되는 파일삭제
  * @  수정일       수정자            수정내용
  * @ -------    --------    ---------------------------
  * @ 2014-10-30   권태성           최초 생성
  * @ 2014-10-31   권태성           파일 삭제 후 result 오류 수정
  * @ --------------------------------------------------
  */
%>
<%
	//request로 받은 파일 정보를 참조해서 파일 삭제 처리
	PrintWriter pirntWriter = response.getWriter();
	JSONObject jsonObject = new JSONObject();

	String fileSize = request.getParameter("fileSize");
	String id = request.getParameter("id");
	String saveName = request.getParameter("saveName");
	String uploadedPath = request.getParameter("uploadedPath");
	String name = request.getParameter("name");
	String type = request.getParameter("type");

	if(saveName.equals("") || uploadedPath.equals("")){
		jsonObject.put("error", "true");
		pirntWriter.println(jsonObject.toString());
	}else{
		File file = new File(uploadedPath+saveName);
			
		if(file.delete()){
			//삭제완료
			jsonObject.put("result", "ok");
			jsonObject.put("msg", "");
			pirntWriter.println(jsonObject.toString());
		}else{
			//삭제 실패
			jsonObject.put("result", "err");
			jsonObject.put("msg", "파일을 찾을 수 없습니다.");
			pirntWriter.println(jsonObject.toString());
		}
	}

%>