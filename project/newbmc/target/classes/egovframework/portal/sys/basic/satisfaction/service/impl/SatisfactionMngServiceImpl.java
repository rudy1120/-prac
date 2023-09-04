package egovframework.portal.sys.basic.satisfaction.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.satisfaction.mapper.SatisfactionMngMapper;
import egovframework.portal.sys.basic.satisfaction.service.SatisfactionMngService;
import egovframework.portal.sys.basic.satisfaction.vo.SatisfactionMngVO;

@Service
public class SatisfactionMngServiceImpl implements SatisfactionMngService {

	@Autowired
	protected SatisfactionMngMapper satisfactionMngMapper;

	@Override
	public int checkDuplSatisfaction(SatisfactionMngVO inputVo) {
		return satisfactionMngMapper.checkDuplSatisfaction(inputVo);
	}

	@Override
	public void insertSatisfaction(SatisfactionMngVO inputVo) {
		satisfactionMngMapper.insertSatisfaction(inputVo);
	}

	@Override
	public List<MenusMngVO> comboMenusForSite(String siteCode) {
		return satisfactionMngMapper.comboMenusForSite(siteCode);
	}

	@Override
	public int getSatisfactionStatCnt(SatisfactionMngVO inputVO) {
		return satisfactionMngMapper.getSatisfactionStatCnt(inputVO);
	}

	@Override
	public List<SatisfactionMngVO> getSatisfactionStatList(SatisfactionMngVO inputVO) {
		return satisfactionMngMapper.getSatisfactionStatList(inputVO);
	}

	@Override
	public List<Map<String, String>> getSatisfactionStatListAsMap(SatisfactionMngVO searchVO) {
		return satisfactionMngMapper.getSatisfactionStatListAsMap(searchVO);
	}

	@Override
	public int getTotalCnt(SatisfactionMngVO searchVO) {
		return satisfactionMngMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<SatisfactionMngVO> getTotalList(SatisfactionMngVO searchVO) {
		return satisfactionMngMapper.getTotalList(searchVO);
	}

	@Override
	public List<Map<String, String>> getTotalListAsMap(SatisfactionMngVO searchVO) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, String>> rtn = new ArrayList<>();
		List<SatisfactionMngVO> list = satisfactionMngMapper.getTotalListAsMap(searchVO);

		Map<String, String> row = null;
		for (SatisfactionMngVO element : list) {
			row = new HashMap<>();
			row.put("siteCode", element.getSiteCode());
			row.put("menuName", element.getMenuName());
			row.put("researchContent", element.getResearchContent());
			row.put("researchPoint", String.valueOf(element.getResearchPoint()));
			row.put("regDt", formatter.format(element.getRegDt()));
			rtn.add(row);
		}

		return rtn;
	}
}
