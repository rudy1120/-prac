package egovframework.portal.sys.receipt.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysReceiptMapper {

	int getTotalCnt(ReceiptContVO searchVO);

	List<ReceiptContVO> getList(ReceiptContVO searchVO);

	void insert(ReceiptContVO insertVO);

	void update(ReceiptContVO updateVO);

	ReceiptContVO getEntity(ReceiptContVO searchVO);

	void delete(ReceiptContVO deleteVO);

	ReceiptContVO getContent(int idx);

	int getReceptionistCnt(ReceiptVO searchVO);

	List<ReceiptVO> getReceptionistList(ReceiptVO searchVO);

	List<ReceiptVO> getTCnt(ReceiptVO searchVO);
}
