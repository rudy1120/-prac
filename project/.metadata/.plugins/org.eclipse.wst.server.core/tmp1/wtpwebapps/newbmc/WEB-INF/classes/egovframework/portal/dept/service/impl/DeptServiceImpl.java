package egovframework.portal.dept.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.dept.mapper.DeptMapper;
import egovframework.portal.dept.service.DeptService;
import egovframework.portal.dept.vo.DeptVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 부서코드 서비스 클래스
 *
 * @author 개발팀 김혜민
 * @since 2014.12.14
 * @version 1.0
 */
@Service("deptService")
public class DeptServiceImpl extends EgovAbstractServiceImpl implements DeptService {

	@Resource(name = "deptMapper")
	private DeptMapper deptMapper;
	@Autowired
	protected BbsService bbsService;

	/**
	 * 게시판 사용 부서카테고리 조회
	 *
	 * @return
	 */
	public List<DeptVO> getDeptCategoryList() {
		return deptMapper.getDeptCategoryList("");
	}

	/**
	 * 하위 부서 카테고리 조회
	 *
	 * @return
	 */
	public List<DeptVO> getDeptCategoryList(String deptId) {
		return deptMapper.getDeptCategoryList(deptId);
	}

	/**
	 * 읍면동 조회
	 */
	public List<DeptVO> getDeptDongList() {
		return deptMapper.getDeptDongList();
	}

	/**
	 * 해당 dept 부서 리스트
	 */
	public List<DeptVO> getDeptListForDeptType(String deptType) {
		return deptMapper.getDeptListForDeptType(deptType);
	}

	/**
	 * 해당부서코드 level
	 */
	public DeptVO getDeptCodeLevel(String deptCode) {
		return deptMapper.getDeptCodeLevel(deptCode);
	}

	/**
	 * 해당 dept 부서 리스트(BBS)
	 */
	public List<DeptVO> getDeptCodeBbsQueryList(String deptCode) {
		return deptMapper.getDeptCodeBbsQueryList(deptCode);
	}

	/**
	 * 해당 dept 부서
	 */
	public DeptVO getDeptName(String deptCode) {
		return deptMapper.getDeptName(deptCode);
	}

	/**
	 * 사전정보공개 구분
	 */
	public List<DeptVO> getDeptGubunList(DeptVO deptPar) {
		return deptMapper.getDeptGubunList(deptPar);
	}

	/**
	 * 사전정보공개 구분
	 */
	public List<DeptVO> getDeptGubunDetailList(DeptVO deptPar) {
		return deptMapper.getDeptGubunDetailList(deptPar);
	}

	@Override
	public List<DeptVO> getAllOpenDataDepthList() {
		return deptMapper.getAllOpenDataDepthList();
	}

	/**
	 * 모든 조직도 정보를 불러온다.
	 */
	public List<DeptVO> selectDeptInfo(HashMap<String, Object> param) throws Exception {
		return deptMapper.selectDeptInfo(param);
	}

	/**
	 * 조직도의 정보를 반환한다. 검색시 이용되며 반환되는 조직의 부서명은 상위 부서명을 포함한다.
	 *
	 * @param param (key : searchKeyword) 검색시.
	 * @return
	 * @throws Exception
	 */
	public List<DeptVO> selectDeptSearchInfo(HashMap<String, Object> param) throws Exception {
		List<DeptVO> list = deptMapper.selectDeptInfo(null); // 전체 부서 목록
		List<DeptVO> searchList = deptMapper.selectDeptInfo(param); // 검색된 부서 목록

		int max = searchList.size();
		for (int i = 0; i < max; i++) {
			DeptVO tmpVO = searchList.get(i);
			tmpVO.setDeptName(findParentNodeNames(list, tmpVO));
		}
		return searchList;
	}

	/**
	 * 선택한 부서의 전체 이름을 반환한다.
	 *
	 * @param list 부서전체목록
	 * @param vo 선택된 부서
	 * @return
	 */
	private String findParentNodeNames(List<DeptVO> list, DeptVO vo) {
		StringBuilder name = new StringBuilder();
		String deptId = vo.getParentDeptId();
		DeptVO tmpResult = null;

		name.append(vo.getDeptName());

		while ((tmpResult = findParentNode(list, deptId)) != null) {
			name.insert(0, " > ");
			name.insert(0, tmpResult.getDeptName());
			deptId = tmpResult.getParentDeptId();
		}
		return name.toString();
	}

	/**
	 * 부서코드를 통해 부서를 검색한다.
	 *
	 * @param list 전체 부서 목록
	 * @param deptId 검새하고자하는 부서의 코드
	 * @return
	 */
	private DeptVO findParentNode(List<DeptVO> list, String deptId) {
		int max = list.size();
		for (int i = 0; i < max; i++) {
			if (list.get(i).getDeptId().equals(deptId)) {
				return list.get(i);
			}
		}
		return null;
	}

}