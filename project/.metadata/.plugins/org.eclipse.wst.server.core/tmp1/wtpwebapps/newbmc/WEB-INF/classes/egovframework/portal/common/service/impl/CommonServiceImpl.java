package egovframework.portal.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;

import egovframework.portal.common.service.CommonService;
import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenuChargeVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.basic.stateMng.service.StateSysService;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.portal.unit.common.service.MenuCommentService;
import egovframework.portal.unit.common.vo.MenuCommentVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 공용서비스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2013.10.16		엄동건				최초 생성
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2014.10.16
 * @version 1.0
 */
@Service("CommonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService {

	@Autowired
	protected MenuMngService menuService;
	@Autowired
	protected StateSysService stateService;
	@Autowired
	protected MenuCommentService menuCommentService;
	@Autowired
	protected MenuMngService menuMngService;

	protected LinkedHashMap<String, String> map_dept = new LinkedHashMap<String, String>();

	@SuppressWarnings("unchecked")
	@Override
	public ModelMap commonDataCreater(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
		String mId = ServletRequestUtils.getStringParameter(request, "mId", ""); // 메뉴 아이디
		String contextPath = request.getRequestURI();
		String[] contextPaths = contextPath.split("/");

		model.addAttribute("curUrlEncode", TUtil.rplcEncRedirectUrl(TUtil.curUrl(request)));
		model.addAttribute("curUrl", TUtil.curUrl(request));

		if (SiteCode.toType(contextPaths[1]).isBuiltIn()) { // built-in
			if (SiteCode.toType(contextPaths[1]).isOneDepthBuiltIn()) {
				model.addAttribute("siteCode", contextPaths[1]);
				model.addAttribute("siteGroup", contextPaths[1]);
				model.addAttribute("siteCodeFull", contextPaths[1]);
			} else {
				model.addAttribute("siteCode", contextPaths[2]);
				model.addAttribute("siteGroup", contextPaths[1]);
				model.addAttribute("siteCodeFull", contextPaths[1] + "/" + contextPaths[2]);
			}

			SiteCode siteCodeType = SiteCode.toType(model.get("siteCode").toString());
			Map<String, Object> MenuMap = menuService.getMenuListMap(model, null, "portal", request);
			List<MenuVO> ptMenus = (List<MenuVO>) MenuMap.get("menus");
			model.addAttribute("ptMenus", ptMenus); // 포탈 메뉴 세팅
			model.addAttribute("menuSize", ptMenus.size());

//			if (("dept".equals(contextPaths[1]))) {
//				model.addAllAttributes(menuService.getMenuListMap(model, (String) map_dept.get(contextPaths[2]), contextPaths[2], request));
//			} else if (SiteCode.toType(contextPaths[1]).isOneDepthBuiltIn()) { // 20170531 J.Ryeon Lee 불필요한 레거시로 보이는 코드를 주석 처리 // 추후 문제가 있다면 재세팅
			if (siteCodeType.isOneDepthBuiltIn()) {
				model.addAllAttributes(menuService.getMenuListMap(model, mId, contextPaths[1], request)); // built-in site Menu
			} else if (siteCodeType.isTwoDepthBuiltIn()) {
				model.addAllAttributes(menuService.getMenuListMap(model, mId, contextPaths[2], request)); // built-in site Menu
			}
		} else if (contextPaths.length <= 2) {
			model.addAttribute("siteCode", "portal");
			model.addAttribute("siteGroup", "portal");
			model.addAttribute("siteCodeFull", "portal");
			model.addAllAttributes(menuService.getMenuListMap(model, mId, "portal", request));
		} else {
			model.addAttribute("siteCode", contextPaths[1]);
			model.addAttribute("siteGroup", contextPaths[1]);
			model.addAttribute("siteCodeFull", contextPaths[1]);
			model.addAllAttributes(menuService.getMenuListMap(model, mId, contextPaths[1], request));
		}

		addActiveMenuTo(model, request, mId);
		
		//총접속자수
		StateSysVO inputVO = new StateSysVO();
		HashMap<String, Integer> totalCnt = stateService.getTotalCount(inputVO); // 사이트 전체 요약 통계 - 상단출력 고정
		model.addAttribute("totalSiteConnectCnt", totalCnt);
		
		// 20160620 J.Ryeon Lee 메뉴 RESEARCH COMMENT
		// 20170531 J.Ryeon Lee 미사용 코드 주석 처리
		int menuCommentPage = ServletRequestUtils.getIntParameter(request, "menuCommentPage", 1);
		MenuCommentVO searchVO = new MenuCommentVO(String.valueOf(model.get("siteCode")), mId);
		searchVO.setFirstIndex(PaginationInfoUtil.calUserFirstIndex(menuCommentPage));
		searchVO.setLastIndex(PaginationInfoUtil.calUserLastIndex(menuCommentPage));
		int total = menuCommentService.getMenuCommentCnt(searchVO);

		model.addAttribute("menuCommentPaginationInfo", PaginationInfoUtil.calUserPaginationInfo(menuCommentPage, total));
		model.addAttribute("menuCommentCnt", total);
		model.addAttribute("menuCommentList", menuCommentService.getMenuCommentList(searchVO));

		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addActiveMenuTo(ModelMap model, HttpServletRequest request, String mId) throws UnsupportedEncodingException {
		if (StringUtil.isNotBlank(mId)) {
			MenuVO menuVO = menuService.getMenuInfo(mId, model.get("siteCode").toString());
			menuVO = menuService.getActiveMenu(menuVO.getmId(), (List<MenuVO>) model.get("menus"));
			if (model.containsKey("addTitleBBS") && menuVO != null) {
				
				MenuVO parent = menuVO.getParentMenu();
				String subHeadTitle2 = menuVO.getMenuName() + model.get("addTitleBBS");
				while (parent != null) {
					subHeadTitle2 += " | " + parent.getMenuName();
					parent = parent.getParentMenu();
				}

				menuVO.setSubHeadTitle2(subHeadTitle2);
			}
			
			if (menuVO != null) {
				model.addAttribute("chargeList", menuMngService.getMenuCharge(new MenuChargeVO(model.get("siteCode").toString(), menuVO.getmId())));
			} else {
				
				model.addAttribute("chargeList", menuMngService.getMenuCharge(new MenuChargeVO(model.get("siteCode").toString(), mId)));
			}
		
			model.addAttribute("activeMenu", menuVO);
	
		} else {
			model.addAttribute("activeMenu", null);
			model.addAttribute("chargeList", menuMngService.getMenuCharge(new MenuChargeVO(model.get("siteCode").toString(), mId)));
		}
	}

}
