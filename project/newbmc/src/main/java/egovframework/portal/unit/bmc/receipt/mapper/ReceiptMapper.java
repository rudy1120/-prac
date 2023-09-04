package egovframework.portal.unit.bmc.receipt.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ReceiptMapper {

	public List<ReceiptContVO> getList(ReceiptContVO receiptContVO);

	public int getTotalCnt(ReceiptContVO receiptContVO);

	public ReceiptContVO getContent(ReceiptContVO receiptContVO);

	public void setViewCount(ReceiptContVO receiptContVO);

	public void stInsert(ReceiptVO insertVO);
	
	public void stsInsert(ReceiptVO insertVO);

	public List<ReceiptVO> read(ReceiptVO receiptVO);

	public int getReceptionistCnt(ReceiptVO receiptVO);

	public void deleteT(ReceiptVO deleteVO);

	public int getCheck(ReceiptVO receiptVO);
	
	String selectstt(ReceiptVO receiptVO);

	public void termInsert(ReceiptVO insertVO);

	public ReceiptVO getInfoB(ReceiptVO receiptVO);

	public void termUp(ReceiptVO insertVO);

	public int termChkIn(ReceiptVO receiptVO);

	public ReceiptVO getGbn(ReceiptVO receiptVO);

	public List<ReceiptVO> getReceptionistList(ReceiptVO receiptVO);
	
	ReceiptVO getInfo(ReceiptVO receiptVO);

	public int chkSkillS(ReceiptVO receiptVO);

	public void deleteS(ReceiptVO deleteVO);
	
	public ReceiptVO getEndDate(ReceiptVO receiptVO);
}
