package egovframework.portal.sys.MenuMng.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("sysMenuMngMapper")
public interface SysMenuMngMapper {

	/** 메뉴목록 조회 */
	public List<SysMenuVO> getMenuList(SysMenuVO searchVO);

	/**
	 * 메뉴정보 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public SysMenuVO getMenu(SysMenuVO searchVO);

	/**
	 * 메뉴추가시 추가할 메뉴의 메뉴ID를 가져온다. maxID 획득
	 *
	 * @param searchVO
	 * @return
	 */
	public String getMaxMid(SysMenuVO searchVO);

	public int getMenuNextSeq();

	public int getMenuNextMenuOrder(SysMenuVO searchVO);

	public int getMenuNextParentIdx(SysMenuVO searchVO);

	public String getNextMenuOrderCode(SysMenuVO searchVO);

	/**
	 * 메뉴 등록처리
	 *
	 * @param insertVO
	 */
	public void insertMenu(SysMenuVO insertVO);

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

	public String getMoveMenuDownTarget(HashMap<String, String> params);

	public SysMenuVO getUpMenu(SysMenuVO vo);

	public SysMenuVO getDownMenu(SysMenuVO vo);

	public SysMenuVO getMoveMenuTarget(SysMenuVO vo);

	public void moveMenuUp(HashMap<String, String> params);

	public void moveMenuDown(HashMap<String, String> params);

	public int chkDownLevelMenu(HashMap<String, String> params);

	public void moveDownLevelMenuUp(HashMap<String, String> params);

	public void moveDownLevelMenuDown(HashMap<String, String> params);

	public String getMoveMenuUpTarget(HashMap<String, String> params);

	public String getMinMenuOrderCode(SysMenuVO vo);

	public String getMaxMenuOrderCode(SysMenuVO vo);

	/** 관리자메뉴 조회시 부모메뉴 정보 */
	public SysMenuVO getSysMenuParent(SysMenuVO searchVO);

	/** 관리자메뉴 목록 조회 */
	public List<SysMenuVO> getSysMenuList(SysMenuVO searchVO);

	public String getSysCurMid(SysMenuVO searchVO);

	public SysMenuVO getSysMenuVO(SysMenuVO searchVO);
}