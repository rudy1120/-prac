package egovframework.portal.sys.departMng.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;
import egovframework.portal.sys.departMng.emp.mapper.EmpMngMapper;
import egovframework.portal.sys.departMng.emp.service.EmpMngService;
import egovframework.portal.sys.departMng.emp.vo.EmpMngVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class EmpMngServiceImpl extends EgovAbstractServiceImpl implements EmpMngService {

	@Autowired
	protected EmpMngMapper mapper;
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	@Autowired
	protected DataSourceTransactionManager transactionManager;

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public int getTotalCnt(EmpMngVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<EmpMngVO> getList(EmpMngVO searchVO) {
		return mapper.getList(searchVO);
	}
	
	@Override
	public EmpMngVO getEntity(EmpMngVO searchVO) {
		EmpMngVO rtn = StringUtil.isNotBlank(searchVO.getUsrId()) ? mapper.getEntity(searchVO) : null;
		return rtn;
	}
	
	@Override
	public String insert(EmpMngVO searchVO) {
		
		searchVO.setAdiInfo7(StringEscapeUtils.unescapeHtml3(searchVO.getAdiInfo7()));
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			mapper.insert(searchVO);
			transactionManager.commit(status);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String update(EmpMngVO searchVO) {
		
		searchVO.setAdiInfo7(StringEscapeUtils.unescapeHtml3(searchVO.getAdiInfo7()));
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			mapper.update(searchVO);
			mapper.chargeUpdate(searchVO);
			transactionManager.commit(status);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String delete(EmpMngVO searchVO) {
		try {
			mapper.delete(searchVO);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}
	
	@Override
	public int getUsrIdCheck(EmpMngVO searchVO) {
		return mapper.getUsrIdCheck(searchVO);
	}

	@Override
	public List<EmpMngVO> getStaffContents(String depcode) {
		return mapper.getStaffContents(depcode);
	}

	@Override
	public List<EmpMngVO> getStaffSearch(DepartMngVO searchVO) {
		
		return mapper.getStaffSearch(searchVO);
	}

}
