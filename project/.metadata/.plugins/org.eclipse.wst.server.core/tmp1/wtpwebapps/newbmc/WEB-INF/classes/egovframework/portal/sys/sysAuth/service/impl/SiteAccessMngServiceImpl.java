package egovframework.portal.sys.sysAuth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.sysAuth.mapper.SiteAccessMngMapper;
import egovframework.portal.sys.sysAuth.service.SiteAccessMngService;
import egovframework.portal.sys.sysAuth.vo.SiteAccessVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 접근제한하기 위한 서비스 클래스
 *
 * @author 개발팀 엄동건
 * @since 2015-01-15
 * @version 1.0
 * @see
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2015-01-15 엄동건
 *
 * </pre>
 */
@Service("siteAccessMngService")
public class SiteAccessMngServiceImpl extends EgovAbstractServiceImpl implements
	SiteAccessMngService {

	@Resource(name = "siteAccessMngMapper")
	private SiteAccessMngMapper siteAccessMngMapper;

	public int getAccessAllowListCnt(SiteAccessVO searchVO) {
		return siteAccessMngMapper.getAccessAllowListCnt(searchVO);
	}

	public List<SiteAccessVO> getAccessAllowList(SiteAccessVO searchVO) {
		return siteAccessMngMapper.getAccessAllowList(searchVO);
	}

	public List<SiteAccessVO> getAccessAllowChkList(SiteAccessVO searchVO) {
		return siteAccessMngMapper.getAccessAllowChkList(searchVO);
	}

	public SiteAccessVO getAccessAllow(SiteAccessVO searchVO) {
		return siteAccessMngMapper.getAccessAllow(searchVO);
	}

	public int getAccessAllowDupChk(SiteAccessVO searchVO) {
		return siteAccessMngMapper.getAccessAllowDupChk(searchVO);
	}

	public void insertAccessAllow(SiteAccessVO insertVO) {
		siteAccessMngMapper.insertAccessAllow(insertVO);
	}

	public void updateAccessAllow(SiteAccessVO updateVO) {
		siteAccessMngMapper.updateAccessAllow(updateVO);
	}

	public void deleteAccessAllow(SiteAccessVO deleteVO) {
		siteAccessMngMapper.deleteAccessAllow(deleteVO);
	}
}
