package egovframework.portal.unit.bmc.sub.web;

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

import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.sub.service.SubscribeService;
import egovframework.portal.unit.bmc.sub.vo.SubscribeVO;

@Controller
public class SubscribeController {
	
	@Autowired
	protected SubscribeService subscribeService;
	
	// 사용안함 2022.12.20
	/** 웹진 신청 페이지 호출 **/
	@RequestMapping("/bmc/sub/subForm.do")
	public String subForm(@ModelAttribute SubscribeVO subscribeVO,
			HttpServletRequest request, 
			HttpServletResponse response, 
			ModelMap model) throws Exception{
		
		return "/bmc/unit/sub/subForm/";
		
	}
	
	/** 웹진 구독 신청 **/
	@RequestMapping(value="/bmc/sub/subWebzine.do")
	public void subWebzine(@ModelAttribute("subscribeVO") SubscribeVO subscribeVO,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		/** 이전 신청여부 확인 **/
		List<SubscribeVO> resultList = subscribeService.getWebInfo(subscribeVO);
		String alertMsg = "";
		if(resultList.size() ==0) {
			subscribeService.subWebzine(subscribeVO);
			alertMsg = "신청이 완료되었습니다.\n감사합니다.";
		}else {
			alertMsg  = "같은 정보로 신청하신 내역이 존재합니다.\n정보 수정을 원하실 경우 수정 페이지를 사용하시기 바랍니다.";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
		
		
	}
	
	// 사용안함 2022.12.20
	/** 웹진 구독정보 수정 페이지 **/
	@RequestMapping(value="/bmc/sub/updtForm.do")
	public String updtWebzine(HttpServletRequest request,
			HttpServletResponse response) throws Exception{		
		return "/bmc/unit/sub/subUpForm/";
		
	}
	
	/**이메일 신청 정보 확인*/
	@RequestMapping(value="/bmc/sub/chkEmail.do")
	public void chkApply(@ModelAttribute("subscribeVO") SubscribeVO subscribeVO,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{

		/** 이전 신청여부 확인 **/
		List<SubscribeVO> resultList = subscribeService.getUpWebInfo(subscribeVO);

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
	
	/**이메일 신청 정보 수정*/
	@RequestMapping(value="/bmc/sub/upWebzine.do")
	public void upWebzine(@ModelAttribute("subscribeVO") SubscribeVO subscribeVO,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		String alertMsg="";
		
		subscribeService.upWebzine(subscribeVO);
		alertMsg="정보가 수정되었습니다. 감사합니다" ;
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
		
	}
	
	// 사용안함 2022.12.20
	@RequestMapping("/bmc/sub/delForm.do")
	public String delForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				return "/bmc/unit/sub/subDelForm/";
	}
	
	@RequestMapping(value="/bmc/sub/delWebzine.do")
	public void delWebzine(@ModelAttribute("subscribeVO") SubscribeVO subscribeVO,
			HttpServletRequest request,
			HttpServletResponse response)  throws Exception{

		/** 이전 신청여부 확인 **/
		List<SubscribeVO> resultList = subscribeService.getDelWebInfo(subscribeVO);
		
		String alertMsg = "";
		if(resultList.size() ==0) {
			alertMsg  = "신청 이력이 없습니다.";
			
		}else {
			subscribeService.delWebzine(subscribeVO);
			alertMsg = "철회가 완료되었습니다.\n이용해주셔서 감사합니다.";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(alertMsg);
	}
	
}
