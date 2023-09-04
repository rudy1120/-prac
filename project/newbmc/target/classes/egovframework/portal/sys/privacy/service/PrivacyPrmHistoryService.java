package egovframework.portal.sys.privacy.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.privacy.ProcType;
import egovframework.portal.sys.privacy.vo.PrivacyPrmHistoryVO;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 개인정보 취급 메뉴 설정 변경 이력 관리 SERVICE
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
public interface PrivacyPrmHistoryService {

	/** @return 전체 이력 건수 */
	int getTotalCnt(PrivacyPrmHistoryVO searchVO);

	/** @return 이력 목록 */
	List<PrivacyPrmHistoryVO> getList(PrivacyPrmHistoryVO searchVO);

	/** @return 전체 이력(MAP) */
	List<Map<String, String>> getTotalListAsMap(PrivacyPrmHistoryVO searchVO);

	/** 로그 기록 */
	void insert(PrivacyPrmVO insertVO, AdminLoginVO admin, String hostIp, ProcType type);

}
