package egovframework.portal.unit.common.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.common.service.CommonService;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.WriterUtil;

/**
 * 데이터 선처리용 bridge controller
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2016. 11. 25.	권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2016. 11. 25.
 */
@Controller
public class DynamicDataBridgeController {

	@Autowired
	protected CommonService commonService;
	@Autowired
	protected DataMngCreatorService dataMngCreaterService;

	/** 데이터 선처리가 필요한 데이터들을 조회하기 위한 bridge 페이지 */
	@RequestMapping("/{siteCode}/{urlName}/bridge.do")
	public String dynamicDataBridge(@PathVariable String siteCode, @PathVariable String urlName,
		@RequestParam String mId, @RequestParam String colName, @RequestParam Map<String, String> searchVO,
		ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tableName = dataMngCreaterService.getTableName(urlName);
		searchVO.put("tableName", tableName);
		DataMngCreatorVO tableDef = dataMngCreaterService.getEntity(tableName);
		if (tableDef != null) {
			/*
				필요할때 구현해서 사용
			*/
			return "redirect:/" + siteCode + "/" + urlName + "/list.do?mId=" + mId + "&searchType=" + colName + "&searchTxt=";
		} else {
			WriterUtil.flushJsAlertAndRedirect(response, "올바른 요청이 아닙니다.", "history.back();");
			return null;
		}

	}

}
