package egovframework.com.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileMasterVO;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovFileMngServiceImpl.java
 * @Description : 파일정보의 관리를 위한 구현 클래스
 * @Modification Information
 *               수정일 수정자 수정내용
 *               ------- ------- -------------------
 *               2009. 3. 25. 이삼섭 최초생성
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 */
@Service("EgovFileMngService")
public class EgovFileMngServiceImpl extends EgovAbstractServiceImpl implements EgovFileMngService {

	@Resource(name = "FileManageDAO")
	private FileManageDAO fileMngDAO;

	/**
	 * 여러 개의 파일을 삭제한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#deleteFileInfs(java.util.List)
	 */
	public void deleteFileInfs(List<?> fvoList) throws Exception {
		fileMngDAO.deleteFileInfs(fvoList);
	}

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#insertFileInf(egovframework.com.cmm.service.FileVO)
	 */
	public String insertFileInf(FileVO fvo) throws Exception {
		String atchFileId = fvo.getAtchFileId();

		fileMngDAO.insertFileInf(fvo);

		return atchFileId;
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#insertFileInfs(java.util.List)
	 */
	public String insertFileInfs(List<?> fvoList) throws Exception {
		String atchFileId = "";

		if (fvoList.size() != 0) {
			atchFileId = fileMngDAO.insertFileInfs(fvoList);
		}
		if (atchFileId == "") {
			atchFileId = null;
		}
		return atchFileId;
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectFileInfs(egovframework.com.cmm.service.FileVO)
	 */
	public List<FileVO> selectFileInfs(FileVO fvo) throws Exception {
		return fileMngDAO.selectFileInfs(fvo);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#updateFileInfs(java.util.List)
	 */
	public void updateFileInfs(List<?> fvoList) throws Exception {
		//Delete & Insert
		fileMngDAO.updateFileInfs(fvoList);
	}

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#deleteFileInf(egovframework.com.cmm.service.FileVO)
	 */
	public void deleteFileInf(FileVO fvo) throws Exception {
		fileMngDAO.deleteFileInf(fvo);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectFileInf(egovframework.com.cmm.service.FileVO)
	 */
	public FileVO selectFileInf(FileVO fvo) throws Exception {
		return fileMngDAO.selectFileInf(fvo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#getMaxFileSN(egovframework.com.cmm.service.FileVO)
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception {
		return fileMngDAO.getMaxFileSN(fvo);
	}

	/**
	 * 전체 파일을 삭제한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#deleteAllFileInf(egovframework.com.cmm.service.FileVO)
	 */
	public void deleteAllFileInf(FileVO fvo) throws Exception {
		fileMngDAO.deleteAllFileInf(fvo);
	}

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectFileListByFileNm(egovframework.com.cmm.service.FileVO)
	 */
	public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception {
		List<FileVO> result = fileMngDAO.selectFileListByFileNm(fvo);
		int cnt = fileMngDAO.selectFileListCntByFileNm(fvo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectImageFileList(egovframework.com.cmm.service.FileVO)
	 */
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
		return fileMngDAO.selectImageFileList(vo);
	}

	/**
	 * 한개의 파일내용 정보를 수정한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#updateFileCn(java.util.List)
	 */
	public void updateFileCn(FileVO fvo) throws Exception {
		fileMngDAO.updateFileCn(fvo);
	}

	/**
	 * fileSN 값 변경
	 *
	 * @param vo
	 */
	public void updateFileSn(FileVO vo) {
		fileMngDAO.updateFileSn(vo);
	}

	@Override
	public void insertFileMaster(FileMasterVO vo) {
		fileMngDAO.insertFileMaster(vo);
	}

	@Override
	public void updateFileMaster(FileMasterVO vo) {
		fileMngDAO.updateFileMaster(vo);
	}

	@Override
	public String getFileAuthInfo(FileMasterVO master) {
		return fileMngDAO.getFileAuthInfo(master);
	}

	@Override
	public Boolean flushable(boolean adminLogin, FileMasterVO master, UserVO user) {
		if (adminLogin) { // 관리자
			return Boolean.TRUE;
		} else if ("N".equals(master.getAuthYn())) { // 전체 공개 파일
			return Boolean.TRUE;
		} else { // 비공개 파일. 작성자만 열람 가능
			String authInfo = getFileAuthInfo(master);
			return user != null && authInfo != null && //
				StringUtil.isNotBlank(authInfo) && //
				user.getPrivatecode().equals(authInfo);
		}
	}
}
