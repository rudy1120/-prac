package egovframework.portal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {

	private static final Logger LOGGER = LogManager.getLogger();

	public FileUtil() {
		// default
	}

	/**
	 * 해당 파일 및 빈 상위 디렉토리를 모두 삭제한다.
	 *
	 * @param uploadDir
	 * @param filePath
	 * @param regex
	 * @return
	 * @throws IOException
	 */
	public static boolean removeFullFile(String uploadDir, String filePath, String regex) throws IOException {
		boolean isDelete = false;
		if (filePath == null) {
			return isDelete;
		}

		String[] arrPath = filePath.split(regex);
		for (int i = 0; i < arrPath.length; i++) {
			String fileName = "";
			int arrLength = arrPath.length - i;
			for (int j = 0; j < arrLength; j++) {
				if (j < arrLength - 1) {
					fileName += arrPath[j] + "/";
				} else {
					fileName += arrPath[j];
				}
			}

			File targetFile = new File(uploadDir + fileName);
			if (targetFile.exists()) {
				if (targetFile.isFile()) {
					isDelete = targetFile.delete();
				} else if (targetFile.listFiles().length == 0) {
					isDelete = targetFile.delete();
				}
			}
		}

		return isDelete;
	}

	private static final String IMG_EXTS[] = { "jpg", "jpeg", "bmp", "png", "gif", "webp", "bpg" };

	public static boolean isImage(String ext) {
		return StringUtil.isNotEmpty(ext) && StringUtil.contains(ext.toLowerCase(), IMG_EXTS);
	}

	/**
	 * 파라미터로 받은 URL에서 파일을 다운로드 합니다.
	 *
	 * @Method Name : downloadFileFromURL
	 * @param url
	 * @param savePath
	 * @param split
	 * @param showStatus
	 * @return
	 * @throws IOException
	 */
	public static boolean downloadFileFromURL(String url, String savePath, String split, boolean showStatus, String saveName) throws IOException {
		LOGGER.info("request URL : " + url);
		LOGGER.info("saveFileName : " + url.substring(url.lastIndexOf(split) + 1, url.length()));
		LOGGER.info("savePath : " + savePath);

		FileOutputStream fos = null;
		InputStream is = null;
		try {
			Thread.sleep(1000);
			URL u = new URL(url);
			File filePath = new File(savePath);
			File fileDir = filePath.getParentFile();
			filePath.mkdirs();
//			fos = new FileOutputStream(savePath+url.substring(url.lastIndexOf(split)+1, url.length()));
			fos = new FileOutputStream(savePath + saveName);
			is = u.openStream();
			byte[] buf = new byte[1024];
			double len = 0;
			double tmp = 0;
			double percent = 0;

			while ((len = is.read(buf)) > 0) {
				tmp += len / 1024 / 1024;
				percent = tmp / 1229 * 100;
//				if (showStatus) {
//					System.out.printf("%.2f", tmp);
//					LOGGER.info("MB Downloaded");
//				}
				fos.write(buf, 0, (int) len);
			}
			LOGGER.info(">>> " + savePath + url.substring(url.lastIndexOf("=") + 1, url.length()) + " download finish");
			return true;
		} catch (Exception e) {
			LOGGER.error(">> 다운로드 오류입니다.", e);
			return false;
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}

}