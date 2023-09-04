package egovframework.portal.unit.bmc.event.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;
import egovframework.portal.unit.bmc.event.service.EventService;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class EventController {
	
	@Autowired
	protected EventService eventService;
	
	// 1. 고객참여이벤트 리스트 조회
	@RequestMapping("/bmc/event/list.do")
	public String list(@ModelAttribute("eventContentVO") EventContentVO eventContentVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		int listCutRecord = StringUtil.isNumber("10") ? 10 : Constant.LIST_CUTRECORD;
		eventContentVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		eventContentVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		int totalCnt = eventService.getTotalCnt(eventContentVO);
		
		List<EventContentVO> list = eventService.getList(eventContentVO);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("page", page);
		model.addAttribute("result", list);
		model.addAttribute("eventContentVO", eventContentVO);
		return "/bmc/unit/event/list/";
	}
	
	// 2. 고객참여이벤트 상세보기
	@RequestMapping("/bmc/event/view.do")
	public String view(@ModelAttribute("eventContentVO") EventContentVO eventContentVO, @ModelAttribute("eventParticipantVO") EventParticipantVO eventParticipantVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		EventContentVO result = eventService.getContent(eventContentVO);
		eventParticipantVO.setIdx(result.getIdx());
		
		List<EventParticipantVO> list = eventService.getResult(eventParticipantVO);
		model.addAttribute("result", result);
		model.addAttribute("list", list);
		return "/bmc/unit/event/view/";
	}
	
	@RequestMapping("/bmc/event/register.do")
	public String regProc(@ModelAttribute("eventParticipantVO") EventParticipantVO eventParticipantVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		EventContentVO result = new EventContentVO(); 
		result.setIdx(eventParticipantVO.getIdx());
		result = eventService.getContent(result);
		if (result == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "진행 중인 게시글이 아닙니다");
			return null;
		} else {
			if (result.getProcLvl() == 1) {
				model.addAttribute("result", result);
				return "/bmc/unit/event/register/";
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "진행 중인 게시글이 아닙니다");
				return null;
			}
		}
	}
	
	// 4. 고객참여이벤트 등록
	@RequestMapping("/bmc/event/register2.do")
	public String register(@ModelAttribute("eventParticipantVO") EventParticipantVO eventParticipantVO, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		int check = eventService.getCheck(eventParticipantVO);
		if (check > 0) {
			String returnPath = "document.location.href='/bmc/event/view.do?idx=" + eventParticipantVO.getIdx() + "&mId=" + mId + "';";
			WriterUtil.flushJsAlertAndRedirect(response, "이미 참가한 게시글입니다.", returnPath);
			return null;
		}
		
		String newIdx = eventService.insert(eventParticipantVO, request);
		if (StringUtil.isNotBlank(newIdx)) {
			String returnPath = "document.location.href='/bmc/event/list.do?mId=" + mId + "';";
			WriterUtil.flushJsAlertAndRedirect(response, "등록되었습니다", returnPath);
			return null;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}
	
	//5.응모내역 조회 페이지 이동
	@RequestMapping("/bmc/event/chkapply.do")
	public String checkapply(@ModelAttribute("eventParticipantVO") EventParticipantVO eventParticipantVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) {
		EventContentVO result = new EventContentVO(); 
		result.setIdx(eventParticipantVO.getIdx());
		result = eventService.getContent(result);
		model.addAttribute("result", result);
		return "/bmc/unit/event/chkApply/";
	}
	
	//6.응모내역 조회 
	@RequestMapping("/bmc/event/chkapply2.do")
	public String checkproc(@ModelAttribute("eventParticipantVO") EventParticipantVO eventParticipantVO, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		int check = eventService.getCheck(eventParticipantVO);
		if (check > 0) {
			String returnPath = "document.location.href='/bmc/event/chkapply.do?idx=" + eventParticipantVO.getIdx() + "&mId=" + mId + "';";
			WriterUtil.flushJsAlertAndRedirect(response, "이미 참가한 게시글입니다.", returnPath);
			return null;
		} else {
			String returnPath = "document.location.href='/bmc/event/chkapply.do?idx=" + eventParticipantVO.getIdx() + "&mId=" + mId + "';";
			WriterUtil.flushJsAlertAndRedirect(response, "참가내역이 없습니다", returnPath);
			return null;
			
		}
		
	}
}
