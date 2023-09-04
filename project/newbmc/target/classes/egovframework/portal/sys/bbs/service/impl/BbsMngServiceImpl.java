package egovframework.portal.sys.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileMasterVO;
import egovframework.portal.bbs.BbsType;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.bbs.BoardState;
import egovframework.portal.sys.bbs.ProcessType;
import egovframework.portal.sys.bbs.mapper.BbsConfigMapper;
import egovframework.portal.sys.bbs.mapper.BbsLogMngMapper;
import egovframework.portal.sys.bbs.mapper.BbsMngMapper;
import egovframework.portal.sys.bbs.service.BbsMngService;
import egovframework.portal.sys.bbs.vo.BbsConfigVO;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.log.vo.BbsLog;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 - 게시글 관리 서비스 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.09.25		김혜민				최초 생성 및 코딩
 * 2017.05.17		J.Ryeon Lee			코드 리팩토링
 * </pre>
 *
 * @author 개발팀 김혜민
 * @since 2014.09.25
 * @version 1.0
 */
@Service("bbsMngService")
public class BbsMngServiceImpl extends EgovAbstractServiceImpl implements BbsMngService {

	@Resource(name = "bbsMngMapper")
	private BbsMngMapper bbsMngMapper;
	@Resource(name = "bbsConfigMapper")
	private BbsConfigMapper bbsConfigMapper;
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	@Autowired
	private BbsLogMngMapper bbsLogMngMapper;
	@Autowired
	protected DataSourceTransactionManager transactionManager;

	public final Logger LOGGER = LogManager.getLogger();

	/** @return 파일 권한 검증 여부 {@link BbsMngServiceImpl#authYn}과 동일 */
	private String authYn(BbsMngVO bbsMngVO, BbsConfigVO bbsConfigVO) {
		if ("N".equals(bbsMngVO.getbPublic()) || "Y".equals(bbsConfigVO.getPtReportYn()) || bbsConfigVO.getPtType().equals(BbsType.MINWON.getCode())) {
			return "Y"; // 비공개 게시글, 민원 게시글, 신고형 게시글
		}

		return "N";
	}

	@Override
	public List<BbsMngVO> getBbsMngNoticeList(BbsMngVO searchVO) throws Exception {
		BbsMngVO param = BeanUtil.copy(searchVO, new BbsMngVO());
		param.setbNotice("Y");

		return bbsMngMapper.getBbsMngNoticeList(param);
	}

	@Override
	public List<BbsMngVO> getBbsMngList(BbsMngVO searchVO) {
		return bbsMngMapper.getBbsMngList(searchVO);
	}

	@Override
	public int getBbsMngCnt(BbsMngVO searchVO) {
		return bbsMngMapper.getBbsMngCnt(searchVO);
	}

	@Override
	public String insertBbsMng(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception {
		AdminLoginVO admin = SessionUtil.getAdminInstance(multiRequest);
		BbsMngVO insertVO = BeanUtil.copy(searchVO, new BbsMngVO());

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			if (!multiRequest.getFileMap().isEmpty()) { // 첨부파일 등록
				insertVO.setAttachId( //
					fileUtil.storeFileAndGetAttachIdByPath( //
						multiRequest, //
						EgovProperties.getProperty(Constant.BBS_FILE_DIR_KEY) + insertVO.getPtIdx() + "/", //
						"BBS_", null, bbsConfigVO.typeIs(BbsType.MOVIE) //
					) //
				);
			}

			insertVO.setDefaultValue(bbsConfigVO);
			insertVO.setbIdx(bbsMngMapper.getBidx());
			insertVO.setbSame(insertVO.getbIdx());
			insertVO.setbHostIp(SessionUtil.getRemoteAddr(multiRequest));
			insertVO.setAdminInfo(admin);

			bbsMngMapper.insertBbsMng(insertVO); // 게시글 등록
			insertLog(insertVO, ProcessType.INSERTED, admin); // 로그 기록
			fileMngService.updateFileMaster( //
				new FileMasterVO(insertVO.getAttachId(), authYn(insertVO, bbsConfigVO), "bbs_board", "b_idx", insertVO.getbIdx(), "b_privatecode") //
			); // 첨부파일 권한 정보 갱신
			transactionManager.commit(status);

			return insertVO.getbIdx();
		} catch (Exception e) {
			LOGGER.error(">> failed insert board", e);
			transactionManager.rollback(status);
		}

		return null;
	}

	@Override
	public BbsMngVO getBbsMngView(BbsMngVO searchVO) throws Exception {
		return bbsMngMapper.getBbsMngView(searchVO);
	}

	@Override
	public List<BbsMngVO> getBbsMngReplyList(BbsMngVO searchVO) throws Exception {
		return bbsMngMapper.getBbsMngReplyList(searchVO);
	}

