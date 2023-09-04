package egovframework.portal.staff.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.staff.mapper.StaffMapper;
import egovframework.portal.staff.service.StaffService;
import egovframework.portal.staff.vo.StaffVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 직원업무정보 서비스 클래스
 *
 * @author 개발팀 김장섭
 * @since 2014-12-28
 * @version 1.0
 * @see
 */
@Service("staffService")
public class StaffServiceImpl extends EgovAbstractServiceImpl implements StaffService {

	@Resource(name = "staffMapper")
	private StaffMapper staffMapper;

	/**
	 * 직원업무정보 리스트 조회
	 *
	 * @return
	 */
	public List<StaffVO> getStaffList(StaffVO searchVO) {
		return staffMapper.getStaffList(searchVO);
	}

	/**
	 * 직원업무정보 조회수
	 *
	 * @return
	 */
	public int getStaffCnt(StaffVO searchVO) {
		return staffMapper.getStaffCnt(searchVO);
	}

	/**
	 * 직원업무정보 리스트 조회 관리자
	 *
	 * @return
	 */
	public List<StaffVO> getStaffMngList(StaffVO searchVO) {
		return staffMapper.getStaffMngList(searchVO);
	}

	/**
	 * 직원업무정보 조회수 관리자
	 *
	 * @return
	 */
	public int getStaffMngCnt(StaffVO searchVO) {
		return staffMapper.getStaffMngCnt(searchVO);
	}

	/**
	 * 직원업무정보 부서코드에 따른 정보 조회
	 *
	 * @return
	 */
	public List<StaffVO> selectDeptCodetoStaffInfo(HashMap<String, Object> param) {
		return staffMapper.selectDeptCodetoStaffInfo(param);
	}

	/**
	 * 직원업무정보 전체직원 정보 조회
	 *
	 * @return
	 */
	public List<StaffVO> allStaffList(HashMap<String, String> param) {
		return staffMapper.allStaffList(param);
	}

}
