package egovframework.portal.sys.log.service;

import java.util.List;

import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

public interface LoggingConfigService {

	List<LoggingConfigVO> getList(LoggingConfigVO searchVO);

	LoggingConfigVO getEntity(LoggingConfigVO searchVO);

	void update(LoggingConfigVO origin, LoggingConfigVO updateVO, AdminLoginVO admin, String hostIp);

}
