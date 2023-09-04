package egovframework.portal.sys.sysAuth.service;

import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 관리자 계정 정보 관리 SERVICE (최고 관리자, 외부 관리자 공통(MyPage용))
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017.08.17		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 17.
 */
public interface AdminMngService {

	/** 계정 정보 갱신 */
	void update(AdminLoginVO updateVO) throws Exception;

	/** 로그인 실패 횟수 증가 */
	void increaseFailedCnt(AdminLoginVO searchVO);

	/** 로그인 실패 횟수 초기화 */
	void resetFailedCnt(AdminLoginVO searchVO);

	/** 최종 로그인 일자 갱신 */
	void updateLastestLoginDt(AdminLoginVO searchVO);

}
