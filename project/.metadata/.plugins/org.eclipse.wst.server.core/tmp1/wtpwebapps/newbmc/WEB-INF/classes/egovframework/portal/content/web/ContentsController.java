package egovframework.portal.content.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.aop.MenuFetcher;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.main.MenuType;
import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.basic.stateMng.service.StateSysService;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 컨텐츠 관련 서비스 컨트롤러 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2013.04.09		박동환				최초 생성 및 코딩
 * 2017.05.31		J.Ryeon Lee			공통 메뉴 처리 코드 원복(redirect되는 케이스가 많으므로 예외 처리) @see {@link MenuFetcher}
 * </pre>
 *
 * @author 개발팀 박동환
 * @since 2013.04.09
 * @version 1.0
 */
@Controller
public class ContentsController {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected EgovPropertyService propertyService;
	@Autowired
	protected MenuMngService menuService;
	@Autowired
	protected StateSysService stateService;
	@Autowired
	protected BbsConfigService bbsConfigService;

	private String getReturnPage(String siteCodeFull, String siteGroup, String siteCode, MenuVO menuVO) {
		String returnPage = "";
		if (MenuType.CONTENT.getCode().equals(menuVO.getMenuType()) || MenuType.FILE.getCode().equals(menuVO.getMenuType())) {
			return "/" + (StringUtil.isBlank(siteGroup) ? siteCode : siteGroup) + "/contents";
		} else if (MenuType.BOARD.getCode().equals(menuVO.getMenuType())) {
			BbsConfigVO config = SiteCode.toType(siteCode).isSupportExternalCommonBbs() // 공통 게시판을 각 부서 홈페이지가 아닌 다른 곳에서 쓰기 위한 임시 처리
				? bbsConfigService.getBbsConfigView(new BbsConfigVO(String.valueOf(menuVO.getBbsMstIdx()))) : null;
			returnPage = config != null && config.getPtSiteCode().equals(SiteCode.COMMON.getCode()) //
				? "redirect:/" + siteCodeFull + menuVO.getProgramUrl() + "?ptIdx=" + menuVO.getBbsMstIdx() + "&mId=" + menuVO.getmId() //
				: "redirect:/" + siteCodeFull + "/bbs/list.do?ptIdx=" + menuVO.getBbsMstIdx() + "&mId=" + menuVO.getmId();
		} else if (MenuType.PROGRAM.getCode().equals(menuVO.getMenuType())) {
			returnPage = "redirect:/" + siteCodeFull + menuVO.getProgramUrl().replaceAll("&amp;", "&") + addMidParameter(menuVO.getProgramUrl(), menuVO.getmId());
		}

		return returnPage;
	}

	private String addMidParameter(String url, String mId) {
		if (StringUtil.isBlank(url)) {
			return "";
		} else {
			if (url.contains("?")) {
				return "&mId=" + mId;
			} else {
				return "?mId=" + mId;
			}
		}
	}

	/** valida content menu로 이동 */
	@RequestMapping("/{siteGroup}/{siteCode}/contents.do")
	public String menuContents(@PathVariable String siteGroup, @PathVariable String siteCode, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mId", mId);
		paramMap.put("siteCode", siteCode);

		return menuContents(siteGroup + "/" + siteCode, mId, request, response, model);
	}

	/** valida content menu로 이동 */
	@RequestMapping("/{siteCodeFull}/contents.do")
	public String menuContents(@PathVariable String siteCodeFull, String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		String siteCode = "";
		if (siteCodeFull.contains("/")) {
			String[] siteCodes = siteCodeFull.split("/");
			siteCode = siteCodes[(siteCodes.length-1)];
		} else {
			siteCode = siteCodeFull;
		}

		if(mId.length() != 10) {
			WriterUtil.flushJsAlertAndRedirect(response, "잘못된 경로로 접근했습니다.", "document.location.href='/" + siteCodeFull + "/main.do';");
			return null;
		}
		
		/** 하위 메뉴가 있지만 컨텐츠를 포함해야하는 메뉴의 경우 childMid를 가져오지 않도록 수정 (2018. 04. 23 권태성) */
		MenuVO curMenu = menuService.getMenuInfo(mId, siteCode);
		if (0 != curMenu.getIsFirst()) {
			String childMid = menuService.getAvailableChildMid(new MenuVO(mId, siteCode));
			if (!"".equals(childMid)) {
				return "redirect:/"+siteCodeFull+"/contents.do?mId=" + childMid;	//사용중인 하위 메뉴가 있을때는 하위 메뉴로 이동
			}
		}

		if (0 != menuService.getAbailableMid(new MenuVO(mId, siteCode))) {
			MenuVO menuVO = menuService.getMenuInfo(mId, siteCode);
			String returnProcess = getReturnPage(siteCodeFull, null, siteCode, menuVO);
			if (returnProcess.contains("redirect:")) {
				return returnProcess;
			} else {
				commonService.commonDataCreater(request, response, model);
				return returnProcess;
			}
		} 
		else {
			WriterUtil.flushJsAlertAndRedirect(response, "존재하지 않는 페이지입니다.", "document.location.href='/" + siteCodeFull + "/main.do';");
			return null;
		}

	}

	/**
	 * 사이트맵 페이지
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{siteCode}/guide/siteMap.do")
	public String commonSiteMaps(@RequestParam String mId, @RequestParam(required = false) String sitemapCode, @PathVariable String siteCode, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		commonService.commonDataCreater(request, response, model);

		if (StringUtil.isNotBlank(sitemapCode) && SiteCode.toType(sitemapCode).hasSitemap()) {
			Map<String, Object> sitemapMenuMap = menuService.getMenuListMap(model, mId, sitemapCode, request);
			if ((Integer) sitemapMenuMap.get("menusSize") == 0) {
				WriterUtil.flushJSInvalidAccess(response);
				return null;
			}

			model.addAttribute("sitemapMenuMap", menuService.getMenuListMap(model, mId, sitemapCode, request).get("menus"));
		}

		model.addAttribute("sitemapCode", sitemapCode);
		return "/guide/siteMaps/" + siteCode;
	}

	/**
	 * 사이트맵 시각화 페이지
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{siteCode}/guide/siteMapVisualization.do")
	public String commonSiteMapsVisualization(@RequestParam(required = false, defaultValue = "portal") String sitemapCode, @PathVariable String siteCode, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		commonService.commonDataCreater(request, response, model);

		String year = ServletRequestUtils.getStringParameter(request, "year", TUtil.getToday("yyyy"));
		String month = ServletRequestUtils.getStringParameter(request, "month", TUtil.getToday("MM"));

		StateSysVO inputVO = new StateSysVO();
		inputVO.setYear(year);
		inputVO.setMonth(month);
		inputVO.setSiteCode(sitemapCode);

		List<StateSysVO> result = stateService.selectStatsVisualization(inputVO);
		model.addAttribute("result", result);
		model.addAttribute("searchVO", inputVO);

		model.addAttribute("sitemapCode", sitemapCode);
		return "/guide/siteMapsVisualization/" + siteCode;
	}
}
