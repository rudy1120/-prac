package egovframework.portal.sys.basic.promotion.service.impl;

import java.io.File;
import java.util.ArrayList;
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

import edu.emory.mathcs.backport.java.util.Arrays;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.basic.promotion.PromotionType;
import egovframework.portal.sys.basic.promotion.mapper.PromotionMngMapper;
import egovframework.portal.sys.basic.promotion.mapper.PromotionSiteMngMapper;
import egovframework.portal.sys.basic.promotion.service.PromotionMngService;
import egovframework.portal.sys.basic.promotion.vo.PromotionSiteVO;
import egovframework.portal.sys.basic.promotion.vo.PromotionVO;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.FileUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.excel.ExcelUploadHelperUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jxl.Workbook;

@Service
public class PromotionMngServiceImpl extends EgovAbstractServiceImpl implements PromotionMngService {

	@Autowired
	protected PromotionMngMapper mapper;
	@Autowired
	protected PromotionSiteMngMapper siteMapper;
	@Autowired
	protected FileUploadHelperService fileUploadHelperService;
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	@Autowired
	protected DataSourceTransactionManager transactionManager;

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public int getTotalCnt(PromotionVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@Override
	public List<PromotionVO> getList(PromotionVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public PromotionVO getEntity(PromotionVO searchVO) {
		PromotionVO rtn = StringUtil.isNotBlank(searchVO.getIdx()) ? mapper.getEntity(searchVO) : null;
		if (rtn != null) {
			List<String> siteIdxs = new ArrayList<>();
			for (PromotionSiteVO siteVO : rtn.getPromotionSites()) {
				siteIdxs.add(siteVO.getSiteIdx());
			}
			rtn.setSiteIdxs(siteIdxs);
		}

		return rtn;
	}

	@Override
	public String insert(PromotionVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			PromotionVO insertVO = BeanUtil.copy(searchVO, new PromotionVO());
			insertVO.setAttachId( //
				fileUtil.storeFileAndGetAttachId( //
					request, //
					EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + insertVO.getType().getPath().toUpperCase() + "/", //
					"", null //
				) //
			);
			insertVO.setDefaultValues();

			//정렬순서 부분
			mapper.updateOtherOrderOneDown(insertVO); // 기존의 entity 들의 정렬값을 한단계 내린다

			mapper.insert(insertVO); // 홍보 자료 등록
			insertSiteInfo(insertVO); // 사이트 정보 등록
			transactionManager.commit(status);

			return insertVO.getIdx();
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String update(PromotionVO searchVO, MultipartHttpServletRequest request) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			PromotionVO updateVO = BeanUtil.copy(searchVO, new PromotionVO());
			updateVO.setAttachId( //
				fileUtil.storeFileAndGetAttachId( //
					request, //
					EgovProperties.getProperty(Constant.PROMOTION_FILE_SAVE_DIR_KEY) + updateVO.getType().getPath().toUpperCase() + "/", //
					"", updateVO.getAttachId() //
				) //
			);
			updateVO.setDefaultValues();

			//정렬순서 부분
			if(!updateVO.getPrmtOldOrder().equals(updateVO.getPrmtOrder())) {
				if(Integer.parseInt(updateVO.getPrmtOldOrder()) > Integer.parseInt(updateVO.getPrmtOrder())) {
					mapper.updateOtherOrderOneDown(updateVO); // 기존의 entity 들의 정렬값을 한단계 내린다
				} else {
					mapper.updateOtherOrderOneUp(updateVO); // 기존의 entity 들의 정렬값을 한단계 올린다
				}
			}

			mapper.update(updateVO); // 홍보 자료 등록
			insertSiteInfo(updateVO); // 사이트 정보 등록
			transactionManager.commit(status);

			return updateVO.getIdx();
		} catch (Exception e) {
			LOGGER.error("", e);
			transactionManager.rollback(status);
			return null;
		}
	}

	@Override
	public String delete(PromotionVO searchVO) {
		try {
			PromotionVO deleteVO = BeanUtil.copy(searchVO, new PromotionVO());
			mapper.delete(deleteVO); // 홍보 자료 삭제

			return deleteVO.getIdx();
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public String changeOrder(PromotionVO searchVO) {
		try {
			PromotionVO changeVO = BeanUtil.copy(searchVO, new PromotionVO());

			//정렬순서 부분
			if(!searchVO.getPrmtOldOrder().equals(searchVO.getPrmtOrder())) {
				if(Integer.parseInt(searchVO.getPrmtOldOrder()) > Integer.parseInt(searchVO.getPrmtOrder())) {
					mapper.updateOtherOrderOneDown(searchVO); // 기존의 entity 들의 정렬값을 한단계 내린다
				} else {
					mapper.updateOtherOrderOneUp(searchVO); // 기존의 entity 들의 정렬값을 한단계 올린다
				}
				mapper.changeOrder(changeVO); //정렬 변경
			}

			return changeVO.getIdx();
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public int uploadBannerExcel(MultipartFile file, Boolean overwrite) throws Exception {
		String uploadDir = EgovProperties.getProperty(Constant.EXCEL_UPLOAD);
		String uploadedFileName = fileUploadHelperService.uploadFile(uploadDir, file);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			if (overwrite) { // 덮어쓰기
				mapper.deleteAll(PromotionType.BANNER.getCode());
			}

			Workbook workbook = Workbook.getWorkbook(new File(uploadDir + uploadedFileName));
			List<Map<String, String>> recordList = ExcelUploadHelperUtil.dataList(workbook.getSheet(0));
			PromotionVO insertVO = null;
			for (Map<String, String> record : recordList) {
				insertVO = new PromotionVO();
				insertVO.setType(PromotionType.BANNER);
				insertVO.setSiteIdxs(Arrays.asList(record.get("e1").split(",")));
				insertVO.setPrmtName(record.get("e2"));
				insertVO.setPrmtUrl(record.get("e3"));
				insertVO.setPrmtSday(record.get("e4"));
				insertVO.setPrmtEday(record.get("e5"));
				insertVO.setUseYn(record.get("e6"));
				insertVO.setDefaultValues();

				mapper.insert(insertVO); // 홍보 자료 등록
				insertSiteInfo(insertVO); // 사이트 정보 등록
			}

			transactionManager.commit(status);
			return recordList.size();
		} catch (Exception e) {
			LOGGER.error(">> failed to upload excel", e);
			transactionManager.rollback(status);
		} finally {
			FileUtil.removeFullFile(uploadDir, uploadedFileName, "/"); // 처리를 위해 등록한 임시 엑셀 파일을 삭제
		}

		return -1;
	}

	private void insertSiteInfo(PromotionVO promotion) {
		siteMapper.deleteAll(promotion.getIdx());
		for (String siteIdx : promotion.getSiteIdxs()) { // 홍보자료 취급 사이트 등록
			siteMapper.insert(new PromotionSiteVO(promotion.getIdx(), siteIdx));
		}
	}

	@Override
	public List<PromotionVO> getPrmtOrderList(PromotionVO searchVO) {
		return mapper.getPrmtOrderList(searchVO);
	}

}
