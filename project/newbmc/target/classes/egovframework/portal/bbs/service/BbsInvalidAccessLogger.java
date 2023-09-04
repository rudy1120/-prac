package egovframework.portal.bbs.service;

public interface BbsInvalidAccessLogger {

	void insert(String ptIdx, String bIdx, String remoteIp, String userId, String process) throws Exception;

}
