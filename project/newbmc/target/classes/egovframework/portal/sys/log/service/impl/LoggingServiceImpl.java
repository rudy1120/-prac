package egovframework.portal.sys.log.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.portal.sys.log.LogType;
import egovframework.portal.sys.log.mapper.LoggingMapper;
import egovframework.portal.sys.log.service.LoggingConfigService;
import egovframework.portal.sys.log.service.LoggingHistoryService;
import egovframework.portal.sys.log.service.LoggingService;
import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 웹로그 패이지 태깅용 SERVICE IMPL
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 3. 27.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 3. 27.
 */
@Service
public class LoggingServiceImpl extends EgovAbstractServiceImpl implements LoggingService {

	@Resource(name = "loggingMapper")
	protected LoggingMapper mapper;
	@Autowired
	protected LoggingHistoryService historyService;
	@Autowired
	protected LoggingConfigService configService;
	@Autowired
	protected DataSourceTransactionManager transactionManager;

	public static final Logger LOGGER = Logger.getLogger(LoggingServiceImpl.class.getName());

	@Override
	public void logging(LoggingVO params) {
		try {
			mapper.adminLogInsert(params);
		} catch (Exception e) {
			LOGGER.error(">> failed log.logging", e);
		}
	}

	@Override
	public int getTotalCnt(LoggingVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<LoggingVO> getList(LoggingVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(LoggingVO searchVO) {
		return mapper.getTotalListAsMap(searchVO);
	}

	@Override
	public List<Map<String, Object>> deleteExpiredLogs(AdminLoginVO admin, String hostIp) {
		List<Map<String, Object>> rtn = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		List<LoggingConfigVO> configList = configService.getList(new LoggingConfigVO());

		for (LoggingConfigVO config : configList) {
			result.put("config", config);
			result.put("cnt", deleteExpiredLogs(config, admin, hostIp));
			rtn.add(result);
		}

		return rtn;
	}

	/** config에 해당하는 보관 기간 만료 로그 일괄 삭제 @return 삭제 건수 */
	private int deleteExpiredLogs(LoggingConfigVO config, AdminLoginVO admin, String hostIp) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 별도 트랜잭션 처리. 한 개의 로그 정보 삭제가 실패하더라도 다음 플로우 처리.
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			final int page = 1;
			int resultCnt = 0; // return value: 실제 처리 총 건수
			int totalCnt = 0; // total target cnt: 예상 처리 총 건수(삭제 대상에 해당하는 로그 총 건수)

			LoggingVO searchVO = new LoggingVO(); // 삭제 대상 로그 목록 FETCH 파라미터
			searchVO.setConfig(config);
			searchVO.setSearchProcType(config.getLogType());
			searchVO.setLogTypeSearcher(LogType.toType(config.getLogType()).getSql()); // 삭제 대상 로그 목록 추출을 위해 로그 타입 분석 후 추가 sql을 세팅
			totalCnt = mapper.getTotalCnt(searchVO);

			List<LoggingVO> deleteTargetList = null; // 삭제 대상 로그 목록

			Map<String, Object> params = new HashMap<>(); // 로그 삭제용 파라미터
			params.put("config", config);

			do { // 로그 일괄 삭제
				searchVO.setFirstIndex(page);
				searchVO.setLastIndex(page + 50);
				deleteTargetList = mapper.getList(searchVO);
				params.put("targets", deleteTargetList); // 삭제 대상 목록 추가

				if (deleteTargetList.size() > 0) {
					mapper.deleteExpiredLogs(params); // 로그 일괄 삭제
					resultCnt += deleteTargetList.size(); // 처리 결과수 증가
					totalCnt -= deleteTargetList.size(); // 남은 처리 건수 감소
				}
			} while (0 < totalCnt);

			historyService.insert(config, resultCnt, admin, hostIp); // 삭제 이력

			transactionManager.commit(status);

			return resultCnt;
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return -1;
		}
	}

}
