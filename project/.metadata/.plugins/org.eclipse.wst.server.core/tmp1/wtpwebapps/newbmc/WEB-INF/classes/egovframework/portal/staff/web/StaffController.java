package egovframework.portal.staff.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.portal.common.service.CommonService;
import egovframework.portal.dept.service.DeptService;
import egovframework.portal.staff.service.StaffService;
import egovframework.portal.staff.vo.StaffVO;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 직원업무정보 Controller 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014. 12. 28.	김장섭				최초 생성 및 코딩
 * 2016. 10. 04.	J.Ryeon Lee			공통 코드 적용 및 sql injection 위험 코드 제거
 * </pre>
 *
 * @author 김장섭
 * @since 2014. 12. 28.
 */
@Controller
public class StaffController {

	@Autowired
	protected StaffService staffService;
	@Autowired
	protected CommonService commonService;
	@Autowired
	protected DeptService deptService;
	@Autowired
	protected SiteMngService siteMngService;

	/** 직원 목록 */
	@RequestMapping("/**/staff/list.do")
	public String staffList(@ModelAttribute("searchVO") StaffVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = staffService.getStaffCnt(searchVO);

		model.addAttribute("deptList", deptService.getDeptCategoryList());
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", staffService.getStaffList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/" + model.get("siteCodeFull").toString() + "/staff/list/";
	}
}
