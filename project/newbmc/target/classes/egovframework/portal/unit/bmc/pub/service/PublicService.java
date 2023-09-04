package egovframework.portal.unit.bmc.pub.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.portal.unit.bmc.pub.vo.PublicVO;

public interface PublicService {
	
	/** 카테고리 목록 */
	List<PublicVO> getCategoryList(PublicVO publicVO) throws Exception;
	
	/** 사전정보공표 카운트 */
	int getDataTotalCnt(PublicVO publicVO) throws Exception;

	/** 사전정보공표목록 */
	List<PublicVO> getDataList(PublicVO publicVO) throws Exception;
	
	/** 사전정보공표 조회수 */
	List<PublicVO> getStatsList(PublicVO publicVO) throws Exception;
	
	/** 사전정보공표 조회수 집계 */
	String insertStats(PublicVO publicVO) throws Exception;

	List<String> getDepartList();

	String insert(PublicVO publicVO, HttpServletRequest request) throws Exception;
	
}


