package egovframework.portal.sys.buy.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.buy.service.SysBuyService;
import egovframework.portal.unit.bmc.apply.service.ApplySmsService;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class SysBuyController {

	@Autowired
	protected  SysBuyService buyService;
	
	@Autowired
	protected ApplySmsService applySmsService;
	
	//관리자 분양목록 조회
	@RequestMapping("/sys/buy/list.do")
	public String list(@ModelAttribute("searchVO") SaleVO searchVO
			, @RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = buyService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		//분양목록 데이터
		model.addAttribute("result", buyService.getBuyList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/buy/list/";
	}
	
	//분양정보 등록 화면 
	@RequestMapping("/sys/buy/write.do")
	public String writeSell(@ModelAttribute("saleVO") SaleVO saleVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//공급용도 데이터
		List<ApplySmsVO> resultList = buyService.getPurposerList();
		model.addAttribute("resultList", resultList);
		
		return "/sys/buy/update/";
	}
	
	//분양정보 수정 화면 
	@RequestMapping("/sys/buy/update.do")
	public String updateSell(@ModelAttribute("saleVO") SaleVO saleVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SaleVO vo = buyService.getView(saleVO);
		model.addAttribute("saleVO", vo);
		
		//공급용도 데이터
		List<ApplySmsVO> resultList = buyService.getPurposerList();
		model.addAttribute("resultList", resultList);
		
		return "/sys/buy/update/";
	}
	//분양정보 신규 저장 
	@RequestMapping("/sys/buy/sell/writeProc.do")
	public String insertSell(@ModelAttribute("saleVO") SaleVO saleVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		boolean ret = buyService.insertSell(request, saleVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();  
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "저장되었습니다.", "/sys/buy/list.do?mId=" + mId, paramMap);
			
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("saleVO", saleVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return "/sys/buy/list.do?mId="+ mId;
		}
		
		return null;
	}
	//분양정보 수정
	@RequestMapping("/sys/buy/sell/modifyProc.do")
	public String updateSell(@ModelAttribute("saleVO") SaleVO saleVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		boolean ret = buyService.updateSell(request, saleVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "수정되었습니다.", "/sys/buy/view.do?idx="+saleVO.getIdx()+"&mId=" + mId, paramMap);
		
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("saleVO", saleVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");		
			return "/sys/buy/list.do?mId="+ mId;
		}
		return null;
	}
	
	//분양정보 삭제
	@RequestMapping("/sys/buy/deleteProc.do")
	public String deleteSell(@ModelAttribute("saleVO") SaleVO saleVO,ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam String mId) throws Exception {

		boolean ret = buyService.delSell(request, saleVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();  
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "삭제되었습니다.", "/sys/buy/list.do?mId="+ mId, paramMap);
			
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("saleVO", saleVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return  "/sys/buy/list.do?mId="+ mId;
		}
		
		return null;
	}
	
	//분양정보 상세 뷰 화면 
	@RequestMapping("/sys/buy/view.do")
	public String view(@ModelAttribute("saleVO") SaleVO saleVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		SaleVO vo = buyService.getView(saleVO);
		
		/*int insertCnt = vo.getUsescode().split(",").length;
		
		String[] arr = vo.getUsescode().split(",");
		List<String> usesList = new ArrayList();
		
		for(int i=0; i<insertCnt; i++) {
			
			vo.setUsescode(arr[i]);
			usesList.add(arr[i]);
			
		}*/
		List<SaleVO> resultList = buyService.getSaleList(saleVO);

		model.addAttribute("element", vo);
		/*model.addAttribute("usesList", usesList);*/
		model.addAttribute("resultList", resultList);

		//model.addAttribute("siteList", siteMngService.getSiteCodeListWhere(new MenusMngVO()));
		//return "/sys/buy/sale/update/";
		
		return "/sys/buy/view/";
	}
	
	//매물목록 신규 저장 
	@RequestMapping("/sys/buy/sale/writeProc.do")
	public String insertSale(@ModelAttribute("saleVO") SaleVO saleVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		boolean ret = buyService.insertSale(request, saleVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();  
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "저장되었습니다.", "/sys/buy/view.do?idx="+saleVO.getPidx()+"&mId=" + mId, paramMap);
			
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("saleVO", saleVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return "/sys/buy/sale/update.do";
		}
		
		return null;
	}
	
	//매물목록 수정
	@RequestMapping("/sys/buy/sale/modifyProc.do")
	public String updateSale(@ModelAttribute("saleVO") SaleVO saleVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		boolean ret = buyService.updateSale(request, saleVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "수정되었습니다.", "/sys/buy/view.do?idx="+saleVO.getPidx()+"&mId=" + mId, paramMap);
		
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("saleVO", saleVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");		
			return "/sys/buy/sale/update.do";
		}
		return null;
	}
	
	
	//매물목록 데이터 등록 페이지 이동
	@RequestMapping("/sys/buy/saleWrite.do")
	public String saleWrite(@ModelAttribute("saleVO") SaleVO saleVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sidx =  Integer.toString(saleVO.getIdx());
		//공급용도 데이터
		List<ApplySmsVO> usesList = applySmsService.getUsesList(sidx);
		model.addAttribute("usesList", usesList);
		
		saleVO.setSaveType("new");
		//매물목록 데이터
		List<SaleVO> resultList = buyService.getSaleList(saleVO);
		model.addAttribute("resultList", resultList);
		
		saleVO.setPidx(saleVO.getIdx());
		saleVO.setIdx(' ');
		model.addAttribute("saleVO", saleVO);
		
		return "/sys/buy/sale/update/";
	}
	//매물목록 데이터 수정 페이지 이동
	@RequestMapping("/sys/buy/saleUpdate.do")
	public String saleUpdate(@ModelAttribute("saleVO") SaleVO saleVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		char idx = (char) saleVO.getIdx();
		SaleVO entity = buyService.getSale(saleVO);
		
		
		if (entity != null) {
			BeanUtil.copy(entity, saleVO);
		}
		
		//공급용도 데이터
		String sidx =  Integer.toString(saleVO.getPidx());
		List<ApplySmsVO> usesList = applySmsService.getUsesList(sidx);
		model.addAttribute("usesList", usesList);
		
		saleVO.setIdx(saleVO.getPidx());
		saleVO.setSaveType("update");
		//매물목록 데이터
		List<SaleVO> resultList = buyService.getSaleList(saleVO);
		model.addAttribute("resultList", resultList);
		
		saleVO.setIdx(idx);
		model.addAttribute("saleVO", saleVO);
		
		
		return "/sys/buy/sale/update/";
	}
	//분양매물데이터 삭제 
	
	//@ResponseBody 
	@RequestMapping("/sys/buy/saleDeleteProc.do")
	public String deleteProc(@ModelAttribute("saleVO") SaleVO saleVO,ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam String mId) throws Exception {
/*		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		buyService.delSale(saleVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));
		return rtn.toString();		
		*/
		int pidx = saleVO.getPidx();
		boolean ret = buyService.delSale(request, saleVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();  
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "삭제되었습니다.", "/sys/buy/view.do?idx="+pidx+"&mId=" + mId, paramMap);
			
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("saleVO", saleVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return  "/sys/buy/view.do?idx="+pidx+"&mId=" + mId;
		}
		
		return null;
	}
	
	//관리자 분양관리 공급용도 목록 조회
	@RequestMapping("/sys/buy/uses/list.do")
	public String usesList(@ModelAttribute("searchVO") SaleVO searchVO
			, @RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = buyService.getTotalUseCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		//공급용도 데이터
		model.addAttribute("result", buyService.getUsesList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/buy/uses/list/";
	}
	
	//공급용도 등록, 수정 화면 
	@RequestMapping({ "sys/buy/uses/write.do", "/sys/buy/uses/modify.do" })
	public String modifyUses(@ModelAttribute("saleVO") SaleVO saleVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SaleVO entity = buyService.getEntity(saleVO);
		
		if (entity != null) {
			BeanUtil.copy(entity, saleVO);
		}
		model.addAttribute("searchVO", saleVO);
		
		return "/sys/buy/uses/update/";
	}
	
	//공급용도 신규 등록 처리
	@RequestMapping("/sys/buy/uses/writeProc.do")
	public String insertUses(@ModelAttribute("saleVO") SaleVO saleVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		//공급용도 중복 체크
		int chkUses = buyService.chkUses(saleVO);

		if(chkUses==0) {
			boolean ret = buyService.insertUses(request, saleVO);

			if (ret) {
				Map<String, String> paramMap = new HashMap<>();  
				WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "저장되었습니다.", "/sys/buy/uses/list.do?mId=" + mId, paramMap);
				
			} else {
				model.addAttribute("error", Boolean.TRUE);
				model.addAttribute("saleVO", saleVO);
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
				return "/sys/buy/uses/list.do?mId="+ mId;
			}
		}else if(chkUses>0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "해당 공급용도명이 존재합니다.");
			return "/sys/buy/uses/write.do?mId=0117020000";
		}

		return null;
	}
	
	//공급용도 수정
	@RequestMapping("/sys/buy/uses/modifyProc.do")
	public String updateUses(@ModelAttribute("saleVO") SaleVO saleVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		//분양관리 사용 유무 체크
		int chkUses = buyService.chkUses2(saleVO);
		
		if(chkUses==0) {
			boolean ret = buyService.updateUses(request, saleVO);
	
			if (ret) {
				Map<String, String> paramMap = new HashMap<>();
				WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "수정되었습니다.", "/sys/buy/uses/list.do?mId=" + mId, paramMap);
			
			} else {
				model.addAttribute("error", Boolean.TRUE);
				model.addAttribute("saleVO", saleVO);
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");		
				return "/sys/buy/uses/list.do?mId="+ mId;
			}
			
		}else if(chkUses>0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "사용되고 있는 공급용도는 수정할 수 없습니다.");
			return "/sys/buy/uses/list.do?mId="+ mId;
		}
		return null;
		
	}
	
	//공급용도 삭제
	@RequestMapping("/sys/buy/uses/deleteProc.do")
	public String deleteUses(@ModelAttribute("saleVO") SaleVO saleVO,ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam String mId) throws Exception {

		//분양관리 사용 유무 체크
		int chkUses = buyService.chkUses2(saleVO);
		
		if(chkUses==0) {
			boolean ret = buyService.delUses(request, saleVO);
	
			if (ret) {
				Map<String, String> paramMap = new HashMap<>();  
				WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "삭제되었습니다.", "/sys/buy/uses/list.do?mId="+ mId, paramMap);
				
			} else {
				model.addAttribute("error", Boolean.TRUE);
				model.addAttribute("saleVO", saleVO);
				WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
				return  "/sys/buy/uses/list.do?mId="+ mId;
			}
		}else if(chkUses>0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "사용되고 있는 공급용도는 삭제할 수 없습니다.");
			return "/sys/buy/uses/list.do?mId=0117020000";
		}
		
		return null;
	}
}
