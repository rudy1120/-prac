package egovframework.portal.sys.basic.participation.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.basic.participation.service.SysParticipationService;
import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class SysParticipationController {
	
	@Autowired
	protected SysParticipationService participationService;
	
	// 1. 고객경영참여 관리자 리스트 조회 화면
	@RequestMapping("/sys/participation/list.do")
	public String list(@ModelAttribute("searchVO") PartContVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = participationService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("result", participationService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/basic/participation/list/";
	}
	
	// 2. 고객경영참여 관리자 수정 및 등록 화면
	@RequestMapping({ "/sys/participation/write.do", "/sys/participation/modify.do" })
	public String modify(@ModelAttribute("searchVO") PartContVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PartContVO entity = participationService.getEntity(searchVO);
		if (entity != null) {
			BeanUtil.copy(entity, searchVO);
		}
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/basic/participation/update/";
	}
	
	// 2-1. 고객경영참여 관리자 등록
	@RequestMapping("/sys/participation/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") PartContVO searchVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String cont = StringEscapeUtils.unescapeHtml3(searchVO.getCont());
		searchVO.setCont(cont);
		String newIdx = participationService.insert(searchVO, request);
		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/sys/participation/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	// 2-2. 고객경영참여 관리자 수정
	@RequestMapping("/sys/participation/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") PartContVO searchVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String cont = StringEscapeUtils.unescapeHtml3(searchVO.getCont());
		searchVO.setCont(cont);
		String modIdx = participationService.update(searchVO, request);
		if (StringUtil.isNotBlank(modIdx)) {
			return "redirect:/sys/participation/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	// 3. 고객경영참여 관리자 삭제
	@ResponseBody
	@RequestMapping("/sys/participation/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") PartContVO searchVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String delIdx = participationService.delete(searchVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}
	
	// 4. 고객경영참여 관리자 참가자 조회 화면
	@RequestMapping("/sys/participation/memberList.do")
	public String memberList(@ModelAttribute("searchVO") ParticipantVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PartContVO partContVO = participationService.getContent(searchVO.getIdx());
		model.addAttribute("partContVO", partContVO);
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = participationService.getParticipantCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", participationService.getParticipantList(searchVO));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/sys/basic/participation/participant/list/";
	}
}
