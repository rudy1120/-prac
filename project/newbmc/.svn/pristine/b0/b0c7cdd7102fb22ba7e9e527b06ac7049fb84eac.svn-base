package egovframework.portal.sys.sysAuth.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.DeptMngVO;
import egovframework.portal.sys.sysAuth.vo.SysMenuAuthVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("sysMemberAuthMngMapper")
public interface SysMemberAuthMngMapper {

	/**
	 * 권한관리 접근메뉴 획득 - 공용메뉴 없어서 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public SysMenuAuthVO getAuthMenuHeadUrl(SysMenuAuthVO searchVO);

	/**
	 * 대메뉴 권한 여부 조회 / 권한관리 02, cms 03
	 *
	 * @param searchVO
	 * @return
	 */
	public int cntCheckAuthMenuHead(SysMenuAuthVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 부서 목록 수 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public int getAuthDeptListCnt(DeptMngVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 부서 목록 조회 - 페이징
	 *
	 * @param searchVO
	 * @return
	 */
	public List<DeptMngVO> getAuthDeptList(DeptMngVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 부서 목록 조회- 전체
	 *
	 * @param searchVO
	 * @return
	 */
	public List<DeptMngVO> getAuthDeptListAll(DeptMngVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 직원별 접근권한 목록 조회 16.05.09 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysMenuAuthVO> getSysUserAuth(SysMenuAuthVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 부서별 직원 조회 16.05.09 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> getSysUserList(SysMenuAuthVO searchVO);

	/**
	 * 총괄관리자 권한조회 - 특정메뉴권한 보유 부서 조회 16.05.09 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> getSysDeptList(SysMenuAuthVO searchVO);

	/**
	 * 총괄관리자 권한조회 - 특정메뉴권한 보유 직원 조회 16.05.09 손영식
	 *
	 * @param searchVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> getSysMemberList(SysMenuAuthVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 부서의 관리자메뉴 접근권한 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysMenuAuthVO> getSysMenusAuthList(SysMenuAuthVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 사이트 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<MenusMngVO> getSysSitesAuthList(MenusMngVO searchVO);

	/**
	 * 총괄관리자 권한관리 - 부서의 사이트 권한 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysSiteAuthVO> getSysSitesAuthInDeptList(SysSiteAuthVO searchVO);

	/**
	 * 관리자 메뉴접근권한 정보 등록
	 *
	 * @param insertVO
	 */
	public void insertSysMenusAuth(SysMenuAuthVO insertVO);

	/**
	 * 관리자 직원개인 메뉴접근권한 정보 등록 20160510 손영식
	 *
	 * @param insertVO
	 */
	public void insertSysUsrAuth(SysMenuAuthVO insertVO);

	/**
	 * 관리자 직원개인 메뉴접근권한 일련번호조회 20160511 손영식
	 *
	 * @param insertVO
	 */
	public int getSysUsrAuthIdx();

	/**
	 * 관리자 메뉴접근 권한정보를 SysMenuAuthVO.userId 를 기준으로 모두 삭제 20160511 손영식
	 *
	 * @param deleteVO
	 */
	public void deleteSysUsrAuth(SysMenuAuthVO deleteVO);

	/**
	 * 관리자 부서이동으로 인한 기존 개별권한 정보 삭제
	 *
	 * @param deleteVO
	 */

	public void deleteSysUsrAuthByMoveDept(AdminLoginVO searchVO);

	/**
	 * 관리자 사이트 권한 정보 등록
	 *
	 * @param insertVO
	 */
	public void insertSysSitesAuth(SysSiteAuthVO insertVO);

	/**
	 * 관리자 메뉴접근 권한정보를 SysMenuAuthVO.deptId 를 기준으로 모두 삭제
	 *
	 * @param deleteVO
	 */
	public void deleteSysMenusAuth(SysMenuAuthVO deleteVO);

	/**
	 * 관리자 사이트 권한정보를 SysSiteAuthVO.deptId 를 기준으로 모두 삭제
	 *
	 * @param deleteVO
	 */
	public void deleteSysSitesAuth(SysSiteAuthVO deleteVO);

	/**
	 * 관리자 로그인 - 로그인시 해당 유저가 속한 부서의 메뉴접근권한 목록 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public List<SysMenuAuthVO> getSysMenusLoginAuthList(AdminLoginVO searchVO);

	public List<SysSiteAuthVO> getSysSitesLoginAuthList(AdminLoginVO searchVO);

	public List<SysSiteAuthVO> getSysSitesLoginAuthListForSubAdmin(AdminLoginVO searchVO);

}
