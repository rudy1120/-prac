package egovframework.portal.sys.privacy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.sys.MenuMng.mapper.MenuMngMapper;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.privacy.PrmType;
import egovframework.portal.sys.privacy.service.PrivacyPrmService;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 개인정보취급메뉴 설정 관리 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 19.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 19.
 */
@Controller
public class PrivacyPrmController {

	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected PrivacyPrmService prmService;
	@Resource(name = "menuMngMapper")
	private MenuMngMapper menuMngMapper;

	/** 설정 목록 */
	@RequestMapping("/sys/privacy/prm/list.do")
	public String list(@ModelAttribute("searchVO") PrivacyPrmVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* PAGINATION SETTING */

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = prmService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", prmService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("prmTypeList", PrmType.values());
		model.addAttribute("siteList", menuMngMapper.getMenusMngListAll(null));

		return "/sys/privacy/prm/list/";
	}

	/** 설정 등록 페이지 */
	@RequestMapping("/sys/privacy/prm/write.do")
	public String write(@ModelAttribute("searchVO") PrivacyPrmVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("prmTypeList", PrmType.values());
		model.addAttribute("siteList", menuMngMapper.getMenusMngListAll(null));

		return "/sys/privacy/prm/write/";
	}

	/** 설정 등록 처리 */
	@RequestMapping(value = "/sys/privacy/prm/writeProc.do", method = RequestMethod.POST)
	public String writeProc(@ModelAttribute("searchVO") PrivacyPrmVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String newIdx = prmService.insert(searchVO, SessionUtil.getAdminInstance(request), SessionUtil.getRemoteAddr(request));
		if (StringUtil.isNotBlank(newIdx)) {
			model.clear();
			return "redirect:/sys/privacy/prm/list.do?mId=" + mId;
		}

		return write(searchVO, mId, model, request, response);
	}

	/** 설정 수정 페이지 */
	@RequestMapping("/sys/privacy/prm/modify.do")
	public String modify(@ModelAttribute("searchVO") PrivacyPrmVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrivacyPrmVO config = prmService.getEntity(searchVO);
		if (config != null) {
			BeanUtil.copy(config, searchVO);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("prmTypeList", PrmType.values());
			model.addAttribute("siteList", menuMngMapper.getMenusMngListAll(null));
			return "/sys/privacy/prm/write/";
		}

		model.clear();
		return "redirect:/sys/privacy/prm/list.do?mId=" + mId;
	}

	/** 설정 수정 처리 */
	@RequestMapping(value = "/sys/privacy/prm/modifyProc.do", method = RequestMethod.POST)
	public String modifyProc(@ModelAttribute("searchVO") PrivacyPrmVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String updateIdx = prmService.update(searchVO, SessionUtil.getAdminInstance(request), SessionUtil.getRemoteAddr(request));
		if (StringUtil.isNotBlank(updateIdx)) {
			model.clear();
			return "redirect:/sys/privacy/prm/list.do?mId=" + mId;
		}

		model.addAttribute("error", Boolean.TRUE);
		return write(searchVO, mId, model, request, response);
	}

	/** 설정 삭제 처리 */
	@ResponseBody
	@RequestMapping(value = "/sys/privacy/prm/deleteProc.do", method = RequestMethod.POST)
	public String deleteProc(@ModelAttribute("searchVO") PrivacyPrmVO searchVO, HttpServletRequest request) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		PrivacyPrmVO entity = prmService.getEntity(searchVO);
		BbsConfigVO bbsConfig = entity != null && StringUtil.isNotBlank(entity.getPtIdx()) // 게시판 개인정보 관리 설정인 경우 게시판 정보 FETCH
			? bbsConfigService.getBbsConfigView(new BbsConfigVO(entity.getPtIdx())) : null;
		if (bbsConfig == null || "Y".equals(bbsConfig.getIsDel())) { // 게시판 개인정보 관리 설정이 아니거나 게시판이 삭제된 경우에만 관련 설정을 삭제 처리
			String deleteIdx = prmService.delete(searchVO, SessionUtil.getAdminInstance(request), SessionUtil.getRemoteAddr(request));
			if (StringUtil.isNotBlank(deleteIdx)) {
				rtn.put("success", Boolean.TRUE);
			}
		}

		return rtn.toString();
	}
}