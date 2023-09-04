package egovframework.portal.sys.sysAuth.service;

import java.util.List;

import egovframework.portal.sys.sysAuth.vo.SiteAccessVO;

/**
 * 관리자 접근제한하기 위한 서비스 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.15		엄동건				최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author 엄동건
 * @since 2015.01.15
 */
public interface SiteAccessMngService {

	public int getAccessAllowListCnt(SiteAccessVO searchVO);

	public List<SiteAccessVO> getAccessAllowList(SiteAccessVO searchVO);

	public List<SiteAccessVO> getAccessAllowChkList(SiteAccessVO searchVO);

	public SiteAccessVO getAccessAllow(SiteAccessVO searchVO);

	public int getAccessAllowDupChk(SiteAccessVO searchVO);

	public void insertAccessAllow(SiteAccessVO insertVO);

	public void updateAccessAllow(SiteAccessVO updateVO);

	public void deleteAccessAllow(SiteAccessVO deleteVO);
}
