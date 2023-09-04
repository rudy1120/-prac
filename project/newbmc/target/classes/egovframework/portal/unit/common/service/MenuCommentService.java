package egovframework.portal.unit.common.service;

import java.util.List;

import egovframework.portal.unit.common.vo.MenuCommentVO;

/**메뉴별 코멘트 기능 SERVICE IMPL
 *
 * @author J.Ryeon Lee
 * @since 2016.06.22
 */
public interface MenuCommentService {

	/** mId를 키로 하는 게시글에 등록된 코멘트 건수 */
	public int getMenuCommentCnt(MenuCommentVO searchVO);

	/** mId를 키로 하는 게시글에 등록된 코멘트 리스트 */
	public List<MenuCommentVO> getMenuCommentList(MenuCommentVO searchVO);

	/** 코멘트 FETCH (IDX) */
	public MenuCommentVO getMenuComment(MenuCommentVO searchVO);

	/** 코멘트 등록 */
	public void insertMenuComment(MenuCommentVO insertVO) throws Exception;

	/** 코멘트 갱신 */
	public void updateMenuComment(MenuCommentVO updateVO) throws Exception;

	/** IDX를 키로 삭제 처리(is_del 플래그 스위칭) */
	public void deleteMenuComment(MenuCommentVO deleteVO) throws Exception;

}
