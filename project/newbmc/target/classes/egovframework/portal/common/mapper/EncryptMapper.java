package egovframework.portal.common.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface EncryptMapper {

	public int getTotalCnt(Map<String, Object> params);

	public List<Map<String, String>> getList(Map<String, Object> params);

	public void update(Map<String, String> target);

}
