package egovframework.portal.unit.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.unit.common.mapper.MenuCommentMapper;
import egovframework.portal.unit.common.service.MenuCommentService;
import egovframework.portal.unit.common.vo.MenuCommentVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class MenuCommentServiceImpl extends EgovAbstractServiceImpl implements MenuCommentService {

	@Resource(name="menuCommentMapper")
	protected MenuCommentMapper menuCommentMapper;

	@Override
	public int getMenuCommentCnt(MenuCommentVO searchVO) {
		return menuCommentMapper.getMenuCommentCnt(searchVO);
	}

	@Override
	public List<MenuCommentVO> getMenuCommentList(MenuCommentVO searchVO) {
		return menuCommentMapper.getMenuCommentList(searchVO);
	}

	@Override
	public MenuCommentVO getMenuComment(MenuCommentVO searchVO) {
		return StringUtil.isNotBlank(searchVO.getIdx())
			? menuCommentMapper.getMenuComment(searchVO) : null;
	}

	@Override
	public void insertMenuComment(MenuCommentVO insertVO) throws Exception {
		menuCommentMapper.insertMenuComment(insertVO);
	}

	@Override
	public void updateMenuComment(MenuCommentVO updateVO) throws Exception {
		menuCommentMapper.updateMenuComment(updateVO);
	}

	@Override
	public void deleteMenuComment(MenuCommentVO deleteVO) throws Exception {
		menuCommentMapper.deleteMenuComment(deleteVO);
	}

}
