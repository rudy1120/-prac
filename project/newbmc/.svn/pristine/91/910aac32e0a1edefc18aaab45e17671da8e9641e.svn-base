<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.net.URLEncoder" %>
<% 
	final String UPLOAD_DIR = EgovProperties.getProperty("Globals.OpenDatafileStorePath");

	response.setContentType("application/x-msdownload");
	
	//String filename = new String(request.getParameter("fileName").getBytes("iso-8859-1"), "utf-8");//java.net.URLDecoder.decode(request.getParameter("fileName"));
	//String sysFilename = new String(request.getParameter("sysFilename").getBytes("iso-8859-1"), "utf-8");//java.net.URLDecoder.decode(request.getParameter("fileName"));
	String filename = request.getParameter("fileName");
	String sysFilename = request.getParameter("sysFilename");
	filename = filename.replaceAll("../","");
	filename  =filename.replaceAll("./","");	
	sysFilename = sysFilename.replaceAll("../","");
	sysFilename  =sysFilename.replaceAll("./","");
	 
	
	String filename2 = java.net.URLDecoder.decode(request.getParameter("fileName"));
	String sysFilename2 = sysFilename;
	//String filePath = new String(request.getParameter("filePath").getBytes("iso-8859-1"), "utf-8");
	String filePath = request.getParameter("filePath");
    filename = filename.replaceAll("../","");
    filename  =filename.replaceAll("./","");	
    sysFilename = sysFilename.replaceAll("../","");
    sysFilename  =sysFilename.replaceAll("./","");

	// webDataFiles 서브경로 위치 여부 ( F는 webDataFiles  C는 content 디렉토리 설정 )
	String fileMode = ServletRequestUtils.getStringParameter(request, "fileMode", "F");
	String site = ServletRequestUtils.getStringParameter(request, "site", "yangsan");
	String fileRoot = "";

	//첨부파일일경우
	if("TOUR".equals(fileMode)){
		fileRoot = EgovProperties.getProperty("Globals.CurtureStorePath");
		site = "";
	}else if("QR".equals(fileMode)){
		fileRoot = EgovProperties.getProperty("Globals.QRfileStorePath");
		site = "";
	}else if("BOOK".equals(fileMode)){
		fileRoot = EgovProperties.getProperty("Globals.EbookfileStorePath");
		site = "";
	}else if( "F".equals(fileMode)){
		//fileRoot = "/Users/TAE/Desktop/AXJ_UPLOAD/";
		fileRoot = UPLOAD_DIR;
		site = "";
	}else{	
		//fileRoot = "/Users/TAE/Desktop/AXJ_UPLOAD/content/"+site+"/";
		fileRoot = "/www/portal/files/"+site+"/opendata/content/";
	}
	
	
	String installFilePath = fileRoot+filePath+"/"+filename;
	
	//김장섭 크롬에서 중복헤더 오류 수정
	filename2 = filename2.replaceAll(",", "_");
	filename2 = filename2.replaceAll(" ", "_");
	filename2 = filename2.replaceAll("'", "_");
	
	sysFilename2 = sysFilename2.replaceAll(",", "_");
	sysFilename2 = sysFilename2.replaceAll(" ", "_");
	sysFilename2 = sysFilename2.replaceAll("'", "_");
	
	
	String userAgent = request.getHeader("User-Agent"); 
	  
	if(userAgent.indexOf("MSIE") > -1){
		sysFilename2 = URLEncoder.encode(sysFilename2, "utf-8");
	}else if (userAgent.indexOf("Trident") > -1) {		// IE11 문자열 깨짐 방지
		sysFilename2 = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	}else{
		sysFilename2 = new String(sysFilename2.getBytes("utf-8"), "iso-8859-1");
	}

	response.setHeader("Content-Disposition", "attachment; filename=\"" + sysFilename2 + "\";");
	response.setHeader("Content-Transfer-Encoding", "binary");
	
	File file = new File(installFilePath); 
	byte[] b = new byte[(int)file.length()]; 
	
	if(file.isFile()) { 
		BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file)); 
		BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream()); 
		int read = 0; 
		while ((read = fin.read(b)) != -1) { 
			outs.write(b,0,read); 
		} 
		outs.close(); 
		fin.close(); 
	} 
%>
