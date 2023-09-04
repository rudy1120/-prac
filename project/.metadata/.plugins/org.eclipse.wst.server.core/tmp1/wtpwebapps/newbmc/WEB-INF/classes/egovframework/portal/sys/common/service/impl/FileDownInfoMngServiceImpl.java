package egovframework.portal.sys.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.FileVO;
import egovframework.portal.sys.common.mapper.FileDownInfoMngMapper;
import egovframework.portal.sys.common.service.FileDownInfoMngService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 첨부파일 다운로드 정보 관련 SERVICE IMPL
 *
 * @author J.Ryeon Lee
 * @since 2061.05.17
 */
@Service
public class FileDownInfoMngServiceImpl extends EgovAbstractServiceImpl implements FileDownInfoMngService {

	@Resource(name = "fileDownInfoMngMapper")
	protected FileDownInfoMngMapper fileDownInfoMngMapper;

	@Override
	public void incrementCnt(FileVO fvo) {
		fileDownInfoMngMapper.incrementCnt(fvo);
	}

	/**
	 * 해당 파일의 다운로드 횟수를 조회합니다.
	 *
	 * @Method Name : selectFileDownCount
	 * @param fvo
	 * @return
	 */
	public int selectFileDownCount(FileVO fvo) {
		return fileDownInfoMngMapper.selectFileDownCount(fvo);
	}

}
