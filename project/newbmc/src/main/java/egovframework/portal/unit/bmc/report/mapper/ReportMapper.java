package egovframework.portal.unit.bmc.report.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.report.vo.ReportVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ReportMapper {

	public List<ReportVO> getList(ReportVO reportVO);

	public int getTotalCnt(ReportVO reportVO);
	
	public void insert(ReportVO insertVO);

	public int getReporterCnt(ReportVO reportVO);

	public int getCheck(ReportVO reportVO);

	public ReportVO getContent(ReportVO reportVO);
	
	String getFileNm(ReportVO reportVO);

}
