package egovframework.portal.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import egovframework.portal.common.service.CommonService;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.StringUtil;

/**
 * 메뉴 세팅 AOP
 *
 * @author J.Ryeon Lee
 * @since 2016.08.18
 */
@Component("menuFetcher")
public class MenuFetcher {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected MenuSysService menuService;

	public static final Logger LOGGER = Logger.getLogger(MenuFetcher.class.getName());

	public void menuSetting(JoinPoint method) throws Throwable {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		ModelMap model = null;

		Object[] signatureArgs = method.getArgs();
		for (Object signatureArg : signatureArgs) {
			if (signatureArg instanceof HttpServletRequest) {
				request = (HttpServletRequest) signatureArg;
			} else if (signatureArg instanceof HttpServletResponse) {
				response = (HttpServletResponse) signatureArg;
			} else if (signatureArg instanceof ModelMap) {
				model = (ModelMap) signatureArg;
			}
		}

		LOGGER.debug("current request object is [" + request + "]");
		String mId = request != null ? request.getParameter("mId") : null;
		if (needMenuList(mId, request, response, model)) {
			String requestURI = request.getRequestURI();
			if (requestURI.contains("/sys/")) { // 관리자
				if (!requestURI.contains("/sysContents.do") && !requestURI.contains("Proc.do")) {
					model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));
				}
			} else { // 사용자
				if (!requestURI.contains("/contents.do") && !requestURI.contains("Proc.do")) {
					commonService.commonDataCreater(request, response, model);
				}
			}
		}
	}

	private boolean needMenuList(String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return StringUtil.isNotBlank(mId) && //
			!"XMLHttpRequest".equals(request.getHeader("X-Requested-With")) && // EXCLUDE AJAX REQUEST CALL
			response != null && model != null;
	}

}