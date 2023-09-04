package egovframework.portal.unit.bmc.movetest.service;

import java.util.List;

import egovframework.portal.unit.bmc.movetest.vo.MoveTestVO;

public interface MoveTestService {
	List<MoveTestVO> getResInfo();
	List<MoveTestVO> chkTime(MoveTestVO moveReserveVO);
	MoveTestVO getCustInfo(MoveTestVO moveReserveVO);
	MoveTestVO getChkCustInfo(MoveTestVO moveReserveVO);
	MoveTestVO chkReserve(MoveTestVO moveReserveVO);
	MoveTestVO chkCust(MoveTestVO moveReserveVO);
	
	void reserve(MoveTestVO moveReserveVO);
	void resUp(MoveTestVO moveReserveVO);
	int chkRes(MoveTestVO moveReserveVO);
	int chkResCust(MoveTestVO moveReserveVO);
	MoveTestVO chkGbn(MoveTestVO moveReserveVO);

}
