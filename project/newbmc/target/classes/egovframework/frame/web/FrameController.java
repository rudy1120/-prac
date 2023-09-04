package egovframework.frame.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.common.service.CommonService;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일			수정자			수정내용
 *  -------------	------------	---------------------------
 *  2015.10.14		J.Ryeon Lee		최초생성
 *
 * </pre>
 *
 * @since 2015.10.14
 * @author J.Ryeon Lee
 */
@Controller("frameController")
public class FrameController {

	@Autowired
	protected CommonService commonService;

	/** one depth i-Frame */
	@RequestMapping("/portal/frame/{frameName}.do")
	public String frame(@PathVariable String frameName, @RequestParam("mId") String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonService.commonDataCreater(request, response, model);
		return "/portal/frame/" + frameName;
	}

}
