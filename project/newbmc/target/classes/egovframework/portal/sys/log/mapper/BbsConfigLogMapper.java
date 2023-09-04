package egovframework.portal.sys.log.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.log.vo.BbsConfigLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("bbsConfigLogMapper")
public interface BbsConfigLogMapper {

	public List<BbsConfigLogVO> getBbsConfigLogList(BbsConfigLogVO searchVO);

	public int getTotalBbsConfigLogCnt(BbsConfigLogVO searchVO);

	public void insertBbsConfigLog(BbsConfigLogVO insertVO);

	public List<Map<String, String>> getTotalListAsMap(BbsConfigLogVO searchVO);

}
