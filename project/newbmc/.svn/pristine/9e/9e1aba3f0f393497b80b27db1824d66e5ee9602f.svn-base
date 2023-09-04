package egovframework.portal.sys.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.bbs.vo.BbsFileDownStatVO;
import egovframework.portal.sys.bbs.vo.BbsOperationStatVO;

/**
 * 게시판별 게시글 조작/첨부파일 다운로드 통계 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 8. 29.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 8. 29.
 */
public interface BbsStatService {

	/** 게시판별 등록/수정/삭제/조회 통계 목록 전체 건수 */
	public int getOperationStatTotalCnt(BbsOperationStatVO searchVO);

	/** 게시판별 등록/수정/삭제/조회 통계 목록 */
	public List<BbsOperationStatVO> getOperationStatList(BbsOperationStatVO searchVO);

	/** 게시판별 등록/수정/삭제/조회 통계 전체 목록 */
	public List<Map<String, String>> getOperationStatListAsMap(BbsOperationStatVO searchVO);

	/** 게시판별 등록/삭제/수정/조회 통계 전체 합계 */
	public Map<String, String> getOperationStatSnippet(BbsOperationStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 목록 전체 건수 */
	public int getFileDownStatTotalCnt(BbsFileDownStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 목록 */
	public List<BbsFileDownStatVO> getFileDownStatList(BbsFileDownStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 전체 목록 */
	public List<Map<String, String>> getFileDownStatListAsMap(BbsFileDownStatVO searchVO);

	/** 게시판별 첨부파일 다운로드 통계 전체 합계 */
	public Map<String, String> getFileDownStatSnippet(BbsFileDownStatVO searchVO);

	/** 엑셀 파일 제목용, 데이터의 검색 연/월/기간 정보를 리턴 */
	public String getSummary(String searchPeriodType, String searchYear, String searchMonth, String searchSday, String searchEday);

}
