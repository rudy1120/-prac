package egovframework.portal.unit.bmc.buy.web;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.common.Constant;
import egovframework.portal.common.service.CommonService;
import egovframework.portal.unit.bmc.apply.service.ApplySmsService;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.buy.service.BuyService;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class BuyController {
	
	@Autowired
	protected CommonService commonService;
	
	@Autowired
	protected BuyService buyService;
	
	@Autowired
	protected ApplySmsService applySmsService;
	
	// 1. 사용자 분양매물목록 조회
	@RequestMapping("/bmc/buy/list.do")
	public String list(@ModelAttribute("saleVO") SaleVO saleVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		
		int listCutRecord = StringUtil.isNumber("10") ? 10 : Constant.LIST_CUTRECORD;
		saleVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		saleVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		if (saleVO.getSearchArea() == null) {
			saleVO.setSearchArea("8");
		};
		//지역 데이터
		/*List<ApplySmsVO> areaList = applySmsService.getAreaList();*/
		List<ApplySmsVO> areaList = buyService.getAreaList();
		
		//공급용도 데이터
		List<ApplySmsVO> purposeList = applySmsService.getPurposerList();
		
		//분양매물목록 개수
		int totalCnt = buyService.getTotalCnt(saleVO);
		
		//분양매물목록 데이터 조회
		List<SaleVO> list = buyService.getList(saleVO);
		
		// 위치도 가져오기
		String mapInfo = buyService.getMapInfo(saleVO);
		
		model.addAttribute("areaList", areaList);
		model.addAttribute("purposeList", purposeList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("page", page);
		model.addAttribute("result", list);
		model.addAttribute("saleVO", saleVO);
		model.addAttribute("mapInfo", mapInfo);
		
		return "/bmc/unit/buy/list/";
	}
	 
	/**전체  공급용도 
	 * @throws Exception */
	@RequestMapping(value="/bmc/apply/getAllPurposerList.do")
	public void getUses(String idx,
			HttpServletResponse response
	) throws Exception {

		List<ApplySmsVO> resultList = buyService.getAllPurposerList();
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
}
