package egovframework.portal.bbs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.dept.vo.DeptVO;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;

/**
 * 게시글 관리 서비스 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014. 11. 06.	김혜민				최초 생성
 * 2017. 05. 18.	J.Ryeon Lee			코드 리팩토링, 불필요한 메소드 제거
 * 2019. 06. 14.	J.Ryeon Lee			추가 검증 로직 추가(첨부파일용량제한 등 추가 컬럼 검증을 위해 추가했으나 추후 부가적인 검증이 필요한 경우 #validate 메소드에 기술 요망)
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 4. 19.
 */
public interface BbsService {

	/** 게시판 목록용 검색 조건 성형 */
	public BbsMngVO formatSearcher(String siteGroup, String siteCode, BbsConfigVO bbsConfigVO, BbsMngVO searchVO) throws Exception;

	/** 공지 조회 */
	public List<BbsMngVO> getBbsNoticeList(BbsMngVO searchVO) throws Exception;

	/** 게시글 목록 */
	public List<BbsMngVO> getBbsList(BbsMngVO searchVO) throws Exception;

	/** 게시글 목록 개수 */
	public int getBbsCnt(BbsMngVO searchVO);

	/** 게시글 상세 */
	public BbsMngVO getBbsView(BbsMngVO searchVO) throws Exception;

	/** 게시글 답변 목록 */
	public List<BbsMngVO> getBbsReplyList(BbsMngVO searchVO) throws Exception;

	/** 게시글 조회수 증가 */
	public void setBbsViewCount(BbsMngVO searchVO);

	/** 게시글 등록 @return success: {bIdx}, fail: null */
	public String insertBbs(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO);

	/** 게시글 비밀번호 디코딩 */
	public String getBoardPasswdDecode(BbsMngVO searchVO) throws Exception;

	/** 게시글 수정 @return success: {bIdx}, fail: null */
	public String updateBbs(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO);

	/** 게시글 삭제 @return success: {bIdx}, fail: null */
	public String deleteBbs(HttpServletRequest request, BbsMngVO searchVO);

	/** 게시글 답변/답글 등록 @return success: {bIdx}, fail: null */
	public String replyInsertBbs(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO);

	/** 사이트 코드에 해당하는 부서코드 */
	public String getDeptSiteCode(String siteCode);

	/** 사이트코드에 해당하는 하위 부서 목록 */
	public List<DeptVO> subDeptList(String siteCode);

	/** 게시판 부서 카테고리 목록 */
	public List<DeptVO> getBbsDeptListCa(String ptCategoryYn, String ptCategoryGubun);

	/** 하위 게시글 목록 개수 */
	public int getReplyCnt(BbsMngVO searchVO);

	/**
	 * 등록/수정 전 추가 검증 처리(첨부파일 용량, 게시글 제목 길이 등)
	 *
	 * @param bbsConfigVO 게시판 설정
	 * @param target 검증 대상
	 * @param fileMap 첨부 파일 정보
	 * @param result error 객체
	 */
	public void validate(BbsConfigVO bbsConfigVO, BbsMngVO target, Map<String, MultipartFile> fileMap, BindingResult result);

	public List<BbsMngVO> getMainBbsList(BbsMngVO bbsVO) throws Exception;

}
