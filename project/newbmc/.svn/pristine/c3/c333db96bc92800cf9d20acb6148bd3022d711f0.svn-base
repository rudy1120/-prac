package egovframework.portal.sys.privacy.web;

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

import egovframework.portal.sys.privacy.ProcType;
import egovframework.portal.sys.privacy.service.PrivacyPrmHistoryService;
import egovframework.portal.sys.privacy.vo.PrivacyPrmHistoryVO;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 개인정보 취급 메뉴 설정 변경 이력 관리 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 19.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 19.
 */
@Controller
public class PrivacyPrmHistoryController {

	@Autowired
	protected PrivacyPrmHistoryService historyService;

	/** 설정 변경 이력 목록 */
	@RequestMapping("/sys/privacy/prm/history/list.do")
	public String list(@ModelAttribute("searchVO") PrivacyPrmHistoryVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* PAGINATION SETTING */

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = historyService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", historyService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("procTypeList", ProcType.values());

		return "/sys/privacy/prm/history/list/";
	}

	/** 설정 변경 이력 다운로드 */
	@RequestMapping("/sys/privacy/prm/history/down.do")
	public void down(@ModelAttribute("searchVO") PrivacyPrmHistoryVO searchVO, @RequestParam("mId") String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = historyService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("sys_privacy_prm_history_" + TUtil.getToday("yyyyMMddHHmmss"), "개인정보 취급 메뉴 정보 변경 이력", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("PRM_NAME,PRM_PERIOD,DEPT_NAME,ADMIN_ID,ADMIN_NAME,PROC_TYPE,UPDATE_DATE"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("프로그램명,보유기간,부서명,관리자ID,관리자명,처리,수정일"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("60,10,35,35,35,15,20");
		excel.setExcel(response, excelData);
	}

}