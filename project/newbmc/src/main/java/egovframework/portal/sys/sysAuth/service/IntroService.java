package egovframework.portal.sys.sysAuth.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.main.BoardCode;
import egovframework.portal.sys.bbs.vo.BbsMngVO;

/**
 * intro SERVICE (기존 컨트롤러 로직 분리)
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.01.01		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.01.01
 */
public interface IntroService {

	/**  */
	public List<BbsMngVO> getRecentBoardList(int lastIndex) throws Exception;

	/** 공지사항 게시글 목록 */
	public List<BbsMngVO> getBbsList(BoardCode boardCode, int limit) throws Exception;

}
