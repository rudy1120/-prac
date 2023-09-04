<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="egovframework.portal.unit.bmc.synap.TimeoutProcessKiller"%>
<%@ page import="java.net.URLEncoder"%>
<%     
	response.setHeader("Pragma", "no-cache" );
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-store");
	response.setHeader("Cache-Control", "no-cache" );

	String SN3HCV_HOME = "/home/synap/";
	String SN3HCV = SN3HCV_HOME + "sn3hcv_exe";
	String TEMPLATE = SN3HCV_HOME + "template";
	String MODULES = SN3HCV_HOME + "modules";
	
	String fileStreCours = request.getParameter("fileStreCours");
	String orignlFileNm = request.getParameter("orignlFileNm");
	String streFileNm = request.getParameter("streFileNm");
	
	// 변환결과를 저장할 디렉토리 (WAS 에서 접근 할 수 있는 경로)
	//String outputPath = "/home/synap/result";
	String outputPath = "/home/newbmc/ROOT/synap/result";
	//String filePath = "/home/synap/files";
	String filePath = fileStreCours + streFileNm;
	
	System.out.println("★★★★★ filePath=>"+filePath);
	
	String errorMessage = "";
	
	// 변환대상 파일이 존재하는지 확인
	File file = new File(filePath);
	if (file.exists()) {
		String fileName = file.getName();
		
		String fileNameUrl = URLEncoder.encode(fileName, "UTF-8");
		fileNameUrl = fileNameUrl.replaceAll("\\+", "%20");
		
		// 월별 폴더 생성
		Calendar cal = Calendar.getInstance();
		String dateString = String.format("%04d%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
		File targetDir = new File(outputPath + File.separator + dateString);
		
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		
		// 변환결과 체크 
		File htmlFile = new File(targetDir.getAbsolutePath() + "/" + fileName + ".xml");
		File htmlDir = new File(targetDir.getAbsolutePath() + "/" + fileName + ".files");
		
		int exitValue = 0;
		if (!htmlFile.exists() || !htmlDir.isDirectory()) {
			String[] cmd = {SN3HCV, "-t", TEMPLATE, "-mod_path", MODULES, filePath, targetDir.getAbsolutePath()}; 
            try {
//             	for(int i = 0; i<=cmd.length; i++){
//             	 out.println(cmd[i]);
            	 
//             	}
            	Timer t = new Timer();
            	Process proc = Runtime.getRuntime().exec(cmd);
            	
            	TimerTask killer = new TimeoutProcessKiller(proc);
            	t.schedule(killer, 60000); // 60초 (변환이 60초 안에 완료되지 않으면 프로세스 종료)
            	
            	exitValue = proc.waitFor();
            	killer.cancel();
            } catch (Exception e) {
            	e.printStackTrace();
            	exitValue = -1;
            }
		}
		
		if (exitValue == 0) {
			response.sendRedirect("https://www.bmc.busan.kr/synap/skin/doc.html?fn=" + fileNameUrl + "&rs=/synap/result/" + dateString + "/");
		} else {
			errorMessage = "변환실패 : " + exitValue;
		}
	} else {
		errorMessage = "변화실패 : 파일이 존재하지 않습니다.";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문서 미리보기</title>
</head>
<body>
	<%=errorMessage%>
</body>
</html>

