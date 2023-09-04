package egovframework.portal.common.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.util.SecurityUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.Tuple;
import egovframework.portal.util.UserUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 이미지 컨트롤러
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017.06.30		권태성				최초 생성 및 코딩
 * 2017.10.13		J.Ryeon Lee			권한 검증 로직 추가
 * 2019.06.05		김장섭				이미지 캐싱 처리를 위해 ETag 옵션 추가
 * </pre>
 *
 * @author 권태성
 * @since 2017. 6. 30.
 */
@Controller
public class ImgController {

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * 이미지 출력
	 *
	 * @param mode 출력모드 (origin, ratio, rate)
	 * @param basePath 저장 경로
	 * @param streFileNm 저장된 파일 이름
	 * @param srcFileNm 원본 파일 이름
	 * @param ext 확장자
	 * @param rate 비율 조정시 조정비율
	 * @param width 너비
	 * @param height 높이
	 * @param request request
	 * @param response response
	 * @throws Exception
	 */
	@RequestMapping("/common/imgView.do")
	public void imgView(@RequestParam("mode") String mode,
		@RequestParam("attachId") String attachId,
		@RequestParam("fileSn") String fileSn,
		@RequestParam(value = "rate", defaultValue = "0.0", required = false) float rate,
		@RequestParam(value = "width", defaultValue = "0", required = false) int width,
		@RequestParam(value = "height", defaultValue = "0", required = false) int height,
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		FileVO fvo = fileService.selectFileInf(new FileVO(SecurityUtil.ariaDec(attachId), SecurityUtil.ariaDec(fileSn)));
		/** ETag 설정 START**/
		String ifNoneMatch = request.getHeader("If-None-Match");
		String etag = fvo.getStreFileNm();
		if (etag.equals(ifNoneMatch)) {
			response.setStatus(304);
			return;
		}
		/** ETag 설정 END **/
		if (fileService.flushable(SessionUtil.isAdminLogin(request), fvo.getMaster(), UserUtil.getInstance())) {
			InputStream in = null;
			OutputStream os = null;

			try {
				String basePath = fvo.getFileStreCours();
				String streFileNm = fvo.getStreFileNm();
				String ext = fvo.getFileExtsn();

				Tuple<String, String> path = setFileInfo(mode, basePath, streFileNm, width, height);
				File srcFile = new File(basePath + streFileNm);
				File destDir = new File(path.getFirst());
				if (!destDir.exists()) {
					destDir.setExecutable(false, true);
					destDir.setReadable(true);
					destDir.setWritable(false, true);
					destDir.mkdirs();
				}

				if (srcFile.isFile()) {
					File destFile = new File(path.getFirst() + path.getSecond() + "." + ext);
					if (!destFile.isFile()) {
						BufferedImage bi = ImageIO.read(srcFile);
						if ("origin".equals(mode) || "originView".equals(mode)) {
							Thumbnails.of(srcFile).size(bi.getWidth(), bi.getHeight()).toFile(destFile);
						} else if (("ratio".equals(mode) || "ratioView".equals(mode))) {
							if (width == 0 || height == 0) {
								Thumbnails.of(srcFile).outputQuality(1.0).size(bi.getWidth(), bi.getHeight()).toFile(destFile);
							} else {
								Thumbnails.of(srcFile).outputQuality(1.0).size(width, height).toFile(destFile);
							}
						} else if ("rate".equals(mode)) {
							Thumbnails.of(srcFile).outputQuality(1.0).size((int) (bi.getWidth() * (width * 0.01)), (int) (bi.getHeight() * (rate * 0.01))).toFile(destFile);
						} else {
							return;
						}
					}
					destFile = new File(path.getFirst() + path.getSecond() + "." + ext);
					in = new FileInputStream(destFile);

					setHeader(response, destFile.length(), path.getSecond(), ext);
					/** ETag 설정 START **/
					response.setHeader("ETag", etag);
					/** ETag 설정 END **/
					os = response.getOutputStream();
					byte b[] = new byte[4096];
					int lng = 0;

					while ((lng = in.read(b)) > 0) {
						os.write(b, 0, lng);
					}
					os.flush();
				}
			} catch (IOException e) {
				LOGGER.error(e);
			} finally {
				if (os != null) {
					os.close();
				}

				if (in != null) {
					in.close();
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Tuple<String, String> setFileInfo(String mode, String basePath, String streFileNm, int width, int height) {
		String resizeSavePath = "";
		String resizeFileName = "";

		if ("origin".equals(mode) || "originView".equals(mode)) {
			resizeSavePath = basePath + "origin/";
			resizeFileName = streFileNm + "_origin";
		} else {
			resizeSavePath = basePath + "ratio/";
			resizeFileName = streFileNm + "_ratio_";
			if (width != 0) {
				resizeFileName = resizeFileName + width;
			}
			if (height != 0) {
				resizeFileName = resizeFileName + height;
			}
		}
		return new Tuple(resizeSavePath, resizeFileName);
	}

	public void setHeader(HttpServletResponse response, long length, String fileName, String ext) throws UnsupportedEncodingException {
		response.reset();
		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName + "." + ext).getBytes("euc-kr"), "8859_1"));
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + length);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
	}

}