package egovframework.portal.unit.bmc.pub.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.pub.vo.PublicVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface PublicMapper {

	List<PublicVO> getCategoryList(PublicVO publicVO) throws Exception;
	
	int getDataTotalCnt(PublicVO publicVO) throws Exception;

	List<PublicVO> getDataList(PublicVO publicVO) throws Exception;
	
	List<PublicVO> getStatsList(PublicVO publicVO) throws Exception;
	
	void insertStats(PublicVO publicVO) throws Exception;

	List<String> getDepartList();
	
	void insert(PublicVO publicVO) throws Exception;
}
