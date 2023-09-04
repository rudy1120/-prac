package egovframework.portal.common.service;

import java.util.List;

import egovframework.portal.common.vo.CommonCodeVO;

public interface CommonCodeService {

	/**
	 * 공통코드 리스트 조회
	 *
	 * @return
	 */
	public List<CommonCodeVO> getCodeList(CommonCodeVO commonCode);

}
