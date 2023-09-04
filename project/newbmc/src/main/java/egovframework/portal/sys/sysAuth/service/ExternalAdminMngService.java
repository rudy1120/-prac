package egovframework.portal.sys.sysAuth.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 외부 관리자 열람/생성/수정/삭제 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.09.22		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.09.22
 */
public interface ExternalAdminMngService {

	/** 외부 관리자 목록 */
	List<AdminLoginVO> getList(AdminLoginVO searchVO);

	/** 외부 관리자 전체 건수 */
	int getTotalCnt(AdminLoginVO searchVO);

	/** 외부 관리자 상세 */
	AdminLoginVO getEntity(String id, String adminTypeCode);

	/** 외부 관리자 등록 */
	void insert(AdminLoginVO insertVO) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;

	/** 외부 관리자 갱신 */
	void update(AdminLoginVO updateVO) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;

	/** 외부 관리자 삭제 */
	void delete(AdminLoginVO deleteVO);

	/** 엑셀 업로드 */
	int upload(MultipartFile file, Boolean overwrite) throws IOException;

	/** @return id/adminTypeCode 조합의 유일 여부 */
	Boolean isUnique(AdminLoginVO searchVO);

}
