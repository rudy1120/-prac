package egovframework.portal.sys.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 공통 엑셀 데이터 업로드 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 19.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 19.
 */
@Controller
public class ExcelUploadController {

	/** 엑셀 업로드 안내 화면(ONE DEPTH) */
	@RequestMapping("/sys/{path0}/excel/upload.do")
	public String upload(@PathVariable String path0, @RequestParam String mId, ModelMap model) {
		model.addAttribute("mId", mId);
		model.addAttribute("fullPath", path0);

		return "/sys/empty/common/excel/upload/";
	}

	/** 엑셀 업로드 안내 화면(TWO DEPTH) */
	@RequestMapping("/sys/{path0}/{path1}/excel/upload.do")
	public String upload(@PathVariable String path0, @PathVariable String path1, @RequestParam String mId, ModelMap model) {
		model.addAttribute("mId", mId);
		model.addAttribute("fullPath", path0 + "/" + path1);

		return "/sys/empty/common/excel/upload/";
	}

	/** 엑셀 업로드 안내 화면(THREE DEPTH) */
	@RequestMapping("/sys/{path0}/{path1}/{path2}/excel/upload.do")
	public String upload(@PathVariable String path0, @PathVariable String path1, @PathVariable String path2, @RequestParam String mId, ModelMap model) {
		model.addAttribute("mId", mId);
		model.addAttribute("fullPath", path0 + "/" + path1 + "/" + path2);

		return "/sys/empty/common/excel/upload/";
	}

}
