package egovframework.portal.sys.sysAuth.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.sysAuth.mapper.SysMemberAuthMngMapper;
import egovframework.portal.sys.sysAuth.service.SysMemberAuthMngService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.DeptMngVO;
import egovframework.portal.sys.sysAuth.vo.SysMenuAuthVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 - 관리자관리 서비스 클래스
 *
 * @author 개발팀 엄동건
 * @since 2015-01-05
 * @version 1.0
 * @see
 */

@Service("sysMemberAuthMngService")
public class SysMemberAuthMngServiceImpl extends EgovAbstractServiceImpl implements SysMemberAuthMngService {

	@Resource(name = "sysMemberAuthMngMapper")
	private SysMemberAuthMngMapper sysMemberAuthMngMapper;

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * 권한관리 접근메뉴 획득 - 공용메뉴 없어서 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public SysMenuAuthVO getAuthMenuHeadUrl(SysMenuAuthVO searchVO) {
		return sysMemberAuthMngMapper.getAuthMenuHeadUrl(searchVO);

	}

	/**
	 * 대메뉴 권한 여부 조회 / 권한관리 02, cms 03
	 *
	 * @param searchVO
	 * @return
	 */
	public int cntCheckAuthMenuHead(SysMenuAuthVO searchVO) {
		return sysMemberAuthMngMapper.cntCheckAuthMenuHead(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 부서 목록 수 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public int getAuthDeptListCnt(DeptMngVO searchVO) {
		return sysMemberAuthMngMapper.getAuthDeptListCnt(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 부서 목록 조회 - 페이징
	 *
	 * @param searchVO
	 * @return
	 */
	public List<DeptMngVO> getAuthDeptList(DeptMngVO searchVO) {
		return sysMemberAuthMngMapper.getAuthDeptList(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 직원별 접근권한 목록 조회 16.05.09 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysMenuAuthVO> getSysUsrAuth(SysMenuAuthVO searchVO) {
		return sysMemberAuthMngMapper.getSysUserAuth(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 부서별 직원 목록 조회 16.05.09 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> getSysUsrList(SysMenuAuthVO searchVO) {
		return sysMemberAuthMngMapper.getSysUserList(searchVO);
	}

	/**
	 * 총괄관리자 권한조회 - 특정메뉴권한 보유한 부서목록 조회 16.05.17 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> getSysDeptList(SysMenuAuthVO searchVO) {
		return sysMemberAuthMngMapper.getSysDeptList(searchVO);

	}

	/**
	 * 총괄관리자 권한조회 - 특정 메뉴권한 보유한 직원목록 조회 16.05.17 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> getSysMemberList(SysMenuAuthVO searchVO) {
		return sysMemberAuthMngMapper.getSysMemberList(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 부서 목록 조회 - 전체
	 *
	 * @param searchVO
	 * @return
	 */
	public List<DeptMngVO> getAuthDeptListAll(DeptMngVO searchVO) {
		return sysMemberAuthMngMapper.getAuthDeptListAll(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 부서의 관리자메뉴 접근권한 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysMenuAuthVO> getSysMenusAuthList(SysMenuAuthVO searchVO) {
		return sysMemberAuthMngMapper.getSysMenusAuthList(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 사이트 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<MenusMngVO> getSysSitesAuthList(MenusMngVO searchVO) {
		return sysMemberAuthMngMapper.getSysSitesAuthList(searchVO);
	}

	/**
	 * 총괄관리자 권한관리 - 부서의 사이트 권한 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysSiteAuthVO> getSysSitesAuthInDeptList(SysSiteAuthVO searchVO) {
		return sysMemberAuthMngMapper.getSysSitesAuthInDeptList(searchVO);
	}

	/**
	 * 관리자 메뉴접근권한 정보 SysMenuAuthVO.deptId를 기준으로 모두 삭제후, 등록
	 *
	 * @param insertVO
	 */
	public void writeSysMenusAuth(SysMenuAuthVO insertVO) {

		this.deleteSysMenusAuth(insertVO);
		if (insertVO != null) {
			if (insertVO.getmIds().length > 0) {
				for (String mId : insertVO.getmIds()) {
					SysMenuAuthVO insVO = new SysMenuAuthVO();
					insVO.setDeptId(insertVO.getDeptId());
					insVO.setmId(mId);

					sysMemberAuthMngMapper.insertSysMenusAuth(insVO);
				}
			}
		}
	}

	/**
	 * 관리자 사이트 권한 정보 SysSiteAuthVO.deptId를 기준으로 모두 삭제후, 등록
	 *
	 * @param insertVO
	 */
	public void writeSysSitesAuth(SysSiteAuthVO insertVO) {
		this.deleteSysSitesAuth(insertVO);

		if (insertVO != null) {
			if (insertVO.getSiteCodes().length > 0) {
				for (String siteCodes : insertVO.getSiteCodes()) {
					int siteIdx = 0;
					String siteCode = "";
					String[] codesIdx = siteCodes.split("_");
					if (codesIdx.length > 0) {
						siteCode = codesIdx[0]; // 사이트코드 추출
						siteIdx = Integer.parseInt(codesIdx[1]); // 사이트idx 추출
					}

					SysSiteAuthVO insVO = new SysSiteAuthVO();
					insVO.setDeptId(insertVO.getDeptId());
					insVO.setSiteCode(siteCode);
					insVO.setSiteIdx(siteIdx);

					sysMemberAuthMngMapper.insertSysSitesAuth(insVO);
				}
			}
		}
	}

	/**
	 * 관리자 메뉴접근권한 정보 SysMenuAuthVO.usrId를 기준으로 모두 삭제후, 등록 20160511 손영식
	 *
	 * @param insertVO
	 */
	public void writeSysUsrAuth(SysMenuAuthVO insertVO) {
		this.deleteSysUsrAuth(insertVO);

		if (insertVO != null) {

			try {
				if (insertVO.getmIds().length > 0) {
					for (String mId : insertVO.getmIds()) {
						SysMenuAuthVO insVO = new SysMenuAuthVO();
						insVO.setDeptId(insertVO.getDeptId());
						insVO.setUsrId(insertVO.getUsrId());
						insVO.setRegId(insertVO.getRegId());
						insVO.setIdx(sysMemberAuthMngMapper.getSysUsrAuthIdx());
						insVO.setmId(mId);

						sysMemberAuthMngMapper.insertSysUsrAuth(insVO);
					}
				}
			} catch (NullPointerException e) {
				LOGGER.error(">> 선택한 권한이 없습니다", e);
			}
		}
	}

	/**
	 * 관리자 메뉴접근 권한정보를 SysMenuAuthVO.deptId 를 기준으로 모두 삭제
	 *
	 * @param deleteVO
	 */
	public void deleteSysMenusAuth(SysMenuAuthVO deleteVO) {
		sysMemberAuthMngMapper.deleteSysMenusAuth(deleteVO);
	}

	/**
	 * 관리자 메뉴접근 권한정보를 SysMenuAuthVO.userId 를 기준으로 모두 삭제 20160511 손영식
	 *
	 * @param deleteVO
	 */

	public void deleteSysUsrAuth(SysMenuAuthVO deleteVO) {
		sysMemberAuthMngMapper.deleteSysUsrAuth(deleteVO);
	}


	/**
	 * 관리자 사이트 권한정보를 SysSiteAuthVO.deptId 를 기준으로 모두 삭제
	 *
	 * @param deleteVO
	 */
	public void deleteSysSitesAuth(SysSiteAuthVO deleteVO) {
		sysMemberAuthMngMapper.deleteSysSitesAuth(deleteVO);
	}

	/**
	 * 관리자 로그인 - 로그인시 해당 유저가 속한 부서의 메뉴접근권한 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysMenuAuthVO> getSysMenusLoginAuthList(AdminLoginVO searchVO) {
		//부서이동시 개별권한 삭제 처리
		sysMemberAuthMngMapper.deleteSysUsrAuthByMoveDept(searchVO);

		return sysMemberAuthMngMapper.getSysMenusLoginAuthList(searchVO);
	}

	public List<SysSiteAuthVO> getSysSitesLoginAuthList(AdminLoginVO searchVO) {
		return sysMemberAuthMngMapper.getSysSitesLoginAuthList(searchVO);
	}

	@Override
	public List<SysSiteAuthVO> getSysSitesLoginAuthListForSubAdmin(AdminLoginVO searchVO) {
		return sysMemberAuthMngMapper.getSysSitesLoginAuthListForSubAdmin(searchVO);
	}
}
