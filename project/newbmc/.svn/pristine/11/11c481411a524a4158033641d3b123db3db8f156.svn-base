package egovframework.portal.sys.bbs.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.bbs.vo.BbsFileDownStatVO;
import egovframework.portal.sys.bbs.vo.BbsOperationStatVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 게시판별 게시글 조작/첨부파일 다운로드 통계 관리 MAPPER
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
@Mapper
public interface BbsStatMapper {

	/** 게시판별 게시글 조작 이력 통계 전체 건수(게시판별로 통계를 내므로 실제로는 게시판의 건수와 동일) */
	public int getOperationStatTotalCnt(BbsOperationStatVO searchVO);

	/** 게시판별 게시글 조작 이력 통계 목록 */
	public List<BbsOperationStatVO> getOperationStatList(BbsOperationStatVO searchVO);

	/** 게시판별 게시글 조작 이력 통계 항목별 전체 합계 */
	public Map<String, String> getOperationStatSnippet(BbsOperationStatVO searchVO);

	/** 게시판별 게시글 조작 이력 통계 전체 목록(NO PAGING) */
	public List<Map<String, String>> getOperationStatListAsMap(BbsOperationStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 전체 건수(게시판별로 통계를 내므로 실제로는 게시판의 건수와 동일) */
	public int getFileDownStatTotalCnt(BbsFileDownStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 목록 */
	public List<BbsFileDownStatVO> getFileDownStatList(BbsFileDownStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 전체 목록(NO PAGING) */
	public Map<String, String> getFileDownStatSnippet(BbsFileDownStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 항복별 전체 합계 */
	public List<Map<String, String>> getFileDownStatListAsMap(BbsFileDownStatVO searchVO);

}
