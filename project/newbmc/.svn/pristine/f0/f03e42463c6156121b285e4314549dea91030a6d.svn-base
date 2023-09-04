package egovframework.portal.sys.departMng.depart.service;

import java.util.List;

import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;

/**
 * 부서 관리 SERVICE 
 * @author boram
 */
public interface DepartMngService {

	/** 전체 부서 카운트 */
	int getTotalCnt(DepartMngVO searchVO);

	/** 부서 목록 */
	List<DepartMngVO> getList(DepartMngVO searchVO);
	
	/** 부서 상세 */
	DepartMngVO getEntity(DepartMngVO searchVO);
	
	/** 부서 등록 */
	String insert(DepartMngVO searchVO);

	/** 부서 수정 */
	String update(DepartMngVO searchVO);

	/** 부서 삭제 */
	String delete(DepartMngVO searchVO);
	
	/** 직원관리에서 사용하는 부서 목록 */
	List<DepartMngVO> getDepartList();

}
