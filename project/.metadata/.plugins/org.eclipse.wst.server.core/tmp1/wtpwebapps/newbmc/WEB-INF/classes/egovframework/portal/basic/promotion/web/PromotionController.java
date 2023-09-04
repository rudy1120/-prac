package egovframework.portal.basic.promotion.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.basic.promotion.service.PromotionService;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.basic.promotion.PromotionType;
import egovframework.portal.sys.basic.promotion.vo.PromotionVO;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 홍보 자료 표시 CONTOLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 20.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 20.
 */
@Controller
public class PromotionController {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected PromotionService promotionService;
	@Autowired
	protected SiteMngService siteMngService;

	/** 목록(배너 ONLY) */
	@RequestMapping("/portal/banner/list.do")
	public String list(@ModelAttribute("searchVO") PromotionVO searchVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		commonService.commonDataCreater(request, response, model);
		searchVO.setType(PromotionType.BANNER);

		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = promotionService.getBannerTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("page", page);

		model.addAttribute("result", promotionService.getBannerList(searchVO));
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("siteList", siteMngService.getListByPrmtType(searchVO.getType()));

		return "/portal/banner/list/";
	}

}
