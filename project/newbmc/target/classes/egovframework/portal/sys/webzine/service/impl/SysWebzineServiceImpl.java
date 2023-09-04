package egovframework.portal.sys.webzine.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.webzine.mapper.SysWebzineMapper;
import egovframework.portal.sys.webzine.service.SysWebzineService;
import egovframework.portal.sys.webzine.vo.SysWebzineVO;

@Service
public class SysWebzineServiceImpl implements SysWebzineService {
	
	@Resource
	private SysWebzineMapper syswebzineMapper;

	@Override
	public int getTotalCnt(SysWebzineVO searchVO) throws Exception {
		return syswebzineMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<SysWebzineVO> getList(SysWebzineVO searchVO) throws Exception {
		return syswebzineMapper.getList(searchVO);
	}

	@Override
	public List<SysWebzineVO> getExcelList(SysWebzineVO searchVO) throws Exception {
		
		return syswebzineMapper.getExcelList(searchVO);
	}

}
