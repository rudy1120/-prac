package egovframework.portal.sys.sysAuth.mapper;

import java.util.List;

import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 외부 관리자 관리 MAPPER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.09.22	J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.09.22
 */
@Mapper
public interface ExternalAdminMngMapper {

	/** {@link #getEntity(AdminLoginVO)} 사용을 추천 */
	@Deprecated
	public AdminLoginVO getAdmin(AdminLoginVO searchVO);

	/** 외부 관리자 목록 */
	public List<AdminLoginVO> getList(AdminLoginVO searchVO);

	/** 전체 건수 */
	public int getTotalCnt(AdminLoginVO searchVO);

	/** 단일 관리자 정보 */
	public AdminLoginVO getEntity(AdminLoginVO searchVO);

	/** 등록 */
	public void insert(AdminLoginVO insertVO);

	/** 수정 */
	public void update(AdminLoginVO updateVO);

	/** 삭제 (플래그 전환) */
	public void delete(AdminLoginVO deleteVO);

	/** 전체 삭제 (완전 삭제) */
	public void deleteAll();

}
