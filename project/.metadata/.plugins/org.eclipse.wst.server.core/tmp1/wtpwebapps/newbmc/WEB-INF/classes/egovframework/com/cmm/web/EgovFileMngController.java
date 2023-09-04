package egovframework.com.cmm.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.sys.common.service.FileDownInfoMngService;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.TokenUtil;
import egovframework.portal.util.WriterUtil;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일				수정자				수정내용
 * ---------------		--------------		---------------------------
 * 2009.03.25			이삼섭				최초 생성
 * 2015.10.20			J.Ryeon Lee			원본 파일 view 및 width 비율 썸네일 view 추가 (mode: originView, ratioView) 관련 속성 추가.
 * 2016.07.29			권태성				썸네일 관련 추가
 * </pre>
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 */
@Controller
public class EgovFileMngController {

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	@Autowired
	private FileDownInfoMngService fileDownInfoMngService;

	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 첨부파일에 대한 목록을 조회한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfs.do")
	public String selectFileInfs(
		@ModelAttribute("searchVO") FileVO fileVO,
		Map<String, Object> commandMap,
		ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");
		String delYn = (String) commandMap.get("delYn");
		String pubrYn = (String) commandMap.get("pubrYn");
		String convertYn = (String) commandMap.get("convertYn");
		String updateFlag = (String) commandMap.get("updateFlag");
		String downloadYn = (String) commandMap.get("downloadYn");
		String showDownload = (String) commandMap.get("showDownload");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		for (FileVO r : result) {
			int n = Integer.parseInt(StringUtil.isNumber(r.getFileMg()) ? r.getFileMg() : "0");
			if (n < 1024) r.setFileMgUnit(String.valueOf(n) + "Byte");
			else if (n < 1024000) r.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
			else r.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("pubrYn", pubrYn);
		model.addAttribute("delYn", delYn);
		model.addAttribute("convertYn", convertYn);
		model.addAttribute("updateFlag", updateFlag);
		model.addAttribute("downloadYn", downloadYn);
		model.addAttribute("showDownload", showDownload);

		return "cmm/fms/EgovFileList";
	}

	/**
	 * 첨부파일에 대한 목록을 조회한다.(리스트에서 보는..)
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfs_toList.do")
	public String selectFileInfs2(
		@ModelAttribute("searchVO") FileVO fileVO,
		Map<String, Object> commandMap,
		ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "cmm/fms/EgovFileList_toList";
	}

	/**
	 * 첨부파일 변경을 위한 수정페이지로 이동한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfsForUpdate.do")
	public String selectFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap, /* SessionVO sessionVO, */ ModelMap model, HttpServletRequest request) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");
		String showComment = (String) commandMap.get("param_showComment");
		String showThumbnail = (String) commandMap.get("param_showThumbnail");
		String param_thumbWidth = (String) commandMap.get("param_thumbWidth");
		String param_thumbHeight = (String) commandMap.get("param_thumbHeight");
		String convertYn = (String) commandMap.get("convertYn");
		String showPreviewBtn = (String) commandMap.get("showPreviewBtn");
		String downloadYn = (String) commandMap.get("downloadYn");
		String fileCnId = (String) commandMap.get("fileCnId");
		String fileCnName = (String) commandMap.get("fileCnName");
		String orderEdit = (String) commandMap.get("orderEdit");
		String maxFileNum = (String) commandMap.get("param_maxFileNum");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		for (FileVO r : result) {
			int n = Integer.parseInt(r.getFileMg());
			if (n < 1024) r.setFileMgUnit(String.valueOf(n) + "Byte");
			else if (n < 1024000) r.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
			else r.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
		}

