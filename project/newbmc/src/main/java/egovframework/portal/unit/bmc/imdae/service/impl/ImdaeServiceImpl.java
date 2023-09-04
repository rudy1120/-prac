package egovframework.portal.unit.bmc.imdae.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.imdae.mapper.ImdaeMapper;
import egovframework.portal.unit.bmc.imdae.service.ImdaeService;
import egovframework.portal.unit.bmc.imdae.vo.ImdaeJiguVO;
import egovframework.portal.unit.bmc.imdae.vo.ImdaeWatingInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class ImdaeServiceImpl extends EgovAbstractServiceImpl implements ImdaeService {

	@Autowired
	private ImdaeMapper mapper;
	
	@Override
	public List<ImdaeJiguVO> getList(ImdaeJiguVO imdaeJiguVO) {
		return mapper.getList(imdaeJiguVO);
	}

	@Override
	public List<ImdaeWatingInfoVO> getWatingList(ImdaeWatingInfoVO imdaeWatingInfoVO) {
		return mapper.getWatingList(imdaeWatingInfoVO);
	}
	
	@Override
	public List<ImdaeJiguVO> getHappyList(ImdaeJiguVO imdaeJiguVO){
		return mapper.getHappyList(imdaeJiguVO);
	}

}
