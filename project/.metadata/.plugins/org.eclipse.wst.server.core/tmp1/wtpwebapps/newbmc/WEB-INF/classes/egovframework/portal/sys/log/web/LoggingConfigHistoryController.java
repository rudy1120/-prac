package egovframework.portal.sys.log.web;

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

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.log.service.LoggingConfigHistoryService;
import egovframework.portal.sys.log.service.LoggingConfigService;
import egovframework.portal.sys.log.vo.LoggingConfigHistoryVO;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 통합 로그 보관 주기 설정 변경 이력 관리 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 8.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 8.
 */
@Controller
public class LoggingConfigHistoryController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected LoggingConfigService configService;
	@Autowired
	protected LoggingConfigHistoryService historyService;

	@RequestMapping("/sys/logging/config/history/list.do")
	public String config(@ModelAttribute("searchVO") LoggingConfigHistoryVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

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

		return "/sys/logging/config/history/list";
	}

	@RequestMapping("/sys/logging/config/history/down.do")
	public void down(@ModelAttribute("searchVO") LoggingConfigHistoryVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = historyService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("sys_logging_list_" + TUtil.getToday("yyyyMMddHHmmss"), "통합 관리자 로그 관리 설정 변경 이력", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("LOG_NAME,OLD_LOG_PERIOD,NEW_LOG_PERIOD,ADMIN_ID,ADMIN_NAME,DEPT_NAME,HOST_IP,UPDATE_DATE"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("항목,기존보관기간,변경보관기간,변경자ID,변경자명,부서명,접근IP,변경일자"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("40,15,15,20,20,20,20,20");
		excel.setExcel(response, excelData);
	}

}