package egovframework.portal.sys.MenuMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.MenuMng.mapper.PrivacyMenuMapper;
import egovframework.portal.sys.MenuMng.service.PrivacyMenuService;
import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class PrivacyMenuServiceImpl extends EgovAbstractServiceImpl implements PrivacyMenuService {

	@Autowired
	protected PrivacyMenuMapper mapper;


	@Override
	public int getTotalCnt(SysMenuVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<SysMenuVO> getList(SysMenuVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(LoggingVO searchVO) {
		return mapper.getTotalListAsMap(searchVO);
	}

}
