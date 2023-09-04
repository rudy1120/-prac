package egovframework.portal.bbs.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.bbs.vo.CommentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 게시글 코멘트 관리 MAPPER
 *
 * @author J.Ryeon Lee
 * @since 2016.03.08
 */
@Mapper("commentMapper")
public interface CommentMapper {

	public int getCommentCnt(String bIdx);

	public List<CommentVO> getCommentList(Map<String, Object> params);

	public CommentVO getComment(CommentVO searchVO);

	public void insertComment(CommentVO insertVO);

	public void updateComment(CommentVO updateVO);

	public void deleteComment(CommentVO deleteVO);

}
