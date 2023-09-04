package egovframework.portal.unit.bmc.look.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.look.vo.LookCustomPayVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface LookMapper {

	List<LookCustomPayVO> getList(LookCustomPayVO lookCustomPayVO);
	
	List<LookCustomPayVO> getReceiptList(LookCustomPayVO lookCustomPayVO);

	List<LookCustomPayVO> getList2(LookCustomPayVO lookCustomPayVO);

}
