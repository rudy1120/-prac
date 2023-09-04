package egovframework.portal.bbs.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.bbs.mapper.BbsAccessLogMapper;
import egovframework.portal.bbs.service.BbsInvalidAccessLogger;

/**
 * 게시판 비정상적 접근 로그 기록 SERVICE IMPL
 *
 * @author J.Ryeon Lee
 */
@Service
public class BbsInvalidAccessLoggerImpl implements BbsInvalidAccessLogger {

	@Resource
	protected BbsAccessLogMapper bbsAccessLogMapper;

	@Override
	public void insert(String ptIdx, String bIdx, String remoteIp, String userId, String process) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ptIdx", ptIdx);
		params.put("bIdx", bIdx);
		params.put("remoteIp", remoteIp);
		params.put("userId", userId);
		params.put("process", process);

		bbsAccessLogMapper.insert(params);
	}

}
