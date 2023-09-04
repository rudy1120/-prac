package egovframework.portal.sys.bbs.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.javascript.rhino.head.json.JsonParser.ParseException;
import com.ibm.icu.util.Calendar;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.bbs.BbsType;
import egovframework.portal.common.CategoryGubunType;
import egovframework.portal.common.Constant;
import egovframework.portal.common.service.CommonCodeService;
import egovframework.portal.dept.service.DeptService;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.service.BbsHelperService;
import egovframework.portal.sys.bbs.service.BbsMngService;
import egovframework.portal.sys.bbs.service.CommentMngService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.sms.service.SmsService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.unit.bmc.apply.service.ApplySmsService;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TokenUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 관리자 - 게시판 Controller 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.10.01		김혜민				최초 생성
 * 2015.07.02		J.Ryeon Lee			미사용 변수 코멘트, javadoc 수정
 * 2015.07.02		J.Ryeon Lee			공지글 및 삭제된 게시물의 경우 답글을 달 수 없도록 수정
 * 2015.07.29		J.Ryeon Lee			공지글 및 삭제된 게시물의 경우 답글을 달 수 없도록 수정
 * 2015.08.06		J.Ryeon Lee			게시판 금칙어 통신 메소드 추가
 * 2016.03.17		J.Ryeon Lee			외부관리자에서도 게시판을 관리할 수 있도록 사이트 코드 도입
 * 2017.05.16		J.Ryeon Lee			코드 리팩토링
 * </pre>
 *
 * @author 김혜민
 * @since 2014.10.01
 */
@Controller("BbsMngController")
public class BbsMngController {

	@Autowired
	protected CommonCodeService commonCodeService;
	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected BbsMngService bbsMngService;
	@Autowired
	protected DeptService deptService;
	@Autowired
	protected SiteMngService siteMngService;
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	@Autowired
	protected CommentMngService commentMngService;
	@Autowired
	protected BbsHelperService bbsMngHelperService;
	
	/** 문자서비스 */
	@Autowired
	protected SmsService smsService;
	
	/** 분양,임대 지역, 사업지구, 주택 유형 데이터 */
	@Autowired
	protected ApplySmsService applySmsService;

	private final Logger LOGGER = LogManager.getLogger();

