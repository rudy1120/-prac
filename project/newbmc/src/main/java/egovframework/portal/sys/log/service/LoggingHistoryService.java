package egovframework.portal.sys.log.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.log.vo.LoggingHistoryVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

public interface LoggingHistoryService {

	int getTotalCnt(LoggingHistoryVO searchVO);

	List<LoggingHistoryVO> getList(LoggingHistoryVO searchVO);

	List<Map<String, String>> getTotalListAsMap(LoggingHistoryVO searchVO);

	void insert(LoggingConfigVO config, int delCnt, AdminLoginVO admin, String hostIp);

}
