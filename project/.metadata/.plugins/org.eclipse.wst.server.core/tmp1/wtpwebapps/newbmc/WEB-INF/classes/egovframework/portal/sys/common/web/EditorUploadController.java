package egovframework.portal.sys.common.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.util.SecurityUtil;

/**
 * 공통 tinymce 이미지 업로드 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2019. 6. 05.		김장섭			최초 생성 및 코딩
 * </pre>
 *
 * @author 김장섭
 * @since 2019. 6. 05.
 */
@Controller
public class EditorUploadController {
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;

	/** tinymce 이미지 업로드  */
	@ResponseBody
	@RequestMapping("/sys/editor/image/upload.do")
	public byte[] upload(final MultipartHttpServletRequest multiRequest) throws UnsupportedEncodingException, JSONException, IOException {
			String location = "";
			try {
				if (!multiRequest.getFileMap().isEmpty()) { // 첨부파일 등록
					String attachId = fileUtil.storeFileAndGetAttachId( //
							multiRequest, //
							EgovProperties.getProperty(Constant.EDITOR_FILE_SAVE_DIR_KEY) + "/", //
							"EDITOR_", null //
					);

					location = "/common/imgView.do?attachId="+SecurityUtil.ariaEnc(attachId)+"&fileSn="+SecurityUtil.ariaEnc("0")+"&mode=origin";
				} else {
					return null;
				}

				JSONObject json = new JSONObject();
				json.put("location", location);
				return json.toString().getBytes("UTF-8");
			} catch (Exception e) {
				return null;
			}

	}
	
	@RequestMapping(value="/file_uploader.do")
	 public String file_uploader_html5(
			@RequestParam("Filedata") MultipartFile loadfile,
			 HttpServletRequest request, 
			 HttpServletResponse response){		 
		
		String return1=request.getParameter("callback");
		String return2="?callback_func=" + request.getParameter("callback_func");
		String return3="";
		
		try {
			 //파일명을 받는다 - 일반 원본파일명
			 String filename = loadfile.getOriginalFilename();
			 	
			 String dftFilePath = request.getSession().getServletContext().getRealPath("images");
			 //파일 기본경로 _ 상세경로
			 File file = new File(dftFilePath);
			 if(!file.exists()) {
			 	file.mkdirs();
			 }
			 String realFileNm = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));

			File saveFile = new File(dftFilePath, realFileNm);
			loadfile.transferTo(saveFile);

	  		return3 += "&bNewLine=true";
   		return3 += "&sFileName=" + filename;
   		return3 += "&sFileURL="+ "/images/"+ realFileNm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:"+return1+return2+return3;
}

}
