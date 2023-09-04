package egovframework.portal.basic.poll.web;

import java.io.UnsupportedEncodingException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.common.service.CommonService;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.basic.poll.PollDupType;
import egovframework.portal.sys.basic.poll.PollJoinGenderType;
import egovframework.portal.sys.basic.poll.service.PollMngService;
import egovframework.portal.sys.basic.poll.service.QuestionMngService;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.CookieUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 10. 13.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 10. 13.
 */
@Controller
public class PollController {

	@Autowired
	protected CommonService commonService;

	@Autowired
	protected SiteMngService siteMngService;

	@Autowired
	protected PollMngService pollMngService;

	@Autowired
	protected QuestionMngService questionMngService;

	/**
	 * 설문조사 목록
	 *
	 * @param searchVO
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/**/poll/list.do")
	public String list(@ModelAttribute PollMngVO searchVO, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String tilesSiteCode = model.get("siteCodeFull").toString();
		String siteCode = model.get("siteCode").toString();
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(searchVO.getPage(), 10));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(searchVO.getPage(), 10));
		searchVO.setSearchSiteCode(siteCode);

		int totalCnt = pollMngService.getPollCnt(searchVO);
		List<PollMngVO> list = pollMngService.getPollList(searchVO);

		model.addAttribute("list", list);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("page", searchVO.getPage());
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, searchVO.getPage(), 10));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(searchVO.getPage(), 10, 10, totalCnt));
		return "/" + tilesSiteCode + "/poll/list";
	}

	/**
	 * 설문조사 상세 페이지
	 *
	 * @param searchVO
	 * @param idx
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/**/poll/view.do")
	public String view(@ModelAttribute PollMngVO searchVO, @RequestParam("idx") int idx, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String tilesSiteCode = model.get("siteCodeFull").toString();
		//BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("poll", pollMngService.getPollView(searchVO));
		return "/" + tilesSiteCode + "/poll/view";
	}

	/**
	 * 설문조사 참여 페이지
	 *
	 * @param searchVO
	 * @param idx
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/{siteCode}/poll/apply.do")
	public String apply(@ModelAttribute PollMngVO searchVO, @RequestParam("idx") int idx, @RequestParam("mId") String mId,
		@PathVariable String siteCode, @CookieValue(value = "POLL_IDX", required = false, defaultValue = "") String cookie,
		HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {

		String tilesSiteCode = model.get("siteCodeFull").toString();
		BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO);
		UserVO user = UserUtil.getInstance();

		/** 질문 정보 */
		searchVO = questionMngService.getQuestionInfo(searchVO);
		model.addAttribute("searchVO", searchVO);

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("idx", String.valueOf(searchVO.getIdx()));
		paramMap.put("page", String.valueOf(searchVO.getPage()));

		/** 등록된 질문 없을때 차단 */
		if (searchVO == null || searchVO.getQuestionList() == null || searchVO.getQuestionList().size() == 0 || StringUtil.isBlank(searchVO.getQuestionList().get(0).getQuestion())) {
			WriterUtil.flushJsPostRedirect(response, "설문조사 접근불가 안내", "등록된 질문이 없으므로 현재 참여할 수 없는 설문조사입니다.", "/" + model.get("siteCodeFull") + "/poll/list.do?mId=" + mId, paramMap);
		}

		/** 설문조사 참여 기간 확인 */
		if (!blockOverDate(searchVO.getStartDate(), searchVO.getEndDate()) || "N".equals(searchVO.getStateYn())) {
			WriterUtil.flushJsPostRedirect(response, "설문조사 접근불가 안내", "진행중인 설문조사가 아닙니다. 참여할 수 없습니다.", "/" + model.get("siteCodeFull") + "/poll/list.do?mId=" + mId, paramMap);
		}

		/** 중복 참여 체크 */
		if (PollDupType.IP.getCode().equals(searchVO.getDupType())) {
			/** IP 체크 */
			if (!blockApply(request, searchVO.getIdx(), PollDupType.IP)) {
				WriterUtil.flushJsPostRedirect(response, "설문조사 접근불가 안내", "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.", "/" + model.get("siteCodeFull") + "/poll/list.do?mId=" + mId, paramMap);
			}
		} else if (PollDupType.COOKIE.getCode().equals(searchVO.getDupType())) {
			/** 쿠키 체크 */
			if (StringUtil.isNotBlank(cookie) && StringUtil.contains(String.valueOf(idx), cookie.split(","))) {
				WriterUtil.flushJsPostRedirect(response, "설문조사 접근불가 안내", "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.", "/" + model.get("siteCodeFull") + "/poll/list.do?mId=" + mId, paramMap);
			}
		} else if (PollDupType.REALNAME.getCode().equals(searchVO.getDupType())) {
			/** 본인인증 체크 */
			if (user == null) {
				WriterUtil.flushAlertAndRedirectToAuthenticationPage(request, response, "설문조사 참여를 위해 본인인증으로 이동 페이지", mId, "설문조사 참여를 위해 본인인증이 필요합니다.", siteCode);
			} else {
				if (!blockApply(request, searchVO.getIdx(), PollDupType.REALNAME)) {
					WriterUtil.flushJsPostRedirect(response, "설문조사 접근불가 안내", "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.", "/" + model.get("siteCodeFull") + "/poll/list.do?mId=" + mId, paramMap);
				}
			}
			/** 설문조사 참여 대상 옵션 체크 (성별) */
			if (!"A".equals(searchVO.getJoinGender())) {
				if (!blockTargetGender(searchVO.getJoinGender(), user.getGender())) {
					WriterUtil.flushJsPostRedirect(response, "설문조사 접근불가 안내", "죄송합니다. 이 설문조사의 대상 성별이 아닙니다.", "/" + model.get("siteCodeFull") + "/poll/list.do?mId=" + mId, paramMap);
				}
			}
			/** 설문조사 참여 대상 옵션 체크 (연령) */
			if ("Y".equals(searchVO.getJoinAgeYn())) {
				if (!blockTargetAge(user.getBirthday(), searchVO.getJoinStAge(), searchVO.getJoinEdAge())) {
					WriterUtil.flushJsPostRedirect(response, "설문조사 접근불가 안내", "죄송합니다. 이 설문조사의 대상 연령이 아닙니다.", "/" + model.get("siteCodeFull") + "/poll/list.do?mId=" + mId, paramMap);
				}
			}
		}

		/** 문항 건너뛰기 정보 */
		model.addAttribute("answerAbleList", questionMngService.getAnswerAbleList(searchVO.getIdx()));
		return "/" + tilesSiteCode + "/poll/apply";
	}

	/**
	 * 설문 조사 응답 처리
	 *
	 * @param searchVO
	 * @param idx
	 * @param siteCode
	 * @param cookie
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/{siteCode}/poll/applyProc.do")
	public String applyProc(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("idx") int idx, @PathVariable String siteCode,
		@CookieValue(value = "POLL_IDX", required = false, defaultValue = "") String cookie,
		HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam("mId") String mId) throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO, true, "responseList", "questionList", "tel");
		UserVO user = UserUtil.getInstance();
		String msg;
		/** 설문조사 참여 기간 확인 */
		if (!blockOverDate(searchVO.getStartDate(), searchVO.getEndDate()) || "N".equals(searchVO.getStateYn())) {
			msg = "진행중인 설문조사가 아닙니다. 참여할 수 없습니다.";
		}

		/** 중복 참여 체크 */
		if (PollDupType.IP.getCode().equals(searchVO.getDupType())) {
			/** IP 체크 */
			if (!blockApply(request, searchVO.getIdx(), PollDupType.IP)) {
				msg = "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.";
			}
		} else if (PollDupType.COOKIE.getCode().equals(searchVO.getDupType())) {
			/** 쿠키 체크 */
			if (StringUtil.isNotBlank(cookie) && StringUtil.contains(String.valueOf(idx), cookie.split(","))) {
				msg = "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.";
			}
		} else if (PollDupType.REALNAME.getCode().equals(searchVO.getDupType())) {
			/** 본인인증 체크 */
			if (user == null) {
				msg = "설문조사에 참여하기 위해서는 본인 인증이 필요합니다.";
			} else {
				if (!blockApply(request, searchVO.getIdx(), PollDupType.REALNAME)) {
					msg = "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.";
				}
			}
		}

		if (searchVO.getResponseList() != null && searchVO.getResponseList().size() > 0) {
			boolean ret = Boolean.FALSE;
			if (user == null) {
				ret = pollMngService.setPollApply(searchVO, SessionUtil.getRemoteAddr(request));
			} else {
				ret = pollMngService.setPollApply(searchVO, user, SessionUtil.getRemoteAddr(request));
			}
			if (ret) {
				if (PollDupType.COOKIE.getCode().equals(searchVO.getDupType())) {
					CookieUtil.setCookie("POLL_IDX", (StringUtil.isNotBlank(cookie) ? cookie + "," + idx : "," + idx), 1, request, response);
				}
				msg = "설문에 참여해 주셔서 감사합니다.";
			} else {
				msg = "등록 처리 중 오류가 발생하였습니다.";
			}
		} else {
			msg = "선택된 설문 항목이 없습니다.";
		}
		String returnPath = "document.location.href='/bmc/poll/list.do?mId=" + mId + "';";
		WriterUtil.flushJsAlertAndRedirect(response, msg, returnPath);
		return null;
		/*return "redirect:/bmc/poll/list.do?mId=" + mId;*/
		
	}
	
	
	/*@ResponseBody
	@RequestMapping(value = "/{siteCode}/poll/applyProc.do")
	public byte[] applyProc(@ModelAttribute("searchVO") PollMngVO searchVO, @RequestParam("idx") int idx, @PathVariable String siteCode,
		@CookieValue(value = "POLL_IDX", required = false, defaultValue = "") String cookie,
		HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam("mId") String mId) throws Exception {
			System.out.println("searchVO : " + searchVO);
		response.setContentType("text/json; charset=UTF-8");
		BeanUtil.copy(pollMngService.getPollView(searchVO), searchVO, true, "responseList", "questionList", "tel");
		UserVO user = UserUtil.getInstance();

		*//** 설문조사 참여 기간 확인 *//*
		if (!blockOverDate(searchVO.getStartDate(), searchVO.getEndDate()) || "N".equals(searchVO.getStateYn())) {
			return jsonBytes("fail", "진행중인 설문조사가 아닙니다. 참여할 수 없습니다.");
		}

		*//** 중복 참여 체크 *//*
		if (PollDupType.IP.getCode().equals(searchVO.getDupType())) {
			*//** IP 체크 *//*
			if (!blockApply(request, searchVO.getIdx(), PollDupType.IP)) {
				return jsonBytes("fail", "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.");
			}
		} else if (PollDupType.COOKIE.getCode().equals(searchVO.getDupType())) {
			*//** 쿠키 체크 *//*
			if (StringUtil.isNotBlank(cookie) && StringUtil.contains(String.valueOf(idx), cookie.split(","))) {
				return jsonBytes("fail", "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.");
			}
		} else if (PollDupType.REALNAME.getCode().equals(searchVO.getDupType())) {
			*//** 본인인증 체크 *//*
			if (user == null) {
				return jsonBytes("fail", "설문조사에 참여하기 위해서는 본인 인증이 필요합니다.");
			} else {
				if (!blockApply(request, searchVO.getIdx(), PollDupType.REALNAME)) {
					return jsonBytes("fail", "죄송합니다. 이미 참여한 설문조사입니다. 설문조사에 참여할 수 없습니다.");
				}
			}
		}

		if (searchVO.getResponseList() != null && searchVO.getResponseList().size() > 0) {
			boolean ret = Boolean.FALSE;
			if (user == null) {
				ret = pollMngService.setPollApply(searchVO, SessionUtil.getRemoteAddr(request));
			} else {
				ret = pollMngService.setPollApply(searchVO, user, SessionUtil.getRemoteAddr(request));
			}
			if (ret) {
				if (PollDupType.COOKIE.getCode().equals(searchVO.getDupType())) {
					CookieUtil.setCookie("POLL_IDX", (StringUtil.isNotBlank(cookie) ? cookie + "," + idx : "," + idx), 1, request, response);
				}
				return jsonBytes("success", "설문에 참여해 주셔서 감사합니다.");
			} else {
				return jsonBytes("fail", "등록 처리 중 오류가 발생하였습니다.");
			}
		} else {
			return jsonBytes("fail", "선택된 설문 항목이 없습니다.");
		}
	}*/
	
	/**
	 * 설문 결과
	 *
	 * @param searchVO
	 * @param idx
	 * @param mId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/**/poll/result.do")
	public String result(@ModelAttribute PollMngVO searchVO, @RequestParam("idx") int idx, @RequestParam("mId") String mId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String tilesSiteCode = model.get("siteCodeFull").toString();
		model.addAttribute("poll", pollMngService.getPollView(searchVO));
		searchVO = questionMngService.getQuestionInfoWithResult(searchVO);
		model.addAttribute("searchVO", searchVO);
//		model.addAttribute("pollResult", questionMngService.getResponseList(idx));

		return "/" + tilesSiteCode + "/poll/result";
	}

	/**
	 * 요청 결과 JSON 출력
	 *
	 * @param flag
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JSONException
	 */
	private byte[] jsonBytes(String flag, String msg) throws UnsupportedEncodingException, JSONException {
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		json.put("msg", msg);
		return json.toString().getBytes("UTF-8");
	}

	/**
	 * 설문조사 중복 참여 차단
	 *
	 * @param request
	 * @param pollIdx
	 * @param type
	 * @return
	 */
	private boolean blockApply(HttpServletRequest request, int pollIdx, PollDupType type) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("idx", pollIdx);
		int ret = 0;

		if (type == PollDupType.IP) {
			paramMap.put("ip", request.getRemoteAddr());
			ret = pollMngService.getResponderWithIp(paramMap);
		} else if (type == PollDupType.REALNAME) {
			UserVO user = UserUtil.getInstance();
			if (user != null) {
				paramMap.put("privateCode", user.getPrivatecode());
				ret = pollMngService.getResponderWithPrivateCode(paramMap);
			}
		}

		if (ret > 0) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	/**
	 * 설문조사 참여 대상 체크 (연령)
	 *
	 * @param birthday
	 * @param stAge
	 * @param edAge
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private boolean blockTargetAge(String birthday, String stAge, String edAge) throws UnsupportedEncodingException {
		/** 연령 체크 (시작 연령 혹은 종료 연령만 존재할 수도 있기 때문에 따로 체크 */
		if (StringUtil.isNotBlank(stAge) && Integer.parseInt(stAge) > Integer.parseInt(birthday.substring(0, 4))) {
			return Boolean.FALSE;
		}
		if (StringUtil.isNotBlank(edAge) && Integer.parseInt(edAge) < Integer.parseInt(birthday.substring(0, 4))) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * 설문조사 참여 대상 체크 (성별)
	 *
	 * @param joinGender
	 * @param userGender
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private boolean blockTargetGender(String joinGender, String userGender) throws UnsupportedEncodingException {
		/** 성별 체크 */
		if (joinGender.equals(PollJoinGenderType.MAN.getCode())) {
			if (!userGender.equals("M")) {
				return Boolean.FALSE;
			}
		} else if (joinGender.equals(PollJoinGenderType.WOMAN.getCode())) {
			if (!userGender.equals("F")) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
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