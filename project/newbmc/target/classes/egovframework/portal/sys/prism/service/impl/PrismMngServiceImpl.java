package egovframework.portal.sys.prism.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.common.TransDefinition;
import egovframework.portal.sys.prism.mapper.PrismMngMapper;
import egovframework.portal.sys.prism.service.PrismMngService;
import egovframework.portal.sys.prism.vo.PrismMngVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.SessionUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 관리자 - 정책연구보고서 게시판 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 *	수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2019.10.22		박선민				최초 생성
 * </pre>
 *
 * @author 박선민
 * @since 2019.10.22
 */
@Service("prismMngService")
public class PrismMngServiceImpl implements PrismMngService {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Resource(name = "prismMngMapper")
	private PrismMngMapper prismMngMapper;
	
	@Resource(name = "txManager")
	private PlatformTransactionManager transactionManager;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;

	@Override
	public int getTotalCnt(PrismMngVO searchVO) throws Exception {
		return prismMngMapper.getTotalCnt(searchVO);
	}
	
	/*정책연구보고서 목록 조회*/
	@Override
	public List<PrismMngVO> getList(PrismMngVO searchVO) throws Exception {
		return prismMngMapper.getList(searchVO);
	}

	/*정책연구보고서 상세 조회*/
	@Override
	public PrismMngVO getEntity(PrismMngVO searchVO) throws Exception {

		return prismMngMapper.getEntity(searchVO);
	}

	/*관리자 정책연구보고서 등록*/
	@Override
	public boolean insert(MultipartHttpServletRequest request, PrismMngVO prismVO) throws Exception {
		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
		
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		boolean result = false;

		try {
			PrismMngVO vo = BeanUtil.copy(prismVO, new PrismMngVO());

			vo.setHostIp(SessionUtil.getRemoteAddr(request));
			vo.setWriteId(admin.getAdminId());
			vo.setbWrite(admin.getAdminName());
			vo.setDepNm(admin.getDeptName());

			//결과보고서 파일 첨부등록
			//resReport
			vo.setResReport((uploadFile(request, "resReportFile")));

			//평가결과서
			vo.setEvalResult((uploadFile(request, "evalResultFile")));
			
			//활용결과 보고서
			vo.setApplReport((uploadFile(request, "applReportFile")));
			
			
			prismMngMapper.insert(vo);
			transactionManager.commit(txStatus);
			
			result = Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			//return Boolean.FALSE;
			result = Boolean.FALSE;
		}
		
		return result;
	}
	
	/*관리자 정책연구보고서 수정 처리*/
	@Override
	public boolean modify(MultipartHttpServletRequest request, PrismMngVO prismVO) throws Exception {
		
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		boolean result = false;
		try {
			PrismMngVO vo = BeanUtil.copy(prismVO, new PrismMngVO());

			//결과보고서 파일 첨부등록
			String resReportFile = (uploadFile(request, "resReportFile"));
			vo.setResReport(("".equals(resReportFile) ? vo.getResReport() : resReportFile));


			//평가결과서
			String evalResultFile = (uploadFile(request, "evalResultFile"));
			vo.setEvalResult(("".equals(evalResultFile) ? vo.getEvalResult() : evalResultFile));
			
			//활용결과 보고서
			String applReportFile = (uploadFile(request, "applReportFile"));
			vo.setApplReport(("".equals(applReportFile) ? vo.getApplReport() : applReportFile));
			
			
			prismMngMapper.modify(vo);
			transactionManager.commit(txStatus);
			
			result = Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			//return Boolean.FALSE;
			result = Boolean.FALSE;
		}
		
		return result;
	}
	//파일업로드 처리
	private String uploadFile(MultipartHttpServletRequest request, String type) throws Exception {
		if (request != null) {
			Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
			String fileCn = "";

			if ("resReportFile".equals(type) && request.getFileMap().get("resReport_file") != null) {
				
				files.put("resReportFile", (request.getFileMap().get("resReport_file") != null && request.getFileMap().get("resReport_file").getSize() > 0 ? request.getFileMap().get("resReport_file") : null));
				fileCn = (request.getParameter("resReportFile") != null ? request.getParameter("resReportFile") : "");
				
			} else if ("evalResultFile".equals(type) && request.getFileMap().get("evalResult_file") != null) {
				
				files.put("evalResultFile", (request.getFileMap().get("evalResult_file") != null && request.getFileMap().get("evalResult_file").getSize() > 0 ? request.getFileMap().get("evalResult_file") : null));
				fileCn = (request.getParameter("evalResultFile") != null ? request.getParameter("evalResultFile") : "");
				
			} else if ("applReportFile".equals(type) && request.getFileMap().get("applReport_file") != null) {
				
				files.put("applReportFile", (request.getFileMap().get("applReport_file") != null && request.getFileMap().get("applReport_file").getSize() > 0 ? request.getFileMap().get("applReport_file") : null));
				fileCn = (request.getParameter("applReportFile") != null ? request.getParameter("applReportFile") : "");
				
			} else {
				return "";
			}
			if (!files.isEmpty() && (files.get("resReportFile") != null || files.get("evalResultFile") != null || files.get("applReportFile") != null)) {
				List<FileVO> _result = null;
				try {
					_result = fileUtil.parseFileInf(files, "PRISM_", 0, "", "/home/webdata/egov_uploadFile/PRISM/", false, request);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (_result != null) {
					_result.get(0).setFileCn(fileCn);
					try {
						//파일여러개 등록
						return fileMngService.insertFileInfs(_result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
				}
			}
		}
		return "";
	}

	/*관리자 정책연구보고서 삭제*/
	@Override
	public boolean delete(HttpServletRequest request, PrismMngVO prismVO) throws Exception {

		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		boolean result = false;
		
		try {
			SaleVO deleteVO = BeanUtil.copy(prismVO, new SaleVO());
			prismMngMapper.delete(prismVO);
			
			transactionManager.commit(txStatus);
			result = Boolean.TRUE;
			System.out.println("result1" + result);

		}catch(NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			result =  Boolean.FALSE;
			System.out.println("result2" + result);

		}

		return result;
	}

	//조회수 업데이트
	@Override
	public void updateCnt(PrismMngVO prismVO) throws Exception {
		//조회수 업데이트
		prismMngMapper.updateCnt(prismVO);
	}



}
