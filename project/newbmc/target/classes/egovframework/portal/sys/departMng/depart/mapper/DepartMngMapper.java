package egovframework.portal.sys.departMng.depart.mapper;

import java.util.List;

import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface DepartMngMapper {

	int getTotalCnt(DepartMngVO searchVO);

	List<DepartMngVO> getList(DepartMngVO searchVO);
	
	DepartMngVO getEntity(DepartMngVO searchVO);
	
	void insert(DepartMngVO searchVO);

	void update(DepartMngVO searchVO);

	void delete(DepartMngVO searchVO);
	
	List<DepartMngVO> getDepartList();

	DepartMngVO getParent(String code);

	void staffUpdate(DepartMngVO searchVO);

	void chargeUpdate(DepartMngVO searchVO);

}
