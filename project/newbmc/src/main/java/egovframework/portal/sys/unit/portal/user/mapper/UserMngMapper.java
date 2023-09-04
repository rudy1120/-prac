package egovframework.portal.sys.unit.portal.user.mapper;

import java.util.List;

import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface UserMngMapper {

	/** 회원 목록 */
	public List<UserVO> getList(UserVO searchVO);

	/** 전체 회원 건수 */
	public int getTotalCnt(UserVO searchVO);

	/** 단일 회원 상세 */
	public UserVO getEntity(String userId);

	/** 회원 정보 수정 */
	public void update(UserVO updateVO);

}
