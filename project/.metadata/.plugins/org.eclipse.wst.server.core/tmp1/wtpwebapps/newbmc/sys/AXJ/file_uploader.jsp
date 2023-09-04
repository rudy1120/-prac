<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.UUID"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<% 
 /*
  * @Class Name : file_uploader.jsp
  * @Description : AXUploader5와 연계되는 파일업로더
  * @  수정일       수정자            수정내용
  * @ -------    --------    ---------------------------
  * @ 2014-10-30   권태성           최초 생성
  * @ --------------------------------------------------
  */
%>

<%
String name = "";
PrintWriter pirntWriter = response.getWriter();
JSONObject jsonObject = new JSONObject();

try{

	if (ServletFileUpload.isMultipartContent(request)){
	    ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
	    uploadHandler.setHeaderEncoding("UTF-8");
	    List<FileItem> items = uploadHandler.parseRequest(request);

	    for (FileItem item : items) {
	            if(item.getSize() > 0 && item.getSize() > 100) {
	                //String name = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
	                // 기존 상단 코드를 막고 하단코드를 이용
	                long fileSize = item.getSize();
	                name = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
	                String filename_ext = name.substring(name.lastIndexOf(".")+1);
	                filename_ext = filename_ext.toLowerCase();
	                String[] allow_file = {"jpg","png","bmp","gif"};
	                int cnt = 0;
	                for(int i=0; i<allow_file.length; i++) {
	                    if(filename_ext.equals(allow_file[i])){
	                        cnt++;
	                    }
	                }
	                if(cnt == 0) {
	                	jsonObject.put("error", "true");
	                	pirntWriter.println(jsonObject.toString());
	                } else {
	                     
	                    //파일 기본경로
	                    //String dftFilePath = request.getServletContext().getRealPath("/");
	                    //파일 기본경로 _ 상세경로
						//String filePath = "/webdata/egov_uploadFile/PHOTO/temp/"; //로컬
						String filePath = "/home/webdata/egov_uploadFile/PHOTO/temp/"; //실서버
	
	                    File file = null;
	                    file = new File(filePath);

	                    if(!file.exists()) {
	                        file.mkdirs();
	                    }

	                    String realFileNm = "";
	                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	                    String today= formatter.format(new java.util.Date());
	                    realFileNm = today+UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."));

	                    String rlFileNm = filePath + realFileNm;
	                    ///////////////// 서버에 파일쓰기 ///////////////// 
	                    InputStream is = item.getInputStream();
	                    OutputStream os=new FileOutputStream(rlFileNm);
	                    int numRead;
	                    byte b[] = new byte[(int)item.getSize()];
	                    while((numRead = is.read(b,0,b.length)) != -1){
	                        os.write(b,0,numRead);
	                    }
	                    if(is != null) {
	                        is.close();
	                    }
	                    os.flush();
	                    os.close();
	                    ///////////////// 서버에 파일쓰기 /////////////////
						
	                    jsonObject.put("name", name);
	                    jsonObject.put("type", filename_ext);
	                    jsonObject.put("saveName", realFileNm);
	                    jsonObject.put("fileSize", fileSize);
	                    jsonObject.put("uploadedPath", filePath);
	                	pirntWriter.println(jsonObject.toString());
	
	                }
	            }
	            /*
	            else {
	            	jsonObject.put("error", "true");
	            	pirntWriter.println(jsonObject.toString());
	            }
	            */
	    }
	}else {
		jsonObject.put("error", "true");
		pirntWriter.println(jsonObject.toString());
	}

}catch(Exception e){
	e.printStackTrace();
	jsonObject.put("error", "true");
	pirntWriter.println(jsonObject.toString());

}
%>