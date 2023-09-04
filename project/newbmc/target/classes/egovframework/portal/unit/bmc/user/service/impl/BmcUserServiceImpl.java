package egovframework.portal.unit.bmc.user.service.impl;

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

import egovframework.portal.unit.bmc.user.mapper.BmcUserMapper;
import egovframework.portal.unit.bmc.user.service.BmcUserService;
import egovframework.portal.unit.portal.user.CertResult;
import egovframework.portal.unit.portal.user.NonActiveType;
import egovframework.portal.unit.portal.user.vo.ConfirmVO;
import egovframework.portal.unit.portal.user.vo.UserLogVO;
import egovframework.portal.unit.bmc.user.vo.BmcUserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.unit.bmc.util.BmcUserUtil;

@Service
public class BmcUserServiceImpl implements BmcUserService {

	@Resource
	private BmcUserMapper bmcUserMapper;

	private final Logger LOGGER = LogManager.getLogger();

	private BmcUserVO getUser(String userId, String privatecode) {
		return StringUtil.isNotBlank(userId) || StringUtil.isNotBlank(privatecode) //
			? bmcUserMapper.getUser(new BmcUserVO(userId, privatecode)) //
			: null;
	}

	@Override
	public CertResult cert(String userId, String rawPw) {
		return cert(getUserByUserId(userId), rawPw);
	}

	/** 회원 로그인 가능 여부 검증 */
	private CertResult cert(BmcUserVO user, String rawPw) {
		if (user == null) {
			return CertResult.FAIL;
		} else if (!user.getPassword().equals(BmcUserUtil.getEncodedPw(rawPw))) {
			return CertResult.INVALID_PASSWORD;
		}

		return CertResult.SUCCESS;
	}

	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Override
	public BmcUserVO getUserByUserId(String userId) {
		return getUser(userId, null);
	}

	@Override
	public BmcUserVO getUserByPrivatecode(String privatecode) {
		return getUser(null, privatecode);
	}

	@Override
	public void insert(BmcUserVO insertVO, String userId) {
		insertVO.setSessionInfo();
		insertVO.setUserId(userId);
		bmcUserMapper.insert(insertVO);
	}

	@Override
	public void update(BmcUserVO updateVO) {
		updateVO.setSessionInfo();
		bmcUserMapper.update(updateVO);
	}

	@Override
	public void updatePassword(BmcUserVO updateVO) {
		if ("Y".equals(updateVO.getIsDormant())) {
			bmcUserMapper.updateDormantUserPassword(updateVO);
		} else {
			bmcUserMapper.updatePassword(updateVO);
		}
	}

	@Override
	public void delete(BmcUserVO deleteVO) {
		delete(deleteVO, NonActiveType.DELETE);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void delete(BmcUserVO deleteVO, NonActiveType deleteType) {
		deleteVO.setSessionInfo();

		insertDeleteUser(deleteVO, deleteType); // 삭제 회원 이력 등록
		bmcUserMapper.delete(deleteVO); // 회원 테이블 정보 삭제
	}

	@Override
	public boolean isDuplicate(ConfirmVO confirmVO) {
		BmcUserVO user = BmcUserUtil.getInstance();
		return bmcUserMapper.getAppliedUserCnt(user) > 0;
	}

	@Override
	public void updateUserPrivatecode(BmcUserVO target, String newDi) {
		BmcUserVO params = new BmcUserVO();
		BeanUtils.copyProperties(target, params);
		params.setPrivatecode(newDi);

		bmcUserMapper.updateUserPrivatecode(params);
	}

	@Override
	public List<BmcUserVO> getDormantiateTargetUserList(BmcUserVO searchVO) {
		return bmcUserMapper.getDormantiateTargetUserList(searchVO);
	}

	@Override
	public void dormantiate(BmcUserVO user) {
		bmcUserMapper.dormantiate(user); // 휴면화 후
		bmcUserMapper.delete(user); // 계정 삭제
	}

	@Override
	public void activate(BmcUserVO target, String newDi) {
		BmcUserVO params = new BmcUserVO();
		BeanUtils.copyProperties(target, params);
		params.setPrivatecode(newDi);

		bmcUserMapper.activate(params); // 활성화 후
		bmcUserMapper.deleteDormant(params); // 백업 정보 삭제
	}

	@Override
	public void insertUserLog(String userId, String remoteAddr, String action) {
		try {
			// 접근 로그
			UserLogVO logVO = new UserLogVO();
			logVO.setUserId(userId);
			logVO.setIp(remoteAddr);
			logVO.setUserAction(action);
			bmcUserMapper.insertUserLog(logVO);
		} catch (Exception e) {
			LOGGER.error(">> failed to save user log", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int sortDormantUser(String remoteAddr) {
		int cnt = 0;

		BmcUserVO searchVO = new BmcUserVO();
		searchVO.setFirstIndex(1);
		searchVO.setLastIndex(100);
		List<BmcUserVO> userList = getDormantiateTargetUserList(searchVO);

		while (userList.size() > 0) {
			for (BmcUserVO user : userList) {
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

		BmcUserVO searchVO = new BmcUserVO();
		searchVO.setFirstIndex(1);
		searchVO.setLastIndex(100);
		List<BmcUserVO> userList = bmcUserMapper.getDeleteTargetUserList(searchVO);

		while (userList.size() > 0) {
			for (BmcUserVO user : userList) {
				deleteDormant(user);
				insertUserLog(user.getUserId(), remoteAddr, "장기휴면회원삭제");
			}
			cnt += userList.size();
			userList = bmcUserMapper.getDeleteTargetUserList(searchVO);
		}

		return cnt;
	}

	/** 삭제 회원 정보 등록 */
	@Override
	public void insertDeleteUser(BmcUserVO deleteVO, NonActiveType deleteType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", deleteVO);
		params.put("deleteCode", deleteType.getCode());
		params.put("deleteReason", deleteType.getName());
		bmcUserMapper.insertDeleteUser(params); // 삭제 회원 이력 테이블로 이동
	}

	/** 휴면 회원 삭제 처리(삭제 정보 백업 및 휴면 정보 삭제 등 연관 처리) */
	private void deleteDormant(BmcUserVO user) {
		insertDeleteUser(user, NonActiveType.NO_LOGIN_HISTORY); // 삭제 정보 백업 후
		bmcUserMapper.deleteDormant(user); // 휴면 정보 삭제
	}

}
