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
<%@ page import="egovframework.portal.util.ImgViewerUtil"%>
<%@ page import="org.springframework.web.bind.ServletRequestUtils"%>
<%
	/**
	 * 영상 썸네일 뷰어
	 *
	 * 수정일		수정자			수정내용
	 * ------------	------------	-----------------------------
	 * 2015.10.27	J.Ryeon Lee		최초 생성 및 코드 작성(기존 thumbImgView.jsp 베이스)
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.10.27
	 */
%>
<%

	/* request parameters */

	// 파일 저장 경로
	String basePath = request.getParameter("basePath");
	// 시스템 파일명
	//String streFileNm = new String(TUtil.nullTobaseStr(request.getParameter("streFileNm"), "").getBytes("ISO-8859-1"), "utf-8");
	String streFileNm = TUtil.nullTobaseStr(request.getParameter("streFileNm"), "");

	/* 사용 변수 선언 */

	String originFileFullPath = basePath + streFileNm + ".jpg";
	String resizingFileSavePath = basePath + "";
	String resizingFileName = streFileNm;
	String resizingFileFullPath = resizingFileSavePath + resizingFileName + ".jpg";

	/* response flush */

	File origin = new File(originFileFullPath);
	if (origin.isFile()) { // 원본 파일 유무 체크
		File videoThumbnail = new File(resizingFileFullPath);
		if (!videoThumbnail.isFile()) { // 리사이징 파일이 없으면
			// 리사이징 파일 생성
			out.print("video thumbnail resizing start");
			ImgViewerUtil.convertByWidth(resizingFileSavePath, originFileFullPath, resizingFileName, request.getParameter("width"), "jpg");
			// 파라미터 정리 : 리사이징된 파일 저장 경로, 원본 파일 경로, 원본 파일 풀경로(파일명까지), 리사이징 후 파일명, 가로 해상도, 파일확장자
			out.print("video thumbnail resizing end");
		}

		File file = new File(resizingFileFullPath);
		InputStream in = new FileInputStream(file);
		OutputStream os = null;

		// 리턴 데이터 설정
		response.reset();
		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String((resizingFileName + ".jpg").getBytes("euc-kr"), "8859_1"));
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Pragma", "no-cache;");
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