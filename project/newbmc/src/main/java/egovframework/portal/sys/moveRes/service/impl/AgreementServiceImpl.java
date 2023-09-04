package egovframework.portal.sys.moveRes.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.moveRes.mapper.AgreementMapper;
import egovframework.portal.sys.moveRes.service.AgreementService;
import egovframework.portal.sys.moveRes.service.ReserveService;
import egovframework.portal.sys.moveRes.vo.AgreementVO;
import egovframework.portal.sys.moveRes.vo.ReserveVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;

@Service
public class AgreementServiceImpl implements AgreementService {
	
	@Resource
	private AgreementMapper AgreementMapper;
	
	private final Logger LOGGER = LogManager.getLogger();

	/****** 시청앞 행복주택 ******/
	@Override
	public int getCityhallTCnt(AgreementVO agreementVO) throws Exception {
		return AgreementMapper.getCityhallTCnt(agreementVO);
	}
	
	@Override
	public List<AgreementVO> getCityhallList(AgreementVO agreementVO) throws Exception {
		return AgreementMapper.getCityhallList(agreementVO);
	}
	
	@Override
	public List<AgreementVO> getExcelList1(AgreementVO agreementVO) throws Exception {
		
		return AgreementMapper.getExcelList1(agreementVO);
	}
	
	
	/****** 아미4 행복주택 ******/
	@Override
	public int getAmiTCnt(AgreementVO agreementVO) throws Exception {
		return AgreementMapper.getAmiTCnt(agreementVO);
	}
	
	@Override
	public List<AgreementVO> getAmiList(AgreementVO agreementVO) throws Exception {
		return AgreementMapper.getAmiList(agreementVO);
	}
	
	@Override
	public List<AgreementVO> getExcelList2(AgreementVO agreementVO) throws Exception {
		
		return AgreementMapper.getExcelList2(agreementVO);
	}

}
