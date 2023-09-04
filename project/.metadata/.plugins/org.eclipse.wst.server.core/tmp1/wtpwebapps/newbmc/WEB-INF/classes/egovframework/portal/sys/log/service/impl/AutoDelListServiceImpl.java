package egovframework.portal.sys.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.log.mapper.AutoDelListMapper;
import egovframework.portal.sys.log.service.AutoDelListService;
import egovframework.portal.sys.log.vo.AutoDelListVO;

@Service
public class AutoDelListServiceImpl implements AutoDelListService {

	@Resource
	private AutoDelListMapper autoDelListMapper;
	
	@Override
	public int getTotalCnt(AutoDelListVO searchVO) throws Exception {
		return autoDelListMapper.getTotalCnt(searchVO);
	}
	
	@Override
	public List<AutoDelListVO> getList(AutoDelListVO searchVO) throws Exception {
		return autoDelListMapper.getList(searchVO);
	}

}
