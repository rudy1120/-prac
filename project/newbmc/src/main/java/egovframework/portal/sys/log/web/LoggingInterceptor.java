package egovframework.portal.sys.log.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.portal.sys.log.service.LoggingService;
import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.web.SysMemberController;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import net.arnx.jsonic.JSON;

/**
 * 관리자 통합 LOGGER
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017.03.27		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.11.08		J.Ryeon Lee			리퀘스트 파라미터 추가 처리
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 3. 27.
 * @see SysMemberController#sysSSOLoginProc(AdminLoginVO, HttpServletRequest, HttpServletResponse, org.springframework.ui.ModelMap)
 */
@Service
public class LoggingInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	protected LoggingService service;

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();

		if (requestURI.endsWith(".do") && !requestURI.contains("sysContents")) {
			AdminLoginVO admin = (AdminLoginVO) SessionUtil.getAdminSessionObj(request);
			String id = getId(admin, request);
			String search = TUtil.addParams(request.getParameterMap());


			LoggingVO params = new LoggingVO();
			params.setMid(search.contains("mId=") ? search.substring(search.indexOf("mId=") + 4, search.indexOf("mId=") + 14) : null);
			params.setFull_url(request.getRequestURL().toString());
			params.setHost_ip(SessionUtil.getRemoteAddr(request));
			params.setAdmin_id(id);
			params.setAdmin_name(admin != null ? (StringUtil.isBlank(admin.getName()) ? admin.getAdminName() : admin.getName()) : null);
			params.setDept_name(admin != null ? admin.getDeptName() : null);
			params.setDept_id(admin != null ? admin.getDeptId() : null);

			if (request.getParameterMap() != null && request.getParameterMap().size() > 0) {
				Map<String, Object> requestParamsMap = new HashMap<>();
				requestParamsMap.putAll(request.getParameterMap());
				requestParamsMap.remove("pwd");
				requestParamsMap.remove("passwd");
				requestParamsMap.remove("password");

				if (requestParamsMap.size() > 0) {
					String reqp = JSON.encode(requestParamsMap);
					params.setParams(reqp.length() > 4000 ? reqp.substring(0, 4000) : reqp);
				}
			}

			service.logging(params);
		}

		return true;
	}

	private String getId(AdminLoginVO admin, HttpServletRequest request) {
		if (admin != null) {
			return StringUtil.isBlank(admin.getAdminId()) ? admin.getId() : admin.getAdminId();
		}

		if (StringUtil.isNotBlank(request.getParameter("adminId"))) {
			return request.getParameter("adminId");
		}

		return null;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// default
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// default
	}
}