	@Override
	public String updateBbsMng(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception {
		AdminLoginVO admin = SessionUtil.getAdminInstance(multiRequest);
		BbsMngVO updateVO = BeanUtil.copy(searchVO, new BbsMngVO());

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			if (!multiRequest.getFileMap().isEmpty()) { // 첨부파일 등록
				updateVO.setAttachId( //
					fileUtil.storeFileAndGetAttachIdByPath( //
						multiRequest, //
						EgovProperties.getProperty(Constant.BBS_FILE_DIR_KEY) + updateVO.getPtIdx() + "/", //
						"BBS_", updateVO.getAttachId(), bbsConfigVO.typeIs(BbsType.MOVIE) //
					) //
				);
			}

			updateVO.setDefaultValue(bbsConfigVO);
			updateVO.setbState(updateVO.isMyArticle(admin) ? BoardState.MODIFIED_BY_OWNER.getCode() : BoardState.MODIFIED_BY_OTHER.getCode());
			updateVO.setbHostIp(SessionUtil.getRemoteAddr(multiRequest));

			bbsMngMapper.updateBbsMng(updateVO); // 게시글 수정
			insertLog(updateVO, ProcessType.UPDATED, admin); // 로그 기록
			fileMngService.updateFileMaster( //
				new FileMasterVO(updateVO.getAttachId(), authYn(updateVO, bbsConfigVO), "bbs_board", "b_idx", updateVO.getbIdx(), "b_privatecode") //
			); // 첨부파일 권한 정보 갱신
			transactionManager.commit(status);

			return updateVO.getbIdx();
		} catch (Exception e) {
			LOGGER.error(">> failed update board", e);
			transactionManager.rollback(status);
		}

		return null;
	}

	@Override
	public void deleteBbsMng(HttpServletRequest request, BbsMngVO originVO, BbsConfigVO bbsConfigVO) throws Exception {
		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
		BbsMngVO deleteVO = BeanUtil.copy(originVO, new BbsMngVO());
		deleteVO.setbState(deleteVO.isMyArticle(admin) ? BoardState.DELETED_BY_OWNER.getCode() : BoardState.DELETED_BY_OTHER.getCode());
		deleteVO.setbWrite(admin.getAdminName());
		deleteVO.setWriteId(admin.getAdminId());
		deleteVO.setbHostIp(SessionUtil.getRemoteAddr(request));

		bbsMngMapper.deleteBbsMng(deleteVO); // 게시글 삭제
		insertLog(deleteVO, ProcessType.DELETED, SessionUtil.getAdminInstance(request)); // 로그 기록
	}

	/** 답글/답변 등록 */
	@Override
	public String replyInsertBbsMng(final MultipartHttpServletRequest multiRequest, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception {
		try {
			AdminLoginVO admin = SessionUtil.getAdminInstance(multiRequest);
			BbsMngVO insertVO = BeanUtil.copy(searchVO, new BbsMngVO());
			insertVO.setbIdx(bbsMngMapper.getBidx()); // new sequence
			insertVO.setDefaultValue(bbsConfigVO);

			int replySort = bbsMngMapper.getReplyMaxBodSame(insertVO);
			insertVO.setbSort(String.valueOf(replySort + 1));
			insertVO.setbLevel(String.valueOf(Integer.parseInt(insertVO.getbLevel()) + 1));
			insertVO.setbHostIp(SessionUtil.getRemoteAddr(multiRequest));
			insertVO.setbPublic("Y"); // 답글은 반드시 공개 등록
			insertVO.setbNotice("N"); // 답글은 공지로 등록 불가
			insertVO.setAdminInfo(admin);

			if (!multiRequest.getFileMap().isEmpty()) { // 첨부파일 검증 >> 등록
				insertVO.setAttachId( //
					fileUtil.storeFileAndGetAttachIdByPath( //
						multiRequest, //
						EgovProperties.getProperty(Constant.BBS_FILE_DIR_KEY) + insertVO.getPtIdx() + "/", //
						"BBS_", null, bbsConfigVO.typeIs(BbsType.MOVIE) //
					) //
				);
			}

			bbsMngMapper.replyInsertBbsMng(insertVO); // 답글 등록
			insertLog(insertVO, ProcessType.ANSWERED, admin); // 로그 기록
			fileMngService.updateFileMaster( //
				new FileMasterVO( //
					insertVO.getAttachId(), authYn(insertVO, bbsConfigVO), "bbs_board", "b_idx", //
					bbsConfigVO.getPtType().equals(BbsType.MINWON.getCode()) ? insertVO.getbSame() : insertVO.getbIdx(), //
					"b_privatecode" //
				) //
			); // 첨부파일 권한 정보 갱신

			return insertVO.getbIdx();
		} catch (Exception e) {
			LOGGER.error("", e);
		}

		return null;
	}

	@Override
	public void setBbsMngViewCount(BbsMngVO searchVO) {
		bbsMngMapper.incrementViewCnt(searchVO); // 20160518 J.Ryeon Lee ADD 일자별 열람자 통계
		bbsMngMapper.updateBbsViewCnt(searchVO);
	}

	@Override
	public int getReplyCnt(BbsMngVO searchVO) {
		return bbsMngMapper.getReplyCnt(searchVO);
	}

	@Override
	public void moveBoardInsert(BbsMngVO moveVO, AdminLoginVO admin, String toPtIdx) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("bIdx", moveVO.getbIdx());
		params.put("toPtIdx", toPtIdx);
		bbsMngMapper.moveBbs(params); // MOVE BOARD

		insertLog(moveVO, ProcessType.MOVED, admin); // 로그 기록
	}

