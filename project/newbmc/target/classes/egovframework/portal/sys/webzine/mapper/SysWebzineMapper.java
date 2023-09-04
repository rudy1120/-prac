package egovframework.portal.sys.webzine.mapper;

import java.util.List;

import egovframework.portal.sys.webzine.vo.SysWebzineVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysWebzineMapper {

	int getTotalCnt(SysWebzineVO searchVO) throws Exception;

	List<SysWebzineVO> getList(SysWebzineVO searchVO) throws Exception;

	List<SysWebzineVO> getExcelList(SysWebzineVO searchVO) throws Exception;

}
