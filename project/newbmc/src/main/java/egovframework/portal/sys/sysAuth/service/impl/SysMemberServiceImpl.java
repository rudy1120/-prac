package egovframework.portal.sys.sysAuth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.sysAuth.mapper.SysMemberMapper;
import egovframework.portal.sys.sysAuth.service.SysMemberService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.DeptMngVO;
import egovframework.portal.sys.sysAuth.vo.PwChangeVO;
import egovframework.portal.sys.sysAuth.vo.SessionChangeVO;
import egovframework.portal.util.SecurityUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 - 관리자관리 서비스 클래스
 *
 * @author 개발팀 엄동건
 * @since 2015-01-05
 * @version 1.0
 * @see
 */
@Service("sysMemberService")
public class SysMemberServiceImpl extends EgovAbstractServiceImpl implements SysMemberService {

	@Resource(name = "sysMemberMapper")
	private SysMemberMapper sysMemberMapper;

	public int getSysMemberListCnt(AdminLoginVO searchVO) {
		return sysMemberMapper.getSysMemberListCnt(searchVO);
	}

	public List<AdminLoginVO> getSysMemberList(AdminLoginVO searchVO) {
		return sysMemberMapper.getSysMemberList(searchVO);
	}

	public AdminLoginVO getSysMember(AdminLoginVO searchVO) {
		return sysMemberMapper.getSysMember(searchVO);
	}

	public List<DeptMngVO> getDeptAllList() {
		return sysMemberMapper.getDeptAllList();
	}

	public void insertSysMember(AdminLoginVO insertVO) {
		if (insertVO != null) {
			insertVO.setPwd(SecurityUtil.encrypt(insertVO.getPwd()));
		}
		sysMemberMapper.insertSysMember(insertVO);
	}

	public void modifySysMember(AdminLoginVO modifyVO) {
		if (!"".equals(modifyVO.getPwd())) {
			modifyVO.setPwd(SecurityUtil.encrypt(modifyVO.getPwd()));
		} else {
			AdminLoginVO result = this.getSysMember(modifyVO);
			modifyVO.setPwd(result.getPwd());
		}
		sysMemberMapper.modifySysMember(modifyVO);
	}

	public void deleteSysMember(AdminLoginVO deleteVO) {
		sysMemberMapper.deleteSysMember(deleteVO);
	}

	/**
	 * 관리자 정보 세부내용 조회
	 *
	 * @param inputVO
	 * @return
	 */
	public AdminLoginVO selectSysMemberDataDetail(AdminLoginVO inputVO) {
		return sysMemberMapper.selectSysMemberDataDetail(inputVO);
	}

	/**
	 * 관리자 정보 리스트 조회(부서기준)
	 *
	 * @param inputVO
	 * @return
	 */
	public List<AdminLoginVO> selectSysMemberDataListByDept(String inputVO) {
		return sysMemberMapper.selectSysMemberDataListByDept(inputVO);
	}

	public int chkSysMemberSsoId(AdminLoginVO searchVO) {
		return sysMemberMapper.chkSysMemberSsoId(searchVO);
	}

	public AdminLoginVO getSsoUser(AdminLoginVO searchVO) {
		return sysMemberMapper.getSsoUser(searchVO);
	}

	public int chkAccessLog(AdminLoginVO searchVO) {
		return sysMemberMapper.chkAccessLog(searchVO);
	}

	@Override
	public List<PwChangeVO> getPwPeriodInfo(PwChangeVO searchVO) {
		return sysMemberMapper.getPwPeriodInfo(searchVO);
	}

	@Override
	public void updatePwPeriod(PwChangeVO searchVO) {
		sysMemberMapper.updatePwPeriod(searchVO);
		
	}

	@Override
	public List<SessionChangeVO> getSiPeriodInfo(SessionChangeVO searchVO) {
		
		return sysMemberMapper.getSiPeriodInfo(searchVO);
	}

	@Override
	public void updateSiPeriod(SessionChangeVO searchVO) {
		sysMemberMapper.updateSiPeriod(searchVO);
		
	}
}