	@Override
	public void reDeleteBbsMng(HttpServletRequest request, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception {
		searchVO.setbWrite(SessionUtil.getAdminName(request));
		searchVO.setbHostIp(SessionUtil.getRemoteAddr(request));

		BbsMngVO logView = bbsMngMapper.getBbsMngView(searchVO);

		bbsMngMapper.reDeleteBbsMng(searchVO);
		insertLog(logView, ProcessType.RESTORED, SessionUtil.getAdminInstance(request)); // 로그 기록
	}

	@Override
	public void bundleDeleteBbsMng(HttpServletRequest request, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception {
		searchVO.setbWrite(SessionUtil.getAdminName(request));
		searchVO.setbHostIp(SessionUtil.getRemoteAddr(request));

		List<String> delCheckedList = searchVO.getDelChecked();
		for (int i = 0; i < delCheckedList.size(); i++) {
//			searchVO.setbIdx(delCheckedList.get(i).toString());
//			BbsMngVO logView = bbsMngMapper.getBbsMngView(searchVO);
//			bbsMngMapper.deleteBbsMng(searchVO); // 게시글 삭제
//			insertLog(logView, ProcessType.DELETED, SessionUtil.getAdminInstance(request)); // 로그 기록

			deleteBbsMng(request, bbsMngMapper.getBbsMngView(new BbsMngVO(delCheckedList.get(i))), bbsConfigVO); // 20170614 J.Ryeon Lee 삭제 메소드 호출하도록 수정
		}
	}

	@Override
	public void bundleRedeleteBbsMng(HttpServletRequest request, BbsMngVO searchVO, BbsConfigVO bbsConfigVO) throws Exception {
		searchVO.setbWrite(SessionUtil.getAdminName(request));
		searchVO.setbHostIp(SessionUtil.getRemoteAddr(request));

		List<String> delCheckedList = searchVO.getDelChecked();
		for (int i = 0; i < delCheckedList.size(); i++) {
//			searchVO.setbIdx(delCheckedList.get(i).toString());
//			BbsMngVO logView = bbsMngMapper.getBbsMngView(searchVO);
//			bbsMngMapper.reDeleteBbsMng(searchVO); // 게시글 삭제
//			insertLog(logView, ProcessType.RESTORED, SessionUtil.getAdminInstance(request)); // 로그 기록

			reDeleteBbsMng(request, bbsMngMapper.getBbsMngView(new BbsMngVO(delCheckedList.get(i))), bbsConfigVO); // 20170614 J.Ryeon Lee 삭제 메소드 호출하도록 수정
		}
	}

	private void insertLog(BbsMngVO searchVO, ProcessType bstate, AdminLoginVO admin) {
		BbsLog blVO = new BbsLog();
		blVO.setPtWriter(admin.getAdminName());
		blVO.setPtPtIdx(searchVO.getPtIdx());
		blVO.setPtBidx(searchVO.getbIdx());
		blVO.setPtTitle(searchVO.getbTitle());
		blVO.setPtHostIp(admin.getHostIp());
		blVO.setPtState(bstate.getCode());
		blVO.setPtWriterId(StringUtil.isNotBlank(admin.getAdminId()) ? admin.getAdminId() : admin.getId());
		bbsLogMngMapper.insertBbsLog(blVO);
	}

	@Override
	public List<BbsMngVO> getMainMgt(BbsMngVO searchVO) {
		
		return bbsMngMapper.getMainMgt(searchVO);
	}

	@Override
	public String uncheck(BbsMngVO searchVO) {
		try {
			BbsMngVO uncheckVO = BeanUtil.copy(searchVO, new BbsMngVO());
			bbsMngMapper.uncheck(uncheckVO);

			return String.valueOf(uncheckVO.getbIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public void orderUpdate(BbsMngVO searchVO) {
		bbsMngMapper.orderUpdate(searchVO);
		
	}

}
