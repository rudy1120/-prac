package egovframework.portal.sys.privacy.web;

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
import egovframework.portal.sys.privacy.service.PrivacyDataService;
import egovframework.portal.sys.privacy.vo.PrivacyDataHistoryVO;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;
import net.arnx.jsonic.JSON;

/**
 * 개인정보취급 데이터 삭제 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 20.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 20.
 */
@Controller
public class PrivacyDataController {

	@Autowired
	protected PrivacyDataService dataService;

	/** 데이터 삭제 이력 목록 */
	@RequestMapping("/sys/privacy/data/history/list.do")
	public String list(@ModelAttribute("searchVO") PrivacyDataHistoryVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* PAGINATION SETTING */

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = dataService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", dataService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/sys/privacy/data/history/list/";
	}

	/** 데이터 삭제 이력 다운로드 */
	@RequestMapping("/sys/privacy/data/history/down.do")
	public void down(@ModelAttribute("searchVO") PrivacyDataHistoryVO searchVO, @RequestParam("mId") String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = dataService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("sys_privacy_data_delete_history_" + TUtil.getToday("yyyyMMddHHmmss"), "개인정보 취급 데이터 삭제 이력", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("PRM_NAME,PRM_TABLE_NAME,PRM_PERIOD,PROC_CNT,DELETE_DATE"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("프로그램명,테이블명,보유기간,삭제건수,삭제일"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("80,35,15,15,20");
		excel.setExcel(response, excelData);
	}

	/** 데이터 삭제 처리 */
	@ResponseBody
	@RequestMapping("/crontab/expired/privacy/data/deleteProc.do")
	public byte[] deleteProc(@ModelAttribute("searchVO") PrivacyPrmVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		Map<String, Object> rtn = new HashMap<>();
		rtn.put("success", Boolean.FALSE);

		String allowedIp[] = EgovProperties.getProperty(Constant.LIVE_SERVER_IPS).split(",");
		String accessIp = SessionUtil.getRemoteAddr(request);
		if (StringUtil.contains(accessIp, allowedIp)) {
			rtn.put("success", Boolean.TRUE);
			rtn.put("result", dataService.deletePrivacyData(SessionUtil.getRemoteAddr(request)));
		}

		return JSON.encode(rtn, Boolean.TRUE).getBytes("UTF-8");
	}
}