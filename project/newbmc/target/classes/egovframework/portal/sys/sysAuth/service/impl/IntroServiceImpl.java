package egovframework.portal.sys.sysAuth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.main.BoardCode;
import egovframework.portal.sys.bbs.service.BbsMngService;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.dynamic.mapper.DynamicDataMngMapper;
import egovframework.portal.sys.sysAuth.service.IntroService;

/**
 * INTRO SERVICE IMPL
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.01.28		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.06.28		J.Ryeon Lee			솔루션 기능 동적 현황 처리
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.01.28
 */
@Service
public class IntroServiceImpl implements IntroService {

	@Autowired
	protected BbsService bbsService;
	@Autowired
	protected BbsMngService bbsMngService;
	@Autowired
	protected DynamicDataMngMapper dynamicDataMngMapper;

	@Override
	public List<BbsMngVO> getRecentBoardList(int lastIndex) throws Exception {
		BbsMngVO searchVO = new BbsMngVO();
		searchVO.setLastIndex(lastIndex);

		return bbsMngService.getBbsMngList(searchVO);
	}

	@Override
	public List<BbsMngVO> getBbsList(BoardCode boardCode, int limit) throws Exception {
		BbsMngVO searchVO = new BbsMngVO();
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(limit);
		searchVO.setPtIdx(boardCode.getPtIdx());

		return bbsService.getBbsList(searchVO);
	}

}
