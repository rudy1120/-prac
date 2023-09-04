package egovframework.portal.sys.MenuMng.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.MenuMng.service.PrivacyMenuService;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 개인정보취급메뉴 CONTROLLER
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 5. 30.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 5. 30.
 */
@Controller
public class PrivacyMenuController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected PrivacyMenuService privacyMenuService;

	@RequestMapping("/sys/totalAdminMng/menuSysMng/privacy/list.do")
	public String list(@ModelAttribute("searchVO") SysMenuVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		/* PAGINATION SETTING */

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = privacyMenuService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", privacyMenuService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/sys/totalAdminMng/menuSysMng/privacy/list";
	}

	@RequestMapping(value = "/sys/totalAdminMng/menuSysMng/privacy/down.do")
	public void down(@ModelAttribute("searchVO") LoggingVO searchVO, @RequestParam("mId") String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = privacyMenuService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("privacy_data_menu_list_" + TUtil.getToday("yyyyMMddHHmmss"), "개인정보취급 관리자 메뉴 목록", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("MID,MENU_NAME,IS_USE,REG_DT,MOD_DT"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("메뉴ID,메뉴명,사용여부,등록일,수정일"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("20,40,10,15,15");
		excel.setExcel(response, excelData);
	}
}