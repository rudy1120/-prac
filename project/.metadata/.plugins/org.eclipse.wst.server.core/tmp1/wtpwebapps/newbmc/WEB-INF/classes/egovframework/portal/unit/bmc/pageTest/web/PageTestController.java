package egovframework.portal.unit.bmc.pageTest.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageTestController {
	
	@RequestMapping("/pageTest/test.do")
	public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strKey = "";
			if(request.getParameter("nf_key") != null){
				strKey=request.getParameter("nf_key");
			}
			if(strKey == "" ){
				String cPath =request.getContextPath();
			    response.sendRedirect(cPath+"/test.jsp");
			}
		return "/bmc/pageTest/test/";
	}
	
}
