package egovframework.portal.unit.bmc.moveRes.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.moveRes.vo.MoveReserveVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface MoveReserveMapper {
	
	List<MoveReserveVO> getResInfo();
	List<MoveReserveVO> chkTime(MoveReserveVO moveReserveVO);
	MoveReserveVO getCustInfoN(MoveReserveVO moveReserveVO);
	MoveReserveVO getCustInfo(MoveReserveVO moveReserveVO);
	MoveReserveVO getChkCustInfo(MoveReserveVO moveReserveVO);
	MoveReserveVO chkReserve(MoveReserveVO moveReserveVO);
	MoveReserveVO chkCust(MoveReserveVO moveReserveVO);
	
	void reserve(MoveReserveVO moveReserveVO);
	void resUp(MoveReserveVO moveReserveVO);
	int chkRes(MoveReserveVO moveReserveVO);
	int chkResCust(MoveReserveVO moveReserveVO);
	MoveReserveVO chkGbn(MoveReserveVO moveReserveVO);
	
	int chkThird(MoveReserveVO moveReserveVO);
	String getgbn2(MoveReserveVO moveReserveVO);
	
}
