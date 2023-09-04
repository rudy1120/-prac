package egovframework.portal.common.web;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 공통 서비스 컨트롤러 클래스
 *
 * <pre>
 * &lt;&lt;개정이력(Modification Information)&gt;&gt;
 * 2013.09.30 박동환
 * 최초 생성
 *
 * </pre>
 *
 * @author 개발팀 박동환
 * @since 2013.09.30
 * @version 1.0
 */
@Controller("CommonController")
public class CommonController {

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	/**
	 * 공통 컨텐츠 로딩
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public ModelMap commonDataCreater(HttpServletRequest request
		, HttpServletResponse response
		, ModelMap model) throws UnsupportedEncodingException {

		return model;
	}

}