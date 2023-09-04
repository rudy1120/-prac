package egovframework.portal.unit.bmc.report.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.common.Constant;
import egovframework.portal.unit.bmc.report.service.ReportService;
import egovframework.portal.unit.bmc.report.vo.ReportVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class ReportController {
	
	@Autowired
	ReportService reportService;

	@RequestMapping("/bmc/report/humanReport.do")
	public String list(HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		return "/bmc/unit/report/humanReport/";
	}
	
	@RequestMapping("/bmc/report/write.do")
	public String notify(@ModelAttribute("reportVO") ReportVO reportVO, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		return "/bmc/unit/report/report/";
	}
	
	@RequestMapping("/bmc/report/notifyProc.do")
	public String registerProc(@ModelAttribute("reportVO") ReportVO reportVO, MultipartHttpServletRequest request, 
			HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		String cont = StringEscapeUtils.unescapeHtml3(reportVO.getCont());
		reportVO.setCont(cont);
		
		String newIdx = reportService.insert(reportVO, request);

		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/bmc/report/humanReport.do?mId=" + mId;
		}
		
		return null;
	}
	
	@RequestMapping("/bmc/report/read.do")
	public String getList(@ModelAttribute("reportVO") ReportVO reportVO, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO userVO = (UserVO) principal;
		
		reportVO.setDupinfo(userVO.getPrivatecode());
		
		int chkUser = reportService.getCheck(reportVO);
		
		if(chkUser > 0) {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
			int listCutRecord = StringUtil.isNumber("8") ? 8 : Constant.LIST_CUTRECORD;
			reportVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
			reportVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
			
			List<ReportVO> list = reportService.getList(reportVO);
			
			int totalCnt = reportService.getReporterCnt(reportVO);
			
			model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
			model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
			model.addAttribute("page", page);
			model.addAttribute("result", list);
			model.addAttribute("reportVO", reportVO);
			model.addAttribute("totalCnt", totalCnt);
			
			return "/bmc/unit/report/list/";
		} else {
			WriterUtil.flushJsAlertAndHistoryBack(response, "신고 접수 내역이 없습니다.");
			return null;
		}
	}
	
	@RequestMapping("/bmc/report/view.do")
	public String view(@ModelAttribute("reportVO") ReportVO reportVO, HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		reportVO.setIdx(request.getParameter("idx"));
		
		ReportVO result = reportService.getContent(reportVO);  
		String fileNm = reportService.getFileNm(reportVO);
		
		model.addAttribute("result", result);
		model.addAttribute("fileNm", fileNm);
		
		return "/bmc/unit/report/view/";
	}
	
}
