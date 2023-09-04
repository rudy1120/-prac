package egovframework.portal.sys.sabo.mapper;

import java.util.List;

import egovframework.portal.sys.sabo.vo.SysSaboVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysSaboMapper {

	int getTotalCnt(SysSaboVO searchVO) throws Exception;

	List<SysSaboVO> getList(SysSaboVO searchVO) throws Exception;

	List<SysSaboVO> getExcelList(SysSaboVO searchVO) throws Exception;

}
