package egovframework.com.yhdb.cooltube.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.yhdb.cooltube.service.CooltubeFileMngService;
import egovframework.com.yhdb.cooltube.vo.MovFileVO;
import egovframework.com.yhdb.cooltube.vo.MovThumbFileVO;
import egovframework.portal.util.TUtil;

/**
 * 관리자 - 게시판 Controller 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016-09-29		박동환				최초 생성 및 코딩
 * 
 * </pre>
 *
 * @author 개발팀 박동환
 * @since 2016-09-29
 * @version 1.0
 */
@Controller("CooltubeMngController")
public class CooltubeMngController {

	@Resource(name = "CooltubeFileMngService")
	private CooltubeFileMngService cooltubeFileMngService;
	
	/**
	 * 동영상 썸네일 view 
	 *
	 * @param multiRequest
	 * @param BbsConfigVO
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/com/yhdb/thumbnailFileViewer.do")
	public String selectMovThumbFileViewer(
		@ModelAttribute("searchVO") MovThumbFileVO movThumbFileVO
		, Map<String, Object> commandMap
		, ModelMap model)
	{
		
		// 조회용 파라미터 설정
		String atchFileId = TUtil.nullTobaseStr((String) commandMap.get("param_atchFileId"), "");
		String width = (String) commandMap.get("width");
		String height = (String) commandMap.get("height");
		String mode = (String) commandMap.get("mode");
		String openTag = (String) commandMap.get("openTag");	//이미지 태그를 감싸는 OPEN 태그
		String closeTag = (String) commandMap.get("closeTag");	//이미지 태그를 감싸는 CLOSE 태그
		
		// 공통기능 관련 파라메터 추가
		String menu = (String) commandMap.get("menu");
		String title = (String) commandMap.get("title");
		
		// 입력값 설정
		movThumbFileVO.setAtchFileId(atchFileId);
		movThumbFileVO.setThumbKind("BIG");
		
		MovThumbFileVO result = cooltubeFileMngService.selectMovThumbFileDetail(movThumbFileVO);
		
		
		model.addAttribute("fileVO", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("width", width);
		model.addAttribute("height", height);
		model.addAttribute("mode", mode);
		model.addAttribute("menu", menu);
		model.addAttribute("title", title);
		model.addAttribute("openTag", TUtil.covertXSS(openTag));
		model.addAttribute("closeTag", TUtil.covertXSS(closeTag));
		
		return "com/yhdb/movThumbFileViewer";
	}
	
	/**
	 * 동영상 view 
	 *
	 * @param multiRequest
	 * @param BbsConfigVO
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/com/yhdb/movFileViewer.do")
	public String selectMovFileViewer(
		@ModelAttribute("searchVO") MovFileVO movFileVO
		, Map<String, Object> commandMap
		, ModelMap model) 
	{
		
		// 조회용 파라미터 설정
		String atchFileId = TUtil.nullTobaseStr((String) commandMap.get("param_atchFileId"), "");
		String fileSn = TUtil.nullTobaseStr((String) commandMap.get("param_fileSn"), "");
		String width = (String) commandMap.get("width");
		String height = (String) commandMap.get("height");
		String mode = (String) commandMap.get("mode");
		
		String openTag = (String) commandMap.get("openTag");	//이미지 태그를 감싸는 OPEN 태그
		String closeTag = (String) commandMap.get("closeTag");	//이미지 태그를 감싸는 CLOSE 태그
		
		// 공통기능 관련 파라메터 추가
		String menu = (String) commandMap.get("menu");
		String title = (String) commandMap.get("title");
		
		movFileVO.setAtchFileId(atchFileId);
		movFileVO.setFileSn(fileSn);
		
		// 동영상 정보
		MovFileVO result = cooltubeFileMngService.selectMovFileDetail(movFileVO);
		// 썸네일 정보
		MovThumbFileVO movThumbFileVO = new MovThumbFileVO();
		movThumbFileVO.setAtchFileId(atchFileId);
		movThumbFileVO.setThumbKind("BIG");
		
		List<MovThumbFileVO> thumbResult = cooltubeFileMngService.selectMovThumbFileList(movThumbFileVO);
		
		model.addAttribute("fileVO", result);
		model.addAttribute("thumbFileList", thumbResult);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("fileSn", fileSn);
		model.addAttribute("width", width);
		model.addAttribute("height", height);
		model.addAttribute("mode", mode);
		model.addAttribute("menu", menu);
		model.addAttribute("title", title);
		model.addAttribute("openTag", TUtil.covertXSS(openTag));
		model.addAttribute("closeTag", TUtil.covertXSS(closeTag));
		
		return "com/yhdb/movFileViewer";
	}
	
	
	
	/**
	 * 동영상 상태 view 
	 * @param request
	 * @param respone
	 * @param model
	 * @return
	 */
	@RequestMapping("/com/yhdb/movStateView.do")
	public String selectMovStateView(
		HttpServletRequest request 
		, HttpServletResponse respone
		, ModelMap model
		, @ModelAttribute("searchVO") MovFileVO movFileVO
		, Map<String, Object> commandMap ) 
	{
		String atchFileId = TUtil.nullTobaseStr((String) commandMap.get("param_atchFileId"), "");
		
		movFileVO.setAtchFileId(atchFileId);
		// 동영상 정보
		MovFileVO resultVO = cooltubeFileMngService.selectMovFileDetail(movFileVO);
				
		model.addAttribute("resultVO", resultVO);
		
		return "com/yhdb/movStateView";
	}
	
