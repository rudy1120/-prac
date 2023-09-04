package egovframework.portal.sys.smsService.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.sys.smsService.mapper.SmsServiceMapper;
import egovframework.portal.sys.smsService.service.SmsServiceService;
import egovframework.portal.sys.smsService.vo.SmsServiceVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class SmsServiceServiceImpl extends EgovAbstractServiceImpl implements SmsServiceService {

	@Resource
	private SmsServiceMapper smsServicemapper;
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	//분양알림 신청 총 개수
	@Override
	public int getTotalCnt(SmsServiceVO searchVO) throws Exception{
		return smsServicemapper.getTotalCnt(searchVO) ;
	}
	
	//모집공고 알림 신청 총 개수
	@Override
	public int getTotalCnt2(SmsServiceVO searchVO) throws Exception {
		return smsServicemapper.getTotalCnt2(searchVO) ;
	}
	@Override
	public int getTotalCnt3(SmsServiceVO searchVO) throws Exception {
		return smsServicemapper.getTotalCnt3(searchVO) ;
	}

	//분양알림 신청자 목록 조회
	@Override
	public List<SmsServiceVO> getList(SmsServiceVO searchVO) throws Exception {
		return smsServicemapper.getList(searchVO);
	}
	
	//분양알림 신청 목록 조회
	@Override
	public List<SmsServiceVO> getList2(SmsServiceVO searchVO) throws Exception {
		return smsServicemapper.getList2(searchVO);
	}
	//문자발송 내역 조회
	@Override
	public List<SmsServiceVO> getList3(SmsServiceVO searchVO) throws Exception {
		return smsServicemapper.getList3(searchVO);
	}
	//분양알림 엑셀리스트 목록 조회
	@Override
	public List<SmsServiceVO> getExcelList(SmsServiceVO searchVO) throws Exception {
		return smsServicemapper.getExcelList(searchVO);
	}

	//모집공고 엑셀리스트 목록 조회
	@Override
	public List<SmsServiceVO> getExcelList2(SmsServiceVO searchVO) throws Exception {
		return smsServicemapper.getExcelList2(searchVO);
	}
	


}
