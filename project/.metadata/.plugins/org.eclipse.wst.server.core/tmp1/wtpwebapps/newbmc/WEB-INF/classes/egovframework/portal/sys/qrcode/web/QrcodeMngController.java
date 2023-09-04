package egovframework.portal.sys.qrcode.web;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.portal.sys.qrcode.service.QrcodeService;
import egovframework.portal.sys.qrcode.vo.QrcodeVO;

/**
 * QR코드 관리 컨트롤러
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.12.13		권태성				최초 생성 및 코딩
 * 2015.07.02		J.Ryeon Lee			미사용 변수 코멘트화, 소스 포맷 적용
 * </pre>
 *
 * @author 권태성
 * @since 2014.12.13
 */
@Controller
public class QrcodeMngController {

	@Autowired
	protected MenuSysService menuService;
	@Autowired
	protected QrcodeService qrcodeService;

	/**
	 * QR코드 리스트
	 *
	 * @param inputVO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sys/qrcode/qrcodeInfoMng.do")
	public String selectQrcodeDataList(@ModelAttribute("searchVO") QrcodeVO inputVO, @RequestParam("mId") String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		// 기본게시판 정보로 게시물 가져옴
		List<QrcodeVO> resultList = qrcodeService.selectQrcodeDataList();
		int resultCnt = qrcodeService.selectQrcodeDataCnt();

		model.addAttribute("inputVO", inputVO);
		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", resultCnt);
		// model.addAttribute("paginationInfo", paginationInfo);
		// model.addAttribute("listOrder", "");
		// model.addAttribute("pageLink", (new HtmlUtil()).getSiteMngPagingHtml(request, Integer.parseInt((String)map.get("resultCnt")), inputVO.getPageIndex(), 10));

		return "/sys/qrcode/qrcodeList";
	}

	/**
	 * QR코드 세부정보
	 *
	 * @param inputVO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/qrcode/selectQrcodeDataDetail.do")
	public String selectQrcodeDataDetail(@ModelAttribute("searchVO") QrcodeVO inputVO, @RequestParam("mId") String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

//		String view = ServletRequestUtils.getStringParameter(request, "view", "");
		int qrcIdx = inputVO.getQrcIdx();
		QrcodeVO vo = qrcodeService.selectQrcodeDataDetail(qrcIdx);

		model.addAttribute("inputVO", inputVO);
		model.addAttribute("result", vo);

		return "/sys/qrcode/qrcodeDetail";
	}

	/**
	 * QR코드 입력페이지
	 *
	 * @param inputVO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sys/qrcode/moveQrcodeDataInsertPage.do")
	public String moveQrcodeDataInsertPage(@ModelAttribute("searchVO") QrcodeVO inputVO, @RequestParam("mId") String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		String fileName = UUID.randomUUID().toString();

		model.addAttribute("fileName", fileName);
		model.addAttribute("qrcWebFileName", fileName + ".gif");
		model.addAttribute("qrcPrintFileName", fileName + ".png");

		return "/sys/qrcode/qrcodeInsert";
	}

	/**
	 * QR코드 입력처리
	 *
	 * @param request
	 * @param response
	 * @param map
	 * @param inputVO
	 * @throws Exception
	 */
	@RequestMapping("/sys/qrcode/insertQrcodeDataProc.do")
	public void insertQrcodeDataProc(HttpServletRequest request, HttpServletResponse response, ModelMap map, QrcodeVO inputVO) throws Exception {

		response.setContentType("text/plain; charset=euc-kr");
		PrintWriter out = response.getWriter();

		// String returnUrl = request.getHeader("REFERER");

		try {
			qrcodeService.insertQrcodeDataProc(inputVO);

			out.println("{\"result\" : \"success\",\"message\" : \"Qrcode 등록이 완료되었습니다.\"}");
			return;
		} catch (Exception e) {
			out.println("{\"result\" : \"fail\",\"message\" : \"Qrcode 등록이 정상적으로 되지 않았습니다.\"}");
			return;
		}
	}

	/**
	 * QR코드 수정페이지
	 *
	 * @param inputVO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sys/qrcode/moveQrcodeDataUpdatePage.do")
	public String moveQrcodeDataUpdatePage(@ModelAttribute("searchVO") QrcodeVO inputVO, @RequestParam("mId") String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		model.addAllAttributes(menuService.getSysMenuInfoMap(model, mId, request));

		QrcodeVO vo = qrcodeService.selectQrcodeDataDetail(inputVO.getQrcIdx());

		model.addAttribute("result", vo);

		return "/sys/qrcode/qrcodeUpdate";
	}

	/**
	 * QR코드 수정 프로세스
	 *
	 * @param request
	 * @param response
	 * @param map
	 * @param inputVO
	 * @throws Exception
	 */
	@RequestMapping("/sys/qrcode/updateQrcodeDataProc.do")
	public void updateBoardDataProc(HttpServletRequest request, HttpServletResponse response, ModelMap map, QrcodeVO inputVO) throws Exception {

		response.setContentType("text/plain; charset=euc-kr");
		PrintWriter out = response.getWriter();

		try {
			qrcodeService.updateQrcodeDataProc(inputVO);

			out.println("{\"result\" : \"success\",\"message\" : \"Qrcode 수정이 완료되었습니다.\"}");
			return;
		} catch (Exception e) {
			out.println("{\"result\" : \"fail\",\"message\" : \"Qrcode 수정이 정상적으로 되지 않았습니다.\"}");
			return;
		}
	}

	/**
	 * QR코드 삭제 프로세스
	 *
	 * @param request
	 * @param response
	 * @param map
	 * @param inputVO
	 * @throws Exception
	 */
	@RequestMapping("/sys/qrcode/deleteQrcodeDataProc.do")
	public void deleteBoardDataProc(HttpServletRequest request, HttpServletResponse response, ModelMap map, QrcodeVO inputVO) throws Exception {

		response.setContentType("text/plain; charset=euc-kr");
		PrintWriter out = response.getWriter();

		try {
			qrcodeService.deleteQrcodeDataProc(inputVO);

			out.println("{\"result\" : \"success\",\"message\" : \"Qrcode가 삭제 되었습니다\"}");
			return;

		} catch (Exception e) {
			out.println("{\"result\" : \"fail\",\"message\" : \"Qrcode가 정상적으로 삭제 되지 않았습니다.\"}");
			return;
		}
	}

	/**
	 * QR코드 미리보기 페이지
	 *
	 * @param inputVO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sys/qrcode/previewQrcodeInfo.do")
	public String previewQrcodeInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		int qrcIdx = ServletRequestUtils.getIntParameter(request, "qrcIdx", 0);

		QrcodeVO vo = qrcodeService.selectQrcodeDataDetail(qrcIdx);

		if (model != null) {
			model.addAttribute("result", vo);
		}

		return "/sys/qrcode/qrcodePreview";
	}
}