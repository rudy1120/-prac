package egovframework.portal.sys.log.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.log.mapper.LoggingConfigHistoryMapper;
import egovframework.portal.sys.log.service.LoggingConfigHistoryService;
import egovframework.portal.sys.log.vo.LoggingConfigHistoryVO;
import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class LoggingConfigHistoryServiceImpl extends EgovAbstractServiceImpl implements LoggingConfigHistoryService {

	@Resource
	protected LoggingConfigHistoryMapper configHistoryMapper;

	@Override
	public int getTotalCnt(LoggingConfigHistoryVO searchVO) {
		return configHistoryMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<LoggingConfigHistoryVO> getList(LoggingConfigHistoryVO searchVO) {
		return configHistoryMapper.getList(searchVO);
	}

	@Override
	public void insert(LoggingConfigVO origin, LoggingConfigVO updateVO, AdminLoginVO admin, String hostIp) {
		LoggingConfigHistoryVO insertVO = new LoggingConfigHistoryVO();
		insertVO.setConfigIdx(origin.getIdx());
		insertVO.setLogName(origin.getLogName());
		insertVO.setOldLogPeriod(origin.getLogPeriod());
		insertVO.setNewLogPeriod(updateVO.getLogPeriod());
		insertVO.setAdminId(admin.getAdminId());
		insertVO.setAdminName(admin.getAdminName());
		insertVO.setDeptId(admin.getDeptId());
		insertVO.setDeptName(admin.getDeptName());
		insertVO.setHostIp(hostIp);

		configHistoryMapper.insert(insertVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(LoggingConfigHistoryVO searchVO) {
		return configHistoryMapper.getTotalListAsMap(searchVO);
	}

}
