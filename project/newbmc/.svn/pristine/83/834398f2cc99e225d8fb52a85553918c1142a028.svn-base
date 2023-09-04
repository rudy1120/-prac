package egovframework.portal.unit.bmc.pub.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.portal.unit.bmc.pub.mapper.PublicMapper;
import egovframework.portal.unit.bmc.pub.service.PublicService;
import egovframework.portal.unit.bmc.pub.vo.PublicVO;

@Service
public class PublicServiceImpl implements PublicService {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private PublicMapper mapper;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	@Override
	public List<PublicVO> getCategoryList(PublicVO publicVO) throws Exception {
		return mapper.getCategoryList(publicVO);
	}
	
	@Override
	public int getDataTotalCnt(PublicVO publicVO) throws Exception {
		return mapper.getDataTotalCnt(publicVO);
	}

	@Override
	public List<PublicVO> getDataList(PublicVO publicVO) throws Exception {
		return mapper.getDataList(publicVO);
	}
	
	@Override
	public List<PublicVO> getStatsList(PublicVO publicVO) throws Exception {
		return mapper.getStatsList(publicVO);
	}
	
	@Override
	public String insertStats(PublicVO publicVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			mapper.insertStats(publicVO);
			transactionManager.commit(status);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public List<String> getDepartList() {
		
		return mapper.getDepartList();
	}

	@Override
	public String insert(PublicVO publicVO, HttpServletRequest request) throws Exception {
		mapper.insert(publicVO);
		return String.valueOf(publicVO.getIdx());
	}
	
}
