package egovframework.portal.staff.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.portal.staff.vo.StaffVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("staffMapper")
public interface StaffMapper {

	/**
	 * 공통게시판 리스트 조회
	 *
	 * @return
	 */
	public List<StaffVO> getStaffList(StaffVO searchVO);

	/**
	 * 공통게시판 조회수
	 *
	 * @return
	 */
	public int getStaffCnt(StaffVO searchVO);

	/**
	 * 공통게시판 리스트 조회 관리자
	 *
	 * @return
	 */
	public List<StaffVO> getStaffMngList(StaffVO searchVO);

	/**
	 * 공통게시판 조회수 관리자
	 *
	 * @return
	 */
	public int getStaffMngCnt(StaffVO searchVO);

	/**
	 * 직원업무정보 부서코드에 따른 정보 조회
	 *
	 * @return
	 */
	public List<StaffVO> selectDeptCodetoStaffInfo(HashMap<String, Object> param);

	/**
	 * 직원업무정보 전체직원 정보 조회
	 *
	 * @return
	 */
	public List<StaffVO> allStaffList(HashMap<String, String> param);

}
