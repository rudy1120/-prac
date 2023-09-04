package egovframework.portal.sys.MenuMng.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.service.MyPageMngService;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenuChargeReqVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import egovframework.rte.fdl.string.EgovStringUtil;

/**
 * 관리자 - 마이페이지 관리 Controller 클래스
 *
 * @author 개발팀 김장섭
 * @since 2016-11-20
 * @version 1.0
 * @see
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2016-11-20 김장섭 *
 *
 * </pre>
 */
@Controller("MyPageMngController")
public class MyPageMngController {

	@Autowired
	protected SiteMngService siteMngService;

	@Autowired
	protected MenuSysService menuSysService;

	@Autowired
	protected MyPageMngService myPageMngService;

	@Autowired
	protected MenuMngService menuMngService;

	private static Logger LOGGER = LoggerFactory.getLogger(MyPageMngController.class);

	@RequestMapping("/sys/totalAdminMng/myPage/list.do")
	public String list(@ModelAttribute("searchVO") MenuVO searchVO, ModelMap model, @RequestParam("mId") String mId,
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

		int page = Integer.parseInt(EgovStringUtil.null2string(request.getParameter("page"), "1")); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		//사용자 정보 셋팅
		AdminLoginVO adminVO = (AdminLoginVO) SessionUtil.getAdminSessionObj(request);

		searchVO.setChargeId(adminVO.getAdminId());

		int totalCnt = myPageMngService.getMyMenuCnt(searchVO); // 카운트 조회

		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("resultList", myPageMngService.getMyMenuList(searchVO));
		model.addAttribute("searchVO", searchVO);
		model.addAllAttributes(menuSysService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/myPage/list";
	}

	@RequestMapping("/sys/totalAdminMng/myPage/ackList.do")
	public String ackList(@ModelAttribute("searchVO") MenuVO searchVO, ModelMap model, @RequestParam("mId") String mId,
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

		int page = Integer.parseInt(EgovStringUtil.null2string(request.getParameter("page"), "1")); // 페이지 번호
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		//사용자 정보 셋팅
		AdminLoginVO adminVO = (AdminLoginVO) SessionUtil.getAdminSessionObj(request);

		searchVO.setChargeId(adminVO.getAdminId());

		int totalCnt = myPageMngService.getMyMenuCnt(searchVO); // 카운트 조회

		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("resultList", myPageMngService.getMyMenuAckList(searchVO));
		model.addAttribute("searchVO", searchVO);
		model.addAllAttributes(menuSysService.getSysMenuInfoMap(model, mId, request));

		return "/sys/totalAdminMng/myPage/ackList";
	}

	@RequestMapping("/sys/myPageMng/requestProc.do")
	public void requestProc(@ModelAttribute("insertVO") MenuVO insertVO, ModelMap model
		, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			String siteCode = TUtil.nullTobaseStr(request.getParameter("siteCode"), "");
			String mId = TUtil.nullTobaseStr(request.getParameter("mId"), "");
			insertVO.setSiteCode(siteCode);
			insertVO.setmId(mId);

			MenuVO resultVO = menuMngService.getMenu(insertVO);

			String moveChargeFnm = TUtil.nullTobaseStr(request.getParameter("moveChargeFnm"), "");
			String moveChargeId = TUtil.nullTobaseStr(request.getParameter("moveChargeId"), "");
			String moveChargeDepNm = TUtil.nullTobaseStr(request.getParameter("moveChargeDepNm"), "");
			String moveChargeDepCode = TUtil.nullTobaseStr(request.getParameter("moveChargeDepCode"), "");
			String moveChargeTel = TUtil.nullTobaseStr(request.getParameter("moveChargeTel"), "");


			if(resultVO != null && !"".equals(moveChargeFnm) && !"".equals(moveChargeId) && !"".equals(moveChargeDepNm) && !"".equals(moveChargeDepCode) && !"".equals(moveChargeTel) ) {
				MenuChargeReqVO inputChargeVO = new MenuChargeReqVO();

				//사용자 정보 셋팅
				AdminLoginVO adminVO = (AdminLoginVO) SessionUtil.getAdminSessionObj(request);

				inputChargeVO.setSite_code(resultVO.getSiteCode());
				inputChargeVO.setMid(resultVO.getmId());
				inputChargeVO.setReq_charge_id(adminVO.getAdminId());
				inputChargeVO.setReq_charge_fnm(adminVO.getName());
				inputChargeVO.setReq_charge_dep_code(adminVO.getDeptId());
				inputChargeVO.setReq_charge_dep_nm(adminVO.getDeptName());
				inputChargeVO.setReq_charge_tel(adminVO.getTel());

				inputChargeVO.setMove_charge_id(moveChargeId);
				inputChargeVO.setMove_charge_fnm(moveChargeFnm);
				inputChargeVO.setMove_charge_dep_code(moveChargeDepCode);
				inputChargeVO.setMove_charge_dep_nm(moveChargeDepNm);
				inputChargeVO.setMove_charge_tel(moveChargeTel);

				myPageMngService.reqMenuChargeMove(inputChargeVO);

				jsonObj.put("flag", "success");
				jsonObj.put("message", "인계요청이 정상적으로 처리되었습니다.");
				out.println(jsonObj.toString());


			} else {
				jsonObj.put("flag", "fail");
				jsonObj.put("message", "인계요청 중 전달값오류가 발생되었습니다.");
				out.println(jsonObj.toString());

			}


		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "인계요청 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

		}
	}