	/** 게시글 목록 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/list.do")
	public String list(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		if (bbsConfigVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
			return null;
		} else {
			searchVO.setConfig(bbsConfigVO); // 게시판 설정
		}

		if (bbsMngHelperService.isAccessableIP(bbsConfigVO)) { // 접근 IP 체크
			int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
			searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
			searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
			int totalCnt = bbsMngService.getBbsMngCnt(searchVO);

			model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
			model.addAttribute("page", page);
			model.addAttribute("bbsConfigVO", bbsConfigVO);
			model.addAttribute("noti", bbsMngService.getBbsMngNoticeList(searchVO));
			model.addAttribute("result", bbsMngService.getBbsMngList(searchVO));
			model.addAttribute("resultCnt", totalCnt);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
			model.addAttribute("siteCode", siteCode);
			addCategoryAttribute(model, bbsConfigVO);

			return "/sys/bbs/bbsMng/list";
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "접근할 수 없는 IP입니다.");
		return null;
	}

	/** 게시글 상세 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/view.do")
	public String view(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		if (bbsConfigVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
			return null;
		} else {
			searchVO.setConfig(bbsConfigVO);
		}

		BbsMngVO bbsMngView = bbsMngService.getBbsMngView(searchVO);
		if (bbsMngView != null) {
			BbsType type = BbsType.toType(bbsConfigVO.getPtType());
			if (BbsType.MOVIE == type) { // 동영상 게시판(영상 정보 세팅)
				FileVO fileVO = new FileVO();
				fileVO.setAtchFileId(bbsMngView.getAttachId());
				List<FileVO> result = fileService.selectFileInfs(fileVO);
				fileVO = result.size() > 0 ? result.get(result.size() - 1) : null;
				model.addAttribute("movieData", fileVO);
			} else if (BbsType.MINWON == type) { // 민원형 게시판(답글 세팅)
				List<BbsMngVO> replyList = bbsMngService.getBbsMngReplyList(searchVO);
				model.addAttribute("replyList", replyList);
				model.addAttribute("replyListSize", replyList.size());
			}

			bbsMngService.setBbsMngViewCount(searchVO); // 조회수 update

			model.addAttribute("siteCode", siteCode);
			model.addAttribute("bbsConfigVO", bbsConfigVO);
			model.addAttribute("bbsMngView", bbsMngView);
			model.addAttribute("movable", bbsMngHelperService.isMovableBbsMng(bbsMngView, bbsConfigVO));

			if ("Y".equals(bbsConfigVO.getPtOutFields().substring(0, 1))) { // 하단 목록 노출
				int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
				searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
				searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
				int totalCnt = bbsMngService.getBbsMngCnt(searchVO);

				model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
				model.addAttribute("page", page);
				model.addAttribute("noti", bbsMngService.getBbsMngNoticeList(searchVO));
				model.addAttribute("result", bbsMngService.getBbsMngList(searchVO));
				model.addAttribute("resultCnt", totalCnt);
				model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
				addCategoryAttribute(model, bbsConfigVO);
			}
			if (StringUtil.equalY(bbsConfigVO.getPtCommentYn())) { // COMMENT
				addCommentAttribute(request, model, bbsMngView);
			}

			return "/sys/bbs/bbsMng/view";
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "잘못된 접근입니다.");
		return null;
	}

	/** 게시글 등록 화면 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/write.do")
	public String write(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		searchVO.setAdminInfo(SessionUtil.getAdminInstance(request));
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		if (bbsConfigVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
			return null;
		} else {
			searchVO.setConfig(bbsConfigVO);
		}
		
		//공급용도 데이터
		List<ApplySmsVO> usesList = applySmsService.getPurposerList();
		model.addAttribute("usesList", usesList);
		
		//주택유형 데이터
		List<ApplySmsVO> housingList = applySmsService.getHousingTypeList();
		model.addAttribute("housingList", housingList);
		
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("bbsConfigVO", bbsConfigVO);
		model.addAttribute("bbsMngVO", searchVO);
		model.addAttribute("siteCode", siteCode);
		addCategoryAttribute(model, bbsConfigVO);
		TokenUtil.saveToken(request);

		return "/sys/bbs/bbsMng/write";
	}

	/** 게시글 등록 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, final MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
		if (!searchVO.getbTermStime().trim().equals("")) {
			searchVO.setbTermSdate(searchVO.getbTermSdate() + " " + searchVO.getbTermStime());
		}
		if (!searchVO.getbTermEtime().trim().equals("")) {
			searchVO.setbTermEdate(searchVO.getbTermEdate() + " " + searchVO.getbTermEtime());
		}
		try {
			if (TokenUtil.isTokenValid(multiRequest)) {
				BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
				searchVO.setConfig(bbsConfigService.getEntityByPk(searchVO.getPtIdx()));
				
				searchVO.setbTitle(StringEscapeUtils.unescapeHtml3(searchVO.getbTitle()));
				searchVO.setSmsMsg(StringEscapeUtils.unescapeHtml3(searchVO.getSmsMsg()));
				String newBIdx = bbsMngService.insertBbsMng(multiRequest, searchVO, bbsConfigVO); // bbs insert
				
				//문자서비스 실행
				if(searchVO.getSmsYN().equals("Y")||searchVO.getSmsYN()=="Y") {
					searchVO.setbIdx(newBIdx);
					smsService.insertSmsQueue(searchVO);
				}
				if (StringUtil.isNotBlank(newBIdx)) {
					TokenUtil.resetToken(multiRequest);
					return "redirect:/sys/" + siteCode + "/bbs/bbsMng/list.do?mId=" + mId + "&ptIdx=" + searchVO.getPtIdx();
				}
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "중복된 요청입니다.");
				return null;
			}
		} catch (Exception e) {
			LOGGER.error(">> sys.bbs.writeProc", e);
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류 발생했습니다.");
		return null;
	}

	/** 게시글 수정 화면 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/modifyView.do")
	public String modify(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		BbsMngVO bbsMngView = bbsConfigVO != null ? bbsMngService.getBbsMngView(searchVO) : null;
		if (bbsMngView == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시글이 존재하지 않습니다.");
			return null;
		} else if (bbsMngView.isNotModitable(SessionUtil.getAdminInstance(request), bbsConfigVO)) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "본인이 작성한 글만 수정/삭제할 수 있습니다.");
			return null;
		} else {
			if (bbsMngView.getbTermSdate().indexOf(" ") > 0) {
				bbsMngView.setbTermStime(bbsMngView.getbTermSdate().split(" ")[1]);
				bbsMngView.setbTermSdate(bbsMngView.getbTermSdate().split(" ")[0]);
			}
			if (bbsMngView.getbTermEdate().indexOf(" ") > 0) {
				bbsMngView.setbTermEtime(bbsMngView.getbTermEdate().split(" ")[1]);
				bbsMngView.setbTermEdate(bbsMngView.getbTermEdate().split(" ")[0]);
			}
			bbsMngView.setConfig(bbsConfigVO);
		}

		//공급용도 데이터
		List<ApplySmsVO> usesList = applySmsService.getPurposerList();
		model.addAttribute("usesList", usesList);
		
		//주택유형 데이터
		List<ApplySmsVO> housingList = applySmsService.getHousingTypeList();
		model.addAttribute("housingList", housingList);
		
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("siteCode", siteCode);
		model.addAttribute("bbsConfigVO", bbsConfigVO);
		model.addAttribute("bbsMngVO", bbsMngView);
		addCategoryAttribute(model, bbsConfigVO);

		return "/sys/bbs/bbsMng/write";
	}

	/** 게시글 수정 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, final MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
		if (!searchVO.getbTermStime().trim().equals("")) {
			searchVO.setbTermSdate(searchVO.getbTermSdate() + " " + searchVO.getbTermStime());
		}
		if (!searchVO.getbTermEtime().trim().equals("")) {
			searchVO.setbTermEdate(searchVO.getbTermEdate() + " " + searchVO.getbTermEtime());
		}
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		BbsMngVO bbsMngVO = bbsConfigVO == null ? null : bbsMngService.getBbsMngView(searchVO);
		if (bbsMngVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
			return null;
		} else if (bbsMngVO.isNotModitable(SessionUtil.getAdminInstance(multiRequest), bbsConfigVO)) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "본인이 작성한 글만 수정/삭제할 수 있습니다.");
			return null;
		} else {
			bbsMngVO.setConfig(bbsConfigVO);
			searchVO.setConfig(bbsConfigVO);
		}

		try {
			BbsType type = BbsType.toType(bbsConfigVO.getPtType());
			searchVO.setbTitle(StringEscapeUtils.unescapeHtml3(searchVO.getbTitle()));
			searchVO.setSmsMsg(StringEscapeUtils.unescapeHtml3(searchVO.getSmsMsg()));
			String bIdx = bbsMngService.updateBbsMng(multiRequest, searchVO, bbsConfigVO); // update
			
			//문자서비스 수정 작업
			searchVO.setbIdx(bIdx);
			smsService.updateCk(searchVO);
			
			if (StringUtil.isNotBlank(bIdx)) {
				if (type != BbsType.FAQ) {
					return "redirect:/sys/" + siteCode + "/bbs/bbsMng/view.do?ptIdx=" + searchVO.getPtIdx() + "&mId=" + mId + "&bIdx=" + bIdx;
				} else {
					return "redirect:/sys/" + siteCode + "/bbs/bbsMng/list.do?ptIdx=" + searchVO.getPtIdx() + "&mId=" + mId;
				}
			}
		} catch (Exception e) {
			LOGGER.error(">> sys.bbs.modifyProc", e);
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
		return null;
	}

	/** 게시글 삭제 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BbsMngVO deleteVO = bbsMngService.getBbsMngView(searchVO);
			BbsConfigVO bbsConfigVO = deleteVO != null ? bbsConfigService.getEntityByPk(searchVO.getPtIdx()) : null;
			if (deleteVO != null) {
				bbsMngService.deleteBbsMng(request, deleteVO, bbsConfigVO);
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("ptIdx", bbsConfigVO.getPtIdx());

				//문자서비스 삭제 작업
				searchVO.setbIdx(bbsConfigVO.getPtIdx());
				smsService.delCk(deleteVO);
				
				WriterUtil.flushJsPostRedirect(response, "게시글 삭제", "삭제되었습니다.", "/sys/" + siteCode + "/bbs/bbsMng/list.do?mId=" + mId, paramMap);
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
			}

			return null;
		} catch (Exception e) {
			LOGGER.error(">> sys.bbs.deleteProc", e);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
			return null;
		}
	}

	/** 게시글 복원 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/reDeleteProc.do")
	public String reDeleteProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
			bbsMngService.reDeleteBbsMng(request, searchVO, bbsConfigVO);

			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("ptIdx", bbsConfigVO.getPtIdx());
			paramMap.put("bIdx", searchVO.getbIdx());

			WriterUtil.flushJsPostRedirect(response, "게시글 복원", "복원되었습니다.", "/sys/" + siteCode + "/bbs/bbsMng/view.do?mId=" + mId, paramMap);
			return null;
		} catch (Exception e) {
			LOGGER.error(">> sys.bbs.deleteProc", e);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
			return null;
		}
	}

	/** 답변/답글 작성 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/replyWrite.do")
	public String replyWrite(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("siteCode", siteCode);

		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
		searchVO.setAdminInfo(admin);

		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		BbsMngVO bbsMngView = bbsConfigVO != null ? bbsMngService.getBbsMngView(searchVO) : null;
		if (bbsMngView != null && bbsConfigVO.isReplable(Boolean.TRUE) && ( //
			!BbsType.MINWON.getCode().equals(bbsConfigVO.getPtType()) || // 민원형이 아니거나
			bbsMngView.getReplyCnt() == 0 // 미답변 상태의 민원형이거나
		)) {
			searchVO.setbSame(bbsMngView.getbSame());
			searchVO.setbLevel(bbsMngView.getbLevel());

			model.addAttribute("bbsConfigVO", bbsConfigVO);
			model.addAttribute("bbsMngVO", searchVO);

			return "/sys/bbs/bbsMng/replyWrite";
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "잘못된 접근입니다.");
		return null;
	}

	/** 답변/답글 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/replyProc.do")
	public void replyProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, final MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		if (bbsConfigVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
		} else {
			String newBIdx = bbsMngService.replyInsertBbsMng(multiRequest, searchVO, bbsConfigVO);
			if (StringUtil.isNotEmpty(newBIdx)) {
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("ptIdx", searchVO.getPtIdx());
				paramMap.put( //
					"bIdx", //
					bbsConfigVO.typeIs(BbsType.MINWON) ? searchVO.getbSame() : newBIdx // 민원형은 민원 원본글로 이동
					);
				WriterUtil.flushJsPostRedirect(response, "등록 완료", null, "/sys/" + siteCode + "/bbs/bbsMng/view.do?mId=" + mId, paramMap);
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
			}
		}
	}

	/** 답변/답글 수정 화면 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/replyModifyView.do")
	public String replyModify(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("siteCode", siteCode);

		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		BbsMngVO bbsMngView = bbsConfigVO != null ? bbsMngService.getBbsMngView(searchVO) : null;
		if (bbsMngView != null) {
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("bbsConfigVO", bbsConfigVO);
			model.addAttribute("bbsMngVO", bbsMngView);
		}

		return "/sys/bbs/bbsMng/replyWrite";
	}

	/** 답변/답글 수정 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/replyModifyProc.do")
	public void replyModifyProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, final MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		if (bbsConfigVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
		} else {
			String updatedBIdx = bbsMngService.updateBbsMng(multiRequest, searchVO, bbsConfigVO);
			if (StringUtil.isNotEmpty(updatedBIdx)) {
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("ptIdx", searchVO.getPtIdx());
				paramMap.put( //
					"bIdx", //
					bbsConfigVO.typeIs(BbsType.MINWON) ? searchVO.getbSame() : updatedBIdx // 민원형은 민원 원본글로 이동
					);
				WriterUtil.flushJsPostRedirect(response, "수정 완료", null, "/sys/" + siteCode + "/bbs/bbsMng/view.do?mId=" + mId, paramMap);
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
			}
		}
	}

	/** 게시물 이동 전처리 (이동 가능 유무 검증) */
	@ResponseBody
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/moveCheck.do")
	public String moveCheck(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode) throws Exception {
		JSONObject rtn = new JSONObject();

		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		BbsMngVO bbsMngView = bbsConfigVO != null ? bbsMngService.getBbsMngView(searchVO) : null;
		if (bbsMngView != null && bbsMngHelperService.isMovableBbsMng(bbsMngView, bbsConfigVO)) {
			rtn.put("replyCount", bbsMngService.getReplyCnt(searchVO)); // 답변글 개수
		}

		return rtn.toString();
	}

