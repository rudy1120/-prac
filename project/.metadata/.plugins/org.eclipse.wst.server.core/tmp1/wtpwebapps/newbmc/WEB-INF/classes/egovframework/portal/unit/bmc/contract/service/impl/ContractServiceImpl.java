package egovframework.portal.unit.bmc.contract.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.contract.mapper.ContractMapper;
import egovframework.portal.unit.bmc.contract.service.ContractService;
import egovframework.portal.unit.bmc.contract.vo.ContractVO;
import egovframework.portal.unit.bmc.contract.vo.CtChangeVO;
import egovframework.portal.unit.bmc.contract.vo.CtInspectVO;
import egovframework.portal.unit.bmc.contract.vo.CtSubContractVO;
import egovframework.portal.unit.bmc.contract.vo.PaymentVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class ContractServiceImpl extends EgovAbstractServiceImpl implements ContractService {
	
	@Autowired
	ContractMapper mapper;
	
	@Override
	public int getTotalCnt(ContractVO contractVO) {
		
		return mapper.getTotalCnt(contractVO);
	}

	@Override
	public List<ContractVO> getList(ContractVO contractVO) {
		
		return mapper.getList(contractVO);
	}

	@Override
	public ContractVO getContent(ContractVO contractVO) {
		
		return mapper.getContent(contractVO);
	}

	@Override
	public List<CtChangeVO> getChangeInfo(ContractVO contractVO) {
		
		return mapper.getChangeInfo(contractVO);
	}

	@Override
	public List<CtInspectVO> getInspectInfo(ContractVO contractVO) {
		
		return mapper.getInspectInfo(contractVO);
	}

	@Override
	public List<CtSubContractVO> getSubcontractInfo(ContractVO contractVO) {
		return mapper.getSubcontractInfo(contractVO);
	}

	@Override
	public List<String> getDeptList() {
		return mapper.getDeptList();
	}

	@Override
	public int getPmTotalCnt(PaymentVO paymentVO) {
		
		return mapper.getPmTotalCnt(paymentVO);
	}

	@Override
	public List<PaymentVO> getPmList(PaymentVO paymentVO) {
		
		return mapper.getPmList(paymentVO);
	}

	@Override
	public int getTotalCnt2(ContractVO contractVO) {		
		return mapper.getTotalCnt2(contractVO);
	}

	@Override
	public List<ContractVO> getList2(ContractVO contractVO) {
		return mapper.getList2(contractVO);
	}

	
	
}
