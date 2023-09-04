package egovframework.portal.unit.bmc.user.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.unit.portal.user.vo.UserLogVO;
import egovframework.portal.unit.bmc.user.vo.BmcUserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface BmcUserMapper {

	public BmcUserVO getUser(BmcUserVO searchVO);

	public void insert(BmcUserVO insertVO);

	public void updateLoginInfo(BmcUserVO BmcUserVO);

	public void update(BmcUserVO updateVO);

	public void updatePassword(BmcUserVO updateVO);

	public void updateDormantUserPassword(BmcUserVO updateVO);

	public void delete(BmcUserVO deleteVO);

	public void insertDeleteUser(Map<String, Object> params);

	public int getAppliedUserCnt(BmcUserVO user);

	public void insertUserLog(UserLogVO insertVO);

	public void updateUserPrivatecode(BmcUserVO params);

	/** 1년 이상 장기 미로그인 회원 목록 FETCH */
	public List<BmcUserVO> getDormantiateTargetUserList(BmcUserVO searchVO);

	/** 회원 휴면 처리 */
	public void dormantiate(BmcUserVO user);

	/** 삭제 대상 회원 목록 FETCH */
	public List<BmcUserVO> getDeleteTargetUserList(BmcUserVO searchVO);

	/** 휴면 회원 활성화 */
	public void activate(BmcUserVO params);

	/** 휴면 회원 삭제 */
	public void deleteDormant(BmcUserVO params);

}
