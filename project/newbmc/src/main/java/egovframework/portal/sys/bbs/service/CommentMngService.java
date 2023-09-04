package egovframework.portal.sys.bbs.service;

import java.util.List;

import egovframework.portal.sys.bbs.vo.CommentMngVO;

/**
 * 코멘트 관리 SERVICE
 *
 * @author J.Ryeon Lee
 * @since 2016.03015
 */
public interface CommentMngService {

	/** bIdx를 키로 하는 게시글에 등록된 코멘트 건수 */
	public int getCommentCnt(String bIdx);

	/** bIdx를 키로 하는 게시글에 등록된 코멘트 리스트 */
	public List<CommentMngVO> getCommentList(String bIdx, int currentPage, int recordCut);

	/** b_idx, pt_idx, idx를 키로 코멘트 FETCH */
	public CommentMngVO getComment(CommentMngVO searchVO);

	/** 코멘트 등록 */
	public void insertComment(CommentMngVO commentMngVO);

	/** 코멘트 수정 */
	public void updateComment(CommentMngVO commentMngVO);

	/** 코멘트 삭제 */
	public void deleteComment(CommentMngVO comment);

	/** 코멘트 복구 */
	public void restoreComment(CommentMngVO comment);

}
