package egovframework.portal.sys.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

public interface BbsMngService {

	/** 공지 목록 */
	public List<BbsMngVO> getBbsMngNoticeList(BbsMngVO searchVO) throws Exception;

	/** 게시글 목록 */
	public List<BbsMngVO> getBbsMngList(BbsMngVO searchVO);

	/** 게시글 목록 개수 */
	public int getBbsMngCnt(BbsMngVO searchVO);

	/** 게시글 등록 @return success: bIdx, fail: null */
	public String insertBbsMng(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception;

	/** FETCH ENTITY */
	public BbsMngVO getBbsMngView(BbsMngVO searchVO) throws Exception;

	/** 답변/답글 목록 */
	public List<BbsMngVO> getBbsMngReplyList(BbsMngVO searchVO) throws Exception;

	/** 게시글 수정 @return success: bIdx, fail: null */
	public String updateBbsMng(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception;

	/** 게시글 삭제 */
	public void deleteBbsMng(HttpServletRequest request, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception;

	/** 답변/답글 등록 @return success: bIdx, fail: null */
	public String replyInsertBbsMng(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception;

	/** 게시글 조회수 증가 */
	public void setBbsMngViewCount(BbsMngVO searchVO);

	/** 답변/답글 개수 */
	public int getReplyCnt(BbsMngVO searchVO);

	/** 게시글 이동 */
	public void moveBoardInsert(BbsMngVO origin, AdminLoginVO admin, String toPtIdx) throws Exception;

	/** 게시글 복원 */
	public void reDeleteBbsMng(HttpServletRequest request, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception;

	/** 게시글 일괄 삭제 @author 상천규 */
	public void bundleDeleteBbsMng(HttpServletRequest request, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception;

	/** 게시글 일괄 복원 @author 상천규 */
	public void bundleRedeleteBbsMng(HttpServletRequest request, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception;

	public List<BbsMngVO> getMainMgt(BbsMngVO searchVO);

	public String uncheck(BbsMngVO searchVO);

	public void orderUpdate(BbsMngVO searchVO);
	

}
