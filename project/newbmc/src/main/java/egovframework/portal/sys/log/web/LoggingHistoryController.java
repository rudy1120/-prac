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
import egovframework.portal.sys.log.service.LoggingConfigService;
import egovframework.portal.sys.log.service.LoggingHistoryService;
import egovframework.portal.sys.log.vo.LoggingHistoryVO;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 통합 로그 삭제 이력 목록 CONTROLLER
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
public class LoggingHistoryController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected LoggingConfigService configService;
	@Autowired
	protected LoggingHistoryService historyService;

	@RequestMapping("/sys/logging/history/list.do")
	public String list(@ModelAttribute("searchVO") LoggingHistoryVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		return "/sys/logging/history/list";
	}

	@RequestMapping("/sys/logging/history/down.do")
	public void down(@ModelAttribute("searchVO") LoggingHistoryVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = historyService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("sys_logging_list_" + TUtil.getToday("yyyyMMddHHmmss"), "통합 관리자 로그 일괄 삭제 이력", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("LOG_NAME,DEL_CNT,DEL_TYPE,ADMIN_ID,ADMIN_NAME,DEPT_NAME,HOST_IP,DELETE_DATE"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("항목,삭제건수,처리구분,처리자ID,처리자명,부서명,접근IP,삭제일자"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("40,10,10,15,15,15,18,20");
		excel.setExcel(response, excelData);
	}

}