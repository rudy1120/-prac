package egovframework.portal.sys.log.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 통합 로그 기록/삭제/관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 12.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 12.
 */
public interface LoggingService {

	/** 로그 기록 */
	void logging(LoggingVO params);

	/** @return 전체 로그 건수 */
	int getTotalCnt(LoggingVO searchVO);

	/** @return 전체 로그 보관 설정 목록 */
	List<LoggingVO> getList(LoggingVO searchVO);

	/** @return 전체 로그 보관 설정 목록(MAP) */
	List<Map<String, String>> getTotalListAsMap(LoggingVO searchVO);

	/** 보관 기간이 만료된 모든 로그를 삭제 @return 맵(설정-삭제 건수) */
	List<Map<String, Object>> deleteExpiredLogs(AdminLoginVO admin, String hostIp);

}
