package egovframework.portal.sys.unit.portal.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.unit.portal.user.service.UserMngService;
import egovframework.portal.unit.portal.user.service.UserService;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 회원 관리 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.08.17		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.08.03		J.Ryeon Lee			기능 개선 및 MyBatis 호환 처리
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.08.17
 */
@Controller
public class UserMngController {

	@Autowired
	protected UserMngService userMngService;
	@Autowired
	protected UserService userService;

	/** 회원 목록 */
	@RequestMapping("/sys/portal/user/list.do")
	public String list(@ModelAttribute("searchVO") UserVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/* PAGINATION SETTING */

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = userMngService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", userMngService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/sys/unit/portal/user/list/";
	}

	/** 회원 수정 */
	@RequestMapping("/sys/portal/user/modify.do")
	public String modify(@ModelAttribute("searchVO") UserVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {
		UserVO user = userMngService.getEntity(searchVO.getUserId());
		if (user != null) {
			BeanUtils.copyProperties(user, searchVO, Constant.IGNORE_PROPERTIES);
			model.addAttribute("searchVO", searchVO);
			return "/sys/unit/portal/user/modify/";
		}

		return null;
	}

	/** 회원 수정 proc */
	@RequestMapping(value = "/sys/portal/user/modifyProc.do", method = RequestMethod.POST)
	public String modifyProc(@ModelAttribute("searchVO") UserVO searchVO, @RequestParam String mId, HttpServletRequest request) throws Exception {
		UserVO user = userMngService.getEntity(searchVO.getUserId());
		if (user != null) {
			userMngService.update(searchVO);
			userService.insertUserLog(user.getUserId(), SessionUtil.getRemoteAddr(request), "modifited by [" + SessionUtil.getAdminId(request) + "]");
			return "redirect:/sys/sysContents.do?mId=" + mId;
		}

		return null;
	}

	/** 회원 삭제 proc */
	@ResponseBody
	@RequestMapping(value = "/sys/portal/user/deleteProc.do", method = RequestMethod.POST)
	public String deleteProc(@ModelAttribute("searchVO") UserVO searchVO, HttpServletRequest request) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		UserVO user = userMngService.getEntity(searchVO.getUserId());
		if (user != null) {
			AdminLoginVO admin = (AdminLoginVO) SessionUtil.getAdminSessionObj(request);
			user.setAdminId(admin.getAdminId());
			userMngService.delete(user);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString();
	}

}