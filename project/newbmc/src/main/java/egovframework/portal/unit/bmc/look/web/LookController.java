package egovframework.portal.unit.bmc.look.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.unit.bmc.look.service.LookService;
import egovframework.portal.unit.bmc.look.vo.LookCustomPayVO;

/**
 * 납부금 조회/신청 서비스
 * @author boram
 */
@Controller
public class LookController {

	@Autowired
	LookService lookService;
	
	/** 납부금 조회 **/
	@RequestMapping("/bmc/look/retrieve.do")
	public String retrieve(@ModelAttribute("lookCustomPayVO") LookCustomPayVO lookCustomPayVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		return "/bmc/unit/look/retrieve/";
	}
	
	/**
	 * 납부금 조회 결과화면
	 * 기존에는 성명,생년월일로 조회 -> 휴대폰번호 추가 필요
	 */
	@RequestMapping("/bmc/look/result.do")
	public String result(@ModelAttribute("lookCustomPayVO") LookCustomPayVO lookCustomPayVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		List<LookCustomPayVO> result = new ArrayList<>();
		String address = "";
		if (mId.equals("0308010200")) {
			result = lookService.getList2(lookCustomPayVO);
			address = "result2/";
		} else {
			result = lookService.getList(lookCustomPayVO);
			address = "result/";
		}
		
		
		LookCustomPayVO lookVO = new LookCustomPayVO();
		if (result.size() != 0) {
			lookVO = result.get(result.size() - 1);
		}
		model.addAttribute("result", result);
		model.addAttribute("lookVO", lookVO);
		return "/bmc/unit/look/" + address;
	}
	
	/**
	 * 임대료 수납내역 조회
	 * 차후 문서보안솔루션 도입 후 오픈 예정
	 */
	@RequestMapping("/bmc/look/receiptList.do")
	public String receiptList(@ModelAttribute("lookCustomPayVO") LookCustomPayVO lookCustomPayVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		return "/bmc/unit/look/receiptList/";
	}
	
	/**
	 * 임대료 수납내역 결과화면
	 * UBIS 데이터와 연동되야 함
	 * 차후 문서보안솔루션 도입 후 오픈 예정
	 */
	@RequestMapping("/bmc/look/receiptResult.do")
	public String receiptResult(@ModelAttribute("lookCustomPayVO") LookCustomPayVO lookCustomPayVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		/** test data **/
		/*lookCustomPayVO.setCustNm("황혜란");
		lookCustomPayVO.setResiNo("520109");
		lookCustomPayVO.setPhonNo("010-7714-7123");*/
		
		List<LookCustomPayVO> result = lookService.getReceiptList(lookCustomPayVO);
		System.out.println(result);
		System.out.println(lookCustomPayVO);
		LookCustomPayVO lookVO = new LookCustomPayVO();
		if (result.size() != 0) {
			lookVO = result.get(result.size() - 1);
			lookVO.setSchSdate(lookCustomPayVO.getSchSdate());
			lookVO.setSchEdate(lookCustomPayVO.getSchEdate());
		}
		model.addAttribute("result", result);
		model.addAttribute("lookVO", lookVO);
		return "/bmc/unit/look/receiptResult/";
	}
	
	/**
	 * 임대료 수납내역 결과화면
	 * UBIS 데이터와 연동되야 함
	 * 차후 문서보안솔루션 도입 후 오픈 예정
	 * return jsp 는 디자인 틀 없이 출력용 화면으로 사용.
	 */
	@RequestMapping("/bmc/look/receiptPrint.do")
	public String receiptPrint(@ModelAttribute("lookCustomPayVO") LookCustomPayVO lookCustomPayVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		/** test data **/
		/*lookCustomPayVO.setCustNm("황혜란");
		lookCustomPayVO.setResiNo("520109");
		lookCustomPayVO.setPhonNo("010-7714-7123");*/
		
		List<LookCustomPayVO> result = lookService.getReceiptList(lookCustomPayVO);
		LookCustomPayVO lookVO = new LookCustomPayVO();
		if (result.size() != 0) {
			lookVO = result.get(result.size() - 1);
			lookVO.setSchSdate(lookCustomPayVO.getSchSdate());
			lookVO.setSchEdate(lookCustomPayVO.getSchEdate());
		}
		model.addAttribute("result", result);
		model.addAttribute("lookVO", lookVO);
		return "/bmc/receiptPrint/";
	}
	/** 증명서발급 **/
	@RequestMapping("/bmc/look/certification.do")
	public String certification(@ModelAttribute("lookCustomPayVO") LookCustomPayVO lookCustomPayVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		return "/bmc/unit/look/certification/";
	}
	
}
