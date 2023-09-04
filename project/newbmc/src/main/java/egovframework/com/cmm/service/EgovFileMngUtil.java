package egovframework.com.cmm.service;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jcodec.api.FrameGrab;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.portal.common.Constant;
import egovframework.portal.util.FileUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovStringUtil;

/**
 * @Class Name : EgovFileMngUtil.java
 * @Description : 메시지 처리 관련 유틸리티
 * @Modification Information
 *               수정일 수정자 수정내용
 *               ------- -------- ---------------------------
 *               2009.02.13 이삼섭 최초 생성
 *               2011.08.09 서준식 utl.fcc패키지와 Dependency제거를 위해 getTimeStamp()메서드 추가
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 02. 13
 * @version 1.0
 * @see
 */
@Component("EgovFileMngUtil")
public class EgovFileMngUtil {

	public static final int BUFF_SIZE = 2048;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
		int fileKey = fileKeyParam;

		String storePathString = "";
		String atchFileIdString = "";

		if ("".equals(storePath) || storePath == null) {
			storePathString = EgovProperties.getProperty("Globals.fileStorePath");
		} else {
			storePathString = EgovProperties.getProperty(storePath);
		}

		if ("".equals(atchFileId) || atchFileId == null) {
			atchFileIdString = idgenService.getNextStringId();
		} else {
			atchFileIdString = atchFileId;
		}

		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			//--------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			//--------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			}
			////------------------------------------

			int index = orginFileName.lastIndexOf(".");
			//String fileName = orginFileName.substring(0, index);
			String fileExt = orginFileName.substring(index + 1);
			String newName = KeyStr + getTimeStamp() + fileKey;
			long size = file.getSize();

