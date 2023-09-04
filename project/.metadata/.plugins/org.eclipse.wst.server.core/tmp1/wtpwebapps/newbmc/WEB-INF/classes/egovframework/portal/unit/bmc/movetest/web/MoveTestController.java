package egovframework.portal.unit.bmc.movetest.web;

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

import egovframework.portal.unit.bmc.movetest.service.MoveTestService;
import egovframework.portal.unit.bmc.movetest.vo.MoveTestVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.WriterUtil;

@Controller
public class MoveTestController {
	
	@Autowired
	protected MoveTestService moveTestService;
	
	/********* 예약 본인인증 *********/
	@RequestMapping("/bmc/movetest/ilgwangMove.do")
	public String reservation(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
//		Date date = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String today = df.format(date);
		//String eDate = "2023-03-29 16:00:00";
		//String eDate = "2023-04-07 17:00:00";
		//String eDate = "2023-06-29 17:00:00";

//		if(today.compareTo(eDate) > 0) {
//			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다. 예약한 이사날짜를 확인해주세요.", "document.location.href='/bmc/movetest/dateConfirm.do';");
//			return null;
//		} else {
			return "/bmc/movetest/ilgwang/";
		
	}
	
	@RequestMapping("/bmc/movetest/ilgwangRes.do")
	public String reserve(@ModelAttribute("MoveTestVO") MoveTestVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/bmc/movetest/ilgwangRes/";
	}
	
	@RequestMapping("/bmc/movetest/reserve.do")
	public String reserveConfirm(@ModelAttribute("MoveTestVO") MoveTestVO moveReserveVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		moveReserveVO.setAddr1(request.getParameter("addr1"));
		moveReserveVO.setTimeSel(request.getParameter("timeSel"));
		moveReserveVO.setDateSel(request.getParameter("dateSel"));
		
		String dateSel = request.getParameter("dateSel");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date eDate = formatter.parse("2023-04-01");
		Date dateSel1 = formatter.parse(dateSel);
		
		int compare = dateSel1.compareTo(eDate);
		
		int chkResCnt = moveTestService.chkRes(moveReserveVO);
		
		String timeSel = request.getParameter("timeSel");
		if(timeSel == null || timeSel == "") {
			WriterUtil.flushJsAlertAndHistoryBack(response, "오류가 발생했습니다. 날짜 및 시간을 다시 선택해주세요.");
			return null;
		} else {
			if(compare <= 0) {
				WriterUtil.flushJsAlertAndHistoryBack(response, "선택할 수 없는 시간입니다.");
				return null;
			} else {
				int chkResCust = moveTestService.chkResCust(moveReserveVO);
				if(chkResCust > 0) {
					WriterUtil.flushJsAlertAndRedirect(response, "이미 예약이 완료됐습니다. 예약한 이사날짜를 확인해주세요.", "document.location.href='/bmc/movetest/dateConfirm.do';");
					return null;
				} else {
					if(chkResCnt > 0) {
						WriterUtil.flushJsAlertAndHistoryBack(response, "이미 예약이 완료된 시간입니다.");
						return null;
					} else {
						moveTestService.reserve(moveReserveVO);
						
						WriterUtil.flushJsAlertAndRedirect(response, "신청이 완료되었습니다.", "document.location.href='/bmc/movetest/dateConfirm.do';");
						return null;
					}
				}
			}
		}
	}
	
