package egovframework.portal.unit.bmc.moveRes.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.moveRes.mapper.MoveReserveMapper;
import egovframework.portal.unit.bmc.moveRes.service.MoveReserveService;
import egovframework.portal.unit.bmc.moveRes.vo.MoveReserveVO;

@Service
public class MoveResServiceImpl implements MoveReserveService{
	
	@Resource 
	private MoveReserveMapper moveReservemapper;

	/**** 일광행복주택 ****/
	@Override
	public List<MoveReserveVO> getResInfo() {
		List<MoveReserveVO> result;
		result = moveReservemapper.getResInfo();
		return result;
	}
	
	@Override
	public List<MoveReserveVO> chkTime(MoveReserveVO moveReserveVO) {
		List<MoveReserveVO> result;
		result = moveReservemapper.chkTime(moveReserveVO);
		return result;
	}
	
	@Override
	public MoveReserveVO getCustInfoN(MoveReserveVO moveReserveVO) {
		MoveReserveVO result;
		result = moveReservemapper.getCustInfoN(moveReserveVO);
		return result;
	}
	
	@Override
	public MoveReserveVO getCustInfo(MoveReserveVO moveReserveVO) {
		MoveReserveVO result;
		result = moveReservemapper.getCustInfo(moveReserveVO);
		return result;
	}
	
	@Override
	public MoveReserveVO getChkCustInfo(MoveReserveVO moveReserveVO) {
		MoveReserveVO result;
		result = moveReservemapper.getChkCustInfo(moveReserveVO);
		return result;
	}
	
	@Override
	public MoveReserveVO chkReserve(MoveReserveVO moveReserveVO) {
		MoveReserveVO result;
		result = moveReservemapper.chkReserve(moveReserveVO);
		return result;
	}
	
	@Override
	public MoveReserveVO chkCust(MoveReserveVO moveReserveVO) {
		MoveReserveVO result;
		result = moveReservemapper.chkCust(moveReserveVO);
		return result;
	}
	
	@Override
	public MoveReserveVO chkGbn(MoveReserveVO moveReserveVO) {
		MoveReserveVO result;
		result = moveReservemapper.chkGbn(moveReserveVO);
		return result;
	}
	
	@Override
	public void reserve(MoveReserveVO moveReserveVO) {
		moveReservemapper.reserve(moveReserveVO);
	}
	
	@Override
	public void resUp(MoveReserveVO moveReserveVO) {
		moveReservemapper.resUp(moveReserveVO);
	}

	@Override
	public int chkRes(MoveReserveVO moveReserveVO) {
		int result;
		result = moveReservemapper.chkRes(moveReserveVO);
		return result;
	}
	
	@Override
	public int chkResCust(MoveReserveVO moveReserveVO) {
		int result;
		result = moveReservemapper.chkResCust(moveReserveVO);
		return result;
	}
	
	@Override
	public int chkThird(MoveReserveVO moveReserveVO) {
		int result;
		result = moveReservemapper.chkThird(moveReserveVO);
		return result;
	}
	
	@Override
	public String getgbn2(MoveReserveVO moveReserveVO) {
		String result;
		result = moveReservemapper.getgbn2(moveReserveVO);
		return result;
	}
	

}
