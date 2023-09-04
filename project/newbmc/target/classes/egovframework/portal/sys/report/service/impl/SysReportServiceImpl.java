package egovframework.portal.sys.report.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.report.mapper.SysReportMapper;
import egovframework.portal.sys.report.service.SysReportService;
import egovframework.portal.unit.bmc.report.vo.ReportVO;

@Service
public class SysReportServiceImpl implements SysReportService {
	
	@Resource
	private SysReportMapper sysreportMapper;

	@Override
	public int getTotalCnt(ReportVO searchVO) throws Exception {
		return sysreportMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<ReportVO> getList(ReportVO searchVO) throws Exception {
		return sysreportMapper.getList(searchVO);
	}

	@Override
	public ReportVO getDetail(ReportVO reportVO) throws Exception {
		ReportVO result;
		
		result = sysreportMapper.getDetail(reportVO);
		
		return result;
	}


}
