package egovframework.portal.sys.report.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.report.vo.ReportVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysReportMapper {

	int getTotalCnt(ReportVO searchVO) throws Exception;

	List<ReportVO> getList(ReportVO searchVO) throws Exception;
	
	ReportVO getDetail(ReportVO reportVO) throws Exception;

}
