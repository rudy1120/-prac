<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ page contentType="image/png;charset=utf-8" %>
<%@ page import="java.io.*" %>
<%@ page import="com.google.zxing.qrcode.QRCodeWriter, com.google.zxing.common.BitMatrix, com.google.zxing.BarcodeFormat, com.google.zxing.client.j2se.MatrixToImageWriter" %>
<%
	QRCodeWriter q = new QRCodeWriter();

	final String UPLOAD_DIR_PROGRAM = EgovProperties.getProperty("Globals.QRfileStorePath");
	//String UPLOAD_DIR_PROGRAM = "/Users/TAE/Desktop/AXJ_UPLOAD";

	//String title = request.getParameter("title");
	String title = new String(request.getParameter("title").getBytes("8859_1"), "utf-8");
	//String name = request.getParameter("name");
	String name = new String(request.getParameter("name").getBytes("8859_1"), "utf-8");
	//String company = request.getParameter("company");
	String company = new String(request.getParameter("company").getBytes("8859_1"), "utf-8");
	//String tel = request.getParameter("tel");
	String tel = new String(request.getParameter("tel").getBytes("8859_1"), "utf-8");
	//String email = request.getParameter("email");
	String email = new String(request.getParameter("email").getBytes("8859_1"), "utf-8");
	//String address = request.getParameter("address");
	String address = new String(request.getParameter("address").getBytes("8859_1"), "utf-8");
	//String website = request.getParameter("website");
	String website = new String(request.getParameter("website").getBytes("8859_1"), "utf-8");
	//String memo = request.getParameter("memo");
	String memo = new String(request.getParameter("memo").getBytes("8859_1"), "utf-8");
	String fileName = request.getParameter("fileName");
	
	String text = "MECARD:N:"+name+"("+company+");TEL:"+tel+";EMAIL:"+email+";NOTE:"+memo+";URL:"+website+";ADR:"+address+";";

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
	//file = new File(Constant.UPLOAD_DIR +  "/qrcode/", fileName + ".png");
	//file2 = new File(Constant.UPLOAD_DIR +  "/qrcode/", fileName + ".gif");	
	file = new File(UPLOAD_DIR_PROGRAM , fileName + ".png");
	file2 = new File(UPLOAD_DIR_PROGRAM , fileName + ".gif");
	
	MatrixToImageWriter.writeToFile(bitMatrix, "png", file);
	MatrixToImageWriter.writeToFile(bitMatrix, "gif", file2);

	outputStream.flush();
	outputStream.close();
%>