package egovframework.portal.unit.bmc.apply.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import egovframework.portal.common.service.CommonService;
import egovframework.portal.unit.bmc.apply.service.ApplySmsService;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;

@Controller
public class ApplySmsController {
	
	@Autowired
	protected CommonService commonService;
	
	@Autowired
	protected ApplySmsService applySmsService;
	
	/**분양알림 신청 화면*/
	@RequestMapping("/bmc/apply/applyForm.do")
	public String applyForm (@ModelAttribute ApplySmsVO applySmsVO,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model
	) throws Exception {
		
		//지역 데이터
		//List<ApplySmsVO> resultList = applySmsService.getAreaList();
		
		List<ApplySmsVO> resultList = applySmsService.getPurposerList();
		//서비스 이용기간 데이터
		List<ApplySmsVO> termList = applySmsService.getServiceTermList();

		model.addAttribute("resultList", resultList);
		model.addAttribute("termList", termList);
		return "/bmc/apply/applyForm/";
	}

	/**임대주택 알림 신청 화면*/
	@RequestMapping("/bmc/apply/applyForm2.do")
	public String applyForm2 (@ModelAttribute ApplySmsVO applySmsVO,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model
	) throws Exception {
		
		//주택유형 데이터
		List<ApplySmsVO> resultList = applySmsService.getHousingTypeList();
		//서비스 이용기간 데이터
		List<ApplySmsVO> termList = applySmsService.getServiceTermList();

		model.addAttribute("resultList", resultList);
		model.addAttribute("termList", termList);
		return "/bmc/apply/applyForm2/";
	}
	
	/**지역에 따른 공급용도 
	 * @throws Exception */
	@RequestMapping(value="/bmc/apply/getUses.do")
	public void getUses(String idx,
			HttpServletResponse response
	) throws Exception {

		List<ApplySmsVO> resultList = applySmsService.getUsesList(idx);
		JSONObject rtn = new JSONObject();
		
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
	
	/**분양알림 신청 */
	@RequestMapping(value="/bmc/apply/applySMS.do")
	public void applySMS(@ModelAttribute("applySmsVO") ApplySmsVO applySmsVO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{

		//1.신청자 신청내역이 존재하는지 확인
		List<ApplySmsVO> resultList = applySmsService.getApplyInfo(applySmsVO);
		
		String alertMsg = "";
		if(resultList.size() ==0) {
			//신규 신청자 등록
			applySmsService.insertApply(applySmsVO);
			alertMsg = "신청이 완료되었습니다.\n감사합니다.";
		}else {
			alertMsg  = "같은 번호로 신청하신 내역이 존재합니다.\n정보 수정을 원하실 경우 수정 페이지를 사용하시기 바랍니다.";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
	}
	
	/**분양신청 정보 수정 화면 이동*/
	@RequestMapping(value="/bmc/apply/updtForm.do")
	public String updtForm (HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception {

		//지역 데이터
		//List<ApplySmsVO> resultList = applySmsService.getAreaList();
		List<ApplySmsVO> resultList = applySmsService.getPurposerList();
		//서비스 이용기간 데이터
		List<ApplySmsVO> termList = applySmsService.getServiceTermList();
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("termList", termList);
		
		return "/bmc/apply/updtForm/";
	}
	
	/**임대주택 정보 수정 화면 이동*/
	@RequestMapping(value="/bmc/apply/updtForm2.do")
	public String updtForm2 (HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception {

		//주택유형 데이터
		List<ApplySmsVO> resultList = applySmsService.getHousingTypeList();
		//서비스 이용기간 데이터
		List<ApplySmsVO> termList = applySmsService.getServiceTermList();

		model.addAttribute("resultList", resultList);
		model.addAttribute("termList", termList);
		
		return "/bmc/apply/updtForm2/";
	}

	/**SMS 신청 정보 확인*/
	@RequestMapping(value="/bmc/apply/chkSMS.do")
	public void chkApply(@ModelAttribute("applySmsVO") ApplySmsVO applySmsVO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{

		//신청자 정보 여부
		List<ApplySmsVO> resultList = applySmsService.getApplyInfo(applySmsVO);

		JSONObject rtn = new JSONObject();
		
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

	/**SMS 신청 정보 수정 */
	@RequestMapping(value="/bmc/apply/updtSMS.do")
	public void updtApply(@ModelAttribute("applySmsVO") ApplySmsVO applySmsVO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		
		String alertMsg = "";
		
		//신청 정보 수정
		applySmsService.updateApply(applySmsVO);
		alertMsg = "수정이 완료되었습니다.\n감사합니다.";
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
	
	}
	
	/**분양정보 신청 정보 삭제 화면 이동*/
	@RequestMapping(value="/bmc/apply/delForm.do")
	public String delForm (HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		return "/bmc/apply/delForm/";
	}
	/**임대주택 신청 정보 삭제 화면 이동*/
	@RequestMapping(value="/bmc/apply/delForm2.do")
	public String delForm2 (HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception {

		return "/bmc/apply/delForm2/";
	}
	
	/**SMS 신청 정보 삭제 */
	@RequestMapping(value="/bmc/apply/delSMS.do")
	public void delApply(@ModelAttribute("applySmsVO") ApplySmsVO applySmsVO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		
		String alertMsg = "";
		
		//신청 정보 삭제
		applySmsService.delApply(applySmsVO);
		alertMsg = "철회가 완료되었습니다.\n이용해주셔서 감사합니다.";
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
	
	}
	
	/* 사업안내 BMC 녹색 랜드 마크 */
	@RequestMapping(value="/bmc/apply/green.do")
	public String greenMark (HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception {

		return "/bmc/apply/greenMain/";
	}

	@RequestMapping(value="/bmc/vr/viewVr.do")
	public String viewVr (HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		return "/bmc/vr/";
	}
	
}
