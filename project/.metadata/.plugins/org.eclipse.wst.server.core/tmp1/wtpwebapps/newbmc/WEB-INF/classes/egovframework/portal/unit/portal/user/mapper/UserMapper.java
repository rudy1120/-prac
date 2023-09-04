package egovframework.portal.unit.portal.user.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.unit.portal.user.vo.UserLogVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface UserMapper {

	public UserVO getUser(UserVO searchVO);

	public void insert(UserVO insertVO);

	public void updateLoginInfo(UserVO userVO);

	public void update(UserVO updateVO);

	public void updatePassword(UserVO updateVO);

	public void updateDormantUserPassword(UserVO updateVO);

	public void delete(UserVO deleteVO);

	public void insertDeleteUser(Map<String, Object> params);

	public int getAppliedUserCnt(UserVO user);

	public void insertUserLog(UserLogVO insertVO);

	public void updateUserPrivatecode(UserVO params);

	/** 1년 이상 장기 미로그인 회원 목록 FETCH */
	public List<UserVO> getDormantiateTargetUserList(UserVO searchVO);

	/** 회원 휴면 처리 */
	public void dormantiate(UserVO user);

	/** 삭제 대상 회원 목록 FETCH */
	public List<UserVO> getDeleteTargetUserList(UserVO searchVO);

	/** 휴면 회원 활성화 */
	public void activate(UserVO params);

	/** 휴면 회원 삭제 */
	public void deleteDormant(UserVO params);

}
