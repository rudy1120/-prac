package egovframework.portal.sys.sms.service;

import egovframework.portal.sys.bbs.vo.BbsMngVO;

public interface SmsService {

	//문자 SC_TRAN insert
	void insertSmsQueue(BbsMngVO searchVO) throws Exception;

	//문자 서비스 수정 작업
	void updateCk(BbsMngVO searchVO) throws Exception;

	//문자 서비스 삭제 작업
	void delCk(BbsMngVO searchVO) throws Exception;

}
