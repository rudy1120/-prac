package egovframework.portal.unit.bmc.receipt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.unit.bmc.receipt.mapper.ReceiptMapper;
import egovframework.portal.unit.bmc.receipt.service.ReceiptService;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptContVO;
import egovframework.portal.unit.bmc.receipt.vo.ReceiptVO;
import egovframework.portal.util.BeanUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class ReceiptServiceImpl extends EgovAbstractServiceImpl implements ReceiptService {
	
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Autowired
	protected DataSourceTransactionManager transactionManager;
	
	private final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private ReceiptMapper receiptMapper;

	@Override
	public List<ReceiptContVO> getList(ReceiptContVO receiptContVO) {
		List<ReceiptContVO> list = receiptMapper.getList(receiptContVO);
		return list;
	}

	@Override
	public int getTotalCnt(ReceiptContVO receiptContVO) {
		return receiptMapper.getTotalCnt(receiptContVO);
	}

	@Override
	public ReceiptContVO getContent(ReceiptContVO receiptContVO) {
		return receiptMapper.getContent(receiptContVO);
	}

	@Override
	public void setViewCount(ReceiptContVO receiptContVO) {
		receiptMapper.setViewCount(receiptContVO);
	}
	
	@Override
	public ReceiptVO getInfoB(ReceiptVO receiptVO) {
		return receiptMapper.getInfoB(receiptVO);
	}
	
	@Override
	public int termChkIn(ReceiptVO receiptVO) {
		return receiptMapper.termChkIn(receiptVO);
	}
	
	@Override
	public boolean termUp(MultipartHttpServletRequest request, ReceiptVO receiptVO) {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean result = false;
		
		try {
			ReceiptVO insertVO = BeanUtil.copy(receiptVO, new ReceiptVO());

			insertVO.setAttachId((uploadFile(request, "attachIdFile"))); //첨부파일(입사지원서)
			
			receiptMapper.termUp(insertVO);
			
			transactionManager.commit(status);
			
			result = Boolean.TRUE;
		} catch (Exception e) {
			transactionManager.rollback(status);
			LOGGER.error(e);
			
			result = Boolean.FALSE;
		}
		
		return result;
	}

	@Override
	public boolean stInsert(MultipartHttpServletRequest request, ReceiptVO receiptVO) throws Exception {
		
//		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
//		
//		TransactionStatus status = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean result = false;

		try {
			ReceiptVO insertVO = BeanUtil.copy(receiptVO, new ReceiptVO());

			insertVO.setResolution((uploadFile(request, "resolutionFile"))); //추천심사위원회 의결서
			insertVO.setRecStatus((uploadFile(request, "recStatusFile"))); //추천현황표
			
			receiptMapper.stInsert(insertVO); 
			
			int cnt = insertVO.getCntN();
			
			for(int i=1;i<=cnt;i++) {
				
				if(i == 1) {
					insertVO.setAttach1((uploadFile(request, "attach" + i + "File"))); // 기능인재추천서1
					insertVO.setAttach2((uploadFile(request, "attach" + (i+1) + "File"))); // 개인정보수집 및 위탁동의서1
					insertVO.setAttach3((uploadFile(request, "attach" + (i+2) + "File"))); // 성적증명서1
					insertVO.setAttach4((uploadFile(request, "attach" + (i+3) + "File"))); //졸업(예정)증명서
					receiptMapper.stsInsert(insertVO);
				} else if (i == 2){	
					insertVO.setAttach1((uploadFile(request, "attach" + (i+3) +"File")));
					insertVO.setAttach2((uploadFile(request, "attach" + (i+4) + "File")));
					insertVO.setAttach3((uploadFile(request, "attach" + (i+5) + "File")));
					insertVO.setAttach4((uploadFile(request, "attach" + (i+6) + "File")));
					insertVO.setSname(insertVO.getSname1());
					receiptMapper.stsInsert(insertVO);
				}else if (i == 3){	
					insertVO.setAttach1((uploadFile(request, "attach" + (i+6) +"File")));
					insertVO.setAttach2((uploadFile(request, "attach" + (i+7) + "File")));
					insertVO.setAttach3((uploadFile(request, "attach" + (i+8) + "File")));
					insertVO.setAttach4((uploadFile(request, "attach" + (i+9) + "File")));
					insertVO.setSname(insertVO.getSname2());
					receiptMapper.stsInsert(insertVO);
				}

			}
			
			transactionManager.commit(status);
			
			result = Boolean.TRUE;
		} catch (Exception e) {
			transactionManager.rollback(status);
			LOGGER.error(e);
			//return Boolean.FALSE;
			result = Boolean.FALSE;
		}
		
		return result;
	}
	
	@Override
	public boolean termInsert(MultipartHttpServletRequest request, ReceiptVO receiptVO) throws Exception {
		
//		AdminLoginVO admin = SessionUtil.getAdminInstance(request);
//		
//		TransactionStatus status = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean result = false;

		try {
			ReceiptVO insertVO = BeanUtil.copy(receiptVO, new ReceiptVO());

			insertVO.setAttachId((uploadFile(request, "attachIdFile"))); //첨부파일(입사지원서)
			
			receiptMapper.termInsert(insertVO); 
			transactionManager.commit(status);
			
			result = Boolean.TRUE;
		} catch (Exception e) {
			transactionManager.rollback(status);
			LOGGER.error(e);
			//return Boolean.FALSE;
			result = Boolean.FALSE;
		}
		
		return result;
	}

	@Override
	public List<ReceiptVO> read(ReceiptVO receiptVO) {
		return receiptMapper.read(receiptVO);
	}

	@Override
	public int getReceptionistCnt(ReceiptVO receiptVO) {
		return receiptMapper.getReceptionistCnt(receiptVO);
	}

	@Override
	public String deleteT(ReceiptVO receiptVO) {
		try {
			ReceiptVO deleteVO = BeanUtil.copy(receiptVO, new ReceiptVO());
			receiptMapper.deleteT(deleteVO);
			return String.valueOf(deleteVO.getSttidx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public int getCheck(ReceiptVO receiptVO) {
		
		return receiptMapper.getCheck(receiptVO);
	}
	
	//파일업로드 처리
	private String uploadFile(MultipartHttpServletRequest request, String type) throws Exception {
		if (request != null) {

			Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
			String fileCn = "";

			if ("resolutionFile".equals(type) && request.getFileMap().get("resolution_file") != null) {
				
				files.put("resolutionFile", (request.getFileMap().get("resolution_file") != null ? request.getFileMap().get("resolution_file") : null));
				fileCn = (request.getParameter("resolutionFile") != null ? request.getParameter("resolutionFile") : "");
				
			} else if ("recStatusFile".equals(type) && request.getFileMap().get("recStatus_file") != null) {
				
				files.put("recStatusFile", (request.getFileMap().get("recStatus_file") != null ? request.getFileMap().get("recStatus_file") : null));
				fileCn = (request.getParameter("recStatusFile") != null ? request.getParameter("recStatusFile") : "");
				
			} else if ("attach1File".equals(type) && request.getFileMap().get("attach1_file") != null) {
				
				files.put("attach1File", (request.getFileMap().get("attach1_file") != null ? request.getFileMap().get("attach1_file") : null));
				fileCn = (request.getParameter("attach1File") != null ? request.getParameter("attach1File") : "");
				
			} else if ("attach2File".equals(type) && request.getFileMap().get("attach2_file") != null) {
				
				files.put("attach2File", (request.getFileMap().get("attach2_file") != null ? request.getFileMap().get("attach2_file") : null));
				fileCn = (request.getParameter("attach2File") != null ? request.getParameter("attach2File") : "");
				
			} else if ("attach3File".equals(type) && request.getFileMap().get("attach3_file") != null) {
				
				files.put("attach3File", (request.getFileMap().get("attach3_file") != null ? request.getFileMap().get("attach3_file") : null));
				fileCn = (request.getParameter("attach3File") != null ? request.getParameter("attach3File") : "");
				
			} else if ("attach4File".equals(type) && request.getFileMap().get("attach4_file") != null) {
				
				files.put("attach4File", (request.getFileMap().get("attach4_file") != null ? request.getFileMap().get("attach4_file") : null));
				fileCn = (request.getParameter("attach4File") != null ? request.getParameter("attach4File") : "");
				
			} else if ("attach5File".equals(type) && request.getFileMap().get("attach5_file") != null) {
				
				files.put("attach5File", (request.getFileMap().get("attach5_file") != null ? request.getFileMap().get("attach5_file") : null));
				fileCn = (request.getParameter("attach5File") != null ? request.getParameter("attach5File") : "");
				
			} else if ("attach6File".equals(type) && request.getFileMap().get("attach6_file") != null) {
				
				files.put("attach6File", (request.getFileMap().get("attach6_file") != null ? request.getFileMap().get("attach6_file") : null));
				fileCn = (request.getParameter("attach6File") != null ? request.getParameter("attach6File") : "");
				
			} else if ("attach7File".equals(type) && request.getFileMap().get("attach7_file") != null) {
				
				files.put("attach7File", (request.getFileMap().get("attach7_file") != null ? request.getFileMap().get("attach7_file") : null));
				fileCn = (request.getParameter("attach7File") != null ? request.getParameter("attach7File") : "");
				
			} else if ("attach8File".equals(type) && request.getFileMap().get("attach8_file") != null) {
				
				files.put("attach8File", (request.getFileMap().get("attach8_file") != null ? request.getFileMap().get("attach8_file") : null));
				fileCn = (request.getParameter("attach8File") != null ? request.getParameter("attach8File") : "");
				
			} else if ("attach9File".equals(type) && request.getFileMap().get("attach9_file") != null) {
				
				files.put("attach9File", (request.getFileMap().get("attach9_file") != null ? request.getFileMap().get("attach9_file") : null));
				fileCn = (request.getParameter("attach9File") != null ? request.getParameter("attach9File") : "");
				
			} else if ("attach10File".equals(type) && request.getFileMap().get("attach10_file") != null) {
				
				files.put("attach10File", (request.getFileMap().get("attach10_file") != null ? request.getFileMap().get("attach10_file") : null));
				fileCn = (request.getParameter("attach10File") != null ? request.getParameter("attach10File") : "");
				
			} else if ("attach11File".equals(type) && request.getFileMap().get("attach11_file") != null) {
				
				files.put("attach11File", (request.getFileMap().get("attach11_file") != null ? request.getFileMap().get("attach11_file") : null));
				fileCn = (request.getParameter("attach11File") != null ? request.getParameter("attach11File") : "");
				
			} else if ("attach12File".equals(type) && request.getFileMap().get("attach12_file") != null) {
				
				files.put("attach12File", (request.getFileMap().get("attach12_file") != null ? request.getFileMap().get("attach12_file") : null));
				fileCn = (request.getParameter("attach12File") != null ? request.getParameter("attach12File") : "");
				
			} else if ("attachIdFile".equals(type) && request.getFileMap().get("attachId_file") != null) {
				
				files.put("attachIdFile", (request.getFileMap().get("attachId_file") != null ? request.getFileMap().get("attachId_file") : null));
				fileCn = (request.getParameter("attachIdFile") != null ? request.getParameter("attachIdFile") : "");
				
			} else {
				return "";
			}
			if (!files.isEmpty() && (files.get("resolutionFile") != null || files.get("recStatusFile") != null 
					|| files.get("attach1File") != null || files.get("attach2File") != null || files.get("attach3File") != null || files.get("attach4File") != null
					|| files.get("attach5File") != null || files.get("attach6File") != null || files.get("attach7File") != null || files.get("attach8File") != null
					|| files.get("attach9File") != null || files.get("attach10File") != null || files.get("attach11File") != null || files.get("attach12File") != null
					|| files.get("attachIdFile") != null )) {
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

	@Override
	public ReceiptVO getGbn(ReceiptVO receiptVO) {
		
		return receiptMapper.getGbn(receiptVO);
	}
	
	@Override
	public List<ReceiptVO> getReceptionistList(ReceiptVO receiptVO) {
		return receiptMapper.getReceptionistList(receiptVO);
	}

	@Override
	public ReceiptVO getInfo(ReceiptVO receiptVO) {
		return receiptMapper.getInfo(receiptVO);
	}
	
	@Override
	public int chkSkillS(ReceiptVO receiptVO) {
		return receiptMapper.chkSkillS(receiptVO);
	}
	
	@Override
	public String deleteS(ReceiptVO receiptVO) {
		try {
			ReceiptVO deleteVO = BeanUtil.copy(receiptVO, new ReceiptVO());
			receiptMapper.deleteS(deleteVO);
			return String.valueOf(deleteVO.getSttidx());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}
	
	@Override
	public ReceiptVO getEndDate(ReceiptVO receiptVO) {
		return receiptMapper.getEndDate(receiptVO);
	}

}
