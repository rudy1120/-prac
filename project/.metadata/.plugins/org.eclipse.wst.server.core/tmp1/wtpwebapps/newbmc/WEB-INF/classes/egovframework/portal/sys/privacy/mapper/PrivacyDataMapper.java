package egovframework.portal.sys.privacy.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.privacy.vo.PrivacyDataHistoryVO;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface PrivacyDataMapper {

	void insertLog(PrivacyDataHistoryVO insertVO);

	int deleteExpiredPrivacyData(PrivacyPrmVO prm);

	int getTotalCnt(PrivacyDataHistoryVO searchVO);

	List<PrivacyDataHistoryVO> getList(PrivacyDataHistoryVO searchVO);

	List<Map<String, String>> getTotalListAsMap(PrivacyDataHistoryVO searchVO);

}
