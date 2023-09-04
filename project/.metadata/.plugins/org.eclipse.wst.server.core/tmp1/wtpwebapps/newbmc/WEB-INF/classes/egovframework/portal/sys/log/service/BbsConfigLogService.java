package egovframework.portal.sys.log.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.log.vo.BbsConfigLogVO;

public interface BbsConfigLogService {

	/** 로그 목록(PAGING) */
	public List<BbsConfigLogVO> getBbsConfigLogList(BbsConfigLogVO searchVO) throws Exception;

	/** 로그 전체 건수 */
	public int getTotalBbsConfigLogCnt(BbsConfigLogVO searchVO);

	/** 로그 등록 */
	public void insertBbsConfigLog(BbsConfigLogVO insertVO) throws Exception;

	/** 로그 전체 목록(엑셀 다운로드용) */
	public List<Map<String, String>> getTotalListAsMap(BbsConfigLogVO searchVO);

}
