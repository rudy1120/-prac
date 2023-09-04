package egovframework.portal.sys.basic.event.service.impl;

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
import egovframework.portal.sys.basic.event.mapper.SysEventMapper;
import egovframework.portal.sys.basic.event.service.SysEventService;
import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;

@Service
public class SysEventServiceImpl implements SysEventService {

	@Autowired
	protected SysEventMapper mapper;
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	
	@Override
	public int getTotalCnt(EventContentVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<EventContentVO> getList(EventContentVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public EventContentVO getEntity(EventContentVO searchVO) {
		EventContentVO rtn = StringUtil.isNotBlank(String.valueOf(searchVO.getIdx())) ? mapper.getEntity(searchVO) : null;
		return rtn;
	}

	@Override
	public String insert(EventContentVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			EventContentVO insertVO = BeanUtil.copy(searchVO, new EventContentVO());
			insertVO.setAttachFile( //
				fileUtil.storeFileAndGetAttachId( //
					request, //
					EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + "/", //
					"", null //
				) //
			);
			String exedate = insertVO.getSdate() + " ~ " + insertVO.getEdate();
			String pubdate = insertVO.getPubdate();
			if (!insertVO.getPubtime().replace(" ", "").equals("")) {
				pubdate = pubdate + " " + insertVO.getPubtime();
			}
			if (!insertVO.getPubetc().replace(" ", "").equals("")) {
				pubdate = pubdate + ", " + insertVO.getPubetc();
			}
			
			insertVO.setExedate(exedate);
			insertVO.setPubdate(pubdate);
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
	public String update(EventContentVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			EventContentVO updateVO = BeanUtil.copy(searchVO, new EventContentVO());
			updateVO.setAttachFile( //
				fileUtil.storeFileAndGetAttachId( //
					request, //
					EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + "/", //
					"", updateVO.getAttachFile() //
				) //
			);
			
			String exedate = updateVO.getSdate() + " ~ " + updateVO.getEdate();
			String pubdate = updateVO.getPubdate();
			if (!updateVO.getPubtime().replace(" ", "").equals("")) {
				pubdate = pubdate + " " + updateVO.getPubtime();
			}
			if (!updateVO.getPubetc().replace(" ", "").equals("")) {
				pubdate = pubdate + ", " + updateVO.getPubetc();
			}
			
			updateVO.setExedate(exedate);
			updateVO.setPubdate(pubdate);
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
	public String delete(EventContentVO searchVO) {
		try {
			EventContentVO deleteVO = BeanUtil.copy(searchVO, new EventContentVO());
			mapper.delete(deleteVO);

			return String.valueOf(deleteVO.getIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public EventContentVO getContent(EventContentVO eventContentVO) {
		
		return mapper.getContent(eventContentVO);
	}

	@Override
	public List<EventParticipantVO> getParList(EventParticipantVO eventParticipantVO) {
		
		return mapper.getParList(eventParticipantVO);
	}

	@Override
	public String loc(EventContentVO searchVO) {
		try {
			EventContentVO locVO = BeanUtil.copy(searchVO, new EventContentVO());
			mapper.loc(locVO);
			mapper.locEnd(searchVO);
			return String.valueOf(locVO.getIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}
	@Override
	public String loc2(EventContentVO searchVO) {
		try {
			EventContentVO locVO = BeanUtil.copy(searchVO, new EventContentVO());
			mapper.locEnd(searchVO);
			return String.valueOf(locVO.getIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}
	
}
