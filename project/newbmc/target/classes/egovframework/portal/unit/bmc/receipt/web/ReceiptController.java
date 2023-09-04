package egovframework.portal.unit.bmc.receipt.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import egovframework.portal.unit.bmc.receipt.service.ReceiptService;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class ReceiptController {
	
	@Autowired
	ReceiptService receiptService;
	
	// 1. 채용응시원서접수 리스트 조회
	@RequestMapping("/bmc/receipt/list.do")
	public String list(@ModelAttribute("receiptContVO") ReceiptContVO receiptContVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		int listCutRecord = StringUtil.isNumber("8") ? 8 : Constant.LIST_CUTRECORD;
		receiptContVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		receiptContVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		int totalCnt = receiptService.getTotalCnt(receiptContVO);
		
		List<ReceiptContVO> list = receiptService.getList(receiptContVO);
			
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("page", page);
		model.addAttribute("result", list);
		model.addAttribute("receiptContVO", receiptContVO);
		return "/bmc/unit/receipt/list/";
	}
	
	// 2. 채용응시원서접수 상세보기 
	@RequestMapping("/bmc/receipt/view.do")
	public String view(@ModelAttribute("receiptContVO") ReceiptContVO receiptContVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		ReceiptContVO result = receiptService.getContent(receiptContVO);
		
		// 조회수 UPDATE
		receiptService.setViewCount(receiptContVO); 
		result.setCnt(result.getCnt() + 1);            
		
		model.addAttribute("result", result);
		return "/bmc/unit/receipt/view/";
	}
	
	// 3-1. 채용응시원서접수 등록 화면
	@RequestMapping("/bmc/receipt/register.do")
	public String register(@ModelAttribute("receiptVO") ReceiptVO receiptVO, HttpSession session , HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO userVO = (UserVO) principal;
		
		receiptVO.setTname(userVO.getUserName());
		receiptVO.setTel(userVO.getTel1() + "-" + userVO.getTel2() + "-" + userVO.getTel3());
		
		String tmp1 = request.getParameter("idx");

		if(tmp1 == null) {
			String tmp = (String) session.getAttribute("idx");
			int idx = Integer.parseInt(tmp);
			receiptVO.setIdx(idx);
		} else {
			int idx = Integer.parseInt(tmp1);
			receiptVO.setIdx(idx);
		}
		
		ReceiptContVO result = new ReceiptContVO();
		result.setIdx(receiptVO.getIdx());
		result = receiptService.getContent(result);
		model.addAttribute("result", result);
		
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(receiptVO.getTname() != null && receiptVO.getTel() != null) {
			if(receiptVO.getIdx() == 2) {
				int chkIn = receiptService.termChkIn(receiptVO);
				if(chkIn > 0) {
					Map<String, String> paramMap = new HashMap<>();
					WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "접수한 내역이 있습니다.", "/bmc/receipt/read.do?mId=" + mId, paramMap);
					return null;
				}
			}
			
			if (result != null && date.format(today).compareTo(result.getSdate()) >= 0 &&
					date.format(today).compareTo(result.getEdate()) <= 0) {
				return "/bmc/unit/receipt/register/";
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "진행 중인 게시글이 아닙니다");
				return null;
			}
		} else {
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return null;
		}
	}
	
	// 3-2. 채용응시원서접수 등록
	@RequestMapping("/bmc/receipt/registerProc.do")
	public String registerProc(@ModelAttribute("receiptVO") ReceiptVO receiptVO, MultipartHttpServletRequest request, 
			HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {

		/* 공통 */		
		receiptVO.setTname(request.getParameter("usernm"));
		receiptVO.setTel(request.getParameter("tel"));
		String gbn = receiptVO.getGbn();
		
		/* gbn = A 기능인재접수 */
		String sname = request.getParameter("sname");
		String cntN = request.getParameter("cntN");
		String school = request.getParameter("school");
		int getIdx = receiptVO.getIdx();
		receiptVO.setCntN(Integer.parseInt(cntN));
		receiptVO.setSname(sname);
		receiptVO.setSchool(school);
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(date);
		
		ReceiptVO getGbn = receiptService.getGbn(receiptVO);
		String eDate = getGbn.getEdate();
		
		if(receiptVO.getTname() != null && receiptVO.getTel() != null) {
			
			if(gbn.equals("A")) {
				if(today.compareTo(eDate) > 0) {
					WriterUtil.flushJsAlertAndHistoryBack(response, "마감된 접수 입니다.");
					return null;
				} else {
					int schoolCnt = receiptService.getCheck(receiptVO);
					
					if(schoolCnt > 0) {
						WriterUtil.flushJsAlertAndHistoryBack(response, "이번 년도에 이미 등록한 학교입니다.");
						return null;
					} else {
						
						boolean ret = receiptService.stInsert(request, receiptVO);
						
						if (ret) {				
							Map<String, String> paramMap = new HashMap<>();
							WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "저장되었습니다.", "/bmc/receipt/read.do?idx=" + getIdx + "&mId=" + mId, paramMap);
							return null;
						} else {
							model.addAttribute("error", Boolean.TRUE);
							model.addAttribute("receiptVO", receiptVO);
							WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
							return null;//"/sys/prismMng/list.do?mId="+ mId;
						}
					}
				}
				
			} else if(gbn.equals("B")) {
				if(today.compareTo(eDate) > 0) {
					WriterUtil.flushJsAlertAndHistoryBack(response, "마감된 접수 입니다.");
					return null;
				} else {
					int chkIn = receiptService.termChkIn(receiptVO);
					if(chkIn > 0) {
						Map<String, String> paramMap = new HashMap<>();
						WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "접수한 내역이 있습니다.", "/bmc/receipt/read.do?idx=" + getIdx + "&mId=" + mId, paramMap);
						return null;
					} else {
						boolean ret = receiptService.termInsert(request, receiptVO);
						
						if (ret) {				
							Map<String, String> paramMap = new HashMap<>();
							WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "저장되었습니다.", "/bmc/receipt/read.do?idx=" + getIdx + "&mId=" + mId, paramMap);
							return null;
						} else {
							model.addAttribute("error", Boolean.TRUE);
							model.addAttribute("receiptVO", receiptVO);
							WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
							return null;//"/sys/prismMng/list.do?mId="+ mId;
						}
					}
				}
			} 
		}
		WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다.");
		return null;

	}
	
	// 4-2. 채용응시원서접수 조회 리스트 화면
	@RequestMapping("/bmc/receipt/read.do")
	public String readResult(@ModelAttribute("receiptVO") ReceiptVO receiptVO, @RequestParam String mId, HttpSession session , 
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO userVO = (UserVO) principal;
		
		receiptVO.setTname(userVO.getUserName());
		receiptVO.setTel(userVO.getTel1() + "-" + userVO.getTel2() + "-" + userVO.getTel3());
		
		String tmp1 = request.getParameter("idx");

		if(tmp1 == null) {
			String tmp = (String) session.getAttribute("idx");
			int idx = Integer.parseInt(tmp);
			receiptVO.setIdx(idx);
		} else {
			int idx = Integer.parseInt(tmp1);
			receiptVO.setIdx(idx);
		}
		
		if (receiptVO.getTname() == null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return null;
		} else {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
			int listCutRecord = StringUtil.isNumber("8") ? 8 : Constant.LIST_CUTRECORD;
			receiptVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
			receiptVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
			
			ReceiptVO getGbn = receiptService.getGbn(receiptVO);
			String gbn = getGbn.getGbn();
			
			model.addAttribute("page", page);
			model.addAttribute("type", getGbn);
			model.addAttribute("receiptVO", receiptVO);
			
			if(gbn.equals("A")) {
				List<ReceiptVO> list = receiptService.getReceptionistList(receiptVO);
				
				int totalCnt = receiptService.getReceptionistCnt(receiptVO);
				model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
				model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
				model.addAttribute("result", list);
				return "/bmc/unit/receipt/readPage/";
				
			} else if(gbn.equals("B")) {
				List<ReceiptVO> list = receiptService.read(receiptVO);
				
				int totalCnt = receiptService.getReceptionistCnt(receiptVO);
				model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
				model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
				model.addAttribute("result", list);
				return "/bmc/unit/receipt/readPage/";
			} else {
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
				return null;
			}
			
		}
	}
	
	// 4-3. 채용응시원서접수 수정 페이지 이동
	@RequestMapping("/bmc/receipt/update.do")
	public String update(@ModelAttribute("receiptVO") ReceiptVO receiptVO, ModelMap model,  @RequestParam String mId
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReceiptVO result = receiptService.getInfoB(receiptVO);
		model.addAttribute("result", result);
	
		return "/bmc/unit/receipt/update/";
	}
	
	// 4-3. 채용응시원서접수 기간제직원 수정
	@RequestMapping("/bmc/receipt/updateProc.do")
	public String updateProc(@ModelAttribute("receiptVO") ReceiptVO receiptVO, ModelMap model,  @RequestParam String mId
			, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		boolean chkUp = receiptService.termUp(request, receiptVO);
		
		if (chkUp) {				
			Map<String, String> paramMap = new HashMap<>();
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "저장되었습니다.", "/bmc/receipt/read.do?mId=" + mId, paramMap);
			return null;
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("receiptVO", receiptVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return null;//"/sys/prismMng/list.do?mId="+ mId;
		}

	}
	
	// 4-3. 채용응시원서접수 접수 내역 삭제
	@ResponseBody
	@RequestMapping("/bmc/receipt/deleteProc.do")
	public String deleteProc(@ModelAttribute("receiptVO") ReceiptVO receiptVO, @RequestParam String mId) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		int chkSkillS = receiptService.chkSkillS(receiptVO);
		
		if(chkSkillS > 0) { // 기능인재접수
			String delIdxT = receiptService.deleteT(receiptVO);
			String delIdxS = receiptService.deleteS(receiptVO);
			rtn.put("success", StringUtil.isNotBlank(delIdxS));
			return rtn.toString();
		} else { // 기능인재접수 외 접수
			String delIdxT = receiptService.deleteT(receiptVO);
			rtn.put("success", StringUtil.isNotBlank(delIdxT));
			return rtn.toString();
		}
	}
	
}
