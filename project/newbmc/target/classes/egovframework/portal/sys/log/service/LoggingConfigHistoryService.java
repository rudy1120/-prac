package egovframework.portal.sys.log.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.log.vo.LoggingConfigHistoryVO;
import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

public interface LoggingConfigHistoryService {

	int getTotalCnt(LoggingConfigHistoryVO searchVO);

	List<LoggingConfigHistoryVO> getList(LoggingConfigHistoryVO searchVO);

	void insert(LoggingConfigVO origin, LoggingConfigVO updateVO, AdminLoginVO admin, String hostIp);

	List<Map<String, String>> getTotalListAsMap(LoggingConfigHistoryVO searchVO);

}
