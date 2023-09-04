package egovframework.portal.sys.log.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.log.service.BbsConfigLogService;
import egovframework.portal.sys.log.vo.BbsConfigLogVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 게시판 로그 관리 CONTORLLOER
 *
 * @author J.Ryeon Lee
 * @since 2015.12.31
 */
@Controller
public class BbsConfigLogController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected BbsConfigLogService bbsConfigLogService;

	@RequestMapping("/sys/{siteCode}/bbs/bbsConfigLog/list.do")
	public String bbsConfigLogList(@ModelAttribute("searchVO") BbsConfigLogVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("siteCode", siteCode);

		if (StringUtil.isNumber(searchVO.getsYear())) {
			searchVO.seteYear(String.valueOf(Integer.parseInt(searchVO.getsYear()) + 1));
		}

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = bbsConfigLogService.getTotalBbsConfigLogCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", bbsConfigLogService.getBbsConfigLogList(searchVO));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("start", Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())) - 5);

		return "/sys/bbs/bbsConfigLog/list";
	}

	/** 게시글 처리 이력 엑셀 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsConfigLog/downProc.do")
	public void operationExcel(@ModelAttribute("searchVO") BbsConfigLogVO searchVO, @RequestParam String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = bbsConfigLogService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig( //
			"bbs_config_log_" + TUtil.getToday("yyyyMMddHHmmss"), //
			"게시판 등록/수정/삭제 이력", //
			dataList, Boolean.TRUE //
		); // 옵션 VO 초기화
		excelData.setDataKeyListValues("PT_IDX,PT_TITLE,PT_DEPT_NAME,PT_WRITER,PT_WRITE_ID,PT_PROC,PT_HOSTIP,PT_DATE"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("코드,처리 당시 게시판명,부서명,이름,처리ID,처리 상태,접근 아이피,처리일자"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("10,70,17,17,17,17,20,20");
		excel.setExcel(response, excelData);
	}

}
