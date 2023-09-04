package egovframework.portal.sys.dynamic.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.emory.mathcs.backport.java.util.Arrays;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.service.DynamicDataMngService;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 동적 현황관리 CONTROLLER
 *
 * @author J.Ryeon Lee
 * @since 2016.05.04
 */
@Controller
public class DynamicDataMngController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected DataMngCreatorService dataMngCreatorService;
	@Autowired
	protected DynamicDataMngService dynamicDataMngService;

	private final Logger LOGGER = LogManager.getLogger();

	/** 현황관리 목록 */
	@RequestMapping("/sys/dataMng/{urlName}/list.do")
	public String list(@RequestParam Map<String, String> searchVO, @PathVariable String urlName, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
		String tableName = dataMngCreatorService.getTableName(urlName);

		searchVO.put("tableName", tableName);
		DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
		if (tableDef != null) {

			/* PAGINATION SETTING */

			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			searchVO.put("firstIndex", (PaginationInfoUtil.calFirstIndex(page)) + "");
			searchVO.put("lastIndex", (PaginationInfoUtil.calLastIndex(page)) + "");
			searchVO.put("orderColName", tableDef.getOrderColName());
			searchVO.put("orderType", tableDef.getOrderType());
			searchVO.put("sys", "sys");

			int totalCnt = dynamicDataMngService.getTotalCnt(searchVO);
			model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
			model.addAttribute("page", page);

			/* VIEW PARAMETER SETTING */
			model.addAttribute("result", dynamicDataMngService.getList(searchVO, tableDef));
			model.addAttribute("resultCnt", totalCnt);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
			model.addAttribute("tableDef", tableDef);

			if (StringUtil.isBlank(tableDef.getSysListPath())) {
				return "/sys/dynamic/data/list";
			} else {
				return "/sys/dynamic/data/custom/list";
			}
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "404");
		return null;
	}

	/** 현황관리 헤더 가이드 업데이트 */
	@RequestMapping("/sys/dataMng/headerModifyProc.do")
	public String headerModifyProc(@ModelAttribute("searchVO") DataMngCreatorVO searchVO, @RequestParam String mId, HttpServletResponse response) throws Exception {
		dataMngCreatorService.updateHeader(searchVO);
		return "redirect:/sys/dataMng/" + searchVO.getUrlName() + "/list.do?mId=" + mId;
	}

	/** 현황관리 등록/수정 */
	@RequestMapping({ "/sys/dataMng/{urlName}/write.do", "/sys/dataMng/{urlName}/modify.do" })
	public String modify(@RequestParam Map<String, String> searchVO, @PathVariable String urlName, @RequestParam(required = false) String idx, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
		String tableName = dataMngCreatorService.getTableName(urlName);
		searchVO.put("tableName", tableName);
		DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
		if (tableDef != null) {
			model.addAttribute("entity", dynamicDataMngService.getEntity(tableName, idx)); // to modify
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("tableDef", tableDef);
			return "/sys/dynamic/data/modify";
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "404");
		return null;
	}

	/** 현황관리 등록 Proc */
	@RequestMapping(value = "/sys/dataMng/{urlName}/writeProc.do", method = RequestMethod.POST)
	public String writeProc(@RequestParam Map<String, String> searchVO, @PathVariable String urlName, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
		String tableName = dataMngCreatorService.getTableName(urlName);
		DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
		if (tableDef != null) {
			dynamicDataMngService.insert(multiRequest, tableDef, searchVO);
			return "redirect:/sys/dataMng/" + urlName + "/list.do?mId=" + mId;
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "404 Not Found");
		return null;
	}

	/** 현황관리 수정 Proc */
	@RequestMapping(value = "/sys/dataMng/{urlName}/modifyProc.do", method = RequestMethod.POST)
	public String modifyProc(@RequestParam Map<String, String> searchVO, @PathVariable String urlName, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
		String tableName = dataMngCreatorService.getTableName(urlName);
		DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
		Map<String, String> entity = tableDef != null ? dynamicDataMngService.getEntity(tableName, searchVO.get("IDX")) : null;
		if (entity != null) {
			String redirect = StringUtil.changeBlankToNull(searchVO.remove("redirect"));
			searchVO.remove("redirect");
			dynamicDataMngService.update(multiRequest, tableDef, searchVO);
			if (StringUtil.isBlank(redirect)) {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("page", searchVO.get("page"));
				paramMap.put("searchType", searchVO.get("searchType"));
				paramMap.put("searchTxt", searchVO.get("searchTxt"));
				WriterUtil.flushJsPostRedirect(response, "통합 관리자", "", "/sys/dataMng/" + urlName + "/list.do?mId=" + mId, paramMap);
				return null;
			} else {
				return "redirect:" + redirect;
			}
		}

		WriterUtil.flushJsAlertAndHistoryBack(response, "404 Not Found");
		return null;
	}

	/** 현황관리 삭제 Proc */
	@ResponseBody
	@RequestMapping(value = "/sys/dataMng/{urlName}/deleteProc.do", method = RequestMethod.POST)
	public String deleteProc(@RequestParam Map<String, String> searchVO, @PathVariable String urlName, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String tableName = dataMngCreatorService.getTableName(urlName);
		DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
		Map<String, String> entity = tableDef != null ? dynamicDataMngService.getEntity(tableName, searchVO.get("IDX")) : null;
		if (entity != null) {
			dynamicDataMngService.delete(tableDef, entity, searchVO);
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString();
	}

	/** 엑셀 파일 업로드창 */
	@RequestMapping("/sys/dataMng/{urlName}/excelUp.do")
	public String excelUp(@PathVariable String urlName, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tableName = dataMngCreatorService.getTableName(urlName);
		model.addAttribute("mId", mId);
		model.addAttribute("tableName", tableName);
		model.addAttribute("urlName", urlName);
		return "/sys/dynamic/data/excelUp";
	}

	/** 엑셀 업로드 */
	@ResponseBody
	@RequestMapping(value = "/sys/dataMng/{urlName}/excelUpload.do", produces = "application/json; charset=utf-8")
	public String excelUpload(@PathVariable String urlName, @RequestParam("mode") String mode, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		String tableName = dataMngCreatorService.getTableName(urlName);
		JSONObject result = new JSONObject().put("success", Boolean.FALSE);

		DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
		if (tableDef != null) {
			try {
				int cnt = dynamicDataMngService.uploadExcel(request.getFile("excelFile"), tableDef, mode);
				result.put("success", Boolean.TRUE)
					.put("cnt", cnt);
			} catch (Exception e) {
				result.put("cnt", -1);
			}
		}

		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/sys/dataMng/{urlName}/batchDeleteProc.do", method = RequestMethod.POST)
	public String batchDeleteProc(@PathVariable String urlName, @RequestParam String idxs) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		try {
			String tableName = dataMngCreatorService.getTableName(urlName);
			DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
			if (tableDef != null) {
				dynamicDataMngService.batchDelete(tableDef, Arrays.asList(idxs.split(",")));
				rtn.put("success", Boolean.TRUE);
			}
		} catch (Exception e) {
			LOGGER.error(">> failed dynamic batch delete process", e);
		}

		return rtn.toString();
	}

}
