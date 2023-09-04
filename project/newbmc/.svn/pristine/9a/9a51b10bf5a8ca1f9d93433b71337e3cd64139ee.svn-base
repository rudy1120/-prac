package egovframework.portal.sys.privacy.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.privacy.vo.PrivacyDataHistoryVO;

/**
 * 개인정보 취급 데이터 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 20.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 20.
 */
public interface PrivacyDataService {

	/** @return 일괄 삭제 결과(삭제 건수, 성공 여부, 삭제 프로그램 정보 등) */
	List<Map<String, Object>> deletePrivacyData(String hostIp);

	/** @return 처리 이력 전체 건수 */
	int getTotalCnt(PrivacyDataHistoryVO searchVO);

	/** @return 처리 이력 목록 */
	List<PrivacyDataHistoryVO> getList(PrivacyDataHistoryVO searchVO);

	/** @return 전체 처리 이력(HashMap) */
	List<Map<String, String>> getTotalListAsMap(PrivacyDataHistoryVO searchVO);

}
