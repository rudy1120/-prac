package egovframework.portal.sys.bbs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.bbs.mapper.BbsStatMapper;
import egovframework.portal.sys.bbs.service.BbsStatService;
import egovframework.portal.sys.bbs.vo.BbsFileDownStatVO;
import egovframework.portal.sys.bbs.vo.BbsOperationStatVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class BbsStatServiceImpl extends EgovAbstractServiceImpl implements BbsStatService {

	@Autowired
	protected BbsStatMapper bbsStatMapper;

	@Override
	public int getOperationStatTotalCnt(BbsOperationStatVO searchVO) {
		return bbsStatMapper.getOperationStatTotalCnt(searchVO);
	}

	@Override
	public List<BbsOperationStatVO> getOperationStatList(BbsOperationStatVO searchVO) {
		return bbsStatMapper.getOperationStatList(searchVO);
	}

	@Override
	public List<Map<String, String>> getOperationStatListAsMap(BbsOperationStatVO searchVO) {
		return bbsStatMapper.getOperationStatListAsMap(searchVO);
	}

	@Override
	public Map<String, String> getOperationStatSnippet(BbsOperationStatVO searchVO) {
		return bbsStatMapper.getOperationStatSnippet(searchVO);
	}

	@Override
	public int getFileDownStatTotalCnt(BbsFileDownStatVO searchVO) {
		return bbsStatMapper.getFileDownStatTotalCnt(searchVO);
	}

	@Override
	public List<BbsFileDownStatVO> getFileDownStatList(BbsFileDownStatVO searchVO) {
		return bbsStatMapper.getFileDownStatList(searchVO);
	}

	@Override
	public List<Map<String, String>> getFileDownStatListAsMap(BbsFileDownStatVO searchVO) {
		return bbsStatMapper.getFileDownStatListAsMap(searchVO);
	}

	@Override
	public Map<String, String> getFileDownStatSnippet(BbsFileDownStatVO searchVO) {
		return bbsStatMapper.getFileDownStatSnippet(searchVO);
	}

	@Override
	public String getSummary(String searchPeriodType, String searchYear, String searchMonth, String searchSday, String searchEday) {
		switch (searchPeriodType) {
			case "m":
				return searchYear + "년 " + searchMonth + "월";
			case "r":
				return searchSday + " ~ " + searchEday;
			default:
				return searchYear + "년";
		}
	}

}