		if ("Y".equals(orderEdit)) {
			TokenUtil.saveToken(request);
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("showComment", StringUtil.isNotBlank(showComment) && showComment.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.TRUE);
		model.addAttribute("showThumbnail", showThumbnail);
		model.addAttribute("thumbWidth", param_thumbWidth);
		model.addAttribute("thumbHeight", param_thumbHeight);
		model.addAttribute("convertYn", convertYn);
		model.addAttribute("showPreviewBtn", StringUtil.isNotBlank(showPreviewBtn) && showPreviewBtn.equals("true") ? Boolean.TRUE : Boolean.FALSE);
		model.addAttribute("downloadYn", downloadYn);
		model.addAttribute("fileCnId", fileCnId);
		model.addAttribute("fileCnName", fileCnName);
		model.addAttribute("orderEdit", orderEdit);
		model.addAttribute("maxFileNum", maxFileNum);

		return "cmm/fms/EgovFileList";
	}

	/**
	 * 첨부파일 변경을 위한 수정페이지로 이동한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfsForUpdateEtc.do")
	public String selectFileInfsForUpdateEtc(
		@ModelAttribute("searchVO") FileVO fileVO,
		Map<String, Object> commandMap,
		// SessionVO sessionVO,
		ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		for (FileVO r : result) {
			int n = Integer.parseInt(r.getFileMg());
			if (n < 1024) r.setFileMgUnit(String.valueOf(n) + "Byte");
			else if (n < 1024000) r.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
			else r.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "cmm/fms/EgovFileListEtc";
	}

	/**
	 * 첨부파일 변경을 위한 수정페이지로 이동한다.2
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfsForUpdate2.do")
	public String selectFileInfsForUpdate2(
		@ModelAttribute("searchVO") FileVO fileVO,
		Map<String, Object> commandMap,
		// SessionVO sessionVO,
		ModelMap model) throws Exception {

		String atchFileIdRe = (String) commandMap.get("param_atchFileIdRe");
		fileVO.setAtchFileId(atchFileIdRe);
		List<FileVO> result = fileService.selectFileInfs(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCntRe", result.size());
		model.addAttribute("atchFileIdRe", atchFileIdRe);

		return "cmm/fms/EgovFileList2";
	}

	/**
	 * 첨부파일에 대한 삭제를 처리한다.
	 *
	 * @param fileVO
	 * @param returnUrl
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/deleteFileInfs.do")
	public String deleteFileInf(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam("returnUrl") String returnUrl,
		// SessionVO sessionVO,
		HttpServletRequest request,
		ModelMap model) throws Exception {

		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		// if (isAuthenticated) {
		fileService.deleteFileInf(fileVO);
		// }

		// --------------------------------------------
		// contextRoot가 있는 경우 제외 시켜야 함
		// --------------------------------------------
		// //return "forward:/cmm/fms/selectFileInfs.do";
		// return "forward:" + returnUrl;

		if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
			return "redirect:" + returnUrl;
		}

		if (returnUrl.startsWith(request.getContextPath())) {
			return "redirect:" + returnUrl.substring(returnUrl.indexOf("/", 1));
		} else {
			return "redirect:" + returnUrl;
		}
		// //------------------------------------------
	}

	/**
	 * 이미지 첨부파일에 대한 목록을 조회한다.
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectImageFileInfs.do")
	public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap,
		// SessionVO sessionVO,
		ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("atchFileId");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectImageFileList(fileVO);

		model.addAttribute("fileList", result);

		return "cmm/fms/EgovImgFileList";
	}

	/**
	 * 첨부파일 변경을 위한 수정페이지로 이동한다.
	 * 추가로 파일의 순서를 수정가능
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileInfsForUpdateAndSort.do")
	public String selectFileInfsForUpdateAndSort(
		@ModelAttribute("searchVO") FileVO fileVO,
		Map<String, Object> commandMap,
		// SessionVO sessionVO,
		ModelMap model) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		for (FileVO r : result) {
			int n = Integer.parseInt(r.getFileMg());
			if (n < 1024) r.setFileMgUnit(String.valueOf(n) + "Byte");
			else if (n < 1024000) r.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
			else r.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "Y");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);

		return "cmm/fms/EgovFileListAndSort";
	}

	/**
	 * 상세 화면에 대한 view
	 *
	 * @param fileVO
	 * @param atchFileId
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/selectFileViewer.do")
	public String selectFileViewer(
		@ModelAttribute("searchVO") FileVO fileVO,
		Map<String, Object> commandMap,
		ModelMap model) throws Exception {

		String atchFileId = TUtil.nullTobaseStr((String) commandMap.get("param_atchFileId"), "");
		String width = (String) commandMap.get("width");
		String height = (String) commandMap.get("height");
		String mode = (String) commandMap.get("mode");
		String pubrYn = (String) commandMap.get("pubrYn");
		String showComment = (String) commandMap.get("showComment"); // true: 이미지 코멘트 출력
		String all = (String) commandMap.get("all"); // true: 모든 첨부파일을 출력  false: 첫번째 첨부파일만 태그로 출력
		String openTag = (String) commandMap.get("openTag"); //이미지 태그를 감싸는 OPEN 태그
		String closeTag = (String) commandMap.get("closeTag"); //이미지 태그를 감싸는 CLOSE 태그
		String insertBr = (String) commandMap.get("insertBr");
		String mainType = (String) commandMap.get("mainType"); //메인에 출력하는 기능인지 (visual, popup 등의 값을 넣어서 사용 / 직링크 생성을 위해 사용)
		String imgHeight = (String) commandMap.get("imgHeight"); //img 태그에 포함되는 height
		String imgWidth = (String) commandMap.get("imgWidth"); //img 태그에 포함되는 width
		String noImgFlush = (String) commandMap.get("noImgFlush"); // noimg를 출력할지 여부
		String defImg = (String) commandMap.get("defImg"); // 이미지가 없는 경우 출력할 대체 이미지 url
//		if(pubrYn.equals("") || pubrYn.equals(null))
//			pubrYn = "N";

		// 공통기능 관련 파라메터 추가
		String menu = (String) commandMap.get("menu");
		String title = (String) commandMap.get("title");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		for (FileVO r : result) {
			//r.setOrignlFileNm(URLEncoder.encode(r.getOrignlFileNm(), "UTF-8")); // 웹표준
			int n = Integer.parseInt(r.getFileMg());
			if (n < 1024) r.setFileMgUnit(String.valueOf(n) + "Byte");
			else if (n < 1024000) r.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
			else r.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("width", width);
		model.addAttribute("height", height);
		model.addAttribute("mode", mode);
		model.addAttribute("menu", menu);
		model.addAttribute("title", title);
		model.addAttribute("pubrYn", pubrYn);
		model.addAttribute("showComment", StringUtil.isNotBlank(showComment) && showComment.equals("false") ? Boolean.FALSE : Boolean.TRUE);
		model.addAttribute("all", StringUtil.isNotBlank(all) && all.equals("false") ? Boolean.FALSE : Boolean.TRUE);
		model.addAttribute("openTag", TUtil.covertXSS(openTag));
		model.addAttribute("closeTag", TUtil.covertXSS(closeTag));
		model.addAttribute("insertBr", StringUtil.isNotBlank(insertBr) && insertBr.equals("true") ? Boolean.TRUE : Boolean.FALSE);
		model.addAttribute("mainType", mainType);
		model.addAttribute("imgHeight", imgHeight);
		model.addAttribute("imgWidth", imgWidth);
		model.addAttribute("noImgFlush", StringUtil.isNotBlank(noImgFlush) && noImgFlush.equals("false") ? Boolean.FALSE : Boolean.TRUE);
		model.addAttribute("defImg", defImg);

		return "cmm/fms/EgovFileViewer";
	}

/*
 * /**
 * 첨부파일 변경을 위한 수정페이지로 이동한다. (동영상 file_sn)으로 분리
 * @param fileVO
 * @param atchFileId
 * @param sessionVO
 * @param model
 * @return
 * @throws Exception
 *//*
	 * @RequestMapping("/cmm/fms/selectFileInfsForUpdateMovie.do")
	 * public String selectFileInfsForUpdateMovie(
	 * @ModelAttribute("searchVO") FileVO fileVO,
	 * Map<String, Object> commandMap,
	 * // SessionVO sessionVO,
	 * ModelMap model
	 * ) throws Exception {
	 * String atchFileId = (String) commandMap.get("param_atchFileId");
	 * String fileExtSn = (String) commandMap.get("fileExtSn");
	 * fileVO.setAtchFileId(atchFileId);
	 * fileVO.setFileExtsn(fileExtSn);
	 * FileVO result = fileService.selectFileInfMovie(fileVO);
	 * int n = Integer.parseInt(result.getFileMg());
	 * if (n < 1024) result.setFileMgUnit(String.valueOf(n) + "Byte");
	 * else if (n < 1024000) result.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
	 * else result.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
	 * model.addAttribute("fileList", result);
	 * model.addAttribute("updateFlag", "Y");
	 * model.addAttribute("fileListCnt", 1);
	 * model.addAttribute("atchFileId", atchFileId);
	 * return "cmm/fms/EgovFileMovie";
	 * }
	 */

