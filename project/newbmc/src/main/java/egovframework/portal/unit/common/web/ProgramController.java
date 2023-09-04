package egovframework.portal.unit.common.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.portal.unit.common.mapper.ProgramMapper;

/**
 * 단위 기능 확인용 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 11. 10.	J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 11. 10.
 */
@Controller
public class ProgramController {

	@Resource
	protected ProgramMapper mapper;

	@RequestMapping("/program/list.do")
	public String list(ModelMap model) throws Exception {
		model.addAttribute("result", mapper.getAllList());

		return "/empty/common/unit/common/programs/";
	}

}
