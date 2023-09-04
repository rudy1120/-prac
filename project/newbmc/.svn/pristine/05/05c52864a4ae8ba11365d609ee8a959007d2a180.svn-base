package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.star.lang.IllegalArgumentException;

import ch.qos.logback.core.net.SyslogOutputStream;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.sys.common.service.FileDownInfoMngService;
import egovframework.portal.util.SecurityUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;
import egovframework.portal.util.WriterUtil;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * ------------		-------------		---------------------------
 * 2009.03.25		이삼섭				최초 생성
 * 2014.02.24		이기하				IE11 브라우저 한글 파일 다운로드시 에러 수정
 * 2015.10.27		J.Ryeong Lee		mp4 다운로드시 파일명에 확장명을 추가하도록 수정 (동영상은 업로드시 확장명 그대로 서버에 저장됨)
 * 2016.05.16		J.Ryeon Lee			파일 다운로드 카운터 추가
 * 2017.07.03		권태성				첨부파일 파라미터 암호화 처리
 * 2017.07.11		권태성				첨부파일 권한 처리
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 */
@Controller
public class EgovFileDownloadController {

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	@Autowired
	protected FileDownInfoMngService fileDownInfoMngService;

	private static final Logger LOG = Logger.getLogger(EgovFileDownloadController.class.getName());

	/** 첨부파일 다운로드 카운팅 증가 */
	@ResponseBody
	@RequestMapping("/cmm/fms/fileDown/cnt.do")
	public String bbsFileDownCnt(Map<String, Object> commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String atchFileId = SecurityUtil.ariaDec((String) commandMap.get("atchFileId"));
		String fileSn = SecurityUtil.ariaDec((String) commandMap.get("fileSn"));

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO fvo = fileService.selectFileInf(fileVO);

		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		if (fvo != null) {
			fileDownInfoMngService.incrementCnt(fvo);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString();
	}

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		} else if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private String setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);
//		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
			encodedFilename = URLEncoder.encode(filename.getBytes("UTF-8").toString(), "8859_1").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}

		return encodedFilename;
	}

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmm/fms/FileDown.do")
	public void cvplFileDownload(Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String atchFileId = SecurityUtil.ariaDec((String) commandMap.get("atchFileId"));
		String fileSn = SecurityUtil.ariaDec((String) commandMap.get("fileSn"));

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO fvo = fileService.selectFileInf(fileVO);

		/*
		 * 2017.07.11 권태성 추가
		 * 게시판이 아닌 일반 프로그램의 경우 권한 체크가 없으므로 기존으로 TRUE
		 * 게시판의 경우 권한 체크를 해야하므로 관리자인 경우에는 TRUE, 관리자가 아닐 경우 게시판 권한으로 체크
		 */
//		Boolean readable = (fvo.getFileStreCours().toLowerCase().contains("bbs") ? (SessionUtil.isAdminLogin(request) ? Boolean.TRUE : bbsHelperService.readable(atchFileId)) : Boolean.TRUE);

		if (fileService.flushable(SessionUtil.isAdminLogin(request), fvo.getMaster(), UserUtil.getInstance())) {

			File uFile = new File(fvo.getFileStreCours(), concatExtIfNeed(fvo.getStreFileNm(), fvo.getFileExtsn()).replace(".mp4.mp4", ".mp4"));
			if (uFile.isFile() == false) {
				uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
			}

			int fSize = (int) uFile.length();

			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				// response.setBufferSize(fSize);	// OutOfMemeory 발생
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
				setDisposition(fvo.getOrignlFileNm(), request, response);
				response.setContentLength(fSize);

				/*
				 * FileCopyUtils.copy(in, response.getOutputStream());
				 * in.close();
				 * response.getOutputStream().flush();
				 * response.getOutputStream().close();
				 */
				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (Exception ex) {
					// ex.printStackTrace();
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error
					LOG.debug("IGNORED: " + ex.getMessage());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							// no-op
							LOG.debug("IGNORED: " + ignore.getMessage());
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							// no-op
							LOG.debug("IGNORED: " + ignore.getMessage());
						}
					}
				}

			} else {
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				printwriter.flush();
				printwriter.close();
			}

		} else {

			WriterUtil.flushJSInvalidAccess(response);

		}

	}

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmm/fms/FarmFileDown.do")
	public void cvplFarmFileDownload(Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String atchFileId = (String) commandMap.get("atchFileId");
		String fileSn = (String) commandMap.get("fileSn");

		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		// if (isAuthenticated) {

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO fvo = fileService.selectFileInf(fileVO);

		File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());

		int fSize = (int) uFile.length();

