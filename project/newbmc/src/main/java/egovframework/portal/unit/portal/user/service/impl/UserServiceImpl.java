package egovframework.portal.unit.portal.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.portal.unit.portal.user.CertResult;
import egovframework.portal.unit.portal.user.NonActiveType;
import egovframework.portal.unit.portal.user.mapper.UserMapper;
import egovframework.portal.unit.portal.user.service.UserService;
import egovframework.portal.unit.portal.user.vo.ConfirmVO;
import egovframework.portal.unit.portal.user.vo.UserLogVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;

/**
 * 회원 관리 SERVICE IMPL
 *
 * <pre>
 * 수정일			수정자				수정내역
 * -------------	----------------	--------------------------
 * 2015.12.10		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.08.03		J.Ryeon Lee			MyBatis 처리 및 개선 기능 반영
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2015.12.10
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	private final Logger LOGGER = LogManager.getLogger();

	private UserVO getUser(String userId, String privatecode) {
		return StringUtil.isNotBlank(userId) || StringUtil.isNotBlank(privatecode) //
			? userMapper.getUser(new UserVO(userId, privatecode)) //
			: null;
	}

	@Override
	public CertResult cert(String userId, String rawPw) {
		return cert(getUserByUserId(userId), rawPw);
	}

	/** 회원 로그인 가능 여부 검증 */
	private CertResult cert(UserVO user, String rawPw) {
		if (user == null) {
			return CertResult.FAIL;
		} else if (!user.getPassword().equals(UserUtil.getEncodedPw(rawPw))) {
			return CertResult.INVALID_PASSWORD;
		}

		return CertResult.SUCCESS;
	}

	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Override
	public UserVO getUserByUserId(String userId) {
		return getUser(userId, null);
	}

	@Override
	public UserVO getUserByPrivatecode(String privatecode) {
		return getUser(null, privatecode);
	}

	@Override
	public void insert(UserVO insertVO, String userId) {
		insertVO.setSessionInfo();
		insertVO.setUserId(userId);
		userMapper.insert(insertVO);
	}

	@Override
	public void update(UserVO updateVO) {
		updateVO.setSessionInfo();
		userMapper.update(updateVO);
	}

	@Override
	public void updatePassword(UserVO updateVO) {
		if ("Y".equals(updateVO.getIsDormant())) {
			userMapper.updateDormantUserPassword(updateVO);
		} else {
			userMapper.updatePassword(updateVO);
		}
	}

	@Override
	public void delete(UserVO deleteVO) {
		delete(deleteVO, NonActiveType.DELETE);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void delete(UserVO deleteVO, NonActiveType deleteType) {
		deleteVO.setSessionInfo();

		insertDeleteUser(deleteVO, deleteType); // 삭제 회원 이력 등록
		userMapper.delete(deleteVO); // 회원 테이블 정보 삭제
	}

	@Override
	public boolean isDuplicate(ConfirmVO confirmVO) {
		UserVO user = UserUtil.getInstance();
		return userMapper.getAppliedUserCnt(user) > 0;
	}

	@Override
	public void updateUserPrivatecode(UserVO target, String newDi) {
		UserVO params = new UserVO();
		BeanUtils.copyProperties(target, params);
		params.setPrivatecode(newDi);

		userMapper.updateUserPrivatecode(params);
	}

	@Override
	public List<UserVO> getDormantiateTargetUserList(UserVO searchVO) {
		return userMapper.getDormantiateTargetUserList(searchVO);
	}

	@Override
	public void dormantiate(UserVO user) {
		userMapper.dormantiate(user); // 휴면화 후
		userMapper.delete(user); // 계정 삭제
	}

	@Override
	public void activate(UserVO target, String newDi) {
		UserVO params = new UserVO();
		BeanUtils.copyProperties(target, params);
		params.setPrivatecode(newDi);

		userMapper.activate(params); // 활성화 후
		userMapper.deleteDormant(params); // 백업 정보 삭제
	}

	@Override
	public void insertUserLog(String userId, String remoteAddr, String action) {
		try {
			// 접근 로그
			UserLogVO logVO = new UserLogVO();
			logVO.setUserId(userId);
			logVO.setIp(remoteAddr);
			logVO.setUserAction(action);
			userMapper.insertUserLog(logVO);
		} catch (Exception e) {
			LOGGER.error(">> failed to save user log", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int sortDormantUser(String remoteAddr) {
		int cnt = 0;

		UserVO searchVO = new UserVO();
		searchVO.setFirstIndex(1);
		searchVO.setLastIndex(100);
		List<UserVO> userList = getDormantiateTargetUserList(searchVO);

		while (userList.size() > 0) {
			for (UserVO user : userList) {
				dormantiate(user);
				insertUserLog(user.getUserId(), remoteAddr, "장기미로그인회원휴면화");
			}
			cnt += userList.size();
			userList = getDormantiateTargetUserList(searchVO);
		}

		return cnt;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int deleteDormantUser(String remoteAddr) {
		int cnt = 0;

		UserVO searchVO = new UserVO();
		searchVO.setFirstIndex(1);
		searchVO.setLastIndex(100);
		List<UserVO> userList = userMapper.getDeleteTargetUserList(searchVO);

		while (userList.size() > 0) {
			for (UserVO user : userList) {
				deleteDormant(user);
				insertUserLog(user.getUserId(), remoteAddr, "장기휴면회원삭제");
			}
			cnt += userList.size();
			userList = userMapper.getDeleteTargetUserList(searchVO);
		}

		return cnt;
	}

	/** 삭제 회원 정보 등록 */
	@Override
	public void insertDeleteUser(UserVO deleteVO, NonActiveType deleteType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", deleteVO);
		params.put("deleteCode", deleteType.getCode());
		params.put("deleteReason", deleteType.getName());
		userMapper.insertDeleteUser(params); // 삭제 회원 이력 테이블로 이동
	}

	/** 휴면 회원 삭제 처리(삭제 정보 백업 및 휴면 정보 삭제 등 연관 처리) */
	private void deleteDormant(UserVO user) {
		insertDeleteUser(user, NonActiveType.NO_LOGIN_HISTORY); // 삭제 정보 백업 후
		userMapper.deleteDormant(user); // 휴면 정보 삭제
	}

}
