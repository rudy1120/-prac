package egovframework.portal.bbs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileMasterVO;
import egovframework.portal.bbs.BbsType;
import egovframework.portal.bbs.service.BbsService;
import egovframework.portal.common.CategoryGubunType;
import egovframework.portal.common.Constant;
import egovframework.portal.common.TransDefinition;
import egovframework.portal.dept.service.DeptService;
import egovframework.portal.dept.vo.DeptVO;
import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.bbs.ProcessType;
import egovframework.portal.sys.bbs.mapper.BbsLogMngMapper;
import egovframework.portal.sys.bbs.mapper.BbsMngMapper;
import egovframework.portal.sys.bbs.service.impl.BbsMngServiceImpl;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.log.vo.BbsLog;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.portal.util.UserUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 게시글 서비스 클래스
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
@Service("bbsService")
public class BbsServiceImpl extends EgovAbstractServiceImpl implements BbsService {

	@Resource(name = "bbsMngMapper")
	private BbsMngMapper bbsMngMapper;
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	@Autowired
	private BbsLogMngMapper bbsLogMngMapper;
	@Autowired
	protected DeptService deptService;
	@Resource(name = "txManager")
	private PlatformTransactionManager transactionManager;

	public final Logger LOGGER = Logger.getLogger(BbsServiceImpl.class.getName());

	/** @return 파일 권한 검증 여부 {@link BbsMngServiceImpl#authYn}과 동일 */
	private String authYn(BbsMngVO bbsMngVO, BbsConfigVO bbsConfigVO) {
		if ("N".equals(bbsMngVO.getbPublic()) || "Y".equals(bbsConfigVO.getPtReportYn()) || bbsConfigVO.getPtType().equals(BbsType.MINWON.getCode())) {
			return "Y"; // 비공개 게시글, 민원 게시글, 신고형 게시글
		}

		return "N";
	}

	@Override
	public BbsMngVO formatSearcher(String siteGroup, String siteCode, BbsConfigVO bbsConfigVO, BbsMngVO searchVO)
		throws Exception {
		BbsType bbsType = BbsType.toType(bbsConfigVO.getPtType());
		if (!(bbsType == BbsType.BASIC && "Y".equals(bbsConfigVO.getPtReplyYn()))) {
			searchVO.setSearchLevel("0"); // 일반 게시판이 아니거나 답글을 허용하지 않는 경우는 b_level = 0인 게시글만 fetch
		}

		// 대표가 아닌 사이트의 공통 게시판인 경우 부서코드 쿼리 추가
		if ("common".equals(bbsConfigVO.getPtSiteCode()) && StringUtil.isNotBlank(siteGroup) && !SiteCode.PORTAL.getCode().equalsIgnoreCase(siteGroup)) {
			if ("Y".equals(bbsConfigVO.getPtCategoryYn()) && CategoryGubunType.useDeptCategory(bbsConfigVO.getPtCategoryGubun())) {
				searchVO.setSearchDeptList(subDeptList(siteCode));
			}
		}

		// 게시글이며 대표 홈페이지일 경우 포탈사이트 노출 여부 확인
		if (SiteCode.COMMON.getCode().equals(bbsConfigVO.getPtSiteCode()) && SiteCode.PORTAL.getCode().equals(siteCode)) {
			searchVO.setSearchMainYn("Y");
		}

		// 비밀글을 지원하는 일반 게시판인 경우 공개글만 검색하도록
		if (StringUtil.isNotBlank(searchVO.getSearchTxt()) && "Y".equals(bbsConfigVO.getPtPublicYn())) {
			searchVO.setSearchPublicYn("Y");
		}

		// 신고형 게시판의 경우 본인 작성글 목록만 검색
		if ("Y".equals(bbsConfigVO.getPtReportYn())) {
			searchVO.setSearchPrivatecode(UserUtil.getDi());
		}

		searchVO.setSearchTxt(TUtil.cleanXSS(searchVO.getSearchTxt()));

		return searchVO;
	}

	@Override
	public List<BbsMngVO> getBbsNoticeList(BbsMngVO searchVO) throws Exception {
		return bbsMngMapper.getBbsNoticeList(searchVO);
	}

	@Override
	public List<BbsMngVO> getBbsList(BbsMngVO searchVO) throws Exception {
		return bbsMngMapper.getBbsList(searchVO);
	}
	
	@Override
	public List<BbsMngVO> getMainBbsList(BbsMngVO bbsVO) throws Exception {
		
		return bbsMngMapper.getMainBbsList(bbsVO);
	}

	@Override
	public int getBbsCnt(BbsMngVO searchVO) {
		return bbsMngMapper.getBbsCnt(searchVO);
	}

	@Override
	public BbsMngVO getBbsView(BbsMngVO searchVO) throws Exception {
		return StringUtil.isNotBlank(searchVO.getbIdx()) ? bbsMngMapper.getBbsView(searchVO) : null;
	}

