package egovframework.portal.unit.bmc.moveRes.service;

import egovframework.portal.unit.bmc.moveRes.vo.MoveAgreeVO;

public interface MoveAgreeService {
	
	//명단확인
	MoveAgreeVO getCustInfo(MoveAgreeVO moveAgreeVO);
	
	//동의 데이터 업데이트
	void upChk(MoveAgreeVO moveAgreeVO);
	
}
