package egovframework.portal.common.mapper;

import java.util.List;

import egovframework.portal.common.vo.CommonCodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("commonCodeMapper")
public interface CommonCodeMapper {

	/**
	 * 공통코드 리스트 조회
	 *
	 * @return
	 */
	public List<CommonCodeVO> getCodeList(CommonCodeVO commonCode);

}
