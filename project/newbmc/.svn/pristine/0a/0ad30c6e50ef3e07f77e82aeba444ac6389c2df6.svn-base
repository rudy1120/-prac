package egovframework.portal.sys.basic.promotion.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.promotion.PromotionType;
import egovframework.portal.sys.basic.promotion.service.PromotionMngService;
import egovframework.portal.sys.basic.promotion.vo.PromotionVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 홍보자료 관리 CONTROLLER (배너, 홍보이미지, 팝업존)
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 18.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 18.
 */
@Controller
public class PromotionMngController {

	@Autowired
	protected PromotionMngService promotionMngService;
	@Autowired
	protected SiteMngService siteMngService;

	/** 목록 */
	@RequestMapping("/sys/{prmtPath}/list.do")
	public String list(@ModelAttribute("searchVO") PromotionVO searchVO, @PathVariable String prmtPath, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		searchVO.setType(PromotionType.toType(prmtPath));

		/* PAGINATION SETTING */
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		List<MenusMngVO> siteList = siteMngService.getListByPrmtType(searchVO.getType());

		if(searchVO.getSearchSiteIdx()==null || "".equals(searchVO.getSearchSiteIdx())) {
			if(siteList != null && siteList.size() > 0) {
				searchVO.setSearchSiteIdx(String.valueOf(siteList.get(0).getIdx()));
			}
		}

		int totalCnt = promotionMngService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", promotionMngService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("prmtTypeList", PromotionType.values());
		model.addAttribute("siteList", siteList);
		model.addAttribute("prmtOrderList", promotionMngService.getPrmtOrderList(searchVO));

		return "/sys/basic/promotion/list/";
	}

	/** 등록/수정 화면 */
	@RequestMapping({ "/sys/{prmtPath}/write.do", "/sys/{prmtPath}/modify.do" })
	public String modify(@ModelAttribute("searchVO") PromotionVO searchVO, @PathVariable String prmtPath, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PromotionVO entity = promotionMngService.getEntity(searchVO);
		if (entity != null) {
			entity.setSearchSiteIdx(searchVO.getSearchSiteIdx());
			BeanUtil.copy(entity, searchVO);
		}

		searchVO.setType(PromotionType.toType(prmtPath));
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("prmtTypeList", PromotionType.values());
		model.addAttribute("siteList", siteMngService.getListByPrmtType(searchVO.getType()));
		model.addAttribute("prmtOrderList", promotionMngService.getPrmtOrderList(searchVO));

		return "/sys/basic/promotion/update/";
	}

	/** 등록 처리 */
	@RequestMapping("/sys/{prmtPath}/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") PromotionVO searchVO, @PathVariable String prmtPath, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		searchVO.setType(PromotionType.toType(prmtPath));
		String newIdx = promotionMngService.insert(searchVO, request);
		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/sys/" + searchVO.getType().getPath() + "/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 수정 처리 */
	@RequestMapping("/sys/{prmtPath}/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") PromotionVO searchVO, @PathVariable String prmtPath, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		searchVO.setType(PromotionType.toType(prmtPath));
		String modIdx = promotionMngService.update(searchVO, request);
		if (StringUtil.isNotBlank(modIdx)) {
			return "redirect:/sys/" + searchVO.getType().getPath() + "/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	/** 삭제 처리 */
	@ResponseBody
	@RequestMapping("/sys/{prmtPath}/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") PromotionVO searchVO, @PathVariable String prmtPath, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		searchVO.setType(PromotionType.toType(prmtPath));
		String delIdx = promotionMngService.delete(searchVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}

	/** 정렬순서변경 처리 */
	@RequestMapping("/sys/{prmtPath}/changeOrderProc.do")
	public String changeOrderProc(@ModelAttribute("searchVO") PromotionVO searchVO, @PathVariable String prmtPath, @RequestParam String mId, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		searchVO.setType(PromotionType.toType(prmtPath));
		String changeIdx = promotionMngService.changeOrder(searchVO);

		if (StringUtil.isNotBlank(changeIdx)) {
			return "redirect:/sys/" + searchVO.getType().getPath() + "/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	@RequestMapping("/sys/banner/excel/format/download.do")
	public void excelFormatDownload(@RequestParam String mId, HttpServletResponse response) throws Exception {
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("sys_banner_format", "통합 관리자 배너 업로드 양식", new ArrayList<Map<String, String>>(), Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("SITE_IDXS,PRMT_NAME,PRMT_URL,PRMT_SDAY,PRMT_EDAY,USE_YN"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("사이트 ID(쉼표로 구분),제목,URL,게시 시작일,게시 종료일,사용 여부(Y/N)"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("40,25,40,15,15,18");
		excel.setExcel(response, excelData);
	}

	/** 엑셀 업로드 */
	@ResponseBody
	@RequestMapping(value = "/sys/banner/excel/uploadProc.do")
	public String excelUploadProc(@RequestParam String mId, MultipartHttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject().put("success", Boolean.FALSE);

		String mode = request.getParameter("mode");
		try {
			result.put("success", Boolean.TRUE) //
				.put("cnt", promotionMngService.uploadBannerExcel(request.getFile("excelFile"), StringUtil.isNotBlank(mode) && "copy".equals(mode)));
		} catch (Exception e) {
			result.put("cnt", -1);
		}

		return result.toString();
	}

}