	/** 게시물 이동 게시판 선택 화면 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/boardMoveForm.do")
	public String boardMoveForm(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, final HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("siteCode", siteCode);

		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		BbsMngVO preBoard = bbsConfigVO != null ? bbsMngService.getBbsMngView(searchVO) : null;
		if (preBoard != null) {
			List<BbsConfigVO> configBoardList = bbsConfigService.configBoardList(bbsConfigVO);
			model.addAttribute("configBoardList", configBoardList);
			model.addAttribute("preBoard", preBoard);
			model.addAttribute("bbsMngVO", searchVO);
		}

		return "/sys/bbs/bbsMng/moveWrite";
	}

	/** 게시글 이동 처리 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/moveWriteProc.do")
	public void moveWriteProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, final HttpServletRequest request, HttpServletResponse response) throws Exception {
		BbsMngVO entity = bbsMngService.getBbsMngView(new BbsMngVO(searchVO.getbIdx()));
		if (entity != null) {
			bbsMngService.moveBoardInsert(entity, SessionUtil.getAdminInstance(request), searchVO.getAftAdIdx());

			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("ptIdx", searchVO.getAftAdIdx());
			WriterUtil.flushJsPostRedirect( //
				response, "게시글 이동 완료", "처리되었습니다. 해당 게시판으로 이동합니다.", //
				"/sys/" + siteCode + "/bbs/bbsMng/list.do?mId=" + mId, //
				paramMap //
			);
		} else {
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
		}
	}

	/** 금칙어 목록 */
	@ResponseBody
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/forbiddenWordList.do")
	public byte[] getForbiddenWordList(@PathVariable String siteCode, HttpServletResponse response, @RequestParam String ptIdx) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		JSONObject json = new JSONObject().put("success", Boolean.FALSE);

