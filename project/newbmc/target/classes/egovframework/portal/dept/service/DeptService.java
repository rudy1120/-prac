package egovframework.portal.dept.service;

import java.util.HashMap;
import java.util.List;

import egovframework.portal.dept.vo.DeptVO;

public interface DeptService {

	/**
	 * 게시판 사용 부서카테고리 조회
	 *
	 * @return
	 */
	public List<DeptVO> getDeptCategoryList();

	// 하위부서 카테고리 조회
	public List<DeptVO> getDeptCategoryList(String deptId);

	// 읍면동 조회
	public List<DeptVO> getDeptDongList();

	// 해당 dept 부서 리스트
	public List<DeptVO> getDeptListForDeptType(String deptType);

	// 해당 부서코드 level
	public DeptVO getDeptCodeLevel(String deptCode);

	// 해당 dept 부서 리스트 (BBS)
	public List<DeptVO> getDeptCodeBbsQueryList(String deptCode);

	// 해당 dept 부서
	public DeptVO getDeptName(String deptCode);

	// 사전정보공개 구분
	public List<DeptVO> getDeptGubunList(DeptVO deptPar);

	// 사전정보공개 구분
	public List<DeptVO> getDeptGubunDetailList(DeptVO deptPar);

	public List<DeptVO> getAllOpenDataDepthList();

	/**
	 * 모든 조직도 정보를 불러온다.
	 * @return
	 * @throws Exception
	 */
	public List<DeptVO> selectDeptInfo(HashMap<String, Object> param) throws Exception;

	/**
	 * 부서명이 상위 부서명을 모두 포함하는 조직도 정보를 불러온다.
	 * 검색필수 (param, key:searchKeyword)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<DeptVO> selectDeptSearchInfo(HashMap<String, Object> param) throws Exception;

}
