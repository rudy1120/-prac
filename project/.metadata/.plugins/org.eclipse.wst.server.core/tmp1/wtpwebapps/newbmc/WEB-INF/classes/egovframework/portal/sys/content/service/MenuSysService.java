package egovframework.portal.sys.content.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.portal.sys.sysAuth.vo.SysMenuAuthVO;

/**
 * 공통 - 메뉴 및 컨텐츠 조회 서비스 클래스
 *
 * <pre>
 * &lt;&lt;개정이력(Modification Information)&gt;&gt;
 * 2014.09.10 김혜민
 * 최초 생성
 *
 * </pre>
 *
 * @author 개발팀 김혜민
 * @since 2014.09.10
 * @version 1.0
 */
public interface MenuSysService {

	/**
	 * 관리자 메뉴
	 */
	public Map<String, Object> getSysMenuInfoMap(ModelMap model, String mId, HttpServletRequest request);

	public StringBuilder getSearchQuery(List<SysMenuAuthVO> menuAuthList, int accessLevelCode);

	public SysMenuVO getMenuInfo(String mId);

	public SysMenuVO getSysCurMidSearchVO(String curMid);
}
