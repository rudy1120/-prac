package egovframework.portal.sys.departMng.emp.mapper;

import java.util.List;

import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;
import egovframework.portal.sys.departMng.emp.vo.EmpMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface EmpMngMapper {

	int getTotalCnt(EmpMngVO searchVO);

	List<EmpMngVO> getList(EmpMngVO searchVO);
	
	EmpMngVO getEntity(EmpMngVO searchVO);
	
	void insert(EmpMngVO searchVO);

	void update(EmpMngVO searchVO);

	void delete(EmpMngVO searchVO);
	
	int getUsrIdCheck(EmpMngVO searchVO);

	List<EmpMngVO> getStaffContents(String depcode);

	void chargeUpdate(EmpMngVO searchVO);

	List<EmpMngVO> getStaffSearch(DepartMngVO searchVO);

}
