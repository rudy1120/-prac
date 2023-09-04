package egovframework.portal.sys.content.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import egovframework.portal.sys.MenuMng.mapper.SysMenuMngMapper;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.SysMenuAuthVO;
import egovframework.portal.util.SessionUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 공통 - 메뉴 및 컨텐츠 조회 서비스 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.09.10		엄동건				최초 생성
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2014.09.10
 * @version 1.0
 */
@Service("menuSysService")
public class MenuSysServiceImpl extends EgovAbstractServiceImpl implements MenuSysService {

	@Resource(name = "sysMenuMngMapper")
	private SysMenuMngMapper sysMenuMngMapper;

	private String getMidRange(List<String> midList) {
		StringBuffer sb = new StringBuffer();
		for (String mid : midList) {
			sb.append(sb.length() > 0 ? String.format(",'%s'", mid) : String.format("'%s'", mid));
		}

		return sb.toString();
	}

	/** 관리자 메뉴 */
	public Map<String, Object> getSysMenuInfoMap(ModelMap model, String mId, HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysMenuVO searchVO = new SysMenuVO();

		if (SessionUtil.isAdminLogin(request)) {

			AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);
			if (adminLoginVO.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode()) { // 외부 관리자의 경우 허용된 mid만
				searchVO.setSearchQuery(" AND mid IN (" + getMidRange(adminLoginVO.getMidList()) + ")"); // 게시판 관리 ONLY
			} else if (adminLoginVO.getAdminAccessLevelCode() != AuthType.SUPER.getCode()) {
				List<SysMenuAuthVO> menuAuthList = adminLoginVO.getAdminMenuAuths();
				int accessLevelCode = adminLoginVO.getAdminAccessLevelCode();
				StringBuilder searchQuerys = getSearchQuery(menuAuthList, accessLevelCode);
				searchVO.setSearchQuery(searchQuerys.toString());
			}

			List<SysMenuVO> menuList = sysMenuMngMapper.getSysMenuList(searchVO);
			List<SysMenuVO> menuResultList = new ArrayList<SysMenuVO>();

			for (SysMenuVO depth1Menu : menuList) {
				if (depth1Menu.getMenuLevel() == 1) {
					List<SysMenuVO> depth2MenuList = new ArrayList<SysMenuVO>();
					for (SysMenuVO depth2Menu : menuList) {
						if (depth2Menu.getMenuLevel() == 2 && depth1Menu.getIdx() == depth2Menu.getParentIdx()) {
							List<SysMenuVO> depth3MenuList = new ArrayList<SysMenuVO>();
//							int flag = 0;
							for (SysMenuVO depth3Menu : menuList) {
								if (depth3Menu.getMenuLevel() == 3 && depth2Menu.getIdx() == depth3Menu.getParentIdx()) {
									List<SysMenuVO> depth4MenuList = new ArrayList<SysMenuVO>();
									for (SysMenuVO depth4Menu : menuList) {
										if (depth4Menu.getMenuLevel() == 4 && depth3Menu.getIdx() == depth4Menu.getParentIdx()) {
											List<SysMenuVO> depth5MenuList = new ArrayList<SysMenuVO>();
											for (SysMenuVO depth5Menu : menuList) {
												if (depth5Menu.getMenuLevel() == 5 && depth4Menu.getIdx() == depth5Menu.getParentIdx()) {
													depth5MenuList.add(depth5Menu);
												}
											}
											depth4Menu.setDepth5MenuList(depth5MenuList);
											depth4MenuList.add(depth4Menu);
										}
									}
									depth3Menu.setDepth4MenuList(depth4MenuList);
									depth3MenuList.add(depth3Menu);
								}
							}
							depth2Menu.setDepth3MenuList(depth3MenuList);
							depth2MenuList.add(depth2Menu);
						}
					}
					depth1Menu.setDepth2MenuList(depth2MenuList);
					menuResultList.add(depth1Menu);
				}
			}

			SysMenuVO menuVO = getMenuInfo(mId);
			if (mId != null) {
				resultMap.put("menuVO", menuVO);
				resultMap.put("menuName", menuVO.getMenuName());
				resultMap.put("curMid", menuVO.getmId());
				if (menuVO.getMenuLevel() == 2) {
					resultMap.put("parentVO", menuVO);
				} else {
					SysMenuVO menuVO2 = sysMenuMngMapper.getSysMenuParent(menuVO);
					if (menuVO2.getMenuLevel() == 2) {
						resultMap.put("parentVO", menuVO2);
					} else {
						SysMenuVO menuVO3 = sysMenuMngMapper.getSysMenuParent(menuVO2);
						resultMap.put("parentVO", menuVO3);
					}

				}

			}

			resultMap.put("menuList", menuResultList);
		}

		return resultMap;
	}

	public StringBuilder getSearchQuery(List<SysMenuAuthVO> menuAuthList, int accessLevelCode) {

		String sq = "'";
		String qm = " , ";

		StringBuilder searchQuerys = new StringBuilder();
		if (menuAuthList == null) {
			return searchQuerys;
		}

		searchQuerys.append(" and MID in ( ");
		int cnt = 0;
		for (SysMenuAuthVO menus : menuAuthList) {
			searchQuerys.append(sq);
			searchQuerys.append(menus.getmId());
			searchQuerys.append(sq);
			if ((menuAuthList.size() - 1) != cnt) {
				searchQuerys.append(qm);
			}
			cnt++;
		}
		searchQuerys.append(" ) ");
		searchQuerys.append(" and ACCESS_LEVEL_CODE <= ");
		searchQuerys.append(accessLevelCode);

		return searchQuerys;
	}

	public SysMenuVO getMenuInfo(String mId) {
		SysMenuVO searchVO = new SysMenuVO();

		SysMenuVO menuVO = new SysMenuVO();
		String reqMid = mId;
		String curMid = mId;

		if (reqMid != null && !"".equals(reqMid)) {
			SysMenuVO curMidSearchVO = getSysCurMidSearchVO(reqMid);
			curMid = sysMenuMngMapper.getSysCurMid(curMidSearchVO);
			if (curMid == null) {
				searchVO.setmId(reqMid);
			} else {
				searchVO.setmId(curMid);
			}

			menuVO = sysMenuMngMapper.getSysMenuVO(searchVO);
		}

		return menuVO;
	}

	public SysMenuVO getSysCurMidSearchVO(String curMid) {
		SysMenuVO searchVO = new SysMenuVO();

		if (curMid.substring(2, 10).equals("00000000")) {
			searchVO.setMenuLevel(1);
			searchVO.setmId(curMid.substring(0, 2));
		} else if (curMid.substring(4, 10).equals("000000")) {
			searchVO.setMenuLevel(2);
			searchVO.setmId(curMid.substring(0, 4));
		} else if (curMid.substring(6, 10).equals("0000")) {
			searchVO.setMenuLevel(3);
			searchVO.setmId(curMid.substring(0, 6));
		} else if (curMid.substring(8, 10).equals("00")) {
			searchVO.setMenuLevel(4);
			searchVO.setmId(curMid.substring(0, 8));
		} else {
			searchVO.setMenuLevel(5);
			searchVO.setmId(curMid.substring(0, 10));
		}

		return searchVO;
	}
}
