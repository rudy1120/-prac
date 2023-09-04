package egovframework.portal.sys.MenuMng.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.MenuMng.vo.MenusChargeHistoryMngVO;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("menuMngMapper")
public interface MenuMngMapper {

	/**
	 * 사이트 수 조회
	 *
	 * @return
	 */
	public int getSiteListCnt();

	/**
	 * 사이트 전체목록 조회
	 *
	 * @return
	 */
	public List<MenusMngVO> getMenusMngListAll(String searchQuery);

	// 메뉴목록 조회
	public List<MenuVO> getMenuList(MenuVO searchVO);

	// 메뉴목록 조회 (담당자정보 추가)
	public List<MenuVO> getMenuListAddChargeNms(MenuVO searchVO);



	// 메뉴점검목록 카운트
	public int getMenuCheckAckTotalCnt(MenuVO searchVO);

	public int getMenuCheckTotalCnt(MenuVO searchVO);

	// 메뉴점검목록 조회
	public List<MenuVO> getMenuCheckList(MenuVO searchVO);

	/**
	 * 메뉴정보 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public MenuVO getMenu(MenuVO searchVO);

	public String getMenuInBbsName(MenuVO searchVO);

	/**
	 * 메뉴추가시 추가할 메뉴의 메뉴ID를 가져온다. maxID 획득
	 *
	 * @param searchVO
	 * @return
	 */
	public String getMaxMid(MenuVO searchVO);

	public int getMenuNextSeq();

	public String getMenuNextMenuOrderCode(MenuVO searchVO);

	public int getMenuNextMenuOrder(MenuVO searchVO);

	public int getMenuNextParentIdx(MenuVO searchVO);

	public String getNextMenuOrderCode(MenuVO searchVO);

	// 우선노출 값 삭제
	public void updateSiblingsFirst(MenuVO searchVO);

	/**
	 * 메뉴 등록처리
	 *
	 * @param insertVO
	 */
	public void insertMenu(MenuVO insertVO);

	/**
	 * 메뉴 수정처리
	 *
	 * @param updateVO
	 */
	public void updateMenu(MenuVO updateVO);

	/**
	 * 메뉴 삭제처리
	 *
	 * @param deleteVO
	 */
	public void deleteMenu(MenuVO deleteVO);

	public String getMoveMenuDownTarget(HashMap<String, String> params);

	public MenuVO getMoveMenuTarget(MenuVO vo);

	public MenuVO getDownMenu(HashMap<String, String> params);

	public MenuVO getUpMenu(HashMap<String, String> params);

	public void moveMenu(HashMap<String, String> params);

	public int chkDownLevelMenu(HashMap<String, String> params);

	public void moveMenuChildren(HashMap<String, String> params);

	public String getMoveMenuUpTarget(HashMap<String, String> params);

	public String getMinMenuOrderCode(MenuVO vo);

	public String getMaxMenuOrderCode(MenuVO vo);

	public List<MenuVO> getMenuNotInBbsList(MenuVO searchVO);

	/**
	 * 메뉴 담당자 이력 등록처리
	 *
	 * @param insertVO
	 */
	public void insertMenuChargeHistory(MenusChargeHistoryMngVO insertVO);

	/**
	 * 해당 사이트의 메뉴 전체 삭제
	 *
	 * @Method Name : deleteMenuTargetSite
	 * @param siteCode
	 */
	public void deleteMenuTargetSite(String siteCode);

	public String getMenuName(Map<String, String> params);
//	{
//		Map<String, String> params = new HashMap<>();
//		params.put("siteCode", siteCode);
//		params.put("mId", menuId);
//
//		return (String) selectOne("menuMng.getMenuName", params);
//	}

	public List<MenuVO> getUserMenuList(MenuVO searchVO);

	public String getCurMid(MenuVO searchVO);

	public MenuVO getMenuVO(MenuVO searchVO);

	public String getSiteDeptCode(String siteCode);

	public List<MenuVO> getHierarchicalMenuList(Map<String, Object> conditions);

	public MenuVO getMenuByIdx(int idx);

	/**
	 * 유효한 MID인지 확인
	 *
	 * @param paramMap
	 * @return
	 */
	public int getAbailableMid(MenuVO paramVO);

	/**
	 * 현재 메뉴 하위에 사용 상태의 하위 메뉴가 있는지 확인
	 *
	 * @param paramVO
	 * @return
	 */
	public String getAvailableChildMid(MenuVO paramVO);

	/**
	 * 해당 사이트의 그룹 코드를 조회
	 *
	 * @param siteCode
	 * @return
	 */
	public String getSiteGroup(String siteCode);

	public List<Map<String, Object>> getURLContentsList();

	public String getURLContents(Map<String, Object> param);

	public List<Map<String, Object>> getURLmenuList();

	public String getURLmenu(Map<String, Object> param);


	public List<MenusChargeHistoryMngVO> getMenuChargeHistoryList(MenuVO searchVO);

}