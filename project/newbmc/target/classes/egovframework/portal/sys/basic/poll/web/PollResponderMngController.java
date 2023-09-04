package egovframework.portal.sys.basic.poll.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.sys.basic.poll.service.PollMngService;
import egovframework.portal.sys.basic.poll.service.PollResponderMngService;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponderMngVO;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 설문 참여자 정보 관리 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 10. 24.	J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 10. 24.
 */
@Controller
public class PollResponderMngController {

	@Resource
	protected PollMngService pollService;
	@Resource
	protected PollResponderMngService responderService;

	/** 참여자 목록 */
	@RequestMapping("/sys/pollMng/responder/list.do")
	public String list(@ModelAttribute("searchVO") PollResponderMngVO searchVO, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		PollMngVO poll = pollService.getPollView(new PollMngVO(searchVO.getPollIdx()));
		if (poll != null && poll.hasResponderInfo()) {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
			searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

			int totalCnt = responderService.getTotalCnt(searchVO);
			model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
			model.addAttribute("page", page);
			model.addAttribute("result", responderService.getList(searchVO));
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
			model.addAttribute("poll", poll);
			model.addAttribute("lottable", poll.isLottable());

			return "/sys/basic/poll/responder/list/";
		}

		WriterUtil.flushJsAlertAndRedirect(response, "중복대상이 [본인인증]으로 설정되어 있지 않거나\\n설문조사 정보가 정확하지 않습니다.", "location.href = '/sys/pollMng/list.do?mId=" + mId + "';");
		return null;
	}

	/** 당첨 취소 */
	@ResponseBody
	@RequestMapping("/sys/pollMng/responder/lottery/cancel.do")
	public String cancel(@ModelAttribute("searchVO") PollResponderMngVO searchVO, @RequestParam String mId) throws JSONException {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		PollMngVO poll = pollService.getPollView(new PollMngVO(searchVO.getPollIdx()));
		PollResponderMngVO entity = poll != null ? responderService.getEntity(searchVO) : null;
		if (entity != null) {
			rtn.put("success", responderService.lotteryCancel(poll, entity));
		}

		return rtn.toString();
	}

	/** 추첨 */
	@ResponseBody
	@RequestMapping("/sys/pollMng/responder/lot.do")
	public String lot(@ModelAttribute("searchVO") PollResponderMngVO searchVO, @RequestParam String mId) throws JSONException {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		PollMngVO poll = pollService.getPollView(new PollMngVO(searchVO.getPollIdx()));
		if (poll != null) {
			rtn.put("success", responderService.lot(poll));
		}

		return rtn.toString();
	}

	/** 엑셀 다운로드 */
	@RequestMapping("/sys/pollMng/responder/downProc.do")
	public void down(@ModelAttribute("searchVO") PollResponderMngVO searchVO, HttpServletResponse response, @RequestParam String mId) throws Exception {
		PollMngVO poll = pollService.getPollView(new PollMngVO(searchVO.getPollIdx()));
		List<Map<String, String>> dataList = responderService.getTotalListAsMap(searchVO);
		ExcelUtil excel = ExcelUtil.INSTANCE;
		ExcelUtilConfig excelData = new ExcelUtilConfig("poll_responder_list_" + TUtil.getToday("yyyyMMddHHmmss"), "[설문 참여자 목록] " + poll.getTitle(), dataList, Boolean.TRUE); // 옵션 VO 초기화
		excelData.setDataKeyListValues("NAME,GENDER,BIRTH,TEL,IP,LOTTERY,CREATE_DATE"); // 쿼리에서 사용한 칼럼 이름
		excelData.setHeaderNameValues("참여자명,성별,생년월일,연락처,IP,추첨상태,참여일자"); // 엑셀 상단에 출력될 열 이름
		excelData.setColSizeListValues("20,10,20,20,20,13,20");
		excel.setExcel(response, excelData);
	}

}