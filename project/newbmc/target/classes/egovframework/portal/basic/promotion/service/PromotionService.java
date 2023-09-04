package egovframework.portal.basic.promotion.service;

import java.util.List;

import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.basic.promotion.vo.PromotionVO;

public interface PromotionService {

	int getBannerTotalCnt(PromotionVO searchVO);

	List<PromotionVO> getBannerList(PromotionVO searchVO);

	List<PromotionVO> getBannerList(SiteCode siteCode, int limit);

	List<PromotionVO> getVisualzoneList(SiteCode siteCode, int limit);

	List<PromotionVO> getPopupzoneList(SiteCode siteCode, int limit);
}
