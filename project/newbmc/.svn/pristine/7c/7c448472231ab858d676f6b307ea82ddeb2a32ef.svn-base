package egovframework.portal.unit.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.common.SessionKey;
import egovframework.portal.unit.common.UserType;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.unit.common.service.MenuCommentService;
import egovframework.portal.unit.common.vo.MenuCommentVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;

/**
 * 메뉴별 코멘트 기능 CONTORLLER
 *
 * @author J.Ryeon Lee
 * @since 2016.06.23
 */
@Controller
public class MenuCommentController {

	@Autowired
	protected MenuCommentService menuCommentService;
	@Autowired
	protected MenuMngService menuService;

	private boolean noAuthentication(Authentication authentication, HttpServletRequest request) {
		return authentication == null &&
			StringUtil.isBlank(SessionUtil.getSessionAttrAsString(request, SessionKey.USER_TYPE.getKey()));
	}

	@ResponseBody
	@RequestMapping(value = { "/{sitecode}/menu/comment/writeProc.do", "/**/{sitecode}/menu/comment/writeProc.do" }, method = RequestMethod.POST)
	public byte[] writeProc(Authentication authentication, @ModelAttribute MenuCommentVO menuCommentVO, @PathVariable String sitecode, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		menuCommentVO.setSitecode(sitecode);
		menuCommentVO.setUserInfo(request, authentication);

		MenuVO menu = menuService.getMenuInfo(menuCommentVO.getmId(), menuCommentVO.getSitecode());
		MenuCommentVO upperComment = menuCommentService.getMenuComment(new MenuCommentVO(menuCommentVO.getUpIdx()));
		if (noAuthentication(authentication, request)) {
			rtn.put("errCode", 0);
		} else if (isInvalidMenu(menu)) {
			rtn.put("errCode", 1);
		} else if (upperComment != null && StringUtil.isNotBlank(upperComment.getUpIdx())) {
			rtn.put("errCode", 3);
		} else {
			menuCommentService.insertMenuComment(menuCommentVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping(value = { "/{sitecode}/menu/comment/modifyProc.do", "/**/{sitecode}/menu/comment/modifyProc.do" }, method = RequestMethod.POST)
	public byte[] modifyProc(Authentication authentication, @ModelAttribute MenuCommentVO menuCommentVO, @PathVariable String sitecode, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		menuCommentVO.setSitecode(sitecode);
		menuCommentVO.setUserInfo(request, authentication);

		MenuVO menu = menuService.getMenuInfo(menuCommentVO.getmId(), menuCommentVO.getSitecode());
		MenuCommentVO comment = isInvalidMenu(menu) ? null : menuCommentService.getMenuComment(menuCommentVO);
		if (noAuthentication(authentication, request)) {
			rtn.put("errCode", 0);
		} else if (comment == null) {
			rtn.put("errCode", 1);
		} else if (isNotWriter(request, authentication, comment)) { // 작성자 본인 여부 확인
			rtn.put("errCode", 2);
		} else {
			menuCommentService.updateMenuComment(menuCommentVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping(value = { "/{sitecode}/menu/comment/deleteProc.do", "/**/{sitecode}/menu/comment/deleteProc.do" }, method = RequestMethod.POST)
	public byte[] deleteProc(Authentication authentication, @ModelAttribute MenuCommentVO menuCommentVO, @PathVariable String sitecode, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		menuCommentVO.setSitecode(sitecode);
		menuCommentVO.setUserInfo(request, authentication);

		MenuVO menu = menuService.getMenuInfo(menuCommentVO.getmId(), menuCommentVO.getSitecode());
		MenuCommentVO comment = isInvalidMenu(menu) ? null : menuCommentService.getMenuComment(menuCommentVO);
		if (noAuthentication(authentication, request)) {
			rtn.put("errCode", 0);
		} else if (comment == null) {
			rtn.put("errCode", 1);
		} else if (isNotWriter(request, authentication, comment)) { // 작성자 본인 여부 확인
			rtn.put("errCode", 2);
		} else {
			menuCommentService.deleteMenuComment(comment);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	private boolean isNotWriter(HttpServletRequest request, Authentication authentication, MenuCommentVO origin) {
		return !isWriter(request, authentication, origin);
	}

	private boolean isWriter(HttpServletRequest request, Authentication authentication, MenuCommentVO origin) {
		UserType _userType = UserType.toType(SessionUtil.getSessionAttrAsString(request, SessionKey.USER_TYPE.getKey()));
		switch (_userType) {
			case FACEBOOK:
				return origin.getUserType().equals(_userType.getCode()) &&
					StringUtil.isNotBlank(origin.getUserId()) &&
					origin.getUserId().equals(SessionUtil.getSessionAttrAsString(request, SessionKey.FB_USER_ID.getKey()));
			case TWITTER:
				return origin.getUserType().equals(_userType.getCode()) &&
					StringUtil.isNotBlank(origin.getUserId()) &&
					origin.getUserId().equals(SessionUtil.getSessionAttrAsString(request, SessionKey.TW_USER_ID.getKey()));

			default:
				UserVO user = UserUtil.getInstance();
				return origin.getUserType().equals(user.getUserType()) &&
					StringUtil.isNotBlank(origin.getPrivatecode()) &&
					origin.getPrivatecode().equals(user.getPrivatecode());
		}
	}

	private boolean isInvalidMenu(MenuVO menu) {
		return !isValidMenu(menu);
	}

	private boolean isValidMenu(MenuVO menu) {
		return menu != null && "Y".equals(menu.getIsSnsComment());
	}

}
