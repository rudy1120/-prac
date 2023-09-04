package egovframework.portal.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.ServletRequestUtils;

import com.yhdb.solution.secukeypad.mweb.MSecuKeypadConstant;
import com.yhdb.solution.secukeypad.mweb.MSecuKeypadDecoder;
import com.yhdb.solution.secukeypad.mweb.MSecuKeypadException;
import com.yhdb.solution.secukeypad.pcweb.SecuKeypadConstant;
import com.yhdb.solution.secukeypad.pcweb.SecuKeypadDecoder;
import com.yhdb.solution.secukeypad.pcweb.SecuKeypadException;

import egovframework.com.cmm.service.EgovProperties;

public enum YSecukeyPadUtil {

	INSTANCE,;

	private static final Logger LOGGER = LogManager.getLogger();

	private static String decode(HttpServletRequest request) {
		String viewGubun = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "viewGubun", ""));

		try {
			return "mobile".equals(viewGubun) ? //
				MSecuKeypadDecoder.decode(request, MSecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET) //
				: SecuKeypadDecoder.decode(request, SecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET);
		} catch (SecuKeypadException | MSecuKeypadException e) {
			LOGGER.error(">> SecuKeypadException occurred", e);
		}

		return "";
	}

	public static String decode(HttpServletRequest request, String encProperty) {
		if ("Y".equals(EgovProperties.getProperty("Globals.secukeypad"))) {
			return decode(request);
		} else {
			return request.getParameter(encProperty);
		}

	}
}
