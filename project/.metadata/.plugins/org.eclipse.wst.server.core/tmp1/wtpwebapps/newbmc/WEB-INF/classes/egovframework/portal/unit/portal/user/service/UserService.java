package egovframework.portal.unit.portal.user.service;

import java.util.List;

import egovframework.portal.unit.portal.user.CertResult;
import egovframework.portal.unit.portal.user.NonActiveType;
import egovframework.portal.unit.portal.user.vo.ConfirmVO;
import egovframework.portal.unit.portal.user.vo.UserVO;

/**
 * 회원 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.12.10		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.08.03		J.Ryeon Lee			javadoc 작성
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2015.12.10
 */
public interface UserService {

	/** 회원 로그인 가능 여부 검증 */
	public CertResult cert(String userId, String rawPw);

	/** 회원 세션 제거(로그아웃 처리) */
	public void logout();

	/** 단일 회원 상세 FETCH */
	public UserVO getUserByUserId(String userId);

	/** 단일 회원 상세 FETCH */
	public UserVO getUserByPrivatecode(String privatecode);

	/** 회원 정보 등록 */
	public void insert(UserVO insertVO, String userId);

	/** 회원 정보 수정 */
	public void update(UserVO updateVO);

	/** 회원 비밀번호 수정 */
	public void updatePassword(UserVO updateVO);

	/** 회원 정보 삭제 */
	public void delete(UserVO deleteVO);

	/** 회원 DI값 유일 여부 검증 */
	public boolean isDuplicate(ConfirmVO confirmVO);

	/** 회원 DI값 수정 */
	public void updateUserPrivatecode(UserVO target, String newDi);

	/** 휴면 회원 목록 FETCH */
	public List<UserVO> getDormantiateTargetUserList(UserVO searchVO);

	/** 회원 휴면 처리 */
	public void dormantiate(UserVO user);

	/** 휴면 회원 활성 처리 */
	public void activate(UserVO target, String newDi);

	/** 회원 process 로그 등록 */
	public void insertUserLog(String userId, String remoteAddr, String content);

	/** 장기간 미로그인(1년 이상) 회원 휴면 처리 */
	public int sortDormantUser(String remoteAddr);

	/** 장기간 미로그인(2년 이상) 회원 삭제 처리 */
	public int deleteDormantUser(String remoteAddr);

	/** 삭제 회원 정보 등록 */
	public void insertDeleteUser(UserVO deleteVO, NonActiveType deleteType);

}
