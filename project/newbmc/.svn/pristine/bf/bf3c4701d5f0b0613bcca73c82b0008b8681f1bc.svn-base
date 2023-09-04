package egovframework.portal.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yhdatabase.aria.ARIAenc;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;

/**
 * 암호화용
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2010.08.11		박준오				최초 생성 및 코딩
 * 2017.01.05		J.Ryeon Lee			시큐어 코딩 - MD5 코드 제거
 * 2017.01.09		J.Ryeon Lee			MD5/SHA256 제거 >> AES 알고리즘 적용
 * 2017.09.19		J.Ryeon Lee			AES >> SHA256 적용
 * </pre>
 *
 * @author 박준오
 * @since 2010.08.11
 */
public class SecurityUtil {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final String ENCRYPTION_KEY = EgovProperties.getProperty(Constant.AES_KEY);

	/** AES, SHA256 중복 적용 */
	public static String encrypt(String raw) {
		return encryptSHA256(aesEncrypt(raw));
	}

	/** SHA256 암호화 */
	public static String encryptSHA256(String raw) {
		if (StringUtil.isEmpty(raw)) {
			return null;
		}

		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(raw.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("", e);
			return null;
		}
	}

	/** AES128 암호화 */
	public static String aesEncrypt(String raw) {
		if (raw == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		Cipher cipher = null;

		try {
			cipher = javax.crypto.Cipher.getInstance("AES");
			SecretKeySpec secretKeySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

			byte byteData[] = cipher.doFinal(raw.getBytes("UTF-8"));
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString().toUpperCase();
		} catch (Exception e) {
			LOGGER.error(">> failed to encrypt/Exception", e);
		}

		return sb.toString();
	}

	/** AES128 복호화 */
	public static String aesDecrypt(String encrypted) {
		if (StringUtil.isBlank(encrypted)) {
			return null;
		}

		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

			byte[] original = cipher.doFinal(hexToByteArray(encrypted));
			return new String(original);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			LOGGER.error("", e);
		}

		return "error";
	}

	private static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		// 16진수 문자열을 byte로 변환
		byte[] byteArray = new byte[hex.length() / 2];

		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		return byteArray;
	}

	/**
	 * ARIA로 암호화
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String ariaEnc(String param) throws Exception {
		return ARIAenc.enc(param, new String(com.yhdatabase.common.util.StringUtil.hexEncode("YCMSGOVPORTAL".getBytes())));
	}

	/**
	 * ARIA로 복호화
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String ariaDec(String param) throws Exception {
		return ARIAenc.dec(param, new String(com.yhdatabase.common.util.StringUtil.hexEncode("YCMSGOVPORTAL".getBytes())));
	}
}
