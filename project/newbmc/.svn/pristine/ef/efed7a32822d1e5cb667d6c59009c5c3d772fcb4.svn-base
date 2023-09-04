package egovframework.portal.sys.bbs.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("bbsConfigMapper")
public interface BbsConfigMapper {

	/** 게시판 목록 */
	public List<BbsConfigVO> getBbsConfigList(BbsConfigVO searchVO);

	/** 게시판 권한 목록 */
	public List<BbsConfigVO> getBbsConfigAuthList(BbsConfigVO searchVO);

	/** 게시판 권한 목록(엑셀 다운로드용/NO PAGING) */
	public List<Map<String, String>> getTotalBbsConfigAuthListAsMap(BbsConfigVO searchVO);

	/** 게시판 목록 개수 */
	public int getBbsConfigCnt(BbsConfigVO searchVO);

	/** 게시판 등록 */
	public void insertBbsConfig(BbsConfigVO searchVO);

	/** 게시판 idx값 get */
	public String getPtIdx();

	/** 게시판 ENTITY FETCH */
	public BbsConfigVO getBbsConfigView(BbsConfigVO searchVO);

	/** 게시판 수정 proc */
	public void updateBbsConfig(BbsConfigVO searchVO);

	/** 게시판 삭제 proc */
	public void deleteBbsConfig(BbsConfigVO searchVO);

	/** 게시글 이동 대상 게시판 목록 (동일한 타입의 게시판 목록) */
	public List<BbsConfigVO> configBoardList(BbsConfigVO searchVO);

	/** 해당 게시판을 사용 중인 메뉴 개수 */
	public int getCntUsingThisBbs(String ptIdx);

}