			if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
			}

			fvo = new FileVO();
			fvo.setFileExtsn(fileExt);
			fvo.setFileStreCours(storePathString);
			fvo.setFileMg(Long.toString(size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));

			result.add(fvo);

			fileKey++;
		}

		return result;
	}

	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath, MultipartHttpServletRequest multiRequest) throws Exception {
		return parseFileInf(files, KeyStr, fileKeyParam, atchFileId, storePath, false, multiRequest);
	}

	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath, boolean extractThumbnail, MultipartHttpServletRequest multiRequest) throws Exception {
		int fileKey = fileKeyParam;
		int file_cn_idx = 0;

		String storePathString = "";
		String atchFileIdString = "";
		String[] file_cn = multiRequest.getParameterValues("file_cn");

//		int lastFileSn = -1;

		if (StringUtil.isNotBlank(atchFileId)) { // 기존 파일정보 파일내용 업데이트
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchFileId);
			List<FileVO> datas = fileMngService.selectFileInfs(fileVO);

			if (datas != null && datas.size() > 0) {
//				lastFileSn = Integer.parseInt(datas.get(datas.size() - 1).getFileSn());
				if (datas.size() > 0) {
//					int max = datas.size();
					if (file_cn != null && file_cn.length > 0 /* && file_cn.length >= max */) {
						for (FileVO fvo2 : datas) {
							if (FileUtil.isImage(fvo2.getFileExtsn())) {
								fvo2.setFileCn(file_cn[file_cn_idx++]);
								fileMngService.updateFileCn(fvo2);
							}
						}
					}
				}
			} else {
				fileKey = 0;
			}
		}

		if ("".equals(storePath) || storePath == null) {
			storePathString = EgovProperties.getProperty("Globals.fileStorePath");
		} else {
			storePathString = storePath;
//			storePathString = EgovProperties.getProperty(storePath);
		}

		if ("".equals(atchFileId) || atchFileId == null) {
			atchFileIdString = idgenService.getNextStringId();
		} else {
			atchFileIdString = atchFileId;
		}

		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;

		file_cn_idx = file_cn != null ? file_cn.length - 1 : -1;
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			//--------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			//--------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			} else if (StringUtil.contains(entry.getValue().getName(), Constant.IGNORE_FILE_NAMES)) { // 20160725 J.Ryeon Lee ADD
				continue;
			}
			////------------------------------------

			int index = orginFileName.lastIndexOf(".");
			//String fileName = orginFileName.substring(0, index);
			String fileExt = orginFileName.substring(index + 1);
			String newName = KeyStr + getTimeStamp() + fileKey;
			long size = file.getSize();

			//20190422 kjspro Add 파일확장자 화이트 리스트 체크
			if (StringUtil.contains(fileExt.toLowerCase(), EgovProperties.getPathProperty(Constant.PERMIT_FILE_EXT_KEY).split(","))) {
				if (!"".equals(orginFileName)) {
					filePath = storePathString + File.separator + newName;
					if (extractThumbnail && fileExt.equalsIgnoreCase("mp4")) { // video file dealing
						file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath + ".mp4"))); // movie save

						// ================ 20151027 J.Ryeon Lee movie thumbnail save ================

						try {
							int frameNumber = 150;
							File movie = new File(filePath + ".mp4");
							BufferedImage frame = FrameGrab.getFrame(movie, frameNumber);
							ImageIO.write(frame, "jpg", new File(filePath + ".jpg"));
						} catch (Exception e) {
							e.printStackTrace();
						}

						// ===========================================================================
					} else if (fileExt.equalsIgnoreCase("mp4")) {
						file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath + ".mp4")));
					} else {
						file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
					}
				}

				fvo = new FileVO();
				fvo.setFileExtsn(fileExt);
				fvo.setFileStreCours(storePathString);
				fvo.setFileMg(Long.toString(size));
				fvo.setOrignlFileNm(orginFileName);
				fvo.setStreFileNm(newName);
				fvo.setAtchFileId(atchFileIdString);
				fvo.setFileSn(String.valueOf(fileKey));
				if (file_cn != null && file_cn_idx > -1 && file_cn.length > file_cn_idx) {
					if (FileUtil.isImage(fvo.getFileExtsn())) {
						fvo.setFileCn(file_cn[file_cn_idx--]);
					}
				}

				result.add(fvo);
				fileKey++;
			}
		}

		List<FileVO> resultClone = new ArrayList<>();
		FileVO fileVOClone = null;
		for (int i = 0; i < result.size(); i++) {
			fileVOClone = new FileVO();
			BeanUtils.copyProperties(result.get(i), fileVOClone);
			resultClone.add(fileVOClone);
		}

		int lastIndex = result.size() - 1;
		for (int i = lastIndex; i > -1; i--) {
			for (int j = 0; j <= lastIndex; j++) {
				if (j + i == lastIndex) {
					result.get(i).setFileSn(resultClone.get(j).getFileSn());
				}
			}
		}

		return result;
	}

	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 * (복수 파일 등록을 위해 메소드 일부 분리 {@link #parseFileInf(Map, String, int, String, String, boolean, MultipartHttpServletRequest)}})
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 * @author J.Ryeon Lee
	 * @since 2016.07.25
	 */
	public FileVO parseFileInf(MultipartFile file, String alt, String KeyStr, String storePathString, boolean extractThumbnail) throws Exception {
		String orginFileName = file.getOriginalFilename();

		//--------------------------------------
		// 원 파일명이 없는 경우 처리
		// (첨부가 되지 않은 input file type)
		//--------------------------------------
		if (StringUtil.isBlank(orginFileName)) {
			return null;
		}
		//------------------------------------

		String filePath = null;
		FileVO fvo = null;
		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		int index = orginFileName.lastIndexOf(".");
		String fileExt = orginFileName.substring(index + 1);
		String newName = KeyStr + getTimeStamp() + 0;
		long size = file.getSize();

		if (StringUtil.isNotBlank(orginFileName)) {
			filePath = storePathString + File.separator + newName;
			if (extractThumbnail && fileExt.equalsIgnoreCase("mp4")) { // video file dealing
				file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath + ".mp4"))); // movie save

				/* ================ 20151027 J.Ryeon Lee movie thumbnail save ================ */

				try {
					int frameNumber = 150;
					File movie = new File(filePath + ".mp4");
					BufferedImage frame = FrameGrab.getFrame(movie, frameNumber);
					ImageIO.write(frame, "jpg", new File(filePath + ".jpg"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				/* =========================================================================== */
			} else {
				file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
			}
		}

		fvo = new FileVO();
		fvo.setFileExtsn(fileExt);
		fvo.setFileStreCours(storePathString);
		fvo.setFileMg(Long.toString(size));
		fvo.setOrignlFileNm(orginFileName);
		fvo.setStreFileNm(newName);
		fvo.setFileCn(alt);
		fvo.setAtchFileId(idgenService.getNextStringId());

		return fvo;
	}

	/**
	 * 첨부파일을 서버에 저장한다.
	 *
	 * @param file
	 * @param newName
	 * @param stordFilePath
	 * @throws Exception
	 */
	protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = file.getInputStream();
			File cFile = new File(stordFilePath);

			if (!cFile.isDirectory()) {
				boolean _flag = cFile.mkdir();
				if (!_flag) {
					throw new IOException("Directory creation Failed ");
				}
			}

			bos = new FileOutputStream(stordFilePath + File.separator + newName);

			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];

			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} finally {
			EgovResourceCloseHelper.close(bos, stream);
		}
	}

