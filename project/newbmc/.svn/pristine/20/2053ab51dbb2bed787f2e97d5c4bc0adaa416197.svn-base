package egovframework.portal.sys.bbs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.service.BbsMngService;
import egovframework.portal.sys.bbs.service.CommentMngService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.bbs.vo.CommentMngVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;

/**
 * 게시글 코멘트 관리 CONTROLLER
 *
 * @author J.Ryeon Lee
 * @since 2016.03.15
 */
@Controller
public class CommentMngController {

	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected BbsMngService bbsMngService;
	@Autowired
	protected CommentMngService commentMngService;

	@ResponseBody
	@RequestMapping(value = "/sys/{siteCode}/bbs/bbsMng/comment/writeProc.do", method = RequestMethod.POST)
	public byte[] writeProc(@ModelAttribute CommentMngVO commentMngVO, @PathVariable String siteCode, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(commentMngVO.getPtIdx()));
		BbsMngVO bbsMng = config != null ? bbsMngService.getBbsMngView(new BbsMngVO(commentMngVO.getbIdx())) : null;
		if (bbsMng == null) {
			rtn.put("errCode", 0);
		} else {
			commentMngVO.setAdminUserInfo(SessionUtil.getRemoteAddr(request), SessionUtil.getAdminInstance(request));
			commentMngService.insertComment(commentMngVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping(value = "/sys/{siteCode}/bbs/bbsMng/comment/modifyProc.do", method = RequestMethod.POST)
	public byte[] modifyProc(@ModelAttribute CommentMngVO commentMngVO, @PathVariable String siteCode, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(commentMngVO.getPtIdx()));
		BbsMngVO bbsMng = config != null ? bbsMngService.getBbsMngView(new BbsMngVO(commentMngVO.getbIdx())) : null;
		CommentMngVO origin = bbsMng != null ? commentMngService.getComment(commentMngVO) : null;
		if (origin == null) {
			rtn.put("errCode", 0);
		} else if (StringUtil.isNotBlank(origin.getPrivatecode())) { // 관리자에 의한 사용자 코멘트 수정 방지. 관리자는 오직 등록/삭제/복구만 가능
			rtn.put("errCode", 1);
		} else {
			commentMngVO.setAdminUserInfo(SessionUtil.getRemoteAddr(request), SessionUtil.getAdminInstance(request));
			commentMngService.updateComment(commentMngVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping(value = "/sys/{siteCode}/bbs/bbsMng/comment/switchStateProc.do", method = RequestMethod.POST)
	public byte[] switchStateProc(@ModelAttribute CommentMngVO commentMngVO, @PathVariable String siteCode, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		CommentMngVO origin = commentMngService.getComment(commentMngVO);
		if (origin == null) {
			rtn.put("errCode", 0);
		} else if (StringUtil.equalN(commentMngVO.getIsDel())) { // 복구
			origin.setAdminUserInfo(SessionUtil.getRemoteAddr(request), SessionUtil.getAdminInstance(request));
			commentMngService.restoreComment(origin);
			rtn.put("success", Boolean.TRUE);
		} else if (StringUtil.equalY(commentMngVO.getIsDel())) { // 삭제
			origin.setAdminUserInfo(SessionUtil.getRemoteAddr(request), SessionUtil.getAdminInstance(request));
			commentMngService.deleteComment(origin);
			rtn.put("success", Boolean.TRUE);
		} else {
			rtn.put("errCode", 1);
		}

		return rtn.toString().getBytes("UTF-8");
	}

}
