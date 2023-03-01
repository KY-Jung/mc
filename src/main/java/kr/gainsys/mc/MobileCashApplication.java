package kr.gainsys.mc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MobileCashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileCashApplication.class, args);
	}

}
