package egovframework.portal.sys.report.service;

import java.util.List;

import egovframework.portal.unit.bmc.report.vo.ReportVO;

public interface SysReportService {

	int getTotalCnt(ReportVO searchVO) throws Exception;

	List<ReportVO> getList(ReportVO searchVO) throws Exception;
	
	ReportVO getDetail(ReportVO reportVO) throws Exception;

}
