package egovframework.portal.sys.dynamic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.portal.common.Constant;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.vo.CategoryVO;
import egovframework.portal.sys.dynamic.vo.ColumnDefVO;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.paging.PaginationInfoUtil;

/**
 * 동적 현황관리 명세서/메뉴 생성 CONTROLLER
 *
 * @author J.Ryeon Lee
 * @since 2016.05.03
 */
@Controller
public class DataMngCreatorController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected DataMngCreatorService dataMngCreatorService;

	/** 현황관리 목록 */
	@RequestMapping("/sys/dataMng/list.do")
	public String list(@ModelAttribute("searchVO") DataMngCreatorVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		/* PAGINATION SETTING */

		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));

		int totalCnt = dataMngCreatorService.getTotalCnt(searchVO);
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		model.addAttribute("page", page);

		/* VIEW PARAMETER SETTING */

		model.addAttribute("result", dataMngCreatorService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));

		return "/sys/dynamic/creator/list";
	}

	/** 현황관리 등록/수정 */
	@RequestMapping({ "/sys/dataMng/write.do", "/sys/dataMng/modify.do" })
	public String modify(@ModelAttribute("searchVO") DataMngCreatorVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {

		DataMngCreatorVO origin = dataMngCreatorService.getEntity(searchVO);
		if (origin != null) { // modify
			BeanUtils.copyProperties(origin, searchVO, Constant.IGNORE_PROPERTIES);
			searchVO.setCategory1List(dataMngCreatorService.getCategoryList(new CategoryVO(searchVO.getTableName(), 1)));
			searchVO.setCategory2List(dataMngCreatorService.getCategoryList(new CategoryVO(searchVO.getTableName(), 2)));
		} else { // write
			searchVO.getColumnDefList().add(new ColumnDefVO()); // min value
		}

		model.addAttribute("searchVO", searchVO);
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		return "/sys/dynamic/creator/modify";
	}

	/** 현황관리 등록 Proc */
	@ResponseBody
	@RequestMapping("/sys/dataMng/writeProc.do")
	public String writeProc(ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status, @ModelAttribute("searchVO") @Valid DataMngCreatorVO searchVO, BindingResult result) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldError().getField());
		} else {
			dataMngCreatorService.insert(searchVO);
			rtn.put("success", Boolean.TRUE);
			status.setComplete();
		}

		return rtn.toString();
	}

	/** 현황관리 수정 Proc */
	@ResponseBody
	@RequestMapping("/sys/dataMng/modifyProc.do")
	public String modifyProc(ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status, @ModelAttribute("searchVO") @Valid DataMngCreatorVO searchVO, BindingResult result) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		if (result.hasErrors()) {
			rtn.put("selector", "#" + result.getFieldError().getField());
		} else {
			DataMngCreatorVO origin = dataMngCreatorService.getEntity(searchVO);
			if (origin != null) {
				dataMngCreatorService.update(origin, searchVO);
				rtn.put("success", Boolean.TRUE);
				status.setComplete();
			}
		}

		return rtn.toString();
	}

	/** 현황관리 삭제 */
	@ResponseBody
	@RequestMapping("/sys/dataMng/deleteProc.do")
	public String deleteProc(@ModelAttribute("searchVO") DataMngCreatorVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		DataMngCreatorVO origin = dataMngCreatorService.getEntity(searchVO);
		if (origin != null) {
			dataMngCreatorService.delete(origin);
			rtn.put("success", Boolean.TRUE);
			status.setComplete();
		}

		return rtn.toString();
	}

	/** 테이블명 유일성 체크 */
	@ResponseBody
	@RequestMapping("/sys/dataMng/isUniqueTableName.do")
	public String isUniqueTableName(@RequestParam String tableName, ModelMap model, HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE).put("isUniqueTableName", Boolean.FALSE);

		int cnt = dataMngCreatorService.getTotalCntUsingName(tableName);
		if (cnt < 1) {
			rtn.put("isUniqueTableName", Boolean.TRUE);
		}

		return rtn.put("success", Boolean.TRUE).toString();
	}

	/**
	 * 현황 카테고리 관리
	 * @Method Name : categoryModify
	 * @param tableName
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/dataMng/categoryModify.do")
	public String categoryModify(@RequestParam String tableName, @RequestParam("categoryIdx") String categoryIdx, ModelMap model, HttpServletRequest request, HttpServletResponse response){

		//등록된 카테고리 조회
		model.addAttribute("categoryIdx", categoryIdx);
		return "/sys/dynamic/category/modify";

	}

}