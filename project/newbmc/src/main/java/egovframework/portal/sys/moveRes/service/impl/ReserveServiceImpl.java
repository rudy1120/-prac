package egovframework.portal.sys.moveRes.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.portal.sys.moveRes.mapper.ReserveMapper;
import egovframework.portal.sys.moveRes.service.ReserveService;
import egovframework.portal.sys.moveRes.vo.ReserveVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;

@Service
public class ReserveServiceImpl implements ReserveService {
	
	@Resource
	private ReserveMapper reserveMapper;
	
	private final Logger LOGGER = LogManager.getLogger();

	@Override
	public int getTCnt(ReserveVO reserveVO) throws Exception {
		return reserveMapper.getTCnt(reserveVO);
	}
	
	@Override
	public int getTotalCnt(ReserveVO reserveVO) throws Exception {
		return reserveMapper.getTotalCnt(reserveVO);
	}
	
	@Override
	public String deleteRes(ReserveVO reserveVO) {
		try {
			ReserveVO deleteVO = BeanUtil.copy(reserveVO, new ReserveVO());
			reserveMapper.deleteRes(deleteVO);

			return String.valueOf(deleteVO.getCust_id());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}
	
	@Override
	public String deleteCust(ReserveVO reserveVO) {
		try {
			ReserveVO deleteVO = BeanUtil.copy(reserveVO, new ReserveVO());
			reserveMapper.deleteCust(deleteVO);

			return String.valueOf(deleteVO.getCust_id());
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	@Override
	public List<ReserveVO> getList(ReserveVO reserveVO) throws Exception {
		return reserveMapper.getList(reserveVO);
	}
	
	@Override
	public List<ReserveVO> getDetailList(ReserveVO reserveVO) throws Exception {
		return reserveMapper.getDetailList(reserveVO);
	}
	
	@Override
	public List<ReserveVO> getExcelList(ReserveVO reserveVO) throws Exception {
		
		return reserveMapper.getExcelList(reserveVO);
	}
	
	@Override
	public List<ReserveVO> chkTime(ReserveVO reserveVO) throws Exception {
		
		List<ReserveVO> result;
		
		result = reserveMapper.chkTime(reserveVO);
		
		return result;
	}
	
	@Override
	public ReserveVO getEntity(ReserveVO reserveVO) {
		ReserveVO rtn = StringUtil.isNotBlank(String.valueOf(reserveVO.getCust_id())) ? reserveMapper.getEntity(reserveVO) : null;
		return rtn;
	}
	
	@Override
	public ReserveVO chkCust(ReserveVO reserveVO) {
		ReserveVO rtn = StringUtil.isNotBlank(String.valueOf(reserveVO.getCust_id())) ? reserveMapper.chkCust(reserveVO) : null;
		return rtn;
	}
	
	@Override
	public ReserveVO chkReserve(ReserveVO reserveVO) {
		ReserveVO result;
		
		result = reserveMapper.chkReserve(reserveVO);
		
		return result;
	}
	
	@Override
	public ReserveVO getTitle(ReserveVO reserveVO) {
		ReserveVO result;
		
		result = reserveMapper.getTitle(reserveVO);
		
		return result;
	}
	
	@Override
	public void resUp(ReserveVO reserveVO) {
		reserveMapper.resUp(reserveVO);
	}
	
	@Override
	public void resInsert(ReserveVO reserveVO) {
		reserveMapper.resInsert(reserveVO);
	}
	
	@Override
	public void regiInsert(ReserveVO reserveVO) {
		reserveMapper.regiInsert(reserveVO);
	}
	
	@Override
	public int chkRes(ReserveVO reserveVO) {
		int result;
		result = reserveMapper.chkRes(reserveVO);
		
		return result;
	}
	

}
