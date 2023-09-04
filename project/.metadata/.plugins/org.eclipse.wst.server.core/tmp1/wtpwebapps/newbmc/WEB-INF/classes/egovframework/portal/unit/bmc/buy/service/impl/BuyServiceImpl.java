package egovframework.portal.unit.bmc.buy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.buy.mapper.BuyMapper;
import egovframework.portal.unit.bmc.buy.service.BuyService;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;

@Service
public class BuyServiceImpl implements BuyService {
	
	@Autowired
	protected BuyMapper buyMapper;
	
	//분양매물 목록 총 개수 조회
	@Override
	public int getTotalCnt(SaleVO buyVO) throws Exception {
		return buyMapper.getTotalCnt(buyVO);
	}

	//분양매물목록 데이터 조회
	@Override
	public List<SaleVO> getList(SaleVO buyVO) throws Exception {
		return buyMapper.getList(buyVO);
	}

	//분양중 지역 데이터 리스트 조회
	@Override
	public List<ApplySmsVO> getAreaList() throws Exception {
		return buyMapper.getAreaList();
	}
	
	//전체 공급용도 데이터
	@Override
	public List<ApplySmsVO> getAllPurposerList() throws Exception {
		return buyMapper.getAllPurposerList();
	}

	@Override
	public String getMapInfo(SaleVO saleVO) {
		
		return buyMapper.getMapInfo(saleVO);
	}

}
