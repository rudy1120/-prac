package egovframework.portal.sys.sabo.service;

import java.util.List;

import egovframework.portal.sys.sabo.vo.SysSaboVO;

public interface SysSaboService {

	int getTotalCnt(SysSaboVO searchVO) throws Exception;

	List<SysSaboVO> getList(SysSaboVO searchVO) throws Exception;

	List<SysSaboVO> getExcelList(SysSaboVO searchVO) throws Exception;

}
