package egovframework.portal.sys.departMng.emp.service;

import java.util.List;

import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;
import egovframework.portal.sys.departMng.emp.vo.EmpMngVO;

/**
 * 직원 관리 SERVICE 
 * @author boram
 */
public interface EmpMngService {

	/** 전체 부서 카운트 */
	int getTotalCnt(EmpMngVO searchVO);

	/** 부서 목록 */
	List<EmpMngVO> getList(EmpMngVO searchVO);
	
	/** 부서 상세 */
	EmpMngVO getEntity(EmpMngVO searchVO);
	
	/** 부서 등록 */
	String insert(EmpMngVO searchVO);

	/** 부서 수정 */
	String update(EmpMngVO searchVO);

	/** 부서 삭제 */
	String delete(EmpMngVO searchVO);
	
	/** 아이디 중복확인 */
	int getUsrIdCheck(EmpMngVO searchVO);

	List<EmpMngVO> getStaffContents(String depcode);

	List<EmpMngVO> getStaffSearch(DepartMngVO searchVO);

}
