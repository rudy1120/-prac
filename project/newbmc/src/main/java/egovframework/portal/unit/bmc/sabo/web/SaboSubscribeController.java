package egovframework.portal.unit.bmc.sabo.web;

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

import egovframework.portal.unit.bmc.sabo.service.SaboSubscribeService;
import egovframework.portal.unit.bmc.sabo.vo.SaboSubscribeVO;

@Controller
public class SaboSubscribeController {
	
	@Autowired
	protected SaboSubscribeService saboSubscribeService;
	
	// 사용안함 2022.12.20
	/** 사보 신청 페이지 호출 **/
	@RequestMapping("/bmc/sabo/saboForm.do")
	public String subForm(@ModelAttribute SaboSubscribeVO saboSubscribeVO,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception{
		
		return "/bmc/unit/sabo/saboForm/";
	}
	
	/** 사보 구독 신청 **/
	@RequestMapping(value="/bmc/sabo/subSabo.do")
	public void subWebzine(@ModelAttribute("SaboSubscribeVO") SaboSubscribeVO saboSubscribeVO,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		/** 이전 신청여부 확인 **/
		List<SaboSubscribeVO> resultList = saboSubscribeService.getSaboInfo(saboSubscribeVO);

		String alertMsg = "";
		if(resultList.size() ==0) {
			saboSubscribeService.subSabo(saboSubscribeVO);
			alertMsg = "신청이 완료되었습니다.\n감사합니다.";
		}else {
			alertMsg  = "같은 정보로 신청하신 내역이 존재합니다.\n정보 수정을 원하실 경우 수정 페이지를 사용하시기 바랍니다.";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
		
		
	}
	
	// 사용안함 2022.12.20
	/** 사보 구독정보 수정 페이지 **/
	@RequestMapping(value="/bmc/sabo/saboUpForm.do")
	public String updtWebzine(HttpServletRequest request,
			HttpServletResponse response) throws Exception{			
		return "/bmc/unit/sabo/saboUpForm/";
		 
	}
	
	/** 주소 신청 정보 확인*/
	@RequestMapping(value="/bmc/sabo/chkAddr.do")
	public void chkApply(@ModelAttribute("SaboSubscribeVO") SaboSubscribeVO saboSubscribeVO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		
		/** 이전 신청여부 확인 **/
		List<SaboSubscribeVO> resultList = saboSubscribeService.getUpSaboInfo(saboSubscribeVO);

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
	
	/**주소 신청 정보 수정*/
	@RequestMapping(value="/bmc/sabo/upSabo.do")
	public void upWebzine(@ModelAttribute("SaboSubscribeVO") SaboSubscribeVO saboSubscribeVO,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		String alertMsg="";
		
		saboSubscribeService.upSabo(saboSubscribeVO);
		alertMsg="정보가 수정되었습니다. 감사합니다" ;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
		
	}
	
	// 사용안함 2022.12.20
	@RequestMapping("/bmc/sabo/saboDelForm.do")
	public String delForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				return "/bmc/unit/sabo/saboDelForm/";
				
	}
	
	@RequestMapping(value="/bmc/sabo/delSabo.do")
	public void delWebzine(@ModelAttribute("SaboSubscribeVO") SaboSubscribeVO saboSubscribeVO,
			HttpServletRequest request,
			HttpServletResponse response)  throws Exception{

		/** 이전 신청여부 확인 **/
		List<SaboSubscribeVO> resultList = saboSubscribeService.getDelSaboInfo(saboSubscribeVO);
		
		String alertMsg = "";
		if(resultList.size() ==0) {
			alertMsg  = "신청 이력이 없습니다.";
		}else {
			saboSubscribeService.delSabo(saboSubscribeVO);
			alertMsg = "철회가 완료되었습니다.\n이용해주셔서 감사합니다.";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
		
		
	}
	
}
