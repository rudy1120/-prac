package egovframework.portal.sys.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.log.service.AutoDelListService;
import egovframework.portal.sys.log.vo.AutoDelListVO;
import egovframework.portal.util.paging.PaginationInfoUtil;


@Controller("AutoDelListController")
public class AutoDelListController {
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	protected AutoDelListService autoDelListService;

	@RequestMapping("/sys/logging/dellist.do")
	public String list(@ModelAttribute("searchVO") AutoDelListVO searchVO, 
			@RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = autoDelListService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		
		model.addAttribute("result", autoDelListService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("reserveVO", searchVO);
		
		return "/sys/logging/delList/";
	}

	
}