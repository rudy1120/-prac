package egovframework.portal.sys.MenuMng.web;

import java.io.PrintWriter;

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
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.portal.sys.MenuMng.service.MenuSysMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.content.vo.SysMenuVO;

/**
 * 관리자 - 메뉴관리 Controller 클래스
 *
 * @author 개발팀 엄동건
 * @since 2014-09-25
 * @version 1.0
 * @see
 */

@Controller("MenuSysMngController")
public class MenuSysMngController {

	@Autowired
	protected MenuSysMngService menuSysMngService;
	@Autowired
	protected MenuSysService menuService;

	private final Logger LOGGER = LogManager.getLogger();

	@RequestMapping("/sys/totalAdminMng/menuSysMng/menuList.do")
	public String menuList(@ModelAttribute("searchVO") MenusMngVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String mId = ServletRequestUtils.getStringParameter(request, "mId", "");
		model.addAllAttributes(menuSysMngService.getMenuListMap(searchVO.getSiteCode()));
		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		model.addAttribute("procMid", ServletRequestUtils.getStringParameter(request, "procMid", "")); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.

		return "/sys/totalAdminMng/menuSysMng/list";
	}

	@RequestMapping("/sys/totalAdminMng/menuSysMng/moveMenuProc.do")
	public void moveMenu(@ModelAttribute("searchVO") SysMenuVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moveKind = ServletRequestUtils.getStringParameter(request, "moveKind", "");

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {
			if ("up".equals(moveKind)) {
				if (menuSysMngService.chkIsTop(searchVO)) {
					jSONObject.put("flag", "fail");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "현재 메뉴위치에서 더이상 올릴 수 없습니다.");
					out.println(jSONObject.toString());
				} else {
					menuSysMngService.moveMenuUp(searchVO);
					jSONObject.put("flag", "success");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "메뉴순서올림 처리가 정상적으로 완료되었습니다.");
					out.println(jSONObject.toString());
				}

			} else if ("down".equals(moveKind)) {
				if (menuSysMngService.chkIsBottom(searchVO)) {
					jSONObject.put("flag", "fail");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "현재 메뉴위치에서 더이상 내릴 수 없습니다.");
					out.println(jSONObject.toString());
				} else {
					menuSysMngService.moveMenuDown(searchVO);
					jSONObject.put("flag", "success");
					jSONObject.put("procMid", searchVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
					jSONObject.put("message", "메뉴순서내림 처리가 정상적으로 완료되었습니다.");
					out.println(jSONObject.toString());
				}
			}
		} catch (Exception e) {
			jSONObject.put("flag", "false");
			jSONObject.put("message", "죄송합니다. 오류가 발생했습니다.");

			out.println(jSONObject.toString());
			LOGGER.error(">> failed to run moveMenu/Exception", e);
		}
	}

	@RequestMapping("/sys/totalAdminMng/menuSysMng/deleteMenuProc.do")
	public void deleteMenu(@ModelAttribute("deleteVO") SysMenuVO deleteVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {

			menuSysMngService.deleteMenu(deleteVO);
			jSONObject.put("flag", "success");
			jSONObject.put("procMid", deleteVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
			jSONObject.put("message", "메뉴 삭제가 정상적으로 처리되었습니다.");

			out.println(jSONObject.toString());

		} catch (Exception e) {
			jSONObject.put("flag", "false");
			jSONObject.put("message", "오류발생");

			out.println(jSONObject.toString());
			LOGGER.error(">> failed to run deleteMenu/Exception", e);
		}
	}

	@RequestMapping("/sys/totalAdminMng/menuSysMng/writeMenuProc.do")
	public void writeMenu(@ModelAttribute("writeVO") SysMenuVO writeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writeKind = ServletRequestUtils.getStringParameter(request, "writeKind", "");

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {
			if ("insert".equals(writeKind)) {
				menuSysMngService.insertMenu(writeVO);
				jSONObject.put("writeKind", writeKind);
				jSONObject.put("procMid", writeVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
				jSONObject.put("flag", "success");
				jSONObject.put("message", "메뉴 등록이 정상적으로 처리되었습니다.");

				out.println(jSONObject.toString());
			} else if ("update".equals(writeKind)) {
				menuSysMngService.updateMenu(writeVO);
				jSONObject.put("writeKind", writeKind);
				jSONObject.put("procMid", writeVO.getmId()); // 수정이나 등록, 삭제 후 트리에서 메뉴위치를 찾아가기 위한 용도.
				jSONObject.put("flag", "success");
				jSONObject.put("message", "메뉴 수정이 정상적으로 처리되었습니다.");

				out.println(jSONObject.toString());
			}
		} catch (Exception e) {
			jSONObject.put("flag", "false");
			jSONObject.put("message", "오류 발생");

			out.println(jSONObject.toString());
			LOGGER.error(">> failed to run writeMenu/Exception", e);
		}

	}

	@RequestMapping("/sys/totalAdminMng/menuSysMng/getAddMenuInfo.do")
	public void getAddMenuInfo(@ModelAttribute("searchVO") SysMenuVO searchVO, HttpServletResponse response) throws Exception {
		String menuId = searchVO.getmId();

		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();

		try {
			jSONObject.put("flag", "success");
			jSONObject.put("menuLevel", menuSysMngService.getNextMenuLevel(menuId)); // 메뉴등록시
			jSONObject.put("mId", menuSysMngService.getNextMid(searchVO));

			jSONObject.put("message", "조회성공");

			out.println(jSONObject.toString());
		} catch (Exception e) {
			jSONObject.put("flag", "false");
			jSONObject.put("message", "조회오류");

			out.println(jSONObject.toString());

			LOGGER.error(">> failed to run getAddMenuInfo/Exception", e);
		}

	}

	@RequestMapping("/sys/totalAdminMng/menuSysMng/getEditMenuInfo.do")
	public void getEditMenuInfo(@ModelAttribute("searchVO") SysMenuVO searchVO, HttpServletResponse response) throws Exception {
		// 출력용 변수 설정
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();

		// json 출력용 jsonArray 생성
		JSONObject jSONObject = new JSONObject();
		SysMenuVO resultVO = menuSysMngService.getMenu(searchVO);
		try {
			jSONObject.put("flag", "success");
			jSONObject.put("mId", resultVO.getmId());
			jSONObject.put("menuName", resultVO.getMenuName());
			jSONObject.put("programUrl", resultVO.getProgramUrl());
			jSONObject.put("target", resultVO.getTarget());
			jSONObject.put("isUse", resultVO.getIsUse());
			jSONObject.put("parentIdx", resultVO.getParentIdx());
			jSONObject.put("menuOrder", resultVO.getMenuOrder());
			jSONObject.put("menuLevel", resultVO.getMenuLevel());
			jSONObject.put("menuSummary", resultVO.getMenuSummary());
			jSONObject.put("accessLevelCode", resultVO.getAccessLevelCode());
			jSONObject.put("privacyDataYn", resultVO.getPrivacyDataYn());
			jSONObject.put("message", "조회성공");

			out.println(jSONObject.toString());
		} catch (Exception e) {
			jSONObject.put("flag", "false");
			jSONObject.put("message", "조회오류");

			out.println(jSONObject.toString());

			LOGGER.error(">> failed to run getEditMenuInfo/Exception", e);
		}
	}

}
