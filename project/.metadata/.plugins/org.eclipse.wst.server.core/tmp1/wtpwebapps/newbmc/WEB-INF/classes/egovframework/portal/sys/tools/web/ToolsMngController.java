package egovframework.portal.sys.tools.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdatabase.urlStatus.UrlStatus;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.content.service.MenuSysService;
import net.arnx.jsonic.JSON;

/**
 * YH데이타베이스 전용 개발/운영 도구 컨트롤러입니다.
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 9. 12.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 9. 12.
 */
@Controller
public class ToolsMngController {

	@Autowired
	protected MenuMngService menuMngService;
	@Autowired
	protected MenuSysService menuService;

	private final Logger LOGGER = LogManager.getLogger();

	@RequestMapping("/hrefCheck.do")
	public String hrefCheck(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<Map<String, Object>> dataList = menuMngService.getURLContentsList();
		model.addAttribute("dataList", dataList);
		return "/sys/hrefCheck";
	}

	@ResponseBody
	@RequestMapping("/hrefCheckData.do")
	public byte[] hrefCheckData(@RequestParam("siteCode") String siteCode, @RequestParam("pageId") String pageId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		String host = "http://" + EgovProperties.getProperty("Globals.full.domain");
		List<String> linkUrlList = new ArrayList<>();
		List<String> linkNameList = new ArrayList<>();
		List<Map<String, Object>> retList = new ArrayList<>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("siteCode", siteCode);
		param.put("pageId", pageId);

		try {
			String tmp = menuMngService.getURLContents(param);
			while (tmp.indexOf("href") != -1) {
				if (tmp.indexOf("href") != -1) {
					tmp = tmp.substring(tmp.indexOf("href") + 6, tmp.length());
					String linkUrl = tmp.substring(0, tmp.indexOf("\"")).replaceAll("&amp;", "&");
					linkUrlList.add(linkUrl);
					tmp = tmp.substring(tmp.substring(0, tmp.indexOf("\"")).length(), tmp.length());
					tmp = tmp.substring(tmp.indexOf(">") + 1, tmp.length());
					String linkName = tmp.substring(0, tmp.indexOf("</a>"));
					linkNameList.add(linkName);
					tmp = tmp.substring(tmp.substring(0, tmp.indexOf("</a>")).length(), tmp.length());
					Map<String, Object> map = new HashMap<>();
					if (linkUrl.indexOf("http") > -1) {
						map.put("urlState", UrlStatus.urlStatus(linkUrl));
					} else {
						map.put("urlState", UrlStatus.urlStatus(host + linkUrl));
					}
					map.put("linkUrl", linkUrl);
					map.put("linkName", linkName);
					retList.add(map);
				} else {
					tmp = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			return new JSONObject().put("flag", "fail").toString().getBytes("UTF-8");
		}
		return JSON.encode(retList, true).getBytes("UTF-8");
	}

	@RequestMapping("/menuCheck.do")
	public String menuCheck(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("dataList", menuMngService.getURLmenuList());
		return "/sys/menu";
	}

	@ResponseBody
	@RequestMapping("/menuCheckData.do")
	public byte[] menuCheckData(@RequestParam("siteCode") String siteCode, @RequestParam("pageId") String pageId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		String host = "http://" + EgovProperties.getProperty("Globals.full.domain");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("siteCode", siteCode);
		param.put("pageId", pageId);
		String url = menuMngService.getURLmenu(param).replaceAll("&amp;", "&");
		int state = 0;
		if (!url.startsWith("http")) {
			url = host + url;
		}
		try {
			state = UrlStatus.urlStatus(url);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			return new JSONObject().put("flag", "fail").toString().getBytes("UTF-8");
		}
		return new JSONObject().put("urlState", state).put("url", url).put("siteCode", siteCode).put("pageId", pageId).toString().getBytes("UTF-8");
	}


	@RequestMapping("/wa.do")
	public String wa(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("dataList", menuMngService.getURLmenuList());
		model.addAttribute("domain", request.getServerName());
		return "/sys/wa";
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/waData.do")
	public byte[] accessibilityCheck(@RequestParam("siteCode") String siteCode, @RequestParam("pageId") String pageId, HttpServletRequest request, HttpServletResponse response) throws JSONException {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("siteCode", siteCode);
		param.put("pageId", pageId);
		String target = menuMngService.getURLmenu(param).replaceAll("&amp;", "&");

		String userAgent = "Mozilla/5.0";
		String url = "http://nact.naver.com/result?input_url=" + target;
		URL obj = null;
		JSONObject jsonObject = new JSONObject();

		try {

			if (target.indexOf("contents.do") != -1) {
				obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setConnectTimeout(50000);
				con.setReadTimeout(50000);
				// optional default is GET
				con.setRequestMethod("GET");

				//add request header
				con.setRequestProperty("User-Agent", userAgent);

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String inputLine;
				StringBuffer rep = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					rep.append(inputLine);
				}
				in.close();

				//print result
				System.out.println(response.toString());

				Map<String, Map<String, List<Map<String, Object>>>> repMap = null;
				if (rep.toString().length() > 0) {
					//((Map<String,Object>)((Map<String,Object>)repMap.get("data").get("item_count")).get("total")).get("warn")
					//((Map<String,Object>)((Map<String,Object>)repMap.get("data").get("item_count")).get("total")).get("error")
					repMap = JSON.decode(rep.toString());
					jsonObject.put("flag", "success");
					jsonObject.put("warn", ((Map<String,Object>)((Map<String,Object>)repMap.get("data").get("item_count")).get("total")).get("warn"));
					jsonObject.put("error", ((Map<String,Object>)((Map<String,Object>)repMap.get("data").get("item_count")).get("total")).get("error"));
					jsonObject.put("url", target);
					return jsonObject.toString().getBytes();
				} else {
					System.out.println("SERVER RESPONSE ERROR (response Error : " + responseCode + ")");
					jsonObject.put("flag", "fail");
					jsonObject.put("msg", "데이터 처리 중 오류가 발생하였습니다. (response Error : " + responseCode + ")");
					return jsonObject.toString().getBytes();
				}
			} else {
				jsonObject.put("flag", "error");
				jsonObject.put("msg", "검사 대상 페이지 아님");
				return jsonObject.toString().getBytes();
			}

		} catch (IOException e) {
			e.printStackTrace();
			jsonObject.put("flag", "fail");
			jsonObject.put("msg", "IOException (" + e.getMessage() + ")");
			return jsonObject.toString().getBytes();
		}

	}

}
