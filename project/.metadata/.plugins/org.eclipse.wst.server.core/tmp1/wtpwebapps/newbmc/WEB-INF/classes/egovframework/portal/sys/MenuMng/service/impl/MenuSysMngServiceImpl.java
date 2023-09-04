package egovframework.portal.sys.MenuMng.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.MenuMng.mapper.SysMenuMngMapper;
import egovframework.portal.sys.MenuMng.service.MenuSysMngService;
import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 - 관리메뉴관리 서비스 클래스
 *
 * @author 개발팀 엄동건
 * @since 2014-09-25
 * @version 1.0
 * @see
 */
@Service("menuSysMngService")
public class MenuSysMngServiceImpl extends EgovAbstractServiceImpl implements MenuSysMngService {

	@Resource(name = "sysMenuMngMapper")
	private SysMenuMngMapper sysMenuMngMapper;

	/**
	 * 메뉴 목록 조회
	 *
	 * @param siteCode
	 * @return
	 */
	public Map<String, Object> getMenuListMap(String siteCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysMenuVO searchVO = new SysMenuVO();
		searchVO.setSiteCode(siteCode);

		List<SysMenuVO> menuList = sysMenuMngMapper.getMenuList(searchVO);
		List<SysMenuVO> menuResultList = new ArrayList<SysMenuVO>();

		for (SysMenuVO depth1Menu : menuList) {
			if (depth1Menu.getMenuLevel() == 1) {
				List<SysMenuVO> depth2MenuList = new ArrayList<SysMenuVO>();
				for (SysMenuVO depth2Menu : menuList) {
					if (depth2Menu.getMenuLevel() == 2 && depth1Menu.getIdx() == depth2Menu.getParentIdx()) {
						List<SysMenuVO> depth3MenuList = new ArrayList<SysMenuVO>();
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

		resultMap.put("siteMenuList", menuResultList);
		resultMap.put("siteMenuSize", menuResultList.size());

		return resultMap;
	}

	/**
	 * 메뉴정보 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public SysMenuVO getMenu(SysMenuVO searchVO) {
		return sysMenuMngMapper.getMenu(searchVO);
	}

	/**
	 * 메뉴추가시 추가할 메뉴의 메뉴ID를 가져온다.
	 *
	 * @param searchVO
	 * @return
	 */
	public String getNextMid(SysMenuVO searchVO) {
		String nextMid = "";
		if (searchVO != null) {
			String orgMid = searchVO.getmId();
			SysMenuVO resultVO = setSearchMaxMid(searchVO);
			String maxMid = sysMenuMngMapper.getMaxMid(resultVO);
			if (maxMid == null) {
				maxMid = orgMid;
			}
			return getNext(maxMid, resultVO.getMenuLevel());
		} else {
			return nextMid;
		}
	}

	/**
	 * 메뉴추가시 추가할 메뉴의 레벨을 가져온다.
	 *
	 * @param menuId
	 * @return
	 */
	public int getNextMenuLevel(String menuId) {
		int menuLevel = 0;
		if (menuId.substring(2, 10).equals("00000000")) {
			menuLevel = 2;
		} else if (menuId.substring(4, 10).equals("000000")) {
			menuLevel = 3;
		} else if (menuId.substring(6, 10).equals("0000")) {
			menuLevel = 4;
		} else if (menuId.substring(8, 10).equals("00")) {
			menuLevel = 5;
		}

		return menuLevel;
	}

	public SysMenuVO setSearchMaxMid(SysMenuVO searchVO) {
		SysMenuVO resultVO = searchVO;
		if (!"".equals(resultVO.getmId()) && resultVO.getmId() != null) {
			if (resultVO.getmId().substring(2, 10).equals("00000000")) {
				resultVO.setMenuLevel(2);
				resultVO.setmId(resultVO.getmId().substring(0, 2));
			} else if (resultVO.getmId().substring(4, 10).equals("000000")) {
				resultVO.setMenuLevel(3);
				resultVO.setmId(resultVO.getmId().substring(0, 4));
			} else if (resultVO.getmId().substring(6, 10).equals("0000")) {
				resultVO.setMenuLevel(4);
				resultVO.setmId(resultVO.getmId().substring(0, 6));
			} else if (resultVO.getmId().substring(8, 10).equals("00")) {
				resultVO.setMenuLevel(5);
				resultVO.setmId(resultVO.getmId().substring(0, 8));
			}
			return resultVO;
		} else {
			return null;
		}
	}

	/**
	 * 메뉴 추가시 다음에 사용할 메뉴아이디를 생성
	 *
	 * @param maxMid
	 * @param menuLevel
	 * @return
	 */
	public String getNext(String maxMid, int menuLevel) {
		String nextMid = "";
		if (!"".equals(maxMid)) {
			if (menuLevel == 2) {
				nextMid = "0" + (Integer.parseInt(maxMid) + 1000000);
			} else if (menuLevel == 3) {
				nextMid = "0" + (Integer.parseInt(maxMid) + 10000);
			} else if (menuLevel == 4) {
				nextMid = "0" + (Integer.parseInt(maxMid) + 100);
			} else if (menuLevel == 5) {
				nextMid = "0" + (Integer.parseInt(maxMid) + 1);
			}
		}

		return nextMid;
	}

	/**
	 * 메뉴 등록처리
	 *
	 * @param insertVO
	 */
	public void insertMenu(SysMenuVO insertVO) {
		if (insertVO != null) {
			String orgMId = insertVO.getmId();
			int nextIdx = sysMenuMngMapper.getMenuNextSeq();
			SysMenuVO searchVO = new SysMenuVO();
			searchVO.setmId(insertVO.getmId());
			searchVO = this.setInsertSearchMid(searchVO);
			int nextMenuOrder = sysMenuMngMapper.getMenuNextMenuOrder(searchVO);
			searchVO.setMenuLevel(searchVO.getMenuLevel() - 1);
			int nextParentIdx = sysMenuMngMapper.getMenuNextParentIdx(searchVO);

			insertVO.setIdx(nextIdx);
			insertVO.setMenuOrderCode(orgMId);
			insertVO.setParentIdx(nextParentIdx);
			insertVO.setMenuOrder(nextMenuOrder);

			sysMenuMngMapper.insertMenu(insertVO);
		}
	}

	/**
	 * 메뉴추가시 추가할 메뉴 아이디로 MenuOrder, ParentIdx 를 생성하기 위한 조회용 메뉴 아이디
	 *
	 * @param searchVO
	 * @return
	 */
	public SysMenuVO setInsertSearchMid(SysMenuVO searchVO) {
		SysMenuVO resultVO = searchVO;
		if (!"".equals(resultVO.getmId()) && resultVO.getmId() != null) {
			if (resultVO.getmId().substring(4, 10).equals("000000")) {
				resultVO.setMenuLevel(2);
				resultVO.setmId(resultVO.getmId().substring(0, 2));
			} else if (resultVO.getmId().substring(6, 10).equals("0000")) {
				resultVO.setMenuLevel(3);
				resultVO.setmId(resultVO.getmId().substring(0, 4));
			} else if (resultVO.getmId().substring(8, 10).equals("00")) {
				resultVO.setMenuLevel(4);
				resultVO.setmId(resultVO.getmId().substring(0, 6));
			} else {
				resultVO.setMenuLevel(5);
				resultVO.setmId(resultVO.getmId().substring(0, 8));
			}
			return resultVO;
		} else {
			return null;
		}
	}

	public SysMenuVO setMaxSearchMenuOrderCode(SysMenuVO searchVO) {
		SysMenuVO resultVO = searchVO;
		if (!"".equals(resultVO.getMenuOrderCode()) && resultVO.getMenuOrderCode() != null) {
			if (resultVO.getMenuOrderCode().substring(4, 10).equals("000000")) {
				resultVO.setMenuLevel(2);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 2));
			} else if (resultVO.getmId().substring(6, 10).equals("0000")) {
				resultVO.setMenuLevel(3);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 4));
			} else if (resultVO.getmId().substring(8, 10).equals("00")) {
				resultVO.setMenuLevel(4);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 6));
			} else {
				resultVO.setMenuLevel(5);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 8));
			}
			return resultVO;
		} else {
			return null;
		}
	}

	/**
	 * 메뉴 수정처리
	 *
	 * @param updateVO
	 */
	public void updateMenu(SysMenuVO updateVO) {
		sysMenuMngMapper.updateMenu(updateVO);
	}

	/**
	 * 메뉴 삭제처리
	 *
	 * @param deleteVO
	 */
	public void deleteMenu(SysMenuVO deleteVO) {
		if (deleteVO != null) {
			SysMenuVO deleteSearchVO = this.getMenu(deleteVO);
			String deleteMid = this.getSearchMoveMid(deleteSearchVO.getMenuLevel(), deleteSearchVO.getmId());
			deleteVO.setmId(deleteMid);
			sysMenuMngMapper.deleteMenu(deleteVO);
		}
	}

	/**
	 * 메뉴순서올림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuUp(SysMenuVO searchVO) {
		SysMenuVO curSysMenuVO = this.getMenu(searchVO);
		String moveDepth = getMoveDepth(curSysMenuVO.getMenuLevel());
		String moveMenuOrderCode = getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getMenuOrderCode());
		String moveMid = getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getmId());
		String siteCode = curSysMenuVO.getSiteCode();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("moveDepth", moveDepth);
		params.put("moveMenuOrderCode", moveMenuOrderCode);
		params.put("siteCode", siteCode);
		params.put("moveMid", moveMid);

		SysMenuVO targetSearchVO = new SysMenuVO();
		//String targetMenuOrderCode = menuSysMngDAO.getMoveMenuDownTarget(params);
		targetSearchVO.setMenuOrderCode(curSysMenuVO.getMenuOrderCode());
		targetSearchVO.setSiteCode(curSysMenuVO.getSiteCode());
		targetSearchVO.setParentIdx(curSysMenuVO.getParentIdx());
		targetSearchVO = sysMenuMngMapper.getUpMenu(targetSearchVO);
		SysMenuVO targetSysMenuVO = sysMenuMngMapper.getMoveMenuTarget(targetSearchVO);

		String targetMoveDepth = getMoveDepth(targetSysMenuVO.getMenuLevel());
		String targetMoveMenuOrderCode = getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getMenuOrderCode());
		String targetMoveMid = getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getmId());
		String targetSiteCode = targetSysMenuVO.getSiteCode();

		// 선택한 메뉴를 한단계 위로 이동
		sysMenuMngMapper.moveMenuUp(params);

		params.put("moveDepth", targetMoveDepth);
		params.put("moveMenuOrderCode", targetMoveMenuOrderCode);
		params.put("siteCode", targetSiteCode);
		params.put("moveMid", targetMoveMid);
		// 선택한 메뉴의 위쪽에 있던 메뉴 아래로 이동
		sysMenuMngMapper.moveMenuDown(params);

		params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getMenuOrderCode()));
		params.put("menuLevel", String.valueOf(targetSysMenuVO.getMenuLevel() + 1));
		params.put("siteCode", siteCode);
		// 선택한 메뉴의 하위메뉴들 체크
		int chkCurrentDownLevelMenu = sysMenuMngMapper.chkDownLevelMenu(params);

		// 선택한 메뉴의 하위메뉴들 이동
		if (chkCurrentDownLevelMenu > 0) {
			params = new HashMap<String, String>();
			params.put("moveDepth", moveDepth);
			params.put("moveMenuOrderCode", getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getMenuOrderCode()));
			params.put("menuLevel", String.valueOf(curSysMenuVO.getMenuLevel() + 1));
			params.put("siteCode", siteCode);
			sysMenuMngMapper.moveDownLevelMenuUp(params);

		}

		params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getMenuOrderCode()));
		params.put("menuLevel", String.valueOf(curSysMenuVO.getMenuLevel() + 1));
		params.put("siteCode", siteCode);
		// 선택한 메뉴의 아래에 있던 메뉴들 체크
		int chkTargetDwonLevelMenu = sysMenuMngMapper.chkDownLevelMenu(params);

		// 선택한 메뉴의 아래에 있던 하위메뉴들 이동
		if (chkTargetDwonLevelMenu > 0) {
			params = new HashMap<String, String>();
			params.put("moveDepth", targetMoveDepth);
			params.put("moveMenuOrderCode", getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getMenuOrderCode()));
			params.put("menuLevel", String.valueOf(targetSysMenuVO.getMenuLevel() + 1));
			params.put("siteCode", targetSiteCode);
			sysMenuMngMapper.moveDownLevelMenuDown(params);
		}

	}

	/**
	 * 메뉴순서내림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuDown(SysMenuVO searchVO) {
		SysMenuVO curSysMenuVO = this.getMenu(searchVO);
		String moveDepth = getMoveDepth(curSysMenuVO.getMenuLevel());
		String moveMenuOrderCode = getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getMenuOrderCode());
		String moveMid = getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getmId());
		String siteCode = curSysMenuVO.getSiteCode();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("moveDepth", moveDepth);
		params.put("moveMenuOrderCode", moveMenuOrderCode);
		params.put("siteCode", siteCode);
		params.put("moveMid", moveMid);

		SysMenuVO targetSearchVO = new SysMenuVO();
		//String targetMenuOrderCode = menuSysMngDAO.getMoveMenuUpTarget(params);

		targetSearchVO.setMenuOrderCode(curSysMenuVO.getMenuOrderCode());
		targetSearchVO.setSiteCode(curSysMenuVO.getSiteCode());
		targetSearchVO.setParentIdx(curSysMenuVO.getParentIdx());
		targetSearchVO = sysMenuMngMapper.getDownMenu(targetSearchVO);
		SysMenuVO targetSysMenuVO = sysMenuMngMapper.getMoveMenuTarget(targetSearchVO);

		String targetMoveDepth = getMoveDepth(targetSysMenuVO.getMenuLevel());
		String targetMoveMenuOrderCode = getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getMenuOrderCode());
		String targetMoveMid = getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getmId());
		String targetSiteCode = targetSysMenuVO.getSiteCode();

		// 선택한 메뉴를 한단계 아래로 이동
		sysMenuMngMapper.moveMenuDown(params);

		// 선택한 메뉴의 아래에 있던 메뉴 위로 이동
		params.put("moveDepth", targetMoveDepth);
		params.put("moveMenuOrderCode", targetMoveMenuOrderCode);
		params.put("siteCode", targetSiteCode);
		params.put("moveMid", targetMoveMid);
		sysMenuMngMapper.moveMenuUp(params);

		params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getMenuOrderCode()));
		params.put("menuLevel", String.valueOf(targetSysMenuVO.getMenuLevel() + 1));
		params.put("siteCode", siteCode);
		// 선택한 메뉴의 하위메뉴들 체크
		int chkCurrentDownLevelMenu = sysMenuMngMapper.chkDownLevelMenu(params);

		// 선택한 메뉴의 하위메뉴들 이동
		if (chkCurrentDownLevelMenu > 0) {
			params = new HashMap<String, String>();
			params.put("moveDepth", moveDepth);
			params.put("moveMenuOrderCode", getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getMenuOrderCode()));
			params.put("menuLevel", String.valueOf(curSysMenuVO.getMenuLevel() + 1));
			params.put("siteCode", siteCode);

			sysMenuMngMapper.moveDownLevelMenuDown(params);
		}

		params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", getSearchMoveMid(curSysMenuVO.getMenuLevel(), curSysMenuVO.getMenuOrderCode()));
		params.put("menuLevel", String.valueOf(curSysMenuVO.getMenuLevel() + 1));
		params.put("siteCode", siteCode);
		// 선택한 메뉴의 아래에 있던 메뉴들 체크
		int chkTargetDwonLevelMenu = sysMenuMngMapper.chkDownLevelMenu(params);

		// 선택한 메뉴의 아래에 있던 하위메뉴들 이동
		if (chkTargetDwonLevelMenu > 0) {
			params = new HashMap<String, String>();
			params.put("moveDepth", targetMoveDepth);
			params.put("moveMenuOrderCode", getSearchMoveMid(targetSysMenuVO.getMenuLevel(), targetSysMenuVO.getMenuOrderCode()));
			params.put("menuLevel", String.valueOf(targetSysMenuVO.getMenuLevel() + 1));
			params.put("siteCode", targetSiteCode);
			sysMenuMngMapper.moveDownLevelMenuUp(params);
		}

	}

	public String getSearchMoveMid(int menuLevel, String mId) {
		String result = "";
		if (!"".equals(mId) && mId != null) {
			if (menuLevel == 1) {
				result = mId.substring(0, 2);
			} else if (menuLevel == 2) {
				result = mId.substring(0, 4);
			} else if (menuLevel == 3) {
				result = mId.substring(0, 6);
			} else if (menuLevel == 4) {
				result = mId.substring(0, 8);
			} else if (menuLevel == 5) {
				result = mId.substring(0, 10);
			}
		}
		return result;
	}

	public String getMoveDepth(int menuLevel) {
		if (menuLevel == 1) {
			return "0100000000";
		} else if (menuLevel == 2) {
			return "0001000000";
		} else if (menuLevel == 3) {
			return "0000010000";
		} else if (menuLevel == 4) {
			return "0000000100";
		} else if (menuLevel == 5) {
			return "0000000001";
		} else {
			return "";
		}
	}

	public boolean chkIsTop(SysMenuVO searchVO) {
		SysMenuVO currentSysMenuVO = this.getMenu(searchVO);
		String currentMenuOrderCode = currentSysMenuVO.getMenuOrderCode();
		SysMenuVO chkSearchVO = this.setMaxSearchMenuOrderCode(currentSysMenuVO);
		String minMenuOrderCode = sysMenuMngMapper.getMinMenuOrderCode(chkSearchVO);
		if (currentMenuOrderCode.equals(minMenuOrderCode)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean chkIsBottom(SysMenuVO searchVO) {
		SysMenuVO currentSysMenuVO = this.getMenu(searchVO);
		String currentMenuOrderCode = currentSysMenuVO.getMenuOrderCode();
		SysMenuVO chkSearchVO = this.setMaxSearchMenuOrderCode(currentSysMenuVO);
		String maxMenuOrderCode = sysMenuMngMapper.getMaxMenuOrderCode(chkSearchVO);
		if (currentMenuOrderCode.equals(maxMenuOrderCode)) {
			return true;
		} else {
			return false;
		}
	}

}
