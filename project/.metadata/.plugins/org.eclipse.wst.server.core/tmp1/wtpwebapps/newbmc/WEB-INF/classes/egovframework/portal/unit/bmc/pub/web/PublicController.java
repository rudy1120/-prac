package egovframework.portal.unit.bmc.pub.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.unit.bmc.pub.service.PublicService;
import egovframework.portal.unit.bmc.pub.vo.PublicVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 사전정보공표 조회
 * @author boram
 */
@Controller
public class PublicController {

	@Autowired
	PublicService publicService;
	
	/** 사전정보공표 조회 **/
	@RequestMapping("/bmc/public/list.do")
	public String list(@ModelAttribute("publicVO") PublicVO publicVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		/* PAGINATION SETTING */
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		publicVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		publicVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		publicVO.setSearchCatIdx(StringUtil.isBlank(publicVO.getSearchCatIdx()) ? "0" : publicVO.getSearchCatIdx());
		int totalCnt = publicService.getDataTotalCnt(publicVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("resultCnt", totalCnt);
		
		
		
		List<String> departList = publicService.getDepartList();
		List<PublicVO> list = publicService.getDataList(publicVO);
		List<PublicVO> catList = publicService.getCategoryList(publicVO);
		List<PublicVO> statsList = publicService.getStatsList(publicVO);
		
		model.addAttribute("departList", departList);
		model.addAttribute("catList", catList);
		model.addAttribute("result", list);
		model.addAttribute("statsList", statsList);
		model.addAttribute("searchCatIdx", publicVO.getSearchCatIdx());
		return "/bmc/unit/public/list/";
	}
	
	/** 사전정보공표 조회수 집계 **/
	@ResponseBody
	@RequestMapping("/bmc/public/statsInsert.do")
	public String statsInsert(@ModelAttribute("publicVO") PublicVO publicVO, HttpServletRequest request) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		
		String catIdx = publicService.insertStats(publicVO);
		if (StringUtil.isNotBlank(catIdx)) {
			rtn.put("success", Boolean.TRUE);
		}
		return rtn.toString();
	}
	
	@RequestMapping("/bmc/public/monitor.do")
	public String monitor(@ModelAttribute("publicVO") PublicVO publicVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO userVO = (UserVO) principal;
		
		
		
		return "/bmc/unit/public/monitor/";
		
	}
	
	@RequestMapping("/bmc/public/registerProc.do")
	public String registerProc(@ModelAttribute("publicVO") PublicVO publicVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		String newIdx = publicService.insert(publicVO, request);
		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/bmc/public/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}
}
