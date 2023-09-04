package egovframework.portal.sys.smsService.mapper;

import java.util.List;
import egovframework.portal.sys.smsService.vo.SmsServiceVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SmsServiceMapper {

	//분양알림 신청 총 개수
	int getTotalCnt(SmsServiceVO searchVO) throws Exception;

	//모집공고 신청 총 개수
	int getTotalCnt2(SmsServiceVO searchVO) throws Exception;
	//모집공고 신청 총 개수
	int getTotalCnt3(SmsServiceVO searchVO) throws Exception;
	
	//분양알림 신청 목록 조회
	List<SmsServiceVO> getList(SmsServiceVO searchVO) throws Exception;

	//모집공고 신청 목록 조회
	List<SmsServiceVO> getList2(SmsServiceVO searchVO) throws Exception;
	
	//문자발송 내역 조회
	List<SmsServiceVO> getList3(SmsServiceVO searchVO) throws Exception;
	
	//분양모집 엑셀리스트 목록 조회
	List<SmsServiceVO> getExcelList(SmsServiceVO searchVO) throws Exception;

	//모집공고 엑셀리스트 목록 조회
	List<SmsServiceVO> getExcelList2(SmsServiceVO searchVO);





}
