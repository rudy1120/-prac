package egovframework.portal.sys.common.service;

import egovframework.com.cmm.service.FileVO;

/**
 * 첨부파일 다운로드 횟수 관리 SERVICE
 *
 * @author J.Ryeon Lee
 * @since 2016.05.17
 */
public interface FileDownInfoMngService {

	void incrementCnt(FileVO fvo);

	/**
	 * 해당 파일의 다운로드 횟수를 조회합니다.
	 * @Method Name : selectFileDownCount
	 * @param fvo
	 * @return
	 */
	public int selectFileDownCount(FileVO fvo);

}
