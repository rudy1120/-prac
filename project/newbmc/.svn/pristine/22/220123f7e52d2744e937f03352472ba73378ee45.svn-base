package egovframework.portal.sys.basic.stateMng.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.basic.stateMng.service.StateSysService;
import egovframework.portal.sys.basic.stateMng.vo.StateSearchMenuSysVO;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;

/**
 * 통계관리 Controller
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.02.13		엄동건				최초 생성
 * 2016.05.17		J.Ryeon Lee			첨부파일 다운로드 통계 추가
 * 2016.05.18		J.Ryeon Lee			게시물 통계 추가 및 requestMapping 내부 관심사 분리 (엑셀 다운시 재활용하기 위해서)
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2014.02.13
 * @version 1.0
 */
@Controller
public class StateSysController {

	@Autowired
	protected MenuSysService menuService;
	@Autowired
	protected StateSysService stateService;
	@Autowired
	protected MenuMngService menuMngService;

	private static final Logger logger = Logger.getLogger(StateSysController.class);

	@RequestMapping("/sys/stateMng/download.do")
	public String statDownloader(@RequestParam String kind, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("kind", kind);
		return "statDownloader";
	}

	/**
	 * 통계관리
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sys/stateMng/list.do")
	public String stateList(@ModelAttribute("searchVO") StateSysVO inputVO, @RequestParam(defaultValue = "N") String downYn, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request)); // 메뉴획득

		// 값 셋팅
		int kind = ServletRequestUtils.getIntParameter(request, "kind", 0); // 통계 탭분류
		String siteCode = ServletRequestUtils.getStringParameter(request, "siteCode", ""); // 사이트분류
		String year = ServletRequestUtils.getStringParameter(request, "year", TUtil.getToday("yyyy"));
		String month = ServletRequestUtils.getStringParameter(request, "month", TUtil.getToday(""));
		int sType = ServletRequestUtils.getIntParameter(request, "sType", 1); // 기간분류

		inputVO.setKind(kind);
		inputVO.setSiteCode((kind == 0 || kind == 7) ? siteCode : (StringUtil.isNotBlank(siteCode) ? siteCode : SiteCode.PORTAL.getCode()));
		if(kind == 6){
			inputVO.setSiteCode("");
		}
		inputVO.setYear(year);
		inputVO.setMonth(month);
		inputVO.setsType(sType);

		// 데이터 셋팅
		inputVO.setSearchTable("state_today_site");

		List<MenusMngVO> listSite = menuMngService.getMenusMngListAll(request); // 사이트코드 리스트 추출- PT_STATE_TODAY_SITE
		List<StateSearchMenuSysVO> listMenu = StringUtil.isNotBlank(inputVO.getSiteCode()) // 사이트코드 리스트 추출
			? stateService.comboMenusForSite(inputVO.getSiteCode()) //
			: null;
		HashMap<String, Integer> totalCnt = stateService.getTotalCount(inputVO); // 사이트전체 요약 통계 - 상단출력 고정
		HashMap<String, Integer> totalCntKind = null; // 부문별 요약 통계 - 사이트/메뉴
		List<StateSysVO> results = new ArrayList<StateSysVO>(0);

		/* =================== 20160518 J.Ryeon Lee union table명 생성 concern 분리 (TODO 분리만 한 거라서 sqlmap으로 옮기고 리팩토링할 필요 있음) =================== */

		inputVO.setSearchTable(stateService.searchTableByParams(kind, sType, inputVO, year)); // union table name setting
		inputVO.setSearchQuery(stateService.searchQueryByParams(kind, sType, inputVO)); // search query setting

		// 통계 출력컨텐츠

		switch (kind) {
			case 1:
				totalCntKind = stateService.getTotalCountSM(inputVO);
				break;
			case 2:
			case 3:
				break;
			default: // case 0
				totalCntKind = stateService.getTotalCountSM(inputVO);
				break;
		}

