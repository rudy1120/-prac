package egovframework.portal.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 이미지 뷰어 유틸
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 10. 6.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2016. 10. 6.
 */
public class ImgViewerUtil {

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * 원본 사이즈로 변환
	 * @param uploadDir
	 * @param original
	 * @param newFileName
	 * @param format
	 * @return
	 * @throws IOException
	 */
	public static boolean convertByOriginalSize(String uploadDir, String original, String newFileName, String format) throws IOException {
		Image img = new ImageIcon(original).getImage();
		return convert(uploadDir, original, newFileName, img.getWidth(null), img.getHeight(null), format);
	}

	/**
	 * width 기준으로 이미지 변환
	 * @param uploadDir
	 * @param original
	 * @param newFileName
	 * @param resizeWidth
	 * @param format
	 * @return
	 * @throws IOException
	 */
	public static boolean convertByWidth(String uploadDir, String original, String newFileName, String resizeWidth, String format) throws IOException {
		Image img = new ImageIcon(original).getImage();
		int imgWidth = img.getWidth(null);
		int imgHeight = img.getHeight(null);
		float ratio = Float.parseFloat(resizeWidth) / imgWidth;

		return convert(uploadDir, original, newFileName, (int) (imgWidth * ratio), (int) (imgHeight * ratio), format);
	}

	/**
	 * width, height 사이즈 기준으로 변환
	 * @param uploadDir
	 * @param original
	 * @param newFileName
	 * @param resizeWidth
	 * @param resizeHeight
	 * @param format
	 * @return
	 * @throws IOException
	 */
	public static boolean convertBySize(String uploadDir, String original, String newFileName, String resizeWidth, String resizeHeight, String format) throws IOException {
		return convert(uploadDir, original, newFileName, Integer.parseInt(resizeWidth), Integer.parseInt(resizeHeight), format);
	}

	/**
	 * 이미지 변환 처리
	 * @param uploadDir
	 * @param original
	 * @param newFileName
	 * @param width
	 * @param height
	 * @param format
	 * @return
	 * @throws IOException
	 */
	public static boolean convert(String uploadDir, String original, String newFileName, int width, int height, String format) throws IOException {
		new File(uploadDir).mkdir();
		new File(uploadDir + File.separator).mkdir();
		File saveFile = new File(uploadDir + File.separator + newFileName + "." + format);
		saveFile.createNewFile();

		try {
			return ImageIO.write(resize(original, width, height), format, saveFile);
		} catch (IOException e) {
			LOGGER.error("[util] 이미지 유틸 convert 중 오류 발생", e);
			return false;
		}
	}

	/**
	 * 이미지 리사이징 처리
	 * @param original
	 * @param scaledWidth
	 * @param scaledHeight
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage resize(String original, int scaledWidth, int scaledHeight) throws IOException {
		Image srcImg = ImageIO.read(new File(original));
		Image imgTarget = srcImg.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		int pixels[] = new int[scaledWidth * scaledHeight];
		PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, scaledWidth, scaledHeight, pixels, 0, scaledWidth);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			LOGGER.error("[util] 이미지 유틸 resize 중 오류 발생", e);
		} catch (Exception e) {
			LOGGER.error("[util] 이미지 유틸 resize 중 오류 발생", e);
		}
		//BufferedImage destImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
		BufferedImage destImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
		destImg.setRGB(0, 0, scaledWidth, scaledHeight, pixels, 0, scaledWidth);
		return destImg;
	}

}