package egovframework.portal.sys.report.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.report.service.SysReportService;
import egovframework.portal.unit.bmc.report.vo.ReportVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class SysReportController {
	
	@Autowired
	protected SysReportService sysreportService;
	
	// 1. 채용응시원서 관리자 리스트 조회 화면
	@RequestMapping("/sys/report/list.do")
	public String list(@ModelAttribute("searchVO") ReportVO searchVO, @RequestParam String mId, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = sysreportService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		
		model.addAttribute("result", sysreportService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		
		return "/sys/report/list/";
	}
	
	@RequestMapping("/sys/report/view.do")
	public String view(@ModelAttribute("reportVO") ReportVO reportVO, @RequestParam String mId, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReportVO result = sysreportService.getDetail(reportVO);
		
		model.addAttribute("result", result);
		
		return "/sys/report/view/";
	}
	
}
