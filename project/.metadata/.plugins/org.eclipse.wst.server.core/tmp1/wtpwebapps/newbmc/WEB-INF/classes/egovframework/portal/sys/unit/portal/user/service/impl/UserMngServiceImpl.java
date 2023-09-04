package egovframework.portal.sys.unit.portal.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.portal.sys.unit.portal.user.mapper.UserMngMapper;
import egovframework.portal.sys.unit.portal.user.service.UserMngService;
import egovframework.portal.unit.portal.user.NonActiveType;
import egovframework.portal.unit.portal.user.mapper.UserMapper;
import egovframework.portal.unit.portal.user.service.UserService;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;

@Service
public class UserMngServiceImpl implements UserMngService {

	@Resource
	protected UserMapper userMapper;
	@Autowired
	protected UserService userService;
	@Resource
	private UserMngMapper userMngMapper;

	@Override
	public List<UserVO> getList(UserVO searchVO) throws Exception {
		return userMngMapper.getList(searchVO);
	}

	@Override
	public int getTotalCnt(UserVO searchVO) {
		return userMngMapper.getTotalCnt(searchVO);
	}

	@Override
	public UserVO getEntity(String userId) throws Exception {
		return StringUtil.isNotBlank(userId) ? userMngMapper.getEntity(userId) : null;
	}

	@Override
	public void update(UserVO updateVO) throws Exception {
		userMngMapper.update(updateVO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delete(UserVO deleteVO) throws Exception {
		userService.insertDeleteUser(deleteVO, NonActiveType.ADMIN_DELETE); // 삭제 회원 이력 테이블로 이동
		userMapper.delete(deleteVO); // 회원 테이블 정보 삭제
	}

}
