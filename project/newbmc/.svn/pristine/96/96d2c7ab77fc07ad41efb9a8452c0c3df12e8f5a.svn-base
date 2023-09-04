package egovframework.portal.basic.promotion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.basic.promotion.service.PromotionService;
import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.promotion.PromotionType;
import egovframework.portal.sys.basic.promotion.mapper.PromotionMngMapper;
import egovframework.portal.sys.basic.promotion.vo.PromotionVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class PromotionServiceImpl extends EgovAbstractServiceImpl implements PromotionService {

	@Autowired
	protected PromotionMngMapper mapper;
	@Autowired
	protected SiteMngService siteMngService;

	private List<PromotionVO> getList(SiteCode siteCode, int limit, PromotionType type) {
		return mapper.getDisplyableList(createSearchVO(siteCode, limit, type));
	}

	private PromotionVO createSearchVO(SiteCode siteCode, int limit, PromotionType type) {
		PromotionVO searchVO = new PromotionVO();
		searchVO.setFirstIndex(1);
		searchVO.setLastIndex(limit);
		searchVO.setType(type);
		searchVO.setUseYn("Y");

		if (siteCode != null) {
			MenusMngVO site = siteMngService.getSiteBySiteCode(siteCode.getCode());
			searchVO.setSearchSiteIdx(String.valueOf(site.getIdx()));
		}

		return searchVO;
	}

	@Override
	public int getBannerTotalCnt(PromotionVO searchVO) {
		searchVO.setUseYn("Y");
		return mapper.getDisplyableTotalCnt(searchVO);
	}

	@Override
	public List<PromotionVO> getBannerList(PromotionVO searchVO) {
		searchVO.setUseYn("Y");
		return mapper.getDisplyableList(searchVO);
	}

	@Override
	public List<PromotionVO> getBannerList(SiteCode siteCode, int limit) {
		return getList(siteCode, limit, PromotionType.BANNER);
	}

	@Override
	public List<PromotionVO> getVisualzoneList(SiteCode siteCode, int limit) {
		return getList(siteCode, limit, PromotionType.VISUALZONE);
	}

	@Override
	public List<PromotionVO> getPopupzoneList(SiteCode siteCode, int limit) {
		return getList(siteCode, limit, PromotionType.POPUPZONE);
	}

}
