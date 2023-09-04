package egovframework.portal.unit.bmc.imdae.web;

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

import egovframework.portal.unit.bmc.imdae.service.ImdaeService;
import egovframework.portal.unit.bmc.imdae.vo.ImdaeJiguVO;
import egovframework.portal.unit.bmc.imdae.vo.ImdaeWatingInfoVO;

@Controller
public class ImdaeController {
	
	@Autowired
	ImdaeService imdaeService;
	
	@RequestMapping("/bmc/imdae/list.do")
	public String list(@ModelAttribute("imdaeJiguVO") ImdaeJiguVO imdaeJiguVO, @ModelAttribute("imdaeWatingInfoVO") ImdaeWatingInfoVO imdaeWatingInfoVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		List<ImdaeJiguVO> jiguList = imdaeService.getList(imdaeJiguVO);
		List<ImdaeWatingInfoVO> watingList = new ArrayList<>();
		if (imdaeWatingInfoVO.getUsername() != null) {
			watingList = imdaeService.getWatingList(imdaeWatingInfoVO);
		}
		
		List<ImdaeJiguVO> happyList = new ArrayList<>();
		if(imdaeJiguVO.getGb() == 5) {
			if(imdaeJiguVO.getHgbn() == null) {
				imdaeJiguVO.setHgbn("1");
			}
			happyList = imdaeService.getHappyList(imdaeJiguVO);
		}
		
		model.addAttribute("gb", imdaeJiguVO.getGb());
		model.addAttribute("jiguList", jiguList);
		model.addAttribute("imdaeJiguVO", imdaeJiguVO);
		model.addAttribute("imdaeWatingInfoVO", imdaeWatingInfoVO);
		model.addAttribute("watingList", watingList);
		model.addAttribute("happyList", happyList);
		return "/bmc/unit/imdae/list/";
	}
}
