package egovframework.portal.sys.privacy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.privacy.ProcType;
import egovframework.portal.sys.privacy.mapper.PrivacyPrmHistoryMapper;
import egovframework.portal.sys.privacy.service.PrivacyPrmHistoryService;
import egovframework.portal.sys.privacy.vo.PrivacyPrmHistoryVO;
import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class PrivacyPrmHistoryServiceImpl extends EgovAbstractServiceImpl implements PrivacyPrmHistoryService {

	@Autowired
	protected PrivacyPrmHistoryMapper mapper;

	@Override
	public int getTotalCnt(PrivacyPrmHistoryVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<PrivacyPrmHistoryVO> getList(PrivacyPrmHistoryVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(PrivacyPrmHistoryVO searchVO) {
		return mapper.getTotalListAsMap(searchVO);
	}

	@Override
	public void insert(PrivacyPrmVO insertVO, AdminLoginVO admin, String hostIp, ProcType type) {
		Map<String, Object> params = new HashMap<>();
		params.put("data", insertVO);
		params.put("admin", admin);
		params.put("hostIp", hostIp);
		params.put("procType", type.getCode());
		params.put("ptIdx", insertVO.getPtIdx());

		mapper.insert(params);
	}

}