		/* ==================================================================================================================================================== */

		switch (kind) {
			case 0:
			case 1:
				switch (sType) {
					case 1:
						results = stateService.selectStatsYearSM(inputVO);
						break;
					case 2:
						results = stateService.selectStatsMonthSM(inputVO);
						break;
					case 3:
						results = stateService.selectStatsPeriodSM(inputVO);
						break;
					default:
						break;
				}
				break;
			case 2:
				results = stateService.selectStatsReferer(inputVO);
				break;
			case 3:
				results = stateService.selectStatsAgent(inputVO);
				break;
			case 6:
				switch (sType) {
					case 1:
						results = stateService.selectStatsYearSM(inputVO);
						break;
					case 2:
						results = stateService.selectStatsMonthSM(inputVO);
						break;
					case 3:
						results = stateService.selectStatsPeriodSM(inputVO);
						break;
					default:
						break;
				}
				break;
			case 7:
				switch (sType) {
					case 1:
						results = stateService.selectStatsYearSM(inputVO);
						break;
					case 2:
						results = stateService.selectStatsMonthSM(inputVO);
						break;
					case 3:
						results = stateService.selectStatsPeriodSM(inputVO);
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}

		int statsCountMax = 0;
		for (StateSysVO i : results) {
			statsCountMax += i.getStatsCount();
		}
		inputVO.setStatsCountMax(statsCountMax);

		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("totalCntKind", totalCntKind);
		model.addAttribute("listSite", listSite);
		model.addAttribute("listMenu", listMenu);
		model.addAttribute("results", results);
		model.addAttribute("inputVo", inputVO);

		/* 20160517 J.Ryeon Lee ADD */

		if (kind == 4) {
			List<Map<String, Integer>> fileDownStat = null;
			if (StringUtil.isNotBlank(inputVO.getDateStart())) {
				inputVO.setYear("");
				inputVO.setMonth("");
				fileDownStat = stateService.selectFileDownStatsPeriodSM(inputVO);
			} else if (StringUtil.isNotBlank(inputVO.getMonth()) && StringUtil.isNotBlank(inputVO.getYear())) {
				fileDownStat = stateService.selectFileDownStatsDaySM(inputVO);
			} else if (StringUtil.isNotBlank(inputVO.getYear())) {
				fileDownStat = stateService.selectFileDownStatsMonthSM(inputVO);
			} else {
				inputVO.setMonth("");
				fileDownStat = stateService.selectFileDownStatsYearSM(inputVO);
			}

			model.addAttribute("userStat", stateService.getTotalFileDownCount(inputVO));
			model.addAttribute("results", fileDownStat);
		} else if (kind == 5) {
			List<Map<String, Integer>> bbsViewStat = null;
			if (StringUtil.isNotBlank(inputVO.getDateStart())) {
				inputVO.setYear("");
				inputVO.setMonth("");
				bbsViewStat = stateService.selectBbsViewStatsPeriodSM(inputVO);
			} else if (StringUtil.isNotBlank(inputVO.getMonth()) && StringUtil.isNotBlank(inputVO.getYear())) {
				bbsViewStat = stateService.selectBbsViewStatsDaySM(inputVO);
			} else if (StringUtil.isNotBlank(inputVO.getYear())) {
				bbsViewStat = stateService.selectBbsViewStatsMonthSM(inputVO);
			} else {
				inputVO.setMonth("");
				bbsViewStat = stateService.selectBbsViewStatsYearSM(inputVO);
			}

			model.addAttribute("userStat", stateService.getTotalBbsViewCount(inputVO));
			model.addAttribute("results", bbsViewStat);
		} else if (kind == 6) {
			List<StateSysVO> popularSiteList = null;
			if(inputVO.getsType() == 3){
				inputVO.setYear(TUtil.getToday("yyyy"));
				inputVO.setMonth("");
				popularSiteList = stateService.selectPopularSitePeriodSM(inputVO);
			} else if(inputVO.getsType() == 2){
				popularSiteList = stateService.selectPopularSiteMonthSM(inputVO);
			} else if(inputVO.getsType() == 1){
				popularSiteList = stateService.selectPopularSiteYearSM(inputVO);
				inputVO.setMonth("");
			} else {
				inputVO.setMonth("");
				popularSiteList = stateService.selectPopularSiteYearSM(inputVO);
			}

			statsCountMax = 0;
			for (int i = 0; i < popularSiteList.size(); i++) {
				statsCountMax += popularSiteList.get(i).getStatsCount();
			}
			inputVO.setStatsCountMax(statsCountMax);

			model.addAttribute("userStat", stateService.getTotalBbsViewCount(inputVO));
			model.addAttribute("results", popularSiteList);
			model.addAttribute("inputVo", inputVO);
		} else if (kind == 7) {
			List<StateSysVO> popularMenuList = null;
			if (inputVO.getsType() == 3) {
				inputVO.setYear(TUtil.getToday("yyyy"));
				inputVO.setMonth("");
				popularMenuList = stateService.selectPopularMenuPeriodSM(inputVO);
			} else if (inputVO.getsType() == 2) {
				popularMenuList = stateService.selectPopularMenuMonthSM(inputVO);
			} else if (inputVO.getsType() == 1) {
				popularMenuList = stateService.selectPopularMenuYearSM(inputVO);
				inputVO.setMonth("");
			} else {
				inputVO.setMonth("");
				popularMenuList = stateService.selectPopularMenuYearSM(inputVO);
			}

			statsCountMax = 0;
			for (int i = 0; i < popularMenuList.size(); i++) {
				statsCountMax += popularMenuList.get(i).getStatsCount();
			}
			inputVO.setStatsCountMax(statsCountMax);

			model.addAttribute("userStat", stateService.getTotalBbsViewCount(inputVO));
			model.addAttribute("results", popularMenuList);
			model.addAttribute("inputVo", inputVO);
		}

		model.addAttribute("thisYear", Calendar.getInstance().get(Calendar.YEAR));

		if (downYn.equals("Y")) {
//			stateService.makeExcelFile(kind, siteCode, year, month, sType, results, response);
			return "statDownloader";
		} else {
			return "/sys/basic/stateMng/state/list/";
		}

	}

