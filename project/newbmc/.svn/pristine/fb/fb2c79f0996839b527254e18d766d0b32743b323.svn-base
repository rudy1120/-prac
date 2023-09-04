package egovframework.portal.sys.log.web;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.log.service.LoggingConfigService;
import egovframework.portal.sys.log.service.LoggingService;
import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;
import net.arnx.jsonic.JSON;

/**
 * 통합 로그 목록 CONTROLLER
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
public class LoggingController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected LoggingService loggingService;
	@Autowired
	protected LoggingConfigService configService;

	@RequestMapping("/sys/logging/list.do")
	public String list(@ModelAttribute("searchVO") LoggingVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		/* PAGINATION SETTING */

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = loggingService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", loggingService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/sys/logging/list";
	}

	@RequestMapping(value = "/sys/logging/down.do")
	public void down(@ModelAttribute("searchVO") LoggingVO searchVO, @RequestParam("mId") String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = loggingService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("sys_logging_list_" + TUtil.getToday("yyyyMMddHHmmss"), "통합 관리자 로그", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("full_url,mid,menu_name,proc_name,host_ip,admin_id,admin_name,dept_name,access_date"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("접근 URL,메뉴ID,메뉴명,처리내용,접근IP,관리자ID,관리자명,부서명,접근일자"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("80,15,28,20,15,15,15,15,20");
		excel.setExcel(response, excelData);
	}

	@ResponseBody
	@RequestMapping(value = "/crontab/expired/logging/deleteProc.do")
	public byte[] deleteBatchProc(@ModelAttribute("searchVO") LoggingConfigVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");

		Map<String, Object> rtn = new HashMap<>();
		rtn.put("success", Boolean.FALSE);

		String allowedIps[] = EgovProperties.getPathProperty(Constant.LIVE_SERVER_IPS).split(",");
		String hostIp = SessionUtil.getRemoteAddr(request);
		if (StringUtil.contains(hostIp, allowedIps)) {
			rtn.put("result", loggingService.deleteExpiredLogs(SessionUtil.getAdminInstance(request), SessionUtil.getRemoteAddr(request)));
			rtn.put("success", Boolean.TRUE);
		}

		return JSON.encode(rtn).getBytes("UTF-8");
	}
}