	@RequestMapping("/sys/myPageMng/requestCancelProc.do")
	public void requestCancelProc(@ModelAttribute("insertVO") MenuChargeReqVO cancelVO, ModelMap model
		, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			cancelVO = myPageMngService.getMyMenuReq(cancelVO);

			if(cancelVO !=null && ( "R".equals(cancelVO.getReq_state()) || "C".equals(cancelVO.getReq_state())) ) {

				myPageMngService.deleteReqMenuCharge(cancelVO);

				jsonObj.put("flag", "success");
				jsonObj.put("message", "인계취소가 정상적으로 처리되었습니다.");
				out.println(jsonObj.toString());
			} else {
				jsonObj.put("flag", "fail");
				jsonObj.put("message", "인계승인이 되어 취소할 수 없습니다.");
				out.println(jsonObj.toString());
			}



		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "인계취소 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

		}
	}


	@RequestMapping("/sys/myPageMng/ackProc.do")
	public void ackProc(@ModelAttribute("insertVO") MenuChargeReqVO reqVO, ModelMap model
		, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {

			reqVO = myPageMngService.getMyMenuReq(reqVO);

			myPageMngService.reqMenuChargeAck(reqVO);

			jsonObj.put("flag", "success");
			jsonObj.put("message", "승인이 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());

		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "승인 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

		}
	}




	@RequestMapping("/sys/myPageMng/ackCancelProc.do")
	public void ackCancelProc(@ModelAttribute("insertVO") MenuChargeReqVO cancelVO, ModelMap model
		, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jsonObj = new JSONObject();

		try {
			cancelVO = myPageMngService.getMyMenuReq(cancelVO);

			myPageMngService.reqMenuChargeCancel(cancelVO);

			jsonObj.put("flag", "success");
			jsonObj.put("message", "승인취소가 정상적으로 처리되었습니다.");
			out.println(jsonObj.toString());

		} catch (Exception e) {
			jsonObj.put("flag", "fail");
			jsonObj.put("message", "승인취소 중 오류가 발생되었습니다.");
			out.println(jsonObj.toString());

		}
	}




	/**
	 * 인계 일괄승인 처리
	 *
	 * @author 김장섭
	 */
	@RequestMapping("/sys/myPageMng/ackListProc.do")
	public void bundleDeleteProc(@ModelAttribute("insertVO") MenuChargeReqVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (searchVO == null || searchVO.getAckChecked() == null || searchVO.getAckChecked().size() == 0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "승인할 메뉴가 존재하지 않습니다.");
		} else {

			for (int i = 0; i < searchVO.getAckChecked().size(); i++) {
				MenuChargeReqVO tmp = new MenuChargeReqVO();
				tmp.setIdx(Integer.valueOf(searchVO.getAckChecked().get(i)));
				tmp = myPageMngService.getMyMenuReq(tmp);
				myPageMngService.reqMenuChargeAck(tmp);
			}

			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("searchType", "REQ");
			WriterUtil.flushJsPostRedirect(response, "게시판 관리", null, "/sys/totalAdminMng/menuMng/menuCheckList.do?mId=" + mId, paramMap);
		}
	}




}
