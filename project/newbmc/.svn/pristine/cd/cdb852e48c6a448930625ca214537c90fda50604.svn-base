package egovframework.portal.sys.basic.stateMng.web;

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.basic.stateMng.service.StateExceptIPMngService;
import egovframework.portal.sys.basic.stateMng.vo.StateExceptIPVO;
import egovframework.portal.util.TUtil;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;

/**
 * 관리자 - 관리자페이지 통계 제외IP Controller 클래스
 *
 * @author 김장섭
 * @since 2018-10-11
 * @version 1.0
 * @see
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2018-10-11 김장섭
 *
 * </pre>
 */
@Controller("StateExceptIPMngController")
public class StateExceptIPMngController {
	@Autowired
	protected MenuSysService menuService;

	@Autowired
	protected StateExceptIPMngService stateExceptIPMngService;

	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/sys/stateExceptIPMng/list.do")
	public String accessAllowList(@ModelAttribute("searchVO") StateExceptIPVO searchVO
		, @RequestParam("mId") String mId
		, HttpServletRequest request
		, ModelMap model) throws Exception {
		String searchType = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchType", "")); // 검색타입
		String searchTxt = TUtil.securityParameter(ServletRequestUtils.getStringParameter(request, "searchTxt", "")); // 검색어

		String search_query = getSearchQuery(searchType, searchTxt);

		searchVO.setSearchQuery(search_query);
		int totalCnt = stateExceptIPMngService.getStateExceptIPListCnt(searchVO);
		List<StateExceptIPVO> resultList = stateExceptIPMngService.getStateExceptIPList(searchVO);

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("resultList", resultList);

		return "/sys/basic/stateMng/exceptIP/list/";
	}

	private String getSearchQuery(String searchType, String searchTxt) {
		String search_query = ""; // 쿼리문

		// 검색쿼리 설정
		if (!"".equals(searchType) && !"".equals(searchTxt)) {
			search_query = " and (" + searchType + " LIKE '%" + searchTxt + "%' or IP_BAND_A LIKE '%" + searchTxt + "%' or IP_BAND_B LIKE '%" + searchTxt + "%')";
		}
		return search_query;
	}

	@RequestMapping("/sys/stateExceptIPMng/write.do")
	public String accessAllowInsertPage(@ModelAttribute("searchVO") StateExceptIPVO searchVO
		, @RequestParam("mId") String mId
		, HttpServletRequest request
		, ModelMap model) throws Exception {

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/basic/stateMng/exceptIP/write/";
	}

	@RequestMapping("/sys/stateExceptIPMng/writeProc.do")
	public void accessAllowInsertProc(@ModelAttribute("insertVO") StateExceptIPVO insertVO
		, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			int dupChk = stateExceptIPMngService.getStateExceptIPDupChk(insertVO);

			if (dupChk > 0) {
				jsonObj.put("flag", "fail");
				jsonObj.put("message", "등록된 제외 아이피가 존재합니다.\r\n 다른 정보를 입력해주십시요.");
				out.println(jsonObj.toString());
			} else {
				stateExceptIPMngService.insertStateExceptIP(insertVO);
				jsonObj.put("flag", "success");
				jsonObj.put("message", "제외 아이피 등록이 정상적으로 처리되었습니다.");
				out.println(jsonObj.toString());
			}
		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "제외 아이피 등록 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			logger.error("제외 아이피 등록 오류");
		}
	}

	@RequestMapping("/sys/stateExceptIPMng/modify.do")
	public String accessAllowUpdatePage(@ModelAttribute("searchVO") StateExceptIPVO searchVO
		, @RequestParam("mId") String mId
		, HttpServletRequest request
		, ModelMap model) throws Exception {

		model.addAttribute("result", stateExceptIPMngService.getStateExceptIP(searchVO));
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/basic/stateMng/exceptIP/modify/";
	}

	@RequestMapping("/sys/stateExceptIPMng/modifyProc.do")
	public void accessAllowUpdateProc(@ModelAttribute("updateVO") StateExceptIPVO updateVO
		, HttpServletRequest request
		, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			stateExceptIPMngService.updateStateExceptIP(updateVO);
			jsonObj.put("flag", "success");
			jsonObj.put("message", "제외 아이피 수정이 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());

		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "제외 아이피 수정 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			logger.error("제외 아이피 수정 오류");
		}
	}

	@RequestMapping("/sys/stateExceptIPMng/deleteProc.do")
	public void accessAllowDeleteProc(@ModelAttribute("searchVO") StateExceptIPVO deleteVO
		, HttpServletRequest request
		, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			stateExceptIPMngService.deleteStateExceptIP(deleteVO);
			jsonObj.put("flag", "success");
			jsonObj.put("message", "제외 아이피 삭제가 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());

		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "제외 아이피 삭제 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

			logger.error("제외 아이피 삭제 오류");
		}
	}
}
