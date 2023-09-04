package egovframework.portal.sys.bbs.service;

import java.util.List;

import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;

/**
 * 게시판 로직 분리 SERVICE (기존 web controller 로직 코드를 분리)
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 5.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 5.
 */
public interface BbsHelperService {

	List<BbsMngVO> getCategoryList(final BbsConfigVO bbsConfigVO);

	Boolean isAccessableIP(final BbsConfigVO bbsConfigVO);

	Boolean isMovableBbsMng(BbsMngVO bbsMngView, BbsConfigVO bbsConfigVO);

	Boolean readable(String attachId);

}
