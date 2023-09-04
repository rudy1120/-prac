package egovframework.portal.sys.bbs.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 4. 20.		권태성				기존 DAO를 Mapper로 포팅
 * 2017. 5. 16.		J.Ryeon Lee			주석 및 메소드 정리
 * </pre>
 *
 * @author 권태성
 * @since 2017. 4. 20.
 */
@Mapper("bbsMngMapper")
public interface BbsMngMapper {

	/** 공지 목록 */
	public List<BbsMngVO> getBbsMngNoticeList(BbsMngVO searchVO);

	/** 게시글 목록 */
	public List<BbsMngVO> getBbsMngList(BbsMngVO searchVO);

	/** 게시글 목록 개수 */
	public int getBbsMngCnt(BbsMngVO searchVO);

	/** 게시글 등록 */
	public void insertBbsMng(BbsMngVO searchVO);

	/** 게시글 idx값 get */
	public String getBidx();

	/** 게시글 ENTITY FETCH */
	public BbsMngVO getBbsMngView(BbsMngVO searchVO);
	
	/** 답변/답글 목록 */
	public List<BbsMngVO> getBbsMngReplyList(BbsMngVO searchVO);

	/** 게시글 수정 */
	public void updateBbsMng(BbsMngVO searchVO);

	/** 게시글 삭제 */
	public void deleteBbsMng(BbsMngVO searchVO);

	/** 게시글 답변/답글 등록 */
	public void replyInsertBbsMng(BbsMngVO searchVO);

	/** 답변글 개수 */
	public int getReplyCnt(BbsMngVO searchVO);

	/** 게시글 복원 */
	public void reDeleteBbsMng(BbsMngVO searchVO);

	/** 게시글 이동 */
	public void moveBbs(Map<String, String> params);

	/* ================================ 사용자 단 ================================ */

	/** 공지 목록 */
	public List<BbsMngVO> getBbsNoticeList(BbsMngVO searchVO);

	/** 게시글 목록 */
	public List<BbsMngVO> getBbsList(BbsMngVO searchVO);

	/** 게시글 목록 개수 */
	public int getBbsCnt(BbsMngVO searchVO);

	/** 게시글 ENTITY FETCH */
	public BbsMngVO getBbsView(BbsMngVO searchVO);

	/** 게시글 답변 목록 */
	public List<BbsMngVO> getBbsReplyList(BbsMngVO searchVO);

	/** 게시글 조회수 증가 */
	public void updateBbsViewCnt(BbsMngVO searchVO);

	/** 게시글 등록 */
	public void insertBbs(BbsMngVO searchVO);

	/** 게시글 비밀번호 디코딩 */
	public String getBoardPasswdDecode(BbsMngVO searchVO);

	/** 게시글 수정 */
	public void updateBbs(BbsMngVO searchVO);

	/** 게시글 삭제 */
	public void deleteBbs(BbsMngVO searchVO);

	/** 게시글 답변 등록 */
	public void replyInsertBbs(BbsMngVO searchVO);

	/** 게시글 답변 max sort값 */
	public int getReplyMaxBodSame(BbsMngVO searchVO);

	/** 사이트 코드에 해당하는 부서 코드 */
	public String getDeptSiteCode(String siteCode);

	/** ptIdx 에 따른 메인 노출(한개) */

	/* 20160518 J.Ryeon Lee ADD */

	/** 일자별 게시글 조회수 증가 */
	public void incrementViewCnt(BbsMngVO searchVO);

	/** 게시글 상세 FETCH BY 첨부파일 ID */
	public BbsMngVO getEntityByAttachId(String attachId);
    /** 메인 게시글 목록(홈페이지) **/
	public List<BbsMngVO> getMainBbsList(BbsMngVO bbsVO);
    /** 메인 게시글 목록관리(관리자) **/
	public List<BbsMngVO> getMainMgt(BbsMngVO searchVO);

	public void uncheck(BbsMngVO uncheckVO);

	public void orderUpdate(BbsMngVO searchVO);

}
