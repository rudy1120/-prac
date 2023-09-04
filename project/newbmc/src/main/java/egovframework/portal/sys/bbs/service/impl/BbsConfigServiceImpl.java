package egovframework.portal.sys.bbs.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.portal.sys.bbs.mapper.BbsConfigMapper;
import egovframework.portal.sys.bbs.service.BbsConfigService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.log.mapper.BbsConfigLogMapper;
import egovframework.portal.sys.log.vo.BbsConfigLogVO;
import egovframework.portal.sys.privacy.service.PrivacyPrmService;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 게시판 관리 SERVICE IMPL
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.09.25		김혜민				최초 생성 및 코딩
 * 2017.06.14		J.Ryeon Lee			코드 리팩토링
 * </pre>
 *
 * @author 김혜민
 * @since 2014.09.25
 */
@Service("bbsConfigService")
public class BbsConfigServiceImpl extends EgovAbstractServiceImpl implements BbsConfigService {

	@Resource(name = "bbsConfigMapper")
	private BbsConfigMapper bbsConfigMapper;
	@Resource(name = "bbsConfigLogMapper")
	protected BbsConfigLogMapper bbsConfigLogMapper;
	@Autowired
	protected PrivacyPrmService privacyPrmService;
	@Autowired
	protected DataSourceTransactionManager transactionManager;

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<BbsConfigVO> getBbsConfigList(BbsConfigVO searchVO) {
		return bbsConfigMapper.getBbsConfigList(searchVO);
	}

	@Override
	public List<BbsConfigVO> getBbsConfigAuthList(BbsConfigVO searchVO) {
		return bbsConfigMapper.getBbsConfigAuthList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalBbsConfigAuthListAsMap(BbsConfigVO searchVO) {
		return bbsConfigMapper.getTotalBbsConfigAuthListAsMap(searchVO);
	}

	@Override
	public int getBbsConfigCnt(BbsConfigVO searchVO) {
		return bbsConfigMapper.getBbsConfigCnt(searchVO);
	}

	@Override
	public String insertBbsConfig(HttpServletRequest request, BbsConfigVO originVO) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			AdminLoginVO admin = SessionUtil.getAdminInstance(request);
			BbsConfigVO insertVO = BeanUtil.copy(originVO, new BbsConfigVO());
			insertVO.setPtIdx(bbsConfigMapper.getPtIdx());
			insertVO.setDefaultValues();

			bbsConfigMapper.insertBbsConfig(insertVO); // 게시판 등록
			insertBbsConfigLog(request, insertVO, "생성"); // 로그 등록

			// privacy data policy 반영
			if (insertVO.includePrivacyData()) {
				PrivacyPrmVO privacyConfig = new PrivacyPrmVO();
				privacyConfig.copyFrom(insertVO, admin);
				String prmIdx = privacyPrmService.insert(privacyConfig, admin, SessionUtil.getRemoteAddr(request));
				if (StringUtil.isBlank(prmIdx)) { // privacy policy insert failed
					transactionManager.rollback(status);
					return null;
				}
			}

			transactionManager.commit(status);
			return insertVO.getPtIdx();
		} catch (NullPointerException | DataAccessException e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
		}

