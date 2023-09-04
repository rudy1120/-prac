package egovframework.portal.sys.bbs.web;

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

import egovframework.portal.common.service.CommonCodeService;
import egovframework.portal.common.vo.CommonCodeVO;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.bbs.service.BbsLogMngService;
import egovframework.portal.sys.bbs.service.BbsStatService;
import egovframework.portal.sys.bbs.vo.BbsLogMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 게시글 처리 이력 관리 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 8. 28.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 8. 28.
 */
@Controller
public class BbsLogMngController {

	@Autowired
	protected MenuSysService menuSysService;
	@Autowired
	protected BbsLogMngService bbsLogMngService;
	@Autowired
	protected SiteMngService siteMngService;
	@Autowired
	protected BbsStatService bbsStatService;
	@Autowired
	protected CommonCodeService commonCodeService;

	/** 게시글 처리 이력 목록 */
	@RequestMapping("/sys/bbs/log/list.do")
	public String list(@ModelAttribute("searchVO") BbsLogMngVO searchVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = bbsLogMngService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", bbsLogMngService.getList(searchVO));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("siteList", siteMngService.getSiteCodeList(request));
		model.addAttribute("searchSummary", bbsStatService.getSummary(searchVO.getSearchPeriodType(), searchVO.getSearchYear(), searchVO.getSearchMonth(), searchVO.getSearchSday(), searchVO.getSearchEday()));
		model.addAttribute("bbsTypeList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));

		return "/sys/bbs/log/list/";
	}

	/** 게시글 처리 이력 엑셀 */
	@RequestMapping("/sys/bbs/log/downProc.do")
	public void operationExcel(@ModelAttribute("searchVO") BbsLogMngVO searchVO, @RequestParam String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = bbsLogMngService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig( //
			"bbs_log_" + TUtil.getToday("yyyyMMddHHmmss"), //
			bbsStatService.getSummary(searchVO.getSearchPeriodType(), searchVO.getSearchYear(), searchVO.getSearchMonth(), searchVO.getSearchSday(), searchVO.getSearchEday()) + " 게시판별 게시글 등록/삭제/수정/조회 건수 통계", //
			dataList, Boolean.TRUE //
		); // 옵션 VO 초기화
		excelData.setDataKeyListValues("SITE_NAME,PT_TITLE,B_TITLE,B_WRITE,B_PROC,OPERATOR_IP,OPERATOR_ID,OPERATE_DATE"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("사이트명,게시판명,게시글명,작성자,처리,처리IP,처리ID,처리일"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("18,50,60,13,10,18,13,18");
		excel.setExcel(response, excelData);
	}

}
