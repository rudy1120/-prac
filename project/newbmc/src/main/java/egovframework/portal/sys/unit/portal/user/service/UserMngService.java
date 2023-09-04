package egovframework.portal.sys.unit.portal.user.service;

import java.util.List;

import egovframework.portal.unit.portal.user.vo.UserVO;

/**
 * 회원 관리 SERVICE
 *
 * @author J.Ryeon Lee
 * @since 2016.08.17
 */
public interface UserMngService {

	/** 목록 */
	public List<UserVO> getList(UserVO searchVO) throws Exception;

	/** 총 목록 건수 */
	public int getTotalCnt(UserVO searchVO);

	/** 회원 정보 객체 */
	public UserVO getEntity(String userId) throws Exception;

	/** 회원 정보 수정 */
	public void update(UserVO updateVO) throws Exception;

	/** 회원 자격 삭제 */
	public void delete(UserVO deleteVO) throws Exception;

}
