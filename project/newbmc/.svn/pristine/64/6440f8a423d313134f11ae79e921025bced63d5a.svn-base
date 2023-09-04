package egovframework.portal.sys.pub.service;

import java.util.List;

import egovframework.portal.sys.pub.vo.SysPublicVO;

public interface SysPublicService {
	
	/** 카테고리 목록 */
	List<SysPublicVO> getCategoryList(SysPublicVO publicVO) throws Exception;
	
	/** 카테고리 상세 */
	SysPublicVO getCategoryEntity(SysPublicVO publicVO) throws Exception;
	
	/** 카테고리 등록 */
	String insertCategory(SysPublicVO publicVO) throws Exception;

	/** 카테고리 수정 */
	String updateCategory(SysPublicVO publicVO) throws Exception;

	/** 카테고리 삭제 */
	String deleteCategory(SysPublicVO publicVO) throws Exception;
	
	/** 사전정보공표 카운트 */
	int getDataTotalCnt(SysPublicVO publicVO) throws Exception;

	/** 사전정보공표목록 */
	List<SysPublicVO> getDataList(SysPublicVO publicVO) throws Exception;
	
	/** 사전정보공표 상세 */
	SysPublicVO getDataEntity(SysPublicVO publicVO) throws Exception;
	
	/** 사전정보공표 등록 */
	String insertData(SysPublicVO publicVO) throws Exception;

	/** 사전정보공표 수정 */
	String updateData(SysPublicVO publicVO) throws Exception;

	/** 사전정보공표 삭제 */
	String deleteData(SysPublicVO publicVO) throws Exception;
	
	/*모니터링 카운트 */
	int getMonitorTotalCnt(SysPublicVO publicVO);

	List<SysPublicVO>  getMonitorList(SysPublicVO publicVO);

	String deleteMonitor(SysPublicVO publicVO) throws Exception;

}


