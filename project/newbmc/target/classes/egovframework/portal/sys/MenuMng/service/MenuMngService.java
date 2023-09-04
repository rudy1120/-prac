package egovframework.portal.sys.MenuMng.service;

import egovframework.portal.sys.MenuMng.vo.MenuChargeVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.MenuMng.vo.MenusChargeHistoryMngVO;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

/**
 * 관리자 - 메뉴관리 서비스 클래스
 *
 * @author 개발팀 엄동건
 * @since 2014-09-11
 * @version 1.0
 * @see
 */

public interface MenuMngService {

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
	public List<MenusMngVO> getMenusMngListAll(HttpServletRequest request);

	/**
	 * 메뉴 목록 조회
	 *
	 * @param siteCode
	 * @return
	 */
	public Map<String, Object> getMenuListMap(String siteCode);

	/**
	 * 메뉴 점검목록 카운터
	 *
	 * @return
	 */
	public int getMenuCheckTotalCnt(MenuVO searchVO);

	/**
	 * 메뉴 점검목록 조회
	 *
	 * @return
	 */
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
	 * 메뉴추가시 추가할 메뉴의 메뉴ID를 가져온다.
	 *
	 * @param searchVO
	 * @return
	 */
	public String getNextMid(MenuVO searchVO);

	/**
	 * 메뉴추가시 추가할 메뉴의 레벨을 가져온다.
	 *
	 * @param menuId
	 * @return
	 */
	public int getNextMenuLevel(String menuId);

	public MenuVO setSearchMaxMid(MenuVO searchVO);

	/**
	 * 메뉴 추가시 다음에 사용할 메뉴아이디를 생성
	 *
	 * @param maxMid
	 * @param menuLevel
	 * @return
	 */
	public String getNext(String maxMid, int menuLevel);

	/**
	 * 메뉴 등록처리
	 *
	 * @param insertVO
	 */
	public void insertMenu(MenuVO insertVO, HttpServletRequest request);

	/**
	 * 메뉴추가시 추가할 메뉴 아이디로 MenuOrder, ParentIdx 를 생성하기 위한 조회용 메뉴 아이디
	 *
	 * @param searchVO
	 * @return
	 */
	public MenuVO setInsertSearchMid(MenuVO searchVO);

	public MenuVO setMaxSearchMenuOrderCode(MenuVO searchVO);

	/**
	 * 메뉴 수정처리
	 *
	 * @param updateVO
	 */
	public void updateMenu(MenuVO updateVO, HttpServletRequest request);

	/**
	 * 메뉴 삭제처리
	 *
	 * @param deleteVO
	 */
	public void deleteMenu(MenuVO deleteVO);

	/**
	 * 메뉴순서올림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuUp(MenuVO searchVO);

	/**
	 * 메뉴순서내림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuDown(MenuVO searchVO);

	public String getSearchMoveMid(int menuLevel, String mId);

	public String getMoveDepth(int menuLevel);

	public boolean chkIsTop(MenuVO searchVO);

	public boolean chkIsBottom(MenuVO searchVO);

	public List<MenuVO> getMenuNotInBbsList(MenuVO searchVO);

	public int getMenuNextSeq();

	/**
	 * 해당 사이트의 메뉴 전체 삭제
	 *
	 * @Method Name : deleteMenuTargetSite
	 * @param siteCode
	 */
	public void deleteMenuTargetSite(String siteCode);

	/*** 사용자단 ***/

	public Map<String, Object> getMenuListMap(ModelMap model, String mId, String siteCode, HttpServletRequest request);

	public MenuVO getMenuInfo(String mId, String siteCode);

	public MenuVO getTmpMenuInfo(String mId, String siteCode);

	public String getSiteDeptCode(String siteCode);

	public List<MenuVO> getHierarchicalMenuList(int rootDepthLevel, int rootIdx, String siteCode, String optionalQuery);

	public List<MenuVO> getHierarchicalMenuList(String siteCode, String oprtionQuery);

//	public List<MenuVO> getTabMenus(String mId, String siteCode);

	public MenuVO getActiveMenu(String mId, List<MenuVO> menus);

	/**
	 * 유효한 MID인지 확인
	 *
	 * @Method Name : getAbailableMid
	 * @param paramMap
	 * @return
	 */
	public int getAbailableMid(MenuVO paramVO);

	/**
	 * 메뉴 담당자 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<MenuChargeVO> getMenuCharge(MenuChargeVO vo);

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
	/**
	 * 메뉴 담당자 히스트로 리스트 조회
	 *
	 * @return
	 */
	public List<MenusChargeHistoryMngVO> getMenuChargeHistoryList(MenuVO searchVO);

}