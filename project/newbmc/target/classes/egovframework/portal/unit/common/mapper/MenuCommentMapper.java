package egovframework.portal.unit.common.mapper;

import java.util.List;

import egovframework.portal.unit.common.vo.MenuCommentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("menuCommentMapper")
public interface MenuCommentMapper {

	public int getMenuCommentCnt(MenuCommentVO searchVO);

	public List<MenuCommentVO> getMenuCommentList(MenuCommentVO searchVO);

	public MenuCommentVO getMenuComment(MenuCommentVO searchVO);

	public void insertMenuComment(MenuCommentVO insertVO);

	public void updateMenuComment(MenuCommentVO updateVO);

	public void deleteMenuComment(MenuCommentVO deleteVO);

}
