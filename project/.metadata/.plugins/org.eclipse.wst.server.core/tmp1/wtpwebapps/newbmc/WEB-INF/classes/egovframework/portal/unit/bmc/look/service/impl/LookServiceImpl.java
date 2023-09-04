package egovframework.portal.unit.bmc.look.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.look.mapper.LookMapper;
import egovframework.portal.unit.bmc.look.service.LookService;
import egovframework.portal.unit.bmc.look.vo.LookCustomPayVO;

@Service
public class LookServiceImpl implements LookService {
	
	@Autowired
	private LookMapper mapper;
	
	@Override
	public List<LookCustomPayVO> getList(LookCustomPayVO lookCustomPayVO) {
		return mapper.getList(lookCustomPayVO);
	}
	
	@Override
	public List<LookCustomPayVO> getReceiptList(LookCustomPayVO lookCustomPayVO) {
		return mapper.getReceiptList(lookCustomPayVO);
	}

	@Override
	public List<LookCustomPayVO> getList2(LookCustomPayVO lookCustomPayVO) {
		return mapper.getList2(lookCustomPayVO);
	}

}
