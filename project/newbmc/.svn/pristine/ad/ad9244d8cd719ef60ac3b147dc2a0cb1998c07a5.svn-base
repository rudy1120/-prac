package egovframework.portal.sys.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.log.service.LoggingConfigHistoryService;
import egovframework.portal.sys.log.service.LoggingConfigService;
import egovframework.portal.sys.log.vo.LoggingConfigVO;
import egovframework.portal.util.SessionUtil;

/**
 * 통합로그 보관 주기 설정 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 8.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 8.
 */
@Controller
public class LoggingConfigController {

	@Autowired
	protected MenuSysService sysMenuService;
	@Autowired
	protected LoggingConfigService configService;
	@Autowired
	protected LoggingConfigHistoryService historyService;

	private final Logger LOGGER = LogManager.getLogger();

	@RequestMapping("/sys/logging/config/list.do")
	public String list(@ModelAttribute("searchVO") LoggingConfigVO searchVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAllAttributes(sysMenuService.getSysMenuInfoMap(model, mId, request));

		model.addAttribute("result", configService.getList(searchVO));
		model.addAttribute("searchVO", searchVO);

		return "/sys/logging/config/list";
	}

	@ResponseBody
	@RequestMapping("/sys/logging/config/modifyProc.do")
	public String modifyProc(@ModelAttribute("searchVO") LoggingConfigVO searchVO, HttpServletRequest request) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		try {
			LoggingConfigVO origin = configService.getEntity(searchVO);
			if (origin != null) {
				configService.update(origin, searchVO, SessionUtil.getAdminInstance(request), SessionUtil.getRemoteAddr(request)); // 설정 변경
				rtn.put("success", Boolean.TRUE);
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}

		return rtn.toString();
	}

}