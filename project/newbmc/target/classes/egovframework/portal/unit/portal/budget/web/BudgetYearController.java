package egovframework.portal.unit.portal.budget.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.unit.portal.budget.service.BudgetYearService;

/**
 * 예산서 컨트롤러
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 6. 22.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 6. 22.
 */
@Controller
public class BudgetYearController {

	@Autowired
	private BudgetYearService budgetYearService;

	/**
	 * 예산서 목록
	 *
	 * @param year
	 * @param type
	 * @param mId
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("/**/budget/list.do")
	public String list(@RequestParam String year, @RequestParam String type, @RequestParam String mId, HttpServletRequest req, HttpServletResponse rep, ModelMap model) {
		model.addAttribute("year", year);
		model.addAttribute("type", type);
		return "/portal/budget/list";
	}

	/**
	 * 예산서 자료 조회
	 *
	 * @param category
	 * @param category2
	 * @param colType
	 * @param caption
	 * @param title
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("/portal/budget/data.do")
	public String data(@RequestParam String category, @RequestParam String category2, @RequestParam String colType,
		@RequestParam String caption, @RequestParam(required = false, defaultValue = "") String title, HttpServletRequest req, HttpServletResponse rep, ModelMap model) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("title", title);
		paramMap.put("category", category);
		paramMap.put("category2", category2);
		paramMap.put("colType", colType);
		paramMap.put("caption", caption);

		List<Map<String, Object>> retList = budgetYearService.getBudgetYear(paramMap);
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("retList", retList);
		return "/portal/budget/data";
	}

	/**
	 * 연도별 예산 데이터
	 *
	 * @param title
	 * @param category
	 * @param category2
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("/portal/budget/tableData.do")
	public String tableData(@RequestParam String title, @RequestParam String category, @RequestParam String category2, HttpServletRequest req, HttpServletResponse rep, ModelMap model) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("title", title);
		paramMap.put("category", category);
		paramMap.put("category2", category2);
		List<Map<String, Object>> retList = budgetYearService.getBudgetTableData(paramMap);
		model.addAttribute("retList", retList);
		model.addAttribute("paramMap", paramMap);
		return "/portal/budget/table";
	}

}