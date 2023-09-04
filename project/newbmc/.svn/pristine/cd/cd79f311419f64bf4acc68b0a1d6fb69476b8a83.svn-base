package egovframework.portal.sys.receipt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;

public interface SysReceiptService {

	int getTotalCnt(ReceiptContVO searchVO);

	List<ReceiptContVO> getList(ReceiptContVO searchVO);

	String insert(ReceiptContVO searchVO, MultipartHttpServletRequest request);

	String update(ReceiptContVO searchVO, MultipartHttpServletRequest request);

	ReceiptContVO getEntity(ReceiptContVO searchVO);

	String delete(ReceiptContVO searchVO);

	ReceiptContVO getContent(int idx);

	int getReceptionistCnt(ReceiptVO searchVO);

	List<ReceiptVO> getReceptionistList(ReceiptVO searchVO);

	List<ReceiptVO> getTCnt(ReceiptVO searchVO);

}
