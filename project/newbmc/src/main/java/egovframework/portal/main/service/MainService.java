package egovframework.portal.main.service;

import java.util.List;

import egovframework.portal.main.BoardCode;
import egovframework.portal.main.SiteCode;
import egovframework.portal.main.web.MainController;
import egovframework.portal.sys.basic.promotion.vo.PromotionVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;

/**
 * {@link MainController} 로직 분리 SERVICE
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
public interface MainService {

	/** 배너 목록 */
	List<PromotionVO> getBannerList(SiteCode siteCode, int limit);

	/** 비주얼존 목록 */
	List<PromotionVO> getVisualzoneList(SiteCode siteCode, int limit);

	/** 팝업존 목록 */
	List<PromotionVO> getPopupzoneList(SiteCode siteCode, int limit);

	/** 게시글 목록 */
	List<BbsMngVO> getBbsList(SiteCode siteCode, BoardCode portalNotice, int limit) throws Exception;
}