	@RequestMapping("/sys/stateMng/comboMenuForSite.do")
	public String comboMenuForSite(@ModelAttribute("searchVO") StateSearchMenuSysVO inputVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		try {

			List<StateSearchMenuSysVO> listMenu = stateService.comboMenusForSite(inputVO.getParamCode());
			String[] set = { "mId", "menuName", "parentName" };
			JSONObject jsonObject = new JSONObject();

			try {
				jsonObject.put("list", listToJson(listMenu, set));
			} catch (JSONException e) {
				logger.error("메뉴 목록 조회 중 JSON 리스트 변환 오류");
			}

			jsonObject.put("flag", "success");
			out.println(jsonObject.toString());

		} catch (Exception ex) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("flag", "fail");
			jsonObject.put("message", "메뉴목록 조회  중 오류가 발생하였습니다.");

			out.println(jsonObject.toString());

			logger.error("메뉴 목록 조회 오류");
		}

		return null;
	}

	public static JSONArray listToJson(List<?> objectList, String[] set) {
		if (set.length == 0) {
			return null;
		}

		JSONArray jsonArray = new JSONArray();

		for (Object object : objectList) {
			JSONObject jsonObject = new JSONObject();

			try {
				for (int i = 0; i < set.length; i++) {
					jsonObject.put(set[i], PropertyUtils.getProperty(object, set[i]) == null ? "" : PropertyUtils.getProperty(object, set[i]));
				}
			} catch (Exception e) {
				logger.error("리스트 json 변환 오류");
				return null;
			}
			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

}
