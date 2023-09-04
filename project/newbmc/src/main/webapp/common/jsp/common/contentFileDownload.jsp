<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.portal.util.TUtil"%>
<%@ page import="java.io.File"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLConnection"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="org.springframework.web.bind.ServletRequestUtils"%>
<%
	/**
	 * 컨텐츠 등록 파일 다운로드
	 *
	 * 수정일		수정자			수정내용
	 * ------------	------------	-----------------------------
	 * 2015.10.20	J.Ryeon Lee		최초 생성 및 코드 작성(기존 thumbImgView.jsp 베이스)
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.10.20
	 */
%>
<%

	/* 사용 변수 선언 */

	String[] tempFileUrl = null;
	String saveFolder = "";
	String filePath = "";
	
	// 파일 저장 경로
	String basePath = request.getParameter("path");
	// 시스템 파일명
	//String streFileNm = new String(TUtil.nullTobaseStr(request.getParameter("streFileNm"), "").getBytes("ISO-8859-1"),"utf-8");  	
  	String streFileNm = TUtil.nullTobaseStr(request.getParameter("file"), "");
  	//원본파일명
  	//String orignlFileNm = new String(TUtil.nullTobaseStr(request.getParameter("orignlFileNm"), "").getBytes("ISO-8859-1"),"utf-8");
  	String orignlFileNm = TUtil.nullTobaseStr(request.getParameter("file"), "");
	
	// 원본 파일 패스
	String rootPath = "/home/webdata/download"+basePath + "/";
	// 파일명, 확장자 추출
	String orgFileName = streFileNm;	

	//파일 저장경로 설정
	String orgFileFullPath = rootPath + orgFileName;

	// 원본 파일 유무 체크
	File oriFile = new File(orgFileFullPath);
	if (oriFile.isFile()) {	
		
		InputStream in = new FileInputStream(oriFile);
		OutputStream os = null;

		response.reset();
		// 리턴 데이터 설정
		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
		response.setHeader("Content-Disposition", "attachment; filename="+orignlFileNm);
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + oriFile.length());
// 		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		out.clear();
		pageContext.pushBody();
		os = response.getOutputStream();
		byte b[] = new byte[4096];
		int leng = 0;

		while ((leng = in.read(b)) > 0) {
			os.write(b, 0, leng);
		}
	}
%>