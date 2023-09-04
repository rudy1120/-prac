package egovframework.portal.sys.privacy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.privacy.mapper.PrivacyDataMapper;
import egovframework.portal.sys.privacy.service.PrivacyDataService;
import egovframework.portal.sys.privacy.service.PrivacyPrmService;
import egovframework.portal.sys.privacy.vo.PrivacyDataHistoryVO;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class PrivacyDataServiceImpl extends EgovAbstractServiceImpl implements PrivacyDataService {

	@Autowired
	protected PrivacyPrmService prmService;
	@Autowired
	protected PrivacyDataMapper dataMapper;
	@Autowired
	protected DataSourceTransactionManager transactionManager;

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Map<String, Object>> deletePrivacyData(String hostIp) {
		List<Map<String, Object>> rtn = new ArrayList<>();
		Map<String, Object> info = null;

		PrivacyPrmVO searchVO = new PrivacyPrmVO();
		int totalPrmCnt = prmService.getTotalCnt(searchVO);
		int page = 1;

		List<PrivacyPrmVO> prmList = null;
		do {
			searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
			searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page)); // 프로그램 목록을 페이징 처리
			prmList = prmService.getList(searchVO);

			for (PrivacyPrmVO prm : prmList) {
				info = new HashMap<>();
				info.put("prm", prm);
				info.put("cnt", deleteExpiredPrivacyData(prm, hostIp)); // 개인정보 취급 데이터 삭제
				rtn.add(info);
			}

			totalPrmCnt -= prmList.size();
			page++;
		} while (totalPrmCnt > 0); // 처리할 프로그램 설정 정보가 있는 한 계속해서 처리

		return rtn;
	}

	private int deleteExpiredPrivacyData(PrivacyPrmVO prm, String hostIp) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 별도 트랜잭션 처리. 한 개의 프로그램 정보 삭제가 실패하더라도 다른 프로그램을 처리하도록.
		TransactionStatus status = transactionManager.getTransaction(def);

		int rtn = -1;
		try {
			prm.setParamsMap(); // json → Object
			prm.setParamsMap(prm.getSqlInjectionClearedParamsMap()); // SQL INJECTION 위험성 키값 제거
			prm.setPrmTriggerCol( // SQL INJECTION 제거 및 기본값 세팅
				StringUtil.isNotBlank(prm.getPrmTriggerCol()) && prm.getPrmTriggerCol().matches(Constant.COLUMN_REG) //
					? prm.getPrmTriggerCol() : "create_date" //
			);
			rtn = dataMapper.deleteExpiredPrivacyData(prm); // 데이터 삭제
			insertLog(prm, rtn, hostIp); // 처리 결과 로그 등록
			transactionManager.commit(status);
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
		}

		return rtn;
	}

	private void insertLog(PrivacyPrmVO prm, int rtn, String hostIp) {
		PrivacyDataHistoryVO insertVO = new PrivacyDataHistoryVO();
		insertVO.setPrmIdx(prm.getIdx());
		insertVO.setPrmName(prm.getPrmName());
		insertVO.setPrmTableName(prm.getPrmTableName());
		insertVO.setPrmPeriod(prm.getPrmPeriod());
		insertVO.setPrm(prm);
		insertVO.setProcCnt(rtn);
		insertVO.setHostIp(hostIp);
		dataMapper.insertLog(insertVO);
	}

	@Override
	public int getTotalCnt(PrivacyDataHistoryVO searchVO) {
		return dataMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<PrivacyDataHistoryVO> getList(PrivacyDataHistoryVO searchVO) {
		return dataMapper.getList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(PrivacyDataHistoryVO searchVO) {
		return dataMapper.getTotalListAsMap(searchVO);
	}

}
