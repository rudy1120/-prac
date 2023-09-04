package egovframework.portal.sys.basic.poll.web;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.basic.poll.service.PollMngService;
import egovframework.portal.sys.basic.poll.service.QuestionMngService;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.WriterUtil;

/**
 * 질문 관리
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 9. 1.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 9. 1.
 */
@Controller
public class PollQuestionMngController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected SiteMngService siteMngService;
	@Autowired
	private PollMngService pollMngService;
	@Autowired
	protected QuestionMngService questionMngService;

	/**
	 * 설문조사 질문 관리
	 *
	 * @param searchVO
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/question/write.do")
	public String questionWrite(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO, true, "page", "searchSiteCode", "searchViewYn", "searchState", "searchTxt");
		model.addAttribute("isNew", "Y");
		model.addAttribute("searchVO", searchVO);
		/** 신규 등록이 아닌 경우 상세 뷰로 전달 **/
		if (questionMngService.getQuestionCnt(searchVO) > 0) {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("idx", String.valueOf(searchVO.getIdx()));
			paramMap.put("page", String.valueOf(searchVO.getPage()));
			paramMap.put("searchSiteCode", searchVO.getSearchSiteCode());
			paramMap.put("searchViewYn", searchVO.getSearchViewYn());
			paramMap.put("searchState", searchVO.getSearchState());
			paramMap.put("searchTxt", searchVO.getSearchTxt());
			WriterUtil.flushJsPostRedirect(response, "설문조사 상세 페이지로 이동", "", "/sys/pollMng/question/view.do?mId=" + mId, paramMap);
			return null;
		} else {
			return "/sys/basic/poll/question/update";
		}
	}

	/**
	 * 설문조사 질문 등록
	 *
	 * @param searchVO
	 * @param result
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sys/pollMng/question/writeProc.do")
	public byte[] questionWriteProc(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/json; charset=UTF-8");
		JSONObject json = new JSONObject();

		boolean ret = questionMngService.setInsertQuestion(request, searchVO);

		if (ret) {
			json.put("flag", "success");
			json.put("msg", "저장되었습니다.");
			return json.toString().getBytes("UTF-8");
		} else {
			json.put("flag", "fail");
			json.put("msg", "저장 처리 중 오류가 발생하였습니다.");
			return json.toString().getBytes("UTF-8");
		}

	}


	/**
	 * 설문조사 질문 상세
	 *
	 * @param searchVO
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/question/view.do")
	public String questionView(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO, true, "page", "searchSiteCode", "searchViewYn", "searchState", "searchTxt");
		model.addAttribute("isNew", "N");
		model.addAttribute("searchVO", searchVO);
		/** 기존에 등록되어 있는 질문 정보 조회 **/
		searchVO = questionMngService.getQuestionInfo(searchVO);
		return "/sys/basic/poll/question/view";
	}

	/**
	 * 설문조사 질문 관리
	 *
	 * @param searchVO
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/question/update.do")
	public String questionUpdate(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO, true, "page", "searchSiteCode", "searchViewYn", "searchState", "searchTxt");
		model.addAttribute("searchVO", searchVO);
		/** 기존에 등록되어 있는 질문 정보 조회 **/
		searchVO = questionMngService.getQuestionInfo(searchVO);

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("idx", String.valueOf(searchVO.getIdx()));
		paramMap.put("page", String.valueOf(searchVO.getPage()));
		paramMap.put("searchSiteCode", searchVO.getSearchSiteCode());
		paramMap.put("searchViewYn", searchVO.getSearchViewYn());
		paramMap.put("searchState", searchVO.getSearchState());
		paramMap.put("searchTxt", searchVO.getSearchTxt());

		if (blockOverDate(searchVO.getStartDate(), searchVO.getEndDate()) && "Y".equals(searchVO.getStateYn()) && "Y".equals(searchVO.getViewYn())) {
			WriterUtil.flushJsPostRedirect(response, "설문조사 페이지 이동", "진행중인 설문의 질문을 수정할 수 없습니다.", "/sys/pollMng/question/view.do?mId=" + mId, paramMap);
			return null;
		} else if (searchVO.getUserCnt() > 0) {
			WriterUtil.flushJsPostRedirect(response, "설문조사 페이지 이동", "이미 참여자가 있는 설문의 질문을 수정할 수 없습니다.", "/sys/pollMng/question/view.do?mId=" + mId, paramMap);
			return null;
		} else {
			return "/sys/basic/poll/question/update";
		}
	}


	@ResponseBody
	@RequestMapping("/sys/pollMng/question/updateProc.do")
	public byte[] questionUpdateProc(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/json; charset=UTF-8");
		JSONObject json = new JSONObject();

		boolean ret = questionMngService.setUpdateQuestion(request, searchVO);

		if (ret) {
			json.put("flag", "success");
			json.put("msg", "수정되었습니다.");
			return json.toString().getBytes("UTF-8");
		} else {
			json.put("flag", "fail");
			json.put("msg", "저장 처리 중 오류가 발생하였습니다.");
			return json.toString().getBytes("UTF-8");
		}

	}


	/**
	 * 설문조사 기간 체크
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private boolean blockOverDate(String startDate, String endDate) {
		Calendar cal = Calendar.getInstance();
		int today = Integer.parseInt(cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1 < 10 ? "0" + (cal.get(Calendar.MONTH) + 1) : (cal.get(Calendar.MONTH) + 1)) + "" + (cal.get(Calendar.DATE) < 10 ? "0" : "") + cal.get(Calendar.DATE));
		if (today < Integer.parseInt(startDate.replace("-", "")) || today > Integer.parseInt(endDate.replace("-", ""))) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}