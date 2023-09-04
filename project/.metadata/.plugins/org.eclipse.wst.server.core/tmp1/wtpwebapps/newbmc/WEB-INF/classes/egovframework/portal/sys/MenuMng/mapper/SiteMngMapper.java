package egovframework.portal.sys.MenuMng.mapper;

import java.util.List;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("siteMngMapper")
public interface SiteMngMapper {

	public int getSiteListCnt(MenusMngVO searchVO);

	public List<MenusMngVO> getSiteList(MenusMngVO searchVO);

	public MenusMngVO getSite(MenusMngVO searchVO);

	public int getSiteIdx(String siteCode);

	public void insertSite(MenusMngVO insertVO);

	public int getSiteSeq();

	public void updateSite(MenusMngVO updateVO);

	public void deleteSite(MenusMngVO deleteVO);

	public List<MenusMngVO> getSiteCodeList(String searchQuery);

	public List<MenusMngVO> getSiteCodeListWhere(MenusMngVO searchVO);

	public MenusMngVO getSiteBySiteCode(String siteCode);

	public List<MenusMngVO> getListByPrmtType(String code);
}