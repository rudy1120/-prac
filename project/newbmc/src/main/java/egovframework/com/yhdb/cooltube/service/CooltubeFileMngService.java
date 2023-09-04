package egovframework.com.yhdb.cooltube.service;

import java.util.List;

import egovframework.com.yhdb.cooltube.vo.MovFileVO;
import egovframework.com.yhdb.cooltube.vo.MovThumbFileVO;

public interface CooltubeFileMngService {

	public List<MovThumbFileVO> selectMovThumbFileList(MovThumbFileVO inputVO);
	
	public MovThumbFileVO selectMovThumbFileDetail(MovThumbFileVO inputVO);
	
	public MovFileVO selectMovFileDetail(MovFileVO inputVO);
	
	public void updateMovThumbnailMain(MovThumbFileVO inputVO);

}
