package egovframework.portal.sys.receipt.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import egovframework.portal.sys.receipt.service.SysReceiptService;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class SysReceiptController {
	
	@Autowired
	protected SysReceiptService sysReceiptService;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	// 1. 채용응시원서 관리자 리스트 조회 화면
	@RequestMapping("/sys/receipt/list.do")
	public String list(@ModelAttribute("searchVO") ReceiptContVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = sysReceiptService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("result", sysReceiptService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/receipt/list/";
	}
	
	// 2. 채용응시원서접수 관리자 등록 화면
	@RequestMapping("/sys/receipt/write.do")
	public String wirte(@ModelAttribute("searchVO") ReceiptContVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/sys/receipt/update/";
	}
	
	// 2. 채용응시원서접수 관리자 수정 화면
	@RequestMapping("/sys/receipt/modify.do")
	public String modify(@ModelAttribute("searchVO") ReceiptContVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReceiptContVO entity = sysReceiptService.getEntity(searchVO);
		
		if (entity == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "게시글이 존재하지 않습니다.");
			return null;
		} else {
			if (entity.getSdate().indexOf(" ") > 0) {
				entity.setStime(entity.getSdate().split(" ")[1]);
				entity.setSdate(entity.getSdate().split(" ")[0]);
			}
			if (entity.getEdate().indexOf(" ") > 0) {
				entity.setEtime(entity.getEdate().split(" ")[1]);
				entity.setEdate(entity.getEdate().split(" ")[0]);
			}
			BeanUtil.copy(entity, searchVO);
		}
		
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/receipt/update/";
	}
	
	// 2-1. 채용응시원서 관리자 등록
	@RequestMapping("/sys/receipt/writeProc.do")
	public String writeProc(@ModelAttribute("searchVO") ReceiptContVO searchVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!searchVO.getStime().trim().equals("")) {
			searchVO.setSdate(searchVO.getSdate() + " " + searchVO.getStime());
		}
		if (!searchVO.getEdate().trim().equals("")) {
			searchVO.setEdate(searchVO.getEdate() + " " + searchVO.getEtime());
		}
		try {
			String cont = StringEscapeUtils.unescapeHtml3(searchVO.getCont());
			searchVO.setCont(cont);
			String newIdx = sysReceiptService.insert(searchVO, request);
			if (StringUtil.isNotBlank(newIdx)) {
				return "redirect:/sys/receipt/list.do?mId=" + mId;
			}
	
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return null;
		} catch(Exception e) {
			LOGGER.error(">> sys.receipt.writeProc", e);
		}
		
		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류 발생했습니다.");
		return null;
	}

	// 2-2. 채용응시원서 관리자 수정
	@RequestMapping("/sys/receipt/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") ReceiptContVO searchVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String cont = StringEscapeUtils.unescapeHtml3(searchVO.getCont());
			searchVO.setCont(cont);
			if (!searchVO.getStime().trim().equals("")) {
				searchVO.setSdate(searchVO.getSdate() + " " + searchVO.getStime());
			}
			if (!searchVO.getEtime().trim().equals("")) {
				searchVO.setEdate(searchVO.getEdate() + " " + searchVO.getEtime());
			}
			String modIdx = sysReceiptService.update(searchVO, request);
			if (StringUtil.isNotBlank(modIdx)) {
				return "redirect:/sys/receipt/list.do?mId=" + mId;
			}
	
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return null;
		} catch(Exception e) {
			LOGGER.error(">> sys.receipt.modifyProc", e);
		}
		
		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류 발생했습니다.");
		return null;
	}

	// 3. 채용응시원서 관리자 삭제
	@ResponseBody
	@RequestMapping("/sys/receipt/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") ReceiptContVO searchVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String delIdx = sysReceiptService.delete(searchVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}
	
	// 4. 채용응시원서 관리자 참가자 조회 화면
	@RequestMapping("/sys/receipt/memberList.do")
	public String memberList(@ModelAttribute("searchVO") ReceiptVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReceiptContVO receiptContVO = sysReceiptService.getContent(searchVO.getIdx());
		model.addAttribute("receiptContVO", receiptContVO);
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = sysReceiptService.getReceptionistCnt(searchVO);
		
		List<ReceiptVO> result = sysReceiptService.getReceptionistList(searchVO);
		
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);
		model.addAttribute("result", result);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/sys/receipt/receptionist/list/";
	}
}
