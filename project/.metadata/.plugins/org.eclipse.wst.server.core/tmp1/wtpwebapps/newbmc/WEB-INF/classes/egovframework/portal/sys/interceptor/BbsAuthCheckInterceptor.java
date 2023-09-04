package egovframework.portal.sys.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.service.BbsMngService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.service.SiteAccessMngService;
import egovframework.portal.sys.sysAuth.service.SysMemberService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 관리자 게시판 접근 권한 검증 INTERCEPTOR
 *
 * @author J.Ryeon Lee
 * @since 2016.05.16
 */
@Service
public class BbsAuthCheckInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	@Autowired
	protected SiteAccessMngService siteAccessMngService;
	@Autowired
	protected SysMemberService sysMemberService;
	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected BbsMngService bbsMngService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
		Boolean allow = Boolean.TRUE;
		String ptIdx = request.getParameter("ptIdx");
		if (StringUtil.isNotBlank(ptIdx) && admin.getAdminAccessLevelCode() < 10) { // exclude super admin and lost session
			BbsConfigVO bbsConfig = bbsConfigService.getBbsConfigView(new BbsConfigVO(ptIdx));

			if (bbsConfig == null) {
				allow = Boolean.FALSE;
			} else if (StringUtil.isNotBlank(bbsConfig.getPtMngDeptIds())) { // 부서 권한 체크
				if (!bbsConfig.getPtMngDeptIds().contains(admin.getDeptId())) {
					allow = Boolean.FALSE;
				}
			} else if (StringUtil.isNotBlank(bbsConfig.getPtMngIds())) { // 로그인 ID 권한 체크
				if (!bbsConfig.getPtMngIds().contains(admin.getAdminId())) {
					allow = Boolean.FALSE;
				}
			}

			if (admin.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode()) {
				if (StringUtil.isBlank(admin.getPtIdxs())) {
					allow = Boolean.FALSE;
				} else if (!StringUtil.contains(bbsConfig.getPtIdx(), admin.getPtIdxs().split(","))) {
					allow = Boolean.FALSE;
				}
			}

			if (!allow) { // 권한이 없는 경우
				response.sendRedirect("/sys/" + (admin.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode() ? admin.getAdminType().getMngSiteCode() : "superMng") + "/bbs/configMng/list.do?mId=0103010000");
			}
		}

		return allow;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//
	}
}