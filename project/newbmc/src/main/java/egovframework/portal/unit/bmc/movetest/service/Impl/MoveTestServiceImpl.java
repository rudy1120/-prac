package egovframework.portal.unit.bmc.movetest.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.movetest.mapper.MoveTestMapper;
import egovframework.portal.unit.bmc.movetest.service.MoveTestService;
import egovframework.portal.unit.bmc.movetest.vo.MoveTestVO;

@Service
public class MoveTestServiceImpl implements MoveTestService{
	
	@Resource 
	private MoveTestMapper moveTestmapper;

	@Override
	public List<MoveTestVO> getResInfo() {
		
		List<MoveTestVO> result;
		
		result = moveTestmapper.getResInfo();
		
		return result;
	}
	
	@Override
	public List<MoveTestVO> chkTime(MoveTestVO moveReserveVO) {
		
		List<MoveTestVO> result;
		
		result = moveTestmapper.chkTime(moveReserveVO);
		
		return result;
	}
	
	@Override
	public MoveTestVO getCustInfo(MoveTestVO moveReserveVO) {
		
		MoveTestVO result;
		
		result = moveTestmapper.getCustInfo(moveReserveVO);
		
		return result;
	}
	
	@Override
	public MoveTestVO getChkCustInfo(MoveTestVO moveReserveVO) {
		
		MoveTestVO result;
		
		result = moveTestmapper.getChkCustInfo(moveReserveVO);
		
		return result;
	}
	
	@Override
	public MoveTestVO chkReserve(MoveTestVO moveReserveVO) {
		MoveTestVO result;
		
		result = moveTestmapper.chkReserve(moveReserveVO);
		
		return result;
	}
	
	@Override
	public MoveTestVO chkCust(MoveTestVO moveReserveVO) {
		MoveTestVO result;
		
		result = moveTestmapper.chkCust(moveReserveVO);
		
		return result;
	}
	
	@Override
	public void reserve(MoveTestVO moveReserveVO) {
		moveTestmapper.reserve(moveReserveVO);
	}
	
	@Override
	public void resUp(MoveTestVO moveReserveVO) {
		moveTestmapper.resUp(moveReserveVO);
	}

	@Override
	public int chkRes(MoveTestVO moveReserveVO) {
		int result;
		result = moveTestmapper.chkRes(moveReserveVO);
		
		return result;
	}
	
	@Override
	public int chkResCust(MoveTestVO moveReserveVO) {
		int result;
		result = moveTestmapper.chkResCust(moveReserveVO);
		
		return result;
	}

	@Override
	public MoveTestVO chkGbn(MoveTestVO moveReserveVO) {
		MoveTestVO result;
		
		result = moveTestmapper.chkGbn(moveReserveVO);
		
		return result;
	}

}