	@RequestMapping("/bmc/movetest/dateConfirm.do")
	public String dateConfirm(@ModelAttribute("MoveTestVO") MoveTestVO moveReserveVO, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserVO) {
			UserVO userVO = (UserVO) principal;
			
			String userName = userVO.getUserName();
			String tel = userVO.getTel1()+ "-" + userVO.getTel2() + "-" + userVO.getTel3();
			
			moveReserveVO.setName(userName);
			moveReserveVO.setTel(tel);
			
			//고객 리스트와 정보 비교
			MoveTestVO custList = moveTestService.getCustInfo(moveReserveVO);
			MoveTestVO chkCust = moveTestService.chkCust(moveReserveVO);

			if(userVO.getUserName() != "") {
				if(chkCust != null) {
					
					if(custList.getMove_date() != null) {
						model.addAttribute("custList", custList);
						
						return "/bmc/movetest/dateConfirm/";
					}else {
						WriterUtil.flushJsAlertAndRedirect(response, "이사 날짜를 먼저 예약해주세요.", "document.location.href='/bmc/movetest/ilgwangMove.do';"); 
						return null; 
					}
					
				} else {
					WriterUtil.flushJsAlertAndRedirect(response, "대상자가 아닙니다.", "document.location.href='/bmc/movetest/ilgwangMove.do';"); 
					return null; 
				}
			} else {
				return "/bmc/movetest/dateConfirm/";
			}
		} else {
			return "/bmc/movetest/dateConfirm/";
		}
		

	}
	
	/************ 고객정보 확인 ************/
	@RequestMapping("/bmc/movetest/chkCust.do")
	public String chkCustList(@ModelAttribute("MoveTestVO") MoveTestVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		/***** 고객 여부 확인 *****/
		MoveTestVO resultList = moveTestService.getChkCustInfo(moveReserveVO);
		/***** 예약 여부 확인 *****/
		MoveTestVO chkRes = moveTestService.chkReserve(moveReserveVO);
		/***** 기예약자 여부 확인 => 값이 있으면 신규 계약자, 값이 없으면 기계약자 *****/
		MoveTestVO chkGbn = moveTestService.chkGbn(moveReserveVO);
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(date);
		String addr1 = moveReserveVO.getAddr1();	
		String addr2 = moveReserveVO.getAddr2();
		
		try {

			if(resultList != null) { //고객 리스트에 있으면
				
				if(chkRes != null ) { //예약 내역이 있으면
					WriterUtil.flushJsAlertAndRedirect(response, "이미 이사 날짜를 예약했습니다.", "document.location.href='/bmc/movetest/dateConfirm.do';"); 
					return null; 
				}
				else{
					
					if(chkGbn != null) { //추가당첨계약자
						
						if(today.equals("2023-03-30")) { // 테스트
							if(addr1.equals("771")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							}  else if(addr1.equals("772")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else if(addr1.equals("773")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else if(addr1.equals("774")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else if(addr2.equals("7001")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else {
								WriterUtil.flushJsAlertAndRedirect(response, "신청기간이 아닙니다.", "document.location.href='/bmc/movetest/ilgwangMove.do';");
								return null;
							}
						} else if(today.equals("2023-03-31")) {
							if(addr1.equals("775")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else if(addr1.equals("776")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else if(addr1.equals("777")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else if(addr1.equals("778")) {
								model.addAttribute("addr1", addr1);
								model.addAttribute("addr2", addr2);
								model.addAttribute("resultList", resultList);
								return "/bmc/movetest/ilgwangRes/";
							} else {
								WriterUtil.flushJsAlertAndRedirect(response, "신청기간이 아닙니다.", "document.location.href='/bmc/movetest/ilgwangMove.do';");
								return null;
							}
						} else {
							model.addAttribute("addr1", addr1);
							model.addAttribute("addr2", addr2);
							model.addAttribute("resultList", resultList);
							return "/bmc/movetest/ilgwangRes/";
							/* WriterUtil.flushJsAlertAndRedirect(response, "신청기간이 아닙니다.", "document.location.href='/bmc/moveRes/ilgwangMove.do';");
							return null; */
						}
					} else {
						WriterUtil.flushJsAlertAndHistoryBack(response, "신청 대상 기간이 아닙니다.");
						return null; 
					}
					
				}
			}else{
				WriterUtil.flushJsAlertAndRedirect(response, "신청 대상자가 아닙니다. 동, 호수를 정확히 기입해주세요.", "document.location.href='/bmc/movetest/ilgwangMove.do';");
				return null; 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/************ 날짜 수정확인 ************/
	@RequestMapping("/bmc/movetest/chkResUp.do")
	public String chkResup(@ModelAttribute("MoveTestVO") MoveTestVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		MoveTestVO custList = moveTestService.getCustInfo(moveReserveVO);
		
		if(custList.getUpdt_yn().equals("Y")) {
			model.addAttribute("custList", custList);
			return "/bmc/movetest/dateUpdate/";
		}
		else {
			WriterUtil.flushJsAlertAndRedirect(response, "이미 1회의 수정을 했습니다.", "document.location.href='/bmc/movetest/dateConfirm.do';");
			return null;
		}
	}
	
	/************ 날짜 수정 ************/
	@RequestMapping("/bmc/movetest/resUpdate.do")
	public String resUpdate(@ModelAttribute("MoveTestVO") MoveTestVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		moveReserveVO.setAddr1(request.getParameter("addr1"));
		moveReserveVO.setTimeSel(request.getParameter("timeSel"));
		moveReserveVO.setDateSel(request.getParameter("dateSel"));
		
		int chkResCnt = moveTestService.chkRes(moveReserveVO);
		if(chkResCnt > 0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "이미 예약이 완료된 시간입니다.");
			return null;
		} else {
			moveTestService.resUp(moveReserveVO);
			
			WriterUtil.flushJsAlertAndRedirect(response, "수정이 완료되었습니다.", "document.location.href='/bmc/movetest/dateConfirm.do';");
			return null;

		}
	
	}
	
	/************ 시간 선택 확인 ************/
	@RequestMapping("/bmc/movetest/chkTime.do")
	public void chkTime(@ModelAttribute("MoveTestVO") MoveTestVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		List<MoveTestVO> resultList = moveTestService.chkTime(moveReserveVO);
		
		ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
		for (int i = 0; i < resultList.size(); i++) {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(resultList.get(i));
			arr.add(new JSONObject(json));
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(arr);
		
	}

}
