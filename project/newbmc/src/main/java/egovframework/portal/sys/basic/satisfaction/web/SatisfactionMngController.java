package egovframework.portal.sys.basic.satisfaction.web;

import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.satisfaction.service.SatisfactionMngService;
import egovframework.portal.sys.basic.satisfaction.vo.SatisfactionMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;
import egovframework.rte.fdl.string.EgovStringUtil;

/**
 * 만족도 관리 Controller
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014. 02. 27.	엄동건				최초 생성 및 코딩
 * 2016. 11. 28.	J.Ryeon Lee			만족도 통계로 관리자 목록 리팩토링
 * 2017. 02. 22.	J.Ryeon Lee			만족도 내용을 평가 내용과 평점으로 제공하도록 기능 분기
 * </pre>
 *
 * @author 엄동건
 * @since 2014. 02. 27.
 */
@Controller
public class SatisfactionMngController {

	@Autowired
	protected MenuSysService menuService;
	@Autowired
	protected MenuMngService menuMngService;
	@Autowired
	protected SatisfactionMngService satisfactionService;
	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected SiteMngService siteMngService;

	private static Logger LOGGER = LoggerFactory.getLogger(SatisfactionMngController.class);

	/** 만족도 평가 목록 */
	@RequestMapping(value = "/sys/satisfaction/total/list.do")
	public String list(@ModelAttribute("searchVO") SatisfactionMngVO searchVO, @RequestParam("mId") String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		int page = Integer.parseInt(EgovStringUtil.null2string(request.getParameter("page"), "1")); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = satisfactionService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("result", satisfactionService.getTotalList(searchVO));

		MenusMngVO siteSearchVO = new MenusMngVO();
		siteSearchVO.setSearchQuery("WHERE is_use = 'Y' AND is_tmp = 'N'");
		siteSearchVO.setFirstIndex(1);
		siteSearchVO.setLastIndex(999);
		model.addAttribute("siteList", siteMngService.getSiteList(siteSearchVO));
		model.addAttribute("searchVO", searchVO);

		return "/sys/satisfaction/total/list";
	}

	/** 만족도 평가 목록 다운로드 */
	@RequestMapping(value = "/sys/satisfaction/total/down.do")
	public void down(@ModelAttribute("searchVO") SatisfactionMngVO searchVO, @RequestParam("mId") String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = satisfactionService.getTotalListAsMap(searchVO); // 목록 데이터 조회
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("satisfaction_list_" + TUtil.getToday("yyyyMMddHHmmss"), "포털 이용 만족도", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("siteCode,menuName,researchContent,researchPoint,regDt"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("사이트,메뉴명,평가내용,점수,평가일"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("15,25,110,10,15");
		excel.setExcel(response, excelData);
	}

	/** 만족도 평점 목록 */
	@RequestMapping(value = "/sys/satisfaction/listSatis.do")
	public String listSatis(@ModelAttribute("searchVO") SatisfactionMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId) throws Exception {
		int page = Integer.parseInt(EgovStringUtil.null2string(request.getParameter("page"), "1")); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = satisfactionService.getSatisfactionStatCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("resultList", satisfactionService.getSatisfactionStatList(searchVO));
		model.addAttribute("totalCount", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("page", page);

		List<MenusMngVO> listMenu = StringUtil.isNotBlank(searchVO.getSiteCode()) // 메뉴 코드 목록
			? satisfactionService.comboMenusForSite(searchVO.getSiteCode()) : null;
			model.addAttribute("listMenu", listMenu);
		model.addAttribute("listSite", menuMngService.getMenusMngListAll(request)); // 사이트 코드 목록
		model.addAttribute("searchVO", searchVO);

		return "/sys/satisfaction/listSatis";
	}

	/** 만족도 평점 다움로드 */
	@RequestMapping(value = "/sys/satisfaction/listSatis/down.do")
	public void listSatisDown(@ModelAttribute("searchVO") SatisfactionMngVO searchVO, HttpServletResponse response) throws Exception {
		List<Map<String, String>> dataList = satisfactionService.getSatisfactionStatListAsMap(searchVO);

		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("satisfaction_list_" + TUtil.getToday("yyyyMMddHHmmss"), "포털 이용 만족도 평점 통계", dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("SITE_NAME,MENU_NAME,R_POINT,APP_CNT"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("사이트,메뉴명,평점,참여자수"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("15,30,15,15");
		excel.setExcel(response, excelData);
	}

	/** 만족도 평가 */
	@RequestMapping(value = "/**/satisfaction/voteSatis.do")
	public String voteSatis(@ModelAttribute("searchVO") SatisfactionMngVO inputVo, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		try {
			String sessionId = (request.getSession()).getId();
			String referer = request.getHeader("REFERER");
			String mId = null;
			String siteCode = ServletRequestUtils.getStringParameter(request, "siteCode", "portal"); // 사이트분류

			URI uri = URI.create(referer);
			List<NameValuePair> params = URLEncodedUtils.parse(uri, "UTF-8");
			for (NameValuePair param : params) {
				if (param.getName().equals("mId")) {
					mId = param.getValue();
				}
			}

			// 로그 저장
			inputVo.setSiteCode(siteCode); // site
			inputVo.setSatisMid(mId); // menu
			inputVo.setSsId(sessionId); // session

			String searchQuery = " where SITE_CODE='" + siteCode + "'";
			if (mId != null && !mId.equals("")) {
				searchQuery += " and SATIS_MID='" + mId + "'";
			}
			searchQuery += " and SSID='" + sessionId + "'";
			searchQuery += " and date_format(REG_DT,'%Y%m%d')='" + TUtil.getToday("yyyyMMdd") + "'";

			inputVo.setSearchQuery(searchQuery);

			JSONObject jsonObject = new JSONObject();
			if (satisfactionService.checkDuplSatisfaction(inputVo) > 0) {
				jsonObject.put("flag", "fail");
				jsonObject.put("message", "이미 평가하신 컨텐츠입니다.");
			} else {
				if (inputVo.getResearchPoint() > 5) {
					inputVo.setResearchPoint(5);
				} else if (inputVo.getResearchPoint() < 0) {
					inputVo.setResearchPoint(0);
				}

				satisfactionService.insertSatisfaction(inputVo);
				jsonObject.put("flag", "true");
				jsonObject.put("message", "컨텐츠 평가를 반영하였습니다.");
			}

			out.println(jsonObject.toString());

		} catch (Exception ex) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("flag", "fail");
			jsonObject.put("message", "메뉴목록 조회  중 오류가 발생하였습니다.");

			out.println(jsonObject.toString());
		}

		return null;
	}

	/** 사이트별 메뉴 FETCH */
	@RequestMapping("/sys/satisfaction/comboMenuForSite.do")
	public String comboMenuForSite(@ModelAttribute("searchVO") SatisfactionMngVO inputVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		try {
			List<MenusMngVO> listMenu = satisfactionService.comboMenusForSite(inputVO.getSiteCode());
			String[] set = { "satisMid", "menuName", "parentName" };
			JSONObject jsonObject = new JSONObject();

			try {
				jsonObject.put("list", TUtil.listToJson(listMenu, set));
			} catch (JSONException e) {
				LOGGER.error("comboMenuForSite JSONException");
			}

			jsonObject.put("flag", "success");
			out.println(jsonObject.toString());

		} catch (Exception ex) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("flag", "fail");
			jsonObject.put("message", "사이트목록 조회  중 오류가 발생하였습니다.");

			out.println(jsonObject.toString());
		}

		return null;
	}

}
