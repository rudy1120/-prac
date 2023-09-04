package egovframework.com.yhdb.cooltube.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.yhdb.cooltube.vo.MovFileVO;
import egovframework.com.yhdb.cooltube.vo.MovThumbFileVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("CooltubeFileDAO")
public class CooltubeFileDAO extends EgovAbstractMapper {

	/**
	 * 동영상 썸네일 파일 리스트 조회
	 *
	 * @return
	 */
	public List<MovThumbFileVO> selectMovThumbFileList(MovThumbFileVO inputVO) {
		return selectList("CooltubeFileDAO.selectMovThumbFileList", inputVO);
	}

	/**
	 * 동영상 썸네일 파일 정보 조회
	 *
	 * @return
	 */
	public MovThumbFileVO selectMovThumbFileDetail(MovThumbFileVO inputVO) {
		return (MovThumbFileVO) selectOne("CooltubeFileDAO.selectMovThumbFileDetail", inputVO);
	}

	/**
	 * 동영상 파일 정보 조회
	 *
	 * @param inputVO
	 * @return
	 */
	public MovFileVO selectMovFileDetail(MovFileVO inputVO) {
		return (MovFileVO) selectOne("CooltubeFileDAO.selectMovFileDetail", inputVO);
	}

	/**
	 * 대표썸네일 초기화
	 *
	 * @param inputVO
	 */
	public void updateMovThumbnailMainReset(MovThumbFileVO inputVO) {
		update("CooltubeFileDAO.updateMovThumbnailMainReset", inputVO);
	}

	/**
	 * 대표썸네일 설정
	 *
	 * @param inputVO
	 */
	public void updateMovThumbnailMain(MovThumbFileVO inputVO) {
		update("CooltubeFileDAO.updateMovThumbnailMain", inputVO);
	}

}
