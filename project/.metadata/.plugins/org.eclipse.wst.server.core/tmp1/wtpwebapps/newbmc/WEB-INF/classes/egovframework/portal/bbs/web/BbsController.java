package egovframework.portal.bbs.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringEscapeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.bbs.BbsType;
import egovframework.portal.bbs.service.BbsInvalidAccessLogger;
import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.bbs.service.CommentService;
import egovframework.portal.common.Constant;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.dept.service.DeptService;
import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.bbs.ColType;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.TokenUtil;
import egovframework.portal.util.UserUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.YSecukeyPadUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 게시판 Controller 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2014.11.06		김혜민				최초 생성 및 코딩
 * 2015.07.02		J.Ryeon Lee			미사용 변수 코멘트, 데드 코드 코멘트
 * 2015.08.18		J.Ryeon Lee			답변글 수정 페이지 이동 처리
 * 2016.07.05		J.Ryeon Lee			spring security 적용
 * 2016.08.16		J.Ryeon Lee			interceptor 처리 및 요원 권한 기능 적용
 * 2016.08.22		권태성				inRealName 요원/본인인증 회원 세션 처리
 * 2016.08.22		J.Ryeon Lee			SQL Injection 처리 및 sql 검색 조건 관련 코드 리팩토링
 * 2017.05.31		J.Ryeon Lee			menu 관련 AOP 처리
 * </pre>
 *
 * @author 개발팀 김혜민
 * @since 2014.11.06
 * @version 1.0
 * @see egovframework.portal.bbs.aop.UserBbsAccessValidator
 */
@Controller("BbsController")
public class BbsController {

	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected BbsService bbsService;
	@Autowired
	protected DeptService deptService;
	@Autowired
	protected SiteMngService siteMngService;
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;
	@Autowired
	protected CommentService commentService;
	@Autowired
	protected BbsInvalidAccessLogger bbsInvalidAccessLogger;
	@Autowired
	protected CommonService commonService;

