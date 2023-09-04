package egovframework.portal.sys.bbs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.bbs.mapper.BbsLogMngMapper;
import egovframework.portal.sys.bbs.service.BbsLogMngService;
import egovframework.portal.sys.bbs.vo.BbsLogMngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class BbsLogMngServiceImpl extends EgovAbstractServiceImpl implements BbsLogMngService {

	@Autowired
	protected BbsLogMngMapper bbsLogMngMapper;

	@Override
	public int getTotalCnt(BbsLogMngVO searchVO) {
		return bbsLogMngMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<BbsLogMngVO> getList(BbsLogMngVO searchVO) {
		return bbsLogMngMapper.getList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(BbsLogMngVO searchVO) {
		return bbsLogMngMapper.getTotalListAsMap(searchVO);
	}

}
