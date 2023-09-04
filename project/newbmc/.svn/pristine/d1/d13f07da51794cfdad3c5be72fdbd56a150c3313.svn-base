package egovframework.portal.sys.sysAuth.service;

import javax.servlet.http.HttpServletRequest;

import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 동적 외부 관리자 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.10.17		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2015. 10. 17.
 */
public interface ExternalAdminLoginService {

	/** 외부 관리자 로그인 처리 */
	void login(HttpServletRequest request, AdminLoginVO admin);

	/** 외부 관리자 로그아웃 처리 */
	void logout(HttpServletRequest request);

}