	@Override
	public List<BbsMngVO> getBbsReplyList(BbsMngVO searchVO) throws Exception {
		return bbsMngMapper.getBbsReplyList(searchVO);
	}

	@Override
	public void setBbsViewCount(BbsMngVO searchVO) {
		bbsMngMapper.incrementViewCnt(searchVO); // 20160518 J.Ryeon Lee ADD 일자별 열람자 통계
		bbsMngMapper.updateBbsViewCnt(searchVO);
	}

	@Override
	public String insertBbs(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO,
		BbsConfigVO bbsConfigVO) {
		TransactionStatus status = transactionManager.getTransaction(TransDefinition.getRequiresNew());

		try {
			BbsMngVO insertVO = BeanUtil.copy(searchVO, new BbsMngVO());
			if (!multiRequest.getFileMap().isEmpty()) { // 첨부파일 등록
				insertVO.setAttachId( //
					fileUtil.storeFileAndGetAttachIdByPath( //
						multiRequest, //
						EgovProperties.getProperty(Constant.BBS_FILE_DIR_KEY) + insertVO.getPtIdx() + "/", //
						"BBS_", null, bbsConfigVO.typeIs(BbsType.MOVIE) //
					) //
				);
			}

			insertVO.setbIdx(bbsMngMapper.getBidx()); // next sequence
			insertVO.setbSame(insertVO.getbIdx());
			insertVO.setbPublic(BbsType.MINWON.equals(bbsConfigVO.getPtType()) ? "N" : insertVO.getbPublic()); // 민원형 게시판의 경우 비공개글로 등록
			insertVO.setbHostIp(SessionUtil.getRemoteAddr(multiRequest));
			insertVO.setDefaultValue(bbsConfigVO);
			insertVO.setSessionInfo(); // 인증 유저 정보 세팅
			bbsMngMapper.insertBbs(insertVO); // 게시글 등록

			insertLog(insertVO, ProcessType.INSERTED, insertVO.getbHostIp()); // 로그 등록
			fileMngService.updateFileMaster( //
				new FileMasterVO(insertVO.getAttachId(), authYn(insertVO, bbsConfigVO), "bbs_board", "b_idx",
					insertVO.getbIdx(), "b_privatecode") //
			); // 첨부파일 권한 정보 갱신

			transactionManager.commit(status);
			return insertVO.getbIdx();
		} catch (Exception e) {
			LOGGER.error(null, e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String getBoardPasswdDecode(BbsMngVO searchVO) throws Exception {
		return bbsMngMapper.getBoardPasswdDecode(searchVO);
	}

	@Override
	public String updateBbs(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO,
		BbsConfigVO bbsConfigVO) {
		TransactionStatus status = transactionManager.getTransaction(TransDefinition.getRequiresNew());

		try {
			BbsMngVO updateVO = BeanUtil.copy(searchVO, new BbsMngVO());
			if (!multiRequest.getFileMap().isEmpty()) { // 첨부파일 등록
				updateVO.setAttachId( //
					fileUtil.storeFileAndGetAttachIdByPath( //
						multiRequest, //
						EgovProperties.getProperty(Constant.BBS_FILE_DIR_KEY) + updateVO.getPtIdx() + "/", //
						"BBS_", updateVO.getAttachId(), bbsConfigVO.typeIs(BbsType.MOVIE) //
					) //
				);
			}

			updateVO.setbHostIp(SessionUtil.getRemoteAddr(multiRequest));
			updateVO.setbTitle(updateVO.getbTitle());
			updateVO.setbContent(updateVO.getbContent());
			updateVO.setDefaultValue(bbsConfigVO);
			updateVO.setbPublic(BbsType.MINWON.equals(bbsConfigVO.getPtType()) ? "N" : updateVO.getbPublic()); // 민원형 게시판의 경우 비공개글로 등록
			updateVO.setSessionInfo();
			bbsMngMapper.updateBbs(updateVO); // 게시글 수정
			insertLog(updateVO, ProcessType.UPDATED, updateVO.getbHostIp()); // 로그 기록
			fileMngService.updateFileMaster( //
				new FileMasterVO(updateVO.getAttachId(), authYn(updateVO, bbsConfigVO), "bbs_board", "b_idx",
					updateVO.getbIdx(), "b_privatecode") //
			); // 첨부파일 권한 정보 갱신

			transactionManager.commit(status);

			return updateVO.getbIdx();
		} catch (Exception e) {
			LOGGER.error(null, e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String deleteBbs(HttpServletRequest request, BbsMngVO searchVO) {
		try {
			searchVO.setbHostIp(SessionUtil.getRemoteAddr(request));
			searchVO.setSessionInfo();
			bbsMngMapper.deleteBbs(searchVO); // 게시글 삭제

			insertLog(bbsMngMapper.getBbsView(searchVO), ProcessType.DELETED, searchVO.getbHostIp()); // 로그 기록

			return searchVO.getbIdx();
		} catch (Exception e) {
			LOGGER.error(null, e);
			return null;
		}
	}

	@Override
	public String replyInsertBbs(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO,
		BbsConfigVO bbsConfigVO) {
		try {
			BbsMngVO insertVO = BeanUtil.copy(searchVO, new BbsMngVO());
			if (!multiRequest.getFileMap().isEmpty()) { // 첨부파일 검증 >> 등록
				insertVO.setAttachId( //
					fileUtil.storeFileAndGetAttachIdByPath( //
						multiRequest, //
						EgovProperties.getProperty(Constant.BBS_FILE_DIR_KEY) + insertVO.getPtIdx() + "/", //
						"BBS_", null, bbsConfigVO.typeIs(BbsType.MOVIE) //
					) //
				);
			}

			insertVO.setbIdx(bbsMngMapper.getBidx());
			insertVO.setSessionInfo(); // 인증된 사용자 정보
			int replySort = bbsMngMapper.getReplyMaxBodSame(insertVO);
			insertVO.setbSort(String.valueOf(replySort + 1));
			insertVO.setbLevel(String.valueOf(Integer.parseInt(insertVO.getbLevel()) + 1));
			insertVO.setbHostIp(SessionUtil.getRemoteAddr(multiRequest));
			insertVO.setDefaultValue(bbsConfigVO);
			insertVO.setbPublic("Y"); // 답글은 늘 공개
			bbsMngMapper.replyInsertBbs(insertVO); // 답글 등록

			insertLog(insertVO, ProcessType.ANSWERED, insertVO.getbHostIp()); // 로그 기록

			return insertVO.getbIdx();
		} catch (Exception e) {
			LOGGER.error(null, e);
			return null;
		}
	}

	/** 사이트 코드에 해당하는 부서코드 */
	@Override
	public String getDeptSiteCode(String siteCode) {
		return bbsMngMapper.getDeptSiteCode(siteCode);
	}

	@Override
	public List<DeptVO> subDeptList(String siteCode) {
		String deptCode = getDeptSiteCode(siteCode);
		if (StringUtil.isNotBlank(deptCode)) {
			DeptVO deptLevel = deptService.getDeptCodeLevel(deptCode);
			return deptService.getDeptCodeBbsQueryList(deptLevel.getDeptId());
		}

		return null; // 사이트 담당 부서가 없는 경우 대표 포털 공통 게시판과 동일하게 취급
	}

	@Override
	public List<DeptVO> getBbsDeptListCa(String ptCategoryYn, String ptCategoryGubun) {
		List<DeptVO> rtn = null;
		if ("Y".equals(ptCategoryYn) && CategoryGubunType.useDeptCategory(ptCategoryGubun)) {
			rtn = deptService.getDeptCategoryList();
		}
		return rtn;
	}

	private void insertLog(BbsMngVO searchVO, ProcessType bstate, String hostIp) {
		UserVO user = UserUtil.getInstance();

		BbsLog blVO = new BbsLog();
		blVO.setPtWriter(user.getUserName());
		blVO.setPtPtIdx(searchVO.getPtIdx());
		blVO.setPtBidx(searchVO.getbIdx());
		blVO.setPtTitle(searchVO.getbTitle());
		blVO.setPtHostIp(hostIp);
		blVO.setPtState(bstate.getCode());
		blVO.setPtWriterId(user.getUserId());
		bbsLogMngMapper.insertBbsLog(blVO);
	}

	@Override
	public int getReplyCnt(BbsMngVO searchVO) {
		return bbsMngMapper.getReplyCnt(searchVO);
	}

	@Override
	public void validate(BbsConfigVO bbsConfigVO, BbsMngVO target, Map<String, MultipartFile> fileMap, BindingResult result) {
		if (isNotValidLength(target.getbTitle(), Integer.parseInt(bbsConfigVO.getPtLimitTitLen()))) {
			result.reject("bTitle", "게시글 제목은 " + bbsConfigVO.getPtLimitTitLen() + "byte 이내여야 합니다.");
		}

		if (!fileMap.isEmpty()) {
			for (MultipartFile file : fileMap.values()) {
				if (isNotValidFileSize(file, Integer.parseInt(bbsConfigVO.getPtLimitFileSize()))) {
					result.reject("file", "첨부파일이 " + bbsConfigVO.getPtLimitFileSize() + "MB를 초과했습니다.");
				}
			}
		}
	}

	public boolean isValidLength(String target, int limit) {
		return StringUtil.isEmpty(target) || target.getBytes().length <= limit;
	}

	public boolean isNotValidLength(String target, int limit) {
		return !isValidLength(target, limit);
	}

	public boolean isValidFileSize(MultipartFile file, int limit) {
		LOGGER.error("file size is [" + file.getSize() + "]");
		return file.isEmpty() || file.getSize() <= limit * 1024 * 1024;
	}

	public boolean isNotValidFileSize(MultipartFile file, int limit) {
		return !isValidFileSize(file, limit);
	}

}
