package egovframework.portal.sys.sabo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.sabo.mapper.SysSaboMapper;
import egovframework.portal.sys.sabo.service.SysSaboService;
import egovframework.portal.sys.sabo.vo.SysSaboVO;

@Service
public class SysSaboServiceImpl implements SysSaboService {
	
	@Resource
	private SysSaboMapper syssaboMapper;

	@Override
	public int getTotalCnt(SysSaboVO searchVO) throws Exception {
		return syssaboMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<SysSaboVO> getList(SysSaboVO searchVO) throws Exception {
		return syssaboMapper.getList(searchVO);
	}

	@Override
	public List<SysSaboVO> getExcelList(SysSaboVO searchVO) throws Exception {
		
		return syssaboMapper.getExcelList(searchVO);
	}

}
