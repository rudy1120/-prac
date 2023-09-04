package egovframework.portal.sys.bbs.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.bbs.vo.CommentMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("commentMngMapper")
public interface CommentMngMapper {

	public int getCommentCnt(String bIdx);

	public List<CommentMngVO> getCommentList(Map<String, Object> params);

	public CommentMngVO getComment(CommentMngVO searchVO);

	public void insertComment(CommentMngVO insertVO);

	public void updateComment(CommentMngVO updateVO);

	public void switchState(CommentMngVO switchVO);

}
