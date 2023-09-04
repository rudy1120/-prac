package egovframework.portal.unit.bmc.look.service;

import java.util.List;

import egovframework.portal.unit.bmc.look.vo.LookCustomPayVO;

public interface LookService {

	List<LookCustomPayVO> getList(LookCustomPayVO lookCustomPayVO);
	
	List<LookCustomPayVO> getReceiptList(LookCustomPayVO lookCustomPayVO);

	List<LookCustomPayVO> getList2(LookCustomPayVO lookCustomPayVO);

}
