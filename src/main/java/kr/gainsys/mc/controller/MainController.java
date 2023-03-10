package kr.gainsys.mc.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MainController {

	////////////////////////////////////////////////////////////////////////////////
	@Value("${server.servlet.context-path}")
	private String CONTEXT_PATH;
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/")
	public String doMain(HttpServletRequest request) {
		log.info("doMain");

		System.out.println("# " + CONTEXT_PATH + "/");
		System.out.println("# redirect: " + CONTEXT_PATH + "/test_thymeleaf");
		return "redirect:/test_thymeleaf";		// CONTEXT_PATH + "/test_thymeleaf" 로 이동
	}
	////////////////////////////////////////////////////////////////////////////////


}
