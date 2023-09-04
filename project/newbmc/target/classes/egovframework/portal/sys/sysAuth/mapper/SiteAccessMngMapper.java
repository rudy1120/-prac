package egovframework.portal.sys.sysAuth.mapper;

import java.util.List;

import egovframework.portal.sys.sysAuth.vo.SiteAccessVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("siteAccessMngMapper")
public interface SiteAccessMngMapper {

	public int getAccessAllowListCnt(SiteAccessVO searchVO);

	public List<SiteAccessVO> getAccessAllowList(SiteAccessVO searchVO);

	public List<SiteAccessVO> getAccessAllowChkList(SiteAccessVO searchVO);

	public SiteAccessVO getAccessAllow(SiteAccessVO searchVO);

	public int getAccessAllowDupChk(SiteAccessVO searchVO);

	public void insertAccessAllow(SiteAccessVO insertVO);

	public void updateAccessAllow(SiteAccessVO updateVO);

	public void deleteAccessAllow(SiteAccessVO deleteVO);

}
