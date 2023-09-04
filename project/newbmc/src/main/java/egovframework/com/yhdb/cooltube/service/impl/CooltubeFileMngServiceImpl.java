package egovframework.com.yhdb.cooltube.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.yhdb.cooltube.dao.CooltubeFileDAO;
import egovframework.com.yhdb.cooltube.service.CooltubeFileMngService;
import egovframework.com.yhdb.cooltube.vo.MovFileVO;
import egovframework.com.yhdb.cooltube.vo.MovThumbFileVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * cooltube 서비스 정의
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016-09-29		박동환				최초 생성 및 코딩
 * 
 * </pre>
 *
 * @author 개발팀 박동환
 * @since 2016-09-29
 * @version 1.0
 */
@Service("CooltubeFileMngService")
public class CooltubeFileMngServiceImpl extends EgovAbstractServiceImpl implements CooltubeFileMngService {

	@Resource(name = "CooltubeFileDAO")
	private CooltubeFileDAO cooltubeFileDAO;

	/**
	 * 동영상 썸네일 파일 리스트 조회
	 *
	 * @return
	 */
	public List<MovThumbFileVO> selectMovThumbFileList(MovThumbFileVO inputVO)
	{
		return cooltubeFileDAO.selectMovThumbFileList(inputVO);
	}
	
	/**
	 * 동영상 썸네일 파일 조회
	 *
	 * @return
	 */
	public MovThumbFileVO selectMovThumbFileDetail(MovThumbFileVO inputVO)
	{
		return cooltubeFileDAO.selectMovThumbFileDetail(inputVO);
	}
	
	
	/**
	 * 동영상 파일 조회
	 *
	 * @return
	 */
	public MovFileVO selectMovFileDetail(MovFileVO inputVO)
	{
		return cooltubeFileDAO.selectMovFileDetail(inputVO);
	}
	
	/**
	 * 대표썸네일 변경 
	 */
	public void updateMovThumbnailMain(MovThumbFileVO inputVO)
	{
		cooltubeFileDAO.updateMovThumbnailMainReset(inputVO);
		cooltubeFileDAO.updateMovThumbnailMain(inputVO);
	}
	

}
