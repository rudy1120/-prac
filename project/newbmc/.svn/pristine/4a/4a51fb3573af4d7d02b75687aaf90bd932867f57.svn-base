package egovframework.portal.unit.bmc.prism.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.prism.service.PrismMngService;
import egovframework.portal.sys.prism.vo.PrismMngVO;
import egovframework.portal.unit.bmc.prism.service.PrismService;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class PrismController {

	@Autowired
	PrismService prismService;

	@Autowired
	PrismMngService prismMngService;

	// 1. 정책연구용역 보고서 화면 리스트 조회
	@RequestMapping("/bmc/prism/list.do")
	public String list(@ModelAttribute("prismVO") PrismMngVO prismVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		int listCutRecord = StringUtil.isNumber("15") ? 15 : Constant.LIST_CUTRECORD;
		prismVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		prismVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		
		int totalCnt = prismMngService.getTotalCnt(prismVO);
		List<PrismMngVO> list = prismMngService.getList(prismVO);
		
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("page", page);
		model.addAttribute("elements", list);
		model.addAttribute("prismVO", prismVO);
		
		return "/bmc/unit/prism/list/";
	}
	
	
	// 1. 정책연구용역 보고서 상세화면 조회
	@RequestMapping("/bmc/prism/view.do")
	public String view(@ModelAttribute("prismVO") PrismMngVO prismVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		PrismMngVO element = prismMngService.getEntity(prismVO);
		
		//조회수 업데이트
		prismMngService.updateCnt(prismVO);
		
		model.addAttribute("element", element);
		return "/bmc/unit/prism/view/";
	}
}


