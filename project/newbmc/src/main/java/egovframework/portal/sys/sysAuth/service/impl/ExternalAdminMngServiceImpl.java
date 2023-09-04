package egovframework.portal.sys.sysAuth.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

import edu.emory.mathcs.backport.java.util.Arrays;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.mapper.ExternalAdminMngMapper;
import egovframework.portal.sys.sysAuth.service.ExternalAdminMngService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.util.FileUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.excel.ExcelUploadHelperUtil;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import jxl.Workbook;

/**
 * 외부 관리자 관리 SERVICE IMPL
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 9. 22.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 9. 22.
 */
@Service
public class ExternalAdminMngServiceImpl extends EgovAbstractMapper implements ExternalAdminMngService {

	@Resource
	protected ExternalAdminMngMapper mapper;
	@Resource
	protected FileUploadHelperService fileUploadHelperService;
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
	@Resource
	protected DataSourceTransactionManager transactionManager;

	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<AdminLoginVO> getList(AdminLoginVO searchVO) {
		return mapper.getList(searchVO);
	}

	@Override
	public int getTotalCnt(AdminLoginVO searchVO) {
		return mapper.getTotalCnt(searchVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AdminLoginVO getEntity(String id, String adminTypeCode) {
		AdminLoginVO searchVO = new AdminLoginVO();
		searchVO.setId(id);
		searchVO.setAdminTypeCode(adminTypeCode);
		AdminLoginVO rtn = StringUtil.isNotBlank(id) ? mapper.getEntity(searchVO) : null;
		if (rtn != null) {
			rtn.setAdminAccessLevelCode(AuthType.EXTERNAL.getCode());
			if (StringUtil.isNotBlank(rtn.getMids())) {
				rtn.setMidList(Arrays.asList(rtn.getMids().split(",")));
			}
		}

		return rtn;
	}

	@Override
	public void insert(AdminLoginVO insertVO) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		insertVO.setMids(insertVO.getMidList().toString().replace(" ", "").replace("[", "").replace("]", ""));
		mapper.insert(insertVO);
	}

	@Override
	public void update(AdminLoginVO updateVO) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		updateVO.setMids(updateVO.getMidList().toString().replace(" ", "").replace("[", "").replace("]", ""));
		mapper.update(updateVO);
	}

	@Override
	public void delete(AdminLoginVO deleteVO) {
		mapper.delete(deleteVO);
	}

	@Override
	public int upload(MultipartFile file, Boolean overwrite) throws IOException {
		String uploadDir = EgovProperties.getProperty(Constant.EXCEL_UPLOAD);
		String uploadedFileName = fileUploadHelperService.uploadFile(uploadDir, file);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			if (overwrite) { // 덮어쓰기
				mapper.deleteAll();
			}

			Workbook workbook = Workbook.getWorkbook(new File(uploadDir + uploadedFileName));
			List<Map<String, String>> recordList = ExcelUploadHelperUtil.dataList(workbook.getSheet(0));
			AdminLoginVO insertVO = null;
			for (Map<String, String> record : recordList) {
				insertVO = new AdminLoginVO();
				insertVO.setId(record.get("e1"));
				insertVO.setAdminId(record.get("e1"));
				insertVO.setName(record.get("e2"));
				insertVO.setPwd(record.get("e3"));
				insertVO.setAdminTypeCode(record.get("e4"));
				insertVO.setMids(record.get("e5"));
				insertVO.setPtIdxs(record.get("e6"));

				mapper.insert(insertVO);
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

	@Override
	public Boolean isUnique(AdminLoginVO searchVO) {
		String id = StringUtil.isBlank(searchVO.getId()) ? searchVO.getAdminId() : searchVO.getId();
		return StringUtil.isNotBlank(id) && StringUtil.isNotBlank(searchVO.getAdminTypeCode()) && //
			getEntity(id, searchVO.getAdminTypeCode()) == null;
	}

}
