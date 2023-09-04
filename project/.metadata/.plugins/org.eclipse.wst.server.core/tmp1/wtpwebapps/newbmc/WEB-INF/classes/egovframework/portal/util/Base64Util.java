package egovframework.portal.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64 Encoding/Decoding 처리를 위한 Util 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.10.02		김혜민				최초 생성 및 코딩
 * 2017.01.05		J.Ryeon Lee			웹 취약 코드 보완
 * </pre>
 *
 * @author 김혜민
 * @since 2014.10.02
 */
public class Base64Util {

	private static final Logger LOGGER = LogManager.getLogger();

	public Base64Util() {
		// default
	}

	/**
	 * Base64Encoding 암호화. binany in ascii out
	 *
	 * @param encodeBytes encoding할 byte array
	 * @return encoding �� String
	 * @throws Exception exception
	 */
	public static String encode(byte[] encodeBytes) throws Exception {

		BASE64Encoder base64Encoder = new BASE64Encoder();
		ByteArrayInputStream bin = null;
		ByteArrayOutputStream bout = null;
		byte[] buf = null;

		try {
			bin = new ByteArrayInputStream(encodeBytes);
			bout = new ByteArrayOutputStream();

			try {
				base64Encoder.encodeBuffer(bin, bout);
			} catch (Exception e) {
				LOGGER.error(">> Base64Util encode - exception", e);
			}
			buf = bout.toByteArray();
		} catch (Exception e) {
			LOGGER.error(">> failed to encd base64", e);
		} finally {
			bin.close();
			bout.close();
		}

		return buf != null ? new String(buf).trim() : null;
	}

	/**
	 * Base64Decoding 복호화. binany out ascii in
	 *
	 * @param strDecode 대상 문자열
	 * @return 복호화 결과
	 * @throws Exception exception
	 */
	public static byte[] decode(String strDecode) throws Exception {

		byte[] buf = null;

		if (strDecode == null) {
			return buf;
		}

		BASE64Decoder base64Decoder = new BASE64Decoder();
		ByteArrayInputStream bin = null;
		ByteArrayOutputStream bout = null;

		try {
			bin = new ByteArrayInputStream(strDecode.getBytes());
			bout = new ByteArrayOutputStream();

			try {
				base64Decoder.decodeBuffer(bin, bout);
			} catch (Exception e) {
				LOGGER.error(">> Base64Util decode - exception", e);
			}

			buf = bout.toByteArray();
		} catch (Exception e) {
			LOGGER.error(">> failed to decode base 64", e);
		} finally {
			if (bin != null) {
				bin.close();
			}
			if (bout != null) {
				bout.close();
			}
		}

		return buf;

	}

}