	/**
	 * 동영상 인코딩 상태 조회
	 * @param request
	 * @param respone
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@RequestMapping("/com/yhdb/getMovProgress.do")
	public String selectMovProgress(
		HttpServletRequest request 
		, HttpServletResponse response
		, @ModelAttribute("searchVO") MovFileVO movFileVO
		, Map<String, Object> commandMap
		, ModelMap model) throws IOException, JSONException 
	{
		// 변수 설정
		float duration = 0.0f;
		float curTime = 0.0f;
		int encStep = 0;
		int failCount = 0;
		String[] fileNewName = null;
		
		// 동영상 정보
		MovFileVO result = cooltubeFileMngService.selectMovFileDetail(movFileVO);
		
		
			
		encStep = Integer.parseInt(result.getEncodingStep());
		failCount = result.getFileCnt();
		fileNewName = result.getStreFileNm().split("\\.");
		
		// 로그 파일 정보 조회
		String logFileName = result.getFileStreCours() + File.separator + "logs"+ File.separator + fileNewName[0] + "_log.txt";
		File logFile = new File(logFileName);
		
		if(logFile.exists()) {
			BufferedReader in = new BufferedReader(new FileReader(logFile));
			
			String s;
			while((s = in.readLine()) != null) {
				
				/** duration 정보 얻기 **/
				String regex = "Duration: (.*?), start:";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(s);
				
				String[] tempValue = null;
				while(matcher.find()) {
					String match = matcher.group();
					
					tempValue = match.split(" ");
				}
				
				if(tempValue != null && tempValue.length > 0) {
					if(!"".equals(tempValue[1])) {
						String[] tmpDuration = tempValue[1].replaceAll(",", "").split(":");
						
						if(!"".equals(tmpDuration[2])) {
							duration += Float.valueOf(tmpDuration[2]);
						}
						if(!"".equals(tmpDuration[1])) {
							duration += Integer.valueOf(tmpDuration[1]) * 60;
						}
						if(!"".equals(tmpDuration[0])) {
							duration += Integer.valueOf(tmpDuration[0]) * 60 * 60;
						}
					}
				}
				/** duration 정보 얻기 **/
				
				/** time 정보 얻기 **/
				regex = "time=(.*?) bitrate";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(s);
				
				tempValue = null;
				while(matcher.find()) {
					String match = matcher.group();
					
					tempValue = match.split(" ");
				}
				
				List<String> lastTime = new ArrayList<String>();
				if(tempValue != null && tempValue.length > 0) {
					String[] tempTime = tempValue[0].split("=");
					if(tempTime != null && tempTime.length > 0) {
						lastTime.add(tempTime[1]);
					}
				}
				
				if(lastTime != null && lastTime.size() > 0) {
					curTime = Float.valueOf((String)lastTime.get(lastTime.size() - 1));
				}
				/** time 정보 얻기 **/
			}
			
			in.close();
		}
			
		
		
		// 출력 데이터
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("encStep", encStep);
		jsonObject.put("failCount", failCount);
		jsonObject.put("curTime", curTime);
		jsonObject.put("duration", duration);
		
		out.println(jsonObject.toString());
		
		return null;
	}
	
	
	/**
	 * 썸네일 변경을 위한 view
	 * @param request
	 * @param respone
	 * @param model
	 * @return
	 */
	@RequestMapping("/com/yhdb/changeMainThumbPop.do")
	public String selectMainThumbListView(
		HttpServletRequest request 
		, HttpServletResponse respone
		, @ModelAttribute("searchVO") MovThumbFileVO movThumbFileVO
		, Map<String, Object> commandMap
		, ModelMap model) 
	{
		String atchFileId = TUtil.nullTobaseStr((String) commandMap.get("param_atchFileId"), "");
		String fileSn = TUtil.nullTobaseStr((String) commandMap.get("param_fileSn"), "");
		
		if (fileSn == null) {
			fileSn = "0";
			
		}
		
		// 입력값 설정
		movThumbFileVO.setAtchFileId(atchFileId);
		movThumbFileVO.setFileSn(fileSn);
		movThumbFileVO.setThumbKind("BIG");
		
		List<MovThumbFileVO> resultList = cooltubeFileMngService.selectMovThumbFileList(movThumbFileVO);
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("atchFileId", atchFileId);
		model.addAttribute("fileSn", fileSn);
		
		return "com/yhdb/changeMainThumbPop";
	}
	
	/**
	 * 대표 썸네일 변경 처리
	 * @param request
	 * @param respone
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@RequestMapping("/com/yhdb/changeMainThumbPopProc.do")
	public String updateMovThumbMain(
		HttpServletRequest request 
		, HttpServletResponse response
		, ModelMap model
		, @ModelAttribute("searchVO") MovThumbFileVO movThumbFileVO
		, Map<String, Object> commandMap ) throws IOException, JSONException 
	{
		// 출력 데이터
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		
		
		try {
			// 동영상 정보 업데이트
			cooltubeFileMngService.updateMovThumbnailMain(movThumbFileVO);
			
			jsonObject.put("flag", "success");
			jsonObject.put("message", "대표썸네일을 변경 하였습니다.");
			
			out.println(jsonObject.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			jsonObject.put("flag", "fail");
			jsonObject.put("message", "대표썸네일 변경 중 오류가 발생하였습니다.");
			
			out.println(jsonObject.toString());
			
		}
		
		return null;
	}
	
}
