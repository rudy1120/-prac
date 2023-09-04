package egovframework.portal.sys.bbs.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.bbs.vo.BbsLogMngVO;
import egovframework.portal.sys.log.vo.BbsLog;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 게시글 변경 이력 관리 MAPPER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 9. 8.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 9. 8.
 */
@Mapper
public interface BbsLogMngMapper {

	/** 게시글 변경 이력 전체 건수 */
	public int getTotalCnt(BbsLogMngVO searchVO);

	/** 게시글 변경 이력 목록 */
	public List<BbsLogMngVO> getList(BbsLogMngVO searchVO);

	/** 게시글 변경 이력 전체 목록(엑셀 다운로드용/NO PAGING) */
	public List<Map<String, String>> getTotalListAsMap(BbsLogMngVO searchVO);

	/** 게시글 변경 이력 등록 */
	public void insertBbsLog(BbsLog blVO);

}
