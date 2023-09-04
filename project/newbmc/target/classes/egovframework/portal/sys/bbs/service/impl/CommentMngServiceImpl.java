package egovframework.portal.sys.bbs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.bbs.mapper.CommentMngMapper;
import egovframework.portal.sys.bbs.service.CommentMngService;
import egovframework.portal.sys.bbs.vo.CommentMngVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 코멘트 관리 SERVICE IMPL
 *
 * @author J.Ryeon Lee
 * @since 2016.03.15
 */
@Service
public class CommentMngServiceImpl extends EgovAbstractServiceImpl implements CommentMngService {

	@Resource(name = "commentMngMapper")
	protected CommentMngMapper commentMngMapper;

	@Override
	public int getCommentCnt(String bIdx) {
		return StringUtil.isNotBlank(bIdx) ? commentMngMapper.getCommentCnt(bIdx) : -1;
	}

	@Override
	public List<CommentMngVO> getCommentList(String bIdx, int currentPage, int recordCut) {
		if (StringUtil.isNotBlank(bIdx)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("firstIndex", PaginationInfoUtil.calFirstIndex(currentPage, recordCut));
			params.put("lastIndex", currentPage * recordCut);
			params.put("bIdx", bIdx);
			List<CommentMngVO> rtn = commentMngMapper.getCommentList(params);

			return rtn;
		}

		return new ArrayList<CommentMngVO>(0);
	}

	@Override
	public CommentMngVO getComment(CommentMngVO searchVO) {
		return StringUtil.isNotBlank(searchVO.getIdx()) ? commentMngMapper.getComment(searchVO) : null;
	}

	@Override
	public void insertComment(CommentMngVO insertVO) {
		commentMngMapper.insertComment(insertVO);
	}

	@Override
	public void updateComment(CommentMngVO updateVO) {
		commentMngMapper.updateComment(updateVO);
	}

	@Override
	public void deleteComment(CommentMngVO switchVO) {
		switchVO.setIsDel("Y");
		commentMngMapper.switchState(switchVO);
	}

	@Override
	public void restoreComment(CommentMngVO switchVO) {
		switchVO.setIsDel("N");
		commentMngMapper.switchState(switchVO);
	}

}
