package egovframework.portal.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.portal.common.mapper.EncryptMapper;
import egovframework.portal.util.SecurityUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;

@Controller
public class EncryptController {

	@Resource
	protected EncryptMapper dao;

	@ResponseBody
	@RequestMapping("/enc/sha256/from/aes.do")
	public String encrypt(@RequestParam String tableName, @RequestParam String keyColumn, @RequestParam String targetColumn) throws Exception {
		JSONObject rtn = new JSONObject();

		int page = 1;
		int listCutRecord = 100;
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("keyColumn", keyColumn);
		params.put("targetColumn", targetColumn);
		params.put("firstIndex", page);
		params.put("lastIndex", PaginationInfoUtil.calLastIndex(page, listCutRecord));

		int totalCnt = dao.getTotalCnt(params);
		List<Map<String, String>> targetList = null;
		String aesEncedPass = null;
//		String rawPass = null;
		String shaEncedPass = null;

		rtn.put("totalCnt", totalCnt);

		do {
			targetList = dao.getList(params);
			for (Map<String, String> target : targetList) {
				aesEncedPass = target.get(targetColumn);
//				rawPass = SecurityUtil.aesDecrypt(aesEncedPass);
				shaEncedPass = SecurityUtil.encryptSHA256(aesEncedPass);
//				System.out.println(String.format("[RESULT] table name: [%s], key: [%s], original: [%s], raw: [%s], to: [%s]", tableName, keyColumn, aesEncedPass, rawPass, shaEncedPass));

				target.put("tableName", tableName);
				target.put("targetColumn", targetColumn);
				target.put("targetValue", shaEncedPass);
				target.put("keyValue", target.get(keyColumn));
				target.put("keyColumn", keyColumn);
				dao.update(target);
			}

			page++;
			params.put("firstIndex", PaginationInfoUtil.calFirstIndex(page, listCutRecord));
			params.put("lastIndex", PaginationInfoUtil.calLastIndex(page, listCutRecord));
			totalCnt -= listCutRecord;
		} while (totalCnt > 0);

		rtn.put("remainCnt", totalCnt);

		return rtn.toString();
	}

}
