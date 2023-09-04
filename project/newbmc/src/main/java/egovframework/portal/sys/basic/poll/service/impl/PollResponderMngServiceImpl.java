package egovframework.portal.sys.basic.poll.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import egovframework.portal.common.TransDefinition;
import egovframework.portal.sys.basic.poll.PollLotteryType;
import egovframework.portal.sys.basic.poll.mapper.PollMngServiceMapper;
import egovframework.portal.sys.basic.poll.mapper.PollResponderMngMapper;
import egovframework.portal.sys.basic.poll.service.PollResponderMngService;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponderMngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class PollResponderMngServiceImpl extends EgovAbstractServiceImpl implements PollResponderMngService {

	@Resource
	protected PollMngServiceMapper pollMapper;
	@Resource
	protected PollResponderMngMapper responderMapper;
	@Resource(name = "txManager")
	private PlatformTransactionManager transactionManager;

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public int getTotalCnt(PollResponderMngVO searchVO) {
		return responderMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<PollResponderMngVO> getList(PollResponderMngVO searchVO) {
		return responderMapper.getList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(PollResponderMngVO searchVO) {
		return responderMapper.getTotalListAsMap(searchVO);
	}

	@Override
	public PollResponderMngVO getEntity(PollResponderMngVO searchVO) {
		return responderMapper.getEntity(searchVO);
	}

	@Override
	public Boolean lotteryCancel(PollMngVO poll, PollResponderMngVO entity) {
		if (isCancelable(poll, entity)) {
			try {
				responderMapper.lotteryCancel(entity);
				return Boolean.TRUE;
			} catch (DataAccessException e) {
				LOGGER.error("", e);
			}
		}

		return Boolean.FALSE;
	}

	@Override
	public Boolean lot(PollMngVO poll) {
		if (poll != null && poll.hasResponderInfo() && poll.isLottable()) {
			TransactionStatus status = transactionManager.getTransaction(TransDefinition.getRequiresNew());
			try {
				pollMapper.updateLotDate(poll);
				responderMapper.lot(poll);
				transactionManager.commit(status);
				return Boolean.TRUE;
			} catch (DataAccessException e) {
				LOGGER.error("", e);
				transactionManager.rollback(status);
			}
		}

		return Boolean.FALSE;
	}

	private Boolean isCancelable(PollMngVO poll, PollResponderMngVO entity) {
		return poll != null && poll.hasResponderInfo() && //
			poll.getLotDate() != null && //
			entity.getLottery().equals(PollLotteryType.WIN.getCode());
	}

}
