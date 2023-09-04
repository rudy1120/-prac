package egovframework.portal.unit.bmc.sabo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.sabo.mapper.SaboSubscribeMapper;
import egovframework.portal.unit.bmc.sabo.service.SaboSubscribeService;
import egovframework.portal.unit.bmc.sabo.vo.SaboSubscribeVO;


@Service
public class SaboSubscribeServiceImpl implements SaboSubscribeService {

	@Resource 
	private SaboSubscribeMapper saboSubscribemapper;
	
	@Override
	public List<SaboSubscribeVO> getSaboInfo(SaboSubscribeVO saboSubscribeVO) {
		
		List<SaboSubscribeVO> result;
		
		result = saboSubscribemapper.getSaboInfo(saboSubscribeVO);
		
		return result;
	}
	
	@Override
	public List<SaboSubscribeVO> getUpSaboInfo(SaboSubscribeVO saboSubscribeVO) {
		
		List<SaboSubscribeVO> result;
		
		result = saboSubscribemapper.getUpSaboInfo(saboSubscribeVO);
		
		return result;
	}
	
	@Override
	public List<SaboSubscribeVO> getDelSaboInfo(SaboSubscribeVO saboSubscribeVO) {
		
		List<SaboSubscribeVO> result;
		
		result = saboSubscribemapper.getDelSaboInfo(saboSubscribeVO);
		
		return result;
	}

	@Override
	public void subSabo(SaboSubscribeVO saboSubscribeVO) {
		
		saboSubscribemapper.subSabo(saboSubscribeVO);
	}

	@Override
	public void upSabo(SaboSubscribeVO saboSubscribeVO) {
		saboSubscribemapper.upSabo(saboSubscribeVO);
		
	}

	@Override
	public void delSabo(SaboSubscribeVO saboSubscribeVO) {
		saboSubscribemapper.delSabo(saboSubscribeVO);
		
	}

}
