package egovframework.portal.unit.bmc.participation.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.common.Constant;
import egovframework.portal.unit.bmc.participation.service.ParticipationService;
import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class ParticipationController {
	
	@Autowired
	ParticipationService participationService;
	
	// 1. 고객경영참여 리스트 조회
	@RequestMapping("/bmc/participation/list.do")
	public String list(@ModelAttribute("partContVO") PartContVO partContVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		int listCutRecord = StringUtil.isNumber("8") ? 8 : Constant.LIST_CUTRECORD;
		partContVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		partContVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		int totalCnt = participationService.getTotalCnt(partContVO);
		
		List<PartContVO> list = participationService.getList(partContVO);
			
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("page", page);
		model.addAttribute("result", list);
		model.addAttribute("partContVO", partContVO);
		return "/bmc/unit/participation/list/";
	}
	
	// 2. 고객경영참여 상세보기 
	@RequestMapping("/bmc/participation/view.do")
	public String view(@ModelAttribute("partContVO") PartContVO partContVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		PartContVO result = participationService.getContent(partContVO);
		
		// 조회수 UPDATE
		participationService.setViewCount(partContVO); 
		result.setCnt(result.getCnt() + 1);            
		
		model.addAttribute("result", result);
		return "/bmc/unit/participation/view/";
	}
	
	// 3-1. 고객경영참여 참가자 등록 화면
	@RequestMapping("/bmc/participation/register.do")
	public String register(@ModelAttribute("participantVO") ParticipantVO participantVO, HttpSession session , HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO userVO = (UserVO) principal;
		
		//20.05.12 본인인증 파라미터값 오류로 세션값 사용
		String tmp = (String) session.getAttribute("idx");
		int idx = Integer.parseInt(tmp);
		participantVO.setIdx(idx);
		
		participantVO.setDupinfo(userVO.getPrivatecode());
		int check = participationService.getCheck(participantVO);
		if (check > 0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "이미 참가한 게시글입니다.");
			return null; 
		} else {
			PartContVO result = new PartContVO();
			result.setIdx(participantVO.getIdx());
			result = participationService.getContent(result);
			model.addAttribute("result", result);
			
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			
			if (result != null && date.format(today).compareTo(result.getSdate()) >= 0 &&
					date.format(today).compareTo(result.getEdate()) <= 0) {
				return "/bmc/unit/participation/register/";
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "진행 중인 게시글이 아닙니다");
				return null;
			}
		}
	}
	
	// 3-2. 고객경영참여 참가자 등록
	@RequestMapping("/bmc/participation/registerProc.do")
	public String registerProc(@ModelAttribute("participantVO") ParticipantVO participantVO, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		String newIdx = participationService.insert(participantVO, request);
		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/bmc/participation/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}
	
	// 4-1. 고객경영참여 조회 정보입력 화면
	@RequestMapping("/bmc/participation/read.do")
	public String read(@ModelAttribute("participantVO") ParticipantVO participantVO, ModelMap model, @RequestParam String mId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/bmc/unit/participation/readProc/";
	}
	
	// 4-2. 고객경영참여 조회 리스트 화면
	@RequestMapping("/bmc/participation/readResult.do")
	public String readResult(@ModelAttribute("participantVO") ParticipantVO participantVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (participantVO.getUsernm() == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return null;
		} else {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
			int listCutRecord = StringUtil.isNumber("8") ? 8 : Constant.LIST_CUTRECORD;
			participantVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
			participantVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
			
			List<ParticipantVO> list = participationService.read(participantVO);
			
			int totalCnt = participationService.getParticipantCnt(participantVO);
			model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
			model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
			model.addAttribute("page", page);
			model.addAttribute("result", list);
			model.addAttribute("participantVO", participantVO);
			model.addAttribute("totalCnt", totalCnt);
			return "/bmc/unit/participation/readPage/";
		}
	}
	
	// 4-3. 고객경영참여 참가 등록 삭제
	@ResponseBody
	@RequestMapping("/bmc/participation/deleteProc.do")
	public String deleteProc(@ModelAttribute("participantVO") ParticipantVO participantVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String delIdx = participationService.delete(participantVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));
		return rtn.toString();
	}
	
}
