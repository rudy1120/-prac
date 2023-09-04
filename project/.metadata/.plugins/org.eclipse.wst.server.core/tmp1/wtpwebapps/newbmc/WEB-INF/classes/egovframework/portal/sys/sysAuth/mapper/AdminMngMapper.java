package egovframework.portal.sys.sysAuth.mapper;

import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("adminMngMapper")
public interface AdminMngMapper {

	public void update(AdminLoginVO updateVO);

	public void increaseFailedCnt(AdminLoginVO searchVO);

	public void resetFailedCnt(AdminLoginVO searchVO);

	public void updateLastestLoginDt(AdminLoginVO searchVO);

}
