package egovframework.portal.unit.bmc.konantech.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import egovframework.portal.unit.bmc.konantech.web.common.CommonUtil;
import egovframework.portal.unit.bmc.konantech.web.common.SetParameter;
import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;
import egovframework.portal.unit.bmc.konantech.web.service.BoardService;
import egovframework.portal.unit.bmc.konantech.web.service.ContentsService;
import egovframework.portal.unit.bmc.konantech.web.service.FileService;
import egovframework.portal.unit.bmc.konantech.web.service.ImgService;
import egovframework.portal.unit.bmc.konantech.web.service.MenuService;
import egovframework.portal.unit.bmc.konantech.web.service.RestService;
import egovframework.portal.unit.bmc.konantech.web.service.UserinfoService;
import egovframework.portal.unit.bmc.konantech.web.service.VideoService;
import egovframework.portal.util.StringUtil;
import egovframework.portal.common.Constant;
import egovframework.portal.util.paging.PaginationInfoUtil;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;


@Controller
public class SearchController {	
	
	@Value("#{kframework['ppk.maxResult']}") private int ppkMaxResult;
	@Value("#{kframework['ppk.domainNo']}") private int ppkDomainNo;
	
	@Autowired
	private SetParameter setParameter;

	@Autowired
	private RestService restService;
	
	@Autowired
	private CommonUtil commonUtil;

	//메뉴
	@Autowired
	private MenuService menuService;

	//조직/부서
	@Autowired
	private UserinfoService userinfoService;
	
	//게시판
	@Autowired
	private BoardService boardService;
	
	//웹콘텐츠
	@Autowired
	private ContentsService contentsService;
	
	//이미지
	@Autowired
	private ImgService imgService;
	
	//동영상
	@Autowired
	private VideoService videoService;
	
	//파일
	@Autowired
	private FileService fileService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService egovFileService;
	
	/**
	 * 통합검색 페이지 처리 메소드
	 * 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bmc/search/totalSearch.do", method = RequestMethod.GET)
	public String search(@RequestParam Map<String, String> map, Model model, HttpSession session,@RequestParam String mId, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		// Setting Parameter
		ParameterVO params = setParameter.setParameter(map,request);
		
		ResultListVO result;
		int total = 0;				

		//검색단어가 있을경우에만
		if(params.getKwd().length()>0) {
			
			int totalCnt = 0;
			//메뉴 검색 서비스
			if (commonUtil.easyChkEqual("TOTAL,menu", params.getCategory(), ",", false)) {
				
				System.out.println("@@@@@@@@@@@메뉴검색@@@@@@@@@@@");
				result = menuService.search(params);
				if(result != null) {
					total += result.getTotal();
					model.addAttribute("menuRow", result.getRows());
					model.addAttribute("menuList", result.getResult());
					model.addAttribute("menuTotal", result.getTotal());
					totalCnt = (int) result.getTotal();
				}
			}
			
			//조직/부서 검색 서비스
			if (commonUtil.easyChkEqual("TOTAL,userinfo", params.getCategory(), ",", false)) {
				
				System.out.println("@@@@@@@@@@@조직/부서검색@@@@@@@@@@@");
				result = userinfoService.search(params);
				if(result != null) {
					total += result.getTotal();
					model.addAttribute("userinfoRow", result.getRows());
					model.addAttribute("userinfoList", result.getResult());
					model.addAttribute("userinfoTotal", result.getTotal());
					totalCnt = (int) result.getTotal();
				}
			}
			
			// 게시판 검색 서비스
			if (commonUtil.easyChkEqual("TOTAL,board", params.getCategory(), ",", false)) {
				
				System.out.println("@@@@@@@@@@@게시판검색@@@@@@@@@@@");
				result = boardService.search(params);
				if(result != null) {
					total += result.getTotal();
					model.addAttribute("boardRow", result.getRows());
					model.addAttribute("boardList", result.getResult());
					model.addAttribute("boardTotal", result.getTotal());
					totalCnt = (int) result.getTotal();
				}
			}
			//웹콘텐츠 검색 서비스
			if (commonUtil.easyChkEqual("TOTAL,contents", params.getCategory(), ",", false)) {
				
				System.out.println("@@@@@@@@@@@웹콘텐츠검색@@@@@@@@@@@");
				result = contentsService.search(params);
				if(result != null) {
					total += result.getTotal();
					model.addAttribute("contentsRow", result.getRows());
					model.addAttribute("contentsList", result.getResult());
					model.addAttribute("contentsTotal", result.getTotal());
					totalCnt = (int) result.getTotal();
				}
			}
			//이미지 검색 서비스
			if (commonUtil.easyChkEqual("TOTAL,img", params.getCategory(), ",", false)) {
				
				System.out.println("@@@@@@@@@@@이미지검색@@@@@@@@@@@");
				result = imgService.search(params);
				if(result != null) {
					total += result.getTotal();
					model.addAttribute("imgRow", result.getRows());
					model.addAttribute("imgList", result.getResult());
					model.addAttribute("imgTotal", result.getTotal());
					totalCnt = (int) result.getTotal();
				}
			}
			//동영상 검색 서비스
			if (commonUtil.easyChkEqual("TOTAL,video", params.getCategory(), ",", false)) {
				
				System.out.println("@@@@@@@@@@@동영상검색@@@@@@@@@@@");
				result = videoService.search(params);
				if(result != null) {
					total += result.getTotal();
					model.addAttribute("videoRow", result.getRows());
					model.addAttribute("videoList", result.getResult());
					model.addAttribute("videoTotal", result.getTotal());
					totalCnt = (int) result.getTotal();
				}
			}
			
			//파일 검색 서비스
			if (commonUtil.easyChkEqual("TOTAL,file", params.getCategory(), ",", false)) {
				
				System.out.println("@@@@@@@@@@@파일검색@@@@@@@@@@@");
				result = fileService.search(params);
				if(result != null) {
					total += result.getTotal();
					model.addAttribute("fileRow", result.getRows());
					model.addAttribute("fileList", result.getResult());
					model.addAttribute("fileTotal", result.getTotal());
					System.out.println("result.getTotal()" + result.getTotal());
					totalCnt = (int) result.getTotal();
				}
			}
			
			if(!"TOTAL".equals(params.getCategory())) {
				int page = params.getPageNum();
				int listCutRecord = params.getPageSize();

				model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
				model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
				model.addAttribute("page", page);
			}

		}
		

		
		//인기검색어
		List<HashMap<String, String>> ppkList = restService.getPopularKwd_hc(ppkDomainNo, ppkMaxResult);
		System.out.println("인기검색어.." + ppkList);
		model.addAttribute("ppkList", ppkList);	
		model.addAttribute("params", params);	
		model.addAttribute("total", total);
		model.addAttribute("mId", mId);
		return "/bmc/unit/search/total/list/";
	}
	
	
	
}