package egovframework.portal.sys.basic.poll.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.poll.service.PollMngService;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 설문조사 관리
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 7. 19.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 19.
 */
@Controller
public class PollMngController {

	@Autowired
	private PollMngService pollMngService;
	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected SiteMngService siteMngService;

	/**
	 * 관리 목록
	 *
	 * @param searchVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/list.do")
	public String list(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(searchVO.getPage()));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(searchVO.getPage()));

		int totalCnt = pollMngService.getPollMngCnt(searchVO);
		List<PollMngVO> list = pollMngService.getPollMngList(searchVO);

		model.addAttribute("list", list);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("page", searchVO.getPage());
		model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, searchVO.getPage()));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(searchVO.getPage(), totalCnt));

		return "/sys/basic/poll/list";

	}

	/**
	 * 설문조사 등록 화면
	 *
	 * @param searchVO
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/write.do")
	public String write(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("isNew", "Y");
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
		model.addAttribute("pollList", pollMngService.getPollMngList(new PollMngVO(0, 1000)));
		return "/sys/basic/poll/update";
	}

	/**
	 * 설문조사 등록 처리
	 *
	 * @param searchVO
	 * @param result
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") @Valid PollMngVO searchVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String title = StringEscapeUtils.unescapeHtml3(searchVO.getTitle());
		searchVO.setTitle(title);
		
		if (result.hasErrors()) {
			model.addAttribute("isNew", "Y");
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("searchVO", searchVO);
			model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
			model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
			return "/sys/basic/poll/update";
		}

		boolean ret = pollMngService.setInsertPoll(request, searchVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "등록되었습니다.", "/sys/pollMng/list.do?mId=" + mId, paramMap);
			return null;
		} else {
			model.addAttribute("isNew", "Y");
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("searchVO", searchVO);
			model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
			model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
			return "/sys/basic/poll/update";
		}

	}

	/**
	 * 설문 조사 노출 여부 설정
	 *
	 * @param idx
	 * @param val
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sys/pollMng/viewChange.do")
	public byte[] viewChange(@RequestParam("idx") int idx, @RequestParam("val") String val, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		if (pollMngService.setUpdatePollViewYn(new PollMngVO(idx, val))) {
			return new JSONObject().put("flag", "success").toString().getBytes("UTF-8");
		} else {
			return new JSONObject().put("flag", "fail").toString().getBytes("UTF-8");
		}
	}

	/**
	 * 설문 조사 수정 화면
	 *
	 * @param searchVO
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/update.do")
	public String update(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO, true, "page", "searchSiteCode", "searchViewYn", "searchState", "searchTxt");
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
		return "/sys/basic/poll/update";
	}

	/**
	 * 수정 처리
	 *
	 * @param searchVO
	 * @param result
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/pollMng/updateProc.do")
	public String updateProc(@ModelAttribute("searchVO") @Valid PollMngVO searchVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("searchVO", searchVO);
			model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
			model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
			return "/sys/basic/poll/update";
		}

		boolean ret = pollMngService.setPollUpdate(request, searchVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "수정되었습니다.", "/sys/pollMng/list.do?mId=" + mId, paramMap);
			return null;
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("searchVO", searchVO);
			model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
			model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
			return "/sys/basic/poll/update";
		}
	}

	/**
	 * 첨부파일 ID 삭제처리
	 *
	 * @param attachId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sys/pollMng/attachDel.do")
	public byte[] attachDel(@RequestParam("attachId") String attachId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		if (pollMngService.setAttachFileNull(attachId)) {
			return new JSONObject().put("flag", "success").toString().getBytes("UTF-8");
		} else {
			return new JSONObject().put("flag", "fail").toString().getBytes("UTF-8");
		}
	}
}