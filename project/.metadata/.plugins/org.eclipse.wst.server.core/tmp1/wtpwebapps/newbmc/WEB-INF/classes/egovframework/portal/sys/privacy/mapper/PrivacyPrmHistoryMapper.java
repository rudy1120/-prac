package egovframework.portal.sys.privacy.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.privacy.vo.PrivacyPrmHistoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface PrivacyPrmHistoryMapper {

	int getTotalCnt(PrivacyPrmHistoryVO searchVO);

	List<PrivacyPrmHistoryVO> getList(PrivacyPrmHistoryVO searchVO);

	List<Map<String, String>> getTotalListAsMap(PrivacyPrmHistoryVO searchVO);

	void insert(Map<String, Object> params);

}
