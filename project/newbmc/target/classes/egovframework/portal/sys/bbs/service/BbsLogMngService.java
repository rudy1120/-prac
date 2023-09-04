package egovframework.portal.sys.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.bbs.vo.BbsLogMngVO;

/**
 * 게시글 처리 이력 로그 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 9. 7.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 9. 7.
 */
public interface BbsLogMngService {

	/** 전체 건수 */
	public int getTotalCnt(BbsLogMngVO searchVO);

	/** 목록 */
	public List<BbsLogMngVO> getList(BbsLogMngVO searchVO);

	/** 전체 목록(엑셀 다운로드용) */
	public List<Map<String, String>> getTotalListAsMap(BbsLogMngVO searchVO);

}
