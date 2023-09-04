package egovframework.portal.sys.log.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.log.mapper.BbsConfigLogMapper;
import egovframework.portal.sys.log.service.BbsConfigLogService;
import egovframework.portal.sys.log.vo.BbsConfigLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class BbsConfigLogServiceImpl extends EgovAbstractServiceImpl implements BbsConfigLogService {

	@Resource(name = "bbsConfigLogMapper")
	protected BbsConfigLogMapper bbsConfigLogMapper;

	@Override
	public List<BbsConfigLogVO> getBbsConfigLogList(BbsConfigLogVO searchVO) throws Exception {
		return bbsConfigLogMapper.getBbsConfigLogList(searchVO);
	}

	@Override
	public int getTotalBbsConfigLogCnt(BbsConfigLogVO searchVO) {
		return bbsConfigLogMapper.getTotalBbsConfigLogCnt(searchVO);
	}

	@Override
	public void insertBbsConfigLog(BbsConfigLogVO insertVO) throws Exception {
		bbsConfigLogMapper.insertBbsConfigLog(insertVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(BbsConfigLogVO searchVO) {
		return bbsConfigLogMapper.getTotalListAsMap(searchVO);
	}

}
