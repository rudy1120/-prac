package egovframework.portal.sys.departMng.depart.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.sys.departMng.depart.service.DepartMngService;
import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 부서관리 CONTROLLER
 * @author boram
 */
@Controller
public class DepartMngController {

	@Autowired
	protected DepartMngService departMngService;

	/** 목록 */
	@RequestMapping("/sys/deptMng/list.do")
	public String list(@ModelAttribute("searchVO") DepartMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* PAGINATION SETTING */
		/*int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = departMngService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));*/

		/* VIEW PARAMETER SETTING */
		List<DepartMngVO> result = departMngService.getList(searchVO);
		
		model.addAttribute("result", result);
		/*model.addAttribute("resultCnt", totalCnt);*/
		model.addAttribute("searchVO", searchVO);

		return "/sys/deptMng/dept/list/";
	}

	/** 등록/수정 화면 */
	@RequestMapping({ "/sys/deptMng/write.do", "/sys/deptMng/modify.do" })
	public String modify(@ModelAttribute("searchVO") DepartMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DepartMngVO entity = departMngService.getEntity(searchVO);
		if (entity != null) {
			entity.setSearchDepCodeNm(searchVO.getSearchDepCodeNm());
			BeanUtil.copy(entity, searchVO);
		}
		List<DepartMngVO> departList = departMngService.getDepartList();
		model.addAttribute("departList", departList);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/deptMng/dept/update/";
	}

	/** 등록 처리 */
	@RequestMapping("/sys/deptMng/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") DepartMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String newIdx = departMngService.insert(searchVO);
		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/sys/deptMng/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 수정 처리 */
	@RequestMapping("/sys/deptMng/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") DepartMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String modIdx = departMngService.update(searchVO);
		if (StringUtil.isNotBlank(modIdx)) {
			return "redirect:/sys/deptMng/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 삭제 처리 */
	@ResponseBody
	@RequestMapping("/sys/deptMng/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") DepartMngVO searchVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		String delIdx = departMngService.delete(searchVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}

}
