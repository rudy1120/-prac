package egovframework.portal.bbs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.bbs.mapper.CommentMapper;
import egovframework.portal.bbs.service.CommentService;
import egovframework.portal.bbs.vo.CommentVO;
import egovframework.portal.unit.common.UserType;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 코멘트 등록/수정/삭제 SERVICE IMPL
 *
 * @author J.Ryeon Lee
 * @since 2016.03.08
 */
@Service
public class CommentServiceImpl extends EgovAbstractServiceImpl implements CommentService {

	@Resource
	protected CommentMapper commentMapper;

	@Override
	public int getCommentCnt(String bIdx) {
		return StringUtil.isNotBlank(bIdx) ? commentMapper.getCommentCnt(bIdx) : 0;
	}

	@Override
	public List<CommentVO> getCommentList(String bIdx, int currentPage, int recordCut) throws Exception {
		if (StringUtil.isNotBlank(bIdx)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("firstIndex", PaginationInfoUtil.calFirstIndex(currentPage, recordCut));
			params.put("lastIndex", currentPage * recordCut);
			params.put("bIdx", bIdx);
			return commentMapper.getCommentList(params);
		}

		return new ArrayList<CommentVO>(0);
	}

	@Override
	public CommentVO getComment(CommentVO searchVO) throws Exception {
		return StringUtil.isNotBlank(searchVO.getbIdx()) ? commentMapper.getComment(searchVO) : null;
	}

	@Override
	public void insertComment(CommentVO insertVO) throws Exception {
		commentMapper.insertComment(insertVO);
	}

	@Override
	public void updateComment(CommentVO updateVO) throws Exception {
		commentMapper.updateComment(updateVO);
	}

	@Override
	public void deleteComment(CommentVO deleteVO) throws Exception {
		commentMapper.deleteComment(deleteVO);
	}

	@Override
	public boolean isInvalidAuthority(CommentVO origin, CommentVO update) {
		UserType userType = UserType.toType(origin.getUserType());
		switch (userType) {
			case FACEBOOK:
				return origin.getWriterId().equals(update.getWriterId());
			case TWITTER:
				return origin.getWriterId().equals(update.getWriterId());
			case MEMBER:
				return origin.getPrivatecode().equals(update.getPrivatecode());
			default:
				return Boolean.FALSE;
		}
	}

}
