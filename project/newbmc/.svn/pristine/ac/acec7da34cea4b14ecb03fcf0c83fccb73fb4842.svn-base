package egovframework.portal.sys.basic.event.web;

import java.util.List;

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

import egovframework.portal.sys.basic.event.service.SysEventService;
import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;
import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class SysEventController {

	@Autowired
	protected SysEventService eventService;
	
	@RequestMapping("/sys/event/list.do")
	public String list(@ModelAttribute("searchVO") EventContentVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = eventService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("result", eventService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/basic/event/list/";
	}
	
	@RequestMapping({ "/sys/event/write.do", "/sys/event/modify.do" })
	public String modify(@ModelAttribute("searchVO") EventContentVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		EventContentVO entity = eventService.getEntity(searchVO);
		if (entity != null) {
			entity.setSdate(entity.getExedate().split(" ~ ")[0]);
			entity.setEdate(entity.getExedate().split(" ~ ")[1]);
			String pub = entity.getPubdate();
			if (pub.contains(",")) {
				entity.setPubetc(pub.substring(pub.indexOf(",") + 2));
				pub = pub.replace(", " + entity.getPubetc(), "");
			}
			entity.setPubdate(entity.getPubdate().substring(0, pub.indexOf(")") + 1));
			pub = pub.replace(entity.getPubdate(), "");
			entity.setPubtime(pub);
			BeanUtil.copy(entity, searchVO);
		}
		
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/basic/event/update/";
	}
	
	@RequestMapping("/sys/event/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") EventContentVO searchVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String newIdx = eventService.insert(searchVO, request);
		if (StringUtil.isNotBlank(newIdx)) {
			return "redirect:/sys/event/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}
	
	@RequestMapping("/sys/event/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") EventContentVO searchVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String modIdx = eventService.update(searchVO, request);
		if (StringUtil.isNotBlank(modIdx)) {
			return "redirect:/sys/event/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
		return null;
	}

	@ResponseBody
	@RequestMapping("/sys/event/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") EventContentVO searchVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		String delIdx = eventService.delete(searchVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}
	
	@RequestMapping("/sys/event/view.do")
	public String view(@ModelAttribute("searchVO") EventContentVO searchVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		EventContentVO result = eventService.getContent(searchVO);
		EventParticipantVO eventParticipantVO = new EventParticipantVO();
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		eventParticipantVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		eventParticipantVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		eventParticipantVO.setIdx(result.getIdx());
		eventParticipantVO.setLim(result.getLim());
		eventParticipantVO.setIswin(searchVO.getIswin());
		List<EventParticipantVO> list = eventService.getParList(eventParticipantVO);
		
		model.addAttribute("result", result);
		model.addAttribute("list", list);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, result.getParCnt()));
		model.addAttribute("page", page);
		model.addAttribute("totalCnt", result.getParCnt());
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(result.getParCnt(), page));
		
		return "/sys/basic/event/participation/list/";
	}
	
	@ResponseBody
	@RequestMapping("/sys/event/loc.do")
	public String loc(@ModelAttribute("searchVO") EventContentVO searchVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String loc = eventService.loc(searchVO);
		rtn.put("success", StringUtil.isNotBlank(loc));
		return rtn.toString();
	}
	
	/*마감하기*/
	@ResponseBody
	@RequestMapping("/sys/event/loc2.do")
	public String loc2(@ModelAttribute("searchVO") EventContentVO searchVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String loc = eventService.loc2(searchVO);
		rtn.put("success", StringUtil.isNotBlank(loc));
		return rtn.toString();
	}
}
