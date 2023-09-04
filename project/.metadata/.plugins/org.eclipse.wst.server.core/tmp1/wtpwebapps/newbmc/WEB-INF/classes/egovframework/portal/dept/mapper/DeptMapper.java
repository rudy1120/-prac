package egovframework.portal.dept.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.portal.dept.vo.DeptVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("deptMapper")
public interface DeptMapper {

	/**
	 * 하위 부서 카테고리 조회
	 *
	 * @param deptId
	 * @return
	 */
	public List<DeptVO> getDeptCategoryList(String deptId);

	/**
	 * 읍면동 조회
	 *
	 * @return
	 */
	public List<DeptVO> getDeptDongList();

	/**
	 * 해당 dept 부서 리스트
	 *
	 * @param deptType
	 * @return
	 */
	public List<DeptVO> getDeptListForDeptType(String deptType);

	/**
	 * 해당부서코드 level
	 */
	public DeptVO getDeptCodeLevel(String deptCode);

	/**
	 * 해당 dept 부서 리스트 (BBS)
	 *
	 * @param deptType
	 * @return
	 */
	public List<DeptVO> getDeptCodeBbsQueryList(String deptCode);

	/**
	 * 해당 dept 부서
	 *
	 * @param deptType
	 * @return
	 */
	public DeptVO getDeptName(String deptCode);

	/**
	 * 사전정보공개 구분
	 *
	 * @param deptType
	 * @return
	 */
	public List<DeptVO> getDeptGubunList(DeptVO deptPar);

	/**
	 * 사전정보공개 구분
	 *
	 * @param deptType
	 * @return
	 */
	public List<DeptVO> getDeptGubunDetailList(DeptVO deptPar);

	public List<DeptVO> getAllOpenDataDepthList();

	public List<String> getSubDeptIdList(String deptId);

	/**
	 * 모든 조직도 정보를 불러온다.
	 *
	 * @param deptType
	 * @return
	 */
	public List<DeptVO> selectDeptInfo(HashMap<String, Object> param) throws Exception;

}