package egovframework.portal.unit.bmc.report.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.unit.bmc.report.mapper.ReportMapper;
import egovframework.portal.unit.bmc.report.service.ReportService;
import egovframework.portal.unit.bmc.report.vo.ReportVO;
import egovframework.portal.util.BeanUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class ReportServiceImpl extends EgovAbstractServiceImpl implements ReportService {
	
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private ReportMapper reportMapper;

	@Override
	public List<ReportVO> getList(ReportVO reportVO) {
		List<ReportVO> list = reportMapper.getList(reportVO);
		return list;
	}

	@Override
	public int getTotalCnt(ReportVO reportVO) {
		return reportMapper.getTotalCnt(reportVO);
	}
	
	@Override
	public String insert(ReportVO reportVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			ReportVO insertVO = BeanUtil.copy(reportVO, new ReportVO());
			insertVO.setAttachId( 
				fileUtil.storeFileAndGetAttachId( 
					request
					, EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + "/"
					, ""
					, null 
				) 
			);
			
			reportMapper.insert(insertVO);
			transactionManager.commit(status);

			return String.valueOf(insertVO.getIdx());
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}
	
	@Override
	public int getReporterCnt(ReportVO reportVO) {
		return reportMapper.getReporterCnt(reportVO);
	}
	
	@Override
	public int getCheck(ReportVO reportVO) {
		
		return reportMapper.getCheck(reportVO);
	}
	
	@Override
	public ReportVO getContent(ReportVO reportVO) {
		return reportMapper.getContent(reportVO);
	}
	
	@Override
	public String getFileNm(ReportVO reportVO) {
		return reportMapper.getFileNm(reportVO);
	}

}
