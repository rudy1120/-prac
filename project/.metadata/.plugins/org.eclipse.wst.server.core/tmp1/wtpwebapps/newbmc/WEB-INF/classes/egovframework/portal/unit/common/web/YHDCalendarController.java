package egovframework.portal.unit.common.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.common.AppStateType;
import egovframework.portal.unit.common.service.YHDCalendarService;
import egovframework.portal.unit.common.vo.DayVO;
import egovframework.portal.util.WriterUtil;
import net.arnx.jsonic.JSON;

/**
 * YHD-Calendar CONTROLLER
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016.08.19		J.Ryeon Lee			금오산 야영장 기능 베이스로 공통 캘린더 컨트롤러 작성
 * 2016.08.27		J.Ryeon Lee			마스터 테이블에서 렌더링 대상 테이블 정보를 fetch 후 작업하도록 수정
 * 2017.01.11		J.Ryeon Lee			2차 빌트인 리퀘스트 매핑 코딩
 * 2017.02.27		J.Ryeon Lee			history.back 구현용 파라미터 추가
 * 2017.07.06		J.Ryeon Lee			기능 고도화(불필요 파라미터 제거 및 dmt_yhd_calendar 테이블 정비)
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.08.19
 */
@Controller
public class YHDCalendarController {

	@Autowired
	protected YHDCalendarService calendarService;

	/** 목록 (2차 빌트인) */
	@RequestMapping("/{siteGroup}/{siteCode}/{keyUrl}/calendar.do")
	public String list(@ModelAttribute DayVO searchVO, @RequestParam(required = false, defaultValue = "") String goTo, @RequestParam String mId, @PathVariable String siteGroup, @PathVariable String siteCode, @PathVariable String keyUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return list(searchVO, goTo, mId, siteGroup + "/" + siteCode, keyUrl, model, request, response);
	}

	/** 목록 */
	@RequestMapping("/{siteCode}/{keyUrl}/calendar.do")
	public String list(@ModelAttribute DayVO searchVO, @RequestParam(required = false, defaultValue = "") String goTo, @RequestParam String mId, @PathVariable String siteCode, @PathVariable String keyUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> table = calendarService.getTableInfo(keyUrl);
		if (table != null) {
			model.addAttribute("tableInfo", table);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("tableInfoJson", JSON.encode(table).replace("\"", "\\\""));
			model.addAttribute("stateTypeList", AppStateType.values());
			model.addAttribute("goTo", goTo);

			return "/" + siteCode + "/yhdcalendar/calendar";
		}

		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

	/** 월별 JSON 데이타 조회 (2차 빌트인) */
	@ResponseBody
	@RequestMapping("/{siteGroup}/{siteCode}/{keyUrl}/monthly/data/fetch.do")
	public byte[] fetch(@ModelAttribute DayVO searchVO, @PathVariable String siteGroup, @PathVariable String siteCode, @PathVariable String keyUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return fetch(searchVO, siteGroup + "/" + siteCode, keyUrl, model, request, response);
	}

	/** 월별 JSON 데이타 조회 */
	@ResponseBody
	@RequestMapping("/{siteCode}/{keyUrl}/monthly/data/fetch.do")
	public byte[] fetch(@ModelAttribute DayVO searchVO, @PathVariable String siteCode, @PathVariable String keyUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain; charset=utf-8");
		JSONObject rtn = new JSONObject().put("list", "");

		searchVO.setTableInfo(calendarService.getTableInfo(keyUrl));
		if (searchVO.getTableInfo() != null) {
			rtn.put("list", calendarService.getMonthlyDataList(searchVO));
			rtn.put("tableInfo", searchVO.getTableInfo());
		}

		return rtn.toString().getBytes("UTF-8");
	}

	/** 일별 JSON 데이타 조회 (2차 빌트인) */
	@ResponseBody
	@RequestMapping("/{siteGroup}/{siteCode}/{keyUrl}/daily/data/fetch.do")
	public byte[] dailyDataList(@ModelAttribute DayVO searchVO, @PathVariable String siteGroup, @PathVariable String siteCode, @PathVariable String keyUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return dailyDataList(searchVO, siteGroup + "/" + siteCode, keyUrl, model, request, response);
	}

	/** 일별 JSON 데이타 조회 */
	@ResponseBody
	@RequestMapping("/{siteCode}/{keyUrl}/daily/data/fetch.do")
	public byte[] dailyDataList(@ModelAttribute DayVO searchVO, @PathVariable String siteCode, @PathVariable String keyUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain; charset=utf-8");
		JSONObject rtn = new JSONObject().put("list", "");

		searchVO.setTableInfo(calendarService.getTableInfo(keyUrl));
		if (searchVO.getTableInfo() != null) {
			rtn.put("list", calendarService.getDailyDataList(searchVO));
			rtn.put("stateTypeList", AppStateType.asMap());
			rtn.put("tableInfo", searchVO.getTableInfo());
		}

		return rtn.toString().getBytes("UTF-8");
	}

}