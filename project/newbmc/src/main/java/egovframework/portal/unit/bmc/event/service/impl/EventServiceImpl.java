package egovframework.portal.unit.bmc.event.service.impl;

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
import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;
import egovframework.portal.unit.bmc.event.mapper.EventMapper;
import egovframework.portal.unit.bmc.event.service.EventService;
import egovframework.portal.util.BeanUtil;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	protected EventMapper mapper;
	
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public int getTotalCnt(EventContentVO eventContentVO) {
		return mapper.getTotalCnt(eventContentVO);
	}

	@Override
	public List<EventContentVO> getList(EventContentVO eventContentVO) {
		List<EventContentVO> list = mapper.getList(eventContentVO);
		return list;
	}

	@Override
	public EventContentVO getContent(EventContentVO eventContentVO) {
		return mapper.getContent(eventContentVO);
	}

	@Override
	public String insert(EventParticipantVO eventParticipantVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			EventParticipantVO insertVO = BeanUtil.copy(eventParticipantVO, new EventParticipantVO());
			insertVO.setAttachId( 
					fileUtil.storeFileAndGetAttachId( 
						request
						, EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + "/"
						, ""
						, null 
					) 
				);
			mapper.insert(insertVO);
			transactionManager.commit(status);

			return String.valueOf(insertVO.getNum());
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public List<EventParticipantVO> getResult(EventParticipantVO eventParticipantVO) {
		return mapper.getResult(eventParticipantVO);
	}

	@Override
	public int getCheck(EventParticipantVO eventParticipantVO) {
		
		return mapper.getCheck(eventParticipantVO);
	}
}
