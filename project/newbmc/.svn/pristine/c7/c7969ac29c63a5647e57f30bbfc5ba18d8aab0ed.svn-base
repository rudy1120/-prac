package egovframework.portal.bbs.web;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.service.BbsMngService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.bbs.vo.BbsXmlVO;
import egovframework.portal.util.IntegerUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.XMLUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import net.arnx.jsonic.JSON;

/**
 * RSS 제공 CONTROLLER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 3.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 3.
 */
@Controller
public class BbsApiController {

	@Autowired
	protected BbsConfigService bbsConfigService;
	@Autowired
	protected BbsMngService bbsMngService;
	@Autowired
	protected SiteMngService siteMngService;

	protected final Logger LOGGER = Logger.getLogger(WriterUtil.class.getName());
	protected final String[] RSS_TYPE = { "rss_1.0", "rss_2.0", "rss_0.9", "rss_0.92", "atom_1.0", "atom_0.3" };
	protected final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/portal/bbs/xml/list.do")
	public void xml(@RequestParam String ptIdx, @RequestParam(required = false) String p, @RequestParam(required = false) String limit, HttpServletResponse response) throws Exception {
		response.setContentType("text/xml; charset=utf-8");

		int page = IntegerUtil.parse(p, 1, 1);
		int listCutRecord = IntegerUtil.parse(limit, 1, 100, 20);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(ptIdx));
		String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><items/>";
		if (config != null && "Y".equals(config.getPtRssYn()) && "N".equals(config.getIsDel())) { // RSS 제공 게시판의 경우
			data = XMLUtil.marshal(new BbsXmlVO(pagingList(ptIdx, page, listCutRecord)), "items", BbsXmlVO.class);
		}

		WriterUtil.flush(response, "text/xml; charset=UTF-8", data);
	}

	@ResponseBody
	@RequestMapping(value = "/portal/bbs/json/list.do")
	public byte[] json(@RequestParam String ptIdx, @RequestParam(required = false) String p, @RequestParam(required = false) String limit, HttpServletResponse response) throws Exception {
		response.setContentType("text/json; charset=utf-8");

		int page = IntegerUtil.parse(p, 1, 1);
		int listCutRecord = IntegerUtil.parse(limit, 1, 100, 20);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(ptIdx));
		if (config != null && "Y".equals(config.getPtRssYn()) && "N".equals(config.getIsDel())) { // RSS 제공 게시판의 경우
			List<BbsMngVO> results = pagingList(ptIdx, page, listCutRecord);
			List<Map<String, String>> items = new ArrayList<>();
			Map<String, String> item = null;
			for (BbsMngVO result : results) {
				item = new HashMap<>();
				item.put("bIdx", result.getbIdx());
				item.put("ptIdx", result.getPtIdx());
				item.put("bTitle", result.getbTitle());
				item.put("bWrite", result.getbWrite());
				item.put("bContent", result.getbContent());
				item.put("bCnt", String.valueOf(result.getbCnt()));
				item.put("createDate", FORMATTER.format(result.getCreateDate()));
				items.add(item);
			}

			return JSON.encode(items).getBytes("UTF-8");
		}

		return JSON.encode(new ArrayList<>()).getBytes("UTF-8");
	}

	@RequestMapping(value = "/portal/bbs/rss/list.do")
	public void rss(@RequestParam String ptIdx, @RequestParam(required = false) String p, @RequestParam(required = false) String limit, @RequestParam String mId, HttpServletResponse response) throws Exception {
		response.setContentType("text/xml; charset=utf-8");

		String domain = "http://" + EgovProperties.getProperty(Constant.FULL_DOMAIN);
		int page = IntegerUtil.parse(p, 1, 1);
		int listCutRecord = IntegerUtil.parse(limit, 1, 100, 20);

		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(ptIdx)); // 게시판 설정 정보
		if (config != null && "Y".equals(config.getPtRssYn()) && "N".equals(config.getIsDel())) { // RSS 제공 게시판의 경우
			MenusMngVO siteInfo = siteMngService.getSiteBySiteCode(config.getPtSiteCode());
			String siteCodeFull = "portal";
			if (siteInfo != null) {
				siteCodeFull = StringUtil.isNotBlank(siteInfo.getSiteGroup()) ? siteInfo.getSiteGroup() + "/" + siteInfo.getSiteCode() : siteInfo.getSiteCode();
			}

			SyndFeed feed = new SyndFeedImpl(); // RSS HEADER 설정
			feed.setFeedType(RSS_TYPE[1]);
			feed.setTitle(config.getPtTitle() + " RSS FEED");
			feed.setLink(domain + "/" + siteCodeFull + "/bbs/list.do?ptIdx=" + config.getPtIdx() + "&mId=" + mId);
			feed.setDescription("");

			List<SyndEntry> entries = new ArrayList<>();
			SyndEntry entry = null;
			SyndContent description = null;

			List<BbsMngVO> results = pagingList(ptIdx, page, listCutRecord);
			for (BbsMngVO result : results) {
				entry = new SyndEntryImpl();
				entry.setTitle(result.getbTitle());
				entry.setLink(domain + "/" + siteCodeFull + "/bbs/view.do?bIdxddd=" + result.getbIdx() + "&ptIdx=" + result.getPtIdx() + "&mId=" + mId);
				entry.setPublishedDate(result.getCreateDate());
				description = new SyndContentImpl();
				description.setType("text/plain");
				description.setValue(result.getbContent());
				entry.setDescription(description);
				entries.add(entry);
			}

			feed.setEntries(entries);

			Writer writer = null;
			try {
				writer = response.getWriter();
				SyndFeedOutput output = new SyndFeedOutput();
				output.output(feed, writer);
			} catch (IOException e) {
				LOGGER.error("", e);
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					LOGGER.error("", e);
				}
			}
		}
	}

	public List<BbsMngVO> pagingList(String ptIdx, int page, int listCutRecord) {
		BbsMngVO searchVO = new BbsMngVO();
		searchVO.setPtIdx(ptIdx);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));

		return bbsMngService.getBbsMngList(searchVO);
	}

}
