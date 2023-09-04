package egovframework.portal.unit.bmc.opendoc.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.opendoc.vo.OpendocVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface OpendocMapper {
	
	public int getListCnt(OpendocVO opendocVO);

	public List<OpendocVO> getList(OpendocVO opendocVO);
	
	public OpendocVO getContent(OpendocVO opendocVO);

}
