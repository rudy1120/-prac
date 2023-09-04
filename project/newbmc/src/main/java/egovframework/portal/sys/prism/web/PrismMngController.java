package egovframework.portal.sys.prism.web;

import java.util.HashMap;
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
import egovframework.portal.sys.prism.service.PrismMngService;
import egovframework.portal.sys.prism.vo.PrismMngVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 관리자 - 정책연구보고서 게시판 Controller 클래스
 *
 * 
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2019.10.22		박선민				최초 생성
 * 
 * @author 박선민
 * @since 2019.10.22
 */
@Controller("PrismMngController")
public class PrismMngController {

	@Autowired
	protected PrismMngService prismMngService;
	
	//관리자 정책연구보고서 목록 화면 조회
	@RequestMapping("/sys/prismMng/list.do")
	public String list(@ModelAttribute("searchVO") PrismMngVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = prismMngService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("result", prismMngService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/prism/list/";
	}
	
	//관리자 정책연구보고서 수정 및 등록 화면
	@RequestMapping({ "sys/prismMng/write.do", "/sys/prismMng/modify.do" })
	public String modify(@ModelAttribute("prismVO") PrismMngVO prismVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrismMngVO entity = prismMngService.getEntity(prismVO);
		if (entity != null) {
			BeanUtil.copy(entity, prismVO);
		}
		model.addAttribute("prismVO", prismVO);
		
		return "/sys/prism/update/";
	}

	// 관리자 정책연구보고서 등록
	@RequestMapping("/sys/prismMng/writeProc.do")
	public String insertSell(@ModelAttribute("prismVO") PrismMngVO prismVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		System.out.println("request:" + request);
		boolean ret = prismMngService.insert(request, prismVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "저장되었습니다.", "/sys/prismMng/list.do?mId=" + mId, paramMap);
			
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("prismVO", prismVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return "/sys/prismMng/list.do?mId="+ mId;
		}
		
		return null;
	}
	
	//관리자 정책연구보고서 상세 화면 
	@RequestMapping("/sys/prismMngview.do")
	public String view(@ModelAttribute("prismVO") PrismMngVO prismVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		PrismMngVO vo = prismMngService.getEntity(prismVO);

		//조회수 업데이트
		prismMngService.updateCnt(prismVO);
		
		model.addAttribute("element", vo);
		return "/sys/prism/view/";
	}
	
	//관리자 정책연구보고서 수정 처리
	@RequestMapping("/sys/prismMng/modifyProc.do")
	public String updateSale(@ModelAttribute("prismVO") PrismMngVO prismVO, BindingResult result, @RequestParam("mId") String mId, MultipartHttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		boolean ret = prismMngService.modify(request, prismVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "수정되었습니다.", "/sys/prismMng/list.do?mId=" + mId, paramMap);
		
		} else {
			model.addAttribute("error", Boolean.TRUE);
			model.addAttribute("prismVO", prismVO);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");		
			return "/sys/prismMng/list.do?mId="+ mId;
		}
		return null;
	}
	
	//관리자 정책연구보고서 삭제
	@RequestMapping("/sys/prismMng/deleteProc.do")
	public String deleteProc(@ModelAttribute("prismVO") PrismMngVO prismVO,ModelMap model, HttpServletRequest request, HttpServletResponse response, @RequestParam String mId) throws Exception {

		boolean ret = prismMngService.delete(request, prismVO);

		if (ret) {
			Map<String, String> paramMap = new HashMap<>();  
			WriterUtil.flushJsPostRedirect(response, "포탈 관리자", "삭제되었습니다.", "/sys/prismMng/list.do?mId=" + mId, paramMap);
			
		} else {
			model.addAttribute("error", Boolean.TRUE);
			WriterUtil.flushJsAlertAndHistoryBack(response, "처리 중 오류가 발생했습니다");
			return "/sys/prismMng/list.do?mId="+ mId;
		}
		
		return null;
	}
	
}
