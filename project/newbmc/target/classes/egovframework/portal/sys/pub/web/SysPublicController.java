package egovframework.portal.sys.pub.web;

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

import egovframework.portal.sys.pub.service.SysPublicService;
import egovframework.portal.sys.pub.vo.SysPublicVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class SysPublicController {
	
	@Autowired
	protected  SysPublicService pubService;
	
	/** 카테고리 목록 조회  */
	@RequestMapping("/sys/public/categoryList.do")
	public String categoryList(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		model.addAttribute("result", pubService.getCategoryList(publicVO));
		return "/sys/public/categoryList/";
	}
	
	/** 카테고리 등록/수정 화면 */
	@RequestMapping({ "/sys/public/categoryWrite.do", "/sys/public/categoryModify.do" })
	public String categoryModify(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SysPublicVO entity = pubService.getCategoryEntity(publicVO);
		if (entity != null) {
			BeanUtil.copy(entity, publicVO);
		}
		model.addAttribute("publicVO", publicVO);
		return "/sys/public/categoryUpdate/";
	}

	/** 카테고리 등록 처리 */
	@RequestMapping("/sys/public/categoryWriteProc.do")
	public String categoryWriteProc(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
		publicVO.setRegId(currentAdmin.getAdminId());
		publicVO.setUptId(currentAdmin.getAdminId());
		
		String catIdx = pubService.insertCategory(publicVO);
		if (StringUtil.isNotBlank(catIdx)) {
			return "redirect:/sys/public/categoryList.do?mId=" + mId;
		}
		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 카테고리 수정 처리 */
	@RequestMapping("/sys/public/categoryModifyProc.do")
	public String categoryModifyProc(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
		publicVO.setUptId(currentAdmin.getAdminId());
		
		String catIdx = pubService.updateCategory(publicVO);
		if (StringUtil.isNotBlank(catIdx)) {
			return "redirect:/sys/public/categoryList.do?mId=" + mId;
		}
		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 카테고리 삭제 처리 */
	@ResponseBody
	@RequestMapping("/sys/public/categoryDeleteProc.do")
	public String categoryDeleteProc(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, HttpServletRequest request) throws Exception {
		
		AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
		publicVO.setUptId(currentAdmin.getAdminId());
		
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String catIdx = pubService.deleteCategory(publicVO);
		rtn.put("success", StringUtil.isNotBlank(catIdx));

		return rtn.toString();
	}
	
	/** 사전정보공표 목록 조회  */
	@RequestMapping("/sys/public/dataList.do")
	public String dataList(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* PAGINATION SETTING */
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		publicVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		publicVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		List<SysPublicVO> catList = pubService.getCategoryList(publicVO);

		int totalCnt = pubService.getDataTotalCnt(publicVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));

		/* VIEW PARAMETER SETTING */
		model.addAttribute("result", pubService.getDataList(publicVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("publicVO", publicVO);
		model.addAttribute("catList", catList);

		return "/sys/public/dataList/";
	}
	
	/** 사전정보공표 등록/수정 화면 */
	@RequestMapping({ "/sys/public/dataWrite.do", "/sys/public/dataModify.do" })
	public String dataModify(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SysPublicVO pubVO = new SysPublicVO();
		SysPublicVO entity = pubService.getDataEntity(publicVO);
		if (entity != null) {
			BeanUtil.copy(entity, pubVO);
		}
		List<SysPublicVO> catList = pubService.getCategoryList(publicVO);
		
		model.addAttribute("publicVO", pubVO);
		model.addAttribute("catList", catList);
		
		return "/sys/public/dataUpdate/";
	}

	/** 사전정보공표 등록 처리 */
	@RequestMapping("/sys/public/dataWriteProc.do")
	public String dataWriteProc(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
		publicVO.setRegId(currentAdmin.getAdminId());
		publicVO.setUptId(currentAdmin.getAdminId());
		publicVO.setName(StringEscapeUtils.unescapeHtml3(publicVO.getName()));
		publicVO.setArticle(StringEscapeUtils.unescapeHtml3(publicVO.getArticle()));
		publicVO.setLink(StringEscapeUtils.unescapeHtml3(publicVO.getLink()));
		String catIdx = pubService.insertData(publicVO);
		if (StringUtil.isNotBlank(catIdx)) {
			return "redirect:/sys/public/dataList.do?mId=" + mId;
		}
		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 사전정보공표 수정 처리 */
	@RequestMapping("/sys/public/dataModifyProc.do")
	public String dataModifyProc(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
		publicVO.setUptId(currentAdmin.getAdminId());
		publicVO.setName(StringEscapeUtils.unescapeHtml3(publicVO.getName()));
		publicVO.setArticle(StringEscapeUtils.unescapeHtml3(publicVO.getArticle()));
		publicVO.setLink(StringEscapeUtils.unescapeHtml3(publicVO.getLink()));
		String catIdx = pubService.updateData(publicVO);
		if (StringUtil.isNotBlank(catIdx)) {
			return "redirect:/sys/public/dataList.do?mId=" + mId;
		}
		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 사전정보공표 삭제 처리 */
	@ResponseBody
	@RequestMapping("/sys/public/dataDeleteProc.do")
	public String dataDeleteProc(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, HttpServletRequest request) throws Exception {
		
		AdminLoginVO currentAdmin = SessionUtil.getAdminInstance(request);
		publicVO.setUptId(currentAdmin.getAdminId());
		
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String catIdx = pubService.deleteData(publicVO);
		rtn.put("success", StringUtil.isNotBlank(catIdx));

		return rtn.toString();
	}

	@RequestMapping("/sys/public/monitorList.do")
	public String monitorList(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* PAGINATION SETTING */
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		publicVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		publicVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		


		int totalCnt = pubService.getMonitorTotalCnt(publicVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));

		/* VIEW PARAMETER SETTING */
		model.addAttribute("result", pubService.getMonitorList(publicVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("publicVO", publicVO);

		return "/sys/public/monitorList/";
	}
	
	/** 모니터링 삭제 처리 */
	@ResponseBody
	@RequestMapping("/sys/public/monitorDeleteProc.do")
	public String monitorDeleteProc(@ModelAttribute("searchVO") SysPublicVO publicVO, @RequestParam String mId, HttpServletRequest request) throws Exception {
		
		
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String catIdx = pubService.deleteMonitor(publicVO);
		rtn.put("success", StringUtil.isNotBlank(catIdx));

		return rtn.toString();
	}
	
}
