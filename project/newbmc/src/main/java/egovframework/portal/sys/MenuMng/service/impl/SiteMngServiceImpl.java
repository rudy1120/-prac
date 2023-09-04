package egovframework.portal.sys.MenuMng.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.MenuMng.mapper.SiteMngMapper;
import egovframework.portal.sys.MenuMng.service.SiteMngService;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.promotion.PromotionType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.portal.util.SessionUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 - 사이트 관리 서비스 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014. 10. 07.	엄동건				최초 생성 및 코딩
 * </pre>
 *
 * @author 엄동건
 * @since 2014. 10. 07.
 */
@Service("siteMngService")
public class SiteMngServiceImpl extends EgovAbstractServiceImpl implements SiteMngService {

	@Resource(name = "siteMngMapper")
	private SiteMngMapper siteMngMapper;

	public int getSiteListCnt(MenusMngVO searchVO) {
		return siteMngMapper.getSiteListCnt(searchVO);
	}

	public List<MenusMngVO> getSiteList(MenusMngVO searchVO) {
		return siteMngMapper.getSiteList(searchVO);
	}

	public MenusMngVO getSite(MenusMngVO searchVO) {
		return siteMngMapper.getSite(searchVO);
	}

	public int getSiteIdx(String siteCode) {
		return siteMngMapper.getSiteIdx(siteCode);
	};

	public void insertSite(MenusMngVO insertVO) {
		if (insertVO != null) {
			insertVO.setIdx(siteMngMapper.getSiteSeq());
			siteMngMapper.insertSite(insertVO);
		}
	}

	public void updateSite(MenusMngVO updateVO) {
		siteMngMapper.updateSite(updateVO);
	}

	public void deleteSite(MenusMngVO deleteVO) {
		siteMngMapper.deleteSite(deleteVO);
	}

	/** 공통게시판에서 사이트 코드 목록 조회 */
	public List<MenusMngVO> getSiteCodeList(HttpServletRequest request) {

		// 권한 설정을 윈한 로그인 정보 조회
		AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);

		String searchQuery = "";
		// 전체관리자가 아닌경우
		if (adminLoginVO.getAdminAccessLevelCode() != 10) {
			List<SysSiteAuthVO> sysSiteAuthList = adminLoginVO.getAdminSiteAuths();

			if (sysSiteAuthList != null) {
				// 사이트 조회용 쿼리 생섵
				StringBuilder siteSearchQuery = new StringBuilder();
				for (int i = 0; i < sysSiteAuthList.size(); i++) {
					SysSiteAuthVO sysSiteAuthVO = sysSiteAuthList.get(i);

					if (i > 0) {
						siteSearchQuery.append(" or ");
					}

					siteSearchQuery.append(" idx = ").append(Integer.toString(sysSiteAuthVO.getSiteIdx()));
				}
				searchQuery = siteSearchQuery.toString();
				if (!searchQuery.equals("")) {
					searchQuery = " and (" + searchQuery + ")";
				}
			}
		}
		return siteMngMapper.getSiteCodeList(searchQuery);
	}

	public List<MenusMngVO> getSiteCodeListWhere(MenusMngVO searchVO) {
		return siteMngMapper.getSiteCodeListWhere(searchVO);
	}

	@Override
	public MenusMngVO getSiteBySiteCode(String siteCode) {
		return siteMngMapper.getSiteBySiteCode(siteCode);
	}

	@Override
	public List<MenusMngVO> getListByPrmtType(PromotionType type) {
		return siteMngMapper.getListByPrmtType(type.getCode());
	}

}
