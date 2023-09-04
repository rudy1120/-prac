package egovframework.portal.unit.bmc.user.service;

import java.util.List;

import egovframework.portal.unit.portal.user.CertResult;
import egovframework.portal.unit.portal.user.NonActiveType;
import egovframework.portal.unit.portal.user.vo.ConfirmVO;
import egovframework.portal.unit.bmc.user.vo.BmcUserVO;

public interface BmcUserService {

	/** 회원 로그인 가능 여부 검증 */
	public CertResult cert(String userId, String rawPw);

	/** 회원 세션 제거(로그아웃 처리) */
	public void logout();

	/** 단일 회원 상세 FETCH */
	public BmcUserVO getUserByUserId(String userId);

	/** 단일 회원 상세 FETCH */
	public BmcUserVO getUserByPrivatecode(String privatecode);

	/** 회원 정보 등록 */
	public void insert(BmcUserVO insertVO, String userId);

	/** 회원 정보 수정 */
	public void update(BmcUserVO updateVO);

	/** 회원 비밀번호 수정 */
	public void updatePassword(BmcUserVO updateVO);

	/** 회원 정보 삭제 */
	public void delete(BmcUserVO deleteVO);

	/** 회원 DI값 유일 여부 검증 */
	public boolean isDuplicate(ConfirmVO confirmVO);

	/** 회원 DI값 수정 */
	public void updateUserPrivatecode(BmcUserVO target, String newDi);

	/** 휴면 회원 목록 FETCH */
	public List<BmcUserVO> getDormantiateTargetUserList(BmcUserVO searchVO);

	/** 회원 휴면 처리 */
	public void dormantiate(BmcUserVO user);

	/** 휴면 회원 활성 처리 */
	public void activate(BmcUserVO target, String newDi);

	/** 회원 process 로그 등록 */
	public void insertUserLog(String userId, String remoteAddr, String content);

	/** 장기간 미로그인(1년 이상) 회원 휴면 처리 */
	public int sortDormantUser(String remoteAddr);

	/** 장기간 미로그인(2년 이상) 회원 삭제 처리 */
	public int deleteDormantUser(String remoteAddr);

	/** 삭제 회원 정보 등록 */
	public void insertDeleteUser(BmcUserVO deleteVO, NonActiveType deleteType);

}
