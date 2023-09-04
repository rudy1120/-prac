package egovframework.portal.sys.privacy.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.privacy.ProcType;
import egovframework.portal.sys.privacy.mapper.PrivacyPrmMapper;
import egovframework.portal.sys.privacy.service.PrivacyPrmHistoryService;
import egovframework.portal.sys.privacy.service.PrivacyPrmService;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class PrivacyPrmServiceImpl extends EgovAbstractServiceImpl implements PrivacyPrmService {

	@Autowired
	protected PrivacyPrmMapper mapper;
	@Autowired
	protected PrivacyPrmHistoryService historyService;

	private Logger LOGGER = LogManager.getLogger();

	@Override
	public int getTotalCnt(PrivacyPrmVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<PrivacyPrmVO> getList(PrivacyPrmVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public PrivacyPrmVO getEntity(PrivacyPrmVO searchVO) {
		return StringUtil.isNotBlank(searchVO.getIdx()) ? mapper.getEntity(searchVO) : null;
	}

	@Override
	public PrivacyPrmVO getEntityByPtIdx(String ptIdx) {
		return mapper.getEntityByPtIdx(ptIdx);
	}

	@Override
	public String insert(PrivacyPrmVO searchVO, AdminLoginVO admin, String hostIp) {
		try {
			PrivacyPrmVO insertVO = BeanUtil.copy(searchVO, new PrivacyPrmVO());
			mapper.insert(insertVO); // 설정 등록
			historyService.insert(insertVO, admin, hostIp, ProcType.INSERT); // 로그 등록
			return insertVO.getIdx();
		} catch (Exception e) {
			LOGGER.error("", e);
		}

		return null;
	}

	@Override
	public String update(PrivacyPrmVO searchVO, AdminLoginVO admin, String hostIp) {
		try {
			PrivacyPrmVO config = getEntity(searchVO);
			if (config != null) {
				PrivacyPrmVO updateVO = BeanUtil.copy(searchVO, new PrivacyPrmVO());
				mapper.update(updateVO); // 설정 변경
				historyService.insert(updateVO, admin, hostIp, ProcType.UPDATE); // 로그 등록
				return updateVO.getIdx();
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}

		return null;
	}

	@Override
	public String delete(PrivacyPrmVO searchVO, AdminLoginVO admin, String hostIp) {
		try {
			PrivacyPrmVO config = getEntity(searchVO);
			if (config != null) {
				mapper.delete(config); // 설정 삭제
				historyService.insert(config, admin, hostIp, ProcType.DELETE); // 로그 등록
				return config.getIdx();
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}

		return null;
	}

}
