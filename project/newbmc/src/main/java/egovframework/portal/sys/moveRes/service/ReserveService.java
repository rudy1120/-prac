package egovframework.portal.sys.moveRes.service;

import java.util.List;

import egovframework.portal.sys.moveRes.vo.ReserveVO;

public interface ReserveService {

	int getTCnt(ReserveVO reserveVO) throws Exception;
	int getTotalCnt(ReserveVO reserveVO) throws Exception;
	String deleteRes(ReserveVO reserveVO);
	String deleteCust(ReserveVO reserveVO);
	
	List<ReserveVO> getList(ReserveVO reserveVO) throws Exception;
	List<ReserveVO> getDetailList(ReserveVO reserveVO) throws Exception;
	List<ReserveVO> getExcelList(ReserveVO reserveVO) throws Exception;
	List<ReserveVO> chkTime(ReserveVO reserveVO) throws Exception;
	
	ReserveVO getEntity(ReserveVO reserveVO);
	ReserveVO chkCust(ReserveVO reserveVO);
	ReserveVO chkReserve(ReserveVO reserveVO);
	ReserveVO getTitle(ReserveVO reserveVO);
	
	void resUp(ReserveVO reserveVO);
	void resInsert(ReserveVO reserveVO);
	void regiInsert(ReserveVO reserveVO);
	int chkRes(ReserveVO reserveVO);
	
}