		if (StringUtil.isNotBlank(ptIdx)) {
			BbsConfigVO config = bbsConfigService.getEntityByPk(ptIdx);
			if (config != null) {
				json.put("ptCheckWord", config.getPtCheckWord());
			}
		}

		return json.toString().getBytes("UTF-8");
	}

	/**
	 * 게시글 일괄 삭제 처리
	 *
	 * @author 상천규
	 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/bundleDeleteProc.do")
	public void bundleDeleteProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		if (bbsConfigVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
		} else {
			bbsMngService.bundleDeleteBbsMng(request, searchVO, bbsConfigVO);

			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("ptIdx", searchVO.getPtIdx());
			paramMap.put("searchDept", searchVO.getSearchDept());
			paramMap.put("searchCategory", searchVO.getSearchCategory());
			paramMap.put("searchDelete", searchVO.getSearchDelete());
			paramMap.put("searchType", searchVO.getSearchType());
			paramMap.put("searchTxt", searchVO.getSearchTxt());
			WriterUtil.flushJsPostRedirect(response, "게시판 관리", null, "/sys/" + siteCode + "/bbs/bbsMng/list.do?mId=" + mId, paramMap);
		}
	}

	/**
	 * 게시글 일괄 복원 처리
	 *
	 * @author 상천규
	 */
	@RequestMapping("/sys/{siteCode}/bbs/bbsMng/bundleRedeleteProc.do")
	public void bundleRedeleteProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCode, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BbsConfigVO bbsConfigVO = bbsConfigService.getEntityByPk(searchVO.getPtIdx());
		if (bbsConfigVO == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시판이 삭제되었거나 존재하지 않습니다.");
		} else {
			bbsMngService.bundleRedeleteBbsMng(request, searchVO, bbsConfigVO);

			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("ptIdx", searchVO.getPtIdx());
			paramMap.put("searchDept", searchVO.getSearchDept());
			paramMap.put("searchCategory", searchVO.getSearchCategory());
			paramMap.put("searchDelete", searchVO.getSearchDelete());
			paramMap.put("searchType", searchVO.getSearchType());
			paramMap.put("searchTxt", searchVO.getSearchTxt());
			WriterUtil.flushJsPostRedirect(response, "게시판 관리", null, "/sys/" + siteCode + "/bbs/bbsMng/list.do?mId=" + mId, paramMap);
		}
	}

	private void addCategoryAttribute(ModelMap model, BbsConfigVO bbsConfigVO) {
		if ("Y".equals(bbsConfigVO.getPtCategoryYn())) { // 20151102 J.Ryeon Lee 카테고리 부서 혼용 요청 대응
			if (CategoryGubunType.useCustomCategory(bbsConfigVO.getPtCategoryGubun())) {
				model.addAttribute("categoryList", bbsMngHelperService.getCategoryList(bbsConfigVO));
			}
			if (CategoryGubunType.useDeptCategory(bbsConfigVO.getPtCategoryGubun())) {
				model.addAttribute("deptList", deptService.getDeptCategoryList());
			}
		}
	}

	private void addCommentAttribute(HttpServletRequest request, ModelMap model, BbsMngVO bbsMngView) {
		int commentPage = ServletRequestUtils.getIntParameter(request, "commentPage", 1);
		int total = commentMngService.getCommentCnt(bbsMngView.getbIdx());

		model.addAttribute("commentPage", commentPage);
		model.addAttribute("commentPaginationInfo", PaginationInfoUtil.calPaginationInfo(commentPage, Constant.LIST_CUTRECORD, Constant.LIST_CUTRECORD, total));
		model.addAttribute("commentCnt", total);
		model.addAttribute(
			"commentList",
			commentMngService.getCommentList(bbsMngView.getbIdx(), commentPage, Constant.LIST_CUTRECORD) //
		);
	}
	
	@RequestMapping("/sys/main/bbs/list.do")
	public String mainList(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (searchVO.getbIdx() != null || !searchVO.getbIdx().equals("")) {
			bbsMngService.orderUpdate(searchVO);
		}
		
		searchVO.setbIdx("");
		List<BbsMngVO> result = bbsMngService.getMainMgt(searchVO);
		model.addAttribute("result", result);
		model.addAttribute("searchVO", searchVO);
		return "/sys/bbs/bbsMng/mainList";
	}
	
	@ResponseBody
	@RequestMapping("/sys/main/bbs/uncheck.do")
	public String uncheckProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId) throws Exception {
		
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		String delIdx = bbsMngService.uncheck(searchVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}
	
	@RequestMapping("/sys/main/bbs/modifyOrder.do")
	public String modifyOrder(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		model.addAttribute("searchVO", searchVO);
		List<BbsMngVO> result = bbsMngService.getMainMgt(searchVO);
		model.addAttribute("result", result);
		return "/sys/bbs/bbsMng/mainList";
	}
	
}
