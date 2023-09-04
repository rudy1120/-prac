package egovframework.portal.unit.bmc.movetest.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.movetest.vo.MoveTestVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface MoveTestMapper {
	
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
