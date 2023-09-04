package egovframework.portal.sys.sysAuth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.sysAuth.mapper.AdminMngMapper;
import egovframework.portal.sys.sysAuth.service.AdminMngService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class AdminMngServiceImpl extends EgovAbstractServiceImpl implements AdminMngService {

	@Resource
	protected AdminMngMapper mapper;

	@Override
	public void update(AdminLoginVO updateVO) throws Exception {
		mapper.update(updateVO);
	}

	@Override
	public void increaseFailedCnt(AdminLoginVO searchVO) {
		mapper.increaseFailedCnt(searchVO);
	}

	@Override
	public void resetFailedCnt(AdminLoginVO searchVO) {
		mapper.resetFailedCnt(searchVO);
	}

	@Override
	public void updateLastestLoginDt(AdminLoginVO searchVO) {
		mapper.updateLastestLoginDt(searchVO);
	}

}
