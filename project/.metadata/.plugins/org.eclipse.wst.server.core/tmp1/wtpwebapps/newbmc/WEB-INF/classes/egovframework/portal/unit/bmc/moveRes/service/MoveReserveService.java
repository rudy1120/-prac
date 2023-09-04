package egovframework.portal.unit.bmc.moveRes.service;

import java.util.List;

import egovframework.portal.unit.bmc.moveRes.vo.MoveReserveVO;

public interface MoveReserveService {

	List<MoveReserveVO> getResInfo();
	List<MoveReserveVO> chkTime(MoveReserveVO moveReserveVO);
	
	MoveReserveVO getCustInfoN(MoveReserveVO moveReserveVO);
	MoveReserveVO getCustInfo(MoveReserveVO moveReserveVO);
	MoveReserveVO getChkCustInfo(MoveReserveVO moveReserveVO);
	MoveReserveVO chkReserve(MoveReserveVO moveReserveVO);
	MoveReserveVO chkCust(MoveReserveVO moveReserveVO);
	MoveReserveVO chkGbn(MoveReserveVO moveReserveVO);
	
	void reserve(MoveReserveVO moveReserveVO);
	void resUp(MoveReserveVO moveReserveVO);
	
	int chkRes(MoveReserveVO moveReserveVO);
	int chkResCust(MoveReserveVO moveReserveVO);
	int chkThird(MoveReserveVO moveReserveVO);
	
	String getgbn2(MoveReserveVO moveReserveVO);
	
}
