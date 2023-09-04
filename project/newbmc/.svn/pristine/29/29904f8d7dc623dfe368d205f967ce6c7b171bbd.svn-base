package egovframework.portal.sys.buy.service.impl;

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
import egovframework.portal.sys.buy.mapper.SysBuyMapper;
import egovframework.portal.sys.buy.service.SysBuyService;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class SysBuyServiceImpl extends EgovAbstractServiceImpl implements SysBuyService{
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Resource
	private SysBuyMapper sysBuyMapper;
	
	@Resource(name = "txManager")
	private PlatformTransactionManager transactionManager;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	//분양목록 데이터
	@Override
	public List<SaleVO> getBuyList(SaleVO searchVO) throws Exception {
		return sysBuyMapper.getBuyList(searchVO);
	}

	//분양목록 데이터 카운트
	@Override
	public int getTotalCnt(SaleVO searchVO) throws Exception {
		return sysBuyMapper.getTotalCnt(searchVO);
	}

	//분양상세화면
	@Override
	public List<SaleVO> getSaleList(SaleVO saleVO) throws Exception {
		return sysBuyMapper.getSaleList(saleVO);
	}

	@Override
	public SaleVO getView(SaleVO saleVO) throws Exception {
		return (SaleVO) sysBuyMapper.getView(saleVO);
	}

	//분양매물 신규등록
	@Override
	public boolean insertSale(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception {
		
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		
		boolean result = false;

			try {
				
				SaleVO vo = BeanUtil.copy(saleVO, new SaleVO());
				vo.setLocation((uploadFile(request, "locationFile")));
				
				//분양매물 신규등록
				sysBuyMapper.insertSale(vo);
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
	//분양매물 수정
	@Override
	public boolean updateSale(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception {
		
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		boolean result = false;
		
		try {
			SaleVO vo = BeanUtil.copy(saleVO, new SaleVO());
			
			String locationFile = (uploadFile(request, "locationFile"));
			vo.setLocation(("".equals(locationFile) ? vo.getLocation() : locationFile));

			/** 파일 설명 글 변경에 따른 처리 **/
			if (StringUtil.isNotBlank(vo.getLocationFileCn())) {
				fileMngService.updateFileCn(new FileVO(vo.getLocation(), "0", vo.getLocationFileCn()));
			}
			
			//분양매물 업데이트
			sysBuyMapper.updateSale(vo);
			
			transactionManager.commit(txStatus);
			result = Boolean.TRUE;

		}catch(NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			result =  Boolean.FALSE;
		}

		return result;
	}
	/**
	 * 업로드 해야 할 파일을 가져옵니다.
	 *
	 * @saleVO request
	 * @saleVO type
	 * @return
	 * @throws Exception
	 */
	private String uploadFile(MultipartHttpServletRequest request, String type) throws Exception {
		if (request != null) {
			Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
			String fileCn = "";
			if ("locationFile".equals(type) && request.getFileMap().get("location_file") != null) {
				files.put("locationFile", (request.getFileMap().get("location_file") != null && request.getFileMap().get("location_file").getSize() > 0 ? request.getFileMap().get("location_file") : null));
				fileCn = (request.getParameter("locationFileCn") != null ? request.getParameter("locationFileCn") : "");
			} else {
				return "";
			}
			if (!files.isEmpty() && (files.get("locationFile") != null)) {
				List<FileVO> _result = fileUtil.parseFileInf(files, "BUY_", 0, "", "/home/webdata/egov_uploadFile/BUY/", false, request);
				if (_result != null) {
					_result.get(0).setFileCn(fileCn);
					return fileMngService.insertFileInfs(_result); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
				}
			}
		}
		return "";
	}



	//매물목록 데이터 상세조회
	@Override
	public SaleVO getSale(SaleVO saleVO) throws Exception {
		return sysBuyMapper.getSale(saleVO);
	}

	//분양매물 데이터 삭제
	@Override
	public boolean delSale(HttpServletRequest request,SaleVO saleVO) throws Exception {

		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		boolean result = false;
		
		try {
			SaleVO deleteVO = BeanUtil.copy(saleVO, new SaleVO());
			sysBuyMapper.delSale(saleVO);
			
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

	//분양정보 신규저장
	@Override
	public boolean insertSell(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		
		boolean result = false;

			try {
				
				SaleVO vo = BeanUtil.copy(saleVO, new SaleVO());
				vo.setMapInfo((uploadFile(request, "locationFile")));
				//분양정보 신규등록
				sysBuyMapper.insertSell(vo);
				transactionManager.commit(txStatus);

				/*int insertCnt = vo.getUsesCk().split(",").length;
				
				String[] arr = vo.getUsesCk().split(",");
				
				for(int i=0; i<insertCnt; i++) {
					
					vo.setUsescode(arr[i]);
					// bmc.areauses 에 사업별 공급용도 저장
					sysBuyMapper.insertAreaUses(vo);
					//applySmsMapper.insertApply(applySmsVO);
				}*/
				
				result = Boolean.TRUE;
			} catch (NullPointerException | DataAccessException e) {
				transactionManager.rollback(txStatus);
				LOGGER.error(e);
				result = Boolean.FALSE;
			}
		return result;
	}

	//분양정보 수정
	@Override
	public boolean updateSell(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception {
		
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		
		boolean result = false;

			try {
				
				SaleVO vo = BeanUtil.copy(saleVO, new SaleVO());
				String locationFile = (uploadFile(request, "locationFile"));
				vo.setMapInfo(("".equals(locationFile) ? vo.getMapInfo() : locationFile));

				/** 파일 설명 글 변경에 따른 처리 **/
				if (StringUtil.isNotBlank(vo.getLocationFileCn())) {
					fileMngService.updateFileCn(new FileVO(vo.getMapInfo(), "0", vo.getLocationFileCn()));
				}
				//분양정보 수정
				sysBuyMapper.updateSell(vo);
				
				// 기존 사업별 공급용도 삭제
				/*sysBuyMapper.deleteAreaUses(vo);

				int insertCnt = vo.getUsesCk().split(",").length;
				
				String[] arr = vo.getUsesCk().split(",");
				
				for(int i=0; i<insertCnt; i++) {
					
					vo.setUsescode(arr[i]);
					//사업별 공급용도 저장
					sysBuyMapper.updateAreaUses(vo);
				}
				*/
				transactionManager.commit(txStatus);
				result = Boolean.TRUE;
			} catch (NullPointerException | DataAccessException e) {
				transactionManager.rollback(txStatus);
				LOGGER.error(e);
				result = Boolean.FALSE;
			}
		return result;
	}

	//분양정보 삭제
	@Override
	public boolean delSell(HttpServletRequest request, SaleVO saleVO) throws Exception {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		
		boolean result = false;
		try {
			SaleVO vo = BeanUtil.copy(saleVO, new SaleVO());

			//분양정보 삭제
			sysBuyMapper.delSell(vo);
			
			//해당 분양정보 분양매물 삭제
			sysBuyMapper.delSales(vo);
			
			transactionManager.commit(txStatus);
			
			result = Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			result = Boolean.FALSE;
		}
	return result;
	}

	//공급용도 카운트
	@Override
	public int getTotalUseCnt(SaleVO searchVO) throws Exception {
		return sysBuyMapper.getTotalUseCnt(searchVO);
	}

	//공급용도 목록
	@Override
	public List<SaleVO> getUsesList(SaleVO searchVO) throws Exception {
		return sysBuyMapper.getUsesList(searchVO);
	}

	//공급용도 선택
	@Override
	public SaleVO getEntity(SaleVO saleVO) throws Exception {
		SaleVO rtn = StringUtil.isNotBlank(String.valueOf(saleVO.getIdx())) ? sysBuyMapper.getEntity(saleVO) : null;
		return rtn;
	}

	//공급용도 중복체크
	@Override
	public int chkUses(SaleVO saleVO) throws Exception {
		return sysBuyMapper.chkUses(saleVO);
	}

	//공급용도 등록
	@Override
	public boolean insertUses(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception {
		return sysBuyMapper.insertUses(saleVO);
	}

	//공급용도 수정
	@Override
	public boolean updateUses(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception {
		return sysBuyMapper.updateUses(saleVO);
	}

	//공급용도 사용유무
	@Override
	public int chkUses2(SaleVO saleVO) throws Exception {
		return sysBuyMapper.chkUses2(saleVO);
	}

	//공급용도 삭제
	@Override
	public boolean delUses(HttpServletRequest request, SaleVO saleVO) throws Exception {
		return sysBuyMapper.delUses(saleVO);
	}

	//공급용도 
	@Override
	public List<ApplySmsVO> getPurposerList() throws Exception {
		return sysBuyMapper.getPurposerList();
	}




}
