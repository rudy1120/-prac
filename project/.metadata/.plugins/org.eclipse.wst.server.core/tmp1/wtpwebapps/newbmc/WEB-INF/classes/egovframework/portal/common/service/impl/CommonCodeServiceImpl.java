package egovframework.portal.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.common.mapper.CommonCodeMapper;
import egovframework.portal.common.service.CommonCodeService;
import egovframework.portal.common.vo.CommonCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 공통코드 서비스 클래스
 *
 * @author 개발팀 김혜민
 * @since 2014.09.25
 * @version 1.0
 * @see
 */
@Service("commonCodeService")
public class CommonCodeServiceImpl extends EgovAbstractServiceImpl implements CommonCodeService {

	@Resource(name = "commonCodeMapper")
	private CommonCodeMapper commonCodeMapper;

	/**
	 * 공통코드 리스트 조회
	 *
	 * @return
	 */
	public List<CommonCodeVO> getCodeList(CommonCodeVO commonCode) {
		return commonCodeMapper.getCodeList(commonCode);
	}

}
