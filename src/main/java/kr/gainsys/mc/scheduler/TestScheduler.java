package kr.gainsys.mc.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {

	// system out 과 로그 파일에 동시 기록됨
	private Logger logger = LoggerFactory.getLogger("kr.gainsys.mc.scheduler");

	@Scheduled(cron = "0/10 * * * * *")		// 0/10 0초에 시작해서 10초 마다 실행
	public void testSchedule() {

		logger.debug("# testSchedule {}", "start");
	}

}
