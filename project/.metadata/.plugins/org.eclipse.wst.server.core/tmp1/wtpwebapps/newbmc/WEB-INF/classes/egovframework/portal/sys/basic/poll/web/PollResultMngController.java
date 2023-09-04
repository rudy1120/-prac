package egovframework.portal.sys.basic.poll.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.basic.poll.service.PollMngService;
import egovframework.portal.sys.basic.poll.service.QuestionMngService;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.Tuple;

/**
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 9. 28.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 9. 28.
 */
@Controller
public class PollResultMngController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected SiteMngService siteMngService;
	@Autowired
	private PollMngService pollMngService;
	@Autowired
	protected QuestionMngService questionMngService;

	/**
	 * 설문조사 결과
	 *
	 * @param searchVO
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/result/view.do")
	public String resultView(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("poll", pollMngService.getPollView(searchVO));
		searchVO = questionMngService.getQuestionInfoWithResult(searchVO);
		model.addAttribute("searchVO", searchVO);
		return "/sys/basic/poll/result/view";
	}

	/**
	 * 설문조사 답변 조회
	 *
	 * @param answer
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/result/comment.do")
	public String comment(@RequestParam("target") int target, @RequestParam("type") String type, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("type", type);
		model.addAttribute("responseList", questionMngService.getResponseList(new Tuple<Integer, String>(target, type)));
		return "/sys/basic/poll/result/comment";
	}

}