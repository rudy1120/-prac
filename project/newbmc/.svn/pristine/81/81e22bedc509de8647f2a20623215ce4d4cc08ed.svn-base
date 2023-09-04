package egovframework.portal.unit.bmc.receipt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;

public interface ReceiptService {

	List<ReceiptContVO> getList(ReceiptContVO receiptContVO);

	int getTotalCnt(ReceiptContVO receiptContVO);

	ReceiptContVO getContent(ReceiptContVO receiptContVO);

	void setViewCount(ReceiptContVO receiptContVO);

	boolean stInsert(MultipartHttpServletRequest request, ReceiptVO ReceiptVO) throws Exception;

	List<ReceiptVO> read(ReceiptVO ReceiptVO);

	int getReceptionistCnt(ReceiptVO ReceiptVO);

	String deleteT(ReceiptVO ReceiptVO);

	int getCheck(ReceiptVO ReceiptVO);

	boolean termInsert(MultipartHttpServletRequest request, ReceiptVO receiptVO) throws Exception;

	ReceiptVO getInfoB(ReceiptVO receiptVO);

	boolean termUp(MultipartHttpServletRequest request, ReceiptVO receiptVO) throws Exception;

	int termChkIn(ReceiptVO receiptVO);

	ReceiptVO getGbn(ReceiptVO receiptVO);

	List<ReceiptVO> getReceptionistList(ReceiptVO receiptVO);

	ReceiptVO getInfo(ReceiptVO receiptVO);

	int chkSkillS(ReceiptVO receiptVO);

	String deleteS(ReceiptVO receiptVO);
	
	ReceiptVO getEndDate(ReceiptVO receiptVO);

}
