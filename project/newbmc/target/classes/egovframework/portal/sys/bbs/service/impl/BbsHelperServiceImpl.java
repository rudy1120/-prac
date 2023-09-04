package egovframework.portal.sys.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.bbs.BbsType;
import egovframework.portal.sys.bbs.mapper.BbsConfigMapper;
import egovframework.portal.sys.bbs.mapper.BbsMngMapper;
import egovframework.portal.sys.bbs.service.BbsHelperService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class BbsHelperServiceImpl extends EgovAbstractServiceImpl implements BbsHelperService {

	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected BbsMngMapper bbsMngMapper;
	@Autowired
	protected BbsConfigMapper configMapper;

	@Override
	public List<BbsMngVO> getCategoryList(final BbsConfigVO bbsConfigVO) {
		List<BbsMngVO> categoryList = new ArrayList<>();
		String[] category = StringUtil.isNotBlank(bbsConfigVO.getPtCategory()) // 카테고리 목록
			? bbsConfigVO.getPtCategory().trim().split("\n") : new String[0];
		for (int i = 0; i < category.length; i++) {
			BbsMngVO cateVO = new BbsMngVO();
			cateVO.setbCategory(category[i].trim());
			categoryList.add(cateVO);
		}

		return categoryList;
	}

	@Override
	public Boolean isAccessableIP(final BbsConfigVO bbsConfigVO) {
		if (StringUtil.isNotBlank(bbsConfigVO.getPtCheckIp())) {
			String checkIp = SessionUtil.getRemoteAddr(request);
			String[] array_data = bbsConfigVO.getPtCheckIp().toString().split(";"); // split 함수사용
			for (int i = 0; i < array_data.length; i++) {
				if (checkIp.toString().equals(array_data[i])) {
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean isMovableBbsMng(BbsMngVO bbsMngView, BbsConfigVO bbsConfigVO) {
		if (bbsMngView == null || bbsConfigVO == null) { // 존재하지 않는 게시물 OR 게시판
			return Boolean.FALSE;
		}
		if ("Y".equals(bbsConfigVO.getPtCategoryYn())) { // 카테고리를 사용하는 게시판의 게시물
			return Boolean.FALSE;
		}
		if ("Y".equals(bbsMngView.getIsDel())) { // 삭제된 게시물
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean readable(String attachId) {
		BbsMngVO article = bbsMngMapper.getEntityByAttachId(attachId);
		BbsConfigVO config = article != null ? configMapper.getBbsConfigView(new BbsConfigVO(article.getPtIdx())) : null;
		UserVO user = UserUtil.getInstance();
		if (article != null) {
			BbsType type = BbsType.toType(config.getPtType());
			switch (type) {
				case MINWON: // 민원형 게시판: 작성자만 열람 가능
					return article.isWritedBy(user);
				default: // 기타 게시판: 권한 및 설정 체크
					if ("Y".equals(config.getPtReportYn()) || "N".equals(article.getbPublic())) { // 작성자만 열람 가능한 글
						return article.isWritedBy(user);
					}

					return Boolean.TRUE;
			}

		}

		return Boolean.FALSE;
	}

}
