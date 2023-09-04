package egovframework.portal.unit.bmc.opendoc.web;

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
import egovframework.portal.unit.bmc.opendoc.service.OpendocService;
import egovframework.portal.unit.bmc.opendoc.vo.OpendocVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 결재 문서목록
 *
 */
@Controller
public class OpendocController {

	@Autowired
	OpendocService opendocService;
	
	/** 결재 문서목록 조회 **/
	@RequestMapping("/bmc/opendoc/List.do")
	public String list(@ModelAttribute("opendocVO") OpendocVO opendocVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int listCutRecord = StringUtil.isNumber("10") ? 10 : Constant.LIST_CUTRECORD;
		opendocVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		opendocVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));

		int totalCnt = opendocService.getListCnt(opendocVO);
		List<OpendocVO> list = opendocService.getList(opendocVO);
		
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("page", page);
		model.addAttribute("result", list);
		
		return "/bmc/unit/opendoc/list/";
	}
	
	/** 결재 문서 상세 조회 **/
	@RequestMapping("/bmc/opendoc/view.do")
	public String view(@ModelAttribute("opendocVO") OpendocVO opendocVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		OpendocVO element = opendocService.getContent(opendocVO);
		model.addAttribute("element", element);
		return "/bmc/unit/opendoc/view/";
	}
}
