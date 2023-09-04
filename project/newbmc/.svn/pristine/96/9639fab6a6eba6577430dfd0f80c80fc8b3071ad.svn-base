package egovframework.portal.unit.bmc.contract.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.contract.vo.ContractVO;
import egovframework.portal.unit.bmc.contract.vo.CtChangeVO;
import egovframework.portal.unit.bmc.contract.vo.CtInspectVO;
import egovframework.portal.unit.bmc.contract.vo.CtSubContractVO;
import egovframework.portal.unit.bmc.contract.vo.PaymentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ContractMapper {

	int getTotalCnt(ContractVO contractVO);

	List<ContractVO> getList(ContractVO contractVO);

	ContractVO getContent(ContractVO contractVO);

	List<CtChangeVO> getChangeInfo(ContractVO contractVO);

	List<CtInspectVO> getInspectInfo(ContractVO contractVO);

	List<CtSubContractVO> getSubcontractInfo(ContractVO contractVO);

	List<String> getDeptList();

	int getPmTotalCnt(PaymentVO paymentVO);

	List<PaymentVO> getPmList(PaymentVO paymentVO);

	int getTotalCnt2(ContractVO contractVO);

	List<ContractVO> getList2(ContractVO contractVO);

}
