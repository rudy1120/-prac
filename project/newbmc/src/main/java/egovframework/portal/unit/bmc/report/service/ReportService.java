package egovframework.portal.unit.bmc.report.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.unit.bmc.report.vo.ReportVO;

public interface ReportService {

	List<ReportVO> getList(ReportVO reportVO);

	int getTotalCnt(ReportVO reportVO);

	String insert(ReportVO reportVO, MultipartHttpServletRequest request);

	int getReporterCnt(ReportVO reportVO);

	int getCheck(ReportVO reportVO);

	ReportVO getContent(ReportVO reportVO);

	String getFileNm(ReportVO reportVO);

}
