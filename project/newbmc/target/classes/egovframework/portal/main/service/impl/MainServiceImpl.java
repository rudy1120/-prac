package egovframework.portal.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.basic.promotion.service.PromotionService;
import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.main.BoardCode;
import egovframework.portal.main.SiteCode;
import egovframework.portal.main.service.MainService;
import egovframework.portal.sys.basic.promotion.vo.PromotionVO;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService {

	@Resource
	protected PromotionService promotionService;
	@Resource
	protected BbsConfigService bbsConfigService;
	@Resource
	protected BbsService bbsService;

	@Override
	public List<PromotionVO> getBannerList(SiteCode siteCode, int limit) {
		return promotionService.getBannerList(siteCode, limit);
	}

	@Override
	public List<PromotionVO> getVisualzoneList(SiteCode siteCode, int limit) {
		return promotionService.getVisualzoneList(siteCode, limit);
	}

	@Override
	public List<PromotionVO> getPopupzoneList(SiteCode siteCode, int limit) {
		return promotionService.getPopupzoneList(siteCode, limit);
	}

	@Override
	public List<BbsMngVO> getBbsList(SiteCode site, BoardCode board, int limit) throws Exception {
		BbsConfigVO config = bbsConfigService.getBbsConfigView(new BbsConfigVO(board.getPtIdx()));
		if (config != null) {
			BbsMngVO searchVO = bbsService.formatSearcher("", site.getCode(), config, new BbsMngVO());
			searchVO.setConfig(config);
			searchVO.setPtIdx(board.getPtIdx());
			searchVO.setFirstIndex(1);
			searchVO.setLastIndex(limit);
			searchVO.setContainNotiYn("Y");
			return bbsService.getBbsList(searchVO);
		}

		return new ArrayList<>(0);
	}

}
