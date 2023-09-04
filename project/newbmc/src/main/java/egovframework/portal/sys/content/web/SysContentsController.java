package egovframework.portal.sys.content.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;

/**
 * 관리자 메뉴 컨텐츠관련 서비스 컨트롤러 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.08.04		김장섭				최초 생성 및 코딩
 * </pre>
 *
 * @author 개발팀 김장섭
 * @since 2016.08.04
 * @version 1.0
 */
@Controller
public class SysContentsController {

	@Autowired
	protected MenuSysService menuSysService;

	private String getReturnPage(SysMenuVO menuVO, HttpServletRequest request, ModelMap model) {
		if (model == null) {
			return null;
		}
		model.clear();
		String programUrl = menuVO.getProgramUrl().replaceAll("&amp;", "&");
		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
		if (admin.getAdminAccessLevelCode() == AuthType.EXTERNAL.getCode()) { // 외부 관리자의 경우 superMng를 변경 처리
			String urls [] = programUrl.split("/");
			if (StringUtil.isNotEmpty(programUrl) && urls[2].equals("superMng")) { // 범용적으로 url이 변경 가능한 경우에만
				urls[2] = admin.getAdminType().getMngSiteCode();
				String url = "/" + StringUtil.concat("/", urls);
				url += url.contains("?") ? "&mId=" + menuVO.getmId() : "?mId=" + menuVO.getmId();
				return "redirect:" + url;
			}
		}

		return "redirect:" + programUrl + addMidParameter(menuVO.getProgramUrl(), menuVO.getmId());
	}

	private String addMidParameter(String url, String mId) {
		if (StringUtil.isBlank(url)) {
			return "";
		} else {
			if (url.contains("?")) {
				return "&mId=" + mId;
			} else {
				return "?mId=" + mId;
			}
		}
	}

	/**
	 * 관리자페이지 컨텐츠 메뉴로 이동한다.
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sys/sysContents.do")
	public String sysMenuContents(HttpServletRequest request, HttpServletResponse response, @RequestParam("mId") String mId, ModelMap model) throws Exception {

		model.addAllAttributes(menuSysService.getSysMenuInfoMap(model, mId, request));

		SysMenuVO menuVO = menuSysService.getMenuInfo(mId);
		model.addAttribute("curUrl", TUtil.curUrl(request));

		return getReturnPage(menuVO, request, model);
	}

}
