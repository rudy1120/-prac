package egovframework.portal.unit.bmc.participation.service.impl;

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
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.unit.bmc.participation.mapper.ParticipationMapper;
import egovframework.portal.unit.bmc.participation.service.ParticipationService;
import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;
import egovframework.portal.util.BeanUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class ParticipationServiceImpl extends EgovAbstractServiceImpl implements ParticipationService {
	
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private ParticipationMapper participationMapper;

	@Override
	public List<PartContVO> getList(PartContVO partContVO) {
		List<PartContVO> list = participationMapper.getList(partContVO);
		return list;
	}

	@Override
	public int getTotalCnt(PartContVO partContVO) {
		return participationMapper.getTotalCnt(partContVO);
	}

	@Override
	public PartContVO getContent(PartContVO partContVO) {
		return participationMapper.getContent(partContVO);
	}

	@Override
	public void setViewCount(PartContVO partContVO) {
		participationMapper.setViewCount(partContVO);
	}

	@Override
	public String insert(ParticipantVO participantVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			ParticipantVO insertVO = BeanUtil.copy(participantVO, new ParticipantVO());
			insertVO.setAttachId( 
				fileUtil.storeFileAndGetAttachId( 
					request
					, EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + "/"
					, ""
					, null 
				) 
			);
			
			participationMapper.insert(insertVO);
			transactionManager.commit(status);

			return String.valueOf(insertVO.getPtidx());
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public List<ParticipantVO> read(ParticipantVO participantVO) {
		return participationMapper.read(participantVO);
	}

	@Override
	public int getParticipantCnt(ParticipantVO participantVO) {
		return participationMapper.getParticipantCnt(participantVO);
	}

	@Override
	public String delete(ParticipantVO participantVO) {
		try {
			ParticipantVO deleteVO = BeanUtil.copy(participantVO, new ParticipantVO());
			participationMapper.delete(deleteVO);
			return String.valueOf(deleteVO.getPtidx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public int getCheck(ParticipantVO participantVO) {
		
		return participationMapper.getCheck(participantVO);
	}
}
