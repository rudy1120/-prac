<%@ page contentType="image/png;charset=utf-8" %>
<%@ page import="java.io.*" %>
<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ page import="com.google.zxing.qrcode.QRCodeWriter, com.google.zxing.common.BitMatrix, com.google.zxing.BarcodeFormat, com.google.zxing.client.j2se.MatrixToImageWriter" %>
<%
	final String UPLOAD_DIR_PROGRAM = EgovProperties.getProperty("Globals.QRfileStorePath");

	QRCodeWriter q = new QRCodeWriter();

	String fileName = request.getParameter("fileName");
	String website = new String(request.getParameter("website").getBytes("8859_1"), "utf-8");
	
	if(website.indexOf(";") > 0) website = website.replace(";", "=");
	if(website.indexOf("^") > 0) website = website.replace("^", "&");
	if(website.indexOf("@") > 0) website = website.replace("@", "?");
	
	String text = website;

	if( text == null ) {
		text = "";
	}
	
	int width = 100;

	String strWidth = request.getParameter("width");

	if( strWidth != null ) {
		width = Integer.parseInt(strWidth);
	}

	int height = 100;

	String strHeight = request.getParameter("height");

	if( strHeight != null ) {
		height = Integer.parseInt(strHeight);
	}
	
	//리눅스용 서버에 한글깨짐 방지를 위해 설정
	text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
	
	BitMatrix bitMatrix = q.encode(text, BarcodeFormat.QR_CODE, width, height);

	out.clear(); // clear buffer

	ServletOutputStream outputStream = response.getOutputStream();

	MatrixToImageWriter.writeToStream(bitMatrix, "gif", outputStream);
	
	File file = null;
	File file2 = null;
	file = new File(UPLOAD_DIR_PROGRAM, fileName);
	file2 = new File(UPLOAD_DIR_PROGRAM, fileName);	
	
	MatrixToImageWriter.writeToFile(bitMatrix, "png", file);
	MatrixToImageWriter.writeToFile(bitMatrix, "gif", file2);

	outputStream.flush();
	outputStream.close();
%>