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

import egovframework.portal.unit.bmc.moveRes.service.MoveReserveService;
import egovframework.portal.unit.bmc.moveRes.vo.MoveReserveVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.WriterUtil;

@Controller
public class MoveReserveController {
	
	@Autowired
	protected MoveReserveService moveReserveService;
	
	/********* 예약 본인인증 *********/
	@RequestMapping("/bmc/moveRes/movement.do") ///bmc/moveRes/ilgwangMove.do
	public String reservation(@ModelAttribute("MoveReserveVO") MoveReserveVO moveReserveVO,
			HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(date);
		
		String sDate = "2023-08-14 10:00:00"; // 전체 시작일자
		String tDate = "2023-11-30 24:00:00"; // 전체 마감일자
//		String tsDate = "2023-08-07 10:00:00"; // 테스트 전체 시작일자
//		String teDate = "2023-08-07 18:00:00"; // 테스트 전체 마감일자
		String gbnDate = ""; // 동별, 전체 예약 구분일자
		
		
		if(today.compareTo(sDate) < 0) { // 시작 전 페이지 막기
			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
			return null;
		} else if(today.compareTo(tDate) > 0) { // 마감 후 페이지 막기
			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
			return null;
		} else { // 예약 기간
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserVO) {
				UserVO userVO = (UserVO) principal;
				
				String userName = userVO.getUserName();
				String tel = userVO.getTel1()+ "-" + userVO.getTel2() + "-" + userVO.getTel3();
				
				moveReserveVO.setName(userName);
				moveReserveVO.setTel(tel); 
				
				if(moveReserveVO.getName() != "" && moveReserveVO.getTel() != "--") {
					MoveReserveVO chkCust = moveReserveService.chkCust(moveReserveVO);
//=====================================================실제 이용자 서비스 시간 시작==================================================
					String sDate1 = "2023-08-14 10:00:00";
					String eDate1 = "2023-08-14 24:00:00";
					String sDate2 = "2023-08-16 10:00:00";
					String eDate2 = "2023-08-16 24:00:00";
					String sDate3 = "2023-08-17 10:00:00";
					String eDate3 = "2023-08-17 24:00:00";
					String sDate4 = "2023-08-18 10:00:00";
					String eDate4 = "2023-08-18 24:00:00";
					
					gbnDate = "2023-08-21 10:00:00";
//=====================================================실제 이용자 서비스 시간 끝==================================================
					
					if(chkCust != null) { //예약자 일 경우
						moveReserveVO.setCust_id(chkCust.getCust_id());
						int chkResCust = moveReserveService.chkResCust(moveReserveVO);
						if(chkResCust > 0) {
							WriterUtil.flushJsAlertAndRedirect(response, "이미 예약이 완료됐습니다. 예약한 이사날짜를 확인해주세요.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
							return null;
						} else {
							if(today.compareTo(gbnDate) < 0) { 
								
								//일자별, 동별 나눠서 서비스 실행
								if(today.compareTo(sDate1) > 0 && today.compareTo(eDate1) < 0) {
									if(chkCust.getAddr1().equals("101")) {
										model.addAttribute("chkCust", chkCust);
									} else {
										WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
										return null;
									}
								} else if(today.compareTo(sDate2) > 0 && today.compareTo(eDate2) < 0) {
									if(chkCust.getAddr1().equals("102")) {
										model.addAttribute("chkCust", chkCust);
									} else {
										WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
										return null;
									}
								} else if(today.compareTo(sDate3) > 0 && today.compareTo(eDate3) < 0) {
									if(chkCust.getAddr1().equals("103")) {
										model.addAttribute("chkCust", chkCust);
									} else {
										WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
										return null;
									}
								} else if(today.compareTo(sDate4) > 0 && today.compareTo(eDate4) < 0) {
									if(chkCust.getAddr1().equals("104")) {
										model.addAttribute("chkCust", chkCust);
									} else {
										WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
										return null;
									}
								} else {
									WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
									return null;
								}
							} else {
								model.addAttribute("chkCust", chkCust);
							}
						}
						
					} else {
						WriterUtil.flushJsAlertAndRedirect(response, "신청 대상자 또는 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
						return null;
					}
				}
			}
			return "/bmc/moveRes/movement/";
		}
		
	}
	
	@RequestMapping("/bmc/moveRes/reserve.do")
	public void reserveConfirm(@ModelAttribute("MoveReserveVO") MoveReserveVO moveReserveVO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		String today = df.format(date);
		String eDate = "2023-11-30 24:00:00"; //전체 마감일자

		if(today.compareTo(eDate) > 0) {
			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
		} else {
			moveReserveVO.setAddr1(request.getParameter("addr1"));
			moveReserveVO.setTimeSel(request.getParameter("timeSel"));
			moveReserveVO.setDateSel(request.getParameter("dateSel"));
	
			String timeSel = request.getParameter("timeSel");
			
			String gbnDate = "2023-08-21 10:00:00"; // 동별, 전체 예약 구분일자
			
			MoveReserveVO chkCust = moveReserveService.chkCust(moveReserveVO);
			
			if(today.compareTo(gbnDate) < 0) {
				
				if(timeSel == null || timeSel == "") {
					WriterUtil.flushJsAlertAndHistoryBack(response, "오류가 발생했습니다. 날짜 및 시간을 다시 선택해주세요.");
				} else {
					int chkResCust = moveReserveService.chkResCust(moveReserveVO);
					if(chkResCust > 0) {
						WriterUtil.flushJsAlertAndRedirect(response, "이미 예약이 완료됐습니다. 예약한 이사날짜를 확인해주세요.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
					} else {
						int chkResCnt = moveReserveService.chkRes(moveReserveVO);
						if(chkResCnt > 0) {
							WriterUtil.flushJsAlertAndHistoryBack(response, "이미 예약이 완료된 시간입니다.");
						} else {
							//실제 적용 날짜 시작
							String sDate1 = "2023-08-14 10:00:00";
							String eDate1 = "2023-08-14 24:00:00";
							String sDate2 = "2023-08-16 10:00:00";
							String eDate2 = "2023-08-16 24:00:00";
							String sDate3 = "2023-08-17 10:00:00";
							String eDate3 = "2023-08-17 24:00:00";
							String sDate4 = "2023-08-18 10:00:00";
							String eDate4 = "2023-08-18 24:00:00";
							//실제 적용 날짜 끝

							if(today.compareTo(sDate1) > 0 && today.compareTo(eDate1) < 0) {
								if(chkCust.getAddr1().equals("101")) {
									moveReserveService.reserve(moveReserveVO);
									
									WriterUtil.flushJsAlertAndRedirect(response, "신청이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								} else {
									WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
								}
							} else if(today.compareTo(sDate2) > 0 && today.compareTo(eDate2) < 0) {
								if(chkCust.getAddr1().equals("102")) {
									moveReserveService.reserve(moveReserveVO);
									
									WriterUtil.flushJsAlertAndRedirect(response, "신청이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								} else {
									WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
								}
							} else if(today.compareTo(sDate3) > 0 && today.compareTo(eDate3) < 0) {
								if(chkCust.getAddr1().equals("103")) {
									moveReserveService.reserve(moveReserveVO);
									
									WriterUtil.flushJsAlertAndRedirect(response, "신청이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								} else {
									WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
								}
							} else if(today.compareTo(sDate4) > 0 && today.compareTo(eDate4) < 0) {
								if(chkCust.getAddr1().equals("104")) {
									moveReserveService.reserve(moveReserveVO);
									
									WriterUtil.flushJsAlertAndRedirect(response, "신청이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								} else {
									WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
								}
							} else {
								WriterUtil.flushJsAlertAndRedirect(response, "신청대상 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
							}
							
						}
					}
					
				}
			} else {
				if(timeSel == null || timeSel == "") {
					WriterUtil.flushJsAlertAndHistoryBack(response, "오류가 발생했습니다. 날짜 및 시간을 다시 선택해주세요.");
				} else {
					int chkResCust = moveReserveService.chkResCust(moveReserveVO);
					if(chkResCust > 0) {
						WriterUtil.flushJsAlertAndRedirect(response, "이미 예약이 완료됐습니다. 예약한 이사날짜를 확인해주세요.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
					} else {
						int chkResCnt = moveReserveService.chkRes(moveReserveVO);
						if(chkResCnt > 0) {
							WriterUtil.flushJsAlertAndHistoryBack(response, "이미 예약이 완료된 시간입니다.");
						} else {
							moveReserveService.reserve(moveReserveVO);
							
							WriterUtil.flushJsAlertAndRedirect(response, "신청이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
						}
					}
				}
			}
		
		}
		
	}
	
	@RequestMapping("/bmc/moveRes/dateConfirm.do")
	public String dateConfirm(@ModelAttribute("MoveReserveVO") MoveReserveVO moveReserveVO, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		String today = df.format(date);
		String eDate = "2023-11-30 24:00:00";

		if(today.compareTo(eDate) > 0) {
			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
			return null;
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserVO) {
				UserVO userVO = (UserVO) principal;
				
				String userName = userVO.getUserName();
				String tel = userVO.getTel1()+ "-" + userVO.getTel2() + "-" + userVO.getTel3();
				
				moveReserveVO.setName(userName);
				moveReserveVO.setTel(tel);
				
				//고객 리스트와 정보 비교
				MoveReserveVO custList = moveReserveService.getCustInfoN(moveReserveVO);
				MoveReserveVO chkCust = moveReserveService.chkCust(moveReserveVO);
	
				if(userVO.getUserName() != "") {
					if(chkCust != null) {
						
						if(custList.getMove_date() != null) {
							model.addAttribute("custList", custList);
							
							return "/bmc/moveRes/dateConfirm/";
						}else {
							WriterUtil.flushJsAlertAndRedirect(response, "이사 날짜를 먼저 예약해주세요.", "document.location.href='/bmc/moveRes/movement.do';"); 
							return null; 
						}
						
					} else {
						WriterUtil.flushJsAlertAndRedirect(response, "대상자가 아닙니다.", "document.location.href='/bmc/moveRes/movement.do';"); 
						return null; 
					}
				} else {
					WriterUtil.flushJsAlertAndRedirect(response, "본인인증 오류 메인으로 돌아갑니다.", "document.location.href='/bmc/main.do';");
					return null;
				}
			} else {
				WriterUtil.flushJsAlertAndRedirect(response, "본인인증 오류 메인으로 돌아갑니다.", "document.location.href='/bmc/main.do';");
				return null;
			}
		}		

	}
	
	/************ 날짜 수정확인 ************/
	@RequestMapping("/bmc/moveRes/chkResUp.do")
	public String chkResup(@ModelAttribute("MoveReserveVO") MoveReserveVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		String today = df.format(date);
		String eDate = "2023-11-30 24:00:00"; //전체 마감일자
		
		if(today.compareTo(eDate) > 0) {
			WriterUtil.flushJsAlertAndRedirect(response, "신청 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
			return null;
		} else {
			MoveReserveVO custList = moveReserveService.getCustInfoN(moveReserveVO);
			
			if(custList.getUpdt_yn().equals("Y")) {
				String gbnDate = "2023-08-21 10:00:00"; // 동별, 전체 예약 구분일자
				
				if(today.compareTo(gbnDate) < 0) { 
					//실제 적용 날짜 시작
					String sDate1 = "2023-08-14 10:00:00";
					String eDate1 = "2023-08-14 24:00:00";
					String sDate2 = "2023-08-16 10:00:00";
					String eDate2 = "2023-08-16 24:00:00";
					String sDate3 = "2023-08-17 10:00:00";
					String eDate3 = "2023-08-17 24:00:00";
					String sDate4 = "2023-08-18 10:00:00";
					String eDate4 = "2023-08-18 24:00:00";
					//실제 적용 날짜 끝
					
					if(today.compareTo(sDate1) > 0 && today.compareTo(eDate1) < 0) {
						if(custList.getAddr1().equals("101")) {
							model.addAttribute("custList", custList);
							return "/bmc/moveRes/dateUpdate/";
						} else {
							WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
							return null;
						}
					} else if(today.compareTo(sDate2) > 0 && today.compareTo(eDate2) < 0) {
						if(custList.getAddr1().equals("102")) {
							model.addAttribute("custList", custList);
							return "/bmc/moveRes/dateUpdate/";
						} else {
							WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
							return null;
						}
					} else if(today.compareTo(sDate3) > 0 && today.compareTo(eDate3) < 0) {
						if(custList.getAddr1().equals("103")) {
							model.addAttribute("custList", custList);
							return "/bmc/moveRes/dateUpdate/";
						} else {
							WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
							return null;
						}
					} else if(today.compareTo(sDate4) > 0 && today.compareTo(eDate4) < 0) {
						if(custList.getAddr1().equals("104")) {
							model.addAttribute("custList", custList);
							return "/bmc/moveRes/dateUpdate/";
						} else {
							WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
							return null;
						}
					} else { 
						WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
						return null;
					}
					
				} else {
					model.addAttribute("custList", custList);
					return "/bmc/moveRes/dateUpdate/";
				}
				
			} else {
				WriterUtil.flushJsAlertAndRedirect(response, "이미 1회의 수정을 했습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
				return null;
			}
			
		}
	}
	
	/************ 날짜 수정 ************/
	@RequestMapping("/bmc/moveRes/resUpdate.do")
	public String resUpdate(@ModelAttribute("MoveReserveVO") MoveReserveVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		String today = df.format(date);
		String eDate = "2023-11-30 24:00:00"; //전체 마감일자

		if(today.compareTo(eDate) > 0) {
			WriterUtil.flushJsAlertAndRedirect(response, "수정 기간이 아닙니다.", "document.location.href='/bmc/main.do';");
			return null;
		} else {
			moveReserveVO.setAddr1(request.getParameter("addr1"));
			moveReserveVO.setTimeSel(request.getParameter("timeSel"));
			moveReserveVO.setDateSel(request.getParameter("dateSel"));
			
			int chkResCnt = moveReserveService.chkRes(moveReserveVO);
			if(chkResCnt > 0) {
				WriterUtil.flushJsAlertAndHistoryBack(response, "이미 예약이 완료된 시간입니다.");
				return null;
			} else {
				MoveReserveVO custList = moveReserveService.getCustInfo(moveReserveVO);
				
				if(custList.getUpdt_yn().equals("N")) {
					WriterUtil.flushJsAlertAndHistoryBack(response, "이미 1회의 수정을 했습니다.");
					return null;
				} else {
					String gbnDate = "2023-08-21 10:00:00"; // 동별, 전체 예약 구분일자
					
					if(today.compareTo(gbnDate) < 0) { 
						//실제 적용 날짜 시작
						String sDate1 = "2023-08-14 10:00:00";
						String eDate1 = "2023-08-14 24:00:00";
						String sDate2 = "2023-08-16 10:00:00";
						String eDate2 = "2023-08-16 24:00:00";
						String sDate3 = "2023-08-17 10:00:00";
						String eDate3 = "2023-08-17 24:00:00";
						String sDate4 = "2023-08-18 10:00:00";
						String eDate4 = "2023-08-18 24:00:00";
						//실제 적용 날짜 끝
						
						if(today.compareTo(sDate1) > 0 && today.compareTo(eDate1) < 0) {
							if(moveReserveVO.getAddr1().equals("101")) {
								moveReserveService.resUp(moveReserveVO);
								
								WriterUtil.flushJsAlertAndRedirect(response, "수정이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							} else {
								WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							}
						} else if(today.compareTo(sDate2) > 0 && today.compareTo(eDate2) < 0) {
							if(moveReserveVO.getAddr1().equals("102")) {
								moveReserveService.resUp(moveReserveVO);
								
								WriterUtil.flushJsAlertAndRedirect(response, "수정이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							} else {
								WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							}
						} else if(today.compareTo(sDate3) > 0 && today.compareTo(eDate3) < 0) {
							if(moveReserveVO.getAddr1().equals("103")) {
								moveReserveService.resUp(moveReserveVO);
									
								WriterUtil.flushJsAlertAndRedirect(response, "수정이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							} else {
								WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							}
						} else if(today.compareTo(sDate4) > 0 && today.compareTo(eDate4) < 0) {
							if(moveReserveVO.getAddr1().equals("104")) {
								moveReserveService.resUp(moveReserveVO);
								
								WriterUtil.flushJsAlertAndRedirect(response, "수정이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							} else {
								WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
								return null;
							}
						} else { 
							WriterUtil.flushJsAlertAndRedirect(response, "수정 대상 기간이 아닙니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
							return null;
						}
					} else {
						moveReserveService.resUp(moveReserveVO);
								
						WriterUtil.flushJsAlertAndRedirect(response, "수정이 완료되었습니다.", "document.location.href='/bmc/moveRes/dateConfirm.do';");
						return null;
					}
				}
			}
		}
	
	}
	
	/************ 시간 선택 확인 ************/
	@RequestMapping("/bmc/moveRes/chkTime.do")
	public void chkTime(@ModelAttribute("MoveReserveVO") MoveReserveVO moveReserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		List<MoveReserveVO> resultList = moveReserveService.chkTime(moveReserveVO);
		
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