	/** 공통 게시판 리스트 */
	@RequestMapping("/**/bbs/list.do")
	public String list(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("addTitleBBS", " 목록");
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경

		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		searchVO.setConfig(bbsConfigVO);

		if (StringUtil.isNotBlank(bbsConfigVO.getPtTopText())) { // board guide tag escape
			bbsConfigVO.setPtTopText(StringUtil.replace(bbsConfigVO.getPtTopText(), "<br>", ""));
		}

		bbsService.formatSearcher(model.get("siteGroup").toString(), model.get("siteCode").toString(), bbsConfigVO,
				searchVO); // 검색 조건 성형

		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호
		int listCutRecord = StringUtil.isNumber(bbsConfigVO.getPtPageSize())
				? Integer.parseInt(bbsConfigVO.getPtPageSize())
				: Constant.LIST_CUTRECORD;
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));

		int totalCnt = bbsService.getBbsCnt(searchVO);
		model.addAttribute("paginationInfo",
				PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("page", page);
		model.addAttribute("bbsConfigVO", bbsConfigVO);
		model.addAttribute("noti", bbsService.getBbsNoticeList(searchVO)); // 공지글
		model.addAttribute("result", bbsService.getBbsList(searchVO)); // 공지글 외 게시글
		model.addAttribute("resultCnt", totalCnt); // 공지글 외 게시글 총 카운트
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("categoryList", bbsConfigVO.getCustomCategoryList());
		model.addAttribute("deptList",
				bbsService.getBbsDeptListCa(bbsConfigVO.getPtCategoryYn(), bbsConfigVO.getPtCategoryGubun()));

		return "/" + model.get("siteCodeFull").toString() + "/bbs/list/";
	}

	/** 게시글 상세 보기 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{root}/{branch}/bbs/secured/view.do")
	public void securedView(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String root,
			@PathVariable String branch, @RequestParam String mId, Authentication auth, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		securedView(searchVO, root + "/" + branch, mId, auth, model, request, response);
	}

	/** 게시글 상세 보기 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteCodeFull}/bbs/secured/view.do")
	public void securedView(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCodeFull,
			@RequestParam String mId, Authentication auth, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("page", String.valueOf(searchVO.getPage()));
		paramMap.put("bIdx", searchVO.getbIdx());
		paramMap.put("searchTxt", searchVO.getSearchTxt());
		paramMap.put("searchType", searchVO.getSearchType());
		paramMap.put("searchCategory", searchVO.getSearchCategory());
		paramMap.put("searchDept", searchVO.getSearchDept());

		WriterUtil.flushJsPostRedirect(response, "게시글 상세", null,
				"/" + siteCodeFull + "/bbs/view.do?ptIdx=" + searchVO.getPtIdx() + "&mId=" + mId, paramMap);
	}

	/** 공통 게시글 상세 보기 */
	@RequestMapping("/**/bbs/view.do")
	public String view(@RequestParam("bIdx") String bIdx, @ModelAttribute("searchVO") BbsMngVO searchVO, Authentication auth, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserVO user = UserUtil.getInstance();
		model.addAttribute("addTitleBBS", " 내용");
		String siteCode = model.get("siteCode").toString();
		String siteGroup = model.get("siteGroup").toString();
		String siteCodeFull = model.get("siteCodeFull").toString();
		String mId = ServletRequestUtils.getStringParameter(request, "mId", "");
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); // 페이지 번호

		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		BbsType bbsType = BbsType.toType(bbsConfigVO.getPtType());
		searchVO.setPtIdx(bbsConfigVO.getPtIdx());

		/*
		 * ====================================== 게시글 접근 가능 여부 검증
		 * ======================================
		 */
		
		if(bIdx.length() != 6) {
			WriterUtil.flushJsAlertAndRedirect(response, "잘못된 경로로 접근했습니다.", "document.location.href='/" + siteCodeFull + "/main.do';");
			return null;
		}

		BbsMngVO bbsView = bbsService.getBbsView(searchVO); // MEMO 요원 게시판 열람 권한의 경우 목록과 함께 aop에서 체크 중. javadoc @see 참조
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(date);
		
		if(today.compareTo(bbsView.getbTermSdate()) < 0) {
			WriterUtil.flushJsAlertAndRedirect(response, "잘못된 경로로 접근했습니다.", "document.location.href='/" + siteCodeFull + "/main.do';");
			return null;
		} else {
			bbsView.setbContent(StringEscapeUtils.unescapeHtml3(bbsView.getbContent()));
			bbsView.setbTitle(StringEscapeUtils.unescapeHtml3(bbsView.getbTitle()));
		
			if (bbsView == null || "Y".equals(bbsView.getIsDel())) { // 존재하지 않거나 삭제된 게시글
				WriterUtil.flushJSAlertNotExistBoard(response);
				return null;
			} else if (!"0".equals(bbsView.getbLevel()) && BbsType.MINWON.getCode().equals(bbsConfigVO.getPtType())) { // 민원형
																														// 게시글
																														// 답변
																														// 직접
																														// 열람
				WriterUtil.flushJSAlertNotExistBoard(response); // 답변글은 반드시 원본글 링크를 통해서만 열람 가능
				return null;
			} else if ("N".equals(bbsView.getbPublic()) && user == null) { // 비공개 게시글 && 인증되지 않은 사용자
				authAlert(response, siteCodeFull, mId, bbsConfigVO.getPtIdx(), bbsView.getbIdx());
				return null;
			} else if (!"0".equals(bbsView.getbLevel()) && "N".equals(bbsView.getbPublic()) && user != null) { // 인증된 사용자의
																												// 비공개 답글 열람
				if (validUserAuthority(bbsConfigVO, bbsView)) {
					// 작성자 본인
				} else if (validUserAuthority(bbsConfigVO, bbsService.getBbsView(new BbsMngVO(bbsView.getbSame())))) {
					// 원본글 작성자의 열람
				} else {
					listAlert(response, siteCodeFull, mId, bbsConfigVO.getPtIdx());
					return null;
				}
			} else if (!validUserAuthority(bbsConfigVO, bbsView)) { // 상세를 볼 권한이 없는 유저의 접근
				if (user == null) { // 미인증 유저의 접근
					authAlert(response, siteCodeFull, mId, bbsConfigVO.getPtIdx(), bbsView.getbIdx());
					return null;
				} else {
					listAlert(response, siteCodeFull, mId, bbsConfigVO.getPtIdx());
					return null;
				}
			}
	
			bbsService.setBbsViewCount(searchVO); // 조회수 update
			bbsView.setbCnt(bbsView.getbCnt() + 1); // 표시 조회수 증가
	
			/*
			 * ====================================== 게시글 상세 관련값 설정
			 * ======================================
			 */
	
			if (bbsType == BbsType.MOVIE) { // 동영상 게시판의 경우 첨부파일 정보를 model에 저장
				model.addAttribute("movieDatas", fileService.selectFileInfs(new FileVO(bbsView.getAttachId())));
			} else if (bbsType == BbsType.MINWON) { // 민원형 게시판의 경우 답변글 정보를 fetch
				List<BbsMngVO> replyList = bbsService.getBbsReplyList(searchVO);
				model.addAttribute("replyList", replyList);
				model.addAttribute("replyListSize", replyList.size());
			}
	
			BeanUtil.copy(bbsView, searchVO); // DB 데이터 복사
			model.addAttribute("page", page);
			model.addAttribute("bbsConfigVO", bbsConfigVO);
			model.addAttribute("bbsView", bbsView);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("siteCode", siteCode);
			model.addAttribute("categoryList", bbsConfigVO.getCustomCategoryList());
			model.addAttribute("deptList",
					bbsService.getBbsDeptListCa(bbsConfigVO.getPtCategoryYn(), bbsConfigVO.getPtCategoryGubun()));
	
			if (StringUtil.equalY(bbsConfigVO.getPtCommentYn())) { // 20160308 J.Ryeon Lee 코멘트 지원 게시판의 경우에만
				int commentPage = ServletRequestUtils.getIntParameter(request, "commentPage", 1);
				int total = commentService.getCommentCnt(bbsView.getbIdx());
	
				model.addAttribute("commentPaginationInfo", PaginationInfoUtil.calPaginationInfo(commentPage,
						Constant.LIST_CUTRECORD, Constant.LIST_CUTRECORD, total));
				model.addAttribute("commentCnt", total);
				model.addAttribute("commentList",
						commentService.getCommentList(bbsView.getbIdx(), commentPage, Constant.LIST_CUTRECORD));
			}
	
			/*
			 * ====================================== 게시글 목록 처리
			 * ======================================
			 */
	
			if ("Y".equals(bbsConfigVO.getPtOutFields().substring(0, 1))) { // 목록 표시 설정 여부
				int viewPageSize = Integer.parseInt(bbsConfigVO.getPtPageSize()); // 목록에 표시할 게시물 건수
				searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, viewPageSize));
				searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, viewPageSize));
	
				searchVO.setConfig(bbsConfigVO); // search parameter
				bbsService.formatSearcher(siteGroup, siteCodeFull, bbsConfigVO, searchVO);
	
				int totalCnt = bbsService.getBbsCnt(searchVO);
				model.addAttribute("paginationInfo",
						PaginationInfoUtil.calPaginationInfo(page, viewPageSize, Constant.LIST_CUTPAGE, totalCnt));
				model.addAttribute("bbsConfigVO", bbsConfigVO);
				model.addAttribute("noti", bbsService.getBbsNoticeList(searchVO));
				model.addAttribute("result", bbsService.getBbsList(searchVO));
				model.addAttribute("resultCnt", totalCnt);
				model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, viewPageSize));
			}
	
			return "/" + model.get("siteCodeFull").toString() + "/bbs/view/";
		}
	}

	/** 공통 게시판 글쓰기 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/**/bbs/write.do")
	public String write(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("addTitleBBS", " 등록");
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경

		String siteCode = model.get("siteCode").toString();
		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		if (bbsConfigVO.writableForUser()) { // 사용자 글쓰기 가능 게시판 여부 검증
			UserVO user = UserUtil.getInstance();

			searchVO.setPtIdx(bbsConfigVO.getPtIdx());
			searchVO.setbWrite(user.getUserName());
			searchVO.setbPrivatecode(user.getPrivatecode());

			model.addAttribute("siteCode", siteCode);
			model.addAttribute("bbsConfigVO", bbsConfigVO);
			model.addAttribute("bbsVO", searchVO);
			model.addAttribute("categoryList", bbsConfigVO.getCustomCategoryList());
			model.addAttribute("deptList",
					bbsService.getBbsDeptListCa(bbsConfigVO.getPtCategoryYn(), bbsConfigVO.getPtCategoryGubun()));
			model.addAttribute("colTypeList", ColType.values());
			TokenUtil.saveToken(request); // 중복방지 Token 생성

			return "/" + model.get("siteCodeFull").toString() + "/bbs/write/";
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "비정상적인 접근입니다.");
		return null;
	}

	/** 공통 게시판 등록 proc */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteGroup}/{siteCode}/bbs/writeProc.do")
	public String writeProc(@PathVariable String siteGroup, @PathVariable String siteCode, @RequestParam String mId,
			ModelMap model, final MultipartHttpServletRequest multiRequest, HttpServletResponse response,
			SessionStatus status, @ModelAttribute("searchVO") @Valid BbsMngVO searchVO, BindingResult result)
			throws Exception {
		String siteCodeFull = siteGroup + "/" + siteCode;
		return writeProc(siteCodeFull, mId, model, multiRequest, response, status, searchVO, result);
	}

	/** 공통 게시판 등록 proc */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteCodeFull}/bbs/writeProc.do")
	public String writeProc(@PathVariable String siteCodeFull, @RequestParam String mId, ModelMap model,
			final MultipartHttpServletRequest multiRequest, HttpServletResponse response, SessionStatus status,
			@ModelAttribute("searchVO") @Valid BbsMngVO searchVO, BindingResult result) throws Exception {
		
		UserVO user = UserUtil.getInstance();
		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		BbsType bbsType = BbsType.toType(bbsConfigVO.getPtType());

		bbsService.validate(bbsConfigVO, searchVO, multiRequest.getFileMap(), result);

		if (result.hasErrors() || !bbsConfigVO.writableForUser()) { // 입력값 검증
			WriterUtil.flushJsAlertAndHistoryBack(response, "잘못된 입력값이 존재합니다.");
			bbsInvalidAccessLogger.insert( // 부정 접근 기록
					searchVO.getPtIdx(), searchVO.getbIdx(), SessionUtil.getRemoteAddr(multiRequest),
					user.getPrivatecode(), "writeProc");
			return null;
		}

		if (!TokenUtil.isTokenValid(multiRequest)) { // 중복방지 Token 체크
			WriterUtil.flushJsAlertAndHistoryBack(response, "중복된 요청입니다.");
			return null;
		}

		searchVO.setbPassword(YSecukeyPadUtil.decode(multiRequest, "bPassword")); // y-secupad 값 decode
		searchVO.setSessionInfo(); // 세션 정보 복사

		if (!user.getPrivatecode().equals(searchVO.getbPrivatecode()) || // invalid user
				(bbsType == BbsType.MINWON && !"Y".equals(searchVO.getPrivacyYn())) // 민원형 & 개인정보 이용 미동의
		) {
			WriterUtil.flushJsAlertAndRedirect(response, "", "/" + siteCodeFull + "/bbs/list.do?mId=" + mId);
			return null;
		} else { // insert
			String resultBIdx = bbsService.insertBbs(multiRequest, searchVO, bbsConfigVO);
			TokenUtil.resetToken(multiRequest);

			if (StringUtil.isBlank(resultBIdx)) {
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
				return null;
			} else if (bbsConfigVO.getPtWriteOnlyYn().equals("Y")) {
				WriterUtil.flushJSAlert(response, WriterUtil.createJsAlertContent("등록되었습니다. 목록으로 이동합니다.",
						"location.href = '/" + siteCodeFull + "/contents.do?mId=" + mId + "';"));
				return null;
			} else {
				return "redirect:" + siteCodeFull + "/bbs/list.do?ptIdx=" + searchVO.getPtIdx() + "&mId=" + mId;
			}
		}
	}

	/** 공통 게시판 수정 화면 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/**/bbs/modifyView.do")
	public String modify(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, Authentication auth,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("addTitleBBS", " 수정");
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경

		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		BbsMngVO bbsView = bbsService.getBbsView(searchVO);

		if (bbsView == null || "Y".equals(bbsView.getIsDel())) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "존재하지 않는 게시글입니다.");
			return null;
		} else if (!bbsView.isWritedBy(UserUtil.getInstance()) || !bbsConfigVO.writableForUser()) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "비정상적인 접근입니다.");
			return null;
		}

		model.addAttribute("bbsConfigVO", bbsConfigVO);
		model.addAttribute("bbsVO", bbsView);
		model.addAttribute("searchVO", BeanUtil.copy(bbsView, searchVO));
		model.addAttribute("categoryList", bbsConfigVO.getCustomCategoryList());
		model.addAttribute("deptList",
				bbsService.getBbsDeptListCa(bbsConfigVO.getPtCategoryYn(), bbsConfigVO.getPtCategoryGubun()));
		model.addAttribute("colTypeList", ColType.values());
		TokenUtil.saveToken(request); // 중복방지 Token 생성

		return "/" + model.get("siteCodeFull").toString() + "/bbs/write/";
	}

	/** 게시글 수정 proc */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteGroup}/{siteCode}/bbs/modifyProc.do")
	public String modifyProc(@PathVariable String siteGroup, @PathVariable String siteCode, @RequestParam String mId,
			Authentication authentication, ModelMap model, final MultipartHttpServletRequest multiRequest,
			HttpServletResponse response, SessionStatus status, @ModelAttribute("searchVO") BbsMngVO searchVO,
			BindingResult result) throws Exception {
		String siteCodeFull = siteGroup + "/" + siteCode;
		return modifyProc(siteCodeFull, mId, authentication, model, multiRequest, response, status, searchVO, result);
	}

	/** 게시글 수정 proc */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteCodeFull}/bbs/modifyProc.do")
	public String modifyProc(@PathVariable String siteCodeFull, @RequestParam String mId, Authentication auth,
			ModelMap model, final MultipartHttpServletRequest multiRequest, HttpServletResponse response,
			SessionStatus status, @ModelAttribute("searchVO") BbsMngVO searchVO, BindingResult result)
			throws Exception {
		UserVO user = UserUtil.getInstance();
		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		BbsMngVO bbsView = bbsService.getBbsView(searchVO);
		BbsType type = BbsType.toType(bbsConfigVO.getPtType());

		bbsService.validate(bbsConfigVO, searchVO, multiRequest.getFileMap(), result);

		if (result.hasErrors() || !bbsConfigVO.writableForUser()) { // 입력값 검증
			WriterUtil.flushJsAlertAndHistoryBack(response, "잘못된 입력값이 존재합니다.");
			bbsInvalidAccessLogger.insert(searchVO.getPtIdx(), searchVO.getbIdx(),
					SessionUtil.getRemoteAddr(multiRequest), user.getPrivatecode(), "modifyProc" //
			);
			return null;
		} else if (!TokenUtil.isTokenValid(multiRequest)) { // 중복방지 Token 체크
			WriterUtil.flushJsAlertAndHistoryBack(response, "중복된 요청입니다.");
			return null;
		}

		int cnt = type == BbsType.MINWON ? bbsService.getReplyCnt(searchVO) : 0;
		if (cnt > 0) { // 답변이 달린 경우
			WriterUtil.flushJSInvalidAccess(response);
			return null;
		}

		searchVO.setbPassword(YSecukeyPadUtil.decode(multiRequest, "bPassword")); // y-secupad value decode
		searchVO.setSessionInfo();

		if (bbsView == null) {
			WriterUtil.flushJSAlertNotExistBoard(response);
			return null;
		} else if (!bbsView.isWritedBy(user)) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "비정상적인 접근입니다.");
			return null;
		} else {
			bbsService.updateBbs(multiRequest, searchVO, bbsConfigVO);
			status.setComplete();
			TokenUtil.resetToken(multiRequest);
			return "redirect:" + siteCodeFull + "/bbs/view.do?bIdx="
					+ (type == BbsType.MINWON ? bbsView.getbSame() : bbsView.getbIdx()) + "&ptIdx="
					+ searchVO.getPtIdx() + "&mId=" + mId;
		}
	}

	/** 게시글 삭제 proc */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/**/bbs/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId,
			Authentication auth, ModelMap model, HttpServletRequest request, HttpServletResponse response,
			SessionStatus status) throws Exception {
		String requestUri = request.getRequestURI();

		BbsMngVO deleteTarget = bbsService.getBbsView(searchVO);
		if (deleteTarget != null && "N".equals(deleteTarget.getIsDel())
				&& deleteTarget.isWritedBy(UserUtil.getInstance())) {
			bbsService.deleteBbs(request, searchVO);
			return "redirect:" + "/" + SiteCode.full(requestUri) + "/bbs/list.do?ptIdx=" + searchVO.getPtIdx() + "&mId="
					+ mId;
		}

		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

	/** 답변/답글 작성 페이지 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/**/bbs/replyWrite.do")
	public String replyWrite(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId,
			Authentication auth, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		model.addAttribute("addTitleBBS", " 답글 등록");
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경

		UserVO user = UserUtil.getInstance();
		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		if (bbsConfigVO.isReplable(Boolean.FALSE)) { // 답글 등록 가능 여부 검증
			if (StringUtil.isNotBlank(bbsConfigVO.getPtTopText())) {
				bbsConfigVO.setPtTopText(StringUtil.replace(bbsConfigVO.getPtTopText(), "<br>", ""));
			}

			/* ======================== 답변글 생성시 필요 파라미터 세팅 ======================== */

			BbsMngVO bbsView = bbsService.getBbsView(searchVO);
			if (isAdminsArticle(bbsView.getbDeptNm(), bbsView.getbPrivatecode())) {
				searchVO.setbReplyAdmin("Y");
				searchVO.setbTitle(bbsView.getbTitle());
			} else {
				searchVO.setbReplyAdmin("N");
				searchVO.setbTitle(bbsView.getbTitle());
			}
			searchVO.setbSame(bbsView.getbSame());
			searchVO.setbLevel(bbsView.getbLevel());
			searchVO.setbWrite(user.getUserName());
			searchVO.setbPrivatecode(user.getPrivatecode());
			searchVO.setbPassword(YSecukeyPadUtil.decode(request, "bPassword"));

			/*
			 * =============================================================================
			 * ======
			 */

			model.addAttribute("bbsConfigVO", bbsConfigVO);
			model.addAttribute("bbsVO", searchVO);
			TokenUtil.saveToken(request); // 중복방지 Token

			return "/" + model.get("siteCodeFull").toString() + "/bbs/replyWrite/";
		}

		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

	/** 답변/답글 등록 처리 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{root}/{branch}/bbs/replyProc.do")
	public void replyProc(@PathVariable String root, @PathVariable String branch,
			@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, Authentication auth,
			ModelMap model, final MultipartHttpServletRequest multiRequest, HttpServletResponse response,
			SessionStatus status) throws Exception {
		replyProc(root + "/" + branch, searchVO, mId, auth, model, multiRequest, response, status);
	}

	/** 답변/답글 등록 처리 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteCodeFull}/bbs/replyProc.do")
	public String replyProc(@PathVariable String siteCodeFull, @ModelAttribute("searchVO") BbsMngVO searchVO,
			@RequestParam String mId, Authentication auth, ModelMap model,
			final MultipartHttpServletRequest multiRequest, HttpServletResponse response, SessionStatus status)
			throws Exception {
		if (!TokenUtil.isTokenValid(multiRequest)) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "중복된 요청입니다.");
			return null;
		}

		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		BbsType type = BbsType.toType(bbsConfigVO.getPtType());
		if (bbsConfigVO.isReplable(Boolean.FALSE)) { // 답글 등록 가능 여부 검증
			String newBIdx = bbsService.replyInsertBbs(multiRequest, searchVO, bbsConfigVO); // 답글 등록
			TokenUtil.resetToken(multiRequest);

			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("bIdx", type == BbsType.MINWON ? searchVO.getbIdx() : newBIdx);
			WriterUtil.flushJsPostRedirect(response, "게시글 등록 완료", null,
					"/" + siteCodeFull + "/bbs/view.do?ptIdx=" + searchVO.getPtIdx() + "&mId=" + mId, paramMap);
			return null;
		}

		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

	/** 답글/답변 수정 페이지 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/**/bbs/replyModifyView.do")
	public String bbsReplyModify(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("addTitleBBS", " 답변 수정");
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경

		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		if (StringUtil.isNotBlank(bbsConfigVO.getPtTopText())) {
			bbsConfigVO.setPtTopText(StringUtil.replace(bbsConfigVO.getPtTopText(), "<br>", ""));
		}

		BbsMngVO bbsView = bbsService.getBbsView(searchVO);
		if (!bbsView.isWritedBy(UserUtil.getInstance())) { // Auth Certification
			WriterUtil.flushJSInvalidAccess(response);
			return null;
		}

		model.addAttribute("bbsConfigVO", bbsConfigVO);
		model.addAttribute("bbsVO", bbsView);
		TokenUtil.saveToken(request);

		return "/" + model.get("siteCodeFull").toString() + "/bbs/replyModify/";
	}

	/** 답변/답글 수정 화면 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{root}/{branch}/bbs/replyModifyProc.do")
	public void bbsReplyModifyProc(@PathVariable String root, @PathVariable String branch,
			@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, ModelMap model,
			final MultipartHttpServletRequest multiRequest, HttpServletResponse response, SessionStatus status)
			throws Exception {
		bbsReplyModifyProc(root + "/" + branch, searchVO, mId, model, multiRequest, response, status);
	}

	/** 답변/답글 수정 처리 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteCodeFull}/bbs/replyModifyProc.do")
	public String bbsReplyModifyProc(@PathVariable String siteCodeFull, @ModelAttribute("searchVO") BbsMngVO searchVO,
			@RequestParam String mId, ModelMap model, final MultipartHttpServletRequest multiRequest,
			HttpServletResponse response, SessionStatus status) throws Exception {
		if (!TokenUtil.isTokenValid(multiRequest)) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "중복된 요청입니다.");
			return null;
		}

		UserVO user = UserUtil.getInstance();
		BbsConfigVO bbsConfigVO = bbsConfigService.getBbsConfigView(new BbsConfigVO(searchVO.getPtIdx()));
		BbsType type = BbsType.toType(bbsConfigVO.getPtType());
		if (StringUtil.isBlank(searchVO.getbPrivatecode())
				|| !user.getPrivatecode().equals(searchVO.getbPrivatecode())) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "비정상적인 접근입니다.");
			return null;
		} else {
			searchVO.setbWrite(user.getUserName());
			searchVO.setbPrivatecode(user.getPrivatecode());
			String bIdx = bbsService.updateBbs(multiRequest, searchVO, bbsConfigVO);
			TokenUtil.resetToken(multiRequest);

			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("bIdx", type == BbsType.MINWON ? searchVO.getbSame() : bIdx);
			WriterUtil.flushJsPostRedirect(response, "게시글 수정 완료", null,
					"/" + siteCodeFull + "/bbs/view.do?ptIdx=" + searchVO.getPtIdx() + "&mId=" + mId, paramMap);
			return null;
		}
	}

	/** 비밀번호 확인 페이지 */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/**/bbs/boardPwd.do")
	public String boardPwd(@ModelAttribute("searchVO") BbsMngVO searchVO, @RequestParam String mId, ModelMap model,
			final HttpServletRequest request, HttpServletResponse response, SessionStatus status,
			@RequestParam(required = false) String cancelUrl) throws Exception {
		model.addAttribute("addTitleBBS", " 비밀번호 확인");
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경

		model.addAttribute("searchVO", searchVO);
		model.addAttribute("cancelUrl", TUtil.covertXSS(cancelUrl));

		return "/" + model.get("siteCodeFull").toString() + "/bbs/" + "boardPwd/";
	}

	/** 비공개글 비밀번호 검증 처리(2depth) */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteGroup}/{siteCode}/bbs/boardPwdProc.do")
	public String boardPwdProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteGroup,
			@PathVariable String siteCode, @RequestParam String mId, final HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return boardPwdProc(searchVO, siteGroup + "/" + siteCode, mId, request, response);
	}

	/** 비공개글 비밀번호 검증 처리(1depth) */
	@Secured({ "ROLE_USER", "ROLE_USER_TMP", "ROLE_USER_TMP_IPIN" })
	@RequestMapping("/{siteCodeFull}/bbs/boardPwdProc.do")
	public String boardPwdProc(@ModelAttribute("searchVO") BbsMngVO searchVO, @PathVariable String siteCodeFull,
			@RequestParam String mId, final HttpServletRequest request, HttpServletResponse response) throws Exception {
		searchVO.setbPrivatecode(UserUtil.getInstance().getPrivatecode());
		searchVO.setbPassword(YSecukeyPadUtil.decode(request, "bPassword"));

		BbsMngVO target = bbsService.getBbsView(searchVO);
		String checkPasswd = target != null ? target.getEncPasswd() : "";

		if (StringUtil.isBlank(checkPasswd)) { // 비밀번호 미입력
			WriterUtil.flushJsAlertAndHistoryBack(response, "비밀번호를 입력해주세요.");
		} else if (!target.isWritedBy(UserUtil.getInstance())) { // 다른 사용자의 접근
			WriterUtil.flushJsAlertAndHistoryBack(response, "권한이 없습니다.");
		} else if (searchVO.getEncPasswd().equals(checkPasswd)) { // 인가된 사용자, 비밀번호 일치
			if (searchVO.getBbsMode().equals("edit")) { // 수정
				return "redirect:" + siteCodeFull + "/bbs/modifyView.do?bIdx=" + searchVO.getbIdx() + "&ptIdx="
						+ searchVO.getPtIdx() + "&mId=" + mId;
			} else if (searchVO.getBbsMode().equals("delete")) { // 삭제
				return "redirect:" + siteCodeFull + "/bbs/deleteProc.do?bIdx=" + searchVO.getbIdx() + "&ptIdx="
						+ searchVO.getPtIdx() + "&mId=" + mId;
			} else if (searchVO.getBbsMode().equals("replyEdit")) { // 답글 수정
				return "redirect:" + siteCodeFull + "/bbs/replyModifyView.do?bIdx=" + searchVO.getbIdx() + "&ptIdx="
						+ searchVO.getPtIdx() + "&mId=" + mId;
			}
		} else { // 잘못된 비밀번호
			WriterUtil.flushJSAlert( //
					response, //
					WriterUtil.createJsAlertContent( //
							"비밀번호가 틀립니다. 다시 입력하세요.",
							"location.href = '/" + siteCodeFull + "/bbs/boardPwd.do?mId=" + mId + "&ptIdx="
									+ searchVO.getPtIdx() + "&bIdx=" + searchVO.getbIdx() + "&bbsMode="
									+ searchVO.getBbsMode() + "'") //
			);
		}

		return null;
	}

	/** 인증 페이지 */
	@RequestMapping("/**/bbs/inRealName.do")
	public String inRealName(@ModelAttribute("searchVO") BbsMngVO inputVO, @RequestParam String mId, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("addTitleBBS", " 본인 인증");
		commonService.addActiveMenuTo(model, request, mId); // sub title 변경
		SecurityContextHolder.getContext().setAuthentication(null); // 세션 초기화

		String cancelUrl = TUtil.covertXSS(ServletRequestUtils.getStringParameter(request, "cancelUrl", "")); // return
																												// url
		model.addAttribute("cancelUrl", cancelUrl);
		model.addAttribute("page", ServletRequestUtils.getIntParameter(request, "page", 1));
		model.addAttribute("inputVO", inputVO);
		model.addAttribute("nameCheck", ServletRequestUtils.getStringParameter(request, "nameCheck", ""));

		return "/" + model.get("siteCodeFull").toString() + "/bbs/inRealName/";
	}

	private void authAlert(HttpServletResponse response, String siteCodeFull, String mId, String ptIdx, String bIdx)
			throws Exception {
		WriterUtil.flushJsAlertAndRedirect( //
				response, "작성한 본인만 열람할 수 있습니다.", //
				"location.href= '/" + siteCodeFull + "/bbs/inRealName.do" + "?mId=" + mId + "&successUrl="
						+ URLEncoder.encode(
								"/" + siteCodeFull + "/bbs/view.do?bIdx=" + bIdx + "&ptIdx=" + ptIdx + "&mId=" + mId,
								"UTF-8")
						+ "';" //
		);
	}

	private void listAlert(HttpServletResponse response, String siteCodeFull, String mId, String ptIdx)
			throws Exception {
		WriterUtil.flushJsAlertAndRedirect( //
				response, "작성한 본인만 열람할 수 있습니다.", //
				"location.href= '/" + siteCodeFull + "/bbs/list.do?mId=" + mId + "&ptIdx=" + ptIdx + "';" //
		);
	}

	private boolean isAdminsArticle(String deptNm, String privatecode) {
		return StringUtil.isBlank(privatecode);
	}

	private boolean validUserAuthority(BbsConfigVO bbsConfigVO, BbsMngVO bbsView) {
		UserVO user = UserUtil.getInstance();
		BbsType bbsType = BbsType.toType(bbsConfigVO.getPtType());
		if (bbsType == BbsType.MINWON) { // 민원형 게시판은 작성한 본인만이 상세 확인 가능
			if (user != null && (user.getPrivatecode().equals(bbsView.getbPrivatecode()))) { // 작성한 본인의 열람 시도
				return true;
			}
		} else { // 기타
			if ("N".equals(bbsConfigVO.getPtPublicYn())) { // 비밀글 작성이 불가능한 게시판의 경우 전체 열람 가능
				return true;
			} else if ("Y".equals(bbsConfigVO.getPtReportYn()) && bbsView.isWritedBy(user)) { // 본인글 열람만을 허용하는 게시판의 게시글
																								// 열람
				return true; // 작성자 본인은 열람 가능
			} else if ("N".equals(bbsConfigVO.getPtReportYn()) && "Y".equals(bbsView.getbPublic())) { // 공개글 열람 시도
				return true;
			} else if ("N".equals(bbsConfigVO.getPtReportYn()) && "Y".equals(bbsConfigVO.getPtPublicYn())
					&& "N".equals(bbsView.getbPublic()) && bbsView.isWritedBy(user)) {
				// 비밀글 작성 지원 게시판의 비밀글 열람 시도시 작성자 본인만 열람 가능
				return true;
			}
		}

		return false;
	}

}
