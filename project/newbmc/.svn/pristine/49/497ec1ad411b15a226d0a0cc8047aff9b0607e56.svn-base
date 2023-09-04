package egovframework.portal.sys.pub.service.impl;

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

import egovframework.portal.sys.pub.mapper.SysPublicMapper;
import egovframework.portal.sys.pub.service.SysPublicService;
import egovframework.portal.sys.pub.vo.SysPublicVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class SysPublicServiceImpl extends EgovAbstractServiceImpl implements SysPublicService {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Resource
	private SysPublicMapper sysPublicMapper;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	@Override
	public List<SysPublicVO> getCategoryList(SysPublicVO publicVO) throws Exception {
		return sysPublicMapper.getCategoryList(publicVO);
	}
	
	@Override
	public SysPublicVO getCategoryEntity(SysPublicVO publicVO) throws Exception {
		SysPublicVO rtn = StringUtil.isNotBlank(publicVO.getCatIdx()) ? sysPublicMapper.getCategoryEntity(publicVO) : null;
		return rtn;
	}
	
	@Override
	public String insertCategory(SysPublicVO publicVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			sysPublicMapper.insertCategory(publicVO);
			transactionManager.commit(status);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String updateCategory(SysPublicVO publicVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			sysPublicMapper.updateCategory(publicVO);
			transactionManager.commit(status);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String deleteCategory(SysPublicVO publicVO) throws Exception {
		try {
			sysPublicMapper.deleteCategory(publicVO);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}
	
	@Override
	public int getDataTotalCnt(SysPublicVO publicVO) throws Exception {
		return sysPublicMapper.getDataTotalCnt(publicVO);
	}

	@Override
	public List<SysPublicVO> getDataList(SysPublicVO publicVO) throws Exception {
		return sysPublicMapper.getDataList(publicVO);
	}
	
	@Override
	public SysPublicVO getDataEntity(SysPublicVO publicVO) throws Exception {
		SysPublicVO rtn = StringUtil.isNotBlank(publicVO.getPubIdx()) ? sysPublicMapper.getDataEntity(publicVO) : null;
		return rtn;
	}
	
	@Override
	public String insertData(SysPublicVO publicVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			sysPublicMapper.insertData(publicVO);
			transactionManager.commit(status);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String updateData(SysPublicVO publicVO) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			sysPublicMapper.updateData(publicVO);
			transactionManager.commit(status);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String deleteData(SysPublicVO publicVO) throws Exception {
		try {
			sysPublicMapper.deleteData(publicVO);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public int getMonitorTotalCnt(SysPublicVO publicVO) {
		// TODO Auto-generated method stub
		return sysPublicMapper.getMonitorTotalCnt(publicVO);
	}

	@Override
	public List<SysPublicVO> getMonitorList(SysPublicVO publicVO) {
		// TODO Auto-generated method stub
		return sysPublicMapper.getMonitorList(publicVO);
	}

	@Override
	public String deleteMonitor(SysPublicVO publicVO) throws Exception{
		try {
			sysPublicMapper.deleteMonitor(publicVO);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

}
