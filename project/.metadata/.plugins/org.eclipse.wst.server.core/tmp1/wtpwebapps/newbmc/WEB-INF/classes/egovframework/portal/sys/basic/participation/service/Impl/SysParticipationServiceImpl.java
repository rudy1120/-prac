package egovframework.portal.sys.basic.participation.service.Impl;

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
import egovframework.portal.sys.basic.participation.mapper.SysParticipationMapper;
import egovframework.portal.sys.basic.participation.service.SysParticipationService;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class SysParticipationServiceImpl extends EgovAbstractServiceImpl implements SysParticipationService {
	
	@Autowired
	protected SysParticipationMapper mapper;
	
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public int getTotalCnt(PartContVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<PartContVO> getList(PartContVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public String insert(PartContVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			PartContVO insertVO = BeanUtil.copy(searchVO, new PartContVO());
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
	public String update(PartContVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			PartContVO updateVO = BeanUtil.copy(searchVO, new PartContVO());
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
	public PartContVO getEntity(PartContVO searchVO) {
		PartContVO rtn = StringUtil.isNotBlank(String.valueOf(searchVO.getIdx())) ? mapper.getEntity(searchVO) : null;
		return rtn;
	}

	@Override
	public String delete(PartContVO searchVO) {
		try {
			PartContVO deleteVO = BeanUtil.copy(searchVO, new PartContVO());
			mapper.delete(deleteVO);

			return String.valueOf(deleteVO.getIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public PartContVO getContent(int idx) {
		return mapper.getContent(idx);
	}

	@Override
	public int getParticipantCnt(ParticipantVO searchVO) {
		return mapper.getParticipantCnt(searchVO);
	}

	@Override
	public List<ParticipantVO> getParticipantList(ParticipantVO searchVO) {
		return mapper.getParticipantList(searchVO);
	}
}