//		boolean file = uFile.isFile();
//		boolean exists = uFile.exists();

		if (fSize > 0) {
//			String mimetype = "application/octet-stream";

			// response.setBufferSize(fSize);	// OutOfMemeory 발생
//			response.setContentType(mimetype);
//			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
//			setDisposition(fvo.getOrignlFileNm(), request, response);
//			response.setContentLength(fSize);

			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			setDisposition(fvo.getOrignlFileNm(), request, response);
			response.setContentLength(fSize);

			/*
			 * FileCopyUtils.copy(in, response.getOutputStream());
			 * in.close();
			 * response.getOutputStream().flush();
			 * response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				// ex.printStackTrace();
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				LOG.debug("IGNORED: " + ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						// no-op
						LOG.debug("IGNORED: " + ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						// no-op
						LOG.debug("IGNORED: " + ignore.getMessage());
					}
				}
			}

		} else {
//			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
		// }
	}

	/** 영상 파일의 경우 확장자를 붙여 저장했으므로 이하의 처리가 필요함 */
	private String concatExtIfNeed(String streFileNm, String fileExtsn) {
		return StringUtil.isNotBlank(fileExtsn) && fileExtsn.equalsIgnoreCase("mp4") ? streFileNm + "." + fileExtsn : streFileNm;
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private String setDisposition2(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		/*if (browser.equals("MSIE")) {
			encodedFilename = new String(filename.getBytes("8859_1"), "EUC-KR");
			response.setHeader("Content-Disposition", dispositionPrefix + URLEncoder.encode(encodedFilename, "UTF-8"));
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = new String(filename.getBytes("8859_1"), "EUC-KR");
			response.setHeader("Content-Disposition", dispositionPrefix + URLEncoder.encode(encodedFilename, "UTF-8"));
		} else if (browser.equals("Firefox")) {
			encodedFilename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			response.setHeader("Content-Disposition", dispositionPrefix + filename.replaceAll(" ", ""));
		} else if (browser.equals("Opera")) {
			encodedFilename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			response.setHeader("Content-Disposition", dispositionPrefix + filename.replaceAll(" ", ""));
		} else if (browser.equals("Chrome")) {
			encodedFilename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			encodedFilename = URLDecoder.decode(encodedFilename, "ISO-8859-1");
			response.setHeader("Content-Disposition", dispositionPrefix + filename.replaceAll(" ", ""));
		} else {
			throw new IOException("Not supported browser");
		}

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}*/
		
		encodedFilename = filename;
		response.setHeader("Content-Disposition", dispositionPrefix + URLEncoder.encode(filename, "UTF-8")+";");

		return encodedFilename;
	}

	@RequestMapping(value = "/FileDown_direct.do")
	public void FileDown_direct(Map<String, Object> commandMap,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String fs = File.separator;

		String filename = request.getParameter("file");
		
		String disp_str = setDisposition2(filename, request, response);

		String path = ServletRequestUtils.getStringParameter(request, "path", "");

		path = new String(path.getBytes("ISO-8859-1"), "UTF-8") + fs;

		String site = ServletRequestUtils.getStringParameter(request, "site", "");

		if (!site.equals("")) { //site 외 관리자에서 다운로드 호출시에는 site 빈값으로.. (ex. QR코드)
			if (site.equals("portal")) site = "";
			if (!site.equals("sys")) {
				site = StringUtils.capitalize(site);
				site = "portal" + site;
			}
		}

		Boolean isAuthenticated = true; // add by dantes98 2013.03.11, 모바일에서도 다운로드 받을 수 있도록 인증을 없앰.
		// EgovUserDetailsHelper.isAuthenticated();
		if (isAuthenticated) {

			File uFile = new File(request.getSession().getServletContext().getRealPath("/") + site + fs + "download" + path + disp_str);
			
			String basePath = new File(request.getSession().getServletContext().getRealPath("/") + site + fs + "download" + path ).getAbsolutePath();
			String dataPath = uFile.getCanonicalPath();
			
			int fSize = (int) uFile.length();
			
			if(!dataPath.startsWith(basePath)) {
				throw new IllegalArgumentException(dataPath + "is not valid path");
				
			}
			
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				// response.setBufferSize(fSize); // OutOfMemeory 발생
				response.setContentType(mimetype);

				response.setContentLength(fSize);

				/*
				 * FileCopyUtils.copy(in, response.getOutputStream());
				 * in.close(); response.getOutputStream().flush();
				 * response.getOutputStream().close();
				 */
				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (Exception ex) {
					ex.printStackTrace();
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error

					PrintWriter printwriter = response.getWriter();
					printwriter.println("<html>");
					printwriter.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head>");
					printwriter.println("<body>");
					printwriter.println("<h4>Could not get file name: " + disp_str + "</h4>");
					printwriter.println("<script>window.close();</script>");
					printwriter.println("</body>");
					printwriter.println("</html>");
					printwriter.flush();
					printwriter.close();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							// no-op
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							// no-op
						}
					}
				}

			} else {

				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<head><meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'/></head>");
				printwriter.println("<body>");
				printwriter.println("<h4>Could not get file name: " + disp_str + "</h4>");
				printwriter.println("<script>alert('파일이 없습니다.');window.close();</script>");
				printwriter.println("</body>");

				printwriter.println("</html>");
				printwriter.flush();
				printwriter.close();
			}
		}
	}

}
