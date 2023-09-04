package egovframework.portal.sys.MenuMng.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import egovframework.portal.staff.vo.StaffVO;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenuChargeVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.MenuMng.vo.MenusChargeHistoryMngVO;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import net.arnx.jsonic.JSON;

/**
 * 메뉴 관리 컨트롤러
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2014. 10. 7.		엄동건				최초 생성
 * 2017. 6. 19.		권태성				다중 담당자 설정 가능하도록 수정
 * 2017. 8. 15.		J.Ryeon Lee			공공누리, CCL 추가
 * </pre>
 *
 * @author 엄동건
 * @since 2014. 10. 7.
 */
@Controller("MenuMngController")
public class MenuMngController {

	@Autowired
	protected MenuMngService menuMngService;
	@Autowired
	protected MenuSysService menuService;

	private static final Logger LOGGER = LogManager.getLogger();

	@RequestMapping("/sys/menu.do")
	public void temp_menu(@ModelAttribute("searchVO") MenusMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect("/sys/totalAdminMng/menuMng/menuList.do");

	}

	@RequestMapping("/sys/totalAdminMng/menuMng/menuList.do")
	public String menuList(@ModelAttribute("searchVO") MenusMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (model == null) {
			return null;
		}

		String mId = ServletRequestUtils.getStringParameter(request, "mId", "");

		List<MenusMngVO> menusMngList = menuMngService.getMenusMngListAll(request);
		if (!"".equals(searchVO.getSiteCode())) {
			model.addAllAttributes(menuMngService.getMenuListMap(searchVO.getSiteCode()));
		}
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));
		model.addAttribute("menusMngList", menusMngList);
		model.addAttribute("procMid", ServletRequestUtils.getStringParameter(request, "procMid", "")); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.

		return "/sys/totalAdminMng/menuMng/list";

	}

	@RequestMapping("/sys/totalAdminMng/menuMng/menuCheckList.do")
	public String menuCheckList(@RequestParam("mId") String mId, @ModelAttribute("searchVO") MenuVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {

		if (model == null) {
			return null;
		}

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		String cmd = TUtil.nullTobaseStr(request.getParameter("cmd"), "");

		List<MenusMngVO> menusMngList = menuMngService.getMenusMngListAll(request);
		model.addAttribute("menusMngList", menusMngList);

		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = 	menuMngService.getMenuCheckTotalCnt(searchVO);

		if("excel".equals(cmd)) {
			searchVO.setFirstIndex(0);
			searchVO.setLastIndex(totalCnt);
		}

		List<MenuVO> resultList = menuMngService.getMenuCheckList(searchVO);

		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("menuCheckList", resultList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("page", page);
		model.addAttribute("procMid", ServletRequestUtils.getStringParameter(request, "procMid", "")); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.


		if("excel".equals(cmd)) {
			return "/sys/totalAdminMng/menuMng/checkListExcel";
		} else {
			return "/sys/totalAdminMng/menuMng/checkList";
		}
	}

	@RequestMapping("/sys/totalAdminMng/menuMng/moveMenuProc.do")
	public void moveMenu(@ModelAttribute("searchVO") MenuVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moveKind = ServletRequestUtils.getStringParameter(request, "moveKind", "");

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {
			if ("up".equals(moveKind)) {
				if (menuMngService.chkIsTop(searchVO)) {
					jSONObject.put("flag", "fail");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "현재 메뉴위치에서 더이상 올릴 수 없습니다.");
					out.println(jSONObject.toString());
				} else {
					menuMngService.moveMenuUp(searchVO);
					jSONObject.put("flag", "success");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "메뉴순서올림 처리가 정상적으로 완료되었습니다.");
					out.println(jSONObject.toString());
				}

			} else if ("down".equals(moveKind)) {
				if (menuMngService.chkIsBottom(searchVO)) {
					jSONObject.put("flag", "fail");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "현재 메뉴위치에서 더이상 내릴 수 없습니다.");
					out.println(jSONObject.toString());
				} else {
					menuMngService.moveMenuDown(searchVO);
					jSONObject.put("flag", "success");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "메뉴순서내림 처리가 정상적으로 완료되었습니다.");
					out.println(jSONObject.toString());
				}

			}

		} catch (Exception e) {

			jSONObject.put("flag", "false");
			jSONObject.put("message", "죄송합니다. 오류가 발생했습니다.");

			out.println(jSONObject.toString());
		}
	}

	@RequestMapping("/sys/totalAdminMng/menuMng/deleteMenuProc.do")
	public void deleteMenu(@ModelAttribute("deleteVO") MenuVO deleteVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {

			menuMngService.deleteMenu(deleteVO);
			jSONObject.put("flag", "success");
			jSONObject.put("procMid", deleteVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
			jSONObject.put("message", "메뉴 삭제가 정상적으로 처리되었습니다.");

			out.println(jSONObject.toString());

		} catch (Exception e) {

			jSONObject.put("flag", "false");
			jSONObject.put("message", "오류발생");

			out.println(jSONObject.toString());
		}
	}

	@RequestMapping("/sys/totalAdminMng/menuMng/writeMenuProc.do")
	public void writeMenu(@ModelAttribute("writeVO") MenuVO writeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writeKind = ServletRequestUtils.getStringParameter(request, "writeKind", "");

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {
			/*
			 * if ((writeVO.getChargeTel2() != null && !writeVO.getChargeTel2().trim().equals(""))) {
			 * writeVO.setChargeTel(writeVO.getChargeTel1() + writeVO.getChargeTel2());
			 * } else {
			 * writeVO.setChargeTel(null);
			 * }
			 */

			if ("insert".equals(writeKind)) {
				writeVO.setMenuName(StringEscapeUtils.unescapeHtml3(writeVO.getMenuName()));
				menuMngService.insertMenu(writeVO, request);
				jSONObject.put("writeKind", writeKind);
				jSONObject.put("procMid", writeVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
				jSONObject.put("flag", "success");
				jSONObject.put("message", "메뉴 등록이 정상적으로 처리되었습니다.");
				out.println(jSONObject.toString());
			} else if ("update".equals(writeKind)) {
				writeVO.setMenuName(StringEscapeUtils.unescapeHtml3(writeVO.getMenuName()));
				menuMngService.updateMenu(writeVO, request);
				jSONObject.put("writeKind", writeKind);
				jSONObject.put("procMid", writeVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
				jSONObject.put("flag", "success");
				jSONObject.put("message", "메뉴 수정이 정상적으로 처리되었습니다.");

				out.println(jSONObject.toString());
			}
		} catch (Exception e) {

			jSONObject.put("flag", "false");
			jSONObject.put("message", "오류발생");

			out.println(jSONObject.toString());
		}

	}

	@RequestMapping("/sys/totalAdminMng/menuMng/getAddMenuInfo.do")
	public void getAddMenuInfo(@ModelAttribute("searchVO") MenuVO searchVO, HttpServletResponse response) throws Exception {
		String menuId = searchVO.getmId();

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {
			jSONObject.put("flag", "success");
			jSONObject.put("menuLevel", menuMngService.getNextMenuLevel(menuId)); // 메뉴등록시
			jSONObject.put("mId", menuMngService.getNextMid(searchVO));

			jSONObject.put("message", "조회성공");

			out.println(jSONObject.toString());
		} catch (Exception e) {
			jSONObject.put("flag", "false");
			jSONObject.put("message", "조회오류");

			out.println(jSONObject.toString());

			LOGGER.error(">> failed to run getAddMenuInfo/Exception", e);
		}

	}

	@RequestMapping("/sys/totalAdminMng/menuMng/getEditMenuInfo.do")
	public void getEditMenuInfo(@ModelAttribute("searchVO") MenuVO searchVO, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();
		MenuVO resultVO = menuMngService.getMenu(searchVO);

		try {

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("flag", "success");
			jsonMap.put("mId", resultVO.getmId());
			jsonMap.put("menuName", resultVO.getMenuName());
			jsonMap.put("linkUrl", resultVO.getLinkUrl());
			jsonMap.put("target", resultVO.getTarget());
			jsonMap.put("menuType", resultVO.getMenuType());
			jsonMap.put("isUse", resultVO.getIsUse());
			jsonMap.put("programUrl", resultVO.getProgramUrl());
			jsonMap.put("contentFilePath", resultVO.getContentFilePath());
			jsonMap.put("cmsSiteCode", resultVO.getCmsSiteCode());
			jsonMap.put("cmsPageId", resultVO.getCmsPageId());
			jsonMap.put("isIncContent", resultVO.getIsIncContent());
			jsonMap.put("bbsMstIdx", resultVO.getBbsMstIdx());
			jsonMap.put("bbsIdx", resultVO.getBbsIdx());
			jsonMap.put("bbsName", menuMngService.getMenuInBbsName(resultVO));
			jsonMap.put("isPoll", resultVO.getIsPoll());
			jsonMap.put("isFirst", resultVO.getIsFirst());
			jsonMap.put("chargeTel", resultVO.getChargeTel());
			jsonMap.put("chargeFnm", resultVO.getChargeFnm());
			jsonMap.put("chargeId", resultVO.getChargeId());
			jsonMap.put("chargeDepCode", resultVO.getChargeDepCode());
			jsonMap.put("chargeDepNm", resultVO.getChargeDepNm());
			jsonMap.put("menuOrderCode", resultVO.getMenuOrderCode());
			jsonMap.put("menuSummary", resultVO.getMenuSummary());
			jsonMap.put("cclType", resultVO.getCclType());
			jsonMap.put("nuriType", resultVO.getNuriType());
			jsonMap.put("message", "조회성공");
			jsonMap.put("isTermset", resultVO.getIsTermset());
			jsonMap.put("termStartDt", TUtil.getSimpleDateFormat(resultVO.getTermStartDt(), "yyyy-MM-dd"));
			jsonMap.put("termEndDt", TUtil.getSimpleDateFormat(resultVO.getTermEndDt(), "yyyy-MM-dd"));
			jsonMap.put("isSnsComment", resultVO.getIsSnsComment());
			jsonMap.put("chargeList", menuMngService.getMenuCharge(new MenuChargeVO(searchVO.getSiteCode(), searchVO.getmId())));
			out.println(JSON.encode(jsonMap));
		} catch (Exception e) {
			jSONObject.put("flag", "false");
			jSONObject.put("message", "조회 오류");

			out.println(jSONObject.toString());
			LOGGER.error(">> failed to run getEditMenuInfo/Exception", e);
		}
	}

	@RequestMapping("/sys/totalAdminMng/menuMng/menuInBbsList.do")
	public String menuInBbsList(@ModelAttribute("searchVO") MenuVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MenuVO> menuNotInBbsList = menuMngService.getMenuNotInBbsList(searchVO);

		model.addAttribute("menuNotInBbsList", menuNotInBbsList);

		return "/sys/totalAdminMng/menuMng/menuInBbsList";
	}

	/**
	 * CMS로 메뉴 보내기 화면
	 *
	 * @param siteCode
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/totalAdminMng/menuMng/sendToCMS.do")
	public String sendSiteToCMS(@RequestParam String siteCode, HttpServletRequest request, HttpServletResponse response,
		HttpSession session, ModelMap model) {

		//menu list
		model.addAttribute("siteCode", siteCode);
		model.addAllAttributes(menuMngService.getMenuListMap(siteCode));

		return "/sys/totalAdminMng/menuMng/sendMenuList";

	}

	@RequestMapping("/sys/totalAdminMng/menuMng/uploadExcel.do")
	public String uploadExcel(@RequestParam String siteCode, HttpServletRequest request, HttpServletResponse response,
		HttpSession session, ModelMap model) {

		model.addAttribute("siteCode", siteCode);

		return "/sys/totalAdminMng/menuMng/uploadExcel";

	}


	@RequestMapping("/sys/totalAdminMng/menuMng/getMenuChargeHistoryInfo.do")
	public void getMenuChargeInfo(@ModelAttribute("searchVO") MenuVO searchVO, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		try {
			List<MenusChargeHistoryMngVO> menusMngList = menuMngService.getMenuChargeHistoryList(searchVO);

			String[] set = { "siteCode", "mid", "chargeId", "chargeFnm", "chargeDepCode", "chargeDepNm", "regDate" };
			JSONObject jsonObject = new JSONObject();

			try {
				jsonObject.put("list", listToJson(menusMngList, set));
			} catch (JSONException e) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("none", "none");
				out.print(jSONObject.toString());
				LOGGER.error("담당자이력 조회 오류");
			}

			jsonObject.put("flag", "success");
			out.println(jsonObject.toString());

		} catch (Exception e) {
			JSONObject jSONObject = new JSONObject();
			jSONObject.put("none", "none");
			out.print(jSONObject.toString());
			LOGGER.error("담당자이력 조회 오류");
		}
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
				LOGGER.error("리스트 json 변환 오류");
				return null;
			}
			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}
	
	/**
	 * 직원정보 선택용 리스트
	 *
	 * @param multiRequest
	 * @param StaffConfigVO
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping("/sys/saeolMng/saeol/staffListChoice.do")
	@RequestMapping("/sys/totalAdminMng/menuMng/staffListChoice.do")
	public String staffListChoice(@ModelAttribute("searchVO") StaffVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String opt = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "opt", "")); // 검색타입
		model.addAttribute("opt", opt);

		//return "/sys/saeolMng/saeol/staff/listChoice";
		return "/sys/totalAdminMng/menuMng/staff/listChoice";
	}

}