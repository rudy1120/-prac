package egovframework.portal.sys.dynamic.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.WriterUtil;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2016. 12. 02.	권태성				최초 생성 및 코딩
 * 2017. 01. 13.	J.Ryeon Lee			대기오염도 maxValue 수정
 * </pre>
 *
 * @author 권태성
 * @since 2016. 12. 2.
 */
@Controller
public class DynamicDataMngBridgeController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected DataMngCreatorService dataMngCreatorService;

	/** 현황 Bridge */
	@RequestMapping("/sys/dataMng/{urlName}/bridge.do")
	public String bridge(@PathVariable String urlName, @RequestParam String mId, @RequestParam String colName, @RequestParam Map<String, String> searchVO,
		ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));
		String tableName = dataMngCreatorService.getTableName(urlName);
		searchVO.put("tableName", tableName);
		DataMngCreatorVO tableDef = dataMngCreatorService.getEntity(tableName);
		if (tableDef != null) {
			/*
			 * 필요할때 구현해서 사용
			 */
			return "redirect:/sys/dataMng/" + urlName + "/list.do?mId=" + mId + "&searchType=" + colName + "&searchTxt=";
		} else {
			WriterUtil.flushJsAlertAndHistoryBack(response, "404");
			return null;
		}
	}

}