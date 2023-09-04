package egovframework.portal.sys.common.mapper;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("fileDownInfoMngMapper")
public interface FileDownInfoMngMapper {

	public void incrementCnt(FileVO fvo);

	/**
	 * 해당 파일의 다운로드 횟수를 조회합니다.
	 *
	 * @Method Name : selectFileDownCount
	 * @param fvo
	 * @return
	 */
	public int selectFileDownCount(FileVO fvo);

}
