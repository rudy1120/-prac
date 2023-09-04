package egovframework.portal.sys.MenuMng.service;

import java.util.Map;

import egovframework.portal.sys.content.vo.SysMenuVO;

/**
 * 관리자 - 관리메뉴관리 서비스 클래스
 *
 * @author 개발팀 엄동건
 * @since 2014-09-25
 * @version 1.0
 * @see
 */

public interface MenuSysMngService {

	/**
	 * 메뉴 목록 조회
	 *
	 * @param siteCode
	 * @return
	 */
	public Map<String, Object> getMenuListMap(String siteCode);

	/**
	 * 메뉴정보 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public SysMenuVO getMenu(SysMenuVO searchVO);

	/**
	 * 메뉴추가시 추가할 메뉴의 메뉴ID를 가져온다.
	 *
	 * @param searchVO
	 * @return
	 */
	public String getNextMid(SysMenuVO searchVO);

	/**
	 * 메뉴추가시 추가할 메뉴의 레벨을 가져온다.
	 *
	 * @param menuId
	 * @return
	 */
	public int getNextMenuLevel(String menuId);

	public SysMenuVO setSearchMaxMid(SysMenuVO searchVO);

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
	public void insertMenu(SysMenuVO insertVO);

	/**
	 * 메뉴추가시 추가할 메뉴 아이디로 MenuOrder, ParentIdx 를 생성하기 위한 조회용 메뉴 아이디
	 *
	 * @param searchVO
	 * @return
	 */
	public SysMenuVO setInsertSearchMid(SysMenuVO searchVO);

	public SysMenuVO setMaxSearchMenuOrderCode(SysMenuVO searchVO);

	/**
	 * 메뉴 수정처리
	 *
	 * @param updateVO
	 */
	public void updateMenu(SysMenuVO updateVO);

	/**
	 * 메뉴 삭제처리
	 *
	 * @param deleteVO
	 */
	public void deleteMenu(SysMenuVO deleteVO);

	/**
	 * 메뉴순서올림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuUp(SysMenuVO searchVO);

	/**
	 * 메뉴순서내림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuDown(SysMenuVO searchVO);

	public String getSearchMoveMid(int menuLevel, String mId);

	public String getMoveDepth(int menuLevel);

	public boolean chkIsTop(SysMenuVO searchVO);

	public boolean chkIsBottom(SysMenuVO searchVO);

}
