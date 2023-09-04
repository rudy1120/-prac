package egovframework.portal.sys.privacy.mapper;

import java.util.List;

import egovframework.portal.sys.privacy.vo.PrivacyPrmVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface PrivacyPrmMapper {

	int getTotalCnt(PrivacyPrmVO searchVO);

	List<PrivacyPrmVO> getList(PrivacyPrmVO searchVO);

	PrivacyPrmVO getEntity(PrivacyPrmVO searchVO);

	PrivacyPrmVO getEntityByPtIdx(String ptIdx);

	void insert(PrivacyPrmVO insertVO);

	void update(PrivacyPrmVO updateVO);

	void delete(PrivacyPrmVO deleteVO);

}
