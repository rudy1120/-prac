package egovframework.portal.sys.MenuMng.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.content.vo.SysMenuVO;
import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface PrivacyMenuMapper {

	public int getTotalCnt(SysMenuVO searchVO);

	public List<SysMenuVO> getList(SysMenuVO searchVO);

	public List<Map<String, String>> getTotalListAsMap(LoggingVO searchVO);

}
