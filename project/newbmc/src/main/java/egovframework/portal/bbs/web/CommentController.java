package egovframework.portal.bbs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.bbs.service.CommentService;
import egovframework.portal.bbs.vo.CommentVO;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.util.StringUtil;

/**
 * 게시글 코멘트 CONTROLLER
 *
 * @author J.Ryeon Lee
 * @since 2016.03.08
 */
@Controller
public class CommentController {

	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected BbsService bbsService;
	@Autowired
	protected CommentService commentService;

	@ResponseBody
	@RequestMapping(value = "/**/bbs/comment/writeProc.do", method = RequestMethod.POST)
	public byte[] writeProc(@ModelAttribute CommentVO commentVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(commentVO.getPtIdx()));
		BbsMngVO bbs = bbsService.getBbsView(new BbsMngVO(commentVO.getbIdx()));
		if (invalidParent(config, bbs)) {
			rtn.put("errCode", 1);
		} else {
			commentVO.setUserInfo(request);
			commentService.insertComment(commentVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping(value = "/**/bbs/comment/modifyProc.do", method = RequestMethod.POST)
	public byte[] modifyProc(@ModelAttribute CommentVO commentVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		commentVO.setUserInfo(request);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(commentVO.getPtIdx()));
		BbsMngVO bbs = bbsService.getBbsView(new BbsMngVO(commentVO.getbIdx()));
		CommentVO comment = commentService.getComment(commentVO);
		if (invalidParent(config, bbs) || comment == null) {
			rtn.put("errCode", 1);
		} else if (!commentService.isInvalidAuthority(comment, commentVO)) { // 작성자 본인 여부 확인
			rtn.put("errCode", 2);
		} else {
			commentService.updateComment(commentVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	@ResponseBody
	@RequestMapping(value = "/**/bbs/comment/deleteProc.do", method = RequestMethod.POST)
	public byte[] deleteProc(@ModelAttribute CommentVO commentVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		commentVO.setUserInfo(request);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(commentVO.getPtIdx()));
		BbsMngVO bbs = bbsService.getBbsView(new BbsMngVO(commentVO.getbIdx()));
		CommentVO comment = commentService.getComment(commentVO);
		if (invalidParent(config, bbs) || comment == null) {
			rtn.put("errCode", 1);
		} else if (!commentService.isInvalidAuthority(comment, commentVO)) { // 작성자 본인 여부 확인
			rtn.put("errCode", 2);
		} else {
			commentService.deleteComment(commentVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString().getBytes("UTF-8");
	}

	private boolean invalidParent(BbsConfigVO config, BbsMngVO bbs) {
		return config == null || // 존재하지 않는 게시판
		StringUtil.equalY(config.getIsDel()) || // 삭제된 게시판
		!StringUtil.equalY(config.getPtCommentYn()) || // 코멘트 작성/수정이 허용되지 않은 게시판
		bbs == null; // 존재하지 않는 게시글
	}

}