	@RequestMapping("/cmm/fms/fileInfo.do")
	public String fileInfo(
		@ModelAttribute("searchVO") FileVO fileVO,
		Map<String, Object> commandMap,
		ModelMap model) throws Exception {

		String atchFileId = TUtil.nullTobaseStr((String) commandMap.get("attchId"), "");
		String fileSn = (String) commandMap.get("fileSn");
		String mode = (String) commandMap.get("mode");
		String showFileName = (String) commandMap.get("showFileName");

		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO file = fileService.selectFileInf(fileVO);
		int downloadCnt = fileDownInfoMngService.selectFileDownCount(fileVO);
		model.addAttribute("mode", mode);
		model.addAttribute("file", file);
		model.addAttribute("downloadCnt", downloadCnt);
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("fileSn", fileSn);
		model.addAttribute("showFileName", showFileName);

		return "cmm/fms/fileInfo";
	}

	/**
	 * 파일 순서변경
	 *
	 * @param atchFileId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmm/cms/fileOrder.do", method = RequestMethod.GET)
	public String fileOrderGet(@RequestParam("atchFileId") String atchFileId, @RequestParam("TOKEN_KEY") String token,
		@RequestParam(value = "type", defaultValue = "", required = false) String type,
		@RequestParam(value = "showThumbnail", defaultValue = "", required = false) String showThumbnail,
		@RequestParam(value = "thumbWidth", defaultValue = "", required = false) String thumbWidth,
		@RequestParam(value = "thumbHeight", defaultValue = "", required = false) String thumbHeight,
		@RequestParam(value = "fileCnt", defaultValue = "", required = false) String fileCnt,
		HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

//		if (!TokenUtil.isTokenValid(request)) {
//			WriterUtil.flushJsAlertAndHistoryBack(response, "올바르지 않은 요청입니다.");
//			return null;
//		}

		if (!"".equals(type)) {
//			TokenUtil.resetToken(request);
			model.addAttribute("type", type);
		} else {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchFileId);
			List<FileVO> fileList = fileService.selectFileInfs(fileVO);
			for (FileVO r : fileList) {
				int n = Integer.parseInt(r.getFileMg());
				if (n < 1024) r.setFileMgUnit(String.valueOf(n) + "Byte");
				else if (n < 1024000) r.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
				else r.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
			}

			/* TokenUtil.resetToken(request); */
			model.addAttribute("fileList", fileList);
		}

		model.addAttribute("fileCnt", fileCnt);
		model.addAttribute("thumbWidth", thumbWidth);
		model.addAttribute("thumbHeight", thumbHeight);
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("showThumbnail", showThumbnail);

		return "cmm/fms/fileOrder";

	}

	@ResponseBody
	@RequestMapping(value = "/cmm/cms/fileOrder.do", method = RequestMethod.POST)
	public String fileOrderPost(@RequestParam("attchFileId") String atchFileId, @RequestParam("TOKEN_KEY") String token,
		@RequestParam("streFileNm") List<String> streFileNms, @RequestParam("fileSn") List<String> fileSns, HttpServletRequest request, HttpServletResponse response) throws JSONException {

		if (!TokenUtil.isTokenValid(request)) {
			WriterUtil.flushJsAlertAndRedirect(response, "올바르지 않은 요청입니다.", "window.close();");
			return null;
		}

		if (streFileNms.size() != fileSns.size()) {
			WriterUtil.flushJsAlertAndRedirect(response, "파일 개수가 올바르지 않습니다.", "window.close();");
			return null;
		}

		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);

		try {

			//무결성 제약조건 때문에 99*로 변경후에 처리......
			for (int i = 0; i < fileSns.size(); i++) {
				FileVO file = new FileVO();
				file.setAtchFileId(atchFileId);
				file.setStreFileNm(streFileNms.get(i));
				file.setFileSn("99" + fileSns.get(i));
				fileService.updateFileSn(file);
			}

			for (int i = 0; i < fileSns.size(); i++) {
				FileVO file = new FileVO();
				file.setAtchFileId(atchFileId);
				file.setStreFileNm(streFileNms.get(i));
				file.setFileSn(fileSns.get(i));
				fileService.updateFileSn(file);
			}

			rtn.put("success", Boolean.TRUE);

		} catch (Exception e) {
			e.printStackTrace();
			return rtn.toString();

		}

		return rtn.toString();

	}
	
	@RequestMapping("/cmm/fms/selectImg.do")
	public String selectImgView(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap, /* SessionVO sessionVO, */ ModelMap model, HttpServletRequest request) throws Exception {

		String atchFileId = (String) commandMap.get("param_atchFileId");
		String showThumbnail = (String) commandMap.get("param_showThumbnail");
		String param_thumbWidth = (String) commandMap.get("param_thumbWidth");
		String param_thumbHeight = (String) commandMap.get("param_thumbHeight");
		String convertYn = (String) commandMap.get("convertYn");
		String showPreviewBtn = (String) commandMap.get("showPreviewBtn");
		String downloadYn = (String) commandMap.get("downloadYn");
		String fileCnId = (String) commandMap.get("fileCnId");
		String fileCnName = (String) commandMap.get("fileCnName");
		String maxFileNum = (String) commandMap.get("param_maxFileNum");
		String mode = (String) commandMap.get("mode");

		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		for (FileVO r : result) {
			int n = Integer.parseInt(r.getFileMg());
			if (n < 1024) r.setFileMgUnit(String.valueOf(n) + "Byte");
			else if (n < 1024000) r.setFileMgUnit(String.valueOf(Math.round((n / 1024.0) * 10) / 10.0) + " KByte");
			else r.setFileMgUnit(String.valueOf(Math.round((n / 1024000.0) * 10) / 10.0 + " MByte"));
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("showThumbnail", showThumbnail);
		model.addAttribute("thumbWidth", param_thumbWidth);
		model.addAttribute("thumbHeight", param_thumbHeight);
		model.addAttribute("convertYn", convertYn);
		model.addAttribute("showPreviewBtn", StringUtil.isNotBlank(showPreviewBtn) && showPreviewBtn.equals("true") ? Boolean.TRUE : Boolean.FALSE);
		model.addAttribute("downloadYn", downloadYn);
		model.addAttribute("fileCnId", fileCnId);
		model.addAttribute("fileCnName", fileCnName);
		model.addAttribute("maxFileNum", maxFileNum);
		model.addAttribute("mode", mode);

		return "cmm/fms/ImgViewer";
	}
	

}