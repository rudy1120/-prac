package egovframework.portal.sys.pub.mapper;

import java.util.List;

import egovframework.portal.sys.pub.vo.SysPublicVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysPublicMapper {

	List<SysPublicVO> getCategoryList(SysPublicVO publicVO) throws Exception;
	
	SysPublicVO getCategoryEntity(SysPublicVO publicVO) throws Exception;
	
	void insertCategory(SysPublicVO publicVO) throws Exception;

	void updateCategory(SysPublicVO publicVO) throws Exception;

	void deleteCategory(SysPublicVO publicVO) throws Exception;
	
	int getDataTotalCnt(SysPublicVO publicVO) throws Exception;

	List<SysPublicVO> getDataList(SysPublicVO publicVO) throws Exception;
	
	SysPublicVO getDataEntity(SysPublicVO publicVO) throws Exception;
	
	void insertData(SysPublicVO publicVO) throws Exception;

	void updateData(SysPublicVO publicVO) throws Exception;

	void deleteData(SysPublicVO publicVO) throws Exception;

	List<SysPublicVO> getMonitorList(SysPublicVO publicVO);

	int getMonitorTotalCnt(SysPublicVO publicVO);

	void deleteMonitor(SysPublicVO publicVO);

}
