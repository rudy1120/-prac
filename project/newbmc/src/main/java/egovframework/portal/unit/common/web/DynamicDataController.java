package egovframework.portal.unit.common.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.common.service.CommonService;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.service.DynamicDataMngService;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 동적 현황 사용자단 뷰 CONTROLLER
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 05. 09.	J.Ryeon Lee			최초 생성 및 코딩
 * 2016. 09. 05.	권태성				url 관련 처리 수정
 * 2016. 11. 30.	J.Ryeon Lee			CLOB 데이터 crlf >> br 태그 변환 처리 추가
 * 2017. 01. 06.	J.Ryeon Lee			빌트인 사이트 리퀘스트 매핑 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 11. 30.
 */
@Controller
public class DynamicDataController {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected DataMngCreatorService dataMngCreaterService;
	@Autowired
	protected DynamicDataMngService dynamicDataMngService;

	private static final String RES_BASIC_LIST = "/dynamic/list";
	private static final String RES_BASIC_VIEW = "/dynamic/view";
	private static final String RES_CUSTOM_LIST = "/dynamic/custom/list";
	private static final String RES_CUSTOM_VIEW = "/dynamic/custom/view";

	@RequestMapping("/{root}/{branch}/{urlName}/list.do")
	public String list(@RequestParam Map<String, String> searchVO, @PathVariable String root, @PathVariable String branch, @PathVariable String urlName, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewResource = list(searchVO, StringUtil.concat("/", root, branch), urlName, mId, model, request, response);
		if (viewResource != null) { // viewResource == null인 경우는 렌더링할 데이터가 없으므로 필터
			return viewResource.contains(RES_CUSTOM_LIST) //
				? "/unit/common/" + root + RES_CUSTOM_LIST //
				: "/unit/common/" + root + RES_BASIC_LIST;
		}

		return null;
	}

	/** 동적 현황 정보 목록 (one depth만 지원) */
	@RequestMapping("/{siteCode}/{urlName}/list.do")
	public String list(@RequestParam Map<String, String> searchVO, @PathVariable String siteCode, @PathVariable String urlName, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tableName = dataMngCreaterService.getTableName(urlName);
		commonService.commonDataCreater(request, response, model);
		searchVO.put("tableName", tableName);

		DataMngCreatorVO tableDef = dataMngCreaterService.getEntity(tableName);
		if (tableDef != null) {

			/* PAGINATION SETTING */

			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			searchVO.put("firstIndex", String.valueOf(PaginationInfoUtil.calFirstIndex(page, tableDef.getListCnt())));
			searchVO.put("lastIndex", String.valueOf(PaginationInfoUtil.calLastIndex(page, tableDef.getListCnt())));
			searchVO.put("orderColName", tableDef.getOrderColName());
			searchVO.put("orderType", tableDef.getOrderType());

			int totalCnt = dynamicDataMngService.getTotalCnt(searchVO);
			model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, tableDef.getListCnt(), 10, totalCnt));
			model.addAttribute("page", page);

			/* VIEW PARAMETER SETTING */

			model.addAttribute("result", dynamicDataMngService.getList(searchVO, tableDef));
			model.addAttribute("resultCnt", totalCnt);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, tableDef.getListCnt()));

			model.addAttribute("tableDef", tableDef);
//			model.addAttribute("siteCode", siteCode);

			if (StringUtil.isBlank(tableDef.getListPath())) {
				return "/unit/common/" + siteCode + RES_BASIC_LIST;
			} else {
				return "/unit/common/" + siteCode + RES_CUSTOM_LIST;
			}
		}

		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

	@RequestMapping("/{root}/{branch}/{urlName}/view.do")
	public String view(@RequestParam Map<String, String> searchVO, @PathVariable String root, @PathVariable String branch, @PathVariable String urlName, @RequestParam String idx, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewResource = view(searchVO, root + "/" + branch, urlName, idx, mId, model, request, response);
		if (viewResource != null) { // viewResource == null인 경우는 렌더링할 데이터가 없으므로 필터
			return viewResource.contains(RES_CUSTOM_VIEW) //
				? "/unit/common/" + root + RES_CUSTOM_VIEW //
				: "/unit/common/" + root + RES_BASIC_VIEW;
		}

		return null;
	}

	/** 동적 현황 정보 상세 화면 (one depth만 지원) */
	@RequestMapping("/{siteCode}/{urlName}/view.do")
	public String view(@RequestParam Map<String, String> searchVO, @PathVariable String siteCode, @PathVariable String urlName, @RequestParam String idx, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);
		String tableName = dataMngCreaterService.getTableName(urlName);
		DataMngCreatorVO tableDef = dataMngCreaterService.getEntity(tableName);
		Map<String, String> entity = tableDef != null ? dynamicDataMngService.getEntity(tableDef.getTableName(), idx, Boolean.TRUE) : null;
		if (entity != null) {
			model.addAttribute("tableDef", tableDef);
			model.addAttribute("entity", entity);
			model.addAttribute("searchVO", searchVO);
			if (StringUtil.isBlank(tableDef.getViewPath())) {
				return "/unit/common/" + siteCode + RES_BASIC_VIEW;
			} else {
				return "/unit/common/" + siteCode + RES_CUSTOM_VIEW;
			}
		}

		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

	/** 동적 현황 정보 지도 (one depth만 지원) */
	@RequestMapping("/{siteCode}/{urlName}/map.do")
	public String map(@PathVariable String siteCode, @PathVariable String urlName, @RequestParam String idx, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tableName = dataMngCreaterService.getTableName(urlName);
		DataMngCreatorVO tableDef = dataMngCreaterService.getEntity(tableName);
		Map<String, String> entity = tableDef != null ? dynamicDataMngService.getEntity(tableDef.getTableName(), idx) : null;
		if (entity != null) {
			model.addAttribute("tableDef", tableDef);
			model.addAttribute("entity", entity);
			model.addAttribute("지도 새창 보기");

			return "/unit/common/" + siteCode + "/dynamic/map";
		}

		WriterUtil.flushJSInvalidAccess(response);
		return null;
	}

}
