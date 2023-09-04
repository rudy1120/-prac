package egovframework.portal.sys.sysAuth.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.MenuMng.service.MenuSysMngService;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.mapper.ExternalAdminMngMapper;
import egovframework.portal.sys.sysAuth.service.ExternalAdminLoginService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.LoginManager;
import egovframework.portal.util.SessionUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 외부 관리자 LOGIN SERVICE IMPL
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 9. 23.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 9. 23.
 */
@Service
public class ExternalAdminLoginServiceImpl extends EgovAbstractServiceImpl implements ExternalAdminLoginService {

	@Resource(name = "externalAdminMngMapper")
	protected ExternalAdminMngMapper externalAdminMngMapper;
	@Autowired
	protected MenuSysMngService menuSysMngService;

	@Override
	public void login(HttpServletRequest request, AdminLoginVO loginVO) {
		loginVO.setName(loginVO.getAdminName());
		loginVO.setAdminAccessLevelCode(AuthType.EXTERNAL.getCode());
		loginVO.setDeptName(loginVO.getAdminName());

		LoginManager.login(request, loginVO);
	}

	@Override
	public void logout(HttpServletRequest request) {
		LoginManager.logout(request, SessionUtil.getAdminInstance(request));
	}

}
