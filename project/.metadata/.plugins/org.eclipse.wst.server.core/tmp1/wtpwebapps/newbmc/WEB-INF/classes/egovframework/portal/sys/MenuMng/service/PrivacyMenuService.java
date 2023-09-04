package egovframework.portal.sys.MenuMng.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.portal.sys.log.vo.LoggingVO;

public interface PrivacyMenuService {

	int getTotalCnt(SysMenuVO searchVO);

	List<SysMenuVO> getList(SysMenuVO searchVO);

	List<Map<String, String>> getTotalListAsMap(LoggingVO searchVO);

}
