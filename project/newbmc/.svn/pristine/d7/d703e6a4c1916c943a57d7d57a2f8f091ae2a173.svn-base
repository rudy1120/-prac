package egovframework.portal.sys.bbs.web;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.common.service.CommonCodeService;
import egovframework.portal.common.vo.CommonCodeVO;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.bbs.service.BbsStatService;
import egovframework.portal.sys.bbs.vo.BbsFileDownStatVO;
import egovframework.portal.sys.bbs.vo.BbsOperationStatVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 게시글 조작 이력 통계 컨트롤러
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
public class BbsStatController {

	@Autowired
	protected MenuSysService menuSysService;
	@Autowired
	protected SiteMngService siteMngService;
	@Autowired
	protected BbsStatService bbsStatService;
	@Autowired
	protected CommonCodeService commonCodeService;

	/** 등록/수정/삭제/조회 통계 목록 */
	@RequestMapping("/sys/bbs/stat/operation/list.do")
	public String operationList(@ModelAttribute("searchVO") BbsOperationStatVO searchVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = bbsStatService.getOperationStatTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", bbsStatService.getOperationStatList(searchVO));
		model.addAttribute("snippet", bbsStatService.getOperationStatSnippet(searchVO));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("siteList", siteMngService.getSiteCodeList(request));
		model.addAttribute("searchSummary", bbsStatService.getSummary(searchVO.getSearchPeriodType(), searchVO.getSearchYear(), searchVO.getSearchMonth(), searchVO.getSearchSday(), searchVO.getSearchEday()));
		model.addAttribute("bbsTypeList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));

		return "/sys/bbs/stat/operation/";
	}

	/** 등록/수정/삭제/조회 통계 엑셀 */
	@RequestMapping("/sys/bbs/stat/operation/downProc.do")
	public void operationExcel(@ModelAttribute("searchVO") BbsOperationStatVO searchVO, @RequestParam String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = bbsStatService.getOperationStatListAsMap(searchVO); // 목록 데이터 조회
		
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig( //
			"bbs_stat_operation_" + TUtil.getToday("yyyyMMddHHmmss"), //
			bbsStatService.getSummary(searchVO.getSearchPeriodType(), searchVO.getSearchYear(), searchVO.getSearchMonth(), searchVO.getSearchSday(), searchVO.getSearchEday()) + " 게시판별 게시글 등록/삭제/수정/조회 건수 통계", //
			dataList, Boolean.TRUE //
		); // 옵션 VO 초기화
		excelData.setDataKeyListValues("site_name,bbs_type_name,config_name,create_cnt,total_delete_cnt,admin_delete_cnt,user_delete_cnt,auto_delete_cnt,update_cnt,hit_cnt"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("사이트명,게시판 타입,게시판명,등록 건수,삭제 건수(전체),삭제 건수(관리자),삭제 건수(사용자),삭제 건수(자동),수정 건수,조회 건수"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("23,18,70,18,18,18,18,18,18,18");
		excel.setExcel(response, excelData);
	}

	/** 첨부파일 통계 목록 */
	@RequestMapping("/sys/bbs/stat/fileDown/list.do")
	public String fileDown(@ModelAttribute("searchVO") BbsFileDownStatVO searchVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = bbsStatService.getFileDownStatTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", bbsStatService.getFileDownStatList(searchVO));
		model.addAttribute("snippet", bbsStatService.getFileDownStatSnippet(searchVO));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("siteList", siteMngService.getSiteCodeList(request));
		model.addAttribute("searchSummary", bbsStatService.getSummary(searchVO.getSearchPeriodType(), searchVO.getSearchYear(), searchVO.getSearchMonth(), searchVO.getSearchSday(), searchVO.getSearchEday()));
		model.addAttribute("bbsTypeList", commonCodeService.getCodeList(new CommonCodeVO("BOARD")));

		return "/sys/bbs/stat/fileDown/";
	}

	/** 첨부파일 통계 엑셀 */
	@RequestMapping("/sys/bbs/stat/fileDown/downProc.do")
	public void fileDownExcel(@ModelAttribute("searchVO") BbsFileDownStatVO searchVO, @RequestParam String mId, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = bbsStatService.getFileDownStatListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig( //
			"bbs_stat_filedown_" + TUtil.getToday("yyyyMMddHHmmss"), //
			bbsStatService.getSummary(searchVO.getSearchPeriodType(), searchVO.getSearchYear(), searchVO.getSearchMonth(), searchVO.getSearchSday(), searchVO.getSearchEday()) + " 게시판별 게시글 첨부파일 다운로드 건수 통계", //
			dataList, Boolean.TRUE //
		); // 옵션 VO 초기화
		excelData.setDataKeyListValues("site_name,bbs_type_name,config_name,down_cnt"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("사이트명,게시판 타입,게시판명,다운로드 건수"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("23,13,70,13");
		excel.setExcel(response, excelData);
	}

}
