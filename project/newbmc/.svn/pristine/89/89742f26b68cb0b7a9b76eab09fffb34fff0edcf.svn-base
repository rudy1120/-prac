package egovframework.portal.sys.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.log.mapper.LoggingConfigMapper;
import egovframework.portal.sys.log.service.LoggingConfigHistoryService;
import egovframework.portal.sys.log.service.LoggingConfigService;
import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class LoggingConfigServiceImpl extends EgovAbstractServiceImpl implements LoggingConfigService {

	@Resource
	protected LoggingConfigMapper configMapper;
	@Autowired
	protected LoggingConfigHistoryService configHistoryService;

	@Override
	public List<LoggingConfigVO> getList(LoggingConfigVO searchVO) {
		return configMapper.getList(searchVO);
	}

	@Override
	public LoggingConfigVO getEntity(LoggingConfigVO searchVO) {
		return StringUtil.isNotBlank(searchVO.getIdx()) //
			? configMapper.getEntity(searchVO) : null;
	}

	@Override
	public void update(LoggingConfigVO origin, LoggingConfigVO updateVO, AdminLoginVO admin, String hostIp) {
		configMapper.update(updateVO);
		configHistoryService.insert(origin, updateVO, admin, hostIp);
	}

}
