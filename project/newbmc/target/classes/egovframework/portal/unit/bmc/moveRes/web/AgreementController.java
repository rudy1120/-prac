package egovframework.portal.unit.bmc.moveRes.web;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.portal.unit.bmc.moveRes.service.MoveAgreeService;
import egovframework.portal.unit.bmc.moveRes.service.MoveReserveService;
import egovframework.portal.unit.bmc.moveRes.vo.MoveAgreeVO;
import egovframework.portal.unit.bmc.moveRes.vo.MoveReserveVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.WriterUtil;

@Controller
public class AgreementController {
	
	@Autowired
	protected MoveAgreeService moveAgreeService;
	
	/********* 행복주택 동의서 *********/
	@RequestMapping("/bmc/happyhouse/agreement.do")
	public String agreePage(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(date);
		
		String cDate = "2023-06-27 14:00:00";
		
		if(today.compareTo(cDate) > 0) {
			return "/bmc/moveRes/agreement/";
		} else{
			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
			return null;
		}
		
	}
	
	@RequestMapping("/bmc/happyhouse/chkProc.do")
	public String gotest(@ModelAttribute("MoveAgreeVO") MoveAgreeVO moveAgreeVO, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserVO) {
			UserVO userVO = (UserVO) principal;
			
			String userName = userVO.getUserName();
			String tel = userVO.getTel1()+ "-" + userVO.getTel2() + "-" + userVO.getTel3();
			
			//테이블, 데이터 추가 후 수정 필요
			moveAgreeVO.setUsername(userName);
			moveAgreeVO.setUsertel(tel);
			
			//고객 리스트와 정보 비교
			MoveAgreeVO chkCust = moveAgreeService.getCustInfo(moveAgreeVO);
			//MoveAgreeVO chkCust = moveAgreeService.chkCust(moveAgreeVO);
			if(chkCust != null) {
				if(chkCust.getApplyYN().equals("N")) {
					model.addAttribute("custList", chkCust);	
					return "/bmc/moveRes/agreement/";
				} else {
					WriterUtil.flushJsAlertAndRedirect(response, "이미 제출이 완료됐습니다. 메인페이지로 이동합니다.", "document.location.href='/bmc/main.do';");
				}
			} else {
				WriterUtil.flushJsAlertAndRedirect(response, "대상자가 아닙니다. 메인페이지로 이동합니다.", "document.location.href='/bmc/main.do';");
			}
		}
		return null;
	}
	
	@RequestMapping("/bmc/happyhouse/agreeProc.do")
	public String reserve(@ModelAttribute("MoveAgreeVO") MoveAgreeVO moveAgreeVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		//System.out.println("getChk1 = " + moveAgreeVO.getChk1());
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(date);
		
		String cDate = "2023-06-27 14:00:00";
		
		MoveAgreeVO chkCust = moveAgreeService.getCustInfo(moveAgreeVO);

		if(today.compareTo(cDate) > 0) {
			if(moveAgreeVO.getChk1() != null && moveAgreeVO.getChk2() != null 
					&& moveAgreeVO.getChk3() != null && moveAgreeVO.getUnderstand() != null) {		
				if(chkCust.getApplyYN().equals("N")) {
					moveAgreeService.upChk(moveAgreeVO);
					return "/bmc/moveRes/agreeSubmit/";
				} else {
					WriterUtil.flushJsAlertAndRedirect(response, "이미 제출이 완료됐습니다. 메인페이지로 이동합니다.", "document.location.href='/bmc/main.do';");
				}
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "필수 항목이 완료되지 않았습니다.");
			}
		} else{
			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
			return null;
		}
		return null;
	}

}
