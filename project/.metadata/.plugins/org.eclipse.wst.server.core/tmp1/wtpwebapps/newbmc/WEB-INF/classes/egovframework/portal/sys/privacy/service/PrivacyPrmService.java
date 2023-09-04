package egovframework.portal.sys.privacy.service;

import java.util.List;

import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 개인정보 취급 메뉴 설정 관리 SERVICE
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
public interface PrivacyPrmService {

	/** @return 전체 설정 건수 */
	int getTotalCnt(PrivacyPrmVO searchVO);

	/** @return 설정 목록 */
	List<PrivacyPrmVO> getList(PrivacyPrmVO searchVO);

	/** @return 설정 상세 */
	PrivacyPrmVO getEntity(PrivacyPrmVO searchVO);

	/** @return {@link BbsConfigVO#getPtIdx()}에 해당하는 개인정보 설정 */
	PrivacyPrmVO getEntityByPtIdx(String ptIdx);

	/** 설정 등록 @return 등록 PK */
	String insert(PrivacyPrmVO searchVO, AdminLoginVO admin, String hostIp);

	/** 설정 수정 @return 수정 PK */
	String update(PrivacyPrmVO searchVO, AdminLoginVO admin, String hostIp);

	/** 설정 삭제 @return 삭제 PK */
	String delete(PrivacyPrmVO searchVO, AdminLoginVO admin, String hostIp);

}
