package egovframework.portal.sys.sms.mapper;

import java.util.List;

import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.smsService.vo.SmsServiceVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SmsMapper {

	//해당 분양공고 선택옵션 신청자 번호 리스트
	public List<SmsServiceVO> getList(String string) throws Exception;

	//해당 임대주택 선택옵션 신청자 번호 리스트
	public List<SmsServiceVO> getList2(String string) throws Exception;
	
	//SC_TRAN 테이블 삽입
	public void insertSmsQueue(BbsMngVO searchVO) throws Exception;

	//SC_TRAN 존재하는지 확인
	public String getSmsQueue(String bIdx) throws Exception;

	//SC_TRAN 삭제
	public void deleteSmsQueue(String string) throws Exception;

	public List<SmsServiceVO> getList3(String string) throws Exception;


	

}
