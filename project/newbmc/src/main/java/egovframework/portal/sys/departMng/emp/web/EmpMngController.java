package egovframework.portal.sys.departMng.emp.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import egovframework.portal.sys.departMng.emp.service.EmpMngService;
import egovframework.portal.sys.departMng.emp.vo.EmpMngVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 직원관리 CONTROLLER
 * @author boram
 */
@Controller
public class EmpMngController {
	
	@Autowired
	protected EmpMngService empMngService;

	@Autowired
	protected DepartMngService departMngService;
	
	private Logger logger = Logger.getLogger(this.getClass());

	/** 목록 */
	@RequestMapping("/sys/empMng/list.do")
	public String list(@ModelAttribute("searchVO") EmpMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* PAGINATION SETTING */
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		List<DepartMngVO> departList = departMngService.getDepartList();

		int totalCnt = empMngService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));

		/* VIEW PARAMETER SETTING */
		model.addAttribute("result", empMngService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("departList", departList);

		return "/sys/deptMng/emp/list/";
	}

	/** 등록/수정 화면 */
	@RequestMapping({ "/sys/empMng/write.do", "/sys/empMng/modify.do" })
	public String modify(@ModelAttribute("searchVO") EmpMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<DepartMngVO> departList = departMngService.getDepartList();
		model.addAttribute("departList", departList);
		
		EmpMngVO entity = empMngService.getEntity(searchVO);
		if (entity != null) {
			BeanUtil.copy(entity, searchVO);
		}
		model.addAttribute("searchVO", searchVO);

		return "/sys/deptMng/emp/update/";
	}

	/** 등록 처리 */
	@RequestMapping("/sys/empMng/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") EmpMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String newIdx = empMngService.insert(searchVO);
		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/sys/empMng/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 수정 처리 */
	@RequestMapping("/sys/empMng/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") EmpMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String modIdx = empMngService.update(searchVO);
		if (StringUtil.isNotBlank(modIdx)) {
			return "redirect:/sys/empMng/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 삭제 처리 */
	@ResponseBody
	@RequestMapping("/sys/empMng/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") EmpMngVO searchVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		String delIdx = empMngService.delete(searchVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}
	
	/** 아이디 중복확인 */
	@RequestMapping("/sys/empMng/usrIdCheck.do")
	public void usrIdCheck(@ModelAttribute("searchVO") EmpMngVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();
		
		int rtcnt = 0;

		try {
			rtcnt = empMngService.getUsrIdCheck(searchVO);
		} catch (Exception e) {
			logger.error("usrIdCheck 오류");
		}
		jsonObj.put("rtcnt", rtcnt);
		out.println(jsonObj.toString());
		
	}

}
