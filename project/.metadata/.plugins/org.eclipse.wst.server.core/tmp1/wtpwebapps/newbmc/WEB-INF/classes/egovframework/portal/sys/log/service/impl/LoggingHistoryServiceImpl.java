package egovframework.portal.sys.log.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.log.mapper.LoggingHistoryMapper;
import egovframework.portal.sys.log.service.LoggingHistoryService;
import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.log.vo.LoggingHistoryVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class LoggingHistoryServiceImpl extends EgovAbstractServiceImpl implements LoggingHistoryService {

	@Resource
	protected LoggingHistoryMapper historyMapper;

	@Override
	public int getTotalCnt(LoggingHistoryVO searchVO) {
		return historyMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<LoggingHistoryVO> getList(LoggingHistoryVO searchVO) {
		return historyMapper.getList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(LoggingHistoryVO searchVO) {
		return historyMapper.getTotalListAsMap(searchVO);
	}

	@Override
	public void insert(LoggingConfigVO config, int delCnt, AdminLoginVO admin, String hostIp) {
		LoggingHistoryVO insertVO = new LoggingHistoryVO();
		insertVO.setConfigIdx(config.getIdx());
		insertVO.setLogName(config.getLogName());
		insertVO.setDelCnt(delCnt);
		insertVO.setAdminId(admin != null ? (StringUtil.isNotBlank(admin.getAdminId()) ? admin.getAdminId() : admin.getId()) : null);
		insertVO.setAdminName(admin != null ? admin.getAdminName() : null);
		insertVO.setDeptName(admin != null ? admin.getDeptName() : null);
		insertVO.setHostIp(hostIp);

		historyMapper.insert(insertVO);
	}

}
