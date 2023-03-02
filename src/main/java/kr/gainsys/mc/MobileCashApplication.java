package kr.gainsys.mc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing	// config 로 이동 (security filter 에서 scan 되는 것을 방지)
@SpringBootApplication
public class MobileCashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileCashApplication.class, args);
	}

}