		return null;
	}

	@Override
	public BbsConfigVO getBbsConfigView(BbsConfigVO searchVO) {
		BbsConfigVO rtn = bbsConfigMapper.getBbsConfigView(searchVO);
		if (rtn != null) {
			rtn.setPtSaveColList(StringUtil.isNotBlank(rtn.getPtSaveCols()) ? Arrays.asList(rtn.getPtSaveCols().split(",")) : null);
			rtn.setPtSaveOptColList(StringUtil.isNotBlank(rtn.getPtSaveOptCols()) ? Arrays.asList(rtn.getPtSaveOptCols().split(",")) : null);
		}

		return rtn;
	}

	@Override
	public String updateBbsConfig(HttpServletRequest request, BbsConfigVO originVO) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			BbsConfigVO db = getBbsConfigView(originVO);
			if (db != null) {
				AdminLoginVO admin = SessionUtil.getAdminInstance(request);
				BbsConfigVO updateVO = BeanUtil.copy(originVO, new BbsConfigVO());
				updateVO.setPtType(db.getPtType()); // 게시판 타입은 변경 불가
				updateVO.setPtLevelInput(db.getPtLevelInput()); // 글쓰기 권한은 변경 불가
				updateVO.setDefaultValues();

				bbsConfigMapper.updateBbsConfig(updateVO); // 게시글 수정
				insertBbsConfigLog(request, updateVO, "수정"); // 로그 등록

				// privacy data policy 반영
				if (updateVO.includePrivacyData()) {
					PrivacyPrmVO privacyConfig = privacyPrmService.getEntityByPtIdx(updateVO.getPtIdx());
					if(privacyConfig!=null) {
						privacyConfig.copyFrom(updateVO, admin);
						String prmIdx = privacyPrmService.update(privacyConfig, admin, SessionUtil.getRemoteAddr(request));
						if (StringUtil.isBlank(prmIdx)) { // privacy policy insert failed
							transactionManager.rollback(status);
							return null;
						}
					}
				}

				transactionManager.commit(status);
				return updateVO.getPtIdx();
			}
		} catch (NullPointerException | DataAccessException e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
		}

		return null;
	}

	@Override
	public String deleteBbsConfig(HttpServletRequest request, BbsConfigVO originVO) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			BbsConfigVO db = getBbsConfigView(originVO);
			if (db != null) {
//				AdminLoginVO admin = SessionUtil.getAdminInstance(request);
				BbsConfigVO deleteVO = getBbsConfigView(originVO);
				bbsConfigMapper.deleteBbsConfig(deleteVO); // 게시판 삭제
				insertBbsConfigLog(request, getBbsConfigView(deleteVO), "삭제"); // 로그 등록

				transactionManager.commit(status);
				return deleteVO.getPtIdx();

//				// privacy data policy 반영
//				PrivacyPrmVO privacyConfig = getPrivacyPrmVO(deleteVO, admin, Boolean.FALSE);
//				String prmIdx = privacyConfig != null ? privacyPrmService.delete(privacyConfig, admin, SessionUtil.getRemoteAddr(request)) : "-";
//				if (StringUtil.isBlank(prmIdx)) { // privacy policy insert failed
//					transactionManager.rollback(status);
//				} else {
//					transactionManager.commit(status);
//					return deleteVO.getPtIdx();
//				}
			}
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
		}

		return null;
	}

	@Override
	public List<BbsConfigVO> configBoardList(BbsConfigVO searchVO) {
		return bbsConfigMapper.configBoardList(searchVO);
	}

	@Override
	public boolean isUsedBbs(String ptIdx) {
		return bbsConfigMapper.getCntUsingThisBbs(ptIdx) > 0;
	}

	@Override
	public BbsConfigVO getEntityByPk(String ptIdx) {
		return getBbsConfigView(new BbsConfigVO(ptIdx));
	}

	/** 게시판 로그 관리 */
	private void insertBbsConfigLog(HttpServletRequest request, BbsConfigVO searchVO, String action) {
		BbsConfigLogVO insertVO = new BbsConfigLogVO();
		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
		insertVO.setPtIdx(searchVO.getPtIdx());
		insertVO.setPtWriter(admin.getName());
		insertVO.setPtWriteId(admin.getAdminId());
		insertVO.setPtTitle(searchVO.getPtTitle());
		insertVO.setPtProc(action);
		insertVO.setPtHostip(SessionUtil.getRemoteAddr(request));
		insertVO.setPtDeptName(admin.getDeptName());

		bbsConfigLogMapper.insertBbsConfigLog(insertVO);
	}

}
