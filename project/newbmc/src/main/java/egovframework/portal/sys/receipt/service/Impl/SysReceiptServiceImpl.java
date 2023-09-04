package egovframework.portal.sys.receipt.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.receipt.mapper.SysReceiptMapper;
import egovframework.portal.sys.receipt.service.SysReceiptService;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class SysReceiptServiceImpl extends EgovAbstractServiceImpl implements SysReceiptService {
	
	@Autowired
	protected SysReceiptMapper mapper;
	
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public int getTotalCnt(ReceiptContVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<ReceiptContVO> getList(ReceiptContVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public String insert(ReceiptContVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			ReceiptContVO insertVO = BeanUtil.copy(searchVO, new ReceiptContVO());
			insertVO.setThumbnail( //
				fileUtil.storeFileAndGetAttachId( //
					request, //
					EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + "/", //
					"", null //
				) //
			);
			
			mapper.insert(insertVO);
			
			transactionManager.commit(status);

			return String.valueOf(insertVO.getIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String update(ReceiptContVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			ReceiptContVO updateVO = BeanUtil.copy(searchVO, new ReceiptContVO());
			updateVO.setThumbnail( //
				fileUtil.storeFileAndGetAttachId( //
					request, //
					EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + "/", //
					"", updateVO.getThumbnail() //
				) //
			);
			mapper.update(updateVO); 
			transactionManager.commit(status);

			return String.valueOf(updateVO.getIdx());
		
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public ReceiptContVO getEntity(ReceiptContVO searchVO) {
		ReceiptContVO rtn = StringUtil.isNotBlank(String.valueOf(searchVO.getIdx())) ? mapper.getEntity(searchVO) : null;
		return rtn;
	}

	@Override
	public String delete(ReceiptContVO searchVO) {
		try {
			ReceiptContVO deleteVO = BeanUtil.copy(searchVO, new ReceiptContVO());
			mapper.delete(deleteVO);

			return String.valueOf(deleteVO.getIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public ReceiptContVO getContent(int idx) {
		return mapper.getContent(idx);
	}

	@Override
	public int getReceptionistCnt(ReceiptVO searchVO) {
		return mapper.getReceptionistCnt(searchVO);
	}

	@Override
	public List<ReceiptVO> getReceptionistList(ReceiptVO searchVO) {
		return mapper.getReceptionistList(searchVO);
	}
	
	@Override
	public List<ReceiptVO> getTCnt(ReceiptVO searchVO) {
		return mapper.getTCnt(searchVO);
	}
	
}