//	private static String getBrowser(HttpServletRequest request) {
//		String header = request.getHeader("User-Agent");
//		if (header.contains("MSIE")) {
//			return "MSIE";
//		} else if (header.contains("Chrome")) {
//			return "Chrome";
//		} else if (header.contains("Opera")) {
//			return "Opera";
//		}
//		return "Firefox";
//	}

	/**
	 * 서버의 파일을 다운로드한다.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String downFileName = "";
		String orgFileName = "";

		if ((String) request.getAttribute("downFile") == null) {
			downFileName = "";
		} else {
			downFileName = (String) request.getAttribute("downFile");
		}

		if ((String) request.getAttribute("orgFileName") == null) {
			orgFileName = "";
		} else {
			orgFileName = (String) request.getAttribute("orginFile");
		}

		orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");

		File file = new File(EgovWebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] buffer = new byte[BUFF_SIZE]; //buffer size 2K.

		String docName = URLEncoder.encode(orgFileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		response.setHeader("Content-Type", "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

//		response.setContentType("application/x-msdownload");
//		response.setHeader("Content-Disposition:", "attachment; filename=" + new String(orgFileName.getBytes(), "UTF-8"));
//		response.setHeader("Content-Transfer-Encoding", "binary");
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Expires", "0");

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;

			while ((read = fin.read(buffer)) != -1) {
				outs.write(buffer, 0, read);
			}
		} finally {
			EgovResourceCloseHelper.close(outs, fin);
		}
	}

	/**
	 * 첨부로 등록된 파일을 서버에 업로드한다.
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, String> uploadFile(MultipartFile file) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		//Write File 이후 Move File????
		String newName = "";
		String stordFilePath = EgovProperties.getProperty("Globals.fileStorePath");
		String orginFileName = file.getOriginalFilename();

		int index = orginFileName.lastIndexOf(".");
		//String fileName = orginFileName.substring(0, _index);
		String fileExt = orginFileName.substring(index + 1);
		long size = file.getSize();

		//newName 은 Naming Convention에 의해서 생성
		newName = getTimeStamp(); // 2012.11 KISA 보안조치
		writeFile(file, newName, stordFilePath);
		//storedFilePath는 지정
		map.put(Globals.ORIGIN_FILE_NM, orginFileName);
		map.put(Globals.UPLOAD_FILE_NM, newName);
		map.put(Globals.FILE_EXT, fileExt);
		map.put(Globals.FILE_PATH, stordFilePath);
		map.put(Globals.FILE_SIZE, String.valueOf(size));

		return map;
	}

	/**
	 * 파일을 실제 물리적인 경로에 생성한다.
	 *
	 * @param file
	 * @param newName
	 * @param stordFilePath
	 * @throws Exception
	 */
	protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = file.getInputStream();
			File cFile = new File(EgovWebUtil.filePathBlackList(stordFilePath));

			if (!cFile.isDirectory())
				cFile.mkdir();

			bos = new FileOutputStream(EgovWebUtil.filePathBlackList(stordFilePath + File.separator + newName));

			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];

			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} finally {
			EgovResourceCloseHelper.close(bos, stream);
		}
	}

	/**
	 * 서버 파일에 대하여 다운로드를 처리한다.
	 *
	 * @param response
	 * @param streFileNm 파일저장 경로가 포함된 형태
	 * @param orignFileNm
	 * @throws Exception
	 */
	public void downFile(HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
		String downFileName = streFileNm;
		String orgFileName = orignFileNm;

		File file = new File(downFileName);

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		int fSize = (int) file.length();
		if (fSize > 0) {
			BufferedInputStream in = null;

			try {
				in = new BufferedInputStream(new FileInputStream(file));

				String mimetype = "application/x-msdownload";

				//response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
				response.setContentLength(fSize);
				//response.setHeader("Content-Transfer-Encoding","binary");
				//response.setHeader("Pragma","no-cache");
				//response.setHeader("Expires","0");
				FileCopyUtils.copy(in, response.getOutputStream());
			} finally {
				EgovResourceCloseHelper.close(in);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}

		/*
		 * String uploadPath = propertiesService.getString("fileDir");
		 * File uFile = new File(uploadPath, requestedFile);
		 * int fSize = (int) uFile.length();
		 * if (fSize > 0) {
		 * BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));
		 * String mimetype = "text/html";
		 * //response.setBufferSize(fSize);
		 * response.setContentType(mimetype);
		 * response.setHeader("Content-Disposition", "attachment; filename=\"" + requestedFile + "\"");
		 * response.setContentLength(fSize);
		 * FileCopyUtils.copy(in, response.getOutputStream());
		 * in.close();
		 * response.getOutputStream().flush();
		 * response.getOutputStream().close();
		 * } else {
		 * response.setContentType("text/html");
		 * PrintWriter printwriter = response.getWriter();
		 * printwriter.println("<html>");
		 * printwriter.println("<br><br><br><h2>Could not get file name:<br>" + requestedFile + "</h2>");
		 * printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
		 * printwriter.println("<br><br><br>&copy; webAccess");
		 * printwriter.println("</html>");
		 * printwriter.flush();
		 * printwriter.close();
		 * }
		 * //
		 */

		/*
		 * response.setContentType("application/x-msdownload");
		 * response.setHeader("Content-Disposition:", "attachment; filename=" + new String(orgFileName.getBytes(),"UTF-8" ));
		 * response.setHeader("Content-Transfer-Encoding","binary");
		 * response.setHeader("Pragma","no-cache");
		 * response.setHeader("Expires","0");
		 * BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
		 * BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
		 * int read = 0;
		 * while ((read = fin.read(b)) != -1) {
		 * outs.write(b,0,read);
		 * }
		 * log.debug(this.getClass().getName()+" BufferedOutputStream Write Complete!!! ");
		 * outs.close();
		 * fin.close();
		 * //
		 */
	}

	/**
	 * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	 *
	 * @param
	 * @return Timestamp 값
	 * @see
	 */
	private static String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
	}

	/**
	 * 첨부파일에 대한 ID 정보를 취득한다.
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public String getNextIdString() throws Exception {
		String atchFileIdString = "";

		atchFileIdString = idgenService.getNextStringId();

		return atchFileIdString;
	}

	/* ========================================= 20151027 J.Ryeon Lee ADD ========================================= */

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	/**
	 * 첨부파일 등록/수정 처리. {@link #storeFileAndGetAttachIdByPath(MultipartHttpServletRequest, String, String, String, boolean)} 래퍼 메소드}
	 * 동영상 파일이어도 별도로 썸네일을 추출하지 않음
	 *
	 * @return 생성/기존 첨부파일 ID
	 */
	public String storeFileAndGetAttachId(final MultipartHttpServletRequest multiRequest, String fileSavePathPropKey, String fileNamePrefix, String attachId) throws Exception {
		return storeFileAndGetAttachIdByPath(multiRequest, EgovProperties.getProperty(fileSavePathPropKey), fileNamePrefix, attachId, false);
	}

	/**
	 * 첨부파일 등록/수정 처리. {@link #storeFileAndGetAttachIdByPath(MultipartHttpServletRequest, String, String, String, boolean)} 래퍼 메소드}
	 * 동영상 파일의 경우 별도로 썸네일 추출
	 *
	 * @return 생성/기존 첨부파일 ID
	 */
	public String storeFileAndGetAttachId(final MultipartHttpServletRequest multiRequest, String fileSavePathPropKey, String fileNamePrefix, String attachId, boolean extractThumbnail) throws Exception {
		return storeFileAndGetAttachIdByPath(multiRequest, EgovProperties.getProperty(fileSavePathPropKey), fileNamePrefix, attachId, extractThumbnail);
	}

	/**
	 * 첨부파일 등록/수정 처리.
	 *
	 * @param path 첨부파일 저장 경로
	 * @param fileNamePrefix 신규 파일명 생성에 사용하는 접두어. 예) prefix + 신규 파일명의 형태로 저장
	 * @param attachId 첨부파일 ID. 신규 등록의 경우에는 null을, 수정인 경우에는 기존 첨부파일ID를 할당해야 함
	 * @param extractThumbnail mp4 확장자의 경우 썸네일 추출 여부
	 * @return 생성/기존 첨부파일 ID
	 */
	@SuppressWarnings("rawtypes")
	public String storeFileAndGetAttachIdByPath(final MultipartHttpServletRequest multiRequest, String path, String fileNamePrefix, String attachId, boolean extractThumbnail) throws Exception {
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		/** 현황관리 개별 파일 추가 부분은 여기서 처리하지 않도록 remove 처리 **/
		Iterator iterator = files.entrySet().iterator();
		ArrayList<String> keys = new ArrayList<>();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if (String.valueOf(entry.getKey()).indexOf("dmt_") > -1) {
				keys.add(String.valueOf(entry.getKey()));
			}
		}
		for (String key : keys) {
			files.remove(key);
		}
		/** 현황관리 개별 파일 추가 부분은 여기서 처리하지 않도록 remove 끝 **/
		List<FileVO> _result = null;
		if (!files.isEmpty()) {
			if (StringUtil.isBlank(attachId)) { // insert
				_result = parseFileInf(files, fileNamePrefix, 0, "", path, extractThumbnail, multiRequest);

				return fileMngService.insertFileInfs(_result); // 파일이 생성되고 나면 생성된 첨부파일 ID를 리턴한다.
			}

			if (EgovStringUtil.isEmpty(attachId)) { // update
				List<FileVO> result = null;
				_result = parseFileInf(files, fileNamePrefix, 0, attachId, path, extractThumbnail, multiRequest);
				return fileMngService.insertFileInfs(result); // 파일이 생성되고 나면 생성된 첨부파일 ID를 리턴한다.
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(attachId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				_result = parseFileInf(files, fileNamePrefix, cnt, attachId, path, extractThumbnail, multiRequest);
				fileMngService.updateFileInfs(_result);
				return attachId;
			}
		} else {
			if (StringUtil.isNotBlank(attachId)) { // 기존 파일정보 파일내용 업데이트
				FileVO fileVO = new FileVO();
				fileVO.setAtchFileId(attachId);
				List<FileVO> datas = fileMngService.selectFileInfs(fileVO);
				String[] file_cn = multiRequest.getParameterValues("file_cn");
				int file_cn_idx = 0;

				if (datas != null && datas.size() > 0) {
//					lastFileSn = Integer.parseInt(datas.get(datas.size() - 1).getFileSn());
					if (datas.size() > 0) {
//						int max = datas.size();
						if (file_cn != null && file_cn.length > 0 /* && file_cn.length >= max */) {
							for (FileVO fvo2 : datas) {
								if (FileUtil.isImage(fvo2.getFileExtsn())) {
									fvo2.setFileCn(file_cn[file_cn_idx++]);
									fileMngService.updateFileCn(fvo2);
								}
							}
						}
					}
				}
			}
		}

		return attachId;
	}

	/* ========================================= 20160725 J.Ryeon Lee ADD ========================================= */

	/** 단일 첨부파일 등록 */
	public String storeFileAndGetAttachId(MultipartFile file, String atchFileId, String alt, String filePrefix, String fileSavePathPropKey) throws Exception {
		if (!file.isEmpty()) {
			FileVO fileVO = parseFileInf(file, alt, filePrefix, EgovProperties.getProperty(fileSavePathPropKey), Boolean.TRUE);
			fileVO.setFileSn("0");
			fileVO.setFileCn(fileVO.getOrignlFileNm());
			return fileMngService.insertFileInf(fileVO); // 파일이 생성되고 나면 생성된 첨부파일 ID를 리턴한다.
		}

		return atchFileId;
	}

}
