package egovframework.portal.unit.bmc.dept.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.departMng.depart.service.DepartMngService;
import egovframework.portal.sys.departMng.depart.vo.DepartMngVO;
import egovframework.portal.sys.departMng.emp.service.EmpMngService;
import egovframework.portal.sys.departMng.emp.vo.EmpMngVO;

@Controller
public class DeptController {
	
	@Autowired
	protected DepartMngService departMngService;
	@Autowired
	protected EmpMngService empMngService;
	
	@RequestMapping("/bmc/dept/list.do")
	public String list(@ModelAttribute("searchVO") DepartMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<DepartMngVO> result = departMngService.getList(searchVO);
		model.addAttribute("result", result);
		model.addAttribute("searchVO", searchVO);

		return "/bmc/unit/dept/list/";
	}
	
	@RequestMapping("/bmc/staff/list.do")
	public String staffList(@ModelAttribute("searchVO") DepartMngVO searchVO, @RequestParam String mId, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<DepartMngVO> result = new ArrayList<>();
		List<EmpMngVO> empList = new ArrayList<>(); 
		if (searchVO.getSearchTxt().equals("") || searchVO.getSearchTxt() == null) {
			result = departMngService.getList(searchVO);
			for (int i=0;i<result.size();i++) {
				result.get(i).setEmpList(empMngService.getStaffContents(result.get(i).getDepCode()));
			}
			
		} else {
			empList = empMngService.getStaffSearch(searchVO);
			
		}
		model.addAttribute("result", result);
		model.addAttribute("empList", empList);
		model.addAttribute("searchVO", searchVO);
		return "/bmc/unit/dept/staffList/";
	}
}
