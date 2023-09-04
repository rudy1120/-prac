package egovframework.portal.bbs.service;

import java.util.List;

import egovframework.portal.bbs.vo.CommentVO;

/**
 * 게시글 코멘트 등록/수정/삭제 SERVICE
 *
 * @author J.Ryeon Lee
 * @since 2016.03.08
 */
public interface CommentService {

	/** bIdx를 키로 하는 게시글에 등록된 코멘트 건수 */
	public int getCommentCnt(String bIdx);

	/** bIdx를 키로 하는 게시글에 등록된 코멘트 리스트 */
	public List<CommentVO> getCommentList(String bIdx, int currentPage, int recordCut) throws Exception;

	/** 코멘트 FETCH (IDX) */
	public CommentVO getComment(CommentVO commentVO) throws Exception;

	/** 코멘트 등록 */
	public void insertComment(CommentVO insertVO) throws Exception;

	/** 코멘트 갱신 */
	public void updateComment(CommentVO updateVO) throws Exception;

	/** IDX를 키로 삭제 처리(is_del 플래그 스위칭) */
	public void deleteComment(CommentVO deleteVO) throws Exception;

	public boolean isInvalidAuthority(CommentVO origin, CommentVO update);

}
