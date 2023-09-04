package egovframework.portal.unit.bmc.moveRes.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.moveRes.mapper.MoveAgreeMapper;
import egovframework.portal.unit.bmc.moveRes.service.MoveAgreeService;
import egovframework.portal.unit.bmc.moveRes.vo.MoveAgreeVO;

@Service
public class MoveAgreeServiceImpl implements MoveAgreeService{
	
	@Resource 
	private MoveAgreeMapper moveAgreemapper;
	
	@Override
	public MoveAgreeVO getCustInfo(MoveAgreeVO moveAgreeVO) {
		MoveAgreeVO result;
		result = moveAgreemapper.getCustInfo(moveAgreeVO);
		return result;
	}
	
	@Override
	public void upChk(MoveAgreeVO moveAgreeVO) {
		moveAgreemapper.upChk(moveAgreeVO);
	}

}
