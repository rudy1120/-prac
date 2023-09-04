package egovframework.portal.unit.bmc.sub.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.sub.mapper.SubscribeMapper;
import egovframework.portal.unit.bmc.sub.service.SubscribeService;
import egovframework.portal.unit.bmc.sub.vo.SubscribeVO;

@Service
public class SubscribeServiceImpl implements SubscribeService {

	@Resource 
	private SubscribeMapper subscribemapper;
	
	@Override
	public List<SubscribeVO> getWebInfo(SubscribeVO subscribeVO) {
		
		List<SubscribeVO> result;
		
		result = subscribemapper.getWebInfo(subscribeVO);
		
		return result;
	}
	
	@Override
	public List<SubscribeVO> getUpWebInfo(SubscribeVO subscribeVO) {
		
		List<SubscribeVO> result;
		
		result = subscribemapper.getUpWebInfo(subscribeVO);
		
		return result;
	}
	
	@Override
	public List<SubscribeVO> getDelWebInfo(SubscribeVO subscribeVO) {
		
		List<SubscribeVO> result;
		
		result = subscribemapper.getDelWebInfo(subscribeVO);
		
		return result;
	}

	@Override
	public void subWebzine(SubscribeVO subscribeVO) {
		
		subscribemapper.subWebzine(subscribeVO);
	}

	@Override
	public void upWebzine(SubscribeVO subscribeVO) {
		subscribemapper.upWebzine(subscribeVO);
		
	}

	@Override
	public void delWebzine(SubscribeVO subscribeVO) {
		subscribemapper.delWebzine(subscribeVO);
		
	}

}
