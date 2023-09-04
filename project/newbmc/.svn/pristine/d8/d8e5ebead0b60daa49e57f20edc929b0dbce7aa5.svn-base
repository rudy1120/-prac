package egovframework.portal.unit.bmc.opendoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.opendoc.mapper.OpendocMapper;
import egovframework.portal.unit.bmc.opendoc.service.OpendocService;
import egovframework.portal.unit.bmc.opendoc.vo.OpendocVO;

@Service
public class OpendocServiceImpl implements OpendocService {
	
	@Autowired
	private OpendocMapper mapper;
	
	@Override
	public int getListCnt(OpendocVO opendocVO) {
		return mapper.getListCnt(opendocVO);
	}
	
	@Override
	public List<OpendocVO> getList(OpendocVO opendocVO) {
		return mapper.getList(opendocVO);
	}
	
	@Override
	public OpendocVO getContent(OpendocVO opendocVO) {
		return mapper.getContent(opendocVO);
	}

}
