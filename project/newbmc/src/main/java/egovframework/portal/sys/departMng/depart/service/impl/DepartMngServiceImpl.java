package egovframework.portal.sys.departMng.depart.service.impl;

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
import egovframework.portal.sys.departMng.depart.mapper.DepartMngMapper;
import egovframework.portal.sys.departMng.depart.service.DepartMngService;
import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class DepartMngServiceImpl extends EgovAbstractServiceImpl implements DepartMngService {

	@Autowired
	protected DepartMngMapper mapper;
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	@Autowired
	protected DataSourceTransactionManager transactionManager;

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public int getTotalCnt(DepartMngVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<DepartMngVO> getList(DepartMngVO searchVO) {
		return mapper.getList(searchVO);
	}
	
	@Override
	public DepartMngVO getEntity(DepartMngVO searchVO) {
		DepartMngVO rtn = StringUtil.isNotBlank(searchVO.getDepCode()) ? mapper.getEntity(searchVO) : null;
		return rtn;
	}
	
	@Override
	public String insert(DepartMngVO searchVO) {
		String code = searchVO.getNewParent();
		if (code.equals("0")) {
			searchVO.setDepCodeNm(searchVO.getNewSname());
			searchVO.setNewDepth(1);
		} else {
			DepartMngVO supVO = mapper.getParent(code);
			int depth = supVO.getNewDepth() + 1;
			searchVO.setNewDepth(depth);
			if (depth > 2) {
				searchVO.setDepCodeNm(supVO.getDepCodeNm() + " " + searchVO.getNewSname());
			} else {
				searchVO.setDepCodeNm(searchVO.getNewSname());
			}
		}
		
		searchVO.setDepCodeNm(StringEscapeUtils.unescapeHtml3(searchVO.getDepCodeNm()));
		searchVO.setDepComment(StringEscapeUtils.unescapeHtml3(searchVO.getDepComment()));
		
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
	public String update(DepartMngVO searchVO) {
		String code = searchVO.getNewParent();
		if (code.equals("0")) {
			searchVO.setDepCodeNm(searchVO.getNewSname());
			searchVO.setNewDepth(1);
		} else {
			DepartMngVO supVO = mapper.getParent(code);
			int depth = supVO.getNewDepth() + 1;
			searchVO.setNewDepth(depth);
			if (depth > 2) {
				searchVO.setDepCodeNm(supVO.getDepCodeNm() + " " + searchVO.getNewSname());
			} else {
				searchVO.setDepCodeNm(searchVO.getNewSname());
			}
		}
		
		searchVO.setDepCodeNm(StringEscapeUtils.unescapeHtml3(searchVO.getDepCodeNm()));
		searchVO.setDepComment(StringEscapeUtils.unescapeHtml3(searchVO.getDepComment()));
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			mapper.update(searchVO);
			mapper.staffUpdate(searchVO);
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
	public String delete(DepartMngVO searchVO) {
		try {
			mapper.delete(searchVO);
			return "SUCCESS";
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}
	
	@Override
	public List<DepartMngVO> getDepartList() {
		return mapper.getDepartList();
	}

}
