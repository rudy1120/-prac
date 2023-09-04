package egovframework.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import egovframework.scheduler.service.SchedulerService;

@Component
public class Scheduler {
	
	@Autowired
	protected SchedulerService schedulerService;
	
	@Scheduled(cron = "0 0 0 * * *")	//0 0 0 * * * 매일 00시 자정에 실행 //0 * * * * * -> 1분 마다
    public void autoDelete() throws Exception {
		//분양임대서비스 자동삭제
		schedulerService.autoDel_sale();
    }
}
