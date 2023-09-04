package egovframework.scheduler.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.scheduler.mapper.SchedulerMapper;
import egovframework.scheduler.service.SchedulerService;
import egovframework.scheduler.vo.TermVO;

@Service
public class SchedulerServiceImpl implements SchedulerService{
	
	@Resource
	private SchedulerMapper schedulermapper;

	@Override
	public void autoDel_sale() throws Exception {
		// 76: 6개월, 77: 1년, 78:2년
		
		TermVO termVO = new TermVO();
		
		//분양, 임대 서비스 신청자 수(6개월, 1년, 2년 합계)
		int sel_sale = schedulermapper.autoSel_sale();
		int sel_housing = schedulermapper.autoSel_housing();

		if(sel_sale > 0 || sel_housing > 0) {
			termVO.setSale(sel_sale);
			termVO.setHousing(sel_housing);
			schedulermapper.autoLog(termVO);
			schedulermapper.autoDel_sale76();
			schedulermapper.autoDel_sale77();
			schedulermapper.autoDel_sale78();
			schedulermapper.autoDel_housing76();
			schedulermapper.autoDel_housing77();
			schedulermapper.autoDel_housing78();
		}
		
	}

}