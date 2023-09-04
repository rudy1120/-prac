package egovframework.portal.main.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.main.SiteCode;
import egovframework.portal.main.service.MainService;
import egovframework.portal.sys.bbs.vo.BbsMngVO;

/**
 * 사이트 메인 CONTROLLER tiles def rule: /{siteCode}/main/
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 20.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 20.
 */

@Controller
public class MainController {

	@Autowired
	protected BbsService bbsService;

	@Resource(name = "CommonService")
	protected CommonService commonService;
	@Autowired
	protected MainService mainService;

	@RequestMapping("/main.do")
	public String portalMain(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		return "redirect:/main.do";
		return "redirect:/bmc/main.do";
	}

	@RequestMapping("/bmc/main.do")
	public String bmcMain(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);
		SecurityContextHolder.getContext().setAuthentication(null); // 세션 초기화
		
		//넷퍼넬 관련. 실서버 적용 시 해당 코드 주석 풀고 해야함!! 20230524
		String strKey = "";
		if(request.getParameter("nf_key") != null){
			strKey=request.getParameter("nf_key");
		}
		if(strKey == "" ){
			String cPath =request.getContextPath();
		    response.sendRedirect(cPath+"/nfChk.jsp");
		}

		BbsMngVO bbsVO = new BbsMngVO();
		bbsVO.setFirstIndex(1);
		bbsVO.setLastIndex(8);
		bbsVO.setPtIdx("0");
		model.addAttribute("mainBoard", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("788"); // 공지사항
		model.addAttribute("boardA", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("768"); // 분양공고
		model.addAttribute("boardB", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("769"); // 임대공고
		model.addAttribute("boardC", bbsService.getMainBbsList(bbsVO));
		// 채용,보상 추가 20.07.09
		bbsVO.setPtIdx("773"); // 채용공고
		model.addAttribute("boardR", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("770"); // 보상공고
		model.addAttribute("boardCP", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("884"); // 입찰정보
		model.addAttribute("boardBD", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("801"); // 보도자료(언론보도)
		model.addAttribute("boardD", bbsService.getMainBbsList(bbsVO));
		bbsVO.setLastIndex(1);
		bbsVO.setPtIdx("867"); // 책자
		model.addAttribute("boardE", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("868"); // 광고
		model.addAttribute("boardF", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("800"); // 영상
		model.addAttribute("boardG", bbsService.getMainBbsList(bbsVO));

		model.addAttribute("bannerList", mainService.getBannerList(SiteCode.BMC, 100));
		model.addAttribute("visualzoneList", mainService.getVisualzoneList(SiteCode.BMC, 100));
		model.addAttribute("popupzoneList", mainService.getPopupzoneList(SiteCode.BMC, 100));

		return "/bmc/main/";
	}

	@RequestMapping("/portal/main.do")
	public String portal(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);

		model.addAttribute("bannerList", mainService.getBannerList(SiteCode.PORTAL, 100));
		model.addAttribute("visualzoneList", mainService.getVisualzoneList(SiteCode.PORTAL, 100));
		model.addAttribute("popupzoneList", mainService.getPopupzoneList(SiteCode.PORTAL, 100));

		return "/portal/main/";
	}

	// 오시리아 메인 호출
	@RequestMapping("/osiria/main.do")
	public String osiria(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);
		
		BbsMngVO bbsVO = new BbsMngVO();
		bbsVO.setFirstIndex(1);
		bbsVO.setLastIndex(5);
		bbsVO.setPtIdx("814");
		model.addAttribute("boardA", bbsService.getMainBbsList(bbsVO));
		bbsVO.setPtIdx("815");
		model.addAttribute("boardB", bbsService.getMainBbsList(bbsVO));

		model.addAttribute("bannerList", mainService.getBannerList(SiteCode.OSIRIA, 100));
		model.addAttribute("visualzoneList", mainService.getVisualzoneList(SiteCode.OSIRIA, 100));
		model.addAttribute("popupzoneList", mainService.getPopupzoneList(SiteCode.OSIRIA, 100));

		return "/osiria/main/";
	}

	// 역사관 메인 호출
	@RequestMapping("/history/main.do")
	public String history(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);
		return "/history/main/";
	}

	// 바다가 메인 호출
	@RequestMapping("/badaga/main.do")
	public String badaga(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);

		return "/bmc/unit/badaga/main/";
	}

	// 인트로 호출
	@RequestMapping("/bmc/hompage/open.do")
	public String recruit(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//commonService.commonDataCreater(request, response, model);

		return "/bmc/recruit/";
	}

	// 영문 메인 호출
	@RequestMapping("/eng/main.do")
	public String engMain(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);
		
		BbsMngVO bbsVO = new BbsMngVO();
		bbsVO.setFirstIndex(1);
		bbsVO.setLastIndex(1);
		bbsVO.setPtIdx("891");
		model.addAttribute("boardA", bbsService.getMainBbsList(bbsVO));
		
		model.addAttribute("bannerList", mainService.getBannerList(SiteCode.PORTAL_EN, 100));
		model.addAttribute("visualzoneList", mainService.getVisualzoneList(SiteCode.PORTAL_EN, 100));
		model.addAttribute("popupzoneList", mainService.getPopupzoneList(SiteCode.PORTAL_EN, 100));		

		return "/eng/main/";
	}

}
