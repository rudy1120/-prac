package egovframework.portal.sys.sysAuth.service;

import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.DeptMngVO;
import egovframework.portal.sys.sysAuth.vo.PwChangeVO;
import egovframework.portal.sys.sysAuth.vo.SessionChangeVO;

import java.util.List;

/**
 * 관리자 - 관리자 서비스 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.05		엄동건				최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author 엄동건
 * @since 2015.01.05
 */
public interface SysMemberService {

	public int getSysMemberListCnt(AdminLoginVO searchVO);

	public List<AdminLoginVO> getSysMemberList(AdminLoginVO searchVO);

	public AdminLoginVO getSysMember(AdminLoginVO searchVO);

	public List<DeptMngVO> getDeptAllList();

	public void insertSysMember(AdminLoginVO insertVO);

	public void modifySysMember(AdminLoginVO modifyVO);

	public void deleteSysMember(AdminLoginVO deleteVO);

	/**
	 * 관리자 정보 세부내용 조회
	 *
	 * @param inputVO
	 * @return
	 */
	public AdminLoginVO selectSysMemberDataDetail(AdminLoginVO inputVO);

	/**
	 * 관리자 정보 리스트 조회(부서기준)
	 *
	 * @param inputVO
	 * @return
	 */
	public List<AdminLoginVO> selectSysMemberDataListByDept(String inputVO);

	public int chkSysMemberSsoId(AdminLoginVO searchVO);

	public AdminLoginVO getSsoUser(AdminLoginVO searchVO);

	public int chkAccessLog(AdminLoginVO searchVO);

	public List<PwChangeVO> getPwPeriodInfo(PwChangeVO searchVO);

	public void updatePwPeriod(PwChangeVO searchVO);

	public List<SessionChangeVO> getSiPeriodInfo(SessionChangeVO searchVO);

	public void updateSiPeriod(SessionChangeVO searchVO);

}
